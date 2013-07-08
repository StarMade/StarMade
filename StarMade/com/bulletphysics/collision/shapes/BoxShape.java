package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.ScalarUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class BoxShape
  extends PolyhedralConvexShape
{
  public BoxShape(Vector3f boxHalfExtents)
  {
    Vector3f margin = new Vector3f(getMargin(), getMargin(), getMargin());
    VectorUtil.mul(this.implicitShapeDimensions, boxHalfExtents, this.localScaling);
    this.implicitShapeDimensions.sub(margin);
  }
  
  public Vector3f getHalfExtentsWithMargin(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
      Vector3f margin = localStack.get$javax$vecmath$Vector3f();
      margin.set(getMargin(), getMargin(), getMargin());
      halfExtents.add(margin);
      return out;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public Vector3f getHalfExtentsWithoutMargin(Vector3f out)
  {
    out.set(this.implicitShapeDimensions);
    return out;
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.BOX_SHAPE_PROXYTYPE;
  }
  
  public Vector3f localGetSupportingVertex(Vector3f vec, Vector3f out)
  {
    Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
    float margin = getMargin();
    halfExtents.field_615 += margin;
    halfExtents.field_616 += margin;
    halfExtents.field_617 += margin;
    out.set(ScalarUtil.fsel(vec.field_615, halfExtents.field_615, -halfExtents.field_615), ScalarUtil.fsel(vec.field_616, halfExtents.field_616, -halfExtents.field_616), ScalarUtil.fsel(vec.field_617, halfExtents.field_617, -halfExtents.field_617));
    return out;
  }
  
  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
  {
    Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
    out.set(ScalarUtil.fsel(vec.field_615, halfExtents.field_615, -halfExtents.field_615), ScalarUtil.fsel(vec.field_616, halfExtents.field_616, -halfExtents.field_616), ScalarUtil.fsel(vec.field_617, halfExtents.field_617, -halfExtents.field_617));
    return out;
  }
  
  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
      for (int local_i = 0; local_i < numVectors; local_i++)
      {
        Vector3f vec = vectors[local_i];
        supportVerticesOut[local_i].set(ScalarUtil.fsel(vec.field_615, halfExtents.field_615, -halfExtents.field_615), ScalarUtil.fsel(vec.field_616, halfExtents.field_616, -halfExtents.field_616), ScalarUtil.fsel(vec.field_617, halfExtents.field_617, -halfExtents.field_617));
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setMargin(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f oldMargin = localStack.get$javax$vecmath$Vector3f();
      oldMargin.set(getMargin(), getMargin(), getMargin());
      Vector3f implicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
      implicitShapeDimensionsWithMargin.add(this.implicitShapeDimensions, oldMargin);
      super.setMargin(margin);
      Vector3f newMargin = localStack.get$javax$vecmath$Vector3f();
      newMargin.set(getMargin(), getMargin(), getMargin());
      this.implicitShapeDimensions.sub(implicitShapeDimensionsWithMargin, newMargin);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setLocalScaling(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f oldMargin = localStack.get$javax$vecmath$Vector3f();
      oldMargin.set(getMargin(), getMargin(), getMargin());
      Vector3f implicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
      implicitShapeDimensionsWithMargin.add(this.implicitShapeDimensions, oldMargin);
      Vector3f unScaledImplicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.div(unScaledImplicitShapeDimensionsWithMargin, implicitShapeDimensionsWithMargin, this.localScaling);
      super.setLocalScaling(scaling);
      VectorUtil.mul(this.implicitShapeDimensions, unScaledImplicitShapeDimensionsWithMargin, this.localScaling);
      this.implicitShapeDimensions.sub(oldMargin);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      AabbUtil2.transformAabb(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), getMargin(), local_t, aabbMin, aabbMax);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f());
      float local_lx = 2.0F * halfExtents.field_615;
      float local_ly = 2.0F * halfExtents.field_616;
      float local_lz = 2.0F * halfExtents.field_617;
      inertia.set(mass / 12.0F * (local_ly * local_ly + local_lz * local_lz), mass / 12.0F * (local_lx * local_lx + local_lz * local_lz), mass / 12.0F * (local_lx * local_lx + local_ly * local_ly));
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getPlane(Vector3f arg1, Vector3f arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Vector4f();
      Vector4f plane = localStack.get$javax$vecmath$Vector4f();
      getPlaneEquation(plane, local_i);
      planeNormal.set(plane.field_596, plane.field_597, plane.field_598);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.negate(planeNormal);
      localGetSupportingVertex(tmp, planeSupport);
      return;
    }
    finally
    {
      .Stack tmp80_78 = localStack;
      tmp80_78.pop$javax$vecmath$Vector3f();
      tmp80_78.pop$javax$vecmath$Vector4f();
    }
  }
  
  public int getNumPlanes()
  {
    return 6;
  }
  
  public int getNumVertices()
  {
    return 8;
  }
  
  public int getNumEdges()
  {
    return 12;
  }
  
  public void getVertex(int arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
      vtx.set(halfExtents.field_615 * (1 - (local_i & 0x1)) - halfExtents.field_615 * (local_i & 0x1), halfExtents.field_616 * (1 - ((local_i & 0x2) >> 1)) - halfExtents.field_616 * ((local_i & 0x2) >> 1), halfExtents.field_617 * (1 - ((local_i & 0x4) >> 2)) - halfExtents.field_617 * ((local_i & 0x4) >> 2));
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getPlaneEquation(Vector4f arg1, int arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
      switch (local_i)
      {
      case 0: 
        plane.set(1.0F, 0.0F, 0.0F, -halfExtents.field_615);
        break;
      case 1: 
        plane.set(-1.0F, 0.0F, 0.0F, -halfExtents.field_615);
        break;
      case 2: 
        plane.set(0.0F, 1.0F, 0.0F, -halfExtents.field_616);
        break;
      case 3: 
        plane.set(0.0F, -1.0F, 0.0F, -halfExtents.field_616);
        break;
      case 4: 
        plane.set(0.0F, 0.0F, 1.0F, -halfExtents.field_617);
        break;
      case 5: 
        plane.set(0.0F, 0.0F, -1.0F, -halfExtents.field_617);
        break;
      default: 
        if (!$assertionsDisabled) {
          throw new AssertionError();
        }
        break;
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void getEdge(int local_i, Vector3f local_pa, Vector3f local_pb)
  {
    int edgeVert0 = 0;
    int edgeVert1 = 0;
    switch (local_i)
    {
    case 0: 
      edgeVert0 = 0;
      edgeVert1 = 1;
      break;
    case 1: 
      edgeVert0 = 0;
      edgeVert1 = 2;
      break;
    case 2: 
      edgeVert0 = 1;
      edgeVert1 = 3;
      break;
    case 3: 
      edgeVert0 = 2;
      edgeVert1 = 3;
      break;
    case 4: 
      edgeVert0 = 0;
      edgeVert1 = 4;
      break;
    case 5: 
      edgeVert0 = 1;
      edgeVert1 = 5;
      break;
    case 6: 
      edgeVert0 = 2;
      edgeVert1 = 6;
      break;
    case 7: 
      edgeVert0 = 3;
      edgeVert1 = 7;
      break;
    case 8: 
      edgeVert0 = 4;
      edgeVert1 = 5;
      break;
    case 9: 
      edgeVert0 = 4;
      edgeVert1 = 6;
      break;
    case 10: 
      edgeVert0 = 5;
      edgeVert1 = 7;
      break;
    case 11: 
      edgeVert0 = 6;
      edgeVert1 = 7;
      break;
    default: 
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      break;
    }
    getVertex(edgeVert0, local_pa);
    getVertex(edgeVert1, local_pb);
  }
  
  public boolean isInside(Vector3f arg1, float arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
      boolean result = (local_pt.field_615 <= halfExtents.field_615 + tolerance) && (local_pt.field_615 >= -halfExtents.field_615 - tolerance) && (local_pt.field_616 <= halfExtents.field_616 + tolerance) && (local_pt.field_616 >= -halfExtents.field_616 - tolerance) && (local_pt.field_617 <= halfExtents.field_617 + tolerance) && (local_pt.field_617 >= -halfExtents.field_617 - tolerance);
      return result;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public String getName()
  {
    return "Box";
  }
  
  public int getNumPreferredPenetrationDirections()
  {
    return 6;
  }
  
  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
  {
    switch (index)
    {
    case 0: 
      penetrationVector.set(1.0F, 0.0F, 0.0F);
      break;
    case 1: 
      penetrationVector.set(-1.0F, 0.0F, 0.0F);
      break;
    case 2: 
      penetrationVector.set(0.0F, 1.0F, 0.0F);
      break;
    case 3: 
      penetrationVector.set(0.0F, -1.0F, 0.0F);
      break;
    case 4: 
      penetrationVector.set(0.0F, 0.0F, 1.0F);
      break;
    case 5: 
      penetrationVector.set(0.0F, 0.0F, -1.0F);
      break;
    default: 
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      break;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.BoxShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */