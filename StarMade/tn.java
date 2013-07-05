/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementDocking;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class tn extends sM
/*     */ {
/*  25 */   private final Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*  26 */   private Vector3f b = new Vector3f();
/*     */   private long jdField_a_of_type_Long;
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public tn(wk paramwk)
/*     */   {
/*  35 */     super(paramwk);
/*     */   }
/*     */ 
/*     */   public final Vector3f a()
/*     */   {
/*  81 */     return this.jdField_a_of_type_JavaxVecmathVector3f;
/*     */   }
/*     */ 
/*     */   private static boolean a(SegmentController paramSegmentController1, SegmentController paramSegmentController2) {
/*  85 */     return (paramSegmentController1.getDockingController().a() != null) && (paramSegmentController1.getDockingController().a().to.a().a() == paramSegmentController2);
/*     */   }
/*     */ 
/*     */   public final boolean c() {
/*  89 */     this.jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
/*  90 */     this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean b() {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean d() {
/*  99 */     if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 5000L) {
/* 100 */       a(new tx());
/* 101 */       return false;
/*     */     }
/*     */     Object localObject1;
/* 105 */     if ((
/* 105 */       localObject1 = ((sL)a().a).a()) != null)
/*     */     {
/* 107 */       ((mF)localObject1).calcWorldTransformRelative(((SegmentController)a()).getSectorId(), ((vg)((SegmentController)a()).getState()).a().getSector(((SegmentController)a()).getSectorId()).a);
/* 108 */       Object localObject3 = localObject1; Object localObject2 = this; if ((localObject3 instanceof SegmentController));
/* 108 */       System.err.println("[AI][TURRET] Dead Entity. Getting new Target"); ((tn)localObject2).b.sub(((mF)localObject3).getClientTransform().origin, ((mF)((tn)localObject2).a().a()).getWorldTransform().origin); if ((((tn)localObject2).b.length() > ((tn)localObject2).a().b() ? 0 : ((localObject3 instanceof kd)) && (((kd)localObject3).c()) && (((tn)localObject2).b.length() > ((tn)localObject2).a().b() / 2.0F) && (!((tn)localObject2).a().a(kq.a).a().equals("Selected Target")) ? 0 : ((localObject3 instanceof cw)) && (!(localObject3 instanceof lD)) && (((cw)localObject3).a().isEmpty()) && (!((wp)localObject3).a().a()) ? 0 : ((localObject3 instanceof lD)) && (((lD)localObject3).isHidden()) ? 0 : ((localObject3 instanceof kd)) && (((kd)localObject3).a()) ? 0 : a((SegmentController)localObject3, (SegmentController)((tn)localObject2).a()) ? 0 : a((SegmentController)((tn)localObject2).a(), (SegmentController)localObject3) ? 0 : 1) == 0) {
/* 109 */         a(new tx());
/* 110 */         return false;
/*     */       }
/* 112 */       localObject2 = ((SegmentController)a()).getWorldTransform().origin;
/*     */ 
/* 115 */       localObject1 = new Vector3f(((mF)localObject1).getClientTransform().origin);
/*     */ 
/* 117 */       this.jdField_a_of_type_JavaxVecmathVector3f.sub((Tuple3f)localObject1, (Tuple3f)localObject2);
/*     */ 
/* 119 */       (
/* 120 */         localObject1 = new Vector3f(this.jdField_a_of_type_JavaxVecmathVector3f))
/* 120 */         .normalize();
/* 121 */       localObject2 = GlUtil.c(new Vector3f(), ((SegmentController)a()).getWorldTransform());
/*     */ 
/* 123 */       (
/* 124 */         localObject3 = new Vector3f((Vector3f)localObject2))
/* 124 */         .negate();
/* 125 */       ((Vector3f)localObject3).sub((Tuple3f)localObject1);
/* 126 */       new Vector3f((Vector3f)localObject2)
/* 127 */         .sub((Tuple3f)localObject1);
/*     */ 
/* 129 */       if (((Vector3f)localObject1).epsilonEquals((Tuple3f)localObject2, 0.4F)) {
/* 130 */         a(new tu());
/* 131 */         return true;
/*     */       }
/*     */     } else {
/* 134 */       System.err.println("[AI] " + a() + " HAS NO TARGET: resetting");
/* 135 */       a(new tx());
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tn
 * JD-Core Version:    0.6.2
 */