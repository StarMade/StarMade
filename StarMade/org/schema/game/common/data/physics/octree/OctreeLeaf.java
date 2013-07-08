package org.schema.game.common.data.physics.octree;

import class_1353;
import class_35;
import class_48;
import class_988;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.schema.game.common.data.physics.BoxShapeExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.graphicsengine.core.GlUtil;

public class OctreeLeaf
{
  private final short field_1767;
  private short cnt;
  private boolean hasHit;
  private boolean onServer;
  private final byte lvl;
  public int index;
  public int localIndex;
  public int nodeIndex;
  public static final int BLOCK_SIZE = 6;
  
  public OctreeLeaf(int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
  {
    assert (paramInt2 >= 0);
    assert (paramByte >= 0);
    this.onServer = paramBoolean;
    this.field_1767 = getSet().getId(paramByte, paramInt1, 0);
    this.lvl = paramByte;
  }
  
  public OctreeLeaf(class_35 paramclass_351, class_35 paramclass_352, int paramInt1, byte paramByte, int paramInt2, boolean paramBoolean)
  {
    assert (paramInt2 >= 0);
    assert (paramByte >= 0);
    assert (paramInt1 < 32767);
    this.onServer = paramBoolean;
    this.field_1767 = getSet().put(paramByte, paramInt1, 0, paramclass_351);
    this.lvl = paramByte;
    getSet().put(paramByte, paramInt1, 1, paramclass_352);
    (paramclass_352 = new class_35(paramclass_352)).c(paramclass_351);
    getSet().put(paramByte, paramInt1, 2, paramclass_352);
    (paramclass_351 = getDim(new class_35())).a2();
    getSet().put(paramByte, paramInt1, 3, paramclass_351);
    this.index = paramInt1;
    this.localIndex = (paramInt1 % 8);
    if (paramBoolean)
    {
      this.nodeIndex = ArrayOctree.getIndex(paramInt1, paramByte - 1);
      (paramclass_351 = new StringBuilder()).append(paramByte);
      for (paramclass_352 = 0; paramclass_352 < paramByte; paramclass_352++) {
        paramclass_351.append("    ");
      }
      paramclass_351.append("#### I " + paramInt1 + " tot " + OctreeVariableSet.nodes + " -> " + ArrayOctree.getIndex(paramInt1, paramByte));
      System.err.println(paramclass_351);
      OctreeVariableSet.nodes += 1;
    }
  }
  
  private boolean between(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return (paramByte1 >= getStartX()) && (paramByte2 >= getStartY()) && (paramByte3 >= getStartZ()) && (paramByte1 < getEndX()) && (paramByte2 < getEndY()) && (paramByte3 < getEndZ());
  }
  
  public void delete(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
  {
    if (this.cnt <= 0) {
      System.err.println("Exception: WARNING Octree Size < 0");
    }
    this.cnt = ((short)Math.max(0, this.cnt - 1));
  }
  
  public void deleteCached(TreeCache paramTreeCache, int paramInt)
  {
    assert (this.cnt > 0);
    this.cnt = ((short)(this.cnt - 1));
  }
  
  public void drawOctree(Vector3f paramVector3f, boolean paramBoolean)
  {
    if ((!paramBoolean) && ((!isHasHit()) || (isEmpty()))) {
      return;
    }
    getSet().tmpMin.set(getStartX(), getStartY(), getStartZ());
    getSet().tmpMin.scale(1.0F);
    getSet().tmpMin.field_615 += -0.5F;
    getSet().tmpMin.field_616 += -0.5F;
    getSet().tmpMin.field_617 += -0.5F;
    getSet().tmpMax.set(getEndX(), getEndY(), getEndZ());
    getSet().tmpMax.scale(1.0F);
    getSet().tmpMax.field_615 += -0.5F;
    getSet().tmpMax.field_616 += -0.5F;
    getSet().tmpMax.field_617 += -0.5F;
    getSet().tmpMax.sub(paramVector3f);
    getSet().tmpMin.sub(paramVector3f);
    paramVector3f = class_1353.a(getSet().tmpMin, getSet().tmpMax);
    GL11.glPolygonMode(1032, 6913);
    GL11.glDisable(2896);
    GL11.glDisable(2884);
    GL11.glEnable(2903);
    GL11.glDisable(32879);
    GL11.glDisable(3553);
    GL11.glDisable(3552);
    GL11.glEnable(3042);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 0.2F);
    float f = 0.0F;
    if (!isEmpty()) {
      f = 1.0F;
    }
    if (!paramBoolean) {
      GlUtil.a38(1.0F, 0.0F, f, 0.9F);
    } else {
      GlUtil.a38(0.0F, 1.0F, 0.0F, 1.0F);
    }
    GL11.glBegin(7);
    for (paramBoolean = false; paramBoolean < paramVector3f.length; paramBoolean++) {
      for (int i = 0; i < paramVector3f[paramBoolean].length; i++) {
        GL11.glVertex3f(paramVector3f[paramBoolean][i].field_615, paramVector3f[paramBoolean][i].field_616, paramVector3f[paramBoolean][i].field_617);
      }
    }
    GL11.glEnd();
    GL11.glEnable(2896);
    GL11.glDisable(2903);
    GL11.glEnable(2884);
    GL11.glDisable(3042);
    GL11.glPolygonMode(1032, 6914);
  }
  
  protected IntersectionCallback doIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
  {
    paramIntersectionCallback.leafCalcs += 1;
    getStart(paramOctreeVariableSet, paramOctreeVariableSet.min);
    getEnd(paramOctreeVariableSet, paramOctreeVariableSet.max);
    paramFloat2 = paramOctreeVariableSet.tmpMin;
    paramBoolean = paramOctreeVariableSet.tmpMax;
    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMinOut;
    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMaxOut;
    float f1 = paramSegment.field_34.field_475 - 0.5F;
    float f2 = paramSegment.field_34.field_476 - 0.5F;
    paramSegment = paramSegment.field_34.field_477 - 0.5F;
    paramFloat2.field_615 = (paramOctreeVariableSet.min.field_453 + f1);
    paramFloat2.field_616 = (paramOctreeVariableSet.min.field_454 + f2);
    paramFloat2.field_617 = (paramOctreeVariableSet.min.field_455 + paramSegment);
    paramBoolean.field_615 = (paramOctreeVariableSet.max.field_453 + f1);
    paramBoolean.field_616 = (paramOctreeVariableSet.max.field_454 + f2);
    paramBoolean.field_617 = (paramOctreeVariableSet.max.field_455 + paramSegment);
    transformAabb(paramOctreeVariableSet, paramFloat2, paramBoolean, paramMatrix3f, paramFloat1, paramTransform, localVector3f1, localVector3f2);
    paramOctreeVariableSet = AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, paramVector3f1, paramVector3f2);
    setHasHit(paramOctreeVariableSet);
    return paramIntersectionCallback;
  }
  
  public IntersectionCallback findIntersecting(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Segment paramSegment, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2, boolean paramBoolean)
  {
    paramIntersectionCallback = doIntersecting(paramOctreeVariableSet, paramIntersectionCallback, paramSegment, paramTransform, paramMatrix3f, paramFloat1, paramVector3f1, paramVector3f2, paramFloat2, paramBoolean);
    if (this.hasHit) {
      paramIntersectionCallback.addHit(paramOctreeVariableSet.tmpMinOut, paramOctreeVariableSet.tmpMaxOut, paramOctreeVariableSet.min.field_453, paramOctreeVariableSet.min.field_454, paramOctreeVariableSet.min.field_455, paramOctreeVariableSet.max.field_453, paramOctreeVariableSet.max.field_454, paramOctreeVariableSet.max.field_455, 255);
    }
    return paramIntersectionCallback;
  }
  
  private void transformAabb(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f1, Vector3f paramVector3f2, Matrix3f paramMatrix3f, float paramFloat, Transform paramTransform, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    (paramVector3f2 = paramOctreeVariableSet.localCenter).add(paramVector3f1, OctreeVariableSet.localCentersAdd[this.lvl]);
    (paramVector3f1 = paramOctreeVariableSet.center).set(paramVector3f2);
    paramTransform.transform(paramVector3f1);
    paramOctreeVariableSet = paramOctreeVariableSet.extend;
    paramVector3f2 = OctreeVariableSet.localHalfExtends[this.lvl];
    paramOctreeVariableSet.field_615 = (paramMatrix3f.m00 * paramVector3f2.field_615 + paramMatrix3f.m01 * paramVector3f2.field_616 + paramMatrix3f.m02 * paramVector3f2.field_617);
    paramOctreeVariableSet.field_616 = (paramMatrix3f.m10 * paramVector3f2.field_615 + paramMatrix3f.m11 * paramVector3f2.field_616 + paramMatrix3f.m12 * paramVector3f2.field_617);
    paramOctreeVariableSet.field_617 = (paramMatrix3f.m20 * paramVector3f2.field_615 + paramMatrix3f.m21 * paramVector3f2.field_616 + paramMatrix3f.m22 * paramVector3f2.field_617);
    paramVector3f3.sub(paramVector3f1, paramOctreeVariableSet);
    paramVector3f4.add(paramVector3f1, paramOctreeVariableSet);
  }
  
  public IntersectionCallback findIntersectingCast(IntersectionCallback paramIntersectionCallback, Transform paramTransform1, BoxShapeExt paramBoxShapeExt, ConvexShape paramConvexShape, float paramFloat1, Segment paramSegment, Transform paramTransform2, Transform paramTransform3, float paramFloat2)
  {
    getSet().tmpMin.set(getStartX(), getStartY(), getStartZ());
    getSet().tmpMin.field_615 += paramSegment.field_34.field_475 - 0.5F;
    getSet().tmpMin.field_616 += paramSegment.field_34.field_476 - 0.5F;
    getSet().tmpMin.field_617 += paramSegment.field_34.field_477 - 0.5F;
    getSet().tmpMax.set(getEndX(), getEndY(), getEndZ());
    getSet().tmpMax.field_615 += paramSegment.field_34.field_475 - 0.5F;
    getSet().tmpMax.field_616 += paramSegment.field_34.field_476 - 0.5F;
    getSet().tmpMax.field_617 += paramSegment.field_34.field_477 - 0.5F;
    paramBoxShapeExt.setDimFromBB(getSet().tmpMin, getSet().tmpMax);
    paramBoxShapeExt = new SubsimplexConvexCast(paramConvexShape, paramBoxShapeExt, new VoronoiSimplexSolver());
    (paramConvexShape = new ConvexCast.CastResult()).allowedPenetration = 0.03F;
    paramConvexShape.fraction = 1.0F;
    paramTransform1 = paramBoxShapeExt.calcTimeOfImpact(paramTransform2, paramTransform3, paramTransform1, paramTransform1, paramConvexShape);
    setHasHit(paramTransform1);
    if (isHasHit())
    {
      System.err.println("NODE hit registered (" + paramConvexShape.hitPoint + " in: " + getSet().tmpMin + " - " + getSet().tmpMax + ", dim: " + getDim(new class_35()) + ": " + getClass());
      if ((isLeaf()) && (!isEmpty())) {
        paramIntersectionCallback.addHit(getSet().tmpMinOut, getSet().tmpMaxOut, getStartX(), getStartY(), getStartZ(), getEndX(), getEndY(), getEndZ(), 255);
      }
    }
    return paramIntersectionCallback;
  }
  
  public IntersectionCallback findIntersectingRay(OctreeVariableSet paramOctreeVariableSet, IntersectionCallback paramIntersectionCallback, Transform paramTransform, Matrix3f paramMatrix3f, float paramFloat1, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat2)
  {
    paramIntersectionCallback.leafCalcs += 1;
    paramFloat2 = paramOctreeVariableSet.tmpMin;
    Vector3f localVector3f1 = paramOctreeVariableSet.tmpMax;
    Vector3f localVector3f2 = paramOctreeVariableSet.tmpMinOut;
    Vector3f localVector3f3 = paramOctreeVariableSet.tmpMaxOut;
    getStart(paramOctreeVariableSet, paramOctreeVariableSet.min);
    getEnd(paramOctreeVariableSet, paramOctreeVariableSet.max);
    float f1 = paramSegment.field_34.field_475 - 0.5F;
    float f2 = paramSegment.field_34.field_476 - 0.5F;
    float f3 = paramSegment.field_34.field_477 - 0.5F;
    paramFloat2.field_615 = (paramOctreeVariableSet.min.field_453 + f1);
    paramFloat2.field_616 = (paramOctreeVariableSet.min.field_454 + f2);
    paramFloat2.field_617 = (paramOctreeVariableSet.min.field_455 + f3);
    localVector3f1.field_615 = (paramOctreeVariableSet.max.field_453 + f1);
    localVector3f1.field_616 = (paramOctreeVariableSet.max.field_454 + f2);
    localVector3f1.field_617 = (paramOctreeVariableSet.max.field_455 + f3);
    if ((!$assertionsDisabled) && ((paramFloat2.field_615 > localVector3f1.field_615) || (paramFloat2.field_616 > localVector3f1.field_616) || (paramFloat2.field_617 > localVector3f1.field_617))) {
      throw new AssertionError("[WARNING] BOUNDING BOX IS FAULTY: " + paramSegment.field_34 + " in " + paramSegment.a16().getSegmentController() + ": " + paramFloat2 + " - " + localVector3f1 + "; star/end " + getStart(new class_35()) + " - " + getEnd(new class_35()) + "------ " + (paramOctreeVariableSet.tmpMin.field_615 > paramOctreeVariableSet.tmpMax.field_615) + "," + (paramOctreeVariableSet.tmpMin.field_616 > paramOctreeVariableSet.tmpMax.field_616) + "," + (paramOctreeVariableSet.tmpMin.field_617 > paramOctreeVariableSet.tmpMax.field_617));
    }
    transformAabb(paramOctreeVariableSet, paramFloat2, localVector3f1, paramMatrix3f, paramFloat1, paramTransform, localVector3f2, localVector3f3);
    paramOctreeVariableSet.param[0] = 1.0F;
    paramOctreeVariableSet.normal.field_615 = 0.0F;
    paramOctreeVariableSet.normal.field_616 = 0.0F;
    paramOctreeVariableSet.normal.field_617 = 0.0F;
    paramTransform = AabbUtil2.rayAabb(paramVector3f1, paramVector3f2, localVector3f2, localVector3f3, paramOctreeVariableSet.param, paramOctreeVariableSet.normal);
    paramMatrix3f = 0;
    if (paramTransform == 0) {
      paramMatrix3f = (class_988.a(paramVector3f2, localVector3f2, localVector3f3)) || (class_988.a(paramVector3f1, localVector3f2, localVector3f3)) ? 1 : 0;
    }
    setHasHit((paramTransform != 0) || (paramMatrix3f != 0));
    if ((isLeaf()) && (isHasHit())) {
      paramIntersectionCallback.addHit(localVector3f2, localVector3f3, paramOctreeVariableSet.min.field_453, paramOctreeVariableSet.min.field_454, paramOctreeVariableSet.min.field_455, paramOctreeVariableSet.max.field_453, paramOctreeVariableSet.max.field_454, paramOctreeVariableSet.max.field_455, 255);
    }
    return paramIntersectionCallback;
  }
  
  public class_35 getDim(class_35 paramclass_35)
  {
    getSet().get((short)(getId() + 2), paramclass_35);
    return paramclass_35;
  }
  
  public class_35 getEnd(class_35 paramclass_35)
  {
    getSet().get((short)(getId() + 1), paramclass_35);
    return paramclass_35;
  }
  
  public Vector3f getEnd(Vector3f paramVector3f)
  {
    getSet().get((short)(getId() + 1), paramVector3f);
    return paramVector3f;
  }
  
  public Vector3f getEnd(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f)
  {
    paramOctreeVariableSet.get((short)(getId() + 1), paramVector3f);
    return paramVector3f;
  }
  
  public class_35 getEnd(OctreeVariableSet paramOctreeVariableSet, class_35 paramclass_35)
  {
    paramOctreeVariableSet.get((short)(getId() + 1), paramclass_35);
    return paramclass_35;
  }
  
  public class_35 getHalfDim(class_35 paramclass_35)
  {
    getSet().get((short)(getId() + 3), paramclass_35);
    return paramclass_35;
  }
  
  public byte getDimX()
  {
    return getSet().getX((short)(getId() + 2));
  }
  
  public byte getEndX()
  {
    return getSet().getX((short)(getId() + 1));
  }
  
  public byte getHalfDimX()
  {
    return getSet().getX((short)(getId() + 3));
  }
  
  public byte getDimY()
  {
    return getSet().getY((short)(getId() + 2));
  }
  
  public byte getEndY()
  {
    return getSet().getY((short)(getId() + 1));
  }
  
  public byte getHalfDimY()
  {
    return getSet().getY((short)(getId() + 3));
  }
  
  public byte getDimZ()
  {
    return getSet().getZ((short)(getId() + 2));
  }
  
  public byte getEndZ()
  {
    return getSet().getZ((short)(getId() + 1));
  }
  
  public byte getHalfDimZ()
  {
    return getSet().getZ((short)(getId() + 3));
  }
  
  public short getId()
  {
    return this.field_1767;
  }
  
  public int getMaxLevel()
  {
    return getSet().maxLevel;
  }
  
  public OctreeVariableSet getSet()
  {
    return Octree.get(onServer());
  }
  
  public byte getStartX()
  {
    return getSet().getX((short)getId());
  }
  
  public byte getStartY()
  {
    return getSet().getY((short)getId());
  }
  
  public byte getStartZ()
  {
    return getSet().getZ((short)getId());
  }
  
  public class_35 getStart(class_35 paramclass_35)
  {
    getSet().get((short)getId(), paramclass_35);
    return paramclass_35;
  }
  
  public Vector3f getStart(Vector3f paramVector3f)
  {
    getSet().get((short)getId(), paramVector3f);
    return paramVector3f;
  }
  
  public class_35 getStart(OctreeVariableSet paramOctreeVariableSet, class_35 paramclass_35)
  {
    paramOctreeVariableSet.get((short)getId(), paramclass_35);
    return paramclass_35;
  }
  
  public Vector3f getStart(OctreeVariableSet paramOctreeVariableSet, Vector3f paramVector3f)
  {
    paramOctreeVariableSet.get((short)getId(), paramVector3f);
    return paramVector3f;
  }
  
  public void insert(byte paramByte1, byte paramByte2, byte paramByte3, TreeCache paramTreeCache, int paramInt)
  {
    assert (between(paramByte1, paramByte2, paramByte3)) : ("not in range: " + paramByte1 + ", " + paramByte2 + ", " + paramByte3 + ": [" + getStartX() + " - " + getEndX() + "], half: " + getHalfDimX());
    this.cnt = ((short)(this.cnt + 1));
  }
  
  public void insertCached(TreeCache paramTreeCache, int paramInt)
  {
    this.cnt = ((short)(this.cnt + 1));
  }
  
  public boolean isEmpty()
  {
    return this.cnt == 0;
  }
  
  public boolean isHasHit()
  {
    return this.hasHit;
  }
  
  protected boolean isLeaf()
  {
    return true;
  }
  
  protected boolean onServer()
  {
    return this.onServer;
  }
  
  public void reset()
  {
    this.cnt = 0;
  }
  
  public void setHasHit(boolean paramBoolean)
  {
    this.hasHit = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.OctreeLeaf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */