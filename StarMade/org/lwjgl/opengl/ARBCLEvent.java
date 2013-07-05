/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.opencl.CLContext;
/*    */ import org.lwjgl.opencl.CLEvent;
/*    */ 
/*    */ public final class ARBCLEvent
/*    */ {
/*    */   public static final int GL_SYNC_CL_EVENT_ARB = 33344;
/*    */   public static final int GL_SYNC_CL_EVENT_COMPLETE_ARB = 33345;
/*    */ 
/*    */   public static GLSync glCreateSyncFromCLeventARB(CLContext context, CLEvent event, int flags)
/*    */   {
/* 24 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 25 */     long function_pointer = caps.glCreateSyncFromCLeventARB;
/* 26 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 27 */     GLSync __result = new GLSync(nglCreateSyncFromCLeventARB(context.getPointer(), event.getPointer(), flags, function_pointer));
/* 28 */     return __result;
/*    */   }
/*    */ 
/*    */   static native long nglCreateSyncFromCLeventARB(long paramLong1, long paramLong2, int paramInt, long paramLong3);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBCLEvent
 * JD-Core Version:    0.6.2
 */