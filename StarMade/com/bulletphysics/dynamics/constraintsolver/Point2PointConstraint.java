/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Matrix3f;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */
/*  38:    */public class Point2PointConstraint
/*  39:    */  extends TypedConstraint
/*  40:    */{
/*  41: 41 */  private final JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  42:    */  
/*  43: 43 */  private final Vector3f pivotInA = new Vector3f();
/*  44: 44 */  private final Vector3f pivotInB = new Vector3f();
/*  45:    */  
/*  46: 46 */  public ConstraintSetting setting = new ConstraintSetting();
/*  47:    */  
/*  48:    */  public Point2PointConstraint() {
/*  49: 49 */    super(TypedConstraintType.POINT2POINT_CONSTRAINT_TYPE);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public Point2PointConstraint(RigidBody rbA, RigidBody rbB, Vector3f pivotInA, Vector3f pivotInB) {
/*  53: 53 */    super(TypedConstraintType.POINT2POINT_CONSTRAINT_TYPE, rbA, rbB);
/*  54: 54 */    this.pivotInA.set(pivotInA);
/*  55: 55 */    this.pivotInB.set(pivotInB);
/*  56:    */  }
/*  57:    */  
/*  61:    */  public Point2PointConstraint(RigidBody arg1, Vector3f arg2) {}
/*  62:    */  
/*  65:    */  public void buildJacobian()
/*  66:    */  {
/*  67: 67 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Vector3f();tmp11_7.push$javax$vecmath$Matrix3f();this.appliedImpulse = 0.0F;
/*  68:    */      
/*  69: 69 */      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  70: 70 */      normal.set(0.0F, 0.0F, 0.0F);
/*  71:    */      
/*  72: 72 */      Matrix3f tmpMat1 = localStack.get$javax$vecmath$Matrix3f();
/*  73: 73 */      Matrix3f tmpMat2 = localStack.get$javax$vecmath$Matrix3f();
/*  74: 74 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  75: 75 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  76: 76 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*  77:    */      
/*  78: 78 */      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*  79: 79 */      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*  80:    */      
/*  81: 81 */      for (int i = 0; i < 3; i++) {
/*  82: 82 */        VectorUtil.setCoord(normal, i, 1.0F);
/*  83:    */        
/*  84: 84 */        tmpMat1.transpose(centerOfMassA.basis);
/*  85: 85 */        tmpMat2.transpose(centerOfMassB.basis);
/*  86:    */        
/*  87: 87 */        tmp1.set(this.pivotInA);
/*  88: 88 */        centerOfMassA.transform(tmp1);
/*  89: 89 */        tmp1.sub(this.rbA.getCenterOfMassPosition(tmpVec));
/*  90:    */        
/*  91: 91 */        tmp2.set(this.pivotInB);
/*  92: 92 */        centerOfMassB.transform(tmp2);
/*  93: 93 */        tmp2.sub(this.rbB.getCenterOfMassPosition(tmpVec));
/*  94:    */        
/*  95: 95 */        this.jac[i].init(tmpMat1, tmpMat2, tmp1, tmp2, normal, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/*  96:    */        
/* 105:105 */        VectorUtil.setCoord(normal, i, 0.0F);
/* 106:    */      }
/* 107:107 */    } finally { .Stack tmp275_273 = localStack;tmp275_273.pop$com$bulletphysics$linearmath$Transform(); .Stack tmp279_275 = tmp275_273;tmp279_275.pop$javax$vecmath$Vector3f();tmp279_275.pop$javax$vecmath$Matrix3f();
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:111 */  public void solveConstraint(float arg1) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 112:112 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 113:113 */      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 114:    */      
/* 115:115 */      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 116:116 */      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 117:    */      
/* 118:118 */      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.pivotInA);
/* 119:119 */      centerOfMassA.transform(pivotAInW);
/* 120:    */      
/* 121:121 */      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.pivotInB);
/* 122:122 */      centerOfMassB.transform(pivotBInW);
/* 123:    */      
/* 124:124 */      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 125:125 */      normal.set(0.0F, 0.0F, 0.0F);
/* 126:    */      
/* 130:130 */      for (int i = 0; i < 3; i++) {
/* 131:131 */        VectorUtil.setCoord(normal, i, 1.0F);
/* 132:132 */        float jacDiagABInv = 1.0F / this.jac[i].getDiagonal();
/* 133:    */        
/* 134:134 */        Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 135:135 */        rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 136:136 */        Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 137:137 */        rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 138:    */        
/* 140:140 */        Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 141:141 */        Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 142:142 */        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 143:143 */        vel.sub(vel1, vel2);
/* 144:    */        
/* 146:146 */        float rel_vel = normal.dot(vel);
/* 147:    */        
/* 155:155 */        tmp.sub(pivotAInW, pivotBInW);
/* 156:156 */        float depth = -tmp.dot(normal);
/* 157:    */        
/* 158:158 */        float impulse = depth * this.setting.tau / timeStep * jacDiagABInv - this.setting.damping * rel_vel * jacDiagABInv;
/* 159:    */        
/* 160:160 */        float impulseClamp = this.setting.impulseClamp;
/* 161:161 */        if (impulseClamp > 0.0F) {
/* 162:162 */          if (impulse < -impulseClamp) {
/* 163:163 */            impulse = -impulseClamp;
/* 164:    */          }
/* 165:165 */          if (impulse > impulseClamp) {
/* 166:166 */            impulse = impulseClamp;
/* 167:    */          }
/* 168:    */        }
/* 169:    */        
/* 170:170 */        this.appliedImpulse += impulse;
/* 171:171 */        Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 172:172 */        impulse_vector.scale(impulse, normal);
/* 173:173 */        tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 174:174 */        this.rbA.applyImpulse(impulse_vector, tmp);
/* 175:175 */        tmp.negate(impulse_vector);
/* 176:176 */        tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 177:177 */        this.rbB.applyImpulse(tmp, tmp2);
/* 178:    */        
/* 179:179 */        VectorUtil.setCoord(normal, i, 0.0F);
/* 180:    */      }
/* 181:181 */    } finally { .Stack tmp446_444 = localStack;tmp446_444.pop$com$bulletphysics$linearmath$Transform();tmp446_444.pop$javax$vecmath$Vector3f();
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 185:    */  public void updateRHS(float timeStep) {}
/* 186:    */  
/* 187:187 */  public void setPivotA(Vector3f pivotA) { this.pivotInA.set(pivotA); }
/* 188:    */  
/* 189:    */  public void setPivotB(Vector3f pivotB)
/* 190:    */  {
/* 191:191 */    this.pivotInB.set(pivotB);
/* 192:    */  }
/* 193:    */  
/* 194:    */  public Vector3f getPivotInA(Vector3f out) {
/* 195:195 */    out.set(this.pivotInA);
/* 196:196 */    return out;
/* 197:    */  }
/* 198:    */  
/* 199:    */  public Vector3f getPivotInB(Vector3f out) {
/* 200:200 */    out.set(this.pivotInB);
/* 201:201 */    return out;
/* 202:    */  }
/* 203:    */  
/* 205:    */  public static class ConstraintSetting
/* 206:    */  {
/* 207:207 */    public float tau = 0.3F;
/* 208:208 */    public float damping = 1.0F;
/* 209:209 */    public float impulseClamp = 0.0F;
/* 210:    */  }
/* 211:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.Point2PointConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */