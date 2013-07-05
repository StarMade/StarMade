/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.schine.graphicsengine.camera.Camera;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class ib extends yz
/*     */ {
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */   private final mF jdField_a_of_type_MF;
/*     */   private final String b;
/*     */ 
/*     */   public ib(hV paramhV, ClientState paramClientState, mF parammF, boolean paramBoolean)
/*     */   {
/*  62 */     super(paramClientState);
/*  63 */     this.jdField_a_of_type_MF = parammF;
/*  64 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*  65 */     if ((parammF instanceof kd)) {
/*  66 */       this.b = "Ship"; return;
/*  67 */     }if ((parammF instanceof kf)) {
/*  68 */       this.b = "Shop"; return;
/*  69 */     }if ((parammF instanceof kl)) {
/*  70 */       this.b = "Death Star"; return;
/*  71 */     }if ((parammF instanceof jy)) {
/*  72 */       this.b = "Asteroid"; return;
/*  73 */     }if ((parammF instanceof lD)) {
/*  74 */       this.b = "Player"; return;
/*     */     }
/*  76 */     this.b = "Other";
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  88 */     this.jdField_a_of_type_Boolean = (hV.a(this.jdField_a_of_type_HV) == this.jdField_a_of_type_MF);
/*     */ 
/*  90 */     ct localct = (ct)a();
/*  91 */     hV.b().set(this.jdField_a_of_type_MF.getWorldTransformClient().origin);
/*  92 */     if (localct.a() != null)
/*  93 */       hV.b().sub(localct.a().getWorldTransform().origin);
/*     */     else {
/*  95 */       hV.b().sub(xe.a().a());
/*     */     }
/*  97 */     float f = hV.b().length();
/*     */ 
/* 100 */     String str2 = this.jdField_a_of_type_MF.toNiceString();
/* 101 */     String str1 = (int)f + "m";
/* 102 */     hV.a(this.jdField_a_of_type_HV).b.set(0, this.b);
/* 103 */     hV.a(this.jdField_a_of_type_HV).b.set(1, str2);
/* 104 */     hV.a(this.jdField_a_of_type_HV).b.set(2, str1);
/* 105 */     hV.a(this.jdField_a_of_type_HV).a().set(5.0F, 5.0F, 0.0F);
/*     */ 
/* 109 */     ij.a(this.jdField_a_of_type_MF, hV.a(), this.jdField_a_of_type_MF == hV.b(this.jdField_a_of_type_HV), (ct)a());
/* 110 */     hV.a().w = 0.8F;
/* 111 */     hV.a(this.jdField_a_of_type_HV).a().c(hV.a());
/*     */ 
/* 113 */     if (this.jdField_a_of_type_Boolean)
/* 114 */       hV.b(this.jdField_a_of_type_HV).a_(5);
/*     */     else {
/* 116 */       hV.b(this.jdField_a_of_type_HV).a_(4);
/*     */     }
/* 118 */     hV.b(this.jdField_a_of_type_HV).b();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 125 */     return 64.0F;
/*     */   }
/*     */ 
/*     */   public final mF a()
/*     */   {
/* 132 */     return this.jdField_a_of_type_MF;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 137 */     return 255.0F;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ib
 * JD-Core Version:    0.6.2
 */