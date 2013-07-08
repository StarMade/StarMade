/*  1:   */package org.jasypt.exceptions;
/*  2:   */
/* 18:   */public final class PasswordAlreadyCleanedException
/* 19:   */  extends RuntimeException
/* 20:   */{
/* 21:   */  private static final long serialVersionUID = 7988484935273871733L;
/* 22:   */  
/* 37:   */  public PasswordAlreadyCleanedException()
/* 38:   */  {
/* 39:39 */    super("Password already cleaned: The encryptor that uses this password has already been initialized and therefore this password has been cleaned so that it is no more present in memory. An exception has been raised when accessing this property in order to avoid inconsistencies. A possible reason for this error is that you are using the same PBEConfig object to configure two different PBE encryptor instances.");
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.exceptions.PasswordAlreadyCleanedException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */