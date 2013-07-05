/*    */ import java.util.Observable;
/*    */ import java.util.Observer;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class gk extends yz
/*    */   implements Observer
/*    */ {
/*    */   private gm jdField_a_of_type_Gm;
/*    */   private gd jdField_a_of_type_Gd;
/*    */   private boolean jdField_a_of_type_Boolean;
/*    */ 
/*    */   public gk(ClientState paramClientState)
/*    */   {
/* 22 */     super(paramClientState);
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 34 */     if (this.jdField_a_of_type_Boolean) {
/* 35 */       this.jdField_a_of_type_Gm.jdField_a_of_type_Boolean = true;
/* 36 */       this.jdField_a_of_type_Boolean = false;
/*    */     }
/* 38 */     k();
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 44 */     this.jdField_a_of_type_Gd = new gd(a());
/* 45 */     this.jdField_a_of_type_Gd.c();
/* 46 */     this.jdField_a_of_type_Gm = new gl(a(), (int)(340.0F - this.jdField_a_of_type_Gd.a()));
/*    */ 
/* 55 */     this.jdField_a_of_type_Gm.c();
/* 56 */     this.jdField_a_of_type_Gm.a().y = this.jdField_a_of_type_Gd.a();
/* 57 */     a(this.jdField_a_of_type_Gm);
/* 58 */     a(this.jdField_a_of_type_Gd);
/*    */   }
/*    */ 
/*    */   public final float a()
/*    */   {
/* 63 */     return this.jdField_a_of_type_Gm.a();
/*    */   }
/*    */ 
/*    */   public final float b()
/*    */   {
/* 68 */     return this.jdField_a_of_type_Gm.b();
/*    */   }
/*    */ 
/*    */   public final void update(Observable paramObservable, Object paramObject)
/*    */   {
/* 73 */     this.jdField_a_of_type_Boolean = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gk
 * JD-Core Version:    0.6.2
 */