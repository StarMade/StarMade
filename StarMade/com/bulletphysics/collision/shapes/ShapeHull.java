/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.linearmath.convexhull.HullDesc;
/*     */ import com.bulletphysics.linearmath.convexhull.HullFlags;
/*     */ import com.bulletphysics.linearmath.convexhull.HullLibrary;
/*     */ import com.bulletphysics.linearmath.convexhull.HullResult;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ShapeHull
/*     */ {
/*  43 */   protected ObjectArrayList<Vector3f> vertices = new ObjectArrayList();
/*  44 */   protected IntArrayList indices = new IntArrayList();
/*     */   protected int numIndices;
/*     */   protected ConvexShape shape;
/*  48 */   protected ObjectArrayList<Vector3f> unitSpherePoints = new ObjectArrayList();
/*     */ 
/* 141 */   private static int NUM_UNITSPHERE_POINTS = 42;
/*     */ 
/* 143 */   private static ObjectArrayList<Vector3f> constUnitSpherePoints = new ObjectArrayList();
/*     */ 
/*     */   public ShapeHull(ConvexShape shape)
/*     */   {
/*  51 */     this.shape = shape;
/*  52 */     this.vertices.clear();
/*  53 */     this.indices.clear();
/*  54 */     this.numIndices = 0;
/*     */ 
/*  56 */     MiscUtil.resize(this.unitSpherePoints, NUM_UNITSPHERE_POINTS + 20, Vector3f.class);
/*  57 */     for (int i = 0; i < constUnitSpherePoints.size(); i++)
/*  58 */       ((Vector3f)this.unitSpherePoints.getQuick(i)).set((Tuple3f)constUnitSpherePoints.getQuick(i));
/*     */   }
/*     */ 
/*     */   public boolean buildHull(float arg1)
/*     */   {
/*  63 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f norm = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  65 */       int numSampleDirections = NUM_UNITSPHERE_POINTS;
/*     */ 
/*  67 */       int numPDA = this.shape.getNumPreferredPenetrationDirections();
/*  68 */       if (numPDA != 0) {
/*  69 */         for (int i = 0; i < numPDA; i++) {
/*  70 */           this.shape.getPreferredPenetrationDirection(i, norm);
/*  71 */           ((Vector3f)this.unitSpherePoints.getQuick(numSampleDirections)).set(norm);
/*  72 */           numSampleDirections++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  77 */       ObjectArrayList supportPoints = new ObjectArrayList();
/*  78 */       MiscUtil.resize(supportPoints, NUM_UNITSPHERE_POINTS + 20, Vector3f.class);
/*     */ 
/*  80 */       for (int i = 0; i < numSampleDirections; i++) {
/*  81 */         this.shape.localGetSupportingVertex((Vector3f)this.unitSpherePoints.getQuick(i), (Vector3f)supportPoints.getQuick(i));
/*     */       }
/*     */ 
/*  84 */       HullDesc hd = new HullDesc();
/*  85 */       hd.flags = HullFlags.TRIANGLES;
/*  86 */       hd.vcount = numSampleDirections;
/*     */ 
/*  92 */       hd.vertices = supportPoints;
/*     */ 
/*  96 */       HullLibrary hl = new HullLibrary();
/*  97 */       HullResult hr = new HullResult();
/*  98 */       if (!hl.createConvexHull(hd, hr)) {
/*  99 */         return false;
/*     */       }
/*     */ 
/* 102 */       MiscUtil.resize(this.vertices, hr.numOutputVertices, Vector3f.class);
/*     */ 
/* 104 */       for (int i = 0; i < hr.numOutputVertices; i++) {
/* 105 */         ((Vector3f)this.vertices.getQuick(i)).set((Tuple3f)hr.outputVertices.getQuick(i));
/*     */       }
/* 107 */       this.numIndices = hr.numIndices;
/* 108 */       MiscUtil.resize(this.indices, this.numIndices, 0);
/* 109 */       for (int i = 0; i < this.numIndices; i++) {
/* 110 */         this.indices.set(i, hr.indices.get(i));
/*     */       }
/*     */ 
/* 114 */       hl.releaseResult(hr);
/*     */ 
/* 116 */       return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public int numTriangles() {
/* 120 */     return this.numIndices / 3;
/*     */   }
/*     */ 
/*     */   public int numVertices() {
/* 124 */     return this.vertices.size();
/*     */   }
/*     */ 
/*     */   public int numIndices() {
/* 128 */     return this.numIndices;
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<Vector3f> getVertexPointer() {
/* 132 */     return this.vertices;
/*     */   }
/*     */ 
/*     */   public IntArrayList getIndexPointer() {
/* 136 */     return this.indices;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 146 */     constUnitSpherePoints.add(new Vector3f(0.0F, -0.0F, -1.0F));
/* 147 */     constUnitSpherePoints.add(new Vector3f(0.723608F, -0.525725F, -0.447219F));
/* 148 */     constUnitSpherePoints.add(new Vector3f(-0.276388F, -0.850649F, -0.447219F));
/* 149 */     constUnitSpherePoints.add(new Vector3f(-0.894426F, -0.0F, -0.447216F));
/* 150 */     constUnitSpherePoints.add(new Vector3f(-0.276388F, 0.850649F, -0.44722F));
/* 151 */     constUnitSpherePoints.add(new Vector3f(0.723608F, 0.525725F, -0.447219F));
/* 152 */     constUnitSpherePoints.add(new Vector3f(0.276388F, -0.850649F, 0.44722F));
/* 153 */     constUnitSpherePoints.add(new Vector3f(-0.723608F, -0.525725F, 0.447219F));
/* 154 */     constUnitSpherePoints.add(new Vector3f(-0.723608F, 0.525725F, 0.447219F));
/* 155 */     constUnitSpherePoints.add(new Vector3f(0.276388F, 0.850649F, 0.447219F));
/* 156 */     constUnitSpherePoints.add(new Vector3f(0.894426F, 0.0F, 0.447216F));
/* 157 */     constUnitSpherePoints.add(new Vector3f(-0.0F, 0.0F, 1.0F));
/* 158 */     constUnitSpherePoints.add(new Vector3f(0.425323F, -0.309011F, -0.850654F));
/* 159 */     constUnitSpherePoints.add(new Vector3f(-0.162456F, -0.499995F, -0.850654F));
/* 160 */     constUnitSpherePoints.add(new Vector3f(0.262869F, -0.809012F, -0.525738F));
/* 161 */     constUnitSpherePoints.add(new Vector3f(0.425323F, 0.309011F, -0.850654F));
/* 162 */     constUnitSpherePoints.add(new Vector3f(0.850648F, -0.0F, -0.525736F));
/* 163 */     constUnitSpherePoints.add(new Vector3f(-0.52573F, -0.0F, -0.850652F));
/* 164 */     constUnitSpherePoints.add(new Vector3f(-0.68819F, -0.499997F, -0.525736F));
/* 165 */     constUnitSpherePoints.add(new Vector3f(-0.162456F, 0.499995F, -0.850654F));
/* 166 */     constUnitSpherePoints.add(new Vector3f(-0.68819F, 0.499997F, -0.525736F));
/* 167 */     constUnitSpherePoints.add(new Vector3f(0.262869F, 0.809012F, -0.525738F));
/* 168 */     constUnitSpherePoints.add(new Vector3f(0.951058F, 0.309013F, 0.0F));
/* 169 */     constUnitSpherePoints.add(new Vector3f(0.951058F, -0.309013F, 0.0F));
/* 170 */     constUnitSpherePoints.add(new Vector3f(0.587786F, -0.809017F, 0.0F));
/* 171 */     constUnitSpherePoints.add(new Vector3f(0.0F, -1.0F, 0.0F));
/* 172 */     constUnitSpherePoints.add(new Vector3f(-0.587786F, -0.809017F, 0.0F));
/* 173 */     constUnitSpherePoints.add(new Vector3f(-0.951058F, -0.309013F, -0.0F));
/* 174 */     constUnitSpherePoints.add(new Vector3f(-0.951058F, 0.309013F, -0.0F));
/* 175 */     constUnitSpherePoints.add(new Vector3f(-0.587786F, 0.809017F, -0.0F));
/* 176 */     constUnitSpherePoints.add(new Vector3f(-0.0F, 1.0F, -0.0F));
/* 177 */     constUnitSpherePoints.add(new Vector3f(0.587786F, 0.809017F, -0.0F));
/* 178 */     constUnitSpherePoints.add(new Vector3f(0.68819F, -0.499997F, 0.525736F));
/* 179 */     constUnitSpherePoints.add(new Vector3f(-0.262869F, -0.809012F, 0.525738F));
/* 180 */     constUnitSpherePoints.add(new Vector3f(-0.850648F, 0.0F, 0.525736F));
/* 181 */     constUnitSpherePoints.add(new Vector3f(-0.262869F, 0.809012F, 0.525738F));
/* 182 */     constUnitSpherePoints.add(new Vector3f(0.68819F, 0.499997F, 0.525736F));
/* 183 */     constUnitSpherePoints.add(new Vector3f(0.52573F, 0.0F, 0.850652F));
/* 184 */     constUnitSpherePoints.add(new Vector3f(0.162456F, -0.499995F, 0.850654F));
/* 185 */     constUnitSpherePoints.add(new Vector3f(-0.425323F, -0.309011F, 0.850654F));
/* 186 */     constUnitSpherePoints.add(new Vector3f(-0.425323F, 0.309011F, 0.850654F));
/* 187 */     constUnitSpherePoints.add(new Vector3f(0.162456F, 0.499995F, 0.850654F));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ShapeHull
 * JD-Core Version:    0.6.2
 */