/*   1:    */package org.lwjgl.input;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import net.java.games.input.Component;
/*   5:    */import net.java.games.input.Component.Identifier.Axis;
/*   6:    */import net.java.games.input.Component.Identifier.Button;
/*   7:    */import net.java.games.input.Event;
/*   8:    */import net.java.games.input.EventQueue;
/*   9:    */import net.java.games.input.Rumbler;
/*  10:    */
/*  50:    */class JInputController
/*  51:    */  implements Controller
/*  52:    */{
/*  53:    */  private net.java.games.input.Controller target;
/*  54:    */  private int index;
/*  55: 55 */  private ArrayList<Component> buttons = new ArrayList();
/*  56:    */  
/*  57: 57 */  private ArrayList<Component> axes = new ArrayList();
/*  58:    */  
/*  59: 59 */  private ArrayList<Component> pov = new ArrayList();
/*  60:    */  
/*  61:    */  private Rumbler[] rumblers;
/*  62:    */  
/*  63:    */  private boolean[] buttonState;
/*  64:    */  
/*  65:    */  private float[] povValues;
/*  66:    */  
/*  67:    */  private float[] axesValue;
/*  68:    */  
/*  69:    */  private float[] axesMax;
/*  70:    */  
/*  71:    */  private float[] deadZones;
/*  72:    */  
/*  73: 73 */  private int xaxis = -1;
/*  74:    */  
/*  75: 75 */  private int yaxis = -1;
/*  76:    */  
/*  77: 77 */  private int zaxis = -1;
/*  78:    */  
/*  79: 79 */  private int rxaxis = -1;
/*  80:    */  
/*  81: 81 */  private int ryaxis = -1;
/*  82:    */  
/*  83: 83 */  private int rzaxis = -1;
/*  84:    */  
/*  91:    */  JInputController(int index, net.java.games.input.Controller target)
/*  92:    */  {
/*  93: 93 */    this.target = target;
/*  94: 94 */    this.index = index;
/*  95:    */    
/*  96: 96 */    Component[] sourceAxes = target.getComponents();
/*  97:    */    
/*  98: 98 */    for (Component sourceAxis : sourceAxes) {
/*  99: 99 */      if ((sourceAxis.getIdentifier() instanceof Component.Identifier.Button)) {
/* 100:100 */        this.buttons.add(sourceAxis);
/* 101:101 */      } else if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.POV)) {
/* 102:102 */        this.pov.add(sourceAxis);
/* 103:    */      } else {
/* 104:104 */        this.axes.add(sourceAxis);
/* 105:    */      }
/* 106:    */    }
/* 107:    */    
/* 108:108 */    this.buttonState = new boolean[this.buttons.size()];
/* 109:109 */    this.povValues = new float[this.pov.size()];
/* 110:110 */    this.axesValue = new float[this.axes.size()];
/* 111:111 */    int buttonsCount = 0;
/* 112:112 */    int axesCount = 0;
/* 113:    */    
/* 115:115 */    for (Component sourceAxis : sourceAxes) {
/* 116:116 */      if ((sourceAxis.getIdentifier() instanceof Component.Identifier.Button)) {
/* 117:117 */        this.buttonState[buttonsCount] = (sourceAxis.getPollData() != 0.0F ? 1 : false);
/* 118:118 */        buttonsCount++;
/* 119:119 */      } else if (!sourceAxis.getIdentifier().equals(Component.Identifier.Axis.POV))
/* 120:    */      {
/* 123:123 */        this.axesValue[axesCount] = sourceAxis.getPollData();
/* 124:124 */        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.X)) {
/* 125:125 */          this.xaxis = axesCount;
/* 126:    */        }
/* 127:127 */        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.Y)) {
/* 128:128 */          this.yaxis = axesCount;
/* 129:    */        }
/* 130:130 */        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.Z)) {
/* 131:131 */          this.zaxis = axesCount;
/* 132:    */        }
/* 133:133 */        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RX)) {
/* 134:134 */          this.rxaxis = axesCount;
/* 135:    */        }
/* 136:136 */        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RY)) {
/* 137:137 */          this.ryaxis = axesCount;
/* 138:    */        }
/* 139:139 */        if (sourceAxis.getIdentifier().equals(Component.Identifier.Axis.RZ)) {
/* 140:140 */          this.rzaxis = axesCount;
/* 141:    */        }
/* 142:    */        
/* 143:143 */        axesCount++;
/* 144:    */      }
/* 145:    */    }
/* 146:    */    
/* 147:147 */    this.axesMax = new float[this.axes.size()];
/* 148:148 */    this.deadZones = new float[this.axes.size()];
/* 149:    */    
/* 150:150 */    for (int i = 0; i < this.axesMax.length; i++) {
/* 151:151 */      this.axesMax[i] = 1.0F;
/* 152:152 */      this.deadZones[i] = 0.05F;
/* 153:    */    }
/* 154:    */    
/* 155:155 */    this.rumblers = target.getRumblers();
/* 156:    */  }
/* 157:    */  
/* 160:    */  public String getName()
/* 161:    */  {
/* 162:162 */    String name = this.target.getName();
/* 163:163 */    return name;
/* 164:    */  }
/* 165:    */  
/* 168:    */  public int getIndex()
/* 169:    */  {
/* 170:170 */    return this.index;
/* 171:    */  }
/* 172:    */  
/* 175:    */  public int getButtonCount()
/* 176:    */  {
/* 177:177 */    return this.buttons.size();
/* 178:    */  }
/* 179:    */  
/* 182:    */  public String getButtonName(int index)
/* 183:    */  {
/* 184:184 */    return ((Component)this.buttons.get(index)).getName();
/* 185:    */  }
/* 186:    */  
/* 189:    */  public boolean isButtonPressed(int index)
/* 190:    */  {
/* 191:191 */    return this.buttonState[index];
/* 192:    */  }
/* 193:    */  
/* 196:    */  public void poll()
/* 197:    */  {
/* 198:198 */    this.target.poll();
/* 199:    */    
/* 200:200 */    Event event = new Event();
/* 201:201 */    EventQueue queue = this.target.getEventQueue();
/* 202:    */    
/* 203:203 */    while (queue.getNextEvent(event))
/* 204:    */    {
/* 205:205 */      if (this.buttons.contains(event.getComponent())) {
/* 206:206 */        Component button = event.getComponent();
/* 207:207 */        int buttonIndex = this.buttons.indexOf(button);
/* 208:208 */        this.buttonState[buttonIndex] = (event.getValue() != 0.0F ? 1 : false);
/* 209:    */        
/* 211:211 */        Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 1, buttonIndex, false, false));
/* 212:    */      }
/* 213:    */      
/* 215:215 */      if (this.pov.contains(event.getComponent())) {
/* 216:216 */        Component povComponent = event.getComponent();
/* 217:217 */        int povIndex = this.pov.indexOf(povComponent);
/* 218:218 */        float prevX = getPovX();
/* 219:219 */        float prevY = getPovY();
/* 220:220 */        this.povValues[povIndex] = event.getValue();
/* 221:    */        
/* 222:222 */        if (prevX != getPovX()) {
/* 223:223 */          Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 3, 0, false, false));
/* 224:    */        }
/* 225:225 */        if (prevY != getPovY()) {
/* 226:226 */          Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 4, 0, false, false));
/* 227:    */        }
/* 228:    */      }
/* 229:    */      
/* 231:231 */      if (this.axes.contains(event.getComponent())) {
/* 232:232 */        Component axis = event.getComponent();
/* 233:233 */        int axisIndex = this.axes.indexOf(axis);
/* 234:234 */        float value = axis.getPollData();
/* 235:    */        
/* 237:237 */        if (Math.abs(value) < this.deadZones[axisIndex]) {
/* 238:238 */          value = 0.0F;
/* 239:    */        }
/* 240:240 */        if (Math.abs(value) < axis.getDeadZone()) {
/* 241:241 */          value = 0.0F;
/* 242:    */        }
/* 243:243 */        if (Math.abs(value) > this.axesMax[axisIndex]) {
/* 244:244 */          this.axesMax[axisIndex] = Math.abs(value);
/* 245:    */        }
/* 246:    */        
/* 248:248 */        value /= this.axesMax[axisIndex];
/* 249:    */        
/* 250:250 */        Controllers.addEvent(new ControllerEvent(this, event.getNanos(), 2, axisIndex, axisIndex == this.xaxis, axisIndex == this.yaxis));
/* 251:    */        
/* 252:252 */        this.axesValue[axisIndex] = value;
/* 253:    */      }
/* 254:    */    }
/* 255:    */  }
/* 256:    */  
/* 259:    */  public int getAxisCount()
/* 260:    */  {
/* 261:261 */    return this.axes.size();
/* 262:    */  }
/* 263:    */  
/* 266:    */  public String getAxisName(int index)
/* 267:    */  {
/* 268:268 */    return ((Component)this.axes.get(index)).getName();
/* 269:    */  }
/* 270:    */  
/* 273:    */  public float getAxisValue(int index)
/* 274:    */  {
/* 275:275 */    return this.axesValue[index];
/* 276:    */  }
/* 277:    */  
/* 280:    */  public float getXAxisValue()
/* 281:    */  {
/* 282:282 */    if (this.xaxis == -1) {
/* 283:283 */      return 0.0F;
/* 284:    */    }
/* 285:    */    
/* 286:286 */    return getAxisValue(this.xaxis);
/* 287:    */  }
/* 288:    */  
/* 291:    */  public float getYAxisValue()
/* 292:    */  {
/* 293:293 */    if (this.yaxis == -1) {
/* 294:294 */      return 0.0F;
/* 295:    */    }
/* 296:    */    
/* 297:297 */    return getAxisValue(this.yaxis);
/* 298:    */  }
/* 299:    */  
/* 302:    */  public float getXAxisDeadZone()
/* 303:    */  {
/* 304:304 */    if (this.xaxis == -1) {
/* 305:305 */      return 0.0F;
/* 306:    */    }
/* 307:    */    
/* 308:308 */    return getDeadZone(this.xaxis);
/* 309:    */  }
/* 310:    */  
/* 313:    */  public float getYAxisDeadZone()
/* 314:    */  {
/* 315:315 */    if (this.yaxis == -1) {
/* 316:316 */      return 0.0F;
/* 317:    */    }
/* 318:    */    
/* 319:319 */    return getDeadZone(this.yaxis);
/* 320:    */  }
/* 321:    */  
/* 324:    */  public void setXAxisDeadZone(float zone)
/* 325:    */  {
/* 326:326 */    setDeadZone(this.xaxis, zone);
/* 327:    */  }
/* 328:    */  
/* 331:    */  public void setYAxisDeadZone(float zone)
/* 332:    */  {
/* 333:333 */    setDeadZone(this.yaxis, zone);
/* 334:    */  }
/* 335:    */  
/* 338:    */  public float getDeadZone(int index)
/* 339:    */  {
/* 340:340 */    return this.deadZones[index];
/* 341:    */  }
/* 342:    */  
/* 345:    */  public void setDeadZone(int index, float zone)
/* 346:    */  {
/* 347:347 */    this.deadZones[index] = zone;
/* 348:    */  }
/* 349:    */  
/* 352:    */  public float getZAxisValue()
/* 353:    */  {
/* 354:354 */    if (this.zaxis == -1) {
/* 355:355 */      return 0.0F;
/* 356:    */    }
/* 357:    */    
/* 358:358 */    return getAxisValue(this.zaxis);
/* 359:    */  }
/* 360:    */  
/* 363:    */  public float getZAxisDeadZone()
/* 364:    */  {
/* 365:365 */    if (this.zaxis == -1) {
/* 366:366 */      return 0.0F;
/* 367:    */    }
/* 368:    */    
/* 369:369 */    return getDeadZone(this.zaxis);
/* 370:    */  }
/* 371:    */  
/* 374:    */  public void setZAxisDeadZone(float zone)
/* 375:    */  {
/* 376:376 */    setDeadZone(this.zaxis, zone);
/* 377:    */  }
/* 378:    */  
/* 381:    */  public float getRXAxisValue()
/* 382:    */  {
/* 383:383 */    if (this.rxaxis == -1) {
/* 384:384 */      return 0.0F;
/* 385:    */    }
/* 386:    */    
/* 387:387 */    return getAxisValue(this.rxaxis);
/* 388:    */  }
/* 389:    */  
/* 392:    */  public float getRXAxisDeadZone()
/* 393:    */  {
/* 394:394 */    if (this.rxaxis == -1) {
/* 395:395 */      return 0.0F;
/* 396:    */    }
/* 397:    */    
/* 398:398 */    return getDeadZone(this.rxaxis);
/* 399:    */  }
/* 400:    */  
/* 403:    */  public void setRXAxisDeadZone(float zone)
/* 404:    */  {
/* 405:405 */    setDeadZone(this.rxaxis, zone);
/* 406:    */  }
/* 407:    */  
/* 410:    */  public float getRYAxisValue()
/* 411:    */  {
/* 412:412 */    if (this.ryaxis == -1) {
/* 413:413 */      return 0.0F;
/* 414:    */    }
/* 415:    */    
/* 416:416 */    return getAxisValue(this.ryaxis);
/* 417:    */  }
/* 418:    */  
/* 421:    */  public float getRYAxisDeadZone()
/* 422:    */  {
/* 423:423 */    if (this.ryaxis == -1) {
/* 424:424 */      return 0.0F;
/* 425:    */    }
/* 426:    */    
/* 427:427 */    return getDeadZone(this.ryaxis);
/* 428:    */  }
/* 429:    */  
/* 432:    */  public void setRYAxisDeadZone(float zone)
/* 433:    */  {
/* 434:434 */    setDeadZone(this.ryaxis, zone);
/* 435:    */  }
/* 436:    */  
/* 439:    */  public float getRZAxisValue()
/* 440:    */  {
/* 441:441 */    if (this.rzaxis == -1) {
/* 442:442 */      return 0.0F;
/* 443:    */    }
/* 444:    */    
/* 445:445 */    return getAxisValue(this.rzaxis);
/* 446:    */  }
/* 447:    */  
/* 450:    */  public float getRZAxisDeadZone()
/* 451:    */  {
/* 452:452 */    if (this.rzaxis == -1) {
/* 453:453 */      return 0.0F;
/* 454:    */    }
/* 455:    */    
/* 456:456 */    return getDeadZone(this.rzaxis);
/* 457:    */  }
/* 458:    */  
/* 461:    */  public void setRZAxisDeadZone(float zone)
/* 462:    */  {
/* 463:463 */    setDeadZone(this.rzaxis, zone);
/* 464:    */  }
/* 465:    */  
/* 468:    */  public float getPovX()
/* 469:    */  {
/* 470:470 */    if (this.pov.size() == 0) {
/* 471:471 */      return 0.0F;
/* 472:    */    }
/* 473:    */    
/* 474:474 */    float value = this.povValues[0];
/* 475:    */    
/* 476:476 */    if ((value == 0.875F) || (value == 0.125F) || (value == 1.0F))
/* 477:    */    {
/* 479:479 */      return -1.0F;
/* 480:    */    }
/* 481:481 */    if ((value == 0.625F) || (value == 0.375F) || (value == 0.5F))
/* 482:    */    {
/* 484:484 */      return 1.0F;
/* 485:    */    }
/* 486:    */    
/* 487:487 */    return 0.0F;
/* 488:    */  }
/* 489:    */  
/* 492:    */  public float getPovY()
/* 493:    */  {
/* 494:494 */    if (this.pov.size() == 0) {
/* 495:495 */      return 0.0F;
/* 496:    */    }
/* 497:    */    
/* 498:498 */    float value = this.povValues[0];
/* 499:    */    
/* 500:500 */    if ((value == 0.875F) || (value == 0.625F) || (value == 0.75F))
/* 501:    */    {
/* 503:503 */      return 1.0F;
/* 504:    */    }
/* 505:505 */    if ((value == 0.125F) || (value == 0.375F) || (value == 0.25F))
/* 506:    */    {
/* 508:508 */      return -1.0F;
/* 509:    */    }
/* 510:    */    
/* 511:511 */    return 0.0F;
/* 512:    */  }
/* 513:    */  
/* 514:    */  public int getRumblerCount() {
/* 515:515 */    return this.rumblers.length;
/* 516:    */  }
/* 517:    */  
/* 518:    */  public String getRumblerName(int index) {
/* 519:519 */    return this.rumblers[index].getAxisName();
/* 520:    */  }
/* 521:    */  
/* 522:    */  public void setRumblerStrength(int index, float strength) {
/* 523:523 */    this.rumblers[index].rumble(strength);
/* 524:    */  }
/* 525:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.input.JInputController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */