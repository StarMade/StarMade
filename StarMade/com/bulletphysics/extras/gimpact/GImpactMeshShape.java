/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
/*   5:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   6:    */import com.bulletphysics.collision.shapes.StridingMeshInterface;
/*   7:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*   8:    */import com.bulletphysics.linearmath.Transform;
/*   9:    */import com.bulletphysics.util.ObjectArrayList;
/*  10:    */import javax.vecmath.Vector3f;
/*  11:    */
/*  43:    */public class GImpactMeshShape
/*  44:    */  extends GImpactShapeInterface
/*  45:    */{
/*  46: 46 */  protected ObjectArrayList<GImpactMeshShapePart> mesh_parts = new ObjectArrayList();
/*  47:    */  
/*  48:    */  public GImpactMeshShape(StridingMeshInterface meshInterface) {
/*  49: 49 */    buildMeshParts(meshInterface);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public int getMeshPartCount() {
/*  53: 53 */    return this.mesh_parts.size();
/*  54:    */  }
/*  55:    */  
/*  56:    */  public GImpactMeshShapePart getMeshPart(int index) {
/*  57: 57 */    return (GImpactMeshShapePart)this.mesh_parts.getQuick(index);
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void setLocalScaling(Vector3f scaling)
/*  61:    */  {
/*  62: 62 */    this.localScaling.set(scaling);
/*  63:    */    
/*  64: 64 */    int i = this.mesh_parts.size();
/*  65: 65 */    while (i-- != 0) {
/*  66: 66 */      GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(i);
/*  67: 67 */      part.setLocalScaling(scaling);
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    this.needs_update = true;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public void setMargin(float margin)
/*  74:    */  {
/*  75: 75 */    this.collisionMargin = margin;
/*  76:    */    
/*  77: 77 */    int i = this.mesh_parts.size();
/*  78: 78 */    while (i-- != 0) {
/*  79: 79 */      GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(i);
/*  80: 80 */      part.setMargin(margin);
/*  81:    */    }
/*  82:    */    
/*  83: 83 */    this.needs_update = true;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public void postUpdate()
/*  87:    */  {
/*  88: 88 */    int i = this.mesh_parts.size();
/*  89: 89 */    while (i-- != 0) {
/*  90: 90 */      GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(i);
/*  91: 91 */      part.postUpdate();
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    this.needs_update = true;
/*  95:    */  }
/*  96:    */  
/*  98:    */  public void calculateLocalInertia(float arg1, Vector3f arg2)
/*  99:    */  {
/* 100:100 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();inertia.set(0.0F, 0.0F, 0.0F);
/* 101:    */      
/* 102:102 */      int i = getMeshPartCount();
/* 103:103 */      float partmass = mass / i;
/* 104:    */      
/* 105:105 */      Vector3f partinertia = localStack.get$javax$vecmath$Vector3f();
/* 106:    */      
/* 107:107 */      while (i-- != 0) {
/* 108:108 */        getMeshPart(i).calculateLocalInertia(partmass, partinertia);
/* 109:109 */        inertia.add(partinertia);
/* 113:    */      }
/* 114:    */      
/* 119:    */    }
/* 120:    */    finally
/* 121:    */    {
/* 126:126 */      localStack.pop$javax$vecmath$Vector3f();
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 130:130 */  PrimitiveManagerBase getPrimitiveManager() { if (!$assertionsDisabled) throw new AssertionError();
/* 131:131 */    return null;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public int getNumChildShapes()
/* 135:    */  {
/* 136:136 */    if (!$assertionsDisabled) throw new AssertionError();
/* 137:137 */    return 0;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public boolean childrenHasTransform()
/* 141:    */  {
/* 142:142 */    if (!$assertionsDisabled) throw new AssertionError();
/* 143:143 */    return false;
/* 144:    */  }
/* 145:    */  
/* 146:    */  public boolean needsRetrieveTriangles()
/* 147:    */  {
/* 148:148 */    if (!$assertionsDisabled) throw new AssertionError();
/* 149:149 */    return false;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public boolean needsRetrieveTetrahedrons()
/* 153:    */  {
/* 154:154 */    if (!$assertionsDisabled) throw new AssertionError();
/* 155:155 */    return false;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public void getBulletTriangle(int prim_index, TriangleShapeEx triangle)
/* 159:    */  {
/* 160:160 */    if (!$assertionsDisabled) throw new AssertionError();
/* 161:    */  }
/* 162:    */  
/* 163:    */  void getBulletTetrahedron(int prim_index, TetrahedronShapeEx tetrahedron)
/* 164:    */  {
/* 165:165 */    if (!$assertionsDisabled) throw new AssertionError();
/* 166:    */  }
/* 167:    */  
/* 168:    */  public void lockChildShapes()
/* 169:    */  {
/* 170:170 */    if (!$assertionsDisabled) throw new AssertionError();
/* 171:    */  }
/* 172:    */  
/* 173:    */  public void unlockChildShapes()
/* 174:    */  {
/* 175:175 */    if (!$assertionsDisabled) throw new AssertionError();
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void getChildAabb(int child_index, Transform t, Vector3f aabbMin, Vector3f aabbMax)
/* 179:    */  {
/* 180:180 */    if (!$assertionsDisabled) throw new AssertionError();
/* 181:    */  }
/* 182:    */  
/* 183:    */  public CollisionShape getChildShape(int index)
/* 184:    */  {
/* 185:185 */    if (!$assertionsDisabled) throw new AssertionError();
/* 186:186 */    return null;
/* 187:    */  }
/* 188:    */  
/* 189:    */  public Transform getChildTransform(int index)
/* 190:    */  {
/* 191:191 */    if (!$assertionsDisabled) throw new AssertionError();
/* 192:192 */    return null;
/* 193:    */  }
/* 194:    */  
/* 195:    */  public void setChildTransform(int index, Transform transform)
/* 196:    */  {
/* 197:197 */    if (!$assertionsDisabled) throw new AssertionError();
/* 198:    */  }
/* 199:    */  
/* 200:    */  ShapeType getGImpactShapeType()
/* 201:    */  {
/* 202:202 */    return ShapeType.TRIMESH_SHAPE;
/* 203:    */  }
/* 204:    */  
/* 205:    */  public String getName()
/* 206:    */  {
/* 207:207 */    return "GImpactMesh";
/* 208:    */  }
/* 209:    */  
/* 211:    */  public void rayTest(Vector3f rayFrom, Vector3f rayTo, CollisionWorld.RayResultCallback resultCallback) {}
/* 212:    */  
/* 214:    */  public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
/* 215:    */  {
/* 216:216 */    int i = this.mesh_parts.size();
/* 217:217 */    while (i-- != 0) {
/* 218:218 */      ((GImpactMeshShapePart)this.mesh_parts.getQuick(i)).processAllTriangles(callback, aabbMin, aabbMax);
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 222:    */  protected void buildMeshParts(StridingMeshInterface meshInterface) {
/* 223:223 */    for (int i = 0; i < meshInterface.getNumSubParts(); i++) {
/* 224:224 */      GImpactMeshShapePart newpart = new GImpactMeshShapePart(meshInterface, i);
/* 225:225 */      this.mesh_parts.add(newpart);
/* 226:    */    }
/* 227:    */  }
/* 228:    */  
/* 229:    */  protected void calcLocalAABB()
/* 230:    */  {
/* 231:231 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 232:    */      
/* 233:233 */      this.localAABB.invalidate();
/* 234:234 */      int i = this.mesh_parts.size();
/* 235:235 */      while (i-- != 0) {
/* 236:236 */        ((GImpactMeshShapePart)this.mesh_parts.getQuick(i)).updateBound();
/* 237:237 */        this.localAABB.merge(((GImpactMeshShapePart)this.mesh_parts.getQuick(i)).getLocalBox(tmpAABB));
/* 238:    */      }
/* 239:239 */    } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 240:    */    }
/* 241:    */  }
/* 242:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMeshShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */