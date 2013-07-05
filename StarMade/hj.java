/*     */ import java.util.HashMap;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class hj extends yr
/*     */ {
/*     */   private yG a;
/*     */   private ys b;
/*     */ 
/*     */   public hj(ClientState paramClientState, ys paramys)
/*     */   {
/*  23 */     super(paramClientState, 530.0F, 60.0F);
/*  24 */     this.jdField_b_of_type_Ys = paramys;
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*     */     Object localObject;
/*  35 */     if ((
/*  35 */       localObject = (ct)a())
/*  35 */       .a().h() != 0)
/*     */     {
/*     */       lP locallP;
/*  37 */       if ((
/*  37 */         locallP = ((ct)localObject).a().a(((ct)localObject).a().h())) != null)
/*     */       {
/*  39 */         if (((
/*  39 */           localObject = (lX)locallP.a().get(((ct)localObject).a().getName())) != null) && 
/*  39 */           (((lX)localObject).a(locallP)))
/*  40 */           super.b();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  55 */     this.jdField_a_of_type_YG = new yG(this.jdField_b_of_type_Float, this.jdField_a_of_type_Float, a());
/*     */ 
/*  61 */     yr localyr = new yr(a(), 100.0F, 40.0F);
/*     */     yP localyP1;
/*  63 */     (
/*  64 */       localyP1 = new yP(100, 20, a()))
/*  64 */       .a("Public Faction");
/*  65 */     hk localhk = new hk(a());
/*     */     yP localyP2;
/* 107 */     (
/* 108 */       localyP2 = new yP(100, 20, a()))
/* 108 */       .a("Consider neutral enemy");
/* 109 */     hl localhl = new hl(a());
/*     */     yP localyP3;
/* 152 */     (
/* 153 */       localyP3 = new yP(100, 20, a()))
/* 153 */       .a("Declare war on hostile action");
/* 154 */     hm localhm = new hm(a());
/*     */     yN localyN1;
/* 197 */     (
/* 199 */       localyN1 = new yN(a(), 90, 20, "Post News", this.jdField_b_of_type_Ys, ((ct)a()).a().a.a.a)).a = 
/* 199 */       "POST_NEWS";
/* 200 */     localyN1.a().x = 260.0F;
/*     */     hn localhn;
/* 203 */     (
/* 222 */       localhn = new hn(a(), "Roles", this.jdField_b_of_type_Ys, ((ct)a()).a().a.a.a)).a = 
/* 222 */       "ROLES";
/* 223 */     localhn.a().x = 430.0F;
/*     */     yN localyN2;
/* 225 */     (
/* 227 */       localyN2 = new yN(a(), 120, 20, "Edit Description", this.jdField_b_of_type_Ys, ((ct)a()).a().a.a.a)).a = 
/* 227 */       "EDIT_DESC";
/* 228 */     localyN2.a().x = 130.0F;
/*     */     yN localyN3;
/* 230 */     (
/* 248 */       localyN3 = new yN(a(), 60, 20, "Offers", new ho(this), ((ct)a()).a().a.a.a))
/* 248 */       .a().x = 360.0F;
/*     */ 
/* 250 */     localhk.a().y = 23.0F;
/*     */ 
/* 254 */     localyP1.a().x = 35.0F;
/* 255 */     localyP1.a().y = 32.0F;
/*     */ 
/* 257 */     localhm.a().x = 145.0F;
/* 258 */     localhm.a().y = 23.0F;
/* 259 */     localyP3.a().x = 180.0F;
/* 260 */     localyP3.a().y = 32.0F;
/*     */ 
/* 264 */     localhl.a().x = 350.0F;
/* 265 */     localhl.a().y = 23.0F;
/* 266 */     localyP2.a().x = 385.0F;
/* 267 */     localyP2.a().y = 32.0F;
/*     */ 
/* 270 */     localyr.a(localhk);
/* 271 */     localyr.a(localyP1);
/* 272 */     localyr.a(localyP2);
/* 273 */     localyr.a(localhl);
/* 274 */     localyr.a(localyP3);
/* 275 */     localyr.a(localhm);
/* 276 */     localyr.a(localyN1);
/* 277 */     localyr.a(localyN2);
/* 278 */     localyr.a(localyN3);
/* 279 */     localyr.a(localhn);
/*     */ 
/* 281 */     this.jdField_a_of_type_YG.c(localyr);
/* 282 */     a(this.jdField_a_of_type_YG);
/* 283 */     super.c();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hj
 * JD-Core Version:    0.6.2
 */