/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.Buffer;
/*   4:    */import java.util.Arrays;
/*   5:    */
/*  42:    */class BaseReferences
/*  43:    */{
/*  44:    */  int elementArrayBuffer;
/*  45:    */  int arrayBuffer;
/*  46:    */  final Buffer[] glVertexAttribPointer_buffer;
/*  47:    */  final Buffer[] glTexCoordPointer_buffer;
/*  48:    */  int glClientActiveTexture;
/*  49:    */  int vertexArrayObject;
/*  50:    */  int pixelPackBuffer;
/*  51:    */  int pixelUnpackBuffer;
/*  52:    */  int indirectBuffer;
/*  53:    */  
/*  54:    */  BaseReferences(ContextCapabilities caps)
/*  55:    */  {
/*  56:    */    int max_vertex_attribs;
/*  57:    */    int max_vertex_attribs;
/*  58: 58 */    if ((caps.OpenGL20) || (caps.GL_ARB_vertex_shader)) {
/*  59: 59 */      max_vertex_attribs = GL11.glGetInteger(34921);
/*  60:    */    } else
/*  61: 61 */      max_vertex_attribs = 0;
/*  62: 62 */    this.glVertexAttribPointer_buffer = new Buffer[max_vertex_attribs];
/*  63:    */    int max_texture_units;
/*  64:    */    int max_texture_units;
/*  65: 65 */    if (caps.OpenGL20) {
/*  66: 66 */      max_texture_units = GL11.glGetInteger(34930); } else { int max_texture_units;
/*  67: 67 */      if ((caps.OpenGL13) || (caps.GL_ARB_multitexture)) {
/*  68: 68 */        max_texture_units = GL11.glGetInteger(34018);
/*  69:    */      } else
/*  70: 70 */        max_texture_units = 1; }
/*  71: 71 */    this.glTexCoordPointer_buffer = new Buffer[max_texture_units];
/*  72:    */  }
/*  73:    */  
/*  74:    */  void clear() {
/*  75: 75 */    this.elementArrayBuffer = 0;
/*  76: 76 */    this.arrayBuffer = 0;
/*  77: 77 */    this.glClientActiveTexture = 0;
/*  78: 78 */    Arrays.fill(this.glVertexAttribPointer_buffer, null);
/*  79: 79 */    Arrays.fill(this.glTexCoordPointer_buffer, null);
/*  80:    */    
/*  81: 81 */    this.vertexArrayObject = 0;
/*  82:    */    
/*  83: 83 */    this.pixelPackBuffer = 0;
/*  84: 84 */    this.pixelUnpackBuffer = 0;
/*  85:    */    
/*  86: 86 */    this.indirectBuffer = 0;
/*  87:    */  }
/*  88:    */  
/*  89:    */  void copy(BaseReferences references, int mask) {
/*  90: 90 */    if ((mask & 0x2) != 0) {
/*  91: 91 */      this.elementArrayBuffer = references.elementArrayBuffer;
/*  92: 92 */      this.arrayBuffer = references.arrayBuffer;
/*  93: 93 */      this.glClientActiveTexture = references.glClientActiveTexture;
/*  94: 94 */      System.arraycopy(references.glVertexAttribPointer_buffer, 0, this.glVertexAttribPointer_buffer, 0, this.glVertexAttribPointer_buffer.length);
/*  95: 95 */      System.arraycopy(references.glTexCoordPointer_buffer, 0, this.glTexCoordPointer_buffer, 0, this.glTexCoordPointer_buffer.length);
/*  96:    */      
/*  97: 97 */      this.vertexArrayObject = references.vertexArrayObject;
/*  98:    */      
/*  99: 99 */      this.indirectBuffer = references.indirectBuffer;
/* 100:    */    }
/* 101:    */    
/* 102:102 */    if ((mask & 0x1) != 0) {
/* 103:103 */      this.pixelPackBuffer = references.pixelPackBuffer;
/* 104:104 */      this.pixelUnpackBuffer = references.pixelUnpackBuffer;
/* 105:    */    }
/* 106:    */  }
/* 107:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.BaseReferences
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */