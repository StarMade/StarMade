/*  1:   */package org.schema.schine.network.exception;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.net.ServerSocket;
/*  5:   */
/*  7:   */public class ServerPortNotAvailableException
/*  8:   */  extends Exception
/*  9:   */{
/* 10:   */  private static final long serialVersionUID = 906116778403491118L;
/* 11:   */  private boolean instanceRunning;
/* 12:   */  
/* 13:   */  public static void main(String[] paramArrayOfString)
/* 14:   */  {
/* 15:15 */    paramArrayOfString = null;
/* 16:   */    try {
/* 17:17 */      paramArrayOfString = new ServerSocket(4242);
/* 18:   */    }
/* 19:   */    catch (IOException localIOException1) {}
/* 20:   */    try
/* 21:   */    {
/* 22:   */      for (;;) {
/* 23:23 */        paramArrayOfString.accept();
/* 24:24 */      } } catch (IOException localIOException2) { 
/* 25:   */      
/* 27:27 */        localIOException2;
/* 28:   */    }
/* 29:   */  }
/* 30:   */  
/* 35:   */  public ServerPortNotAvailableException(String paramString)
/* 36:   */  {
/* 37:34 */    super(paramString);
/* 38:   */  }
/* 39:   */  
/* 42:   */  public boolean isInstanceRunning()
/* 43:   */  {
/* 44:41 */    return this.instanceRunning;
/* 45:   */  }
/* 46:   */  
/* 49:   */  public void setInstanceRunning(boolean paramBoolean)
/* 50:   */  {
/* 51:48 */    this.instanceRunning = paramBoolean;
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.exception.ServerPortNotAvailableException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */