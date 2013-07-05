/*    */ public class bc extends H
/*    */ {
/*    */   private final gZ jdField_a_of_type_GZ;
/*    */   private final lP jdField_a_of_type_LP;
/*    */ 
/*    */   public bc(ct paramct, lP paramlP)
/*    */   {
/* 20 */     super(paramct);
/* 21 */     this.jdField_a_of_type_GZ = new gZ(this.a, this, paramlP);
/* 22 */     this.jdField_a_of_type_LP = paramlP;
/*    */   }
/*    */ 
/*    */   public final boolean a()
/*    */   {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   public void handleKeyEvent()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final yz a()
/*    */   {
/* 46 */     return this.jdField_a_of_type_GZ;
/*    */   }
/*    */ 
/*    */   public void a()
/*    */   {
/* 51 */     this.a.a().a.a.a.a(500);
/*    */   }
/*    */ 
/*    */   public final void a(yz paramyz, xp paramxp)
/*    */   {
/* 58 */     if (paramxp.a()) {
/* 59 */       paramxp = this.jdField_a_of_type_GZ.a();
/*    */ 
/* 61 */       for (int i = 0; i < 5; i++) {
/* 62 */         if (paramxp[i].f()) {
/* 63 */           return;
/*    */         }
/*    */       }
/*    */ 
/* 67 */       if (paramyz.b().equals("OK"))
/*    */       {
/*    */         mc localmc;
/* 69 */         (
/* 70 */           localmc = new mc()).a = 
/* 70 */           this.jdField_a_of_type_LP.a();
/* 71 */         for (paramyz = 0; paramyz < 5; paramyz++) {
/* 72 */           if (paramxp[paramyz].e()) {
/* 73 */             localmc.a()[paramyz] |= 1L;
/*    */           }
/* 75 */           if (paramxp[paramyz].c()) {
/* 76 */             localmc.a()[paramyz] |= 4L;
/*    */           }
/* 78 */           if (paramxp[paramyz].b()) {
/* 79 */             localmc.a()[paramyz] |= 2L;
/*    */           }
/* 81 */           if (paramxp[paramyz].d()) {
/* 82 */             localmc.a()[paramyz] |= 8L;
/*    */           }
/* 84 */           localmc.a()[paramyz] = paramxp[paramyz].a();
/*    */         }
/*    */ 
/* 87 */         paramyz = null; this.a.a().a(localmc);
/*    */ 
/* 89 */         d();
/* 90 */         return; } if ((paramyz.b().equals("CANCEL")) || (paramyz.b().equals("X"))) {
/* 91 */         paramyz = null; d();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bc
 * JD-Core Version:    0.6.2
 */