/*    */ import org.schema.schine.network.server.ServerMessage;
/*    */ 
/*    */ public class wf extends vW
/*    */ {
/*    */   public q b;
/*    */ 
/*    */   public wf(vg paramvg, q paramq)
/*    */   {
/* 39 */     super(paramvg);
/* 40 */     this.b = paramq;
/*    */   }
/*    */ 
/*    */   public wf(vg paramvg) {
/* 44 */     super(paramvg);
/*    */   }
/*    */ 
/*    */   public void a(lE paramlE)
/*    */   {
/* 54 */     paramlE.a(new ServerMessage("#### Transmission Start\nHostile identified...\nExterminate...\n#### Transmission End\n", 2, paramlE.getId()));
/*    */   }
/*    */ 
/*    */   protected Ad a()
/*    */   {
/* 77 */     return new Ad(Af.k, null, this.b);
/*    */   }
/*    */ 
/*    */   protected void a(Ad paramAd)
/*    */   {
/* 85 */     this.b = ((q)paramAd.a());
/* 86 */     if (this.a != null)
/* 87 */       ((sL)this.a).a(this.b);
/*    */   }
/*    */ 
/*    */   public wb a()
/*    */   {
/* 95 */     return wb.a;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wf
 * JD-Core Version:    0.6.2
 */