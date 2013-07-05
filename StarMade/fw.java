/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class fw extends yz
/*     */ {
/*     */   private yr jdField_a_of_type_Yr;
/*     */   private yr jdField_b_of_type_Yr;
/*     */   private yG jdField_a_of_type_YG;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP jdField_b_of_type_YP;
/*     */   private io jdField_a_of_type_Io;
/*  38 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private yA jdField_a_of_type_YA;
/*     */ 
/*     */   public fw(ClientState paramClientState)
/*     */   {
/*  42 */     super(paramClientState);
/*  43 */     this.jdField_a_of_type_Yr = new yr(a(), 300.0F, 300.0F);
/*  44 */     this.jdField_a_of_type_Io = new io(paramClientState);
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz)
/*     */   {
/*  49 */     this.jdField_a_of_type_Yr.a(paramyz);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*  54 */     this.jdField_a_of_type_Yr.a();
/*     */   }
/*     */ 
/*     */   public final void b(yz paramyz) {
/*  58 */     this.jdField_a_of_type_Yr.b(paramyz);
/*     */   }
/*     */ 
/*     */   public final void b() {
/*  62 */     if (this.jdField_a_of_type_Boolean)
/*  63 */       c();
/*     */     String str;
/*  69 */     if (!(
/*  69 */       str = "press " + cv.U.b() + "\nto enter advanced\nbuild mode\n\n")
/*  69 */       .equals(this.jdField_a_of_type_YP.b.get(0))) {
/*  70 */       this.jdField_a_of_type_YP.b.set(0, str);
/*     */     }
/*  72 */     GlUtil.d();
/*     */ 
/*  74 */     this.jdField_a_of_type_Io.h(17);
/*  75 */     this.jdField_a_of_type_Io.b();
/*     */ 
/*  77 */     r();
/*  78 */     this.jdField_a_of_type_Yr.b();
/*  79 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final ai a() {
/*  83 */     return ((ct)a()).a().a.a.a.a();
/*     */   }
/*     */ 
/*     */   public final az a()
/*     */   {
/*     */     ar localar;
/*  88 */     if ((
/*  88 */       localar = ((ct)a()).a().a.a.a)
/*  88 */       .a().a().a.c) {
/*  89 */       return localar.a().a().a.a();
/*     */     }
/*  91 */     return localar.a().a().a();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/*  96 */     return this.jdField_a_of_type_Yr.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 102 */     return this.jdField_a_of_type_Yr.b();
/*     */   }
/*     */ 
/*     */   public final boolean a_() {
/* 106 */     return this.jdField_a_of_type_Yr.a_();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 117 */     this.jdField_a_of_type_Io.c();
/* 118 */     this.jdField_a_of_type_YP = new yP(100, 30, d.c(), a());
/* 119 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*     */ 
/* 121 */     this.jdField_a_of_type_YP.b.add("press " + cv.U.b() + "\nto enter advanced\nbuild mode\n\n");
/*     */ 
/* 123 */     this.jdField_a_of_type_YP.b.add("press " + cv.t.b() + "\nto reset the camera\n\n");
/*     */ 
/* 125 */     this.jdField_a_of_type_YP.b.add("hold Shift\nto move faster\n\n");
/*     */ 
/* 127 */     this.jdField_a_of_type_YP.b.add("press " + cv.r.b() + "\nfor flight mode\n\n");
/*     */ 
/* 129 */     this.jdField_a_of_type_YP.b.add("Shift + MouseWheel to zoom\n\n");
/*     */ 
/* 131 */     this.jdField_b_of_type_Yr = new yr(a());
/*     */ 
/* 133 */     this.jdField_b_of_type_YP = new yP(100, 30, d.d(), a());
/* 134 */     this.jdField_b_of_type_YP.b = new ArrayList();
/* 135 */     this.jdField_b_of_type_YP.b.add("");
/*     */ 
/* 137 */     this.jdField_b_of_type_YP.a().y = 100.0F;
/*     */ 
/* 139 */     this.jdField_a_of_type_YG = new yG(533.0F, 316.0F, a());
/* 140 */     this.jdField_a_of_type_YG.a().set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 142 */     this.jdField_a_of_type_YA = new yA(a());
/*     */ 
/* 144 */     this.jdField_a_of_type_YA.a(a());
/* 145 */     this.jdField_b_of_type_Yr.a(this.jdField_a_of_type_YA);
/* 146 */     this.jdField_b_of_type_Yr.a(this.jdField_b_of_type_YP);
/*     */ 
/* 149 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/*     */ 
/* 153 */     this.jdField_a_of_type_YA.a(this.jdField_b_of_type_YP);
/*     */ 
/* 155 */     this.jdField_a_of_type_Yr.c();
/*     */ 
/* 157 */     fw localfw = this; this.jdField_a_of_type_YA.clear(); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 100, "Remove Mode", new fx(localfw, localfw.a()), false)); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "X", new fz(localfw.a(), localfw.a().a), false)); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "Y", new fz(localfw.a(), localfw.a().b), false)); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "Z", new fz(localfw.a(), localfw.a().c), false)); Object localObject = new yM(localfw.a(), 90, "Orientation", new fF(localfw.a()), false); localfw.jdField_a_of_type_YA.a((yD)localObject); (localObject = new yP(100, 40, d.d(), localfw.a())).a("Symmetry Build Planes"); localfw.jdField_a_of_type_YA.a(new yD((yz)localObject, (yz)localObject, localfw.a())); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "XY-Plane", new fC(localfw.a(), 1), false)); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "XZ-Plane", new fC(localfw.a(), 2), false)); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 60, "YZ-Plane", new fC(localfw.a(), 4), false)); localfw.jdField_a_of_type_YA.a(new yM(localfw.a(), 100, "Odd-sym Mode", new fy(localfw, localfw.a()), false));
/*     */ 
/* 159 */     super.a(this.jdField_a_of_type_Yr);
/*     */ 
/* 161 */     this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YG);
/*     */ 
/* 163 */     d();
/*     */ 
/* 166 */     this.jdField_a_of_type_Yr.a().set(xm.b() - 270, 64.0F, 0.0F);
/* 167 */     this.jdField_a_of_type_Yr.g = true;
/*     */ 
/* 169 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 235 */     super.a(paramxq);
/* 236 */     if (Keyboard.isKeyDown(cv.U.a())) {
/* 237 */       this.jdField_a_of_type_YG.c(this.jdField_b_of_type_Yr); return;
/*     */     }
/* 239 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YP);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fw
 * JD-Core Version:    0.6.2
 */