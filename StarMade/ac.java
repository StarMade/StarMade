/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public final class ac extends U
/*     */ {
/*     */   private wT a;
/*     */ 
/*     */   public ac(ct paramct)
/*     */   {
/*  34 */     super(paramct);
/*     */   }
/*     */ 
/*     */   public final void b(boolean paramBoolean)
/*     */   {
/*  46 */     wV.a = !paramBoolean;
/*     */ 
/*  48 */     if (paramBoolean)
/*     */     {
/*  56 */       a().b(false);
/*  57 */       int i = 0;
/*     */       Iterator localIterator;
/*  58 */       synchronized (a().b()) {
/*  59 */         for (localIterator = a().b().iterator(); localIterator.hasNext(); ) {
/*  60 */           if (((H)localIterator.next() instanceof D))
/*     */           {
/*  61 */             i = 1;
/*     */           }
/*     */         }
/*     */       }
/*  65 */       if (i == 0) {
/*  66 */         ??? = new D(a());
/*  67 */         a().b().add(???);
/*     */       }
/*     */     }
/*     */ 
/*  71 */     super.b(paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/*  98 */     super.a(paramxq);
/*  99 */     wV.a = false;
/* 100 */     if (this.a == null) {
/* 101 */       ac localac = this; (localObject = new Transform()).setIdentity(); Object localObject = new xb(new ad((Transform)localObject)); localac.a().a();
/*     */       Vector3f localVector3f;
/* 101 */       (localVector3f = new Vector3f(cW.a().a())).negate(); localVector3f.normalize(); localac.a = new wT((xb)localObject, new Vector3f(125.0F, 70.0F, 223.0F), localVector3f);
/*     */     }
/* 103 */     if (xe.a() != this.a) {
/* 104 */       xe.a(this.a);
/* 105 */       this.a.a(paramxq);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ac
 * JD-Core Version:    0.6.2
 */