/*  1:   */package org.lwjgl;
/*  2:   */
/* 20:   */public abstract class PointerWrapperAbstract
/* 21:   */  implements PointerWrapper
/* 22:   */{
/* 23:   */  protected final long pointer;
/* 24:   */  
/* 42:   */  protected PointerWrapperAbstract(long pointer)
/* 43:   */  {
/* 44:44 */    this.pointer = pointer;
/* 45:   */  }
/* 46:   */  
/* 54:   */  public boolean isValid()
/* 55:   */  {
/* 56:56 */    return this.pointer != 0L;
/* 57:   */  }
/* 58:   */  
/* 63:   */  public final void checkValid()
/* 64:   */  {
/* 65:65 */    if ((LWJGLUtil.DEBUG) && (!isValid()))
/* 66:66 */      throw new IllegalStateException("This " + getClass().getSimpleName() + " pointer is not valid.");
/* 67:   */  }
/* 68:   */  
/* 69:   */  public final long getPointer() {
/* 70:70 */    checkValid();
/* 71:71 */    return this.pointer;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public boolean equals(Object o) {
/* 75:75 */    if (this == o) return true;
/* 76:76 */    if (!(o instanceof PointerWrapperAbstract)) { return false;
/* 77:   */    }
/* 78:78 */    PointerWrapperAbstract that = (PointerWrapperAbstract)o;
/* 79:   */    
/* 80:80 */    if (this.pointer != that.pointer) { return false;
/* 81:   */    }
/* 82:82 */    return true;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public int hashCode() {
/* 86:86 */    return (int)(this.pointer ^ this.pointer >>> 32);
/* 87:   */  }
/* 88:   */  
/* 89:   */  public String toString() {
/* 90:90 */    return getClass().getSimpleName() + " pointer (0x" + Long.toHexString(this.pointer).toUpperCase() + ")";
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.PointerWrapperAbstract
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */