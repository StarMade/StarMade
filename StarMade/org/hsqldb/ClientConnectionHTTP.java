package org.hsqldb;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowOutputInterface;

public class ClientConnectionHTTP
  extends ClientConnection
{
  static final String ENCODING = "ISO-8859-1";
  static final int IDLENGTH = 12;
  private HttpURLConnection httpConnection = null;
  
  public ClientConnectionHTTP(String paramString1, int paramInt1, String paramString2, String paramString3, boolean paramBoolean, String paramString4, String paramString5, int paramInt2)
  {
    super(paramString1, paramInt1, paramString2, paramString3, paramBoolean, paramString4, paramString5, paramInt2);
  }
  
  protected void initConnection(String paramString, int paramInt, boolean paramBoolean) {}
  
  protected void openConnection(String paramString, int paramInt, boolean paramBoolean)
  {
    try
    {
      URL localURL = null;
      String str = "";
      if (!this.path.endsWith("/")) {
        str = "/";
      }
      str = "http://" + paramString + ":" + paramInt + this.path + str + this.database;
      if (paramBoolean) {
        localURL = new URL("https://" + paramString + ":" + paramInt + this.path + str + this.database);
      } else {
        localURL = new URL(str);
      }
      this.httpConnection = ((HttpURLConnection)localURL.openConnection());
      this.httpConnection.setDefaultUseCaches(false);
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace(System.out);
    }
  }
  
  protected void closeConnection() {}
  
  public synchronized Result execute(Result paramResult)
  {
    openConnection(this.host, this.port, this.isTLS);
    Result localResult = super.execute(paramResult);
    closeConnection();
    return localResult;
  }
  
  protected void write(Result paramResult)
    throws IOException, HsqlException
  {
    HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
    DataOutputStream localDataOutputStream = new DataOutputStream(localHsqlByteArrayOutputStream);
    paramResult.write(this, localDataOutputStream, this.rowOut);
    this.httpConnection.setRequestMethod("POST");
    this.httpConnection.setDoOutput(true);
    this.httpConnection.setUseCaches(false);
    this.httpConnection.setRequestProperty("Content-Type", "application/octet-stream");
    this.httpConnection.setRequestProperty("Content-Length", String.valueOf(12 + localHsqlByteArrayOutputStream.size()));
    this.dataOutput = new DataOutputStream(this.httpConnection.getOutputStream());
    this.dataOutput.writeInt(paramResult.getDatabaseId());
    this.dataOutput.writeLong(paramResult.getSessionId());
    localHsqlByteArrayOutputStream.writeTo(this.dataOutput);
    this.dataOutput.flush();
  }
  
  protected Result read()
    throws IOException, HsqlException
  {
    this.dataInput = new DataInputStream(new BufferedInputStream(this.httpConnection.getInputStream()));
    this.rowOut.reset();
    Result localResult = Result.newResult(this.dataInput, this.rowIn);
    localResult.readAdditionalResults(this, this.dataInput, this.rowIn);
    this.dataInput.close();
    return localResult;
  }
  
  protected void handshake()
    throws IOException
  {}
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ClientConnectionHTTP
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */