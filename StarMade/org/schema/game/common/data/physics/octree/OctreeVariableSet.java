package org.schema.game.common.data.physics.octree;

import class_35;
import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;

public class OctreeVariableSet
{
  public boolean field_1685;
  boolean first = true;
  class_35 min = new class_35();
  class_35 max = new class_35();
  Vector3f tmpMin = new Vector3f();
  Vector3f tmpMax = new Vector3f();
  Vector3f tmpMinOut = new Vector3f();
  Vector3f tmpMaxOut = new Vector3f();
  Vector3f tmpMin2 = new Vector3f();
  Vector3f tmpMax2 = new Vector3f();
  Vector3f tmpMinOut2 = new Vector3f();
  Vector3f tmpMaxOut2 = new Vector3f();
  Vector3f tmpDistTest = new Vector3f();
  private boolean cacheInitialized;
  public static final int MAX_IDS = 4097;
  public TreeCache[] treeCache = new TreeCache[4096];
  float[] param = new float[1];
  Vector3f normal = new Vector3f();
  short gen = 0;
  private OctreeLevel tmp = new OctreeLevel();
  public HashMap map = new HashMap();
  public ByteBuffer mapV = BufferUtils.createByteBuffer(7020);
  public int maxLevel;
  public Vector3f localHalfExtents = new Vector3f();
  public Vector3f localCenter = new Vector3f();
  public Matrix3f abs_b = new Matrix3f();
  public Vector3f center = new Vector3f();
  public Vector3f extend = new Vector3f();
  public Vector3f tmpAB = new Vector3f();
  public boolean debug;
  public final Vector3f aabbHalfExtent = new Vector3f();
  public final Vector3f aabbCenter = new Vector3f();
  public final Vector3f source = new Vector3f();
  public final Vector3f target = new Vector3f();
  public final Vector3f field_1686 = new Vector3f();
  public final Vector3f hitNormal = new Vector3f();
  public static Vector3f localHalfExtend;
  public static Vector3f[] localHalfExtends;
  public static Vector3f[] localCentersAdd;
  public static int nodes;
  
  public void get(short paramShort, class_35 paramclass_35)
  {
    paramShort *= 3;
    paramclass_35.b(this.mapV.get(paramShort), this.mapV.get(paramShort + 1), this.mapV.get(paramShort + 2));
  }
  
  public void get(short paramShort, Vector3f paramVector3f)
  {
    paramShort *= 3;
    paramVector3f.set(this.mapV.get(paramShort), this.mapV.get(paramShort + 1), this.mapV.get(paramShort + 2));
  }
  
  public byte getX(short paramShort)
  {
    return this.mapV.get(paramShort * 3);
  }
  
  public byte getY(short paramShort)
  {
    return this.mapV.get(paramShort * 3 + 1);
  }
  
  public byte getZ(short paramShort)
  {
    return this.mapV.get(paramShort * 3 + 2);
  }
  
  public short getId(byte paramByte, int paramInt1, int paramInt2)
  {
    this.tmp.level = paramByte;
    this.tmp.index = paramInt1;
    this.tmp.field_1828 = paramInt2;
    return ((Short)this.map.get(this.tmp)).shortValue();
  }
  
  public void initializeCache()
  {
    if (!this.cacheInitialized)
    {
      for (int i = 0; i < this.treeCache.length; i++) {
        this.treeCache[i] = new TreeCache();
      }
      this.cacheInitialized = true;
    }
  }
  
  public short put(byte paramByte, int paramInt1, int paramInt2, class_35 paramclass_35)
  {
    OctreeLevel localOctreeLevel;
    (localOctreeLevel = new OctreeLevel()).level = paramByte;
    localOctreeLevel.index = paramInt1;
    localOctreeLevel.field_1828 = paramInt2;
    assert (!this.map.containsKey(localOctreeLevel)) : (paramByte + "; " + paramInt1 + "; " + paramInt2 + ": " + this.map);
    this.map.put(localOctreeLevel, Short.valueOf(this.gen));
    this.mapV.put(this.gen * 3, paramclass_35.field_453);
    this.mapV.put(this.gen * 3 + 1, paramclass_35.field_454);
    this.mapV.put(this.gen * 3 + 2, paramclass_35.field_455);
    paramByte = this.gen;
    this.gen = ((short)(this.gen + 1));
    return paramByte;
  }
  
  static
  {
    localHalfExtend = new Vector3f();
    localHalfExtends = new Vector3f[4];
    localCentersAdd = new Vector3f[4];
    int i = 8;
    for (int j = 0; j < localHalfExtends.length; j++)
    {
      Vector3f localVector3f1 = new Vector3f(-i, -i, -i);
      Vector3f localVector3f2 = new Vector3f(i, i, i);
      localCentersAdd[j] = new Vector3f();
      localHalfExtends[j] = new Vector3f();
      localHalfExtends[j].sub(localVector3f2, localVector3f1);
      localHalfExtends[j].scale(0.5F);
      localHalfExtends[j].field_615 += 0.1F;
      localHalfExtends[j].field_616 += 0.1F;
      localHalfExtends[j].field_617 += 0.1F;
      localCentersAdd[j].set(i, i, i);
      i /= 2;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeVariableSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */