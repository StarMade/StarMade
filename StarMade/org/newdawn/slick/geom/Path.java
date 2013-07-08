package org.newdawn.slick.geom;

import java.util.ArrayList;

public class Path
  extends Shape
{
  private ArrayList localPoints = new ArrayList();
  private float field_330;
  private float field_331;
  private boolean closed;
  private ArrayList holes = new ArrayList();
  private ArrayList hole;
  
  public Path(float local_sx, float local_sy)
  {
    this.localPoints.add(new float[] { local_sx, local_sy });
    this.field_330 = local_sx;
    this.field_331 = local_sy;
    this.pointsDirty = true;
  }
  
  public void startHole(float local_sx, float local_sy)
  {
    this.hole = new ArrayList();
    this.holes.add(this.hole);
  }
  
  public void lineTo(float local_x, float local_y)
  {
    if (this.hole != null) {
      this.hole.add(new float[] { local_x, local_y });
    } else {
      this.localPoints.add(new float[] { local_x, local_y });
    }
    this.field_330 = local_x;
    this.field_331 = local_y;
    this.pointsDirty = true;
  }
  
  public void close()
  {
    this.closed = true;
  }
  
  public void curveTo(float local_x, float local_y, float cx1, float cy1, float cx2, float cy2)
  {
    curveTo(local_x, local_y, cx1, cy1, cx2, cy2, 10);
  }
  
  public void curveTo(float local_x, float local_y, float cx1, float cy1, float cx2, float cy2, int segments)
  {
    if ((this.field_330 == local_x) && (this.field_331 == local_y)) {
      return;
    }
    Curve curve = new Curve(new Vector2f(this.field_330, this.field_331), new Vector2f(cx1, cy1), new Vector2f(cx2, cy2), new Vector2f(local_x, local_y));
    float step = 1.0F / segments;
    for (int local_i = 1; local_i < segments + 1; local_i++)
    {
      float local_t = local_i * step;
      Vector2f local_p = curve.pointAt(local_t);
      if (this.hole != null) {
        this.hole.add(new float[] { local_p.field_1680, local_p.field_1681 });
      } else {
        this.localPoints.add(new float[] { local_p.field_1680, local_p.field_1681 });
      }
      this.field_330 = local_p.field_1680;
      this.field_331 = local_p.field_1681;
    }
    this.pointsDirty = true;
  }
  
  protected void createPoints()
  {
    this.points = new float[this.localPoints.size() * 2];
    for (int local_i = 0; local_i < this.localPoints.size(); local_i++)
    {
      float[] local_p = (float[])this.localPoints.get(local_i);
      this.points[(local_i * 2)] = local_p[0];
      this.points[(local_i * 2 + 1)] = local_p[1];
    }
  }
  
  public Shape transform(Transform transform)
  {
    Path local_p = new Path(this.field_330, this.field_331);
    local_p.localPoints = transform(this.localPoints, transform);
    for (int local_i = 0; local_i < this.holes.size(); local_i++) {
      local_p.holes.add(transform((ArrayList)this.holes.get(local_i), transform));
    }
    local_p.closed = this.closed;
    return local_p;
  }
  
  private ArrayList transform(ArrayList pts, Transform local_t)
  {
    float[] local_in = new float[pts.size() * 2];
    float[] out = new float[pts.size() * 2];
    for (int local_i = 0; local_i < pts.size(); local_i++)
    {
      local_in[(local_i * 2)] = ((float[])(float[])pts.get(local_i))[0];
      local_in[(local_i * 2 + 1)] = ((float[])(float[])pts.get(local_i))[1];
    }
    local_t.transform(local_in, 0, out, 0, pts.size());
    ArrayList local_i = new ArrayList();
    for (int local_i1 = 0; local_i1 < pts.size(); local_i1++) {
      local_i.add(new float[] { out[(local_i1 * 2)], out[(local_i1 * 2 + 1)] });
    }
    return local_i;
  }
  
  public boolean closed()
  {
    return this.closed;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Path
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */