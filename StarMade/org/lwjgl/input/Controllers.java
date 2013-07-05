/*     */ package org.lwjgl.input;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.java.games.input.Controller.Type;
/*     */ import net.java.games.input.ControllerEnvironment;
/*     */ import org.lwjgl.LWJGLException;
/*     */ 
/*     */ public class Controllers
/*     */ {
/*  47 */   private static ArrayList<JInputController> controllers = new ArrayList();
/*     */   private static int controllerCount;
/*  52 */   private static ArrayList<ControllerEvent> events = new ArrayList();
/*     */   private static ControllerEvent event;
/*     */   private static boolean created;
/*     */ 
/*     */   public static void create()
/*     */     throws LWJGLException
/*     */   {
/*  65 */     if (created)
/*  66 */       return;
/*     */     try
/*     */     {
/*  69 */       ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
/*     */ 
/*  71 */       net.java.games.input.Controller[] found = env.getControllers();
/*  72 */       ArrayList lollers = new ArrayList();
/*  73 */       for (net.java.games.input.Controller c : found) {
/*  74 */         if ((!c.getType().equals(Controller.Type.KEYBOARD)) && (!c.getType().equals(Controller.Type.MOUSE)))
/*     */         {
/*  76 */           lollers.add(c);
/*     */         }
/*     */       }
/*     */ 
/*  80 */       for (net.java.games.input.Controller c : lollers) {
/*  81 */         createController(c);
/*     */       }
/*     */ 
/*  84 */       created = true;
/*     */     } catch (Throwable e) {
/*  86 */       throw new LWJGLException("Failed to initialise controllers", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void createController(net.java.games.input.Controller c)
/*     */   {
/*  96 */     net.java.games.input.Controller[] subControllers = c.getControllers();
/*  97 */     if (subControllers.length == 0) {
/*  98 */       JInputController controller = new JInputController(controllerCount, c);
/*     */ 
/* 100 */       controllers.add(controller);
/* 101 */       controllerCount += 1;
/*     */     } else {
/* 103 */       for (net.java.games.input.Controller sub : subControllers)
/* 104 */         createController(sub);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Controller getController(int index)
/*     */   {
/* 116 */     return (Controller)controllers.get(index);
/*     */   }
/*     */ 
/*     */   public static int getControllerCount()
/*     */   {
/* 125 */     return controllers.size();
/*     */   }
/*     */ 
/*     */   public static void poll()
/*     */   {
/* 133 */     for (int i = 0; i < controllers.size(); i++)
/* 134 */       getController(i).poll();
/*     */   }
/*     */ 
/*     */   public static void clearEvents()
/*     */   {
/* 142 */     events.clear();
/*     */   }
/*     */ 
/*     */   public static boolean next()
/*     */   {
/* 151 */     if (events.size() == 0) {
/* 152 */       event = null;
/* 153 */       return false;
/*     */     }
/*     */ 
/* 156 */     event = (ControllerEvent)events.remove(0);
/*     */ 
/* 158 */     return event != null;
/*     */   }
/*     */ 
/*     */   public static boolean isCreated()
/*     */   {
/* 165 */     return created;
/*     */   }
/*     */ 
/*     */   public static void destroy()
/*     */   {
/*     */   }
/*     */ 
/*     */   public static Controller getEventSource()
/*     */   {
/* 196 */     return event.getSource();
/*     */   }
/*     */ 
/*     */   public static int getEventControlIndex()
/*     */   {
/* 205 */     return event.getControlIndex();
/*     */   }
/*     */ 
/*     */   public static boolean isEventButton()
/*     */   {
/* 214 */     return event.isButton();
/*     */   }
/*     */ 
/*     */   public static boolean isEventAxis()
/*     */   {
/* 223 */     return event.isAxis();
/*     */   }
/*     */ 
/*     */   public static boolean isEventXAxis()
/*     */   {
/* 232 */     return event.isXAxis();
/*     */   }
/*     */ 
/*     */   public static boolean isEventYAxis()
/*     */   {
/* 241 */     return event.isYAxis();
/*     */   }
/*     */ 
/*     */   public static boolean isEventPovX()
/*     */   {
/* 250 */     return event.isPovX();
/*     */   }
/*     */ 
/*     */   public static boolean isEventPovY()
/*     */   {
/* 259 */     return event.isPovY();
/*     */   }
/*     */ 
/*     */   public static long getEventNanoseconds()
/*     */   {
/* 268 */     return event.getTimeStamp();
/*     */   }
/*     */ 
/*     */   static void addEvent(ControllerEvent event)
/*     */   {
/* 277 */     if (event != null)
/* 278 */       events.add(event);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Controllers
 * JD-Core Version:    0.6.2
 */