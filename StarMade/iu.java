/*    */ import java.util.ArrayList;
/*    */ import org.newdawn.slick.Color;
/*    */ import org.newdawn.slick.UnicodeFont;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class iu extends yz
/*    */ {
/*    */   ArrayList jdField_a_of_type_JavaUtilArrayList;
/*    */   private static yP jdField_a_of_type_YP;
/*    */   private yG jdField_a_of_type_YG;
/* 20 */   private boolean jdField_a_of_type_Boolean = true;
/*    */   private ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*    */   private static ElementInformation b;
/*    */ 
/*    */   public iu(ElementInformation paramElementInformation, ClientState paramClientState, UnicodeFont paramUnicodeFont)
/*    */   {
/* 25 */     super(paramClientState);
/* 26 */     if (jdField_a_of_type_YP == null) {
/* 27 */       (
/* 28 */         iu.jdField_a_of_type_YP = new yP(512, 512, paramUnicodeFont, paramClientState))
/* 28 */         .a(512);
/* 29 */       jdField_a_of_type_YP.a(Color.green);
/*    */     }
/* 31 */     this.jdField_a_of_type_YG = new yG(336.0F, 184.0F, paramClientState);
/* 32 */     this.jdField_a_of_type_YG.c(jdField_a_of_type_YP);
/* 33 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/* 34 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 46 */     if (this.jdField_a_of_type_Boolean) {
/* 47 */       c();
/*    */     }
/* 49 */     if (b != this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation) {
/* 50 */       jdField_a_of_type_YP.b = this.jdField_a_of_type_JavaUtilArrayList;
/* 51 */       jdField_a_of_type_YP.jdField_a_of_type_Boolean = false;
/* 52 */       jdField_a_of_type_YP.e();
/* 53 */       b = this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*    */     }
/*    */ 
/* 56 */     GlUtil.d();
/* 57 */     r();
/* 58 */     this.jdField_a_of_type_YG.b();
/*    */ 
/* 60 */     GlUtil.c();
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 66 */     return this.jdField_a_of_type_YG.a();
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 78 */     return this.jdField_a_of_type_YG.b();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 90 */     this.jdField_a_of_type_YG.c();
/* 91 */     this.jdField_a_of_type_Boolean = false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iu
 * JD-Core Version:    0.6.2
 */