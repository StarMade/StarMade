/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.schema.schine.graphicsengine.core.GlUtil;
/*    */ import org.schema.schine.network.client.ClientState;
/*    */ 
/*    */ public final class in extends yz
/*    */ {
/*    */   public yP a;
/*    */   public yP b;
/*    */   public yP c;
/*    */   private yE a;
/*    */ 
/*    */   public in(ClientState paramClientState)
/*    */   {
/* 32 */     super(paramClientState);
/*    */ 
/* 98 */     new q();
/*    */ 
/* 33 */     this.jdField_a_of_type_YE = new yE(xe.a().a("top-bar-gui-"), paramClientState);
/*    */   }
/*    */ 
/*    */   public final void a()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected final void d()
/*    */   {
/* 43 */     this.jdField_a_of_type_YE.h(36);
/*    */   }
/*    */ 
/*    */   public final void b()
/*    */   {
/* 48 */     if (k()) {
/* 49 */       d();
/*    */     }
/* 51 */     GlUtil.d();
/*    */ 
/* 53 */     r();
/* 54 */     this.jdField_a_of_type_YE.b();
/*    */ 
/* 56 */     GlUtil.c();
/*    */   }
/*    */ 
/*    */   public final float a() {
/* 60 */     return 64.0F;
/*    */   }
/*    */ 
/*    */   public final float b() {
/* 64 */     return 768.0F;
/*    */   }
/*    */ 
/*    */   public final void c()
/*    */   {
/* 72 */     this.jdField_a_of_type_YP = new yP(300, 40, d.j(), a());
/* 73 */     this.jdField_a_of_type_YP.b = new ArrayList(1);
/* 74 */     this.jdField_a_of_type_YP.b.add("0");
/* 75 */     this.jdField_a_of_type_YP.a().x = 240.0F;
/* 76 */     this.jdField_a_of_type_YP.a().y = 34.0F;
/*    */ 
/* 78 */     this.b = new yP(300, 40, d.k(), a());
/* 79 */     this.b.b = new ArrayList(1);
/* 80 */     this.b.b.add("");
/* 81 */     this.b.b.add("");
/* 82 */     this.b.a().x = 398.0F;
/* 83 */     this.b.a().y = 30.0F;
/*    */ 
/* 85 */     this.c = new yP(300, 40, d.j(), a());
/* 86 */     this.c.b = new ArrayList(1);
/* 87 */     this.c.b.add("0");
/* 88 */     this.c.a().x = 609.0F;
/* 89 */     this.c.a().y = 34.0F;
/*    */ 
/* 93 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_YP);
/* 94 */     this.jdField_a_of_type_YE.a(this.c);
/* 95 */     this.jdField_a_of_type_YE.a(this.b);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     in
 * JD-Core Version:    0.6.2
 */