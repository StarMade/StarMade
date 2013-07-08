/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */
/*  34:    */public class BU_Simplex1to4
/*  35:    */  extends PolyhedralConvexShape
/*  36:    */{
/*  37: 37 */  protected int numVertices = 0;
/*  38: 38 */  protected Vector3f[] vertices = new Vector3f[4];
/*  39:    */  
/*  40:    */  public BU_Simplex1to4() {}
/*  41:    */  
/*  42:    */  public BU_Simplex1to4(Vector3f pt0)
/*  43:    */  {
/*  44: 44 */    addVertex(pt0);
/*  45:    */  }
/*  46:    */  
/*  47:    */  public BU_Simplex1to4(Vector3f pt0, Vector3f pt1) {
/*  48: 48 */    addVertex(pt0);
/*  49: 49 */    addVertex(pt1);
/*  50:    */  }
/*  51:    */  
/*  52:    */  public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2) {
/*  53: 53 */    addVertex(pt0);
/*  54: 54 */    addVertex(pt1);
/*  55: 55 */    addVertex(pt2);
/*  56:    */  }
/*  57:    */  
/*  58:    */  public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2, Vector3f pt3) {
/*  59: 59 */    addVertex(pt0);
/*  60: 60 */    addVertex(pt1);
/*  61: 61 */    addVertex(pt2);
/*  62: 62 */    addVertex(pt3);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public void reset() {
/*  66: 66 */    this.numVertices = 0;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public BroadphaseNativeType getShapeType()
/*  70:    */  {
/*  71: 71 */    return BroadphaseNativeType.TETRAHEDRAL_SHAPE_PROXYTYPE;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public void addVertex(Vector3f pt) {
/*  75: 75 */    if (this.vertices[this.numVertices] == null) {
/*  76: 76 */      this.vertices[this.numVertices] = new Vector3f();
/*  77:    */    }
/*  78:    */    
/*  79: 79 */    this.vertices[(this.numVertices++)] = pt;
/*  80:    */    
/*  81: 81 */    recalcLocalAabb();
/*  82:    */  }
/*  83:    */  
/*  85:    */  public int getNumVertices()
/*  86:    */  {
/*  87: 87 */    return this.numVertices;
/*  88:    */  }
/*  89:    */  
/*  92:    */  public int getNumEdges()
/*  93:    */  {
/*  94: 94 */    switch (this.numVertices) {
/*  95: 95 */    case 0:  return 0;
/*  96: 96 */    case 1:  return 0;
/*  97: 97 */    case 2:  return 1;
/*  98: 98 */    case 3:  return 3;
/*  99: 99 */    case 4:  return 6;
/* 100:    */    }
/* 101:    */    
/* 102:102 */    return 0;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public void getEdge(int i, Vector3f pa, Vector3f pb)
/* 106:    */  {
/* 107:107 */    switch (this.numVertices) {
/* 108:    */    case 2: 
/* 109:109 */      pa.set(this.vertices[0]);
/* 110:110 */      pb.set(this.vertices[1]);
/* 111:111 */      break;
/* 112:    */    case 3: 
/* 113:113 */      switch (i) {
/* 114:    */      case 0: 
/* 115:115 */        pa.set(this.vertices[0]);
/* 116:116 */        pb.set(this.vertices[1]);
/* 117:117 */        break;
/* 118:    */      case 1: 
/* 119:119 */        pa.set(this.vertices[1]);
/* 120:120 */        pb.set(this.vertices[2]);
/* 121:121 */        break;
/* 122:    */      case 2: 
/* 123:123 */        pa.set(this.vertices[2]);
/* 124:124 */        pb.set(this.vertices[0]);
/* 125:    */      }
/* 126:    */      
/* 127:127 */      break;
/* 128:    */    case 4: 
/* 129:129 */      switch (i) {
/* 130:    */      case 0: 
/* 131:131 */        pa.set(this.vertices[0]);
/* 132:132 */        pb.set(this.vertices[1]);
/* 133:133 */        break;
/* 134:    */      case 1: 
/* 135:135 */        pa.set(this.vertices[1]);
/* 136:136 */        pb.set(this.vertices[2]);
/* 137:137 */        break;
/* 138:    */      case 2: 
/* 139:139 */        pa.set(this.vertices[2]);
/* 140:140 */        pb.set(this.vertices[0]);
/* 141:141 */        break;
/* 142:    */      case 3: 
/* 143:143 */        pa.set(this.vertices[0]);
/* 144:144 */        pb.set(this.vertices[3]);
/* 145:145 */        break;
/* 146:    */      case 4: 
/* 147:147 */        pa.set(this.vertices[1]);
/* 148:148 */        pb.set(this.vertices[3]);
/* 149:149 */        break;
/* 150:    */      case 5: 
/* 151:151 */        pa.set(this.vertices[2]);
/* 152:152 */        pb.set(this.vertices[3]);
/* 153:    */      }
/* 154:    */      break;
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 158:    */  public void getVertex(int i, Vector3f vtx)
/* 159:    */  {
/* 160:160 */    vtx.set(this.vertices[i]);
/* 161:    */  }
/* 162:    */  
/* 163:    */  public int getNumPlanes()
/* 164:    */  {
/* 165:165 */    switch (this.numVertices) {
/* 166:166 */    case 0:  return 0;
/* 167:167 */    case 1:  return 0;
/* 168:168 */    case 2:  return 0;
/* 169:169 */    case 3:  return 2;
/* 170:170 */    case 4:  return 4;
/* 171:    */    }
/* 172:172 */    return 0;
/* 173:    */  }
/* 174:    */  
/* 176:    */  public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i) {}
/* 177:    */  
/* 178:    */  public int getIndex(int i)
/* 179:    */  {
/* 180:180 */    return 0;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public boolean isInside(Vector3f pt, float tolerance)
/* 184:    */  {
/* 185:185 */    return false;
/* 186:    */  }
/* 187:    */  
/* 188:    */  public String getName()
/* 189:    */  {
/* 190:190 */    return "BU_Simplex1to4";
/* 191:    */  }
/* 192:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BU_Simplex1to4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */