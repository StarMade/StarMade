/*      */ package org.schema.schine.graphicsengine.forms;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.vecmath.Vector3f;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GL15;
/*      */ import org.lwjgl.opengl.GL20;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ import xM;
/*      */ import xO;
/*      */ import xS;
/*      */ import xV;
/*      */ import xu;
/*      */ import yf;
/*      */ import yi;
/*      */ import zx;
/*      */ 
/*      */ public class Mesh extends xS
/*      */ {
/*      */   public xV[] a;
/*      */   private Vector3f[] jdField_b_of_type_ArrayOfJavaxVecmathVector3f;
/*      */   private Vector3f[] jdField_c_of_type_ArrayOfJavaxVecmathVector3f;
/*  826 */   private boolean jdField_a_of_type_Boolean = false;
/*      */ 
/*  829 */   private IntBuffer jdField_d_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */ 
/*  832 */   private IntBuffer jdField_e_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */ 
/*  834 */   private IntBuffer jdField_f_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */   private IntBuffer g;
/*      */   private IntBuffer h;
/*  843 */   private IntBuffer jdField_i_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */   public int g;
/*      */   private int jdField_a_of_type_Int;
/*      */   public int h;
/*      */   private int jdField_b_of_type_Int;
/*      */   private boolean jdField_b_of_type_Boolean;
/*      */   private yi[] jdField_a_of_type_ArrayOfYi;
/*      */   private yf jdField_a_of_type_Yf;
/*      */   private boolean jdField_c_of_type_Boolean;
/*      */   private static int jdField_c_of_type_Int;
/*  845 */   private int jdField_d_of_type_Int = 0;
/*      */   public IntBuffer b;
/*      */   public IntBuffer c;
/*      */   public FloatBuffer c;
/*      */   public FloatBuffer d;
/*      */   private static int jdField_e_of_type_Int;
/* 1405 */   private int jdField_i_of_type_Int = 4;
/*      */   private boolean jdField_d_of_type_Boolean;
/* 1412 */   private Vector3f jdField_c_of_type_JavaxVecmathVector3f = new Vector3f();
/*      */ 
/*      */   public static void a(Mesh paramMesh)
/*      */   {
/*  314 */     if ((paramMesh.jdField_d_of_type_Int == 0) && (jdField_c_of_type_Int > 0))
/*  315 */       return;
/*  316 */     if (paramMesh.jdField_d_of_type_Int == 0) {
/*  317 */       jdField_c_of_type_Int += 1;
/*      */     }
/*      */ 
/*  320 */     jdField_e_of_type_Int += 1;
/*  321 */     if ((paramMesh.jdField_d_of_type_Int != 0) && (jdField_e_of_type_Int % 5 != 0)) {
/*  322 */       return;
/*      */     }
/*      */ 
/*  328 */     long l1 = System.currentTimeMillis();
/*      */ 
/*  330 */     if (paramMesh.jdField_d_of_type_Int == 0) {
/*  331 */       paramMesh.jdField_d_of_type_Int += 1;
/*  332 */       return;
/*      */     }
/*  334 */     if (paramMesh.jdField_d_of_type_Int == 1) {
/*  335 */       paramMesh.jdField_d_of_type_Int += 1;
/*  336 */       return;
/*      */     }
/*      */ 
/*  339 */     if (paramMesh.jdField_d_of_type_Int == 2) {
/*  340 */       paramMesh.jdField_d_of_type_Int += 1;
/*  341 */       return;
/*      */     }
/*  343 */     l1 = System.currentTimeMillis() - l1;
/*      */ 
/*  347 */     long l2 = System.currentTimeMillis();
/*      */ 
/*  350 */     if (paramMesh.jdField_d_of_type_Int == 3) {
/*  351 */       paramMesh.jdField_d_of_type_Int += 1;
/*  352 */       return;
/*      */     }
/*  354 */     l2 = System.currentTimeMillis() - l2;
/*      */ 
/*  358 */     long l3 = System.currentTimeMillis();
/*      */ 
/*  362 */     l3 = System.currentTimeMillis() - l3;
/*      */ 
/*  364 */     long l4 = System.currentTimeMillis();
/*  365 */     if (paramMesh.jdField_d_of_type_Int == 4) {
/*  366 */       if ((!jdField_f_of_type_Boolean) && (paramMesh.jdField_b_of_type_JavaNioFloatBuffer.position() != 0)) throw new AssertionError();
/*  367 */       a(2, paramMesh);
/*  368 */       paramMesh.jdField_d_of_type_Int += 1;
/*  369 */       return;
/*      */     }
/*  371 */     if (paramMesh.jdField_d_of_type_Int == 5) {
/*  372 */       if ((!jdField_f_of_type_Boolean) && (paramMesh.jdField_c_of_type_JavaNioFloatBuffer.position() != 0)) throw new AssertionError();
/*  373 */       a(4, paramMesh);
/*  374 */       paramMesh.jdField_d_of_type_Int += 1;
/*  375 */       return;
/*      */     }
/*  377 */     if (paramMesh.jdField_d_of_type_Int == 6) {
/*  378 */       if ((!jdField_f_of_type_Boolean) && (paramMesh.jdField_d_of_type_JavaNioFloatBuffer.position() != 0)) throw new AssertionError();
/*  379 */       a(3, paramMesh);
/*  380 */       paramMesh.jdField_d_of_type_Int += 1;
/*  381 */       return;
/*      */     }
/*  383 */     if (paramMesh.jdField_d_of_type_Int == 7) {
/*  384 */       if ((!jdField_f_of_type_Boolean) && (paramMesh.a.position() != 0)) throw new AssertionError();
/*  385 */       paramMesh.a.capacity(); a(1, paramMesh);
/*  386 */       paramMesh.jdField_d_of_type_Int += 1;
/*  387 */       return;
/*      */     }
/*  389 */     l4 = System.currentTimeMillis() - l4;
/*      */ 
/*  393 */     if (paramMesh.jdField_d_of_type_Int == 8)
/*      */     {
/*  400 */       paramMesh.jdField_g_of_type_Int = 3;
/*      */ 
/*  403 */       jdField_c_of_type_Int -= 1;
/*      */ 
/*  405 */       System.err.println("BOUND " + paramMesh.b() + " to VBO(" + paramMesh.jdField_d_of_type_JavaNioIntBuffer.get(0) + "): faces: " + paramMesh.jdField_h_of_type_Int + ", indices: " + paramMesh.a.capacity() + " type: " + paramMesh.jdField_g_of_type_Int + ", alloc: " + l1 + ", prep: " + l2 + ", gen: " + l3 + ", bufferList: " + l4 + ", total: " + (l1 + l2 + l3 + l4) + ", currently building: " + jdField_c_of_type_Int);
/*  406 */       paramMesh.e = true;
/*      */     }
/*      */ 
/*  409 */     GL15.glBindBuffer(34962, 0);
/*      */   }
/*      */ 
/*      */   private static void a(int paramInt, Mesh paramMesh)
/*      */   {
/*  438 */     switch (paramInt) {
/*      */     case 1:
/*  440 */       if (paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0) {
/*  441 */         GL15.glGenBuffers(paramMesh.jdField_f_of_type_JavaNioIntBuffer);
/*      */       }
/*  443 */       GL15.glBindBuffer(34963, paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  445 */       GL15.glBufferData(34963, paramMesh.a, 35040);
/*      */ 
/*  447 */       GL15.glBindBuffer(34963, 0);
/*  448 */       break;
/*      */     case 5:
/*  451 */       if (paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0) {
/*  452 */         GL15.glGenBuffers(paramMesh.jdField_f_of_type_JavaNioIntBuffer);
/*      */       }
/*  454 */       GL15.glBindBuffer(34963, paramMesh.jdField_h_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  456 */       GL15.glBufferData(34963, paramMesh.jdField_b_of_type_JavaNioIntBuffer, 35040);
/*      */ 
/*  458 */       GL15.glBindBuffer(34963, 0);
/*  459 */       break;
/*      */     case 6:
/*  462 */       if (paramMesh.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0) {
/*  463 */         GL15.glGenBuffers(paramMesh.jdField_f_of_type_JavaNioIntBuffer);
/*      */       }
/*  465 */       GL15.glBindBuffer(34963, paramMesh.jdField_g_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  467 */       GL15.glBufferData(34963, paramMesh.jdField_c_of_type_JavaNioIntBuffer, 35040);
/*      */ 
/*  469 */       GL15.glBindBuffer(34963, 0);
/*  470 */       break;
/*      */     case 2:
/*  474 */       GL15.glGenBuffers(paramMesh.jdField_d_of_type_JavaNioIntBuffer);
/*      */ 
/*  476 */       GL15.glBindBuffer(34962, paramMesh.jdField_d_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  479 */       GL15.glBufferData(34962, paramMesh.jdField_b_of_type_JavaNioFloatBuffer, 35044);
/*      */ 
/*  481 */       GL15.glBindBuffer(34962, 0);
/*  482 */       break;
/*      */     case 4:
/*  486 */       GL15.glGenBuffers(paramMesh.jdField_e_of_type_JavaNioIntBuffer);
/*  487 */       GL15.glBindBuffer(34962, paramMesh.jdField_e_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  489 */       GL15.glBufferData(34962, paramMesh.jdField_c_of_type_JavaNioFloatBuffer, 35044);
/*  490 */       GL15.glBindBuffer(34962, 0);
/*  491 */       break;
/*      */     case 3:
/*  495 */       GL15.glGenBuffers(paramMesh.jdField_i_of_type_JavaNioIntBuffer);
/*  496 */       GL15.glBindBuffer(34962, paramMesh.jdField_i_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/*  498 */       GL15.glBufferData(34962, paramMesh.jdField_d_of_type_JavaNioFloatBuffer, 35044);
/*  499 */       GL15.glBindBuffer(34962, 0);
/*      */     }
/*      */ 
/*  502 */     GlUtil.f();
/*      */   }
/*      */ 
/*      */   private static Mesh a(String paramString)
/*      */   {
/*      */     Mesh localMesh;
/*  680 */     String str1 = paramString; String[] arrayOfString = null;
/*      */ 
/*  678 */     (
/*  680 */       localMesh = new Mesh()).jdField_a_of_type_JavaLangString = 
/*  680 */       str1;
/*  681 */     int j = 0;
/*  682 */     int k = 0;
/*  683 */     int m = 0;
/*  684 */     int n = 0;
/*      */ 
/*  686 */     if (!(
/*  686 */       paramString = new File(paramString))
/*  686 */       .exists()) {
/*  687 */       throw new FileNotFoundException(paramString.getPath());
/*      */     }
/*      */ 
/*  691 */     BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
/*      */     try {
/*  693 */       while (localBufferedReader.ready())
/*      */       {
/*      */         String str2;
/*  696 */         if ((
/*  696 */           str2 = localBufferedReader.readLine())
/*  696 */           .contains("vn ")) {
/*  697 */           localMesh.jdField_b_of_type_Int += 1;
/*      */         }
/*  699 */         if (str2.contains("vt ")) {
/*  700 */           localMesh.jdField_a_of_type_Int += 1;
/*      */         }
/*  702 */         if (str2.contains("clazz ")) {
/*  703 */           localMesh.f += 1;
/*      */         }
/*  705 */         if (str2.contains("f ")) {
/*  706 */           localMesh.jdField_h_of_type_Int += 1;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  715 */       localMesh.jdField_c_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.jdField_b_of_type_Int];
/*  716 */       localMesh.jdField_b_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.jdField_a_of_type_Int];
/*  717 */       localMesh.jdField_a_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[localMesh.f];
/*  718 */       localMesh.jdField_a_of_type_ArrayOfXV = new xV[localMesh.jdField_h_of_type_Int];
/*  719 */       for (int i1 = 0; i1 < localMesh.jdField_a_of_type_ArrayOfXV.length; i1++) {
/*  720 */         localMesh.jdField_a_of_type_ArrayOfXV[i1] = new xV();
/*      */       }
/*  722 */       localBufferedReader = new BufferedReader(new FileReader(paramString));
/*  723 */       while (localBufferedReader.ready())
/*      */       {
/*      */         String str3;
/*  725 */         if ((
/*  725 */           str3 = localBufferedReader.readLine())
/*  725 */           .contains("vn ")) {
/*  726 */           paramString = str3.split("[\\s]+");
/*      */ 
/*  728 */           localMesh.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[k] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
/*  729 */           k++;
/*      */         }
/*  731 */         if (str3.contains("vt ")) {
/*  732 */           paramString = str3.split("[\\s]+");
/*      */ 
/*  734 */           localMesh.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[m] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
/*  735 */           m++;
/*      */         }
/*  737 */         if (str3.contains("clazz ")) {
/*  738 */           paramString = str3.split("[\\s]+");
/*      */ 
/*  740 */           localMesh.jdField_a_of_type_ArrayOfJavaxVecmathVector3f[j] = new Vector3f(Float.parseFloat(paramString[1]), Float.parseFloat(paramString[2]), Float.parseFloat(paramString[3]));
/*  741 */           j++;
/*      */         }
/*  743 */         if (str3.contains("f ")) {
/*  744 */           paramString = str3.split("[\\s]+");
/*      */ 
/*  746 */           localMesh.jdField_a_of_type_Boolean = true;
/*      */ 
/*  752 */           for (int i2 = 1; i2 < paramString.length; i2++)
/*      */           {
/*  755 */             arrayOfString = paramString[i2].split("/");
/*  756 */             localMesh.jdField_a_of_type_ArrayOfXV[n].a[(i2 - 1)] = (Integer.parseInt(arrayOfString[0]) - 1);
/*      */ 
/*  759 */             localMesh.jdField_a_of_type_ArrayOfXV[n].c[(i2 - 1)] = (Integer.parseInt(arrayOfString[1]) - 1);
/*      */ 
/*  762 */             localMesh.jdField_a_of_type_ArrayOfXV[n].b[(i2 - 1)] = (Integer.parseInt(arrayOfString[2]) - 1);
/*      */           }
/*      */ 
/*  765 */           n++;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  773 */       localIOException.printStackTrace();
/*      */     }
/*      */ 
/*  775 */     return localMesh;
/*      */   }
/*      */ 
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/*      */     try
/*      */     {
/*  785 */       a("data/test.obj");
/*      */       return;
/*      */     }
/*      */     catch (FileNotFoundException localFileNotFoundException)
/*      */     {
/*  788 */       localFileNotFoundException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public Mesh()
/*      */   {
/*  836 */     this.jdField_g_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */ 
/*  838 */     this.jdField_h_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
/*      */ 
/* 1417 */     this.jdField_b_of_type_Boolean = true;
/* 1418 */     this.jdField_a_of_type_Zx = new zx();
/*      */   }
/*      */ 
/*      */   public void a()
/*      */   {
/* 1423 */     if (a() != null) {
/* 1424 */       a().b();
/*      */     }
/* 1426 */     if (this.jdField_g_of_type_Int == 3)
/*      */     {
/* 1428 */       this.jdField_d_of_type_JavaNioIntBuffer.rewind();
/* 1429 */       this.jdField_e_of_type_JavaNioIntBuffer.rewind();
/* 1430 */       this.jdField_i_of_type_JavaNioIntBuffer.rewind();
/* 1431 */       GL15.glDeleteBuffers(this.jdField_d_of_type_JavaNioIntBuffer);
/* 1432 */       GL15.glDeleteBuffers(this.jdField_e_of_type_JavaNioIntBuffer);
/* 1433 */       GL15.glDeleteBuffers(this.jdField_i_of_type_JavaNioIntBuffer);
/*      */     }
/* 1435 */     for (Iterator localIterator = this.jdField_a_of_type_JavaUtilList.iterator(); localIterator.hasNext(); ) ((xM)localIterator.next())
/* 1436 */         .a();
/*      */   }
/*      */ 
/*      */   public void b()
/*      */   {
/* 1458 */     if (h()) {
/* 1459 */       return;
/*      */     }
/*      */ 
/* 1462 */     if (xu.A.b()) {
/* 1463 */       this.jdField_c_of_type_Boolean = true;
/* 1464 */       GL11.glPolygonMode(1032, 6913);
/*      */     }
/*      */ 
/* 1467 */     Object localObject = this; if (!h()) { if (((Mesh)localObject).jdField_b_of_type_Boolean) { ((Mesh)localObject).c(); ((Mesh)localObject).jdField_b_of_type_Boolean = false; } if (((Mesh)localObject).a() != null) ((Mesh)localObject).jdField_c_of_type_Boolean = ((Mesh)((Mesh)localObject).a()).jdField_c_of_type_Boolean; GlUtil.d(); GL11.glDisable(3553); GL11.glBindTexture(3553, 0); GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F); GL11.glEnable(2896); GL11.glEnable(2929); if (((Mesh)localObject).jdField_c_of_type_Boolean) { GL11.glDisable(3042); GL11.glDisable(2896); GL11.glDisable(3553); GL11.glEnable(2903); GlUtil.a(1.0F, 1.0F, 1.0F, 1.0F); } else { ((Mesh)localObject).jdField_a_of_type_Zx.a(); GL11.glEnable(3553);
/*      */       }
/*      */     }
/* 1470 */     switch (this.jdField_g_of_type_Int)
/*      */     {
/*      */     case 3:
/* 1473 */       if ((!jdField_f_of_type_Boolean) && (this.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 1474 */       f();
/*      */ 
/* 1476 */       break;
/*      */     case 2:
/* 1478 */       throw new UnsupportedOperationException();
/*      */     case 0:
/*      */     case 1:
/*      */     default:
/* 1481 */       System.err.println("SOFTWARE " + this);
/* 1482 */       d();
/*      */     }
/* 1484 */     for (localObject = this.jdField_a_of_type_JavaUtilList.iterator(); ((Iterator)localObject).hasNext(); ) ((xM)((Iterator)localObject).next())
/* 1485 */         .b();
/*      */ 
/* 1489 */     localObject = this; GlUtil.c(); GL11.glDisable(2884); ((Mesh)localObject).jdField_a_of_type_Zx.c();
/*      */ 
/* 1491 */     if (xu.A.b()) {
/* 1492 */       GL11.glPolygonMode(1032, 6914);
/* 1493 */       this.jdField_c_of_type_Boolean = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   public final void f()
/*      */   {
/* 1499 */     i();
/*      */ 
/* 1501 */     j();
/*      */ 
/* 1503 */     s();
/*      */   }
/*      */ 
/*      */   public final void g()
/*      */   {
/* 1509 */     if ((!jdField_f_of_type_Boolean) && (!this.jdField_d_of_type_Boolean)) throw new AssertionError();
/*      */ 
/* 1511 */     GL11.glDrawElements(this.jdField_i_of_type_Int, this.jdField_h_of_type_Int * 3, 5125, 0L);
/*      */   }
/*      */ 
/*      */   public final yf a()
/*      */   {
/* 1656 */     return this.jdField_a_of_type_Yf;
/*      */   }
/*      */ 
/*      */   public final yi[] a()
/*      */   {
/* 1707 */     return this.jdField_a_of_type_ArrayOfYi;
/*      */   }
/*      */ 
/*      */   public final boolean b()
/*      */   {
/* 1740 */     this.jdField_a_of_type_XO.b(this.jdField_c_of_type_JavaxVecmathVector3f);
/* 1741 */     this.jdField_c_of_type_JavaxVecmathVector3f.sub(this.b);
/* 1742 */     return this.jdField_c_of_type_JavaxVecmathVector3f.length() < 2.0F;
/*      */   }
/*      */ 
/*      */   public final void h()
/*      */   {
/* 1764 */     if ((!jdField_f_of_type_Boolean) && (this.jdField_d_of_type_Boolean)) throw new AssertionError("Type is: " + this.jdField_g_of_type_Int);
/* 1765 */     if ((!jdField_f_of_type_Boolean) && (this.jdField_g_of_type_Int != 3)) throw new AssertionError("Type is: " + this.jdField_g_of_type_Int);
/* 1766 */     if ((!jdField_f_of_type_Boolean) && (this.jdField_d_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 1767 */     if ((!jdField_f_of_type_Boolean) && (this.jdField_e_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 1768 */     if ((!jdField_f_of_type_Boolean) && (this.jdField_i_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/* 1769 */     if ((!jdField_f_of_type_Boolean) && (this.jdField_f_of_type_JavaNioIntBuffer.get(0) == 0)) throw new AssertionError();
/*      */ 
/* 1772 */     GL20.glEnableVertexAttribArray(0);
/* 1773 */     GL20.glEnableVertexAttribArray(1);
/* 1774 */     GL20.glEnableVertexAttribArray(2);
/*      */ 
/* 1778 */     GL15.glBindBuffer(34962, this.jdField_d_of_type_JavaNioIntBuffer.get(0));
/* 1779 */     GL20.glVertexAttribPointer(0, 3, 5126, false, 0, 0L);
/*      */ 
/* 1783 */     GL15.glBindBuffer(34962, this.jdField_i_of_type_JavaNioIntBuffer.get(0));
/* 1784 */     GL20.glVertexAttribPointer(1, 3, 5126, false, 0, 0L);
/*      */ 
/* 1787 */     GL15.glBindBuffer(34962, this.jdField_e_of_type_JavaNioIntBuffer.get(0));
/* 1788 */     GL20.glVertexAttribPointer(2, 2, 5126, false, 0, 0L);
/*      */ 
/* 1793 */     GL15.glBindBuffer(34963, this.jdField_f_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/* 1795 */     this.jdField_d_of_type_Boolean = true;
/*      */   }
/*      */ 
/*      */   public final void i()
/*      */   {
/* 1804 */     GL11.glEnableClientState(32884);
/*      */ 
/* 1806 */     GL15.glBindBuffer(34962, this.jdField_d_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/* 1808 */     GL11.glVertexPointer(3, 5126, 0, 0L);
/*      */ 
/* 1815 */     GL11.glEnableClientState(32885);
/*      */ 
/* 1817 */     GL15.glBindBuffer(34962, this.jdField_i_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/* 1819 */     GL11.glNormalPointer(5126, 0, 0L);
/*      */ 
/* 1822 */     GL11.glEnableClientState(32888);
/*      */ 
/* 1824 */     GL15.glBindBuffer(34962, this.jdField_e_of_type_JavaNioIntBuffer.get(0));
/*      */ 
/* 1826 */     GL11.glTexCoordPointer(2, 5126, 0, 0L);
/*      */ 
/* 1828 */     if (this.jdField_a_of_type_Yf != null) {
/* 1829 */       this.jdField_a_of_type_Yf.a();
/*      */     }
/* 1831 */     GL15.glBindBuffer(34963, this.jdField_f_of_type_JavaNioIntBuffer.get(0));
/*      */   }
/*      */ 
/*      */   public void c()
/*      */   {
/*      */   }
/*      */ 
/*      */   public final void j()
/*      */   {
/* 1904 */     GL11.glDrawElements(this.jdField_i_of_type_Int, this.jdField_h_of_type_Int * 3, 5125, 0L);
/*      */   }
/*      */ 
/*      */   public final void b(int paramInt)
/*      */   {
/* 1959 */     this.jdField_h_of_type_Int = paramInt;
/*      */   }
/*      */ 
/*      */   public final void k()
/*      */   {
/* 1977 */     this.jdField_a_of_type_Boolean = true;
/*      */   }
/*      */ 
/*      */   public final void a(yf paramyf)
/*      */   {
/* 1984 */     this.jdField_a_of_type_Yf = paramyf;
/*      */   }
/*      */ 
/*      */   public static void l()
/*      */   {
/*      */   }
/*      */ 
/*      */   public final void c(int paramInt)
/*      */   {
/* 2002 */     this.jdField_a_of_type_Int = paramInt;
/*      */   }
/*      */ 
/*      */   public final void a(yi[] paramArrayOfyi)
/*      */   {
/* 2052 */     this.jdField_a_of_type_ArrayOfYi = paramArrayOfyi;
/*      */   }
/*      */ 
/*      */   private void d()
/*      */   {
/* 2057 */     GL11.glBegin(4);
/*      */ 
/* 2063 */     if ((this.jdField_a_of_type_ArrayOfXV == null) || (this.jdField_a_of_type_ArrayOfXV.length <= 0)) {
/* 2064 */       throw new IllegalArgumentException("Mesh " + b() + " has no faces");
/*      */     }
/*      */ 
/* 2067 */     for (int j = 0; j < this.jdField_a_of_type_ArrayOfXV.length; 
/* 2070 */       j++)
/*      */     {
/* 2078 */       if ((this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_a_of_type_ArrayOfXV[j].c != null))
/*      */       {
/* 2080 */         GL11.glTexCoord2f(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[0]].x, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[0]].y);
/*      */       }
/*      */ 
/* 2087 */       if (this.jdField_a_of_type_Boolean) {
/* 2088 */         GL11.glNormal3f(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[0]].x, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[0]].y, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[0]].z);
/*      */       }
/*      */       else
/*      */       {
/* 2093 */         GL11.glNormal3f(null.x, null.y, null.z);
/*      */       }
/*      */       Vector3f[] arrayOfVector3f;
/* 2107 */       GL11.glVertex3f((
/* 2107 */         arrayOfVector3f = this.jdField_a_of_type_ArrayOfJavaxVecmathVector3f)[
/* 2107 */         this.jdField_a_of_type_ArrayOfXV[j].a[0]].x, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[0]].y, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[0]].z);
/*      */ 
/* 2114 */       if ((this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_a_of_type_ArrayOfXV[j].c != null)) {
/* 2115 */         GL11.glTexCoord2f(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[1]].x, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[1]].y);
/*      */       }
/*      */ 
/* 2122 */       if (this.jdField_a_of_type_Boolean) {
/* 2123 */         GL11.glNormal3f(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[1]].x, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[1]].y, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[1]].z);
/*      */       }
/*      */       else
/*      */       {
/* 2128 */         GL11.glNormal3f(null.x, null.y, null.z);
/*      */       }
/*      */ 
/* 2140 */       GL11.glVertex3f(arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[1]].x, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[1]].y, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[1]].z);
/*      */ 
/* 2145 */       if ((this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f != null) && (this.jdField_a_of_type_ArrayOfXV[j].c != null)) {
/* 2146 */         GL11.glTexCoord2f(this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[2]].x, this.jdField_b_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].c[2]].y);
/*      */       }
/*      */ 
/* 2153 */       if (this.jdField_a_of_type_Boolean) {
/* 2154 */         GL11.glNormal3f(this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[2]].x, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[2]].y, this.jdField_c_of_type_ArrayOfJavaxVecmathVector3f[this.jdField_a_of_type_ArrayOfXV[j].b[2]].z);
/*      */       }
/*      */       else
/*      */       {
/* 2159 */         GL11.glNormal3f(null.x, null.y, null.z);
/*      */       }
/*      */ 
/* 2170 */       GL11.glVertex3f(arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[2]].x, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[2]].y, arrayOfVector3f[this.jdField_a_of_type_ArrayOfXV[j].a[2]].z);
/*      */     }
/*      */ 
/* 2175 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */   public final void m()
/*      */   {
/* 2276 */     GL15.glBindBuffer(34963, 0); GL15.glBindBuffer(34962, 0);
/*      */ 
/* 2279 */     GL20.glDisableVertexAttribArray(0);
/* 2280 */     GL20.glDisableVertexAttribArray(1);
/* 2281 */     GL20.glDisableVertexAttribArray(2);
/* 2282 */     this.jdField_d_of_type_Boolean = false;
/*      */   }
/*      */   public final void s() {
/* 2285 */     GL15.glBindBuffer(34962, 0);
/*      */ 
/* 2288 */     GL11.glDisableClientState(32884);
/*      */ 
/* 2290 */     GL11.glDisableClientState(32888);
/*      */ 
/* 2292 */     GL11.glDisableClientState(32885);
/*      */ 
/* 2294 */     if (this.jdField_a_of_type_Yf != null) {
/* 2295 */       this.jdField_a_of_type_Yf.b();
/*      */     }
/* 2297 */     GL15.glBindBuffer(34963, 0);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.forms.Mesh
 * JD-Core Version:    0.6.2
 */