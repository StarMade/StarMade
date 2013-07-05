/*     */ import java.io.PrintStream;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.EXTFramebufferMultisample;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.lwjgl.opengl.GL30;
/*     */ import org.schema.schine.graphicsengine.core.GLException;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ 
/*     */ public final class xi
/*     */ {
/*     */   private IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/*     */   public int a;
/*     */   private int jdField_d_of_type_Int;
/*     */   public boolean a;
/*     */   public boolean b;
/*     */   private boolean c;
/*     */   private boolean jdField_d_of_type_Boolean;
/*     */   public int b;
/*     */   public int c;
/* 167 */   private boolean jdField_e_of_type_Boolean = true;
/*     */   private xi jdField_a_of_type_Xi;
/* 172 */   private boolean jdField_f_of_type_Boolean = true;
/*     */   private int jdField_e_of_type_Int;
/*     */   private int jdField_f_of_type_Int;
/*     */ 
/*     */   private static int b()
/*     */   {
/* 137 */     return ((Integer)xu.S.a()).intValue();
/*     */   }
/*     */ 
/*     */   public xi(int paramInt1, int paramInt2)
/*     */   {
/* 180 */     this.jdField_b_of_type_Int = paramInt1;
/*     */ 
/* 189 */     this.jdField_c_of_type_Int = paramInt2;
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 341 */     System.out.println("[FBO] cleaning up FBO ");
/* 342 */     xi localxi = this; if (this.jdField_a_of_type_JavaNioIntBuffer != null) { System.out.println("[FBO] deleting Frame buffers "); GL30.glDeleteFramebuffers(localxi.jdField_a_of_type_JavaNioIntBuffer); } else { System.out.println("[FBO] no Frame buffers to clean up "); }
/* 343 */     localxi = this;
/*     */     IntBuffer localIntBuffer;
/* 343 */     (localIntBuffer = BufferUtils.createIntBuffer(1)).put(0, localxi.jdField_e_of_type_Int); localIntBuffer.rewind(); GL30.glDeleteRenderbuffers(localIntBuffer); localIntBuffer.put(0, localxi.jdField_f_of_type_Int); localIntBuffer.rewind(); GL30.glDeleteRenderbuffers(localIntBuffer);
/* 344 */     System.out.println("[FBO] deleting  fbo textures " + this.jdField_a_of_type_Int);
/* 345 */     GL11.glDeleteTextures(this.jdField_a_of_type_Int);
/* 346 */     if (this.jdField_a_of_type_Xi != null) {
/* 347 */       System.out.println("[FBO] deleting BLIN fbo textures " + this.jdField_a_of_type_Xi.jdField_a_of_type_Int);
/* 348 */       GL11.glDeleteTextures(this.jdField_a_of_type_Xi.jdField_a_of_type_Int);
/*     */     }
/* 350 */     if (this.jdField_d_of_type_Int != 0) {
/* 351 */       System.out.println("[FBO] deleting depth fbo texture " + this.jdField_d_of_type_Int);
/* 352 */       GL11.glDeleteTextures(this.jdField_d_of_type_Int);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static int c()
/*     */   {
/*     */     IntBuffer localIntBuffer;
/* 476 */     GL30.glGenRenderbuffers(localIntBuffer = BufferUtils.createIntBuffer(1));
/*     */ 
/* 477 */     return localIntBuffer.get(0);
/*     */   }
/*     */ 
/*     */   private void a(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 491 */     IntBuffer localIntBuffer = BufferUtils.createIntBuffer(16);
/* 492 */     GL11.glGetInteger(34024, localIntBuffer);
/* 493 */     localIntBuffer.rewind();
/* 494 */     int i = localIntBuffer.get(0);
/*     */ 
/* 497 */     if ((paramInt1 > i) || (paramInt2 > i)) {
/* 498 */       throw new GLException("height or width of renderbuffer store exceeds max");
/*     */     }
/*     */ 
/* 501 */     if ((b() == 0) || (!this.jdField_f_of_type_Boolean)) {
/* 502 */       GL30.glRenderbufferStorage(36161, paramInt3, paramInt2, paramInt1); return;
/*     */     }
/* 504 */     System.err.println("CREATING MULTISAMPLED RENDERBUFFER. SAMPLES: " + b());
/* 505 */     EXTFramebufferMultisample.glRenderbufferStorageMultisampleEXT(36161, b(), paramInt3, paramInt2, paramInt1);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 613 */     if ((this.jdField_a_of_type_Boolean) && (b() > 0)) {
/* 614 */       xi localxi = this; GL30.glBindFramebuffer(36008, localxi.jdField_a_of_type_JavaNioIntBuffer.get(0)); GL30.glBindFramebuffer(36009, localxi.jdField_a_of_type_Xi.jdField_a_of_type_JavaNioIntBuffer.get(0)); GL30.glBlitFramebuffer(0, 0, localxi.jdField_b_of_type_Int, localxi.jdField_c_of_type_Int, 0, 0, localxi.jdField_b_of_type_Int, localxi.jdField_c_of_type_Int, 16384, 9728); GL30.glBindFramebuffer(36008, 0); GL30.glBindFramebuffer(36009, 0);
/*     */     }
/*     */ 
/* 617 */     GL30.glBindRenderbuffer(36161, 0);
/* 618 */     GL30.glBindFramebuffer(36160, 0);
/*     */ 
/* 620 */     GL11.glViewport(0, 0, xm.b(), xm.a());
/* 621 */     this.jdField_a_of_type_Boolean = false;
/*     */   }
/*     */ 
/*     */   private static void f() {
/* 625 */     GL11.glBegin(7);
/*     */ 
/* 627 */     GL11.glTexCoord2f(0.0F, 0.0F); GL11.glVertex2f(0.0F, 0.0F);
/*     */ 
/* 629 */     GL11.glTexCoord2f(0.0F, 1.0F); GL11.glVertex2f(0.0F, xm.a());
/*     */ 
/* 631 */     GL11.glTexCoord2f(1.0F, 1.0F); GL11.glVertex2f(xm.b(), xm.a());
/*     */ 
/* 633 */     GL11.glTexCoord2f(1.0F, 0.0F); GL11.glVertex2f(xm.b(), 0.0F);
/*     */ 
/* 635 */     GL11.glEnd();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 646 */     h();
/* 647 */     f();
/* 648 */     g();
/*     */   }
/*     */ 
/*     */   public final void a(zj paramzj)
/*     */   {
/* 660 */     h();
/* 661 */     paramzj.b();
/* 662 */     f();
/* 663 */     paramzj.d();
/* 664 */     g();
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 762 */     GL11.glViewport(0, 0, this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/*     */ 
/* 765 */     int i = this.jdField_a_of_type_JavaNioIntBuffer.get(0); GL30.glBindFramebuffer(36160, i);
/* 766 */     if (!this.jdField_b_of_type_Boolean) {
/* 767 */       i = this.jdField_f_of_type_Int; GL30.glBindRenderbuffer(36161, i);
/*     */     }
/* 769 */     if (!this.jdField_e_of_type_Boolean) {
/* 770 */       i = this.jdField_e_of_type_Int; GL30.glBindRenderbuffer(36161, i);
/*     */     }
/*     */ 
/* 773 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   private static void g() {
/* 777 */     GL11.glEnable(2884);
/*     */ 
/* 780 */     GL11.glBindTexture(3553, 0);
/* 781 */     GL11.glDisable(3553);
/* 782 */     GL11.glEnable(2896);
/* 783 */     GL11.glDisable(3042);
/* 784 */     GL11.glEnable(2929);
/* 785 */     GlUtil.a(5889);
/* 786 */     GlUtil.c();
/* 787 */     GlUtil.a(5888);
/* 788 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final int a()
/*     */   {
/* 851 */     if (b() > 0) {
/* 852 */       return this.jdField_a_of_type_Xi.jdField_a_of_type_Int;
/*     */     }
/* 854 */     return this.jdField_a_of_type_Int;
/*     */   }
/*     */ 
/*     */   public final void e()
/*     */   {
/* 881 */     if (this.jdField_c_of_type_Boolean) {
/* 882 */       return;
/*     */     }
/* 884 */     this.jdField_c_of_type_Boolean = true;
/* 885 */     if (this.jdField_d_of_type_Boolean)
/*     */     {
/* 887 */       a();
/*     */     }
/*     */ 
/* 892 */     System.out.println("[FrameBuffer] creating frame buffer  " + this.jdField_b_of_type_Int + " / " + this.jdField_c_of_type_Int);
/*     */ 
/* 894 */     if ((b() > 0) && (this.jdField_f_of_type_Boolean)) {
/* 895 */       this.jdField_a_of_type_Xi = new xi(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 896 */       this.jdField_a_of_type_Xi.jdField_e_of_type_Boolean = this.jdField_e_of_type_Boolean;
/* 897 */       this.jdField_a_of_type_Xi.jdField_b_of_type_Boolean = this.jdField_b_of_type_Boolean;
/* 898 */       this.jdField_a_of_type_Xi.jdField_f_of_type_Boolean = false;
/* 899 */       this.jdField_e_of_type_Boolean = false;
/* 900 */       this.jdField_b_of_type_Boolean = false;
/* 901 */       this.jdField_a_of_type_Xi.e();
/*     */     }
/*     */ 
/* 904 */     xi localxi1 = this; this.jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1); GL30.glGenFramebuffers(localxi1.jdField_a_of_type_JavaNioIntBuffer);
/* 905 */     int j = this.jdField_a_of_type_JavaNioIntBuffer.get(0); GL30.glBindFramebuffer(36160, j);
/*     */ 
/* 908 */     GlUtil.f();
/*     */     int m;
/*     */     int k;
/* 911 */     if (this.jdField_e_of_type_Boolean) {
/* 912 */       localxi1 = this; System.out.println("[FrameBuffer] initializing frame buffer textures "); GL11.glGenTextures(GlUtil.a()); localxi1.jdField_a_of_type_Int = GlUtil.a().get(0); GL11.glBindTexture(3553, localxi1.jdField_a_of_type_Int); GlUtil.f(); GL11.glTexParameteri(3553, 10241, 9729); GL11.glTexParameteri(3553, 10240, 9729); GlUtil.f(); GL11.glTexParameteri(3553, 10242, 10496); GL11.glTexParameteri(3553, 10243, 10496); GlUtil.f(); GL11.glTexImage2D(3553, 0, 6408, localxi1.jdField_b_of_type_Int, localxi1.jdField_c_of_type_Int, 0, 6408, 5121, null); GlUtil.f();
/* 913 */       j = this.jdField_a_of_type_Int; GL30.glFramebufferTexture2D(36160, 36064, 3553, j, 0);
/* 914 */       GlUtil.f();
/*     */     } else {
/* 916 */       localxi1 = this; this.jdField_e_of_type_Int = c(); j = localxi1.jdField_e_of_type_Int; GL30.glBindRenderbuffer(36161, j); j = localxi1.jdField_c_of_type_Int; m = localxi1.jdField_b_of_type_Int; localxi1.a(j, m, 6408); k = localxi1.jdField_e_of_type_Int; GL30.glFramebufferRenderbuffer(36160, 36064, 36161, k); GL30.glBindRenderbuffer(36161, 0);
/*     */     }
/* 918 */     GlUtil.f();
/* 919 */     if (this.jdField_b_of_type_Boolean) {
/* 920 */       localxi1 = this; System.err.println("creating depth texture"); GL11.glGenTextures(GlUtil.a()); k = GlUtil.a().get(0); GL11.glBindTexture(3553, k); GL11.glTexParameteri(3553, 10242, 10497); GL11.glTexParameteri(3553, 10243, 10497); GL11.glTexParameteri(3553, 33085, 3); GlUtil.f(); GL11.glTexParameteri(3553, 10241, 9987); GlUtil.f(); GL11.glTexParameteri(3553, 10240, 9729); GlUtil.f(); GL11.glTexParameteri(3553, 33169, 1); GL11.glTexParameteri(3553, 34891, 32841); GL11.glTexImage2D(3553, 0, 6408, localxi1.jdField_b_of_type_Int, localxi1.jdField_c_of_type_Int, 0, 6408, 5121, null); GlUtil.f(); this.jdField_d_of_type_Int = k;
/* 921 */       int i = this.jdField_d_of_type_Int; GL30.glFramebufferTexture2D(36160, 36096, 3553, i, 0);
/* 922 */       GlUtil.f();
/*     */     }
/*     */     else {
/* 925 */       xi localxi2 = this; this.jdField_f_of_type_Int = c(); j = localxi2.jdField_f_of_type_Int; GL30.glBindRenderbuffer(36161, j); m = localxi2.jdField_c_of_type_Int; k = localxi2.jdField_b_of_type_Int; localxi2.a(m, k, 6402); j = localxi2.jdField_f_of_type_Int; GL30.glFramebufferRenderbuffer(36160, 36096, 36161, j); GL30.glBindRenderbuffer(36161, 0);
/*     */     }
/*     */ 
/* 928 */     GlUtil.f();
/* 929 */     if ((j = GL30.glCheckFramebufferStatus(36160)) != 36053) { String str = "unknown"; switch (j) { case 36061:
/* 929 */         str = "GL_FRAMEBUFFER_UNSUPPORTED_EXn"; break;
/*     */       case 36054:
/* 929 */         str = "INCOMPLETE_ATTACHMENT"; break;
/*     */       case 36055:
/* 929 */         str = "FRAMEBUFFER_MISSING_ATTACHMENT"; break;
/*     */       case 36182:
/* 929 */         str = "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"; break;
/*     */       case 36059:
/* 929 */         str = "INCOMPLETE_DRAW_BUFFER"; break;
/*     */       case 36060:
/* 929 */         str = "INCOMPLETE_READ_BUFFER"; break;
/*     */       case 36006:
/* 929 */         str = "BINDING"; } throw new GLException("FrameBuffer Exception: " + str + ": (" + j + ")"); } GlUtil.f();
/* 930 */     GL30.glBindFramebuffer(36160, 0);
/*     */ 
/* 932 */     GL11.glBindTexture(3553, 0);
/* 933 */     this.jdField_d_of_type_Boolean = true;
/* 934 */     this.jdField_c_of_type_Boolean = false;
/*     */ 
/* 936 */     GlUtil.f();
/*     */   }
/*     */ 
/*     */   private void h()
/*     */   {
/* 967 */     GlUtil.a(5889);
/* 968 */     GlUtil.d();
/* 969 */     GlUtil.b();
/*     */ 
/* 973 */     GlUtil.a(xm.b(), 0.0F, xm.a());
/* 974 */     GlUtil.a(5888);
/* 975 */     GlUtil.d();
/* 976 */     GlUtil.b();
/*     */ 
/* 979 */     GL11.glDisable(2896);
/* 980 */     GL11.glDisable(2929);
/*     */ 
/* 987 */     GL11.glEnable(3553);
/*     */ 
/* 989 */     GL13.glActiveTexture(33984);
/*     */ 
/* 991 */     GL11.glBindTexture(3553, a());
/*     */ 
/* 996 */     GL11.glDisable(2884);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xi
 * JD-Core Version:    0.6.2
 */