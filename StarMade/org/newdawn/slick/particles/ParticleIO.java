/*   1:    */package org.newdawn.slick.particles;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileInputStream;
/*   5:    */import java.io.FileOutputStream;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.OutputStream;
/*   9:    */import java.io.OutputStreamWriter;
/*  10:    */import java.util.ArrayList;
/*  11:    */import javax.xml.parsers.DocumentBuilder;
/*  12:    */import javax.xml.parsers.DocumentBuilderFactory;
/*  13:    */import javax.xml.transform.Result;
/*  14:    */import javax.xml.transform.Transformer;
/*  15:    */import javax.xml.transform.TransformerFactory;
/*  16:    */import javax.xml.transform.dom.DOMSource;
/*  17:    */import javax.xml.transform.stream.StreamResult;
/*  18:    */import org.newdawn.slick.Color;
/*  19:    */import org.newdawn.slick.geom.Vector2f;
/*  20:    */import org.newdawn.slick.util.Log;
/*  21:    */import org.newdawn.slick.util.ResourceLoader;
/*  22:    */import org.w3c.dom.Document;
/*  23:    */import org.w3c.dom.Element;
/*  24:    */import org.w3c.dom.NodeList;
/*  25:    */
/*  47:    */public class ParticleIO
/*  48:    */{
/*  49:    */  public static ParticleSystem loadConfiguredSystem(String ref, Color mask)
/*  50:    */    throws IOException
/*  51:    */  {
/*  52: 52 */    return loadConfiguredSystem(ResourceLoader.getResourceAsStream(ref), null, null, mask);
/*  53:    */  }
/*  54:    */  
/*  64:    */  public static ParticleSystem loadConfiguredSystem(String ref)
/*  65:    */    throws IOException
/*  66:    */  {
/*  67: 67 */    return loadConfiguredSystem(ResourceLoader.getResourceAsStream(ref), null, null, null);
/*  68:    */  }
/*  69:    */  
/*  79:    */  public static ParticleSystem loadConfiguredSystem(File ref)
/*  80:    */    throws IOException
/*  81:    */  {
/*  82: 82 */    return loadConfiguredSystem(new FileInputStream(ref), null, null, null);
/*  83:    */  }
/*  84:    */  
/*  94:    */  public static ParticleSystem loadConfiguredSystem(InputStream ref, Color mask)
/*  95:    */    throws IOException
/*  96:    */  {
/*  97: 97 */    return loadConfiguredSystem(ref, null, null, mask);
/*  98:    */  }
/*  99:    */  
/* 108:    */  public static ParticleSystem loadConfiguredSystem(InputStream ref)
/* 109:    */    throws IOException
/* 110:    */  {
/* 111:111 */    return loadConfiguredSystem(ref, null, null, null);
/* 112:    */  }
/* 113:    */  
/* 125:    */  public static ParticleSystem loadConfiguredSystem(String ref, ConfigurableEmitterFactory factory)
/* 126:    */    throws IOException
/* 127:    */  {
/* 128:128 */    return loadConfiguredSystem(ResourceLoader.getResourceAsStream(ref), factory, null, null);
/* 129:    */  }
/* 130:    */  
/* 143:    */  public static ParticleSystem loadConfiguredSystem(File ref, ConfigurableEmitterFactory factory)
/* 144:    */    throws IOException
/* 145:    */  {
/* 146:146 */    return loadConfiguredSystem(new FileInputStream(ref), factory, null, null);
/* 147:    */  }
/* 148:    */  
/* 160:    */  public static ParticleSystem loadConfiguredSystem(InputStream ref, ConfigurableEmitterFactory factory)
/* 161:    */    throws IOException
/* 162:    */  {
/* 163:163 */    return loadConfiguredSystem(ref, factory, null, null);
/* 164:    */  }
/* 165:    */  
/* 179:    */  public static ParticleSystem loadConfiguredSystem(InputStream ref, ConfigurableEmitterFactory factory, ParticleSystem system, Color mask)
/* 180:    */    throws IOException
/* 181:    */  {
/* 182:182 */    if (factory == null) {
/* 183:183 */      factory = new ConfigurableEmitterFactory() {
/* 184:    */        public ConfigurableEmitter createEmitter(String name) {
/* 185:185 */          return new ConfigurableEmitter(name);
/* 186:    */        }
/* 187:    */      };
/* 188:    */    }
/* 189:    */    try {
/* 190:190 */      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 191:    */      
/* 192:192 */      Document document = builder.parse(ref);
/* 193:    */      
/* 194:194 */      Element element = document.getDocumentElement();
/* 195:195 */      if (!element.getNodeName().equals("system")) {
/* 196:196 */        throw new IOException("Not a particle system file");
/* 197:    */      }
/* 198:    */      
/* 199:199 */      if (system == null) {
/* 200:200 */        system = new ParticleSystem("org/newdawn/slick/data/particle.tga", 2000, mask);
/* 201:    */      }
/* 202:    */      
/* 203:203 */      boolean additive = "true".equals(element.getAttribute("additive"));
/* 204:204 */      if (additive) {
/* 205:205 */        system.setBlendingMode(1);
/* 206:    */      } else {
/* 207:207 */        system.setBlendingMode(2);
/* 208:    */      }
/* 209:209 */      boolean points = "true".equals(element.getAttribute("points"));
/* 210:210 */      system.setUsePoints(points);
/* 211:    */      
/* 212:212 */      NodeList list = element.getElementsByTagName("emitter");
/* 213:213 */      for (int i = 0; i < list.getLength(); i++) {
/* 214:214 */        Element em = (Element)list.item(i);
/* 215:215 */        ConfigurableEmitter emitter = factory.createEmitter("new");
/* 216:216 */        elementToEmitter(em, emitter);
/* 217:    */        
/* 218:218 */        system.addEmitter(emitter);
/* 219:    */      }
/* 220:    */      
/* 221:221 */      system.setRemoveCompletedEmitters(false);
/* 222:222 */      return system;
/* 223:    */    } catch (IOException e) {
/* 224:224 */      Log.error(e);
/* 225:225 */      throw e;
/* 226:    */    } catch (Exception e) {
/* 227:227 */      Log.error(e);
/* 228:228 */      throw new IOException("Unable to load particle system config");
/* 229:    */    }
/* 230:    */  }
/* 231:    */  
/* 241:    */  public static void saveConfiguredSystem(File file, ParticleSystem system)
/* 242:    */    throws IOException
/* 243:    */  {
/* 244:244 */    saveConfiguredSystem(new FileOutputStream(file), system);
/* 245:    */  }
/* 246:    */  
/* 255:    */  public static void saveConfiguredSystem(OutputStream out, ParticleSystem system)
/* 256:    */    throws IOException
/* 257:    */  {
/* 258:    */    try
/* 259:    */    {
/* 260:260 */      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 261:    */      
/* 262:262 */      Document document = builder.newDocument();
/* 263:    */      
/* 264:264 */      Element root = document.createElement("system");
/* 265:265 */      root.setAttribute("additive", new StringBuilder().append("").append(system.getBlendingMode() == 1).toString());
/* 266:    */      
/* 270:270 */      root.setAttribute("points", new StringBuilder().append("").append(system.usePoints()).toString());
/* 271:    */      
/* 272:272 */      document.appendChild(root);
/* 273:273 */      for (int i = 0; i < system.getEmitterCount(); i++) {
/* 274:274 */        ParticleEmitter current = system.getEmitter(i);
/* 275:275 */        if ((current instanceof ConfigurableEmitter)) {
/* 276:276 */          Element element = emitterToElement(document, (ConfigurableEmitter)current);
/* 277:    */          
/* 278:278 */          root.appendChild(element);
/* 279:    */        } else {
/* 280:280 */          throw new RuntimeException("Only ConfigurableEmitter instances can be stored");
/* 281:    */        }
/* 282:    */      }
/* 283:    */      
/* 285:285 */      Result result = new StreamResult(new OutputStreamWriter(out, "utf-8"));
/* 286:    */      
/* 287:287 */      DOMSource source = new DOMSource(document);
/* 288:    */      
/* 289:289 */      TransformerFactory factory = TransformerFactory.newInstance();
/* 290:290 */      Transformer xformer = factory.newTransformer();
/* 291:291 */      xformer.setOutputProperty("indent", "yes");
/* 292:    */      
/* 293:293 */      xformer.transform(source, result);
/* 294:    */    } catch (Exception e) {
/* 295:295 */      Log.error(e);
/* 296:296 */      throw new IOException("Unable to save configured particle system");
/* 297:    */    }
/* 298:    */  }
/* 299:    */  
/* 309:    */  public static ConfigurableEmitter loadEmitter(String ref)
/* 310:    */    throws IOException
/* 311:    */  {
/* 312:312 */    return loadEmitter(ResourceLoader.getResourceAsStream(ref), null);
/* 313:    */  }
/* 314:    */  
/* 322:    */  public static ConfigurableEmitter loadEmitter(File ref)
/* 323:    */    throws IOException
/* 324:    */  {
/* 325:325 */    return loadEmitter(new FileInputStream(ref), null);
/* 326:    */  }
/* 327:    */  
/* 337:    */  public static ConfigurableEmitter loadEmitter(InputStream ref)
/* 338:    */    throws IOException
/* 339:    */  {
/* 340:340 */    return loadEmitter(ref, null);
/* 341:    */  }
/* 342:    */  
/* 355:    */  public static ConfigurableEmitter loadEmitter(String ref, ConfigurableEmitterFactory factory)
/* 356:    */    throws IOException
/* 357:    */  {
/* 358:358 */    return loadEmitter(ResourceLoader.getResourceAsStream(ref), factory);
/* 359:    */  }
/* 360:    */  
/* 372:    */  public static ConfigurableEmitter loadEmitter(File ref, ConfigurableEmitterFactory factory)
/* 373:    */    throws IOException
/* 374:    */  {
/* 375:375 */    return loadEmitter(new FileInputStream(ref), factory);
/* 376:    */  }
/* 377:    */  
/* 390:    */  public static ConfigurableEmitter loadEmitter(InputStream ref, ConfigurableEmitterFactory factory)
/* 391:    */    throws IOException
/* 392:    */  {
/* 393:393 */    if (factory == null) {
/* 394:394 */      factory = new ConfigurableEmitterFactory() {
/* 395:    */        public ConfigurableEmitter createEmitter(String name) {
/* 396:396 */          return new ConfigurableEmitter(name);
/* 397:    */        }
/* 398:    */      };
/* 399:    */    }
/* 400:    */    try {
/* 401:401 */      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 402:    */      
/* 403:403 */      Document document = builder.parse(ref);
/* 404:    */      
/* 405:405 */      if (!document.getDocumentElement().getNodeName().equals("emitter")) {
/* 406:406 */        throw new IOException("Not a particle emitter file");
/* 407:    */      }
/* 408:    */      
/* 409:409 */      ConfigurableEmitter emitter = factory.createEmitter("new");
/* 410:410 */      elementToEmitter(document.getDocumentElement(), emitter);
/* 411:    */      
/* 412:412 */      return emitter;
/* 413:    */    } catch (IOException e) {
/* 414:414 */      Log.error(e);
/* 415:415 */      throw e;
/* 416:    */    } catch (Exception e) {
/* 417:417 */      Log.error(e);
/* 418:418 */      throw new IOException("Unable to load emitter");
/* 419:    */    }
/* 420:    */  }
/* 421:    */  
/* 431:    */  public static void saveEmitter(File file, ConfigurableEmitter emitter)
/* 432:    */    throws IOException
/* 433:    */  {
/* 434:434 */    saveEmitter(new FileOutputStream(file), emitter);
/* 435:    */  }
/* 436:    */  
/* 445:    */  public static void saveEmitter(OutputStream out, ConfigurableEmitter emitter)
/* 446:    */    throws IOException
/* 447:    */  {
/* 448:    */    try
/* 449:    */    {
/* 450:450 */      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 451:    */      
/* 452:452 */      Document document = builder.newDocument();
/* 453:    */      
/* 454:454 */      document.appendChild(emitterToElement(document, emitter));
/* 455:455 */      Result result = new StreamResult(new OutputStreamWriter(out, "utf-8"));
/* 456:    */      
/* 457:457 */      DOMSource source = new DOMSource(document);
/* 458:    */      
/* 459:459 */      TransformerFactory factory = TransformerFactory.newInstance();
/* 460:460 */      Transformer xformer = factory.newTransformer();
/* 461:461 */      xformer.setOutputProperty("indent", "yes");
/* 462:    */      
/* 463:463 */      xformer.transform(source, result);
/* 464:    */    } catch (Exception e) {
/* 465:465 */      Log.error(e);
/* 466:466 */      throw new IOException("Failed to save emitter");
/* 467:    */    }
/* 468:    */  }
/* 469:    */  
/* 478:    */  private static Element getFirstNamedElement(Element element, String name)
/* 479:    */  {
/* 480:480 */    NodeList list = element.getElementsByTagName(name);
/* 481:481 */    if (list.getLength() == 0) {
/* 482:482 */      return null;
/* 483:    */    }
/* 484:    */    
/* 485:485 */    return (Element)list.item(0);
/* 486:    */  }
/* 487:    */  
/* 496:    */  private static void elementToEmitter(Element element, ConfigurableEmitter emitter)
/* 497:    */  {
/* 498:498 */    emitter.name = element.getAttribute("name");
/* 499:499 */    emitter.setImageName(element.getAttribute("imageName"));
/* 500:    */    
/* 501:501 */    String renderType = element.getAttribute("renderType");
/* 502:502 */    emitter.usePoints = 1;
/* 503:503 */    if (renderType.equals("quads")) {
/* 504:504 */      emitter.usePoints = 3;
/* 505:    */    }
/* 506:506 */    if (renderType.equals("points")) {
/* 507:507 */      emitter.usePoints = 2;
/* 508:    */    }
/* 509:    */    
/* 510:510 */    String useOriented = element.getAttribute("useOriented");
/* 511:511 */    if (useOriented != null) {
/* 512:512 */      emitter.useOriented = "true".equals(useOriented);
/* 513:    */    }
/* 514:514 */    String useAdditive = element.getAttribute("useAdditive");
/* 515:515 */    if (useAdditive != null) {
/* 516:516 */      emitter.useAdditive = "true".equals(useAdditive);
/* 517:    */    }
/* 518:518 */    parseRangeElement(getFirstNamedElement(element, "spawnInterval"), emitter.spawnInterval);
/* 519:    */    
/* 520:520 */    parseRangeElement(getFirstNamedElement(element, "spawnCount"), emitter.spawnCount);
/* 521:    */    
/* 522:522 */    parseRangeElement(getFirstNamedElement(element, "initialLife"), emitter.initialLife);
/* 523:    */    
/* 524:524 */    parseRangeElement(getFirstNamedElement(element, "initialSize"), emitter.initialSize);
/* 525:    */    
/* 526:526 */    parseRangeElement(getFirstNamedElement(element, "xOffset"), emitter.xOffset);
/* 527:    */    
/* 528:528 */    parseRangeElement(getFirstNamedElement(element, "yOffset"), emitter.yOffset);
/* 529:    */    
/* 530:530 */    parseRangeElement(getFirstNamedElement(element, "initialDistance"), emitter.initialDistance);
/* 531:    */    
/* 532:532 */    parseRangeElement(getFirstNamedElement(element, "speed"), emitter.speed);
/* 533:533 */    parseRangeElement(getFirstNamedElement(element, "length"), emitter.length);
/* 534:    */    
/* 535:535 */    parseRangeElement(getFirstNamedElement(element, "emitCount"), emitter.emitCount);
/* 536:    */    
/* 538:538 */    parseValueElement(getFirstNamedElement(element, "spread"), emitter.spread);
/* 539:    */    
/* 540:540 */    parseValueElement(getFirstNamedElement(element, "angularOffset"), emitter.angularOffset);
/* 541:    */    
/* 542:542 */    parseValueElement(getFirstNamedElement(element, "growthFactor"), emitter.growthFactor);
/* 543:    */    
/* 544:544 */    parseValueElement(getFirstNamedElement(element, "gravityFactor"), emitter.gravityFactor);
/* 545:    */    
/* 546:546 */    parseValueElement(getFirstNamedElement(element, "windFactor"), emitter.windFactor);
/* 547:    */    
/* 548:548 */    parseValueElement(getFirstNamedElement(element, "startAlpha"), emitter.startAlpha);
/* 549:    */    
/* 550:550 */    parseValueElement(getFirstNamedElement(element, "endAlpha"), emitter.endAlpha);
/* 551:    */    
/* 552:552 */    parseValueElement(getFirstNamedElement(element, "alpha"), emitter.alpha);
/* 553:553 */    parseValueElement(getFirstNamedElement(element, "size"), emitter.size);
/* 554:554 */    parseValueElement(getFirstNamedElement(element, "velocity"), emitter.velocity);
/* 555:    */    
/* 556:556 */    parseValueElement(getFirstNamedElement(element, "scaleY"), emitter.scaleY);
/* 557:    */    
/* 559:559 */    Element color = getFirstNamedElement(element, "color");
/* 560:560 */    NodeList steps = color.getElementsByTagName("step");
/* 561:561 */    emitter.colors.clear();
/* 562:562 */    for (int i = 0; i < steps.getLength(); i++) {
/* 563:563 */      Element step = (Element)steps.item(i);
/* 564:564 */      float offset = Float.parseFloat(step.getAttribute("offset"));
/* 565:565 */      float r = Float.parseFloat(step.getAttribute("r"));
/* 566:566 */      float g = Float.parseFloat(step.getAttribute("g"));
/* 567:567 */      float b = Float.parseFloat(step.getAttribute("b"));
/* 568:    */      
/* 569:569 */      emitter.addColorPoint(offset, new Color(r, g, b, 1.0F));
/* 570:    */    }
/* 571:    */    
/* 573:573 */    emitter.replay();
/* 574:    */  }
/* 575:    */  
/* 585:    */  private static Element emitterToElement(Document document, ConfigurableEmitter emitter)
/* 586:    */  {
/* 587:587 */    Element root = document.createElement("emitter");
/* 588:588 */    root.setAttribute("name", emitter.name);
/* 589:589 */    root.setAttribute("imageName", emitter.imageName == null ? "" : emitter.imageName);
/* 590:    */    
/* 591:591 */    root.setAttribute("useOriented", emitter.useOriented ? "true" : "false");
/* 592:    */    
/* 594:594 */    root.setAttribute("useAdditive", emitter.useAdditive ? "true" : "false");
/* 595:    */    
/* 598:598 */    if (emitter.usePoints == 1) {
/* 599:599 */      root.setAttribute("renderType", "inherit");
/* 600:    */    }
/* 601:601 */    if (emitter.usePoints == 2) {
/* 602:602 */      root.setAttribute("renderType", "points");
/* 603:    */    }
/* 604:604 */    if (emitter.usePoints == 3) {
/* 605:605 */      root.setAttribute("renderType", "quads");
/* 606:    */    }
/* 607:    */    
/* 608:608 */    root.appendChild(createRangeElement(document, "spawnInterval", emitter.spawnInterval));
/* 609:    */    
/* 610:610 */    root.appendChild(createRangeElement(document, "spawnCount", emitter.spawnCount));
/* 611:    */    
/* 612:612 */    root.appendChild(createRangeElement(document, "initialLife", emitter.initialLife));
/* 613:    */    
/* 614:614 */    root.appendChild(createRangeElement(document, "initialSize", emitter.initialSize));
/* 615:    */    
/* 616:616 */    root.appendChild(createRangeElement(document, "xOffset", emitter.xOffset));
/* 617:    */    
/* 618:618 */    root.appendChild(createRangeElement(document, "yOffset", emitter.yOffset));
/* 619:    */    
/* 620:620 */    root.appendChild(createRangeElement(document, "initialDistance", emitter.initialDistance));
/* 621:    */    
/* 622:622 */    root.appendChild(createRangeElement(document, "speed", emitter.speed));
/* 623:623 */    root.appendChild(createRangeElement(document, "length", emitter.length));
/* 624:    */    
/* 626:626 */    root.appendChild(createRangeElement(document, "emitCount", emitter.emitCount));
/* 627:    */    
/* 629:629 */    root.appendChild(createValueElement(document, "spread", emitter.spread));
/* 630:    */    
/* 632:632 */    root.appendChild(createValueElement(document, "angularOffset", emitter.angularOffset));
/* 633:    */    
/* 634:634 */    root.appendChild(createValueElement(document, "growthFactor", emitter.growthFactor));
/* 635:    */    
/* 636:636 */    root.appendChild(createValueElement(document, "gravityFactor", emitter.gravityFactor));
/* 637:    */    
/* 638:638 */    root.appendChild(createValueElement(document, "windFactor", emitter.windFactor));
/* 639:    */    
/* 640:640 */    root.appendChild(createValueElement(document, "startAlpha", emitter.startAlpha));
/* 641:    */    
/* 642:642 */    root.appendChild(createValueElement(document, "endAlpha", emitter.endAlpha));
/* 643:    */    
/* 644:644 */    root.appendChild(createValueElement(document, "alpha", emitter.alpha));
/* 645:645 */    root.appendChild(createValueElement(document, "size", emitter.size));
/* 646:646 */    root.appendChild(createValueElement(document, "velocity", emitter.velocity));
/* 647:    */    
/* 648:648 */    root.appendChild(createValueElement(document, "scaleY", emitter.scaleY));
/* 649:    */    
/* 652:652 */    Element color = document.createElement("color");
/* 653:653 */    ArrayList list = emitter.colors;
/* 654:654 */    for (int i = 0; i < list.size(); i++) {
/* 655:655 */      ConfigurableEmitter.ColorRecord record = (ConfigurableEmitter.ColorRecord)list.get(i);
/* 656:656 */      Element step = document.createElement("step");
/* 657:657 */      step.setAttribute("offset", new StringBuilder().append("").append(record.pos).toString());
/* 658:658 */      step.setAttribute("r", new StringBuilder().append("").append(record.col.r).toString());
/* 659:659 */      step.setAttribute("g", new StringBuilder().append("").append(record.col.g).toString());
/* 660:660 */      step.setAttribute("b", new StringBuilder().append("").append(record.col.b).toString());
/* 661:    */      
/* 662:662 */      color.appendChild(step);
/* 663:    */    }
/* 664:    */    
/* 665:665 */    root.appendChild(color);
/* 666:    */    
/* 667:667 */    return root;
/* 668:    */  }
/* 669:    */  
/* 681:    */  private static Element createRangeElement(Document document, String name, ConfigurableEmitter.Range range)
/* 682:    */  {
/* 683:683 */    Element element = document.createElement(name);
/* 684:684 */    element.setAttribute("min", new StringBuilder().append("").append(range.getMin()).toString());
/* 685:685 */    element.setAttribute("max", new StringBuilder().append("").append(range.getMax()).toString());
/* 686:686 */    element.setAttribute("enabled", new StringBuilder().append("").append(range.isEnabled()).toString());
/* 687:    */    
/* 688:688 */    return element;
/* 689:    */  }
/* 690:    */  
/* 702:    */  private static Element createValueElement(Document document, String name, ConfigurableEmitter.Value value)
/* 703:    */  {
/* 704:704 */    Element element = document.createElement(name);
/* 705:    */    
/* 707:707 */    if ((value instanceof ConfigurableEmitter.SimpleValue)) {
/* 708:708 */      element.setAttribute("type", "simple");
/* 709:709 */      element.setAttribute("value", new StringBuilder().append("").append(value.getValue(0.0F)).toString());
/* 710:710 */    } else if ((value instanceof ConfigurableEmitter.RandomValue)) {
/* 711:711 */      element.setAttribute("type", "random");
/* 712:712 */      element.setAttribute("value", new StringBuilder().append("").append(((ConfigurableEmitter.RandomValue)value).getValue()).toString());
/* 714:    */    }
/* 715:715 */    else if ((value instanceof ConfigurableEmitter.LinearInterpolator)) {
/* 716:716 */      element.setAttribute("type", "linear");
/* 717:717 */      element.setAttribute("min", new StringBuilder().append("").append(((ConfigurableEmitter.LinearInterpolator)value).getMin()).toString());
/* 718:    */      
/* 719:719 */      element.setAttribute("max", new StringBuilder().append("").append(((ConfigurableEmitter.LinearInterpolator)value).getMax()).toString());
/* 720:    */      
/* 721:721 */      element.setAttribute("active", new StringBuilder().append("").append(((ConfigurableEmitter.LinearInterpolator)value).isActive()).toString());
/* 722:    */      
/* 724:724 */      ArrayList curve = ((ConfigurableEmitter.LinearInterpolator)value).getCurve();
/* 725:725 */      for (int i = 0; i < curve.size(); i++) {
/* 726:726 */        Vector2f point = (Vector2f)curve.get(i);
/* 727:    */        
/* 728:728 */        Element pointElement = document.createElement("point");
/* 729:729 */        pointElement.setAttribute("x", new StringBuilder().append("").append(point.x).toString());
/* 730:730 */        pointElement.setAttribute("y", new StringBuilder().append("").append(point.y).toString());
/* 731:    */        
/* 732:732 */        element.appendChild(pointElement);
/* 733:    */      }
/* 734:    */    } else {
/* 735:735 */      Log.warn(new StringBuilder().append("unkown value type ignored: ").append(value.getClass()).toString());
/* 736:    */    }
/* 737:    */    
/* 738:738 */    return element;
/* 739:    */  }
/* 740:    */  
/* 749:    */  private static void parseRangeElement(Element element, ConfigurableEmitter.Range range)
/* 750:    */  {
/* 751:751 */    if (element == null) {
/* 752:752 */      return;
/* 753:    */    }
/* 754:754 */    range.setMin(Float.parseFloat(element.getAttribute("min")));
/* 755:755 */    range.setMax(Float.parseFloat(element.getAttribute("max")));
/* 756:756 */    range.setEnabled("true".equals(element.getAttribute("enabled")));
/* 757:    */  }
/* 758:    */  
/* 767:    */  private static void parseValueElement(Element element, ConfigurableEmitter.Value value)
/* 768:    */  {
/* 769:769 */    if (element == null) {
/* 770:770 */      return;
/* 771:    */    }
/* 772:    */    
/* 773:773 */    String type = element.getAttribute("type");
/* 774:774 */    String v = element.getAttribute("value");
/* 775:    */    
/* 776:776 */    if ((type == null) || (type.length() == 0))
/* 777:    */    {
/* 778:778 */      if ((value instanceof ConfigurableEmitter.SimpleValue)) {
/* 779:779 */        ((ConfigurableEmitter.SimpleValue)value).setValue(Float.parseFloat(v));
/* 780:780 */      } else if ((value instanceof ConfigurableEmitter.RandomValue)) {
/* 781:781 */        ((ConfigurableEmitter.RandomValue)value).setValue(Float.parseFloat(v));
/* 782:    */      } else {
/* 783:783 */        Log.warn(new StringBuilder().append("problems reading element, skipping: ").append(element).toString());
/* 784:    */      }
/* 785:    */      
/* 786:    */    }
/* 787:787 */    else if (type.equals("simple")) {
/* 788:788 */      ((ConfigurableEmitter.SimpleValue)value).setValue(Float.parseFloat(v));
/* 789:789 */    } else if (type.equals("random")) {
/* 790:790 */      ((ConfigurableEmitter.RandomValue)value).setValue(Float.parseFloat(v));
/* 791:791 */    } else if (type.equals("linear")) {
/* 792:792 */      String min = element.getAttribute("min");
/* 793:793 */      String max = element.getAttribute("max");
/* 794:794 */      String active = element.getAttribute("active");
/* 795:    */      
/* 796:796 */      NodeList points = element.getElementsByTagName("point");
/* 797:    */      
/* 798:798 */      ArrayList curve = new ArrayList();
/* 799:799 */      for (int i = 0; i < points.getLength(); i++) {
/* 800:800 */        Element point = (Element)points.item(i);
/* 801:    */        
/* 802:802 */        float x = Float.parseFloat(point.getAttribute("x"));
/* 803:803 */        float y = Float.parseFloat(point.getAttribute("y"));
/* 804:    */        
/* 805:805 */        curve.add(new Vector2f(x, y));
/* 806:    */      }
/* 807:    */      
/* 808:808 */      ((ConfigurableEmitter.LinearInterpolator)value).setCurve(curve);
/* 809:809 */      ((ConfigurableEmitter.LinearInterpolator)value).setMin(Integer.parseInt(min));
/* 810:810 */      ((ConfigurableEmitter.LinearInterpolator)value).setMax(Integer.parseInt(max));
/* 811:811 */      ((ConfigurableEmitter.LinearInterpolator)value).setActive("true".equals(active));
/* 812:    */    } else {
/* 813:813 */      Log.warn(new StringBuilder().append("unkown type detected: ").append(type).toString());
/* 814:    */    }
/* 815:    */  }
/* 816:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.particles.ParticleIO
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */