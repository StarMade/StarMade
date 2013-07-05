/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hm extends yw
/*     */ {
/*     */   hm(ClientState paramClientState)
/*     */   {
/* 154 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final void e() {
/* 158 */     int i = ((ct)a()).a().h();
/*     */ 
/* 160 */     if (((ct)a()).a().a(i) != null)
/*     */     {
/* 161 */       lP.c(((ct)a()).a(), true); return;
/*     */     }
/* 163 */     ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 164 */     System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/* 171 */     int i = ((ct)a()).a().h();
/*     */ 
/* 173 */     if (((ct)a()).a().a(i) != null)
/*     */     {
/* 174 */       lP.c(((ct)a()).a(), false); return;
/*     */     }
/* 176 */     ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 177 */     System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*     */   }
/*     */ 
/*     */   protected final boolean b()
/*     */   {
/* 184 */     int i = ((ct)a()).a().h();
/*     */     lP locallP;
/* 186 */     if ((
/* 186 */       locallP = ((ct)a()).a().a(i)) != null)
/*     */     {
/* 187 */       return locallP.d();
/*     */     }
/* 189 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hm
 * JD-Core Version:    0.6.2
 */