/*   1:    */package org.newdawn.slick.state;
/*   2:    */
/*   3:    */import java.util.Collection;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.Set;
/*   7:    */import org.newdawn.slick.Game;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Input;
/*  11:    */import org.newdawn.slick.InputListener;
/*  12:    */import org.newdawn.slick.SlickException;
/*  13:    */import org.newdawn.slick.state.transition.EmptyTransition;
/*  14:    */import org.newdawn.slick.state.transition.Transition;
/*  15:    */
/*  20:    */public abstract class StateBasedGame
/*  21:    */  implements Game, InputListener
/*  22:    */{
/*  23: 23 */  private HashMap states = new HashMap();
/*  24:    */  
/*  26:    */  private GameState currentState;
/*  27:    */  
/*  29:    */  private GameState nextState;
/*  30:    */  
/*  32:    */  private GameContainer container;
/*  33:    */  
/*  35:    */  private String title;
/*  36:    */  
/*  37:    */  private Transition enterTransition;
/*  38:    */  
/*  39:    */  private Transition leaveTransition;
/*  40:    */  
/*  42:    */  public StateBasedGame(String name)
/*  43:    */  {
/*  44: 44 */    this.title = name;
/*  45:    */    
/*  46: 46 */    this.currentState = new BasicGameState() {
/*  47:    */      public int getID() {
/*  48: 48 */        return -1;
/*  49:    */      }
/*  50:    */      
/*  52:    */      public void init(GameContainer container, StateBasedGame game)
/*  53:    */        throws SlickException
/*  54:    */      {}
/*  55:    */      
/*  56:    */      public void render(StateBasedGame game, Graphics g)
/*  57:    */        throws SlickException
/*  58:    */      {}
/*  59:    */      
/*  60:    */      public void update(GameContainer container, StateBasedGame game, int delta)
/*  61:    */        throws SlickException
/*  62:    */      {}
/*  63:    */      
/*  64:    */      public void render(GameContainer container, StateBasedGame game, Graphics g)
/*  65:    */        throws SlickException
/*  66:    */      {}
/*  67:    */    };
/*  68:    */  }
/*  69:    */  
/*  70:    */  public void inputStarted() {}
/*  71:    */  
/*  72:    */  public int getStateCount()
/*  73:    */  {
/*  74: 74 */    return this.states.keySet().size();
/*  75:    */  }
/*  76:    */  
/*  81:    */  public int getCurrentStateID()
/*  82:    */  {
/*  83: 83 */    return this.currentState.getID();
/*  84:    */  }
/*  85:    */  
/*  90:    */  public GameState getCurrentState()
/*  91:    */  {
/*  92: 92 */    return this.currentState;
/*  93:    */  }
/*  94:    */  
/* 100:    */  public void setInput(Input input) {}
/* 101:    */  
/* 106:    */  public void addState(GameState state)
/* 107:    */  {
/* 108:108 */    this.states.put(new Integer(state.getID()), state);
/* 109:    */    
/* 110:110 */    if (this.currentState.getID() == -1) {
/* 111:111 */      this.currentState = state;
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 120:    */  public GameState getState(int id)
/* 121:    */  {
/* 122:122 */    return (GameState)this.states.get(new Integer(id));
/* 123:    */  }
/* 124:    */  
/* 129:    */  public void enterState(int id)
/* 130:    */  {
/* 131:131 */    enterState(id, new EmptyTransition(), new EmptyTransition());
/* 132:    */  }
/* 133:    */  
/* 140:    */  public void enterState(int id, Transition leave, Transition enter)
/* 141:    */  {
/* 142:142 */    if (leave == null) {
/* 143:143 */      leave = new EmptyTransition();
/* 144:    */    }
/* 145:145 */    if (enter == null) {
/* 146:146 */      enter = new EmptyTransition();
/* 147:    */    }
/* 148:148 */    this.leaveTransition = leave;
/* 149:149 */    this.enterTransition = enter;
/* 150:    */    
/* 151:151 */    this.nextState = getState(id);
/* 152:152 */    if (this.nextState == null) {
/* 153:153 */      throw new RuntimeException("No game state registered with the ID: " + id);
/* 154:    */    }
/* 155:    */    
/* 156:156 */    this.leaveTransition.init(this.currentState, this.nextState);
/* 157:    */  }
/* 158:    */  
/* 160:    */  public final void init(GameContainer container)
/* 161:    */    throws SlickException
/* 162:    */  {
/* 163:163 */    this.container = container;
/* 164:164 */    initStatesList(container);
/* 165:    */    
/* 166:166 */    Iterator gameStates = this.states.values().iterator();
/* 167:    */    
/* 168:168 */    while (gameStates.hasNext()) {
/* 169:169 */      GameState state = (GameState)gameStates.next();
/* 170:    */      
/* 171:171 */      state.init(container, this);
/* 172:    */    }
/* 173:    */    
/* 174:174 */    if (this.currentState != null) {
/* 175:175 */      this.currentState.enter(container, this);
/* 176:    */    }
/* 177:    */  }
/* 178:    */  
/* 182:    */  public abstract void initStatesList(GameContainer paramGameContainer)
/* 183:    */    throws SlickException;
/* 184:    */  
/* 188:    */  public final void render(GameContainer container, Graphics g)
/* 189:    */    throws SlickException
/* 190:    */  {
/* 191:191 */    preRenderState(container, g);
/* 192:    */    
/* 193:193 */    if (this.leaveTransition != null) {
/* 194:194 */      this.leaveTransition.preRender(this, container, g);
/* 195:195 */    } else if (this.enterTransition != null) {
/* 196:196 */      this.enterTransition.preRender(this, container, g);
/* 197:    */    }
/* 198:    */    
/* 199:199 */    this.currentState.render(container, this, g);
/* 200:    */    
/* 201:201 */    if (this.leaveTransition != null) {
/* 202:202 */      this.leaveTransition.postRender(this, container, g);
/* 203:203 */    } else if (this.enterTransition != null) {
/* 204:204 */      this.enterTransition.postRender(this, container, g);
/* 205:    */    }
/* 206:    */    
/* 207:207 */    postRenderState(container, g);
/* 208:    */  }
/* 209:    */  
/* 216:    */  protected void preRenderState(GameContainer container, Graphics g)
/* 217:    */    throws SlickException
/* 218:    */  {}
/* 219:    */  
/* 226:    */  protected void postRenderState(GameContainer container, Graphics g)
/* 227:    */    throws SlickException
/* 228:    */  {}
/* 229:    */  
/* 235:    */  public final void update(GameContainer container, int delta)
/* 236:    */    throws SlickException
/* 237:    */  {
/* 238:238 */    preUpdateState(container, delta);
/* 239:    */    
/* 240:240 */    if (this.leaveTransition != null) {
/* 241:241 */      this.leaveTransition.update(this, container, delta);
/* 242:242 */      if (this.leaveTransition.isComplete()) {
/* 243:243 */        this.currentState.leave(container, this);
/* 244:244 */        GameState prevState = this.currentState;
/* 245:245 */        this.currentState = this.nextState;
/* 246:246 */        this.nextState = null;
/* 247:247 */        this.leaveTransition = null;
/* 248:248 */        if (this.enterTransition == null) {
/* 249:249 */          this.currentState.enter(container, this);
/* 250:    */        } else {
/* 251:251 */          this.enterTransition.init(this.currentState, prevState);
/* 252:    */        }
/* 253:    */      } else {
/* 254:254 */        return;
/* 255:    */      }
/* 256:    */    }
/* 257:    */    
/* 258:258 */    if (this.enterTransition != null) {
/* 259:259 */      this.enterTransition.update(this, container, delta);
/* 260:260 */      if (this.enterTransition.isComplete()) {
/* 261:261 */        this.currentState.enter(container, this);
/* 262:262 */        this.enterTransition = null;
/* 263:    */      } else {
/* 264:264 */        return;
/* 265:    */      }
/* 266:    */    }
/* 267:    */    
/* 268:268 */    this.currentState.update(container, this, delta);
/* 269:    */    
/* 270:270 */    postUpdateState(container, delta);
/* 271:    */  }
/* 272:    */  
/* 280:    */  protected void preUpdateState(GameContainer container, int delta)
/* 281:    */    throws SlickException
/* 282:    */  {}
/* 283:    */  
/* 291:    */  protected void postUpdateState(GameContainer container, int delta)
/* 292:    */    throws SlickException
/* 293:    */  {}
/* 294:    */  
/* 301:    */  private boolean transitioning()
/* 302:    */  {
/* 303:303 */    return (this.leaveTransition != null) || (this.enterTransition != null);
/* 304:    */  }
/* 305:    */  
/* 308:    */  public boolean closeRequested()
/* 309:    */  {
/* 310:310 */    return true;
/* 311:    */  }
/* 312:    */  
/* 315:    */  public String getTitle()
/* 316:    */  {
/* 317:317 */    return this.title;
/* 318:    */  }
/* 319:    */  
/* 324:    */  public GameContainer getContainer()
/* 325:    */  {
/* 326:326 */    return this.container;
/* 327:    */  }
/* 328:    */  
/* 331:    */  public void controllerButtonPressed(int controller, int button)
/* 332:    */  {
/* 333:333 */    if (transitioning()) {
/* 334:334 */      return;
/* 335:    */    }
/* 336:    */    
/* 337:337 */    this.currentState.controllerButtonPressed(controller, button);
/* 338:    */  }
/* 339:    */  
/* 342:    */  public void controllerButtonReleased(int controller, int button)
/* 343:    */  {
/* 344:344 */    if (transitioning()) {
/* 345:345 */      return;
/* 346:    */    }
/* 347:    */    
/* 348:348 */    this.currentState.controllerButtonReleased(controller, button);
/* 349:    */  }
/* 350:    */  
/* 353:    */  public void controllerDownPressed(int controller)
/* 354:    */  {
/* 355:355 */    if (transitioning()) {
/* 356:356 */      return;
/* 357:    */    }
/* 358:    */    
/* 359:359 */    this.currentState.controllerDownPressed(controller);
/* 360:    */  }
/* 361:    */  
/* 364:    */  public void controllerDownReleased(int controller)
/* 365:    */  {
/* 366:366 */    if (transitioning()) {
/* 367:367 */      return;
/* 368:    */    }
/* 369:    */    
/* 370:370 */    this.currentState.controllerDownReleased(controller);
/* 371:    */  }
/* 372:    */  
/* 375:    */  public void controllerLeftPressed(int controller)
/* 376:    */  {
/* 377:377 */    if (transitioning()) {
/* 378:378 */      return;
/* 379:    */    }
/* 380:    */    
/* 381:381 */    this.currentState.controllerLeftPressed(controller);
/* 382:    */  }
/* 383:    */  
/* 386:    */  public void controllerLeftReleased(int controller)
/* 387:    */  {
/* 388:388 */    if (transitioning()) {
/* 389:389 */      return;
/* 390:    */    }
/* 391:    */    
/* 392:392 */    this.currentState.controllerLeftReleased(controller);
/* 393:    */  }
/* 394:    */  
/* 397:    */  public void controllerRightPressed(int controller)
/* 398:    */  {
/* 399:399 */    if (transitioning()) {
/* 400:400 */      return;
/* 401:    */    }
/* 402:    */    
/* 403:403 */    this.currentState.controllerRightPressed(controller);
/* 404:    */  }
/* 405:    */  
/* 408:    */  public void controllerRightReleased(int controller)
/* 409:    */  {
/* 410:410 */    if (transitioning()) {
/* 411:411 */      return;
/* 412:    */    }
/* 413:    */    
/* 414:414 */    this.currentState.controllerRightReleased(controller);
/* 415:    */  }
/* 416:    */  
/* 419:    */  public void controllerUpPressed(int controller)
/* 420:    */  {
/* 421:421 */    if (transitioning()) {
/* 422:422 */      return;
/* 423:    */    }
/* 424:    */    
/* 425:425 */    this.currentState.controllerUpPressed(controller);
/* 426:    */  }
/* 427:    */  
/* 430:    */  public void controllerUpReleased(int controller)
/* 431:    */  {
/* 432:432 */    if (transitioning()) {
/* 433:433 */      return;
/* 434:    */    }
/* 435:    */    
/* 436:436 */    this.currentState.controllerUpReleased(controller);
/* 437:    */  }
/* 438:    */  
/* 441:    */  public void keyPressed(int key, char c)
/* 442:    */  {
/* 443:443 */    if (transitioning()) {
/* 444:444 */      return;
/* 445:    */    }
/* 446:    */    
/* 447:447 */    this.currentState.keyPressed(key, c);
/* 448:    */  }
/* 449:    */  
/* 452:    */  public void keyReleased(int key, char c)
/* 453:    */  {
/* 454:454 */    if (transitioning()) {
/* 455:455 */      return;
/* 456:    */    }
/* 457:    */    
/* 458:458 */    this.currentState.keyReleased(key, c);
/* 459:    */  }
/* 460:    */  
/* 463:    */  public void mouseMoved(int oldx, int oldy, int newx, int newy)
/* 464:    */  {
/* 465:465 */    if (transitioning()) {
/* 466:466 */      return;
/* 467:    */    }
/* 468:    */    
/* 469:469 */    this.currentState.mouseMoved(oldx, oldy, newx, newy);
/* 470:    */  }
/* 471:    */  
/* 474:    */  public void mouseDragged(int oldx, int oldy, int newx, int newy)
/* 475:    */  {
/* 476:476 */    if (transitioning()) {
/* 477:477 */      return;
/* 478:    */    }
/* 479:    */    
/* 480:480 */    this.currentState.mouseDragged(oldx, oldy, newx, newy);
/* 481:    */  }
/* 482:    */  
/* 484:    */  public void mouseClicked(int button, int x, int y, int clickCount)
/* 485:    */  {
/* 486:486 */    if (transitioning()) {
/* 487:487 */      return;
/* 488:    */    }
/* 489:    */    
/* 490:490 */    this.currentState.mouseClicked(button, x, y, clickCount);
/* 491:    */  }
/* 492:    */  
/* 495:    */  public void mousePressed(int button, int x, int y)
/* 496:    */  {
/* 497:497 */    if (transitioning()) {
/* 498:498 */      return;
/* 499:    */    }
/* 500:    */    
/* 501:501 */    this.currentState.mousePressed(button, x, y);
/* 502:    */  }
/* 503:    */  
/* 506:    */  public void mouseReleased(int button, int x, int y)
/* 507:    */  {
/* 508:508 */    if (transitioning()) {
/* 509:509 */      return;
/* 510:    */    }
/* 511:    */    
/* 512:512 */    this.currentState.mouseReleased(button, x, y);
/* 513:    */  }
/* 514:    */  
/* 517:    */  public boolean isAcceptingInput()
/* 518:    */  {
/* 519:519 */    if (transitioning()) {
/* 520:520 */      return false;
/* 521:    */    }
/* 522:    */    
/* 523:523 */    return this.currentState.isAcceptingInput();
/* 524:    */  }
/* 525:    */  
/* 529:    */  public void inputEnded() {}
/* 530:    */  
/* 534:    */  public void mouseWheelMoved(int newValue)
/* 535:    */  {
/* 536:536 */    if (transitioning()) {
/* 537:537 */      return;
/* 538:    */    }
/* 539:    */    
/* 540:540 */    this.currentState.mouseWheelMoved(newValue);
/* 541:    */  }
/* 542:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.StateBasedGame
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */