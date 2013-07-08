package org.schema.game.common.data.physics;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.util.ArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;

public class CubesCompoundShape
  extends CompoundShape
{
  private SegmentController segmentController;
  private final ArrayList attached = new ArrayList();
  private static Transform ident;
  private Vector3f aabbMin = new Vector3f();
  private Vector3f aabbMax = new Vector3f();
  private Vector3f halfExtents = new Vector3f();
  private Vector3f localAabbMin = new Vector3f();
  private Vector3f localAabbMax = new Vector3f();
  private final Vector3f tmpLocalAabbMin = new Vector3f();
  private final Vector3f tmpLocalAabbMax = new Vector3f();
  Vector3f _localAabbMin = new Vector3f();
  Vector3f _localAabbMax = new Vector3f();
  Vector3f localCenter = new Vector3f();
  Vector3f localHalfExtents = new Vector3f();
  Matrix3f abs_b = new Matrix3f();
  Vector3f center = new Vector3f();
  Vector3f tmp = new Vector3f();
  Vector3f extent = new Vector3f();
  
  public CubesCompoundShape(SegmentController paramSegmentController)
  {
    this.segmentController = paramSegmentController;
  }
  
  public ArrayList getAttached()
  {
    return this.attached;
  }
  
  public SegmentController getSegmentController()
  {
    return this.segmentController;
  }
  
  public boolean isCompound()
  {
    return true;
  }
  
  public void calculateLocalInertia(float paramFloat, Vector3f paramVector3f)
  {
    getAabb(ident, this.aabbMin, this.aabbMax);
    this.halfExtents.sub(this.aabbMax, this.aabbMin);
    this.halfExtents.scale(0.5F);
    float f1 = 2.0F * this.halfExtents.field_615;
    float f2 = 2.0F * this.halfExtents.field_616;
    float f3 = 2.0F * this.halfExtents.field_617;
    paramVector3f.field_615 = (paramFloat / 12.0F * (f2 * f2 + f3 * f3));
    paramVector3f.field_616 = (paramFloat / 12.0F * (f1 * f1 + f3 * f3));
    paramVector3f.field_617 = (paramFloat / 12.0F * (f1 * f1 + f2 * f2));
  }
  
  public void recalculateLocalAabb()
  {
    this.localAabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
    this.localAabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
    for (int i = 0; i < getChildList().size(); i++)
    {
      ((CompoundShapeChild)getChildList().getQuick(i)).childShape.getAabb(((CompoundShapeChild)getChildList().getQuick(i)).transform, this.tmpLocalAabbMin, this.tmpLocalAabbMax);
      for (int j = 0; j < 3; j++)
      {
        if (VectorUtil.getCoord(this.localAabbMin, j) > VectorUtil.getCoord(this.tmpLocalAabbMin, j)) {
          VectorUtil.setCoord(this.localAabbMin, j, VectorUtil.getCoord(this.tmpLocalAabbMin, j));
        }
        if (VectorUtil.getCoord(this.localAabbMax, j) < VectorUtil.getCoord(this.tmpLocalAabbMax, j)) {
          VectorUtil.setCoord(this.localAabbMax, j, VectorUtil.getCoord(this.tmpLocalAabbMax, j));
        }
      }
    }
  }
  
  public void addChildShape(Transform paramTransform, CollisionShape paramCollisionShape)
  {
    CompoundShapeChild localCompoundShapeChild;
    (localCompoundShapeChild = new CompoundShapeChild()).transform.set(paramTransform);
    localCompoundShapeChild.childShape = paramCollisionShape;
    localCompoundShapeChild.childShapeType = paramCollisionShape.getShapeType();
    localCompoundShapeChild.childMargin = paramCollisionShape.getMargin();
    getChildList().add(localCompoundShapeChild);
    paramCollisionShape.getAabb(paramTransform, this._localAabbMin, this._localAabbMax);
    VectorUtil.setMin(this.localAabbMin, this._localAabbMin);
    VectorUtil.setMax(this.localAabbMax, this._localAabbMax);
  }
  
  public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
    this.localHalfExtents.scale(0.5F);
    this.localHalfExtents.field_615 += getMargin();
    this.localHalfExtents.field_616 += getMargin();
    this.localHalfExtents.field_617 += getMargin();
    this.localCenter.add(this.localAabbMax, this.localAabbMin);
    this.localCenter.scale(0.5F);
    this.abs_b.set(paramTransform.basis);
    MatrixUtil.absolute(this.abs_b);
    this.center.set(this.localCenter);
    paramTransform.transform(this.center);
    this.abs_b.getRow(0, this.tmp);
    this.extent.field_615 = this.tmp.dot(this.localHalfExtents);
    this.abs_b.getRow(1, this.tmp);
    this.extent.field_616 = this.tmp.dot(this.localHalfExtents);
    this.abs_b.getRow(2, this.tmp);
    this.extent.field_617 = this.tmp.dot(this.localHalfExtents);
    paramVector3f1.sub(this.center, this.extent);
    paramVector3f2.add(this.center, this.extent);
  }
  
  public String toString()
  {
    return "[CCS" + (this.segmentController.isOnServer() ? "|SER " : "|CLI ") + this.segmentController + "]";
  }
  
  static
  {
    (CubesCompoundShape.ident = new Transform()).setIdentity();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubesCompoundShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */