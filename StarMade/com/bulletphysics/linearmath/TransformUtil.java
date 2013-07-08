/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import javax.vecmath.Matrix3f;
/*   5:    */import javax.vecmath.Quat4f;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  37:    */public class TransformUtil
/*  38:    */{
/*  39:    */  public static final float SIMDSQRT12 = 0.7071068F;
/*  40:    */  public static final float ANGULAR_MOTION_THRESHOLD = 0.7853982F;
/*  41:    */  
/*  42:    */  public static float recipSqrt(float x)
/*  43:    */  {
/*  44: 44 */    return 1.0F / (float)Math.sqrt(x);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public static void planeSpace1(Vector3f n, Vector3f p, Vector3f q) {
/*  48: 48 */    if (Math.abs(n.z) > 0.7071068F)
/*  49:    */    {
/*  50: 50 */      float a = n.y * n.y + n.z * n.z;
/*  51: 51 */      float k = recipSqrt(a);
/*  52: 52 */      p.set(0.0F, -n.z * k, n.y * k);
/*  53:    */      
/*  54: 54 */      q.set(a * k, -n.x * p.z, n.x * p.y);
/*  55:    */    }
/*  56:    */    else
/*  57:    */    {
/*  58: 58 */      float a = n.x * n.x + n.y * n.y;
/*  59: 59 */      float k = recipSqrt(a);
/*  60: 60 */      p.set(-n.y * k, n.x * k, 0.0F);
/*  61:    */      
/*  62: 62 */      q.set(-n.z * p.y, n.z * p.x, a * k);
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  66:    */  public static void integrateTransform(Transform arg0, Vector3f arg1, Vector3f arg2, float arg3, Transform arg4)
/*  67:    */  {
/*  68: 68 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Quat4f();predictedTransform.origin.scaleAdd(timeStep, linvel, curTrans.origin);
/*  69:    */      
/*  78: 78 */      Vector3f axis = localStack.get$javax$vecmath$Vector3f();
/*  79: 79 */      float fAngle = angvel.length();
/*  80:    */      
/*  82: 82 */      if (fAngle * timeStep > 0.7853982F) {
/*  83: 83 */        fAngle = 0.7853982F / timeStep;
/*  84:    */      }
/*  85:    */      
/*  86: 86 */      if (fAngle < 0.001F)
/*  87:    */      {
/*  88: 88 */        axis.scale(0.5F * timeStep - timeStep * timeStep * timeStep * 0.02083333F * fAngle * fAngle, angvel);
/*  89:    */      }
/*  90:    */      else
/*  91:    */      {
/*  92: 92 */        axis.scale((float)Math.sin(0.5F * fAngle * timeStep) / fAngle, angvel);
/*  93:    */      }
/*  94: 94 */      Quat4f dorn = localStack.get$javax$vecmath$Quat4f();
/*  95: 95 */      dorn.set(axis.x, axis.y, axis.z, (float)Math.cos(fAngle * timeStep * 0.5F));
/*  96: 96 */      Quat4f orn0 = curTrans.getRotation(localStack.get$javax$vecmath$Quat4f());
/*  97:    */      
/*  98: 98 */      Quat4f predictedOrn = localStack.get$javax$vecmath$Quat4f();
/*  99: 99 */      predictedOrn.mul(dorn, orn0);
/* 100:100 */      predictedOrn.normalize();
/* 101:    */      
/* 102:102 */      predictedTransform.setRotation(predictedOrn);
/* 103:103 */    } finally { .Stack tmp204_202 = localStack;tmp204_202.pop$javax$vecmath$Vector3f();tmp204_202.pop$javax$vecmath$Quat4f();
/* 104:    */    } }
/* 105:    */  
/* 106:106 */  public static void calculateVelocity(Transform arg0, Transform arg1, float arg2, Vector3f arg3, Vector3f arg4) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();linVel.sub(transform1.origin, transform0.origin);
/* 107:107 */      linVel.scale(1.0F / timeStep);
/* 108:    */      
/* 109:109 */      Vector3f axis = localStack.get$javax$vecmath$Vector3f();
/* 110:110 */      float[] angle = new float[1];
/* 111:111 */      calculateDiffAxisAngle(transform0, transform1, axis, angle);
/* 112:112 */      angVel.scale(angle[0] / timeStep, axis);
/* 113:113 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 120:    */  public static void calculateDiffAxisAngle(Transform arg0, Transform arg1, Vector3f arg2, float[] arg3)
/* 121:    */  {
/* 122:122 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Quat4f();tmp7_5.push$javax$vecmath$Matrix3f();Matrix3f tmp = localStack.get$javax$vecmath$Matrix3f();
/* 123:123 */      tmp.set(transform0.basis);
/* 124:124 */      MatrixUtil.invert(tmp);
/* 125:    */      
/* 126:126 */      Matrix3f dmat = localStack.get$javax$vecmath$Matrix3f();
/* 127:127 */      dmat.mul(transform1.basis, tmp);
/* 128:    */      
/* 129:129 */      Quat4f dorn = localStack.get$javax$vecmath$Quat4f();
/* 130:130 */      MatrixUtil.getRotation(dmat, dorn);
/* 131:    */      
/* 135:135 */      dorn.normalize();
/* 136:    */      
/* 137:137 */      angle[0] = QuaternionUtil.getAngle(dorn);
/* 138:138 */      axis.set(dorn.x, dorn.y, dorn.z);
/* 139:    */      
/* 143:143 */      float len = axis.lengthSquared();
/* 144:144 */      if (len < 1.421086E-014F) {
/* 145:145 */        axis.set(1.0F, 0.0F, 0.0F);
/* 146:    */      }
/* 147:    */      else
/* 148:148 */        axis.scale(1.0F / (float)Math.sqrt(len));
/* 149:    */    } finally {
/* 150:150 */      .Stack tmp148_146 = localStack;tmp148_146.pop$javax$vecmath$Quat4f();tmp148_146.pop$javax$vecmath$Matrix3f();
/* 151:    */    }
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.TransformUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */