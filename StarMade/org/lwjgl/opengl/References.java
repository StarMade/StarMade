/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.Buffer;
/*    */ 
/*    */ class References extends BaseReferences
/*    */ {
/*    */   Buffer ARB_matrix_palette_glMatrixIndexPointerARB_pPointer;
/*    */   Buffer ARB_vertex_blend_glWeightPointerARB_pPointer;
/*    */   Buffer EXT_fog_coord_glFogCoordPointerEXT_data;
/*    */   Buffer EXT_secondary_color_glSecondaryColorPointerEXT_pPointer;
/*    */   Buffer EXT_vertex_shader_glVariantPointerEXT_pAddr;
/*    */   Buffer EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer;
/*    */   Buffer GL11_glColorPointer_pointer;
/*    */   Buffer GL11_glEdgeFlagPointer_pointer;
/*    */   Buffer GL11_glNormalPointer_pointer;
/*    */   Buffer GL11_glVertexPointer_pointer;
/*    */   Buffer GL14_glFogCoordPointer_data;
/*    */ 
/*    */   References(ContextCapabilities caps)
/*    */   {
/*  7 */     super(caps);
/*    */   }
/*    */ 
/*    */   void copy(References references, int mask)
/*    */   {
/* 22 */     super.copy(references, mask);
/* 23 */     if ((mask & 0x2) != 0) {
/* 24 */       this.ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = references.ARB_matrix_palette_glMatrixIndexPointerARB_pPointer;
/* 25 */       this.ARB_vertex_blend_glWeightPointerARB_pPointer = references.ARB_vertex_blend_glWeightPointerARB_pPointer;
/* 26 */       this.EXT_fog_coord_glFogCoordPointerEXT_data = references.EXT_fog_coord_glFogCoordPointerEXT_data;
/* 27 */       this.EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = references.EXT_secondary_color_glSecondaryColorPointerEXT_pPointer;
/* 28 */       this.EXT_vertex_shader_glVariantPointerEXT_pAddr = references.EXT_vertex_shader_glVariantPointerEXT_pAddr;
/* 29 */       this.EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer = references.EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer;
/* 30 */       this.GL11_glColorPointer_pointer = references.GL11_glColorPointer_pointer;
/* 31 */       this.GL11_glEdgeFlagPointer_pointer = references.GL11_glEdgeFlagPointer_pointer;
/* 32 */       this.GL11_glNormalPointer_pointer = references.GL11_glNormalPointer_pointer;
/* 33 */       this.GL11_glVertexPointer_pointer = references.GL11_glVertexPointer_pointer;
/* 34 */       this.GL14_glFogCoordPointer_data = references.GL14_glFogCoordPointer_data;
/*    */     }
/*    */   }
/*    */ 
/* 38 */   void clear() { super.clear();
/* 39 */     this.ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = null;
/* 40 */     this.ARB_vertex_blend_glWeightPointerARB_pPointer = null;
/* 41 */     this.EXT_fog_coord_glFogCoordPointerEXT_data = null;
/* 42 */     this.EXT_secondary_color_glSecondaryColorPointerEXT_pPointer = null;
/* 43 */     this.EXT_vertex_shader_glVariantPointerEXT_pAddr = null;
/* 44 */     this.EXT_vertex_weighting_glVertexWeightPointerEXT_pPointer = null;
/* 45 */     this.GL11_glColorPointer_pointer = null;
/* 46 */     this.GL11_glEdgeFlagPointer_pointer = null;
/* 47 */     this.GL11_glNormalPointer_pointer = null;
/* 48 */     this.GL11_glVertexPointer_pointer = null;
/* 49 */     this.GL14_glFogCoordPointer_data = null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.References
 * JD-Core Version:    0.6.2
 */