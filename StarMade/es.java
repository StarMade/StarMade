/*  1:   */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*  2:   */import javax.vecmath.Vector3f;
/*  3:   */import org.schema.schine.graphicsengine.camera.Camera;
/*  4:   */
/* 13:   */public final class es
/* 14:   */  implements xg
/* 15:   */{
/* 16:   */  public yY a;
/* 17:   */  private yZ jdField_a_of_type_YZ;
/* 18:18 */  private boolean jdField_a_of_type_Boolean = true;
/* 19:   */  
/* 20:   */  public es() {
/* 21:21 */    this.jdField_a_of_type_YY = new yY();
/* 22:22 */    this.jdField_a_of_type_YZ = new yZ(this.jdField_a_of_type_YY);
/* 23:   */  }
/* 24:   */  
/* 25:   */  public final void a(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback) {
/* 26:26 */    xe.a().a();this.jdField_a_of_type_YY.a(new Vector3f(paramClosestRayResultCallback.hitPointWorld), 4.0F);
/* 27:   */  }
/* 28:   */  
/* 29:29 */  public final void a(Vector3f paramVector3f, float paramFloat) { xe.a().a();this.jdField_a_of_type_YY.a(new Vector3f(paramVector3f), paramFloat);
/* 30:   */  }
/* 31:   */  
/* 33:   */  public final void a() {}
/* 34:   */  
/* 36:   */  public final void b()
/* 37:   */  {
/* 38:38 */    if (this.jdField_a_of_type_Boolean) {
/* 39:39 */      c();
/* 40:   */    }
/* 41:41 */    this.jdField_a_of_type_YZ.b();
/* 42:   */  }
/* 43:   */  
/* 51:   */  public final void c()
/* 52:   */  {
/* 53:53 */    this.jdField_a_of_type_YZ.c();
/* 54:54 */    this.jdField_a_of_type_Boolean = false;
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     es
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */