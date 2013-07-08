package org.hsqldb.server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.hsqldb.DatabaseManager;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.lib.InOutUtil;
import org.hsqldb.resources.BundleHandler;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;

class WebServerConnection
  implements Runnable
{
  static final String ENCODING = "ISO-8859-1";
  private Charset iso = Charset.forName("ISO-8859-1");
  private CharsetDecoder iso_8859_1_decoder = this.iso.newDecoder();
  private Socket socket;
  private WebServer server;
  private static final int REQUEST_TYPE_BAD = 0;
  private static final int REQUEST_TYPE_GET = 1;
  private static final int REQUEST_TYPE_HEAD = 2;
  private static final int REQUEST_TYPE_POST = 3;
  private static final String HEADER_OK = "HTTP/1.0 200 OK";
  private static final String HEADER_BAD_REQUEST = "HTTP/1.0 400 Bad Request";
  private static final String HEADER_NOT_FOUND = "HTTP/1.0 404 Not Found";
  private static final String HEADER_FORBIDDEN = "HTTP/1.0 403 Forbidden";
  static final int BUFFER_SIZE = 256;
  final byte[] mainBuffer = new byte[256];
  private RowOutputBinary rowOut = new RowOutputBinary(this.mainBuffer);
  private RowInputBinary rowIn = new RowInputBinary(this.rowOut);
  static byte[] BYTES_GET;
  static byte[] BYTES_HEAD;
  static byte[] BYTES_POST;
  static byte[] BYTES_CONTENT;
  static final byte[] BYTES_WHITESPACE = { 32, 9 };
  private static final int hnd_content_types = BundleHandler.getBundleHandle("content-types", null);
  
  WebServerConnection(Socket paramSocket, WebServer paramWebServer)
  {
    this.server = paramWebServer;
    this.socket = paramSocket;
  }
  
  private String getMimeTypeString(String paramString)
  {
    if (paramString == null) {
      return "text/html";
    }
    int i = paramString.lastIndexOf('.');
    String str1 = null;
    String str2 = null;
    if (i >= 0)
    {
      str1 = paramString.substring(i).toLowerCase();
      str2 = this.server.serverProperties.getProperty(str1);
    }
    if ((str2 == null) && (str1.length() > 1)) {
      str2 = BundleHandler.getString(hnd_content_types, str1.substring(1));
    }
    return str2 == null ? "text/html" : str2;
  }
  
  public void run()
  {
    DataInputStream localDataInputStream = null;
    try
    {
      localDataInputStream = new DataInputStream(this.socket.getInputStream());
      String str = null;
      int j = 0;
      do
      {
        i = InOutUtil.readLine(localDataInputStream, this.rowOut);
        if (i == 0) {
          throw new Exception();
        }
      } while (i < 2);
      byte[] arrayOfByte = this.rowOut.toByteArray();
      int k = this.rowOut.size() - i;
      if (ArrayUtil.containsAt(arrayOfByte, k, BYTES_POST))
      {
        j = 3;
        k += BYTES_POST.length;
      }
      else if (ArrayUtil.containsAt(arrayOfByte, k, BYTES_GET))
      {
        j = 1;
        k += BYTES_GET.length;
      }
      else if (ArrayUtil.containsAt(arrayOfByte, k, BYTES_HEAD))
      {
        j = 2;
        k += BYTES_HEAD.length;
      }
      else
      {
        j = 0;
      }
      int i = ArrayUtil.countStartElementsAt(arrayOfByte, k, BYTES_WHITESPACE);
      if (i == 0) {
        j = 0;
      }
      k += i;
      i = ArrayUtil.countNonStartElementsAt(arrayOfByte, k, BYTES_WHITESPACE);
      str = new String(arrayOfByte, k, i, "ISO-8859-1");
      switch (j)
      {
      case 3: 
        processPost(localDataInputStream, str);
        break;
      case 0: 
        processError(0);
        break;
      case 1: 
        processGet(str, true);
        break;
      case 2: 
        processGet(str, false);
      }
      return;
    }
    catch (Exception localException)
    {
      this.server.printStackTrace(localException);
    }
    finally
    {
      try
      {
        if (localDataInputStream != null) {
          localDataInputStream.close();
        }
        this.socket.close();
      }
      catch (IOException localIOException3)
      {
        this.server.printStackTrace(localIOException3);
      }
    }
  }
  
  private void processPost(InputStream paramInputStream, String paramString)
    throws IOException
  {
    try
    {
      int i;
      do
      {
        i = InOutUtil.readLine(paramInputStream, this.rowOut);
      } while (i > 2);
      String str = this.iso_8859_1_decoder.decode(ByteBuffer.wrap(this.rowOut.toByteArray())).toString();
      if (str.indexOf("Content-Type: application/octet-stream") < 0) {
        throw new Exception();
      }
    }
    catch (Exception localException)
    {
      processError(400);
      return;
    }
    processQuery(paramInputStream);
  }
  
  void processQuery(InputStream paramInputStream)
  {
    try
    {
      DataInputStream localDataInputStream = new DataInputStream(paramInputStream);
      int i = localDataInputStream.readInt();
      long l = localDataInputStream.readLong();
      int j = localDataInputStream.readByte();
      Session localSession = DatabaseManager.getSession(i, l);
      Result localResult1 = Result.newResult(localSession, j, localDataInputStream, this.rowIn);
      localResult1.setDatabaseId(i);
      localResult1.setSessionId(l);
      Result localResult2;
      if (localResult1.getType() == 31)
      {
        try
        {
          String str1 = localResult1.getDatabaseName();
          int m = this.server.getDBIndex(str1);
          int n = this.server.dbID[m];
          localSession = DatabaseManager.newSession(n, localResult1.getMainString(), localResult1.getSubString(), localResult1.getZoneString(), localResult1.getUpdateCount());
          localResult1.readAdditionalResults(localSession, localDataInputStream, this.rowIn);
          localResult2 = Result.newConnectionAcknowledgeResponse(localSession.getDatabase(), localSession.getId(), n);
        }
        catch (HsqlException localHsqlException)
        {
          localResult2 = Result.newErrorResult(localHsqlException);
        }
        catch (RuntimeException localRuntimeException)
        {
          localResult2 = Result.newErrorResult(localRuntimeException);
        }
      }
      else
      {
        k = localResult1.getDatabaseId();
        if (localSession == null)
        {
          localResult2 = Result.newErrorResult(Error.error(402));
        }
        else
        {
          localResult1.setSession(localSession);
          localResult1.readLobResults(localSession, localDataInputStream, this.rowIn);
          localResult2 = localSession.execute(localResult1);
        }
      }
      int k = localResult1.getType();
      if ((k == 32) || (k == 10))
      {
        localObject1 = new DataOutputStream(this.socket.getOutputStream());
        localObject2 = getHead("HTTP/1.0 200 OK", false, "application/octet-stream", 6);
        ((DataOutputStream)localObject1).write(((String)localObject2).getBytes("ISO-8859-1"));
        ((DataOutputStream)localObject1).writeByte(32);
        ((DataOutputStream)localObject1).writeInt(4);
        ((DataOutputStream)localObject1).writeByte(0);
        ((DataOutputStream)localObject1).close();
        return;
      }
      Object localObject1 = new HsqlByteArrayOutputStream();
      Object localObject2 = new DataOutputStream((OutputStream)localObject1);
      localResult2.write(localSession, (DataOutputStream)localObject2, this.rowOut);
      DataOutputStream localDataOutputStream = new DataOutputStream(this.socket.getOutputStream());
      String str2 = getHead("HTTP/1.0 200 OK", false, "application/octet-stream", ((HsqlByteArrayOutputStream)localObject1).size());
      localDataOutputStream.write(str2.getBytes("ISO-8859-1"));
      ((HsqlByteArrayOutputStream)localObject1).writeTo(localDataOutputStream);
      localDataOutputStream.close();
    }
    catch (Exception localException)
    {
      this.server.printStackTrace(localException);
    }
  }
  
  private void processGet(String paramString, boolean paramBoolean)
  {
    try
    {
      if (paramString.endsWith("/")) {
        paramString = paramString + this.server.getDefaultWebPage();
      }
      if (paramString.indexOf("..") != -1)
      {
        processError(403);
        return;
      }
      paramString = this.server.getWebRoot() + paramString;
      if (File.separatorChar != '/') {
        paramString = paramString.replace('/', File.separatorChar);
      }
      DataInputStream localDataInputStream = null;
      this.server.printWithThread("GET " + paramString);
      String str;
      try
      {
        File localFile = new File(paramString);
        localDataInputStream = new DataInputStream(new FileInputStream(localFile));
        str = getHead("HTTP/1.0 200 OK", true, getMimeTypeString(paramString), (int)localFile.length());
      }
      catch (IOException localIOException)
      {
        processError(404);
        if (localDataInputStream != null) {
          localDataInputStream.close();
        }
        return;
      }
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(this.socket.getOutputStream());
      localBufferedOutputStream.write(str.getBytes("ISO-8859-1"));
      if (paramBoolean)
      {
        int i;
        while ((i = localDataInputStream.read()) != -1) {
          localBufferedOutputStream.write(i);
        }
      }
      localBufferedOutputStream.flush();
      localBufferedOutputStream.close();
      localDataInputStream.close();
    }
    catch (Exception localException)
    {
      this.server.printError("processGet: " + localException.toString());
      this.server.printStackTrace(localException);
    }
  }
  
  String getHead(String paramString1, boolean paramBoolean, String paramString2, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append(paramString1).append("\r\n");
    if (paramBoolean)
    {
      localStringBuffer.append("Allow: GET, HEAD, POST\nMIME-Version: 1.0\r\n");
      localStringBuffer.append("Server: ").append("HSQL Database Engine").append("\r\n");
    }
    if (paramString2 != null)
    {
      localStringBuffer.append("Cache-Control: no-cache\r\n");
      localStringBuffer.append("Content-Type: ").append(paramString2).append("\r\n");
    }
    localStringBuffer.append("\r\n");
    return localStringBuffer.toString();
  }
  
  private void processError(int paramInt)
  {
    this.server.printWithThread("processError " + paramInt);
    String str;
    switch (paramInt)
    {
    case 400: 
      str = getHead("HTTP/1.0 400 Bad Request", false, null, 0);
      str = str + BundleHandler.getString(WebServer.webBundleHandle, "BAD_REQUEST");
      break;
    case 403: 
      str = getHead("HTTP/1.0 403 Forbidden", false, null, 0);
      str = str + BundleHandler.getString(WebServer.webBundleHandle, "FORBIDDEN");
      break;
    case 401: 
    case 402: 
    case 404: 
    default: 
      str = getHead("HTTP/1.0 404 Not Found", false, null, 0);
      str = str + BundleHandler.getString(WebServer.webBundleHandle, "NOT_FOUND");
    }
    try
    {
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(this.socket.getOutputStream());
      localBufferedOutputStream.write(str.getBytes("ISO-8859-1"));
      localBufferedOutputStream.flush();
      localBufferedOutputStream.close();
    }
    catch (Exception localException)
    {
      this.server.printError("processError: " + localException.toString());
      this.server.printStackTrace(localException);
    }
  }
  
  String getConnectionThreadName()
  {
    return "HSQLDB HTTP Connection @" + Integer.toString(hashCode(), 16);
  }
  
  static
  {
    try
    {
      BYTES_GET = "GET".getBytes("ISO-8859-1");
      BYTES_HEAD = "HEAD".getBytes("ISO-8859-1");
      BYTES_POST = "POST".getBytes("ISO-8859-1");
      BYTES_CONTENT = "Content-Length: ".getBytes("ISO-8859-1");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      Error.runtimeError(201, "RowOutputTextLog");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.server.WebServerConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */