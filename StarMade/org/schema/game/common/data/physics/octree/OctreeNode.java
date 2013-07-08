package org.schema.game.common.data.physics.octree;

import class_35;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.physics.BoxShapeExt;
import org.schema.game.common.data.world.Segment;

public class OctreeNode
  extends OctreeLeaf
{
  private OctreeLeaf[] children;
  
  public OctreeNode(int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
  {
    super(paramInt1, paramByte, paramInt2, paramBoolean);
  }
  
  public OctreeNode(class_35 paramclass_351, class_35 paramclass_352, int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
  {
    super(paramclass_351, paramclass_352, paramInt1, paramByte, paramInt2, paramBoolean);
  }
  
  public void delete(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
  {
    super.delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt);
    if ((paramByte2 >= getStartX()) && (paramByte2 < getEndY() - getHalfDimY()))
    {
      if ((paramByte1 >= getStartX()) && (paramByte1 < getEndX() - getHalfDimX()))
      {
        if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ()))
        {
          paramTreeCache.lvlToIndex[paramInt] = 0;
          this.children[0].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
          return;
        }
        paramTreeCache.lvlToIndex[paramInt] = 3;
        this.children[3].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
        return;
      }
      if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ()))
      {
        paramTreeCache.lvlToIndex[paramInt] = 1;
        this.children[1].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
        return;
      }
      paramTreeCache.lvlToIndex[paramInt] = 2;
      this.children[2].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
      return;
    }
    if ((paramByte1 >= getStartX()) && (paramByte1 < getEndX() - getHalfDimX()))
    {
      if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ()))
      {
        paramTreeCache.lvlToIndex[paramInt] = 4;
        this.children[4].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
        return;
      }
      paramTreeCache.lvlToIndex[paramInt] = 7;
      this.children[7].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
      return;
    }
    if ((paramByte3 >= getStartZ()) && (paramByte3 < getEndZ() - getHalfDimZ()))
    {
      paramTreeCache.lvlToIndex[paramInt] = 5;
      this.children[5].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
      return;
    }
    paramTreeCache.lvlToIndex[paramInt] = 6;
    this.children[6].delete(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
  }
  
  public void deleteCached(TreeCache paramTreeCache, int paramInt)
  {
    super.deleteCached(paramTreeCache, paramInt + 1);
    this.children[paramTreeCache.lvlToIndex[paramInt]].deleteCached(paramTreeCache, paramInt + 1);
  }
  
  public void drawOctree(Vector3f paramVector3f, boolean paramBoolean)
  {
    if (isHasHit())
    {
      super.drawOctree(paramVector3f, paramBoolean);
      for (paramBoolean = false; paramBoolean < this.children.length; paramBoolean++) {
        this.children[paramBoolean].drawOctree(paramVector3f, false);
      }
    }
  }
  
  public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
  {
    paramIntersectionCallback = doIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
    if (isHasHit()) {
      for (int i = 0; i < this.children.length; i++) {
        if (!this.children[i].isEmpty()) {
          paramIntersectionCallback = this.children[i].findIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
        } else {
          this.children[i].setHasHit(false);
        }
      }
    }
    return paramIntersectionCallback;
  }
  
  public IntersectionCallback findIntersectingCast(IntersectionCallback paramIntersectionCallback, Transform paramTransform1, BoxShapeExt paramBoxShapeExt, ConvexShape paramConvexShape, float paramFloat1, Segment paramSegment, Transform paramTransform2, Transform paramTransform3, float paramFloat2)
  {
    paramIntersectionCallback = super.findIntersectingCast(paramIntersectionCallback, paramTransform1, paramBoxShapeExt, paramConvexShape, paramFloat1, paramSegment, paramTransform2, paramTransform3, paramFloat2);
    if (isHasHit()) {
      for (int i = 0; i < this.children.length; i++) {
        paramIntersectionCallback = this.children[i].findIntersectingCast(paramIntersectionCallback, paramTransform1, paramBoxShapeExt, paramConvexShape, paramFloat1, paramSegment, paramTransform2, paramTransform3, paramFloat2);
      }
    }
    return paramIntersectionCallback;
  }
  
  public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    paramIntersectionCallback = super.findIntersectingRay(paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
    if (isHasHit()) {
      for (int i = 0; i < this.children.length; i++) {
        if (!this.children[i].isEmpty()) {
          paramIntersectionCallback = this.children[i].findIntersectingRay(paramOctreeVariableSet, paramIntersectionCallback, paramTransform, paramMatrix3f, paramFloat1, paramSegment, paramVector3f1, paramVector3f2, paramFloat2);
        } else {
          this.children[i].setHasHit(false);
        }
      }
    }
    return paramIntersectionCallback;
  }
  
  public void insert(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
  {
    super.insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
    byte b1 = getStartX();
    byte b2 = getStartY();
    byte b3 = getStartZ();
    int i = getEndX();
    int j = getEndY();
    int k = getEndZ();
    if ((paramByte2 >= b2) && (paramByte2 < j - getHalfDimY()))
    {
      if ((paramByte1 >= b1) && (paramByte1 < i - getHalfDimX()))
      {
        if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ()))
        {
          paramTreeCache.lvlToIndex[paramInt] = 0;
          this.children[0].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
          return;
        }
        paramTreeCache.lvlToIndex[paramInt] = 3;
        this.children[3].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
        return;
      }
      if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ()))
      {
        paramTreeCache.lvlToIndex[paramInt] = 1;
        this.children[1].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
        return;
      }
      paramTreeCache.lvlToIndex[paramInt] = 2;
      this.children[2].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
      return;
    }
    if ((paramByte1 >= b1) && (paramByte1 < i - getHalfDimX()))
    {
      if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ()))
      {
        paramTreeCache.lvlToIndex[paramInt] = 4;
        this.children[4].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
        return;
      }
      paramTreeCache.lvlToIndex[paramInt] = 7;
      this.children[7].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
      return;
    }
    if ((paramByte3 >= b3) && (paramByte3 < k - getHalfDimZ()))
    {
      paramTreeCache.lvlToIndex[paramInt] = 5;
      this.children[5].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
      return;
    }
    paramTreeCache.lvlToIndex[paramInt] = 6;
    this.children[6].insert(paramByte1, paramByte2, paramByte3, paramTreeCache, paramInt + 1);
  }
  
  public void insertCached(TreeCache paramTreeCache, int paramInt)
  {
    super.insertCached(paramTreeCache, paramInt + 1);
    this.children[paramTreeCache.lvlToIndex[paramInt]].insertCached(paramTreeCache, paramInt + 1);
  }
  
  public void reset()
  {
    super.reset();
    for (int i = 0; i < this.children.length; i++) {
      this.children[i].reset();
    }
  }
  
  protected boolean isLeaf()
  {
    return false;
  }
  
  public int split(int paramInt1, int paramInt2)
  {
    int i = 1;
    this.children = new OctreeLeaf[8];
    int j;
    if (getSet().first)
    {
      class_35 localclass_351 = getStart(new class_35());
      class_35 localclass_352;
      (localclass_352 = getHalfDim(new class_35())).a1(localclass_351);
      class_35 localclass_353 = new class_35(localclass_351);
      class_35 localclass_354 = new class_35(localclass_352);
      localclass_353.a(getHalfDimX(), (byte)0, (byte)0);
      localclass_354.a(getHalfDimX(), (byte)0, (byte)0);
      class_35 localclass_355 = new class_35(localclass_351);
      class_35 localclass_356 = new class_35(localclass_352);
      localclass_355.a(getHalfDimX(), (byte)0, getHalfDimZ());
      localclass_356.a(getHalfDimX(), (byte)0, getHalfDimZ());
      class_35 localclass_357 = new class_35(localclass_351);
      class_35 localclass_358 = new class_35(localclass_352);
      localclass_357.a((byte)0, (byte)0, getHalfDimZ());
      localclass_358.a((byte)0, (byte)0, getHalfDimZ());
      class_35 localclass_359 = new class_35(localclass_351);
      class_35 localclass_3510 = new class_35(localclass_352);
      localclass_359.a((byte)0, getHalfDimY(), (byte)0);
      localclass_3510.a((byte)0, getHalfDimY(), (byte)0);
      class_35 localclass_3511 = new class_35(localclass_351);
      class_35 localclass_3512 = new class_35(localclass_352);
      localclass_3511.a(getHalfDimX(), getHalfDimY(), (byte)0);
      localclass_3512.a(getHalfDimX(), getHalfDimY(), (byte)0);
      class_35 localclass_3513 = new class_35(localclass_351);
      class_35 localclass_3514 = new class_35(localclass_352);
      localclass_3513.a(getHalfDimX(), getHalfDimY(), getHalfDimZ());
      localclass_3514.a(getHalfDimX(), getHalfDimY(), getHalfDimZ());
      class_35 localclass_3515 = new class_35(localclass_351);
      class_35 localclass_3516 = new class_35(localclass_352);
      localclass_3515.a((byte)0, getHalfDimY(), getHalfDimZ());
      localclass_3516.a((byte)0, getHalfDimY(), getHalfDimZ());
      if (paramInt2 < getMaxLevel())
      {
        this.children[0] = new OctreeNode(localclass_351, localclass_352, paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[1] = new OctreeNode(localclass_353, localclass_354, (paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[2] = new OctreeNode(localclass_355, localclass_356, (paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[3] = new OctreeNode(localclass_357, localclass_358, (paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[4] = new OctreeNode(localclass_359, localclass_3510, (paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[5] = new OctreeNode(localclass_3511, localclass_3512, (paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[6] = new OctreeNode(localclass_3513, localclass_3514, (paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[7] = new OctreeNode(localclass_3515, localclass_3516, (paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        for (j = 0; j < this.children.length; j++) {
          i += ((OctreeNode)this.children[j]).split((paramInt1 << 3) + j, paramInt2 + 1);
        }
      }
      else
      {
        this.children[0] = new OctreeLeaf(j, localclass_352, paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[1] = new OctreeLeaf(localclass_353, localclass_354, (paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[2] = new OctreeLeaf(localclass_355, localclass_356, (paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[3] = new OctreeLeaf(localclass_357, localclass_358, (paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[4] = new OctreeLeaf(localclass_359, localclass_3510, (paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[5] = new OctreeLeaf(localclass_3511, localclass_3512, (paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[6] = new OctreeLeaf(localclass_3513, localclass_3514, (paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        this.children[7] = new OctreeLeaf(localclass_3515, localclass_3516, (paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
        i += 8;
      }
    }
    else if (paramInt2 < getMaxLevel())
    {
      this.children[0] = new OctreeNode(paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[1] = new OctreeNode((paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[2] = new OctreeNode((paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[3] = new OctreeNode((paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[4] = new OctreeNode((paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[5] = new OctreeNode((paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[6] = new OctreeNode((paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[7] = new OctreeNode((paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      for (j = 0; j < this.children.length; j++) {
        i += ((OctreeNode)this.children[j]).split((paramInt1 << 3) + j, paramInt2 + 1);
      }
    }
    else
    {
      this.children[0] = new OctreeLeaf(paramInt1 << 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[1] = new OctreeLeaf((paramInt1 << 3) + 1, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[2] = new OctreeLeaf((paramInt1 << 3) + 2, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[3] = new OctreeLeaf((paramInt1 << 3) + 3, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[4] = new OctreeLeaf((paramInt1 << 3) + 4, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[5] = new OctreeLeaf((paramInt1 << 3) + 5, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[6] = new OctreeLeaf((paramInt1 << 3) + 6, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      this.children[7] = new OctreeLeaf((paramInt1 << 3) + 7, (byte)(paramInt2 + 1), getMaxLevel(), onServer());
      i += 8;
    }
    return i;
  }
  
  public OctreeLeaf[] getChildren()
  {
    return this.children;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */