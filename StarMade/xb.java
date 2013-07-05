/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class xb extends xa
/*    */ {
/*    */   public yh a;
/*    */   protected Transform a;
/* 17 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */ 
/*    */   public xb(yh paramyh)
/*    */   {
/* 21 */     a(paramyh);
/*    */   }
/*    */ 
/*    */   public final yh a()
/*    */   {
/* 26 */     return this.jdField_a_of_type_Yh;
/*    */   }
/*    */ 
/*    */   public Vector3f a()
/*    */   {
/* 37 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = this.jdField_a_of_type_Yh.getWorldTransform();
/* 38 */     if (this.jdField_a_of_type_ComBulletphysicsLinearmathTransform != null)
/* 39 */       this.jdField_a_of_type_JavaxVecmathVector3f.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.origin);
/*    */     else {
/* 41 */       this.jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*    */     }
/* 43 */     return this.jdField_a_of_type_JavaxVecmathVector3f;
/*    */   }
/*    */ 
/*    */   public final void a(yh paramyh)
/*    */   {
/* 50 */     if ((!jdField_a_of_type_Boolean) && (paramyh == null)) throw new AssertionError();
/* 51 */     this.jdField_a_of_type_Yh = paramyh;
/*    */   }
/*    */ 
/*    */   public void a(xq paramxq)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xb
 * JD-Core Version:    0.6.2
 */