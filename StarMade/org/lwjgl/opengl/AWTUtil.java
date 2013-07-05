/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.IllegalComponentStateException;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.PointerInfo;
/*     */ import java.awt.Robot;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.nio.IntBuffer;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ final class AWTUtil
/*     */ {
/*     */   public static boolean hasWheel()
/*     */   {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   public static int getButtonCount() {
/*  64 */     return 3;
/*     */   }
/*     */ 
/*     */   public static int getNativeCursorCapabilities() {
/*  68 */     if ((LWJGLUtil.getPlatform() != 2) || (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4))) {
/*  69 */       int cursor_colors = Toolkit.getDefaultToolkit().getMaximumCursorColors();
/*  70 */       boolean supported = (cursor_colors >= 32767) && (getMaxCursorSize() > 0);
/*  71 */       int caps = supported ? 3 : 4;
/*  72 */       return caps;
/*     */     }
/*     */ 
/*  84 */     return 0;
/*     */   }
/*     */ 
/*     */   public static Robot createRobot(Component component)
/*     */   {
/*     */     try {
/*  90 */       return (Robot)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */         public Robot run() throws Exception {
/*  92 */           return new Robot(this.val$component.getGraphicsConfiguration().getDevice());
/*     */         } } );
/*     */     }
/*     */     catch (PrivilegedActionException e) {
/*  96 */       LWJGLUtil.log("Got exception while creating robot: " + e.getCause());
/*  97 */     }return null;
/*     */   }
/*     */ 
/*     */   private static int transformY(Component component, int y)
/*     */   {
/* 102 */     return component.getHeight() - 1 - y;
/*     */   }
/*     */ 
/*     */   private static Point getPointerLocation(Component component)
/*     */   {
/*     */     try
/*     */     {
/* 112 */       GraphicsConfiguration config = component.getGraphicsConfiguration();
/* 113 */       if (config != null) {
/* 114 */         PointerInfo pointer_info = (PointerInfo)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*     */           public PointerInfo run() throws Exception {
/* 116 */             return MouseInfo.getPointerInfo();
/*     */           }
/*     */         });
/* 119 */         GraphicsDevice device = pointer_info.getDevice();
/* 120 */         if (device == config.getDevice()) {
/* 121 */           return pointer_info.getLocation();
/*     */         }
/* 123 */         return null;
/*     */       }
/*     */     } catch (Exception e) {
/* 126 */       LWJGLUtil.log("Failed to query pointer location: " + e.getCause());
/*     */     }
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */   public static Point getCursorPosition(Component component)
/*     */   {
/*     */     try
/*     */     {
/* 137 */       Point pointer_location = getPointerLocation(component);
/* 138 */       if (pointer_location != null) {
/* 139 */         Point location = component.getLocationOnScreen();
/* 140 */         pointer_location.translate(-location.x, -location.y);
/* 141 */         pointer_location.move(pointer_location.x, transformY(component, pointer_location.y));
/* 142 */         return pointer_location;
/*     */       }
/*     */     } catch (IllegalComponentStateException e) {
/* 145 */       LWJGLUtil.log("Failed to set cursor position: " + e);
/*     */     } catch (NoClassDefFoundError e) {
/* 147 */       LWJGLUtil.log("Failed to query cursor position: " + e);
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setCursorPosition(Component component, Robot robot, int x, int y) {
/* 153 */     if (robot != null)
/*     */       try {
/* 155 */         Point location = component.getLocationOnScreen();
/* 156 */         int transformed_x = location.x + x;
/* 157 */         int transformed_y = location.y + transformY(component, y);
/* 158 */         robot.mouseMove(transformed_x, transformed_y);
/*     */       } catch (IllegalComponentStateException e) {
/* 160 */         LWJGLUtil.log("Failed to set cursor position: " + e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static int getMinCursorSize()
/*     */   {
/* 166 */     Dimension min_size = Toolkit.getDefaultToolkit().getBestCursorSize(0, 0);
/* 167 */     return Math.max(min_size.width, min_size.height);
/*     */   }
/*     */ 
/*     */   public static int getMaxCursorSize() {
/* 171 */     Dimension max_size = Toolkit.getDefaultToolkit().getBestCursorSize(10000, 10000);
/* 172 */     return Math.min(max_size.width, max_size.height);
/*     */   }
/*     */ 
/*     */   public static Cursor createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException
/*     */   {
/* 177 */     BufferedImage cursor_image = new BufferedImage(width, height, 2);
/* 178 */     int[] pixels = new int[images.remaining()];
/* 179 */     int old_position = images.position();
/* 180 */     images.get(pixels);
/* 181 */     images.position(old_position);
/* 182 */     cursor_image.setRGB(0, 0, width, height, pixels, 0, width);
/* 183 */     return Toolkit.getDefaultToolkit().createCustomCursor(cursor_image, new Point(xHotspot, yHotspot), "LWJGL Custom cursor");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AWTUtil
 * JD-Core Version:    0.6.2
 */