package org.newdawn.slick.geom;

import org.newdawn.slick.util.FastTrig;

public class Transform
{
  private float[] matrixPosition;
  
  public Transform()
  {
    this.matrixPosition = new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F };
  }
  
  public Transform(Transform other)
  {
    this.matrixPosition = new float[9];
    for (int local_i = 0; local_i < 9; local_i++) {
      this.matrixPosition[local_i] = other.matrixPosition[local_i];
    }
  }
  
  public Transform(Transform local_t1, Transform local_t2)
  {
    this(local_t1);
    concatenate(local_t2);
  }
  
  public Transform(float[] matrixPosition)
  {
    if (matrixPosition.length != 6) {
      throw new RuntimeException("The parameter must be a float array of length 6.");
    }
    this.matrixPosition = new float[] { matrixPosition[0], matrixPosition[1], matrixPosition[2], matrixPosition[3], matrixPosition[4], matrixPosition[5], 0.0F, 0.0F, 1.0F };
  }
  
  public Transform(float point00, float point01, float point02, float point10, float point11, float point12)
  {
    this.matrixPosition = new float[] { point00, point01, point02, point10, point11, point12, 0.0F, 0.0F, 1.0F };
  }
  
  public void transform(float[] source, int sourceOffset, float[] destination, int destOffset, int numberOfPoints)
  {
    float[] result = source == destination ? new float[numberOfPoints * 2] : destination;
    for (int local_i = 0; local_i < numberOfPoints * 2; local_i += 2) {
      for (int local_j = 0; local_j < 6; local_j += 3) {
        result[(local_i + local_j / 3)] = (source[(local_i + sourceOffset)] * this.matrixPosition[local_j] + source[(local_i + sourceOffset + 1)] * this.matrixPosition[(local_j + 1)] + 1.0F * this.matrixPosition[(local_j + 2)]);
      }
    }
    if (source == destination) {
      for (int local_i = 0; local_i < numberOfPoints * 2; local_i += 2)
      {
        destination[(local_i + destOffset)] = result[local_i];
        destination[(local_i + destOffset + 1)] = result[(local_i + 1)];
      }
    }
  }
  
  public Transform concatenate(Transform local_tx)
  {
    float[] local_mp = new float[9];
    float n00 = this.matrixPosition[0] * local_tx.matrixPosition[0] + this.matrixPosition[1] * local_tx.matrixPosition[3];
    float n01 = this.matrixPosition[0] * local_tx.matrixPosition[1] + this.matrixPosition[1] * local_tx.matrixPosition[4];
    float n02 = this.matrixPosition[0] * local_tx.matrixPosition[2] + this.matrixPosition[1] * local_tx.matrixPosition[5] + this.matrixPosition[2];
    float n10 = this.matrixPosition[3] * local_tx.matrixPosition[0] + this.matrixPosition[4] * local_tx.matrixPosition[3];
    float n11 = this.matrixPosition[3] * local_tx.matrixPosition[1] + this.matrixPosition[4] * local_tx.matrixPosition[4];
    float n12 = this.matrixPosition[3] * local_tx.matrixPosition[2] + this.matrixPosition[4] * local_tx.matrixPosition[5] + this.matrixPosition[5];
    local_mp[0] = n00;
    local_mp[1] = n01;
    local_mp[2] = n02;
    local_mp[3] = n10;
    local_mp[4] = n11;
    local_mp[5] = n12;
    this.matrixPosition = local_mp;
    return this;
  }
  
  public String toString()
  {
    String result = "Transform[[" + this.matrixPosition[0] + "," + this.matrixPosition[1] + "," + this.matrixPosition[2] + "][" + this.matrixPosition[3] + "," + this.matrixPosition[4] + "," + this.matrixPosition[5] + "][" + this.matrixPosition[6] + "," + this.matrixPosition[7] + "," + this.matrixPosition[8] + "]]";
    return result.toString();
  }
  
  public float[] getMatrixPosition()
  {
    return this.matrixPosition;
  }
  
  public static Transform createRotateTransform(float angle)
  {
    return new Transform((float)FastTrig.cos(angle), -(float)FastTrig.sin(angle), 0.0F, (float)FastTrig.sin(angle), (float)FastTrig.cos(angle), 0.0F);
  }
  
  public static Transform createRotateTransform(float angle, float local_x, float local_y)
  {
    Transform temp = createRotateTransform(angle);
    float sinAngle = temp.matrixPosition[3];
    float oneMinusCosAngle = 1.0F - temp.matrixPosition[4];
    temp.matrixPosition[2] = (local_x * oneMinusCosAngle + local_y * sinAngle);
    temp.matrixPosition[5] = (local_y * oneMinusCosAngle - local_x * sinAngle);
    return temp;
  }
  
  public static Transform createTranslateTransform(float xOffset, float yOffset)
  {
    return new Transform(1.0F, 0.0F, xOffset, 0.0F, 1.0F, yOffset);
  }
  
  public static Transform createScaleTransform(float xScale, float yScale)
  {
    return new Transform(xScale, 0.0F, 0.0F, 0.0F, yScale, 0.0F);
  }
  
  public Vector2f transform(Vector2f local_pt)
  {
    float[] local_in = { local_pt.field_1680, local_pt.field_1681 };
    float[] out = new float[2];
    transform(local_in, 0, out, 0, 1);
    return new Vector2f(out[0], out[1]);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Transform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */