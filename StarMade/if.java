/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.vecmath.Vector3f;
/*    */ import javax.vecmath.Vector4f;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class if extends yD
/*    */ {
/*    */   private id jdField_a_of_type_Id;
/*    */   private yP jdField_a_of_type_YP;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */   private boolean b;
/*    */   private yx jdField_a_of_type_Yx;
/*    */ 
/*    */   public if(ClientState paramClientState, String paramString, id paramid, boolean paramBoolean)
/*    */   {
/* 24 */     super(paramClientState);
/* 25 */     this.b = paramBoolean;
/* 26 */     this.a = paramString;
/* 27 */     this.jdField_a_of_type_Id = paramid;
/* 28 */     this.jdField_a_of_type_YP = new yP(315, 30, d.h(), paramClientState);
/* 29 */     this.jdField_a_of_type_YP.b = new ArrayList();
/* 30 */     this.jdField_a_of_type_YP.b.add(paramString);
/* 31 */     this.a = this;
/* 32 */     this.jdField_a_of_type_YP.a(paramid);
/* 33 */     this.jdField_a_of_type_Id.a(paramid);
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 43 */     if (!this.jdField_a_of_type_Boolean) {
/* 44 */       c();
/*    */     }
/* 46 */     GlUtil.d();
/* 47 */     r();
/* 48 */     if (this.b) {
/* 49 */       this.jdField_a_of_type_Yx.b();
/*    */     }
/* 51 */     this.jdField_a_of_type_YP.b();
/*    */ 
/* 53 */     this.jdField_a_of_type_Id.a().x = this.jdField_a_of_type_YP.b();
/*    */ 
/* 55 */     this.jdField_a_of_type_Id.b();
/* 56 */     GlUtil.c();
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 61 */     return this.jdField_a_of_type_Id.a() + 5.0F;
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 66 */     return this.jdField_a_of_type_YP.b() + this.jdField_a_of_type_Id.b();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 76 */     this.jdField_a_of_type_YP.c();
/* 77 */     this.jdField_a_of_type_Id.c();
/*    */ 
/* 79 */     this.jdField_a_of_type_YP.a().y += 8.0F;
/*    */ 
/* 81 */     this.jdField_a_of_type_Id.a().y += 8.0F;
/*    */ 
/* 83 */     if (this.b) {
/* 84 */       this.jdField_a_of_type_Yx = new yx(a(), b(), a(), new Vector4f(0.06F, 0.06F, 0.06F, 0.3F));
/*    */     }
/* 86 */     this.jdField_a_of_type_YP.g = true;
/*    */ 
/* 88 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     if
 * JD-Core Version:    0.6.2
 */