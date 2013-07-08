/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.PointerWrapperAbstract;
/*  4:   */
/* 39:   */abstract class CLObject
/* 40:   */  extends PointerWrapperAbstract
/* 41:   */{
/* 42:   */  protected CLObject(long pointer)
/* 43:   */  {
/* 44:44 */    super(pointer);
/* 45:   */  }
/* 46:   */  
/* 47:   */  final long getPointerUnsafe() {
/* 48:48 */    return this.pointer;
/* 49:   */  }
/* 50:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */