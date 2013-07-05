/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import org.schema.game.network.objects.NetworkPlayer;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ 
/*     */ final class gi extends K
/*     */ {
/*     */   gi(gd paramgd, ct paramct, Object paramObject1, Object paramObject2, String paramString)
/*     */   {
/* 273 */     super(paramct, 50, paramObject1, paramObject2, paramString);
/*     */   }
/*     */   public final String[] getCommandPrefixes() {
/* 276 */     return null;
/*     */   }
/*     */ 
/*     */   public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*     */   {
/* 282 */     return paramString1;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 287 */     return this.a.b().indexOf(this) != this.a.b().size() - 1;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 292 */     this.a.a().e(false);
/*     */   }
/*     */ 
/*     */   public final void onFailedTextCheck(String paramString)
/*     */   {
/* 297 */     a("SHIPNAME INVALID: " + paramString);
/*     */   }
/*     */ 
/*     */   public final boolean a(String paramString)
/*     */   {
/*     */     mF localmF;
/* 303 */     if (((
/* 303 */       localmF = this.a.a()) == null) || 
/* 303 */       (!(localmF instanceof kd))) {
/* 304 */       System.err.println("[ERROR] Player not int a ship");
/* 305 */       return false;
/*     */     }
/*     */     RemoteStringArray localRemoteStringArray;
/* 307 */     (
/* 308 */       localRemoteStringArray = new RemoteStringArray(2, this.a.a().a()))
/* 308 */       .set(0, "#save;" + localmF.getId());
/* 309 */     localRemoteStringArray.set(1, paramString);
/* 310 */     this.a.a().a().catalogBuyBuffer.add(localRemoteStringArray);
/*     */ 
/* 312 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gi
 * JD-Core Version:    0.6.2
 */