/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  12:    */class GLUhalfEdge
/*  13:    */{
/*  14:    */  public GLUhalfEdge next;
/*  15:    */  
/*  24:    */  public GLUhalfEdge Sym;
/*  25:    */  
/*  34:    */  public GLUhalfEdge Onext;
/*  35:    */  
/*  44:    */  public GLUhalfEdge Lnext;
/*  45:    */  
/*  54:    */  public GLUvertex Org;
/*  55:    */  
/*  64:    */  public GLUface Lface;
/*  65:    */  
/*  73:    */  public ActiveRegion activeRegion;
/*  74:    */  
/*  82:    */  public int winding;
/*  83:    */  
/*  91:    */  public boolean first;
/*  92:    */  
/* 101:    */  GLUhalfEdge(boolean first)
/* 102:    */  {
/* 103:103 */    this.first = first;
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUhalfEdge
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */