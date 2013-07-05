/*     */ package org.schema.game.common.data.physics.octree;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ 
/*     */ public class Octree
/*     */ {
/*  18 */   public static OctreeVariableSet serverSet = new OctreeVariableSet();
/*  19 */   public static OctreeVariableSet clientSet = new OctreeVariableSet();
/*     */   public static boolean dr;
/*     */   private static final byte ROOT_HALF_DIM = 8;
/*     */   private final OctreeNode root;
/*  48 */   private int occTreeCount = 0;
/*     */ 
/*     */   public static void dr(boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  29 */     get(paramBoolean2).dr = paramBoolean1;
/*     */   }
/*     */   public static OctreeVariableSet get(boolean paramBoolean) {
/*  32 */     if (paramBoolean) return serverSet; return clientSet;
/*     */   }
/*     */ 
/*     */   public static int getTreeCacheIndex(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/*  42 */     return (paramByte3 << 8) + (paramByte2 << 4) + paramByte1;
/*     */   }
/*     */ 
/*     */   public Octree(int paramInt, boolean paramBoolean)
/*     */   {
/*  55 */     synchronized (get(paramBoolean))
/*     */     {
/*  57 */       if (get(paramBoolean).first)
/*     */       {
/*  59 */         get(paramBoolean).initializeCache();
/*  60 */         assert (get(paramBoolean).maxLevel == 0);
/*  61 */         get(paramBoolean).maxLevel = paramInt;
/*  62 */         o localo1 = new o();
/*  63 */         o localo2 = new o();
/*  64 */         localo1.a((byte)-8, (byte)-8, (byte)-8);
/*  65 */         localo2.a((byte)8, (byte)8, (byte)8);
/*  66 */         System.err.println("[OCTREE] Building Octree");
/*  67 */         this.root = new OctreeNode(localo1, localo2, 0, (byte)0, paramInt, paramBoolean);
/*  68 */         buildOctree();
/*  69 */         get(paramBoolean).first = false;
/*  70 */         if (paramBoolean)
/*  71 */           System.err.println((paramBoolean ? "[SERVER]" : "[CLIENT]") + "[OCTREE] NODES: " + OctreeVariableSet.nodes);
/*     */       }
/*     */       else {
/*  74 */         assert (get(paramBoolean).maxLevel == paramInt);
/*  75 */         this.root = new OctreeNode(0, (byte)0, paramInt, paramBoolean);
/*  76 */         buildOctree();
/*     */       }
/*  78 */       return;
/*     */     }
/*     */   }
/*     */ 
/*  82 */   private void buildOctree() { if (getRoot().getMaxLevel() > 0)
/*     */     {
/*  84 */       this.occTreeCount += getRoot().split(0, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void delete(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/*  91 */     int i = getTreeCacheIndex(paramByte1, paramByte2, paramByte3);
/*  92 */     if (!this.root.getSet().treeCache[i].initialized) {
/*  93 */       this.root.delete((byte)(paramByte1 - 8), (byte)(paramByte2 - 8), (byte)(paramByte3 - 8), this.root.getSet().treeCache[i], 0);
/*  94 */       this.root.getSet().treeCache[i].initialized = true; return;
/*     */     }
/*  96 */     this.root.insertCached(this.root.getSet().treeCache[i], 0);
/*     */   }
/*     */ 
/*     */   public void drawOctree(Vector3f paramVector3f)
/*     */   {
/* 103 */     this.root.drawOctree(paramVector3f, true);
/*     */   }
/*     */   public int getOccTreeCount() {
/* 106 */     return this.occTreeCount;
/*     */   }
/*     */   public OctreeNode getRoot() {
/* 109 */     return this.root;
/*     */   }
/*     */ 
/*     */   public void insert(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 113 */     int i = getTreeCacheIndex(paramByte1, paramByte2, paramByte3);
/*     */ 
/* 115 */     if (!this.root.getSet().treeCache[i].initialized) {
/* 116 */       this.root.insert((byte)(paramByte1 - 8), (byte)(paramByte2 - 8), (byte)(paramByte3 - 8), this.root.getSet().treeCache[i], 0);
/* 117 */       this.root.getSet().treeCache[i].initialized = true; return;
/*     */     }
/* 119 */     this.root.insertCached(this.root.getSet().treeCache[i], 0);
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 125 */     this.root.reset();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.Octree
 * JD-Core Version:    0.6.2
 */