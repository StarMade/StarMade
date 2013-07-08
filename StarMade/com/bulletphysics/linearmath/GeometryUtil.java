/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.util.ObjectArrayList;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import javax.vecmath.Vector4f;
/*   7:    */
/*  36:    */public class GeometryUtil
/*  37:    */{
/*  38:    */  public static boolean isPointInsidePlanes(ObjectArrayList<Vector4f> planeEquations, Vector3f point, float margin)
/*  39:    */  {
/*  40: 40 */    int numbrushes = planeEquations.size();
/*  41: 41 */    for (int i = 0; i < numbrushes; i++) {
/*  42: 42 */      Vector4f N1 = (Vector4f)planeEquations.getQuick(i);
/*  43: 43 */      float dist = VectorUtil.dot3(N1, point) + N1.w - margin;
/*  44: 44 */      if (dist > 0.0F) {
/*  45: 45 */        return false;
/*  46:    */      }
/*  47:    */    }
/*  48: 48 */    return true;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public static boolean areVerticesBehindPlane(Vector4f planeNormal, ObjectArrayList<Vector3f> vertices, float margin) {
/*  52: 52 */    int numvertices = vertices.size();
/*  53: 53 */    for (int i = 0; i < numvertices; i++) {
/*  54: 54 */      Vector3f N1 = (Vector3f)vertices.getQuick(i);
/*  55: 55 */      float dist = VectorUtil.dot3(planeNormal, N1) + planeNormal.w - margin;
/*  56: 56 */      if (dist > 0.0F) {
/*  57: 57 */        return false;
/*  58:    */      }
/*  59:    */    }
/*  60: 60 */    return true;
/*  61:    */  }
/*  62:    */  
/*  63:    */  private static boolean notExist(Vector4f planeEquation, ObjectArrayList<Vector4f> planeEquations) {
/*  64: 64 */    int numbrushes = planeEquations.size();
/*  65: 65 */    for (int i = 0; i < numbrushes; i++) {
/*  66: 66 */      Vector4f N1 = (Vector4f)planeEquations.getQuick(i);
/*  67: 67 */      if (VectorUtil.dot3(planeEquation, N1) > 0.999F) {
/*  68: 68 */        return false;
/*  69:    */      }
/*  70:    */    }
/*  71: 71 */    return true;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public static void getPlaneEquationsFromVertices(ObjectArrayList<Vector3f> arg0, ObjectArrayList<Vector4f> arg1) {
/*  75: 75 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Vector4f();Vector4f planeEquation = localStack.get$javax$vecmath$Vector4f();
/*  76: 76 */      Vector3f edge0 = localStack.get$javax$vecmath$Vector3f();Vector3f edge1 = localStack.get$javax$vecmath$Vector3f();
/*  77: 77 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  78:    */      
/*  79: 79 */      int numvertices = vertices.size();
/*  80:    */      
/*  81: 81 */      for (int i = 0; i < numvertices; i++) {
/*  82: 82 */        Vector3f N1 = (Vector3f)vertices.getQuick(i);
/*  83:    */        
/*  84: 84 */        for (int j = i + 1; j < numvertices; j++) {
/*  85: 85 */          Vector3f N2 = (Vector3f)vertices.getQuick(j);
/*  86:    */          
/*  87: 87 */          for (int k = j + 1; k < numvertices; k++) {
/*  88: 88 */            Vector3f N3 = (Vector3f)vertices.getQuick(k);
/*  89:    */            
/*  90: 90 */            edge0.sub(N2, N1);
/*  91: 91 */            edge1.sub(N3, N1);
/*  92: 92 */            float normalSign = 1.0F;
/*  93: 93 */            for (int ww = 0; ww < 2; ww++) {
/*  94: 94 */              tmp.cross(edge0, edge1);
/*  95: 95 */              planeEquation.x = (normalSign * tmp.x);
/*  96: 96 */              planeEquation.y = (normalSign * tmp.y);
/*  97: 97 */              planeEquation.z = (normalSign * tmp.z);
/*  98:    */              
/*  99: 99 */              if (VectorUtil.lengthSquared3(planeEquation) > 1.0E-004F) {
/* 100:100 */                VectorUtil.normalize3(planeEquation);
/* 101:101 */                if (notExist(planeEquation, planeEquationsOut)) {
/* 102:102 */                  planeEquation.w = (-VectorUtil.dot3(planeEquation, N1));
/* 103:    */                  
/* 105:105 */                  if (areVerticesBehindPlane(planeEquation, vertices, 0.01F)) {
/* 106:106 */                    planeEquationsOut.add(new Vector4f(planeEquation));
/* 107:    */                  }
/* 108:    */                }
/* 109:    */              }
/* 110:110 */              normalSign = -1.0F;
/* 111:    */            }
/* 112:    */          }
/* 113:    */        }
/* 114:    */      }
/* 115:115 */    } finally { .Stack tmp284_282 = localStack;tmp284_282.pop$javax$vecmath$Vector3f();tmp284_282.pop$javax$vecmath$Vector4f();
/* 116:    */    } }
/* 117:    */  
/* 118:118 */  public static void getVerticesFromPlaneEquations(ObjectArrayList<Vector4f> arg0, ObjectArrayList<Vector3f> arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f n2n3 = localStack.get$javax$vecmath$Vector3f();
/* 119:119 */      Vector3f n3n1 = localStack.get$javax$vecmath$Vector3f();
/* 120:120 */      Vector3f n1n2 = localStack.get$javax$vecmath$Vector3f();
/* 121:121 */      Vector3f potentialVertex = localStack.get$javax$vecmath$Vector3f();
/* 122:    */      
/* 123:123 */      int numbrushes = planeEquations.size();
/* 124:    */      
/* 125:125 */      for (int i = 0; i < numbrushes; i++) {
/* 126:126 */        Vector4f N1 = (Vector4f)planeEquations.getQuick(i);
/* 127:    */        
/* 128:128 */        for (int j = i + 1; j < numbrushes; j++) {
/* 129:129 */          Vector4f N2 = (Vector4f)planeEquations.getQuick(j);
/* 130:    */          
/* 131:131 */          for (int k = j + 1; k < numbrushes; k++) {
/* 132:132 */            Vector4f N3 = (Vector4f)planeEquations.getQuick(k);
/* 133:    */            
/* 134:134 */            VectorUtil.cross3(n2n3, N2, N3);
/* 135:135 */            VectorUtil.cross3(n3n1, N3, N1);
/* 136:136 */            VectorUtil.cross3(n1n2, N1, N2);
/* 137:    */            
/* 138:138 */            if ((n2n3.lengthSquared() > 1.0E-004F) && (n3n1.lengthSquared() > 1.0E-004F) && (n1n2.lengthSquared() > 1.0E-004F))
/* 139:    */            {
/* 147:147 */              float quotient = VectorUtil.dot3(N1, n2n3);
/* 148:148 */              if (Math.abs(quotient) > 1.0E-006F) {
/* 149:149 */                quotient = -1.0F / quotient;
/* 150:150 */                n2n3.scale(N1.w);
/* 151:151 */                n3n1.scale(N2.w);
/* 152:152 */                n1n2.scale(N3.w);
/* 153:153 */                potentialVertex.set(n2n3);
/* 154:154 */                potentialVertex.add(n3n1);
/* 155:155 */                potentialVertex.add(n1n2);
/* 156:156 */                potentialVertex.scale(quotient);
/* 157:    */                
/* 159:159 */                if (isPointInsidePlanes(planeEquations, potentialVertex, 0.01F))
/* 160:160 */                  verticesOut.add(new Vector3f(potentialVertex));
/* 161:    */              }
/* 162:    */            }
/* 163:    */          }
/* 164:    */        }
/* 165:    */      }
/* 166:    */    } finally {
/* 167:167 */      localStack.pop$javax$vecmath$Vector3f();
/* 168:    */    }
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.GeometryUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */