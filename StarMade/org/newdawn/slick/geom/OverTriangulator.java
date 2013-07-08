/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   8:    */public class OverTriangulator
/*   9:    */  implements Triangulator
/*  10:    */{
/*  11:    */  private float[][] triangles;
/*  12:    */  
/*  17:    */  public OverTriangulator(Triangulator tris)
/*  18:    */  {
/*  19: 19 */    this.triangles = new float[tris.getTriangleCount() * 6 * 3][2];
/*  20:    */    
/*  21: 21 */    int tcount = 0;
/*  22: 22 */    for (int i = 0; i < tris.getTriangleCount(); i++) {
/*  23: 23 */      float cx = 0.0F;
/*  24: 24 */      float cy = 0.0F;
/*  25: 25 */      for (int p = 0; p < 3; p++) {
/*  26: 26 */        float[] pt = tris.getTrianglePoint(i, p);
/*  27: 27 */        cx += pt[0];
/*  28: 28 */        cy += pt[1];
/*  29:    */      }
/*  30:    */      
/*  31: 31 */      cx /= 3.0F;
/*  32: 32 */      cy /= 3.0F;
/*  33:    */      
/*  34: 34 */      for (int p = 0; p < 3; p++) {
/*  35: 35 */        int n = p + 1;
/*  36: 36 */        if (n > 2) {
/*  37: 37 */          n = 0;
/*  38:    */        }
/*  39:    */        
/*  40: 40 */        float[] pt1 = tris.getTrianglePoint(i, p);
/*  41: 41 */        float[] pt2 = tris.getTrianglePoint(i, n);
/*  42:    */        
/*  43: 43 */        pt1[0] = ((pt1[0] + pt2[0]) / 2.0F);
/*  44: 44 */        pt1[1] = ((pt1[1] + pt2[1]) / 2.0F);
/*  45:    */        
/*  46: 46 */        this.triangles[(tcount * 3 + 0)][0] = cx;
/*  47: 47 */        this.triangles[(tcount * 3 + 0)][1] = cy;
/*  48: 48 */        this.triangles[(tcount * 3 + 1)][0] = pt1[0];
/*  49: 49 */        this.triangles[(tcount * 3 + 1)][1] = pt1[1];
/*  50: 50 */        this.triangles[(tcount * 3 + 2)][0] = pt2[0];
/*  51: 51 */        this.triangles[(tcount * 3 + 2)][1] = pt2[1];
/*  52: 52 */        tcount++;
/*  53:    */      }
/*  54:    */      
/*  55: 55 */      for (int p = 0; p < 3; p++) {
/*  56: 56 */        int n = p + 1;
/*  57: 57 */        if (n > 2) {
/*  58: 58 */          n = 0;
/*  59:    */        }
/*  60:    */        
/*  61: 61 */        float[] pt1 = tris.getTrianglePoint(i, p);
/*  62: 62 */        float[] pt2 = tris.getTrianglePoint(i, n);
/*  63:    */        
/*  64: 64 */        pt2[0] = ((pt1[0] + pt2[0]) / 2.0F);
/*  65: 65 */        pt2[1] = ((pt1[1] + pt2[1]) / 2.0F);
/*  66:    */        
/*  67: 67 */        this.triangles[(tcount * 3 + 0)][0] = cx;
/*  68: 68 */        this.triangles[(tcount * 3 + 0)][1] = cy;
/*  69: 69 */        this.triangles[(tcount * 3 + 1)][0] = pt1[0];
/*  70: 70 */        this.triangles[(tcount * 3 + 1)][1] = pt1[1];
/*  71: 71 */        this.triangles[(tcount * 3 + 2)][0] = pt2[0];
/*  72: 72 */        this.triangles[(tcount * 3 + 2)][1] = pt2[1];
/*  73: 73 */        tcount++;
/*  74:    */      }
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  81:    */  public void addPolyPoint(float x, float y) {}
/*  82:    */  
/*  86:    */  public int getTriangleCount()
/*  87:    */  {
/*  88: 88 */    return this.triangles.length / 3;
/*  89:    */  }
/*  90:    */  
/*  93:    */  public float[] getTrianglePoint(int tri, int i)
/*  94:    */  {
/*  95: 95 */    float[] pt = this.triangles[(tri * 3 + i)];
/*  96:    */    
/*  97: 97 */    return new float[] { pt[0], pt[1] };
/*  98:    */  }
/*  99:    */  
/* 103:    */  public void startHole() {}
/* 104:    */  
/* 108:    */  public boolean triangulate()
/* 109:    */  {
/* 110:110 */    return true;
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.OverTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */