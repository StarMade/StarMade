/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hb extends yw
/*     */ {
/*     */   hb(ha paramha, ClientState paramClientState)
/*     */   {
/* 136 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final boolean b() {
/* 140 */     return this.a.d;
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/* 146 */     if (this.a.a == 4) {
/* 147 */       ((ct)a()).a().b("Cannot modify rights\nof admin role"); return;
/*     */     }
/*     */ 
/* 153 */     this.a.d = false;
/*     */   }
/*     */ 
/*     */   protected final void e()
/*     */   {
/* 160 */     this.a.d = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hb
 * JD-Core Version:    0.6.2
 */