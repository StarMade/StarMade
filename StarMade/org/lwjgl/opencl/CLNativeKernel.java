/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ public abstract class CLNativeKernel extends PointerWrapperAbstract
/*    */ {
/*    */   protected CLNativeKernel()
/*    */   {
/* 51 */     super(CallbackUtil.getNativeKernelCallback());
/*    */   }
/*    */ 
/*    */   protected abstract void execute(ByteBuffer[] paramArrayOfByteBuffer);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLNativeKernel
 * JD-Core Version:    0.6.2
 */