/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ abstract class CLProgramCallback extends PointerWrapperAbstract
/*    */ {
/*    */   private CLContext context;
/*    */ 
/*    */   protected CLProgramCallback()
/*    */   {
/* 46 */     super(CallbackUtil.getProgramCallback());
/*    */   }
/*    */ 
/*    */   final void setContext(CLContext context)
/*    */   {
/* 55 */     this.context = context;
/*    */   }
/*    */ 
/*    */   private void handleMessage(long program_address)
/*    */   {
/* 64 */     handleMessage(this.context.getCLProgram(program_address));
/*    */   }
/*    */ 
/*    */   protected abstract void handleMessage(CLProgram paramCLProgram);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLProgramCallback
 * JD-Core Version:    0.6.2
 */