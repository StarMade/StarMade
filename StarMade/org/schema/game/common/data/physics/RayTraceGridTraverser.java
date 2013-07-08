package org.schema.game.common.data.physics;

import class_48;
import class_886;
import class_888;
import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public class RayTraceGridTraverser
{
  private class_48 cell = new class_48();
  private Vector3f tMax = new Vector3f();
  private Vector3f tDelta = new Vector3f();
  private Vector3f cellBoundary = new Vector3f();
  private int stepX;
  private int stepY;
  private int stepZ;
  private Ray ray;
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new RayTraceGridTraverser();
    Ray localRay;
    (localRay = new Ray()).position = new Vector3f(16.638065F, 5.568517F, 18.922298F);
    localRay.direction = new Vector3f(0.0F, -6.0F, 0.0F);
    paramArrayOfString.getCellsOnRay(localRay, 6, null, null);
  }
  
  public void getCellsOnRay(Ray paramRay, int paramInt, class_888 paramclass_888, SegmentController paramSegmentController)
  {
    this.ray = paramRay;
    class_48 localclass_48;
    int j = (localclass_48 = getCellAt(paramRay.position, this.cell)).field_475;
    int k = localclass_48.field_476;
    int i = localclass_48.field_477;
    this.stepX = ((int)Math.signum(paramRay.direction.field_615));
    this.stepY = ((int)Math.signum(paramRay.direction.field_616));
    this.stepZ = ((int)Math.signum(paramRay.direction.field_617));
    this.cellBoundary.set(j + (this.stepX > 0 ? 1 : 0), k + (this.stepY > 0 ? 1 : 0), i + (this.stepZ > 0 ? 1 : 0));
    this.tMax.set((this.cellBoundary.field_615 - paramRay.position.field_615) / paramRay.direction.field_615, (this.cellBoundary.field_616 - paramRay.position.field_616) / paramRay.direction.field_616, (this.cellBoundary.field_617 - paramRay.position.field_617) / paramRay.direction.field_617);
    if ((Float.isNaN(this.tMax.field_615)) || (Float.isInfinite(this.tMax.field_615))) {
      this.tMax.field_615 = (1.0F / 1.0F);
    }
    if ((Float.isNaN(this.tMax.field_616)) || (Float.isInfinite(this.tMax.field_616))) {
      this.tMax.field_616 = (1.0F / 1.0F);
    }
    if ((Float.isNaN(this.tMax.field_617)) || (Float.isInfinite(this.tMax.field_617))) {
      this.tMax.field_617 = (1.0F / 1.0F);
    }
    this.tDelta.set(this.stepX / paramRay.direction.field_615, this.stepY / paramRay.direction.field_616, this.stepZ / paramRay.direction.field_617);
    if (Float.isNaN(this.tDelta.field_615)) {
      this.tDelta.field_615 = (1.0F / 1.0F);
    }
    if (Float.isNaN(this.tDelta.field_616)) {
      this.tDelta.field_616 = (1.0F / 1.0F);
    }
    if (Float.isNaN(this.tDelta.field_617)) {
      this.tDelta.field_617 = (1.0F / 1.0F);
    }
    for (paramRay = 0; paramRay < paramInt; paramRay++)
    {
      if (!handle(j, k, i, paramclass_888, paramSegmentController)) {
        return;
      }
      if (this.tMax.field_615 < this.tMax.field_616)
      {
        if (this.tMax.field_615 < this.tMax.field_617)
        {
          j += this.stepX;
          this.tMax.field_615 += this.tDelta.field_615;
        }
        else
        {
          i += this.stepZ;
          this.tMax.field_617 += this.tDelta.field_617;
        }
      }
      else if (this.tMax.field_616 < this.tMax.field_617)
      {
        k += this.stepY;
        this.tMax.field_616 += this.tDelta.field_616;
      }
      else
      {
        i += this.stepZ;
        this.tMax.field_617 += this.tDelta.field_617;
      }
    }
    if (SubsimplexRayCubesCovexCast.debug) {
      System.err.println("NO SEGMENT FOUND ON PATH: " + paramInt);
    }
  }
  
  private boolean handleDebug(int paramInt1, int paramInt2, int paramInt3)
  {
    System.err.println("TESTING: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; " + this.stepX + ", " + this.stepY + ", " + this.stepZ + "; MAX " + this.tMax + "; DELTA " + this.tDelta + "; " + this.ray.position + " -> " + this.ray.direction);
    return false;
  }
  
  private boolean handle(int paramInt1, int paramInt2, int paramInt3, class_888 paramclass_888, SegmentController paramSegmentController)
  {
    paramInt1 <<= 4;
    paramInt2 <<= 4;
    paramInt3 <<= 4;
    if (((paramInt1 = paramSegmentController.getSegmentBuffer().a4(paramInt1, paramInt2, paramInt3)) != null) && (!paramInt1.g()))
    {
      if (SubsimplexRayCubesCovexCast.debug) {
        System.err.println("TRAVERSED TO " + paramInt1.field_34);
      }
      return paramclass_888.handle(paramInt1);
    }
    return true;
  }
  
  private class_48 getCellAt(Vector3f paramVector3f, class_48 paramclass_48)
  {
    paramclass_48.b(FastMath.a9(paramVector3f.field_615), FastMath.a9(paramVector3f.field_616), FastMath.a9(paramVector3f.field_617));
    return paramclass_48;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.RayTraceGridTraverser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */