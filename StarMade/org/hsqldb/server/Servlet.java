package org.hsqldb.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hsqldb.Database;
import org.hsqldb.DatabaseManager;
import org.hsqldb.DatabaseURL;
import org.hsqldb.HsqlException;
import org.hsqldb.Session;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.result.Result;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputBinary;

public class Servlet
  extends HttpServlet
{
  private static final int BUFFER_SIZE = 256;
  private String dbType;
  private String dbPath;
  private String errorStr;
  private RowOutputBinary rowOut;
  private RowInputBinary rowIn;
  private int iQueries;
  private static long lModified = 0L;
  
  public void init(ServletConfig paramServletConfig)
  {
    try
    {
      super.init(paramServletConfig);
      this.rowOut = new RowOutputBinary(256, 1);
      this.rowIn = new RowInputBinary(this.rowOut);
    }
    catch (ServletException localServletException)
    {
      log(localServletException.toString());
    }
    String str1 = getInitParameter("hsqldb.server.database");
    if (str1 == null) {
      str1 = ".";
    }
    String str2 = getInitParameter("hsqldb.server.use_web-inf_path");
    if ((!str1.equals(".")) && ("true".equalsIgnoreCase(str2))) {
      str1 = getServletContext().getRealPath("/") + "WEB-INF/" + str1;
    }
    HsqlProperties localHsqlProperties = DatabaseURL.parseURL(str1, false, false);
    log("Database filename = " + str1);
    if (localHsqlProperties == null)
    {
      this.errorStr = "Bad Database name";
    }
    else
    {
      this.dbPath = localHsqlProperties.getProperty("database");
      this.dbType = localHsqlProperties.getProperty("connection_type");
      try
      {
        DatabaseManager.getDatabase(this.dbType, this.dbPath, localHsqlProperties);
      }
      catch (HsqlException localHsqlException)
      {
        this.errorStr = localHsqlException.getMessage();
      }
    }
    if (this.errorStr == null)
    {
      log("Initialization completed.");
    }
    else
    {
      log("Database could not be initialised.");
      log(this.errorStr);
    }
  }
  
  protected long getLastModified(HttpServletRequest paramHttpServletRequest)
  {
    return lModified++;
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws IOException, ServletException
  {
    String str = paramHttpServletRequest.getQueryString();
    if ((str == null) || (str.length() == 0))
    {
      paramHttpServletResponse.setContentType("text/html");
      paramHttpServletResponse.setHeader("Pragma", "no-cache");
      PrintWriter localPrintWriter = paramHttpServletResponse.getWriter();
      localPrintWriter.println("<html><head><title>HSQL Database Engine Servlet</title>");
      localPrintWriter.println("</head><body><h1>HSQL Database Engine Servlet</h1>");
      localPrintWriter.println("The servlet is running.<p>");
      if (this.errorStr == null)
      {
        localPrintWriter.println("The database is also running.<p>");
        localPrintWriter.println("Database name: " + this.dbType + this.dbPath + "<p>");
        localPrintWriter.println("Queries processed: " + this.iQueries + "<p>");
      }
      else
      {
        localPrintWriter.println("<h2>The database is not running!</h2>");
        localPrintWriter.println("The error message is:<p>");
        localPrintWriter.println(this.errorStr);
      }
      localPrintWriter.println("</body></html>");
    }
  }
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws IOException, ServletException
  {
    synchronized (this)
    {
      DataInputStream localDataInputStream = null;
      DataOutputStream localDataOutputStream1 = null;
      try
      {
        localDataInputStream = new DataInputStream(paramHttpServletRequest.getInputStream());
        int i = localDataInputStream.readInt();
        long l1 = localDataInputStream.readLong();
        int j = localDataInputStream.readByte();
        Session localSession = DatabaseManager.getSession(i, l1);
        Result localResult1 = Result.newResult(localSession, j, localDataInputStream, this.rowIn);
        localResult1.setDatabaseId(i);
        localResult1.setSessionId(l1);
        int k = localResult1.getType();
        Result localResult2;
        if (k == 31)
        {
          try
          {
            localSession = DatabaseManager.newSession(this.dbType, this.dbPath, localResult1.getMainString(), localResult1.getSubString(), new HsqlProperties(), localResult1.getZoneString(), localResult1.getUpdateCount());
            localResult1.readAdditionalResults(null, localDataInputStream, this.rowIn);
            localResult2 = Result.newConnectionAcknowledgeResponse(localSession.getDatabase(), localSession.getId(), localSession.getDatabase().getDatabaseID());
          }
          catch (HsqlException localHsqlException2)
          {
            localResult2 = Result.newErrorResult(localHsqlException2);
          }
        }
        else
        {
          if ((k == 32) || (k == 10))
          {
            paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
            paramHttpServletResponse.setContentType("application/octet-stream");
            paramHttpServletResponse.setContentLength(6);
            localDataOutputStream1 = new DataOutputStream(paramHttpServletResponse.getOutputStream());
            localDataOutputStream1.writeByte(32);
            localDataOutputStream1.writeInt(4);
            localDataOutputStream1.writeByte(0);
            localDataOutputStream1.close();
            if (localDataOutputStream1 != null) {
              localDataOutputStream1.close();
            }
            if (localDataInputStream != null) {
              localDataInputStream.close();
            }
            return;
          }
          int m = localResult1.getDatabaseId();
          long l2 = localResult1.getSessionId();
          localSession = DatabaseManager.getSession(m, l2);
          localResult1.readLobResults(localSession, localDataInputStream, this.rowIn);
          localResult2 = localSession.execute(localResult1);
        }
        HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream();
        DataOutputStream localDataOutputStream2 = new DataOutputStream(localHsqlByteArrayOutputStream);
        localResult2.write(localSession, localDataOutputStream2, this.rowOut);
        paramHttpServletResponse.setHeader("Cache-Control", "no-cache");
        paramHttpServletResponse.setContentType("application/octet-stream");
        paramHttpServletResponse.setContentLength(localHsqlByteArrayOutputStream.size());
        localDataOutputStream1 = new DataOutputStream(paramHttpServletResponse.getOutputStream());
        localHsqlByteArrayOutputStream.writeTo(localDataOutputStream1);
        this.iQueries += 1;
      }
      catch (HsqlException localHsqlException1) {}finally
      {
        if (localDataOutputStream1 != null) {
          localDataOutputStream1.close();
        }
        if (localDataInputStream != null) {
          localDataInputStream.close();
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.Servlet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */