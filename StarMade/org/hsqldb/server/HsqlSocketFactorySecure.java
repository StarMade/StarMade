package org.hsqldb.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Principal;
import java.security.Provider;
import java.security.Security;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public final class HsqlSocketFactorySecure
  extends HsqlSocketFactory
  implements HandshakeCompletedListener
{
  protected Object socketFactory;
  protected Object serverSocketFactory;
  protected final Object socket_factory_mutex = new Object();
  protected final Object server_socket_factory_mutex = new Object();
  
  protected HsqlSocketFactorySecure()
    throws Exception
  {
    if (Security.getProvider("SunJSSE") == null) {
      try
      {
        Provider localProvider = (Provider)Class.forName("com.sun.net.ssl.internal.ssl.Provider").newInstance();
        Security.addProvider(localProvider);
      }
      catch (Exception localException) {}
    }
  }
  
  public void configureSocket(Socket paramSocket)
  {
    super.configureSocket(paramSocket);
    SSLSocket localSSLSocket = (SSLSocket)paramSocket;
    localSSLSocket.addHandshakeCompletedListener(this);
  }
  
  public ServerSocket createServerSocket(int paramInt)
    throws Exception
  {
    SSLServerSocket localSSLServerSocket = (SSLServerSocket)getServerSocketFactoryImpl().createServerSocket(paramInt);
    if (Error.TRACESYSTEMOUT)
    {
      Error.printSystemOut("[" + this + "]: createServerSocket()");
      Error.printSystemOut("capabilities for " + localSSLServerSocket + ":");
      Error.printSystemOut("----------------------------");
      dump("supported cipher suites", localSSLServerSocket.getSupportedCipherSuites());
      dump("enabled cipher suites", localSSLServerSocket.getEnabledCipherSuites());
    }
    return localSSLServerSocket;
  }
  
  public ServerSocket createServerSocket(int paramInt, String paramString)
    throws Exception
  {
    InetAddress localInetAddress = InetAddress.getByName(paramString);
    SSLServerSocket localSSLServerSocket = (SSLServerSocket)getServerSocketFactoryImpl().createServerSocket(paramInt, 128, localInetAddress);
    if (Error.TRACESYSTEMOUT)
    {
      Error.printSystemOut("[" + this + "]: createServerSocket()");
      Error.printSystemOut("capabilities for " + localSSLServerSocket + ":");
      Error.printSystemOut("----------------------------");
      dump("supported cipher suites", localSSLServerSocket.getSupportedCipherSuites());
      dump("enabled cipher suites", localSSLServerSocket.getEnabledCipherSuites());
    }
    return localSSLServerSocket;
  }
  
  private static void dump(String paramString, String[] paramArrayOfString)
  {
    Error.printSystemOut(paramString);
    Error.printSystemOut("----------------------------");
    for (int i = 0; i < paramArrayOfString.length; i++) {
      Error.printSystemOut(String.valueOf(paramArrayOfString[i]));
    }
    Error.printSystemOut("----------------------------");
  }
  
  public Socket createSocket(String paramString, int paramInt)
    throws Exception
  {
    SSLSocket localSSLSocket = (SSLSocket)getSocketFactoryImpl().createSocket(paramString, paramInt);
    localSSLSocket.addHandshakeCompletedListener(this);
    localSSLSocket.startHandshake();
    verify(paramString, localSSLSocket.getSession());
    return localSSLSocket;
  }
  
  public boolean isSecure()
  {
    return true;
  }
  
  protected SSLServerSocketFactory getServerSocketFactoryImpl()
    throws Exception
  {
    Object localObject1;
    synchronized (this.server_socket_factory_mutex)
    {
      localObject1 = this.serverSocketFactory;
      if (localObject1 == null)
      {
        localObject1 = SSLServerSocketFactory.getDefault();
        this.serverSocketFactory = localObject1;
      }
    }
    return (SSLServerSocketFactory)localObject1;
  }
  
  protected SSLSocketFactory getSocketFactoryImpl()
    throws Exception
  {
    Object localObject1;
    synchronized (this.socket_factory_mutex)
    {
      localObject1 = this.socketFactory;
      if (localObject1 == null)
      {
        localObject1 = SSLSocketFactory.getDefault();
        this.socketFactory = localObject1;
      }
    }
    return (SSLSocketFactory)localObject1;
  }
  
  protected void verify(String paramString, SSLSession paramSSLSession)
    throws Exception
  {
    X509Certificate[] arrayOfX509Certificate = paramSSLSession.getPeerCertificateChain();
    X509Certificate localX509Certificate = arrayOfX509Certificate[0];
    Principal localPrincipal = localX509Certificate.getSubjectDN();
    String str1 = String.valueOf(localPrincipal);
    int i = str1.indexOf("CN=");
    if (i < 0) {
      throw new UnknownHostException(Error.getMessage(63));
    }
    i += 3;
    int j = str1.indexOf(',', i);
    String str2 = str1.substring(i, j > -1 ? j : str1.length());
    if (str2.length() < 1) {
      throw new UnknownHostException(Error.getMessage(64));
    }
    if (!str2.equalsIgnoreCase(paramString)) {
      throw new UnknownHostException(Error.getMessage(65, 0, new Object[] { str2, paramString }));
    }
  }
  
  public void handshakeCompleted(HandshakeCompletedEvent paramHandshakeCompletedEvent)
  {
    if (Error.TRACESYSTEMOUT)
    {
      SSLSocket localSSLSocket = paramHandshakeCompletedEvent.getSocket();
      SSLSession localSSLSession = paramHandshakeCompletedEvent.getSession();
      Error.printSystemOut("SSL handshake completed:");
      Error.printSystemOut("------------------------------------------------");
      Error.printSystemOut("socket:      : " + localSSLSocket);
      Error.printSystemOut("cipher suite : " + localSSLSession.getCipherSuite());
      String str = StringConverter.byteArrayToHexString(localSSLSession.getId());
      Error.printSystemOut("session id   : " + str);
      Error.printSystemOut("------------------------------------------------");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.server.HsqlSocketFactorySecure
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */