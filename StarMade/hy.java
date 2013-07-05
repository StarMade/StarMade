/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class hy extends yz
/*     */   implements Observer
/*     */ {
/*     */   private yG jdField_a_of_type_YG;
/*     */   private yA jdField_a_of_type_YA;
/*     */   private int jdField_a_of_type_Int;
/*     */   private int jdField_b_of_type_Int;
/*     */   private ys jdField_b_of_type_Ys;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public hy(ClientState paramClientState, ys paramys)
/*     */   {
/*  37 */     super(paramClientState);
/*  38 */     this.jdField_a_of_type_Int = 540;
/*  39 */     this.jdField_b_of_type_Int = 345;
/*  40 */     this.jdField_b_of_type_Ys = paramys;
/*     */ 
/*  42 */     ((ct)super.a()).a().a().addObserver(this);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  56 */     if (this.jdField_a_of_type_Boolean) {
/*  57 */       e();
/*  58 */       this.jdField_a_of_type_Boolean = false;
/*     */     }
/*     */ 
/*  61 */     k();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  66 */     this.jdField_a_of_type_YG = new yG(this.jdField_a_of_type_Int, this.jdField_b_of_type_Int, (ct)super.a());
/*  67 */     this.jdField_a_of_type_YA = new yA((ct)super.a());
/*  68 */     this.jdField_a_of_type_YA.a(this.jdField_b_of_type_Ys);
/*  69 */     this.jdField_a_of_type_YA.g = true;
/*     */ 
/*  72 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/*     */ 
/*  74 */     e();
/*     */ 
/*  76 */     a(this.jdField_a_of_type_YG);
/*     */   }
/*     */ 
/*     */   private void e()
/*     */   {
/*  81 */     this.jdField_a_of_type_YA.clear();
/*     */ 
/*  83 */     Object localObject1 = ((ct)super.a()).a().a();
/*  84 */     int i = 0;
/*  85 */     for (localObject1 = ((lT)localObject1).a().iterator(); ((Iterator)localObject1).hasNext(); ) { Object localObject2 = (lP)((Iterator)localObject1).next();
/*     */ 
/*  87 */       yA localyA = new yA((ct)super.a());
/*     */ 
/*  89 */       yx localyx = new yx((ct)super.a(), 510.0F, 80.0F, i % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F));
/*     */       hz localhz;
/*  91 */       (
/*  96 */         localhz = new hz((ct)super.a(), (lP)localObject2))
/*  96 */         .c();
/*  97 */       localyx.a(localhz);
/*  98 */       localyA.a(new yD(localyx, localyx, (ct)super.a()));
/*     */ 
/* 100 */       (
/* 109 */         localObject2 = new hA((ct)super.a(), localyA, new hB(this, (ct)super.a(), (lP)localObject2, "+", i), new hB(this, (ct)super.a(), (lP)localObject2, "-", i)))
/* 109 */         .addObserver(this);
/*     */ 
/* 111 */       this.jdField_a_of_type_YA.a(new yD((yz)localObject2, (yz)localObject2, (ct)super.a()));
/* 112 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 274 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 279 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 290 */     if ((paramObservable instanceof yC)) {
/* 291 */       this.jdField_a_of_type_YA.f(); return;
/*     */     }
/* 293 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hy
 * JD-Core Version:    0.6.2
 */