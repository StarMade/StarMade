/*     */ package org.newdawn.slick.state;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.newdawn.slick.Game;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Input;
/*     */ import org.newdawn.slick.InputListener;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.state.transition.EmptyTransition;
/*     */ import org.newdawn.slick.state.transition.Transition;
/*     */ 
/*     */ public abstract class StateBasedGame
/*     */   implements Game, InputListener
/*     */ {
/*  23 */   private HashMap states = new HashMap();
/*     */   private GameState currentState;
/*     */   private GameState nextState;
/*     */   private GameContainer container;
/*     */   private String title;
/*     */   private Transition enterTransition;
/*     */   private Transition leaveTransition;
/*     */ 
/*     */   public StateBasedGame(String name)
/*     */   {
/*  44 */     this.title = name;
/*     */ 
/*  46 */     this.currentState = new BasicGameState() {
/*     */       public int getID() {
/*  48 */         return -1;
/*     */       }
/*     */ 
/*     */       public void init(GameContainer container, StateBasedGame game) throws SlickException
/*     */       {
/*     */       }
/*     */ 
/*     */       public void render(StateBasedGame game, Graphics g) throws SlickException
/*     */       {
/*     */       }
/*     */ 
/*     */       public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
/*     */       {
/*     */       }
/*     */ 
/*     */       public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
/*     */       {
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public void inputStarted()
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getStateCount() {
/*  74 */     return this.states.keySet().size();
/*     */   }
/*     */ 
/*     */   public int getCurrentStateID()
/*     */   {
/*  83 */     return this.currentState.getID();
/*     */   }
/*     */ 
/*     */   public GameState getCurrentState()
/*     */   {
/*  92 */     return this.currentState;
/*     */   }
/*     */ 
/*     */   public void setInput(Input input)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void addState(GameState state)
/*     */   {
/* 108 */     this.states.put(new Integer(state.getID()), state);
/*     */ 
/* 110 */     if (this.currentState.getID() == -1)
/* 111 */       this.currentState = state;
/*     */   }
/*     */ 
/*     */   public GameState getState(int id)
/*     */   {
/* 122 */     return (GameState)this.states.get(new Integer(id));
/*     */   }
/*     */ 
/*     */   public void enterState(int id)
/*     */   {
/* 131 */     enterState(id, new EmptyTransition(), new EmptyTransition());
/*     */   }
/*     */ 
/*     */   public void enterState(int id, Transition leave, Transition enter)
/*     */   {
/* 142 */     if (leave == null) {
/* 143 */       leave = new EmptyTransition();
/*     */     }
/* 145 */     if (enter == null) {
/* 146 */       enter = new EmptyTransition();
/*     */     }
/* 148 */     this.leaveTransition = leave;
/* 149 */     this.enterTransition = enter;
/*     */ 
/* 151 */     this.nextState = getState(id);
/* 152 */     if (this.nextState == null) {
/* 153 */       throw new RuntimeException("No game state registered with the ID: " + id);
/*     */     }
/*     */ 
/* 156 */     this.leaveTransition.init(this.currentState, this.nextState);
/*     */   }
/*     */ 
/*     */   public final void init(GameContainer container)
/*     */     throws SlickException
/*     */   {
/* 163 */     this.container = container;
/* 164 */     initStatesList(container);
/*     */ 
/* 166 */     Iterator gameStates = this.states.values().iterator();
/*     */ 
/* 168 */     while (gameStates.hasNext()) {
/* 169 */       GameState state = (GameState)gameStates.next();
/*     */ 
/* 171 */       state.init(container, this);
/*     */     }
/*     */ 
/* 174 */     if (this.currentState != null)
/* 175 */       this.currentState.enter(container, this);
/*     */   }
/*     */ 
/*     */   public abstract void initStatesList(GameContainer paramGameContainer)
/*     */     throws SlickException;
/*     */ 
/*     */   public final void render(GameContainer container, Graphics g)
/*     */     throws SlickException
/*     */   {
/* 191 */     preRenderState(container, g);
/*     */ 
/* 193 */     if (this.leaveTransition != null)
/* 194 */       this.leaveTransition.preRender(this, container, g);
/* 195 */     else if (this.enterTransition != null) {
/* 196 */       this.enterTransition.preRender(this, container, g);
/*     */     }
/*     */ 
/* 199 */     this.currentState.render(container, this, g);
/*     */ 
/* 201 */     if (this.leaveTransition != null)
/* 202 */       this.leaveTransition.postRender(this, container, g);
/* 203 */     else if (this.enterTransition != null) {
/* 204 */       this.enterTransition.postRender(this, container, g);
/*     */     }
/*     */ 
/* 207 */     postRenderState(container, g);
/*     */   }
/*     */ 
/*     */   protected void preRenderState(GameContainer container, Graphics g)
/*     */     throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void postRenderState(GameContainer container, Graphics g)
/*     */     throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void update(GameContainer container, int delta)
/*     */     throws SlickException
/*     */   {
/* 238 */     preUpdateState(container, delta);
/*     */ 
/* 240 */     if (this.leaveTransition != null) {
/* 241 */       this.leaveTransition.update(this, container, delta);
/* 242 */       if (this.leaveTransition.isComplete()) {
/* 243 */         this.currentState.leave(container, this);
/* 244 */         GameState prevState = this.currentState;
/* 245 */         this.currentState = this.nextState;
/* 246 */         this.nextState = null;
/* 247 */         this.leaveTransition = null;
/* 248 */         if (this.enterTransition == null)
/* 249 */           this.currentState.enter(container, this);
/*     */         else
/* 251 */           this.enterTransition.init(this.currentState, prevState);
/*     */       }
/*     */       else {
/* 254 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 258 */     if (this.enterTransition != null) {
/* 259 */       this.enterTransition.update(this, container, delta);
/* 260 */       if (this.enterTransition.isComplete()) {
/* 261 */         this.currentState.enter(container, this);
/* 262 */         this.enterTransition = null;
/*     */       } else {
/* 264 */         return;
/*     */       }
/*     */     }
/*     */ 
/* 268 */     this.currentState.update(container, this, delta);
/*     */ 
/* 270 */     postUpdateState(container, delta);
/*     */   }
/*     */ 
/*     */   protected void preUpdateState(GameContainer container, int delta)
/*     */     throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void postUpdateState(GameContainer container, int delta)
/*     */     throws SlickException
/*     */   {
/*     */   }
/*     */ 
/*     */   private boolean transitioning()
/*     */   {
/* 303 */     return (this.leaveTransition != null) || (this.enterTransition != null);
/*     */   }
/*     */ 
/*     */   public boolean closeRequested()
/*     */   {
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 317 */     return this.title;
/*     */   }
/*     */ 
/*     */   public GameContainer getContainer()
/*     */   {
/* 326 */     return this.container;
/*     */   }
/*     */ 
/*     */   public void controllerButtonPressed(int controller, int button)
/*     */   {
/* 333 */     if (transitioning()) {
/* 334 */       return;
/*     */     }
/*     */ 
/* 337 */     this.currentState.controllerButtonPressed(controller, button);
/*     */   }
/*     */ 
/*     */   public void controllerButtonReleased(int controller, int button)
/*     */   {
/* 344 */     if (transitioning()) {
/* 345 */       return;
/*     */     }
/*     */ 
/* 348 */     this.currentState.controllerButtonReleased(controller, button);
/*     */   }
/*     */ 
/*     */   public void controllerDownPressed(int controller)
/*     */   {
/* 355 */     if (transitioning()) {
/* 356 */       return;
/*     */     }
/*     */ 
/* 359 */     this.currentState.controllerDownPressed(controller);
/*     */   }
/*     */ 
/*     */   public void controllerDownReleased(int controller)
/*     */   {
/* 366 */     if (transitioning()) {
/* 367 */       return;
/*     */     }
/*     */ 
/* 370 */     this.currentState.controllerDownReleased(controller);
/*     */   }
/*     */ 
/*     */   public void controllerLeftPressed(int controller)
/*     */   {
/* 377 */     if (transitioning()) {
/* 378 */       return;
/*     */     }
/*     */ 
/* 381 */     this.currentState.controllerLeftPressed(controller);
/*     */   }
/*     */ 
/*     */   public void controllerLeftReleased(int controller)
/*     */   {
/* 388 */     if (transitioning()) {
/* 389 */       return;
/*     */     }
/*     */ 
/* 392 */     this.currentState.controllerLeftReleased(controller);
/*     */   }
/*     */ 
/*     */   public void controllerRightPressed(int controller)
/*     */   {
/* 399 */     if (transitioning()) {
/* 400 */       return;
/*     */     }
/*     */ 
/* 403 */     this.currentState.controllerRightPressed(controller);
/*     */   }
/*     */ 
/*     */   public void controllerRightReleased(int controller)
/*     */   {
/* 410 */     if (transitioning()) {
/* 411 */       return;
/*     */     }
/*     */ 
/* 414 */     this.currentState.controllerRightReleased(controller);
/*     */   }
/*     */ 
/*     */   public void controllerUpPressed(int controller)
/*     */   {
/* 421 */     if (transitioning()) {
/* 422 */       return;
/*     */     }
/*     */ 
/* 425 */     this.currentState.controllerUpPressed(controller);
/*     */   }
/*     */ 
/*     */   public void controllerUpReleased(int controller)
/*     */   {
/* 432 */     if (transitioning()) {
/* 433 */       return;
/*     */     }
/*     */ 
/* 436 */     this.currentState.controllerUpReleased(controller);
/*     */   }
/*     */ 
/*     */   public void keyPressed(int key, char c)
/*     */   {
/* 443 */     if (transitioning()) {
/* 444 */       return;
/*     */     }
/*     */ 
/* 447 */     this.currentState.keyPressed(key, c);
/*     */   }
/*     */ 
/*     */   public void keyReleased(int key, char c)
/*     */   {
/* 454 */     if (transitioning()) {
/* 455 */       return;
/*     */     }
/*     */ 
/* 458 */     this.currentState.keyReleased(key, c);
/*     */   }
/*     */ 
/*     */   public void mouseMoved(int oldx, int oldy, int newx, int newy)
/*     */   {
/* 465 */     if (transitioning()) {
/* 466 */       return;
/*     */     }
/*     */ 
/* 469 */     this.currentState.mouseMoved(oldx, oldy, newx, newy);
/*     */   }
/*     */ 
/*     */   public void mouseDragged(int oldx, int oldy, int newx, int newy)
/*     */   {
/* 476 */     if (transitioning()) {
/* 477 */       return;
/*     */     }
/*     */ 
/* 480 */     this.currentState.mouseDragged(oldx, oldy, newx, newy);
/*     */   }
/*     */ 
/*     */   public void mouseClicked(int button, int x, int y, int clickCount)
/*     */   {
/* 486 */     if (transitioning()) {
/* 487 */       return;
/*     */     }
/*     */ 
/* 490 */     this.currentState.mouseClicked(button, x, y, clickCount);
/*     */   }
/*     */ 
/*     */   public void mousePressed(int button, int x, int y)
/*     */   {
/* 497 */     if (transitioning()) {
/* 498 */       return;
/*     */     }
/*     */ 
/* 501 */     this.currentState.mousePressed(button, x, y);
/*     */   }
/*     */ 
/*     */   public void mouseReleased(int button, int x, int y)
/*     */   {
/* 508 */     if (transitioning()) {
/* 509 */       return;
/*     */     }
/*     */ 
/* 512 */     this.currentState.mouseReleased(button, x, y);
/*     */   }
/*     */ 
/*     */   public boolean isAcceptingInput()
/*     */   {
/* 519 */     if (transitioning()) {
/* 520 */       return false;
/*     */     }
/*     */ 
/* 523 */     return this.currentState.isAcceptingInput();
/*     */   }
/*     */ 
/*     */   public void inputEnded()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseWheelMoved(int newValue)
/*     */   {
/* 536 */     if (transitioning()) {
/* 537 */       return;
/*     */     }
/*     */ 
/* 540 */     this.currentState.mouseWheelMoved(newValue);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.state.StateBasedGame
 * JD-Core Version:    0.6.2
 */