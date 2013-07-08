package org.lwjgl.opencl;

abstract class CLObjectRetainable
  extends CLObject
{
  private int refCount;
  
  protected CLObjectRetainable(long pointer)
  {
    super(pointer);
    if (super.isValid()) {
      this.refCount = 1;
    }
  }
  
  public final int getReferenceCount()
  {
    return this.refCount;
  }
  
  public final boolean isValid()
  {
    return this.refCount > 0;
  }
  
  int retain()
  {
    checkValid();
    return ++this.refCount;
  }
  
  int release()
  {
    checkValid();
    return --this.refCount;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLObjectRetainable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */