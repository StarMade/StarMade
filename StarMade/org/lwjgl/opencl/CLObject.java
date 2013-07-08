package org.lwjgl.opencl;

import org.lwjgl.PointerWrapperAbstract;

abstract class CLObject
  extends PointerWrapperAbstract
{
  protected CLObject(long pointer)
  {
    super(pointer);
  }
  
  final long getPointerUnsafe()
  {
    return this.pointer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */