/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */import o;
/*  6:   */import org.schema.game.common.controller.SegmentController;
/*  7:   */import org.schema.game.common.data.world.Segment;
/*  8:   */
/* 11:   */public class CubeRayCastResult
/* 12:   */  extends CollisionWorld.ClosestRayResultCallback
/* 13:   */{
/* 14:   */  private Object owner;
/* 15:   */  private Object userData;
/* 16:16 */  private boolean respectShields = false;
/* 17:17 */  private boolean ignoereNotPhysical = false;
/* 18:   */  public SegmentController filter;
/* 19:   */  public boolean onlyCubeMeshes;
/* 20:20 */  public o cubePos = new o();
/* 21:   */  private Segment segment;
/* 22:   */  private Segment lastHitSegment;
/* 23:   */  
/* 24:   */  public CubeRayCastResult(Vector3f paramVector3f1, Vector3f paramVector3f2, Object paramObject, SegmentController paramSegmentController)
/* 25:   */  {
/* 26:26 */    super(paramVector3f1, paramVector3f2);
/* 27:27 */    this.owner = paramObject;
/* 28:28 */    this.filter = paramSegmentController;
/* 29:   */  }
/* 30:   */  
/* 32:   */  public Segment getLastHitSegment()
/* 33:   */  {
/* 34:34 */    return this.lastHitSegment;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public Object getOwner() {
/* 38:38 */    return this.owner;
/* 39:   */  }
/* 40:   */  
/* 42:   */  public Object getUserData()
/* 43:   */  {
/* 44:44 */    return this.userData;
/* 45:   */  }
/* 46:   */  
/* 49:   */  public boolean isIgnoereNotPhysical()
/* 50:   */  {
/* 51:51 */    return this.ignoereNotPhysical;
/* 52:   */  }
/* 53:   */  
/* 55:   */  public boolean isRespectShields()
/* 56:   */  {
/* 57:57 */    return this.respectShields;
/* 58:   */  }
/* 59:   */  
/* 62:   */  public void setIgnoereNotPhysical(boolean paramBoolean)
/* 63:   */  {
/* 64:64 */    this.ignoereNotPhysical = paramBoolean;
/* 65:   */  }
/* 66:   */  
/* 68:   */  public void setLastHitSegment(Segment paramSegment)
/* 69:   */  {
/* 70:70 */    this.lastHitSegment = paramSegment;
/* 71:   */  }
/* 72:   */  
/* 74:   */  public void setRespectShields(boolean paramBoolean)
/* 75:   */  {
/* 76:76 */    this.respectShields = paramBoolean;
/* 77:   */  }
/* 78:   */  
/* 80:   */  public void setUserData(Object paramObject)
/* 81:   */  {
/* 82:82 */    this.userData = paramObject;
/* 83:   */  }
/* 84:   */  
/* 86:   */  public Segment getSegment()
/* 87:   */  {
/* 88:88 */    return this.segment;
/* 89:   */  }
/* 90:   */  
/* 92:   */  public void setSegment(Segment paramSegment)
/* 93:   */  {
/* 94:94 */    this.segment = paramSegment;
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeRayCastResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */