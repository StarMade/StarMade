package org.hsqldb.server;

import java.io.PrintStream;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.resources.BundleHandler;

public class WebServer extends Server
{
  static int webBundleHandle = BundleHandler.getBundleHandle("webserver", null);

  public WebServer()
  {
    super(0);
  }

  public static void main(String[] paramArrayOfString)
  {
    HsqlProperties localHsqlProperties = null;
    localHsqlProperties = HsqlProperties.argArrayToProps(paramArrayOfString, "server");
    String[] arrayOfString = localHsqlProperties.getErrorKeys();
    if (arrayOfString.length != 0)
    {
      System.out.println("no value for argument:" + arrayOfString[0]);
      printHelp("webserver.help");
      return;
    }
    String str1 = localHsqlProperties.getProperty("server.props");
    String str2 = "";
    if (str1 == null)
    {
      str1 = "webserver";
      str2 = ".properties";
    }
    str1 = FileUtil.getFileUtil().canonicalOrAbsolutePath(str1);
    ServerProperties localServerProperties1 = ServerConfiguration.getPropertiesFromFile(0, str1, str2);
    ServerProperties localServerProperties2 = localServerProperties1 == null ? new ServerProperties(0) : localServerProperties1;
    localServerProperties2.addProperties(localHsqlProperties);
    ServerConfiguration.translateDefaultDatabaseProperty(localServerProperties2);
    ServerConfiguration.translateDefaultNoSystemExitProperty(localServerProperties2);
    ServerConfiguration.translateAddressProperty(localServerProperties2);
    WebServer localWebServer = new WebServer();
    try
    {
      localWebServer.setProperties(localServerProperties2);
    }
    catch (Exception localException)
    {
      localWebServer.printError("Failed to set properties");
      localWebServer.printStackTrace(localException);
      return;
    }
    localWebServer.print("Startup sequence initiated from main() method");
    if (localServerProperties1 != null)
    {
      localWebServer.print("Loaded properties from [" + str1 + ".properties]");
    }
    else
    {
      localWebServer.print("Could not load properties from file");
      localWebServer.print("Using cli/default properties only");
    }
    localWebServer.start();
  }

  public String getDefaultWebPage()
  {
    return this.serverProperties.getProperty("server.default_page");
  }

  public String getHelpString()
  {
    return BundleHandler.getString(serverBundleHandle, "webserver.help");
  }

  public String getProductName()
  {
    return "HSQLDB web server";
  }

  public String getProtocol()
  {
    return isTls() ? "HTTPS" : "HTTP";
  }

  public String getWebRoot()
  {
    return this.serverProperties.getProperty("server.root");
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.WebServer
 * JD-Core Version:    0.6.2
 */