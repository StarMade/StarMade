/*     */ import org.lwjgl.opengl.GL20;
/*     */ 
/*     */ public final class zm extends zj
/*     */ {
/*     */   public zm(String paramString1, String paramString2)
/*     */   {
/* 407 */     super(paramString1, paramString2);
/*     */   }
/*     */   public final void a(int paramInt) {
/* 410 */     GL20.glBindAttribLocation(paramInt, 3, "indices");
/* 411 */     GL20.glBindAttribLocation(paramInt, 4, "weights");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zm
 * JD-Core Version:    0.6.2
 */