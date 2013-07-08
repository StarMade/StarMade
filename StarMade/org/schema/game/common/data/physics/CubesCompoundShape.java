/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*   4:    */import com.bulletphysics.collision.shapes.CompoundShape;
/*   5:    */import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*   6:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import com.bulletphysics.linearmath.VectorUtil;
/*   9:    */import com.bulletphysics.util.ObjectArrayList;
/*  10:    */import java.util.ArrayList;
/*  11:    */import javax.vecmath.Matrix3f;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */import org.schema.game.common.controller.SegmentController;
/*  14:    */
/*  21:    */public class CubesCompoundShape
/*  22:    */  extends CompoundShape
/*  23:    */{
/*  24:    */  private SegmentController segmentController;
/*  25: 25 */  private final ArrayList attached = new ArrayList();
/*  26:    */  private static Transform ident;
/*  27:    */  
/*  28:    */  public CubesCompoundShape(SegmentController paramSegmentController) {
/*  29: 29 */    this.segmentController = paramSegmentController;
/*  30:    */  }
/*  31:    */  
/*  37:    */  public ArrayList getAttached()
/*  38:    */  {
/*  39: 39 */    return this.attached;
/*  40:    */  }
/*  41:    */  
/*  42:    */  public SegmentController getSegmentController() {
/*  43: 43 */    return this.segmentController;
/*  44:    */  }
/*  45:    */  
/*  53:    */  public boolean isCompound()
/*  54:    */  {
/*  55: 55 */    return true;
/*  56:    */  }
/*  57:    */  
/*  60: 60 */  static { (CubesCompoundShape.ident = new Transform()).setIdentity(); }
/*  61:    */  
/*  62: 62 */  private Vector3f aabbMin = new Vector3f();
/*  63: 63 */  private Vector3f aabbMax = new Vector3f();
/*  64: 64 */  private Vector3f halfExtents = new Vector3f();
/*  65:    */  
/*  67: 67 */  private Vector3f localAabbMin = new Vector3f();
/*  68: 68 */  private Vector3f localAabbMax = new Vector3f();
/*  69:    */  
/*  74:    */  public void calculateLocalInertia(float paramFloat, Vector3f paramVector3f)
/*  75:    */  {
/*  76: 76 */    getAabb(ident, this.aabbMin, this.aabbMax);
/*  77:    */    
/*  78: 78 */    this.halfExtents.sub(this.aabbMax, this.aabbMin);
/*  79: 79 */    this.halfExtents.scale(0.5F);
/*  80:    */    
/*  81: 81 */    float f1 = 2.0F * this.halfExtents.x;
/*  82: 82 */    float f2 = 2.0F * this.halfExtents.y;
/*  83: 83 */    float f3 = 2.0F * this.halfExtents.z;
/*  84:    */    
/*  85: 85 */    paramVector3f.x = (paramFloat / 12.0F * (f2 * f2 + f3 * f3));
/*  86: 86 */    paramVector3f.y = (paramFloat / 12.0F * (f1 * f1 + f3 * f3));
/*  87: 87 */    paramVector3f.z = (paramFloat / 12.0F * (f1 * f1 + f2 * f2));
/*  88:    */  }
/*  89:    */  
/*  90: 90 */  private final Vector3f tmpLocalAabbMin = new Vector3f();
/*  91: 91 */  private final Vector3f tmpLocalAabbMax = new Vector3f();
/*  92:    */  
/*  93:    */  public void recalculateLocalAabb()
/*  94:    */  {
/*  95: 95 */    this.localAabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  96: 96 */    this.localAabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  97:    */    
/* 101:101 */    for (int i = 0; i < getChildList().size(); i++) {
/* 102:102 */      ((CompoundShapeChild)getChildList().getQuick(i)).childShape.getAabb(((CompoundShapeChild)getChildList().getQuick(i)).transform, this.tmpLocalAabbMin, this.tmpLocalAabbMax);
/* 103:    */      
/* 104:104 */      for (int j = 0; j < 3; j++) {
/* 105:105 */        if (VectorUtil.getCoord(this.localAabbMin, j) > VectorUtil.getCoord(this.tmpLocalAabbMin, j)) {
/* 106:106 */          VectorUtil.setCoord(this.localAabbMin, j, VectorUtil.getCoord(this.tmpLocalAabbMin, j));
/* 107:    */        }
/* 108:108 */        if (VectorUtil.getCoord(this.localAabbMax, j) < VectorUtil.getCoord(this.tmpLocalAabbMax, j)) {
/* 109:109 */          VectorUtil.setCoord(this.localAabbMax, j, VectorUtil.getCoord(this.tmpLocalAabbMax, j));
/* 110:    */        }
/* 111:    */      }
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 117:117 */  Vector3f _localAabbMin = new Vector3f();
/* 118:118 */  Vector3f _localAabbMax = new Vector3f();
/* 119:    */  
/* 120:    */  public void addChildShape(Transform paramTransform, CollisionShape paramCollisionShape)
/* 121:    */  {
/* 122:    */    CompoundShapeChild localCompoundShapeChild;
/* 123:123 */    (localCompoundShapeChild = new CompoundShapeChild()).transform.set(paramTransform);
/* 124:124 */    localCompoundShapeChild.childShape = paramCollisionShape;
/* 125:125 */    localCompoundShapeChild.childShapeType = paramCollisionShape.getShapeType();
/* 126:126 */    localCompoundShapeChild.childMargin = paramCollisionShape.getMargin();
/* 127:    */    
/* 128:128 */    getChildList().add(localCompoundShapeChild);
/* 129:    */    
/* 132:132 */    paramCollisionShape.getAabb(paramTransform, this._localAabbMin, this._localAabbMax);
/* 133:    */    
/* 146:146 */    VectorUtil.setMin(this.localAabbMin, this._localAabbMin);
/* 147:147 */    VectorUtil.setMax(this.localAabbMax, this._localAabbMax);
/* 148:    */  }
/* 149:    */  
/* 150:150 */  Vector3f localCenter = new Vector3f();
/* 151:151 */  Vector3f localHalfExtents = new Vector3f();
/* 152:152 */  Matrix3f abs_b = new Matrix3f();
/* 153:153 */  Vector3f center = new Vector3f();
/* 154:154 */  Vector3f tmp = new Vector3f();
/* 155:155 */  Vector3f extent = new Vector3f();
/* 156:    */  
/* 160:    */  public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 161:    */  {
/* 162:162 */    this.localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
/* 163:163 */    this.localHalfExtents.scale(0.5F);
/* 164:164 */    this.localHalfExtents.x += getMargin();
/* 165:165 */    this.localHalfExtents.y += getMargin();
/* 166:166 */    this.localHalfExtents.z += getMargin();
/* 167:    */    
/* 170:170 */    this.localCenter.add(this.localAabbMax, this.localAabbMin);
/* 171:171 */    this.localCenter.scale(0.5F);
/* 172:    */    
/* 175:175 */    this.abs_b.set(paramTransform.basis);
/* 176:176 */    MatrixUtil.absolute(this.abs_b);
/* 177:    */    
/* 178:178 */    this.center.set(this.localCenter);
/* 179:179 */    paramTransform.transform(this.center);
/* 180:    */    
/* 184:184 */    this.abs_b.getRow(0, this.tmp);
/* 185:185 */    this.extent.x = this.tmp.dot(this.localHalfExtents);
/* 186:186 */    this.abs_b.getRow(1, this.tmp);
/* 187:187 */    this.extent.y = this.tmp.dot(this.localHalfExtents);
/* 188:188 */    this.abs_b.getRow(2, this.tmp);
/* 189:189 */    this.extent.z = this.tmp.dot(this.localHalfExtents);
/* 190:    */    
/* 191:191 */    paramVector3f1.sub(this.center, this.extent);
/* 192:192 */    paramVector3f2.add(this.center, this.extent);
/* 193:    */  }
/* 194:    */  
/* 230:    */  public String toString()
/* 231:    */  {
/* 232:232 */    return "[CCS" + (this.segmentController.isOnServer() ? "|SER " : "|CLI ") + this.segmentController + "]";
/* 233:    */  }
/* 234:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubesCompoundShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */