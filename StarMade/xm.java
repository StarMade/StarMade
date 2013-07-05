/*     */ import java.awt.Dimension;
/*     */ import java.awt.Toolkit;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.SocketException;
/*     */ import java.util.Locale;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.ContextCapabilities;
/*     */ import org.lwjgl.opengl.Display;
/*     */ import org.lwjgl.opengl.DisplayMode;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import org.lwjgl.opengl.PixelFormat;
/*     */ import org.schema.schine.graphicsengine.core.FinishedFrameException;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.graphicsengine.core.ResourceException;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import org.schema.schine.network.client.ClientControllerInterface;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.exception.DisconnectException;
/*     */ 
/*     */ public class xm
/*     */ {
/*  88 */   private static boolean jdField_a_of_type_Boolean = false;
/*  89 */   private static boolean jdField_b_of_type_Boolean = false;
/*     */ 
/* 299 */   private boolean c = true;
/*     */   private long jdField_a_of_type_Long;
/* 304 */   private xq jdField_a_of_type_Xq = new xq();
/*     */   private boolean d;
/*     */   private static boolean e;
/*     */   private xh jdField_a_of_type_Xh;
/*     */   private static DisplayMode jdField_a_of_type_OrgLwjglOpenglDisplayMode;
/*     */   public static ClientState a;
/*     */   private String jdField_a_of_type_JavaLangString;
/*     */   private boolean f;
/* 322 */   private static String jdField_b_of_type_JavaLangString = "";
/*     */   private static long jdField_b_of_type_Long;
/*     */ 
/*     */   public static int a()
/*     */   {
/* 100 */     return jdField_a_of_type_OrgLwjglOpenglDisplayMode.getHeight();
/*     */   }
/*     */ 
/*     */   public static int b() {
/* 104 */     return jdField_a_of_type_OrgLwjglOpenglDisplayMode.getWidth();
/*     */   }
/*     */   public static boolean a() {
/* 107 */     return e;
/*     */   }
/*     */ 
/*     */   public static boolean b()
/*     */   {
/* 114 */     return jdField_b_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public static void a(Exception paramException) {
/* 118 */     if (e) {
/* 119 */       return;
/*     */     }
/*     */ 
/* 122 */     if ((paramException != null) && ((paramException instanceof SocketException)) && (paramException.getMessage() != null) && (paramException.getMessage().contains("Connection reset by peer: socket write error"))) {
/* 123 */       b(paramException);
/* 124 */       return;
/*     */     }
/*     */ 
/* 127 */     Object[] arrayOfObject = { "Retry", "Send Crash Report", "Exit" };
/* 128 */     paramException.printStackTrace();
/* 129 */     String str = "Critical Error";
/*     */     JFrame localJFrame;
/* 130 */     (
/* 131 */       localJFrame = new JFrame(str))
/* 131 */       .setUndecorated(true);
/*     */ 
/* 133 */     localJFrame.setVisible(true);
/*     */ 
/* 135 */     localJFrame.setAlwaysOnTop(true);
/* 136 */     Object localObject = Toolkit.getDefaultToolkit().getScreenSize();
/*     */ 
/* 138 */     boolean bool = jdField_b_of_type_JavaLangString.toLowerCase(Locale.ENGLISH).contains("intel");
/*     */ 
/* 140 */     localJFrame.setLocation(((Dimension)localObject).width / 2, ((Dimension)localObject).height / 2);
/*     */ 
/* 142 */     localObject = "";
/*     */ 
/* 145 */     if ((paramException.getMessage() != null) && (paramException.getMessage().contains("Database lock acquisition"))) {
/* 146 */       localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens when there is still an instance of the game running\ncheck your process manager for \"javaw.exe\" and terminate it, or restart your computer to solve this problem.";
/*     */     }
/*     */ 
/* 152 */     if ((paramException.getMessage() != null) && (paramException.getMessage().contains("shieldhit"))) {
/* 153 */       localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens on some ATI cards\nplease try to update your drivers, and if it still doesn't work,\nset \"Shieldhit drawing\" to \"off\" in the advanced graphics settings on start-up";
/*     */     }
/*     */ 
/* 158 */     if ((paramException.getMessage() != null) && (paramException.getMessage().contains("SkyFromSpace"))) {
/* 159 */       localObject = (String)localObject + "\n\nIMPORTANT NOTE: this crash happens on some ATI cards\nplease try to update your drivers, and if it still doesn't work,\nset \"Atmosphere Shader\" to \"none\" in the advanced graphics settings on start-up";
/*     */     }
/*     */ 
/* 164 */     if ((bool) || ((paramException.getMessage() != null) && (paramException.getMessage().contains("perpixel")))) {
/* 165 */       localObject = (String)localObject + "\n\nIMPORTANT NOTE: The program has detected, that you are using an Intel Card.\nThe older Drivers of intel have known bugs. \nIf your error happened while the game was loading (at 99% or 100%), the\ncrash was most likely caused by the driver bug. \nPlease update your Graphics Card Driver to the latest version.\nThe problem is 99% on Intel cards. If you have an Intel card, plese try:\nhttp://www.intel.com/p/en_US/support/detect/graphics\n\nif it still doesn't work you have to find & download the driver manually from intel.com,\nsince the update tool doesn't always supply the newest Version\nI hope this information helps you\n\nIf you don't have an intel card, please check if your system uses dual graphics cores, and set java and the launcher to accelerated in the graphics card's settings\n\n- schema";
/*     */     }
/*     */ 
/* 176 */     switch (JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage() + (String)localObject, str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/*     */     {
/*     */     case 0:
/* 182 */       break;
/*     */     case 1:
/* 184 */       paramException = new String[0]; SwingUtilities.invokeLater(new xn(paramException));
/* 185 */       break;
/*     */     case 2:
/* 187 */       System.err.println("[GLFrame] (ErrorDialog Chose Exit) Error Message: " + paramException.getMessage());
/* 188 */       jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit();
/*     */     }
/*     */ 
/* 192 */     localJFrame.dispose();
/*     */   }
/*     */ 
/*     */   public static void b(Exception paramException)
/*     */   {
/* 197 */     if (e) {
/* 198 */       return;
/*     */     }
/*     */ 
/* 201 */     Object[] arrayOfObject = { "Exit" };
/* 202 */     paramException.printStackTrace();
/* 203 */     String str = "Disconnected";
/*     */     JFrame localJFrame;
/* 204 */     (
/* 205 */       localJFrame = new JFrame(str))
/* 205 */       .setUndecorated(true);
/*     */ 
/* 207 */     localJFrame.setVisible(true);
/*     */ 
/* 209 */     localJFrame.setAlwaysOnTop(true);
/* 210 */     Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/*     */ 
/* 213 */     localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/*     */ 
/* 216 */     JOptionPane.showOptionDialog(localJFrame, paramException.getClass().getSimpleName() + ": " + paramException.getMessage(), str, 0, 0, null, arrayOfObject, arrayOfObject[0]);
/*     */ 
/* 220 */     System.err.println("[GLFrame] (ErrorDialog Chose Exit) Error Message: " + paramException.getMessage());
/*     */ 
/* 223 */     jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit();
/*     */ 
/* 227 */     localJFrame.dispose();
/*     */   }
/*     */ 
/*     */   public static void a()
/*     */   {
/*     */     try
/*     */     {
/* 234 */       throw new FinishedFrameException();
/*     */     } catch (FinishedFrameException localFinishedFrameException) {
/* 236 */       System.err.println("!!!! THIS DISPLAYS THE STACKTRACE OF A REGULAR GL FRAME EXIT");
/* 237 */       localFinishedFrameException.printStackTrace();
/*     */ 
/* 239 */       e = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void b()
/*     */   {
/* 246 */     jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public xm(ClientState paramClientState, xh paramxh)
/*     */   {
/* 346 */     this.jdField_a_of_type_Xh = paramxh;
/* 347 */     jdField_a_of_type_OrgSchemaSchineNetworkClientClientState = paramClientState;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 445 */     System.out.println("[GLFrame] disposing GLFrame");
/* 446 */     this.d = true;
/* 447 */     a();
/*     */   }
/*     */ 
/*     */   private void d()
/*     */   {
/* 539 */     if (this.d) {
/* 540 */       return;
/*     */     }
/* 542 */     GL11.glClear(16640);
/* 543 */     if (!xe.a().a())
/*     */     {
/* 545 */       GlUtil.e();
/*     */       try {
/* 547 */         if (this.c)
/*     */         {
/* 549 */           xe.a().a().a("data/./image-resource/schine.png", "schine");
/*     */ 
/* 553 */           System.out.println("[GLFrame] loading content data");
/* 554 */           xe.a().a("preparing data");
/*     */ 
/* 556 */           xe.a().a();
/* 557 */           this.c = false;
/*     */         }
/* 559 */         xe.a().b();
/* 560 */         xe.a(); } catch (ResourceException localResourceException) {
/* 561 */         (
/* 562 */           localObject = 
/* 567 */           localResourceException).printStackTrace();
/* 563 */         a((Exception)localObject); } catch (Exception localException) {
/* 564 */         (
/* 565 */           localObject = 
/* 567 */           localException).printStackTrace();
/* 566 */         a((Exception)localObject);
/*     */       }
/*     */ 
/* 569 */       GlUtil.e();
/* 570 */       if (xe.a().a()) {
/* 571 */         System.out.println("[GLFRAME] Content has been loaded");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 576 */     h.a("DRAW");
/* 577 */     GlUtil.d();
/* 578 */     Object localObject = this; if (this.jdField_a_of_type_Xh != null) { try { ((xm)localObject).jdField_a_of_type_Xh.b(); h.a("betweenDraw"); GL11.glClearColor(0.04F, 0.04F, 0.08F, 0.0F); } catch (ErrorDialogException localErrorDialogException) { a(localErrorDialogException); }  } else { GL11.glClear(16640); GlUtil.a(0.2F, 1.0F, 0.3F, 1.0F); } if (System.currentTimeMillis() > ((xm)localObject).jdField_a_of_type_Long + 1000L) ((xm)localObject).jdField_a_of_type_Long = System.currentTimeMillis(); jdField_b_of_type_Long = (jdField_b_of_type_Long + 1L) % 9223372036854775807L;
/* 579 */     GlUtil.c();
/* 580 */     h.b("DRAW");
/*     */   }
/*     */ 
/*     */   public final void a(String paramString)
/*     */   {
/* 836 */     this.jdField_a_of_type_JavaLangString = paramString;
/*     */ 
/* 838 */     h.a("betweenDraw");
/* 839 */     paramString = this; this.jdField_a_of_type_Long = System.currentTimeMillis();
/*     */     Object localObject3;
/*     */     Object localObject4;
/*     */     Object localObject1;
/*     */     try
/*     */     {
/* 839 */       f(); paramString.e(); Mouse.create(); Mouse.setGrabbed(false); int i = 0; jdField_a_of_type_Boolean = false;
/*     */       JFrame localJFrame;
/* 839 */       if (!GLContext.getCapabilities().OpenGL21) { System.out.println("[Open-GL] FramBuffers are not supported on this Graphics Card"); Object[] arrayOfObject1 = { "Exit" }; localObject3 = "Requirements not met"; (localJFrame = new JFrame((String)localObject3)).setUndecorated(true); localJFrame.setVisible(true); localJFrame.setAlwaysOnTop(true); localObject4 = Toolkit.getDefaultToolkit().getScreenSize(); localJFrame.setLocation(((Dimension)localObject4).width / 2, ((Dimension)localObject4).height / 2); j = 0; switch (JOptionPane.showOptionDialog(localJFrame, "Sorry! Your graphics card does not have the capabilities to run this Game :(\nYou need a graphics card capable of OpenGL 2.1", (String)localObject3, 1, 0, null, arrayOfObject1, arrayOfObject1[0]))
/*     */         {
/*     */         case 0:
/* 839 */           int j;
/* 839 */           System.err.println("Exiting because of unsupported graphcis card"); jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit(); } localJFrame.dispose(); } if ((!GLContext.getCapabilities().OpenGL30) && ((xu.x.b()) || (xu.U.b()))) { System.out.println("[Open-GL] FramBuffers are not supported on this Graphics Card"); Object[] arrayOfObject2 = { "Continue Without Frambuffers", "Exit" }; localObject3 = "FramBuffer not supported"; (localJFrame = new JFrame((String)localObject3)).setUndecorated(true); localJFrame.setVisible(true); localJFrame.setAlwaysOnTop(true); localObject4 = Toolkit.getDefaultToolkit().getScreenSize(); localJFrame.setLocation(((Dimension)localObject4).width / 2, ((Dimension)localObject4).height / 2); k = 0; switch (JOptionPane.showOptionDialog(localJFrame, "FramBuffers are not supported on this Graphics Card.\nFramebuffers are used for effects like bloom.", (String)localObject3, 1, 0, null, arrayOfObject2, arrayOfObject2[0]))
/*     */         {
/*     */         case 0:
/*     */           int k;
/* 839 */           xu.U.a(Boolean.valueOf(false)); xu.x.a(Boolean.valueOf(false)); break;
/*     */         case 1:
/* 839 */           System.err.println("Exiting because unsupported frame buffer"); jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit(); } localJFrame.dispose(); } jdField_b_of_type_JavaLangString = GL11.glGetString(7937); } catch (LWJGLException localLWJGLException) { (localObject1 = localLWJGLException).printStackTrace(); throw new ErrorDialogException(localObject1.getClass() + ": " + ((LWJGLException)localObject1).getMessage()); } try { localObject1 = null; new File("./logs/").mkdir(); if ((localObject3 = new File("./logs/graphcisinfo.txt")).exists()) ((File)localObject3).delete(); boolean bool = (localObject3 = new File("./logs/graphcisinfo.txt")).createNewFile(); System.err.println("FILE CREATED: " + bool); (localObject4 = new BufferedWriter(new FileWriter((File)localObject3))).append("Running on thread: " + Thread.currentThread().getName()); ((BufferedWriter)localObject4).newLine(); ((BufferedWriter)localObject4).append("Adapter: " + Display.getAdapter()); ((BufferedWriter)localObject4).newLine(); ((BufferedWriter)localObject4).append("Driver Version: " + Display.getVersion()); ((BufferedWriter)localObject4).newLine(); localObject1 = GL11.glGetString(7936); ((BufferedWriter)localObject4).append("Vendor: " + (String)localObject1); ((BufferedWriter)localObject4).newLine(); localObject1 = GL11.glGetString(7938); ((BufferedWriter)localObject4).append("OpenGL Version: " + (String)localObject1); ((BufferedWriter)localObject4).newLine(); localObject1 = GL11.glGetString(7937); ((BufferedWriter)localObject4).append("Renderer: " + (String)localObject1); ((BufferedWriter)localObject4).newLine(); if (GLContext.getCapabilities().OpenGL20) { localObject1 = GL11.glGetString(35724); ((BufferedWriter)localObject4).append("GLSL Ver: " + (String)localObject1); } ((BufferedWriter)localObject4).flush(); ((BufferedWriter)localObject4).close(); } catch (IOException localIOException1) { localObject1 = null; localIOException1.printStackTrace(); } GlUtil.a(); System.out.println("[GLFrame] Open-GL initialized"); paramString.jdField_a_of_type_Xq.a(); paramString.f = false; new Thread(new xo(paramString)).start();
/*     */     try { xe.a().a(); paramString.f = true; } catch (Exception localException2) { localException2.printStackTrace(); throw new ErrorDialogException("Sound initialization failed. Please restart and turn sound off in options."); } try { if (jdField_a_of_type_OrgSchemaSchineNetworkClientClientState == null) System.err.println("waiting for state"); for (goto 839; !e; ) { if (jdField_a_of_type_Boolean) jdField_b_of_type_Boolean = true; if (jdField_b_of_type_Boolean) { System.err.println("Screen setting changed"); f(); paramString.e(); GL11.glViewport(0, 0, jdField_a_of_type_OrgLwjglOpenglDisplayMode.getWidth(), jdField_a_of_type_OrgLwjglOpenglDisplayMode.getHeight()); xe.a(); } if (Display.isCloseRequested()) { System.err.println("[GLFrame] Display Requested Close"); a(); } else if (Display.isActive()) { h.b("betweenDraw"); System.currentTimeMillis(); paramString.a(paramString.jdField_a_of_type_Xq); h.a("RENDERLOOP"); paramString.d(); h.b("RENDERLOOP"); if (xu.v.b()) Display.sync(60);  } else { System.currentTimeMillis(); paramString.a(paramString.jdField_a_of_type_Xq); if ((Display.isVisible()) || (Display.isDirty())) paramString.d(); Display.sync(60); } paramString.jdField_a_of_type_Xq.b(); h.a("RENDERLOOPINACT"); Display.update(true); if ((xe.a() != null) && (wV.a())) wV.a(); h.b("RENDERLOOPINACT"); if (jdField_b_of_type_Boolean) { jdField_b_of_type_Boolean = false; int m = 0; jdField_a_of_type_Boolean = false; }  }  } catch (Exception localException1) {
/* 839 */       System.err.println("[GLFRAME] THROWN: " + localException1.getClass() + " Now Printing StackTrace"); localException1.printStackTrace(); System.err.println("[GLFRAME] THROWN: " + localException1.getClass() + "Printing StackTrace DONE"); if ((localException1 instanceof SocketException)) b(new DisconnectException("You have been disconnected from the Server\nThis is caused either connection problem or a server crash.\n\nActualException: " + localException1.getClass().getSimpleName())); else a(localException1);  } System.out.println("[GLFrame] terminated frame: exiting program; finished: " + e);
/*     */     Object localObject2;
/*     */     try { Display.destroy(); xe.a(); if (Ak.a()) { xe.a(); Ak.b(); }  } catch (Exception localException3) { localObject2 = null; localException3.printStackTrace(); } System.err.println("Exiting because of terminated frame");
/*     */     try { jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.disconnect(); } catch (IOException localIOException2) { localObject2 = null; localIOException2.printStackTrace(); } jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.exit();
/*     */   }
/*     */ 
/*     */   private void a(xq paramxq)
/*     */   {
/* 844 */     if (!xe.a().a()) {
/* 845 */       return;
/*     */     }
/* 847 */     synchronized (jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.updateLock) { jdField_a_of_type_OrgSchemaSchineNetworkClientClientState.getController().update(paramxq);
/* 849 */       this.jdField_a_of_type_Xh.a(paramxq);
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void e() {
/* 856 */     boolean bool = true;
/*     */     try {
/* 858 */       bool = GLContext.getCapabilities().GL_ARB_multisample; } catch (Exception localException) {
/* 859 */       System.err.println("ARB_SUPPORTED COULD NOT BE RETRIEVED");
/* 860 */     }if (!jdField_b_of_type_Boolean) {
/* 861 */       Display.destroy();
/*     */ 
/* 863 */       if ((!g) && (jdField_a_of_type_OrgLwjglOpenglDisplayMode == null)) throw new AssertionError();
/*     */ 
/* 865 */       Display.setTitle(this.jdField_a_of_type_JavaLangString);
/*     */ 
/* 868 */       if (xu.I.a().equals("top-left")) {
/* 869 */         Display.setLocation(0, 0);
/*     */       }
/*     */     }
/* 872 */     Display.setDisplayMode(jdField_a_of_type_OrgLwjglOpenglDisplayMode);
/* 873 */     if (!jdField_b_of_type_Boolean) {
/*     */       try {
/* 875 */         if ((bool) && (((Integer)xu.S.a()).intValue() > 0)) {
/* 876 */           System.err.println("CHANGING PIXEL FORMAT!!!!");
/*     */ 
/* 884 */           Display.create(new PixelFormat(jdField_a_of_type_OrgLwjglOpenglDisplayMode.getBitsPerPixel(), 8, 24, 1, ((Integer)xu.S.a()).intValue()));
/*     */         }
/*     */         else
/*     */         {
/* 886 */           Display.create();
/*     */         }
/*     */       } catch (LWJGLException localLWJGLException) {
/* 889 */         Display.create();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 894 */     Display.setVSyncEnabled(xu.d.b());
/*     */ 
/* 896 */     GlUtil.f();
/*     */   }
/*     */ 
/*     */   private static void f()
/*     */   {
/*     */     xx localxx;
/* 901 */     boolean bool = xu.c.b(); int j = localxx.a.b; int i = (
/* 901 */       localxx = (xx)xu.b.a()).a.a;
/* 901 */     System.err.println("FULLSCREEN " + bool + "; " + i + " / " + j);
/*     */     try { if ((Display.getDisplayMode().getWidth() == i) && (Display.getDisplayMode().getHeight() == j) && (Display.isFullscreen() == bool)) { jdField_a_of_type_OrgLwjglOpenglDisplayMode = Display.getDisplayMode(); System.err.println("WARNING Displaymode already set!!!"); return; }  } catch (Exception localException) { localException.printStackTrace(); } Object localObject = null; if (bool) { DisplayMode[] arrayOfDisplayMode = Display.getAvailableDisplayModes(); int k = 0; for (int m = 0; m < arrayOfDisplayMode.length; m++) { DisplayMode localDisplayMode = arrayOfDisplayMode[m]; System.err.println("CHECKING DISPLAY MODE: " + localDisplayMode); if ((localDisplayMode.getWidth() == i) && (localDisplayMode.getHeight() == j)) { if (((localObject == null) || (localDisplayMode.getFrequency() >= k)) && ((localObject == null) || (localDisplayMode.getBitsPerPixel() > ((DisplayMode)localObject).getBitsPerPixel()))) k = (localObject = localDisplayMode).getFrequency(); if ((localDisplayMode.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) && (localDisplayMode.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) { localObject = localDisplayMode; System.err.println("Compatible DisplayMode found: " + localDisplayMode + "; fullsceen capable " + ((DisplayMode)localObject).isFullscreenCapable()); break; }  }  } } else {
/* 901 */       localObject = new DisplayMode(i, j); } if (localObject == null) { localObject = new DisplayMode(i, j); System.out.println("Failed to find value mode: " + i + "x" + j + " fs=" + bool + ": FallBackFullscreen Supported: " + ((DisplayMode)localObject).isFullscreenCapable()); } Display.setDisplayMode(xm.jdField_a_of_type_OrgLwjglOpenglDisplayMode = localObject); Display.setFullscreen((bool) && (jdField_a_of_type_OrgLwjglOpenglDisplayMode.isFullscreenCapable()));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xm
 * JD-Core Version:    0.6.2
 */