package org.lwjgl.input;

import java.util.ArrayList;
import java.util.Iterator;
import net.java.games.input.Controller.Type;
import net.java.games.input.ControllerEnvironment;
import org.lwjgl.LWJGLException;

public class Controllers
{
  private static ArrayList<JInputController> controllers = new ArrayList();
  private static int controllerCount;
  private static ArrayList<ControllerEvent> events = new ArrayList();
  private static ControllerEvent event;
  private static boolean created;
  
  public static void create()
    throws LWJGLException
  {
    if (created) {
      return;
    }
    try
    {
      ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
      net.java.games.input.Controller[] found = env.getControllers();
      ArrayList<net.java.games.input.Controller> lollers = new ArrayList();
      for (net.java.games.input.Controller local_c : found) {
        if ((!local_c.getType().equals(Controller.Type.KEYBOARD)) && (!local_c.getType().equals(Controller.Type.MOUSE))) {
          lollers.add(local_c);
        }
      }
      Iterator arr$ = lollers.iterator();
      while (arr$.hasNext())
      {
        net.java.games.input.Controller len$ = (net.java.games.input.Controller)arr$.next();
        createController(len$);
      }
      created = true;
    }
    catch (Throwable env)
    {
      throw new LWJGLException("Failed to initialise controllers", env);
    }
  }
  
  private static void createController(net.java.games.input.Controller local_c)
  {
    net.java.games.input.Controller[] subControllers = local_c.getControllers();
    if (subControllers.length == 0)
    {
      JInputController controller = new JInputController(controllerCount, local_c);
      controllers.add(controller);
      controllerCount += 1;
    }
    else
    {
      for (net.java.games.input.Controller sub : subControllers) {
        createController(sub);
      }
    }
  }
  
  public static Controller getController(int index)
  {
    return (Controller)controllers.get(index);
  }
  
  public static int getControllerCount()
  {
    return controllers.size();
  }
  
  public static void poll()
  {
    for (int local_i = 0; local_i < controllers.size(); local_i++) {
      getController(local_i).poll();
    }
  }
  
  public static void clearEvents()
  {
    events.clear();
  }
  
  public static boolean next()
  {
    if (events.size() == 0)
    {
      event = null;
      return false;
    }
    event = (ControllerEvent)events.remove(0);
    return event != null;
  }
  
  public static boolean isCreated()
  {
    return created;
  }
  
  public static void destroy() {}
  
  public static Controller getEventSource()
  {
    return event.getSource();
  }
  
  public static int getEventControlIndex()
  {
    return event.getControlIndex();
  }
  
  public static boolean isEventButton()
  {
    return event.isButton();
  }
  
  public static boolean isEventAxis()
  {
    return event.isAxis();
  }
  
  public static boolean isEventXAxis()
  {
    return event.isXAxis();
  }
  
  public static boolean isEventYAxis()
  {
    return event.isYAxis();
  }
  
  public static boolean isEventPovX()
  {
    return event.isPovX();
  }
  
  public static boolean isEventPovY()
  {
    return event.isPovY();
  }
  
  public static long getEventNanoseconds()
  {
    return event.getTimeStamp();
  }
  
  static void addEvent(ControllerEvent event)
  {
    if (event != null) {
      events.add(event);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.input.Controllers
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */