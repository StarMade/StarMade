/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector4f;
/*    */ 
/*    */ public abstract class ev
/*    */ {
/*    */   public Transform a;
/*    */   private float a;
/*    */   public String a;
/*    */   public Vector4f a;
/*    */ 
/*    */   public ev(Transform paramTransform, String paramString)
/*    */   {
/* 14 */     this.jdField_a_of_type_JavaxVecmathVector4f = null;
/*    */ 
/* 17 */     this.jdField_a_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
/* 18 */     this.jdField_a_of_type_JavaLangString = paramString;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 26 */     return ((ev)paramObject).jdField_a_of_type_JavaLangString.equals(this.jdField_a_of_type_JavaLangString);
/*    */   }
/*    */ 
/*    */   public abstract Transform a();
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 52 */     return this.jdField_a_of_type_JavaLangString.hashCode();
/*    */   }
/*    */ 
/*    */   public boolean a() {
/* 56 */     return this.jdField_a_of_type_Float < 0.3F;
/*    */   }
/*    */ 
/*    */   public void a(xq paramxq)
/*    */   {
/* 72 */     this.jdField_a_of_type_Float += paramxq.a();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ev
 * JD-Core Version:    0.6.2
 */