package org.lwjgl.util.jinput;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.util.plugins.Plugin;

public class LWJGLEnvironmentPlugin
  extends ControllerEnvironment
  implements Plugin
{
  private final Controller[] controllers = { new LWJGLKeyboard(), new LWJGLMouse() };
  
  public Controller[] getControllers()
  {
    return this.controllers;
  }
  
  public boolean isSupported()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLEnvironmentPlugin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */