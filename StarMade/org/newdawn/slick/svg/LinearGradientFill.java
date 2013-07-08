package org.newdawn.slick.svg;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.TexCoordGenerator;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class LinearGradientFill
  implements TexCoordGenerator
{
  private Vector2f start;
  private Vector2f end;
  private Gradient gradient;
  private Line line;
  private Shape shape;
  
  public LinearGradientFill(Shape shape, Transform trans, Gradient gradient)
  {
    this.gradient = gradient;
    float local_x = gradient.getX1();
    float local_y = gradient.getY1();
    float local_mx = gradient.getX2();
    float local_my = gradient.getY2();
    float local_h = local_my - local_y;
    float local_w = local_mx - local_x;
    float[] local_s = { local_x, local_y + local_h / 2.0F };
    gradient.getTransform().transform(local_s, 0, local_s, 0, 1);
    trans.transform(local_s, 0, local_s, 0, 1);
    float[] local_e = { local_x + local_w, local_y + local_h / 2.0F };
    gradient.getTransform().transform(local_e, 0, local_e, 0, 1);
    trans.transform(local_e, 0, local_e, 0, 1);
    this.start = new Vector2f(local_s[0], local_s[1]);
    this.end = new Vector2f(local_e[0], local_e[1]);
    this.line = new Line(this.start, this.end);
  }
  
  public Vector2f getCoordFor(float local_x, float local_y)
  {
    Vector2f result = new Vector2f();
    this.line.getClosestPoint(new Vector2f(local_x, local_y), result);
    float local_u = result.distance(this.start);
    local_u /= this.line.length();
    return new Vector2f(local_u, 0.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.LinearGradientFill
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */