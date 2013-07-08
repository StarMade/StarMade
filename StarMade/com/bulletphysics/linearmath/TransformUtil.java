package com.bulletphysics.linearmath;

import com.bulletphysics..Stack;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class TransformUtil
{
  public static final float SIMDSQRT12 = 0.7071068F;
  public static final float ANGULAR_MOTION_THRESHOLD = 0.7853982F;
  
  public static float recipSqrt(float local_x)
  {
    return 1.0F / (float)Math.sqrt(local_x);
  }
  
  public static void planeSpace1(Vector3f local_n, Vector3f local_p, Vector3f local_q)
  {
    if (Math.abs(local_n.field_617) > 0.7071068F)
    {
      float local_a = local_n.field_616 * local_n.field_616 + local_n.field_617 * local_n.field_617;
      float local_k = recipSqrt(local_a);
      local_p.set(0.0F, -local_n.field_617 * local_k, local_n.field_616 * local_k);
      local_q.set(local_a * local_k, -local_n.field_615 * local_p.field_617, local_n.field_615 * local_p.field_616);
    }
    else
    {
      float local_a = local_n.field_615 * local_n.field_615 + local_n.field_616 * local_n.field_616;
      float local_k = recipSqrt(local_a);
      local_p.set(-local_n.field_616 * local_k, local_n.field_615 * local_k, 0.0F);
      local_q.set(-local_n.field_617 * local_p.field_616, local_n.field_617 * local_p.field_615, local_a * local_k);
    }
  }
  
  public static void integrateTransform(Transform arg0, Vector3f arg1, Vector3f arg2, float arg3, Transform arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Quat4f();
      predictedTransform.origin.scaleAdd(timeStep, linvel, curTrans.origin);
      Vector3f axis = localStack.get$javax$vecmath$Vector3f();
      float fAngle = angvel.length();
      if (fAngle * timeStep > 0.7853982F) {
        fAngle = 0.7853982F / timeStep;
      }
      if (fAngle < 0.001F) {
        axis.scale(0.5F * timeStep - timeStep * timeStep * timeStep * 0.02083333F * fAngle * fAngle, angvel);
      } else {
        axis.scale((float)Math.sin(0.5F * fAngle * timeStep) / fAngle, angvel);
      }
      Quat4f dorn = localStack.get$javax$vecmath$Quat4f();
      dorn.set(axis.field_615, axis.field_616, axis.field_617, (float)Math.cos(fAngle * timeStep * 0.5F));
      Quat4f orn0 = curTrans.getRotation(localStack.get$javax$vecmath$Quat4f());
      Quat4f predictedOrn = localStack.get$javax$vecmath$Quat4f();
      predictedOrn.mul(dorn, orn0);
      predictedOrn.normalize();
      predictedTransform.setRotation(predictedOrn);
      return;
    }
    finally
    {
      .Stack tmp204_202 = localStack;
      tmp204_202.pop$javax$vecmath$Vector3f();
      tmp204_202.pop$javax$vecmath$Quat4f();
    }
  }
  
  public static void calculateVelocity(Transform arg0, Transform arg1, float arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      linVel.sub(transform1.origin, transform0.origin);
      linVel.scale(1.0F / timeStep);
      Vector3f axis = localStack.get$javax$vecmath$Vector3f();
      float[] angle = new float[1];
      calculateDiffAxisAngle(transform0, transform1, axis, angle);
      angVel.scale(angle[0] / timeStep, axis);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void calculateDiffAxisAngle(Transform arg0, Transform arg1, Vector3f arg2, float[] arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Quat4f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Matrix3f tmp = localStack.get$javax$vecmath$Matrix3f();
      tmp.set(transform0.basis);
      MatrixUtil.invert(tmp);
      Matrix3f dmat = localStack.get$javax$vecmath$Matrix3f();
      dmat.mul(transform1.basis, tmp);
      Quat4f dorn = localStack.get$javax$vecmath$Quat4f();
      MatrixUtil.getRotation(dmat, dorn);
      dorn.normalize();
      angle[0] = QuaternionUtil.getAngle(dorn);
      axis.set(dorn.field_596, dorn.field_597, dorn.field_598);
      float len = axis.lengthSquared();
      if (len < 1.421086E-014F) {
        axis.set(1.0F, 0.0F, 0.0F);
      } else {
        axis.scale(1.0F / (float)Math.sqrt(len));
      }
      return;
    }
    finally
    {
      .Stack tmp148_146 = localStack;
      tmp148_146.pop$javax$vecmath$Quat4f();
      tmp148_146.pop$javax$vecmath$Matrix3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.TransformUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */