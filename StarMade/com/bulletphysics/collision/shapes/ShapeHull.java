/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.MiscUtil;
/*   5:    */import com.bulletphysics.linearmath.convexhull.HullDesc;
/*   6:    */import com.bulletphysics.linearmath.convexhull.HullFlags;
/*   7:    */import com.bulletphysics.linearmath.convexhull.HullLibrary;
/*   8:    */import com.bulletphysics.linearmath.convexhull.HullResult;
/*   9:    */import com.bulletphysics.util.IntArrayList;
/*  10:    */import com.bulletphysics.util.ObjectArrayList;
/*  11:    */import javax.vecmath.Tuple3f;
/*  12:    */import javax.vecmath.Vector3f;
/*  13:    */
/*  41:    */public class ShapeHull
/*  42:    */{
/*  43: 43 */  protected ObjectArrayList<Vector3f> vertices = new ObjectArrayList();
/*  44: 44 */  protected IntArrayList indices = new IntArrayList();
/*  45:    */  
/*  46:    */  protected int numIndices;
/*  47:    */  protected ConvexShape shape;
/*  48: 48 */  protected ObjectArrayList<Vector3f> unitSpherePoints = new ObjectArrayList();
/*  49:    */  
/*  50:    */  public ShapeHull(ConvexShape shape) {
/*  51: 51 */    this.shape = shape;
/*  52: 52 */    this.vertices.clear();
/*  53: 53 */    this.indices.clear();
/*  54: 54 */    this.numIndices = 0;
/*  55:    */    
/*  56: 56 */    MiscUtil.resize(this.unitSpherePoints, NUM_UNITSPHERE_POINTS + 20, Vector3f.class);
/*  57: 57 */    for (int i = 0; i < constUnitSpherePoints.size(); i++) {
/*  58: 58 */      ((Vector3f)this.unitSpherePoints.getQuick(i)).set((Tuple3f)constUnitSpherePoints.getQuick(i));
/*  59:    */    }
/*  60:    */  }
/*  61:    */  
/*  62:    */  public boolean buildHull(float arg1) {
/*  63: 63 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f norm = localStack.get$javax$vecmath$Vector3f();
/*  64:    */      
/*  65: 65 */      int numSampleDirections = NUM_UNITSPHERE_POINTS;
/*  66:    */      
/*  67: 67 */      int numPDA = this.shape.getNumPreferredPenetrationDirections();
/*  68: 68 */      if (numPDA != 0) {
/*  69: 69 */        for (int i = 0; i < numPDA; i++) {
/*  70: 70 */          this.shape.getPreferredPenetrationDirection(i, norm);
/*  71: 71 */          ((Vector3f)this.unitSpherePoints.getQuick(numSampleDirections)).set(norm);
/*  72: 72 */          numSampleDirections++;
/*  73:    */        }
/*  74:    */      }
/*  75:    */      
/*  77: 77 */      ObjectArrayList<Vector3f> supportPoints = new ObjectArrayList();
/*  78: 78 */      MiscUtil.resize(supportPoints, NUM_UNITSPHERE_POINTS + 20, Vector3f.class);
/*  79:    */      
/*  80: 80 */      for (int i = 0; i < numSampleDirections; i++) {
/*  81: 81 */        this.shape.localGetSupportingVertex((Vector3f)this.unitSpherePoints.getQuick(i), (Vector3f)supportPoints.getQuick(i));
/*  82:    */      }
/*  83:    */      
/*  84: 84 */      HullDesc hd = new HullDesc();
/*  85: 85 */      hd.flags = HullFlags.TRIANGLES;
/*  86: 86 */      hd.vcount = numSampleDirections;
/*  87:    */      
/*  92: 92 */      hd.vertices = supportPoints;
/*  93:    */      
/*  96: 96 */      HullLibrary hl = new HullLibrary();
/*  97: 97 */      HullResult hr = new HullResult();
/*  98: 98 */      if (!hl.createConvexHull(hd, hr)) {
/*  99: 99 */        return false;
/* 100:    */      }
/* 101:    */      
/* 102:102 */      MiscUtil.resize(this.vertices, hr.numOutputVertices, Vector3f.class);
/* 103:    */      
/* 104:104 */      for (int i = 0; i < hr.numOutputVertices; i++) {
/* 105:105 */        ((Vector3f)this.vertices.getQuick(i)).set((Tuple3f)hr.outputVertices.getQuick(i));
/* 106:    */      }
/* 107:107 */      this.numIndices = hr.numIndices;
/* 108:108 */      MiscUtil.resize(this.indices, this.numIndices, 0);
/* 109:109 */      for (int i = 0; i < this.numIndices; i++) {
/* 110:110 */        this.indices.set(i, hr.indices.get(i));
/* 111:    */      }
/* 112:    */      
/* 114:114 */      hl.releaseResult(hr);
/* 115:    */      
/* 116:116 */      return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 117:    */    }
/* 118:    */  }
/* 119:    */  
/* 120:120 */  public int numTriangles() { return this.numIndices / 3; }
/* 121:    */  
/* 122:    */  public int numVertices()
/* 123:    */  {
/* 124:124 */    return this.vertices.size();
/* 125:    */  }
/* 126:    */  
/* 127:    */  public int numIndices() {
/* 128:128 */    return this.numIndices;
/* 129:    */  }
/* 130:    */  
/* 131:    */  public ObjectArrayList<Vector3f> getVertexPointer() {
/* 132:132 */    return this.vertices;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public IntArrayList getIndexPointer() {
/* 136:136 */    return this.indices;
/* 137:    */  }
/* 138:    */  
/* 141:141 */  private static int NUM_UNITSPHERE_POINTS = 42;
/* 142:    */  
/* 143:143 */  private static ObjectArrayList<Vector3f> constUnitSpherePoints = new ObjectArrayList();
/* 144:    */  
/* 145:    */  static {
/* 146:146 */    constUnitSpherePoints.add(new Vector3f(0.0F, -0.0F, -1.0F));
/* 147:147 */    constUnitSpherePoints.add(new Vector3f(0.723608F, -0.525725F, -0.447219F));
/* 148:148 */    constUnitSpherePoints.add(new Vector3f(-0.276388F, -0.850649F, -0.447219F));
/* 149:149 */    constUnitSpherePoints.add(new Vector3f(-0.894426F, -0.0F, -0.447216F));
/* 150:150 */    constUnitSpherePoints.add(new Vector3f(-0.276388F, 0.850649F, -0.44722F));
/* 151:151 */    constUnitSpherePoints.add(new Vector3f(0.723608F, 0.525725F, -0.447219F));
/* 152:152 */    constUnitSpherePoints.add(new Vector3f(0.276388F, -0.850649F, 0.44722F));
/* 153:153 */    constUnitSpherePoints.add(new Vector3f(-0.723608F, -0.525725F, 0.447219F));
/* 154:154 */    constUnitSpherePoints.add(new Vector3f(-0.723608F, 0.525725F, 0.447219F));
/* 155:155 */    constUnitSpherePoints.add(new Vector3f(0.276388F, 0.850649F, 0.447219F));
/* 156:156 */    constUnitSpherePoints.add(new Vector3f(0.894426F, 0.0F, 0.447216F));
/* 157:157 */    constUnitSpherePoints.add(new Vector3f(-0.0F, 0.0F, 1.0F));
/* 158:158 */    constUnitSpherePoints.add(new Vector3f(0.425323F, -0.309011F, -0.850654F));
/* 159:159 */    constUnitSpherePoints.add(new Vector3f(-0.162456F, -0.499995F, -0.850654F));
/* 160:160 */    constUnitSpherePoints.add(new Vector3f(0.262869F, -0.809012F, -0.525738F));
/* 161:161 */    constUnitSpherePoints.add(new Vector3f(0.425323F, 0.309011F, -0.850654F));
/* 162:162 */    constUnitSpherePoints.add(new Vector3f(0.850648F, -0.0F, -0.525736F));
/* 163:163 */    constUnitSpherePoints.add(new Vector3f(-0.52573F, -0.0F, -0.850652F));
/* 164:164 */    constUnitSpherePoints.add(new Vector3f(-0.68819F, -0.499997F, -0.525736F));
/* 165:165 */    constUnitSpherePoints.add(new Vector3f(-0.162456F, 0.499995F, -0.850654F));
/* 166:166 */    constUnitSpherePoints.add(new Vector3f(-0.68819F, 0.499997F, -0.525736F));
/* 167:167 */    constUnitSpherePoints.add(new Vector3f(0.262869F, 0.809012F, -0.525738F));
/* 168:168 */    constUnitSpherePoints.add(new Vector3f(0.951058F, 0.309013F, 0.0F));
/* 169:169 */    constUnitSpherePoints.add(new Vector3f(0.951058F, -0.309013F, 0.0F));
/* 170:170 */    constUnitSpherePoints.add(new Vector3f(0.587786F, -0.809017F, 0.0F));
/* 171:171 */    constUnitSpherePoints.add(new Vector3f(0.0F, -1.0F, 0.0F));
/* 172:172 */    constUnitSpherePoints.add(new Vector3f(-0.587786F, -0.809017F, 0.0F));
/* 173:173 */    constUnitSpherePoints.add(new Vector3f(-0.951058F, -0.309013F, -0.0F));
/* 174:174 */    constUnitSpherePoints.add(new Vector3f(-0.951058F, 0.309013F, -0.0F));
/* 175:175 */    constUnitSpherePoints.add(new Vector3f(-0.587786F, 0.809017F, -0.0F));
/* 176:176 */    constUnitSpherePoints.add(new Vector3f(-0.0F, 1.0F, -0.0F));
/* 177:177 */    constUnitSpherePoints.add(new Vector3f(0.587786F, 0.809017F, -0.0F));
/* 178:178 */    constUnitSpherePoints.add(new Vector3f(0.68819F, -0.499997F, 0.525736F));
/* 179:179 */    constUnitSpherePoints.add(new Vector3f(-0.262869F, -0.809012F, 0.525738F));
/* 180:180 */    constUnitSpherePoints.add(new Vector3f(-0.850648F, 0.0F, 0.525736F));
/* 181:181 */    constUnitSpherePoints.add(new Vector3f(-0.262869F, 0.809012F, 0.525738F));
/* 182:182 */    constUnitSpherePoints.add(new Vector3f(0.68819F, 0.499997F, 0.525736F));
/* 183:183 */    constUnitSpherePoints.add(new Vector3f(0.52573F, 0.0F, 0.850652F));
/* 184:184 */    constUnitSpherePoints.add(new Vector3f(0.162456F, -0.499995F, 0.850654F));
/* 185:185 */    constUnitSpherePoints.add(new Vector3f(-0.425323F, -0.309011F, 0.850654F));
/* 186:186 */    constUnitSpherePoints.add(new Vector3f(-0.425323F, 0.309011F, 0.850654F));
/* 187:187 */    constUnitSpherePoints.add(new Vector3f(0.162456F, 0.499995F, 0.850654F));
/* 188:    */  }
/* 189:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ShapeHull
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */