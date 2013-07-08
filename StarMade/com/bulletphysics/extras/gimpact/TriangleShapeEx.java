package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.TriangleShape;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class TriangleShapeEx
  extends TriangleShape
{
  public TriangleShapeEx() {}
  
  public TriangleShapeEx(Vector3f local_p0, Vector3f local_p1, Vector3f local_p2)
  {
    super(local_p0, local_p1, local_p2);
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tv0 = localStack.get$javax$vecmath$Vector3f(this.vertices1[0]);
      local_t.transform(tv0);
      Vector3f tv1 = localStack.get$javax$vecmath$Vector3f(this.vertices1[1]);
      local_t.transform(tv1);
      Vector3f tv2 = localStack.get$javax$vecmath$Vector3f(this.vertices1[2]);
      local_t.transform(tv2);
      BoxCollision.AABB trianglebox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      trianglebox.init(tv0, tv1, tv2, this.collisionMargin);
      aabbMin.set(trianglebox.min);
      aabbMax.set(trianglebox.max);
      return;
    }
    finally
    {
      .Stack tmp126_124 = localStack;
      tmp126_124.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      tmp126_124.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void applyTransform(Transform local_t)
  {
    local_t.transform(this.vertices1[0]);
    local_t.transform(this.vertices1[1]);
    local_t.transform(this.vertices1[2]);
  }
  
  public void buildTriPlane(Vector4f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      tmp1.sub(this.vertices1[1], this.vertices1[0]);
      tmp2.sub(this.vertices1[2], this.vertices1[0]);
      normal.cross(tmp1, tmp2);
      normal.normalize();
      plane.set(normal.field_615, normal.field_616, normal.field_617, this.vertices1[0].dot(normal));
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean overlap_test_conservative(TriangleShapeEx arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector4f();
      float total_margin = getMargin() + other.getMargin();
      Vector4f plane0 = localStack.get$javax$vecmath$Vector4f();
      buildTriPlane(plane0);
      Vector4f plane1 = localStack.get$javax$vecmath$Vector4f();
      other.buildTriPlane(plane1);
      float dis0 = ClipPolygon.distance_point_plane(plane0, other.vertices1[0]) - total_margin;
      float dis1 = ClipPolygon.distance_point_plane(plane0, other.vertices1[1]) - total_margin;
      float dis2 = ClipPolygon.distance_point_plane(plane0, other.vertices1[2]) - total_margin;
      if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
        return false;
      }
      dis0 = ClipPolygon.distance_point_plane(plane1, this.vertices1[0]) - total_margin;
      dis1 = ClipPolygon.distance_point_plane(plane1, this.vertices1[1]) - total_margin;
      dis2 = ClipPolygon.distance_point_plane(plane1, this.vertices1[2]) - total_margin;
      return (dis0 <= 0.0F) || (dis1 <= 0.0F) || (dis2 <= 0.0F);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector4f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.TriangleShapeEx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */