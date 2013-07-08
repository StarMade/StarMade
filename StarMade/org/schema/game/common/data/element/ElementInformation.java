/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import java.lang.reflect.Field;
/*   4:    */import java.util.HashSet;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.Map.Entry;
/*   7:    */import java.util.Properties;
/*   8:    */import java.util.Set;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import org.w3c.dom.Document;
/*  11:    */import org.w3c.dom.Node;
/*  12:    */
/*  15:    */public class ElementInformation
/*  16:    */  implements Comparable
/*  17:    */{
/*  18:    */  public final short id;
/*  19:    */  public final String name;
/*  20:    */  public final Class type;
/*  21:    */  public short textureId;
/*  22: 22 */  public int buildIconNum = 62;
/*  23:    */  @org.schema.game.common.data.element.annotation.Element(states={"1", "3", "6"}, tag="IndividualSides", updateTextures=true)
/*  24: 24 */  public int individualSides = 1;
/*  25:    */  
/*  26:    */  @org.schema.game.common.data.element.annotation.Element(from=0, to=2147483647, tag="Price")
/*  27: 27 */  public long price = 100L;
/*  28:    */  
/*  29:    */  @org.schema.game.common.data.element.annotation.Element(tag="Description", textArea=true)
/*  30: 30 */  public String description = "undefined description";
/*  31:    */  
/*  36:    */  @org.schema.game.common.data.element.annotation.Element(tag="FullName")
/*  37: 37 */  public String fullName = "";
/*  38:    */  
/*  41:    */  @org.schema.game.common.data.element.annotation.Element(tag="ControlledBy", collectionElementTag="Element", collectionType="blockTypes")
/*  42: 42 */  public final Set controlledBy = new HashSet();
/*  43:    */  
/*  44:    */  @org.schema.game.common.data.element.annotation.Element(tag="Controlling", collectionElementTag="Element", collectionType="blockTypes")
/*  45: 45 */  public final Set controlling = new HashSet();
/*  46:    */  
/*  48:    */  @org.schema.game.common.data.element.annotation.Element(tag="Factory", factory=true)
/*  49:    */  public BlockFactory factory;
/*  50:    */  
/*  51:    */  @org.schema.game.common.data.element.annotation.Element(tag="Animated")
/*  52:    */  public boolean animated;
/*  53:    */  
/*  54:    */  @org.schema.game.common.data.element.annotation.Element(from=0, to=2147483647, tag="Armour")
/*  55: 55 */  public int amour = 0;
/*  56:    */  
/*  57:    */  @org.schema.game.common.data.element.annotation.Element(tag="Transparency")
/*  58:    */  public boolean blended;
/*  59:    */  
/*  60:    */  @org.schema.game.common.data.element.annotation.Element(tag="InShop")
/*  61: 61 */  public boolean shoppable = true;
/*  62:    */  
/*  63:    */  @org.schema.game.common.data.element.annotation.Element(tag="Orientation")
/*  64:    */  public boolean orientatable;
/*  65:    */  
/*  66:    */  @org.schema.game.common.data.element.annotation.Element(tag="Enterable")
/*  67:    */  public boolean enterable;
/*  68:    */  
/*  69:    */  @org.schema.game.common.data.element.annotation.Element(tag="LightSource")
/*  70:    */  public boolean lightSource;
/*  71:    */  
/*  72:    */  @org.schema.game.common.data.element.annotation.Element(from=1, to=511, tag="Hitpoints")
/*  73: 73 */  public short maxHitPoints = 100;
/*  74:    */  
/*  79:    */  @org.schema.game.common.data.element.annotation.Element(tag="Placable")
/*  80: 80 */  public boolean placable = true;
/*  81:    */  
/*  82:    */  @org.schema.game.common.data.element.annotation.Element(tag="CanActivate")
/*  83:    */  public boolean canActivate;
/*  84:    */  
/*  85:    */  @org.schema.game.common.data.element.annotation.Element(tag="Physical")
/*  86: 86 */  public boolean physical = true;
/*  87:    */  
/*  88:    */  @org.schema.game.common.data.element.annotation.Element(tag="BlockStyle", states={"0", "1", "2", "3"})
/*  89:    */  public int blockStyle;
/*  90:    */  
/*  91:    */  @org.schema.game.common.data.element.annotation.Element(tag="LightSourceColor", vector3f=true)
/*  92: 92 */  public final Vector3f lightSourceColor = new Vector3f(1.0F, 1.0F, 1.0F);
/*  93:    */  
/*  95:    */  @org.schema.game.common.data.element.annotation.Element(tag="Level", level=true)
/*  96:    */  public BlockLevel level;
/*  97:    */  
/*  99:    */  private float armourPercent;
/* 100:    */  
/* 103:    */  public ElementInformation(short paramShort1, String paramString, Class paramClass, short paramShort2)
/* 104:    */  {
/* 105:105 */    this.name = paramString;
/* 106:106 */    this.type = paramClass;
/* 107:107 */    this.textureId = paramShort2;
/* 108:108 */    this.id = paramShort1;
/* 109:    */  }
/* 110:    */  
/* 111:111 */  public boolean canActivate() { return this.canActivate; }
/* 112:    */  
/* 115:    */  public int getAmour()
/* 116:    */  {
/* 117:117 */    return this.amour;
/* 118:    */  }
/* 119:    */  
/* 121:    */  public int getBlockStyle()
/* 122:    */  {
/* 123:123 */    return this.blockStyle;
/* 124:    */  }
/* 125:    */  
/* 127:    */  public int getBuildIconNum()
/* 128:    */  {
/* 129:129 */    return this.buildIconNum;
/* 130:    */  }
/* 131:    */  
/* 132:132 */  public Set getControlledBy() { return this.controlledBy; }
/* 133:    */  
/* 134:    */  public Set getControlling() {
/* 135:135 */    return this.controlling;
/* 136:    */  }
/* 137:    */  
/* 139:    */  public String getDescription()
/* 140:    */  {
/* 141:141 */    return this.description;
/* 142:    */  }
/* 143:    */  
/* 145:    */  public String getFullName()
/* 146:    */  {
/* 147:147 */    if (this.fullName == null) {
/* 148:148 */      return getName();
/* 149:    */    }
/* 150:150 */    return this.fullName;
/* 151:    */  }
/* 152:    */  
/* 154:    */  public short getId()
/* 155:    */  {
/* 156:156 */    return this.id;
/* 157:    */  }
/* 158:    */  
/* 160:    */  public Vector3f getLightSourceColor()
/* 161:    */  {
/* 162:162 */    return this.lightSourceColor;
/* 163:    */  }
/* 164:    */  
/* 166:    */  public short getMaxHitPoints()
/* 167:    */  {
/* 168:168 */    return this.maxHitPoints;
/* 169:    */  }
/* 170:    */  
/* 172:    */  public String getName()
/* 173:    */  {
/* 174:174 */    return this.name;
/* 175:    */  }
/* 176:    */  
/* 178:    */  public long getPrice()
/* 179:    */  {
/* 180:180 */    return this.price;
/* 181:    */  }
/* 182:    */  
/* 184:    */  public short getTextureId()
/* 185:    */  {
/* 186:186 */    return this.textureId;
/* 187:    */  }
/* 188:    */  
/* 189:189 */  public Class getType() { return this.type; }
/* 190:    */  
/* 193:    */  public boolean isAnimated()
/* 194:    */  {
/* 195:195 */    return this.animated;
/* 196:    */  }
/* 197:    */  
/* 199:    */  public boolean isBlended()
/* 200:    */  {
/* 201:201 */    return this.blended;
/* 202:    */  }
/* 203:    */  
/* 204:204 */  public boolean isController() { return !getControlling().isEmpty(); }
/* 205:    */  
/* 208:    */  public boolean isEnterable()
/* 209:    */  {
/* 210:210 */    return this.enterable;
/* 211:    */  }
/* 212:    */  
/* 213:213 */  public int getIndividualSides() { return this.individualSides; }
/* 214:    */  
/* 215:    */  public boolean isLightSource() {
/* 216:216 */    return this.lightSource;
/* 217:    */  }
/* 218:    */  
/* 219:219 */  public boolean isOrientatable() { return this.orientatable; }
/* 220:    */  
/* 224:    */  public boolean isPhysical()
/* 225:    */  {
/* 226:226 */    return this.physical;
/* 227:    */  }
/* 228:    */  
/* 230:    */  public boolean isPhysical(boolean paramBoolean)
/* 231:    */  {
/* 232:232 */    if (this.id == 122) {
/* 233:233 */      return paramBoolean;
/* 234:    */    }
/* 235:235 */    return this.physical;
/* 236:    */  }
/* 237:    */  
/* 238:238 */  public boolean isPlacable() { return this.placable; }
/* 239:    */  
/* 242:    */  public boolean isShoppable()
/* 243:    */  {
/* 244:244 */    return this.shoppable;
/* 245:    */  }
/* 246:    */  
/* 248:    */  public void setAmour(int paramInt)
/* 249:    */  {
/* 250:250 */    this.amour = paramInt;
/* 251:251 */    this.armourPercent = (paramInt / 100.0F);
/* 252:    */  }
/* 253:    */  
/* 255:    */  public void setAnimated(boolean paramBoolean)
/* 256:    */  {
/* 257:257 */    this.animated = paramBoolean;
/* 258:    */  }
/* 259:    */  
/* 262:    */  public void setBlended(boolean paramBoolean)
/* 263:    */  {
/* 264:264 */    this.blended = paramBoolean;
/* 265:    */  }
/* 266:    */  
/* 268:    */  public void setBlockStyle(int paramInt)
/* 269:    */  {
/* 270:270 */    this.blockStyle = paramInt;
/* 271:    */  }
/* 272:    */  
/* 274:    */  public void setBuildIconNum(int paramInt)
/* 275:    */  {
/* 276:276 */    this.buildIconNum = paramInt;
/* 277:    */  }
/* 278:    */  
/* 280:    */  public void setCanActivate(boolean paramBoolean)
/* 281:    */  {
/* 282:282 */    this.canActivate = paramBoolean;
/* 283:    */  }
/* 284:    */  
/* 287:    */  public void setDescription(String paramString)
/* 288:    */  {
/* 289:289 */    this.description = paramString;
/* 290:    */  }
/* 291:    */  
/* 293:    */  public void setEnterable(boolean paramBoolean)
/* 294:    */  {
/* 295:295 */    this.enterable = paramBoolean;
/* 296:    */  }
/* 297:    */  
/* 298:298 */  public void setFullName(String paramString) { this.fullName = paramString; }
/* 299:    */  
/* 300:    */  public void setIndividualSides(int paramInt) {
/* 301:301 */    this.individualSides = paramInt;
/* 302:    */  }
/* 303:    */  
/* 304:304 */  public void setLightSource(boolean paramBoolean) { this.lightSource = paramBoolean; }
/* 305:    */  
/* 308:    */  public void setMaxHitPoints(short paramShort)
/* 309:    */  {
/* 310:310 */    this.maxHitPoints = paramShort;
/* 311:    */  }
/* 312:    */  
/* 313:313 */  public void setOrientatable(boolean paramBoolean) { this.orientatable = paramBoolean; }
/* 314:    */  
/* 317:    */  public void setPhysical(boolean paramBoolean)
/* 318:    */  {
/* 319:319 */    this.physical = paramBoolean;
/* 320:    */  }
/* 321:    */  
/* 323:    */  public void setPlacable(boolean paramBoolean)
/* 324:    */  {
/* 325:325 */    this.placable = paramBoolean;
/* 326:    */  }
/* 327:    */  
/* 329:    */  public void setPrice(long paramLong)
/* 330:    */  {
/* 331:331 */    this.price = paramLong;
/* 332:    */  }
/* 333:    */  
/* 335:    */  public void setShoppable(boolean paramBoolean)
/* 336:    */  {
/* 337:337 */    this.shoppable = paramBoolean;
/* 338:    */  }
/* 339:    */  
/* 341:    */  public BlockLevel getLevel()
/* 342:    */  {
/* 343:343 */    return this.level;
/* 344:    */  }
/* 345:    */  
/* 347:    */  public void setLevel(BlockLevel paramBlockLevel)
/* 348:    */  {
/* 349:349 */    this.level = paramBlockLevel;
/* 350:    */  }
/* 351:    */  
/* 352:    */  public boolean isLeveled() {
/* 353:353 */    return this.level != null;
/* 354:    */  }
/* 355:    */  
/* 356:    */  public void setFactory(BlockFactory paramBlockFactory) {
/* 357:357 */    this.factory = paramBlockFactory;
/* 358:    */  }
/* 359:    */  
/* 360:360 */  public BlockFactory getFactory() { return this.factory; }
/* 361:    */  
/* 362:    */  public String toString()
/* 363:    */  {
/* 364:364 */    return getName() + "(" + getId() + ")";
/* 365:    */  }
/* 366:    */  
/* 368:368 */  public int compareTo(ElementInformation paramElementInformation) { return this.name.compareTo(paramElementInformation.name); }
/* 369:    */  
/* 370:    */  public static String getKeyId(short paramShort) {
/* 371:371 */    Object localObject = ElementKeyMap.properties.entrySet();
/* 372:372 */    String str = null;
/* 373:373 */    for (localObject = ((Set)localObject).iterator(); ((Iterator)localObject).hasNext();) { Map.Entry localEntry;
/* 374:374 */      if ((localEntry = (Map.Entry)((Iterator)localObject).next()).getValue().equals(String.valueOf(paramShort))) {
/* 375:375 */        str = localEntry.getKey().toString();
/* 376:376 */        break;
/* 377:    */      }
/* 378:    */    }
/* 379:379 */    return str;
/* 380:    */  }
/* 381:    */  
/* 386:    */  public boolean equals(Object paramObject)
/* 387:    */  {
/* 388:388 */    return ((ElementInformation)paramObject).getId() == getId();
/* 389:    */  }
/* 390:    */  
/* 395:395 */  public int hashCode() { return getId(); }
/* 396:    */  
/* 397:    */  public void appendXML(Document paramDocument, org.w3c.dom.Element paramElement) {
/* 398:398 */    Object localObject1 = getName().replaceAll("[^a-zA-Z]+", "");
/* 399:    */    
/* 400:400 */    localObject1 = paramDocument.createElement((String)localObject1);
/* 401:    */    
/* 404:    */    Object localObject2;
/* 405:    */    
/* 407:407 */    if ((localObject2 = getKeyId(getId())) == null) {
/* 408:408 */      throw new CannotAppendXMLException("Cannot find property key for Block ID " + getId() + "; Check your Block properties file");
/* 409:    */    }
/* 410:410 */    ((org.w3c.dom.Element)localObject1).setAttribute("type", (String)localObject2);
/* 411:411 */    ((org.w3c.dom.Element)localObject1).setAttribute("icon", String.valueOf(getBuildIconNum()));
/* 412:412 */    ((org.w3c.dom.Element)localObject1).setAttribute("textureId", String.valueOf(getTextureId()));
/* 413:413 */    ((org.w3c.dom.Element)localObject1).setAttribute("name", this.name);
/* 414:    */    
/* 416:416 */    for (Object localObject3 : ElementInformation.class.getFields()) {
/* 417:    */      Object localObject4;
/* 418:    */      try {
/* 419:418 */        if (localObject3.get(this) == null) {
/* 420:419 */          continue;
/* 421:    */        }
/* 422:    */      } catch (IllegalArgumentException localIllegalArgumentException) {
/* 423:422 */        (localObject4 = 
/* 424:    */        
/* 428:427 */          localIllegalArgumentException).printStackTrace();throw new CannotAppendXMLException(((IllegalArgumentException)localObject4).getMessage());
/* 429:    */      } catch (IllegalAccessException localIllegalAccessException) {
/* 430:425 */        (localObject4 = localIllegalAccessException).printStackTrace();
/* 431:426 */        throw new CannotAppendXMLException(((IllegalAccessException)localObject4).getMessage());
/* 432:    */      }
/* 433:    */      
/* 436:431 */      if ((localObject4 = (org.schema.game.common.data.element.annotation.Element)localObject3.getAnnotation(org.schema.game.common.data.element.annotation.Element.class)) != null)
/* 437:    */      {
/* 438:433 */        org.w3c.dom.Element localElement1 = paramDocument.createElement(((org.schema.game.common.data.element.annotation.Element)localObject4).tag());
/* 439:    */        try { Object localObject6;
/* 440:435 */          Object localObject7; org.w3c.dom.Element localElement2; if (((org.schema.game.common.data.element.annotation.Element)localObject4).factory()) {
/* 441:436 */            if (getFactory().input == null) {
/* 442:437 */              localElement1.setTextContent("INPUT");
/* 448:    */            }
/* 449:    */            else
/* 450:    */            {
/* 455:450 */              for (int k = 0; k < getFactory().input.length; k++) {
/* 456:451 */                localObject6 = paramDocument.createElement("Product");
/* 457:    */                
/* 458:453 */                localObject7 = paramDocument.createElement("Input");
/* 459:454 */                localElement2 = paramDocument.createElement("Output");
/* 460:    */                org.w3c.dom.Element localElement3;
/* 461:456 */                for (int m = 0; m < getFactory().input[k].length; m++) {
/* 462:457 */                  localObject4 = getFactory().input[k][m];
/* 463:458 */                  (
/* 464:459 */                    localElement3 = paramDocument.createElement("Item")).setAttribute("count", String.valueOf(((FactoryResource)localObject4).count));
/* 465:    */                  
/* 467:462 */                  if ((localObject4 = getKeyId(((FactoryResource)localObject4).type)) == null) {
/* 468:463 */                    throw new CannotAppendXMLException("[Factory][Input] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
/* 469:    */                  }
/* 470:    */                  
/* 471:466 */                  localElement3.setTextContent((String)localObject4);
/* 472:    */                  
/* 473:468 */                  ((org.w3c.dom.Element)localObject7).appendChild(localElement3);
/* 474:    */                }
/* 475:    */                
/* 476:471 */                for (m = 0; m < getFactory().output[k].length; m++) {
/* 477:472 */                  localObject4 = getFactory().output[k][m];
/* 478:473 */                  (
/* 479:474 */                    localElement3 = paramDocument.createElement("Item")).setAttribute("count", String.valueOf(((FactoryResource)localObject4).count));
/* 480:    */                  
/* 482:477 */                  if ((localObject4 = getKeyId(((FactoryResource)localObject4).type)) == null) {
/* 483:478 */                    throw new CannotAppendXMLException("[Factory][Output] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
/* 484:    */                  }
/* 485:    */                  
/* 486:481 */                  localElement3.setTextContent((String)localObject4);
/* 487:    */                  
/* 488:483 */                  localElement2.appendChild(localElement3);
/* 489:    */                }
/* 490:    */                
/* 491:486 */                ((org.w3c.dom.Element)localObject6).appendChild((Node)localObject7);
/* 492:487 */                ((org.w3c.dom.Element)localObject6).appendChild(localElement2);
/* 493:488 */                localElement1.appendChild((Node)localObject6);
/* 495:    */              }
/* 496:    */              
/* 497:    */            }
/* 498:    */            
/* 500:    */          }
/* 501:496 */          else if (((org.schema.game.common.data.element.annotation.Element)localObject4).level())
/* 502:    */          {
/* 503:498 */            localObject5 = paramDocument.createElement("Id");
/* 504:499 */            localObject6 = paramDocument.createElement("Nr");
/* 505:    */            
/* 506:501 */            if ((localObject7 = getKeyId(getLevel().getIdBase())) == null) {
/* 507:502 */              throw new CannotAppendXMLException("[Level] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
/* 508:    */            }
/* 509:504 */            ((org.w3c.dom.Element)localObject5).setTextContent((String)localObject7);
/* 510:505 */            ((org.w3c.dom.Element)localObject6).setTextContent(String.valueOf(getLevel().getLevel()));
/* 511:    */            
/* 512:507 */            localElement1.appendChild((Node)localObject5);
/* 513:508 */            localElement1.appendChild((Node)localObject6);
/* 514:509 */          } else if (((org.schema.game.common.data.element.annotation.Element)localObject4).vector3f()) {
/* 515:510 */            localObject5 = (Vector3f)localObject3.get(this);
/* 516:    */            
/* 517:512 */            localElement1.setTextContent(((Vector3f)localObject5).x + "," + ((Vector3f)localObject5).y + "," + ((Vector3f)localObject5).z);
/* 518:    */          }
/* 519:514 */          else if (((org.schema.game.common.data.element.annotation.Element)localObject4).collectionType().equals("blockTypes"))
/* 520:    */          {
/* 522:517 */            if ((localObject5 = (Set)localObject3.get(this)).isEmpty()) {
/* 523:    */              continue;
/* 524:    */            }
/* 525:520 */            for (localObject6 = ((Set)localObject5).iterator(); ((Iterator)localObject6).hasNext();) { localObject7 = (Short)((Iterator)localObject6).next();
/* 526:521 */              if ((!ElementKeyMap.getFactorykeyset().contains(Short.valueOf(getId()))) || (!ElementKeyMap.getFactorykeyset().contains(localObject7))) {
/* 527:522 */                localElement2 = paramDocument.createElement(((org.schema.game.common.data.element.annotation.Element)localObject4).collectionElementTag());
/* 528:    */                
/* 530:    */                String str;
/* 531:    */                
/* 532:527 */                if ((str = getKeyId(((Short)localObject7).shortValue())) == null) {
/* 533:528 */                  throw new CannotAppendXMLException("[BlockSet] " + localObject3.getName() + " Cannot find property key for Block ID " + localObject7 + "; Check your Block properties file");
/* 534:    */                }
/* 535:530 */                localElement2.setTextContent(str);
/* 536:531 */                localElement1.appendChild(localElement2);
/* 537:    */              }
/* 538:    */            }
/* 539:    */          } else {
/* 540:535 */            localObject5 = localObject3.get(this).toString();
/* 541:    */            
/* 542:537 */            if (((org.schema.game.common.data.element.annotation.Element)localObject4).textArea()) {
/* 543:538 */              localObject5 = ((String)localObject5).replace("\n", "\\n\\r");
/* 544:    */            }
/* 545:540 */            if (((String)localObject5).length() == 0) {
/* 546:    */              continue;
/* 547:    */            }
/* 548:543 */            localElement1.setTextContent((String)localObject5);
/* 549:    */          }
/* 550:    */        } catch (Exception localException) {
/* 551:    */          Object localObject5;
/* 552:547 */          (localObject5 = 
/* 553:    */          
/* 554:549 */            localException).printStackTrace();throw new CannotAppendXMLException(((Exception)localObject5).getMessage());
/* 555:    */        }
/* 556:    */        
/* 557:551 */        ((org.w3c.dom.Element)localObject1).appendChild(localElement1);
/* 558:    */      }
/* 559:    */    }
/* 560:    */    
/* 561:555 */    paramElement.appendChild((Node)localObject1);
/* 562:    */  }
/* 563:    */  
/* 564:    */  public boolean isDockable() {
/* 565:559 */    return (getId() == 7) || (getId() == 289);
/* 566:    */  }
/* 567:    */  
/* 569:    */  public float getArmourPercent()
/* 570:    */  {
/* 571:565 */    return this.armourPercent;
/* 572:    */  }
/* 573:    */  
/* 574:568 */  public static byte defaultActive(short paramShort) { return (byte)((paramShort == 16) || (paramShort == 32) || (paramShort == 48) || (paramShort == 40) || (paramShort == 30) || (paramShort == 24) ? 0 : 1); }
/* 575:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementInformation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */