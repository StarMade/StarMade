package org.newdawn.slick.geom;

import java.io.Serializable;
import org.newdawn.slick.util.FastTrig;

public class Vector2f
  implements Serializable
{
  private static final long serialVersionUID = 1339934L;
  public float field_1680;
  public float field_1681;
  
  public strictfp Vector2f() {}
  
  public strictfp Vector2f(float[] coords)
  {
    this.field_1680 = coords[0];
    this.field_1681 = coords[1];
  }
  
  public strictfp Vector2f(double theta)
  {
    this.field_1680 = 1.0F;
    this.field_1681 = 0.0F;
    setTheta(theta);
  }
  
  public strictfp void setTheta(double theta)
  {
    if ((theta < -360.0D) || (theta > 360.0D)) {
      theta %= 360.0D;
    }
    if (theta < 0.0D) {
      theta = 360.0D + theta;
    }
    double oldTheta = getTheta();
    if ((theta < -360.0D) || (theta > 360.0D)) {
      oldTheta %= 360.0D;
    }
    if (theta < 0.0D) {
      oldTheta = 360.0D + oldTheta;
    }
    float len = length();
    this.field_1680 = (len * (float)FastTrig.cos(StrictMath.toRadians(theta)));
    this.field_1681 = (len * (float)FastTrig.sin(StrictMath.toRadians(theta)));
  }
  
  public strictfp Vector2f add(double theta)
  {
    setTheta(getTheta() + theta);
    return this;
  }
  
  public strictfp Vector2f sub(double theta)
  {
    setTheta(getTheta() - theta);
    return this;
  }
  
  public strictfp double getTheta()
  {
    double theta = StrictMath.toDegrees(StrictMath.atan2(this.field_1681, this.field_1680));
    if ((theta < -360.0D) || (theta > 360.0D)) {
      theta %= 360.0D;
    }
    if (theta < 0.0D) {
      theta = 360.0D + theta;
    }
    return theta;
  }
  
  public strictfp float getX()
  {
    return this.field_1680;
  }
  
  public strictfp float getY()
  {
    return this.field_1681;
  }
  
  public strictfp Vector2f(Vector2f other)
  {
    this(other.getX(), other.getY());
  }
  
  public strictfp Vector2f(float local_x, float local_y)
  {
    this.field_1680 = local_x;
    this.field_1681 = local_y;
  }
  
  public strictfp void set(Vector2f other)
  {
    set(other.getX(), other.getY());
  }
  
  public strictfp float dot(Vector2f other)
  {
    return this.field_1680 * other.getX() + this.field_1681 * other.getY();
  }
  
  public strictfp Vector2f set(float local_x, float local_y)
  {
    this.field_1680 = local_x;
    this.field_1681 = local_y;
    return this;
  }
  
  public strictfp Vector2f getPerpendicular()
  {
    return new Vector2f(-this.field_1681, this.field_1680);
  }
  
  public strictfp Vector2f set(float[] local_pt)
  {
    return set(local_pt[0], local_pt[1]);
  }
  
  public strictfp Vector2f negate()
  {
    return new Vector2f(-this.field_1680, -this.field_1681);
  }
  
  public strictfp Vector2f negateLocal()
  {
    this.field_1680 = (-this.field_1680);
    this.field_1681 = (-this.field_1681);
    return this;
  }
  
  public strictfp Vector2f add(Vector2f local_v)
  {
    this.field_1680 += local_v.getX();
    this.field_1681 += local_v.getY();
    return this;
  }
  
  public strictfp Vector2f sub(Vector2f local_v)
  {
    this.field_1680 -= local_v.getX();
    this.field_1681 -= local_v.getY();
    return this;
  }
  
  public strictfp Vector2f scale(float local_a)
  {
    this.field_1680 *= local_a;
    this.field_1681 *= local_a;
    return this;
  }
  
  public strictfp Vector2f normalise()
  {
    float local_l = length();
    if (local_l == 0.0F) {
      return this;
    }
    this.field_1680 /= local_l;
    this.field_1681 /= local_l;
    return this;
  }
  
  public strictfp Vector2f getNormal()
  {
    Vector2f local_cp = copy();
    local_cp.normalise();
    return local_cp;
  }
  
  public strictfp float lengthSquared()
  {
    return this.field_1680 * this.field_1680 + this.field_1681 * this.field_1681;
  }
  
  public strictfp float length()
  {
    return (float)Math.sqrt(lengthSquared());
  }
  
  public strictfp void projectOntoUnit(Vector2f local_b, Vector2f result)
  {
    float local_dp = local_b.dot(this);
    result.field_1680 = (local_dp * local_b.getX());
    result.field_1681 = (local_dp * local_b.getY());
  }
  
  public strictfp Vector2f copy()
  {
    return new Vector2f(this.field_1680, this.field_1681);
  }
  
  public strictfp String toString()
  {
    return "[Vector2f " + this.field_1680 + "," + this.field_1681 + " (" + length() + ")]";
  }
  
  public strictfp float distance(Vector2f other)
  {
    return (float)Math.sqrt(distanceSquared(other));
  }
  
  public strictfp float distanceSquared(Vector2f other)
  {
    float local_dx = other.getX() - getX();
    float local_dy = other.getY() - getY();
    return local_dx * local_dx + local_dy * local_dy;
  }
  
  public strictfp int hashCode()
  {
    return 997 * (int)this.field_1680 ^ 991 * (int)this.field_1681;
  }
  
  public strictfp boolean equals(Object other)
  {
    if ((other instanceof Vector2f))
    {
      Vector2f local_o = (Vector2f)other;
      return (local_o.field_1680 == this.field_1680) && (local_o.field_1681 == this.field_1681);
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Vector2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */