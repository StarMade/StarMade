/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class it extends yz
/*     */ {
/*     */   private int jdField_a_of_type_Int;
/*     */   private int jdField_b_of_type_Int;
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yE jdField_b_of_type_YE;
/*     */   private yE c;
/*     */   private yE d;
/*     */   private bz jdField_a_of_type_Bz;
/*     */   private yP jdField_a_of_type_YP;
/*  32 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private yP jdField_b_of_type_YP;
/*     */ 
/*     */   public it(ClientState paramClientState, bz parambz)
/*     */   {
/*  35 */     super(paramClientState);
/*  36 */     this.jdField_a_of_type_Int = 400;
/*  37 */     this.jdField_b_of_type_Int = 217;
/*  38 */     this.jdField_a_of_type_Bz = parambz;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  49 */     if (this.jdField_a_of_type_Boolean) {
/*  50 */       c();
/*     */     }
/*     */ 
/*  53 */     GlUtil.d();
/*  54 */     r();
/*     */ 
/*  56 */     this.jdField_a_of_type_YE.m();
/*  57 */     this.jdField_b_of_type_YE.m();
/*  58 */     this.c.m();
/*  59 */     this.d.m();
/*     */ 
/*  64 */     this.jdField_a_of_type_YE.a_(cU.a.a((this.jdField_a_of_type_YE.a_()) && (Mouse.isButtonDown(0))));
/*  65 */     this.jdField_a_of_type_YE.b();
/*     */ 
/*  67 */     this.jdField_b_of_type_YE.a_(cU.b.a((this.jdField_b_of_type_YE.a_()) && (Mouse.isButtonDown(0))));
/*  68 */     this.jdField_b_of_type_YE.b();
/*     */ 
/*  70 */     this.c.a_(cU.k.a((this.c.a_()) && (Mouse.isButtonDown(0))));
/*  71 */     this.c.b();
/*     */ 
/*  73 */     this.d.a_(cU.l.a((this.d.a_()) && (Mouse.isButtonDown(0))));
/*  74 */     this.d.b();
/*     */ 
/*  81 */     if (this.jdField_a_of_type_Bz.a >= 0) {
/*  82 */       ElementInformation localElementInformation = ElementKeyMap.getInfo(this.jdField_a_of_type_Bz.a);
/*  83 */       this.jdField_b_of_type_YP.b.set(0, localElementInformation.getFullName());
/*     */ 
/*  85 */       kf localkf = ((ct)a()).a();
/*  86 */       long l = 0L;
/*  87 */       if (localkf != null)
/*     */       {
/*  89 */         int i = localkf.a().a(localElementInformation.getId());
/*  90 */         int j = localkf.a().a(i);
/*  91 */         if ((i >= 0) && (j > 0))
/*     */         {
/*  93 */           l = localkf.a(localElementInformation, 1);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  98 */       if (l <= 0L)
/*  99 */         this.jdField_a_of_type_YP.b.set(0, "not for sale");
/*     */       else {
/* 101 */         this.jdField_a_of_type_YP.b.set(0, "Cost: " + l);
/*     */       }
/*     */     }
/*     */ 
/* 105 */     this.jdField_a_of_type_YP.b();
/* 106 */     this.jdField_b_of_type_YP.b();
/* 107 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 114 */     return this.jdField_b_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 119 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 130 */     this.jdField_a_of_type_YE = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 131 */     this.jdField_b_of_type_YE = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 132 */     this.d = new yE(xe.a().a("buttons-8x8-gui-"), a());
/* 133 */     this.c = new yE(xe.a().a("buttons-8x8-gui-"), a());
/*     */ 
/* 135 */     this.jdField_a_of_type_YE.a_(cU.a.a(false));
/* 136 */     this.jdField_b_of_type_YE.a_(cU.b.a(false));
/* 137 */     this.c.a_(cU.k.a(false));
/* 138 */     this.d.a_(cU.l.a(false));
/*     */ 
/* 140 */     this.jdField_a_of_type_YE.a(16.0F, 47.0F, 0.0F);
/*     */ 
/* 142 */     this.jdField_b_of_type_YE.a(172.0F, 47.0F, 0.0F);
/* 143 */     this.c.a(16.0F, 115.0F, 0.0F);
/* 144 */     this.d.a(172.0F, 115.0F, 0.0F);
/* 145 */     this.c.a.set(0.8F, 0.8F, 0.8F);
/* 146 */     this.d.a.set(0.8F, 0.8F, 0.8F);
/*     */ 
/* 149 */     this.jdField_a_of_type_YE.a(this.jdField_a_of_type_Bz);
/* 150 */     this.jdField_b_of_type_YE.a(this.jdField_a_of_type_Bz);
/* 151 */     this.c.a(this.jdField_a_of_type_Bz);
/* 152 */     this.d.a(this.jdField_a_of_type_Bz);
/*     */ 
/* 154 */     this.jdField_a_of_type_YE.a = "buy";
/* 155 */     this.jdField_b_of_type_YE.a = "sell";
/* 156 */     this.c.a = "buy_more";
/* 157 */     this.d.a = "sell_more";
/*     */ 
/* 159 */     this.jdField_a_of_type_YE.c();
/* 160 */     this.jdField_b_of_type_YE.c();
/* 161 */     this.c.c();
/* 162 */     this.d.c();
/*     */ 
/* 167 */     this.jdField_a_of_type_YP = new yP(200, 200, d.i(), a());
/* 168 */     this.jdField_a_of_type_YP.b = new ArrayList(1);
/* 169 */     this.jdField_a_of_type_YP.b.add("Cost: 0");
/* 170 */     this.jdField_a_of_type_YP.a(16.0F, 27.0F, 0.0F);
/*     */ 
/* 174 */     this.jdField_b_of_type_YP = new yP(200, 200, d.h(), a());
/* 175 */     this.jdField_b_of_type_YP.b = new ArrayList(1);
/*     */ 
/* 177 */     this.jdField_b_of_type_YP.b.add("");
/*     */ 
/* 179 */     this.jdField_b_of_type_YP.a(16.0F, 3.0F, 0.0F);
/*     */ 
/* 181 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it
 * JD-Core Version:    0.6.2
 */