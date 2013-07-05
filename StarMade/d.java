/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontFormatException;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.logging.FileHandler;
/*     */ import java.util.logging.LogManager;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.opengl.ContextCapabilities;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.UnicodeFont;
/*     */ import org.newdawn.slick.font.effects.ColorEffect;
/*     */ import org.newdawn.slick.font.effects.OutlineEffect;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.crashreporter.CrashReporter;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.core.ResourceException;
/*     */ 
/*     */ public final class d
/*     */   implements wE
/*     */ {
/*     */   private static e jdField_a_of_type_E;
/*     */   private static e jdField_b_of_type_E;
/*     */   private static FileHandler jdField_a_of_type_JavaUtilLoggingFileHandler;
/*     */   private static Logger jdField_a_of_type_JavaUtilLoggingLogger;
/*     */   private static ImageIcon[] jdField_a_of_type_ArrayOfJavaxSwingImageIcon;
/*     */   public float a;
/*     */   private static UnicodeFont jdField_a_of_type_OrgNewdawnSlickUnicodeFont;
/*     */   private static UnicodeFont jdField_b_of_type_OrgNewdawnSlickUnicodeFont;
/*     */   private static UnicodeFont c;
/*     */   private static UnicodeFont d;
/*     */   private static UnicodeFont e;
/*     */   private static UnicodeFont f;
/*     */   private static UnicodeFont g;
/*     */   private static UnicodeFont h;
/*     */   private static UnicodeFont i;
/*     */   private static UnicodeFont j;
/*     */   private static UnicodeFont k;
/*     */   private static UnicodeFont l;
/*     */   private static UnicodeFont m;
/*     */   private static UnicodeFont n;
/*     */   private static UnicodeFont o;
/*     */   public static Set a;
/*     */   public static zj a;
/*     */   public static zj b;
/*     */   public static zj c;
/*     */   public static zj d;
/*     */   public static zj e;
/*     */   public static zj f;
/*     */   public static zj g;
/*     */   public static zj h;
/*     */   public static zj i;
/*     */   public static zj j;
/*     */   public static zj k;
/*     */   public static zj l;
/*     */   public static zj m;
/*     */   public static zj n;
/*     */   public static zj o;
/*     */   public static zj p;
/*     */   public static zj q;
/*     */   public static zj r;
/*     */   public static zj s;
/*     */   public static zj t;
/*     */   public static zj u;
/*     */   public static zj v;
/*     */   public static zj w;
/*     */   public static zj x;
/*     */   public static zj y;
/*     */   public static zj z;
/*     */   public static zj A;
/*     */   public static zj B;
/*     */   public static zj C;
/*     */   public static zj D;
/*     */   public static zj E;
/*     */   public static zj F;
/*     */   public static zj G;
/*     */   public static zj H;
/*     */   public static zj I;
/*     */   public static zj J;
/*     */   public static Ac[] a;
/*     */ 
/*     */   public static void a()
/*     */   {
/*  21 */     if (jdField_b_of_type_E != null) {
/*  22 */       jdField_b_of_type_E.close();
/*     */     }
/*  24 */     if (jdField_a_of_type_E != null) {
/*  25 */       jdField_a_of_type_E.close();
/*     */     }
/*     */ 
/*  28 */     if (jdField_a_of_type_JavaUtilLoggingFileHandler != null) {
/*  29 */       if (jdField_a_of_type_JavaUtilLoggingLogger != null) {
/*  30 */         jdField_a_of_type_JavaUtilLoggingLogger.removeHandler(jdField_a_of_type_JavaUtilLoggingFileHandler);
/*     */       }
/*     */ 
/*  33 */       jdField_a_of_type_JavaUtilLoggingFileHandler.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void b()
/*     */   {
/*  40 */     a();
/*     */     LogManager localLogManager;
/*  42 */     (
/*  43 */       localLogManager = LogManager.getLogManager())
/*  43 */       .reset();
/*     */ 
/*  45 */     if (!(
/*  45 */       localObject = new File("./logs/"))
/*  45 */       .exists()) {
/*  46 */       ((File)localObject).mkdirs();
/*     */     }
/*  48 */     (
/*  53 */       d.jdField_a_of_type_JavaUtilLoggingFileHandler = new FileHandler("./logs/log.txt", 4194304, 20))
/*  53 */       .setFormatter(new b());
/*     */ 
/*  56 */     Object localObject = System.out;
/*  57 */     PrintStream localPrintStream = System.err;
/*     */ 
/*  63 */     (
/*  64 */       d.jdField_a_of_type_JavaUtilLoggingLogger = Logger.getLogger("stdout"))
/*  64 */       .addHandler(jdField_a_of_type_JavaUtilLoggingFileHandler);
/*  65 */     jdField_b_of_type_E = new e((PrintStream)localObject, jdField_a_of_type_JavaUtilLoggingLogger, g.a, "OUT");
/*  66 */     System.setOut(new PrintStream(jdField_b_of_type_E, true));
/*  67 */     localLogManager.addLogger(jdField_a_of_type_JavaUtilLoggingLogger);
/*     */ 
/*  69 */     (
/*  70 */       d.jdField_a_of_type_JavaUtilLoggingLogger = Logger.getLogger("stderr"))
/*  70 */       .addHandler(jdField_a_of_type_JavaUtilLoggingFileHandler);
/*  71 */     jdField_a_of_type_E = new e(localPrintStream, jdField_a_of_type_JavaUtilLoggingLogger, g.b, "ERR");
/*  72 */     System.setErr(new PrintStream(jdField_a_of_type_E, true));
/*  73 */     localLogManager.addLogger(jdField_a_of_type_JavaUtilLoggingLogger);
/*     */   }
/*     */ 
/*     */   public static void a(Transform paramTransform1, Transform paramTransform2)
/*     */   {
/* 128 */     float f1 = paramTransform1.basis.m00;
/* 129 */     float f2 = paramTransform1.basis.m01;
/* 130 */     float f3 = paramTransform1.basis.m02;
/* 131 */     float f4 = paramTransform1.origin.x;
/* 132 */     float f5 = paramTransform1.basis.m10;
/* 133 */     float f6 = paramTransform1.basis.m11;
/* 134 */     float f7 = paramTransform1.basis.m12;
/* 135 */     float f8 = paramTransform1.origin.y;
/* 136 */     float f9 = paramTransform1.basis.m20;
/* 137 */     float f10 = paramTransform1.basis.m21;
/* 138 */     float f11 = paramTransform1.basis.m22;
/* 139 */     float f12 = paramTransform1.origin.z;
/* 140 */     float f13 = paramTransform2.basis.m00;
/*     */ 
/* 146 */     float f14 = paramTransform2.basis.m01;
/* 147 */     float f15 = paramTransform2.basis.m02;
/* 148 */     float f16 = paramTransform2.origin.x;
/* 149 */     float f17 = paramTransform2.basis.m10;
/* 150 */     float f18 = paramTransform2.basis.m11;
/* 151 */     float f19 = paramTransform2.basis.m12;
/* 152 */     float f20 = paramTransform2.origin.y;
/* 153 */     float f21 = paramTransform2.basis.m20;
/* 154 */     float f22 = paramTransform2.basis.m21;
/* 155 */     float f23 = paramTransform2.basis.m22;
/* 156 */     paramTransform2 = paramTransform2.origin.z;
/* 157 */     float f24 = f1 * f13 + f2 * f17 + f3 * f21 + f4 * 0.0F;
/*     */ 
/* 169 */     float f25 = f1 * f14 + f2 * f18 + f3 * f22 + f4 * 0.0F;
/*     */ 
/* 171 */     float f26 = f1 * f15 + f2 * f19 + f3 * f23 + f4 * 0.0F;
/*     */ 
/* 173 */     f1 = f1 * f16 + f2 * f20 + f3 * paramTransform2 + f4;
/*     */ 
/* 176 */     f2 = f5 * f13 + f6 * f17 + f7 * f21 + f8 * 0.0F;
/*     */ 
/* 178 */     f3 = f5 * f14 + f6 * f18 + f7 * f22 + f8 * 0.0F;
/*     */ 
/* 180 */     f4 = f5 * f15 + f6 * f19 + f7 * f23 + f8 * 0.0F;
/*     */ 
/* 182 */     f5 = f5 * f16 + f6 * f20 + f7 * paramTransform2 + f8;
/*     */ 
/* 185 */     f6 = f9 * f13 + f10 * f17 + f11 * f21 + f12 * 0.0F;
/*     */ 
/* 187 */     f7 = f9 * f14 + f10 * f18 + f11 * f22 + f12 * 0.0F;
/*     */ 
/* 189 */     f8 = f9 * f15 + f10 * f19 + f11 * f23 + f12 * 0.0F;
/*     */ 
/* 191 */     paramTransform2 = f9 * f16 + f10 * f20 + f11 * paramTransform2 + f12;
/*     */ 
/* 194 */     paramTransform1.basis.m00 = f24;
/*     */ 
/* 209 */     paramTransform1.basis.m01 = f25;
/* 210 */     paramTransform1.basis.m02 = f26;
/* 211 */     paramTransform1.origin.x = f1;
/* 212 */     paramTransform1.basis.m10 = f2;
/* 213 */     paramTransform1.basis.m11 = f3;
/* 214 */     paramTransform1.basis.m12 = f4;
/* 215 */     paramTransform1.origin.y = f5;
/* 216 */     paramTransform1.basis.m20 = f6;
/* 217 */     paramTransform1.basis.m21 = f7;
/* 218 */     paramTransform1.basis.m22 = f8;
/* 219 */     paramTransform1.origin.z = paramTransform2;
/*     */   }
/*     */ 
/*     */   public static Quat4f a(float paramFloat, Vector3f paramVector3f, Quat4f paramQuat4f)
/*     */   {
/*  49 */     if ((paramVector3f.x == 0.0F) && (paramVector3f.y == 0.0F) && (paramVector3f.z == 0.0F)) {
/*  50 */       paramQuat4f.set(0.0F, 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     else {
/*  53 */       float f1 = FastMath.h(paramFloat = 0.5F * paramFloat);
/*     */ 
/*  54 */       paramQuat4f.w = FastMath.d(paramFloat);
/*  55 */       paramQuat4f.x = (f1 * paramVector3f.x);
/*  56 */       paramQuat4f.y = (f1 * paramVector3f.y);
/*  57 */       paramQuat4f.z = (f1 * paramVector3f.z);
/*     */     }
/*  59 */     return paramQuat4f;
/*     */   }
/*     */ 
/*     */   public static Vector3f a(Quat4f paramQuat4f, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/* 104 */     float f1 = paramQuat4f.x;
/* 105 */     float f2 = paramQuat4f.y;
/* 106 */     float f3 = paramQuat4f.z;
/* 107 */     paramQuat4f = paramQuat4f.w;
/* 108 */     if (paramVector3f2 == null) {
/* 109 */       paramVector3f2 = new Vector3f();
/*     */     }
/* 111 */     if ((paramVector3f1.x == 0.0F) && (paramVector3f1.y == 0.0F) && (paramVector3f1.z == 0.0F)) {
/* 112 */       paramVector3f2.set(0.0F, 0.0F, 0.0F);
/*     */     } else {
/* 114 */       float f4 = paramVector3f1.x; float f5 = paramVector3f1.y; paramVector3f1 = paramVector3f1.z;
/* 115 */       paramVector3f2.x = (paramQuat4f * paramQuat4f * f4 + f2 * 2.0F * paramQuat4f * paramVector3f1 - f3 * 2.0F * paramQuat4f * f5 + f1 * f1 * f4 + f2 * 2.0F * f1 * f5 + f3 * 2.0F * f1 * paramVector3f1 - f3 * f3 * f4 - f2 * f2 * f4);
/*     */ 
/* 118 */       paramVector3f2.y = (f1 * 2.0F * f2 * f4 + f2 * f2 * f5 + f3 * 2.0F * f2 * paramVector3f1 + paramQuat4f * 2.0F * f3 * f4 - f3 * f3 * f5 + paramQuat4f * paramQuat4f * f5 - f1 * 2.0F * paramQuat4f * paramVector3f1 - f1 * f1 * f5);
/*     */ 
/* 121 */       paramVector3f2.z = (f1 * 2.0F * f3 * f4 + f2 * 2.0F * f3 * f5 + f3 * f3 * paramVector3f1 - paramQuat4f * 2.0F * f2 * f4 - f2 * f2 * paramVector3f1 + paramQuat4f * 2.0F * f1 * f5 - f1 * f1 * paramVector3f1 + paramQuat4f * paramQuat4f * paramVector3f1);
/*     */     }
/*     */ 
/* 125 */     return paramVector3f2;
/*     */   }
/*     */ 
/*     */   public static Quat4f a(Quat4f paramQuat4f1, Quat4f paramQuat4f2, float paramFloat, Quat4f paramQuat4f3)
/*     */   {
/* 216 */     if ((paramQuat4f1.x == paramQuat4f2.x) && (paramQuat4f1.y == paramQuat4f2.y) && (paramQuat4f1.z == paramQuat4f2.z) && (paramQuat4f1.w == paramQuat4f2.w)) {
/* 217 */       paramQuat4f3.set(paramQuat4f1);
/* 218 */       return paramQuat4f3;
/*     */     }
/*     */     float f1;
/* 224 */     if ((
/* 224 */       f1 = paramQuat4f1.x * paramQuat4f2.x + paramQuat4f1.y * paramQuat4f2.y + paramQuat4f1.z * paramQuat4f2.z + paramQuat4f1.w * paramQuat4f2.w) < 
/* 224 */       0.0F)
/*     */     {
/* 226 */       paramQuat4f2.x = (-paramQuat4f2.x);
/* 227 */       paramQuat4f2.y = (-paramQuat4f2.y);
/* 228 */       paramQuat4f2.z = (-paramQuat4f2.z);
/* 229 */       paramQuat4f2.w = (-paramQuat4f2.w);
/* 230 */       f1 = -f1;
/*     */     }
/*     */ 
/* 234 */     float f2 = 1.0F - paramFloat;
/* 235 */     float f3 = paramFloat;
/*     */ 
/* 239 */     if (1.0F - f1 > 0.1F)
/*     */     {
/* 241 */       f1 = FastMath.b(f1);
/* 242 */       f3 = 1.0F / FastMath.h(f1);
/*     */ 
/* 246 */       f2 = FastMath.h(f2 * f1) * f3;
/* 247 */       f3 = FastMath.h(paramFloat * f1) * f3;
/*     */     }
/*     */ 
/* 253 */     paramQuat4f3.x = (f2 * paramQuat4f1.x + f3 * paramQuat4f2.x);
/* 254 */     paramQuat4f3.y = (f2 * paramQuat4f1.y + f3 * paramQuat4f2.y);
/* 255 */     paramQuat4f3.z = (f2 * paramQuat4f1.z + f3 * paramQuat4f2.z);
/* 256 */     paramQuat4f3.w = (f2 * paramQuat4f1.w + f3 * paramQuat4f2.w);
/*     */ 
/* 259 */     return paramQuat4f3;
/*     */   }
/*     */ 
/*     */   public static void a(Matrix3f paramMatrix3f, Quat4f paramQuat4f)
/*     */   {
/*  18 */     if ((
/*  18 */       f1 = paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22) > 
/*  18 */       0.0F) {
/*  19 */       f1 = FastMath.l(f1 + 1.0F) * 2.0F;
/*  20 */       paramQuat4f.w = (0.25F * f1);
/*  21 */       paramQuat4f.x = ((paramMatrix3f.m21 - paramMatrix3f.m12) / f1);
/*  22 */       paramQuat4f.y = ((paramMatrix3f.m02 - paramMatrix3f.m20) / f1);
/*  23 */       paramQuat4f.z = ((paramMatrix3f.m10 - paramMatrix3f.m01) / f1);
/*  24 */       return; } if ((paramMatrix3f.m00 > paramMatrix3f.m11) && (paramMatrix3f.m00 > paramMatrix3f.m22)) {
/*  25 */       f1 = FastMath.l(1.0F + paramMatrix3f.m00 - paramMatrix3f.m11 - paramMatrix3f.m22) * 2.0F;
/*  26 */       paramQuat4f.w = ((paramMatrix3f.m21 - paramMatrix3f.m12) / f1);
/*  27 */       paramQuat4f.x = (0.25F * f1);
/*  28 */       paramQuat4f.y = ((paramMatrix3f.m01 + paramMatrix3f.m10) / f1);
/*  29 */       paramQuat4f.z = ((paramMatrix3f.m02 + paramMatrix3f.m20) / f1);
/*  30 */       return; } if (paramMatrix3f.m11 > paramMatrix3f.m22) {
/*  31 */       f1 = FastMath.l(1.0F + paramMatrix3f.m11 - paramMatrix3f.m00 - paramMatrix3f.m22) * 2.0F;
/*  32 */       paramQuat4f.w = ((paramMatrix3f.m02 - paramMatrix3f.m20) / f1);
/*  33 */       paramQuat4f.x = ((paramMatrix3f.m01 + paramMatrix3f.m10) / f1);
/*  34 */       paramQuat4f.y = (0.25F * f1);
/*  35 */       paramQuat4f.z = ((paramMatrix3f.m12 + paramMatrix3f.m21) / f1);
/*  36 */       return;
/*  37 */     }float f1 = FastMath.l(1.0F + paramMatrix3f.m22 - paramMatrix3f.m00 - paramMatrix3f.m11) * 2.0F;
/*  38 */     paramQuat4f.w = ((paramMatrix3f.m10 - paramMatrix3f.m01) / f1);
/*  39 */     paramQuat4f.x = ((paramMatrix3f.m02 + paramMatrix3f.m20) / f1);
/*  40 */     paramQuat4f.y = ((paramMatrix3f.m12 + paramMatrix3f.m21) / f1);
/*  41 */     paramQuat4f.z = (0.25F * f1);
/*     */   }
/*     */ 
/*     */   public static void a(int paramInt1, int paramInt2, int paramInt3, mI parammI, int paramInt4, Random paramRandom)
/*     */   {
/*  56 */     if (paramInt1 == 8) if (((paramInt2 == 8 ? 1 : 0) & (paramInt3 == 5 ? 1 : 0)) != 0) {
/*  57 */         parammI.a(paramInt4, mD.c);
/*  58 */         parammI.a(paramInt4, mC.b);
/*  59 */         return;
/*     */       }
/*  61 */     if (paramInt1 == 100) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/*  62 */         parammI.a(paramInt4, mD.c);
/*  63 */         parammI.a(paramInt4, mC.b);
/*  64 */         return;
/*     */       }
/*  66 */     if (paramInt1 == 200) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/*  67 */         parammI.a(paramInt4, mD.c);
/*  68 */         parammI.a(paramInt4, mC.c);
/*  69 */         return;
/*     */       }
/*  71 */     if (paramInt1 == 300) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/*  72 */         parammI.a(paramInt4, mD.c);
/*  73 */         parammI.a(paramInt4, mC.e);
/*  74 */         return;
/*     */       }
/*  76 */     if (paramInt1 == 400) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/*  77 */         parammI.a(paramInt4, mD.c);
/*  78 */         parammI.a(paramInt4, mC.jdField_a_of_type_MC);
/*  79 */         return;
/*     */       }
/*  81 */     if (paramInt1 == 500) if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0) {
/*  82 */         parammI.a(paramInt4, mD.c);
/*  83 */         parammI.a(paramInt4, mC.d);
/*  84 */         return;
/*     */       }
/*  86 */     if (paramInt1 == 8) if (((paramInt2 == 5 ? 1 : 0) & (paramInt3 == 8 ? 1 : 0)) != 0) {
/*  87 */         parammI.a(paramInt4, mD.a);
/*  88 */         parammI.a(paramInt4, kk.a);
/*  89 */         return;
/*     */       }
/*  91 */     if (paramInt1 == 8) if (((paramInt2 == 5 ? 1 : 0) & (paramInt3 == 5 ? 1 : 0)) != 0) {
/*  92 */         parammI.a(paramInt4, mD.a);
/*  93 */         parammI.a(paramInt4, kk.c);
/*  94 */         return;
/*     */       }
/*  96 */     if (mx.b.a(paramInt1, paramInt2, paramInt3)) {
/*  97 */       parammI.a(paramInt4, mD.d); return;
/*  98 */     }if ((paramInt1 == mx.b.jdField_a_of_type_Int) && (paramInt2 == mx.b.b - 1) && (paramInt3 == mx.b.c)) {
/*  99 */       parammI.a(paramInt4, mD.c);
/* 100 */       a(parammI, paramInt4, paramRandom); return;
/*     */     }
/*     */ 
/* 103 */     if ((
/* 103 */       paramInt1 = paramRandom.nextInt(250)) < 
/* 103 */       5) {
/* 104 */       parammI.a(paramInt4, mD.c);
/* 105 */       a(parammI, paramInt4, paramRandom); return;
/*     */     }
/*     */ 
/* 108 */     if (paramInt1 < 240) {
/* 109 */       parammI.a(paramInt4, mD.b); return;
/*     */     }
/* 111 */     parammI.a(paramInt4, mD.a);
/*     */ 
/* 113 */     if (paramRandom.nextInt(5) == 0)
/*     */     {
/* 114 */       parammI.a(paramInt4, kk.c); return;
/*     */     }
/* 116 */     parammI.a(paramInt4, kk.a);
/*     */   }
/*     */ 
/*     */   private static void a(mI parammI, int paramInt, Random paramRandom)
/*     */   {
/* 126 */     int i1 = paramRandom.nextInt(mC.values().length);
/* 127 */     while (!mC.values()[i1].jdField_a_of_type_Boolean) {
/* 128 */       i1 = paramRandom.nextInt(mC.values().length);
/*     */     }
/* 130 */     parammI.a(paramInt, mC.values()[i1]);
/*     */   }
/*     */ 
/*     */   public static ImageIcon a(int paramInt)
/*     */   {
/*  40 */     if (jdField_a_of_type_ArrayOfJavaxSwingImageIcon == null) {
/*     */       try {
/*  42 */         jdField_a_of_type_ArrayOfJavaxSwingImageIcon = new ImageIcon[768]; (localObject = new BufferedImage[3])[0] = ImageIO.read(new File("./data/textures/block/t000.png")); localObject[1] = ImageIO.read(new File("./data/textures/block/t001.png")); localObject[2] = ImageIO.read(new File("./data/textures/block/t002.png")); for (int i1 = 0; i1 < jdField_a_of_type_ArrayOfJavaxSwingImageIcon.length; i1++) { int i2 = i1 % 256 % 16; int i3 = i1 % 256 / 16; BufferedImage localBufferedImage = localObject[(i1 / 256)].getSubimage(i2 << 6, i3 << 6, 64, 64); jdField_a_of_type_ArrayOfJavaxSwingImageIcon[i1] = new ImageIcon(localBufferedImage);
/*     */         }
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */         Object localObject;
/*  43 */         (
/*  44 */           localObject = 
/*  46 */           localIOException).printStackTrace();
/*  45 */         a((Exception)localObject);
/*     */       }
/*     */     }
/*  48 */     return jdField_a_of_type_ArrayOfJavaxSwingImageIcon[paramInt];
/*     */   }
/*     */ 
/*     */   public static void a(String paramString1, String paramString2, String paramString3, String paramString4, File paramFile)
/*     */   {
/* 120 */     if ((paramString1 != null) && (paramString4 != null) && (paramFile != null))
/*     */     {
/* 122 */       StringBuffer localStringBuffer = new StringBuffer("ftp://");
/*     */ 
/* 124 */       if ((paramString2 != null) && (paramString3 != null))
/*     */       {
/* 126 */         localStringBuffer.append(paramString2);
/* 127 */         localStringBuffer.append(':');
/* 128 */         localStringBuffer.append(paramString3);
/* 129 */         localStringBuffer.append('@');
/*     */       }
/* 131 */       localStringBuffer.append(paramString1);
/* 132 */       localStringBuffer.append('/');
/* 133 */       localStringBuffer.append(paramString4);
/*     */ 
/* 138 */       localStringBuffer.append(";type=i");
/*     */ 
/* 140 */       paramString1 = null;
/* 141 */       paramString2 = null;
/*     */       try
/*     */       {
/* 145 */         paramString3 = new URL(localStringBuffer.toString())
/* 145 */           .openConnection();
/*     */ 
/* 147 */         paramString2 = new BufferedOutputStream(paramString3.getOutputStream());
/* 148 */         paramString1 = new BufferedInputStream(new FileInputStream(paramFile));
/*     */ 
/* 152 */         while ((paramString3 = paramString1.read()) != -1)
/*     */         {
/* 154 */           paramString2.write(paramString3);
/*     */         }
/*     */ 
/*     */         try
/*     */         {
/* 159 */           paramString1.close();
/*     */         }
/*     */         catch (IOException localIOException1)
/*     */         {
/* 167 */           localIOException1.printStackTrace();
/*     */         }
/*     */ 
/*     */         try
/*     */         {
/* 168 */           paramString2.close();
/*     */           return;
/*     */         }
/*     */         catch (IOException localIOException2)
/*     */         {
/* 176 */           localIOException2.printStackTrace();
/*     */           return;
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/* 159 */         if (paramString1 != null) {
/*     */           try
/*     */           {
/* 162 */             paramString1.close();
/*     */           }
/*     */           catch (IOException localIOException3)
/*     */           {
/* 167 */             localIOException3.printStackTrace();
/*     */           }
/*     */         }
/*     */ 
/* 168 */         if (paramString2 != null) {
/*     */           try
/*     */           {
/* 171 */             paramString2.close();
/*     */           }
/*     */           catch (IOException localIOException4)
/*     */           {
/* 176 */             localIOException4.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 178 */     System.out.println("Input not available.");
/*     */   }
/*     */ 
/*     */   private static void a(String paramString1, String paramString2, ZipOutputStream paramZipOutputStream, String paramString3)
/*     */   {
/*  28 */     paramString1.replace('\\', '/');
/*  29 */     paramString2.replace('\\', '/');
/*     */     File localFile;
/*  34 */     String[] arrayOfString = (
/*  34 */       localFile = new File(paramString2))
/*  34 */       .list();
/*     */     try {
/*  36 */       for (int i1 = 0; i1 < arrayOfString.length; i1++) {
/*  37 */         if ((paramString3 == null) || (!arrayOfString[i1].startsWith(paramString3))) {
/*  38 */           b(paramString1 + "/" + localFile.getName(), paramString2 + "/" + arrayOfString[i1], paramZipOutputStream, paramString3);
/*     */         }
/*     */       }
/*     */ 
/*  42 */       return;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void b(String paramString1, String paramString2, ZipOutputStream paramZipOutputStream, String paramString3)
/*     */   {
/*  59 */     paramString1.replace('\\', '/');
/*  60 */     paramString2.replace('\\', '/');
/*     */     File localFile;
/*  63 */     if ((
/*  63 */       localFile = new File(paramString2))
/*  63 */       .isDirectory()) {
/*  64 */       a(paramString1, paramString2, paramZipOutputStream, paramString3); return;
/*     */     }
/*     */ 
/*  67 */     paramString3 = new byte[1024];
/*     */     try
/*     */     {
/*  70 */       paramString2 = new FileInputStream(paramString2);
/*     */ 
/*  75 */       if ((
/*  75 */         paramString1 = paramString1 + "/" + localFile.getName())
/*  75 */         .startsWith("/")) {
/*  76 */         paramString1 = paramString1.substring(1);
/*     */       }
/*  78 */       System.err.println("PUTTING: " + paramString1);
/*     */ 
/*  82 */       paramString1 = new ZipEntry(paramString1);
/*  83 */       paramZipOutputStream.putNextEntry(paramString1);
/*     */ 
/*  85 */       while ((paramString1 = paramString2.read(paramString3)) > 0) {
/*  86 */         paramZipOutputStream.write(paramString3, 0, paramString1);
/*  88 */       }paramString2.close();
/*     */       return;
/*     */     } catch (Exception localException) {
/*  91 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 109 */     System.out.println("Zipping folder: " + paramString1 + " to " + paramString2 + " (Filter: " + paramString3 + ")");
/*     */ 
/* 113 */     paramString2.replace('\\', '/');
/* 114 */     paramString1.replace('\\', '/');
/*     */ 
/* 116 */     FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
/* 117 */     paramString2 = new ZipOutputStream(localFileOutputStream);
/*     */ 
/* 120 */     a("", paramString1, paramString2, paramString3);
/*     */ 
/* 122 */     paramString2.flush();
/* 123 */     paramString2.close();
/* 124 */     localFileOutputStream.close();
/*     */   }
/*     */ 
/*     */   public static void a(Exception paramException)
/*     */   {
/*  44 */     Object[] arrayOfObject = { "Retry", "Exit" };
/*  45 */     paramException.printStackTrace();
/*  46 */     String str1 = "Critical Error";
/*     */     JFrame localJFrame;
/*  47 */     (
/*  48 */       localJFrame = new JFrame(str1))
/*  48 */       .setUndecorated(true);
/*     */ 
/*  50 */     localJFrame.setVisible(true);
/*     */ 
/*  52 */     Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/*  53 */     String str2 = "";
/*     */ 
/*  55 */     if ((paramException.getMessage() != null) && (paramException.getMessage().contains("Database lock acquisition"))) {
/*  56 */       str2 = str2 + "\n\nIMPORTANT NOTE: this crash happens when there is still an instance of the game running\ncheck your process manager for \"javaw.exe\" and terminate it, or restart your computer to solve this problem.";
/*     */     }
/*     */ 
/*  62 */     if (str2.length() == 0) {
/*  63 */       str2 = "To help to fix this error, \nplease send your /logs directory to schema@star-made.org";
/*     */     }
/*     */ 
/*  67 */     localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/*  68 */     switch (JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage() + "\n\n " + str2 + "\n\n", str1, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/*     */     {
/*     */     case 0:
/*  75 */       break;
/*     */     case 1:
/*  77 */       System.err.println("[GLFrame] Error Message: " + paramException.getMessage());
/*     */       try
/*     */       {
/*  84 */         CrashReporter.a();
/*     */       }
/*     */       catch (IOException localIOException) {
/*  87 */         localIOException.printStackTrace();
/*     */       }
/*     */ 
/*  88 */       System.exit(0);
/*     */     case 2:
/*     */     }
/*     */ 
/*  93 */     localJFrame.dispose();
/*     */   }
/*     */   public static void a(Exception paramException, boolean paramBoolean) {
/*  96 */     Object[] arrayOfObject = { "Ok", "Exit" };
/*  97 */     paramException.printStackTrace();
/*  98 */     String str = "Error";
/*     */     JFrame localJFrame;
/*  99 */     (
/* 100 */       localJFrame = new JFrame(str))
/* 100 */       .setUndecorated(true);
/*     */ 
/* 102 */     localJFrame.setVisible(true);
/*     */ 
/* 104 */     Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/* 105 */     localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/* 106 */     switch (JOptionPane.showOptionDialog(localJFrame, (paramBoolean ? paramException.getClass().getSimpleName() : "") + paramException.getMessage(), str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/*     */     {
/*     */     case 0:
/* 111 */       break;
/*     */     case 1:
/* 113 */       System.err.println("[GLFrame] Error Message: " + paramException.getMessage());
/*     */       try
/*     */       {
/* 120 */         CrashReporter.a();
/*     */       }
/*     */       catch (IOException localIOException) {
/* 123 */         localIOException.printStackTrace();
/*     */       }
/*     */ 
/* 124 */       System.exit(0);
/*     */     case 2:
/*     */     }
/*     */ 
/* 129 */     localJFrame.dispose();
/*     */   }
/*     */ 
/*     */   public final void a(Float paramFloat)
/*     */   {
/*  96 */     this.jdField_a_of_type_Float = paramFloat.floatValue();
/*     */   }
/*     */ 
/*     */   public static UnicodeFont a()
/*     */   {
/*  33 */     if (jdField_b_of_type_OrgNewdawnSlickUnicodeFont == null) {
/*  34 */       Font localFont = new Font("Arial", 1, 12);
/*  35 */       (
/*  36 */         d.jdField_b_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont(localFont))
/*  36 */         .getEffects().add(new OutlineEffect(4, Color.black));
/*  37 */       jdField_b_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
/*  38 */       jdField_b_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
/*     */       try {
/*  40 */         jdField_b_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/*  43 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  45 */     return jdField_b_of_type_OrgNewdawnSlickUnicodeFont;
/*     */   }
/*     */ 
/*     */   public static UnicodeFont b() {
/*  49 */     if (c == null) {
/*  50 */       Font localFont = new Font("Arial", 1, 12);
/*  51 */       (
/*  52 */         d.c = new UnicodeFont(localFont))
/*  52 */         .getEffects().add(new OutlineEffect(4, Color.black));
/*  53 */       c.getEffects().add(new ColorEffect(Color.white));
/*  54 */       c.addAsciiGlyphs();
/*     */       try {
/*  56 */         c.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/*  59 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  61 */     return c;
/*     */   }
/*     */ 
/*     */   public static UnicodeFont c() {
/*  65 */     if (m == null) {
/*  66 */       Font localFont = new Font("Arial", 1, 16);
/*  67 */       (
/*  68 */         d.m = new UnicodeFont(localFont))
/*  68 */         .getEffects().add(new OutlineEffect(4, Color.black));
/*  69 */       m.getEffects().add(new ColorEffect(Color.white));
/*  70 */       m.addAsciiGlyphs();
/*     */       try {
/*  72 */         m.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/*  75 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  77 */     return m;
/*     */   }
/*     */   public static UnicodeFont d() {
/*  80 */     if (d == null) {
/*  81 */       Font localFont = new Font("Arial", 1, 18);
/*  82 */       (
/*  83 */         d.d = new UnicodeFont(localFont))
/*  83 */         .getEffects().add(new OutlineEffect(4, Color.black));
/*  84 */       d.getEffects().add(new ColorEffect(Color.white));
/*  85 */       d.addAsciiGlyphs();
/*     */       try {
/*  87 */         d.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/*  90 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  92 */     return d;
/*     */   }
/*     */   public static UnicodeFont e() {
/*  95 */     if (jdField_a_of_type_OrgNewdawnSlickUnicodeFont == null) {
/*  96 */       Font localFont = new Font("Arial", 1, 24);
/*  97 */       (
/*  98 */         d.jdField_a_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont(localFont))
/*  98 */         .getEffects().add(new OutlineEffect(4, Color.black));
/*  99 */       jdField_a_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
/* 100 */       jdField_a_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
/*     */       try {
/* 102 */         jdField_a_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 105 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 107 */     return jdField_a_of_type_OrgNewdawnSlickUnicodeFont;
/*     */   }
/*     */   public static UnicodeFont f() {
/* 110 */     if (n == null) {
/* 111 */       Font localFont = new Font("Arial", 1, 32);
/* 112 */       (
/* 113 */         d.n = new UnicodeFont(localFont))
/* 113 */         .getEffects().add(new OutlineEffect(4, Color.black));
/* 114 */       n.getEffects().add(new ColorEffect(Color.white));
/* 115 */       n.addAsciiGlyphs();
/*     */       try {
/* 117 */         n.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 120 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 122 */     return n;
/*     */   }
/*     */   public static UnicodeFont g() {
/* 125 */     if (h == null) {
/* 126 */       Font localFont = new Font("Arial", 1, 15);
/* 127 */       (
/* 128 */         d.h = new UnicodeFont(localFont))
/* 128 */         .getEffects().add(new OutlineEffect(4, Color.black));
/* 129 */       h.getEffects().add(new ColorEffect(Color.green.darker()));
/* 130 */       h.addAsciiGlyphs();
/*     */       try {
/* 132 */         h.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 135 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 137 */     return h;
/*     */   }
/*     */   public static UnicodeFont h() {
/* 140 */     if (k == null) {
/* 141 */       Font localFont = new Font("Arial", 1, 14);
/* 142 */       (
/* 143 */         d.k = new UnicodeFont(localFont))
/* 143 */         .getEffects().add(new OutlineEffect(4, Color.black));
/* 144 */       k.getEffects().add(new ColorEffect(Color.white));
/* 145 */       k.addAsciiGlyphs();
/*     */       try {
/* 147 */         k.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 150 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 152 */     return k;
/*     */   }
/*     */ 
/*     */   public static UnicodeFont i() {
/* 156 */     if (f == null)
/*     */     {
/*     */       try
/*     */       {
/* 166 */         Font localFont = Font.createFont(0, new File("data//font/dotricebold.ttf"))
/* 166 */           .deriveFont(14.0F);
/*     */ 
/* 168 */         (
/* 169 */           d.f = new UnicodeFont(localFont))
/* 169 */           .getEffects().add(new OutlineEffect(4, Color.black));
/*     */ 
/* 171 */         f.getEffects().add(new ColorEffect(Color.white));
/*     */ 
/* 173 */         f.addAsciiGlyphs();
/*     */ 
/* 175 */         f.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException)
/*     */       {
/* 182 */         localSlickException.printStackTrace();
/*     */       }
/*     */       catch (FontFormatException localFontFormatException)
/*     */       {
/* 182 */         localFontFormatException.printStackTrace();
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 182 */         localIOException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 185 */     return f;
/*     */   }
/*     */ 
/*     */   public static UnicodeFont j() {
/* 189 */     if (e == null)
/*     */     {
/*     */       try
/*     */       {
/* 199 */         Font localFont = Font.createFont(0, new File("data//font/dotricebold.ttf"))
/* 199 */           .deriveFont(20.0F);
/*     */ 
/* 201 */         (
/* 202 */           d.e = new UnicodeFont(localFont))
/* 202 */           .getEffects().add(new OutlineEffect(4, Color.black));
/*     */ 
/* 204 */         e.getEffects().add(new ColorEffect(Color.white));
/*     */ 
/* 206 */         e.addAsciiGlyphs();
/*     */ 
/* 208 */         e.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException)
/*     */       {
/* 215 */         localSlickException.printStackTrace();
/*     */       }
/*     */       catch (FontFormatException localFontFormatException)
/*     */       {
/* 215 */         localFontFormatException.printStackTrace();
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 215 */         localIOException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 218 */     return e;
/*     */   }
/*     */   public static UnicodeFont k() {
/* 221 */     if (g == null) {
/* 222 */       Font localFont = new Font("Arial", 0, 11);
/* 223 */       (
/* 224 */         d.g = new UnicodeFont(localFont))
/* 224 */         .getEffects().add(new OutlineEffect(2, Color.black));
/* 225 */       g.getEffects().add(new ColorEffect(Color.white));
/* 226 */       g.addAsciiGlyphs();
/*     */       try {
/* 228 */         g.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 231 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 233 */     return g;
/*     */   }
/*     */   public static UnicodeFont l() {
/* 236 */     if (l == null) {
/* 237 */       Font localFont = new Font("Arial", 0, 12);
/* 238 */       (
/* 239 */         d.l = new UnicodeFont(localFont))
/* 239 */         .getEffects().add(new ColorEffect(Color.white));
/* 240 */       l.addAsciiGlyphs();
/*     */       try {
/* 242 */         l.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 245 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 247 */     return l;
/*     */   }
/*     */   public static UnicodeFont m() {
/* 250 */     if (o == null) {
/* 251 */       Font localFont = new Font("Arial", 0, 12);
/* 252 */       (
/* 253 */         d.o = new UnicodeFont(localFont))
/* 253 */         .getEffects().add(new ColorEffect(Color.white));
/* 254 */       o.addAsciiGlyphs();
/*     */       try {
/* 256 */         o.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 259 */         localSlickException.printStackTrace();
/*     */       }
/*     */ 
/* 260 */       o.setDisplayListCaching(false);
/*     */     }
/* 262 */     return o;
/*     */   }
/*     */   public static UnicodeFont n() {
/* 265 */     if (i == null) {
/* 266 */       Font localFont = new Font("Arial", 0, 13);
/* 267 */       (
/* 268 */         d.i = new UnicodeFont(localFont))
/* 268 */         .getEffects().add(new OutlineEffect(4, Color.black));
/* 269 */       i.getEffects().add(new ColorEffect(Color.white));
/* 270 */       i.addAsciiGlyphs();
/*     */       try {
/* 272 */         i.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 275 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 277 */     return i;
/*     */   }
/*     */   public static UnicodeFont o() {
/* 280 */     if (j == null) {
/* 281 */       Font localFont = new Font("Arial", 0, 15);
/* 282 */       (
/* 283 */         d.j = new UnicodeFont(localFont))
/* 283 */         .getEffects().add(new OutlineEffect(4, Color.black));
/* 284 */       j.getEffects().add(new ColorEffect(Color.white));
/* 285 */       j.addAsciiGlyphs();
/*     */       try {
/* 287 */         j.loadGlyphs();
/*     */       }
/*     */       catch (SlickException localSlickException) {
/* 290 */         localSlickException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 292 */     return j;
/*     */   }
/*     */ 
/*     */   public static void c()
/*     */   {
/* 498 */     for (zj localzj : jdField_a_of_type_JavaUtilSet)
/*     */       try {
/* 500 */         if (localzj != null) {
/* 501 */           localzj.a.clear();
/* 502 */           localzj.a();
/*     */         }
/*     */       }
/*     */       catch (ResourceException localResourceException) {
/* 506 */         localResourceException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private static ByteBuffer a(File paramFile)
/*     */   {
/* 349 */     paramFile = new FileInputStream(paramFile);
/*     */     try
/*     */     {
/*     */       Object localObject1;
/* 353 */       ByteBuffer localByteBuffer = GlUtil.a((int)(
/* 353 */         localObject1 = paramFile.getChannel())
/* 353 */         .size(), 0);
/*     */ 
/* 355 */       ((FileChannel)localObject1).read(localByteBuffer);
/* 356 */       localByteBuffer.rewind();
/* 357 */       return localByteBuffer;
/*     */     }
/*     */     finally {
/* 360 */       paramFile.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(File paramFile, zA paramzA)
/*     */   {
/* 370 */     ByteBuffer localByteBuffer = a(paramFile);
/*     */     zw localzw1;
/* 374 */     if (((
/* 374 */       localzw1 = new zw(localByteBuffer)).k & 
/* 374 */       0x200000) == 0) {
/* 375 */       throw new IOException("Not a volume texture: " + paramFile);
/*     */     }
/* 377 */     if (localzw1.jdField_a_of_type_JavaLangString != null) {
/* 378 */       throw new IOException("Compression not supported for 3D textures: " + localzw1.jdField_a_of_type_JavaLangString);
/*     */     }
/* 380 */     paramFile = localzw1.c;
/* 381 */     int i1 = localzw1.b;
/* 382 */     int i2 = localzw1.d;
/* 383 */     int i3 = (localzw1.f & 0x1) == 0 ? 6407 : 6408;
/*     */     zw localzw2;
/* 385 */     if (localzw2.j == -16777216) { if (!GLContext.getCapabilities().GL_EXT_bgra) throw new IOException("BGRA format not supported."); if (localzw2.j != -16777216); } throw ((localzw2.g == 255) && (localzw2.h == 0) && (localzw2.i == 0) ? 8194 : (localzw2.g == 16711680) && (localzw2.h == 65280) && (localzw2.i == 255) ? 32993 : (localzw2.f & 0x1) == 0 ? 32992 : ((localzw2 = localzw1).g == 255) && (localzw2.h == 65280) && (localzw2.i == 16711680) ? 6408 : (localzw2.f & 0x1) == 0 ? 6407 : new IOException("Unknown format: " + localzw2.g + "/" + localzw2.h + "/" + localzw2.i + "/" + localzw2.j));
/*     */   }
/*     */ 
/*     */   public static void a(File paramFile1, File paramFile2)
/*     */   {
/*  67 */     paramFile1 = new FileInputStream(paramFile1);
/*  68 */     paramFile2 = new FileOutputStream(paramFile2);
/*     */ 
/*  70 */     byte[] arrayOfByte = new byte[1024];
/*     */     int i1;
/*  72 */     while ((i1 = paramFile1.read(arrayOfByte)) > 0) {
/*  73 */       paramFile2.write(arrayOfByte, 0, i1);
/*     */     }
/*  75 */     paramFile1.close();
/*  76 */     paramFile2.close();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     d
 * JD-Core Version:    0.6.2
 */