/*  1:   */package org.lwjgl.util.jinput;
/*  2:   */
/*  3:   */import net.java.games.input.Controller;
/*  4:   */import net.java.games.input.ControllerEnvironment;
/*  5:   */import net.java.games.util.plugins.Plugin;
/*  6:   */
/* 37:   */public class LWJGLEnvironmentPlugin
/* 38:   */  extends ControllerEnvironment
/* 39:   */  implements Plugin
/* 40:   */{
/* 41:   */  private final Controller[] controllers;
/* 42:   */  
/* 43:   */  public LWJGLEnvironmentPlugin()
/* 44:   */  {
/* 45:45 */    this.controllers = new Controller[] { new LWJGLKeyboard(), new LWJGLMouse() };
/* 46:   */  }
/* 47:   */  
/* 48:   */  public Controller[] getControllers() {
/* 49:49 */    return this.controllers;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public boolean isSupported() {
/* 53:53 */    return true;
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLEnvironmentPlugin
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */