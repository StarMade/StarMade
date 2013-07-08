package org.lwjgl.util;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.DisplayMode;

public final class Display
{
  private static final boolean DEBUG = false;
  
  public static DisplayMode[] getAvailableDisplayModes(int minWidth, int minHeight, int maxWidth, int maxHeight, int minBPP, int maxBPP, int minFreq, int maxFreq)
    throws LWJGLException
  {
    DisplayMode[] modes = org.lwjgl.opengl.Display.getAvailableDisplayModes();
    if (LWJGLUtil.DEBUG)
    {
      System.out.println("Available screen modes:");
      for (DisplayMode mode : modes) {
        System.out.println(mode);
      }
    }
    ArrayList<DisplayMode> arr$ = new ArrayList(modes.length);
    for (int len$ = 0; len$ < modes.length; len$++)
    {
      assert (modes[len$] != null) : ("" + len$ + " " + modes.length);
      if (((minWidth == -1) || (modes[len$].getWidth() >= minWidth)) && ((maxWidth == -1) || (modes[len$].getWidth() <= maxWidth)) && ((minHeight == -1) || (modes[len$].getHeight() >= minHeight)) && ((maxHeight == -1) || (modes[len$].getHeight() <= maxHeight)) && ((minBPP == -1) || (modes[len$].getBitsPerPixel() >= minBPP)) && ((maxBPP == -1) || (modes[len$].getBitsPerPixel() <= maxBPP)) && ((modes[len$].getFrequency() == 0) || (((minFreq == -1) || (modes[len$].getFrequency() >= minFreq)) && ((maxFreq == -1) || (modes[len$].getFrequency() <= maxFreq))))) {
        arr$.add(modes[len$]);
      }
    }
    DisplayMode[] len$ = new DisplayMode[arr$.size()];
    arr$.toArray(len$);
    if (LWJGLUtil.DEBUG) {}
    return len$;
  }
  
  public static DisplayMode setDisplayMode(DisplayMode[] local_dm, String[] param)
    throws Exception
  {
    Arrays.sort(local_dm, new Comparator()
    {
      final Display.1FieldAccessor[] accessors = new Display.1FieldAccessor[this.val$param.length];
      
      public int compare(DisplayMode dm1, DisplayMode dm2)
      {
        for (Display.1FieldAccessor accessor : this.accessors)
        {
          int local_f1 = accessor.getInt(dm1);
          int local_f2 = accessor.getInt(dm2);
          if ((accessor.usePreferred) && (local_f1 != local_f2))
          {
            if (local_f1 == accessor.preferred) {
              return -1;
            }
            if (local_f2 == accessor.preferred) {
              return 1;
            }
            int absf1 = Math.abs(local_f1 - accessor.preferred);
            int absf2 = Math.abs(local_f2 - accessor.preferred);
            if (absf1 < absf2) {
              return -1;
            }
            if (absf1 > absf2) {
              return 1;
            }
          }
          else
          {
            if (local_f1 < local_f2) {
              return accessor.order;
            }
            if (local_f1 != local_f2) {
              return -accessor.order;
            }
          }
        }
        return 0;
      }
    });
    if (LWJGLUtil.DEBUG)
    {
      System.out.println("Sorted display modes:");
      for (DisplayMode aDm : local_dm) {
        System.out.println(aDm);
      }
    }
    DisplayMode[] arr$ = local_dm;
    int len$ = arr$.length;
    int local_i$ = 0;
    while (local_i$ < len$)
    {
      DisplayMode aDm = arr$[local_i$];
      try
      {
        if (LWJGLUtil.DEBUG) {
          System.out.println("Attempting to set displaymode: " + aDm);
        }
        org.lwjgl.opengl.Display.setDisplayMode(aDm);
        return aDm;
      }
      catch (Exception local_e)
      {
        if (LWJGLUtil.DEBUG)
        {
          System.out.println("Failed to set display mode to " + aDm);
          local_e.printStackTrace();
        }
        local_i$++;
      }
    }
    throw new Exception("Failed to set display mode.");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.Display
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */