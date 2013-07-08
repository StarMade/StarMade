package com.bulletphysics.dynamics.vehicle;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ContactConstraint;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraintType;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.QuaternionUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ArrayPool;
import com.bulletphysics.util.FloatArrayList;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

public class RaycastVehicle
  extends TypedConstraint
{
  private final ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
  private static RigidBody s_fixedObject = new RigidBody(0.0F, null, null);
  private static final float sideFrictionStiffness2 = 1.0F;
  protected ObjectArrayList<Vector3f> forwardWS = new ObjectArrayList();
  protected ObjectArrayList<Vector3f> axle = new ObjectArrayList();
  protected FloatArrayList forwardImpulse = new FloatArrayList();
  protected FloatArrayList sideImpulse = new FloatArrayList();
  private float tau;
  private float damping;
  private VehicleRaycaster vehicleRaycaster;
  private float pitchControl = 0.0F;
  private float steeringValue;
  private float currentVehicleSpeedKmHour;
  private RigidBody chassisBody;
  private int indexRightAxis = 0;
  private int indexUpAxis = 2;
  private int indexForwardAxis = 1;
  public ObjectArrayList<WheelInfo> wheelInfo = new ObjectArrayList();
  
  public RaycastVehicle(VehicleTuning tuning, RigidBody chassis, VehicleRaycaster raycaster)
  {
    super(TypedConstraintType.VEHICLE_CONSTRAINT_TYPE);
    this.vehicleRaycaster = raycaster;
    this.chassisBody = chassis;
    defaultInit(tuning);
  }
  
  private void defaultInit(VehicleTuning tuning)
  {
    this.currentVehicleSpeedKmHour = 0.0F;
    this.steeringValue = 0.0F;
  }
  
  public WheelInfo addWheel(Vector3f connectionPointCS, Vector3f wheelDirectionCS0, Vector3f wheelAxleCS, float suspensionRestLength, float wheelRadius, VehicleTuning tuning, boolean isFrontWheel)
  {
    WheelInfoConstructionInfo local_ci = new WheelInfoConstructionInfo();
    local_ci.chassisConnectionCS.set(connectionPointCS);
    local_ci.wheelDirectionCS.set(wheelDirectionCS0);
    local_ci.wheelAxleCS.set(wheelAxleCS);
    local_ci.suspensionRestLength = suspensionRestLength;
    local_ci.wheelRadius = wheelRadius;
    local_ci.suspensionStiffness = tuning.suspensionStiffness;
    local_ci.wheelsDampingCompression = tuning.suspensionCompression;
    local_ci.wheelsDampingRelaxation = tuning.suspensionDamping;
    local_ci.frictionSlip = tuning.frictionSlip;
    local_ci.bIsFrontWheel = isFrontWheel;
    local_ci.maxSuspensionTravelCm = tuning.maxSuspensionTravelCm;
    this.wheelInfo.add(new WheelInfo(local_ci));
    WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(getNumWheels() - 1);
    updateWheelTransformsWS(wheel, false);
    updateWheelTransform(getNumWheels() - 1, false);
    return wheel;
  }
  
  public Transform getWheelTransformWS(int wheelIndex, Transform out)
  {
    assert (wheelIndex < getNumWheels());
    WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(wheelIndex);
    out.set(wheel.worldTransform);
    return out;
  }
  
  public void updateWheelTransform(int wheelIndex)
  {
    updateWheelTransform(wheelIndex, true);
  }
  
  public void updateWheelTransform(int arg1, boolean arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Quat4f();
      tmp11_7.push$javax$vecmath$Matrix3f();
      WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(wheelIndex);
      updateWheelTransformsWS(wheel, interpolatedTransform);
      Vector3f local_up = localStack.get$javax$vecmath$Vector3f();
      local_up.negate(wheel.raycastInfo.wheelDirectionWS);
      Vector3f right = wheel.raycastInfo.wheelAxleWS;
      Vector3f fwd = localStack.get$javax$vecmath$Vector3f();
      fwd.cross(local_up, right);
      fwd.normalize();
      float steering = wheel.steering;
      Quat4f steeringOrn = localStack.get$javax$vecmath$Quat4f();
      QuaternionUtil.setRotation(steeringOrn, local_up, steering);
      Matrix3f steeringMat = localStack.get$javax$vecmath$Matrix3f();
      MatrixUtil.setRotation(steeringMat, steeringOrn);
      Quat4f rotatingOrn = localStack.get$javax$vecmath$Quat4f();
      QuaternionUtil.setRotation(rotatingOrn, right, -wheel.rotation);
      Matrix3f rotatingMat = localStack.get$javax$vecmath$Matrix3f();
      MatrixUtil.setRotation(rotatingMat, rotatingOrn);
      Matrix3f basis2 = localStack.get$javax$vecmath$Matrix3f();
      basis2.setRow(0, right.field_615, fwd.field_615, local_up.field_615);
      basis2.setRow(1, right.field_616, fwd.field_616, local_up.field_616);
      basis2.setRow(2, right.field_617, fwd.field_617, local_up.field_617);
      Matrix3f wheelBasis = wheel.worldTransform.basis;
      wheelBasis.mul(steeringMat, rotatingMat);
      wheelBasis.mul(basis2);
      wheel.worldTransform.origin.scaleAdd(wheel.raycastInfo.suspensionLength, wheel.raycastInfo.wheelDirectionWS, wheel.raycastInfo.hardPointWS);
      return;
    }
    finally
    {
      .Stack tmp296_294 = localStack;
      tmp296_294.pop$javax$vecmath$Vector3f();
      .Stack tmp300_296 = tmp296_294;
      tmp300_296.pop$javax$vecmath$Quat4f();
      tmp300_296.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void resetSuspension()
  {
    for (int local_i = 0; local_i < this.wheelInfo.size(); local_i++)
    {
      WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(local_i);
      wheel.raycastInfo.suspensionLength = wheel.getSuspensionRestLength();
      wheel.suspensionRelativeVelocity = 0.0F;
      wheel.raycastInfo.contactNormalWS.negate(wheel.raycastInfo.wheelDirectionWS);
      wheel.clippedInvContactDotSuspension = 1.0F;
    }
  }
  
  public void updateWheelTransformsWS(WheelInfo wheel)
  {
    updateWheelTransformsWS(wheel, true);
  }
  
  public void updateWheelTransformsWS(WheelInfo arg1, boolean arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      wheel.raycastInfo.isInContact = false;
      Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      if ((interpolatedTransform) && (getRigidBody().getMotionState() != null)) {
        getRigidBody().getMotionState().getWorldTransform(chassisTrans);
      }
      wheel.raycastInfo.hardPointWS.set(wheel.chassisConnectionPointCS);
      chassisTrans.transform(wheel.raycastInfo.hardPointWS);
      wheel.raycastInfo.wheelDirectionWS.set(wheel.wheelDirectionCS);
      chassisTrans.basis.transform(wheel.raycastInfo.wheelDirectionWS);
      wheel.raycastInfo.wheelAxleWS.set(wheel.wheelAxleCS);
      chassisTrans.basis.transform(wheel.raycastInfo.wheelAxleWS);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public float rayCast(WheelInfo arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      updateWheelTransformsWS(wheel, false);
      float depth = -1.0F;
      float raylen = wheel.getSuspensionRestLength() + wheel.wheelsRadius;
      Vector3f rayvector = localStack.get$javax$vecmath$Vector3f();
      rayvector.scale(raylen, wheel.raycastInfo.wheelDirectionWS);
      Vector3f source = wheel.raycastInfo.hardPointWS;
      wheel.raycastInfo.contactPointWS.add(source, rayvector);
      Vector3f target = wheel.raycastInfo.contactPointWS;
      float param = 0.0F;
      VehicleRaycasterResult rayResults = new VehicleRaycasterResult();
      assert (this.vehicleRaycaster != null);
      Object object = this.vehicleRaycaster.castRay(source, target, rayResults);
      wheel.raycastInfo.groundObject = null;
      if (object != null)
      {
        param = rayResults.distFraction;
        depth = raylen * rayResults.distFraction;
        wheel.raycastInfo.contactNormalWS.set(rayResults.hitNormalInWorld);
        wheel.raycastInfo.isInContact = true;
        wheel.raycastInfo.groundObject = s_fixedObject;
        float hitDistance = param * raylen;
        wheel.raycastInfo.suspensionLength = (hitDistance - wheel.wheelsRadius);
        float minSuspensionLength = wheel.getSuspensionRestLength() - wheel.maxSuspensionTravelCm * 0.01F;
        float maxSuspensionLength = wheel.getSuspensionRestLength() + wheel.maxSuspensionTravelCm * 0.01F;
        if (wheel.raycastInfo.suspensionLength < minSuspensionLength) {
          wheel.raycastInfo.suspensionLength = minSuspensionLength;
        }
        if (wheel.raycastInfo.suspensionLength > maxSuspensionLength) {
          wheel.raycastInfo.suspensionLength = maxSuspensionLength;
        }
        wheel.raycastInfo.contactPointWS.set(rayResults.hitPointInWorld);
        float denominator = wheel.raycastInfo.contactNormalWS.dot(wheel.raycastInfo.wheelDirectionWS);
        Vector3f chassis_velocity_at_contactPoint = localStack.get$javax$vecmath$Vector3f();
        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
        relpos.sub(wheel.raycastInfo.contactPointWS, getRigidBody().getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
        getRigidBody().getVelocityInLocalPoint(relpos, chassis_velocity_at_contactPoint);
        float projVel = wheel.raycastInfo.contactNormalWS.dot(chassis_velocity_at_contactPoint);
        if (denominator >= -0.1F)
        {
          wheel.suspensionRelativeVelocity = 0.0F;
          wheel.clippedInvContactDotSuspension = 10.0F;
        }
        else
        {
          float inv = -1.0F / denominator;
          wheel.suspensionRelativeVelocity = (projVel * inv);
          wheel.clippedInvContactDotSuspension = inv;
        }
      }
      else
      {
        wheel.raycastInfo.suspensionLength = wheel.getSuspensionRestLength();
        wheel.suspensionRelativeVelocity = 0.0F;
        wheel.raycastInfo.contactNormalWS.negate(wheel.raycastInfo.wheelDirectionWS);
        wheel.clippedInvContactDotSuspension = 1.0F;
      }
      return depth;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public Transform getChassisWorldTransform(Transform out)
  {
    return getRigidBody().getCenterOfMassTransform(out);
  }
  
  public void updateVehicle(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < getNumWheels(); local_i++) {
        updateWheelTransform(local_i, false);
      }
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      this.currentVehicleSpeedKmHour = (3.6F * getRigidBody().getLinearVelocity(local_i).length());
      Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Vector3f forwardW = localStack.get$javax$vecmath$Vector3f();
      forwardW.set(chassisTrans.basis.getElement(0, this.indexForwardAxis), chassisTrans.basis.getElement(1, this.indexForwardAxis), chassisTrans.basis.getElement(2, this.indexForwardAxis));
      if (forwardW.dot(getRigidBody().getLinearVelocity(local_i)) < 0.0F) {
        this.currentVehicleSpeedKmHour *= -1.0F;
      }
      int local_i1 = 0;
      float depth;
      for (local_i1 = 0; local_i1 < this.wheelInfo.size(); local_i1++) {
        depth = rayCast((WheelInfo)this.wheelInfo.getQuick(local_i1));
      }
      updateSuspension(step);
      for (local_i1 = 0; local_i1 < this.wheelInfo.size(); local_i1++)
      {
        WheelInfo depth = (WheelInfo)this.wheelInfo.getQuick(local_i1);
        float suspensionForce = depth.wheelsSuspensionForce;
        float gMaxSuspensionForce = 6000.0F;
        if (suspensionForce > gMaxSuspensionForce) {
          suspensionForce = gMaxSuspensionForce;
        }
        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
        impulse.scale(suspensionForce * step, depth.raycastInfo.contactNormalWS);
        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
        relpos.sub(depth.raycastInfo.contactPointWS, getRigidBody().getCenterOfMassPosition(local_i));
        getRigidBody().applyImpulse(impulse, relpos);
      }
      updateFriction(step);
      for (local_i1 = 0; local_i1 < this.wheelInfo.size(); local_i1++)
      {
        WheelInfo depth = (WheelInfo)this.wheelInfo.getQuick(local_i1);
        Vector3f suspensionForce = localStack.get$javax$vecmath$Vector3f();
        suspensionForce.sub(depth.raycastInfo.hardPointWS, getRigidBody().getCenterOfMassPosition(local_i));
        Vector3f gMaxSuspensionForce = getRigidBody().getVelocityInLocalPoint(suspensionForce, localStack.get$javax$vecmath$Vector3f());
        if (depth.raycastInfo.isInContact)
        {
          Transform impulse = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
          Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
          relpos.set(impulse.basis.getElement(0, this.indexForwardAxis), impulse.basis.getElement(1, this.indexForwardAxis), impulse.basis.getElement(2, this.indexForwardAxis));
          float proj = relpos.dot(depth.raycastInfo.contactNormalWS);
          local_i.scale(proj, depth.raycastInfo.contactNormalWS);
          relpos.sub(local_i);
          float proj2 = relpos.dot(gMaxSuspensionForce);
          depth.deltaRotation = (proj2 * step / depth.wheelsRadius);
          depth.rotation += depth.deltaRotation;
        }
        else
        {
          depth.rotation += depth.deltaRotation;
        }
        depth.deltaRotation *= 0.99F;
      }
      return;
    }
    finally
    {
      .Stack tmp592_590 = localStack;
      tmp592_590.pop$com$bulletphysics$linearmath$Transform();
      tmp592_590.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setSteeringValue(float steering, int wheel)
  {
    assert ((wheel >= 0) && (wheel < getNumWheels()));
    WheelInfo wheel_info = getWheelInfo(wheel);
    wheel_info.steering = steering;
  }
  
  public float getSteeringValue(int wheel)
  {
    return getWheelInfo(wheel).steering;
  }
  
  public void applyEngineForce(float force, int wheel)
  {
    assert ((wheel >= 0) && (wheel < getNumWheels()));
    WheelInfo wheel_info = getWheelInfo(wheel);
    wheel_info.engineForce = force;
  }
  
  public WheelInfo getWheelInfo(int index)
  {
    assert ((index >= 0) && (index < getNumWheels()));
    return (WheelInfo)this.wheelInfo.getQuick(index);
  }
  
  public void setBrake(float brake, int wheelIndex)
  {
    assert ((wheelIndex >= 0) && (wheelIndex < getNumWheels()));
    getWheelInfo(wheelIndex).brake = brake;
  }
  
  public void updateSuspension(float deltaTime)
  {
    float chassisMass = 1.0F / this.chassisBody.getInvMass();
    for (int w_it = 0; w_it < getNumWheels(); w_it++)
    {
      WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(w_it);
      if (wheel_info.raycastInfo.isInContact)
      {
        float susp_length = wheel_info.getSuspensionRestLength();
        float current_length = wheel_info.raycastInfo.suspensionLength;
        float length_diff = susp_length - current_length;
        float force = wheel_info.suspensionStiffness * length_diff * wheel_info.clippedInvContactDotSuspension;
        float susp_length = wheel_info.suspensionRelativeVelocity;
        float current_length;
        float current_length;
        if (susp_length < 0.0F) {
          current_length = wheel_info.wheelsDampingCompression;
        } else {
          current_length = wheel_info.wheelsDampingRelaxation;
        }
        force -= current_length * susp_length;
        wheel_info.wheelsSuspensionForce = (force * chassisMass);
        if (wheel_info.wheelsSuspensionForce < 0.0F) {
          wheel_info.wheelsSuspensionForce = 0.0F;
        }
      }
      else
      {
        wheel_info.wheelsSuspensionForce = 0.0F;
      }
    }
  }
  
  private float calcRollingFriction(WheelContactPoint arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      float local_j1 = 0.0F;
      Vector3f contactPosWorld = contactPoint.frictionPositionWorld;
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      rel_pos1.sub(contactPosWorld, contactPoint.body0.getCenterOfMassPosition(tmp));
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      rel_pos2.sub(contactPosWorld, contactPoint.body1.getCenterOfMassPosition(tmp));
      float maxImpulse = contactPoint.maxImpulse;
      Vector3f vel1 = contactPoint.body0.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel2 = contactPoint.body1.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.sub(vel1, vel2);
      float vrel = contactPoint.frictionDirectionWorld.dot(vel);
      local_j1 = -vrel * contactPoint.jacDiagABInv;
      local_j1 = Math.min(local_j1, maxImpulse);
      local_j1 = Math.max(local_j1, -maxImpulse);
      return local_j1;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void updateFriction(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Vector3f();
      tmp11_7.push$javax$vecmath$Matrix3f();
      int numWheel = getNumWheels();
      if (numWheel == 0) {
        return;
      }
      MiscUtil.resize(this.forwardWS, numWheel, Vector3f.class);
      MiscUtil.resize(this.axle, numWheel, Vector3f.class);
      MiscUtil.resize(this.forwardImpulse, numWheel, 0.0F);
      MiscUtil.resize(this.sideImpulse, numWheel, 0.0F);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      int numWheelsOnGround = 0;
      for (int local_i = 0; local_i < getNumWheels(); local_i++)
      {
        WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(local_i);
        RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
        if (groundObject != null) {
          numWheelsOnGround++;
        }
        this.sideImpulse.set(local_i, 0.0F);
        this.forwardImpulse.set(local_i, 0.0F);
      }
      Transform local_i = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int wheel_info = 0; wheel_info < getNumWheels(); wheel_info++)
      {
        WheelInfo groundObject = (WheelInfo)this.wheelInfo.getQuick(wheel_info);
        RigidBody groundObject = (RigidBody)groundObject.raycastInfo.groundObject;
        if (groundObject != null)
        {
          getWheelTransformWS(wheel_info, local_i);
          Matrix3f wheelBasis0 = localStack.get$javax$vecmath$Matrix3f(local_i.basis);
          ((Vector3f)this.axle.getQuick(wheel_info)).set(wheelBasis0.getElement(0, this.indexRightAxis), wheelBasis0.getElement(1, this.indexRightAxis), wheelBasis0.getElement(2, this.indexRightAxis));
          Vector3f surfNormalWS = groundObject.raycastInfo.contactNormalWS;
          float proj = ((Vector3f)this.axle.getQuick(wheel_info)).dot(surfNormalWS);
          tmp.scale(proj, surfNormalWS);
          ((Vector3f)this.axle.getQuick(wheel_info)).sub(tmp);
          ((Vector3f)this.axle.getQuick(wheel_info)).normalize();
          ((Vector3f)this.forwardWS.getQuick(wheel_info)).cross(surfNormalWS, (Vector3f)this.axle.getQuick(wheel_info));
          ((Vector3f)this.forwardWS.getQuick(wheel_info)).normalize();
          float[] floatPtr = (float[])this.floatArrays.getFixed(1);
          ContactConstraint.resolveSingleBilateral(this.chassisBody, groundObject.raycastInfo.contactPointWS, groundObject, groundObject.raycastInfo.contactPointWS, 0.0F, (Vector3f)this.axle.getQuick(wheel_info), floatPtr, timeStep);
          this.sideImpulse.set(wheel_info, floatPtr[0]);
          this.floatArrays.release(floatPtr);
          this.sideImpulse.set(wheel_info, this.sideImpulse.get(wheel_info) * 1.0F);
        }
      }
      float local_i = 1.0F;
      float wheel_info = 0.5F;
      boolean groundObject = false;
      for (int groundObject = 0; groundObject < getNumWheels(); groundObject++)
      {
        WheelInfo wheelBasis0 = (WheelInfo)this.wheelInfo.getQuick(groundObject);
        RigidBody surfNormalWS = (RigidBody)wheelBasis0.raycastInfo.groundObject;
        float proj = 0.0F;
        if (surfNormalWS != null) {
          if (wheelBasis0.engineForce != 0.0F)
          {
            proj = wheelBasis0.engineForce * timeStep;
          }
          else
          {
            float floatPtr = 0.0F;
            float maxImpulse = wheelBasis0.brake != 0.0F ? wheelBasis0.brake : floatPtr;
            WheelContactPoint contactPt = new WheelContactPoint(this.chassisBody, surfNormalWS, wheelBasis0.raycastInfo.contactPointWS, (Vector3f)this.forwardWS.getQuick(groundObject), maxImpulse);
            proj = calcRollingFriction(contactPt);
          }
        }
        this.forwardImpulse.set(groundObject, 0.0F);
        ((WheelInfo)this.wheelInfo.getQuick(groundObject)).skidInfo = 1.0F;
        if (surfNormalWS != null)
        {
          ((WheelInfo)this.wheelInfo.getQuick(groundObject)).skidInfo = 1.0F;
          float floatPtr = wheelBasis0.wheelsSuspensionForce * timeStep * wheelBasis0.frictionSlip;
          float maxImpulse = floatPtr;
          float contactPt = floatPtr * maxImpulse;
          this.forwardImpulse.set(groundObject, proj);
          float local_x = this.forwardImpulse.get(groundObject) * wheel_info;
          float local_y = this.sideImpulse.get(groundObject) * local_i;
          float impulseSquared = local_x * local_x + local_y * local_y;
          if (impulseSquared > contactPt)
          {
            groundObject = true;
            float factor = floatPtr / (float)Math.sqrt(impulseSquared);
            ((WheelInfo)this.wheelInfo.getQuick(groundObject)).skidInfo *= factor;
          }
        }
      }
      if (groundObject) {
        for (int groundObject = 0; groundObject < getNumWheels(); groundObject++) {
          if ((this.sideImpulse.get(groundObject) != 0.0F) && (((WheelInfo)this.wheelInfo.getQuick(groundObject)).skidInfo < 1.0F))
          {
            this.forwardImpulse.set(groundObject, this.forwardImpulse.get(groundObject) * ((WheelInfo)this.wheelInfo.getQuick(groundObject)).skidInfo);
            this.sideImpulse.set(groundObject, this.sideImpulse.get(groundObject) * ((WheelInfo)this.wheelInfo.getQuick(groundObject)).skidInfo);
          }
        }
      }
      for (int groundObject = 0; groundObject < getNumWheels(); groundObject++)
      {
        WheelInfo wheelBasis0 = (WheelInfo)this.wheelInfo.getQuick(groundObject);
        Vector3f surfNormalWS = localStack.get$javax$vecmath$Vector3f();
        surfNormalWS.sub(wheelBasis0.raycastInfo.contactPointWS, this.chassisBody.getCenterOfMassPosition(tmp));
        if (this.forwardImpulse.get(groundObject) != 0.0F)
        {
          tmp.scale(this.forwardImpulse.get(groundObject), (Tuple3f)this.forwardWS.getQuick(groundObject));
          this.chassisBody.applyImpulse(tmp, surfNormalWS);
        }
        if (this.sideImpulse.get(groundObject) != 0.0F)
        {
          RigidBody proj = (RigidBody)((WheelInfo)this.wheelInfo.getQuick(groundObject)).raycastInfo.groundObject;
          Vector3f floatPtr = localStack.get$javax$vecmath$Vector3f();
          floatPtr.sub(wheelBasis0.raycastInfo.contactPointWS, proj.getCenterOfMassPosition(tmp));
          Vector3f maxImpulse = localStack.get$javax$vecmath$Vector3f();
          maxImpulse.scale(this.sideImpulse.get(groundObject), (Tuple3f)this.axle.getQuick(groundObject));
          surfNormalWS.field_617 *= wheelBasis0.rollInfluence;
          this.chassisBody.applyImpulse(maxImpulse, surfNormalWS);
          tmp.negate(maxImpulse);
          proj.applyImpulse(tmp, floatPtr);
        }
      }
      return;
    }
    finally
    {
      .Stack tmp1205_1203 = localStack;
      tmp1205_1203.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp1209_1205 = tmp1205_1203;
      tmp1209_1205.pop$javax$vecmath$Vector3f();
      tmp1209_1205.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void buildJacobian() {}
  
  public void solveConstraint(float timeStep) {}
  
  public int getNumWheels()
  {
    return this.wheelInfo.size();
  }
  
  public void setPitchControl(float pitch)
  {
    this.pitchControl = pitch;
  }
  
  public RigidBody getRigidBody()
  {
    return this.chassisBody;
  }
  
  public int getRightAxis()
  {
    return this.indexRightAxis;
  }
  
  public int getUpAxis()
  {
    return this.indexUpAxis;
  }
  
  public int getForwardAxis()
  {
    return this.indexForwardAxis;
  }
  
  public Vector3f getForwardVector(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      out.set(chassisTrans.basis.getElement(0, this.indexForwardAxis), chassisTrans.basis.getElement(1, this.indexForwardAxis), chassisTrans.basis.getElement(2, this.indexForwardAxis));
      return out;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public float getCurrentSpeedKmHour()
  {
    return this.currentVehicleSpeedKmHour;
  }
  
  public void setCoordinateSystem(int rightIndex, int upIndex, int forwardIndex)
  {
    this.indexRightAxis = rightIndex;
    this.indexUpAxis = upIndex;
    this.indexForwardAxis = forwardIndex;
  }
  
  private static class WheelContactPoint
  {
    public RigidBody body0;
    public RigidBody body1;
    public final Vector3f frictionPositionWorld = new Vector3f();
    public final Vector3f frictionDirectionWorld = new Vector3f();
    public float jacDiagABInv;
    public float maxImpulse;
    
    public WheelContactPoint(RigidBody body0, RigidBody body1, Vector3f frictionPosWorld, Vector3f frictionDirectionWorld, float maxImpulse)
    {
      this.body0 = body0;
      this.body1 = body1;
      this.frictionPositionWorld.set(frictionPosWorld);
      this.frictionDirectionWorld.set(frictionDirectionWorld);
      this.maxImpulse = maxImpulse;
      float denom0 = body0.computeImpulseDenominator(frictionPosWorld, frictionDirectionWorld);
      float denom1 = body1.computeImpulseDenominator(frictionPosWorld, frictionDirectionWorld);
      float relaxation = 1.0F;
      this.jacDiagABInv = (relaxation / (denom0 + denom1));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.RaycastVehicle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */