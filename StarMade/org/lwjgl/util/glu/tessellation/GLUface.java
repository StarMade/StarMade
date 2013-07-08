package org.lwjgl.util.glu.tessellation;

class GLUface
{
  public GLUface next;
  public GLUface prev;
  public GLUhalfEdge anEdge;
  public Object data;
  public GLUface trail;
  public boolean marked;
  public boolean inside;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */