/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ 
/*     */ final class ge extends K
/*     */ {
/*     */   ge(gd paramgd, ct paramct, Object paramObject1, Object paramObject2, String paramString)
/*     */   {
/* 145 */     super(paramct, 50, paramObject1, paramObject2, paramString);
/*     */   }
/*     */   public final String[] getCommandPrefixes() {
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   public final String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*     */   {
/* 154 */     return paramString1;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 159 */     return this.a.b().indexOf(this) != this.a.b().size() - 1;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 164 */     this.a.a().e(false);
/*     */   }
/*     */ 
/*     */   public final void onFailedTextCheck(String paramString)
/*     */   {
/* 169 */     a("SHIPNAME INVALID: " + paramString);
/*     */   }
/*     */ 
/*     */   public final boolean a(String paramString)
/*     */   {
/*     */     mF localmF;
/* 175 */     if (((
/* 175 */       localmF = this.a.a()) == null) || 
/* 175 */       (!(localmF instanceof kd))) {
/* 176 */       System.err.println("[ERROR] Player not int a ship");
/* 177 */       return false;
/*     */     }
/*     */     try {
/* 180 */       tH.a.a((SegmentController)localmF, paramString, true); } catch (IOException localIOException) {
/* 181 */       (
/* 182 */         paramString = 
/* 184 */         localIOException).printStackTrace();
/* 183 */       xm.a(paramString);
/*     */     }
/*     */ 
/* 186 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ge
 * JD-Core Version:    0.6.2
 */