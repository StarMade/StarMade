/*   1:    */package org.lwjgl.input;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import net.java.games.input.Controller.Type;
/*   5:    */import net.java.games.input.ControllerEnvironment;
/*   6:    */import org.lwjgl.LWJGLException;
/*   7:    */
/*  45:    */public class Controllers
/*  46:    */{
/*  47: 47 */  private static ArrayList<JInputController> controllers = new ArrayList();
/*  48:    */  
/*  50:    */  private static int controllerCount;
/*  51:    */  
/*  52: 52 */  private static ArrayList<ControllerEvent> events = new ArrayList();
/*  53:    */  
/*  55:    */  private static ControllerEvent event;
/*  56:    */  
/*  58:    */  private static boolean created;
/*  59:    */  
/*  62:    */  public static void create()
/*  63:    */    throws LWJGLException
/*  64:    */  {
/*  65: 65 */    if (created) {
/*  66: 66 */      return;
/*  67:    */    }
/*  68:    */    try {
/*  69: 69 */      ControllerEnvironment env = ControllerEnvironment.getDefaultEnvironment();
/*  70:    */      
/*  71: 71 */      net.java.games.input.Controller[] found = env.getControllers();
/*  72: 72 */      ArrayList<net.java.games.input.Controller> lollers = new ArrayList();
/*  73: 73 */      for (net.java.games.input.Controller c : found) {
/*  74: 74 */        if ((!c.getType().equals(Controller.Type.KEYBOARD)) && (!c.getType().equals(Controller.Type.MOUSE)))
/*  75:    */        {
/*  76: 76 */          lollers.add(c);
/*  77:    */        }
/*  78:    */      }
/*  79:    */      
/*  80: 80 */      for (net.java.games.input.Controller c : lollers) {
/*  81: 81 */        createController(c);
/*  82:    */      }
/*  83:    */      
/*  84: 84 */      created = true;
/*  85:    */    } catch (Throwable e) {
/*  86: 86 */      throw new LWJGLException("Failed to initialise controllers", e);
/*  87:    */    }
/*  88:    */  }
/*  89:    */  
/*  94:    */  private static void createController(net.java.games.input.Controller c)
/*  95:    */  {
/*  96: 96 */    net.java.games.input.Controller[] subControllers = c.getControllers();
/*  97: 97 */    if (subControllers.length == 0) {
/*  98: 98 */      JInputController controller = new JInputController(controllerCount, c);
/*  99:    */      
/* 100:100 */      controllers.add(controller);
/* 101:101 */      controllerCount += 1;
/* 102:    */    } else {
/* 103:103 */      for (net.java.games.input.Controller sub : subControllers) {
/* 104:104 */        createController(sub);
/* 105:    */      }
/* 106:    */    }
/* 107:    */  }
/* 108:    */  
/* 114:    */  public static Controller getController(int index)
/* 115:    */  {
/* 116:116 */    return (Controller)controllers.get(index);
/* 117:    */  }
/* 118:    */  
/* 123:    */  public static int getControllerCount()
/* 124:    */  {
/* 125:125 */    return controllers.size();
/* 126:    */  }
/* 127:    */  
/* 131:    */  public static void poll()
/* 132:    */  {
/* 133:133 */    for (int i = 0; i < controllers.size(); i++) {
/* 134:134 */      getController(i).poll();
/* 135:    */    }
/* 136:    */  }
/* 137:    */  
/* 140:    */  public static void clearEvents()
/* 141:    */  {
/* 142:142 */    events.clear();
/* 143:    */  }
/* 144:    */  
/* 149:    */  public static boolean next()
/* 150:    */  {
/* 151:151 */    if (events.size() == 0) {
/* 152:152 */      event = null;
/* 153:153 */      return false;
/* 154:    */    }
/* 155:    */    
/* 156:156 */    event = (ControllerEvent)events.remove(0);
/* 157:    */    
/* 158:158 */    return event != null;
/* 159:    */  }
/* 160:    */  
/* 163:    */  public static boolean isCreated()
/* 164:    */  {
/* 165:165 */    return created;
/* 166:    */  }
/* 167:    */  
/* 180:    */  public static void destroy() {}
/* 181:    */  
/* 194:    */  public static Controller getEventSource()
/* 195:    */  {
/* 196:196 */    return event.getSource();
/* 197:    */  }
/* 198:    */  
/* 203:    */  public static int getEventControlIndex()
/* 204:    */  {
/* 205:205 */    return event.getControlIndex();
/* 206:    */  }
/* 207:    */  
/* 212:    */  public static boolean isEventButton()
/* 213:    */  {
/* 214:214 */    return event.isButton();
/* 215:    */  }
/* 216:    */  
/* 221:    */  public static boolean isEventAxis()
/* 222:    */  {
/* 223:223 */    return event.isAxis();
/* 224:    */  }
/* 225:    */  
/* 230:    */  public static boolean isEventXAxis()
/* 231:    */  {
/* 232:232 */    return event.isXAxis();
/* 233:    */  }
/* 234:    */  
/* 239:    */  public static boolean isEventYAxis()
/* 240:    */  {
/* 241:241 */    return event.isYAxis();
/* 242:    */  }
/* 243:    */  
/* 248:    */  public static boolean isEventPovX()
/* 249:    */  {
/* 250:250 */    return event.isPovX();
/* 251:    */  }
/* 252:    */  
/* 257:    */  public static boolean isEventPovY()
/* 258:    */  {
/* 259:259 */    return event.isPovY();
/* 260:    */  }
/* 261:    */  
/* 266:    */  public static long getEventNanoseconds()
/* 267:    */  {
/* 268:268 */    return event.getTimeStamp();
/* 269:    */  }
/* 270:    */  
/* 275:    */  static void addEvent(ControllerEvent event)
/* 276:    */  {
/* 277:277 */    if (event != null) {
/* 278:278 */      events.add(event);
/* 279:    */    }
/* 280:    */  }
/* 281:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.Controllers
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */