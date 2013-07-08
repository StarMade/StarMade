/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.SphereShape;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/*  7:   */public class ModifiedAABBSphereShape
/*  8:   */  extends SphereShape
/*  9:   */{
/* 10:   */  private float ext;
/* 11:11 */  Vector3f extent = new Vector3f();
/* 12:   */  
/* 13:13 */  public ModifiedAABBSphereShape(float paramFloat1, float paramFloat2) { super(paramFloat1);
/* 14:14 */    this.ext = paramFloat2;
/* 15:   */  }
/* 16:   */  
/* 19:   */  public void getAabb(Transform paramTransform, Vector3f paramVector3f1, Vector3f paramVector3f2)
/* 20:   */  {
/* 21:21 */    super.getAabb(paramTransform, paramVector3f1, paramVector3f2);
/* 22:22 */    paramTransform = paramTransform.origin;
/* 23:23 */    this.extent.set(getMargin() + this.ext, getMargin() + this.ext, getMargin() + this.ext);
/* 24:24 */    paramVector3f1.sub(paramTransform, this.extent);
/* 25:25 */    paramVector3f2.add(paramTransform, this.extent);
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedAABBSphereShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */