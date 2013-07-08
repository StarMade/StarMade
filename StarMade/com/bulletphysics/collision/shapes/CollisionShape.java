/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  40:    */public abstract class CollisionShape
/*  41:    */{
/*  42:    */  protected Object userPointer;
/*  43:    */  
/*  44:    */  public abstract void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2);
/*  45:    */  
/*  46:    */  public void getBoundingSphere(Vector3f arg1, float[] arg2)
/*  47:    */  {
/*  48: 48 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  49:    */      
/*  50: 50 */      Transform tr = localStack.get$com$bulletphysics$linearmath$Transform();
/*  51: 51 */      tr.setIdentity();
/*  52: 52 */      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  53:    */      
/*  54: 54 */      getAabb(tr, aabbMin, aabbMax);
/*  55:    */      
/*  56: 56 */      tmp.sub(aabbMax, aabbMin);
/*  57: 57 */      radius[0] = (tmp.length() * 0.5F);
/*  58:    */      
/*  59: 59 */      tmp.add(aabbMin, aabbMax);
/*  60: 60 */      center.scale(0.5F, tmp);
/*  61: 61 */    } finally { .Stack tmp101_99 = localStack;tmp101_99.pop$com$bulletphysics$linearmath$Transform();tmp101_99.pop$javax$vecmath$Vector3f();
/*  62:    */    }
/*  63:    */  }
/*  64:    */  
/*  65: 65 */  public float getAngularMotionDisc() { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  66: 66 */      float[] disc = new float[1];
/*  67: 67 */      getBoundingSphere(center, disc);
/*  68: 68 */      disc[0] += center.length();
/*  69: 69 */      return disc[0]; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  74:    */  public void calculateTemporalAabb(Transform arg1, Vector3f arg2, Vector3f arg3, float arg4, Vector3f arg5, Vector3f arg6)
/*  75:    */  {
/*  76: 76 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();getAabb(curTrans, temporalAabbMin, temporalAabbMax);
/*  77:    */      
/*  78: 78 */      float temporalAabbMaxx = temporalAabbMax.x;
/*  79: 79 */      float temporalAabbMaxy = temporalAabbMax.y;
/*  80: 80 */      float temporalAabbMaxz = temporalAabbMax.z;
/*  81: 81 */      float temporalAabbMinx = temporalAabbMin.x;
/*  82: 82 */      float temporalAabbMiny = temporalAabbMin.y;
/*  83: 83 */      float temporalAabbMinz = temporalAabbMin.z;
/*  84:    */      
/*  86: 86 */      Vector3f linMotion = localStack.get$javax$vecmath$Vector3f(linvel);
/*  87: 87 */      linMotion.scale(timeStep);
/*  88:    */      
/*  90: 90 */      if (linMotion.x > 0.0F) {
/*  91: 91 */        temporalAabbMaxx += linMotion.x;
/*  92:    */      }
/*  93:    */      else {
/*  94: 94 */        temporalAabbMinx += linMotion.x;
/*  95:    */      }
/*  96: 96 */      if (linMotion.y > 0.0F) {
/*  97: 97 */        temporalAabbMaxy += linMotion.y;
/*  98:    */      }
/*  99:    */      else {
/* 100:100 */        temporalAabbMiny += linMotion.y;
/* 101:    */      }
/* 102:102 */      if (linMotion.z > 0.0F) {
/* 103:103 */        temporalAabbMaxz += linMotion.z;
/* 104:    */      }
/* 105:    */      else {
/* 106:106 */        temporalAabbMinz += linMotion.z;
/* 107:    */      }
/* 108:    */      
/* 110:110 */      float angularMotion = angvel.length() * getAngularMotionDisc() * timeStep;
/* 111:111 */      Vector3f angularMotion3d = localStack.get$javax$vecmath$Vector3f();
/* 112:112 */      angularMotion3d.set(angularMotion, angularMotion, angularMotion);
/* 113:113 */      temporalAabbMin.set(temporalAabbMinx, temporalAabbMiny, temporalAabbMinz);
/* 114:114 */      temporalAabbMax.set(temporalAabbMaxx, temporalAabbMaxy, temporalAabbMaxz);
/* 115:    */      
/* 116:116 */      temporalAabbMin.sub(angularMotion3d);
/* 117:117 */      temporalAabbMax.add(angularMotion3d);
/* 118:118 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 119:    */    }
/* 120:    */  }
/* 121:    */  
/* 122:122 */  public boolean isPolyhedral() { return getShapeType().isPolyhedral(); }
/* 123:    */  
/* 124:    */  public boolean isConvex()
/* 125:    */  {
/* 126:126 */    return getShapeType().isConvex();
/* 127:    */  }
/* 128:    */  
/* 129:    */  public boolean isConcave() {
/* 130:130 */    return getShapeType().isConcave();
/* 131:    */  }
/* 132:    */  
/* 133:    */  public boolean isCompound() {
/* 134:134 */    return getShapeType().isCompound();
/* 135:    */  }
/* 136:    */  
/* 137:    */  public boolean isInfinite()
/* 138:    */  {
/* 139:139 */    return getShapeType().isInfinite();
/* 140:    */  }
/* 141:    */  
/* 143:    */  public abstract BroadphaseNativeType getShapeType();
/* 144:    */  
/* 146:    */  public abstract void setLocalScaling(Vector3f paramVector3f);
/* 147:    */  
/* 149:    */  public abstract Vector3f getLocalScaling(Vector3f paramVector3f);
/* 150:    */  
/* 151:    */  public abstract void calculateLocalInertia(float paramFloat, Vector3f paramVector3f);
/* 152:    */  
/* 153:    */  public abstract String getName();
/* 154:    */  
/* 155:    */  public abstract void setMargin(float paramFloat);
/* 156:    */  
/* 157:    */  public abstract float getMargin();
/* 158:    */  
/* 159:    */  public void setUserPointer(Object userPtr)
/* 160:    */  {
/* 161:161 */    this.userPointer = userPtr;
/* 162:    */  }
/* 163:    */  
/* 164:    */  public Object getUserPointer() {
/* 165:165 */    return this.userPointer;
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CollisionShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */