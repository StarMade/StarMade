/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  6:   */public final class ARBVertexAttribBinding
/*  7:   */{
/*  8:   */  public static final int GL_VERTEX_ATTRIB_BINDING = 33492;
/*  9:   */  
/* 12:   */  public static final int GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 33493;
/* 13:   */  
/* 15:   */  public static final int GL_VERTEX_BINDING_DIVISOR = 33494;
/* 16:   */  
/* 18:   */  public static final int GL_VERTEX_BINDING_OFFSET = 33495;
/* 19:   */  
/* 21:   */  public static final int GL_VERTEX_BINDING_STRIDE = 33496;
/* 22:   */  
/* 24:   */  public static final int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 33497;
/* 25:   */  
/* 27:   */  public static final int GL_MAX_VERTEX_ATTRIB_BINDINGS = 33498;
/* 28:   */  
/* 31:   */  public static void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride)
/* 32:   */  {
/* 33:33 */    GL43.glBindVertexBuffer(bindingindex, buffer, offset, stride);
/* 34:   */  }
/* 35:   */  
/* 36:   */  public static void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized, int relativeoffset) {
/* 37:37 */    GL43.glVertexAttribFormat(attribindex, size, type, normalized, relativeoffset);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public static void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) {
/* 41:41 */    GL43.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
/* 42:   */  }
/* 43:   */  
/* 44:   */  public static void glVertexAttribLFormat(int attribindex, int size, int type, int relativeoffset) {
/* 45:45 */    GL43.glVertexAttribLFormat(attribindex, size, type, relativeoffset);
/* 46:   */  }
/* 47:   */  
/* 48:   */  public static void glVertexAttribBinding(int attribindex, int bindingindex) {
/* 49:49 */    GL43.glVertexAttribBinding(attribindex, bindingindex);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public static void glVertexBindingDivisor(int bindingindex, int divisor) {
/* 53:53 */    GL43.glVertexBindingDivisor(bindingindex, divisor);
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBVertexAttribBinding
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */