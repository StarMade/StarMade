package org.schema.game.common.data.physics.octree;

import class_35;
import java.io.PrintStream;
import javax.vecmath.Vector3f;

public class Octree
{
  public static OctreeVariableSet serverSet = new OctreeVariableSet();
  public static OctreeVariableSet clientSet = new OctreeVariableSet();
  public static boolean field_1768;
  private static final byte ROOT_HALF_DIM = 8;
  private final OctreeNode root;
  private int occTreeCount = 0;
  
  public static void dr(boolean paramBoolean1, boolean paramBoolean2)
  {
    get(paramBoolean2).field_1685 = paramBoolean1;
  }
  
  public static OctreeVariableSet get(boolean paramBoolean)
  {
    if (paramBoolean) {
      return serverSet;
    }
    return clientSet;
  }
  
  public static int getTreeCacheIndex(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return (paramByte3 << 8) + (paramByte2 << 4) + paramByte1;
  }
  
  public Octree(int paramInt, boolean paramBoolean)
  {
    synchronized (get(paramBoolean))
    {
      if (get(paramBoolean).first)
      {
        get(paramBoolean).initializeCache();
        assert (get(paramBoolean).maxLevel == 0);
        get(paramBoolean).maxLevel = paramInt;
        class_35 localclass_351 = new class_35();
        class_35 localclass_352 = new class_35();
        localclass_351.a((byte)-8, (byte)-8, (byte)-8);
        localclass_352.a((byte)8, (byte)8, (byte)8);
        System.err.println("[OCTREE] Building Octree");
        this.root = new OctreeNode(localclass_351, localclass_352, 0, (byte)0, paramInt, paramBoolean);
        buildOctree();
        get(paramBoolean).first = false;
        if (paramBoolean) {
          System.err.println((paramBoolean ? "[SERVER]" : "[CLIENT]") + "[OCTREE] NODES: " + OctreeVariableSet.nodes);
        }
      }
      else
      {
        assert (get(paramBoolean).maxLevel == paramInt);
        this.root = new OctreeNode(0, (byte)0, paramInt, paramBoolean);
        buildOctree();
      }
      return;
    }
  }
  
  private void buildOctree()
  {
    if (getRoot().getMaxLevel() > 0) {
      this.occTreeCount += getRoot().split(0, 0);
    }
  }
  
  public void delete(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    int i = getTreeCacheIndex(paramByte1, paramByte2, paramByte3);
    if (!this.root.getSet().treeCache[i].initialized)
    {
      this.root.delete((byte)(paramByte1 - 8), (byte)(paramByte2 - 8), (byte)(paramByte3 - 8), this.root.getSet().treeCache[i], 0);
      this.root.getSet().treeCache[i].initialized = true;
      return;
    }
    this.root.insertCached(this.root.getSet().treeCache[i], 0);
  }
  
  public void drawOctree(Vector3f paramVector3f)
  {
    this.root.drawOctree(paramVector3f, true);
  }
  
  public int getOccTreeCount()
  {
    return this.occTreeCount;
  }
  
  public OctreeNode getRoot()
  {
    return this.root;
  }
  
  public void insert(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    int i = getTreeCacheIndex(paramByte1, paramByte2, paramByte3);
    if (!this.root.getSet().treeCache[i].initialized)
    {
      this.root.insert((byte)(paramByte1 - 8), (byte)(paramByte2 - 8), (byte)(paramByte3 - 8), this.root.getSet().treeCache[i], 0);
      this.root.getSet().treeCache[i].initialized = true;
      return;
    }
    this.root.insertCached(this.root.getSet().treeCache[i], 0);
  }
  
  public void reset()
  {
    this.root.reset();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.Octree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */