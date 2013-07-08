/*  1:   */package org.lwjgl.util.glu.tessellation;
/*  2:   */
/* 20:   */class GLUvertex
/* 21:   */{
/* 22:   */  public GLUvertex next;
/* 23:   */  
/* 40:   */  public GLUvertex prev;
/* 41:   */  
/* 58:   */  public GLUhalfEdge anEdge;
/* 59:   */  
/* 76:   */  public Object data;
/* 77:   */  
/* 94:94 */  public double[] coords = new double[3];
/* 95:   */  public double s;
/* 96:   */  public double t;
/* 97:   */  public int pqHandle;
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUvertex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */