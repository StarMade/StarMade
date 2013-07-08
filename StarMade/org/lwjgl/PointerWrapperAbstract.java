package org.lwjgl;

public abstract class PointerWrapperAbstract
  implements PointerWrapper
{
  protected final long pointer;
  
  protected PointerWrapperAbstract(long pointer)
  {
    this.pointer = pointer;
  }
  
  public boolean isValid()
  {
    return this.pointer != 0L;
  }
  
  public final void checkValid()
  {
    if ((LWJGLUtil.DEBUG) && (!isValid())) {
      throw new IllegalStateException("This " + getClass().getSimpleName() + " pointer is not valid.");
    }
  }
  
  public final long getPointer()
  {
    checkValid();
    return this.pointer;
  }
  
  public boolean equals(Object local_o)
  {
    if (this == local_o) {
      return true;
    }
    if (!(local_o instanceof PointerWrapperAbstract)) {
      return false;
    }
    PointerWrapperAbstract that = (PointerWrapperAbstract)local_o;
    return this.pointer == that.pointer;
  }
  
  public int hashCode()
  {
    return (int)(this.pointer ^ this.pointer >>> 32);
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + " pointer (0x" + Long.toHexString(this.pointer).toUpperCase() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.PointerWrapperAbstract
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */