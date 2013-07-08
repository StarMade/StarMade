package org.newdawn.slick.geom;

public class NeatTriangulator
  implements Triangulator
{
  static final float EPSILON = 1.0E-006F;
  private float[] pointsX = new float[100];
  private float[] pointsY = new float[100];
  private int numPoints = 0;
  private Edge[] edges = new Edge[100];
  private int[] field_415;
  private int numEdges = 0;
  private Triangle[] triangles = new Triangle[100];
  private int numTriangles = 0;
  private float offset = 1.0E-006F;
  
  public void clear()
  {
    this.numPoints = 0;
    this.numEdges = 0;
    this.numTriangles = 0;
  }
  
  private int findEdge(int local_i, int local_j)
  {
    int local_l;
    int local_k;
    int local_l;
    if (local_i < local_j)
    {
      int local_k = local_i;
      local_l = local_j;
    }
    else
    {
      local_k = local_j;
      local_l = local_i;
    }
    for (int local_i1 = 0; local_i1 < this.numEdges; local_i1++) {
      if ((this.edges[local_i1].field_1834 == local_k) && (this.edges[local_i1].field_1835 == local_l)) {
        return local_i1;
      }
    }
    return -1;
  }
  
  private void addEdge(int local_i, int local_j, int local_k)
  {
    int local_l1 = findEdge(local_i, local_j);
    Edge edge;
    Edge edge;
    int local_j1;
    int local_k1;
    if (local_l1 < 0)
    {
      if (this.numEdges == this.edges.length)
      {
        Edge[] aedge = new Edge[this.edges.length * 2];
        System.arraycopy(this.edges, 0, aedge, 0, this.numEdges);
        this.edges = aedge;
      }
      int local_j1 = -1;
      int local_k1 = -1;
      local_l1 = this.numEdges++;
      edge = this.edges[local_l1] =  = new Edge();
    }
    else
    {
      edge = this.edges[local_l1];
      local_j1 = edge.field_1836;
      local_k1 = edge.field_1837;
    }
    int aedge;
    int local_i1;
    if (local_i < local_j)
    {
      int aedge = local_i;
      int local_i1 = local_j;
      local_j1 = local_k;
    }
    else
    {
      aedge = local_j;
      local_i1 = local_i;
      local_k1 = local_k;
    }
    edge.field_1834 = aedge;
    edge.field_1835 = local_i1;
    edge.field_1836 = local_j1;
    edge.field_1837 = local_k1;
    edge.suspect = true;
  }
  
  private void deleteEdge(int local_i, int local_j)
    throws NeatTriangulator.InternalException
  {
    int local_k;
    if (0 > (local_k = findEdge(local_i, local_j))) {
      throw new InternalException("Attempt to delete unknown edge");
    }
    this.edges[local_k] = this.edges[(--this.numEdges)];
  }
  
  void markSuspect(int local_i, int local_j, boolean flag)
    throws NeatTriangulator.InternalException
  {
    int local_k;
    if (0 > (local_k = findEdge(local_i, local_j))) {
      throw new InternalException("Attempt to mark unknown edge");
    }
    this.edges[local_k].suspect = flag;
  }
  
  private Edge chooseSuspect()
  {
    for (int local_i = 0; local_i < this.numEdges; local_i++)
    {
      Edge edge = this.edges[local_i];
      if (edge.suspect)
      {
        edge.suspect = false;
        if ((edge.field_1836 >= 0) && (edge.field_1837 >= 0)) {
          return edge;
        }
      }
    }
    return null;
  }
  
  private static float rho(float local_f, float local_f1, float local_f2, float local_f3, float local_f4, float local_f5)
  {
    float local_f6 = local_f4 - local_f2;
    float local_f7 = local_f5 - local_f3;
    float local_f8 = local_f - local_f4;
    float local_f9 = local_f1 - local_f5;
    float f18 = local_f6 * local_f9 - local_f7 * local_f8;
    if (f18 > 0.0F)
    {
      if (f18 < 1.0E-006F) {
        f18 = 1.0E-006F;
      }
      float f12 = local_f6 * local_f6;
      float f13 = local_f7 * local_f7;
      float f14 = local_f8 * local_f8;
      float f15 = local_f9 * local_f9;
      float f10 = local_f2 - local_f;
      float f11 = local_f3 - local_f1;
      float f16 = f10 * f10;
      float f17 = f11 * f11;
      return (f12 + f13) * (f14 + f15) * (f16 + f17) / (f18 * f18);
    }
    return -1.0F;
  }
  
  private static boolean insideTriangle(float local_f, float local_f1, float local_f2, float local_f3, float local_f4, float local_f5, float local_f6, float local_f7)
  {
    float local_f8 = local_f4 - local_f2;
    float local_f9 = local_f5 - local_f3;
    float f10 = local_f - local_f4;
    float f11 = local_f1 - local_f5;
    float f12 = local_f2 - local_f;
    float f13 = local_f3 - local_f1;
    float f14 = local_f6 - local_f;
    float f15 = local_f7 - local_f1;
    float f16 = local_f6 - local_f2;
    float f17 = local_f7 - local_f3;
    float f18 = local_f6 - local_f4;
    float f19 = local_f7 - local_f5;
    float f22 = local_f8 * f17 - local_f9 * f16;
    float f20 = f12 * f15 - f13 * f14;
    float f21 = f10 * f19 - f11 * f18;
    return (f22 >= 0.0D) && (f21 >= 0.0D) && (f20 >= 0.0D);
  }
  
  private boolean snip(int local_i, int local_j, int local_k, int local_l)
  {
    float local_f = this.pointsX[this.field_415[local_i]];
    float local_f1 = this.pointsY[this.field_415[local_i]];
    float local_f2 = this.pointsX[this.field_415[local_j]];
    float local_f3 = this.pointsY[this.field_415[local_j]];
    float local_f4 = this.pointsX[this.field_415[local_k]];
    float local_f5 = this.pointsY[this.field_415[local_k]];
    if (1.0E-006F > (local_f2 - local_f) * (local_f5 - local_f1) - (local_f3 - local_f1) * (local_f4 - local_f)) {
      return false;
    }
    for (int local_i1 = 0; local_i1 < local_l; local_i1++) {
      if ((local_i1 != local_i) && (local_i1 != local_j) && (local_i1 != local_k))
      {
        float local_f6 = this.pointsX[this.field_415[local_i1]];
        float local_f7 = this.pointsY[this.field_415[local_i1]];
        if (insideTriangle(local_f, local_f1, local_f2, local_f3, local_f4, local_f5, local_f6, local_f7)) {
          return false;
        }
      }
    }
    return true;
  }
  
  private float area()
  {
    float local_f = 0.0F;
    int local_i = this.numPoints - 1;
    int local_j = 0;
    while (local_j < this.numPoints)
    {
      local_f += this.pointsX[local_i] * this.pointsY[local_j] - this.pointsY[local_i] * this.pointsX[local_j];
      local_i = local_j++;
    }
    return local_f * 0.5F;
  }
  
  public void basicTriangulation()
    throws NeatTriangulator.InternalException
  {
    int local_i = this.numPoints;
    if (local_i < 3) {
      return;
    }
    this.numEdges = 0;
    this.numTriangles = 0;
    this.field_415 = new int[local_i];
    if (0.0D < area()) {
      for (int local_k = 0; local_k < local_i; local_k++) {
        this.field_415[local_k] = local_k;
      }
    } else {
      for (int local_k = 0; local_k < local_i; local_k++) {
        this.field_415[local_k] = (this.numPoints - 1 - local_k);
      }
    }
    int local_k = 2 * local_i;
    int local_i1 = local_i - 1;
    while (local_i > 2)
    {
      if (0 >= local_k--) {
        throw new InternalException("Bad polygon");
      }
      int local_j = local_i1;
      if (local_i <= local_j) {
        local_j = 0;
      }
      local_i1 = local_j + 1;
      if (local_i <= local_i1) {
        local_i1 = 0;
      }
      int local_j1 = local_i1 + 1;
      if (local_i <= local_j1) {
        local_j1 = 0;
      }
      if (snip(local_j, local_i1, local_j1, local_i))
      {
        int local_l1 = this.field_415[local_j];
        int local_i2 = this.field_415[local_i1];
        int local_j2 = this.field_415[local_j1];
        if (this.numTriangles == this.triangles.length)
        {
          Triangle[] atriangle = new Triangle[this.triangles.length * 2];
          System.arraycopy(this.triangles, 0, atriangle, 0, this.numTriangles);
          this.triangles = atriangle;
        }
        this.triangles[this.numTriangles] = new Triangle(local_l1, local_i2, local_j2);
        addEdge(local_l1, local_i2, this.numTriangles);
        addEdge(local_i2, local_j2, this.numTriangles);
        addEdge(local_j2, local_l1, this.numTriangles);
        this.numTriangles += 1;
        int atriangle = local_i1;
        for (int local_l2 = local_i1 + 1; local_l2 < local_i; local_l2++)
        {
          this.field_415[atriangle] = this.field_415[local_l2];
          atriangle++;
        }
        local_i--;
        local_k = 2 * local_i;
      }
    }
    this.field_415 = null;
  }
  
  private void optimize()
    throws NeatTriangulator.InternalException
  {
    Edge edge;
    while ((edge = chooseSuspect()) != null)
    {
      int local_i1 = edge.field_1834;
      int local_k1 = edge.field_1835;
      int local_i = edge.field_1836;
      int local_j = edge.field_1837;
      int local_j1 = -1;
      int local_l1 = -1;
      for (int local_k = 0; local_k < 3; local_k++)
      {
        int local_i2 = this.triangles[local_i].field_2233[local_k];
        if ((local_i1 != local_i2) && (local_k1 != local_i2))
        {
          local_l1 = local_i2;
          break;
        }
      }
      for (int local_k = 0; local_k < 3; local_k++)
      {
        int local_i2 = this.triangles[local_j].field_2233[local_k];
        if ((local_i1 != local_i2) && (local_k1 != local_i2))
        {
          local_j1 = local_i2;
          break;
        }
      }
      if ((-1 == local_j1) || (-1 == local_l1)) {
        throw new InternalException("can't find quad");
      }
      float local_k = this.pointsX[local_i1];
      float local_i2 = this.pointsY[local_i1];
      float local_f2 = this.pointsX[local_j1];
      float local_f3 = this.pointsY[local_j1];
      float local_f4 = this.pointsX[local_k1];
      float local_f5 = this.pointsY[local_k1];
      float local_f6 = this.pointsX[local_l1];
      float local_f7 = this.pointsY[local_l1];
      float local_f8 = rho(local_k, local_i2, local_f2, local_f3, local_f4, local_f5);
      float local_f9 = rho(local_k, local_i2, local_f4, local_f5, local_f6, local_f7);
      float f10 = rho(local_f2, local_f3, local_f4, local_f5, local_f6, local_f7);
      float f11 = rho(local_f2, local_f3, local_f6, local_f7, local_k, local_i2);
      if ((0.0F > local_f8) || (0.0F > local_f9)) {
        throw new InternalException("original triangles backwards");
      }
      if ((0.0F <= f10) && (0.0F <= f11))
      {
        if (local_f8 > local_f9) {
          local_f8 = local_f9;
        }
        if (f10 > f11) {
          f10 = f11;
        }
        if (local_f8 > f10)
        {
          deleteEdge(local_i1, local_k1);
          this.triangles[local_i].field_2233[0] = local_j1;
          this.triangles[local_i].field_2233[1] = local_k1;
          this.triangles[local_i].field_2233[2] = local_l1;
          this.triangles[local_j].field_2233[0] = local_j1;
          this.triangles[local_j].field_2233[1] = local_l1;
          this.triangles[local_j].field_2233[2] = local_i1;
          addEdge(local_j1, local_k1, local_i);
          addEdge(local_k1, local_l1, local_i);
          addEdge(local_l1, local_j1, local_i);
          addEdge(local_l1, local_i1, local_j);
          addEdge(local_i1, local_j1, local_j);
          addEdge(local_j1, local_l1, local_j);
          markSuspect(local_j1, local_l1, false);
        }
      }
    }
  }
  
  public boolean triangulate()
  {
    try
    {
      basicTriangulation();
      return true;
    }
    catch (InternalException local_e)
    {
      this.numEdges = 0;
    }
    return false;
  }
  
  public void addPolyPoint(float local_x, float local_y)
  {
    for (int local_i = 0; local_i < this.numPoints; local_i++) {
      if ((this.pointsX[local_i] == local_x) && (this.pointsY[local_i] == local_y))
      {
        local_y += this.offset;
        this.offset += 1.0E-006F;
      }
    }
    if (this.numPoints == this.pointsX.length)
    {
      float[] local_i = new float[this.numPoints * 2];
      System.arraycopy(this.pointsX, 0, local_i, 0, this.numPoints);
      this.pointsX = local_i;
      local_i = new float[this.numPoints * 2];
      System.arraycopy(this.pointsY, 0, local_i, 0, this.numPoints);
      this.pointsY = local_i;
    }
    this.pointsX[this.numPoints] = local_x;
    this.pointsY[this.numPoints] = local_y;
    this.numPoints += 1;
  }
  
  public int getTriangleCount()
  {
    return this.numTriangles;
  }
  
  public float[] getTrianglePoint(int tri, int local_i)
  {
    float local_xp = this.pointsX[this.triangles[tri].field_2233[local_i]];
    float local_yp = this.pointsY[this.triangles[tri].field_2233[local_i]];
    return new float[] { local_xp, local_yp };
  }
  
  public void startHole() {}
  
  class InternalException
    extends Exception
  {
    public InternalException(String msg)
    {
      super();
    }
  }
  
  class Edge
  {
    int field_1834 = -1;
    int field_1835 = -1;
    int field_1836 = -1;
    int field_1837 = -1;
    boolean suspect;
    
    Edge() {}
  }
  
  class Triangle
  {
    int[] field_2233 = new int[3];
    
    Triangle(int local_i, int local_j, int local_k)
    {
      this.field_2233[0] = local_i;
      this.field_2233[1] = local_j;
      this.field_2233[2] = local_k;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.NeatTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */