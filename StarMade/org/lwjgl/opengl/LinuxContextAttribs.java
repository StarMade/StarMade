/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  8:   */final class LinuxContextAttribs
/*  9:   */  implements ContextAttribsImplementation
/* 10:   */{
/* 11:   */  private static final int GLX_CONTEXT_MAJOR_VERSION_ARB = 8337;
/* 12:   */  
/* 17:   */  private static final int GLX_CONTEXT_MINOR_VERSION_ARB = 8338;
/* 18:   */  
/* 23:   */  private static final int GLX_CONTEXT_FLAGS_ARB = 8340;
/* 24:   */  
/* 28:   */  private static final int GLX_CONTEXT_PROFILE_MASK_ARB = 37158;
/* 29:   */  
/* 33:   */  private static final int GLX_CONTEXT_DEBUG_BIT_ARB = 1;
/* 34:   */  
/* 38:   */  private static final int GLX_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB = 2;
/* 39:   */  
/* 43:   */  private static final int GLX_CONTEXT_CORE_PROFILE_BIT_ARB = 1;
/* 44:   */  
/* 48:   */  private static final int GLX_CONTEXT_COMPATIBILITY_PROFILE_BIT_ARB = 2;
/* 49:   */  
/* 54:   */  public int getMajorVersionAttrib()
/* 55:   */  {
/* 56:56 */    return 8337;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public int getMinorVersionAttrib() {
/* 60:60 */    return 8338;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public int getLayerPlaneAttrib() {
/* 64:64 */    throw new UnsupportedOperationException();
/* 65:   */  }
/* 66:   */  
/* 67:   */  public int getFlagsAttrib() {
/* 68:68 */    return 8340;
/* 69:   */  }
/* 70:   */  
/* 71:   */  public int getDebugBit() {
/* 72:72 */    return 1;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public int getForwardCompatibleBit() {
/* 76:76 */    return 2;
/* 77:   */  }
/* 78:   */  
/* 79:   */  public int getProfileMaskAttrib() {
/* 80:80 */    return 37158;
/* 81:   */  }
/* 82:   */  
/* 83:   */  public int getProfileCoreBit() {
/* 84:84 */    return 1;
/* 85:   */  }
/* 86:   */  
/* 87:   */  public int getProfileCompatibilityBit() {
/* 88:88 */    return 2;
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxContextAttribs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */