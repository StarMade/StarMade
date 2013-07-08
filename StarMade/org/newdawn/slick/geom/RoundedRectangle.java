package org.newdawn.slick.geom;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.util.FastTrig;

public class RoundedRectangle
  extends Rectangle
{
  public static final int TOP_LEFT = 1;
  public static final int TOP_RIGHT = 2;
  public static final int BOTTOM_RIGHT = 4;
  public static final int BOTTOM_LEFT = 8;
  public static final int ALL = 15;
  private static final int DEFAULT_SEGMENT_COUNT = 25;
  private float cornerRadius;
  private int segmentCount;
  private int cornerFlags;
  
  public RoundedRectangle(float local_x, float local_y, float width, float height, float cornerRadius)
  {
    this(local_x, local_y, width, height, cornerRadius, 25);
  }
  
  public RoundedRectangle(float local_x, float local_y, float width, float height, float cornerRadius, int segmentCount)
  {
    this(local_x, local_y, width, height, cornerRadius, segmentCount, 15);
  }
  
  public RoundedRectangle(float local_x, float local_y, float width, float height, float cornerRadius, int segmentCount, int cornerFlags)
  {
    super(local_x, local_y, width, height);
    if (cornerRadius < 0.0F) {
      throw new IllegalArgumentException("corner radius must be >= 0");
    }
    this.field_1743 = local_x;
    this.field_1744 = local_y;
    this.width = width;
    this.height = height;
    this.cornerRadius = cornerRadius;
    this.segmentCount = segmentCount;
    this.pointsDirty = true;
    this.cornerFlags = cornerFlags;
  }
  
  public float getCornerRadius()
  {
    return this.cornerRadius;
  }
  
  public void setCornerRadius(float cornerRadius)
  {
    if ((cornerRadius >= 0.0F) && (cornerRadius != this.cornerRadius))
    {
      this.cornerRadius = cornerRadius;
      this.pointsDirty = true;
    }
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  public void setHeight(float height)
  {
    if (this.height != height)
    {
      this.height = height;
      this.pointsDirty = true;
    }
  }
  
  public float getWidth()
  {
    return this.width;
  }
  
  public void setWidth(float width)
  {
    if (width != this.width)
    {
      this.width = width;
      this.pointsDirty = true;
    }
  }
  
  protected void createPoints()
  {
    this.maxX = (this.field_1743 + this.width);
    this.maxY = (this.field_1744 + this.height);
    this.minX = this.field_1743;
    this.minY = this.field_1744;
    float useWidth = this.width - 1.0F;
    float useHeight = this.height - 1.0F;
    if (this.cornerRadius == 0.0F)
    {
      this.points = new float[8];
      this.points[0] = this.field_1743;
      this.points[1] = this.field_1744;
      this.points[2] = (this.field_1743 + useWidth);
      this.points[3] = this.field_1744;
      this.points[4] = (this.field_1743 + useWidth);
      this.points[5] = (this.field_1744 + useHeight);
      this.points[6] = this.field_1743;
      this.points[7] = (this.field_1744 + useHeight);
    }
    else
    {
      float doubleRadius = this.cornerRadius * 2.0F;
      if (doubleRadius > useWidth)
      {
        doubleRadius = useWidth;
        this.cornerRadius = (doubleRadius / 2.0F);
      }
      if (doubleRadius > useHeight)
      {
        doubleRadius = useHeight;
        this.cornerRadius = (doubleRadius / 2.0F);
      }
      ArrayList tempPoints = new ArrayList();
      if ((this.cornerFlags & 0x1) != 0)
      {
        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.field_1743 + this.cornerRadius, this.field_1744 + this.cornerRadius, 180.0F, 270.0F));
      }
      else
      {
        tempPoints.add(new Float(this.field_1743));
        tempPoints.add(new Float(this.field_1744));
      }
      if ((this.cornerFlags & 0x2) != 0)
      {
        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.field_1743 + useWidth - this.cornerRadius, this.field_1744 + this.cornerRadius, 270.0F, 360.0F));
      }
      else
      {
        tempPoints.add(new Float(this.field_1743 + useWidth));
        tempPoints.add(new Float(this.field_1744));
      }
      if ((this.cornerFlags & 0x4) != 0)
      {
        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.field_1743 + useWidth - this.cornerRadius, this.field_1744 + useHeight - this.cornerRadius, 0.0F, 90.0F));
      }
      else
      {
        tempPoints.add(new Float(this.field_1743 + useWidth));
        tempPoints.add(new Float(this.field_1744 + useHeight));
      }
      if ((this.cornerFlags & 0x8) != 0)
      {
        tempPoints.addAll(createPoints(this.segmentCount, this.cornerRadius, this.field_1743 + this.cornerRadius, this.field_1744 + useHeight - this.cornerRadius, 90.0F, 180.0F));
      }
      else
      {
        tempPoints.add(new Float(this.field_1743));
        tempPoints.add(new Float(this.field_1744 + useHeight));
      }
      this.points = new float[tempPoints.size()];
      for (int local_i = 0; local_i < tempPoints.size(); local_i++) {
        this.points[local_i] = ((Float)tempPoints.get(local_i)).floatValue();
      }
    }
    findCenter();
    calculateRadius();
  }
  
  private List createPoints(int numberOfSegments, float radius, float local_cx, float local_cy, float start, float end)
  {
    ArrayList tempPoints = new ArrayList();
    int step = 360 / numberOfSegments;
    for (float local_a = start; local_a <= end + step; local_a += step)
    {
      float ang = local_a;
      if (ang > end) {
        ang = end;
      }
      float local_x = (float)(local_cx + FastTrig.cos(Math.toRadians(ang)) * radius);
      float local_y = (float)(local_cy + FastTrig.sin(Math.toRadians(ang)) * radius);
      tempPoints.add(new Float(local_x));
      tempPoints.add(new Float(local_y));
    }
    return tempPoints;
  }
  
  public Shape transform(Transform transform)
  {
    checkPoints();
    Polygon resultPolygon = new Polygon();
    float[] result = new float[this.points.length];
    transform.transform(this.points, 0, result, 0, this.points.length / 2);
    resultPolygon.points = result;
    resultPolygon.findCenter();
    return resultPolygon;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.RoundedRectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */