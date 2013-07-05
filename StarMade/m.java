/*     */ import java.io.Serializable;
/*     */ import java.rmi.Remote;
/*     */ 
/*     */ public final class m
/*     */   implements Serializable, Cloneable, Remote
/*     */ {
/*     */   private static final long serialVersionUID = 65793L;
/*     */   private float[] a;
/*     */ 
/*     */   public strictfp m()
/*     */   {
/* 379 */     this.a = new float[16];
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     m
 * JD-Core Version:    0.6.2
 */