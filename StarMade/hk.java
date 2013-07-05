/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hk extends yw
/*     */ {
/*     */   hk(ClientState paramClientState)
/*     */   {
/*  65 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   protected final void e() {
/*  69 */     int i = ((ct)a()).a().h();
/*     */ 
/*  71 */     if (((ct)a()).a().a(i) != null)
/*     */     {
/*  72 */       lP.a(((ct)a()).a(), true); return;
/*     */     }
/*  74 */     ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/*  75 */     System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*     */   }
/*     */ 
/*     */   protected final void f()
/*     */   {
/*  82 */     int i = ((ct)a()).a().h();
/*     */ 
/*  84 */     if (((ct)a()).a().a(i) != null)
/*     */     {
/*  85 */       lP.a(((ct)a()).a(), false); return;
/*     */     }
/*  87 */     ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/*  88 */     System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*     */   }
/*     */ 
/*     */   protected final boolean b()
/*     */   {
/*  95 */     int i = ((ct)a()).a().h();
/*     */     lP locallP;
/*  97 */     if ((
/*  97 */       locallP = ((ct)a()).a().a(i)) != null)
/*     */     {
/*  98 */       return locallP.c();
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hk
 * JD-Core Version:    0.6.2
 */