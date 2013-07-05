/*    */ package org.lwjgl.util.glu.tessellation;
/*    */ 
/*    */ class GLUvertex
/*    */ {
/*    */   public GLUvertex next;
/*    */   public GLUvertex prev;
/*    */   public GLUhalfEdge anEdge;
/*    */   public Object data;
/* 94 */   public double[] coords = new double[3];
/*    */   public double s;
/*    */   public double t;
/*    */   public int pqHandle;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.GLUvertex
 * JD-Core Version:    0.6.2
 */