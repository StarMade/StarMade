/*     */ import java.util.HashMap;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class hn extends yN
/*     */ {
/*     */   hn(ClientState paramClientState, Object paramObject, ys paramys, yT paramyT)
/*     */   {
/* 204 */     super(paramClientState, 50, 20, paramObject, paramys, paramyT);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 211 */     int i = ((ct)a()).a().h();
/*     */     lP locallP;
/* 213 */     if ((
/* 213 */       locallP = ((ct)a()).a().a(i)) != null)
/*     */     {
/*     */       lX locallX;
/* 215 */       if (((
/* 215 */         locallX = (lX)locallP.a().get(((ct)a()).a().getName())) != null) && 
/* 215 */         (locallX.d(locallP)))
/* 216 */         super.b();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hn
 * JD-Core Version:    0.6.2
 */