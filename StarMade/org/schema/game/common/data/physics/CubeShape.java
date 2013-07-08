package org.schema.game.common.data.physics;

import class_35;
import class_48;
import class_886;
import class_988;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.network.StateInterface;

public class CubeShape
  extends CollisionShape
{
  private float margin = 0.15F;
  private class_886 segmentBuffer;
  private Vector3f min = new Vector3f();
  private Vector3f max = new Vector3f();
  private Vector3f minCached = new Vector3f();
  private Vector3f maxCached = new Vector3f();
  private Transform cachedTransform = new Transform();
  private short cacheDate = -1;
  private float cachedMargin;
  private static Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
  
  public CubeShape(class_886 paramclass_886)
  {
    this.segmentBuffer = paramclass_886;
    this.cachedTransform.setIdentity();
  }
  
  public void calculateLocalInertia(float paramFloat, Vector3f paramVector3f)
  {
    float f1 = this.segmentBuffer.a6().field_1274.field_615 - this.segmentBuffer.a6().field_1273.field_615 + this.margin;
    float f2 = this.segmentBuffer.a6().field_1274.field_616 - this.segmentBuffer.a6().field_1273.field_616 + this.margin;
    float f3 = this.segmentBuffer.a6().field_1274.field_617 - this.segmentBuffer.a6().field_1273.field_617 + this.margin;
    paramVector3f.set(paramFloat / 3.0F * (f2 * f2 + f3 * f3), paramFloat / 3.0F * (f1 * f1 + f3 * f3), paramFloat / 3.0F * (f1 * f1 + f2 * f2));
  }
  
  public void getAabb(Transform paramTransform, float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    if ((paramFloat == this.cachedMargin) && (this.segmentBuffer.a12().getState().getUpdateNumber() == this.cacheDate) && (paramTransform.equals(this.cachedTransform)))
    {
      paramVector3f1.set(this.minCached);
      paramVector3f2.set(this.maxCached);
      return;
    }
    getAabbUncached(paramTransform, paramFloat, paramVector3f1, paramVector3f2);
  }
  
  public void getAabbUncached(Transform paramTransform, float paramFloat, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    if (!this.segmentBuffer.a6().a6())
    {
      this.min.set(0.0F, 0.0F, 0.0F);
      this.max.set(0.0F, 0.0F, 0.0F);
    }
    else
    {
      this.min.set(this.segmentBuffer.a6().field_1273);
      this.max.set(this.segmentBuffer.a6().field_1274);
    }
    if ((this.min.field_615 > this.max.field_615) || (this.min.field_616 > this.max.field_616) || (this.min.field_617 > this.max.field_617))
    {
      System.err.println("[EXCEPTION] WARNING. physics cube AABB is corrupt: " + this.segmentBuffer.a12());
      this.min.set(0.0F, 0.0F, 0.0F);
      this.max.set(0.0F, 0.0F, 0.0F);
    }
    AabbUtil2.transformAabb(this.min, this.max, paramFloat, paramTransform, paramVector3f1, paramVector3f2);
    this.minCached.set(paramVector3f1);
    this.maxCached.set(paramVector3f2);
    this.cachedTransform.set(paramTransform);
    this.cachedMargin = paramFloat;
    this.cacheDate = this.segmentBuffer.a12().getState().getUpdateNumber();
  }
  
  public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    getAabb(paramTransform, this.margin, paramVector3f1, paramVector3f2);
  }
  
  public Vector3f getLocalScaling(Vector3f paramVector3f)
  {
    return localScaling;
  }
  
  public float getMargin()
  {
    return this.margin;
  }
  
  public String getName()
  {
    return "CUBES_MESH";
  }
  
  public static void transformAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4, AABBVarSet paramAABBVarSet, Matrix3f paramMatrix3f)
  {
    assert (paramVector3f1.field_615 <= paramVector3f2.field_615);
    assert (paramVector3f1.field_616 <= paramVector3f2.field_616);
    assert (paramVector3f1.field_617 <= paramVector3f2.field_617);
    Vector3f localVector3f;
    (localVector3f = paramAABBVarSet.localHalfExtents).sub(paramVector3f2, paramVector3f1);
    localVector3f.scale(0.5F);
    localVector3f.field_615 += paramFloat;
    localVector3f.field_616 += paramFloat;
    localVector3f.field_617 += paramFloat;
    (paramFloat = paramAABBVarSet.localCenter).add(paramVector3f2, paramVector3f1);
    paramFloat.scale(0.5F);
    (paramVector3f1 = paramAABBVarSet.abs_b).set(paramMatrix3f);
    (paramVector3f2 = paramAABBVarSet.center).set(paramFloat);
    paramTransform.transform(paramVector3f2);
    (paramFloat = paramAABBVarSet.extent).field_615 = (paramVector3f1.m00 * localVector3f.field_615 + paramVector3f1.m01 * localVector3f.field_616 + paramVector3f1.m02 * localVector3f.field_617);
    paramFloat.field_616 = (paramVector3f1.m10 * localVector3f.field_615 + paramVector3f1.m11 * localVector3f.field_616 + paramVector3f1.m12 * localVector3f.field_617);
    paramFloat.field_617 = (paramVector3f1.m20 * localVector3f.field_615 + paramVector3f1.m21 * localVector3f.field_616 + paramVector3f1.m22 * localVector3f.field_617);
    paramVector3f3.sub(paramVector3f2, paramFloat);
    paramVector3f4.add(paramVector3f2, paramFloat);
  }
  
  public static void transformAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4, AABBVarSet paramAABBVarSet)
  {
    assert (paramVector3f1.field_615 <= paramVector3f2.field_615);
    assert (paramVector3f1.field_616 <= paramVector3f2.field_616);
    assert (paramVector3f1.field_617 <= paramVector3f2.field_617);
    Vector3f localVector3f;
    (localVector3f = paramAABBVarSet.localHalfExtents).sub(paramVector3f2, paramVector3f1);
    localVector3f.scale(0.5F);
    localVector3f.field_615 += paramFloat;
    localVector3f.field_616 += paramFloat;
    localVector3f.field_617 += paramFloat;
    (paramFloat = paramAABBVarSet.localCenter).add(paramVector3f2, paramVector3f1);
    paramFloat.scale(0.5F);
    (paramVector3f1 = paramAABBVarSet.abs_b).set(paramTransform.basis);
    MatrixUtil.absolute(paramVector3f1);
    (paramVector3f2 = paramAABBVarSet.center).set(paramFloat);
    paramTransform.transform(paramVector3f2);
    (paramFloat = paramAABBVarSet.extent).field_615 = (paramVector3f1.m00 * localVector3f.field_615 + paramVector3f1.m01 * localVector3f.field_616 + paramVector3f1.m02 * localVector3f.field_617);
    paramFloat.field_616 = (paramVector3f1.m10 * localVector3f.field_615 + paramVector3f1.m11 * localVector3f.field_616 + paramVector3f1.m12 * localVector3f.field_617);
    paramFloat.field_617 = (paramVector3f1.m20 * localVector3f.field_615 + paramVector3f1.m21 * localVector3f.field_616 + paramVector3f1.m22 * localVector3f.field_617);
    paramVector3f3.sub(paramVector3f2, paramFloat);
    paramVector3f4.add(paramVector3f2, paramFloat);
  }
  
  public void getSegmentAabb(Segment paramSegment, Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, AABBVarSet paramAABBVarSet)
  {
    Object localObject;
    if (paramSegment.jdField_field_34_of_type_Short == this.segmentBuffer.a12().getState().getUpdateNumber())
    {
      float[] arrayOfFloat = paramSegment.jdField_field_34_of_type_ArrayOfFloat;
      localObject = paramTransform;
      if (((arrayOfFloat[0] == ((Transform)localObject).basis.m00) && (arrayOfFloat[1] == ((Transform)localObject).basis.m10) && (arrayOfFloat[2] == ((Transform)localObject).basis.m20) && (arrayOfFloat[3] == 0.0F) && (arrayOfFloat[4] == ((Transform)localObject).basis.m01) && (arrayOfFloat[5] == ((Transform)localObject).basis.m11) && (arrayOfFloat[6] == ((Transform)localObject).basis.m21) && (arrayOfFloat[7] == 0.0F) && (arrayOfFloat[8] == ((Transform)localObject).basis.m02) && (arrayOfFloat[9] == ((Transform)localObject).basis.m12) && (arrayOfFloat[10] == ((Transform)localObject).basis.m22) && (arrayOfFloat[11] == 0.0F) && (arrayOfFloat[12] == ((Transform)localObject).origin.field_615) && (arrayOfFloat[13] == ((Transform)localObject).origin.field_616) && (arrayOfFloat[14] == ((Transform)localObject).origin.field_617) && (arrayOfFloat[15] == 1.0F) ? 1 : 0) != 0)
      {
        paramVector3f1.set(paramSegment.field_35);
        paramVector3f2.set(paramSegment.field_36);
        return;
      }
    }
    if (!paramSegment.g())
    {
      if ((localObject = paramSegment.a16()).getMin().field_453 <= ((SegmentData)localObject).getMax().field_453)
      {
        paramVector3f3.set(paramSegment.jdField_field_34_of_type_Class_48.field_475 + ((SegmentData)localObject).getMin().field_453 - 8 - 0.5F, paramSegment.jdField_field_34_of_type_Class_48.field_476 + ((SegmentData)localObject).getMin().field_454 - 8 - 0.5F, paramSegment.jdField_field_34_of_type_Class_48.field_477 + ((SegmentData)localObject).getMin().field_455 - 8 - 0.5F);
        paramVector3f4.set(paramSegment.jdField_field_34_of_type_Class_48.field_475 + ((SegmentData)localObject).getMax().field_453 - 8 + 0.5F, paramSegment.jdField_field_34_of_type_Class_48.field_476 + ((SegmentData)localObject).getMax().field_454 - 8 + 0.5F, paramSegment.jdField_field_34_of_type_Class_48.field_477 + ((SegmentData)localObject).getMax().field_455 - 8 + 0.5F);
        transformAabb(paramVector3f3, paramVector3f4, this.margin, paramTransform, paramVector3f1, paramVector3f2, paramAABBVarSet);
      }
      else
      {
        System.err.println("[CUBESHAPE] " + ((SegmentData)localObject).getSegmentController().getState() + " WARNING: NON INIT SEGMENT DATA AABB REQUEST " + ((SegmentData)localObject).getMin() + "; " + ((SegmentData)localObject).getMax() + ": " + ((SegmentData)localObject).getSegmentController() + ": RESTRUCTING AABB");
        ((SegmentData)localObject).restructBB(true);
        paramSegment.a15().getSegmentBuffer().a23();
        paramVector3f2.set(0.0F, 0.0F, 0.0F);
        paramVector3f1.set(0.0F, 0.0F, 0.0F);
      }
    }
    else
    {
      System.err.println("[CUBESHAPE] EMPTY SEGMENT DATA AABB REQUEST");
      paramVector3f2.set(0.0F, 0.0F, 0.0F);
      paramVector3f1.set(0.0F, 0.0F, 0.0F);
    }
    paramSegment.jdField_field_34_of_type_Short = this.segmentBuffer.a12().getState().getUpdateNumber();
    paramTransform.getOpenGLMatrix(paramSegment.jdField_field_34_of_type_ArrayOfFloat);
    paramSegment.field_35[0] = paramVector3f1.field_615;
    paramSegment.field_35[1] = paramVector3f1.field_616;
    paramSegment.field_35[2] = paramVector3f1.field_617;
    paramSegment.field_36[0] = paramVector3f2.field_615;
    paramSegment.field_36[1] = paramVector3f2.field_616;
    paramSegment.field_36[2] = paramVector3f2.field_617;
  }
  
  public class_886 getSegmentBuffer()
  {
    return this.segmentBuffer;
  }
  
  public BroadphaseNativeType getShapeType()
  {
    return BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE;
  }
  
  public void setLocalScaling(Vector3f paramVector3f)
  {
    localScaling.absolute(paramVector3f);
  }
  
  public void setMargin(float paramFloat)
  {
    this.margin = paramFloat;
  }
  
  public void setSegmentBuffer(class_886 paramclass_886)
  {
    this.segmentBuffer = paramclass_886;
  }
  
  public String toString()
  {
    return "[CubesShape" + (this.segmentBuffer.a12().isOnServer() ? "|SER " : "|CLI ") + this.segmentBuffer.a12() + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */