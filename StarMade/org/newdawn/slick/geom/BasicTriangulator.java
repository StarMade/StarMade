package org.newdawn.slick.geom;

import java.util.ArrayList;

public class BasicTriangulator
  implements Triangulator
{
  private static final float EPSILON = 1.0E-010F;
  private PointList poly = new PointList();
  private PointList tris = new PointList();
  private boolean tried;
  
  public void addPolyPoint(float local_x, float local_y)
  {
    Point local_p = new Point(local_x, local_y);
    if (!this.poly.contains(local_p)) {
      this.poly.add(local_p);
    }
  }
  
  public int getPolyPointCount()
  {
    return this.poly.size();
  }
  
  public float[] getPolyPoint(int index)
  {
    return new float[] { this.poly.get(index).field_2071, this.poly.get(index).field_2072 };
  }
  
  public boolean triangulate()
  {
    this.tried = true;
    boolean worked = process(this.poly, this.tris);
    return worked;
  }
  
  public int getTriangleCount()
  {
    if (!this.tried) {
      throw new RuntimeException("Call triangulate() before accessing triangles");
    }
    return this.tris.size() / 3;
  }
  
  public float[] getTrianglePoint(int tri, int local_i)
  {
    if (!this.tried) {
      throw new RuntimeException("Call triangulate() before accessing triangles");
    }
    return this.tris.get(tri * 3 + local_i).toArray();
  }
  
  private float area(PointList contour)
  {
    int local_n = contour.size();
    float local_A = 0.0F;
    int local_p = local_n - 1;
    int local_q = 0;
    while (local_q < local_n)
    {
      Point contourP = contour.get(local_p);
      Point contourQ = contour.get(local_q);
      local_A += contourP.getX() * contourQ.getY() - contourQ.getX() * contourP.getY();
      local_p = local_q++;
    }
    return local_A * 0.5F;
  }
  
  private boolean insideTriangle(float local_Ax, float local_Ay, float local_Bx, float local_By, float local_Cx, float local_Cy, float local_Px, float local_Py)
  {
    float local_ax = local_Cx - local_Bx;
    float local_ay = local_Cy - local_By;
    float local_bx = local_Ax - local_Cx;
    float local_by = local_Ay - local_Cy;
    float local_cx = local_Bx - local_Ax;
    float local_cy = local_By - local_Ay;
    float apx = local_Px - local_Ax;
    float apy = local_Py - local_Ay;
    float bpx = local_Px - local_Bx;
    float bpy = local_Py - local_By;
    float cpx = local_Px - local_Cx;
    float cpy = local_Py - local_Cy;
    float aCROSSbp = local_ax * bpy - local_ay * bpx;
    float cCROSSap = local_cx * apy - local_cy * apx;
    float bCROSScp = local_bx * cpy - local_by * cpx;
    return (aCROSSbp >= 0.0F) && (bCROSScp >= 0.0F) && (cCROSSap >= 0.0F);
  }
  
  private boolean snip(PointList contour, int local_u, int local_v, int local_w, int local_n, int[] local_V)
  {
    float local_Ax = contour.get(local_V[local_u]).getX();
    float local_Ay = contour.get(local_V[local_u]).getY();
    float local_Bx = contour.get(local_V[local_v]).getX();
    float local_By = contour.get(local_V[local_v]).getY();
    float local_Cx = contour.get(local_V[local_w]).getX();
    float local_Cy = contour.get(local_V[local_w]).getY();
    if (1.0E-010F > (local_Bx - local_Ax) * (local_Cy - local_Ay) - (local_By - local_Ay) * (local_Cx - local_Ax)) {
      return false;
    }
    for (int local_p = 0; local_p < local_n; local_p++) {
      if ((local_p != local_u) && (local_p != local_v) && (local_p != local_w))
      {
        float local_Px = contour.get(local_V[local_p]).getX();
        float local_Py = contour.get(local_V[local_p]).getY();
        if (insideTriangle(local_Ax, local_Ay, local_Bx, local_By, local_Cx, local_Cy, local_Px, local_Py)) {
          return false;
        }
      }
    }
    return true;
  }
  
  private boolean process(PointList contour, PointList result)
  {
    result.clear();
    int local_n = contour.size();
    if (local_n < 3) {
      return false;
    }
    int[] local_V = new int[local_n];
    if (0.0F < area(contour)) {
      for (int local_v = 0; local_v < local_n; local_v++) {
        local_V[local_v] = local_v;
      }
    } else {
      for (int local_v = 0; local_v < local_n; local_v++) {
        local_V[local_v] = (local_n - 1 - local_v);
      }
    }
    int local_v = local_n;
    int count = 2 * local_v;
    int local_m = 0;
    int local_v1 = local_v - 1;
    while (local_v > 2)
    {
      if (0 >= count--) {
        return false;
      }
      int local_u = local_v1;
      if (local_v <= local_u) {
        local_u = 0;
      }
      local_v1 = local_u + 1;
      if (local_v <= local_v1) {
        local_v1 = 0;
      }
      int local_w = local_v1 + 1;
      if (local_v <= local_w) {
        local_w = 0;
      }
      if (snip(contour, local_u, local_v1, local_w, local_v, local_V))
      {
        int local_a = local_V[local_u];
        int local_b = local_V[local_v1];
        int local_c = local_V[local_w];
        result.add(contour.get(local_a));
        result.add(contour.get(local_b));
        result.add(contour.get(local_c));
        local_m++;
        int local_s = local_v1;
        for (int local_t = local_v1 + 1; local_t < local_v; local_t++)
        {
          local_V[local_s] = local_V[local_t];
          local_s++;
        }
        local_v--;
        count = 2 * local_v;
      }
    }
    return true;
  }
  
  public void startHole() {}
  
  private class PointList
  {
    private ArrayList points = new ArrayList();
    
    public PointList() {}
    
    public boolean contains(BasicTriangulator.Point local_p)
    {
      return this.points.contains(local_p);
    }
    
    public void add(BasicTriangulator.Point point)
    {
      this.points.add(point);
    }
    
    public void remove(BasicTriangulator.Point point)
    {
      this.points.remove(point);
    }
    
    public int size()
    {
      return this.points.size();
    }
    
    public BasicTriangulator.Point get(int local_i)
    {
      return (BasicTriangulator.Point)this.points.get(local_i);
    }
    
    public void clear()
    {
      this.points.clear();
    }
  }
  
  private class Point
  {
    private float field_2071;
    private float field_2072;
    private float[] array;
    
    public Point(float local_x, float local_y)
    {
      this.field_2071 = local_x;
      this.field_2072 = local_y;
      this.array = new float[] { local_x, local_y };
    }
    
    public float getX()
    {
      return this.field_2071;
    }
    
    public float getY()
    {
      return this.field_2072;
    }
    
    public float[] toArray()
    {
      return this.array;
    }
    
    public int hashCode()
    {
      return (int)(this.field_2071 * this.field_2072 * 31.0F);
    }
    
    public boolean equals(Object other)
    {
      if ((other instanceof Point))
      {
        Point local_p = (Point)other;
        return (local_p.field_2071 == this.field_2071) && (local_p.field_2072 == this.field_2072);
      }
      return false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.BasicTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */