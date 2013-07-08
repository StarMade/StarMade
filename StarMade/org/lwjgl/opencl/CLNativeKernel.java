/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.PointerWrapperAbstract;
/*  5:   */
/* 46:   */public abstract class CLNativeKernel
/* 47:   */  extends PointerWrapperAbstract
/* 48:   */{
/* 49:   */  protected CLNativeKernel()
/* 50:   */  {
/* 51:51 */    super(CallbackUtil.getNativeKernelCallback());
/* 52:   */  }
/* 53:   */  
/* 54:   */  protected abstract void execute(ByteBuffer[] paramArrayOfByteBuffer);
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLNativeKernel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */