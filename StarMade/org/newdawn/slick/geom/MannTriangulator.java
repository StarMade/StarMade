package org.newdawn.slick.geom;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MannTriangulator
  implements Triangulator
{
  private static final double EPSILON = 1.E-005D;
  protected PointBag contour = getPointBag();
  protected PointBag holes;
  private PointBag nextFreePointBag;
  private Point nextFreePoint;
  private List triangles = new ArrayList();
  
  public void addPolyPoint(float local_x, float local_y)
  {
    addPoint(new Vector2f(local_x, local_y));
  }
  
  public void reset()
  {
    while (this.holes != null) {
      this.holes = freePointBag(this.holes);
    }
    this.contour.clear();
    this.holes = null;
  }
  
  public void startHole()
  {
    PointBag newHole = getPointBag();
    newHole.next = this.holes;
    this.holes = newHole;
  }
  
  private void addPoint(Vector2f local_pt)
  {
    if (this.holes == null)
    {
      Point local_p = getPoint(local_pt);
      this.contour.add(local_p);
    }
    else
    {
      Point local_p = getPoint(local_pt);
      this.holes.add(local_p);
    }
  }
  
  private Vector2f[] triangulate(Vector2f[] result)
  {
    this.contour.computeAngles();
    for (PointBag hole = this.holes; hole != null; hole = hole.next) {
      hole.computeAngles();
    }
    while (this.holes != null)
    {
      Point hole = this.holes.first;
      do
      {
        if (hole.angle <= 0.0D)
        {
          Point pContour = this.contour.first;
          do
          {
            if ((hole.isInfront(pContour)) && (pContour.isInfront(hole)) && (!this.contour.doesIntersectSegment(hole.field_2200, pContour.field_2200)))
            {
              PointBag hole = this.holes;
              while (!hole.doesIntersectSegment(hole.field_2200, pContour.field_2200)) {
                if ((hole = hole.next) == null)
                {
                  Point newPtContour = getPoint(pContour.field_2200);
                  pContour.insertAfter(newPtContour);
                  Point newPtHole = getPoint(hole.field_2200);
                  hole.insertBefore(newPtHole);
                  pContour.next = hole;
                  hole.prev = pContour;
                  newPtHole.next = newPtContour;
                  newPtContour.prev = newPtHole;
                  pContour.computeAngle();
                  hole.computeAngle();
                  newPtContour.computeAngle();
                  newPtHole.computeAngle();
                  this.holes.first = null;
                  break label247;
                }
              }
            }
          } while ((pContour = pContour.next) != this.contour.first);
        }
      } while ((hole = hole.next) != this.holes.first);
      label247:
      this.holes = freePointBag(this.holes);
    }
    int hole = this.contour.countPoints() - 2;
    int pContour = hole * 3 + 1;
    if (result.length < pContour) {
      result = (Vector2f[])Array.newInstance(result.getClass().getComponentType(), pContour);
    }
    int hole = 0;
    for (;;)
    {
      Point newPtContour = this.contour.first;
      if ((newPtContour == null) || (newPtContour.next == newPtContour.prev)) {
        break;
      }
      do
      {
        if (newPtContour.angle > 0.0D)
        {
          Point newPtHole = newPtContour.prev;
          Point next = newPtContour.next;
          if (((next.next == newPtHole) || ((newPtHole.isInfront(next)) && (next.isInfront(newPtHole)))) && (!this.contour.doesIntersectSegment(newPtHole.field_2200, next.field_2200)))
          {
            result[(hole++)] = newPtContour.field_2200;
            result[(hole++)] = next.field_2200;
            result[(hole++)] = newPtHole.field_2200;
            break;
          }
        }
      } while ((newPtContour = newPtContour.next) != this.contour.first);
      Point newPtHole = newPtContour.prev;
      Point next = newPtContour.next;
      this.contour.first = newPtHole;
      newPtContour.unlink();
      freePoint(newPtContour);
      next.computeAngle();
      newPtHole.computeAngle();
    }
    result[hole] = null;
    this.contour.clear();
    return result;
  }
  
  private PointBag getPointBag()
  {
    PointBag local_pb = this.nextFreePointBag;
    if (local_pb != null)
    {
      this.nextFreePointBag = local_pb.next;
      local_pb.next = null;
      return local_pb;
    }
    return new PointBag();
  }
  
  private PointBag freePointBag(PointBag local_pb)
  {
    PointBag next = local_pb.next;
    local_pb.clear();
    local_pb.next = this.nextFreePointBag;
    this.nextFreePointBag = local_pb;
    return next;
  }
  
  private Point getPoint(Vector2f local_pt)
  {
    Point local_p = this.nextFreePoint;
    if (local_p != null)
    {
      this.nextFreePoint = local_p.next;
      local_p.next = null;
      local_p.prev = null;
      local_p.field_2200 = local_pt;
      return local_p;
    }
    return new Point(local_pt);
  }
  
  private void freePoint(Point local_p)
  {
    local_p.next = this.nextFreePoint;
    this.nextFreePoint = local_p;
  }
  
  private void freePoints(Point head)
  {
    head.prev.next = this.nextFreePoint;
    head.prev = null;
    this.nextFreePoint = head;
  }
  
  public boolean triangulate()
  {
    Vector2f[] temp = triangulate(new Vector2f[0]);
    for (int local_i = 0; (local_i < temp.length) && (temp[local_i] != null); local_i++) {
      this.triangles.add(temp[local_i]);
    }
    return true;
  }
  
  public int getTriangleCount()
  {
    return this.triangles.size() / 3;
  }
  
  public float[] getTrianglePoint(int tri, int local_i)
  {
    Vector2f local_pt = (Vector2f)this.triangles.get(tri * 3 + local_i);
    return new float[] { local_pt.field_1680, local_pt.field_1681 };
  }
  
  protected class PointBag
    implements Serializable
  {
    protected MannTriangulator.Point first;
    protected PointBag next;
    
    protected PointBag() {}
    
    public void clear()
    {
      if (this.first != null)
      {
        MannTriangulator.this.freePoints(this.first);
        this.first = null;
      }
    }
    
    public void add(MannTriangulator.Point local_p)
    {
      if (this.first != null)
      {
        this.first.insertBefore(local_p);
      }
      else
      {
        this.first = local_p;
        local_p.next = local_p;
        local_p.prev = local_p;
      }
    }
    
    public void computeAngles()
    {
      if (this.first == null) {
        return;
      }
      MannTriangulator.Point local_p = this.first;
      do
      {
        local_p.computeAngle();
      } while ((local_p = local_p.next) != this.first);
    }
    
    public boolean doesIntersectSegment(Vector2f local_v1, Vector2f local_v2)
    {
      double dxA = local_v2.field_1680 - local_v1.field_1680;
      double dyA = local_v2.field_1681 - local_v1.field_1681;
      MannTriangulator.Point local_n;
      for (MannTriangulator.Point local_p = this.first;; local_p = local_n)
      {
        local_n = local_p.next;
        if ((local_p.field_2200 != local_v1) && (local_n.field_2200 != local_v1) && (local_p.field_2200 != local_v2) && (local_n.field_2200 != local_v2))
        {
          double dxB = local_n.field_2200.field_1680 - local_p.field_2200.field_1680;
          double dyB = local_n.field_2200.field_1681 - local_p.field_2200.field_1681;
          double local_d = dxA * dyB - dyA * dxB;
          if (Math.abs(local_d) > 1.E-005D)
          {
            double tmp1 = local_p.field_2200.field_1680 - local_v1.field_1680;
            double tmp2 = local_p.field_2200.field_1681 - local_v1.field_1681;
            double local_tA = (dyB * tmp1 - dxB * tmp2) / local_d;
            double local_tB = (dyA * tmp1 - dxA * tmp2) / local_d;
            if ((local_tA >= 0.0D) && (local_tA <= 1.0D) && (local_tB >= 0.0D) && (local_tB <= 1.0D)) {
              return true;
            }
          }
        }
        if (local_n == this.first) {
          return false;
        }
      }
    }
    
    public int countPoints()
    {
      if (this.first == null) {
        return 0;
      }
      int count = 0;
      MannTriangulator.Point local_p = this.first;
      do
      {
        count++;
      } while ((local_p = local_p.next) != this.first);
      return count;
    }
    
    public boolean contains(Vector2f point)
    {
      if (this.first == null) {
        return false;
      }
      if (this.first.prev.field_2200.equals(point)) {
        return true;
      }
      return this.first.field_2200.equals(point);
    }
  }
  
  private static class Point
    implements Serializable
  {
    protected Vector2f field_2200;
    protected Point prev;
    protected Point next;
    protected double field_2201;
    protected double field_2202;
    protected double angle;
    protected double dist;
    
    public Point(Vector2f local_pt)
    {
      this.field_2200 = local_pt;
    }
    
    public void unlink()
    {
      this.prev.next = this.next;
      this.next.prev = this.prev;
      this.next = null;
      this.prev = null;
    }
    
    public void insertBefore(Point local_p)
    {
      this.prev.next = local_p;
      local_p.prev = this.prev;
      local_p.next = this;
      this.prev = local_p;
    }
    
    public void insertAfter(Point local_p)
    {
      this.next.prev = local_p;
      local_p.prev = this;
      local_p.next = this.next;
      this.next = local_p;
    }
    
    private double hypot(double local_x, double local_y)
    {
      return Math.sqrt(local_x * local_x + local_y * local_y);
    }
    
    public void computeAngle()
    {
      if (this.prev.field_2200.equals(this.field_2200)) {
        this.field_2200.field_1680 += 0.01F;
      }
      double dx1 = this.field_2200.field_1680 - this.prev.field_2200.field_1680;
      double dy1 = this.field_2200.field_1681 - this.prev.field_2200.field_1681;
      double len1 = hypot(dx1, dy1);
      dx1 /= len1;
      dy1 /= len1;
      if (this.next.field_2200.equals(this.field_2200)) {
        this.field_2200.field_1681 += 0.01F;
      }
      double dx2 = this.next.field_2200.field_1680 - this.field_2200.field_1680;
      double dy2 = this.next.field_2200.field_1681 - this.field_2200.field_1681;
      double len2 = hypot(dx2, dy2);
      dx2 /= len2;
      dy2 /= len2;
      double nx1 = -dy1;
      double ny1 = dx1;
      this.field_2201 = ((nx1 - dy2) * 0.5D);
      this.field_2202 = ((ny1 + dx2) * 0.5D);
      if (this.field_2201 * this.field_2201 + this.field_2202 * this.field_2202 < 1.E-005D)
      {
        this.field_2201 = dx1;
        this.field_2202 = dy2;
        this.angle = 1.0D;
        if (dx1 * dx2 + dy1 * dy2 > 0.0D)
        {
          this.field_2201 = (-dx1);
          this.field_2202 = (-dy1);
        }
      }
      else
      {
        this.angle = (this.field_2201 * dx2 + this.field_2202 * dy2);
      }
    }
    
    public double getAngle(Point local_p)
    {
      double local_dx = local_p.field_2200.field_1680 - this.field_2200.field_1680;
      double local_dy = local_p.field_2200.field_1681 - this.field_2200.field_1681;
      double dlen = hypot(local_dx, local_dy);
      return (this.field_2201 * local_dx + this.field_2202 * local_dy) / dlen;
    }
    
    public boolean isConcave()
    {
      return this.angle < 0.0D;
    }
    
    public boolean isInfront(double local_dx, double local_dy)
    {
      boolean sidePrev = (this.prev.field_2200.field_1681 - this.field_2200.field_1681) * local_dx + (this.field_2200.field_1680 - this.prev.field_2200.field_1680) * local_dy >= 0.0D;
      boolean sideNext = (this.field_2200.field_1681 - this.next.field_2200.field_1681) * local_dx + (this.next.field_2200.field_1680 - this.field_2200.field_1680) * local_dy >= 0.0D;
      return this.angle < 0.0D ? sidePrev | sideNext : sidePrev & sideNext;
    }
    
    public boolean isInfront(Point local_p)
    {
      return isInfront(local_p.field_2200.field_1680 - this.field_2200.field_1680, local_p.field_2200.field_1681 - this.field_2200.field_1681);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.MannTriangulator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */