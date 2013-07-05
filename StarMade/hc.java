/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hc extends yw
/*     */ {
/*     */   hc(ha paramha, ClientState paramClientState)
/*     */   {
/* 164 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final boolean b() {
/* 168 */     return this.a.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/* 174 */     if (this.a.jdField_a_of_type_Int == 4) {
/* 175 */       ((ct)a()).a().b("Cannot modify rights\nof admin role"); return;
/*     */     }
/*     */ 
/* 181 */     this.a.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   protected final void e()
/*     */   {
/* 188 */     this.a.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hc
 * JD-Core Version:    0.6.2
 */