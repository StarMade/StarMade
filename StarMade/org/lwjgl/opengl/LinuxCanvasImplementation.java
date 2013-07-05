/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ final class LinuxCanvasImplementation
/*     */   implements AWTCanvasImplementation
/*     */ {
/*     */   static int getScreenFromDevice(GraphicsDevice device)
/*     */     throws LWJGLException
/*     */   {
/*     */     try
/*     */     {
/*  53 */       Method getScreen_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Method run() throws Exception {
/*  55 */           return this.val$device.getClass().getMethod("getScreen", new Class[0]);
/*     */         }
/*     */       });
/*  58 */       Integer screen = (Integer)getScreen_method.invoke(device, new Object[0]);
/*  59 */       return screen.intValue();
/*     */     } catch (Exception e) {
/*  61 */       throw new LWJGLException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static int getVisualIDFromConfiguration(GraphicsConfiguration configuration) throws LWJGLException {
/*     */     try {
/*  67 */       Method getVisual_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Method run() throws Exception {
/*  69 */           return this.val$configuration.getClass().getMethod("getVisual", new Class[0]);
/*     */         }
/*     */       });
/*  72 */       Integer visual = (Integer)getVisual_method.invoke(configuration, new Object[0]);
/*  73 */       return visual.intValue();
/*     */     } catch (Exception e) {
/*  75 */       throw new LWJGLException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/*  80 */     return new LinuxAWTGLCanvasPeerInfo(component);
/*     */   }
/*     */ 
/*     */   public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
/*     */     throws LWJGLException
/*     */   {
/*     */     try
/*     */     {
/*  90 */       int screen = getScreenFromDevice(device);
/*  91 */       int visual_id_matching_format = findVisualIDFromFormat(screen, pixel_format);
/*  92 */       GraphicsConfiguration[] configurations = device.getConfigurations();
/*  93 */       for (GraphicsConfiguration configuration : configurations) {
/*  94 */         int visual_id = getVisualIDFromConfiguration(configuration);
/*  95 */         if (visual_id == visual_id_matching_format)
/*  96 */           return configuration;
/*     */       }
/*     */     } catch (LWJGLException e) {
/*  99 */       LWJGLUtil.log("Got exception while trying to determine configuration: " + e);
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */   private static int findVisualIDFromFormat(int screen, PixelFormat pixel_format) throws LWJGLException {
/*     */     try {
/* 106 */       LinuxDisplay.lockAWT();
/*     */       try {
/* 108 */         GLContext.loadOpenGLLibrary();
/*     */         try {
/* 110 */           LinuxDisplay.incDisplay();
/* 111 */           return nFindVisualIDFromFormat(LinuxDisplay.getDisplay(), screen, pixel_format);
/*     */         } finally {
/*     */         }
/*     */       }
/*     */       finally {
/*     */       }
/*     */     }
/*     */     finally {
/* 119 */       LinuxDisplay.unlockAWT();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static native int nFindVisualIDFromFormat(long paramLong, int paramInt, PixelFormat paramPixelFormat)
/*     */     throws LWJGLException;
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxCanvasImplementation
 * JD-Core Version:    0.6.2
 */