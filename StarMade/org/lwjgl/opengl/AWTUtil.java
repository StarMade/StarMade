/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.Cursor;
/*   5:    */import java.awt.Dimension;
/*   6:    */import java.awt.GraphicsConfiguration;
/*   7:    */import java.awt.GraphicsDevice;
/*   8:    */import java.awt.IllegalComponentStateException;
/*   9:    */import java.awt.MouseInfo;
/*  10:    */import java.awt.Point;
/*  11:    */import java.awt.PointerInfo;
/*  12:    */import java.awt.Robot;
/*  13:    */import java.awt.Toolkit;
/*  14:    */import java.awt.image.BufferedImage;
/*  15:    */import java.nio.IntBuffer;
/*  16:    */import java.security.AccessController;
/*  17:    */import java.security.PrivilegedActionException;
/*  18:    */import java.security.PrivilegedExceptionAction;
/*  19:    */import org.lwjgl.LWJGLException;
/*  20:    */import org.lwjgl.LWJGLUtil;
/*  21:    */
/*  56:    */final class AWTUtil
/*  57:    */{
/*  58:    */  public static boolean hasWheel()
/*  59:    */  {
/*  60: 60 */    return true;
/*  61:    */  }
/*  62:    */  
/*  63:    */  public static int getButtonCount() {
/*  64: 64 */    return 3;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public static int getNativeCursorCapabilities() {
/*  68: 68 */    if ((LWJGLUtil.getPlatform() != 2) || (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4))) {
/*  69: 69 */      int cursor_colors = Toolkit.getDefaultToolkit().getMaximumCursorColors();
/*  70: 70 */      boolean supported = (cursor_colors >= 32767) && (getMaxCursorSize() > 0);
/*  71: 71 */      int caps = supported ? 3 : 4;
/*  72: 72 */      return caps;
/*  73:    */    }
/*  74:    */    
/*  84: 84 */    return 0;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public static Robot createRobot(Component component)
/*  88:    */  {
/*  89:    */    try {
/*  90: 90 */      (Robot)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*  91:    */        public Robot run() throws Exception {
/*  92: 92 */          return new Robot(this.val$component.getGraphicsConfiguration().getDevice());
/*  93:    */        }
/*  94:    */      });
/*  95:    */    } catch (PrivilegedActionException e) {
/*  96: 96 */      LWJGLUtil.log("Got exception while creating robot: " + e.getCause()); }
/*  97: 97 */    return null;
/*  98:    */  }
/*  99:    */  
/* 100:    */  private static int transformY(Component component, int y)
/* 101:    */  {
/* 102:102 */    return component.getHeight() - 1 - y;
/* 103:    */  }
/* 104:    */  
/* 108:    */  private static Point getPointerLocation(Component component)
/* 109:    */  {
/* 110:    */    try
/* 111:    */    {
/* 112:112 */      GraphicsConfiguration config = component.getGraphicsConfiguration();
/* 113:113 */      if (config != null) {
/* 114:114 */        PointerInfo pointer_info = (PointerInfo)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/* 115:    */          public PointerInfo run() throws Exception {
/* 116:116 */            return MouseInfo.getPointerInfo();
/* 117:    */          }
/* 118:118 */        });
/* 119:119 */        GraphicsDevice device = pointer_info.getDevice();
/* 120:120 */        if (device == config.getDevice()) {
/* 121:121 */          return pointer_info.getLocation();
/* 122:    */        }
/* 123:123 */        return null;
/* 124:    */      }
/* 125:    */    } catch (Exception e) {
/* 126:126 */      LWJGLUtil.log("Failed to query pointer location: " + e.getCause());
/* 127:    */    }
/* 128:128 */    return null;
/* 129:    */  }
/* 130:    */  
/* 133:    */  public static Point getCursorPosition(Component component)
/* 134:    */  {
/* 135:    */    try
/* 136:    */    {
/* 137:137 */      Point pointer_location = getPointerLocation(component);
/* 138:138 */      if (pointer_location != null) {
/* 139:139 */        Point location = component.getLocationOnScreen();
/* 140:140 */        pointer_location.translate(-location.x, -location.y);
/* 141:141 */        pointer_location.move(pointer_location.x, transformY(component, pointer_location.y));
/* 142:142 */        return pointer_location;
/* 143:    */      }
/* 144:    */    } catch (IllegalComponentStateException e) {
/* 145:145 */      LWJGLUtil.log("Failed to set cursor position: " + e);
/* 146:    */    } catch (NoClassDefFoundError e) {
/* 147:147 */      LWJGLUtil.log("Failed to query cursor position: " + e);
/* 148:    */    }
/* 149:149 */    return null;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public static void setCursorPosition(Component component, Robot robot, int x, int y) {
/* 153:153 */    if (robot != null) {
/* 154:    */      try {
/* 155:155 */        Point location = component.getLocationOnScreen();
/* 156:156 */        int transformed_x = location.x + x;
/* 157:157 */        int transformed_y = location.y + transformY(component, y);
/* 158:158 */        robot.mouseMove(transformed_x, transformed_y);
/* 159:    */      } catch (IllegalComponentStateException e) {
/* 160:160 */        LWJGLUtil.log("Failed to set cursor position: " + e);
/* 161:    */      }
/* 162:    */    }
/* 163:    */  }
/* 164:    */  
/* 165:    */  public static int getMinCursorSize() {
/* 166:166 */    Dimension min_size = Toolkit.getDefaultToolkit().getBestCursorSize(0, 0);
/* 167:167 */    return Math.max(min_size.width, min_size.height);
/* 168:    */  }
/* 169:    */  
/* 170:    */  public static int getMaxCursorSize() {
/* 171:171 */    Dimension max_size = Toolkit.getDefaultToolkit().getBestCursorSize(10000, 10000);
/* 172:172 */    return Math.min(max_size.width, max_size.height);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public static Cursor createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException
/* 176:    */  {
/* 177:177 */    BufferedImage cursor_image = new BufferedImage(width, height, 2);
/* 178:178 */    int[] pixels = new int[images.remaining()];
/* 179:179 */    int old_position = images.position();
/* 180:180 */    images.get(pixels);
/* 181:181 */    images.position(old_position);
/* 182:182 */    cursor_image.setRGB(0, 0, width, height, pixels, 0, width);
/* 183:183 */    return Toolkit.getDefaultToolkit().createCustomCursor(cursor_image, new Point(xHotspot, yHotspot), "LWJGL Custom cursor");
/* 184:    */  }
/* 185:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AWTUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */