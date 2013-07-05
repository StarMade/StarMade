/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ final class fk extends yz
/*     */ {
/*     */   private yP jdField_a_of_type_YP;
/*     */   private yP b;
/*     */   private yP c;
/*     */   private yP d;
/*     */   private yP e;
/*  79 */   private int jdField_a_of_type_Int = 100;
/*     */ 
/*  80 */   public fk(ClientState paramClientState) { super(paramClientState);
/*     */ 
/*  83 */     this.jdField_a_of_type_YP = new yP(300, 30, d.b(), paramClientState);
/*  84 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*  85 */     this.jdField_a_of_type_YP.b.add("");
/*     */ 
/*  87 */     this.b = new yP(300, 30, d.b(), paramClientState);
/*  88 */     this.b.b = new ArrayList();
/*  89 */     this.b.b.add("");
/*  90 */     this.b.a().x += this.jdField_a_of_type_Int;
/*     */ 
/*  92 */     this.c = new yP(300, 30, d.b(), paramClientState);
/*  93 */     this.c.b = new ArrayList();
/*  94 */     this.c.b.add("");
/*  95 */     this.c.a().x += 2 * this.jdField_a_of_type_Int;
/*     */ 
/*  97 */     this.d = new yP(300, 30, d.b(), paramClientState);
/*  98 */     this.d.b = new ArrayList();
/*  99 */     this.d.b.add("");
/* 100 */     this.d.a().x += 3 * this.jdField_a_of_type_Int;
/*     */ 
/* 102 */     this.e = new yP(300, 30, d.b(), paramClientState);
/* 103 */     this.e.b = new ArrayList();
/* 104 */     this.e.b.add("");
/* 105 */     this.e.a().x += 4 * this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 116 */     r();
/* 117 */     this.jdField_a_of_type_YP.b();
/* 118 */     this.b.b();
/* 119 */     this.c.b();
/* 120 */     this.d.b();
/* 121 */     this.e.b();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 126 */     return this.jdField_a_of_type_YP.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 131 */     return this.jdField_a_of_type_YP.b() * 4.0F + 4 * this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 141 */     this.jdField_a_of_type_YP.c();
/* 142 */     this.b.c();
/* 143 */     this.c.c();
/* 144 */     this.d.c();
/* 145 */     this.e.c();
/*     */   }
/*     */ 
/*     */   public final void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
/* 149 */     this.jdField_a_of_type_YP.b.set(0, paramString1);
/* 150 */     this.b.b.set(0, paramString2);
/* 151 */     this.c.b.set(0, paramString3);
/* 152 */     this.d.b.set(0, paramString4);
/* 153 */     this.e.b.set(0, paramString5);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fk
 * JD-Core Version:    0.6.2
 */