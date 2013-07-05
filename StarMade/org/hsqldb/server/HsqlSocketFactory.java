package org.hsqldb.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HsqlSocketFactory
{
  private static HsqlSocketFactory plainImpl;
  private static HsqlSocketFactory sslImpl;

  protected HsqlSocketFactory()
    throws Exception
  {
  }

  public static HsqlSocketFactory getInstance(boolean paramBoolean)
    throws Exception
  {
    return paramBoolean ? getSSLImpl() : getPlainImpl();
  }

  public void configureSocket(Socket paramSocket)
  {
  }

  public ServerSocket createServerSocket(int paramInt)
    throws Exception
  {
    return new ServerSocket(paramInt);
  }

  public ServerSocket createServerSocket(int paramInt, String paramString)
    throws Exception
  {
    return new ServerSocket(paramInt, 128, InetAddress.getByName(paramString));
  }

  public Socket createSocket(String paramString, int paramInt)
    throws Exception
  {
    return new Socket(paramString, paramInt);
  }

  public boolean isSecure()
  {
    return false;
  }

  private static HsqlSocketFactory getPlainImpl()
    throws Exception
  {
    synchronized (HsqlSocketFactory.class)
    {
      if (plainImpl == null)
        plainImpl = new HsqlSocketFactory();
    }
    return plainImpl;
  }

  private static HsqlSocketFactory getSSLImpl()
    throws Exception
  {
    synchronized (HsqlSocketFactory.class)
    {
      if (sslImpl == null)
        sslImpl = newFactory("org.hsqldb.server.HsqlSocketFactorySecure");
    }
    return sslImpl;
  }

  private static HsqlSocketFactory newFactory(String paramString)
    throws Exception
  {
    Class localClass = Class.forName(paramString);
    Class[] arrayOfClass = new Class[0];
    Constructor localConstructor = localClass.getDeclaredConstructor(arrayOfClass);
    Object[] arrayOfObject = new Object[0];
    Object localObject;
    try
    {
      localObject = localConstructor.newInstance(arrayOfObject);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Throwable localThrowable = localInvocationTargetException.getTargetException();
      throw ((localThrowable instanceof Exception) ? (Exception)localThrowable : new RuntimeException(localThrowable.toString()));
    }
    return (HsqlSocketFactory)localObject;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.HsqlSocketFactory
 * JD-Core Version:    0.6.2
 */