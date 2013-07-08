package org.newdawn.slick.geom;

import java.util.ArrayList;

public class GeomUtil
{
  public float EPSILON = 1.0E-004F;
  public float EDGE_SCALE = 1.0F;
  public int MAX_POINTS = 10000;
  public GeomUtilListener listener;
  
  public Shape[] subtract(Shape target, Shape missing)
  {
    target = target.transform(new Transform());
    missing = missing.transform(new Transform());
    int count = 0;
    for (int local_i = 0; local_i < target.getPointCount(); local_i++) {
      if (missing.contains(target.getPoint(local_i)[0], target.getPoint(local_i)[1])) {
        count++;
      }
    }
    if (count == target.getPointCount()) {
      return new Shape[0];
    }
    if (!target.intersects(missing)) {
      return new Shape[] { target };
    }
    int local_i = 0;
    for (int local_i1 = 0; local_i1 < missing.getPointCount(); local_i1++) {
      if ((target.contains(missing.getPoint(local_i1)[0], missing.getPoint(local_i1)[1])) && (!onPath(target, missing.getPoint(local_i1)[0], missing.getPoint(local_i1)[1]))) {
        local_i++;
      }
    }
    for (int local_i1 = 0; local_i1 < target.getPointCount(); local_i1++) {
      if ((missing.contains(target.getPoint(local_i1)[0], target.getPoint(local_i1)[1])) && (!onPath(missing, target.getPoint(local_i1)[0], target.getPoint(local_i1)[1]))) {
        local_i++;
      }
    }
    if (local_i < 1) {
      return new Shape[] { target };
    }
    return combine(target, missing, true);
  }
  
  private boolean onPath(Shape path, float local_x, float local_y)
  {
    for (int local_i = 0; local_i < path.getPointCount() + 1; local_i++)
    {
      int local_n = rationalPoint(path, local_i + 1);
      Line line = getLine(path, rationalPoint(path, local_i), local_n);
      if (line.distance(new Vector2f(local_x, local_y)) < this.EPSILON * 100.0F) {
        return true;
      }
    }
    return false;
  }
  
  public void setListener(GeomUtilListener listener)
  {
    this.listener = listener;
  }
  
  public Shape[] union(Shape target, Shape other)
  {
    target = target.transform(new Transform());
    other = other.transform(new Transform());
    if (!target.intersects(other)) {
      return new Shape[] { target, other };
    }
    boolean touches = false;
    int buttCount = 0;
    for (int local_i = 0; local_i < target.getPointCount(); local_i++)
    {
      if ((other.contains(target.getPoint(local_i)[0], target.getPoint(local_i)[1])) && (!other.hasVertex(target.getPoint(local_i)[0], target.getPoint(local_i)[1])))
      {
        touches = true;
        break;
      }
      if (other.hasVertex(target.getPoint(local_i)[0], target.getPoint(local_i)[1])) {
        buttCount++;
      }
    }
    for (int local_i = 0; local_i < other.getPointCount(); local_i++) {
      if ((target.contains(other.getPoint(local_i)[0], other.getPoint(local_i)[1])) && (!target.hasVertex(other.getPoint(local_i)[0], other.getPoint(local_i)[1])))
      {
        touches = true;
        break;
      }
    }
    if ((!touches) && (buttCount < 2)) {
      return new Shape[] { target, other };
    }
    return combine(target, other, false);
  }
  
  private Shape[] combine(Shape target, Shape other, boolean subtract)
  {
    if (subtract)
    {
      ArrayList shapes = new ArrayList();
      ArrayList used = new ArrayList();
      for (int local_i = 0; local_i < target.getPointCount(); local_i++)
      {
        float[] point = target.getPoint(local_i);
        if (other.contains(point[0], point[1]))
        {
          used.add(new Vector2f(point[0], point[1]));
          if (this.listener != null) {
            this.listener.pointExcluded(point[0], point[1]);
          }
        }
      }
      for (int local_i = 0; local_i < target.getPointCount(); local_i++)
      {
        float[] point = target.getPoint(local_i);
        Vector2f local_pt = new Vector2f(point[0], point[1]);
        if (!used.contains(local_pt))
        {
          Shape result = combineSingle(target, other, true, local_i);
          shapes.add(result);
          for (int local_j = 0; local_j < result.getPointCount(); local_j++)
          {
            float[] kpoint = result.getPoint(local_j);
            Vector2f kpt = new Vector2f(kpoint[0], kpoint[1]);
            used.add(kpt);
          }
        }
      }
      return (Shape[])shapes.toArray(new Shape[0]);
    }
    for (int shapes = 0; shapes < target.getPointCount(); shapes++) {
      if ((!other.contains(target.getPoint(shapes)[0], target.getPoint(shapes)[1])) && (!other.hasVertex(target.getPoint(shapes)[0], target.getPoint(shapes)[1])))
      {
        Shape used = combineSingle(target, other, false, shapes);
        return new Shape[] { used };
      }
    }
    return new Shape[] { other };
  }
  
  private Shape combineSingle(Shape target, Shape missing, boolean subtract, int start)
  {
    Shape current = target;
    Shape other = missing;
    int point = start;
    int dir = 1;
    Polygon poly = new Polygon();
    boolean first = true;
    int loop = 0;
    float local_px = current.getPoint(point)[0];
    float local_py = current.getPoint(point)[1];
    while ((!poly.hasVertex(local_px, local_py)) || (first) || (current != target))
    {
      first = false;
      loop++;
      if (loop > this.MAX_POINTS) {
        break;
      }
      poly.addPoint(local_px, local_py);
      if (this.listener != null) {
        this.listener.pointUsed(local_px, local_py);
      }
      Line line = getLine(current, local_px, local_py, rationalPoint(current, point + dir));
      HitResult hit = intersect(other, line);
      if (hit != null)
      {
        Line hitLine = hit.line;
        Vector2f local_pt = hit.field_502;
        local_px = local_pt.field_1680;
        local_py = local_pt.field_1681;
        if (this.listener != null) {
          this.listener.pointIntersected(local_px, local_py);
        }
        if (other.hasVertex(local_px, local_py))
        {
          point = other.indexOf(local_pt.field_1680, local_pt.field_1681);
          dir = 1;
          local_px = local_pt.field_1680;
          local_py = local_pt.field_1681;
          Shape temp = current;
          current = other;
          other = temp;
        }
        else
        {
          float temp = hitLine.getDX() / hitLine.length();
          float local_dy = hitLine.getDY() / hitLine.length();
          temp *= this.EDGE_SCALE;
          local_dy *= this.EDGE_SCALE;
          if (current.contains(local_pt.field_1680 + temp, local_pt.field_1681 + local_dy))
          {
            if (subtract)
            {
              if (current == missing)
              {
                point = hit.field_501;
                dir = -1;
              }
              else
              {
                point = hit.field_500;
                dir = 1;
              }
            }
            else if (current == target)
            {
              point = hit.field_501;
              dir = -1;
            }
            else
            {
              point = hit.field_501;
              dir = -1;
            }
            Shape temp = current;
            current = other;
            other = temp;
          }
          else if (current.contains(local_pt.field_1680 - temp, local_pt.field_1681 - local_dy))
          {
            if (subtract)
            {
              if (current == target)
              {
                point = hit.field_501;
                dir = -1;
              }
              else
              {
                point = hit.field_500;
                dir = 1;
              }
            }
            else if (current == missing)
            {
              point = hit.field_500;
              dir = 1;
            }
            else
            {
              point = hit.field_500;
              dir = 1;
            }
            Shape temp = current;
            current = other;
            other = temp;
          }
          else
          {
            if (subtract) {
              break;
            }
            point = hit.field_500;
            dir = 1;
            Shape temp = current;
            current = other;
            other = temp;
            point = rationalPoint(current, point + dir);
            local_px = current.getPoint(point)[0];
            local_py = current.getPoint(point)[1];
          }
        }
      }
      else
      {
        point = rationalPoint(current, point + dir);
        local_px = current.getPoint(point)[0];
        local_py = current.getPoint(point)[1];
      }
    }
    poly.addPoint(local_px, local_py);
    if (this.listener != null) {
      this.listener.pointUsed(local_px, local_py);
    }
    return poly;
  }
  
  public HitResult intersect(Shape shape, Line line)
  {
    float distance = 3.4028235E+38F;
    HitResult hit = null;
    for (int local_i = 0; local_i < shape.getPointCount(); local_i++)
    {
      int next = rationalPoint(shape, local_i + 1);
      Line local = getLine(shape, local_i, next);
      Vector2f local_pt = line.intersect(local, true);
      if (local_pt != null)
      {
        float newDis = local_pt.distance(line.getStart());
        if ((newDis < distance) && (newDis > this.EPSILON))
        {
          hit = new HitResult();
          hit.field_502 = local_pt;
          hit.line = local;
          hit.field_500 = local_i;
          hit.field_501 = next;
          distance = newDis;
        }
      }
    }
    return hit;
  }
  
  public static int rationalPoint(Shape shape, int local_p)
  {
    while (local_p < 0) {
      local_p += shape.getPointCount();
    }
    while (local_p >= shape.getPointCount()) {
      local_p -= shape.getPointCount();
    }
    return local_p;
  }
  
  public Line getLine(Shape shape, int local_s, int local_e)
  {
    float[] start = shape.getPoint(local_s);
    float[] end = shape.getPoint(local_e);
    Line line = new Line(start[0], start[1], end[0], end[1]);
    return line;
  }
  
  public Line getLine(Shape shape, float local_sx, float local_sy, int local_e)
  {
    float[] end = shape.getPoint(local_e);
    Line line = new Line(local_sx, local_sy, end[0], end[1]);
    return line;
  }
  
  public class HitResult
  {
    public Line line;
    public int field_500;
    public int field_501;
    public Vector2f field_502;
    
    public HitResult() {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.GeomUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */