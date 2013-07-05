/*      */ import java.io.PrintStream;
/*      */ import java.nio.BufferOverflowException;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GL13;
/*      */ import org.lwjgl.opengl.GL15;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import org.lwjgl.util.vector.Matrix4f;
/*      */ import org.schema.schine.graphicsengine.camera.Camera;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ 
/*      */ public class yg extends ya
/*      */ {
/*   84 */   private int jdField_b_of_type_Int = 1;
/*      */ 
/*   86 */   private int jdField_c_of_type_Int = 0;
/*      */ 
/*   93 */   private boolean jdField_a_of_type_Boolean = false;
/*      */ 
/*   96 */   private boolean jdField_b_of_type_Boolean = true;
/*      */ 
/*   99 */   private boolean jdField_c_of_type_Boolean = true;
/*      */   private javax.vecmath.Vector4f jdField_a_of_type_JavaxVecmathVector4f;
/*      */   private int jdField_d_of_type_Int;
/*      */   private int jdField_e_of_type_Int;
/*  105 */   private boolean jdField_d_of_type_Boolean = false;
/*      */ 
/*  108 */   private boolean jdField_f_of_type_Boolean = false;
/*      */ 
/*  110 */   private IntBuffer jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */   private IntBuffer jdField_b_of_type_JavaNioIntBuffer;
/*  114 */   private IntBuffer jdField_c_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*  115 */   private boolean jdField_g_of_type_Boolean = true;
/*      */ 
/*  119 */   private boolean jdField_h_of_type_Boolean = true;
/*      */   private int jdField_f_of_type_Int;
/*      */   private int jdField_g_of_type_Int;
/*      */   private static FloatBuffer jdField_b_of_type_JavaNioFloatBuffer;
/*      */   private static FloatBuffer jdField_c_of_type_JavaNioFloatBuffer;
/*      */   private static FloatBuffer jdField_d_of_type_JavaNioFloatBuffer;
/*      */   private static int jdField_h_of_type_Int;
/*      */   private static int jdField_i_of_type_Int;
/*      */   private static int j;
/*      */   private static int k;
/*      */   private static FloatBuffer jdField_e_of_type_JavaNioFloatBuffer;
/*      */   public int a;
/*      */   private static IntBuffer jdField_d_of_type_JavaNioIntBuffer;
/*      */   private static FloatBuffer jdField_f_of_type_JavaNioFloatBuffer;
/*      */   private static FloatBuffer jdField_g_of_type_JavaNioFloatBuffer;
/*      */   private static FloatBuffer jdField_h_of_type_JavaNioFloatBuffer;
/*      */ 
/*      */   public static void a(yg paramyg)
/*      */   {
/*  140 */     if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null)
/*  141 */       GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*      */     else {
/*  143 */       GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     }
/*  145 */     if (k != paramyg.jdField_c_of_type_Int) {
/*  146 */       GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*  147 */       GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  148 */       k = paramyg.jdField_c_of_type_Int;
/*      */     }
/*  150 */     GL11.glDrawArrays(7, 0, 4);
/*      */   }
/*      */ 
/*      */   public static void b(yg paramyg) {
/*  154 */     k = -1;
/*  155 */     if (paramyg.jdField_h_of_type_Boolean) {
/*  156 */       paramyg.c();
/*      */     }
/*      */ 
/*  159 */     if (xu.y.b()) {
/*  160 */       n();
/*      */     }
/*      */ 
/*  163 */     GL11.glDisable(2896);
/*  164 */     if (paramyg.jdField_b_of_type_Boolean)
/*  165 */       GL11.glEnable(2929);
/*      */     else {
/*  167 */       GL11.glDisable(2929);
/*      */     }
/*      */ 
/*  170 */     if (paramyg.jdField_c_of_type_Boolean) {
/*  171 */       GL11.glEnable(3042);
/*  172 */       GL11.glBlendFunc(770, 771);
/*      */     }
/*      */     else
/*      */     {
/*  178 */       GL11.glDisable(3042);
/*      */     }
/*      */ 
/*  181 */     paramyg.jdField_a_of_type_Zx.a().a();
/*      */ 
/*  183 */     if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b()))
/*      */     {
/*  185 */       GL11.glEnableClientState(32884);
/*      */ 
/*  187 */       GL11.glEnableClientState(32888);
/*      */ 
/*  189 */       GL11.glEnableClientState(32885);
/*      */ 
/*  192 */       GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  194 */       GL11.glVertexPointer(3, 5126, 0, 0L);
/*      */ 
/*  196 */       GL13.glActiveTexture(33984);
/*      */ 
/*  198 */       if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null)
/*  199 */         GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*      */       else {
/*  201 */         GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       }
/*      */ 
/*  204 */       GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*      */ 
/*  206 */       GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*      */ 
/*  208 */       GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  210 */       GL11.glNormalPointer(5126, 0, 0L); return;
/*      */     }
/*  212 */     if (!jdField_i_of_type_Boolean) throw new AssertionError();
/*      */   }
/*      */ 
/*      */   public static void c(yg paramyg)
/*      */   {
/*  218 */     GL11.glDisableClientState(32884);
/*      */ 
/*  220 */     GL11.glDisableClientState(32888);
/*      */ 
/*  222 */     GL11.glDisableClientState(32885);
/*      */ 
/*  224 */     paramyg.jdField_a_of_type_Zx.a(); zy.b();
/*      */ 
/*  227 */     GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*  229 */     GL11.glDisable(3042);
/*  230 */     GL11.glEnable(2929);
/*  231 */     GL11.glDisable(2903);
/*  232 */     GL11.glEnable(2896);
/*  233 */     if (xu.y.b())
/*  234 */       n();
/*      */   }
/*      */ 
/*      */   public static void d(yg paramyg)
/*      */   {
/*  240 */     if (paramyg.jdField_h_of_type_Boolean) {
/*  241 */       paramyg.c();
/*      */     }
/*      */ 
/*  244 */     if (paramyg.h()) {
/*  245 */       return;
/*      */     }
/*      */ 
/*  248 */     if (xu.y.b()) {
/*  249 */       n();
/*      */     }
/*  251 */     GlUtil.d();
/*      */ 
/*  254 */     GL11.glDisable(2896);
/*  255 */     if (paramyg.jdField_b_of_type_Boolean)
/*  256 */       GL11.glEnable(2929);
/*      */     else {
/*  258 */       GL11.glDisable(2929);
/*      */     }
/*      */ 
/*  261 */     if (paramyg.jdField_c_of_type_Boolean) {
/*  262 */       GL11.glEnable(3042);
/*  263 */       GL11.glBlendFunc(770, 771);
/*      */     }
/*      */     else
/*      */     {
/*  269 */       GL11.glDisable(3042);
/*      */     }
/*      */ 
/*  272 */     paramyg.jdField_a_of_type_Zx.a().a();
/*      */ 
/*  274 */     if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b()))
/*      */     {
/*  276 */       GL11.glEnableClientState(32884);
/*      */ 
/*  278 */       GL11.glEnableClientState(32888);
/*      */ 
/*  280 */       GL11.glEnableClientState(32885);
/*      */ 
/*  283 */       GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  285 */       GL11.glVertexPointer(3, 5126, 0, 0L);
/*      */ 
/*  287 */       GL13.glActiveTexture(33984);
/*      */ 
/*  289 */       if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null)
/*  290 */         GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*      */       else {
/*  292 */         GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       }
/*      */ 
/*  295 */       GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*      */ 
/*  297 */       GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*      */ 
/*  302 */       GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  304 */       GL11.glNormalPointer(5126, 0, 0L);
/*      */ 
/*  308 */       GlUtil.d();
/*      */ 
/*  338 */       if (paramyg.jdField_f_of_type_Boolean) {
/*  339 */         GlUtil.a(paramyg.a());
/*  340 */         jdField_e_of_type_JavaNioFloatBuffer.put(0, paramyg.a.x);
/*  341 */         jdField_e_of_type_JavaNioFloatBuffer.put(5, -paramyg.a.y);
/*  342 */         jdField_e_of_type_JavaNioFloatBuffer.put(10, paramyg.a.z);
/*      */ 
/*  344 */         jdField_e_of_type_JavaNioFloatBuffer.put(12, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m30);
/*  345 */         jdField_e_of_type_JavaNioFloatBuffer.put(13, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m31);
/*  346 */         jdField_e_of_type_JavaNioFloatBuffer.put(14, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m32);
/*  347 */         jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*  348 */         GlUtil.a(jdField_e_of_type_JavaNioFloatBuffer);
/*      */       }
/*  350 */       GL11.glDrawArrays(7, 0, 4);
/*  351 */       GlUtil.c();
/*      */ 
/*  356 */       GL11.glDisableClientState(32884);
/*      */ 
/*  358 */       GL11.glDisableClientState(32888);
/*      */ 
/*  360 */       GL11.glDisableClientState(32885);
/*      */     } else {
/*  362 */       float f1 = paramyg.jdField_a_of_type_Zx.a().jdField_b_of_type_Int * paramyg.a.x;
/*      */ 
/*  369 */       float f2 = paramyg.jdField_a_of_type_Zx.a().jdField_a_of_type_Int * paramyg.a.y;
/*      */ 
/*  371 */       GL11.glBegin(7);
/*      */ 
/*  373 */       if (paramyg.jdField_a_of_type_Boolean) {
/*  374 */         GL11.glTexCoord2f(0.0F, 0.0F);
/*  375 */         GL11.glVertex3f(-f1 / 2.0F, -f2 / 2.0F, 0.0F);
/*  376 */         GL11.glTexCoord2f(0.0F, 1.0F);
/*  377 */         GL11.glVertex3f(-f1 / 2.0F, f2 / 2.0F, 0.0F);
/*  378 */         GL11.glTexCoord2f(1.0F, 1.0F);
/*  379 */         GL11.glVertex3f(f1 / 2.0F, f2 / 2.0F, 0.0F);
/*  380 */         GL11.glTexCoord2f(1.0F, 0.0F);
/*  381 */         GL11.glVertex3f(f1 / 2.0F, -f2 / 2.0F, 0.0F);
/*      */       } else {
/*  383 */         GL11.glTexCoord2f(0.0F, 0.0F);
/*  384 */         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  385 */         GL11.glTexCoord2f(0.0F, 1.0F);
/*  386 */         GL11.glVertex3f(0.0F, f2, 0.0F);
/*  387 */         GL11.glTexCoord2f(1.0F, 1.0F);
/*  388 */         GL11.glVertex3f(f1, f2, 0.0F);
/*  389 */         GL11.glTexCoord2f(1.0F, 0.0F);
/*  390 */         GL11.glVertex3f(f1, 0.0F, 0.0F);
/*      */       }
/*      */ 
/*  393 */       GL11.glEnd();
/*      */     }
/*  395 */     paramyg.jdField_a_of_type_Zx.a(); zy.b();
/*      */ 
/*  397 */     GlUtil.c();
/*      */ 
/*  400 */     GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*  402 */     GL11.glDisable(3042);
/*  403 */     GL11.glEnable(2929);
/*  404 */     GL11.glDisable(2903);
/*  405 */     GL11.glEnable(2896);
/*  406 */     if (xu.y.b())
/*  407 */       n();
/*      */   }
/*      */ 
/*      */   public static void a(yg paramyg, xZ[] paramArrayOfxZ, Camera paramCamera)
/*      */   {
/*  482 */     if (paramyg.jdField_h_of_type_Boolean) {
/*  483 */       paramyg.c();
/*      */     }
/*      */ 
/*  486 */     if (paramyg.h()) {
/*  487 */       return;
/*      */     }
/*      */ 
/*  491 */     int m = (paramArrayOfxZ.length > 0) && ((paramArrayOfxZ[0] instanceof xY)) ? 1 : 0;
/*  492 */     int n = (paramArrayOfxZ.length > 0) && ((paramArrayOfxZ[0] instanceof yc)) ? 1 : 0;
/*      */ 
/*  495 */     if (xu.y.b()) {
/*  496 */       n();
/*      */     }
/*  498 */     GlUtil.d();
/*      */ 
/*  500 */     yc localyc = null;
/*      */ 
/*  502 */     GL11.glDisable(2896);
/*  503 */     if (paramyg.jdField_b_of_type_Boolean)
/*  504 */       GL11.glEnable(2929);
/*      */     else {
/*  506 */       GL11.glDisable(2929);
/*      */     }
/*      */ 
/*  509 */     if (paramyg.jdField_c_of_type_Boolean) {
/*  510 */       GL11.glEnable(3042);
/*  511 */       if (paramyg.jdField_a_of_type_Int == 0)
/*  512 */         GL11.glBlendFunc(770, 771);
/*      */       else {
/*  514 */         GL11.glBlendFunc(770, 1);
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  521 */       GL11.glDisable(3042);
/*      */     }
/*  523 */     float f1 = 0.0F;
/*  524 */     float f2 = 0.0F;
/*  525 */     if (n != 0) {
/*  526 */       f1 = Mouse.getX();
/*  527 */       f2 = Mouse.getY();
/*      */     }
/*      */ 
/*  530 */     paramyg.jdField_a_of_type_Zx.a().a();
/*      */ 
/*  533 */     if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b())) {
/*  534 */       d.B.c();
/*      */ 
/*  536 */       GlUtil.a(d.B, "selectionColor", 1.0F, 1.0F, 1.0F, 1.0F);
/*  537 */       GlUtil.a(d.B, "mainTexA", 0);
/*      */ 
/*  539 */       GL11.glEnableClientState(32884);
/*      */ 
/*  541 */       GL11.glEnableClientState(32888);
/*      */ 
/*  543 */       GL11.glEnableClientState(32885);
/*      */ 
/*  546 */       GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  548 */       GL11.glVertexPointer(3, 5126, 0, 0L);
/*      */ 
/*  550 */       GL13.glActiveTexture(33984);
/*      */ 
/*  552 */       if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null)
/*  553 */         GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*      */       else {
/*  555 */         GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       }
/*      */ 
/*  565 */       GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*  566 */       GL11.glNormalPointer(5126, 0, 0L);
/*      */ 
/*  570 */       GlUtil.d();
/*  571 */       int i1 = -1;
/*      */ 
/*  573 */       javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramCamera.f());
/*  574 */       javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramCamera.e());
/*  575 */       localVector3f1.scale(0.3F);
/*  576 */       localVector3f2.scale(0.3F);
/*  577 */       long l = System.currentTimeMillis();
/*  578 */       for (int i2 = 0; i2 < paramArrayOfxZ.length; i2++) {
/*  579 */         xZ localxZ = paramArrayOfxZ[i2];
/*      */ 
/*  581 */         if (paramCamera.a(localxZ.a())) {
/*  582 */           paramyg.b(localxZ.a(paramyg));
/*      */ 
/*  586 */           if (paramyg.jdField_c_of_type_Int != i1) {
/*  587 */             GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*      */ 
/*  589 */             GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  590 */             i1 = paramyg.jdField_c_of_type_Int;
/*      */           }
/*  592 */           if (m != 0) {
/*  593 */             xY localxY = (xY)localxZ;
/*  594 */             GlUtil.a(d.B, "selectionColor", localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/*  595 */             GlUtil.a(localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/*      */           }
/*  597 */           GlUtil.d();
/*  598 */           if (!paramyg.jdField_a_of_type_Boolean)
/*  599 */             GlUtil.c(localxZ.a().x + localVector3f1.x + localVector3f2.x, localxZ.a().y + localVector3f1.y + localVector3f2.y, localxZ.a().z + localVector3f1.z + localVector3f2.z);
/*      */           else {
/*  601 */             GlUtil.c(localxZ.a().x, localxZ.a().y, localxZ.a().z);
/*      */           }
/*      */ 
/*  604 */           float f5 = localxZ.a(l);
/*  605 */           jdField_e_of_type_JavaNioFloatBuffer.put(0, f5);
/*  606 */           jdField_e_of_type_JavaNioFloatBuffer.put(5, -f5);
/*  607 */           jdField_e_of_type_JavaNioFloatBuffer.put(10, f5);
/*      */ 
/*  609 */           jdField_e_of_type_JavaNioFloatBuffer.put(12, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m30);
/*  610 */           jdField_e_of_type_JavaNioFloatBuffer.put(13, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m31);
/*  611 */           jdField_e_of_type_JavaNioFloatBuffer.put(14, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m32);
/*  612 */           jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*  613 */           GlUtil.a(jdField_e_of_type_JavaNioFloatBuffer);
/*  614 */           if ((n != 0) && (((yc)localxZ).b()))
/*      */           {
/*  616 */             org.lwjgl.util.vector.Vector4f localVector4f = new org.lwjgl.util.vector.Vector4f();
/*  617 */             Matrix4f.transform(xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f, new org.lwjgl.util.vector.Vector4f(0.0F, 0.0F, 0.0F, 1.0F), localVector4f);
/*  618 */             Matrix4f.transform(xe.b, new org.lwjgl.util.vector.Vector4f(localVector4f), localVector4f);
/*      */ 
/*  620 */             float f6 = localVector4f.z / localVector4f.w * 0.5F + 0.5F;
/*      */ 
/*  622 */             float f9 = f6; float f8 = f2; float f7 = f1; yg localyg = paramyg; Matrix4f localMatrix4f1 = xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f; Matrix4f localMatrix4f2 = xe.b; jdField_d_of_type_JavaNioIntBuffer.rewind(); jdField_d_of_type_JavaNioIntBuffer.put(xe.jdField_a_of_type_JavaNioIntBuffer); xe.jdField_a_of_type_JavaNioIntBuffer.rewind(); jdField_d_of_type_JavaNioIntBuffer.rewind(); jdField_d_of_type_JavaNioIntBuffer.put(0, 0); jdField_d_of_type_JavaNioIntBuffer.put(1, 0); jdField_d_of_type_JavaNioIntBuffer.put(2, jdField_d_of_type_JavaNioIntBuffer.get(2)); jdField_d_of_type_JavaNioIntBuffer.put(3, jdField_d_of_type_JavaNioIntBuffer.get(3)); if (jdField_g_of_type_JavaNioFloatBuffer == null) { jdField_f_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16); jdField_g_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16); } jdField_g_of_type_JavaNioFloatBuffer.rewind(); localMatrix4f1.store(jdField_g_of_type_JavaNioFloatBuffer); jdField_g_of_type_JavaNioFloatBuffer.rewind(); jdField_f_of_type_JavaNioFloatBuffer.rewind(); localMatrix4f2.store(jdField_f_of_type_JavaNioFloatBuffer); jdField_f_of_type_JavaNioFloatBuffer.rewind(); GLU.gluUnProject(f7, f8, f9, jdField_g_of_type_JavaNioFloatBuffer, jdField_f_of_type_JavaNioFloatBuffer, jdField_d_of_type_JavaNioIntBuffer, jdField_h_of_type_JavaNioFloatBuffer); f7 = jdField_h_of_type_JavaNioFloatBuffer.get(0); f8 = jdField_h_of_type_JavaNioFloatBuffer.get(1); jdField_h_of_type_JavaNioFloatBuffer.get(2); f7 += localyg.jdField_d_of_type_Int / 2; f8 += localyg.jdField_e_of_type_Int / 2; int i4 = (f7 < localyg.jdField_d_of_type_Int) && (f7 > 0.0F) ? 1 : 0; int i3 = (f8 < localyg.jdField_e_of_type_Int) && (f8 > 0.0F) ? 1 : 0; if (((i4 != 0) && (i3 != 0) ? 1 : 0) != 0) {
/*  623 */               if (localyc != null) {
/*  624 */                 if (localyc.a() > ((yc)localxZ).a()) {
/*  625 */                   localyc.a();
/*  626 */                   ((yc)localxZ).a(f6);
/*  627 */                   localyc = (yc)localxZ;
/*      */                 } else {
/*  629 */                   ((yc)localxZ).a();
/*      */                 }
/*      */               } else {
/*  632 */                 ((yc)localxZ).a(f6);
/*  633 */                 localyc = (yc)localxZ;
/*      */               }
/*      */             }
/*  636 */             else ((yc)localxZ).a();
/*      */ 
/*      */           }
/*      */ 
/*  641 */           GL11.glDrawArrays(7, 0, 4);
/*      */ 
/*  643 */           GlUtil.c();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  648 */       GlUtil.c();
/*  649 */       zj.e();
/*      */ 
/*  653 */       GL11.glDisableClientState(32884);
/*      */ 
/*  655 */       GL11.glDisableClientState(32888);
/*      */ 
/*  657 */       GL11.glDisableClientState(32885);
/*      */     } else {
/*  659 */       float f3 = paramyg.jdField_a_of_type_Zx.a().jdField_b_of_type_Int * paramyg.a.x;
/*      */ 
/*  666 */       float f4 = paramyg.jdField_a_of_type_Zx.a().jdField_a_of_type_Int * paramyg.a.y;
/*      */ 
/*  668 */       GL11.glBegin(7);
/*      */ 
/*  670 */       if (paramyg.jdField_a_of_type_Boolean) {
/*  671 */         GL11.glTexCoord2f(0.0F, 0.0F);
/*  672 */         GL11.glVertex3f(-f3 / 2.0F, -f4 / 2.0F, 0.0F);
/*  673 */         GL11.glTexCoord2f(0.0F, 1.0F);
/*  674 */         GL11.glVertex3f(-f3 / 2.0F, f4 / 2.0F, 0.0F);
/*  675 */         GL11.glTexCoord2f(1.0F, 1.0F);
/*  676 */         GL11.glVertex3f(f3 / 2.0F, f4 / 2.0F, 0.0F);
/*  677 */         GL11.glTexCoord2f(1.0F, 0.0F);
/*  678 */         GL11.glVertex3f(f3 / 2.0F, -f4 / 2.0F, 0.0F);
/*      */       } else {
/*  680 */         GL11.glTexCoord2f(0.0F, 0.0F);
/*  681 */         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  682 */         GL11.glTexCoord2f(0.0F, 1.0F);
/*  683 */         GL11.glVertex3f(0.0F, f4, 0.0F);
/*  684 */         GL11.glTexCoord2f(1.0F, 1.0F);
/*  685 */         GL11.glVertex3f(f3, f4, 0.0F);
/*  686 */         GL11.glTexCoord2f(1.0F, 0.0F);
/*  687 */         GL11.glVertex3f(f3, 0.0F, 0.0F);
/*      */       }
/*      */ 
/*  690 */       GL11.glEnd();
/*      */     }
/*  692 */     paramyg.jdField_a_of_type_Zx.a(); zy.b();
/*      */ 
/*  694 */     GlUtil.c();
/*      */ 
/*  697 */     GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*  699 */     GL11.glDisable(3042);
/*  700 */     GL11.glEnable(2929);
/*  701 */     GL11.glDisable(2903);
/*  702 */     GL11.glEnable(2896);
/*  703 */     if (xu.y.b())
/*  704 */       n();
/*      */   }
/*      */ 
/*      */   public static void a(yg paramyg, Collection paramCollection, Camera paramCamera)
/*      */   {
/*  710 */     if (paramyg.jdField_h_of_type_Boolean) {
/*  711 */       paramyg.c();
/*      */     }
/*      */ 
/*  714 */     if (paramyg.h()) {
/*  715 */       return;
/*      */     }
/*      */ 
/*  719 */     int m = (paramCollection.size() > 0) && ((paramCollection.iterator().next() instanceof xY)) ? 1 : 0;
/*      */ 
/*  722 */     if (xu.y.b()) {
/*  723 */       n();
/*      */     }
/*  725 */     GlUtil.d();
/*      */ 
/*  728 */     GL11.glDisable(2896);
/*  729 */     if (paramyg.jdField_b_of_type_Boolean)
/*  730 */       GL11.glEnable(2929);
/*      */     else {
/*  732 */       GL11.glDisable(2929);
/*      */     }
/*      */ 
/*  735 */     xY localxY = null; if (paramyg.jdField_c_of_type_Boolean) {
/*  736 */       GL11.glEnable(3042);
/*  737 */       if (paramyg.jdField_a_of_type_Int == 0)
/*  738 */         GL11.glBlendFunc(770, 771);
/*      */       else {
/*  740 */         GL11.glBlendFunc(770, 1);
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  747 */       GL11.glDisable(3042);
/*      */     }
/*      */ 
/*  750 */     paramyg.jdField_a_of_type_Zx.a().a();
/*      */ 
/*  753 */     if ((paramyg.jdField_g_of_type_Boolean) && (xu.N.b()))
/*      */     {
/*  755 */       d.B.c();
/*      */ 
/*  757 */       GlUtil.a(d.B, "selectionColor", 1.0F, 1.0F, 1.0F, 1.0F);
/*  758 */       GlUtil.a(d.B, "mainTexA", 0);
/*      */ 
/*  760 */       GL11.glEnableClientState(32884);
/*      */ 
/*  762 */       GL11.glEnableClientState(32888);
/*      */ 
/*  764 */       GL11.glEnableClientState(32885);
/*      */ 
/*  767 */       GL15.glBindBuffer(34962, paramyg.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  769 */       GL11.glVertexPointer(3, 5126, 0, 0L);
/*      */ 
/*  771 */       GL13.glActiveTexture(33984);
/*      */ 
/*  773 */       if (paramyg.jdField_a_of_type_JavaxVecmathVector4f != null)
/*  774 */         GlUtil.a(paramyg.jdField_a_of_type_JavaxVecmathVector4f.x, paramyg.jdField_a_of_type_JavaxVecmathVector4f.y, paramyg.jdField_a_of_type_JavaxVecmathVector4f.z, paramyg.jdField_a_of_type_JavaxVecmathVector4f.w);
/*      */       else {
/*  776 */         GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       }
/*      */ 
/*  784 */       GL15.glBindBuffer(34962, paramyg.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  786 */       GL11.glNormalPointer(5126, 0, 0L);
/*      */ 
/*  789 */       GlUtil.d();
/*      */ 
/*  792 */       int n = -1;
/*      */ 
/*  794 */       javax.vecmath.Vector3f localVector3f1 = new javax.vecmath.Vector3f(paramCamera.f());
/*  795 */       javax.vecmath.Vector3f localVector3f2 = new javax.vecmath.Vector3f(paramCamera.e());
/*  796 */       localVector3f1.scale(0.3F);
/*  797 */       localVector3f2.scale(0.3F);
/*  798 */       long l = System.currentTimeMillis();
/*  799 */       for (xZ localxZ : paramCollection) {
/*  800 */         if ((paramCamera.a(localxZ.a())) && (localxZ.a(paramyg) >= 0))
/*      */         {
/*  802 */           paramyg.b(localxZ.a(paramyg));
/*      */ 
/*  806 */           localxY = null; if (paramyg.jdField_c_of_type_Int != n) {
/*  807 */             GL15.glBindBuffer(34962, paramyg.jdField_b_of_type_JavaNioIntBuffer.get(paramyg.jdField_c_of_type_Int));
/*      */ 
/*  809 */             GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*  810 */             n = paramyg.jdField_c_of_type_Int;
/*      */           }
/*  812 */           if (m != 0) {
/*  813 */             localxY = (xY)localxZ;
/*  814 */             GlUtil.a(d.B, "selectionColor", localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/*  815 */             GlUtil.a(localxY.a().x, localxY.a().y, localxY.a().z, localxY.a().w);
/*      */           }
/*      */ 
/*  818 */           GlUtil.d();
/*  819 */           if (!paramyg.jdField_a_of_type_Boolean)
/*  820 */             GlUtil.c(localxZ.a().x + localVector3f1.x + localVector3f2.x, localxZ.a().y + localVector3f1.y + localVector3f2.y, localxZ.a().z + localVector3f1.z + localVector3f2.z);
/*      */           else {
/*  822 */             GlUtil.c(localxZ.a().x, localxZ.a().y, localxZ.a().z);
/*      */           }
/*      */ 
/*  825 */           float f3 = localxZ.a(l);
/*  826 */           jdField_e_of_type_JavaNioFloatBuffer.put(0, f3);
/*  827 */           jdField_e_of_type_JavaNioFloatBuffer.put(5, -f3);
/*  828 */           jdField_e_of_type_JavaNioFloatBuffer.put(10, f3);
/*      */ 
/*  830 */           jdField_e_of_type_JavaNioFloatBuffer.put(12, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m30);
/*  831 */           jdField_e_of_type_JavaNioFloatBuffer.put(13, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m31);
/*  832 */           jdField_e_of_type_JavaNioFloatBuffer.put(14, xe.jdField_a_of_type_OrgLwjglUtilVectorMatrix4f.m32);
/*  833 */           jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*  834 */           GlUtil.a(jdField_e_of_type_JavaNioFloatBuffer);
/*      */ 
/*  837 */           GL11.glDrawArrays(7, 0, 4);
/*  838 */           GlUtil.c();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  843 */       GlUtil.c();
/*  844 */       zj.e();
/*      */ 
/*  848 */       GL11.glDisableClientState(32884);
/*      */ 
/*  850 */       GL11.glDisableClientState(32888);
/*      */ 
/*  852 */       GL11.glDisableClientState(32885);
/*      */     } else {
/*  854 */       paramCollection = null; float f1 = paramyg.jdField_a_of_type_Zx.a().jdField_b_of_type_Int * paramyg.a.x;
/*      */ 
/*  861 */       float f2 = paramyg.jdField_a_of_type_Zx.a().jdField_a_of_type_Int * paramyg.a.y;
/*      */ 
/*  863 */       GL11.glBegin(7);
/*      */ 
/*  865 */       if (paramyg.jdField_a_of_type_Boolean) {
/*  866 */         GL11.glTexCoord2f(0.0F, 0.0F);
/*  867 */         GL11.glVertex3f(-f1 / 2.0F, -f2 / 2.0F, 0.0F);
/*  868 */         GL11.glTexCoord2f(0.0F, 1.0F);
/*  869 */         GL11.glVertex3f(-f1 / 2.0F, f2 / 2.0F, 0.0F);
/*  870 */         GL11.glTexCoord2f(1.0F, 1.0F);
/*  871 */         GL11.glVertex3f(f1 / 2.0F, f2 / 2.0F, 0.0F);
/*  872 */         GL11.glTexCoord2f(1.0F, 0.0F);
/*  873 */         GL11.glVertex3f(f1 / 2.0F, -f2 / 2.0F, 0.0F);
/*      */       } else {
/*  875 */         GL11.glTexCoord2f(0.0F, 0.0F);
/*  876 */         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
/*  877 */         GL11.glTexCoord2f(0.0F, 1.0F);
/*  878 */         GL11.glVertex3f(0.0F, f2, 0.0F);
/*  879 */         GL11.glTexCoord2f(1.0F, 1.0F);
/*  880 */         GL11.glVertex3f(f1, f2, 0.0F);
/*  881 */         GL11.glTexCoord2f(1.0F, 0.0F);
/*  882 */         GL11.glVertex3f(f1, 0.0F, 0.0F);
/*      */       }
/*      */ 
/*  885 */       GL11.glEnd();
/*      */     }
/*  887 */     paramyg.jdField_a_of_type_Zx.a(); zy.b();
/*      */ 
/*  889 */     GlUtil.c();
/*      */ 
/*  892 */     GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*  894 */     GL11.glDisable(3042);
/*  895 */     GL11.glEnable(2929);
/*  896 */     GL11.glDisable(2903);
/*  897 */     GL11.glEnable(2896);
/*  898 */     if (xu.y.b())
/*  899 */       n();
/*      */   }
/*      */ 
/*      */   private yg(int paramInt1, int paramInt2)
/*      */   {
/*  910 */     this.jdField_a_of_type_Zx = new zx();
/*  911 */     this.jdField_d_of_type_Int = paramInt1;
/*  912 */     this.jdField_e_of_type_Int = paramInt2;
/*      */   }
/*      */ 
/*      */   public yg(zy paramzy)
/*      */   {
/*  922 */     this(paramzy.jdField_b_of_type_Int, paramzy.jdField_a_of_type_Int);
/*  923 */     a().c(paramzy);
/*      */   }
/*      */ 
/*      */   public final void b()
/*      */   {
/*  931 */     GlUtil.d();
/*  932 */     if (this.jdField_d_of_type_Boolean) {
/*  933 */       GL11.glScalef(1.0F, -1.0F, 1.0F);
/*      */     }
/*  935 */     r();
/*  936 */     d(this);
/*  937 */     GlUtil.c();
/*      */   }
/*      */ 
/*      */   public final int a()
/*      */   {
/*  944 */     return this.jdField_e_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final int b()
/*      */   {
/*  951 */     return this.jdField_b_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final javax.vecmath.Vector4f a()
/*      */   {
/*  979 */     return this.jdField_a_of_type_JavaxVecmathVector4f;
/*      */   }
/*      */ 
/*      */   public final int c()
/*      */   {
/*  985 */     return this.jdField_d_of_type_Int;
/*      */   }
/*      */ 
/*      */   public final void c()
/*      */   {
/* 1039 */     if (!this.jdField_h_of_type_Boolean) {
/* 1040 */       return;
/*      */     }
/* 1042 */     if (this.jdField_g_of_type_Boolean)
/*      */     {
/* 1044 */       int m = 1;
/* 1045 */       int n = 1;
/* 1046 */       n[] arrayOfn1 = null; if (this.jdField_b_of_type_Int > 1) {
/* 1047 */         if (this.jdField_f_of_type_Int != this.jdField_g_of_type_Int) {
/* 1048 */           System.err.println("UNEVEN PARTS OF SPRITE " + this.jdField_f_of_type_Int + " / " + this.jdField_g_of_type_Int);
/*      */         }
/* 1050 */         m = this.jdField_f_of_type_Int;
/* 1051 */         n = this.jdField_g_of_type_Int;
/*      */       }
/*      */ 
/* 1054 */       float f1 = 1.0F / m;
/* 1055 */       float f2 = 1.0F / n;
/*      */ 
/* 1058 */       if (jdField_b_of_type_JavaNioFloatBuffer == null) {
/* 1059 */         jdField_b_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(jdField_h_of_type_Int);
/* 1060 */         jdField_c_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(jdField_i_of_type_Int);
/* 1061 */         jdField_d_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(j);
/*      */       } else {
/* 1063 */         jdField_b_of_type_JavaNioFloatBuffer.rewind();
/* 1064 */         jdField_c_of_type_JavaNioFloatBuffer.rewind();
/* 1065 */         jdField_d_of_type_JavaNioFloatBuffer.rewind();
/*      */       }
/*      */       int i3;
/*      */       try
/*      */       {
/* 1070 */         arrayOfn1 = new n[4];
/* 1071 */         n[] arrayOfn2 = new n[4];
/* 1072 */         n localn1 = null; if (this.jdField_a_of_type_Boolean) {
/* 1073 */           arrayOfn1[3] = new n(-this.jdField_d_of_type_Int / 2, -this.jdField_e_of_type_Int / 2, 0.0F);
/* 1074 */           arrayOfn2[3] = new n(0.0F, 0.0F, 1.0F);
/*      */ 
/* 1076 */           arrayOfn1[2] = new n(this.jdField_d_of_type_Int / 2, -this.jdField_e_of_type_Int / 2, 0.0F);
/* 1077 */           arrayOfn2[2] = new n(0.0F, 0.0F, 1.0F);
/*      */ 
/* 1079 */           arrayOfn1[1] = new n(this.jdField_d_of_type_Int / 2, this.jdField_e_of_type_Int / 2, 0.0F);
/* 1080 */           arrayOfn2[1] = new n(0.0F, 0.0F, 1.0F);
/*      */ 
/* 1082 */           arrayOfn1[0] = new n(-this.jdField_d_of_type_Int / 2, this.jdField_e_of_type_Int / 2, 0.0F);
/* 1083 */           arrayOfn2[0] = new n(0.0F, 0.0F, 1.0F);
/*      */         } else {
/* 1085 */           arrayOfn1[3] = new n(0.0F, 0.0F, 0.0F);
/* 1086 */           arrayOfn2[3] = new n(0.0F, 0.0F, 1.0F);
/*      */ 
/* 1088 */           arrayOfn1[2] = new n(this.jdField_d_of_type_Int, 0.0F, 0.0F);
/* 1089 */           arrayOfn2[2] = new n(0.0F, 0.0F, 1.0F);
/*      */ 
/* 1091 */           arrayOfn1[1] = new n(this.jdField_d_of_type_Int, this.jdField_e_of_type_Int, 0.0F);
/* 1092 */           arrayOfn2[1] = new n(0.0F, 0.0F, 1.0F);
/*      */ 
/* 1094 */           arrayOfn1[0] = new n(0.0F, this.jdField_e_of_type_Int, 0.0F);
/* 1095 */           arrayOfn2[0] = new n(0.0F, 0.0F, 1.0F);
/*      */         }
/*      */ 
/* 1099 */         for (i3 = 0; i3 < arrayOfn1.length; i3++) {
/* 1100 */           localn1 = arrayOfn1[i3];
/* 1101 */           n localn2 = arrayOfn2[i3];
/*      */ 
/* 1103 */           jdField_b_of_type_JavaNioFloatBuffer.put(localn1.a);
/* 1104 */           jdField_b_of_type_JavaNioFloatBuffer.put(localn1.b);
/* 1105 */           jdField_b_of_type_JavaNioFloatBuffer.put(localn1.c);
/*      */ 
/* 1107 */           jdField_d_of_type_JavaNioFloatBuffer.put(localn2.a);
/* 1108 */           jdField_d_of_type_JavaNioFloatBuffer.put(localn2.b);
/* 1109 */           jdField_d_of_type_JavaNioFloatBuffer.put(localn2.c);
/*      */         }
/*      */ 
/*      */       }
/*      */       catch (BufferOverflowException localBufferOverflowException)
/*      */       {
/* 1117 */         localBufferOverflowException.printStackTrace();
/*      */       }
/*      */ 
/* 1120 */       jdField_b_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/* 1122 */       jdField_d_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/* 1127 */       GL15.glGenBuffers(this.jdField_a_of_type_JavaNioIntBuffer);
/* 1128 */       GL15.glBindBuffer(34962, this.jdField_a_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/* 1130 */       GL15.glBufferData(34962, jdField_b_of_type_JavaNioFloatBuffer, 35044);
/*      */ 
/* 1132 */       this.jdField_b_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(this.jdField_b_of_type_Int);
/* 1133 */       GL15.glGenBuffers(this.jdField_b_of_type_JavaNioIntBuffer);
/*      */ 
/* 1138 */       int i1 = 0;
/*      */ 
/* 1140 */       for (int i2 = 0; i2 < n; i2++) {
/* 1141 */         for (i3 = 0; i3 < m; i3++)
/*      */         {
/* 1143 */           float f6 = (i2 + 1) * f2; float f5 = i2 * f2; float f4 = (i3 + 1) * f1; float f3 = i3 * f1;
/*      */           javax.vecmath.Vector3f[] arrayOfVector3f;
/* 1143 */           (arrayOfVector3f = new javax.vecmath.Vector3f[4])[3] = new javax.vecmath.Vector3f(f3, f5, 0.0F); arrayOfVector3f[2] = new javax.vecmath.Vector3f(f4, f5, 0.0F); arrayOfVector3f[1] = new javax.vecmath.Vector3f(f4, f6, 0.0F); arrayOfVector3f[0] = new javax.vecmath.Vector3f(f3, f6, 0.0F); Object localObject = arrayOfVector3f;
/*      */ 
/* 1145 */           jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/* 1147 */           for (int i4 = 0; i4 < localObject.length; i4++) {
/* 1148 */             jdField_c_of_type_JavaNioFloatBuffer.put(localObject[i4].x);
/* 1149 */             jdField_c_of_type_JavaNioFloatBuffer.put(localObject[i4].y);
/*      */           }
/* 1151 */           jdField_c_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/* 1153 */           GL15.glBindBuffer(34962, this.jdField_b_of_type_JavaNioIntBuffer.get(i1));
/*      */ 
/* 1155 */           GL15.glBufferData(34962, jdField_c_of_type_JavaNioFloatBuffer, 35044);
/* 1156 */           i1++;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1161 */       GL15.glGenBuffers(this.jdField_c_of_type_JavaNioIntBuffer);
/* 1162 */       GL15.glBindBuffer(34962, this.jdField_c_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/* 1164 */       GL15.glBufferData(34962, jdField_d_of_type_JavaNioFloatBuffer, 35044);
/*      */     }
/*      */ 
/* 1170 */     this.jdField_h_of_type_Boolean = false;
/*      */   }
/*      */ 
/*      */   public final void a(boolean paramBoolean)
/*      */   {
/* 1179 */     this.jdField_f_of_type_Boolean = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final void d()
/*      */   {
/* 1188 */     this.jdField_c_of_type_Boolean = true;
/*      */   }
/*      */ 
/*      */   public final void b(boolean paramBoolean)
/*      */   {
/* 1197 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final void c(boolean paramBoolean)
/*      */   {
/* 1204 */     this.jdField_d_of_type_Boolean = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final void a(int paramInt)
/*      */   {
/* 1211 */     this.jdField_e_of_type_Int = paramInt;
/*      */   }
/*      */ 
/*      */   public final void a(int paramInt1, int paramInt2)
/*      */   {
/* 1219 */     this.jdField_b_of_type_Int = (paramInt1 * paramInt2);
/* 1220 */     this.jdField_f_of_type_Int = paramInt1;
/* 1221 */     this.jdField_g_of_type_Int = paramInt2;
/*      */   }
/*      */ 
/*      */   public final void d(boolean paramBoolean)
/*      */   {
/* 1237 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*      */   }
/*      */ 
/*      */   public final void b(int paramInt)
/*      */   {
/* 1245 */     if ((!jdField_i_of_type_Boolean) && ((paramInt >= this.jdField_b_of_type_Int) || (paramInt < 0))) throw new AssertionError("tried to set " + paramInt + " / " + this.jdField_b_of_type_Int);
/* 1246 */     this.jdField_c_of_type_Int = paramInt;
/*      */   }
/*      */ 
/*      */   public final void c(javax.vecmath.Vector4f paramVector4f) {
/* 1250 */     this.jdField_a_of_type_JavaxVecmathVector4f = paramVector4f;
/*      */   }
/*      */ 
/*      */   public final void c(int paramInt)
/*      */   {
/* 1257 */     this.jdField_d_of_type_Int = paramInt;
/*      */   }
/*      */ 
/*      */   public final void a(xq paramxq)
/*      */   {
/* 1265 */     System.err.println("Cannot update static Sprite");
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  131 */     jdField_h_of_type_Int = 12;
/*      */ 
/*  133 */     jdField_i_of_type_Int = 8;
/*      */ 
/*  135 */     j = 12;
/*      */ 
/*  137 */     k = -1;
/*      */ 
/*  411 */     jdField_e_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*      */     Matrix4f localMatrix4f;
/*  417 */     (
/*  418 */       localMatrix4f = new Matrix4f())
/*  418 */       .setIdentity();
/*  419 */     localMatrix4f.scale(new org.lwjgl.util.vector.Vector3f(0.01F, -0.01F, 0.01F));
/*  420 */     localMatrix4f.store(jdField_e_of_type_JavaNioFloatBuffer);
/*      */ 
/*  422 */     jdField_e_of_type_JavaNioFloatBuffer.rewind();
/*      */ 
/*  424 */     jdField_d_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(16);
/*  425 */     jdField_f_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  426 */     jdField_g_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
/*  427 */     jdField_h_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
/*  428 */     BufferUtils.createFloatBuffer(1);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yg
 * JD-Core Version:    0.6.2
 */