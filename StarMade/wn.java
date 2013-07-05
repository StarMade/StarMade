/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public abstract class wn extends wo
/*    */ {
/*    */   private final Sendable a;
/*    */ 
/*    */   public wn(String paramString, Sendable paramSendable)
/*    */   {
/* 16 */     super(paramString, paramSendable.getState());
/* 17 */     this.a = paramSendable;
/*    */   }
/*    */ 
/*    */   public Sendable a()
/*    */   {
/* 22 */     return this.a;
/*    */   }
/*    */ 
/*    */   public final boolean a() {
/* 26 */     return (super.a()) || ((!this.a) && (((wp)this.a).a().b()));
/*    */   }
/*    */ 
/*    */   public final void a(xq paramxq)
/*    */   {
/* 34 */     super.a(paramxq);
/* 35 */     if (a()) {
/* 36 */       if (this.a) {
/* 37 */         b(paramxq); return;
/*    */       }
/* 39 */       a_(paramxq);
/*    */     }
/*    */   }
/*    */ 
/*    */   public float b() {
/* 44 */     if (!b) throw new AssertionError();
/* 45 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   public abstract void a_(xq paramxq);
/*    */ 
/*    */   public abstract void b(xq paramxq);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wn
 * JD-Core Version:    0.6.2
 */