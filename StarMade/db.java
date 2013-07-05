/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.util.Comparator;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ public final class db
/*     */   implements Comparator
/*     */ {
/*     */   private final Vector3f a;
/* 118 */   private Vector3f b = new Vector3f();
/*     */ 
/* 120 */   public db() { this.a = new Vector3f(); }
/*     */ 
/*     */ 
/*     */   private synchronized int a(mr parammr1, mr parammr2)
/*     */   {
/* 125 */     if ((parammr1 == parammr2) || (parammr1.equals(parammr2))) {
/* 126 */       return 0;
/*     */     }
/*     */ 
/* 129 */     return Float.compare(a(parammr1.a, parammr1), a(parammr2.a, parammr2));
/*     */   }
/*     */ 
/*     */   private float a(q paramq, Segment paramSegment)
/*     */   {
/* 134 */     this.b.set(paramq.a, paramq.b, paramq.c);
/* 135 */     paramSegment.a().getWorldTransformClient().basis.transform(this.b);
/* 136 */     this.b.add(paramSegment.a().getWorldTransformClient().origin);
/*     */ 
/* 138 */     this.b.sub(this.a);
/* 139 */     return this.b.lengthSquared();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     db
 * JD-Core Version:    0.6.2
 */