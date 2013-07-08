/*  1:   */package org.apache.commons.lang3;
/*  2:   */
/* 17:   */public class SerializationException
/* 18:   */  extends RuntimeException
/* 19:   */{
/* 20:   */  private static final long serialVersionUID = 4029025366392702726L;
/* 21:   */  
/* 35:   */  public SerializationException() {}
/* 36:   */  
/* 50:   */  public SerializationException(String msg)
/* 51:   */  {
/* 52:52 */    super(msg);
/* 53:   */  }
/* 54:   */  
/* 61:   */  public SerializationException(Throwable cause)
/* 62:   */  {
/* 63:63 */    super(cause);
/* 64:   */  }
/* 65:   */  
/* 73:   */  public SerializationException(String msg, Throwable cause)
/* 74:   */  {
/* 75:75 */    super(msg, cause);
/* 76:   */  }
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.SerializationException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */