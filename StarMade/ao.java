/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public final class ao extends K
/*     */ {
/*     */   private yp a;
/*     */ 
/*     */   public ao(ct paramct, short paramShort, String paramString, Object paramObject, yp paramyp)
/*     */   {
/*  22 */     super(paramct, 5, paramString, paramObject, "1");
/*  23 */     this.a = paramyp;
/*     */ 
/*  28 */     a(new ap(paramShort));
/*     */   }
/*     */ 
/*     */   public final String[] getCommandPrefixes()
/*     */   {
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*     */   {
/*  63 */     return paramString1;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  68 */     return this.a.b().indexOf(this) != this.a.b().size() - 1;
/*     */   }
/*     */ 
/*     */   public final void a() {
/*  72 */     this.a.a().a.a.a.e(false);
/*     */   }
/*     */ 
/*     */   public final void onFailedTextCheck(String paramString)
/*     */   {
/*  79 */     a(paramString);
/*     */   }
/*     */ 
/*     */   public final boolean a(String paramString)
/*     */   {
/*  87 */     paramString = Integer.parseInt(paramString);
/*  88 */     paramString = Math.min(((hR)this.a).a(false), paramString);
/*     */ 
/*  91 */     this.a.a(true);
/*     */ 
/*  93 */     this.a.setDragging(this.a);
/*     */ 
/*  95 */     this.a.c((int)this.a.f().x + 32);
/*  96 */     this.a.d((int)this.a.f().y + 32);
/*  97 */     this.a.a(System.currentTimeMillis());
/*     */ 
/*  99 */     ((hR)this.a).b(paramString);
/*     */ 
/* 102 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ao
 * JD-Core Version:    0.6.2
 */