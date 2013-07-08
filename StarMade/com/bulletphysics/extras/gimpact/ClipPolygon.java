package com.bulletphysics.extras.gimpact;

import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ArrayPool;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

class ClipPolygon
{
  public static float distance_point_plane(Vector4f plane, Vector3f point)
  {
    return VectorUtil.dot3(point, plane) - plane.field_599;
  }
  
  public static void vec_blend(Vector3f local_vr, Vector3f local_va, Vector3f local_vb, float blend_factor)
  {
    local_vr.scale(1.0F - blend_factor, local_va);
    local_vr.scaleAdd(blend_factor, local_vb, local_vr);
  }
  
  public static void plane_clip_polygon_collect(Vector3f point0, Vector3f point1, float dist0, float dist1, ObjectArrayList<Vector3f> clipped, int[] clipped_count)
  {
    boolean _prevclassif = dist0 > 1.192093E-007F;
    boolean _classif = dist1 > 1.192093E-007F;
    if (_classif != _prevclassif)
    {
      float blendfactor = -dist0 / (dist1 - dist0);
      vec_blend((Vector3f)clipped.getQuick(clipped_count[0]), point0, point1, blendfactor);
      clipped_count[0] += 1;
    }
    if (!_classif)
    {
      ((Vector3f)clipped.getQuick(clipped_count[0])).set(point1);
      clipped_count[0] += 1;
    }
  }
  
  public static int plane_clip_polygon(Vector4f plane, ObjectArrayList<Vector3f> polygon_points, int polygon_point_count, ObjectArrayList<Vector3f> clipped)
  {
    ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
    int[] clipped_count = (int[])intArrays.getFixed(1);
    clipped_count[0] = 0;
    float firstdist = distance_point_plane(plane, (Vector3f)polygon_points.getQuick(0));
    if (firstdist <= 1.192093E-007F)
    {
      ((Vector3f)clipped.getQuick(clipped_count[0])).set((Tuple3f)polygon_points.getQuick(0));
      clipped_count[0] += 1;
    }
    float olddist = firstdist;
    for (int local_i = 1; local_i < polygon_point_count; local_i++)
    {
      float dist = distance_point_plane(plane, (Vector3f)polygon_points.getQuick(local_i));
      plane_clip_polygon_collect((Vector3f)polygon_points.getQuick(local_i - 1), (Vector3f)polygon_points.getQuick(local_i), olddist, dist, clipped, clipped_count);
      olddist = dist;
    }
    plane_clip_polygon_collect((Vector3f)polygon_points.getQuick(polygon_point_count - 1), (Vector3f)polygon_points.getQuick(0), olddist, firstdist, clipped, clipped_count);
    int local_i = clipped_count[0];
    intArrays.release(clipped_count);
    return local_i;
  }
  
  public static int plane_clip_triangle(Vector4f plane, Vector3f point0, Vector3f point1, Vector3f point2, ObjectArrayList<Vector3f> clipped)
  {
    ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
    int[] clipped_count = (int[])intArrays.getFixed(1);
    clipped_count[0] = 0;
    float firstdist = distance_point_plane(plane, point0);
    if (firstdist <= 1.192093E-007F)
    {
      ((Vector3f)clipped.getQuick(clipped_count[0])).set(point0);
      clipped_count[0] += 1;
    }
    float olddist = firstdist;
    float dist = distance_point_plane(plane, point1);
    plane_clip_polygon_collect(point0, point1, olddist, dist, clipped, clipped_count);
    olddist = dist;
    dist = distance_point_plane(plane, point2);
    plane_clip_polygon_collect(point1, point2, olddist, dist, clipped, clipped_count);
    olddist = dist;
    plane_clip_polygon_collect(point2, point0, olddist, firstdist, clipped, clipped_count);
    int ret = clipped_count[0];
    intArrays.release(clipped_count);
    return ret;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.ClipPolygon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */