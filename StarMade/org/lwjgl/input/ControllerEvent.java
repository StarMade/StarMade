/*     */ package org.lwjgl.input;
/*     */ 
/*     */ class ControllerEvent
/*     */ {
/*     */   public static final int BUTTON = 1;
/*     */   public static final int AXIS = 2;
/*     */   public static final int POVX = 3;
/*     */   public static final int POVY = 4;
/*     */   private Controller source;
/*     */   private int index;
/*     */   private int type;
/*     */   private boolean xaxis;
/*     */   private boolean yaxis;
/*     */   private long timeStamp;
/*     */ 
/*     */   ControllerEvent(Controller source, long timeStamp, int type, int index, boolean xaxis, boolean yaxis)
/*     */   {
/*  73 */     this.source = source;
/*  74 */     this.timeStamp = timeStamp;
/*  75 */     this.type = type;
/*  76 */     this.index = index;
/*  77 */     this.xaxis = xaxis;
/*  78 */     this.yaxis = yaxis;
/*     */   }
/*     */ 
/*     */   public long getTimeStamp()
/*     */   {
/*  88 */     return this.timeStamp;
/*     */   }
/*     */ 
/*     */   public Controller getSource()
/*     */   {
/*  97 */     return this.source;
/*     */   }
/*     */ 
/*     */   public int getControlIndex()
/*     */   {
/* 106 */     return this.index;
/*     */   }
/*     */ 
/*     */   public boolean isButton()
/*     */   {
/* 115 */     return this.type == 1;
/*     */   }
/*     */ 
/*     */   public boolean isAxis()
/*     */   {
/* 124 */     return this.type == 2;
/*     */   }
/*     */ 
/*     */   public boolean isPovY()
/*     */   {
/* 133 */     return this.type == 4;
/*     */   }
/*     */ 
/*     */   public boolean isPovX()
/*     */   {
/* 142 */     return this.type == 3;
/*     */   }
/*     */ 
/*     */   public boolean isXAxis()
/*     */   {
/* 151 */     return this.xaxis;
/*     */   }
/*     */ 
/*     */   public boolean isYAxis()
/*     */   {
/* 160 */     return this.yaxis;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 167 */     return "[" + this.source + " type=" + this.type + " xaxis=" + this.xaxis + " yaxis=" + this.yaxis + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.ControllerEvent
 * JD-Core Version:    0.6.2
 */