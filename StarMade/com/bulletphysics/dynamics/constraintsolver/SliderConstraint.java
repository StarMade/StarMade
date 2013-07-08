package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class SliderConstraint
  extends TypedConstraint
{
  public static final float SLIDER_CONSTRAINT_DEF_SOFTNESS = 1.0F;
  public static final float SLIDER_CONSTRAINT_DEF_DAMPING = 1.0F;
  public static final float SLIDER_CONSTRAINT_DEF_RESTITUTION = 0.7F;
  protected final Transform frameInA = new Transform();
  protected final Transform frameInB = new Transform();
  protected boolean useLinearReferenceFrameA;
  protected float lowerLinLimit;
  protected float upperLinLimit;
  protected float lowerAngLimit;
  protected float upperAngLimit;
  protected float softnessDirLin;
  protected float restitutionDirLin;
  protected float dampingDirLin;
  protected float softnessDirAng;
  protected float restitutionDirAng;
  protected float dampingDirAng;
  protected float softnessLimLin;
  protected float restitutionLimLin;
  protected float dampingLimLin;
  protected float softnessLimAng;
  protected float restitutionLimAng;
  protected float dampingLimAng;
  protected float softnessOrthoLin;
  protected float restitutionOrthoLin;
  protected float dampingOrthoLin;
  protected float softnessOrthoAng;
  protected float restitutionOrthoAng;
  protected float dampingOrthoAng;
  protected boolean solveLinLim;
  protected boolean solveAngLim;
  protected JacobianEntry[] jacLin = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  protected float[] jacLinDiagABInv = new float[3];
  protected JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  protected float timeStep;
  protected final Transform calculatedTransformA = new Transform();
  protected final Transform calculatedTransformB = new Transform();
  protected final Vector3f sliderAxis = new Vector3f();
  protected final Vector3f realPivotAInW = new Vector3f();
  protected final Vector3f realPivotBInW = new Vector3f();
  protected final Vector3f projPivotInW = new Vector3f();
  protected final Vector3f delta = new Vector3f();
  protected final Vector3f depth = new Vector3f();
  protected final Vector3f relPosA = new Vector3f();
  protected final Vector3f relPosB = new Vector3f();
  protected float linPos;
  protected float angDepth;
  protected float kAngle;
  protected boolean poweredLinMotor;
  protected float targetLinMotorVelocity;
  protected float maxLinMotorForce;
  protected float accumulatedLinMotorImpulse;
  protected boolean poweredAngMotor;
  protected float targetAngMotorVelocity;
  protected float maxAngMotorForce;
  protected float accumulatedAngMotorImpulse;
  
  public SliderConstraint()
  {
    super(TypedConstraintType.SLIDER_CONSTRAINT_TYPE);
    this.useLinearReferenceFrameA = true;
    initParams();
  }
  
  public SliderConstraint(RigidBody rbA, RigidBody rbB, Transform frameInA, Transform frameInB, boolean useLinearReferenceFrameA)
  {
    super(TypedConstraintType.SLIDER_CONSTRAINT_TYPE, rbA, rbB);
    this.frameInA.set(frameInA);
    this.frameInB.set(frameInB);
    this.useLinearReferenceFrameA = useLinearReferenceFrameA;
    initParams();
  }
  
  protected void initParams()
  {
    this.lowerLinLimit = 1.0F;
    this.upperLinLimit = -1.0F;
    this.lowerAngLimit = 0.0F;
    this.upperAngLimit = 0.0F;
    this.softnessDirLin = 1.0F;
    this.restitutionDirLin = 0.7F;
    this.dampingDirLin = 0.0F;
    this.softnessDirAng = 1.0F;
    this.restitutionDirAng = 0.7F;
    this.dampingDirAng = 0.0F;
    this.softnessOrthoLin = 1.0F;
    this.restitutionOrthoLin = 0.7F;
    this.dampingOrthoLin = 1.0F;
    this.softnessOrthoAng = 1.0F;
    this.restitutionOrthoAng = 0.7F;
    this.dampingOrthoAng = 1.0F;
    this.softnessLimLin = 1.0F;
    this.restitutionLimLin = 0.7F;
    this.dampingLimLin = 1.0F;
    this.softnessLimAng = 1.0F;
    this.restitutionLimAng = 0.7F;
    this.dampingLimAng = 1.0F;
    this.poweredLinMotor = false;
    this.targetLinMotorVelocity = 0.0F;
    this.maxLinMotorForce = 0.0F;
    this.accumulatedLinMotorImpulse = 0.0F;
    this.poweredAngMotor = false;
    this.targetAngMotorVelocity = 0.0F;
    this.maxAngMotorForce = 0.0F;
    this.accumulatedAngMotorImpulse = 0.0F;
  }
  
  public void buildJacobian()
  {
    if (this.useLinearReferenceFrameA) {
      buildJacobianInt(this.rbA, this.rbB, this.frameInA, this.frameInB);
    } else {
      buildJacobianInt(this.rbB, this.rbA, this.frameInB, this.frameInA);
    }
  }
  
  public void solveConstraint(float timeStep)
  {
    this.timeStep = timeStep;
    if (this.useLinearReferenceFrameA) {
      solveConstraintInt(this.rbA, this.rbB);
    } else {
      solveConstraintInt(this.rbB, this.rbA);
    }
  }
  
  public Transform getCalculatedTransformA(Transform out)
  {
    out.set(this.calculatedTransformA);
    return out;
  }
  
  public Transform getCalculatedTransformB(Transform out)
  {
    out.set(this.calculatedTransformB);
    return out;
  }
  
  public Transform getFrameOffsetA(Transform out)
  {
    out.set(this.frameInA);
    return out;
  }
  
  public Transform getFrameOffsetB(Transform out)
  {
    out.set(this.frameInB);
    return out;
  }
  
  public float getLowerLinLimit()
  {
    return this.lowerLinLimit;
  }
  
  public void setLowerLinLimit(float lowerLimit)
  {
    this.lowerLinLimit = lowerLimit;
  }
  
  public float getUpperLinLimit()
  {
    return this.upperLinLimit;
  }
  
  public void setUpperLinLimit(float upperLimit)
  {
    this.upperLinLimit = upperLimit;
  }
  
  public float getLowerAngLimit()
  {
    return this.lowerAngLimit;
  }
  
  public void setLowerAngLimit(float lowerLimit)
  {
    this.lowerAngLimit = lowerLimit;
  }
  
  public float getUpperAngLimit()
  {
    return this.upperAngLimit;
  }
  
  public void setUpperAngLimit(float upperLimit)
  {
    this.upperAngLimit = upperLimit;
  }
  
  public boolean getUseLinearReferenceFrameA()
  {
    return this.useLinearReferenceFrameA;
  }
  
  public float getSoftnessDirLin()
  {
    return this.softnessDirLin;
  }
  
  public float getRestitutionDirLin()
  {
    return this.restitutionDirLin;
  }
  
  public float getDampingDirLin()
  {
    return this.dampingDirLin;
  }
  
  public float getSoftnessDirAng()
  {
    return this.softnessDirAng;
  }
  
  public float getRestitutionDirAng()
  {
    return this.restitutionDirAng;
  }
  
  public float getDampingDirAng()
  {
    return this.dampingDirAng;
  }
  
  public float getSoftnessLimLin()
  {
    return this.softnessLimLin;
  }
  
  public float getRestitutionLimLin()
  {
    return this.restitutionLimLin;
  }
  
  public float getDampingLimLin()
  {
    return this.dampingLimLin;
  }
  
  public float getSoftnessLimAng()
  {
    return this.softnessLimAng;
  }
  
  public float getRestitutionLimAng()
  {
    return this.restitutionLimAng;
  }
  
  public float getDampingLimAng()
  {
    return this.dampingLimAng;
  }
  
  public float getSoftnessOrthoLin()
  {
    return this.softnessOrthoLin;
  }
  
  public float getRestitutionOrthoLin()
  {
    return this.restitutionOrthoLin;
  }
  
  public float getDampingOrthoLin()
  {
    return this.dampingOrthoLin;
  }
  
  public float getSoftnessOrthoAng()
  {
    return this.softnessOrthoAng;
  }
  
  public float getRestitutionOrthoAng()
  {
    return this.restitutionOrthoAng;
  }
  
  public float getDampingOrthoAng()
  {
    return this.dampingOrthoAng;
  }
  
  public void setSoftnessDirLin(float softnessDirLin)
  {
    this.softnessDirLin = softnessDirLin;
  }
  
  public void setRestitutionDirLin(float restitutionDirLin)
  {
    this.restitutionDirLin = restitutionDirLin;
  }
  
  public void setDampingDirLin(float dampingDirLin)
  {
    this.dampingDirLin = dampingDirLin;
  }
  
  public void setSoftnessDirAng(float softnessDirAng)
  {
    this.softnessDirAng = softnessDirAng;
  }
  
  public void setRestitutionDirAng(float restitutionDirAng)
  {
    this.restitutionDirAng = restitutionDirAng;
  }
  
  public void setDampingDirAng(float dampingDirAng)
  {
    this.dampingDirAng = dampingDirAng;
  }
  
  public void setSoftnessLimLin(float softnessLimLin)
  {
    this.softnessLimLin = softnessLimLin;
  }
  
  public void setRestitutionLimLin(float restitutionLimLin)
  {
    this.restitutionLimLin = restitutionLimLin;
  }
  
  public void setDampingLimLin(float dampingLimLin)
  {
    this.dampingLimLin = dampingLimLin;
  }
  
  public void setSoftnessLimAng(float softnessLimAng)
  {
    this.softnessLimAng = softnessLimAng;
  }
  
  public void setRestitutionLimAng(float restitutionLimAng)
  {
    this.restitutionLimAng = restitutionLimAng;
  }
  
  public void setDampingLimAng(float dampingLimAng)
  {
    this.dampingLimAng = dampingLimAng;
  }
  
  public void setSoftnessOrthoLin(float softnessOrthoLin)
  {
    this.softnessOrthoLin = softnessOrthoLin;
  }
  
  public void setRestitutionOrthoLin(float restitutionOrthoLin)
  {
    this.restitutionOrthoLin = restitutionOrthoLin;
  }
  
  public void setDampingOrthoLin(float dampingOrthoLin)
  {
    this.dampingOrthoLin = dampingOrthoLin;
  }
  
  public void setSoftnessOrthoAng(float softnessOrthoAng)
  {
    this.softnessOrthoAng = softnessOrthoAng;
  }
  
  public void setRestitutionOrthoAng(float restitutionOrthoAng)
  {
    this.restitutionOrthoAng = restitutionOrthoAng;
  }
  
  public void setDampingOrthoAng(float dampingOrthoAng)
  {
    this.dampingOrthoAng = dampingOrthoAng;
  }
  
  public void setPoweredLinMotor(boolean onOff)
  {
    this.poweredLinMotor = onOff;
  }
  
  public boolean getPoweredLinMotor()
  {
    return this.poweredLinMotor;
  }
  
  public void setTargetLinMotorVelocity(float targetLinMotorVelocity)
  {
    this.targetLinMotorVelocity = targetLinMotorVelocity;
  }
  
  public float getTargetLinMotorVelocity()
  {
    return this.targetLinMotorVelocity;
  }
  
  public void setMaxLinMotorForce(float maxLinMotorForce)
  {
    this.maxLinMotorForce = maxLinMotorForce;
  }
  
  public float getMaxLinMotorForce()
  {
    return this.maxLinMotorForce;
  }
  
  public void setPoweredAngMotor(boolean onOff)
  {
    this.poweredAngMotor = onOff;
  }
  
  public boolean getPoweredAngMotor()
  {
    return this.poweredAngMotor;
  }
  
  public void setTargetAngMotorVelocity(float targetAngMotorVelocity)
  {
    this.targetAngMotorVelocity = targetAngMotorVelocity;
  }
  
  public float getTargetAngMotorVelocity()
  {
    return this.targetAngMotorVelocity;
  }
  
  public void setMaxAngMotorForce(float maxAngMotorForce)
  {
    this.maxAngMotorForce = maxAngMotorForce;
  }
  
  public float getMaxAngMotorForce()
  {
    return this.maxAngMotorForce;
  }
  
  public float getLinearPos()
  {
    return this.linPos;
  }
  
  public boolean getSolveLinLimit()
  {
    return this.solveLinLim;
  }
  
  public float getLinDepth()
  {
    return this.depth.field_615;
  }
  
  public boolean getSolveAngLimit()
  {
    return this.solveAngLim;
  }
  
  public float getAngDepth()
  {
    return this.angDepth;
  }
  
  public void buildJacobianInt(RigidBody arg1, RigidBody arg2, Transform arg3, Transform arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      this.calculatedTransformA.mul(rbA.getCenterOfMassTransform(tmpTrans), frameInA);
      this.calculatedTransformB.mul(rbB.getCenterOfMassTransform(tmpTrans), frameInB);
      this.realPivotAInW.set(this.calculatedTransformA.origin);
      this.realPivotBInW.set(this.calculatedTransformB.origin);
      this.calculatedTransformA.basis.getColumn(0, tmp);
      this.sliderAxis.set(tmp);
      this.delta.sub(this.realPivotBInW, this.realPivotAInW);
      this.projPivotInW.scaleAdd(this.sliderAxis.dot(this.delta), this.sliderAxis, this.realPivotAInW);
      this.relPosA.sub(this.projPivotInW, rbA.getCenterOfMassPosition(tmp));
      this.relPosB.sub(this.realPivotBInW, rbB.getCenterOfMassPosition(tmp));
      Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 3; local_i++)
      {
        this.calculatedTransformA.basis.getColumn(local_i, normalWorld);
        Matrix3f mat1 = rbA.getCenterOfMassTransform(tmpTrans1).basis;
        mat1.transpose();
        Matrix3f mat2 = rbB.getCenterOfMassTransform(tmpTrans2).basis;
        mat2.transpose();
        this.jacLin[local_i].init(mat1, mat2, this.relPosA, this.relPosB, normalWorld, rbA.getInvInertiaDiagLocal(tmp), rbA.getInvMass(), rbB.getInvInertiaDiagLocal(tmp2), rbB.getInvMass());
        this.jacLinDiagABInv[local_i] = (1.0F / this.jacLin[local_i].getDiagonal());
        VectorUtil.setCoord(this.depth, local_i, this.delta.dot(normalWorld));
      }
      testLinLimits();
      for (int local_i = 0; local_i < 3; local_i++)
      {
        this.calculatedTransformA.basis.getColumn(local_i, normalWorld);
        Matrix3f mat1 = rbA.getCenterOfMassTransform(tmpTrans1).basis;
        mat1.transpose();
        Matrix3f mat2 = rbB.getCenterOfMassTransform(tmpTrans2).basis;
        mat2.transpose();
        this.jacAng[local_i].init(normalWorld, mat1, mat2, rbA.getInvInertiaDiagLocal(tmp), rbB.getInvInertiaDiagLocal(tmp2));
      }
      testAngLimits();
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      this.calculatedTransformA.basis.getColumn(0, local_i);
      this.kAngle = (1.0F / (rbA.computeAngularImpulseDenominator(local_i) + rbB.computeAngularImpulseDenominator(local_i)));
      this.accumulatedLinMotorImpulse = 0.0F;
      this.accumulatedAngMotorImpulse = 0.0F;
      return;
    }
    finally
    {
      .Stack tmp510_508 = localStack;
      tmp510_508.pop$com$bulletphysics$linearmath$Transform();
      tmp510_508.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void solveConstraintInt(RigidBody arg1, RigidBody arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f velA = rbA.getVelocityInLocalPoint(this.relPosA, localStack.get$javax$vecmath$Vector3f());
      Vector3f velB = rbB.getVelocityInLocalPoint(this.relPosB, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.sub(velA, velB);
      Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 3; local_i++)
      {
        Vector3f normal = this.jacLin[local_i].linearJointAxis;
        float rel_vel = normal.dot(vel);
        float depth = VectorUtil.getCoord(this.depth, local_i);
        float softness = this.solveLinLim ? this.softnessLimLin : local_i != 0 ? this.softnessOrthoLin : this.softnessDirLin;
        float restitution = this.solveLinLim ? this.restitutionLimLin : local_i != 0 ? this.restitutionOrthoLin : this.restitutionDirLin;
        float damping = this.solveLinLim ? this.dampingLimLin : local_i != 0 ? this.dampingOrthoLin : this.dampingDirLin;
        float normalImpulse = softness * (restitution * depth / this.timeStep - damping * rel_vel) * this.jacLinDiagABInv[local_i];
        impulse_vector.scale(normalImpulse, normal);
        rbA.applyImpulse(impulse_vector, this.relPosA);
        tmp.negate(impulse_vector);
        rbB.applyImpulse(tmp, this.relPosB);
        if ((this.poweredLinMotor) && (local_i == 0) && (this.accumulatedLinMotorImpulse < this.maxLinMotorForce))
        {
          float desiredMotorVel = this.targetLinMotorVelocity;
          float motor_relvel = desiredMotorVel + rel_vel;
          normalImpulse = -motor_relvel * this.jacLinDiagABInv[local_i];
          float new_acc = this.accumulatedLinMotorImpulse + Math.abs(normalImpulse);
          if (new_acc > this.maxLinMotorForce) {
            new_acc = this.maxLinMotorForce;
          }
          float del = new_acc - this.accumulatedLinMotorImpulse;
          if (normalImpulse < 0.0F) {
            normalImpulse = -del;
          } else {
            normalImpulse = del;
          }
          this.accumulatedLinMotorImpulse = new_acc;
          impulse_vector.scale(normalImpulse, normal);
          rbA.applyImpulse(impulse_vector, this.relPosA);
          tmp.negate(impulse_vector);
          rbB.applyImpulse(tmp, this.relPosB);
        }
      }
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      this.calculatedTransformA.basis.getColumn(0, local_i);
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      this.calculatedTransformB.basis.getColumn(0, normal);
      Vector3f rel_vel = rbA.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      Vector3f depth = rbB.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      Vector3f softness = localStack.get$javax$vecmath$Vector3f();
      softness.scale(local_i.dot(rel_vel), local_i);
      Vector3f restitution = localStack.get$javax$vecmath$Vector3f();
      restitution.scale(normal.dot(depth), normal);
      Vector3f damping = localStack.get$javax$vecmath$Vector3f();
      damping.sub(rel_vel, softness);
      Vector3f normalImpulse = localStack.get$javax$vecmath$Vector3f();
      normalImpulse.sub(depth, restitution);
      Vector3f desiredMotorVel = localStack.get$javax$vecmath$Vector3f();
      desiredMotorVel.sub(damping, normalImpulse);
      float motor_relvel = desiredMotorVel.length();
      if (motor_relvel > 1.0E-005F)
      {
        Vector3f new_acc = localStack.get$javax$vecmath$Vector3f();
        new_acc.normalize(desiredMotorVel);
        float del = rbA.computeAngularImpulseDenominator(new_acc) + rbB.computeAngularImpulseDenominator(new_acc);
        desiredMotorVel.scale(1.0F / del * this.dampingOrthoAng * this.softnessOrthoAng);
      }
      Vector3f new_acc = localStack.get$javax$vecmath$Vector3f();
      new_acc.cross(local_i, normal);
      new_acc.scale(1.0F / this.timeStep);
      float del = new_acc.length();
      if (del > 1.0E-005F)
      {
        Vector3f normal2 = localStack.get$javax$vecmath$Vector3f();
        normal2.normalize(new_acc);
        float denom2 = rbA.computeAngularImpulseDenominator(normal2) + rbB.computeAngularImpulseDenominator(normal2);
        new_acc.scale(1.0F / denom2 * this.restitutionOrthoAng * this.softnessOrthoAng);
      }
      tmp.negate(desiredMotorVel);
      tmp.add(new_acc);
      rbA.applyTorqueImpulse(tmp);
      tmp.sub(desiredMotorVel, new_acc);
      rbB.applyTorqueImpulse(tmp);
      float normal2;
      if (this.solveAngLim)
      {
        tmp.sub(depth, rel_vel);
        float normal2 = tmp.dot(local_i) * this.dampingLimAng + this.angDepth * this.restitutionLimAng / this.timeStep;
        normal2 *= this.kAngle * this.softnessLimAng;
      }
      else
      {
        tmp.sub(depth, rel_vel);
        normal2 = tmp.dot(local_i) * this.dampingDirAng + this.angDepth * this.restitutionDirAng / this.timeStep;
        normal2 *= this.kAngle * this.softnessDirAng;
      }
      Vector3f denom2 = localStack.get$javax$vecmath$Vector3f();
      denom2.scale(normal2, local_i);
      rbA.applyTorqueImpulse(denom2);
      tmp.negate(denom2);
      rbB.applyTorqueImpulse(tmp);
      if ((this.poweredAngMotor) && (this.accumulatedAngMotorImpulse < this.maxAngMotorForce))
      {
        Vector3f velrel = localStack.get$javax$vecmath$Vector3f();
        velrel.sub(softness, restitution);
        float projRelVel = velrel.dot(local_i);
        float desiredMotorVel = this.targetAngMotorVelocity;
        float motor_relvel = desiredMotorVel - projRelVel;
        float angImpulse = this.kAngle * motor_relvel;
        float new_acc = this.accumulatedAngMotorImpulse + Math.abs(angImpulse);
        if (new_acc > this.maxAngMotorForce) {
          new_acc = this.maxAngMotorForce;
        }
        float del = new_acc - this.accumulatedAngMotorImpulse;
        if (angImpulse < 0.0F) {
          angImpulse = -del;
        } else {
          angImpulse = del;
        }
        this.accumulatedAngMotorImpulse = new_acc;
        Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
        motorImp.scale(angImpulse, local_i);
        rbA.applyTorqueImpulse(motorImp);
        tmp.negate(motorImp);
        rbB.applyTorqueImpulse(tmp);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void calculateTransforms()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      if (this.useLinearReferenceFrameA)
      {
        this.calculatedTransformA.mul(this.rbA.getCenterOfMassTransform(tmpTrans), this.frameInA);
        this.calculatedTransformB.mul(this.rbB.getCenterOfMassTransform(tmpTrans), this.frameInB);
      }
      else
      {
        this.calculatedTransformA.mul(this.rbB.getCenterOfMassTransform(tmpTrans), this.frameInB);
        this.calculatedTransformB.mul(this.rbA.getCenterOfMassTransform(tmpTrans), this.frameInA);
      }
      this.realPivotAInW.set(this.calculatedTransformA.origin);
      this.realPivotBInW.set(this.calculatedTransformB.origin);
      this.calculatedTransformA.basis.getColumn(0, this.sliderAxis);
      this.delta.sub(this.realPivotBInW, this.realPivotAInW);
      this.projPivotInW.scaleAdd(this.sliderAxis.dot(this.delta), this.sliderAxis, this.realPivotAInW);
      Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 3; local_i++)
      {
        this.calculatedTransformA.basis.getColumn(local_i, normalWorld);
        VectorUtil.setCoord(this.depth, local_i, this.delta.dot(normalWorld));
      }
      return;
    }
    finally
    {
      .Stack tmp249_247 = localStack;
      tmp249_247.pop$com$bulletphysics$linearmath$Transform();
      tmp249_247.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void testLinLimits()
  {
    this.solveLinLim = false;
    this.linPos = this.depth.field_615;
    if (this.lowerLinLimit <= this.upperLinLimit)
    {
      if (this.depth.field_615 > this.upperLinLimit)
      {
        this.depth.field_615 -= this.upperLinLimit;
        this.solveLinLim = true;
      }
      else if (this.depth.field_615 < this.lowerLinLimit)
      {
        this.depth.field_615 -= this.lowerLinLimit;
        this.solveLinLim = true;
      }
      else
      {
        this.depth.field_615 = 0.0F;
      }
    }
    else {
      this.depth.field_615 = 0.0F;
    }
  }
  
  public void testAngLimits()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.angDepth = 0.0F;
      this.solveAngLim = false;
      if (this.lowerAngLimit <= this.upperAngLimit)
      {
        Vector3f axisA0 = localStack.get$javax$vecmath$Vector3f();
        this.calculatedTransformA.basis.getColumn(1, axisA0);
        Vector3f axisA1 = localStack.get$javax$vecmath$Vector3f();
        this.calculatedTransformA.basis.getColumn(2, axisA1);
        Vector3f axisB0 = localStack.get$javax$vecmath$Vector3f();
        this.calculatedTransformB.basis.getColumn(1, axisB0);
        float rot = (float)Math.atan2(axisB0.dot(axisA1), axisB0.dot(axisA0));
        if (rot < this.lowerAngLimit)
        {
          this.angDepth = (rot - this.lowerAngLimit);
          this.solveAngLim = true;
        }
        else if (rot > this.upperAngLimit)
        {
          this.angDepth = (rot - this.upperAngLimit);
          this.solveAngLim = true;
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public Vector3f getAncorInA(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Vector3f ancorInA = out;
      ancorInA.scaleAdd((this.lowerLinLimit + this.upperLinLimit) * 0.5F, this.sliderAxis, this.realPivotAInW);
      this.rbA.getCenterOfMassTransform(tmpTrans);
      tmpTrans.inverse();
      tmpTrans.transform(ancorInA);
      return ancorInA;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public Vector3f getAncorInB(Vector3f out)
  {
    Vector3f ancorInB = out;
    ancorInB.set(this.frameInB.origin);
    return ancorInB;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SliderConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */