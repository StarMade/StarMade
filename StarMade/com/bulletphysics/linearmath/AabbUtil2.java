/*   1:    */package com.bulletphysics.linearmath;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import javax.vecmath.Matrix3f;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/*  34:    */public class AabbUtil2
/*  35:    */{
/*  36:    */  public static void aabbExpand(Vector3f aabbMin, Vector3f aabbMax, Vector3f expansionMin, Vector3f expansionMax)
/*  37:    */  {
/*  38: 38 */    aabbMin.add(expansionMin);
/*  39: 39 */    aabbMax.add(expansionMax);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public static int outcode(Vector3f p, Vector3f halfExtent) {
/*  43: 43 */    return (p.x < -halfExtent.x ? 1 : 0) | (p.x > halfExtent.x ? 8 : 0) | (p.y < -halfExtent.y ? 2 : 0) | (p.y > halfExtent.y ? 16 : 0) | (p.z < -halfExtent.z ? 4 : 0) | (p.z > halfExtent.z ? 32 : 0);
/*  44:    */  }
/*  45:    */  
/*  50:    */  public static boolean rayAabb(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, float[] arg4, Vector3f arg5)
/*  51:    */  {
/*  52: 52 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f aabbHalfExtent = localStack.get$javax$vecmath$Vector3f();
/*  53: 53 */      Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
/*  54: 54 */      Vector3f source = localStack.get$javax$vecmath$Vector3f();
/*  55: 55 */      Vector3f target = localStack.get$javax$vecmath$Vector3f();
/*  56: 56 */      Vector3f r = localStack.get$javax$vecmath$Vector3f();
/*  57: 57 */      Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/*  58:    */      
/*  59: 59 */      aabbHalfExtent.sub(aabbMax, aabbMin);
/*  60: 60 */      aabbHalfExtent.scale(0.5F);
/*  61:    */      
/*  62: 62 */      aabbCenter.add(aabbMax, aabbMin);
/*  63: 63 */      aabbCenter.scale(0.5F);
/*  64:    */      
/*  65: 65 */      source.sub(rayFrom, aabbCenter);
/*  66: 66 */      target.sub(rayTo, aabbCenter);
/*  67:    */      
/*  68: 68 */      int sourceOutcode = outcode(source, aabbHalfExtent);
/*  69: 69 */      int targetOutcode = outcode(target, aabbHalfExtent);
/*  70: 70 */      if ((sourceOutcode & targetOutcode) == 0) {
/*  71: 71 */        float lambda_enter = 0.0F;
/*  72: 72 */        float lambda_exit = param[0];
/*  73: 73 */        r.sub(target, source);
/*  74:    */        
/*  75: 75 */        float normSign = 1.0F;
/*  76: 76 */        hitNormal.set(0.0F, 0.0F, 0.0F);
/*  77: 77 */        int bit = 1;
/*  78:    */        
/*  79: 79 */        for (int j = 0; j < 2; j++) {
/*  80: 80 */          for (int i = 0; i != 3; i++) {
/*  81: 81 */            if ((sourceOutcode & bit) != 0) {
/*  82: 82 */              float lambda = (-VectorUtil.getCoord(source, i) - VectorUtil.getCoord(aabbHalfExtent, i) * normSign) / VectorUtil.getCoord(r, i);
/*  83: 83 */              if (lambda_enter <= lambda) {
/*  84: 84 */                lambda_enter = lambda;
/*  85: 85 */                hitNormal.set(0.0F, 0.0F, 0.0F);
/*  86: 86 */                VectorUtil.setCoord(hitNormal, i, normSign);
/*  87:    */              }
/*  88:    */            }
/*  89: 89 */            else if ((targetOutcode & bit) != 0) {
/*  90: 90 */              float lambda = (-VectorUtil.getCoord(source, i) - VectorUtil.getCoord(aabbHalfExtent, i) * normSign) / VectorUtil.getCoord(r, i);
/*  91:    */              
/*  92: 92 */              lambda_exit = Math.min(lambda_exit, lambda);
/*  93:    */            }
/*  94: 94 */            bit <<= 1;
/*  95:    */          }
/*  96: 96 */          normSign = -1.0F;
/*  97:    */        }
/*  98: 98 */        if (lambda_enter <= lambda_exit) {
/*  99: 99 */          param[0] = lambda_enter;
/* 100:100 */          normal.set(hitNormal);
/* 101:101 */          return true;
/* 102:    */        }
/* 103:    */      }
/* 104:104 */      return false; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 109:    */  public static boolean testAabbAgainstAabb2(Vector3f aabbMin1, Vector3f aabbMax1, Vector3f aabbMin2, Vector3f aabbMax2)
/* 110:    */  {
/* 111:111 */    boolean overlap = true;
/* 112:112 */    overlap = (aabbMin1.x > aabbMax2.x) || (aabbMax1.x < aabbMin2.x) ? false : overlap;
/* 113:113 */    overlap = (aabbMin1.z > aabbMax2.z) || (aabbMax1.z < aabbMin2.z) ? false : overlap;
/* 114:114 */    overlap = (aabbMin1.y > aabbMax2.y) || (aabbMax1.y < aabbMin2.y) ? false : overlap;
/* 115:115 */    return overlap;
/* 116:    */  }
/* 117:    */  
/* 120:    */  public static boolean testTriangleAgainstAabb2(Vector3f[] vertices, Vector3f aabbMin, Vector3f aabbMax)
/* 121:    */  {
/* 122:122 */    Vector3f p1 = vertices[0];
/* 123:123 */    Vector3f p2 = vertices[1];
/* 124:124 */    Vector3f p3 = vertices[2];
/* 125:    */    
/* 126:126 */    if (Math.min(Math.min(p1.x, p2.x), p3.x) > aabbMax.x) return false;
/* 127:127 */    if (Math.max(Math.max(p1.x, p2.x), p3.x) < aabbMin.x) { return false;
/* 128:    */    }
/* 129:129 */    if (Math.min(Math.min(p1.z, p2.z), p3.z) > aabbMax.z) return false;
/* 130:130 */    if (Math.max(Math.max(p1.z, p2.z), p3.z) < aabbMin.z) { return false;
/* 131:    */    }
/* 132:132 */    if (Math.min(Math.min(p1.y, p2.y), p3.y) > aabbMax.y) return false;
/* 133:133 */    if (Math.max(Math.max(p1.y, p2.y), p3.y) < aabbMin.y) { return false;
/* 134:    */    }
/* 135:135 */    return true;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public static void transformAabb(Vector3f arg0, float arg1, Transform arg2, Vector3f arg3, Vector3f arg4) {
/* 139:139 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f halfExtentsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 140:140 */      halfExtents.x += margin;
/* 141:141 */      halfExtents.y += margin;
/* 142:142 */      halfExtents.z += margin;
/* 143:    */      
/* 144:144 */      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(t.basis);
/* 145:145 */      MatrixUtil.absolute(abs_b);
/* 146:    */      
/* 147:147 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 148:    */      
/* 149:149 */      Vector3f center = localStack.get$javax$vecmath$Vector3f(t.origin);
/* 150:150 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 151:151 */      abs_b.getRow(0, tmp);
/* 152:152 */      extent.x = tmp.dot(halfExtentsWithMargin);
/* 153:153 */      abs_b.getRow(1, tmp);
/* 154:154 */      extent.y = tmp.dot(halfExtentsWithMargin);
/* 155:155 */      abs_b.getRow(2, tmp);
/* 156:156 */      extent.z = tmp.dot(halfExtentsWithMargin);
/* 157:    */      
/* 158:158 */      aabbMinOut.sub(center, extent);
/* 159:159 */      aabbMaxOut.add(center, extent);
/* 160:160 */    } finally { .Stack tmp186_184 = localStack;tmp186_184.pop$javax$vecmath$Vector3f();tmp186_184.pop$javax$vecmath$Matrix3f();
/* 161:    */    } }
/* 162:    */  
/* 163:163 */  public static void transformAabb(Vector3f arg0, Vector3f arg1, float arg2, Transform arg3, Vector3f arg4, Vector3f arg5) { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();assert (localAabbMin.x <= localAabbMax.x);
/* 164:164 */      assert (localAabbMin.y <= localAabbMax.y);
/* 165:165 */      assert (localAabbMin.z <= localAabbMax.z);
/* 166:    */      
/* 167:167 */      Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/* 168:168 */      localHalfExtents.sub(localAabbMax, localAabbMin);
/* 169:169 */      localHalfExtents.scale(0.5F);
/* 170:    */      
/* 171:171 */      localHalfExtents.x += margin;
/* 172:172 */      localHalfExtents.y += margin;
/* 173:173 */      localHalfExtents.z += margin;
/* 174:    */      
/* 175:175 */      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 176:176 */      localCenter.add(localAabbMax, localAabbMin);
/* 177:177 */      localCenter.scale(0.5F);
/* 178:    */      
/* 179:179 */      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 180:180 */      MatrixUtil.absolute(abs_b);
/* 181:    */      
/* 182:182 */      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 183:183 */      trans.transform(center);
/* 184:    */      
/* 185:185 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 186:186 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 187:    */      
/* 188:188 */      abs_b.getRow(0, tmp);
/* 189:189 */      extent.x = tmp.dot(localHalfExtents);
/* 190:190 */      abs_b.getRow(1, tmp);
/* 191:191 */      extent.y = tmp.dot(localHalfExtents);
/* 192:192 */      abs_b.getRow(2, tmp);
/* 193:193 */      extent.z = tmp.dot(localHalfExtents);
/* 194:    */      
/* 195:195 */      aabbMinOut.sub(center, extent);
/* 196:196 */      aabbMaxOut.add(center, extent);
/* 197:197 */    } finally { .Stack tmp304_302 = localStack;tmp304_302.pop$javax$vecmath$Vector3f();tmp304_302.pop$javax$vecmath$Matrix3f();
/* 198:    */    }
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.AabbUtil2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */