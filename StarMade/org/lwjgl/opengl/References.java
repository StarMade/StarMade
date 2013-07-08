/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.Buffer;
/*  4:   */
/*  5:   */class References extends BaseReferences { Buffer ARB_matrix_palette_glMatrixIndexPointerARB_pPointer;
/*  6:   */  
/*  7: 7 */  References(ContextCapabilities caps) { super(caps); }
/*  8:   */  
/* 10:   */  Buffer ARB_vertex_blend_glWeightPointerARB_pPointer;
/* 11:   */  Buffer EXT_fog_coord_glFogCoordPointerEXT_data;
/* 12:   */  Buffer EXT_secondary_color_glSecondaryColorPointerEXT_pPointer;
/* 13:   */  Buffer EXT_vertex_shader_glVariantPointerEXT_pAddr;
/* 14:   */  Buffer EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer;
/* 15:   */  Buffer GL11_glColorPointer_pointer;
/* 16:   */  Buffer GL11_glEdgeFlagPointer_pointer;
/* 17:   */  Buffer GL11_glNormalPointer_pointer;
/* 18:   */  Buffer GL11_glVertexPointer_pointer;
/* 19:   */  Buffer GL14_glFogCoordPointer_data;
/* 20:   */  void copy(References references, int mask)
/* 21:   */  {
/* 22:22 */    super.copy(references, mask);
/* 23:23 */    if ((mask & 0x2) != 0) {
/* 24:24 */      this.ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = references.ARB_matrix_palette_glMatrixIndexPointerARB_pPointer;
/* 25:25 */      this.ARB_vertex_blend_glWeightPointerARB_pPointer = references.ARB_vertex_blend_glWeightPointerARB_pPointer;
/* 26:26 */      this.EXT_fog_coord_glFogCoordPointerEXT_data = references.EXT_fog_coord_glFogCoordPointerEXT_data;
/* 27:27 */      this.EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = references.EXT_secondary_color_glSecondaryColorPointerEXT_pPointer;
/* 28:28 */      this.EXT_vertex_shader_glVariantPointerEXT_pAddr = references.EXT_vertex_shader_glVariantPointerEXT_pAddr;
/* 29:29 */      this.EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer = references.EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer;
/* 30:30 */      this.GL11_glColorPointer_pointer = references.GL11_glColorPointer_pointer;
/* 31:31 */      this.GL11_glEdgeFlagPointer_pointer = references.GL11_glEdgeFlagPointer_pointer;
/* 32:32 */      this.GL11_glNormalPointer_pointer = references.GL11_glNormalPointer_pointer;
/* 33:33 */      this.GL11_glVertexPointer_pointer = references.GL11_glVertexPointer_pointer;
/* 34:34 */      this.GL14_glFogCoordPointer_data = references.GL14_glFogCoordPointer_data;
/* 35:   */    }
/* 36:   */  }
/* 37:   */  
/* 38:38 */  void clear() { super.clear();
/* 39:39 */    this.ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = null;
/* 40:40 */    this.ARB_vertex_blend_glWeightPointerARB_pPointer = null;
/* 41:41 */    this.EXT_fog_coord_glFogCoordPointerEXT_data = null;
/* 42:42 */    this.EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = null;
/* 43:43 */    this.EXT_vertex_shader_glVariantPointerEXT_pAddr = null;
/* 44:44 */    this.EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer = null;
/* 45:45 */    this.GL11_glColorPointer_pointer = null;
/* 46:46 */    this.GL11_glEdgeFlagPointer_pointer = null;
/* 47:47 */    this.GL11_glNormalPointer_pointer = null;
/* 48:48 */    this.GL11_glVertexPointer_pointer = null;
/* 49:49 */    this.GL14_glFogCoordPointer_data = null;
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.References
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */