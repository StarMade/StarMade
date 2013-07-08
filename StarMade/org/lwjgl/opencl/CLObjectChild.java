package org.lwjgl.opencl;

import org.lwjgl.LWJGLUtil;

abstract class CLObjectChild<P extends CLObject>
  extends CLObjectRetainable
{
  private final P parent;
  
  protected CLObjectChild(long pointer, P parent)
  {
    super(pointer);
    if ((LWJGLUtil.DEBUG) && (parent != null) && (!parent.isValid())) {
      throw new IllegalStateException("The parent specified is not a valid CL object.");
    }
    this.parent = parent;
  }
  
  public P getParent()
  {
    return this.parent;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLObjectChild
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */