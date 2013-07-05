/*    */ import javax.vecmath.Vector3f;
/*    */ import javax.vecmath.Vector4f;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ 
/*    */ public final class ym extends yl
/*    */ {
/*    */   private Vector3f a;
/*    */ 
/*    */   public ym(Vector3f paramVector3f, Vector4f paramVector4f)
/*    */   {
/* 25 */     super((byte)0);
/* 26 */     this.jdField_a_of_type_JavaxVecmathVector3f = paramVector3f;
/* 27 */     this.jdField_a_of_type_JavaxVecmathVector4f = paramVector4f;
/*    */   }
/*    */ 
/*    */   public final void a() {
/* 31 */     GL11.glDisable(3553);
/* 32 */     GL11.glEnable(2903);
/* 33 */     GL11.glDisable(2896);
/* 34 */     GL11.glEnable(3042);
/* 35 */     GL11.glBlendFunc(770, 771);
/*    */ 
/* 37 */     if (this.jdField_a_of_type_JavaxVecmathVector4f != null)
/* 38 */       GlUtil.a(this.jdField_a_of_type_JavaxVecmathVector4f.x, this.jdField_a_of_type_JavaxVecmathVector4f.y, this.jdField_a_of_type_JavaxVecmathVector4f.z, this.jdField_a_of_type_JavaxVecmathVector4f.w * a());
/*    */     else {
/* 40 */       GlUtil.a(1.0F, 1.0F, 1.0F, a());
/*    */     }
/* 42 */     GL11.glBegin(1);
/* 43 */     GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x - this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 44 */     GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x + this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/*    */ 
/* 46 */     GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y - this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/* 47 */     GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y + this.jdField_a_of_type_Float, this.jdField_a_of_type_JavaxVecmathVector3f.z);
/*    */ 
/* 49 */     GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z - this.jdField_a_of_type_Float);
/* 50 */     GL11.glVertex3f(this.jdField_a_of_type_JavaxVecmathVector3f.x, this.jdField_a_of_type_JavaxVecmathVector3f.y, this.jdField_a_of_type_JavaxVecmathVector3f.z + this.jdField_a_of_type_Float);
/* 51 */     GL11.glEnd();
/*    */ 
/* 53 */     GL11.glDisable(3042);
/* 54 */     GL11.glDisable(2903);
/* 55 */     GL11.glEnable(2896);
/* 56 */     GL11.glEnable(3553);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ym
 * JD-Core Version:    0.6.2
 */