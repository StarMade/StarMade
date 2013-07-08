package org.lwjgl.util.glu.tessellation;

class GLUhalfEdge
{
  public GLUhalfEdge next;
  public GLUhalfEdge Sym;
  public GLUhalfEdge Onext;
  public GLUhalfEdge Lnext;
  public GLUvertex Org;
  public GLUface Lface;
  public ActiveRegion activeRegion;
  public int winding;
  public boolean first;
  
  GLUhalfEdge(boolean first)
  {
    this.first = first;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUhalfEdge
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */