package org.lwjgl.opencl;

import java.nio.ByteBuffer;
import org.lwjgl.PointerWrapperAbstract;

public abstract class CLNativeKernel
  extends PointerWrapperAbstract
{
  protected CLNativeKernel()
  {
    super(CallbackUtil.getNativeKernelCallback());
  }
  
  protected abstract void execute(ByteBuffer[] paramArrayOfByteBuffer);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLNativeKernel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */