package org.lwjgl.util.glu.tessellation;

class ActiveRegion
{
  GLUhalfEdge eUp;
  DictNode nodeUp;
  int windingNumber;
  boolean inside;
  boolean sentinel;
  boolean dirty;
  boolean fixUpperEdge;
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.ActiveRegion
 * JD-Core Version:    0.6.2
 */