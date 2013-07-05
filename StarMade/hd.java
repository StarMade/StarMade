/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hd extends yw
/*     */ {
/*     */   hd(ha paramha, ClientState paramClientState)
/*     */   {
/* 192 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final boolean b() {
/* 196 */     return this.a.b;
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/* 202 */     if (this.a.a == 4) {
/* 203 */       ((ct)a()).a().b("Cannot modify rights\nof admin role"); return;
/*     */     }
/*     */ 
/* 209 */     this.a.b = false;
/*     */   }
/*     */ 
/*     */   protected final void e()
/*     */   {
/* 216 */     this.a.b = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hd
 * JD-Core Version:    0.6.2
 */