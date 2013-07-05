/*     */ package org.schema.game.common.data.physics.octree;
/*     */ 
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.schema.game.common.data.physics.BoxShapeExt;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ public class OctreeNode extends OctreeLeaf
/*     */ {
/*     */   private OctreeLeaf[] children;
/*     */ 
/*     */   public OctreeNode(int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*     */   {
/*  20 */     super(paramInt1, paramByte, paramInt2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public OctreeNode(o paramo1, o paramo2, int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
/*     */   {
/*  25 */     super(paramo1, paramo2, paramInt1, paramByte, paramInt2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void delete(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
/*     */   {
/*  31 */     super.delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt);
/*     */ 
/*  33 */     if ((paramByte2 >= getStartX()) && (paramByte2 < getEndY() - getHalfDimY()))
/*     */     {
/*  35 */       if ((paramByte1 >= getStartX()) && (paramByte1 < getEndX() - getHalfDimX())) {
/*  36 */         if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  37 */           paramTreeCache.lvlToIndex[paramInt] = 0;
/*     */ 
/*  39 */           this.children[0].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */         }
/*  41 */         paramTreeCache.lvlToIndex[paramInt] = 3;
/*     */ 
/*  43 */         this.children[3].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */       }
/*     */ 
/*  46 */       if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  47 */         paramTreeCache.lvlToIndex[paramInt] = 1;
/*     */ 
/*  49 */         this.children[1].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */       }
/*  51 */       paramTreeCache.lvlToIndex[paramInt] = 2;
/*     */ 
/*  53 */       this.children[2].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */     }
/*     */ 
/*  58 */     if ((paramByte1 >= getStartX()) && (paramByte1 < getEndX() - getHalfDimX())) {
/*  59 */       if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  60 */         paramTreeCache.lvlToIndex[paramInt] = 4;
/*     */ 
/*  62 */         this.children[4].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */       }
/*  64 */       paramTreeCache.lvlToIndex[paramInt] = 7;
/*     */ 
/*  66 */       this.children[7].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */     }
/*     */ 
/*  69 */     if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ())) {
/*  70 */       paramTreeCache.lvlToIndex[paramInt] = 5;
/*     */ 
/*  72 */       this.children[5].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */     }
/*  74 */     paramTreeCache.lvlToIndex[paramInt] = 6;
/*     */ 
/*  76 */     this.children[6].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
/*     */   }
/*     */ 
/*     */   public void deleteCached(TreeCache paramTreeCache, int paramInt)
/*     */   {
/*  83 */     super.deleteCached(paramTreeCache, paramInt + 1);
/*  84 */     this.children[paramTreeCache.lvlToIndex[paramInt]].deleteCached(paramTreeCache, paramInt + 1);
/*     */   }
/*     */ 
/*     */   public void drawOctree(Vector3f paramVector3f, boolean paramBoolean)
/*     */   {
/*  91 */     if (isHasHit()) {
/*  92 */       super.drawOctree(paramVector3f, paramBoolean);
/*  93 */       for (paramBoolean = false; paramBoolean < this.children.length; paramBoolean++)
/*  94 */         this.children[paramBoolean].drawOctree(paramVector3f, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
/*     */   {
/* 106 */     paramIntersectionCallback = doIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
/*     */ 
/* 109 */     if (isHasHit()) {
/* 110 */       for (int i = 0; i < this.children.length; i++) {
/* 111 */         if (!this.children[i].isEmpty())
/* 112 */           paramIntersectionCallback = this.children[i].findIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
/*     */         else {
/* 114 */           this.children[i].setHasHit(false);
/*     */         }
/*     */       }
/*     */     }
/* 118 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersectingCast(IntersectionCallback paramIntersectionCallback, Transform paramTransform1, BoxShapeExt paramBoxShapeExt, ConvexShape paramConvexShape, float paramFloat1, Segment paramSegment, Transform paramTransform2, Transform paramTransform3, float paramFloat2)
/*     */   {
/* 127 */     paramIntersectionCallback = super.findIntersectingCast(paramIntersectionCallback, paramTransform1, paramBoxShapeExt, paramConvexShape, paramFloat1, paramSegment, paramTransform2, paramTransform3, paramFloat2);
/*     */ 
/* 129 */     if (isHasHit())
/*     */     {
/* 131 */       for (int i = 0; i < this.children.length; i++) {
/* 132 */         paramIntersectionCallback = this.children[i].findIntersectingCast(paramIntersectionCallback, paramTransform1, paramBoxShapeExt, paramConvexShape, paramFloat1, paramSegment, paramTransform2, paramTransform3, paramFloat2);
/*     */       }
/*     */     }
/* 135 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
/*     */   {
/* 142 */     paramIntersectionCallback = super.findIntersectingRay(paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/*     */ 
/* 144 */     if (isHasHit())
/*     */     {
/* 146 */       for (int i = 0; i < this.children.length; i++) {
/* 147 */         if (!this.children[i].isEmpty())
/* 148 */           paramIntersectionCallback = this.children[i].findIntersectingRay(paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
/*     */         else {
/* 150 */           this.children[i].setHasHit(false);
/*     */         }
/*     */       }
/*     */     }
/* 154 */     return paramIntersectionCallback;
/*     */   }
/*     */ 
/*     */   public void insert(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt) {
/* 158 */     super.insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
/*     */ 
/* 161 */     byte b1 = getStartX();
/* 162 */     byte b2 = getStartY();
/* 163 */     byte b3 = getStartZ();
/*     */ 
/* 165 */     int i = getEndX();
/* 166 */     int j = getEndY();
/* 167 */     int k = getEndZ();
/*     */ 
/* 170 */     if ((paramByte2 >= b2) && (paramByte2 < j - getHalfDimY()))
/*     */     {
/* 173 */       if ((paramByte1 >= b1) && (paramByte1 < i - getHalfDimX())) {
/* 174 */         if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 175 */           paramTreeCache.lvlToIndex[paramInt] = 0;
/*     */ 
/* 177 */           this.children[0].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */         }
/* 179 */         paramTreeCache.lvlToIndex[paramInt] = 3;
/*     */ 
/* 181 */         this.children[3].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */       }
/*     */ 
/* 184 */       if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 185 */         paramTreeCache.lvlToIndex[paramInt] = 1;
/*     */ 
/* 187 */         this.children[1].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */       }
/* 189 */       paramTreeCache.lvlToIndex[paramInt] = 2;
/*     */ 
/* 191 */       this.children[2].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */     }
/*     */ 
/* 197 */     if ((paramByte1 >= b1) && (paramByte1 < i - getHalfDimX())) {
/* 198 */       if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 199 */         paramTreeCache.lvlToIndex[paramInt] = 4;
/*     */ 
/* 201 */         this.children[4].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */       }
/* 203 */       paramTreeCache.lvlToIndex[paramInt] = 7;
/*     */ 
/* 205 */       this.children[7].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */     }
/*     */ 
/* 208 */     if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ())) {
/* 209 */       paramTreeCache.lvlToIndex[paramInt] = 5;
/*     */ 
/* 211 */       this.children[5].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1); return;
/*     */     }
/* 213 */     paramTreeCache.lvlToIndex[paramInt] = 6;
/*     */ 
/* 215 */     this.children[6].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
/*     */   }
/*     */ 
/*     */   public void insertCached(TreeCache paramTreeCache, int paramInt)
/*     */   {
/* 222 */     super.insertCached(paramTreeCache, paramInt + 1);
/* 223 */     this.children[paramTreeCache.lvlToIndex[paramInt]].insertCached(paramTreeCache, paramInt + 1);
/*     */   }
/*     */ 
/*     */   public void reset() {
/* 227 */     super.reset();
/* 228 */     for (int i = 0; i < this.children.length; i++)
/* 229 */       this.children[i].reset();
/*     */   }
/*     */ 
/*     */   protected boolean isLeaf()
/*     */   {
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */   public int split(int paramInt1, int paramInt2)
/*     */   {
/* 242 */     int i = 1;
/* 243 */     this.children = new OctreeLeaf[8];
/*     */     int j;
/* 245 */     if (getSet().first) {
/* 246 */       o localo1 = getStart(new o());
/*     */       o localo2;
/* 247 */       (
/* 248 */         localo2 = getHalfDim(new o()))
/* 248 */         .a(localo1);
/*     */ 
/* 250 */       o localo3 = new o(localo1);
/* 251 */       o localo4 = new o(localo2);
/* 252 */       localo3.a(getHalfDimX(), (byte)0, (byte)0);
/* 253 */       localo4.a(getHalfDimX(), (byte)0, (byte)0);
/*     */ 
/* 255 */       o localo5 = new o(localo1);
/* 256 */       o localo6 = new o(localo2);
/* 257 */       localo5.a(getHalfDimX(), (byte)0, getHalfDimZ());
/* 258 */       localo6.a(getHalfDimX(), (byte)0, getHalfDimZ());
/*     */ 
/* 260 */       o localo7 = new o(localo1);
/* 261 */       o localo8 = new o(localo2);
/* 262 */       localo7.a((byte)0, (byte)0, getHalfDimZ());
/* 263 */       localo8.a((byte)0, (byte)0, getHalfDimZ());
/*     */ 
/* 265 */       o localo9 = new o(localo1);
/* 266 */       o localo10 = new o(localo2);
/* 267 */       localo9.a((byte)0, getHalfDimY(), (byte)0);
/* 268 */       localo10.a((byte)0, getHalfDimY(), (byte)0);
/*     */ 
/* 270 */       o localo11 = new o(localo1);
/* 271 */       o localo12 = new o(localo2);
/* 272 */       localo11.a(getHalfDimX(), getHalfDimY(), (byte)0);
/* 273 */       localo12.a(getHalfDimX(), getHalfDimY(), (byte)0);
/*     */ 
/* 275 */       o localo13 = new o(localo1);
/* 276 */       o localo14 = new o(localo2);
/* 277 */       localo13.a(getHalfDimX(), getHalfDimY(), getHalfDimZ());
/* 278 */       localo14.a(getHalfDimX(), getHalfDimY(), getHalfDimZ());
/*     */ 
/* 280 */       o localo15 = new o(localo1);
/* 281 */       o localo16 = new o(localo2);
/* 282 */       localo15.a((byte)0, getHalfDimY(), getHalfDimZ());
/* 283 */       localo16.a((byte)0, getHalfDimY(), getHalfDimZ());
/*     */ 
/* 287 */       if (paramInt2 < getMaxLevel()) {
/* 288 */         this.children[0] = new OctreeNode(localo1, localo2, paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 289 */         this.children[1] = new OctreeNode(localo3, localo4, (paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 290 */         this.children[2] = new OctreeNode(localo5, localo6, (paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 291 */         this.children[3] = new OctreeNode(localo7, localo8, (paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 292 */         this.children[4] = new OctreeNode(localo9, localo10, (paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 293 */         this.children[5] = new OctreeNode(localo11, localo12, (paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 294 */         this.children[6] = new OctreeNode(localo13, localo14, (paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 295 */         this.children[7] = new OctreeNode(localo15, localo16, (paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 296 */         for (j = 0; j < this.children.length; j++)
/* 297 */           i += ((OctreeNode)this.children[j]).split((paramInt1 << 3) + j, paramInt2 + 1);
/*     */       }
/*     */       else {
/* 300 */         this.children[0] = new OctreeLeaf(j, localo2, paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 301 */         this.children[1] = new OctreeLeaf(localo3, localo4, (paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 302 */         this.children[2] = new OctreeLeaf(localo5, localo6, (paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 303 */         this.children[3] = new OctreeLeaf(localo7, localo8, (paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 304 */         this.children[4] = new OctreeLeaf(localo9, localo10, (paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 305 */         this.children[5] = new OctreeLeaf(localo11, localo12, (paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 306 */         this.children[6] = new OctreeLeaf(localo13, localo14, (paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 307 */         this.children[7] = new OctreeLeaf(localo15, localo16, (paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 308 */         i += 8;
/*     */       }
/*     */     }
/* 311 */     else if (paramInt2 < getMaxLevel()) {
/* 312 */       this.children[0] = new OctreeNode(paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 313 */       this.children[1] = new OctreeNode((paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 314 */       this.children[2] = new OctreeNode((paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 315 */       this.children[3] = new OctreeNode((paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 316 */       this.children[4] = new OctreeNode((paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 317 */       this.children[5] = new OctreeNode((paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 318 */       this.children[6] = new OctreeNode((paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 319 */       this.children[7] = new OctreeNode((paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 320 */       for (j = 0; j < this.children.length; j++)
/* 321 */         i += ((OctreeNode)this.children[j]).split((paramInt1 << 3) + j, paramInt2 + 1);
/*     */     }
/*     */     else {
/* 324 */       this.children[0] = new OctreeLeaf(paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 325 */       this.children[1] = new OctreeLeaf((paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 326 */       this.children[2] = new OctreeLeaf((paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 327 */       this.children[3] = new OctreeLeaf((paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 328 */       this.children[4] = new OctreeLeaf((paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 329 */       this.children[5] = new OctreeLeaf((paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 330 */       this.children[6] = new OctreeLeaf((paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 331 */       this.children[7] = new OctreeLeaf((paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
/* 332 */       i += 8;
/*     */     }
/*     */ 
/* 335 */     return i;
/*     */   }
/*     */ 
/*     */   public OctreeLeaf[] getChildren()
/*     */   {
/* 343 */     return this.children;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeNode
 * JD-Core Version:    0.6.2
 */