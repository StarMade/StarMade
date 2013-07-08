/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.LWJGLUtil;
/*  4:   */
/* 39:   */abstract class CLObjectChild<P extends CLObject>
/* 40:   */  extends CLObjectRetainable
/* 41:   */{
/* 42:   */  private final P parent;
/* 43:   */  
/* 44:   */  protected CLObjectChild(long pointer, P parent)
/* 45:   */  {
/* 46:46 */    super(pointer);
/* 47:   */    
/* 48:48 */    if ((LWJGLUtil.DEBUG) && (parent != null) && (!parent.isValid())) {
/* 49:49 */      throw new IllegalStateException("The parent specified is not a valid CL object.");
/* 50:   */    }
/* 51:51 */    this.parent = parent;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public P getParent() {
/* 55:55 */    return this.parent;
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObjectChild
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */