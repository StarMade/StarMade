package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class CompoundShape
  extends CollisionShape
{
  private final ObjectArrayList<CompoundShapeChild> children = new ObjectArrayList();
  private final Vector3f localAabbMin = new Vector3f(1.0E+030F, 1.0E+030F, 1.0E+030F);
  private final Vector3f localAabbMax = new Vector3f(-1.0E+030F, -1.0E+030F, -1.0E+030F);
  private OptimizedBvh aabbTree = null;
  private float collisionMargin = 0.0F;
  protected final Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
  
  public void addChildShape(Transform arg1, CollisionShape arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      CompoundShapeChild child = new CompoundShapeChild();
      child.transform.set(localTransform);
      child.childShape = shape;
      child.childShapeType = shape.getShapeType();
      child.childMargin = shape.getMargin();
      this.children.add(child);
      Vector3f _localAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f _localAabbMax = localStack.get$javax$vecmath$Vector3f();
      shape.getAabb(localTransform, _localAabbMin, _localAabbMax);
      VectorUtil.setMin(this.localAabbMin, _localAabbMin);
      VectorUtil.setMax(this.localAabbMax, _localAabbMax);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void removeChildShape(CollisionShape shape)
  {
    boolean done_removing;
    do
    {
      done_removing = true;
      for (int local_i = 0; local_i < this.children.size(); local_i++) {
        if (((CompoundShapeChild)this.children.getQuick(local_i)).childShape == shape)
        {
          this.children.removeQuick(local_i);
          done_removing = false;
          break;
        }
      }
    } while (!done_removing);
    recalculateLocalAabb();
  }
  
  public int getNumChildShapes()
  {
    return this.children.size();
  }
  
  public CollisionShape getChildShape(int index)
  {
    return ((CompoundShapeChild)this.children.getQuick(index)).childShape;
  }
  
  public Transform getChildTransform(int index, Transform out)
  {
    out.set(((CompoundShapeChild)this.children.getQuick(index)).transform);
    return out;
  }
  
  public ObjectArrayList<CompoundShapeChild> getChildList()
  {
    return this.children;
  }
  
  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
      localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
      localHalfExtents.scale(0.5F);
      localHalfExtents.field_615 += getMargin();
      localHalfExtents.field_616 += getMargin();
      localHalfExtents.field_617 += getMargin();
      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
      localCenter.add(this.localAabbMax, this.localAabbMin);
      localCenter.scale(0.5F);
      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
      MatrixUtil.absolute(abs_b);
      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
      trans.transform(center);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
      abs_b.getRow(0, tmp);
      extent.field_615 = tmp.dot(localHalfExtents);
      abs_b.getRow(1, tmp);
      extent.field_616 = tmp.dot(localHalfExtents);
      abs_b.getRow(2, tmp);
      extent.field_617 = tmp.dot(localHalfExtents);
      aabbMin.sub(center, extent);
      aabbMax.add(center, extent);
      return;
    }
    finally
    {
      .Stack tmp245_243 = localStack;
      tmp245_243.pop$javax$vecmath$Vector3f();
      tmp245_243.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void recalculateLocalAabb()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.localAabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
      this.localAabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
      Vector3f tmpLocalAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpLocalAabbMax = localStack.get$javax$vecmath$Vector3f();
      for (int local_j = 0; local_j < this.children.size(); local_j++)
      {
        ((CompoundShapeChild)this.children.getQuick(local_j)).childShape.getAabb(((CompoundShapeChild)this.children.getQuick(local_j)).transform, tmpLocalAabbMin, tmpLocalAabbMax);
        for (int local_i = 0; local_i < 3; local_i++)
        {
          if (VectorUtil.getCoord(this.localAabbMin, local_i) > VectorUtil.getCoord(tmpLocalAabbMin, local_i)) {
            VectorUtil.setCoord(this.localAabbMin, local_i, VectorUtil.getCoord(tmpLocalAabbMin, local_i));
          }
          if (VectorUtil.getCoord(this.localAabbMax, local_i) < VectorUtil.getCoord(tmpLocalAabbMax, local_i)) {
            VectorUtil.setCoord(this.localAabbMax, local_i, VectorUtil.getCoord(tmpLocalAabbMax, local_i));
          }
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setLocalScaling(Vector3f scaling)
  {
    this.localScaling.set(scaling);
  }
  
  public Vector3f getLocalScaling(Vector3f out)
  {
    out.set(this.localScaling);
    return out;
  }
  
  public void calculateLocalInertia(float arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
      ident.setIdentity();
      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
      getAabb(ident, aabbMin, aabbMax);
      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
      halfExtents.sub(aabbMax, aabbMin);
      halfExtents.scale(0.5F);
      float local_lx = 2.0F * halfExtents.field_615;
      float local_ly = 2.0F * halfExtents.field_616;
      float local_lz = 2.0F * halfExtents.field_617;
      inertia.field_615 = (mass / 12.0F * (local_ly * local_ly + local_lz * local_lz));
      inertia.field_616 = (mass / 12.0F * (local_lx * local_lx + local_lz * local_lz));
      inertia.field_617 = (mass / 12.0F * (local_lx * local_lx + local_ly * local_ly));
      return;
    }
    finally
    {
      .Stack tmp169_167 = localStack;
      tmp169_167.pop$com$bulletphysics$linearmath$Transform();
      tmp169_167.pop$javax$vecmath$Vector3f();
    }
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.COMPOUND_SHAPE_PROXYTYPE;
  }
  
  public void setMargin(float margin)
  {
    this.collisionMargin = margin;
  }
  
  public float getMargin()
  {
    return this.collisionMargin;
  }
  
  public String getName()
  {
    return "Compound";
  }
  
  public OptimizedBvh getAabbTree()
  {
    return this.aabbTree;
  }
  
  public void calculatePrincipalAxisTransform(float[] arg1, Transform arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      int local_n = this.children.size();
      float totalMass = 0.0F;
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      center.set(0.0F, 0.0F, 0.0F);
      for (int local_k = 0; local_k < local_n; local_k++)
      {
        center.scaleAdd(masses[local_k], ((CompoundShapeChild)this.children.getQuick(local_k)).transform.origin, center);
        totalMass += masses[local_k];
      }
      center.scale(1.0F / totalMass);
      principal.origin.set(center);
      Matrix3f local_k = localStack.get$javax$vecmath$Matrix3f();
      local_k.setZero();
      for (int local_k1 = 0; local_k1 < local_n; local_k1++)
      {
        Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
        ((CompoundShapeChild)this.children.getQuick(local_k1)).childShape.calculateLocalInertia(masses[local_k1], local_i);
        Transform local_t = ((CompoundShapeChild)this.children.getQuick(local_k1)).transform;
        Vector3f local_o = localStack.get$javax$vecmath$Vector3f();
        local_o.sub(local_t.origin, center);
        Matrix3f local_j = localStack.get$javax$vecmath$Matrix3f();
        local_j.transpose(local_t.basis);
        local_j.m00 *= local_i.field_615;
        local_j.m01 *= local_i.field_615;
        local_j.m02 *= local_i.field_615;
        local_j.m10 *= local_i.field_616;
        local_j.m11 *= local_i.field_616;
        local_j.m12 *= local_i.field_616;
        local_j.m20 *= local_i.field_617;
        local_j.m21 *= local_i.field_617;
        local_j.m22 *= local_i.field_617;
        local_j.mul(local_t.basis, local_j);
        local_k.add(local_j);
        float local_o2 = local_o.lengthSquared();
        local_j.setRow(0, local_o2, 0.0F, 0.0F);
        local_j.setRow(1, 0.0F, local_o2, 0.0F);
        local_j.setRow(2, 0.0F, 0.0F, local_o2);
        local_j.m00 += local_o.field_615 * -local_o.field_615;
        local_j.m01 += local_o.field_616 * -local_o.field_615;
        local_j.m02 += local_o.field_617 * -local_o.field_615;
        local_j.m10 += local_o.field_615 * -local_o.field_616;
        local_j.m11 += local_o.field_616 * -local_o.field_616;
        local_j.m12 += local_o.field_617 * -local_o.field_616;
        local_j.m20 += local_o.field_615 * -local_o.field_617;
        local_j.m21 += local_o.field_616 * -local_o.field_617;
        local_j.m22 += local_o.field_617 * -local_o.field_617;
        local_k.m00 += masses[local_k1] * local_j.m00;
        local_k.m01 += masses[local_k1] * local_j.m01;
        local_k.m02 += masses[local_k1] * local_j.m02;
        local_k.m10 += masses[local_k1] * local_j.m10;
        local_k.m11 += masses[local_k1] * local_j.m11;
        local_k.m12 += masses[local_k1] * local_j.m12;
        local_k.m20 += masses[local_k1] * local_j.m20;
        local_k.m21 += masses[local_k1] * local_j.m21;
        local_k.m22 += masses[local_k1] * local_j.m22;
      }
      MatrixUtil.diagonalize(local_k, principal.basis, 1.0E-005F, 20);
      inertia.set(local_k.m00, local_k.m11, local_k.m22);
      return;
    }
    finally
    {
      .Stack tmp839_837 = localStack;
      tmp839_837.pop$javax$vecmath$Vector3f();
      tmp839_837.pop$javax$vecmath$Matrix3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CompoundShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */