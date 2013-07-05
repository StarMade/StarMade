/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class hp extends yz
/*     */ {
/*     */   private hH jdField_a_of_type_HH;
/*     */   private yr jdField_a_of_type_Yr;
/*     */   private yr b;
/*     */   private hj jdField_a_of_type_Hj;
/*     */   private yP jdField_a_of_type_YP;
/*     */   lP jdField_a_of_type_LP;
/*     */ 
/*     */   public hp(ClientState paramClientState)
/*     */   {
/*  30 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  40 */     k();
/*     */   }
/*     */ 
/*     */   private aO a() {
/*  44 */     return ((ct)a()).a().a.a.a;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  49 */     this.jdField_a_of_type_Yr = new yr(a(), 400.0F, 40.0F);
/*  50 */     this.b = new yr(a(), 400.0F, 40.0F);
/*     */ 
/*  52 */     this.jdField_a_of_type_HH = new hq(a(), a());
/*     */ 
/*  79 */     this.jdField_a_of_type_Hj = new hj(a(), ((ct)a()).a().a.a.a);
/*     */ 
/*  81 */     this.jdField_a_of_type_Hj.c();
/*  82 */     this.b.a().y = 40.0F;
/*  83 */     this.jdField_a_of_type_Hj.a().y = 80.0F;
/*  84 */     this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_Hj);
/*  85 */     this.jdField_a_of_type_Yr.c();
/*     */ 
/*  87 */     this.jdField_a_of_type_YP = new yP(100, 40, d.d(), a());
/*     */ 
/*  89 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*  90 */     this.jdField_a_of_type_YP.b.add(new hr(this));
/*     */     yP localyP;
/* 103 */     (
/* 104 */       localyP = new yP(100, 30, d.h(), a()))
/* 104 */       .a(new hs(this));
/*     */     hu localhu;
/* 133 */     (
/* 161 */       localhu = new hu(this, a(), "Abandon Home", new ht(this), a()))
/* 161 */       .a().x = 400.0F;
/*     */ 
/* 163 */     this.b.a(localyP);
/* 164 */     this.b.a(localhu);
/*     */ 
/* 166 */     this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YP);
/* 167 */     this.jdField_a_of_type_HH.a().y = 140.0F;
/* 168 */     this.jdField_a_of_type_HH.c();
/* 169 */     a(this.jdField_a_of_type_HH);
/* 170 */     a(this.jdField_a_of_type_Yr);
/* 171 */     a(this.b);
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 176 */     if (this.jdField_a_of_type_HH != null) return this.jdField_a_of_type_HH.a(); return 0.0F;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 181 */     if (this.jdField_a_of_type_HH != null) return this.jdField_a_of_type_HH.b(); return 0.0F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hp
 * JD-Core Version:    0.6.2
 */