/*    */ import org.lwjgl.input.Keyboard;
/*    */ import org.schema.schine.network.ChatSystem;
/*    */ 
/*    */ public final class ak extends U
/*    */ {
/*    */   public ak(ct paramct)
/*    */   {
/* 18 */     super(paramct);
/*    */   }
/*    */ 
/*    */   public final void handleKeyEvent()
/*    */   {
/* 23 */     a().getChat().handleKeyEvent();
/*    */   }
/*    */ 
/*    */   public final void a(xp paramxp)
/*    */   {
/* 28 */     super.a(paramxp);
/*    */   }
/*    */ 
/*    */   public final void b(boolean paramBoolean)
/*    */   {
/* 38 */     a().a().a.jdField_a_of_type_Aq.e(paramBoolean);
/* 39 */     a().a().a.jdField_a_of_type_Ae.e(paramBoolean);
/* 40 */     a().a().a.jdField_a_of_type_Ac.e(paramBoolean);
/* 41 */     super.b(paramBoolean);
/*    */   }
/*    */ 
/*    */   public final void c(boolean paramBoolean)
/*    */   {
/* 49 */     super.c(paramBoolean);
/* 50 */     Keyboard.enableRepeatEvents(paramBoolean);
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 57 */     super.a(paramxq);
/* 58 */     wV.a = false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ak
 * JD-Core Version:    0.6.2
 */