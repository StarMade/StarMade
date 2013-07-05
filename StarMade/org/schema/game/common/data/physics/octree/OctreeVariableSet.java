/*     */ package org.schema.game.common.data.physics.octree;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.HashMap;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.lwjgl.BufferUtils;
/*     */ 
/*     */ public class OctreeVariableSet
/*     */ {
/*     */   public boolean dr;
/*  15 */   boolean first = true;
/*  16 */   o min = new o();
/*  17 */   o max = new o();
/*  18 */   Vector3f tmpMin = new Vector3f();
/*  19 */   Vector3f tmpMax = new Vector3f();
/*  20 */   Vector3f tmpMinOut = new Vector3f();
/*  21 */   Vector3f tmpMaxOut = new Vector3f();
/*  22 */   Vector3f tmpMin2 = new Vector3f();
/*  23 */   Vector3f tmpMax2 = new Vector3f();
/*  24 */   Vector3f tmpMinOut2 = new Vector3f();
/*  25 */   Vector3f tmpMaxOut2 = new Vector3f();
/*  26 */   Vector3f tmpDistTest = new Vector3f();
/*     */   private boolean cacheInitialized;
/*     */   public static final int MAX_IDS = 4097;
/*  39 */   public TreeCache[] treeCache = new TreeCache[4096];
/*     */ 
/*  42 */   float[] param = new float[1];
/*     */ 
/*  44 */   Vector3f normal = new Vector3f();
/*     */ 
/*  46 */   short gen = 0;
/*     */ 
/*  48 */   private OctreeLevel tmp = new OctreeLevel();
/*  49 */   public HashMap map = new HashMap();
/*     */ 
/*  52 */   public ByteBuffer mapV = BufferUtils.createByteBuffer(7020);
/*     */   public int maxLevel;
/*  59 */   public Vector3f localHalfExtents = new Vector3f();
/*  60 */   public Vector3f localCenter = new Vector3f();
/*  61 */   public Matrix3f abs_b = new Matrix3f();
/*  62 */   public Vector3f center = new Vector3f();
/*  63 */   public Vector3f extend = new Vector3f();
/*  64 */   public Vector3f tmpAB = new Vector3f();
/*     */   public boolean debug;
/*  66 */   public final Vector3f aabbHalfExtent = new Vector3f();
/*  67 */   public final Vector3f aabbCenter = new Vector3f();
/*  68 */   public final Vector3f source = new Vector3f();
/*  69 */   public final Vector3f target = new Vector3f();
/*  70 */   public final Vector3f r = new Vector3f();
/*  71 */   public final Vector3f hitNormal = new Vector3f();
/*     */   public static Vector3f localHalfExtend;
/*     */   public static Vector3f[] localHalfExtends;
/*     */   public static Vector3f[] localCentersAdd;
/*     */   public static int nodes;
/*     */ 
/*     */   public void get(short paramShort, o paramo)
/*     */   {
/* 129 */     paramShort *= 3;
/* 130 */     paramo.b(this.mapV.get(paramShort), this.mapV.get(paramShort + 1), this.mapV.get(paramShort + 2));
/*     */   }
/*     */   public void get(short paramShort, Vector3f paramVector3f) {
/* 133 */     paramShort *= 3;
/* 134 */     paramVector3f.set(this.mapV.get(paramShort), this.mapV.get(paramShort + 1), this.mapV.get(paramShort + 2));
/*     */   }
/*     */   public byte getX(short paramShort) {
/* 137 */     return this.mapV.get(paramShort * 3);
/*     */   }
/*     */   public byte getY(short paramShort) {
/* 140 */     return this.mapV.get(paramShort * 3 + 1);
/*     */   }
/*     */   public byte getZ(short paramShort) {
/* 143 */     return this.mapV.get(paramShort * 3 + 2);
/*     */   }
/*     */ 
/*     */   public short getId(byte paramByte, int paramInt1, int paramInt2)
/*     */   {
/* 181 */     this.tmp.level = paramByte;
/* 182 */     this.tmp.index = paramInt1;
/* 183 */     this.tmp.id = paramInt2;
/*     */ 
/* 185 */     return ((Short)this.map.get(this.tmp)).shortValue();
/*     */   }
/*     */ 
/*     */   public void initializeCache() {
/* 188 */     if (!this.cacheInitialized)
/*     */     {
/* 190 */       for (int i = 0; i < this.treeCache.length; i++) {
/* 191 */         this.treeCache[i] = new TreeCache();
/*     */       }
/* 193 */       this.cacheInitialized = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public short put(byte paramByte, int paramInt1, int paramInt2, o paramo)
/*     */   {
/*     */     OctreeLevel localOctreeLevel;
/* 197 */     (
/* 198 */       localOctreeLevel = new OctreeLevel()).level = 
/* 198 */       paramByte;
/* 199 */     localOctreeLevel.index = paramInt1;
/* 200 */     localOctreeLevel.id = paramInt2;
/* 201 */     assert (!this.map.containsKey(localOctreeLevel)) : (paramByte + "; " + paramInt1 + "; " + paramInt2 + ": " + this.map);
/* 202 */     this.map.put(localOctreeLevel, Short.valueOf(this.gen));
/* 203 */     this.mapV.put(this.gen * 3, paramo.a);
/* 204 */     this.mapV.put(this.gen * 3 + 1, paramo.b);
/* 205 */     this.mapV.put(this.gen * 3 + 2, paramo.c);
/* 206 */     paramByte = this.gen;
/* 207 */     this.gen = ((short)(this.gen + 1));
/*     */ 
/* 209 */     return paramByte;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  73 */     localHalfExtend = new Vector3f();
/*  74 */     localHalfExtends = new Vector3f[4];
/*  75 */     localCentersAdd = new Vector3f[4];
/*     */ 
/*  78 */     int i = 8;
/*  79 */     for (int j = 0; j < localHalfExtends.length; j++) {
/*  80 */       Vector3f localVector3f1 = new Vector3f(-i, -i, -i);
/*  81 */       Vector3f localVector3f2 = new Vector3f(i, i, i);
/*     */ 
/*  83 */       localCentersAdd[j] = new Vector3f();
/*  84 */       localHalfExtends[j] = new Vector3f();
/*  85 */       localHalfExtends[j].sub(localVector3f2, localVector3f1);
/*  86 */       localHalfExtends[j].scale(0.5F);
/*     */ 
/*  88 */       localHalfExtends[j].x += 0.1F;
/*  89 */       localHalfExtends[j].y += 0.1F;
/*  90 */       localHalfExtends[j].z += 0.1F;
/*     */ 
/*  92 */       localCentersAdd[j].set(i, i, i);
/*  93 */       i /= 2;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeVariableSet
 * JD-Core Version:    0.6.2
 */