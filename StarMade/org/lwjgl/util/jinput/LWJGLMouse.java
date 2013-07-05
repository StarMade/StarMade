/*     */ package org.lwjgl.util.jinput;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.java.games.input.AbstractComponent;
/*     */ import net.java.games.input.Component;
/*     */ import net.java.games.input.Component.Identifier.Axis;
/*     */ import net.java.games.input.Component.Identifier.Button;
/*     */ import net.java.games.input.Controller;
/*     */ import net.java.games.input.Event;
/*     */ import net.java.games.input.Rumbler;
/*     */ 
/*     */ final class LWJGLMouse extends net.java.games.input.Mouse
/*     */ {
/*     */   private static final int EVENT_X = 1;
/*     */   private static final int EVENT_Y = 2;
/*     */   private static final int EVENT_WHEEL = 3;
/*     */   private static final int EVENT_BUTTON = 4;
/*     */   private static final int EVENT_DONE = 5;
/*  53 */   private int event_state = 5;
/*     */ 
/*     */   LWJGLMouse() {
/*  56 */     super("LWJGLMouse", createComponents(), new Controller[0], new Rumbler[0]);
/*     */   }
/*     */ 
/*     */   private static Component[] createComponents() {
/*  60 */     return new Component[] { new Axis(Component.Identifier.Axis.X), new Axis(Component.Identifier.Axis.Y), new Axis(Component.Identifier.Axis.Z), new Button(Component.Identifier.Button.LEFT), new Button(Component.Identifier.Button.MIDDLE), new Button(Component.Identifier.Button.RIGHT) };
/*     */   }
/*     */ 
/*     */   public synchronized void pollDevice()
/*     */     throws IOException
/*     */   {
/*  69 */     if (!org.lwjgl.input.Mouse.isCreated())
/*  70 */       return;
/*  71 */     org.lwjgl.input.Mouse.poll();
/*  72 */     for (int i = 0; i < 3; i++)
/*  73 */       setButtonState(i);
/*     */   }
/*     */ 
/*     */   private Button map(int lwjgl_button) {
/*  77 */     switch (lwjgl_button) {
/*     */     case 0:
/*  79 */       return (Button)getLeft();
/*     */     case 1:
/*  81 */       return (Button)getRight();
/*     */     case 2:
/*  83 */       return (Button)getMiddle();
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   private void setButtonState(int lwjgl_button)
/*     */   {
/*  90 */     Button button = map(lwjgl_button);
/*  91 */     if (button != null)
/*  92 */       button.setValue(org.lwjgl.input.Mouse.isButtonDown(lwjgl_button) ? 1.0F : 0.0F);
/*     */   }
/*     */ 
/*     */   protected synchronized boolean getNextDeviceEvent(Event event) throws IOException {
/*  96 */     if (!org.lwjgl.input.Mouse.isCreated())
/*  97 */       return false;
/*     */     while (true) {
/*  99 */       long nanos = org.lwjgl.input.Mouse.getEventNanoseconds();
/* 100 */       switch (this.event_state) {
/*     */       case 1:
/* 102 */         this.event_state = 2;
/* 103 */         int dx = org.lwjgl.input.Mouse.getEventDX();
/* 104 */         if (dx != 0) {
/* 105 */           event.set(getX(), dx, nanos);
/* 106 */           return true;
/*     */         }
/*     */         break;
/*     */       case 2:
/* 110 */         this.event_state = 3;
/*     */ 
/* 114 */         int dy = -org.lwjgl.input.Mouse.getEventDY();
/* 115 */         if (dy != 0) {
/* 116 */           event.set(getY(), dy, nanos);
/* 117 */           return true;
/*     */         }
/*     */         break;
/*     */       case 3:
/* 121 */         this.event_state = 4;
/* 122 */         int dwheel = org.lwjgl.input.Mouse.getEventDWheel();
/* 123 */         if (dwheel != 0) {
/* 124 */           event.set(getWheel(), dwheel, nanos);
/* 125 */           return true;
/*     */         }
/*     */         break;
/*     */       case 4:
/* 129 */         this.event_state = 5;
/* 130 */         int lwjgl_button = org.lwjgl.input.Mouse.getEventButton();
/* 131 */         if (lwjgl_button != -1) {
/* 132 */           Button button = map(lwjgl_button);
/* 133 */           if (button != null) {
/* 134 */             event.set(button, org.lwjgl.input.Mouse.getEventButtonState() ? 1.0F : 0.0F, nanos);
/* 135 */             return true;
/*     */           }
/*     */         }
/* 137 */         break;
/*     */       case 5:
/* 140 */         if (!org.lwjgl.input.Mouse.next())
/* 141 */           return false;
/* 142 */         this.event_state = 1;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   static final class Button extends AbstractComponent
/*     */   {
/*     */     private float value;
/*     */ 
/*     */     Button(Component.Identifier.Button button_id)
/*     */     {
/* 172 */       super(button_id);
/*     */     }
/*     */ 
/*     */     void setValue(float value) {
/* 176 */       this.value = value;
/*     */     }
/*     */ 
/*     */     protected float poll() throws IOException {
/* 180 */       return this.value;
/*     */     }
/*     */ 
/*     */     public boolean isRelative() {
/* 184 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean isAnalog() {
/* 188 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */   static final class Axis extends AbstractComponent
/*     */   {
/*     */     Axis(Component.Identifier.Axis axis_id)
/*     */     {
/* 152 */       super(axis_id);
/*     */     }
/*     */ 
/*     */     public boolean isRelative() {
/* 156 */       return true;
/*     */     }
/*     */ 
/*     */     protected float poll() throws IOException {
/* 160 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     public boolean isAnalog() {
/* 164 */       return true;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLMouse
 * JD-Core Version:    0.6.2
 */