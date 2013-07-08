package org.newdawn.slick.geom;

public class Curve
  extends Shape
{
  private Vector2f field_407;
  private Vector2f field_408;
  private Vector2f field_409;
  private Vector2f field_410;
  private int segments;
  
  public Curve(Vector2f local_p1, Vector2f local_c1, Vector2f local_c2, Vector2f local_p2)
  {
    this(local_p1, local_c1, local_c2, local_p2, 20);
  }
  
  public Curve(Vector2f local_p1, Vector2f local_c1, Vector2f local_c2, Vector2f local_p2, int segments)
  {
    this.field_407 = new Vector2f(local_p1);
    this.field_408 = new Vector2f(local_c1);
    this.field_409 = new Vector2f(local_c2);
    this.field_410 = new Vector2f(local_p2);
    this.segments = segments;
    this.pointsDirty = true;
  }
  
  public Vector2f pointAt(float local_t)
  {
    float local_a = 1.0F - local_t;
    float local_b = local_t;
    float local_f1 = local_a * local_a * local_a;
    float local_f2 = 3.0F * local_a * local_a * local_b;
    float local_f3 = 3.0F * local_a * local_b * local_b;
    float local_f4 = local_b * local_b * local_b;
    float local_nx = this.field_407.field_1680 * local_f1 + this.field_408.field_1680 * local_f2 + this.field_409.field_1680 * local_f3 + this.field_410.field_1680 * local_f4;
    float local_ny = this.field_407.field_1681 * local_f1 + this.field_408.field_1681 * local_f2 + this.field_409.field_1681 * local_f3 + this.field_410.field_1681 * local_f4;
    return new Vector2f(local_nx, local_ny);
  }
  
  protected void createPoints()
  {
    float step = 1.0F / this.segments;
    this.points = new float[(this.segments + 1) * 2];
    for (int local_i = 0; local_i < this.segments + 1; local_i++)
    {
      float local_t = local_i * step;
      Vector2f local_p = pointAt(local_t);
      this.points[(local_i * 2)] = local_p.field_1680;
      this.points[(local_i * 2 + 1)] = local_p.field_1681;
    }
  }
  
  public Shape transform(Transform transform)
  {
    float[] pts = new float[8];
    float[] dest = new float[8];
    pts[0] = this.field_407.field_1680;
    pts[1] = this.field_407.field_1681;
    pts[2] = this.field_408.field_1680;
    pts[3] = this.field_408.field_1681;
    pts[4] = this.field_409.field_1680;
    pts[5] = this.field_409.field_1681;
    pts[6] = this.field_410.field_1680;
    pts[7] = this.field_410.field_1681;
    transform.transform(pts, 0, dest, 0, 4);
    return new Curve(new Vector2f(dest[0], dest[1]), new Vector2f(dest[2], dest[3]), new Vector2f(dest[4], dest[5]), new Vector2f(dest[6], dest[7]));
  }
  
  public boolean closed()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Curve
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */