/*    */ package com.jcraft.jorbis;
/*    */ 
/*    */ public class JOrbisException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public JOrbisException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public JOrbisException(String s)
/*    */   {
/* 38 */     super("JOrbis: " + s);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.JOrbisException
 * JD-Core Version:    0.6.2
 */