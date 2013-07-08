/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/* 20:   */abstract class CLObjectRetainable
/* 21:   */  extends CLObject
/* 22:   */{
/* 23:   */  private int refCount;
/* 24:   */  
/* 42:   */  protected CLObjectRetainable(long pointer)
/* 43:   */  {
/* 44:44 */    super(pointer);
/* 45:   */    
/* 46:46 */    if (super.isValid())
/* 47:47 */      this.refCount = 1;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public final int getReferenceCount() {
/* 51:51 */    return this.refCount;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public final boolean isValid() {
/* 55:55 */    return this.refCount > 0;
/* 56:   */  }
/* 57:   */  
/* 58:   */  int retain() {
/* 59:59 */    checkValid();
/* 60:   */    
/* 61:61 */    return ++this.refCount;
/* 62:   */  }
/* 63:   */  
/* 64:   */  int release() {
/* 65:65 */    checkValid();
/* 66:   */    
/* 67:67 */    return --this.refCount;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObjectRetainable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */