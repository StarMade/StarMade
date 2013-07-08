package org.newdawn.slick.svg;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.TexCoordGenerator;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class RadialGradientFill
  implements TexCoordGenerator
{
  private Vector2f centre;
  private float radius;
  private Gradient gradient;
  private Shape shape;
  
  public RadialGradientFill(Shape shape, Transform trans, Gradient gradient)
  {
    this.gradient = gradient;
    this.radius = gradient.getR();
    float local_x = gradient.getX1();
    float local_y = gradient.getY1();
    float[] local_c = { local_x, local_y };
    gradient.getTransform().transform(local_c, 0, local_c, 0, 1);
    trans.transform(local_c, 0, local_c, 0, 1);
    float[] local_rt = { local_x, local_y - this.radius };
    gradient.getTransform().transform(local_rt, 0, local_rt, 0, 1);
    trans.transform(local_rt, 0, local_rt, 0, 1);
    this.centre = new Vector2f(local_c[0], local_c[1]);
    Vector2f dis = new Vector2f(local_rt[0], local_rt[1]);
    this.radius = dis.distance(this.centre);
  }
  
  public Vector2f getCoordFor(float local_x, float local_y)
  {
    float local_u = this.centre.distance(new Vector2f(local_x, local_y));
    local_u /= this.radius;
    if (local_u > 0.99F) {
      local_u = 0.99F;
    }
    return new Vector2f(local_u, 0.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.RadialGradientFill
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */