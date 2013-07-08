/*  1:   */package org.schema.schine.network.udp;
/*  2:   */
/*  4:   */public class UDPException
/*  5:   */  extends RuntimeException
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 2679247287365962074L;
/*  8:   */  private Exception ioException;
/*  9:   */  
/* 10:   */  public UDPException(String paramString)
/* 11:   */  {
/* 12:12 */    super(paramString);
/* 13:   */  }
/* 14:   */  
/* 15:   */  public UDPException(String paramString, Exception paramException) {
/* 16:16 */    super(paramString);
/* 17:17 */    this.ioException = paramException;
/* 18:   */  }
/* 19:   */  
/* 22:   */  public Exception getIoException()
/* 23:   */  {
/* 24:24 */    return this.ioException;
/* 25:   */  }
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */