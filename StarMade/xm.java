/*   1:    */import java.awt.Dimension;
/*   2:    */import java.awt.Toolkit;
/*   3:    */import java.io.BufferedWriter;
/*   4:    */import java.io.File;
/*   5:    */import java.io.FileWriter;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.PrintStream;
/*   8:    */import java.net.SocketException;
/*   9:    */import java.util.Locale;
/*  10:    */import javax.swing.JFrame;
/*  11:    */import javax.swing.JOptionPane;
/*  12:    */import javax.swing.SwingUtilities;
/*  13:    */import org.lwjgl.LWJGLException;
/*  14:    */import org.lwjgl.input.Mouse;
/*  15:    */import org.lwjgl.opengl.ContextCapabilities;
/*  16:    */import org.lwjgl.opengl.Display;
/*  17:    */import org.lwjgl.opengl.DisplayMode;
/*  18:    */import org.lwjgl.opengl.GL11;
/*  19:    */import org.lwjgl.opengl.GLContext;
/*  20:    */import org.lwjgl.opengl.PixelFormat;
/*  21:    */import org.schema.schine.graphicsengine.core.FinishedFrameException;
/*  22:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  23:    */import org.schema.schine.graphicsengine.core.ResourceException;
/*  24:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  25:    */import org.schema.schine.network.client.ClientControllerInterface;
/*  26:    */import org.schema.schine.network.client.ClientState;
/*  27:    */import org.schema.schine.network.exception.DisconnectException;
/*  28:    */
/*  86:    */public class xm
/*  87:    */{
/*  88: 88 */  private static boolean jdField_a_of_type_Boolean = false;
/*  89: 89 */  private static boolean jdField_b_of_type_Boolean = false;
/*  90:    */  
/*  98:    */  public static int a()
/*  99:    */  {
/* 100:100 */    return jdField_a_of_type_OrgLwjglOpenglDisplayMode.getHeight();
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static int b() {
/* 104:104 */    return jdField_a_of_type_OrgLwjglOpenglDisplayMode.getWidth();
/* 105:    */  }
/* 106:    */  
/* 107:107 */  public static boolean a() { return e; }
/* 108:    */  
/* 112:    */  public static boolean b()
/* 113:    */  {
/* 114:114 */    return jdField_b_of_type_Boolean;
/* 115:    */  }
/* 116:    */  
/* 117:    */  public static void a(Exception paramException) {
/* 118:118 */    if (e) {
/* 119:119 */      return;
/* 120:    */    }
/* 121:    */    
/* 122:122 */    if ((paramException != null) && ((paramException instanceof SocketException)) && (paramException.getMessage() != null) && (paramException.getMessage().contains("Connection reset by peer: socket write error"))) {
/* 123:123 */      b(paramException);
/* 124:124 */      return;
/* 125:    */    }
/* 126:    */    
/* 127:127 */    Object[] arrayOfObject = { "Retry", "Send Crash Report", "Exit" };
/* 128:128 */    paramException.printStackTrace();
/* 129:129 */    String str = "Critical Error";
/* 130:    */    JFrame localJFrame;
/* 131:131 */    (localJFrame = new JFrame(str)).setUndecorated(true);
/* 132:    */    
/* 133:133 */    localJFrame.setVisible(true);
/* 134:    */    
/* 135:135 */    localJFrame.setAlwaysOnTop(true);
/* 136:136 */    Object localObject = Toolkit.getDefaultToolkit().getScreenSize();
/* 137:    */    
/* 138:138 */    boolean bool = jdField_b_of_type_JavaLangString.toLowerCase(Locale.ENGLISH).contains("intel");
/* 139:    */    
/* 140:140 */    localJFrame.setLocation(((Dimension)localObject).width / 2, ((Dimension)localObject).height / 2);
/* 141:    */    
/* 142:142 */    localObject = "";
/* 143:    */    
/* 145:145 */    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("Database lock acquisition"))) {
/* 146:146 */      localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens when there is still an instance of the game running\ncheck your process manager for \"javaw.exe\" and terminate it, or restart your computer to solve this problem.";
/* 147:    */    }
/* 148:    */    
/* 152:152 */    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("shieldhit"))) {
/* 153:153 */      localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens on some ATI cards\nplease try to update your drivers, and if it still doesn't work,\nset \"Shieldhit drawing\" to \"off\" in the advanced graphics settings on start-up";
/* 154:    */    }
/* 155:    */    
/* 158:158 */    if ((paramException.getMessage() != null) && (paramException.getMessage().contains("SkyFromSpace"))) {
/* 159:159 */      localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens on some ATI cards\nplease try to update your drivers, and if it still doesn't work,\nset \"Atmosphere Shader\" to \"none\" in the advanced graphics settings on start-up";
/* 160:    */    }
/* 161:    */    
/* 164:164 */    if ((bool) || ((paramException.getMessage() != null) && (paramException.getMessage().contains("perpixel")))) {
/* 165:165 */      localObject = (String)localObject + "\n\nIMPORTANT NOTE: The program has detected, that you are using an Intel Card.\nThe older Drivers of intel have known bugs. \nIf your error happened while the game was loading (at 99% or 100%), the\ncrash was most likely caused by the driver bug. \nPlease update your Graphics Card Driver to the latest version.\nThe problem is 99% on Intel cards. If you have an Intel card, plese try:\nhttp://www.intel.com/p/en_US/support/detect/graphics\n\nif it still doesn't work you have to find & download the driver manually from intel.com,\nsince the update tool doesn't always supply the newest Version\nI hope this information helps you\n\nIf you don't have an intel card, please check if your system uses dual graphics cores, and set java and the launcher to accelerated in the graphics card's settings\n\n- schema";
/* 166:    */    }
/* 167:    */    
/* 176:176 */    switch (JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage() + (String)localObject, str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/* 177:    */    {
/* 181:    */    case 0: 
/* 182:182 */      break;
/* 183:    */    case 1: 
/* 184:184 */      paramException = new String[0];SwingUtilities.invokeLater(new xn(paramException));
/* 185:185 */      break;
/* 186:    */    case 2: 
/* 187:187 */      System.err.println("[GLFrame] (ErrorDialog Chose Exit) Error Message: " + paramException.getMessage());
/* 188:188 */      jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit();
/* 189:    */    }
/* 190:    */    
/* 191:    */    
/* 192:192 */    localJFrame.dispose();
/* 193:    */  }
/* 194:    */  
/* 195:    */  public static void b(Exception paramException)
/* 196:    */  {
/* 197:197 */    if (e) {
/* 198:198 */      return;
/* 199:    */    }
/* 200:    */    
/* 201:201 */    Object[] arrayOfObject = { "Exit" };
/* 202:202 */    paramException.printStackTrace();
/* 203:203 */    String str = "Disconnected";
/* 204:    */    JFrame localJFrame;
/* 205:205 */    (localJFrame = new JFrame(str)).setUndecorated(true);
/* 206:    */    
/* 207:207 */    localJFrame.setVisible(true);
/* 208:    */    
/* 209:209 */    localJFrame.setAlwaysOnTop(true);
/* 210:210 */    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/* 211:    */    
/* 213:213 */    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/* 214:    */    
/* 216:216 */    JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage(), str, 0, 0, null, arrayOfObject, arrayOfObject[0]);
/* 217:    */    
/* 220:220 */    System.err.println("[GLFrame] (ErrorDialog Chose Exit) Error Message: " + paramException.getMessage());
/* 221:    */    
/* 223:223 */    jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit();
/* 224:    */    
/* 227:227 */    localJFrame.dispose();
/* 228:    */  }
/* 229:    */  
/* 230:    */  public static void a()
/* 231:    */  {
/* 232:    */    try
/* 233:    */    {
/* 234:234 */      throw new FinishedFrameException();
/* 235:    */    } catch (FinishedFrameException localFinishedFrameException) {
/* 236:236 */      System.err.println("!!!! THIS DISPLAYS THE STACKTRACE OF A REGULAR GL FRAME EXIT");
/* 237:237 */      localFinishedFrameException.printStackTrace();
/* 238:    */      
/* 239:239 */      e = true;
/* 240:    */    }
/* 241:    */  }
/* 242:    */  
/* 244:    */  public static void b()
/* 245:    */  {
/* 246:246 */    jdField_a_of_type_Boolean = true;
/* 247:    */  }
/* 248:    */  
/* 299:299 */  private boolean c = true;
/* 300:    */  
/* 302:    */  private long jdField_a_of_type_Long;
/* 303:    */  
/* 304:304 */  private xq jdField_a_of_type_Xq = new xq();
/* 305:    */  
/* 307:    */  private boolean d;
/* 308:    */  
/* 310:    */  private static boolean e;
/* 311:    */  
/* 312:    */  private xh jdField_a_of_type_Xh;
/* 313:    */  
/* 314:    */  private static DisplayMode jdField_a_of_type_OrgLwjglOpenglDisplayMode;
/* 315:    */  
/* 316:    */  public static ClientState a;
/* 317:    */  
/* 318:    */  private String jdField_a_of_type_JavaLangString;
/* 319:    */  
/* 320:    */  private boolean f;
/* 321:    */  
/* 322:322 */  private static String jdField_b_of_type_JavaLangString = "";
/* 323:    */  
/* 333:    */  private static long jdField_b_of_type_Long;
/* 334:    */  
/* 344:    */  public xm(ClientState paramClientState, xh paramxh)
/* 345:    */  {
/* 346:346 */    this.jdField_a_of_type_Xh = paramxh;
/* 347:347 */    jdField_a_of_type_OrgSchemaSchineNetworkClientClientState = paramClientState;
/* 348:    */  }
/* 349:    */  
/* 443:    */  public final void c()
/* 444:    */  {
/* 445:445 */    System.out.println("[GLFrame] disposing GLFrame");
/* 446:446 */    this.d = true;
/* 447:447 */    a();
/* 448:    */  }
/* 449:    */  
/* 537:    */  private void d()
/* 538:    */  {
/* 539:539 */    if (this.d) {
/* 540:540 */      return;
/* 541:    */    }
/* 542:542 */    GL11.glClear(16640);
/* 543:543 */    if (!xe.a().a())
/* 544:    */    {
/* 545:545 */      GlUtil.e();
/* 546:    */      try {
/* 547:547 */        if (this.c)
/* 548:    */        {
/* 549:549 */          xe.a().a().a("data/./image-resource/schine.png", "schine");
/* 550:    */          
/* 553:553 */          System.out.println("[GLFrame] loading content data");
/* 554:554 */          xe.a().a("preparing data");
/* 555:    */          
/* 556:556 */          xe.a().a();
/* 557:557 */          this.c = false;
/* 558:    */        }
/* 559:559 */        xe.a().b();
/* 560:560 */        xe.a();
/* 561:    */      } catch (ResourceException localResourceException) {
/* 562:562 */        (localObject = 
/* 563:    */        
/* 567:567 */          localResourceException).printStackTrace();a((Exception)localObject);
/* 568:    */      } catch (Exception localException) {
/* 569:565 */        (localObject = 
/* 570:    */        
/* 571:567 */          localException).printStackTrace();a((Exception)localObject);
/* 572:    */      }
/* 573:    */      
/* 574:569 */      GlUtil.e();
/* 575:570 */      if (xe.a().a()) {
/* 576:571 */        System.out.println("[GLFRAME] Content has been loaded");
/* 577:    */      }
/* 578:    */    }
/* 579:    */    
/* 581:576 */    h.a("DRAW");
/* 582:577 */    GlUtil.d();
/* 583:578 */    Object localObject = this; if (this.jdField_a_of_type_Xh != null) { try { ((xm)localObject).jdField_a_of_type_Xh.b();h.a("betweenDraw");GL11.glClearColor(0.04F, 0.04F, 0.08F, 0.0F); } catch (ErrorDialogException localErrorDialogException) { a(localErrorDialogException; } } else { GL11.glClear(16640);GlUtil.a(0.2F, 1.0F, 0.3F, 1.0F); } if (System.currentTimeMillis() > ((xm)localObject).jdField_a_of_type_Long + 1000L) ((xm)localObject).jdField_a_of_type_Long = System.currentTimeMillis(); jdField_b_of_type_Long = (jdField_b_of_type_Long + 1L) % 9223372036854775807L;
/* 584:579 */    GlUtil.c();
/* 585:580 */    h.b("DRAW");
/* 586:    */  }
/* 587:    */  
/* 842:    */  public final void a(String paramString)
/* 843:    */  {
/* 844:839 */    this.jdField_a_of_type_JavaLangString = paramString;
/* 845:    */    
/* 846:841 */    h.a("betweenDraw");
/* 847:842 */    paramString = this;this.jdField_a_of_type_Long = System.currentTimeMillis(); Object localObject3; Object localObject4; Object localObject1; try { f();paramString.e();Mouse.create();Mouse.setGrabbed(false);int i = 0;jdField_a_of_type_Boolean = false; JFrame localJFrame; if (!GLContext.getCapabilities().OpenGL21) { System.out.println("[Open-GL] FramBuffers are not supported on this Graphics Card");Object[] arrayOfObject1 = { "Exit" };localObject3 = "Requirements not met";(localJFrame = new JFrame((String)localObject3)).setUndecorated(true);localJFrame.setVisible(true);localJFrame.setAlwaysOnTop(true);localObject4 = Toolkit.getDefaultToolkit().getScreenSize();localJFrame.setLocation(((Dimension)localObject4).width / 2, ((Dimension)localObject4).height / 2);j = 0; switch (JOptionPane.showOptionDialog(localJFrame, "Sorry! Your graphics card does not have the capabilities to run this Game :(\nYou need a graphics card capable of OpenGL 2.1", (String)localObject3, 1, 0, null, arrayOfObject1, arrayOfObject1[0])) {case 0:  int j; System.err.println("Exiting because of unsupported graphcis card");jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit(); } localJFrame.dispose(); } if ((!GLContext.getCapabilities().OpenGL30) && ((xu.x.b()) || (xu.V.b()))) { System.out.println("[Open-GL] FramBuffers are not supported on this Graphics Card");Object[] arrayOfObject2 = { "Continue Without Frambuffers", "Exit" };localObject3 = "FramBuffer not supported";(localJFrame = new JFrame((String)localObject3)).setUndecorated(true);localJFrame.setVisible(true);localJFrame.setAlwaysOnTop(true);localObject4 = Toolkit.getDefaultToolkit().getScreenSize();localJFrame.setLocation(((Dimension)localObject4).width / 2, ((Dimension)localObject4).height / 2);k = 0; switch (JOptionPane.showOptionDialog(localJFrame, "FramBuffers are not supported on this Graphics Card.\nFramebuffers are used for effects like bloom.", (String)localObject3, 1, 0, null, arrayOfObject2, arrayOfObject2[0])) {case 0:  int k; xu.V.a(Boolean.valueOf(false));xu.x.a(Boolean.valueOf(false));break; case 1:  System.err.println("Exiting because unsupported frame buffer");jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit(); } localJFrame.dispose(); } jdField_b_of_type_JavaLangString = GL11.glGetString(7937); } catch (LWJGLException localLWJGLException) { (localObject1 = localLWJGLException).printStackTrace();throw new ErrorDialogException(localObject1.getClass() + ": " + ((LWJGLException)localObject1).getMessage()); } try { localObject1 = null;new File("./logs/").mkdir(); if ((localObject3 = new File("./logs/graphcisinfo.txt")).exists()) ((File)localObject3).delete(); boolean bool = (localObject3 = new File("./logs/graphcisinfo.txt")).createNewFile();System.err.println("FILE CREATED: " + bool);(localObject4 = new BufferedWriter(new FileWriter((File)localObject3))).append("Running on thread: " + Thread.currentThread().getName());((BufferedWriter)localObject4).newLine();((BufferedWriter)localObject4).append("Adapter: " + Display.getAdapter());((BufferedWriter)localObject4).newLine();((BufferedWriter)localObject4).append("Driver Version: " + Display.getVersion());((BufferedWriter)localObject4).newLine();localObject1 = GL11.glGetString(7936);((BufferedWriter)localObject4).append("Vendor: " + (String)localObject1);((BufferedWriter)localObject4).newLine();localObject1 = GL11.glGetString(7938);((BufferedWriter)localObject4).append("OpenGL Version: " + (String)localObject1);((BufferedWriter)localObject4).newLine();localObject1 = GL11.glGetString(7937);((BufferedWriter)localObject4).append("Renderer: " + (String)localObject1);((BufferedWriter)localObject4).newLine(); if (GLContext.getCapabilities().OpenGL20) { localObject1 = GL11.glGetString(35724);((BufferedWriter)localObject4).append("GLSL Ver: " + (String)localObject1); } ((BufferedWriter)localObject4).flush();((BufferedWriter)localObject4).close(); } catch (IOException localIOException1) { localObject1 = null;localIOException1.printStackTrace(); } GlUtil.a();System.out.println("[GLFrame] Open-GL initialized");paramString.jdField_a_of_type_Xq.a();paramString.f = false;new Thread(new xo(paramString)).start(); try { xe.a().a();paramString.f = true; } catch (Exception localException2) { localException2.printStackTrace();throw new ErrorDialogException("Sound initialization failed. Please restart and turn sound off in options."); } try { if (jdField_a_of_type_OrgSchemaSchineNetworkClientClientState == null) System.err.println("waiting for state"); for (goto 839; !e;) { if (jdField_a_of_type_Boolean) jdField_b_of_type_Boolean = true; if (jdField_b_of_type_Boolean) { System.err.println("Screen setting changed");f();paramString.e();GL11.glViewport(0, 0, jdField_a_of_type_OrgLwjglOpenglDisplayMode.getWidth(), jdField_a_of_type_OrgLwjglOpenglDisplayMode.getHeight());xe.a(); } if (Display.isCloseRequested()) { System.err.println("[GLFrame] Display Requested Close");a(); } else if (Display.isActive()) { h.b("betweenDraw");h.a("RENDERLOOP");paramString.d();h.b("RENDERLOOP"); if (xu.v.b()) Display.sync(60); paramString.a(paramString.jdField_a_of_type_Xq); } else { if ((Display.isVisible()) || (Display.isDirty())) paramString.d(); paramString.a(paramString.jdField_a_of_type_Xq);Display.sync(60); } paramString.jdField_a_of_type_Xq.b();h.a("RENDERLOOPINACT");Display.update(true);h.b("RENDERLOOPINACT"); if ((xe.a() != null) && (wV.a())) wV.a(); if (jdField_b_of_type_Boolean) { jdField_b_of_type_Boolean = false;int m = 0;jdField_a_of_type_Boolean = false; } } } catch (Exception localException1) { System.err.println("[GLFRAME] THROWN: " + localException1.getClass() + " Now Printing StackTrace");localException1.printStackTrace();System.err.println("[GLFRAME] THROWN: " + localException1.getClass() + "Printing StackTrace DONE"); if ((localException1 instanceof SocketException)) b(new DisconnectException("You have been disconnected from the Server\nThis is caused either connection problem or a server crash.\n\nActualException: " + localException1.getClass().getSimpleName())); else a(localException1); } System.out.println("[GLFrame] terminated frame: exiting program; finished: " + e); Object localObject2; try { Display.destroy();xe.a(); if (Ao.a()) { xe.a();Ao.b(); } } catch (Exception localException3) { localObject2 = null;localException3.printStackTrace(); } System.err.println("Exiting because of terminated frame"); try { jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.disconnect(); } catch (IOException localIOException2) { localObject2 = null;localIOException2.printStackTrace(); } jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit();
/* 848:    */  }
/* 849:    */  
/* 850:    */  private void a(xq paramxq)
/* 851:    */  {
/* 852:847 */    if (!xe.a().a()) {
/* 853:848 */      return;
/* 854:    */    }
/* 855:850 */    synchronized (jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.updateLock) {
/* 856:851 */      jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.getController().update(paramxq);
/* 857:852 */      this.jdField_a_of_type_Xh.a(paramxq); return;
/* 858:    */    }
/* 859:    */  }
/* 860:    */  
/* 862:    */  private void e()
/* 863:    */  {
/* 864:859 */    boolean bool = true;
/* 865:    */    try {
/* 866:861 */      bool = GLContext.getCapabilities().GL_ARB_multisample;
/* 867:862 */    } catch (Exception localException) { System.err.println("ARB_SUPPORTED COULD NOT BE RETRIEVED"); }
/* 868:863 */    if (!jdField_b_of_type_Boolean) {
/* 869:864 */      Display.destroy();
/* 870:    */      
/* 871:866 */      if ((!g) && (jdField_a_of_type_OrgLwjglOpenglDisplayMode == null)) { throw new AssertionError();
/* 872:    */      }
/* 873:868 */      Display.setTitle(this.jdField_a_of_type_JavaLangString);
/* 874:    */      
/* 876:871 */      if (xu.I.a().equals("top-left")) {
/* 877:872 */        Display.setLocation(0, 0);
/* 878:    */      }
/* 879:    */    }
/* 880:875 */    Display.setDisplayMode(jdField_a_of_type_OrgLwjglOpenglDisplayMode);
/* 881:876 */    if (!jdField_b_of_type_Boolean) {
/* 882:    */      try {
/* 883:878 */        if ((bool) && (((Integer)xu.T.a()).intValue() > 0)) {
/* 884:879 */          System.err.println("CHANGING PIXEL FORMAT!!!!");
/* 885:    */          
/* 892:887 */          Display.create(new PixelFormat(jdField_a_of_type_OrgLwjglOpenglDisplayMode.getBitsPerPixel(), 8, 24, 1, ((Integer)xu.T.a()).intValue()));
/* 893:    */        } else {
/* 894:889 */          Display.create();
/* 895:    */        }
/* 896:    */      } catch (LWJGLException localLWJGLException) {
/* 897:892 */        Display.create();
/* 898:    */      }
/* 899:    */    }
/* 900:    */    
/* 902:897 */    Display.setVSyncEnabled(xu.d.b());
/* 903:    */    
/* 904:899 */    GlUtil.f();
/* 905:    */  }
/* 906:    */  
/* 907:    */  private static void f() {
/* 908:    */    xx localxx;
/* 909:904 */    boolean bool = xu.c.b();int j = localxx.a.b;int i = (localxx = (xx)xu.b.a()).a.a;System.err.println("FULLSCREEN " + bool + "; " + i + " / " + j); try { if ((Display.getDisplayMode().getWidth() == i) && (Display.getDisplayMode().getHeight() == j) && (Display.isFullscreen() == bool)) { jdField_a_of_type_OrgLwjglOpenglDisplayMode = Display.getDisplayMode();System.err.println("WARNING Displaymode already set!!!");return; } } catch (Exception localException) { localException; } Object localObject = null; if (bool) { DisplayMode[] arrayOfDisplayMode = Display.getAvailableDisplayModes();int k = 0; for (int m = 0; m < arrayOfDisplayMode.length; m++) { DisplayMode localDisplayMode = arrayOfDisplayMode[m];System.err.println("CHECKING DISPLAY MODE: " + localDisplayMode); if ((localDisplayMode.getWidth() == i) && (localDisplayMode.getHeight() == j)) { if (((localObject == null) || (localDisplayMode.getFrequency() >= k)) && ((localObject == null) || (localDisplayMode.getBitsPerPixel() > ((DisplayMode)localObject).getBitsPerPixel()))) k = (localObject = localDisplayMode).getFrequency(); if ((localDisplayMode.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (localDisplayMode.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) { localObject = localDisplayMode;System.err.println("Compatible DisplayMode found: " + localDisplayMode + "; fullsceen capable " + ((DisplayMode)localObject).isFullscreenCapable());break; } } } } else { localObject = new DisplayMode(i, j); } if (localObject == null) { localObject = new DisplayMode(i, j);System.out.println("Failed to find value mode: " + i + "x" + j + " fs=" + bool + ": FallBackFullscreen Supported: " + ((DisplayMode)localObject).isFullscreenCapable()); } Display.setDisplayMode(xm.jdField_a_of_type_OrgLwjglOpenglDisplayMode = localObject);Display.setFullscreen((bool) && (jdField_a_of_type_OrgLwjglOpenglDisplayMode.isFullscreenCapable()));
/* 910:    */  }
/* 911:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */