/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   6:    */import com.bulletphysics.linearmath.ScalarUtil;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import com.bulletphysics.linearmath.VectorUtil;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import javax.vecmath.Vector4f;
/*  11:    */
/*  42:    */public class BoxShape
/*  43:    */  extends PolyhedralConvexShape
/*  44:    */{
/*  45:    */  public BoxShape(Vector3f boxHalfExtents)
/*  46:    */  {
/*  47: 47 */    Vector3f margin = new Vector3f(getMargin(), getMargin(), getMargin());
/*  48: 48 */    VectorUtil.mul(this.implicitShapeDimensions, boxHalfExtents, this.localScaling);
/*  49: 49 */    this.implicitShapeDimensions.sub(margin);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public Vector3f getHalfExtentsWithMargin(Vector3f arg1) {
/*  53: 53 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
/*  54: 54 */      Vector3f margin = localStack.get$javax$vecmath$Vector3f();
/*  55: 55 */      margin.set(getMargin(), getMargin(), getMargin());
/*  56: 56 */      halfExtents.add(margin);
/*  57: 57 */      return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/*  58:    */    }
/*  59:    */  }
/*  60:    */  
/*  61: 61 */  public Vector3f getHalfExtentsWithoutMargin(Vector3f out) { out.set(this.implicitShapeDimensions);
/*  62: 62 */    return out;
/*  63:    */  }
/*  64:    */  
/*  65:    */  public BroadphaseNativeType getShapeType()
/*  66:    */  {
/*  67: 67 */    return BroadphaseNativeType.BOX_SHAPE_PROXYTYPE;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public Vector3f localGetSupportingVertex(Vector3f vec, Vector3f out)
/*  71:    */  {
/*  72: 72 */    Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
/*  73:    */    
/*  74: 74 */    float margin = getMargin();
/*  75: 75 */    halfExtents.x += margin;
/*  76: 76 */    halfExtents.y += margin;
/*  77: 77 */    halfExtents.z += margin;
/*  78:    */    
/*  79: 79 */    out.set(ScalarUtil.fsel(vec.x, halfExtents.x, -halfExtents.x), ScalarUtil.fsel(vec.y, halfExtents.y, -halfExtents.y), ScalarUtil.fsel(vec.z, halfExtents.z, -halfExtents.z));
/*  80:    */    
/*  83: 83 */    return out;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*  87:    */  {
/*  88: 88 */    Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
/*  89:    */    
/*  90: 90 */    out.set(ScalarUtil.fsel(vec.x, halfExtents.x, -halfExtents.x), ScalarUtil.fsel(vec.y, halfExtents.y, -halfExtents.y), ScalarUtil.fsel(vec.z, halfExtents.z, -halfExtents.z));
/*  91:    */    
/*  94: 94 */    return out;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*  98:    */  {
/*  99: 99 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/* 100:    */      
/* 101:101 */      for (int i = 0; i < numVectors; i++) {
/* 102:102 */        Vector3f vec = vectors[i];
/* 103:103 */        supportVerticesOut[i].set(ScalarUtil.fsel(vec.x, halfExtents.x, -halfExtents.x), ScalarUtil.fsel(vec.y, halfExtents.y, -halfExtents.y), ScalarUtil.fsel(vec.z, halfExtents.z, -halfExtents.z));
/* 104:    */      }
/* 105:    */    }
/* 106:    */    finally {
/* 107:107 */      localStack.pop$javax$vecmath$Vector3f();
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:    */  public void setMargin(float arg1) {
/* 112:112 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f oldMargin = localStack.get$javax$vecmath$Vector3f();
/* 113:113 */      oldMargin.set(getMargin(), getMargin(), getMargin());
/* 114:114 */      Vector3f implicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 115:115 */      implicitShapeDimensionsWithMargin.add(this.implicitShapeDimensions, oldMargin);
/* 116:    */      
/* 117:117 */      super.setMargin(margin);
/* 118:118 */      Vector3f newMargin = localStack.get$javax$vecmath$Vector3f();
/* 119:119 */      newMargin.set(getMargin(), getMargin(), getMargin());
/* 120:120 */      this.implicitShapeDimensions.sub(implicitShapeDimensionsWithMargin, newMargin);
/* 121:121 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 122:    */    }
/* 123:    */  }
/* 124:    */  
/* 125:125 */  public void setLocalScaling(Vector3f arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f oldMargin = localStack.get$javax$vecmath$Vector3f();
/* 126:126 */      oldMargin.set(getMargin(), getMargin(), getMargin());
/* 127:127 */      Vector3f implicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 128:128 */      implicitShapeDimensionsWithMargin.add(this.implicitShapeDimensions, oldMargin);
/* 129:129 */      Vector3f unScaledImplicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 130:130 */      VectorUtil.div(unScaledImplicitShapeDimensionsWithMargin, implicitShapeDimensionsWithMargin, this.localScaling);
/* 131:    */      
/* 132:132 */      super.setLocalScaling(scaling);
/* 133:    */      
/* 134:134 */      VectorUtil.mul(this.implicitShapeDimensions, unScaledImplicitShapeDimensionsWithMargin, this.localScaling);
/* 135:135 */      this.implicitShapeDimensions.sub(oldMargin);
/* 136:136 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 140:140 */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();AabbUtil2.transformAabb(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), getMargin(), t, aabbMin, aabbMax);
/* 141:141 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 145:    */  public void calculateLocalInertia(float arg1, Vector3f arg2) {
/* 146:146 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f());
/* 147:    */      
/* 148:148 */      float lx = 2.0F * halfExtents.x;
/* 149:149 */      float ly = 2.0F * halfExtents.y;
/* 150:150 */      float lz = 2.0F * halfExtents.z;
/* 151:    */      
/* 152:152 */      inertia.set(mass / 12.0F * (ly * ly + lz * lz), mass / 12.0F * (lx * lx + lz * lz), mass / 12.0F * (lx * lx + ly * ly));
/* 153:    */    }
/* 154:    */    finally {
/* 155:155 */      localStack.pop$javax$vecmath$Vector3f();
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 159:    */  public void getPlane(Vector3f arg1, Vector3f arg2, int arg3) {
/* 160:160 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Vector4f();Vector4f plane = localStack.get$javax$vecmath$Vector4f();
/* 161:161 */      getPlaneEquation(plane, i);
/* 162:162 */      planeNormal.set(plane.x, plane.y, plane.z);
/* 163:163 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 164:164 */      tmp.negate(planeNormal);
/* 165:165 */      localGetSupportingVertex(tmp, planeSupport);
/* 166:166 */    } finally { .Stack tmp80_78 = localStack;tmp80_78.pop$javax$vecmath$Vector3f();tmp80_78.pop$javax$vecmath$Vector4f();
/* 167:    */    }
/* 168:    */  }
/* 169:    */  
/* 170:170 */  public int getNumPlanes() { return 6; }
/* 171:    */  
/* 173:    */  public int getNumVertices()
/* 174:    */  {
/* 175:175 */    return 8;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public int getNumEdges()
/* 179:    */  {
/* 180:180 */    return 12;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void getVertex(int arg1, Vector3f arg2)
/* 184:    */  {
/* 185:185 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/* 186:    */      
/* 187:187 */      vtx.set(halfExtents.x * (1 - (i & 0x1)) - halfExtents.x * (i & 0x1), halfExtents.y * (1 - ((i & 0x2) >> 1)) - halfExtents.y * ((i & 0x2) >> 1), halfExtents.z * (1 - ((i & 0x4) >> 2)) - halfExtents.z * ((i & 0x4) >> 2));
/* 188:    */    }
/* 189:    */    finally {
/* 190:190 */      localStack.pop$javax$vecmath$Vector3f();
/* 191:    */    } }
/* 192:    */  
/* 193:193 */  public void getPlaneEquation(Vector4f arg1, int arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/* 194:    */      
/* 195:195 */      switch (i) {
/* 196:    */      case 0: 
/* 197:197 */        plane.set(1.0F, 0.0F, 0.0F, -halfExtents.x);
/* 198:198 */        break;
/* 199:    */      case 1: 
/* 200:200 */        plane.set(-1.0F, 0.0F, 0.0F, -halfExtents.x);
/* 201:201 */        break;
/* 202:    */      case 2: 
/* 203:203 */        plane.set(0.0F, 1.0F, 0.0F, -halfExtents.y);
/* 204:204 */        break;
/* 205:    */      case 3: 
/* 206:206 */        plane.set(0.0F, -1.0F, 0.0F, -halfExtents.y);
/* 207:207 */        break;
/* 208:    */      case 4: 
/* 209:209 */        plane.set(0.0F, 0.0F, 1.0F, -halfExtents.z);
/* 210:210 */        break;
/* 211:    */      case 5: 
/* 212:212 */        plane.set(0.0F, 0.0F, -1.0F, -halfExtents.z);
/* 213:213 */        break;
/* 214:    */      default: 
/* 215:215 */        if (!$assertionsDisabled) throw new AssertionError();
/* 216:    */        break; }
/* 217:217 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 221:221 */  public void getEdge(int i, Vector3f pa, Vector3f pb) { int edgeVert0 = 0;
/* 222:222 */    int edgeVert1 = 0;
/* 223:    */    
/* 224:224 */    switch (i) {
/* 225:    */    case 0: 
/* 226:226 */      edgeVert0 = 0;
/* 227:227 */      edgeVert1 = 1;
/* 228:228 */      break;
/* 229:    */    case 1: 
/* 230:230 */      edgeVert0 = 0;
/* 231:231 */      edgeVert1 = 2;
/* 232:232 */      break;
/* 233:    */    case 2: 
/* 234:234 */      edgeVert0 = 1;
/* 235:235 */      edgeVert1 = 3;
/* 236:    */      
/* 237:237 */      break;
/* 238:    */    case 3: 
/* 239:239 */      edgeVert0 = 2;
/* 240:240 */      edgeVert1 = 3;
/* 241:241 */      break;
/* 242:    */    case 4: 
/* 243:243 */      edgeVert0 = 0;
/* 244:244 */      edgeVert1 = 4;
/* 245:245 */      break;
/* 246:    */    case 5: 
/* 247:247 */      edgeVert0 = 1;
/* 248:248 */      edgeVert1 = 5;
/* 249:    */      
/* 250:250 */      break;
/* 251:    */    case 6: 
/* 252:252 */      edgeVert0 = 2;
/* 253:253 */      edgeVert1 = 6;
/* 254:254 */      break;
/* 255:    */    case 7: 
/* 256:256 */      edgeVert0 = 3;
/* 257:257 */      edgeVert1 = 7;
/* 258:258 */      break;
/* 259:    */    case 8: 
/* 260:260 */      edgeVert0 = 4;
/* 261:261 */      edgeVert1 = 5;
/* 262:262 */      break;
/* 263:    */    case 9: 
/* 264:264 */      edgeVert0 = 4;
/* 265:265 */      edgeVert1 = 6;
/* 266:266 */      break;
/* 267:    */    case 10: 
/* 268:268 */      edgeVert0 = 5;
/* 269:269 */      edgeVert1 = 7;
/* 270:270 */      break;
/* 271:    */    case 11: 
/* 272:272 */      edgeVert0 = 6;
/* 273:273 */      edgeVert1 = 7;
/* 274:274 */      break;
/* 275:    */    default: 
/* 276:276 */      if (!$assertionsDisabled) throw new AssertionError();
/* 277:    */      break;
/* 278:    */    }
/* 279:279 */    getVertex(edgeVert0, pa);
/* 280:280 */    getVertex(edgeVert1, pb);
/* 281:    */  }
/* 282:    */  
/* 283:    */  public boolean isInside(Vector3f arg1, float arg2)
/* 284:    */  {
/* 285:285 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/* 286:    */      
/* 289:289 */      return (pt.x <= halfExtents.x + tolerance) && (pt.x >= -halfExtents.x - tolerance) && (pt.y <= halfExtents.y + tolerance) && (pt.y >= -halfExtents.y - tolerance) && (pt.z <= halfExtents.z + tolerance) && (pt.z >= -halfExtents.z - tolerance);
/* 292:    */    }
/* 293:    */    finally
/* 294:    */    {
/* 297:297 */      localStack.pop$javax$vecmath$Vector3f();
/* 298:    */    }
/* 299:    */  }
/* 300:    */  
/* 301:    */  public String getName() {
/* 302:302 */    return "Box";
/* 303:    */  }
/* 304:    */  
/* 305:    */  public int getNumPreferredPenetrationDirections()
/* 306:    */  {
/* 307:307 */    return 6;
/* 308:    */  }
/* 309:    */  
/* 310:    */  public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/* 311:    */  {
/* 312:312 */    switch (index) {
/* 313:    */    case 0: 
/* 314:314 */      penetrationVector.set(1.0F, 0.0F, 0.0F);
/* 315:315 */      break;
/* 316:    */    case 1: 
/* 317:317 */      penetrationVector.set(-1.0F, 0.0F, 0.0F);
/* 318:318 */      break;
/* 319:    */    case 2: 
/* 320:320 */      penetrationVector.set(0.0F, 1.0F, 0.0F);
/* 321:321 */      break;
/* 322:    */    case 3: 
/* 323:323 */      penetrationVector.set(0.0F, -1.0F, 0.0F);
/* 324:324 */      break;
/* 325:    */    case 4: 
/* 326:326 */      penetrationVector.set(0.0F, 0.0F, 1.0F);
/* 327:327 */      break;
/* 328:    */    case 5: 
/* 329:329 */      penetrationVector.set(0.0F, 0.0F, -1.0F);
/* 330:330 */      break;
/* 331:    */    default: 
/* 332:332 */      if (!$assertionsDisabled) throw new AssertionError();
/* 333:    */      break;
/* 334:    */    }
/* 335:    */  }
/* 336:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BoxShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */