/*    */ import javax.vecmath.Vector4f;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.opengl.GL13;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ 
/*    */ public final class zp
/*    */   implements zn
/*    */ {
/*    */   public int a;
/*    */   public Vector4f a;
/*    */ 
/*    */   public zp()
/*    */   {
/* 15 */     this.jdField_a_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   public final void d()
/*    */   {
/* 24 */     GL13.glActiveTexture(33984);
/* 25 */     GL11.glBindTexture(3553, 0);
/*    */   }
/*    */ 
/*    */   public final void a(zj paramzj)
/*    */   {
/* 39 */     GlUtil.a(paramzj, "silhouetteColor", this.jdField_a_of_type_JavaxVecmathVector4f);
/*    */ 
/* 41 */     GL13.glActiveTexture(33984);
/* 42 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Int);
/* 43 */     GlUtil.a(paramzj, "silhouetteTexture", 0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zp
 * JD-Core Version:    0.6.2
 */