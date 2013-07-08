/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  7:   */final class WindowsContextAttribs
/*  8:   */  implements ContextAttribsImplementation
/*  9:   */{
/* 10:   */  private static final int WGL_CONTEXT_MAJOR_VERSION_ARB = 8337;
/* 11:   */  
/* 15:   */  private static final int WGL_CONTEXT_MINOR_VERSION_ARB = 8338;
/* 16:   */  
/* 20:   */  private static final int WGL_CONTEXT_LAYER_PLANE_ARB = 8339;
/* 21:   */  
/* 25:   */  private static final int WGL_CONTEXT_FLAGS_ARB = 8340;
/* 26:   */  
/* 30:   */  private static final int WGL_CONTEXT_PROFILE_MASK_ARB = 37158;
/* 31:   */  
/* 35:   */  private static final int WGL_CONTEXT_DEBUG_BIT_ARB = 1;
/* 36:   */  
/* 40:   */  private static final int WGL_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB = 2;
/* 41:   */  
/* 45:   */  private static final int WGL_CONTEXT_CORE_PROFILE_BIT_ARB = 1;
/* 46:   */  
/* 50:   */  private static final int WGL_CONTEXT_COMPATIBILITY_PROFILE_BIT_ARB = 2;
/* 51:   */  
/* 55:   */  public int getMajorVersionAttrib()
/* 56:   */  {
/* 57:57 */    return 8337;
/* 58:   */  }
/* 59:   */  
/* 60:   */  public int getMinorVersionAttrib() {
/* 61:61 */    return 8338;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public int getLayerPlaneAttrib() {
/* 65:65 */    return 8339;
/* 66:   */  }
/* 67:   */  
/* 68:   */  public int getFlagsAttrib() {
/* 69:69 */    return 8340;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public int getDebugBit() {
/* 73:73 */    return 1;
/* 74:   */  }
/* 75:   */  
/* 76:   */  public int getForwardCompatibleBit() {
/* 77:77 */    return 2;
/* 78:   */  }
/* 79:   */  
/* 80:   */  public int getProfileMaskAttrib() {
/* 81:81 */    return 37158;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public int getProfileCoreBit() {
/* 85:85 */    return 1;
/* 86:   */  }
/* 87:   */  
/* 88:   */  public int getProfileCompatibilityBit() {
/* 89:89 */    return 2;
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsContextAttribs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */