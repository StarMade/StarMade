/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ 
/*    */ public final class lk extends ln
/*    */ {
/* 12 */   private Transform b = new Transform();
/*    */ 
/*    */   public lk(StateInterface paramStateInterface)
/*    */   {
/* 17 */     super(paramStateInterface);
/* 18 */     this.jdField_a_of_type_Float = 15.0F;
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 29 */     super.a(paramxq);
/* 30 */     c(paramxq);
/*    */   }
/*    */ 
/*    */   private void c(xq paramxq)
/*    */   {
/*    */     Vector3f localVector3f;
/* 35 */     (
/* 36 */       localVector3f = new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f))
/* 36 */       .scale((float)(paramxq.a() * 10.0D));
/*    */ 
/* 38 */     this.b.set(this.jdField_a_of_type_ComBulletphysicsLinearmathTransform);
/* 39 */     this.b.origin.add(localVector3f);
/*    */ 
/* 41 */     a(this.b);
/*    */   }
/*    */ 
/*    */   public final void b(xq paramxq)
/*    */   {
/* 47 */     c(paramxq);
/*    */   }
/*    */ 
/*    */   public final byte a()
/*    */   {
/* 54 */     return 1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lk
 * JD-Core Version:    0.6.2
 */