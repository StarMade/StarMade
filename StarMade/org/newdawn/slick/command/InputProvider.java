/*   1:    */package org.newdawn.slick.command;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collection;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map.Entry;
/*   9:    */import java.util.Set;
/*  10:    */import org.newdawn.slick.Input;
/*  11:    */import org.newdawn.slick.util.InputAdapter;
/*  12:    */
/*  22:    */public class InputProvider
/*  23:    */{
/*  24:    */  private HashMap commands;
/*  25: 25 */  private ArrayList listeners = new ArrayList();
/*  26:    */  
/*  28:    */  private Input input;
/*  29:    */  
/*  31: 31 */  private HashMap commandState = new HashMap();
/*  32:    */  
/*  34: 34 */  private boolean active = true;
/*  35:    */  
/*  42:    */  public InputProvider(Input input)
/*  43:    */  {
/*  44: 44 */    this.input = input;
/*  45:    */    
/*  46: 46 */    input.addListener(new InputListenerImpl(null));
/*  47: 47 */    this.commands = new HashMap();
/*  48:    */  }
/*  49:    */  
/*  56:    */  public List getUniqueCommands()
/*  57:    */  {
/*  58: 58 */    List uniqueCommands = new ArrayList();
/*  59:    */    
/*  60: 60 */    for (Iterator it = this.commands.values().iterator(); it.hasNext();) {
/*  61: 61 */      Command command = (Command)it.next();
/*  62:    */      
/*  63: 63 */      if (!uniqueCommands.contains(command)) {
/*  64: 64 */        uniqueCommands.add(command);
/*  65:    */      }
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    return uniqueCommands;
/*  69:    */  }
/*  70:    */  
/*  78:    */  public List getControlsFor(Command command)
/*  79:    */  {
/*  80: 80 */    List controlsForCommand = new ArrayList();
/*  81:    */    
/*  82: 82 */    for (Iterator it = this.commands.entrySet().iterator(); it.hasNext();) {
/*  83: 83 */      Map.Entry entry = (Map.Entry)it.next();
/*  84: 84 */      Control key = (Control)entry.getKey();
/*  85: 85 */      Command value = (Command)entry.getValue();
/*  86:    */      
/*  87: 87 */      if (value == command) {
/*  88: 88 */        controlsForCommand.add(key);
/*  89:    */      }
/*  90:    */    }
/*  91: 91 */    return controlsForCommand;
/*  92:    */  }
/*  93:    */  
/*  99:    */  public void setActive(boolean active)
/* 100:    */  {
/* 101:101 */    this.active = active;
/* 102:    */  }
/* 103:    */  
/* 108:    */  public boolean isActive()
/* 109:    */  {
/* 110:110 */    return this.active;
/* 111:    */  }
/* 112:    */  
/* 119:    */  public void addListener(InputProviderListener listener)
/* 120:    */  {
/* 121:121 */    this.listeners.add(listener);
/* 122:    */  }
/* 123:    */  
/* 130:    */  public void removeListener(InputProviderListener listener)
/* 131:    */  {
/* 132:132 */    this.listeners.remove(listener);
/* 133:    */  }
/* 134:    */  
/* 142:    */  public void bindCommand(Control control, Command command)
/* 143:    */  {
/* 144:144 */    this.commands.put(control, command);
/* 145:    */    
/* 146:146 */    if (this.commandState.get(command) == null) {
/* 147:147 */      this.commandState.put(command, new CommandState(null));
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 155:    */  public void clearCommand(Command command)
/* 156:    */  {
/* 157:157 */    List controls = getControlsFor(command);
/* 158:    */    
/* 159:159 */    for (int i = 0; i < controls.size(); i++) {
/* 160:160 */      unbindCommand((Control)controls.get(i));
/* 161:    */    }
/* 162:    */  }
/* 163:    */  
/* 169:    */  public void unbindCommand(Control control)
/* 170:    */  {
/* 171:171 */    Command command = (Command)this.commands.remove(control);
/* 172:172 */    if ((command != null) && 
/* 173:173 */      (!this.commands.keySet().contains(command))) {
/* 174:174 */      this.commandState.remove(command);
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 185:    */  private CommandState getState(Command command)
/* 186:    */  {
/* 187:187 */    return (CommandState)this.commandState.get(command);
/* 188:    */  }
/* 189:    */  
/* 197:    */  public boolean isCommandControlDown(Command command)
/* 198:    */  {
/* 199:199 */    return getState(command).isDown();
/* 200:    */  }
/* 201:    */  
/* 209:    */  public boolean isCommandControlPressed(Command command)
/* 210:    */  {
/* 211:211 */    return getState(command).isPressed();
/* 212:    */  }
/* 213:    */  
/* 220:    */  protected void firePressed(Command command)
/* 221:    */  {
/* 222:222 */    getState(command).down = true;
/* 223:223 */    getState(command).pressed = true;
/* 224:    */    
/* 225:225 */    if (!isActive()) {
/* 226:226 */      return;
/* 227:    */    }
/* 228:    */    
/* 229:229 */    for (int i = 0; i < this.listeners.size(); i++) {
/* 230:230 */      ((InputProviderListener)this.listeners.get(i)).controlPressed(command);
/* 231:    */    }
/* 232:    */  }
/* 233:    */  
/* 240:    */  protected void fireReleased(Command command)
/* 241:    */  {
/* 242:242 */    getState(command).down = false;
/* 243:    */    
/* 244:244 */    if (!isActive()) {
/* 245:245 */      return;
/* 246:    */    }
/* 247:    */    
/* 248:248 */    for (int i = 0; i < this.listeners.size(); i++) {
/* 249:249 */      ((InputProviderListener)this.listeners.get(i)).controlReleased(command);
/* 250:    */    }
/* 251:    */  }
/* 252:    */  
/* 256:    */  private class CommandState
/* 257:    */  {
/* 258:    */    private boolean down;
/* 259:    */    
/* 262:    */    private boolean pressed;
/* 263:    */    
/* 266:    */    private CommandState() {}
/* 267:    */    
/* 270:    */    public boolean isPressed()
/* 271:    */    {
/* 272:272 */      if (this.pressed) {
/* 273:273 */        this.pressed = false;
/* 274:274 */        return true;
/* 275:    */      }
/* 276:    */      
/* 277:277 */      return false;
/* 278:    */    }
/* 279:    */    
/* 284:    */    public boolean isDown()
/* 285:    */    {
/* 286:286 */      return this.down;
/* 287:    */    }
/* 288:    */  }
/* 289:    */  
/* 292:    */  private class InputListenerImpl
/* 293:    */    extends InputAdapter
/* 294:    */  {
/* 295:    */    private InputListenerImpl() {}
/* 296:    */    
/* 298:    */    public boolean isAcceptingInput()
/* 299:    */    {
/* 300:300 */      return true;
/* 301:    */    }
/* 302:    */    
/* 305:    */    public void keyPressed(int key, char c)
/* 306:    */    {
/* 307:307 */      Command command = (Command)InputProvider.this.commands.get(new KeyControl(key));
/* 308:308 */      if (command != null) {
/* 309:309 */        InputProvider.this.firePressed(command);
/* 310:    */      }
/* 311:    */    }
/* 312:    */    
/* 315:    */    public void keyReleased(int key, char c)
/* 316:    */    {
/* 317:317 */      Command command = (Command)InputProvider.this.commands.get(new KeyControl(key));
/* 318:318 */      if (command != null) {
/* 319:319 */        InputProvider.this.fireReleased(command);
/* 320:    */      }
/* 321:    */    }
/* 322:    */    
/* 325:    */    public void mousePressed(int button, int x, int y)
/* 326:    */    {
/* 327:327 */      Command command = (Command)InputProvider.this.commands.get(new MouseButtonControl(button));
/* 328:    */      
/* 329:329 */      if (command != null) {
/* 330:330 */        InputProvider.this.firePressed(command);
/* 331:    */      }
/* 332:    */    }
/* 333:    */    
/* 336:    */    public void mouseReleased(int button, int x, int y)
/* 337:    */    {
/* 338:338 */      Command command = (Command)InputProvider.this.commands.get(new MouseButtonControl(button));
/* 339:    */      
/* 340:340 */      if (command != null) {
/* 341:341 */        InputProvider.this.fireReleased(command);
/* 342:    */      }
/* 343:    */    }
/* 344:    */    
/* 347:    */    public void controllerLeftPressed(int controller)
/* 348:    */    {
/* 349:349 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
/* 350:    */      
/* 352:352 */      if (command != null) {
/* 353:353 */        InputProvider.this.firePressed(command);
/* 354:    */      }
/* 355:    */    }
/* 356:    */    
/* 359:    */    public void controllerLeftReleased(int controller)
/* 360:    */    {
/* 361:361 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
/* 362:    */      
/* 364:364 */      if (command != null) {
/* 365:365 */        InputProvider.this.fireReleased(command);
/* 366:    */      }
/* 367:    */    }
/* 368:    */    
/* 371:    */    public void controllerRightPressed(int controller)
/* 372:    */    {
/* 373:373 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
/* 374:    */      
/* 376:376 */      if (command != null) {
/* 377:377 */        InputProvider.this.firePressed(command);
/* 378:    */      }
/* 379:    */    }
/* 380:    */    
/* 383:    */    public void controllerRightReleased(int controller)
/* 384:    */    {
/* 385:385 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
/* 386:    */      
/* 388:388 */      if (command != null) {
/* 389:389 */        InputProvider.this.fireReleased(command);
/* 390:    */      }
/* 391:    */    }
/* 392:    */    
/* 395:    */    public void controllerUpPressed(int controller)
/* 396:    */    {
/* 397:397 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
/* 398:    */      
/* 400:400 */      if (command != null) {
/* 401:401 */        InputProvider.this.firePressed(command);
/* 402:    */      }
/* 403:    */    }
/* 404:    */    
/* 406:    */    public void controllerUpReleased(int controller)
/* 407:    */    {
/* 408:408 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
/* 409:    */      
/* 411:411 */      if (command != null) {
/* 412:412 */        InputProvider.this.fireReleased(command);
/* 413:    */      }
/* 414:    */    }
/* 415:    */    
/* 418:    */    public void controllerDownPressed(int controller)
/* 419:    */    {
/* 420:420 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
/* 421:    */      
/* 423:423 */      if (command != null) {
/* 424:424 */        InputProvider.this.firePressed(command);
/* 425:    */      }
/* 426:    */    }
/* 427:    */    
/* 430:    */    public void controllerDownReleased(int controller)
/* 431:    */    {
/* 432:432 */      Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
/* 433:    */      
/* 435:435 */      if (command != null) {
/* 436:436 */        InputProvider.this.fireReleased(command);
/* 437:    */      }
/* 438:    */    }
/* 439:    */    
/* 443:    */    public void controllerButtonPressed(int controller, int button)
/* 444:    */    {
/* 445:445 */      Command command = (Command)InputProvider.this.commands.get(new ControllerButtonControl(controller, button));
/* 446:    */      
/* 447:447 */      if (command != null) {
/* 448:448 */        InputProvider.this.firePressed(command);
/* 449:    */      }
/* 450:    */    }
/* 451:    */    
/* 455:    */    public void controllerButtonReleased(int controller, int button)
/* 456:    */    {
/* 457:457 */      Command command = (Command)InputProvider.this.commands.get(new ControllerButtonControl(controller, button));
/* 458:    */      
/* 459:459 */      if (command != null) {
/* 460:460 */        InputProvider.this.fireReleased(command);
/* 461:    */      }
/* 462:    */    }
/* 463:    */  }
/* 464:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.InputProvider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */