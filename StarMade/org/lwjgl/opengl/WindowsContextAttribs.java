/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ final class WindowsContextAttribs
/*    */   implements ContextAttribsImplementation
/*    */ {
/*    */   private static final int WGL_CONTEXT_MAJOR_VERSION_ARB = 8337;
/*    */   private static final int WGL_CONTEXT_MINOR_VERSION_ARB = 8338;
/*    */   private static final int WGL_CONTEXT_LAYER_PLANE_ARB = 8339;
/*    */   private static final int WGL_CONTEXT_FLAGS_ARB = 8340;
/*    */   private static final int WGL_CONTEXT_PROFILE_MASK_ARB = 37158;
/*    */   private static final int WGL_CONTEXT_DEBUG_BIT_ARB = 1;
/*    */   private static final int WGL_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB = 2;
/*    */   private static final int WGL_CONTEXT_CORE_PROFILE_BIT_ARB = 1;
/*    */   private static final int WGL_CONTEXT_COMPATIBILITY_PROFILE_BIT_ARB = 2;
/*    */ 
/*    */   public int getMajorVersionAttrib()
/*    */   {
/* 57 */     return 8337;
/*    */   }
/*    */ 
/*    */   public int getMinorVersionAttrib() {
/* 61 */     return 8338;
/*    */   }
/*    */ 
/*    */   public int getLayerPlaneAttrib() {
/* 65 */     return 8339;
/*    */   }
/*    */ 
/*    */   public int getFlagsAttrib() {
/* 69 */     return 8340;
/*    */   }
/*    */ 
/*    */   public int getDebugBit() {
/* 73 */     return 1;
/*    */   }
/*    */ 
/*    */   public int getForwardCompatibleBit() {
/* 77 */     return 2;
/*    */   }
/*    */ 
/*    */   public int getProfileMaskAttrib() {
/* 81 */     return 37158;
/*    */   }
/*    */ 
/*    */   public int getProfileCoreBit() {
/* 85 */     return 1;
/*    */   }
/*    */ 
/*    */   public int getProfileCompatibilityBit() {
/* 89 */     return 2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsContextAttribs
 * JD-Core Version:    0.6.2
 */