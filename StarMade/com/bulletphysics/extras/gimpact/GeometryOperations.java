/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.VectorUtil;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import javax.vecmath.Vector4f;
/*   7:    */
/*  39:    */class GeometryOperations
/*  40:    */{
/*  41:    */  public static final float PLANEDIREPSILON = 1.0E-007F;
/*  42:    */  public static final float PARALELENORMALS = 1.0E-006F;
/*  43:    */  
/*  44:    */  public static final float CLAMP(float number, float minval, float maxval)
/*  45:    */  {
/*  46: 46 */    return number > maxval ? maxval : number < minval ? minval : number;
/*  47:    */  }
/*  48:    */  
/*  51:    */  public static void edge_plane(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector4f arg3)
/*  52:    */  {
/*  53: 53 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f planenormal = localStack.get$javax$vecmath$Vector3f();
/*  54: 54 */      planenormal.sub(e2, e1);
/*  55: 55 */      planenormal.cross(planenormal, normal);
/*  56: 56 */      planenormal.normalize();
/*  57:    */      
/*  58: 58 */      plane.set(planenormal);
/*  59: 59 */      plane.w = e2.dot(planenormal);
/*  60: 60 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  64:    */  public static void closest_point_on_segment(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3)
/*  65:    */  {
/*  66: 66 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f n = localStack.get$javax$vecmath$Vector3f();
/*  67: 67 */      n.sub(e2, e1);
/*  68: 68 */      cp.sub(v, e1);
/*  69: 69 */      float _scalar = cp.dot(n) / n.dot(n);
/*  70: 70 */      if (_scalar < 0.0F) {
/*  71: 71 */        cp = e1;
/*  72:    */      }
/*  73: 73 */      else if (_scalar > 1.0F) {
/*  74: 74 */        cp = e2;
/*  75:    */      }
/*  76:    */      else
/*  77: 77 */        cp.scaleAdd(_scalar, n, e1);
/*  78:    */    } finally {
/*  79: 79 */      localStack.pop$javax$vecmath$Vector3f();
/*  80:    */    }
/*  81:    */  }
/*  82:    */  
/*  85:    */  public static int line_plane_collision(Vector4f plane, Vector3f vDir, Vector3f vPoint, Vector3f pout, float[] tparam, float tmin, float tmax)
/*  86:    */  {
/*  87: 87 */    float _dotdir = VectorUtil.dot3(vDir, plane);
/*  88:    */    
/*  89: 89 */    if (Math.abs(_dotdir) < 1.0E-007F) {
/*  90: 90 */      tparam[0] = tmax;
/*  91: 91 */      return 0;
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    float _dis = ClipPolygon.distance_point_plane(plane, vPoint);
/*  95: 95 */    int returnvalue = _dis < 0.0F ? 2 : 1;
/*  96: 96 */    tparam[0] = (-_dis / _dotdir);
/*  97:    */    
/*  98: 98 */    if (tparam[0] < tmin) {
/*  99: 99 */      returnvalue = 0;
/* 100:100 */      tparam[0] = tmin;
/* 101:    */    }
/* 102:102 */    else if (tparam[0] > tmax) {
/* 103:103 */      returnvalue = 0;
/* 104:104 */      tparam[0] = tmax;
/* 105:    */    }
/* 106:106 */    pout.scaleAdd(tparam[0], vDir, vPoint);
/* 107:107 */    return returnvalue;
/* 108:    */  }
/* 109:    */  
/* 112:    */  public static void segment_collision(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5)
/* 113:    */  {
/* 114:114 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Vector4f();Vector3f AD = localStack.get$javax$vecmath$Vector3f();
/* 115:115 */      AD.sub(vA2, vA1);
/* 116:    */      
/* 117:117 */      Vector3f BD = localStack.get$javax$vecmath$Vector3f();
/* 118:118 */      BD.sub(vB2, vB1);
/* 119:    */      
/* 120:120 */      Vector3f N = localStack.get$javax$vecmath$Vector3f();
/* 121:121 */      N.cross(AD, BD);
/* 122:122 */      float[] tp = { N.lengthSquared() };
/* 123:    */      
/* 124:124 */      Vector4f _M = localStack.get$javax$vecmath$Vector4f();
/* 125:    */      
/* 126:126 */      if (tp[0] < 1.192093E-007F)
/* 127:    */      {
/* 129:129 */        boolean invert_b_order = false;
/* 130:130 */        _M.x = vB1.dot(AD);
/* 131:131 */        _M.y = vB2.dot(AD);
/* 132:    */        
/* 133:133 */        if (_M.x > _M.y) {
/* 134:134 */          invert_b_order = true;
/* 135:    */          
/* 136:136 */          _M.x += _M.y;
/* 137:137 */          _M.y = (_M.x - _M.y);
/* 138:138 */          _M.x -= _M.y;
/* 139:    */        }
/* 140:140 */        _M.z = vA1.dot(AD);
/* 141:141 */        _M.w = vA2.dot(AD);
/* 142:    */        
/* 143:143 */        N.x = ((_M.x + _M.y) * 0.5F);
/* 144:144 */        N.y = ((_M.z + _M.w) * 0.5F);
/* 145:    */        
/* 146:146 */        if (N.x < N.y) {
/* 147:147 */          if (_M.y < _M.z) {
/* 148:148 */            vPointB = invert_b_order ? vB1 : vB2;
/* 149:149 */            vPointA = vA1;
/* 150:    */          }
/* 151:151 */          else if (_M.y < _M.w) {
/* 152:152 */            vPointB = invert_b_order ? vB1 : vB2;
/* 153:153 */            closest_point_on_segment(vPointA, vPointB, vA1, vA2);
/* 154:    */          }
/* 155:    */          else {
/* 156:156 */            vPointA = vA2;
/* 157:157 */            closest_point_on_segment(vPointB, vPointA, vB1, vB2);
/* 158:    */          }
/* 159:    */          
/* 160:    */        }
/* 161:161 */        else if (_M.w < _M.x) {
/* 162:162 */          vPointB = invert_b_order ? vB2 : vB1;
/* 163:163 */          vPointA = vA2;
/* 164:    */        }
/* 165:165 */        else if (_M.w < _M.y) {
/* 166:166 */          vPointA = vA2;
/* 167:167 */          closest_point_on_segment(vPointB, vPointA, vB1, vB2);
/* 168:    */        }
/* 169:    */        else {
/* 170:170 */          vPointB = invert_b_order ? vB1 : vB2;
/* 171:171 */          closest_point_on_segment(vPointA, vPointB, vA1, vA2);
/* 172:    */        }
/* 173:    */        
/* 174:174 */        return;
/* 175:    */      }
/* 176:    */      
/* 177:177 */      N.cross(N, BD);
/* 178:178 */      _M.set(N.x, N.y, N.z, vB1.dot(N));
/* 179:    */      
/* 181:181 */      line_plane_collision(_M, AD, vA1, vPointA, tp, 0.0F, 1.0F);
/* 182:    */      
/* 184:184 */      vPointB.sub(vPointA, vB1);
/* 185:185 */      tp[0] = vPointB.dot(BD);
/* 186:186 */      tp[0] /= BD.dot(BD);
/* 187:187 */      tp[0] = CLAMP(tp[0], 0.0F, 1.0F);
/* 188:    */      
/* 189:189 */      vPointB.scaleAdd(tp[0], BD, vB1);
/* 190:190 */    } finally { .Stack tmp549_547 = localStack;tmp549_547.pop$javax$vecmath$Vector3f();tmp549_547.pop$javax$vecmath$Vector4f();
/* 191:    */    }
/* 192:    */  }
/* 193:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GeometryOperations
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */