/*     */ import java.util.Comparator;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public final class da
/*     */   implements Comparator
/*     */ {
/*     */   private final Vector3f a;
/*     */ 
/*     */   public da()
/*     */   {
/*  92 */     new Vector3f();
/*     */ 
/*  94 */     this.a = new Vector3f();
/*     */   }
/*     */ 
/*     */   public final synchronized int a(mr parammr1, mr parammr2)
/*     */   {
/*  99 */     if ((parammr1 == parammr2) || (parammr1.equals(parammr2))) {
/* 100 */       return 0;
/*     */     }
/*     */ 
/* 103 */     return Float.compare(parammr1.a, parammr2.a);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     da
 * JD-Core Version:    0.6.2
 */