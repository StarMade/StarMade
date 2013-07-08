package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class PrimitiveTriangle
{
  private final ObjectArrayList<Vector3f> tmpVecList1 = new ObjectArrayList(16);
  private final ObjectArrayList<Vector3f> tmpVecList2 = new ObjectArrayList(16);
  private final ObjectArrayList<Vector3f> tmpVecList3 = new ObjectArrayList(16);
  public final Vector3f[] vertices;
  public final Vector4f plane;
  public float margin;
  
  public PrimitiveTriangle()
  {
    for (int local_i = 0; local_i < 16; local_i++)
    {
      this.tmpVecList1.add(new Vector3f());
      this.tmpVecList2.add(new Vector3f());
      this.tmpVecList3.add(new Vector3f());
    }
    this.vertices = new Vector3f[3];
    this.plane = new Vector4f();
    this.margin = 0.01F;
    for (int local_i = 0; local_i < this.vertices.length; local_i++) {
      this.vertices[local_i] = new Vector3f();
    }
  }
  
  public void set(PrimitiveTriangle tri)
  {
    throw new UnsupportedOperationException();
  }
  
  public void buildTriPlane()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      tmp1.sub(this.vertices[1], this.vertices[0]);
      tmp2.sub(this.vertices[2], this.vertices[0]);
      normal.cross(tmp1, tmp2);
      normal.normalize();
      this.plane.set(normal.field_615, normal.field_616, normal.field_617, this.vertices[0].dot(normal));
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean overlap_test_conservative(PrimitiveTriangle other)
  {
    float total_margin = this.margin + other.margin;
    float dis0 = ClipPolygon.distance_point_plane(this.plane, other.vertices[0]) - total_margin;
    float dis1 = ClipPolygon.distance_point_plane(this.plane, other.vertices[1]) - total_margin;
    float dis2 = ClipPolygon.distance_point_plane(this.plane, other.vertices[2]) - total_margin;
    if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
      return false;
    }
    dis0 = ClipPolygon.distance_point_plane(other.plane, this.vertices[0]) - total_margin;
    dis1 = ClipPolygon.distance_point_plane(other.plane, this.vertices[1]) - total_margin;
    dis2 = ClipPolygon.distance_point_plane(other.plane, this.vertices[2]) - total_margin;
    return (dis0 <= 0.0F) || (dis1 <= 0.0F) || (dis2 <= 0.0F);
  }
  
  public void get_edge_plane(int arg1, Vector4f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f local_e0 = this.vertices[edge_index];
      Vector3f local_e1 = this.vertices[((edge_index + 1) % 3)];
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.set(this.plane.field_596, this.plane.field_597, this.plane.field_598);
      GeometryOperations.edge_plane(local_e0, local_e1, tmp, plane);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void applyTransform(Transform local_t)
  {
    local_t.transform(this.vertices[0]);
    local_t.transform(this.vertices[1]);
    local_t.transform(this.vertices[2]);
  }
  
  public int clip_triangle(PrimitiveTriangle arg1, ObjectArrayList<Vector3f> arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector4f();
      ObjectArrayList<Vector3f> temp_points = this.tmpVecList1;
      Vector4f edgeplane = localStack.get$javax$vecmath$Vector4f();
      get_edge_plane(0, edgeplane);
      int clipped_count = ClipPolygon.plane_clip_triangle(edgeplane, other.vertices[0], other.vertices[1], other.vertices[2], temp_points);
      if (clipped_count == 0) {
        return 0;
      }
      ObjectArrayList<Vector3f> temp_points1 = this.tmpVecList2;
      get_edge_plane(1, edgeplane);
      clipped_count = ClipPolygon.plane_clip_polygon(edgeplane, temp_points, clipped_count, temp_points1);
      if (clipped_count == 0) {
        return 0;
      }
      get_edge_plane(2, edgeplane);
      clipped_count = ClipPolygon.plane_clip_polygon(edgeplane, temp_points1, clipped_count, clipped_points);
      return clipped_count;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector4f();
    }
  }
  
  public boolean find_triangle_collision_clip_method(PrimitiveTriangle arg1, TriangleContact arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$TriangleContact();
      float margin = this.margin + other.margin;
      ObjectArrayList<Vector3f> clipped_points = this.tmpVecList3;
      TriangleContact contacts1 = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
      contacts1.separating_normal.set(this.plane);
      int clipped_count = clip_triangle(other, clipped_points);
      if (clipped_count == 0) {
        return false;
      }
      contacts1.merge_points(contacts1.separating_normal, margin, clipped_points, clipped_count);
      if (contacts1.point_count == 0) {
        return false;
      }
      contacts1.separating_normal.field_596 *= -1.0F;
      contacts1.separating_normal.field_597 *= -1.0F;
      contacts1.separating_normal.field_598 *= -1.0F;
      TriangleContact contacts2 = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
      contacts2.separating_normal.set(other.plane);
      clipped_count = other.clip_triangle(this, clipped_points);
      if (clipped_count == 0) {
        return false;
      }
      contacts2.merge_points(contacts2.separating_normal, margin, clipped_points, clipped_count);
      if (contacts2.point_count == 0) {
        return false;
      }
      if (contacts2.penetration_depth < contacts1.penetration_depth) {
        contacts.copy_from(contacts2);
      } else {
        contacts.copy_from(contacts1);
      }
      return true;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$TriangleContact();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.PrimitiveTriangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */