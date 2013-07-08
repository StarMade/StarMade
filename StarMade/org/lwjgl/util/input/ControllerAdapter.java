/*   1:    */package org.lwjgl.util.input;
/*   2:    */
/*   3:    */import org.lwjgl.input.Controller;
/*   4:    */
/*  46:    */public class ControllerAdapter
/*  47:    */  implements Controller
/*  48:    */{
/*  49:    */  public String getName()
/*  50:    */  {
/*  51: 51 */    return "Dummy Controller";
/*  52:    */  }
/*  53:    */  
/*  58:    */  public int getIndex()
/*  59:    */  {
/*  60: 60 */    return 0;
/*  61:    */  }
/*  62:    */  
/*  67:    */  public int getButtonCount()
/*  68:    */  {
/*  69: 69 */    return 0;
/*  70:    */  }
/*  71:    */  
/*  78:    */  public String getButtonName(int index)
/*  79:    */  {
/*  80: 80 */    return "button n/a";
/*  81:    */  }
/*  82:    */  
/*  88:    */  public boolean isButtonPressed(int index)
/*  89:    */  {
/*  90: 90 */    return false;
/*  91:    */  }
/*  92:    */  
/*  97:    */  public void poll() {}
/*  98:    */  
/* 103:    */  public float getPovX()
/* 104:    */  {
/* 105:105 */    return 0.0F;
/* 106:    */  }
/* 107:    */  
/* 112:    */  public float getPovY()
/* 113:    */  {
/* 114:114 */    return 0.0F;
/* 115:    */  }
/* 116:    */  
/* 122:    */  public float getDeadZone(int index)
/* 123:    */  {
/* 124:124 */    return 0.0F;
/* 125:    */  }
/* 126:    */  
/* 133:    */  public void setDeadZone(int index, float zone) {}
/* 134:    */  
/* 140:    */  public int getAxisCount()
/* 141:    */  {
/* 142:142 */    return 0;
/* 143:    */  }
/* 144:    */  
/* 150:    */  public String getAxisName(int index)
/* 151:    */  {
/* 152:152 */    return "axis n/a";
/* 153:    */  }
/* 154:    */  
/* 163:    */  public float getAxisValue(int index)
/* 164:    */  {
/* 165:165 */    return 0.0F;
/* 166:    */  }
/* 167:    */  
/* 173:    */  public float getXAxisValue()
/* 174:    */  {
/* 175:175 */    return 0.0F;
/* 176:    */  }
/* 177:    */  
/* 182:    */  public float getXAxisDeadZone()
/* 183:    */  {
/* 184:184 */    return 0.0F;
/* 185:    */  }
/* 186:    */  
/* 193:    */  public void setXAxisDeadZone(float zone) {}
/* 194:    */  
/* 200:    */  public float getYAxisValue()
/* 201:    */  {
/* 202:202 */    return 0.0F;
/* 203:    */  }
/* 204:    */  
/* 209:    */  public float getYAxisDeadZone()
/* 210:    */  {
/* 211:211 */    return 0.0F;
/* 212:    */  }
/* 213:    */  
/* 220:    */  public void setYAxisDeadZone(float zone) {}
/* 221:    */  
/* 227:    */  public float getZAxisValue()
/* 228:    */  {
/* 229:229 */    return 0.0F;
/* 230:    */  }
/* 231:    */  
/* 236:    */  public float getZAxisDeadZone()
/* 237:    */  {
/* 238:238 */    return 0.0F;
/* 239:    */  }
/* 240:    */  
/* 247:    */  public void setZAxisDeadZone(float zone) {}
/* 248:    */  
/* 254:    */  public float getRXAxisValue()
/* 255:    */  {
/* 256:256 */    return 0.0F;
/* 257:    */  }
/* 258:    */  
/* 263:    */  public float getRXAxisDeadZone()
/* 264:    */  {
/* 265:265 */    return 0.0F;
/* 266:    */  }
/* 267:    */  
/* 274:    */  public void setRXAxisDeadZone(float zone) {}
/* 275:    */  
/* 281:    */  public float getRYAxisValue()
/* 282:    */  {
/* 283:283 */    return 0.0F;
/* 284:    */  }
/* 285:    */  
/* 290:    */  public float getRYAxisDeadZone()
/* 291:    */  {
/* 292:292 */    return 0.0F;
/* 293:    */  }
/* 294:    */  
/* 301:    */  public void setRYAxisDeadZone(float zone) {}
/* 302:    */  
/* 308:    */  public float getRZAxisValue()
/* 309:    */  {
/* 310:310 */    return 0.0F;
/* 311:    */  }
/* 312:    */  
/* 317:    */  public float getRZAxisDeadZone()
/* 318:    */  {
/* 319:319 */    return 0.0F;
/* 320:    */  }
/* 321:    */  
/* 325:    */  public void setRZAxisDeadZone(float zone) {}
/* 326:    */  
/* 329:    */  public int getRumblerCount()
/* 330:    */  {
/* 331:331 */    return 0;
/* 332:    */  }
/* 333:    */  
/* 334:    */  public String getRumblerName(int index) {
/* 335:335 */    return "rumber n/a";
/* 336:    */  }
/* 337:    */  
/* 338:    */  public void setRumblerStrength(int index, float strength) {}
/* 339:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.input.ControllerAdapter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */