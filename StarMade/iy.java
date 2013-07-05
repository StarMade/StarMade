/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class iy extends yr
/*     */   implements ys
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP b;
/*     */   private yP c;
/*     */   private yP d;
/*     */   private yP e;
/*     */   private yP f;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public iy(ClientState paramClientState)
/*     */   {
/*  35 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/*  43 */     if ((a() != null) && 
/*  44 */       (paramxp.jdField_a_of_type_Boolean) && (paramxp.jdField_a_of_type_Int == 0))
/*     */     {
/*  46 */       if (paramyz == this.b) {
/*  47 */         a().c(); return;
/*  48 */       }if (paramyz == this.c) {
/*  49 */         a().e(); return;
/*  50 */       }if (paramyz == this.d) {
/*  51 */         a().f(); return;
/*  52 */       }if (paramyz == this.e) {
/*  53 */         a().b(); return;
/*  54 */       }if (paramyz == this.f)
/*  55 */         a().d();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  69 */     if (!this.jdField_a_of_type_Boolean) {
/*  70 */       c();
/*     */     }
/*  72 */     super.b();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  77 */     return 180.0F;
/*     */   }
/*     */ 
/*     */   private bJ a()
/*     */   {
/*  83 */     return ((ct)a()).a().a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/*  88 */     return 260.0F;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 107 */     Object localObject = d.n();
/*     */ 
/* 109 */     this.jdField_a_of_type_YP = new yP(120, 30, d.o(), a());
/*     */ 
/* 113 */     this.b = new yP(120, 30, (UnicodeFont)localObject, a());
/* 114 */     this.c = new yP(120, 30, (UnicodeFont)localObject, a());
/* 115 */     this.d = new yP(120, 30, (UnicodeFont)localObject, a());
/* 116 */     this.e = new yP(120, 30, (UnicodeFont)localObject, a());
/* 117 */     this.f = new yP(120, 30, (UnicodeFont)localObject, a());
/*     */ 
/* 121 */     this.b.a(this);
/* 122 */     this.c.a(this);
/* 123 */     this.d.a(this);
/* 124 */     this.e.a(this);
/* 125 */     this.f.a(this);
/*     */ 
/* 127 */     this.b.g = true;
/* 128 */     this.c.g = true;
/* 129 */     this.d.g = true;
/* 130 */     this.e.g = true;
/* 131 */     this.f.g = true;
/*     */ 
/* 134 */     this.jdField_a_of_type_YP.b = new ArrayList(1);
/* 135 */     this.b.b = new ArrayList(1);
/* 136 */     this.c.b = new ArrayList(1);
/* 137 */     this.d.b = new ArrayList(1);
/* 138 */     this.e.b = new ArrayList(1);
/* 139 */     this.f.b = new ArrayList(1);
/*     */ 
/* 141 */     this.jdField_a_of_type_YP.b.add("Tutorial Controls");
/* 142 */     this.b.b.add("Skip Part");
/* 143 */     this.c.b.add("Repeat Part");
/* 144 */     this.d.b.add("skip current step");
/* 145 */     this.e.b.add("end tutorial");
/* 146 */     this.f.b.add("reset tutorial");
/*     */ 
/* 149 */     localObject = new Vector4f(0.0F, 0.0F, 0.0F, 0.7F);
/* 150 */     yx localyx1 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 151 */     yx localyx2 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 152 */     yx localyx3 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 153 */     yx localyx4 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 154 */     yx localyx5 = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/* 155 */     localObject = new yx(a(), 120.0F, 20.0F, (Vector4f)localObject);
/*     */ 
/* 158 */     localyx4.a().set(0.0F, 30.0F, 0.0F);
/*     */ 
/* 160 */     localyx3.a().set(0.0F, 60.0F, 0.0F);
/* 161 */     localyx2.a().set(120.0F, 60.0F, 0.0F);
/*     */ 
/* 164 */     ((yx)localObject).a().set(0.0F, 90.0F, 0.0F);
/* 165 */     localyx5.a().set(120.0F, 90.0F, 0.0F);
/*     */ 
/* 167 */     localyx1.a(this.jdField_a_of_type_YP);
/* 168 */     localyx2.a(this.b);
/* 169 */     localyx3.a(this.c);
/* 170 */     localyx4.a(this.d);
/* 171 */     localyx5.a(this.e);
/* 172 */     ((yx)localObject).a(this.f);
/*     */ 
/* 174 */     a(localyx1);
/* 175 */     a(localyx2);
/* 176 */     a(localyx3);
/* 177 */     a(localyx4);
/* 178 */     a(localyx5);
/* 179 */     a((yz)localObject);
/*     */ 
/* 182 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     iy
 * JD-Core Version:    0.6.2
 */