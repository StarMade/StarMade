package org.hsqldb.server;

public abstract interface ServerConstants
{
  public static final int SERVER_STATE_ONLINE = 1;
  public static final int SERVER_STATE_OPENING = 4;
  public static final int SERVER_STATE_CLOSING = 8;
  public static final int SERVER_STATE_SHUTDOWN = 16;
  public static final int SC_DATABASE_SHUTDOWN = 0;
  public static final String SC_DEFAULT_ADDRESS = "0.0.0.0";
  public static final String SC_DEFAULT_DATABASE = "test";
  public static final int SC_DEFAULT_HSQL_SERVER_PORT = 9001;
  public static final int SC_DEFAULT_HSQLS_SERVER_PORT = 554;
  public static final int SC_DEFAULT_HTTP_SERVER_PORT = 80;
  public static final int SC_DEFAULT_HTTPS_SERVER_PORT = 443;
  public static final int SC_DEFAULT_BER_SERVER_PORT = 9101;
  public static final boolean SC_DEFAULT_SERVER_AUTORESTART = false;
  public static final boolean SC_DEFAULT_NO_SYSTEM_EXIT = true;
  public static final boolean SC_DEFAULT_SILENT = true;
  public static final boolean SC_DEFAULT_TLS = false;
  public static final boolean SC_DEFAULT_TRACE = false;
  public static final boolean SC_DEFAULT_REMOTE_OPEN_DB = false;
  public static final int SC_DEFAULT_MAX_DATABASES = 10;
  public static final int SC_PROTOCOL_HTTP = 0;
  public static final int SC_PROTOCOL_HSQL = 1;
  public static final int SC_PROTOCOL_BER = 2;
  public static final String SC_DEFAULT_WEB_MIME = "text/html";
  public static final String SC_DEFAULT_WEB_PAGE = "index.html";
  public static final String SC_DEFAULT_WEB_ROOT = ".";
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.ServerConstants
 * JD-Core Version:    0.6.2
 */