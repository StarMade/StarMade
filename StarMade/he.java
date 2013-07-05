/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class he extends yw
/*     */ {
/*     */   he(ha paramha, ClientState paramClientState)
/*     */   {
/* 220 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final boolean b() {
/* 224 */     return this.a.c;
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/* 230 */     if (this.a.a == 4) {
/* 231 */       ((ct)a()).a().b("Cannot modify rights\nof admin role"); return;
/*     */     }
/*     */ 
/* 237 */     this.a.c = false;
/*     */   }
/*     */ 
/*     */   protected final void e()
/*     */   {
/* 244 */     this.a.c = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     he
 * JD-Core Version:    0.6.2
 */