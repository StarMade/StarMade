/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class BU_Simplex1to4 extends PolyhedralConvexShape
/*     */ {
/*  37 */   protected int numVertices = 0;
/*  38 */   protected Vector3f[] vertices = new Vector3f[4];
/*     */ 
/*     */   public BU_Simplex1to4() {
/*     */   }
/*     */ 
/*     */   public BU_Simplex1to4(Vector3f pt0) {
/*  44 */     addVertex(pt0);
/*     */   }
/*     */ 
/*     */   public BU_Simplex1to4(Vector3f pt0, Vector3f pt1) {
/*  48 */     addVertex(pt0);
/*  49 */     addVertex(pt1);
/*     */   }
/*     */ 
/*     */   public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2) {
/*  53 */     addVertex(pt0);
/*  54 */     addVertex(pt1);
/*  55 */     addVertex(pt2);
/*     */   }
/*     */ 
/*     */   public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2, Vector3f pt3) {
/*  59 */     addVertex(pt0);
/*  60 */     addVertex(pt1);
/*  61 */     addVertex(pt2);
/*  62 */     addVertex(pt3);
/*     */   }
/*     */ 
/*     */   public void reset() {
/*  66 */     this.numVertices = 0;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/*  71 */     return BroadphaseNativeType.TETRAHEDRAL_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void addVertex(Vector3f pt) {
/*  75 */     if (this.vertices[this.numVertices] == null) {
/*  76 */       this.vertices[this.numVertices] = new Vector3f();
/*     */     }
/*     */ 
/*  79 */     this.vertices[(this.numVertices++)] = pt;
/*     */ 
/*  81 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public int getNumVertices()
/*     */   {
/*  87 */     return this.numVertices;
/*     */   }
/*     */ 
/*     */   public int getNumEdges()
/*     */   {
/*  94 */     switch (this.numVertices) { case 0:
/*  95 */       return 0;
/*     */     case 1:
/*  96 */       return 0;
/*     */     case 2:
/*  97 */       return 1;
/*     */     case 3:
/*  98 */       return 3;
/*     */     case 4:
/*  99 */       return 6;
/*     */     }
/*     */ 
/* 102 */     return 0;
/*     */   }
/*     */ 
/*     */   public void getEdge(int i, Vector3f pa, Vector3f pb)
/*     */   {
/* 107 */     switch (this.numVertices) {
/*     */     case 2:
/* 109 */       pa.set(this.vertices[0]);
/* 110 */       pb.set(this.vertices[1]);
/* 111 */       break;
/*     */     case 3:
/* 113 */       switch (i) {
/*     */       case 0:
/* 115 */         pa.set(this.vertices[0]);
/* 116 */         pb.set(this.vertices[1]);
/* 117 */         break;
/*     */       case 1:
/* 119 */         pa.set(this.vertices[1]);
/* 120 */         pb.set(this.vertices[2]);
/* 121 */         break;
/*     */       case 2:
/* 123 */         pa.set(this.vertices[2]);
/* 124 */         pb.set(this.vertices[0]);
/*     */       }
/*     */ 
/* 127 */       break;
/*     */     case 4:
/* 129 */       switch (i) {
/*     */       case 0:
/* 131 */         pa.set(this.vertices[0]);
/* 132 */         pb.set(this.vertices[1]);
/* 133 */         break;
/*     */       case 1:
/* 135 */         pa.set(this.vertices[1]);
/* 136 */         pb.set(this.vertices[2]);
/* 137 */         break;
/*     */       case 2:
/* 139 */         pa.set(this.vertices[2]);
/* 140 */         pb.set(this.vertices[0]);
/* 141 */         break;
/*     */       case 3:
/* 143 */         pa.set(this.vertices[0]);
/* 144 */         pb.set(this.vertices[3]);
/* 145 */         break;
/*     */       case 4:
/* 147 */         pa.set(this.vertices[1]);
/* 148 */         pb.set(this.vertices[3]);
/* 149 */         break;
/*     */       case 5:
/* 151 */         pa.set(this.vertices[2]);
/* 152 */         pb.set(this.vertices[3]);
/*     */       }
/*     */       break;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void getVertex(int i, Vector3f vtx)
/*     */   {
/* 160 */     vtx.set(this.vertices[i]);
/*     */   }
/*     */ 
/*     */   public int getNumPlanes()
/*     */   {
/* 165 */     switch (this.numVertices) { case 0:
/* 166 */       return 0;
/*     */     case 1:
/* 167 */       return 0;
/*     */     case 2:
/* 168 */       return 0;
/*     */     case 3:
/* 169 */       return 2;
/*     */     case 4:
/* 170 */       return 4;
/*     */     }
/* 172 */     return 0;
/*     */   }
/*     */ 
/*     */   public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getIndex(int i) {
/* 180 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean isInside(Vector3f pt, float tolerance)
/*     */   {
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 190 */     return "BU_Simplex1to4";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BU_Simplex1to4
 * JD-Core Version:    0.6.2
 */