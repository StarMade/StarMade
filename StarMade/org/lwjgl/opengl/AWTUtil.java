package org.lwjgl.opengl;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.IllegalComponentStateException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

final class AWTUtil
{
  public static boolean hasWheel()
  {
    return true;
  }
  
  public static int getButtonCount()
  {
    return 3;
  }
  
  public static int getNativeCursorCapabilities()
  {
    if ((LWJGLUtil.getPlatform() != 2) || (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4)))
    {
      int cursor_colors = Toolkit.getDefaultToolkit().getMaximumCursorColors();
      boolean supported = (cursor_colors >= 32767) && (getMaxCursorSize() > 0);
      int caps = supported ? 3 : 4;
      return caps;
    }
    return 0;
  }
  
  public static Robot createRobot(Component component)
  {
    try
    {
      (Robot)AccessController.doPrivileged(new PrivilegedExceptionAction()
      {
        public Robot run()
          throws Exception
        {
          return new Robot(this.val$component.getGraphicsConfiguration().getDevice());
        }
      });
    }
    catch (PrivilegedActionException local_e)
    {
      LWJGLUtil.log("Got exception while creating robot: " + local_e.getCause());
    }
    return null;
  }
  
  private static int transformY(Component component, int local_y)
  {
    return component.getHeight() - 1 - local_y;
  }
  
  private static Point getPointerLocation(Component component)
  {
    try
    {
      GraphicsConfiguration config = component.getGraphicsConfiguration();
      if (config != null)
      {
        PointerInfo pointer_info = (PointerInfo)AccessController.doPrivileged(new PrivilegedExceptionAction()
        {
          public PointerInfo run()
            throws Exception
          {
            return MouseInfo.getPointerInfo();
          }
        });
        GraphicsDevice device = pointer_info.getDevice();
        if (device == config.getDevice()) {
          return pointer_info.getLocation();
        }
        return null;
      }
    }
    catch (Exception config)
    {
      LWJGLUtil.log("Failed to query pointer location: " + config.getCause());
    }
    return null;
  }
  
  public static Point getCursorPosition(Component component)
  {
    try
    {
      Point pointer_location = getPointerLocation(component);
      if (pointer_location != null)
      {
        Point location = component.getLocationOnScreen();
        pointer_location.translate(-location.x, -location.y);
        pointer_location.move(pointer_location.x, transformY(component, pointer_location.y));
        return pointer_location;
      }
    }
    catch (IllegalComponentStateException pointer_location)
    {
      LWJGLUtil.log("Failed to set cursor position: " + pointer_location);
    }
    catch (NoClassDefFoundError pointer_location)
    {
      LWJGLUtil.log("Failed to query cursor position: " + pointer_location);
    }
    return null;
  }
  
  public static void setCursorPosition(Component component, Robot robot, int local_x, int local_y)
  {
    if (robot != null) {
      try
      {
        Point location = component.getLocationOnScreen();
        int transformed_x = location.x + local_x;
        int transformed_y = location.y + transformY(component, local_y);
        robot.mouseMove(transformed_x, transformed_y);
      }
      catch (IllegalComponentStateException location)
      {
        LWJGLUtil.log("Failed to set cursor position: " + location);
      }
    }
  }
  
  public static int getMinCursorSize()
  {
    Dimension min_size = Toolkit.getDefaultToolkit().getBestCursorSize(0, 0);
    return Math.max(min_size.width, min_size.height);
  }
  
  public static int getMaxCursorSize()
  {
    Dimension max_size = Toolkit.getDefaultToolkit().getBestCursorSize(10000, 10000);
    return Math.min(max_size.width, max_size.height);
  }
  
  public static Cursor createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
    throws LWJGLException
  {
    BufferedImage cursor_image = new BufferedImage(width, height, 2);
    int[] pixels = new int[images.remaining()];
    int old_position = images.position();
    images.get(pixels);
    images.position(old_position);
    cursor_image.setRGB(0, 0, width, height, pixels, 0, width);
    return Toolkit.getDefaultToolkit().createCustomCursor(cursor_image, new Point(xHotspot, yHotspot), "LWJGL Custom cursor");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.AWTUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */