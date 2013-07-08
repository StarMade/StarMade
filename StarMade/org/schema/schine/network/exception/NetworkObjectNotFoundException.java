/*  1:   */package org.schema.schine.network.exception;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */
/*  7:   */public class NetworkObjectNotFoundException
/*  8:   */  extends Exception
/*  9:   */{
/* 10:   */  private static final long serialVersionUID = 5077844495312584968L;
/* 11:   */  
/* 12:   */  public NetworkObjectNotFoundException(int paramInt, Class paramClass, String paramString, HashMap paramHashMap)
/* 13:   */  {
/* 14:14 */    super(paramClass.getSimpleName() + " with id " + paramInt + " not found. updateString: " + paramString + ". available: " + paramHashMap);
/* 15:   */  }
/* 16:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.exception.NetworkObjectNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */