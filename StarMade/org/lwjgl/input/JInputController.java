/*     */ package org.lwjgl.input;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.java.games.input.Component;
/*     */ import net.java.games.input.Component.Identifier.Axis;
/*     */ import net.java.games.input.Component.Identifier.Button;
/*     */ import net.java.games.input.Event;
/*     */ import net.java.games.input.EventQueue;
/*     */ import net.java.games.input.Rumbler;
/*     */ 
/*     */ class JInputController
/*     */   implements Controller
/*     */ {
/*     */   private net.java.games.input.Controller target;
/*     */   private int index;
/*  55 */   private ArrayList<Component> buttons = new ArrayList();
/*     */ 
/*  57 */   private ArrayList<Component> axes = new ArrayList();
/*     */ 
/*  59 */   private ArrayList<Component> pov = new ArrayList();
/*     */   private Rumbler[] rumblers;
/*     */   private boolean[] buttonState;
/*     */   private float[] povValues;
/*     */   private float[] axesValue;
/*     */   private float[] axesMax;
/*     */   private float[] deadZones;
/*  73 */   private int xaxis = -1;
/*     */ 
/*  75 */   private int yaxis = -1;
/*     */ 
/*  77 */   private int zaxis = -1;
/*     */ 
/*  79 */   private int rxaxis = -1;
/*     */ 
/*  81 */   private int ryaxis = -1;
/*     */ 
/*  83 */   private int rzaxis = -1;
/*     */ 
/*     */   JInputController(int index, net.java.games.input.Controller target)
/*     */   {
/*  93 */     this.target = target;
/*  94 */     this.index = index;
/*     */ 
/*  96 */     Component[] sourceAxes = target.getComponents();
/*     */ 
/*  98 */     for (Component sourceAxis : sourceAxes) {
/*  99 */       if ((sourceAxis.getIdentifier() instanceof Component.Identifier.Button))
/* 100 */         this.buttons.add(sourceAxis);
/* 101 */       else if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.POV))
/* 102 */         this.pov.add(sourceAxis);
/*     */       else {
/* 104 */         this.axes.add(sourceAxis);
/*     */       }
/*     */     }
/*     */ 
/* 108 */     this.buttonState = new boolean[this.buttons.size()];
/* 109 */     this.povValues = new float[this.pov.size()];
/* 110 */     this.axesValue = new float[this.axes.size()];
/* 111 */     int buttonsCount = 0;
/* 112 */     int axesCount = 0;
/*     */ 
/* 115 */     for (Component sourceAxis : sourceAxes) {
/* 116 */       if ((sourceAxis.getIdentifier() instanceof Component.Identifier.Button)) {
/* 117 */         this.buttonState[buttonsCount] = (sourceAxis.getPollData() != 0.0F ? 1 : false);
/* 118 */         buttonsCount++;
/* 119 */       } else if (!sourceAxis.getIdentifier().equals(Component.Identifier.Axis.POV))
/*     */       {
/* 123 */         this.axesValue[axesCount] = sourceAxis.getPollData();
/* 124 */         if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.X)) {
/* 125 */           this.xaxis = axesCount;
/*     */         }
/* 127 */         if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.Y)) {
/* 128 */           this.yaxis = axesCount;
/*     */         }
/* 130 */         if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.Z)) {
/* 131 */           this.zaxis = axesCount;
/*     */         }
/* 133 */         if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RX)) {
/* 134 */           this.rxaxis = axesCount;
/*     */         }
/* 136 */         if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RY)) {
/* 137 */           this.ryaxis = axesCount;
/*     */         }
/* 139 */         if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RZ)) {
/* 140 */           this.rzaxis = axesCount;
/*     */         }
/*     */ 
/* 143 */         axesCount++;
/*     */       }
/*     */     }
/*     */ 
/* 147 */     this.axesMax = new float[this.axes.size()];
/* 148 */     this.deadZones = new float[this.axes.size()];
/*     */ 
/* 150 */     for (int i = 0; i < this.axesMax.length; i++) {
/* 151 */       this.axesMax[i] = 1.0F;
/* 152 */       this.deadZones[i] = 0.05F;
/*     */     }
/*     */ 
/* 155 */     this.rumblers = target.getRumblers();
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 162 */     String name = this.target.getName();
/* 163 */     return name;
/*     */   }
/*     */ 
/*     */   public int getIndex()
/*     */   {
/* 170 */     return this.index;
/*     */   }
/*     */ 
/*     */   public int getButtonCount()
/*     */   {
/* 177 */     return this.buttons.size();
/*     */   }
/*     */ 
/*     */   public String getButtonName(int index)
/*     */   {
/* 184 */     return ((Component)this.buttons.get(index)).getName();
/*     */   }
/*     */ 
/*     */   public boolean isButtonPressed(int index)
/*     */   {
/* 191 */     return this.buttonState[index];
/*     */   }
/*     */ 
/*     */   public void poll()
/*     */   {
/* 198 */     this.target.poll();
/*     */ 
/* 200 */     Event event = new Event();
/* 201 */     EventQueue queue = this.target.getEventQueue();
/*     */ 
/* 203 */     while (queue.getNextEvent(event))
/*     */     {
/* 205 */       if (this.buttons.contains(event.getComponent())) {
/* 206 */         Component button = event.getComponent();
/* 207 */         int buttonIndex = this.buttons.indexOf(button);
/* 208 */         this.buttonState[buttonIndex] = (event.getValue() != 0.0F ? 1 : false);
/*     */ 
/* 211 */         Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 1, buttonIndex, false, false));
/*     */       }
/*     */ 
/* 215 */       if (this.pov.contains(event.getComponent())) {
/* 216 */         Component povComponent = event.getComponent();
/* 217 */         int povIndex = this.pov.indexOf(povComponent);
/* 218 */         float prevX = getPovX();
/* 219 */         float prevY = getPovY();
/* 220 */         this.povValues[povIndex] = event.getValue();
/*     */ 
/* 222 */         if (prevX != getPovX()) {
/* 223 */           Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 3, 0, false, false));
/*     */         }
/* 225 */         if (prevY != getPovY()) {
/* 226 */           Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 4, 0, false, false));
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 231 */       if (this.axes.contains(event.getComponent())) {
/* 232 */         Component axis = event.getComponent();
/* 233 */         int axisIndex = this.axes.indexOf(axis);
/* 234 */         float value = axis.getPollData();
/*     */ 
/* 237 */         if (Math.abs(value) < this.deadZones[axisIndex]) {
/* 238 */           value = 0.0F;
/*     */         }
/* 240 */         if (Math.abs(value) < axis.getDeadZone()) {
/* 241 */           value = 0.0F;
/*     */         }
/* 243 */         if (Math.abs(value) > this.axesMax[axisIndex]) {
/* 244 */           this.axesMax[axisIndex] = Math.abs(value);
/*     */         }
/*     */ 
/* 248 */         value /= this.axesMax[axisIndex];
/*     */ 
/* 250 */         Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 2, axisIndex, axisIndex == this.xaxis, axisIndex == this.yaxis));
/*     */ 
/* 252 */         this.axesValue[axisIndex] = value;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getAxisCount()
/*     */   {
/* 261 */     return this.axes.size();
/*     */   }
/*     */ 
/*     */   public String getAxisName(int index)
/*     */   {
/* 268 */     return ((Component)this.axes.get(index)).getName();
/*     */   }
/*     */ 
/*     */   public float getAxisValue(int index)
/*     */   {
/* 275 */     return this.axesValue[index];
/*     */   }
/*     */ 
/*     */   public float getXAxisValue()
/*     */   {
/* 282 */     if (this.xaxis == -1) {
/* 283 */       return 0.0F;
/*     */     }
/*     */ 
/* 286 */     return getAxisValue(this.xaxis);
/*     */   }
/*     */ 
/*     */   public float getYAxisValue()
/*     */   {
/* 293 */     if (this.yaxis == -1) {
/* 294 */       return 0.0F;
/*     */     }
/*     */ 
/* 297 */     return getAxisValue(this.yaxis);
/*     */   }
/*     */ 
/*     */   public float getXAxisDeadZone()
/*     */   {
/* 304 */     if (this.xaxis == -1) {
/* 305 */       return 0.0F;
/*     */     }
/*     */ 
/* 308 */     return getDeadZone(this.xaxis);
/*     */   }
/*     */ 
/*     */   public float getYAxisDeadZone()
/*     */   {
/* 315 */     if (this.yaxis == -1) {
/* 316 */       return 0.0F;
/*     */     }
/*     */ 
/* 319 */     return getDeadZone(this.yaxis);
/*     */   }
/*     */ 
/*     */   public void setXAxisDeadZone(float zone)
/*     */   {
/* 326 */     setDeadZone(this.xaxis, zone);
/*     */   }
/*     */ 
/*     */   public void setYAxisDeadZone(float zone)
/*     */   {
/* 333 */     setDeadZone(this.yaxis, zone);
/*     */   }
/*     */ 
/*     */   public float getDeadZone(int index)
/*     */   {
/* 340 */     return this.deadZones[index];
/*     */   }
/*     */ 
/*     */   public void setDeadZone(int index, float zone)
/*     */   {
/* 347 */     this.deadZones[index] = zone;
/*     */   }
/*     */ 
/*     */   public float getZAxisValue()
/*     */   {
/* 354 */     if (this.zaxis == -1) {
/* 355 */       return 0.0F;
/*     */     }
/*     */ 
/* 358 */     return getAxisValue(this.zaxis);
/*     */   }
/*     */ 
/*     */   public float getZAxisDeadZone()
/*     */   {
/* 365 */     if (this.zaxis == -1) {
/* 366 */       return 0.0F;
/*     */     }
/*     */ 
/* 369 */     return getDeadZone(this.zaxis);
/*     */   }
/*     */ 
/*     */   public void setZAxisDeadZone(float zone)
/*     */   {
/* 376 */     setDeadZone(this.zaxis, zone);
/*     */   }
/*     */ 
/*     */   public float getRXAxisValue()
/*     */   {
/* 383 */     if (this.rxaxis == -1) {
/* 384 */       return 0.0F;
/*     */     }
/*     */ 
/* 387 */     return getAxisValue(this.rxaxis);
/*     */   }
/*     */ 
/*     */   public float getRXAxisDeadZone()
/*     */   {
/* 394 */     if (this.rxaxis == -1) {
/* 395 */       return 0.0F;
/*     */     }
/*     */ 
/* 398 */     return getDeadZone(this.rxaxis);
/*     */   }
/*     */ 
/*     */   public void setRXAxisDeadZone(float zone)
/*     */   {
/* 405 */     setDeadZone(this.rxaxis, zone);
/*     */   }
/*     */ 
/*     */   public float getRYAxisValue()
/*     */   {
/* 412 */     if (this.ryaxis == -1) {
/* 413 */       return 0.0F;
/*     */     }
/*     */ 
/* 416 */     return getAxisValue(this.ryaxis);
/*     */   }
/*     */ 
/*     */   public float getRYAxisDeadZone()
/*     */   {
/* 423 */     if (this.ryaxis == -1) {
/* 424 */       return 0.0F;
/*     */     }
/*     */ 
/* 427 */     return getDeadZone(this.ryaxis);
/*     */   }
/*     */ 
/*     */   public void setRYAxisDeadZone(float zone)
/*     */   {
/* 434 */     setDeadZone(this.ryaxis, zone);
/*     */   }
/*     */ 
/*     */   public float getRZAxisValue()
/*     */   {
/* 441 */     if (this.rzaxis == -1) {
/* 442 */       return 0.0F;
/*     */     }
/*     */ 
/* 445 */     return getAxisValue(this.rzaxis);
/*     */   }
/*     */ 
/*     */   public float getRZAxisDeadZone()
/*     */   {
/* 452 */     if (this.rzaxis == -1) {
/* 453 */       return 0.0F;
/*     */     }
/*     */ 
/* 456 */     return getDeadZone(this.rzaxis);
/*     */   }
/*     */ 
/*     */   public void setRZAxisDeadZone(float zone)
/*     */   {
/* 463 */     setDeadZone(this.rzaxis, zone);
/*     */   }
/*     */ 
/*     */   public float getPovX()
/*     */   {
/* 470 */     if (this.pov.size() == 0) {
/* 471 */       return 0.0F;
/*     */     }
/*     */ 
/* 474 */     float value = this.povValues[0];
/*     */ 
/* 476 */     if ((value == 0.875F) || (value == 0.125F) || (value == 1.0F))
/*     */     {
/* 479 */       return -1.0F;
/*     */     }
/* 481 */     if ((value == 0.625F) || (value == 0.375F) || (value == 0.5F))
/*     */     {
/* 484 */       return 1.0F;
/*     */     }
/*     */ 
/* 487 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getPovY()
/*     */   {
/* 494 */     if (this.pov.size() == 0) {
/* 495 */       return 0.0F;
/*     */     }
/*     */ 
/* 498 */     float value = this.povValues[0];
/*     */ 
/* 500 */     if ((value == 0.875F) || (value == 0.625F) || (value == 0.75F))
/*     */     {
/* 503 */       return 1.0F;
/*     */     }
/* 505 */     if ((value == 0.125F) || (value == 0.375F) || (value == 0.25F))
/*     */     {
/* 508 */       return -1.0F;
/*     */     }
/*     */ 
/* 511 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public int getRumblerCount() {
/* 515 */     return this.rumblers.length;
/*     */   }
/*     */ 
/*     */   public String getRumblerName(int index) {
/* 519 */     return this.rumblers[index].getAxisName();
/*     */   }
/*     */ 
/*     */   public void setRumblerStrength(int index, float strength) {
/* 523 */     this.rumblers[index].rumble(strength);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.JInputController
 * JD-Core Version:    0.6.2
 */