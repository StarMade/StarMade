/*    */ import java.util.Map;
/*    */ 
/*    */ public final class ew
/*    */   implements xg
/*    */ {
/*    */   private ct jdField_a_of_type_Ct;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */   private yg[] jdField_a_of_type_ArrayOfYg;
/*    */ 
/*    */   public ew(ct paramct)
/*    */   {
/* 18 */     this.jdField_a_of_type_Ct = paramct;
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 30 */     if (!this.jdField_a_of_type_Boolean)
/* 31 */       c();
/*    */     mv localmv;
/* 34 */     if (((
/* 34 */       localmv = this.jdField_a_of_type_Ct.a()) == null) || 
/* 34 */       (localmv.a().isEmpty()))
/*    */     {
/* 36 */       return;
/*    */     }
/* 38 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfYg.length; i++)
/*    */     {
/*    */       yg localyg;
/* 39 */       (
/* 41 */         localyg = this.jdField_a_of_type_ArrayOfYg[i])
/* 41 */         .b(0.01F, 0.01F, 0.01F);
/* 42 */       localyg.c(true);
/* 43 */       localyg.a(true);
/*    */ 
/* 45 */       yg.a(localyg, localmv.a().values(), xe.a());
/*    */ 
/* 47 */       localyg.a(false);
/* 48 */       localyg.c(false);
/* 49 */       localyg.b(1.0F, 1.0F, 1.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 60 */     this.jdField_a_of_type_ArrayOfYg = new yg[3];
/* 61 */     this.jdField_a_of_type_ArrayOfYg[0] = xe.a().a("build-icons-00-16x16-gui-");
/* 62 */     this.jdField_a_of_type_ArrayOfYg[1] = xe.a().a("build-icons-01-16x16-gui-");
/* 63 */     this.jdField_a_of_type_ArrayOfYg[2] = xe.a().a("build-icons-extra-gui-");
/*    */ 
/* 65 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ew
 * JD-Core Version:    0.6.2
 */