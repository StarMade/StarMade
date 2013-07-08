package org.schema.game.common.data.physics;

import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class GjkPairDetectorVariables
{
  public final Vector3f tmp = new Vector3f();
  public final Vector3f normalInB = new Vector3f();
  public final Vector3f tmpNormalInB = new Vector3f();
  public final Vector3f tmpPointOnA = new Vector3f();
  public final Vector3f field_483 = new Vector3f();
  public final Vector3f qWorld = new Vector3f();
  public final Vector3f pWorld = new Vector3f();
  public final Vector3f qInB = new Vector3f();
  public final Vector3f pInA = new Vector3f();
  public final Vector3f seperatingAxisInA = new Vector3f();
  public final Vector3f seperatingAxisInB = new Vector3f();
  public final Vector3f positionOffset = new Vector3f();
  public final Vector3f pointOnB = new Vector3f();
  public final Vector3f pointOnA = new Vector3f();
  public final Vector3f tmpPointOnB = new Vector3f();
  public final Transform localTransB = new Transform();
  public final Transform localTransA = new Transform();
  public final Vector3f axis = new Vector3f();
  public final Matrix3f tmp2 = new Matrix3f();
  public final Matrix3f dmat = new Matrix3f();
  public final Quat4f dorn = new Quat4f();
  public final Vector3f iAxis = new Vector3f();
  public final Quat4f iDorn = new Quat4f();
  public final Quat4f iorn0 = new Quat4f();
  public final Quat4f iPredictOrn = new Quat4f();
  public final float[] float4Temp = new float[4];
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.GjkPairDetectorVariables
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */