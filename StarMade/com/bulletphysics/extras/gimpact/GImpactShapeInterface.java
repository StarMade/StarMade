/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
/*   6:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   7:    */import com.bulletphysics.collision.shapes.ConcaveShape;
/*   8:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*   9:    */import com.bulletphysics.linearmath.Transform;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */
/*  44:    */public abstract class GImpactShapeInterface
/*  45:    */  extends ConcaveShape
/*  46:    */{
/*  47: 47 */  protected BoxCollision.AABB localAABB = new BoxCollision.AABB();
/*  48:    */  protected boolean needs_update;
/*  49: 49 */  protected final Vector3f localScaling = new Vector3f();
/*  50: 50 */  GImpactBvh box_set = new GImpactBvh();
/*  51:    */  
/*  52:    */  public GImpactShapeInterface() {
/*  53: 53 */    this.localAABB.invalidate();
/*  54: 54 */    this.needs_update = true;
/*  55: 55 */    this.localScaling.set(1.0F, 1.0F, 1.0F);
/*  56:    */  }
/*  57:    */  
/*  66:    */  public void updateBound()
/*  67:    */  {
/*  68: 68 */    if (!this.needs_update) {
/*  69: 69 */      return;
/*  70:    */    }
/*  71: 71 */    calcLocalAABB();
/*  72: 72 */    this.needs_update = false;
/*  73:    */  }
/*  74:    */  
/*  79:    */  public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*  80:    */  {
/*  81: 81 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB transformedbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(this.localAABB);
/*  82: 82 */      transformedbox.appy_transform(t);
/*  83: 83 */      aabbMin.set(transformedbox.min);
/*  84: 84 */      aabbMax.set(transformedbox.max);
/*  85: 85 */    } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void postUpdate()
/*  90:    */  {
/*  91: 91 */    this.needs_update = true;
/*  92:    */  }
/*  93:    */  
/*  96:    */  public BoxCollision.AABB getLocalBox(BoxCollision.AABB out)
/*  97:    */  {
/*  98: 98 */    out.set(this.localAABB);
/*  99: 99 */    return out;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public BroadphaseNativeType getShapeType()
/* 103:    */  {
/* 104:104 */    return BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE;
/* 105:    */  }
/* 106:    */  
/* 110:    */  public void setLocalScaling(Vector3f scaling)
/* 111:    */  {
/* 112:112 */    this.localScaling.set(scaling);
/* 113:113 */    postUpdate();
/* 114:    */  }
/* 115:    */  
/* 116:    */  public Vector3f getLocalScaling(Vector3f out)
/* 117:    */  {
/* 118:118 */    out.set(this.localScaling);
/* 119:119 */    return out;
/* 120:    */  }
/* 121:    */  
/* 122:    */  public void setMargin(float margin)
/* 123:    */  {
/* 124:124 */    this.collisionMargin = margin;
/* 125:125 */    int i = getNumChildShapes();
/* 126:126 */    while (i-- != 0) {
/* 127:127 */      CollisionShape child = getChildShape(i);
/* 128:128 */      child.setMargin(margin);
/* 129:    */    }
/* 130:    */    
/* 131:131 */    this.needs_update = true;
/* 132:    */  }
/* 133:    */  
/* 135:    */  abstract ShapeType getGImpactShapeType();
/* 136:    */  
/* 138:    */  GImpactBvh getBoxSet()
/* 139:    */  {
/* 140:140 */    return this.box_set;
/* 141:    */  }
/* 142:    */  
/* 145:    */  public boolean hasBoxSet()
/* 146:    */  {
/* 147:147 */    if (this.box_set.getNodeCount() == 0) {
/* 148:148 */      return false;
/* 149:    */    }
/* 150:150 */    return true;
/* 151:    */  }
/* 152:    */  
/* 156:    */  abstract PrimitiveManagerBase getPrimitiveManager();
/* 157:    */  
/* 161:    */  public abstract int getNumChildShapes();
/* 162:    */  
/* 165:    */  public abstract boolean childrenHasTransform();
/* 166:    */  
/* 169:    */  public abstract boolean needsRetrieveTriangles();
/* 170:    */  
/* 173:    */  public abstract boolean needsRetrieveTetrahedrons();
/* 174:    */  
/* 177:    */  public abstract void getBulletTriangle(int paramInt, TriangleShapeEx paramTriangleShapeEx);
/* 178:    */  
/* 181:    */  abstract void getBulletTetrahedron(int paramInt, TetrahedronShapeEx paramTetrahedronShapeEx);
/* 182:    */  
/* 185:    */  public void lockChildShapes() {}
/* 186:    */  
/* 189:    */  public void unlockChildShapes() {}
/* 190:    */  
/* 193:    */  void getPrimitiveTriangle(int index, PrimitiveTriangle triangle)
/* 194:    */  {
/* 195:195 */    getPrimitiveManager().get_primitive_triangle(index, triangle);
/* 196:    */  }
/* 197:    */  
/* 200:    */  protected void calcLocalAABB()
/* 201:    */  {
/* 202:202 */    lockChildShapes();
/* 203:203 */    if (this.box_set.getNodeCount() == 0) {
/* 204:204 */      this.box_set.buildSet();
/* 205:    */    }
/* 206:    */    else {
/* 207:207 */      this.box_set.update();
/* 208:    */    }
/* 209:209 */    unlockChildShapes();
/* 210:    */    
/* 211:211 */    this.box_set.getGlobalBox(this.localAABB);
/* 212:    */  }
/* 213:    */  
/* 216:    */  public void getChildAabb(int arg1, Transform arg2, Vector3f arg3, Vector3f arg4)
/* 217:    */  {
/* 218:218 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB child_aabb = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 219:219 */      getPrimitiveManager().get_primitive_box(child_index, child_aabb);
/* 220:220 */      child_aabb.appy_transform(t);
/* 221:221 */      aabbMin.set(child_aabb.min);
/* 222:222 */      aabbMax.set(child_aabb.max);
/* 223:223 */    } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 224:    */    }
/* 225:    */  }
/* 226:    */  
/* 227:    */  public abstract CollisionShape getChildShape(int paramInt);
/* 228:    */  
/* 229:    */  public abstract Transform getChildTransform(int paramInt);
/* 230:    */  
/* 231:    */  public abstract void setChildTransform(int paramInt, Transform paramTransform);
/* 232:    */  
/* 233:    */  public void rayTest(Vector3f rayFrom, Vector3f rayTo, CollisionWorld.RayResultCallback resultCallback) {}
/* 234:    */  
/* 235:    */  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax) {}
/* 236:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactShapeInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */