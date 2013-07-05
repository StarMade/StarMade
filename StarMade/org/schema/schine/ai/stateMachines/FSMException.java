/*    */ package org.schema.schine.ai.stateMachines;
/*    */ 
/*    */ import wq;
/*    */ import wt;
/*    */ import wv;
/*    */ 
/*    */ public class FSMException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = -4217837268845972600L;
/*    */ 
/*    */   public FSMException(wt paramwt, wv paramwv)
/*    */   {
/* 63 */     super("ERR: Transition from \"" + paramwt + "\" --" + paramwv + "--> \"newState\" failed | not found in " + paramwt + ". inputs: " + paramwt.a().a + ", outputs " + paramwt.a().b);
/*    */   }
/*    */ 
/*    */   public FSMException(String paramString)
/*    */   {
/* 73 */     super(paramString);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.ai.stateMachines.FSMException
 * JD-Core Version:    0.6.2
 */