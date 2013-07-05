/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ final class LinuxContextAttribs
/*    */   implements ContextAttribsImplementation
/*    */ {
/*    */   private static final int GLX_CONTEXT_MAJOR_VERSION_ARB = 8337;
/*    */   private static final int GLX_CONTEXT_MINOR_VERSION_ARB = 8338;
/*    */   private static final int GLX_CONTEXT_FLAGS_ARB = 8340;
/*    */   private static final int GLX_CONTEXT_PROFILE_MASK_ARB = 37158;
/*    */   private static final int GLX_CONTEXT_DEBUG_BIT_ARB = 1;
/*    */   private static final int GLX_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB = 2;
/*    */   private static final int GLX_CONTEXT_CORE_PROFILE_BIT_ARB = 1;
/*    */   private static final int GLX_CONTEXT_COMPATIBILITY_PROFILE_BIT_ARB = 2;
/*    */ 
/*    */   public int getMajorVersionAttrib()
/*    */   {
/* 56 */     return 8337;
/*    */   }
/*    */ 
/*    */   public int getMinorVersionAttrib() {
/* 60 */     return 8338;
/*    */   }
/*    */ 
/*    */   public int getLayerPlaneAttrib() {
/* 64 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   public int getFlagsAttrib() {
/* 68 */     return 8340;
/*    */   }
/*    */ 
/*    */   public int getDebugBit() {
/* 72 */     return 1;
/*    */   }
/*    */ 
/*    */   public int getForwardCompatibleBit() {
/* 76 */     return 2;
/*    */   }
/*    */ 
/*    */   public int getProfileMaskAttrib() {
/* 80 */     return 37158;
/*    */   }
/*    */ 
/*    */   public int getProfileCoreBit() {
/* 84 */     return 1;
/*    */   }
/*    */ 
/*    */   public int getProfileCompatibilityBit() {
/* 88 */     return 2;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxContextAttribs
 * JD-Core Version:    0.6.2
 */