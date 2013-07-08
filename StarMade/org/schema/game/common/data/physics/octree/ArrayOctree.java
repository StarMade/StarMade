package org.schema.game.common.data.physics.octree;

import class_35;
import class_48;
import class_988;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.schema.game.common.data.world.Segment;

public class ArrayOctree
{
  private final short[] data = new short[NODES];
  private final boolean[] hits = new boolean[NODES];
  private final OctreeVariableSet set;
  static final int MAX_DEEPNESS = 2;
  static final int COUNT_LVLS = 3;
  static final int NODES;
  public static final short HIT_BIT = 16384;
  public static final int POSSIBLE_LEAF_HITS = 512;
  static final int BUFFER_SIZE;
  static ByteBuffer dimBuffer;
  static IntBuffer indexBuffer;
  private static OctreeVariableSet serverSet = new OctreeVariableSet();
  private static OctreeVariableSet clientSet = new OctreeVariableSet();
  
  public ArrayOctree(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.set = serverSet;
      return;
    }
    this.set = clientSet;
  }
  
  public static byte getStart(int paramInt1, int paramInt2)
  {
    paramInt2 *= 6;
    return dimBuffer.get(paramInt2 + paramInt1);
  }
  
  public static byte getEnd(int paramInt1, int paramInt2)
  {
    paramInt2 *= 6;
    return dimBuffer.get(paramInt2 + 3 + paramInt1);
  }
  
  public static void getBox(int paramInt, class_35 paramclass_351, class_35 paramclass_352)
  {
    paramInt *= 6;
    paramclass_351.b(dimBuffer.get(paramInt), dimBuffer.get(paramInt + 1), dimBuffer.get(paramInt + 2));
    paramclass_352.b(dimBuffer.get(paramInt + 3), dimBuffer.get(paramInt + 4), dimBuffer.get(paramInt + 5));
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.err.println(128);
  }
  
  public static int getIndex(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return paramInt1 + 1;
    }
    int i = 1;
    int j = 8;
    for (int k = 0; k < paramInt2; k++)
    {
      i += j;
      j <<= 3;
    }
    return i + paramInt1;
  }
  
  private static int getSuperIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    switch (paramInt4)
    {
    case 0: 
      return paramInt1;
    case 1: 
      return (paramInt1 << 3) + paramInt2;
    case 2: 
      return (paramInt1 << 3 << 3) + (paramInt2 << 3) + paramInt3;
    }
    throw new IllegalArgumentException();
  }
  
  public void insert(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    for (int i = 0; i < 3; i++)
    {
      int j = ArrayOctreeGenerator.getNodeIndex(paramByte1, paramByte2, paramByte3, i);
      if (i == 2)
      {
        int k = (paramByte3 % 2 << 2) + (paramByte2 % 2 << 1) + paramByte1 % 2;
        k = (short)(1 << k);
        int tmp55_53 = j;
        short[] tmp55_50 = this.data;
        tmp55_50[tmp55_53] = ((short)(tmp55_50[tmp55_53] | k));
        assert (this.data[j] > 0);
      }
      else
      {
        int tmp95_93 = j;
        short[] tmp95_90 = this.data;
        tmp95_90[tmp95_93] = ((short)(tmp95_90[tmp95_93] + 1));
      }
    }
  }
  
  public void delete(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    for (int i = 0; i < 3; i++)
    {
      int j = ArrayOctreeGenerator.getNodeIndex(paramByte1, paramByte2, paramByte3, i);
      if (i == 2)
      {
        int k = (paramByte3 % 2 << 2) + (paramByte2 % 2 << 1) + paramByte1 % 2;
        k = (short)(1 << k);
        int tmp55_53 = j;
        short[] tmp55_50 = this.data;
        tmp55_50[tmp55_53] = ((short)(tmp55_50[tmp55_53] & (k ^ 0xFFFFFFFF)));
      }
      else
      {
        int tmp73_71 = j;
        short[] tmp73_68 = this.data;
        tmp73_68[tmp73_71] = ((short)(tmp73_68[tmp73_71] - 1));
      }
      if (this.data[j] < 0)
      {
        System.err.println("[OCTREE] EXCEPTION: NODE < 0");
        this.data[j] = 0;
      }
    }
  }
  
  public void reset()
  {
    Arrays.fill(this.data, (short)0);
  }
  
  public void setHasHit(int paramInt, boolean paramBoolean)
  {
    this.hits[paramInt] = paramBoolean;
  }
  
  public boolean isHasHit(int paramInt)
  {
    return this.hits[paramInt];
  }
  
  public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    for (int i = 0; i < 8; i++) {
      paramIntersectionCallback = findIntersecting(i, 0, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
    }
    return paramIntersectionCallback;
  }
  
  private IntersectionCallback findIntersecting(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    int i = getIndex(paramInt1, paramInt2);
    if (this.data[i] > 0)
    {
      paramIntersectionCallback = doIntersecting(paramInt1, paramInt2, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
      if ((paramInt2 < 2) && (isHasHit(i)))
      {
        paramInt1 <<= 3;
        for (i = 0; i < 8; i++) {
          paramIntersectionCallback = findIntersecting(paramInt1 + i, paramInt2 + 1, paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2);
        }
      }
    }
    else
    {
      setHasHit(i, false);
    }
    return paramIntersectionCallback;
  }
  
  private IntersectionCallback doIntersecting(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    paramIntersectionCallback.leafCalcs += 1;
    getBox(paramInt1 = getIndex(paramInt1, paramInt2), paramOctreeVariableSet.min, paramOctreeVariableSet.max);
    paramFloat2 = paramOctreeVariableSet.tmpMin;
    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
    Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
    float f1 = paramSegment.field_34.field_475 - 0.5F;
    float f2 = paramSegment.field_34.field_476 - 0.5F;
    paramSegment = paramSegment.field_34.field_477 - 0.5F;
    paramFloat2.field_615 = (paramOctreeVariableSet.min.field_453 + f1);
    paramFloat2.field_616 = (paramOctreeVariableSet.min.field_454 + f2);
    paramFloat2.field_617 = (paramOctreeVariableSet.min.field_455 + paramSegment);
    localVector3f1.field_615 = (paramOctreeVariableSet.max.field_453 + f1);
    localVector3f1.field_616 = (paramOctreeVariableSet.max.field_454 + f2);
    localVector3f1.field_617 = (paramOctreeVariableSet.max.field_455 + paramSegment);
    transformAabb(paramOctreeVariableSet, paramInt2, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
    paramSegment = AabbUtil2.testAabbAgainstAabb2(localVector3f2, localVector3f3, paramVector3f1, paramVector3f2);
    setHasHit(paramInt1, paramSegment);
    if ((paramSegment != 0) && (paramInt2 == 2))
    {
      assert (this.data[paramInt1] > 0);
      paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.field_453, paramOctreeVariableSet.min.field_454, paramOctreeVariableSet.min.field_455, paramOctreeVariableSet.max.field_453, paramOctreeVariableSet.max.field_454, paramOctreeVariableSet.max.field_455, this.data[paramInt1]);
    }
    return paramIntersectionCallback;
  }
  
  public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    for (int i = 0; i < 8; i++) {
      paramIntersectionCallback = findIntersectingRay(i, 0, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
    }
    return paramIntersectionCallback;
  }
  
  private IntersectionCallback findIntersectingRay(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    int i = getIndex(paramInt1, paramInt2);
    if ((this.data[i] > 0) || (paramInt2 == 0))
    {
      paramIntersectionCallback = doIntersectingRay(paramInt1, paramInt2, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
      if ((paramInt2 < 2) && (isHasHit(i)))
      {
        paramInt1 <<= 3;
        for (i = 0; i < 8; i++) {
          paramIntersectionCallback = findIntersectingRay(paramInt1 + i, paramInt2 + 1, paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
        }
      }
    }
    else
    {
      setHasHit(i, false);
    }
    return paramIntersectionCallback;
  }
  
  private IntersectionCallback doIntersectingRay(int paramInt1, int paramInt2, OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    paramInt1 = getIndex(paramInt1, paramInt2);
    paramIntersectionCallback.leafCalcs += 1;
    paramFloat2 = paramOctreeVariableSet.tmpMin;
    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
    Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
    getBox(paramInt1, paramOctreeVariableSet.min, paramOctreeVariableSet.max);
    float f1 = paramSegment.field_34.field_475 - 0.5F;
    float f2 = paramSegment.field_34.field_476 - 0.5F;
    paramSegment = paramSegment.field_34.field_477 - 0.5F;
    paramFloat2.field_615 = (paramOctreeVariableSet.min.field_453 + f1);
    paramFloat2.field_616 = (paramOctreeVariableSet.min.field_454 + f2);
    paramFloat2.field_617 = (paramOctreeVariableSet.min.field_455 + paramSegment);
    localVector3f1.field_615 = (paramOctreeVariableSet.max.field_453 + f1);
    localVector3f1.field_616 = (paramOctreeVariableSet.max.field_454 + f2);
    localVector3f1.field_617 = (paramOctreeVariableSet.max.field_455 + paramSegment);
    transformAabb(paramOctreeVariableSet, paramInt2, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
    paramOctreeVariableSet.param[0] = 1.0F;
    paramOctreeVariableSet.normal.field_615 = 0.0F;
    paramOctreeVariableSet.normal.field_616 = 0.0F;
    paramOctreeVariableSet.normal.field_617 = 0.0F;
    paramTransform = rayAabb(paramVector3f1, paramVector3f2, localVector3f2, localVector3f3, paramOctreeVariableSet.param, paramOctreeVariableSet.normal);
    paramMatrix3f = 0;
    if (paramTransform == 0) {
      paramMatrix3f = (class_988.a(paramVector3f2, localVector3f2, localVector3f3)) || (class_988.a(paramVector3f1, localVector3f2, localVector3f3)) ? 1 : 0;
    }
    paramTransform = (paramTransform != 0) || (paramMatrix3f != 0) ? 1 : 0;
    setHasHit(paramInt1, paramTransform);
    if ((paramTransform != 0) && (paramInt2 == 2))
    {
      assert (this.data[paramInt1] > 0);
      paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.field_453, paramOctreeVariableSet.min.field_454, paramOctreeVariableSet.min.field_455, paramOctreeVariableSet.max.field_453, paramOctreeVariableSet.max.field_454, paramOctreeVariableSet.max.field_455, this.data[paramInt1]);
    }
    return paramIntersectionCallback;
  }
  
  private void transformAabb(OctreeVariableSet paramOctreeVariableSet, int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, Matrix3f paramMatrix3f, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    (paramInt = paramOctreeVariableSet.localCenter).add(paramVector3f2, paramVector3f1);
    paramInt.scale(0.5F);
    Vector3f localVector3f;
    (localVector3f = paramOctreeVariableSet.center).set(paramInt);
    paramTransform.transform(localVector3f);
    paramInt = paramOctreeVariableSet.extend;
    (paramOctreeVariableSet = paramOctreeVariableSet.localHalfExtents).sub(paramVector3f2, paramVector3f1);
    paramOctreeVariableSet.scale(0.5F);
    paramOctreeVariableSet.field_615 += paramFloat;
    paramOctreeVariableSet.field_616 += paramFloat;
    paramOctreeVariableSet.field_617 += paramFloat;
    paramInt.field_615 = (paramMatrix3f.m00 * paramOctreeVariableSet.field_615 + paramMatrix3f.m01 * paramOctreeVariableSet.field_616 + paramMatrix3f.m02 * paramOctreeVariableSet.field_617);
    paramInt.field_616 = (paramMatrix3f.m10 * paramOctreeVariableSet.field_615 + paramMatrix3f.m11 * paramOctreeVariableSet.field_616 + paramMatrix3f.m12 * paramOctreeVariableSet.field_617);
    paramInt.field_617 = (paramMatrix3f.m20 * paramOctreeVariableSet.field_615 + paramMatrix3f.m21 * paramOctreeVariableSet.field_616 + paramMatrix3f.m22 * paramOctreeVariableSet.field_617);
    paramVector3f3.sub(localVector3f, paramInt);
    paramVector3f4.add(localVector3f, paramInt);
  }
  
  public boolean rayAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, float[] paramArrayOfFloat, Vector3f paramVector3f5)
  {
    Vector3f localVector3f1 = this.set.aabbHalfExtent;
    Vector3f localVector3f2 = this.set.aabbCenter;
    Vector3f localVector3f3 = this.set.source;
    Vector3f localVector3f4 = this.set.target;
    Vector3f localVector3f6 = this.set.field_1686;
    Vector3f localVector3f7 = this.set.hitNormal;
    localVector3f1.sub(paramVector3f4, paramVector3f3);
    localVector3f1.scale(0.5F);
    localVector3f2.add(paramVector3f4, paramVector3f3);
    localVector3f2.scale(0.5F);
    localVector3f3.sub(paramVector3f1, localVector3f2);
    localVector3f4.sub(paramVector3f2, localVector3f2);
    paramVector3f1 = AabbUtil2.outcode(localVector3f3, localVector3f1);
    paramVector3f2 = AabbUtil2.outcode(localVector3f4, localVector3f1);
    if ((paramVector3f1 & paramVector3f2) == 0)
    {
      paramVector3f3 = 0.0F;
      paramVector3f4 = paramArrayOfFloat[0];
      localVector3f6.sub(localVector3f4, localVector3f3);
      float f1 = 1.0F;
      localVector3f7.set(0.0F, 0.0F, 0.0F);
      Vector3f localVector3f5 = 1;
      for (int j = 0; j < 2; j++)
      {
        for (int k = 0; k != 3; k++)
        {
          float f2;
          if ((paramVector3f1 & localVector3f5) != 0)
          {
            f2 = (-VectorUtil.getCoord(localVector3f3, k) - VectorUtil.getCoord(localVector3f1, k) * f1) / VectorUtil.getCoord(localVector3f6, k);
            if (paramVector3f3 <= f2)
            {
              paramVector3f3 = f2;
              localVector3f7.set(0.0F, 0.0F, 0.0F);
              VectorUtil.setCoord(localVector3f7, k, f1);
            }
          }
          else if ((paramVector3f2 & localVector3f5) != 0)
          {
            f2 = (-VectorUtil.getCoord(localVector3f3, k) - VectorUtil.getCoord(localVector3f1, k) * f1) / VectorUtil.getCoord(localVector3f6, k);
            paramVector3f4 = Math.min(paramVector3f4, f2);
          }
          int i;
          localVector3f5 <<= 1;
        }
        f1 = -1.0F;
      }
      if (paramVector3f3 <= paramVector3f4)
      {
        paramArrayOfFloat[0] = paramVector3f3;
        paramVector3f5.set(localVector3f7);
        return true;
      }
    }
    return false;
  }
  
  public OctreeVariableSet getSet()
  {
    return this.set;
  }
  
  public static OctreeVariableSet getSet(boolean paramBoolean)
  {
    if (paramBoolean) {
      return serverSet;
    }
    return clientSet;
  }
  
  static
  {
    NODES = 585;
    dimBuffer = BufferUtils.createByteBuffer(ArrayOctree.BUFFER_SIZE = 3510);
    indexBuffer = BufferUtils.createIntBuffer(12288);
    ArrayOctreeGenerator.splitStart(new class_35(-8.0F, -8.0F, -8.0F), new class_35(8.0F, 8.0F, 8.0F), (byte)8);
    ArrayOctreeTraverse.create();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */