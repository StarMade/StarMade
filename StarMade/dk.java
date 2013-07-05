/*     */ import java.util.ArrayList;
/*     */ 
/*     */ final class dk
/*     */   implements Runnable
/*     */ {
/*     */   dk(dj paramdj)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void run()
/*     */   {
/* 242 */     ArrayList localArrayList = new ArrayList();
/* 243 */     for (int i = 0; i < 2744; i++)
/*     */     {
/* 245 */       if (mD.values()[dj.a(this.a).a().a().b(i)] == 
/* 245 */         mD.c) {
/* 246 */         localArrayList.add(Integer.valueOf(i));
/*     */       }
/*     */     }
/* 249 */     dj.a(this.a, (Integer[])localArrayList.toArray(new Integer[localArrayList.size()]));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dk
 * JD-Core Version:    0.6.2
 */