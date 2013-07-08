import com.bulletphysics.linearmath.Transform;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.schema.common.FastMath;
import org.schema.game.common.crashreporter.CrashReporter;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_29
  implements class_1073
{
  private static class_30 jdField_field_19_of_type_Class_30;
  private static class_30 jdField_field_20_of_type_Class_30;
  private static FileHandler jdField_field_19_of_type_JavaUtilLoggingFileHandler;
  private static Logger jdField_field_19_of_type_JavaUtilLoggingLogger;
  private static ImageIcon[] jdField_field_19_of_type_ArrayOfJavaxSwingImageIcon;
  public float field_19;
  private static UnicodeFont jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont;
  private static UnicodeFont jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont;
  private static UnicodeFont field_21;
  private static UnicodeFont field_22;
  private static UnicodeFont field_23;
  private static UnicodeFont field_24;
  private static UnicodeFont field_25;
  private static UnicodeFont field_26;
  private static UnicodeFont field_27;
  private static UnicodeFont field_28;
  private static UnicodeFont field_29;
  private static UnicodeFont field_30;
  private static UnicodeFont field_31;
  private static UnicodeFont field_32;
  private static UnicodeFont field_33;
  public static class_68[] field_19;
  
  public static void a()
  {
    if (jdField_field_20_of_type_Class_30 != null) {
      jdField_field_20_of_type_Class_30.close();
    }
    if (jdField_field_19_of_type_Class_30 != null) {
      jdField_field_19_of_type_Class_30.close();
    }
    if (jdField_field_19_of_type_JavaUtilLoggingFileHandler != null)
    {
      if (jdField_field_19_of_type_JavaUtilLoggingLogger != null) {
        jdField_field_19_of_type_JavaUtilLoggingLogger.removeHandler(jdField_field_19_of_type_JavaUtilLoggingFileHandler);
      }
      jdField_field_19_of_type_JavaUtilLoggingFileHandler.close();
    }
  }
  
  public static void b()
  {
    
    LogManager localLogManager;
    (localLogManager = LogManager.getLogManager()).reset();
    if (!(localObject = new File("./logs/")).exists()) {
      ((File)localObject).mkdirs();
    }
    (class_29.jdField_field_19_of_type_JavaUtilLoggingFileHandler = new FileHandler("./logs/log.txt", 4194304, 20)).setFormatter(new class_31());
    Object localObject = System.out;
    PrintStream localPrintStream = System.err;
    (class_29.jdField_field_19_of_type_JavaUtilLoggingLogger = Logger.getLogger("stdout")).addHandler(jdField_field_19_of_type_JavaUtilLoggingFileHandler);
    jdField_field_20_of_type_Class_30 = new class_30((PrintStream)localObject, jdField_field_19_of_type_JavaUtilLoggingLogger, class_28.field_442, "OUT");
    System.setOut(new PrintStream(jdField_field_20_of_type_Class_30, true));
    localLogManager.addLogger(jdField_field_19_of_type_JavaUtilLoggingLogger);
    (class_29.jdField_field_19_of_type_JavaUtilLoggingLogger = Logger.getLogger("stderr")).addHandler(jdField_field_19_of_type_JavaUtilLoggingFileHandler);
    jdField_field_19_of_type_Class_30 = new class_30(localPrintStream, jdField_field_19_of_type_JavaUtilLoggingLogger, class_28.field_443, "ERR");
    System.setErr(new PrintStream(jdField_field_19_of_type_Class_30, true));
    localLogManager.addLogger(jdField_field_19_of_type_JavaUtilLoggingLogger);
  }
  
  public static void a1(Transform paramTransform1, Transform paramTransform2)
  {
    float f1 = paramTransform1.basis.m00;
    float f2 = paramTransform1.basis.m01;
    float f3 = paramTransform1.basis.m02;
    float f4 = paramTransform1.origin.field_615;
    float f5 = paramTransform1.basis.m10;
    float f6 = paramTransform1.basis.m11;
    float f7 = paramTransform1.basis.m12;
    float f8 = paramTransform1.origin.field_616;
    float f9 = paramTransform1.basis.m20;
    float f10 = paramTransform1.basis.m21;
    float f11 = paramTransform1.basis.m22;
    float f12 = paramTransform1.origin.field_617;
    float f13 = paramTransform2.basis.m00;
    float f14 = paramTransform2.basis.m01;
    float f15 = paramTransform2.basis.m02;
    float f16 = paramTransform2.origin.field_615;
    float f17 = paramTransform2.basis.m10;
    float f18 = paramTransform2.basis.m11;
    float f19 = paramTransform2.basis.m12;
    float f20 = paramTransform2.origin.field_616;
    float f21 = paramTransform2.basis.m20;
    float f22 = paramTransform2.basis.m21;
    float f23 = paramTransform2.basis.m22;
    paramTransform2 = paramTransform2.origin.field_617;
    float f24 = f1 * f13 + f2 * f17 + f3 * f21 + f4 * 0.0F;
    float f25 = f1 * f14 + f2 * f18 + f3 * f22 + f4 * 0.0F;
    float f26 = f1 * f15 + f2 * f19 + f3 * f23 + f4 * 0.0F;
    f1 = f1 * f16 + f2 * f20 + f3 * paramTransform2 + f4;
    f2 = f5 * f13 + f6 * f17 + f7 * f21 + f8 * 0.0F;
    f3 = f5 * f14 + f6 * f18 + f7 * f22 + f8 * 0.0F;
    f4 = f5 * f15 + f6 * f19 + f7 * f23 + f8 * 0.0F;
    f5 = f5 * f16 + f6 * f20 + f7 * paramTransform2 + f8;
    f6 = f9 * f13 + f10 * f17 + f11 * f21 + f12 * 0.0F;
    f7 = f9 * f14 + f10 * f18 + f11 * f22 + f12 * 0.0F;
    f8 = f9 * f15 + f10 * f19 + f11 * f23 + f12 * 0.0F;
    paramTransform2 = f9 * f16 + f10 * f20 + f11 * paramTransform2 + f12;
    paramTransform1.basis.m00 = f24;
    paramTransform1.basis.m01 = f25;
    paramTransform1.basis.m02 = f26;
    paramTransform1.origin.field_615 = f1;
    paramTransform1.basis.m10 = f2;
    paramTransform1.basis.m11 = f3;
    paramTransform1.basis.m12 = f4;
    paramTransform1.origin.field_616 = f5;
    paramTransform1.basis.m20 = f6;
    paramTransform1.basis.m21 = f7;
    paramTransform1.basis.m22 = f8;
    paramTransform1.origin.field_617 = paramTransform2;
  }
  
  public static Quat4f a2(float paramFloat, Vector3f paramVector3f, Quat4f paramQuat4f)
  {
    if ((paramVector3f.field_615 == 0.0F) && (paramVector3f.field_616 == 0.0F) && (paramVector3f.field_617 == 0.0F))
    {
      paramQuat4f.set(0.0F, 0.0F, 0.0F, 1.0F);
    }
    else
    {
      float f = FastMath.h(paramFloat = 0.5F * paramFloat);
      paramQuat4f.field_599 = FastMath.d(paramFloat);
      paramQuat4f.field_596 = (f * paramVector3f.field_615);
      paramQuat4f.field_597 = (f * paramVector3f.field_616);
      paramQuat4f.field_598 = (f * paramVector3f.field_617);
    }
    return paramQuat4f;
  }
  
  public static Vector3f a3(Quat4f paramQuat4f, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    float f1 = paramQuat4f.field_596;
    float f2 = paramQuat4f.field_597;
    float f3 = paramQuat4f.field_598;
    paramQuat4f = paramQuat4f.field_599;
    if (paramVector3f2 == null) {
      paramVector3f2 = new Vector3f();
    }
    if ((paramVector3f1.field_615 == 0.0F) && (paramVector3f1.field_616 == 0.0F) && (paramVector3f1.field_617 == 0.0F))
    {
      paramVector3f2.set(0.0F, 0.0F, 0.0F);
    }
    else
    {
      float f4 = paramVector3f1.field_615;
      float f5 = paramVector3f1.field_616;
      paramVector3f1 = paramVector3f1.field_617;
      paramVector3f2.field_615 = (paramQuat4f * paramQuat4f * f4 + f2 * 2.0F * paramQuat4f * paramVector3f1 - f3 * 2.0F * paramQuat4f * f5 + f1 * f1 * f4 + f2 * 2.0F * f1 * f5 + f3 * 2.0F * f1 * paramVector3f1 - f3 * f3 * f4 - f2 * f2 * f4);
      paramVector3f2.field_616 = (f1 * 2.0F * f2 * f4 + f2 * f2 * f5 + f3 * 2.0F * f2 * paramVector3f1 + paramQuat4f * 2.0F * f3 * f4 - f3 * f3 * f5 + paramQuat4f * paramQuat4f * f5 - f1 * 2.0F * paramQuat4f * paramVector3f1 - f1 * f1 * f5);
      paramVector3f2.field_617 = (f1 * 2.0F * f3 * f4 + f2 * 2.0F * f3 * f5 + f3 * f3 * paramVector3f1 - paramQuat4f * 2.0F * f2 * f4 - f2 * f2 * paramVector3f1 + paramQuat4f * 2.0F * f1 * f5 - f1 * f1 * paramVector3f1 + paramQuat4f * paramQuat4f * paramVector3f1);
    }
    return paramVector3f2;
  }
  
  public static Quat4f a4(Quat4f paramQuat4f1, Quat4f paramQuat4f2, float paramFloat, Quat4f paramQuat4f3)
  {
    if ((paramQuat4f1.field_596 == paramQuat4f2.field_596) && (paramQuat4f1.field_597 == paramQuat4f2.field_597) && (paramQuat4f1.field_598 == paramQuat4f2.field_598) && (paramQuat4f1.field_599 == paramQuat4f2.field_599))
    {
      paramQuat4f3.set(paramQuat4f1);
      return paramQuat4f3;
    }
    float f1;
    if ((f1 = paramQuat4f1.field_596 * paramQuat4f2.field_596 + paramQuat4f1.field_597 * paramQuat4f2.field_597 + paramQuat4f1.field_598 * paramQuat4f2.field_598 + paramQuat4f1.field_599 * paramQuat4f2.field_599) < 0.0F)
    {
      paramQuat4f2.field_596 = (-paramQuat4f2.field_596);
      paramQuat4f2.field_597 = (-paramQuat4f2.field_597);
      paramQuat4f2.field_598 = (-paramQuat4f2.field_598);
      paramQuat4f2.field_599 = (-paramQuat4f2.field_599);
      f1 = -f1;
    }
    float f2 = 1.0F - paramFloat;
    float f3 = paramFloat;
    if (1.0F - f1 > 0.1F)
    {
      f1 = FastMath.b(f1);
      f3 = 1.0F / FastMath.h(f1);
      f2 = FastMath.h(f2 * f1) * f3;
      f3 = FastMath.h(paramFloat * f1) * f3;
    }
    paramQuat4f3.field_596 = (f2 * paramQuat4f1.field_596 + f3 * paramQuat4f2.field_596);
    paramQuat4f3.field_597 = (f2 * paramQuat4f1.field_597 + f3 * paramQuat4f2.field_597);
    paramQuat4f3.field_598 = (f2 * paramQuat4f1.field_598 + f3 * paramQuat4f2.field_598);
    paramQuat4f3.field_599 = (f2 * paramQuat4f1.field_599 + f3 * paramQuat4f2.field_599);
    return paramQuat4f3;
  }
  
  public static void a5(Matrix3f paramMatrix3f, Quat4f paramQuat4f)
  {
    if ((f = paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22) > 0.0F)
    {
      f = FastMath.l(f + 1.0F) * 2.0F;
      paramQuat4f.field_599 = (0.25F * f);
      paramQuat4f.field_596 = ((paramMatrix3f.m21 - paramMatrix3f.m12) / f);
      paramQuat4f.field_597 = ((paramMatrix3f.m02 - paramMatrix3f.m20) / f);
      paramQuat4f.field_598 = ((paramMatrix3f.m10 - paramMatrix3f.m01) / f);
      return;
    }
    if ((paramMatrix3f.m00 > paramMatrix3f.m11) && (paramMatrix3f.m00 > paramMatrix3f.m22))
    {
      f = FastMath.l(1.0F + paramMatrix3f.m00 - paramMatrix3f.m11 - paramMatrix3f.m22) * 2.0F;
      paramQuat4f.field_599 = ((paramMatrix3f.m21 - paramMatrix3f.m12) / f);
      paramQuat4f.field_596 = (0.25F * f);
      paramQuat4f.field_597 = ((paramMatrix3f.m01 + paramMatrix3f.m10) / f);
      paramQuat4f.field_598 = ((paramMatrix3f.m02 + paramMatrix3f.m20) / f);
      return;
    }
    if (paramMatrix3f.m11 > paramMatrix3f.m22)
    {
      f = FastMath.l(1.0F + paramMatrix3f.m11 - paramMatrix3f.m00 - paramMatrix3f.m22) * 2.0F;
      paramQuat4f.field_599 = ((paramMatrix3f.m02 - paramMatrix3f.m20) / f);
      paramQuat4f.field_596 = ((paramMatrix3f.m01 + paramMatrix3f.m10) / f);
      paramQuat4f.field_597 = (0.25F * f);
      paramQuat4f.field_598 = ((paramMatrix3f.m12 + paramMatrix3f.m21) / f);
      return;
    }
    float f = FastMath.l(1.0F + paramMatrix3f.m22 - paramMatrix3f.m00 - paramMatrix3f.m11) * 2.0F;
    paramQuat4f.field_599 = ((paramMatrix3f.m10 - paramMatrix3f.m01) / f);
    paramQuat4f.field_596 = ((paramMatrix3f.m02 + paramMatrix3f.m20) / f);
    paramQuat4f.field_597 = ((paramMatrix3f.m12 + paramMatrix3f.m21) / f);
    paramQuat4f.field_598 = (0.25F * f);
  }
  
  public static void a6(int paramInt1, int paramInt2, int paramInt3, class_791 paramclass_791, int paramInt4, Random paramRandom)
  {
    if (paramInt1 == 8) {
      if (((paramInt2 == 8 ? 1 : 0) & (paramInt3 == 5 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1067);
        paramclass_791.a200(paramInt4, class_810.field_1073);
        return;
      }
    }
    if (paramInt1 == 100) {
      if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1067);
        paramclass_791.a200(paramInt4, class_810.field_1073);
        return;
      }
    }
    if (paramInt1 == 200) {
      if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1067);
        paramclass_791.a200(paramInt4, class_810.field_1074);
        return;
      }
    }
    if (paramInt1 == 300) {
      if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1067);
        paramclass_791.a200(paramInt4, class_810.field_1076);
        return;
      }
    }
    if (paramInt1 == 400) {
      if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1067);
        paramclass_791.a200(paramInt4, class_810.jdField_field_1072_of_type_Class_810);
        return;
      }
    }
    if (paramInt1 == 500) {
      if (((paramInt2 == 100 ? 1 : 0) & (paramInt3 == 100 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1067);
        paramclass_791.a200(paramInt4, class_810.field_1075);
        return;
      }
    }
    if (paramInt1 == 8) {
      if (((paramInt2 == 5 ? 1 : 0) & (paramInt3 == 8 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1065);
        paramclass_791.a201(paramInt4, class_780.field_1037);
        return;
      }
    }
    if (paramInt1 == 8) {
      if (((paramInt2 == 5 ? 1 : 0) & (paramInt3 == 5 ? 1 : 0)) != 0)
      {
        paramclass_791.a199(paramInt4, class_808.field_1065);
        paramclass_791.a201(paramInt4, class_780.field_1039);
        return;
      }
    }
    if (class_670.field_139.a3(paramInt1, paramInt2, paramInt3))
    {
      paramclass_791.a199(paramInt4, class_808.field_1068);
      return;
    }
    if ((paramInt1 == class_670.field_139.field_475) && (paramInt2 == class_670.field_139.field_476 - 1) && (paramInt3 == class_670.field_139.field_477))
    {
      paramclass_791.a199(paramInt4, class_808.field_1067);
      a7(paramclass_791, paramInt4, paramRandom);
      return;
    }
    if ((paramInt1 = paramRandom.nextInt(250)) < 5)
    {
      paramclass_791.a199(paramInt4, class_808.field_1067);
      a7(paramclass_791, paramInt4, paramRandom);
      return;
    }
    if (paramInt1 < 240)
    {
      paramclass_791.a199(paramInt4, class_808.field_1066);
      return;
    }
    paramclass_791.a199(paramInt4, class_808.field_1065);
    if (paramRandom.nextInt(5) == 0)
    {
      paramclass_791.a201(paramInt4, class_780.field_1039);
      return;
    }
    paramclass_791.a201(paramInt4, class_780.field_1037);
  }
  
  private static void a7(class_791 paramclass_791, int paramInt, Random paramRandom)
  {
    for (int i = paramRandom.nextInt(class_810.values().length); !class_810.values()[i].jdField_field_1072_of_type_Boolean; i = paramRandom.nextInt(class_810.values().length)) {}
    paramclass_791.a200(paramInt, class_810.values()[i]);
  }
  
  public static ImageIcon a8(int paramInt)
  {
    if (jdField_field_19_of_type_ArrayOfJavaxSwingImageIcon == null) {
      try
      {
        jdField_field_19_of_type_ArrayOfJavaxSwingImageIcon = new ImageIcon[768];
        (localObject = new BufferedImage[3])[0] = ImageIO.read(new File("./data/textures/block/t000.png"));
        localObject[1] = ImageIO.read(new File("./data/textures/block/t001.png"));
        localObject[2] = ImageIO.read(new File("./data/textures/block/t002.png"));
        for (int i = 0; i < jdField_field_19_of_type_ArrayOfJavaxSwingImageIcon.length; i++)
        {
          int j = i % 256 % 16;
          int k = i % 256 / 16;
          BufferedImage localBufferedImage = localObject[(i / 256)].getSubimage(j << 6, k << 6, 64, 64);
          jdField_field_19_of_type_ArrayOfJavaxSwingImageIcon[i] = new ImageIcon(localBufferedImage);
        }
      }
      catch (IOException localIOException)
      {
        Object localObject;
        (localObject = localIOException).printStackTrace();
        a12((Exception)localObject);
      }
    }
    return jdField_field_19_of_type_ArrayOfJavaxSwingImageIcon[paramInt];
  }
  
  public static void a9(String paramString1, String paramString2, String paramString3, String paramString4, File paramFile)
  {
    if ((paramString1 != null) && (paramString4 != null) && (paramFile != null))
    {
      StringBuffer localStringBuffer = new StringBuffer("ftp://");
      if ((paramString2 != null) && (paramString3 != null))
      {
        localStringBuffer.append(paramString2);
        localStringBuffer.append(':');
        localStringBuffer.append(paramString3);
        localStringBuffer.append('@');
      }
      localStringBuffer.append(paramString1);
      localStringBuffer.append('/');
      localStringBuffer.append(paramString4);
      localStringBuffer.append(";type=i");
      paramString1 = null;
      paramString2 = null;
      try
      {
        paramString3 = new URL(localStringBuffer.toString()).openConnection();
        paramString2 = new BufferedOutputStream(paramString3.getOutputStream());
        paramString1 = new BufferedInputStream(new FileInputStream(paramFile));
        while ((paramString3 = paramString1.read()) != -1) {
          paramString2.write(paramString3);
        }
        try
        {
          paramString1.close();
        }
        catch (IOException localIOException1)
        {
          localIOException1;
        }
        try
        {
          paramString2.close();
          return;
        }
        catch (IOException localIOException2)
        {
          localIOException2.printStackTrace();
          return;
        }
        System.out.println("Input not available.");
      }
      finally
      {
        if (paramString1 != null) {
          try
          {
            paramString1.close();
          }
          catch (IOException localIOException3)
          {
            localIOException3;
          }
        }
        if (paramString2 != null) {
          try
          {
            paramString2.close();
          }
          catch (IOException localIOException4)
          {
            localIOException4;
          }
        }
      }
    }
  }
  
  private static void a10(String paramString1, String paramString2, ZipOutputStream paramZipOutputStream, String paramString3)
  {
    paramString1.replace('\\', '/');
    paramString2.replace('\\', '/');
    File localFile;
    String[] arrayOfString = (localFile = new File(paramString2)).list();
    try
    {
      for (int i = 0; i < arrayOfString.length; i++) {
        if ((paramString3 == null) || (!arrayOfString[i].startsWith(paramString3))) {
          b1(paramString1 + "/" + localFile.getName(), paramString2 + "/" + arrayOfString[i], paramZipOutputStream, paramString3);
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private static void b1(String paramString1, String paramString2, ZipOutputStream paramZipOutputStream, String paramString3)
  {
    paramString1.replace('\\', '/');
    paramString2.replace('\\', '/');
    File localFile;
    if ((localFile = new File(paramString2)).isDirectory())
    {
      a10(paramString1, paramString2, paramZipOutputStream, paramString3);
      return;
    }
    paramString3 = new byte[1024];
    try
    {
      paramString2 = new FileInputStream(paramString2);
      if ((paramString1 = paramString1 + "/" + localFile.getName()).startsWith("/")) {
        paramString1 = paramString1.substring(1);
      }
      System.err.println("PUTTING: " + paramString1);
      paramString1 = new ZipEntry(paramString1);
      paramZipOutputStream.putNextEntry(paramString1);
      while ((paramString1 = paramString2.read(paramString3)) > 0) {
        paramZipOutputStream.write(paramString3, 0, paramString1);
      }
      paramString2.close();
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  public static void a11(String paramString1, String paramString2, String paramString3)
  {
    System.out.println("Zipping folder: " + paramString1 + " to " + paramString2 + " (Filter: " + paramString3 + ")");
    paramString2.replace('\\', '/');
    paramString1.replace('\\', '/');
    FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
    paramString2 = new ZipOutputStream(localFileOutputStream);
    a10("", paramString1, paramString2, paramString3);
    paramString2.flush();
    paramString2.close();
    localFileOutputStream.close();
  }
  
  public static void a12(Exception paramException)
  {
    Object[] arrayOfObject = { "Retry", "Exit" };
    paramException.printStackTrace();
    String str1 = "Critical Error";
    JFrame localJFrame;
    (localJFrame = new JFrame(str1)).setUndecorated(true);
    localJFrame.setVisible(true);
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
    String str2 = "";
    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("Database lock acquisition"))) {
      str2 = str2 + "\n\nIMPORTANT NOTE: this crash happens when there is still an instance of the game running\ncheck your process manager for \"javaw.exe\" and terminate it, or restart your computer to solve this problem.";
    }
    if (str2.length() == 0) {
      str2 = "To help to fix this error, \nplease send your /logs directory to schema@star-made.org";
    }
    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
    switch (JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage() + "\n\n " + str2 + "\n\n", str1, 1, 0, null, arrayOfObject, arrayOfObject[0]))
    {
    case 0: 
      break;
    case 1: 
      System.err.println("[GLFrame] Error Message: " + paramException.getMessage());
      try
      {
        CrashReporter.a();
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      System.exit(0);
    }
    localJFrame.dispose();
  }
  
  public static void a13(Exception paramException, boolean paramBoolean)
  {
    Object[] arrayOfObject = { "Ok", "Exit" };
    paramException.printStackTrace();
    String str = "Error";
    JFrame localJFrame;
    (localJFrame = new JFrame(str)).setUndecorated(true);
    localJFrame.setVisible(true);
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
    switch (JOptionPane.showOptionDialog(localJFrame, (paramBoolean ? paramException.getClass().getSimpleName() : "") + paramException.getMessage(), str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
    {
    case 0: 
      break;
    case 1: 
      System.err.println("[GLFrame] Error Message: " + paramException.getMessage());
      try
      {
        CrashReporter.a();
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      System.exit(0);
    }
    localJFrame.dispose();
  }
  
  public final void a14(Float paramFloat)
  {
    this.jdField_field_19_of_type_Float = paramFloat.floatValue();
  }
  
  public static UnicodeFont a16()
  {
    if (jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont == null)
    {
      Font localFont = new Font("Arial", 1, 12);
      (class_29.jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
      jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
      try
      {
        jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return jdField_field_20_of_type_OrgNewdawnSlickUnicodeFont;
  }
  
  public static UnicodeFont b2()
  {
    if (field_21 == null)
    {
      Font localFont = new Font("Arial", 1, 12);
      (class_29.field_21 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_21.getEffects().add(new ColorEffect(Color.white));
      field_21.addAsciiGlyphs();
      try
      {
        field_21.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_21;
  }
  
  public static UnicodeFont c()
  {
    if (field_31 == null)
    {
      Font localFont = new Font("Arial", 1, 16);
      (class_29.field_31 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_31.getEffects().add(new ColorEffect(Color.white));
      field_31.addAsciiGlyphs();
      try
      {
        field_31.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_31;
  }
  
  public static UnicodeFont d()
  {
    if (field_22 == null)
    {
      Font localFont = new Font("Arial", 1, 18);
      (class_29.field_22 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_22.getEffects().add(new ColorEffect(Color.white));
      field_22.addAsciiGlyphs();
      try
      {
        field_22.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_22;
  }
  
  public static UnicodeFont e()
  {
    if (jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont == null)
    {
      Font localFont = new Font("Arial", 1, 24);
      (class_29.jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
      jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
      try
      {
        jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return jdField_field_19_of_type_OrgNewdawnSlickUnicodeFont;
  }
  
  public static UnicodeFont f()
  {
    if (field_32 == null)
    {
      Font localFont = new Font("Arial", 1, 32);
      (class_29.field_32 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_32.getEffects().add(new ColorEffect(Color.white));
      field_32.addAsciiGlyphs();
      try
      {
        field_32.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_32;
  }
  
  public static UnicodeFont g()
  {
    if (field_26 == null)
    {
      Font localFont = new Font("Arial", 1, 15);
      (class_29.field_26 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_26.getEffects().add(new ColorEffect(Color.green.darker()));
      field_26.addAsciiGlyphs();
      try
      {
        field_26.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_26;
  }
  
  public static UnicodeFont h()
  {
    if (field_29 == null)
    {
      Font localFont = new Font("Arial", 1, 14);
      (class_29.field_29 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_29.getEffects().add(new ColorEffect(Color.white));
      field_29.addAsciiGlyphs();
      try
      {
        field_29.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_29;
  }
  
  public static UnicodeFont i()
  {
    if (field_24 == null) {
      try
      {
        Font localFont = Font.createFont(0, new File("data//font/dotricebold.ttf")).deriveFont(14.0F);
        (class_29.field_24 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
        field_24.getEffects().add(new ColorEffect(Color.white));
        field_24.addAsciiGlyphs();
        field_24.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
      catch (FontFormatException localFontFormatException)
      {
        localFontFormatException;
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
    }
    return field_24;
  }
  
  public static UnicodeFont j()
  {
    if (field_23 == null) {
      try
      {
        Font localFont = Font.createFont(0, new File("data//font/dotricebold.ttf")).deriveFont(20.0F);
        (class_29.field_23 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
        field_23.getEffects().add(new ColorEffect(Color.white));
        field_23.addAsciiGlyphs();
        field_23.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
      catch (FontFormatException localFontFormatException)
      {
        localFontFormatException;
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
    }
    return field_23;
  }
  
  public static UnicodeFont k()
  {
    if (field_25 == null)
    {
      Font localFont = new Font("Arial", 0, 11);
      (class_29.field_25 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(2, Color.black));
      field_25.getEffects().add(new ColorEffect(Color.white));
      field_25.addAsciiGlyphs();
      try
      {
        field_25.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_25;
  }
  
  public static UnicodeFont l()
  {
    if (field_30 == null)
    {
      Font localFont = new Font("Arial", 0, 12);
      (class_29.field_30 = new UnicodeFont(localFont)).getEffects().add(new ColorEffect(Color.white));
      field_30.addAsciiGlyphs();
      try
      {
        field_30.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_30;
  }
  
  public static UnicodeFont m()
  {
    if (field_33 == null)
    {
      Font localFont = new Font("Arial", 0, 12);
      (class_29.field_33 = new UnicodeFont(localFont)).getEffects().add(new ColorEffect(Color.white));
      field_33.addAsciiGlyphs();
      try
      {
        field_33.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
      field_33.setDisplayListCaching(false);
    }
    return field_33;
  }
  
  public static UnicodeFont n()
  {
    if (field_27 == null)
    {
      Font localFont = new Font("Arial", 0, 13);
      (class_29.field_27 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_27.getEffects().add(new ColorEffect(Color.white));
      field_27.addAsciiGlyphs();
      try
      {
        field_27.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_27;
  }
  
  public static UnicodeFont o()
  {
    if (field_28 == null)
    {
      Font localFont = new Font("Arial", 0, 15);
      (class_29.field_28 = new UnicodeFont(localFont)).getEffects().add(new OutlineEffect(4, Color.black));
      field_28.getEffects().add(new ColorEffect(Color.white));
      field_28.addAsciiGlyphs();
      try
      {
        field_28.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    return field_28;
  }
  
  private static ByteBuffer a17(File paramFile)
  {
    paramFile = new FileInputStream(paramFile);
    try
    {
      ByteBuffer localByteBuffer = GlUtil.a5((int)(localObject1 = paramFile.getChannel()).size(), 0);
      ((FileChannel)localObject1).read(localByteBuffer);
      localByteBuffer.rewind();
      Object localObject1 = localByteBuffer;
      return localObject1;
    }
    finally
    {
      paramFile.close();
    }
  }
  
  public static void a18(File paramFile, class_1391 paramclass_1391)
  {
    ByteBuffer localByteBuffer = a17(paramFile);
    class_1396 localclass_13961;
    if (((localclass_13961 = new class_1396(localByteBuffer)).field_1603 & 0x200000) == 0) {
      throw new IOException("Not a volume texture: " + paramFile);
    }
    if (localclass_13961.jdField_field_1593_of_type_JavaLangString != null) {
      throw new IOException("Compression not supported for 3D textures: " + localclass_13961.jdField_field_1593_of_type_JavaLangString);
    }
    paramFile = localclass_13961.field_1595;
    int i = localclass_13961.field_1594;
    int j = localclass_13961.field_1596;
    int k = (localclass_13961.field_1598 & 0x1) == 0 ? 6407 : 6408;
    class_1396 localclass_13962;
    if (localclass_13962.field_1602 == -16777216)
    {
      if (!GLContext.getCapabilities().GL_EXT_bgra) {
        throw new IOException("BGRA format not supported.");
      }
      if (localclass_13962.field_1602 != -16777216) {}
    }
    throw ((localclass_13962.field_1599 == 255) && (localclass_13962.field_1600 == 0) && (localclass_13962.field_1601 == 0) ? 8194 : (localclass_13962.field_1599 == 16711680) && (localclass_13962.field_1600 == 65280) && (localclass_13962.field_1601 == 255) ? 32993 : (localclass_13962.field_1598 & 0x1) == 0 ? 32992 : ((localclass_13962 = localclass_13961).field_1599 == 255) && (localclass_13962.field_1600 == 65280) && (localclass_13962.field_1601 == 16711680) ? 6408 : (localclass_13962.field_1598 & 0x1) == 0 ? 6407 : new IOException("Unknown format: " + localclass_13962.field_1599 + "/" + localclass_13962.field_1600 + "/" + localclass_13962.field_1601 + "/" + localclass_13962.field_1602));
  }
  
  public static void a19(File paramFile1, File paramFile2)
  {
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    byte[] arrayOfByte = new byte[1024];
    int i;
    while ((i = paramFile1.read(arrayOfByte)) > 0) {
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile1.close();
    paramFile2.close();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_29
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */