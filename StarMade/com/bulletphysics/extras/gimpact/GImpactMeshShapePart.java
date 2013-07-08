/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   5:    */import com.bulletphysics.collision.shapes.StridingMeshInterface;
/*   6:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import com.bulletphysics.util.IntArrayList;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  50:    */public class GImpactMeshShapePart
/*  51:    */  extends GImpactShapeInterface
/*  52:    */{
/*  53: 53 */  TrimeshPrimitiveManager primitive_manager = new TrimeshPrimitiveManager();
/*  54:    */  
/*  55: 55 */  private final IntArrayList collided = new IntArrayList();
/*  56:    */  
/*  57:    */  public GImpactMeshShapePart() {
/*  58: 58 */    this.box_set.setPrimitiveManager(this.primitive_manager);
/*  59:    */  }
/*  60:    */  
/*  61:    */  public GImpactMeshShapePart(StridingMeshInterface meshInterface, int part) {
/*  62: 62 */    this.primitive_manager.meshInterface = meshInterface;
/*  63: 63 */    this.primitive_manager.part = part;
/*  64: 64 */    this.box_set.setPrimitiveManager(this.primitive_manager);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public boolean childrenHasTransform()
/*  68:    */  {
/*  69: 69 */    return false;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void lockChildShapes()
/*  73:    */  {
/*  74: 74 */    TrimeshPrimitiveManager dummymanager = (TrimeshPrimitiveManager)this.box_set.getPrimitiveManager();
/*  75: 75 */    dummymanager.lock();
/*  76:    */  }
/*  77:    */  
/*  78:    */  public void unlockChildShapes()
/*  79:    */  {
/*  80: 80 */    TrimeshPrimitiveManager dummymanager = (TrimeshPrimitiveManager)this.box_set.getPrimitiveManager();
/*  81: 81 */    dummymanager.unlock();
/*  82:    */  }
/*  83:    */  
/*  84:    */  public int getNumChildShapes()
/*  85:    */  {
/*  86: 86 */    return this.primitive_manager.get_primitive_count();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public CollisionShape getChildShape(int index)
/*  90:    */  {
/*  91: 91 */    if (!$assertionsDisabled) throw new AssertionError();
/*  92: 92 */    return null;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public Transform getChildTransform(int index)
/*  96:    */  {
/*  97: 97 */    if (!$assertionsDisabled) throw new AssertionError();
/*  98: 98 */    return null;
/*  99:    */  }
/* 100:    */  
/* 101:    */  public void setChildTransform(int index, Transform transform)
/* 102:    */  {
/* 103:103 */    if (!$assertionsDisabled) throw new AssertionError();
/* 104:    */  }
/* 105:    */  
/* 106:    */  PrimitiveManagerBase getPrimitiveManager()
/* 107:    */  {
/* 108:108 */    return this.primitive_manager;
/* 109:    */  }
/* 110:    */  
/* 111:    */  TrimeshPrimitiveManager getTrimeshPrimitiveManager() {
/* 112:112 */    return this.primitive_manager;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void calculateLocalInertia(float arg1, Vector3f arg2)
/* 116:    */  {
/* 117:117 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();lockChildShapes();
/* 118:    */      
/* 121:121 */      inertia.set(0.0F, 0.0F, 0.0F);
/* 122:    */      
/* 123:123 */      int i = getVertexCount();
/* 124:124 */      float pointmass = mass / i;
/* 125:    */      
/* 126:126 */      Vector3f pointintertia = localStack.get$javax$vecmath$Vector3f();
/* 127:    */      
/* 128:128 */      while (i-- != 0) {
/* 129:129 */        getVertex(i, pointintertia);
/* 130:130 */        GImpactMassUtil.get_point_inertia(pointintertia, pointmass, pointintertia);
/* 131:131 */        inertia.add(pointintertia);
/* 132:    */      }
/* 133:    */      
/* 150:150 */      unlockChildShapes();
/* 151:151 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 152:    */    }
/* 153:    */  }
/* 154:    */  
/* 155:155 */  public String getName() { return "GImpactMeshShapePart"; }
/* 156:    */  
/* 158:    */  ShapeType getGImpactShapeType()
/* 159:    */  {
/* 160:160 */    return ShapeType.TRIMESH_SHAPE_PART;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public boolean needsRetrieveTriangles()
/* 164:    */  {
/* 165:165 */    return true;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public boolean needsRetrieveTetrahedrons()
/* 169:    */  {
/* 170:170 */    return false;
/* 171:    */  }
/* 172:    */  
/* 173:    */  public void getBulletTriangle(int prim_index, TriangleShapeEx triangle)
/* 174:    */  {
/* 175:175 */    this.primitive_manager.get_bullet_triangle(prim_index, triangle);
/* 176:    */  }
/* 177:    */  
/* 178:    */  void getBulletTetrahedron(int prim_index, TetrahedronShapeEx tetrahedron)
/* 179:    */  {
/* 180:180 */    if (!$assertionsDisabled) throw new AssertionError();
/* 181:    */  }
/* 182:    */  
/* 183:    */  public int getVertexCount() {
/* 184:184 */    return this.primitive_manager.get_vertex_count();
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void getVertex(int vertex_index, Vector3f vertex) {
/* 188:188 */    this.primitive_manager.get_vertex(vertex_index, vertex);
/* 189:    */  }
/* 190:    */  
/* 191:    */  public void setMargin(float margin)
/* 192:    */  {
/* 193:193 */    this.primitive_manager.margin = margin;
/* 194:194 */    postUpdate();
/* 195:    */  }
/* 196:    */  
/* 197:    */  public float getMargin()
/* 198:    */  {
/* 199:199 */    return this.primitive_manager.margin;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public void setLocalScaling(Vector3f scaling)
/* 203:    */  {
/* 204:204 */    this.primitive_manager.scale.set(scaling);
/* 205:205 */    postUpdate();
/* 206:    */  }
/* 207:    */  
/* 208:    */  public Vector3f getLocalScaling(Vector3f out)
/* 209:    */  {
/* 210:210 */    out.set(this.primitive_manager.scale);
/* 211:211 */    return out;
/* 212:    */  }
/* 213:    */  
/* 214:    */  public int getPart() {
/* 215:215 */    return this.primitive_manager.part;
/* 216:    */  }
/* 217:    */  
/* 218:    */  public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
/* 219:    */  {
/* 220:220 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();tmp7_5.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle();lockChildShapes();
/* 221:221 */      BoxCollision.AABB box = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 222:222 */      box.min.set(aabbMin);
/* 223:223 */      box.max.set(aabbMax);
/* 224:    */      
/* 225:225 */      this.collided.clear();
/* 226:226 */      this.box_set.boxQuery(box, this.collided);
/* 227:    */      
/* 228:228 */      if (this.collided.size() == 0) {
/* 229:229 */        unlockChildShapes();
/* 230:230 */        return;
/* 231:    */      }
/* 232:    */      
/* 233:233 */      int part = getPart();
/* 234:234 */      PrimitiveTriangle triangle = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 235:235 */      int i = this.collided.size();
/* 236:236 */      while (i-- != 0) {
/* 237:237 */        getPrimitiveTriangle(this.collided.get(i), triangle);
/* 238:238 */        callback.processTriangle(triangle.vertices, part, this.collided.get(i));
/* 239:    */      }
/* 240:240 */      unlockChildShapes();
/* 241:241 */    } finally { .Stack tmp172_170 = localStack;tmp172_170.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();tmp172_170.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 242:    */    }
/* 243:    */  }
/* 244:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMeshShapePart
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */