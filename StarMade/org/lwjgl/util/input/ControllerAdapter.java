/*     */ package org.lwjgl.util.input;
/*     */ 
/*     */ import org.lwjgl.input.Controller;
/*     */ 
/*     */ public class ControllerAdapter
/*     */   implements Controller
/*     */ {
/*     */   public String getName()
/*     */   {
/*  51 */     return "Dummy Controller";
/*     */   }
/*     */ 
/*     */   public int getIndex()
/*     */   {
/*  60 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getButtonCount()
/*     */   {
/*  69 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getButtonName(int index)
/*     */   {
/*  80 */     return "button n/a";
/*     */   }
/*     */ 
/*     */   public boolean isButtonPressed(int index)
/*     */   {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   public void poll()
/*     */   {
/*     */   }
/*     */ 
/*     */   public float getPovX()
/*     */   {
/* 105 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getPovY()
/*     */   {
/* 114 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getDeadZone(int index)
/*     */   {
/* 124 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setDeadZone(int index, float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getAxisCount()
/*     */   {
/* 142 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getAxisName(int index)
/*     */   {
/* 152 */     return "axis n/a";
/*     */   }
/*     */ 
/*     */   public float getAxisValue(int index)
/*     */   {
/* 165 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getXAxisValue()
/*     */   {
/* 175 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getXAxisDeadZone()
/*     */   {
/* 184 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setXAxisDeadZone(float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public float getYAxisValue()
/*     */   {
/* 202 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getYAxisDeadZone()
/*     */   {
/* 211 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setYAxisDeadZone(float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public float getZAxisValue()
/*     */   {
/* 229 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getZAxisDeadZone()
/*     */   {
/* 238 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setZAxisDeadZone(float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public float getRXAxisValue()
/*     */   {
/* 256 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getRXAxisDeadZone()
/*     */   {
/* 265 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setRXAxisDeadZone(float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public float getRYAxisValue()
/*     */   {
/* 283 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getRYAxisDeadZone()
/*     */   {
/* 292 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setRYAxisDeadZone(float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public float getRZAxisValue()
/*     */   {
/* 310 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public float getRZAxisDeadZone()
/*     */   {
/* 319 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setRZAxisDeadZone(float zone)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getRumblerCount()
/*     */   {
/* 331 */     return 0;
/*     */   }
/*     */ 
/*     */   public String getRumblerName(int index) {
/* 335 */     return "rumber n/a";
/*     */   }
/*     */ 
/*     */   public void setRumblerStrength(int index, float strength)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.input.ControllerAdapter
 * JD-Core Version:    0.6.2
 */