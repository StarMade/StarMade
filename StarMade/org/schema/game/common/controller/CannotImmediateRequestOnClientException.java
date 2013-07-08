/*  1:   */package org.schema.game.common.controller;
/*  2:   */
/*  3:   */import q;
/*  4:   */
/*  6:   */public class CannotImmediateRequestOnClientException
/*  7:   */  extends RuntimeException
/*  8:   */{
/*  9:   */  private static final long serialVersionUID = -7838434728382878333L;
/* 10:   */  private final q a;
/* 11:   */  
/* 12:   */  public CannotImmediateRequestOnClientException(q paramq)
/* 13:   */  {
/* 14:14 */    this.a = new q(paramq);
/* 15:   */  }
/* 16:   */  
/* 19:   */  public final q a()
/* 20:   */  {
/* 21:21 */    return this.a;
/* 22:   */  }
/* 23:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.CannotImmediateRequestOnClientException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */