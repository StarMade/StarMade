package org.schema.game.common.data.physics;

import class_888;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.SegmentController;

public class RayCubeGridSolver
{
  private SegmentController controller;
  private Vector3f inputStart = new Vector3f();
  private Vector3f inputEnd = new Vector3f();
  private Vector3f dir = new Vector3f();
  private RayTraceGridTraverser tra = new RayTraceGridTraverser();
  private Ray field_2035 = new Ray();
  private static final float MIN_DEPTH = 3.0F;
  private final Transform inv = new Transform();
  
  public void initialize(Vector3f paramVector3f1, Vector3f paramVector3f2, SegmentController paramSegmentController, Transform paramTransform)
  {
    this.inputStart.set(paramVector3f1);
    this.inputEnd.set(paramVector3f2);
    this.controller = paramSegmentController;
    this.inv.set(paramTransform);
    this.inv.inverse();
    this.inv.transform(this.inputStart);
    this.inv.transform(this.inputEnd);
    this.inputStart.field_615 += 8.5F;
    this.inputStart.field_616 += 8.5F;
    this.inputStart.field_617 += 8.5F;
    this.inputEnd.field_615 += 8.5F;
    this.inputEnd.field_616 += 8.5F;
    this.inputEnd.field_617 += 8.5F;
    if (SubsimplexRayCubesCovexCast.debug) {
      System.err.println("START OF TRAVERSE +888 IS " + this.inputStart);
    }
    this.inputStart.field_615 /= 16.0F;
    this.inputStart.field_616 /= 16.0F;
    this.inputStart.field_617 /= 16.0F;
    this.inputEnd.field_615 /= 16.0F;
    this.inputEnd.field_616 /= 16.0F;
    this.inputEnd.field_617 /= 16.0F;
    if (SubsimplexRayCubesCovexCast.debug) {
      System.err.println("START OF TRAVERSE SMALL IS " + this.inputStart);
    }
    this.dir.sub(this.inputEnd, this.inputStart);
    this.dir.scale(1.5F);
    if (this.dir.length() < 3.0F)
    {
      this.dir.normalize();
      this.dir.scale(3.0F);
    }
    this.field_2035.direction.set(this.dir);
    this.field_2035.position.set(this.inputStart);
  }
  
  public void traverseSegmentsOnRay(class_888 paramclass_888)
  {
    this.tra.getCellsOnRay(this.field_2035, (int)Math.max(3.0F, FastMath.b4(this.dir.length())), paramclass_888, this.controller);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.RayCubeGridSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */