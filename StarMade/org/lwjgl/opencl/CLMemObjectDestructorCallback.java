package org.lwjgl.opencl;

import org.lwjgl.PointerWrapperAbstract;

public abstract class CLMemObjectDestructorCallback
  extends PointerWrapperAbstract
{
  protected CLMemObjectDestructorCallback()
  {
    super(CallbackUtil.getMemObjectDestructorCallback());
  }
  
  protected abstract void handleMessage(long paramLong);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLMemObjectDestructorCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */