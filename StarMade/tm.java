/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.network.objects.NetworkShip;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector3f;
/*     */ 
/*     */ public final class tm extends sJ
/*     */ {
/*     */   private long jdField_a_of_type_Long;
/*  58 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*     */ 
/*     */   public tm(kd paramkd, boolean paramBoolean)
/*     */   {
/*  33 */     super("Turret_Ent", paramkd);
/*  34 */     if (paramkd.isOnServer())
/*  35 */       this.a = new tl(this, paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a(lb paramlb)
/*     */   {
/*  44 */     if (System.currentTimeMillis() - this.jdField_a_of_type_Long < 5000L) {
/*  45 */       return;
/*     */     }
/*  47 */     if ((paramlb instanceof kd)) {
/*  48 */       kd localkd = (kd)paramlb;
/*     */ 
/*  50 */       if (((
/*  50 */         paramlb = ((vf)a()).a().a(localkd, a())) == 
/*  50 */         lZ.b) || (paramlb == lZ.a))
/*     */       {
/*  52 */         this.jdField_a_of_type_Long = System.currentTimeMillis();
/*  53 */         ((sL)this.a).a(localkd);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void b(xq paramxq)
/*     */   {
/*  66 */     super.b(paramxq);
/*     */ 
/*  70 */     if ((a() instanceof to)) {
/*  71 */       a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  72 */       a().a().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  73 */       a().a().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  74 */       a().a().targetVelocity.set(new Vector3f(0.0F, 0.0F, 0.0F)); return;
/*     */     }
/*  76 */     if ((a() instanceof tn)) {
/*  77 */       a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  78 */       a().a().targetPosition.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  79 */       a().a().targetVelocity.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*     */ 
/*  82 */       this.jdField_a_of_type_JavaxVecmathVector3f.set(((tn)a()).a());
/*  83 */       a().a().orientationDir.set(this.jdField_a_of_type_JavaxVecmathVector3f);
/*  84 */       a(paramxq, this.jdField_a_of_type_JavaxVecmathVector3f); return;
/*     */     }
/*  86 */     if ((a() instanceof tp)) {
/*  87 */       a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*  88 */       a(paramxq, this.jdField_a_of_type_JavaxVecmathVector3f);
/*     */ 
/*  90 */       paramxq = new Vector3f();
/*  91 */       Vector3f localVector3f = new Vector3f();
/*     */ 
/*  93 */       paramxq.set(((tp)a()).a());
/*  94 */       localVector3f.set(((tp)a()).b());
/*     */ 
/*  97 */       if (paramxq.length() > 0.0F) {
/*  98 */         a().a().targetPosition.set(paramxq);
/*  99 */         a().a().targetVelocity.set(localVector3f);
/*     */ 
/* 102 */         a(paramxq, localVector3f);
/*     */ 
/* 104 */         a(paramxq);
/* 105 */         b(paramxq);
/*     */ 
/* 107 */         ((tp)a()).a().set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 109 */         a().a(new tz());
/*     */       }
/* 111 */       return;
/* 112 */     }a().a().moveDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/* 113 */     a().a().orientationDir.set(new Vector3f(0.0F, 0.0F, 0.0F));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tm
 * JD-Core Version:    0.6.2
 */