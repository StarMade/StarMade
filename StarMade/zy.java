/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ 
/*     */ public final class zy
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */ 
/*     */   public zy(int paramInt)
/*     */   {
/* 130 */     this.c = paramInt;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 166 */     GL13.glActiveTexture(33984);
/*     */ 
/* 171 */     GL11.glEnable(3553);
/* 172 */     GL11.glBindTexture(3553, this.c);
/*     */   }
/*     */ 
/*     */   public static void b()
/*     */   {
/* 225 */     GL11.glBindTexture(3553, 0);
/* 226 */     GL11.glDisable(3553);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     zy
 * JD-Core Version:    0.6.2
 */