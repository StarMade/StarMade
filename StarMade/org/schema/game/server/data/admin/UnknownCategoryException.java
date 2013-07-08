/*  1:   */package org.schema.game.server.data.admin;
/*  2:   */
/*  4:   */public class UnknownCategoryException
/*  5:   */  extends Exception
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = -2713992827958593228L;
/*  8:   */  private String a;
/*  9:   */  
/* 10:   */  public UnknownCategoryException(String paramString)
/* 11:   */  {
/* 12:12 */    this.a = paramString;
/* 13:   */  }
/* 14:   */  
/* 17:   */  public final String a()
/* 18:   */  {
/* 19:19 */    return this.a;
/* 20:   */  }
/* 21:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.server.data.admin.UnknownCategoryException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */