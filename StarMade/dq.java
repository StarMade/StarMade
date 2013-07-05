/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.forms.Mesh;
/*     */ 
/*     */ public final class dq
/*     */   implements xg, zn
/*     */ {
/*     */   public float a;
/*     */   private int jdField_a_of_type_Int;
/*     */   private int jdField_b_of_type_Int;
/*     */   private float jdField_b_of_type_Float;
/*     */   private float c;
/*     */   private Mesh jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  59 */     GlUtil.d();
/*     */ 
/*  64 */     GlUtil.a(xd.a.a());
/*  65 */     GlUtil.b(10.0F, 10.0F, 10.0F);
/*  66 */     GL11.glEnable(2884);
/*  67 */     GL11.glEnable(3042);
/*  68 */     GL11.glBlendFunc(770, 771);
/*     */ 
/*  70 */     d.r.a = this;
/*  71 */     d.r.b();
/*  72 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
/*  73 */     d.r.d();
/*  74 */     GL11.glDisable(3042);
/*  75 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  88 */     this.jdField_a_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)xe.a().a("BlackHole").a().iterator().next());
/*     */ 
/*  90 */     this.jdField_b_of_type_Float = 5.0E-007F;
/*  91 */     this.c = 0.7F;
/*     */ 
/*  93 */     this.jdField_a_of_type_Int = xe.a().a("detail").a().a().c;
/*  94 */     this.jdField_b_of_type_Int = xe.a().a("detail_normal").a().a().c;
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 114 */     GL13.glActiveTexture(33984);
/* 115 */     GL11.glDisable(3553);
/* 116 */     GL11.glBindTexture(3553, 0);
/*     */ 
/* 118 */     GL13.glActiveTexture(33985);
/* 119 */     GL11.glDisable(3553);
/* 120 */     GL11.glBindTexture(3553, 0);
/*     */ 
/* 122 */     GL13.glActiveTexture(33984);
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 143 */     GlUtil.a(paramzj, "cSharp", this.jdField_b_of_type_Float);
/* 144 */     GlUtil.a(paramzj, "cCover", this.c);
/* 145 */     GlUtil.a(paramzj, "cMove", this.jdField_a_of_type_Float);
/* 146 */     GlUtil.a(paramzj, "lightPos", xd.a.a().x, xd.a.a().y, xd.a.a().z, 1.0F);
/*     */ 
/* 151 */     GL13.glActiveTexture(33984);
/* 152 */     GL11.glEnable(3553);
/* 153 */     GL11.glBindTexture(3553, this.jdField_a_of_type_Int);
/*     */ 
/* 156 */     GL13.glActiveTexture(33985);
/* 157 */     GL11.glEnable(3553);
/* 158 */     GL11.glBindTexture(3553, this.jdField_b_of_type_Int);
/*     */ 
/* 160 */     GL13.glActiveTexture(33986);
/*     */ 
/* 162 */     GlUtil.a(paramzj, "tex", 0);
/* 163 */     GlUtil.a(paramzj, "nmap", 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dq
 * JD-Core Version:    0.6.2
 */