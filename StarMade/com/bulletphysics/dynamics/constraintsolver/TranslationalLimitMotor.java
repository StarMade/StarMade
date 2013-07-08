/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.VectorUtil;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  45:    */public class TranslationalLimitMotor
/*  46:    */{
/*  47: 47 */  public final Vector3f lowerLimit = new Vector3f();
/*  48: 48 */  public final Vector3f upperLimit = new Vector3f();
/*  49: 49 */  public final Vector3f accumulatedImpulse = new Vector3f();
/*  50:    */  public float limitSoftness;
/*  51:    */  public float damping;
/*  52:    */  public float restitution;
/*  53:    */  
/*  54:    */  public TranslationalLimitMotor()
/*  55:    */  {
/*  56: 56 */    this.lowerLimit.set(0.0F, 0.0F, 0.0F);
/*  57: 57 */    this.upperLimit.set(0.0F, 0.0F, 0.0F);
/*  58: 58 */    this.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
/*  59:    */    
/*  60: 60 */    this.limitSoftness = 0.7F;
/*  61: 61 */    this.damping = 1.0F;
/*  62: 62 */    this.restitution = 0.5F;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public TranslationalLimitMotor(TranslationalLimitMotor other) {
/*  66: 66 */    this.lowerLimit.set(other.lowerLimit);
/*  67: 67 */    this.upperLimit.set(other.upperLimit);
/*  68: 68 */    this.accumulatedImpulse.set(other.accumulatedImpulse);
/*  69:    */    
/*  70: 70 */    this.limitSoftness = other.limitSoftness;
/*  71: 71 */    this.damping = other.damping;
/*  72: 72 */    this.restitution = other.restitution;
/*  73:    */  }
/*  74:    */  
/*  81:    */  public boolean isLimited(int limitIndex)
/*  82:    */  {
/*  83: 83 */    return VectorUtil.getCoord(this.upperLimit, limitIndex) >= VectorUtil.getCoord(this.lowerLimit, limitIndex);
/*  84:    */  }
/*  85:    */  
/*  86:    */  public float solveLinearAxis(float arg1, float arg2, RigidBody arg3, Vector3f arg4, RigidBody arg5, Vector3f arg6, int arg7, Vector3f arg8, Vector3f arg9)
/*  87:    */  {
/*  88: 88 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  89: 89 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*  90:    */      
/*  92: 92 */      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/*  93:    */      
/*  94: 94 */      rel_pos1.sub(anchorPos, body1.getCenterOfMassPosition(tmpVec));
/*  95:    */      
/*  96: 96 */      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/*  97:    */      
/*  98: 98 */      rel_pos2.sub(anchorPos, body2.getCenterOfMassPosition(tmpVec));
/*  99:    */      
/* 100:100 */      Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 101:101 */      Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 102:102 */      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 103:103 */      vel.sub(vel1, vel2);
/* 104:    */      
/* 105:105 */      float rel_vel = axis_normal_on_a.dot(vel);
/* 106:    */      
/* 110:110 */      tmp.sub(pointInA, pointInB);
/* 111:111 */      float depth = -tmp.dot(axis_normal_on_a);
/* 112:112 */      float lo = -1.0E+030F;
/* 113:113 */      float hi = 1.0E+030F;
/* 114:    */      
/* 115:115 */      float minLimit = VectorUtil.getCoord(this.lowerLimit, limit_index);
/* 116:116 */      float maxLimit = VectorUtil.getCoord(this.upperLimit, limit_index);
/* 117:    */      
/* 119:119 */      if (minLimit < maxLimit)
/* 120:    */      {
/* 121:121 */        if (depth > maxLimit) {
/* 122:122 */          depth -= maxLimit;
/* 123:123 */          lo = 0.0F;
/* 126:    */        }
/* 127:127 */        else if (depth < minLimit) {
/* 128:128 */          depth -= minLimit;
/* 129:129 */          hi = 0.0F;
/* 130:    */        }
/* 131:    */        else {
/* 132:132 */          return 0.0F;
/* 133:    */        }
/* 134:    */      }
/* 135:    */      
/* 138:138 */      float normalImpulse = this.limitSoftness * (this.restitution * depth / timeStep - this.damping * rel_vel) * jacDiagABInv;
/* 139:    */      
/* 140:140 */      float oldNormalImpulse = VectorUtil.getCoord(this.accumulatedImpulse, limit_index);
/* 141:141 */      float sum = oldNormalImpulse + normalImpulse;
/* 142:142 */      VectorUtil.setCoord(this.accumulatedImpulse, limit_index, sum < lo ? 0.0F : sum > hi ? 0.0F : sum);
/* 143:143 */      normalImpulse = VectorUtil.getCoord(this.accumulatedImpulse, limit_index) - oldNormalImpulse;
/* 144:    */      
/* 145:145 */      Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 146:146 */      impulse_vector.scale(normalImpulse, axis_normal_on_a);
/* 147:147 */      body1.applyImpulse(impulse_vector, rel_pos1);
/* 148:    */      
/* 149:149 */      tmp.negate(impulse_vector);
/* 150:150 */      body2.applyImpulse(tmp, rel_pos2);
/* 151:151 */      return normalImpulse; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 152:    */    }
/* 153:    */  }
/* 154:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.TranslationalLimitMotor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */