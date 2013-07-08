/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.Transform;
/*   7:    */import com.bulletphysics.linearmath.VectorUtil;
/*   8:    */import com.bulletphysics.util.ObjectArrayList;
/*   9:    */import javax.vecmath.Matrix3f;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */
/*  42:    */public class CompoundShape
/*  43:    */  extends CollisionShape
/*  44:    */{
/*  45: 45 */  private final ObjectArrayList<CompoundShapeChild> children = new ObjectArrayList();
/*  46: 46 */  private final Vector3f localAabbMin = new Vector3f(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  47: 47 */  private final Vector3f localAabbMax = new Vector3f(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  48:    */  
/*  49: 49 */  private OptimizedBvh aabbTree = null;
/*  50:    */  
/*  51: 51 */  private float collisionMargin = 0.0F;
/*  52: 52 */  protected final Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
/*  53:    */  
/*  55:    */  public void addChildShape(Transform arg1, CollisionShape arg2)
/*  56:    */  {
/*  57: 57 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();CompoundShapeChild child = new CompoundShapeChild();
/*  58: 58 */      child.transform.set(localTransform);
/*  59: 59 */      child.childShape = shape;
/*  60: 60 */      child.childShapeType = shape.getShapeType();
/*  61: 61 */      child.childMargin = shape.getMargin();
/*  62:    */      
/*  63: 63 */      this.children.add(child);
/*  64:    */      
/*  66: 66 */      Vector3f _localAabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f _localAabbMax = localStack.get$javax$vecmath$Vector3f();
/*  67: 67 */      shape.getAabb(localTransform, _localAabbMin, _localAabbMax);
/*  68:    */      
/*  81: 81 */      VectorUtil.setMin(this.localAabbMin, _localAabbMin);
/*  82: 82 */      VectorUtil.setMax(this.localAabbMax, _localAabbMax);
/*  83: 83 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  84:    */    }
/*  85:    */  }
/*  86:    */  
/*  88:    */  public void removeChildShape(CollisionShape shape)
/*  89:    */  {
/*  90:    */    boolean done_removing;
/*  91:    */    do
/*  92:    */    {
/*  93: 93 */      done_removing = true;
/*  94:    */      
/*  95: 95 */      for (int i = 0; i < this.children.size(); i++) {
/*  96: 96 */        if (((CompoundShapeChild)this.children.getQuick(i)).childShape == shape) {
/*  97: 97 */          this.children.removeQuick(i);
/*  98: 98 */          done_removing = false;
/*  99: 99 */          break;
/* 100:    */        }
/* 101:    */        
/* 102:    */      }
/* 103:103 */    } while (!done_removing);
/* 104:    */    
/* 105:105 */    recalculateLocalAabb();
/* 106:    */  }
/* 107:    */  
/* 108:    */  public int getNumChildShapes() {
/* 109:109 */    return this.children.size();
/* 110:    */  }
/* 111:    */  
/* 112:    */  public CollisionShape getChildShape(int index) {
/* 113:113 */    return ((CompoundShapeChild)this.children.getQuick(index)).childShape;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public Transform getChildTransform(int index, Transform out) {
/* 117:117 */    out.set(((CompoundShapeChild)this.children.getQuick(index)).transform);
/* 118:118 */    return out;
/* 119:    */  }
/* 120:    */  
/* 121:    */  public ObjectArrayList<CompoundShapeChild> getChildList() {
/* 122:122 */    return this.children;
/* 123:    */  }
/* 124:    */  
/* 128:    */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/* 129:    */  {
/* 130:130 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/* 131:131 */      localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
/* 132:132 */      localHalfExtents.scale(0.5F);
/* 133:133 */      localHalfExtents.x += getMargin();
/* 134:134 */      localHalfExtents.y += getMargin();
/* 135:135 */      localHalfExtents.z += getMargin();
/* 136:    */      
/* 137:137 */      Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 138:138 */      localCenter.add(this.localAabbMax, this.localAabbMin);
/* 139:139 */      localCenter.scale(0.5F);
/* 140:    */      
/* 141:141 */      Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 142:142 */      MatrixUtil.absolute(abs_b);
/* 143:    */      
/* 144:144 */      Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 145:145 */      trans.transform(center);
/* 146:    */      
/* 147:147 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 148:    */      
/* 149:149 */      Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 150:150 */      abs_b.getRow(0, tmp);
/* 151:151 */      extent.x = tmp.dot(localHalfExtents);
/* 152:152 */      abs_b.getRow(1, tmp);
/* 153:153 */      extent.y = tmp.dot(localHalfExtents);
/* 154:154 */      abs_b.getRow(2, tmp);
/* 155:155 */      extent.z = tmp.dot(localHalfExtents);
/* 156:    */      
/* 157:157 */      aabbMin.sub(center, extent);
/* 158:158 */      aabbMax.add(center, extent);
/* 159:159 */    } finally { .Stack tmp245_243 = localStack;tmp245_243.pop$javax$vecmath$Vector3f();tmp245_243.pop$javax$vecmath$Matrix3f();
/* 160:    */    }
/* 161:    */  }
/* 162:    */  
/* 166:    */  public void recalculateLocalAabb()
/* 167:    */  {
/* 168:168 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();this.localAabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 169:169 */      this.localAabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/* 170:    */      
/* 171:171 */      Vector3f tmpLocalAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 172:172 */      Vector3f tmpLocalAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 173:    */      
/* 175:175 */      for (int j = 0; j < this.children.size(); j++) {
/* 176:176 */        ((CompoundShapeChild)this.children.getQuick(j)).childShape.getAabb(((CompoundShapeChild)this.children.getQuick(j)).transform, tmpLocalAabbMin, tmpLocalAabbMax);
/* 177:    */        
/* 178:178 */        for (int i = 0; i < 3; i++) {
/* 179:179 */          if (VectorUtil.getCoord(this.localAabbMin, i) > VectorUtil.getCoord(tmpLocalAabbMin, i)) {
/* 180:180 */            VectorUtil.setCoord(this.localAabbMin, i, VectorUtil.getCoord(tmpLocalAabbMin, i));
/* 181:    */          }
/* 182:182 */          if (VectorUtil.getCoord(this.localAabbMax, i) < VectorUtil.getCoord(tmpLocalAabbMax, i))
/* 183:183 */            VectorUtil.setCoord(this.localAabbMax, i, VectorUtil.getCoord(tmpLocalAabbMax, i));
/* 184:    */        }
/* 185:    */      }
/* 186:    */    } finally {
/* 187:187 */      localStack.pop$javax$vecmath$Vector3f();
/* 188:    */    }
/* 189:    */  }
/* 190:    */  
/* 191:191 */  public void setLocalScaling(Vector3f scaling) { this.localScaling.set(scaling); }
/* 192:    */  
/* 194:    */  public Vector3f getLocalScaling(Vector3f out)
/* 195:    */  {
/* 196:196 */    out.set(this.localScaling);
/* 197:197 */    return out;
/* 198:    */  }
/* 199:    */  
/* 201:    */  public void calculateLocalInertia(float arg1, Vector3f arg2)
/* 202:    */  {
/* 203:203 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/* 204:204 */      ident.setIdentity();
/* 205:205 */      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/* 206:206 */      getAabb(ident, aabbMin, aabbMax);
/* 207:    */      
/* 208:208 */      Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 209:209 */      halfExtents.sub(aabbMax, aabbMin);
/* 210:210 */      halfExtents.scale(0.5F);
/* 211:    */      
/* 212:212 */      float lx = 2.0F * halfExtents.x;
/* 213:213 */      float ly = 2.0F * halfExtents.y;
/* 214:214 */      float lz = 2.0F * halfExtents.z;
/* 215:    */      
/* 216:216 */      inertia.x = (mass / 12.0F * (ly * ly + lz * lz));
/* 217:217 */      inertia.y = (mass / 12.0F * (lx * lx + lz * lz));
/* 218:218 */      inertia.z = (mass / 12.0F * (lx * lx + ly * ly));
/* 219:219 */    } finally { .Stack tmp169_167 = localStack;tmp169_167.pop$com$bulletphysics$linearmath$Transform();tmp169_167.pop$javax$vecmath$Vector3f();
/* 220:    */    }
/* 221:    */  }
/* 222:    */  
/* 223:223 */  public BroadphaseNativeType getShapeType() { return BroadphaseNativeType.COMPOUND_SHAPE_PROXYTYPE; }
/* 224:    */  
/* 226:    */  public void setMargin(float margin)
/* 227:    */  {
/* 228:228 */    this.collisionMargin = margin;
/* 229:    */  }
/* 230:    */  
/* 231:    */  public float getMargin()
/* 232:    */  {
/* 233:233 */    return this.collisionMargin;
/* 234:    */  }
/* 235:    */  
/* 236:    */  public String getName()
/* 237:    */  {
/* 238:238 */    return "Compound";
/* 239:    */  }
/* 240:    */  
/* 243:    */  public OptimizedBvh getAabbTree()
/* 244:    */  {
/* 245:245 */    return this.aabbTree;
/* 246:    */  }
/* 247:    */  
/* 257:    */  public void calculatePrincipalAxisTransform(float[] arg1, Transform arg2, Vector3f arg3)
/* 258:    */  {
/* 259:259 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f();tmp7_5.push$javax$vecmath$Matrix3f();int n = this.children.size();
/* 260:    */      
/* 261:261 */      float totalMass = 0.0F;
/* 262:262 */      Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 263:263 */      center.set(0.0F, 0.0F, 0.0F);
/* 264:264 */      for (int k = 0; k < n; k++) {
/* 265:265 */        center.scaleAdd(masses[k], ((CompoundShapeChild)this.children.getQuick(k)).transform.origin, center);
/* 266:266 */        totalMass += masses[k];
/* 267:    */      }
/* 268:268 */      center.scale(1.0F / totalMass);
/* 269:269 */      principal.origin.set(center);
/* 270:    */      
/* 271:271 */      Matrix3f tensor = localStack.get$javax$vecmath$Matrix3f();
/* 272:272 */      tensor.setZero();
/* 273:    */      
/* 274:274 */      for (int k = 0; k < n; k++) {
/* 275:275 */        Vector3f i = localStack.get$javax$vecmath$Vector3f();
/* 276:276 */        ((CompoundShapeChild)this.children.getQuick(k)).childShape.calculateLocalInertia(masses[k], i);
/* 277:    */        
/* 278:278 */        Transform t = ((CompoundShapeChild)this.children.getQuick(k)).transform;
/* 279:279 */        Vector3f o = localStack.get$javax$vecmath$Vector3f();
/* 280:280 */        o.sub(t.origin, center);
/* 281:    */        
/* 283:283 */        Matrix3f j = localStack.get$javax$vecmath$Matrix3f();
/* 284:284 */        j.transpose(t.basis);
/* 285:    */        
/* 286:286 */        j.m00 *= i.x;
/* 287:287 */        j.m01 *= i.x;
/* 288:288 */        j.m02 *= i.x;
/* 289:289 */        j.m10 *= i.y;
/* 290:290 */        j.m11 *= i.y;
/* 291:291 */        j.m12 *= i.y;
/* 292:292 */        j.m20 *= i.z;
/* 293:293 */        j.m21 *= i.z;
/* 294:294 */        j.m22 *= i.z;
/* 295:    */        
/* 296:296 */        j.mul(t.basis, j);
/* 297:    */        
/* 299:299 */        tensor.add(j);
/* 300:    */        
/* 302:302 */        float o2 = o.lengthSquared();
/* 303:303 */        j.setRow(0, o2, 0.0F, 0.0F);
/* 304:304 */        j.setRow(1, 0.0F, o2, 0.0F);
/* 305:305 */        j.setRow(2, 0.0F, 0.0F, o2);
/* 306:306 */        j.m00 += o.x * -o.x;
/* 307:307 */        j.m01 += o.y * -o.x;
/* 308:308 */        j.m02 += o.z * -o.x;
/* 309:309 */        j.m10 += o.x * -o.y;
/* 310:310 */        j.m11 += o.y * -o.y;
/* 311:311 */        j.m12 += o.z * -o.y;
/* 312:312 */        j.m20 += o.x * -o.z;
/* 313:313 */        j.m21 += o.y * -o.z;
/* 314:314 */        j.m22 += o.z * -o.z;
/* 315:    */        
/* 317:317 */        tensor.m00 += masses[k] * j.m00;
/* 318:318 */        tensor.m01 += masses[k] * j.m01;
/* 319:319 */        tensor.m02 += masses[k] * j.m02;
/* 320:320 */        tensor.m10 += masses[k] * j.m10;
/* 321:321 */        tensor.m11 += masses[k] * j.m11;
/* 322:322 */        tensor.m12 += masses[k] * j.m12;
/* 323:323 */        tensor.m20 += masses[k] * j.m20;
/* 324:324 */        tensor.m21 += masses[k] * j.m21;
/* 325:325 */        tensor.m22 += masses[k] * j.m22;
/* 326:    */      }
/* 327:    */      
/* 328:328 */      MatrixUtil.diagonalize(tensor, principal.basis, 1.0E-005F, 20);
/* 329:    */      
/* 330:330 */      inertia.set(tensor.m00, tensor.m11, tensor.m22);
/* 331:331 */    } finally { .Stack tmp839_837 = localStack;tmp839_837.pop$javax$vecmath$Vector3f();tmp839_837.pop$javax$vecmath$Matrix3f();
/* 332:    */    }
/* 333:    */  }
/* 334:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CompoundShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */