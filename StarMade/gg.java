/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import org.schema.game.common.data.UploadInProgressException;
/*     */ 
/*     */ final class gg extends K
/*     */ {
/*     */   gg(gd paramgd, ct paramct, Object paramObject1, Object paramObject2, String paramString)
/*     */   {
/* 211 */     super(paramct, 50, paramObject1, paramObject2, paramString);
/*     */   }
/*     */   public final String[] getCommandPrefixes() {
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */   public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*     */   {
/* 220 */     return paramString1;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 225 */     return this.a.b().indexOf(this) != this.a.b().size() - 1;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 230 */     this.a.a().e(false);
/*     */   }
/*     */ 
/*     */   public final void onFailedTextCheck(String paramString)
/*     */   {
/* 235 */     a("SHIPNAME INVALID: " + paramString);
/*     */   }
/*     */ 
/*     */   public final boolean a(String paramString)
/*     */   {
/*     */     try {
/* 241 */       this.a.a().a().a(paramString);
/*     */ 
/* 243 */       return true; } catch (IOException localIOException) {
/* 244 */       (
/* 245 */         paramString = localIOException)
/* 245 */         .printStackTrace();
/* 246 */       xm.a(paramString);
/*     */     } catch (UploadInProgressException paramString) {
/* 248 */       this.a.a().b("Cannot Upload!\nThere is already\nan Upload in progress");
/*     */     }
/*     */ 
/* 251 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gg
 * JD-Core Version:    0.6.2
 */