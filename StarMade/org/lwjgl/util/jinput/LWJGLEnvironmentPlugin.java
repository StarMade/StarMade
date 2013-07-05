/*    */ package org.lwjgl.util.jinput;
/*    */ 
/*    */ import net.java.games.input.Controller;
/*    */ import net.java.games.input.ControllerEnvironment;
/*    */ import net.java.games.util.plugins.Plugin;
/*    */ 
/*    */ public class LWJGLEnvironmentPlugin extends ControllerEnvironment
/*    */   implements Plugin
/*    */ {
/*    */   private final Controller[] controllers;
/*    */ 
/*    */   public LWJGLEnvironmentPlugin()
/*    */   {
/* 45 */     this.controllers = new Controller[] { new LWJGLKeyboard(), new LWJGLMouse() };
/*    */   }
/*    */ 
/*    */   public Controller[] getControllers() {
/* 49 */     return this.controllers;
/*    */   }
/*    */ 
/*    */   public boolean isSupported() {
/* 53 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLEnvironmentPlugin
 * JD-Core Version:    0.6.2
 */