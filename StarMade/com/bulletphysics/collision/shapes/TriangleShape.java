package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class TriangleShape
  extends PolyhedralConvexShape
{
  public final Vector3f[] vertices1 = { new Vector3f(), new Vector3f(), new Vector3f() };
  
  public TriangleShape() {}
  
  public TriangleShape(Vector3f local_p0, Vector3f local_p1, Vector3f local_p2)
  {
    this.vertices1[0].set(local_p0);
    this.vertices1[1].set(local_p1);
    this.vertices1[2].set(local_p2);
  }
  
  public void init(Vector3f local_p0, Vector3f local_p1, Vector3f local_p2)
  {
    this.vertices1[0].set(local_p0);
    this.vertices1[1].set(local_p1);
    this.vertices1[2].set(local_p2);
  }
  
  public int getNumVertices()
  {
    return 3;
  }
  
  public Vector3f getVertexPtr(int index)
  {
    return this.vertices1[index];
  }
  
  public void getVertex(int index, Vector3f vert)
  {
    vert.set(this.vertices1[index]);
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.TRIANGLE_SHAPE_PROXYTYPE;
  }
  
  public int getNumEdges()
  {
    return 3;
  }
  
  public void getEdge(int local_i, Vector3f local_pa, Vector3f local_pb)
  {
    getVertex(local_i, local_pa);
    getVertex((local_i + 1) % 3, local_pb);
  }
  
  public void getAabb(Transform local_t, Vector3f aabbMin, Vector3f aabbMax)
  {
    getAabbSlow(local_t, aabbMin, aabbMax);
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f dots = localStack.get$javax$vecmath$Vector3f();
      dots.set(dir.dot(this.vertices1[0]), dir.dot(this.vertices1[1]), dir.dot(this.vertices1[2]));
      out.set(this.vertices1[com.bulletphysics.linearmath.VectorUtil.maxAxis(dots)]);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f dots = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < numVectors; local_i++)
      {
        Vector3f dir = vectors[local_i];
        dots.set(dir.dot(this.vertices1[0]), dir.dot(this.vertices1[1]), dir.dot(this.vertices1[2]));
        supportVerticesOut[local_i].set(this.vertices1[com.bulletphysics.linearmath.VectorUtil.maxAxis(dots)]);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int local_i)
  {
    getPlaneEquation(local_i, planeNormal, planeSupport);
  }
  
  public int getNumPlanes()
  {
    return 1;
  }
  
  public void calcNormal(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp1.sub(this.vertices1[1], this.vertices1[0]);
      tmp2.sub(this.vertices1[2], this.vertices1[0]);
      normal.cross(tmp1, tmp2);
      normal.normalize();
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getPlaneEquation(int local_i, Vector3f planeNormal, Vector3f planeSupport)
  {
    calcNormal(planeNormal);
    planeSupport.set(this.vertices1[0]);
  }
  
  public void calculateLocalInertia(float mass, Vector3f inertia)
  {
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    inertia.set(0.0F, 0.0F, 0.0F);
  }
  
  public boolean isInside(Vector3f arg1, float arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      calcNormal(normal);
      float dist = local_pt.dot(normal);
      float planeconst = this.vertices1[0].dot(normal);
      dist -= planeconst;
      if ((dist >= -tolerance) && (dist <= tolerance))
      {
        for (int local_i = 0; local_i < 3; local_i++)
        {
          Vector3f local_pa = localStack.get$javax$vecmath$Vector3f();
          Vector3f local_pb = localStack.get$javax$vecmath$Vector3f();
          getEdge(local_i, local_pa, local_pb);
          Vector3f edge = localStack.get$javax$vecmath$Vector3f();
          edge.sub(local_pb, local_pa);
          Vector3f edgeNormal = localStack.get$javax$vecmath$Vector3f();
          edgeNormal.cross(edge, normal);
          edgeNormal.normalize();
          dist = local_pt.dot(edgeNormal);
          float edgeConst = local_pa.dot(edgeNormal);
          dist -= edgeConst;
          if (dist < -tolerance) {
            return false;
          }
        }
        return true;
      }
      return false;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "Triangle";
  }
  
  public int getNumPreferredPenetrationDirections()
  {
    return 2;
  }
  
  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
  {
    calcNormal(penetrationVector);
    if (index != 0) {
      penetrationVector.scale(-1.0F);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */