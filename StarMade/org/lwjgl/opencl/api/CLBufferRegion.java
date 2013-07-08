/*  1:   */package org.lwjgl.opencl.api;
/*  2:   */
/*  3:   */import org.lwjgl.PointerBuffer;
/*  4:   */
/* 42:   */public final class CLBufferRegion
/* 43:   */{
/* 44:44 */  public static final int STRUCT_SIZE = 2 * PointerBuffer.getPointerSize();
/* 45:   */  private final int origin;
/* 46:   */  private final int size;
/* 47:   */  
/* 48:   */  public CLBufferRegion(int origin, int size)
/* 49:   */  {
/* 50:50 */    this.origin = origin;
/* 51:51 */    this.size = size;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public int getOrigin() {
/* 55:55 */    return this.origin;
/* 56:   */  }
/* 57:   */  
/* 58:   */  public int getSize() {
/* 59:59 */    return this.size;
/* 60:   */  }
/* 61:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.api.CLBufferRegion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */