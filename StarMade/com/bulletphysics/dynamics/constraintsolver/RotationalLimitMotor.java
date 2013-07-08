/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/*  46:    */public class RotationalLimitMotor
/*  47:    */{
/*  48:    */  public float loLimit;
/*  49:    */  public float hiLimit;
/*  50:    */  public float targetVelocity;
/*  51:    */  public float maxMotorForce;
/*  52:    */  public float maxLimitForce;
/*  53:    */  public float damping;
/*  54:    */  public float limitSoftness;
/*  55:    */  public float ERP;
/*  56:    */  public float bounce;
/*  57:    */  public boolean enableMotor;
/*  58:    */  public float currentLimitError;
/*  59:    */  public int currentLimit;
/*  60:    */  public float accumulatedImpulse;
/*  61:    */  
/*  62:    */  public RotationalLimitMotor()
/*  63:    */  {
/*  64: 64 */    this.accumulatedImpulse = 0.0F;
/*  65: 65 */    this.targetVelocity = 0.0F;
/*  66: 66 */    this.maxMotorForce = 0.1F;
/*  67: 67 */    this.maxLimitForce = 300.0F;
/*  68: 68 */    this.loLimit = -3.402824E+038F;
/*  69: 69 */    this.hiLimit = 3.4028235E+38F;
/*  70: 70 */    this.ERP = 0.5F;
/*  71: 71 */    this.bounce = 0.0F;
/*  72: 72 */    this.damping = 1.0F;
/*  73: 73 */    this.limitSoftness = 0.5F;
/*  74: 74 */    this.currentLimit = 0;
/*  75: 75 */    this.currentLimitError = 0.0F;
/*  76: 76 */    this.enableMotor = false;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public RotationalLimitMotor(RotationalLimitMotor limot) {
/*  80: 80 */    this.targetVelocity = limot.targetVelocity;
/*  81: 81 */    this.maxMotorForce = limot.maxMotorForce;
/*  82: 82 */    this.limitSoftness = limot.limitSoftness;
/*  83: 83 */    this.loLimit = limot.loLimit;
/*  84: 84 */    this.hiLimit = limot.hiLimit;
/*  85: 85 */    this.ERP = limot.ERP;
/*  86: 86 */    this.bounce = limot.bounce;
/*  87: 87 */    this.currentLimit = limot.currentLimit;
/*  88: 88 */    this.currentLimitError = limot.currentLimitError;
/*  89: 89 */    this.enableMotor = limot.enableMotor;
/*  90:    */  }
/*  91:    */  
/*  95:    */  public boolean isLimited()
/*  96:    */  {
/*  97: 97 */    if (this.loLimit >= this.hiLimit) return false;
/*  98: 98 */    return true;
/*  99:    */  }
/* 100:    */  
/* 104:    */  public boolean needApplyTorques()
/* 105:    */  {
/* 106:106 */    if ((this.currentLimit == 0) && (!this.enableMotor)) return false;
/* 107:107 */    return true;
/* 108:    */  }
/* 109:    */  
/* 112:    */  public int testLimitValue(float test_value)
/* 113:    */  {
/* 114:114 */    if (this.loLimit > this.hiLimit) {
/* 115:115 */      this.currentLimit = 0;
/* 116:116 */      return 0;
/* 117:    */    }
/* 118:    */    
/* 119:119 */    if (test_value < this.loLimit) {
/* 120:120 */      this.currentLimit = 1;
/* 121:121 */      this.currentLimitError = (test_value - this.loLimit);
/* 122:122 */      return 1;
/* 123:    */    }
/* 124:124 */    if (test_value > this.hiLimit) {
/* 125:125 */      this.currentLimit = 2;
/* 126:126 */      this.currentLimitError = (test_value - this.hiLimit);
/* 127:127 */      return 2;
/* 128:    */    }
/* 129:    */    
/* 130:130 */    this.currentLimit = 0;
/* 131:131 */    return 0;
/* 132:    */  }
/* 133:    */  
/* 137:    */  public float solveAngularLimits(float arg1, Vector3f arg2, float arg3, RigidBody arg4, RigidBody arg5)
/* 138:    */  {
/* 139:139 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (!needApplyTorques()) {
/* 140:140 */        return 0.0F;
/* 141:    */      }
/* 142:    */      
/* 143:143 */      float target_velocity = this.targetVelocity;
/* 144:144 */      float maxMotorForce = this.maxMotorForce;
/* 145:    */      
/* 147:147 */      if (this.currentLimit != 0) {
/* 148:148 */        target_velocity = -this.ERP * this.currentLimitError / timeStep;
/* 149:149 */        maxMotorForce = this.maxLimitForce;
/* 150:    */      }
/* 151:    */      
/* 152:152 */      maxMotorForce *= timeStep;
/* 153:    */      
/* 155:155 */      Vector3f vel_diff = body0.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 156:156 */      if (body1 != null) {
/* 157:157 */        vel_diff.sub(body1.getAngularVelocity(localStack.get$javax$vecmath$Vector3f()));
/* 158:    */      }
/* 159:    */      
/* 160:160 */      float rel_vel = axis.dot(vel_diff);
/* 161:    */      
/* 163:163 */      float motor_relvel = this.limitSoftness * (target_velocity - this.damping * rel_vel);
/* 164:    */      
/* 165:165 */      if ((motor_relvel < 1.192093E-007F) && (motor_relvel > -1.192093E-007F)) {
/* 166:166 */        return 0.0F;
/* 167:    */      }
/* 168:    */      
/* 170:170 */      float unclippedMotorImpulse = (1.0F + this.bounce) * motor_relvel * jacDiagABInv;
/* 171:    */      
/* 173:    */      float clippedMotorImpulse;
/* 174:    */      
/* 176:176 */      if (unclippedMotorImpulse > 0.0F) {
/* 177:177 */        clippedMotorImpulse = unclippedMotorImpulse > maxMotorForce ? maxMotorForce : unclippedMotorImpulse;
/* 178:    */      }
/* 179:    */      else {
/* 180:180 */        clippedMotorImpulse = unclippedMotorImpulse < -maxMotorForce ? -maxMotorForce : unclippedMotorImpulse;
/* 181:    */      }
/* 182:    */      
/* 184:184 */      float lo = -1.0E+030F;
/* 185:185 */      float hi = 1.0E+030F;
/* 186:    */      
/* 187:187 */      float oldaccumImpulse = this.accumulatedImpulse;
/* 188:188 */      float sum = oldaccumImpulse + clippedMotorImpulse;
/* 189:189 */      this.accumulatedImpulse = (sum < lo ? 0.0F : sum > hi ? 0.0F : sum);
/* 190:    */      
/* 191:191 */      float clippedMotorImpulse = this.accumulatedImpulse - oldaccumImpulse;
/* 192:    */      
/* 193:193 */      Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
/* 194:194 */      motorImp.scale(clippedMotorImpulse, axis);
/* 195:    */      
/* 196:196 */      body0.applyTorqueImpulse(motorImp);
/* 197:197 */      if (body1 != null) {
/* 198:198 */        motorImp.negate();
/* 199:199 */        body1.applyTorqueImpulse(motorImp);
/* 200:    */      }
/* 201:    */      
/* 202:202 */      return clippedMotorImpulse; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 203:    */    }
/* 204:    */  }
/* 205:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */