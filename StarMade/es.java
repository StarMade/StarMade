/*    */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.schine.graphicsengine.camera.Camera;
/*    */ 
/*    */ public final class es
/*    */   implements xg
/*    */ {
/*    */   public yY a;
/*    */   private yZ jdField_a_of_type_YZ;
/* 18 */   private boolean jdField_a_of_type_Boolean = true;
/*    */ 
/*    */   public es() {
/* 21 */     this.jdField_a_of_type_YY = new yY();
/* 22 */     this.jdField_a_of_type_YZ = new yZ(this.jdField_a_of_type_YY);
/*    */   }
/*    */ 
/*    */   public final void a(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback) {
/* 26 */     xe.a().a(); this.jdField_a_of_type_YY.a(new Vector3f(paramClosestRayResultCallback.hitPointWorld), 4.0F);
/*    */   }
/*    */   public final void a(Vector3f paramVector3f, float paramFloat) {
/* 29 */     xe.a().a(); this.jdField_a_of_type_YY.a(new Vector3f(paramVector3f), paramFloat);
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 38 */     if (this.jdField_a_of_type_Boolean) {
/* 39 */       c();
/*    */     }
/* 41 */     this.jdField_a_of_type_YZ.b();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 53 */     this.jdField_a_of_type_YZ.c();
/* 54 */     this.jdField_a_of_type_Boolean = false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     es
 * JD-Core Version:    0.6.2
 */