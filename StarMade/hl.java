/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hl extends yw
/*     */ {
/*     */   hl(ClientState paramClientState)
/*     */   {
/* 109 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final void e() {
/* 113 */     int i = ((ct)a()).a().h();
/*     */ 
/* 115 */     if (((ct)a()).a().a(i) != null)
/*     */     {
/* 116 */       lP.b(((ct)a()).a(), true); return;
/*     */     }
/* 118 */     ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 119 */     System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/* 126 */     int i = ((ct)a()).a().h();
/*     */ 
/* 128 */     if (((ct)a()).a().a(i) != null)
/*     */     {
/* 129 */       lP.b(((ct)a()).a(), false); return;
/*     */     }
/* 131 */     ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 132 */     System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*     */   }
/*     */ 
/*     */   protected final boolean b()
/*     */   {
/* 139 */     int i = ((ct)a()).a().h();
/*     */     lP locallP;
/* 141 */     if ((
/* 141 */       locallP = ((ct)a()).a().a(i)) != null)
/*     */     {
/* 142 */       return locallP.b();
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hl
 * JD-Core Version:    0.6.2
 */