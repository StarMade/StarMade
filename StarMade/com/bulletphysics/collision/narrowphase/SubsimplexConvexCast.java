/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.linearmath.VectorUtil;
/*   8:    */import javax.vecmath.Vector3f;
/*   9:    */
/*  51:    */public class SubsimplexConvexCast
/*  52:    */  extends ConvexCast
/*  53:    */{
/*  54:    */  private static final int MAX_ITERATIONS = 32;
/*  55:    */  private SimplexSolverInterface simplexSolver;
/*  56:    */  private ConvexShape convexA;
/*  57:    */  private ConvexShape convexB;
/*  58:    */  
/*  59:    */  public SubsimplexConvexCast(ConvexShape shapeA, ConvexShape shapeB, SimplexSolverInterface simplexSolver)
/*  60:    */  {
/*  61: 61 */    this.convexA = shapeA;
/*  62: 62 */    this.convexB = shapeB;
/*  63: 63 */    this.simplexSolver = simplexSolver;
/*  64:    */  }
/*  65:    */  
/*  66:    */  public boolean calcTimeOfImpact(Transform arg1, Transform arg2, Transform arg3, Transform arg4, ConvexCast.CastResult arg5) {
/*  67: 67 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  68:    */      
/*  69: 69 */      this.simplexSolver.reset();
/*  70:    */      
/*  71: 71 */      Vector3f linVelA = localStack.get$javax$vecmath$Vector3f();
/*  72: 72 */      Vector3f linVelB = localStack.get$javax$vecmath$Vector3f();
/*  73: 73 */      linVelA.sub(toA.origin, fromA.origin);
/*  74: 74 */      linVelB.sub(toB.origin, fromB.origin);
/*  75:    */      
/*  76: 76 */      float lambda = 0.0F;
/*  77:    */      
/*  78: 78 */      Transform interpolatedTransA = localStack.get$com$bulletphysics$linearmath$Transform(fromA);
/*  79: 79 */      Transform interpolatedTransB = localStack.get$com$bulletphysics$linearmath$Transform(fromB);
/*  80:    */      
/*  82: 82 */      Vector3f r = localStack.get$javax$vecmath$Vector3f();
/*  83: 83 */      r.sub(linVelA, linVelB);
/*  84:    */      
/*  85: 85 */      Vector3f v = localStack.get$javax$vecmath$Vector3f();
/*  86:    */      
/*  87: 87 */      tmp.negate(r);
/*  88: 88 */      MatrixUtil.transposeTransform(tmp, tmp, fromA.basis);
/*  89: 89 */      Vector3f supVertexA = this.convexA.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
/*  90: 90 */      fromA.transform(supVertexA);
/*  91:    */      
/*  92: 92 */      MatrixUtil.transposeTransform(tmp, r, fromB.basis);
/*  93: 93 */      Vector3f supVertexB = this.convexB.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
/*  94: 94 */      fromB.transform(supVertexB);
/*  95:    */      
/*  96: 96 */      v.sub(supVertexA, supVertexB);
/*  97:    */      
/*  98: 98 */      int maxIter = 32;
/*  99:    */      
/* 100:100 */      Vector3f n = localStack.get$javax$vecmath$Vector3f();
/* 101:101 */      n.set(0.0F, 0.0F, 0.0F);
/* 102:102 */      boolean hasResult = false;
/* 103:103 */      Vector3f c = localStack.get$javax$vecmath$Vector3f();
/* 104:    */      
/* 105:105 */      float lastLambda = lambda;
/* 106:    */      
/* 107:107 */      float dist2 = v.lengthSquared();
/* 108:    */      
/* 111:111 */      float epsilon = 1.0E-004F;
/* 112:    */      
/* 113:113 */      Vector3f w = localStack.get$javax$vecmath$Vector3f();Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 114:    */      
/* 116:116 */      while ((dist2 > epsilon) && (maxIter-- != 0)) {
/* 117:117 */        tmp.negate(v);
/* 118:118 */        MatrixUtil.transposeTransform(tmp, tmp, interpolatedTransA.basis);
/* 119:119 */        this.convexA.localGetSupportingVertex(tmp, supVertexA);
/* 120:120 */        interpolatedTransA.transform(supVertexA);
/* 121:    */        
/* 122:122 */        MatrixUtil.transposeTransform(tmp, v, interpolatedTransB.basis);
/* 123:123 */        this.convexB.localGetSupportingVertex(tmp, supVertexB);
/* 124:124 */        interpolatedTransB.transform(supVertexB);
/* 125:    */        
/* 126:126 */        w.sub(supVertexA, supVertexB);
/* 127:    */        
/* 128:128 */        float VdotW = v.dot(w);
/* 129:    */        
/* 130:130 */        if (lambda > 1.0F) {
/* 131:131 */          return false;
/* 132:    */        }
/* 133:    */        
/* 134:134 */        if (VdotW > 0.0F) {
/* 135:135 */          float VdotR = v.dot(r);
/* 136:    */          
/* 137:137 */          if (VdotR >= -1.421086E-014F) {
/* 138:138 */            return false;
/* 139:    */          }
/* 140:    */          
/* 141:141 */          lambda -= VdotW / VdotR;
/* 142:    */          
/* 145:145 */          VectorUtil.setInterpolate3(interpolatedTransA.origin, fromA.origin, toA.origin, lambda);
/* 146:146 */          VectorUtil.setInterpolate3(interpolatedTransB.origin, fromB.origin, toB.origin, lambda);
/* 147:    */          
/* 149:149 */          w.sub(supVertexA, supVertexB);
/* 150:150 */          lastLambda = lambda;
/* 151:151 */          n.set(v);
/* 152:152 */          hasResult = true;
/* 153:    */        }
/* 154:    */        
/* 155:155 */        this.simplexSolver.addVertex(w, supVertexA, supVertexB);
/* 156:156 */        if (this.simplexSolver.closest(v)) {
/* 157:157 */          dist2 = v.lengthSquared();
/* 158:158 */          hasResult = true;
/* 161:    */        }
/* 162:    */        else
/* 163:    */        {
/* 166:166 */          dist2 = 0.0F;
/* 167:    */        }
/* 168:    */      }
/* 169:    */      
/* 175:175 */      result.fraction = lambda;
/* 176:176 */      if (n.lengthSquared() >= 1.421086E-014F) {
/* 177:177 */        result.normal.normalize(n);
/* 178:    */      }
/* 179:    */      else {
/* 180:180 */        result.normal.set(0.0F, 0.0F, 0.0F);
/* 181:    */      }
/* 182:    */      
/* 184:184 */      if (result.normal.dot(r) >= -result.allowedPenetration) {
/* 185:185 */        return false;
/* 186:    */      }
/* 187:187 */      Vector3f hitA = localStack.get$javax$vecmath$Vector3f();
/* 188:188 */      Vector3f hitB = localStack.get$javax$vecmath$Vector3f();
/* 189:189 */      this.simplexSolver.compute_points(hitA, hitB);
/* 190:190 */      result.hitPoint.set(hitB);
/* 191:191 */      return true; } finally { .Stack tmp644_642 = localStack;tmp644_642.pop$com$bulletphysics$linearmath$Transform();tmp644_642.pop$javax$vecmath$Vector3f();
/* 192:    */    }
/* 193:    */  }
/* 194:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.SubsimplexConvexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */