/*   1:    */package com.bulletphysics.dynamics.constraintsolver;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.VectorUtil;
/*   5:    */import javax.vecmath.Matrix3f;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  46:    */public class JacobianEntry
/*  47:    */{
/*  48: 48 */  public final Vector3f linearJointAxis = new Vector3f();
/*  49: 49 */  public final Vector3f aJ = new Vector3f();
/*  50: 50 */  public final Vector3f bJ = new Vector3f();
/*  51: 51 */  public final Vector3f m_0MinvJt = new Vector3f();
/*  52: 52 */  public final Vector3f m_1MinvJt = new Vector3f();
/*  53:    */  
/*  60:    */  public float Adiag;
/*  61:    */  
/*  69:    */  public void init(Matrix3f world2A, Matrix3f world2B, Vector3f rel_pos1, Vector3f rel_pos2, Vector3f jointAxis, Vector3f inertiaInvA, float massInvA, Vector3f inertiaInvB, float massInvB)
/*  70:    */  {
/*  71: 71 */    this.linearJointAxis.set(jointAxis);
/*  72:    */    
/*  73: 73 */    this.aJ.cross(rel_pos1, this.linearJointAxis);
/*  74: 74 */    world2A.transform(this.aJ);
/*  75:    */    
/*  76: 76 */    this.bJ.set(this.linearJointAxis);
/*  77: 77 */    this.bJ.negate();
/*  78: 78 */    this.bJ.cross(rel_pos2, this.bJ);
/*  79: 79 */    world2B.transform(this.bJ);
/*  80:    */    
/*  81: 81 */    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.aJ);
/*  82: 82 */    VectorUtil.mul(this.m_1MinvJt, inertiaInvB, this.bJ);
/*  83: 83 */    this.Adiag = (massInvA + this.m_0MinvJt.dot(this.aJ) + massInvB + this.m_1MinvJt.dot(this.bJ));
/*  84:    */    
/*  85: 85 */    assert (this.Adiag > 0.0F);
/*  86:    */  }
/*  87:    */  
/*  95:    */  public void init(Vector3f jointAxis, Matrix3f world2A, Matrix3f world2B, Vector3f inertiaInvA, Vector3f inertiaInvB)
/*  96:    */  {
/*  97: 97 */    this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
/*  98:    */    
/*  99: 99 */    this.aJ.set(jointAxis);
/* 100:100 */    world2A.transform(this.aJ);
/* 101:    */    
/* 102:102 */    this.bJ.set(jointAxis);
/* 103:103 */    this.bJ.negate();
/* 104:104 */    world2B.transform(this.bJ);
/* 105:    */    
/* 106:106 */    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.aJ);
/* 107:107 */    VectorUtil.mul(this.m_1MinvJt, inertiaInvB, this.bJ);
/* 108:108 */    this.Adiag = (this.m_0MinvJt.dot(this.aJ) + this.m_1MinvJt.dot(this.bJ));
/* 109:    */    
/* 110:110 */    assert (this.Adiag > 0.0F);
/* 111:    */  }
/* 112:    */  
/* 119:    */  public void init(Vector3f axisInA, Vector3f axisInB, Vector3f inertiaInvA, Vector3f inertiaInvB)
/* 120:    */  {
/* 121:121 */    this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
/* 122:122 */    this.aJ.set(axisInA);
/* 123:    */    
/* 124:124 */    this.bJ.set(axisInB);
/* 125:125 */    this.bJ.negate();
/* 126:    */    
/* 127:127 */    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.aJ);
/* 128:128 */    VectorUtil.mul(this.m_1MinvJt, inertiaInvB, this.bJ);
/* 129:129 */    this.Adiag = (this.m_0MinvJt.dot(this.aJ) + this.m_1MinvJt.dot(this.bJ));
/* 130:    */    
/* 131:131 */    assert (this.Adiag > 0.0F);
/* 132:    */  }
/* 133:    */  
/* 142:    */  public void init(Matrix3f world2A, Vector3f rel_pos1, Vector3f rel_pos2, Vector3f jointAxis, Vector3f inertiaInvA, float massInvA)
/* 143:    */  {
/* 144:144 */    this.linearJointAxis.set(jointAxis);
/* 145:    */    
/* 146:146 */    this.aJ.cross(rel_pos1, jointAxis);
/* 147:147 */    world2A.transform(this.aJ);
/* 148:    */    
/* 149:149 */    this.bJ.set(jointAxis);
/* 150:150 */    this.bJ.negate();
/* 151:151 */    this.bJ.cross(rel_pos2, this.bJ);
/* 152:152 */    world2A.transform(this.bJ);
/* 153:    */    
/* 154:154 */    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.aJ);
/* 155:155 */    this.m_1MinvJt.set(0.0F, 0.0F, 0.0F);
/* 156:156 */    this.Adiag = (massInvA + this.m_0MinvJt.dot(this.aJ));
/* 157:    */    
/* 158:158 */    assert (this.Adiag > 0.0F);
/* 159:    */  }
/* 160:    */  
/* 161:161 */  public float getDiagonal() { return this.Adiag; }
/* 162:    */  
/* 165:    */  public float getNonDiagonal(JacobianEntry jacB, float massInvA)
/* 166:    */  {
/* 167:167 */    JacobianEntry jacA = this;
/* 168:168 */    float lin = massInvA * jacA.linearJointAxis.dot(jacB.linearJointAxis);
/* 169:169 */    float ang = jacA.m_0MinvJt.dot(jacB.aJ);
/* 170:170 */    return lin + ang;
/* 171:    */  }
/* 172:    */  
/* 175:    */  public float getNonDiagonal(JacobianEntry arg1, float arg2, float arg3)
/* 176:    */  {
/* 177:177 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();JacobianEntry jacA = this;
/* 178:    */      
/* 179:179 */      Vector3f lin = localStack.get$javax$vecmath$Vector3f();
/* 180:180 */      VectorUtil.mul(lin, jacA.linearJointAxis, jacB.linearJointAxis);
/* 181:    */      
/* 182:182 */      Vector3f ang0 = localStack.get$javax$vecmath$Vector3f();
/* 183:183 */      VectorUtil.mul(ang0, jacA.m_0MinvJt, jacB.aJ);
/* 184:    */      
/* 185:185 */      Vector3f ang1 = localStack.get$javax$vecmath$Vector3f();
/* 186:186 */      VectorUtil.mul(ang1, jacA.m_1MinvJt, jacB.bJ);
/* 187:    */      
/* 188:188 */      Vector3f lin0 = localStack.get$javax$vecmath$Vector3f();
/* 189:189 */      lin0.scale(massInvA, lin);
/* 190:    */      
/* 191:191 */      Vector3f lin1 = localStack.get$javax$vecmath$Vector3f();
/* 192:192 */      lin1.scale(massInvB, lin);
/* 193:    */      
/* 194:194 */      Vector3f sum = localStack.get$javax$vecmath$Vector3f();
/* 195:195 */      VectorUtil.add(sum, ang0, ang1, lin0, lin1);
/* 196:    */      
/* 197:197 */      return sum.x + sum.y + sum.z; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 198:    */    }
/* 199:    */  }
/* 200:    */  
/* 201:201 */  public float getRelativeVelocity(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f linrel = localStack.get$javax$vecmath$Vector3f();
/* 202:202 */      linrel.sub(linvelA, linvelB);
/* 203:    */      
/* 204:204 */      Vector3f angvela = localStack.get$javax$vecmath$Vector3f();
/* 205:205 */      VectorUtil.mul(angvela, angvelA, this.aJ);
/* 206:    */      
/* 207:207 */      Vector3f angvelb = localStack.get$javax$vecmath$Vector3f();
/* 208:208 */      VectorUtil.mul(angvelb, angvelB, this.bJ);
/* 209:    */      
/* 210:210 */      VectorUtil.mul(linrel, linrel, this.linearJointAxis);
/* 211:    */      
/* 212:212 */      angvela.add(angvelb);
/* 213:213 */      angvela.add(linrel);
/* 214:    */      
/* 215:215 */      float rel_vel2 = angvela.x + angvela.y + angvela.z;
/* 216:216 */      return rel_vel2 + 1.192093E-007F; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 217:    */    }
/* 218:    */  }
/* 219:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.JacobianEntry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */