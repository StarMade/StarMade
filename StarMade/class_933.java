import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;
import org.schema.schine.graphicsengine.core.FinishedFrameException;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.ResourceException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.client.ClientControllerInterface;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.exception.DisconnectException;

public class class_933
{
  private static boolean jdField_field_1156_of_type_Boolean = false;
  private static boolean jdField_field_1157_of_type_Boolean = false;
  private boolean field_1158 = true;
  private long jdField_field_1156_of_type_Long;
  private class_941 jdField_field_1156_of_type_Class_941 = new class_941();
  private boolean field_1159;
  private static boolean field_1160;
  private class_923 jdField_field_1156_of_type_Class_923;
  private static DisplayMode jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode;
  public static ClientState field_1156;
  private String jdField_field_1156_of_type_JavaLangString;
  private boolean field_1161;
  private static String jdField_field_1157_of_type_JavaLangString = "";
  private static long jdField_field_1157_of_type_Long;
  
  public static int a()
  {
    return jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode.getHeight();
  }
  
  public static int b()
  {
    return jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode.getWidth();
  }
  
  public static boolean a1()
  {
    return field_1160;
  }
  
  public static boolean b1()
  {
    return jdField_field_1157_of_type_Boolean;
  }
  
  public static void a2(Exception paramException)
  {
    if (field_1160) {
      return;
    }
    if ((paramException != null) && ((paramException instanceof SocketException)) && (paramException.getMessage() != null) && (paramException.getMessage().contains("Connection reset by peer: socket write error")))
    {
      b2(paramException);
      return;
    }
    Object[] arrayOfObject = { "Retry", "Send Crash Report", "Exit" };
    paramException.printStackTrace();
    String str = "Critical Error";
    JFrame localJFrame;
    (localJFrame = new JFrame(str)).setUndecorated(true);
    localJFrame.setVisible(true);
    localJFrame.setAlwaysOnTop(true);
    Object localObject = Toolkit.getDefaultToolkit().getScreenSize();
    boolean bool = jdField_field_1157_of_type_JavaLangString.toLowerCase(Locale.ENGLISH).contains("intel");
    localJFrame.setLocation(((Dimension)localObject).width / 2, ((Dimension)localObject).height / 2);
    localObject = "";
    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("Database lock acquisition"))) {
      localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens when there is still an instance of the game running\ncheck your process manager for \"javaw.exe\" and terminate it, or restart your computer to solve this problem.";
    }
    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("shieldhit"))) {
      localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens on some ATI cards\nplease try to update your drivers, and if it still doesn't work,\nset \"Shieldhit drawing\" to \"off\" in the advanced graphics settings on start-up";
    }
    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("SkyFromSpace"))) {
      localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens on some ATI cards\nplease try to update your drivers, and if it still doesn't work,\nset \"Atmosphere Shader\" to \"none\" in the advanced graphics settings on start-up";
    }
    if ((bool) || ((paramException.getMessage() != null) && (paramException.getMessage().contains("perpixel")))) {
      localObject = (String)localObject + "\n\nIMPORTANT NOTE: The program has detected, that you are using an Intel Card.\nThe older Drivers of intel have known bugs. \nIf your error happened while the game was loading (at 99% or 100%), the\ncrash was most likely caused by the driver bug. \nPlease update your Graphics Card Driver to the latest version.\nThe problem is 99% on Intel cards. If you have an Intel card, plese try:\nhttp://www.intel.com/p/en_US/support/detect/graphics\n\nif it still doesn't work you have to find & download the driver manually from intel.com,\nsince the update tool doesn't always supply the newest Version\nI hope this information helps you\n\nIf you don't have an intel card, please check if your system uses dual graphics cores, and set java and the launcher to accelerated in the graphics card's settings\n\n- schema";
    }
    switch (JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage() + (String)localObject, str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
    {
    case 0: 
      break;
    case 1: 
      paramException = new String[0];
      SwingUtilities.invokeLater(new class_927(paramException));
      break;
    case 2: 
      System.err.println("[GLFrame] (ErrorDialog Chose Exit) Error Message: " + paramException.getMessage());
      jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.exit();
    }
    localJFrame.dispose();
  }
  
  public static void b2(Exception paramException)
  {
    if (field_1160) {
      return;
    }
    Object[] arrayOfObject = { "Exit" };
    paramException.printStackTrace();
    String str = "Disconnected";
    JFrame localJFrame;
    (localJFrame = new JFrame(str)).setUndecorated(true);
    localJFrame.setVisible(true);
    localJFrame.setAlwaysOnTop(true);
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
    JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage(), str, 0, 0, null, arrayOfObject, arrayOfObject[0]);
    System.err.println("[GLFrame] (ErrorDialog Chose Exit) Error Message: " + paramException.getMessage());
    jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.exit();
    localJFrame.dispose();
  }
  
  public static void a3()
  {
    try
    {
      throw new FinishedFrameException();
    }
    catch (FinishedFrameException localFinishedFrameException)
    {
      System.err.println("!!!! THIS DISPLAYS THE STACKTRACE OF A REGULAR GL FRAME EXIT");
      localFinishedFrameException.printStackTrace();
      field_1160 = true;
    }
  }
  
  public static void b3()
  {
    jdField_field_1156_of_type_Boolean = true;
  }
  
  public class_933(ClientState paramClientState, class_923 paramclass_923)
  {
    this.jdField_field_1156_of_type_Class_923 = paramclass_923;
    jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState = paramClientState;
  }
  
  public final void c()
  {
    System.out.println("[GLFrame] disposing GLFrame");
    this.field_1159 = true;
    a3();
  }
  
  private void d()
  {
    if (this.field_1159) {
      return;
    }
    GL11.glClear(16640);
    if (!class_969.a2().a6())
    {
      GlUtil.e1();
      try
      {
        if (this.field_1158)
        {
          class_969.a2().a1().a1("data/./image-resource/schine.png", "schine");
          System.out.println("[GLFrame] loading content data");
          class_969.a2().a10("preparing data");
          class_969.a2().a7();
          this.field_1158 = false;
        }
        class_969.a2().b();
        class_969.a6();
      }
      catch (ResourceException localResourceException)
      {
        (localObject = localResourceException).printStackTrace();
        a2((Exception)localObject);
      }
      catch (Exception localException)
      {
        (localObject = localException).printStackTrace();
        a2((Exception)localObject);
      }
      GlUtil.e1();
      if (class_969.a2().a6()) {
        System.out.println("[GLFRAME] Content has been loaded");
      }
    }
    class_40.a("DRAW");
    GlUtil.d1();
    Object localObject = this;
    if (this.jdField_field_1156_of_type_Class_923 != null)
    {
      try
      {
        ((class_933)localObject).jdField_field_1156_of_type_Class_923.b();
        class_40.a("betweenDraw");
        GL11.glClearColor(0.04F, 0.04F, 0.08F, 0.0F);
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        a2(localErrorDialogException;
      }
    }
    else
    {
      GL11.glClear(16640);
      GlUtil.a38(0.2F, 1.0F, 0.3F, 1.0F);
    }
    if (System.currentTimeMillis() > ((class_933)localObject).jdField_field_1156_of_type_Long + 1000L) {
      ((class_933)localObject).jdField_field_1156_of_type_Long = System.currentTimeMillis();
    }
    jdField_field_1157_of_type_Long = (jdField_field_1157_of_type_Long + 1L) % 9223372036854775807L;
    GlUtil.c2();
    class_40.b("DRAW");
  }
  
  public final void a4(String paramString)
  {
    this.jdField_field_1156_of_type_JavaLangString = paramString;
    class_40.a("betweenDraw");
    paramString = this;
    this.jdField_field_1156_of_type_Long = System.currentTimeMillis();
    Object localObject3;
    Object localObject4;
    Object localObject1;
    try
    {
      f();
      paramString.e();
      Mouse.create();
      Mouse.setGrabbed(false);
      int i = 0;
      jdField_field_1156_of_type_Boolean = false;
      JFrame localJFrame;
      if (!GLContext.getCapabilities().OpenGL21)
      {
        System.out.println("[Open-GL] FramBuffers are not supported on this Graphics Card");
        Object[] arrayOfObject1 = { "Exit" };
        localObject3 = "Requirements not met";
        (localJFrame = new JFrame((String)localObject3)).setUndecorated(true);
        localJFrame.setVisible(true);
        localJFrame.setAlwaysOnTop(true);
        localObject4 = Toolkit.getDefaultToolkit().getScreenSize();
        localJFrame.setLocation(((Dimension)localObject4).width / 2, ((Dimension)localObject4).height / 2);
        j = 0;
        switch (JOptionPane.showOptionDialog(localJFrame, "Sorry! Your graphics card does not have the capabilities to run this Game :(\nYou need a graphics card capable of OpenGL 2.1", (String)localObject3, 1, 0, null, arrayOfObject1, arrayOfObject1[0]))
        {
        case 0: 
          int j;
          System.err.println("Exiting because of unsupported graphcis card");
          jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.exit();
        }
        localJFrame.dispose();
      }
      if ((!GLContext.getCapabilities().OpenGL30) && ((class_949.field_1206.b1()) || (class_949.field_1240.b1())))
      {
        System.out.println("[Open-GL] FramBuffers are not supported on this Graphics Card");
        Object[] arrayOfObject2 = { "Continue Without Frambuffers", "Exit" };
        localObject3 = "FramBuffer not supported";
        (localJFrame = new JFrame((String)localObject3)).setUndecorated(true);
        localJFrame.setVisible(true);
        localJFrame.setAlwaysOnTop(true);
        localObject4 = Toolkit.getDefaultToolkit().getScreenSize();
        localJFrame.setLocation(((Dimension)localObject4).width / 2, ((Dimension)localObject4).height / 2);
        k = 0;
        switch (JOptionPane.showOptionDialog(localJFrame, "FramBuffers are not supported on this Graphics Card.\nFramebuffers are used for effects like bloom.", (String)localObject3, 1, 0, null, arrayOfObject2, arrayOfObject2[0]))
        {
        case 0: 
          int k;
          class_949.field_1240.a8(Boolean.valueOf(false));
          class_949.field_1206.a8(Boolean.valueOf(false));
          break;
        case 1: 
          System.err.println("Exiting because unsupported frame buffer");
          jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.exit();
        }
        localJFrame.dispose();
      }
      jdField_field_1157_of_type_JavaLangString = GL11.glGetString(7937);
    }
    catch (LWJGLException localLWJGLException)
    {
      (localObject1 = localLWJGLException).printStackTrace();
      throw new ErrorDialogException(localObject1.getClass() + ": " + ((LWJGLException)localObject1).getMessage());
    }
    try
    {
      localObject1 = null;
      new File("./logs/").mkdir();
      if ((localObject3 = new File("./logs/graphcisinfo.txt")).exists()) {
        ((File)localObject3).delete();
      }
      boolean bool = (localObject3 = new File("./logs/graphcisinfo.txt")).createNewFile();
      System.err.println("FILE CREATED: " + bool);
      (localObject4 = new BufferedWriter(new FileWriter((File)localObject3))).append("Running on thread: " + Thread.currentThread().getName());
      ((BufferedWriter)localObject4).newLine();
      ((BufferedWriter)localObject4).append("Adapter: " + Display.getAdapter());
      ((BufferedWriter)localObject4).newLine();
      ((BufferedWriter)localObject4).append("Driver Version: " + Display.getVersion());
      ((BufferedWriter)localObject4).newLine();
      localObject1 = GL11.glGetString(7936);
      ((BufferedWriter)localObject4).append("Vendor: " + (String)localObject1);
      ((BufferedWriter)localObject4).newLine();
      localObject1 = GL11.glGetString(7938);
      ((BufferedWriter)localObject4).append("OpenGL Version: " + (String)localObject1);
      ((BufferedWriter)localObject4).newLine();
      localObject1 = GL11.glGetString(7937);
      ((BufferedWriter)localObject4).append("Renderer: " + (String)localObject1);
      ((BufferedWriter)localObject4).newLine();
      if (GLContext.getCapabilities().OpenGL20)
      {
        localObject1 = GL11.glGetString(35724);
        ((BufferedWriter)localObject4).append("GLSL Ver: " + (String)localObject1);
      }
      ((BufferedWriter)localObject4).flush();
      ((BufferedWriter)localObject4).close();
    }
    catch (IOException localIOException1)
    {
      localObject1 = null;
      localIOException1.printStackTrace();
    }
    GlUtil.a2();
    System.out.println("[GLFrame] Open-GL initialized");
    paramString.jdField_field_1156_of_type_Class_941.a2();
    paramString.field_1161 = false;
    new Thread(new class_929(paramString)).start();
    try
    {
      class_969.a().a();
      paramString.field_1161 = true;
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
      throw new ErrorDialogException("Sound initialization failed. Please restart and turn sound off in options.");
    }
    try
    {
      while (jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState == null) {
        System.err.println("waiting for state");
      }
      while (!field_1160)
      {
        if (jdField_field_1156_of_type_Boolean) {
          jdField_field_1157_of_type_Boolean = true;
        }
        if (jdField_field_1157_of_type_Boolean)
        {
          System.err.println("Screen setting changed");
          f();
          paramString.e();
          GL11.glViewport(0, 0, jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode.getWidth(), jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode.getHeight());
          class_969.a6();
        }
        if (Display.isCloseRequested())
        {
          System.err.println("[GLFrame] Display Requested Close");
          a3();
        }
        else if (Display.isActive())
        {
          class_40.b("betweenDraw");
          class_40.a("RENDERLOOP");
          paramString.d();
          class_40.b("RENDERLOOP");
          if (class_949.field_1202.b1()) {
            Display.sync(60);
          }
          paramString.a5(paramString.jdField_field_1156_of_type_Class_941);
        }
        else
        {
          if ((Display.isVisible()) || (Display.isDirty())) {
            paramString.d();
          }
          paramString.a5(paramString.jdField_field_1156_of_type_Class_941);
          Display.sync(60);
        }
        paramString.jdField_field_1156_of_type_Class_941.b();
        class_40.a("RENDERLOOPINACT");
        Display.update(true);
        class_40.b("RENDERLOOPINACT");
        if ((class_969.a1() != null) && (class_1046.a1())) {
          class_1046.a();
        }
        if (jdField_field_1157_of_type_Boolean)
        {
          jdField_field_1157_of_type_Boolean = false;
          int m = 0;
          jdField_field_1156_of_type_Boolean = false;
        }
      }
    }
    catch (Exception localException1)
    {
      System.err.println("[GLFRAME] THROWN: " + localException1.getClass() + " Now Printing StackTrace");
      localException1.printStackTrace();
      System.err.println("[GLFRAME] THROWN: " + localException1.getClass() + "Printing StackTrace DONE");
      if ((localException1 instanceof SocketException)) {
        b2(new DisconnectException("You have been disconnected from the Server\nThis is caused either connection problem or a server crash.\n\nActualException: " + localException1.getClass().getSimpleName()));
      } else {
        a2(localException1);
      }
    }
    System.out.println("[GLFrame] terminated frame: exiting program; finished: " + field_1160);
    Object localObject2;
    try
    {
      Display.destroy();
      class_969.a();
      if (class_76.a9())
      {
        class_969.a();
        class_76.b();
      }
    }
    catch (Exception localException3)
    {
      localObject2 = null;
      localException3.printStackTrace();
    }
    System.err.println("Exiting because of terminated frame");
    try
    {
      jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.disconnect();
    }
    catch (IOException localIOException2)
    {
      localObject2 = null;
      localIOException2.printStackTrace();
    }
    jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.exit();
  }
  
  private void a5(class_941 paramclass_941)
  {
    if (!class_969.a2().a6()) {
      return;
    }
    synchronized (jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.updateLock)
    {
      jdField_field_1156_of_type_OrgSchemaSchineNetworkClientClientState.getController().update(paramclass_941);
      this.jdField_field_1156_of_type_Class_923.a1(paramclass_941);
      return;
    }
  }
  
  private void e()
  {
    boolean bool = true;
    try
    {
      bool = GLContext.getCapabilities().GL_ARB_multisample;
    }
    catch (Exception localException)
    {
      System.err.println("ARB_SUPPORTED COULD NOT BE RETRIEVED");
    }
    if (!jdField_field_1157_of_type_Boolean)
    {
      Display.destroy();
      if ((!field_1162) && (jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode == null)) {
        throw new AssertionError();
      }
      Display.setTitle(this.jdField_field_1156_of_type_JavaLangString);
      if (class_949.field_1224.a4().equals("top-left")) {
        Display.setLocation(0, 0);
      }
    }
    Display.setDisplayMode(jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode);
    if (!jdField_field_1157_of_type_Boolean) {
      try
      {
        if ((bool) && (((Integer)class_949.field_1238.a4()).intValue() > 0))
        {
          System.err.println("CHANGING PIXEL FORMAT!!!!");
          Display.create(new PixelFormat(jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode.getBitsPerPixel(), 8, 24, 1, ((Integer)class_949.field_1238.a4()).intValue()));
        }
        else
        {
          Display.create();
        }
      }
      catch (LWJGLException localLWJGLException)
      {
        Display.create();
      }
    }
    Display.setVSyncEnabled(class_949.field_1178.b1());
    GlUtil.f1();
  }
  
  private static void f()
  {
    class_1398 localclass_1398;
    boolean bool = class_949.field_1177.b1();
    int j = localclass_1398.field_1611.field_1174;
    int i = (localclass_1398 = (class_1398)class_949.field_1176.a4()).field_1611.field_1173;
    System.err.println("FULLSCREEN " + bool + "; " + i + " / " + j);
    try
    {
      if ((Display.getDisplayMode().getWidth() == i) && (Display.getDisplayMode().getHeight() == j) && (Display.isFullscreen() == bool))
      {
        jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode = Display.getDisplayMode();
        System.err.println("WARNING Displaymode already set!!!");
        return;
      }
    }
    catch (Exception localException)
    {
      localException;
    }
    Object localObject = null;
    if (bool)
    {
      DisplayMode[] arrayOfDisplayMode = Display.getAvailableDisplayModes();
      int k = 0;
      for (int m = 0; m < arrayOfDisplayMode.length; m++)
      {
        DisplayMode localDisplayMode = arrayOfDisplayMode[m];
        System.err.println("CHECKING DISPLAY MODE: " + localDisplayMode);
        if ((localDisplayMode.getWidth() == i) && (localDisplayMode.getHeight() == j))
        {
          if (((localObject == null) || (localDisplayMode.getFrequency() >= k)) && ((localObject == null) || (localDisplayMode.getBitsPerPixel() > ((DisplayMode)localObject).getBitsPerPixel()))) {
            k = (localObject = localDisplayMode).getFrequency();
          }
          if ((localDisplayMode.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (localDisplayMode.getFrequency() == Display.getDesktopDisplayMode().getFrequency()))
          {
            localObject = localDisplayMode;
            System.err.println("Compatible DisplayMode found: " + localDisplayMode + "; fullsceen capable " + ((DisplayMode)localObject).isFullscreenCapable());
            break;
          }
        }
      }
    }
    else
    {
      localObject = new DisplayMode(i, j);
    }
    if (localObject == null)
    {
      localObject = new DisplayMode(i, j);
      System.out.println("Failed to find value mode: " + i + "x" + j + " fs=" + bool + ": FallBackFullscreen Supported: " + ((DisplayMode)localObject).isFullscreenCapable());
    }
    Display.setDisplayMode(class_933.jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode = localObject);
    Display.setFullscreen((bool) && (jdField_field_1156_of_type_OrgLwjglOpenglDisplayMode.isFullscreenCapable()));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_933
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */