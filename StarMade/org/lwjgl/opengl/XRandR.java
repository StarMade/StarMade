package org.lwjgl.opengl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lwjgl.LWJGLUtil;

public class XRandR
{
  private static Screen[] current;
  private static Map<String, Screen[]> screens;
  private static final Pattern SCREEN_PATTERN1 = Pattern.compile("^(\\d+)x(\\d+)\\+(\\d+)\\+(\\d+)$");
  private static final Pattern SCREEN_PATTERN2 = Pattern.compile("^(\\d+)x(\\d+)$");
  
  private static void populate()
  {
    if (screens == null)
    {
      screens = new HashMap();
      try
      {
        Process local_p = Runtime.getRuntime().exec(new String[] { "xrandr", "-q" });
        List<Screen> currentList = new ArrayList();
        List<Screen> possibles = new ArrayList();
        String name = null;
        BufferedReader local_br = new BufferedReader(new InputStreamReader(local_p.getInputStream()));
        String line;
        while ((line = local_br.readLine()) != null)
        {
          line = line.trim();
          String[] local_sa = line.split("\\s+");
          if ("connected".equals(local_sa[1]))
          {
            if (name != null)
            {
              screens.put(name, possibles.toArray(new Screen[possibles.size()]));
              possibles.clear();
            }
            name = local_sa[0];
            parseScreen(currentList, name, local_sa[2]);
          }
          else if (Pattern.matches("\\d*x\\d*", local_sa[0]))
          {
            parseScreen(possibles, name, local_sa[0]);
          }
        }
        screens.put(name, possibles.toArray(new Screen[possibles.size()]));
        current = (Screen[])currentList.toArray(new Screen[currentList.size()]);
      }
      catch (Throwable local_p)
      {
        LWJGLUtil.log("Exception in XRandR.populate(): " + local_p.getMessage());
        screens.clear();
        current = new Screen[0];
      }
    }
  }
  
  public static Screen[] getConfiguration()
  {
    populate();
    return (Screen[])current.clone();
  }
  
  public static void setConfiguration(Screen... screens)
  {
    if (screens.length == 0) {
      throw new IllegalArgumentException("Must specify at least one screen");
    }
    List<String> cmd = new ArrayList();
    cmd.add("xrandr");
    for (Screen screen : current)
    {
      boolean found = false;
      for (Screen screen1 : screens) {
        if (screen1.name.equals(screen.name))
        {
          found = true;
          break;
        }
      }
      if (!found)
      {
        cmd.add("--output");
        cmd.add(screen.name);
        cmd.add("--off");
      }
    }
    for (Screen screen : screens) {
      screen.getArgs(cmd);
    }
    try
    {
      Process arr$ = Runtime.getRuntime().exec((String[])cmd.toArray(new String[cmd.size()]));
      BufferedReader len$ = new BufferedReader(new InputStreamReader(arr$.getInputStream()));
      String local_i$1;
      while ((local_i$1 = len$.readLine()) != null) {
        LWJGLUtil.log("Unexpected output from xrandr process: " + local_i$1);
      }
      current = screens;
    }
    catch (IOException arr$)
    {
      LWJGLUtil.log("XRandR exception in setConfiguration(): " + arr$.getMessage());
    }
  }
  
  public static String[] getScreenNames()
  {
    populate();
    return (String[])screens.keySet().toArray(new String[screens.size()]);
  }
  
  public static Screen[] getResolutions(String name)
  {
    populate();
    return (Screen[])((Screen[])screens.get(name)).clone();
  }
  
  private static void parseScreen(List<Screen> list, String name, String what)
  {
    Matcher local_m = SCREEN_PATTERN1.matcher(what);
    if (!local_m.matches())
    {
      local_m = SCREEN_PATTERN2.matcher(what);
      if (!local_m.matches())
      {
        LWJGLUtil.log("Did not match: " + what);
        return;
      }
    }
    int width = Integer.parseInt(local_m.group(1));
    int height = Integer.parseInt(local_m.group(2));
    int ypos;
    int xpos;
    int ypos;
    if (local_m.groupCount() > 3)
    {
      int xpos = Integer.parseInt(local_m.group(3));
      ypos = Integer.parseInt(local_m.group(4));
    }
    else
    {
      xpos = 0;
      ypos = 0;
    }
    list.add(new Screen(name, width, height, xpos, ypos, null));
  }
  
  public static class Screen
    implements Cloneable
  {
    public final String name;
    public final int width;
    public final int height;
    public int xPos;
    public int yPos;
    
    private Screen(String name, int width, int height, int xPos, int yPos)
    {
      this.name = name;
      this.width = width;
      this.height = height;
      this.xPos = xPos;
      this.yPos = yPos;
    }
    
    private void getArgs(List<String> argList)
    {
      argList.add("--output");
      argList.add(this.name);
      argList.add("--mode");
      argList.add(this.width + "x" + this.height);
      argList.add("--pos");
      argList.add(this.xPos + "x" + this.yPos);
    }
    
    public String toString()
    {
      return this.name + " " + this.width + "x" + this.height + " @ " + this.xPos + "x" + this.yPos;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.XRandR
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */