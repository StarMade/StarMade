/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   4:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   5:    */import com.bulletphysics.linearmath.AabbUtil2;
/*   6:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   7:    */import jL;
/*   8:    */import java.io.PrintStream;
/*   9:    */import o;
/*  10:    */import org.schema.game.common.controller.SegmentController;
/*  11:    */import org.schema.game.common.data.world.Segment;
/*  12:    */import org.schema.game.common.data.world.SegmentData;
/*  13:    */import org.schema.schine.network.StateInterface;
/*  14:    */import q;
/*  15:    */import xO;
/*  16:    */
/*  17:    */public class CubeShape extends CollisionShape
/*  18:    */{
/*  19: 19 */  private float margin = 0.15F;
/*  20:    */  
/*  21:    */  private jL segmentBuffer;
/*  22:    */  
/*  23: 23 */  private javax.vecmath.Vector3f min = new javax.vecmath.Vector3f();
/*  24:    */  
/*  26: 26 */  private javax.vecmath.Vector3f max = new javax.vecmath.Vector3f();
/*  27:    */  
/*  28: 28 */  private javax.vecmath.Vector3f minCached = new javax.vecmath.Vector3f();
/*  29: 29 */  private javax.vecmath.Vector3f maxCached = new javax.vecmath.Vector3f();
/*  30:    */  
/*  32: 32 */  private com.bulletphysics.linearmath.Transform cachedTransform = new com.bulletphysics.linearmath.Transform();
/*  33: 33 */  private short cacheDate = -1;
/*  34:    */  private float cachedMargin;
/*  35: 35 */  private static javax.vecmath.Vector3f localScaling = new javax.vecmath.Vector3f(1.0F, 1.0F, 1.0F);
/*  36:    */  
/*  37: 37 */  public CubeShape(jL paramjL) { this.segmentBuffer = paramjL;
/*  38: 38 */    this.cachedTransform.setIdentity();
/*  39:    */  }
/*  40:    */  
/*  44:    */  public void calculateLocalInertia(float paramFloat, javax.vecmath.Vector3f paramVector3f)
/*  45:    */  {
/*  46: 46 */    float f1 = this.segmentBuffer.a().b.x - this.segmentBuffer.a().a.x + this.margin;
/*  47: 47 */    float f2 = this.segmentBuffer.a().b.y - this.segmentBuffer.a().a.y + this.margin;
/*  48: 48 */    float f3 = this.segmentBuffer.a().b.z - this.segmentBuffer.a().a.z + this.margin;
/*  49:    */    
/*  53: 53 */    paramVector3f.set(paramFloat / 3.0F * (f2 * f2 + f3 * f3), paramFloat / 3.0F * (f1 * f1 + f3 * f3), paramFloat / 3.0F * (f1 * f1 + f2 * f2));
/*  54:    */  }
/*  55:    */  
/*  57:    */  public void getAabb(com.bulletphysics.linearmath.Transform paramTransform, float paramFloat, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/*  58:    */  {
/*  59: 59 */    if ((paramFloat == this.cachedMargin) && (this.segmentBuffer.a().getState().getUpdateNumber() == this.cacheDate) && (paramTransform.equals(this.cachedTransform)))
/*  60:    */    {
/*  62: 62 */      paramVector3f1.set(this.minCached);
/*  63: 63 */      paramVector3f2.set(this.maxCached);return;
/*  64:    */    }
/*  65: 65 */    getAabbUncached(paramTransform, paramFloat, paramVector3f1, paramVector3f2);
/*  66:    */  }
/*  67:    */  
/*  68:    */  public void getAabbUncached(com.bulletphysics.linearmath.Transform paramTransform, float paramFloat, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/*  69:    */  {
/*  70: 70 */    if (!this.segmentBuffer.a().a())
/*  71:    */    {
/*  72: 72 */      this.min.set(0.0F, 0.0F, 0.0F);
/*  73: 73 */      this.max.set(0.0F, 0.0F, 0.0F);
/*  74:    */    } else {
/*  75: 75 */      this.min.set(this.segmentBuffer.a().a);
/*  76: 76 */      this.max.set(this.segmentBuffer.a().b);
/*  77:    */    }
/*  78: 78 */    if ((this.min.x > this.max.x) || (this.min.y > this.max.y) || (this.min.z > this.max.z)) {
/*  79: 79 */      System.err.println("[EXCEPTION] WARNING. physics cube AABB is corrupt: " + this.segmentBuffer.a());
/*  80:    */      
/*  81: 81 */      this.min.set(0.0F, 0.0F, 0.0F);
/*  82: 82 */      this.max.set(0.0F, 0.0F, 0.0F);
/*  83:    */    }
/*  84:    */    
/*  88: 88 */    AabbUtil2.transformAabb(this.min, this.max, paramFloat, paramTransform, paramVector3f1, paramVector3f2);
/*  89:    */    
/*  90: 90 */    this.minCached.set(paramVector3f1);
/*  91: 91 */    this.maxCached.set(paramVector3f2);
/*  92: 92 */    this.cachedTransform.set(paramTransform);
/*  93: 93 */    this.cachedMargin = paramFloat;
/*  94: 94 */    this.cacheDate = this.segmentBuffer.a().getState().getUpdateNumber();
/*  95:    */  }
/*  96:    */  
/*  99:    */  public void getAabb(com.bulletphysics.linearmath.Transform paramTransform, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/* 100:    */  {
/* 101:101 */    getAabb(paramTransform, this.margin, paramVector3f1, paramVector3f2);
/* 102:    */  }
/* 103:    */  
/* 106:    */  public javax.vecmath.Vector3f getLocalScaling(javax.vecmath.Vector3f paramVector3f)
/* 107:    */  {
/* 108:108 */    return localScaling;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public float getMargin()
/* 112:    */  {
/* 113:113 */    return this.margin;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public String getName()
/* 117:    */  {
/* 118:118 */    return "CUBES_MESH";
/* 119:    */  }
/* 120:    */  
/* 121:    */  public static void transformAabb(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat, com.bulletphysics.linearmath.Transform paramTransform, javax.vecmath.Vector3f paramVector3f3, javax.vecmath.Vector3f paramVector3f4, AABBVarSet paramAABBVarSet, javax.vecmath.Matrix3f paramMatrix3f)
/* 122:    */  {
/* 123:123 */    assert (paramVector3f1.x <= paramVector3f2.x);
/* 124:124 */    assert (paramVector3f1.y <= paramVector3f2.y);
/* 125:125 */    assert (paramVector3f1.z <= paramVector3f2.z);
/* 126:    */    
/* 127:    */    javax.vecmath.Vector3f localVector3f;
/* 128:128 */    (localVector3f = paramAABBVarSet.localHalfExtents).sub(paramVector3f2, paramVector3f1);
/* 129:129 */    localVector3f.scale(0.5F);
/* 130:    */    
/* 131:131 */    localVector3f.x += paramFloat;
/* 132:132 */    localVector3f.y += paramFloat;
/* 133:133 */    localVector3f.z += paramFloat;
/* 134:    */    
/* 135:135 */    (
/* 136:136 */      paramFloat = paramAABBVarSet.localCenter).add(paramVector3f2, paramVector3f1);
/* 137:137 */    paramFloat.scale(0.5F);
/* 138:    */    
/* 139:139 */    (
/* 140:140 */      paramVector3f1 = paramAABBVarSet.abs_b).set(paramMatrix3f);
/* 141:    */    
/* 144:144 */    (
/* 145:145 */      paramVector3f2 = paramAABBVarSet.center).set(paramFloat);
/* 146:146 */    paramTransform.transform(paramVector3f2);
/* 147:    */    
/* 159:159 */    (paramFloat = paramAABBVarSet.extent).x = (paramVector3f1.m00 * localVector3f.x + paramVector3f1.m01 * localVector3f.y + paramVector3f1.m02 * localVector3f.z);
/* 160:    */    
/* 163:163 */    paramFloat.y = (paramVector3f1.m10 * localVector3f.x + paramVector3f1.m11 * localVector3f.y + paramVector3f1.m12 * localVector3f.z);
/* 164:    */    
/* 168:168 */    paramFloat.z = (paramVector3f1.m20 * localVector3f.x + paramVector3f1.m21 * localVector3f.y + paramVector3f1.m22 * localVector3f.z);
/* 169:    */    
/* 171:171 */    paramVector3f3.sub(paramVector3f2, paramFloat);
/* 172:172 */    paramVector3f4.add(paramVector3f2, paramFloat);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public static void transformAabb(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, float paramFloat, com.bulletphysics.linearmath.Transform paramTransform, javax.vecmath.Vector3f paramVector3f3, javax.vecmath.Vector3f paramVector3f4, AABBVarSet paramAABBVarSet) {
/* 176:176 */    assert (paramVector3f1.x <= paramVector3f2.x);
/* 177:177 */    assert (paramVector3f1.y <= paramVector3f2.y);
/* 178:178 */    assert (paramVector3f1.z <= paramVector3f2.z);
/* 179:    */    
/* 180:    */    javax.vecmath.Vector3f localVector3f;
/* 181:181 */    (localVector3f = paramAABBVarSet.localHalfExtents).sub(paramVector3f2, paramVector3f1);
/* 182:182 */    localVector3f.scale(0.5F);
/* 183:    */    
/* 184:184 */    localVector3f.x += paramFloat;
/* 185:185 */    localVector3f.y += paramFloat;
/* 186:186 */    localVector3f.z += paramFloat;
/* 187:    */    
/* 188:188 */    (
/* 189:189 */      paramFloat = paramAABBVarSet.localCenter).add(paramVector3f2, paramVector3f1);
/* 190:190 */    paramFloat.scale(0.5F);
/* 191:    */    
/* 192:192 */    (
/* 193:193 */      paramVector3f1 = paramAABBVarSet.abs_b).set(paramTransform.basis);
/* 194:194 */    MatrixUtil.absolute(paramVector3f1);
/* 195:    */    
/* 196:196 */    (
/* 197:197 */      paramVector3f2 = paramAABBVarSet.center).set(paramFloat);
/* 198:198 */    paramTransform.transform(paramVector3f2);
/* 199:    */    
/* 211:211 */    (paramFloat = paramAABBVarSet.extent).x = (paramVector3f1.m00 * localVector3f.x + paramVector3f1.m01 * localVector3f.y + paramVector3f1.m02 * localVector3f.z);
/* 212:    */    
/* 215:215 */    paramFloat.y = (paramVector3f1.m10 * localVector3f.x + paramVector3f1.m11 * localVector3f.y + paramVector3f1.m12 * localVector3f.z);
/* 216:    */    
/* 220:220 */    paramFloat.z = (paramVector3f1.m20 * localVector3f.x + paramVector3f1.m21 * localVector3f.y + paramVector3f1.m22 * localVector3f.z);
/* 221:    */    
/* 222:222 */    paramVector3f3.sub(paramVector3f2, paramFloat);
/* 223:223 */    paramVector3f4.add(paramVector3f2, paramFloat);
/* 224:    */  }
/* 225:    */  
/* 226:    */  public void getSegmentAabb(Segment paramSegment, com.bulletphysics.linearmath.Transform paramTransform, javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2, javax.vecmath.Vector3f paramVector3f3, javax.vecmath.Vector3f paramVector3f4, AABBVarSet paramAABBVarSet) {
/* 227:    */    Object localObject;
/* 228:228 */    if (paramSegment.jdField_a_of_type_Short == this.segmentBuffer.a().getState().getUpdateNumber()) { float[] arrayOfFloat = paramSegment.jdField_a_of_type_ArrayOfFloat;localObject = paramTransform; if (((arrayOfFloat[0] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m00) && (arrayOfFloat[1] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m10) && (arrayOfFloat[2] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m20) && (arrayOfFloat[3] == 0.0F) && (arrayOfFloat[4] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m01) && (arrayOfFloat[5] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m11) && (arrayOfFloat[6] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m21) && (arrayOfFloat[7] == 0.0F) && (arrayOfFloat[8] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m02) && (arrayOfFloat[9] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m12) && (arrayOfFloat[10] == ((com.bulletphysics.linearmath.Transform)localObject).basis.m22) && (arrayOfFloat[11] == 0.0F) && (arrayOfFloat[12] == ((com.bulletphysics.linearmath.Transform)localObject).origin.x) && (arrayOfFloat[13] == ((com.bulletphysics.linearmath.Transform)localObject).origin.y) && (arrayOfFloat[14] == ((com.bulletphysics.linearmath.Transform)localObject).origin.z) && (arrayOfFloat[15] == 1.0F) ? 1 : 0) != 0)
/* 229:    */      {
/* 231:231 */        paramVector3f1.set(paramSegment.b);
/* 232:232 */        paramVector3f2.set(paramSegment.c);return;
/* 233:    */      } }
/* 234:234 */    if (!paramSegment.g())
/* 235:    */    {
/* 237:237 */      if ((localObject = paramSegment.a()).getMin().a <= ((SegmentData)localObject).getMax().a) {
/* 238:238 */        paramVector3f3.set(paramSegment.jdField_a_of_type_Q.a + ((SegmentData)localObject).getMin().a - 8 - 0.5F, paramSegment.jdField_a_of_type_Q.b + ((SegmentData)localObject).getMin().b - 8 - 0.5F, paramSegment.jdField_a_of_type_Q.c + ((SegmentData)localObject).getMin().c - 8 - 0.5F);
/* 239:    */        
/* 244:244 */        paramVector3f4.set(paramSegment.jdField_a_of_type_Q.a + ((SegmentData)localObject).getMax().a - 8 + 0.5F, paramSegment.jdField_a_of_type_Q.b + ((SegmentData)localObject).getMax().b - 8 + 0.5F, paramSegment.jdField_a_of_type_Q.c + ((SegmentData)localObject).getMax().c - 8 + 0.5F);
/* 245:    */        
/* 250:250 */        transformAabb(paramVector3f3, paramVector3f4, this.margin, paramTransform, paramVector3f1, paramVector3f2, paramAABBVarSet);
/* 252:    */      }
/* 253:    */      else
/* 254:    */      {
/* 255:255 */        System.err.println("[CUBESHAPE] " + ((SegmentData)localObject).getSegmentController().getState() + " WARNING: NON INIT SEGMENT DATA AABB REQUEST " + ((SegmentData)localObject).getMin() + "; " + ((SegmentData)localObject).getMax() + ": " + ((SegmentData)localObject).getSegmentController() + ": RESTRUCTING AABB");
/* 256:256 */        ((SegmentData)localObject).restructBB(true);
/* 257:257 */        paramSegment.a().getSegmentBuffer().a();
/* 258:258 */        paramVector3f2.set(0.0F, 0.0F, 0.0F);
/* 259:259 */        paramVector3f1.set(0.0F, 0.0F, 0.0F);
/* 260:    */      }
/* 261:    */    } else {
/* 262:262 */      System.err.println("[CUBESHAPE] EMPTY SEGMENT DATA AABB REQUEST");
/* 263:263 */      paramVector3f2.set(0.0F, 0.0F, 0.0F);
/* 264:264 */      paramVector3f1.set(0.0F, 0.0F, 0.0F);
/* 265:    */    }
/* 266:266 */    paramSegment.jdField_a_of_type_Short = this.segmentBuffer.a().getState().getUpdateNumber();
/* 267:267 */    paramTransform.getOpenGLMatrix(paramSegment.jdField_a_of_type_ArrayOfFloat);
/* 268:268 */    paramSegment.b[0] = paramVector3f1.x;
/* 269:269 */    paramSegment.b[1] = paramVector3f1.y;
/* 270:270 */    paramSegment.b[2] = paramVector3f1.z;
/* 271:    */    
/* 272:272 */    paramSegment.c[0] = paramVector3f2.x;
/* 273:273 */    paramSegment.c[1] = paramVector3f2.y;
/* 274:274 */    paramSegment.c[2] = paramVector3f2.z;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public jL getSegmentBuffer() {
/* 278:278 */    return this.segmentBuffer;
/* 279:    */  }
/* 280:    */  
/* 281:    */  public BroadphaseNativeType getShapeType()
/* 282:    */  {
/* 283:283 */    return BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE;
/* 284:    */  }
/* 285:    */  
/* 286:    */  public void setLocalScaling(javax.vecmath.Vector3f paramVector3f)
/* 287:    */  {
/* 288:288 */    localScaling.absolute(paramVector3f);
/* 289:    */  }
/* 290:    */  
/* 295:    */  public void setMargin(float paramFloat)
/* 296:    */  {
/* 297:297 */    this.margin = paramFloat;
/* 298:    */  }
/* 299:    */  
/* 300:    */  public void setSegmentBuffer(jL paramjL)
/* 301:    */  {
/* 302:302 */    this.segmentBuffer = paramjL;
/* 303:    */  }
/* 304:    */  
/* 305:    */  public String toString()
/* 306:    */  {
/* 307:307 */    return "[CubesShape" + (this.segmentBuffer.a().isOnServer() ? "|SER " : "|CLI ") + this.segmentBuffer.a() + "]";
/* 308:    */  }
/* 309:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */