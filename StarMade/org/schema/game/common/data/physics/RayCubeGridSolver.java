/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.Transform;
/*   4:    */import jM;
/*   5:    */import java.io.PrintStream;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import org.schema.common.FastMath;
/*   8:    */import org.schema.game.common.controller.SegmentController;
/*   9:    */
/*  12:    */public class RayCubeGridSolver
/*  13:    */{
/*  14:    */  private SegmentController controller;
/*  15: 15 */  private Vector3f inputStart = new Vector3f();
/*  16: 16 */  private Vector3f inputEnd = new Vector3f();
/*  17:    */  
/*  18: 18 */  private Vector3f dir = new Vector3f();
/*  19:    */  
/*  20: 20 */  private RayTraceGridTraverser tra = new RayTraceGridTraverser();
/*  21:    */  
/*  22: 22 */  private Ray r = new Ray();
/*  23:    */  
/*  24:    */  private static final float MIN_DEPTH = 3.0F;
/*  25:    */  
/*  26: 26 */  private final Transform inv = new Transform();
/*  27:    */  
/*  28:    */  public void initialize(Vector3f paramVector3f1, Vector3f paramVector3f2, SegmentController paramSegmentController, Transform paramTransform) {
/*  29: 29 */    this.inputStart.set(paramVector3f1);
/*  30: 30 */    this.inputEnd.set(paramVector3f2);
/*  31: 31 */    this.controller = paramSegmentController;
/*  32:    */    
/*  33: 33 */    this.inv.set(paramTransform);
/*  34: 34 */    this.inv.inverse();
/*  35: 35 */    this.inv.transform(this.inputStart);
/*  36: 36 */    this.inv.transform(this.inputEnd);
/*  37:    */    
/*  41: 41 */    this.inputStart.x += 8.5F;
/*  42: 42 */    this.inputStart.y += 8.5F;
/*  43: 43 */    this.inputStart.z += 8.5F;
/*  44:    */    
/*  45: 45 */    this.inputEnd.x += 8.5F;
/*  46: 46 */    this.inputEnd.y += 8.5F;
/*  47: 47 */    this.inputEnd.z += 8.5F;
/*  48:    */    
/*  49: 49 */    if (SubsimplexRayCubesCovexCast.debug) {
/*  50: 50 */      System.err.println("START OF TRAVERSE +888 IS " + this.inputStart);
/*  51:    */    }
/*  52:    */    
/*  61: 61 */    this.inputStart.x /= 16.0F;
/*  62: 62 */    this.inputStart.y /= 16.0F;
/*  63: 63 */    this.inputStart.z /= 16.0F;
/*  64:    */    
/*  65: 65 */    this.inputEnd.x /= 16.0F;
/*  66: 66 */    this.inputEnd.y /= 16.0F;
/*  67: 67 */    this.inputEnd.z /= 16.0F;
/*  68: 68 */    if (SubsimplexRayCubesCovexCast.debug) {
/*  69: 69 */      System.err.println("START OF TRAVERSE SMALL IS " + this.inputStart);
/*  70:    */    }
/*  71:    */    
/*  83: 83 */    this.dir.sub(this.inputEnd, this.inputStart);
/*  84:    */    
/*  85: 85 */    this.dir.scale(1.5F);
/*  86:    */    
/*  87: 87 */    if (this.dir.length() < 3.0F) {
/*  88: 88 */      this.dir.normalize();
/*  89: 89 */      this.dir.scale(3.0F);
/*  90:    */    }
/*  91:    */    
/*  92: 92 */    this.r.direction.set(this.dir);
/*  93: 93 */    this.r.position.set(this.inputStart);
/*  94:    */  }
/*  95:    */  
/* 100:    */  public void traverseSegmentsOnRay(jM paramjM)
/* 101:    */  {
/* 102:102 */    this.tra.getCellsOnRay(this.r, (int)Math.max(3.0F, FastMath.b(this.dir.length())), paramjM, this.controller);
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RayCubeGridSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */