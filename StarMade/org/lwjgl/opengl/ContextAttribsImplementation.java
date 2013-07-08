package org.lwjgl.opengl;

abstract interface ContextAttribsImplementation
{
  public abstract int getMajorVersionAttrib();
  
  public abstract int getMinorVersionAttrib();
  
  public abstract int getLayerPlaneAttrib();
  
  public abstract int getFlagsAttrib();
  
  public abstract int getDebugBit();
  
  public abstract int getForwardCompatibleBit();
  
  public abstract int getProfileMaskAttrib();
  
  public abstract int getProfileCoreBit();
  
  public abstract int getProfileCompatibilityBit();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ContextAttribsImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */