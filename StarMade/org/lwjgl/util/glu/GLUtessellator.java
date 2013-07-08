package org.lwjgl.util.glu;

public abstract interface GLUtessellator
{
  public abstract void gluDeleteTess();
  
  public abstract void gluTessProperty(int paramInt, double paramDouble);
  
  public abstract void gluGetTessProperty(int paramInt1, double[] paramArrayOfDouble, int paramInt2);
  
  public abstract void gluTessNormal(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract void gluTessCallback(int paramInt, GLUtessellatorCallback paramGLUtessellatorCallback);
  
  public abstract void gluTessVertex(double[] paramArrayOfDouble, int paramInt, Object paramObject);
  
  public abstract void gluTessBeginPolygon(Object paramObject);
  
  public abstract void gluTessBeginContour();
  
  public abstract void gluTessEndContour();
  
  public abstract void gluTessEndPolygon();
  
  public abstract void gluBeginPolygon();
  
  public abstract void gluNextContour(int paramInt);
  
  public abstract void gluEndPolygon();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.GLUtessellator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */