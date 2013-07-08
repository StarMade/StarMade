/*   1:    */package org.schema.game.common.data.physics.octree;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import o;
/*   6:    */
/*  16:    */public class Octree
/*  17:    */{
/*  18: 18 */  public static OctreeVariableSet serverSet = new OctreeVariableSet();
/*  19: 19 */  public static OctreeVariableSet clientSet = new OctreeVariableSet();
/*  20:    */  
/*  22:    */  public static boolean dr;
/*  23:    */  
/*  25:    */  private static final byte ROOT_HALF_DIM = 8;
/*  26:    */  
/*  27:    */  private final OctreeNode root;
/*  28:    */  
/*  29: 29 */  public static void dr(boolean paramBoolean1, boolean paramBoolean2) { get(paramBoolean2).dr = paramBoolean1; }
/*  30:    */  
/*  31:    */  public static OctreeVariableSet get(boolean paramBoolean) {
/*  32: 32 */    if (paramBoolean) return serverSet; return clientSet;
/*  33:    */  }
/*  34:    */  
/*  40:    */  public static int getTreeCacheIndex(byte paramByte1, byte paramByte2, byte paramByte3)
/*  41:    */  {
/*  42: 42 */    return (paramByte3 << 8) + (paramByte2 << 4) + paramByte1;
/*  43:    */  }
/*  44:    */  
/*  48: 48 */  private int occTreeCount = 0;
/*  49:    */  
/*  53:    */  public Octree(int paramInt, boolean paramBoolean)
/*  54:    */  {
/*  55: 55 */    synchronized (get(paramBoolean))
/*  56:    */    {
/*  57: 57 */      if (get(paramBoolean).first)
/*  58:    */      {
/*  59: 59 */        get(paramBoolean).initializeCache();
/*  60: 60 */        assert (get(paramBoolean).maxLevel == 0);
/*  61: 61 */        get(paramBoolean).maxLevel = paramInt;
/*  62: 62 */        o localo1 = new o();
/*  63: 63 */        o localo2 = new o();
/*  64: 64 */        localo1.a((byte)-8, (byte)-8, (byte)-8);
/*  65: 65 */        localo2.a((byte)8, (byte)8, (byte)8);
/*  66: 66 */        System.err.println("[OCTREE] Building Octree");
/*  67: 67 */        this.root = new OctreeNode(localo1, localo2, 0, (byte)0, paramInt, paramBoolean);
/*  68: 68 */        buildOctree();
/*  69: 69 */        get(paramBoolean).first = false;
/*  70: 70 */        if (paramBoolean) {
/*  71: 71 */          System.err.println((paramBoolean ? "[SERVER]" : "[CLIENT]") + "[OCTREE] NODES: " + OctreeVariableSet.nodes);
/*  72:    */        }
/*  73:    */      } else {
/*  74: 74 */        assert (get(paramBoolean).maxLevel == paramInt);
/*  75: 75 */        this.root = new OctreeNode(0, (byte)0, paramInt, paramBoolean);
/*  76: 76 */        buildOctree();
/*  77:    */      }
/*  78: 78 */      return;
/*  79:    */    }
/*  80:    */  }
/*  81:    */  
/*  82: 82 */  private void buildOctree() { if (getRoot().getMaxLevel() > 0)
/*  83:    */    {
/*  84: 84 */      this.occTreeCount += getRoot().split(0, 0);
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  89:    */  public void delete(byte paramByte1, byte paramByte2, byte paramByte3)
/*  90:    */  {
/*  91: 91 */    int i = getTreeCacheIndex(paramByte1, paramByte2, paramByte3);
/*  92: 92 */    if (!this.root.getSet().treeCache[i].initialized) {
/*  93: 93 */      this.root.delete((byte)(paramByte1 - 8), (byte)(paramByte2 - 8), (byte)(paramByte3 - 8), this.root.getSet().treeCache[i], 0);
/*  94: 94 */      this.root.getSet().treeCache[i].initialized = true;return;
/*  95:    */    }
/*  96: 96 */    this.root.insertCached(this.root.getSet().treeCache[i], 0);
/*  97:    */  }
/*  98:    */  
/* 101:    */  public void drawOctree(Vector3f paramVector3f)
/* 102:    */  {
/* 103:103 */    this.root.drawOctree(paramVector3f, true);
/* 104:    */  }
/* 105:    */  
/* 106:106 */  public int getOccTreeCount() { return this.occTreeCount; }
/* 107:    */  
/* 108:    */  public OctreeNode getRoot() {
/* 109:109 */    return this.root;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void insert(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 113:113 */    int i = getTreeCacheIndex(paramByte1, paramByte2, paramByte3);
/* 114:    */    
/* 115:115 */    if (!this.root.getSet().treeCache[i].initialized) {
/* 116:116 */      this.root.insert((byte)(paramByte1 - 8), (byte)(paramByte2 - 8), (byte)(paramByte3 - 8), this.root.getSet().treeCache[i], 0);
/* 117:117 */      this.root.getSet().treeCache[i].initialized = true;return;
/* 118:    */    }
/* 119:119 */    this.root.insertCached(this.root.getSet().treeCache[i], 0);
/* 120:    */  }
/* 121:    */  
/* 123:    */  public void reset()
/* 124:    */  {
/* 125:125 */    this.root.reset();
/* 126:    */  }
/* 127:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.Octree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */