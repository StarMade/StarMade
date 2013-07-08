package com.bulletphysics.extras.gimpact;

import com.bulletphysics.util.ArrayPool;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class TriangleContact
{
  private final ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
  public static final int MAX_TRI_CLIPPING = 16;
  public float penetration_depth;
  public int point_count;
  public final Vector4f separating_normal = new Vector4f();
  public Vector3f[] points = new Vector3f[16];
  
  public TriangleContact()
  {
    for (int local_i = 0; local_i < this.points.length; local_i++) {
      this.points[local_i] = new Vector3f();
    }
  }
  
  public TriangleContact(TriangleContact other)
  {
    copy_from(other);
  }
  
  public void set(TriangleContact other)
  {
    copy_from(other);
  }
  
  public void copy_from(TriangleContact other)
  {
    this.penetration_depth = other.penetration_depth;
    this.separating_normal.set(other.separating_normal);
    this.point_count = other.point_count;
    int local_i = this.point_count;
    while (local_i-- != 0) {
      this.points[local_i].set(other.points[local_i]);
    }
  }
  
  public void merge_points(Vector4f plane, float margin, ObjectArrayList<Vector3f> points, int point_count)
  {
    this.point_count = 0;
    this.penetration_depth = -1000.0F;
    int[] point_indices = (int[])this.intArrays.getFixed(16);
    for (int local__k = 0; local__k < point_count; local__k++)
    {
      float _dist = -ClipPolygon.distance_point_plane(plane, (Vector3f)points.getQuick(local__k)) + margin;
      if (_dist >= 0.0F) {
        if (_dist > this.penetration_depth)
        {
          this.penetration_depth = _dist;
          point_indices[0] = local__k;
          this.point_count = 1;
        }
        else if (_dist + 1.192093E-007F >= this.penetration_depth)
        {
          point_indices[this.point_count] = local__k;
          this.point_count += 1;
        }
      }
    }
    for (int local__k = 0; local__k < this.point_count; local__k++) {
      this.points[local__k].set((Tuple3f)points.getQuick(point_indices[local__k]));
    }
    this.intArrays.release(point_indices);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.TriangleContact
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */