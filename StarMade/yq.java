/*    */ import javax.vecmath.Vector3f;
/*    */ import org.lwjgl.input.Mouse;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class yq extends yr
/*    */ {
/*    */   private boolean jdField_a_of_type_Boolean;
/* 12 */   private q jdField_a_of_type_Q = new q();
/*    */   public yz a;
/*    */   private boolean b;
/*    */ 
/*    */   public yq(ClientState paramClientState, yz paramyz)
/*    */   {
/* 18 */     super(paramClientState, 380.0F, 40.0F);
/*    */ 
/* 20 */     this.jdField_a_of_type_Yz = paramyz;
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 29 */     this.g = true;
/* 30 */     super.b();
/*    */ 
/* 32 */     if ((a_()) && (Mouse.isButtonDown(0)) && 
/* 33 */       (!this.b) && 
/* 34 */       (!this.jdField_a_of_type_Boolean)) {
/* 35 */       this.jdField_a_of_type_Boolean = true;
/*    */ 
/* 37 */       this.jdField_a_of_type_Q.b(Mouse.getX(), xm.a() - Mouse.getY(), 0);
/*    */     }
/*    */ 
/* 42 */     this.b = Mouse.isButtonDown(0);
/*    */     Object localObject;
/* 44 */     if (this.jdField_a_of_type_Boolean) {
/* 45 */       (
/* 46 */         localObject = new q(Mouse.getX(), xm.a() - Mouse.getY(), 0))
/* 46 */         .c(this.jdField_a_of_type_Q);
/* 47 */       this.jdField_a_of_type_Yz.a().x += ((q)localObject).a;
/* 48 */       this.jdField_a_of_type_Yz.a().y += ((q)localObject).b;
/*    */ 
/* 52 */       this.jdField_a_of_type_Q.b(Mouse.getX(), xm.a() - Mouse.getY(), 0);
/* 53 */       if (!Mouse.isButtonDown(0))
/* 54 */         this.jdField_a_of_type_Boolean = false; 
/*    */     }
/* 55 */     if ((localObject = this.jdField_a_of_type_Yz).a().x < 0.0F) ((yz)localObject).a().x = 0.0F; if (((yz)localObject).a().y < 0.0F) ((yz)localObject).a().y = 0.0F; if (((yz)localObject).a().x + ((yz)localObject).b() > xm.b()) ((yz)localObject).a().x = (xm.b() - ((yz)localObject).b()); if (((yz)localObject).a().y + ((yz)localObject).a() > xm.a()) ((yz)localObject).a().y = (xm.a() - ((yz)localObject).a());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yq
 * JD-Core Version:    0.6.2
 */