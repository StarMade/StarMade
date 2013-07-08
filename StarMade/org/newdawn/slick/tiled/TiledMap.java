/*   1:    */package org.newdawn.slick.tiled;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Properties;
/*   8:    */import javax.xml.parsers.DocumentBuilder;
/*   9:    */import javax.xml.parsers.DocumentBuilderFactory;
/*  10:    */import org.newdawn.slick.Image;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.SpriteSheet;
/*  13:    */import org.newdawn.slick.util.Log;
/*  14:    */import org.newdawn.slick.util.ResourceLoader;
/*  15:    */import org.w3c.dom.Document;
/*  16:    */import org.w3c.dom.Element;
/*  17:    */import org.w3c.dom.NodeList;
/*  18:    */import org.xml.sax.EntityResolver;
/*  19:    */import org.xml.sax.InputSource;
/*  20:    */import org.xml.sax.SAXException;
/*  21:    */
/*  32:    */public class TiledMap
/*  33:    */{
/*  34:    */  private static boolean headless;
/*  35:    */  protected int width;
/*  36:    */  protected int height;
/*  37:    */  protected int tileWidth;
/*  38:    */  protected int tileHeight;
/*  39:    */  protected String tilesLocation;
/*  40:    */  protected Properties props;
/*  41:    */  
/*  42:    */  private static void setHeadless(boolean h)
/*  43:    */  {
/*  44: 44 */    headless = h;
/*  45:    */  }
/*  46:    */  
/*  63: 63 */  protected ArrayList tileSets = new ArrayList();
/*  64:    */  
/*  65: 65 */  protected ArrayList layers = new ArrayList();
/*  66:    */  
/*  67: 67 */  protected ArrayList objectGroups = new ArrayList();
/*  68:    */  
/*  70:    */  protected static final int ORTHOGONAL = 1;
/*  71:    */  
/*  73:    */  protected static final int ISOMETRIC = 2;
/*  74:    */  
/*  76:    */  protected int orientation;
/*  77:    */  
/*  78: 78 */  private boolean loadTileSets = true;
/*  79:    */  
/*  84:    */  public TiledMap(String ref)
/*  85:    */    throws SlickException
/*  86:    */  {
/*  87: 87 */    this(ref, true);
/*  88:    */  }
/*  89:    */  
/*  95:    */  public TiledMap(String ref, boolean loadTileSets)
/*  96:    */    throws SlickException
/*  97:    */  {
/*  98: 98 */    this.loadTileSets = loadTileSets;
/*  99: 99 */    ref = ref.replace('\\', '/');
/* 100:100 */    load(ResourceLoader.getResourceAsStream(ref), ref.substring(0, ref.lastIndexOf("/")));
/* 101:    */  }
/* 102:    */  
/* 108:    */  public TiledMap(String ref, String tileSetsLocation)
/* 109:    */    throws SlickException
/* 110:    */  {
/* 111:111 */    load(ResourceLoader.getResourceAsStream(ref), tileSetsLocation);
/* 112:    */  }
/* 113:    */  
/* 118:    */  public TiledMap(InputStream in)
/* 119:    */    throws SlickException
/* 120:    */  {
/* 121:121 */    load(in, "");
/* 122:    */  }
/* 123:    */  
/* 129:    */  public TiledMap(InputStream in, String tileSetsLocation)
/* 130:    */    throws SlickException
/* 131:    */  {
/* 132:132 */    load(in, tileSetsLocation);
/* 133:    */  }
/* 134:    */  
/* 139:    */  public String getTilesLocation()
/* 140:    */  {
/* 141:141 */    return this.tilesLocation;
/* 142:    */  }
/* 143:    */  
/* 149:    */  public int getLayerIndex(String name)
/* 150:    */  {
/* 151:151 */    int idx = 0;
/* 152:    */    
/* 153:153 */    for (int i = 0; i < this.layers.size(); i++) {
/* 154:154 */      Layer layer = (Layer)this.layers.get(i);
/* 155:    */      
/* 156:156 */      if (layer.name.equals(name)) {
/* 157:157 */        return i;
/* 158:    */      }
/* 159:    */    }
/* 160:    */    
/* 161:161 */    return -1;
/* 162:    */  }
/* 163:    */  
/* 172:    */  public Image getTileImage(int x, int y, int layerIndex)
/* 173:    */  {
/* 174:174 */    Layer layer = (Layer)this.layers.get(layerIndex);
/* 175:    */    
/* 176:176 */    int tileSetIndex = layer.data[x][y][0];
/* 177:177 */    if ((tileSetIndex >= 0) && (tileSetIndex < this.tileSets.size())) {
/* 178:178 */      TileSet tileSet = (TileSet)this.tileSets.get(tileSetIndex);
/* 179:    */      
/* 180:180 */      int sheetX = tileSet.getTileX(layer.data[x][y][1]);
/* 181:181 */      int sheetY = tileSet.getTileY(layer.data[x][y][1]);
/* 182:    */      
/* 183:183 */      return tileSet.tiles.getSprite(sheetX, sheetY);
/* 184:    */    }
/* 185:    */    
/* 186:186 */    return null;
/* 187:    */  }
/* 188:    */  
/* 193:    */  public int getWidth()
/* 194:    */  {
/* 195:195 */    return this.width;
/* 196:    */  }
/* 197:    */  
/* 202:    */  public int getHeight()
/* 203:    */  {
/* 204:204 */    return this.height;
/* 205:    */  }
/* 206:    */  
/* 211:    */  public int getTileHeight()
/* 212:    */  {
/* 213:213 */    return this.tileHeight;
/* 214:    */  }
/* 215:    */  
/* 220:    */  public int getTileWidth()
/* 221:    */  {
/* 222:222 */    return this.tileWidth;
/* 223:    */  }
/* 224:    */  
/* 235:    */  public int getTileId(int x, int y, int layerIndex)
/* 236:    */  {
/* 237:237 */    Layer layer = (Layer)this.layers.get(layerIndex);
/* 238:238 */    return layer.getTileID(x, y);
/* 239:    */  }
/* 240:    */  
/* 251:    */  public void setTileId(int x, int y, int layerIndex, int tileid)
/* 252:    */  {
/* 253:253 */    Layer layer = (Layer)this.layers.get(layerIndex);
/* 254:254 */    layer.setTileID(x, y, tileid);
/* 255:    */  }
/* 256:    */  
/* 265:    */  public String getMapProperty(String propertyName, String def)
/* 266:    */  {
/* 267:267 */    if (this.props == null)
/* 268:268 */      return def;
/* 269:269 */    return this.props.getProperty(propertyName, def);
/* 270:    */  }
/* 271:    */  
/* 281:    */  public String getLayerProperty(int layerIndex, String propertyName, String def)
/* 282:    */  {
/* 283:283 */    Layer layer = (Layer)this.layers.get(layerIndex);
/* 284:284 */    if ((layer == null) || (layer.props == null))
/* 285:285 */      return def;
/* 286:286 */    return layer.props.getProperty(propertyName, def);
/* 287:    */  }
/* 288:    */  
/* 299:    */  public String getTileProperty(int tileID, String propertyName, String def)
/* 300:    */  {
/* 301:301 */    if (tileID == 0) {
/* 302:302 */      return def;
/* 303:    */    }
/* 304:    */    
/* 305:305 */    TileSet set = findTileSet(tileID);
/* 306:    */    
/* 307:307 */    Properties props = set.getProperties(tileID);
/* 308:308 */    if (props == null) {
/* 309:309 */      return def;
/* 310:    */    }
/* 311:311 */    return props.getProperty(propertyName, def);
/* 312:    */  }
/* 313:    */  
/* 319:    */  public void render(int x, int y)
/* 320:    */  {
/* 321:321 */    render(x, y, 0, 0, this.width, this.height, false);
/* 322:    */  }
/* 323:    */  
/* 330:    */  public void render(int x, int y, int layer)
/* 331:    */  {
/* 332:332 */    render(x, y, 0, 0, getWidth(), getHeight(), layer, false);
/* 333:    */  }
/* 334:    */  
/* 344:    */  public void render(int x, int y, int sx, int sy, int width, int height)
/* 345:    */  {
/* 346:346 */    render(x, y, sx, sy, width, height, false);
/* 347:    */  }
/* 348:    */  
/* 361:    */  public void render(int x, int y, int sx, int sy, int width, int height, int l, boolean lineByLine)
/* 362:    */  {
/* 363:363 */    Layer layer = (Layer)this.layers.get(l);
/* 364:    */    
/* 365:365 */    switch (this.orientation) {
/* 366:    */    case 1: 
/* 367:367 */      for (int ty = 0; ty < height; ty++) {
/* 368:368 */        layer.render(x, y, sx, sy, width, ty, lineByLine, this.tileWidth, this.tileHeight);
/* 369:    */      }
/* 370:370 */      break;
/* 371:    */    case 2: 
/* 372:372 */      renderIsometricMap(x, y, sx, sy, width, height, layer, lineByLine);
/* 373:373 */      break;
/* 374:    */    }
/* 375:    */    
/* 376:    */  }
/* 377:    */  
/* 390:    */  public void render(int x, int y, int sx, int sy, int width, int height, boolean lineByLine)
/* 391:    */  {
/* 392:392 */    switch (this.orientation) {
/* 393:    */    case 1: 
/* 394:394 */      for (int ty = 0; ty < height; ty++) {
/* 395:395 */        for (int i = 0; i < this.layers.size(); i++) {
/* 396:396 */          Layer layer = (Layer)this.layers.get(i);
/* 397:397 */          layer.render(x, y, sx, sy, width, ty, lineByLine, this.tileWidth, this.tileHeight);
/* 398:    */        }
/* 399:    */      }
/* 400:400 */      break;
/* 401:    */    case 2: 
/* 402:402 */      renderIsometricMap(x, y, sx, sy, width, height, null, lineByLine);
/* 403:403 */      break;
/* 404:    */    }
/* 405:    */    
/* 406:    */  }
/* 407:    */  
/* 423:    */  protected void renderIsometricMap(int x, int y, int sx, int sy, int width, int height, Layer layer, boolean lineByLine)
/* 424:    */  {
/* 425:425 */    ArrayList drawLayers = this.layers;
/* 426:426 */    if (layer != null) {
/* 427:427 */      drawLayers = new ArrayList();
/* 428:428 */      drawLayers.add(layer);
/* 429:    */    }
/* 430:    */    
/* 431:431 */    int maxCount = width * height;
/* 432:432 */    int allCount = 0;
/* 433:    */    
/* 434:434 */    boolean allProcessed = false;
/* 435:    */    
/* 436:436 */    int initialLineX = x;
/* 437:437 */    int initialLineY = y;
/* 438:    */    
/* 439:439 */    int startLineTileX = 0;
/* 440:440 */    int startLineTileY = 0;
/* 441:441 */    while (!allProcessed)
/* 442:    */    {
/* 443:443 */      int currentTileX = startLineTileX;
/* 444:444 */      int currentTileY = startLineTileY;
/* 445:445 */      int currentLineX = initialLineX;
/* 446:    */      
/* 447:447 */      int min = 0;
/* 448:448 */      if (height > width) {
/* 449:449 */        min = width - currentTileX < height ? width - currentTileX - 1 : startLineTileY < width - 1 ? startLineTileY : width - 1;
/* 450:    */      } else {
/* 451:451 */        min = width - currentTileX < height ? width - currentTileX - 1 : startLineTileY < height - 1 ? startLineTileY : height - 1;
/* 452:    */      }
/* 453:453 */      for (int burner = 0; burner <= min; burner++) {
/* 454:454 */        for (int layerIdx = 0; layerIdx < drawLayers.size(); layerIdx++) {
/* 455:455 */          Layer currentLayer = (Layer)drawLayers.get(layerIdx);
/* 456:456 */          currentLayer.render(currentLineX, initialLineY, currentTileX, currentTileY, 1, 0, lineByLine, this.tileWidth, this.tileHeight);
/* 457:    */        }
/* 458:458 */        currentLineX += this.tileWidth;
/* 459:    */        
/* 460:460 */        allCount++;currentTileX++;currentTileY--;
/* 461:    */      }
/* 462:    */      
/* 474:467 */      if (startLineTileY < height - 1) {
/* 475:468 */        startLineTileY++;
/* 476:469 */        initialLineX -= this.tileWidth / 2;
/* 477:470 */        initialLineY += this.tileHeight / 2;
/* 478:    */      } else {
/* 479:472 */        startLineTileX++;
/* 480:473 */        initialLineX += this.tileWidth / 2;
/* 481:474 */        initialLineY += this.tileHeight / 2;
/* 482:    */      }
/* 483:    */      
/* 484:477 */      if (allCount >= maxCount) {
/* 485:478 */        allProcessed = true;
/* 486:    */      }
/* 487:    */    }
/* 488:    */  }
/* 489:    */  
/* 493:    */  public int getLayerCount()
/* 494:    */  {
/* 495:488 */    return this.layers.size();
/* 496:    */  }
/* 497:    */  
/* 502:    */  private int parseInt(String value)
/* 503:    */  {
/* 504:    */    try
/* 505:    */    {
/* 506:499 */      return Integer.parseInt(value);
/* 507:    */    } catch (NumberFormatException e) {}
/* 508:501 */    return 0;
/* 509:    */  }
/* 510:    */  
/* 517:    */  private void load(InputStream in, String tileSetsLocation)
/* 518:    */    throws SlickException
/* 519:    */  {
/* 520:513 */    this.tilesLocation = tileSetsLocation;
/* 521:    */    try
/* 522:    */    {
/* 523:516 */      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 524:517 */      factory.setValidating(false);
/* 525:518 */      DocumentBuilder builder = factory.newDocumentBuilder();
/* 526:519 */      builder.setEntityResolver(new EntityResolver()
/* 527:    */      {
/* 528:    */        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
/* 529:522 */          return new InputSource(new ByteArrayInputStream(new byte[0]));
/* 530:    */        }
/* 531:    */        
/* 532:525 */      });
/* 533:526 */      Document doc = builder.parse(in);
/* 534:527 */      Element docElement = doc.getDocumentElement();
/* 535:    */      
/* 536:529 */      if (docElement.getAttribute("orientation").equals("orthogonal")) {
/* 537:530 */        this.orientation = 1;
/* 538:    */      } else {
/* 539:532 */        this.orientation = 2;
/* 540:    */      }
/* 541:    */      
/* 545:538 */      this.width = parseInt(docElement.getAttribute("width"));
/* 546:539 */      this.height = parseInt(docElement.getAttribute("height"));
/* 547:540 */      this.tileWidth = parseInt(docElement.getAttribute("tilewidth"));
/* 548:541 */      this.tileHeight = parseInt(docElement.getAttribute("tileheight"));
/* 549:    */      
/* 551:544 */      Element propsElement = (Element)docElement.getElementsByTagName("properties").item(0);
/* 552:545 */      if (propsElement != null) {
/* 553:546 */        NodeList properties = propsElement.getElementsByTagName("property");
/* 554:547 */        if (properties != null) {
/* 555:548 */          this.props = new Properties();
/* 556:549 */          for (int p = 0; p < properties.getLength(); p++) {
/* 557:550 */            Element propElement = (Element)properties.item(p);
/* 558:    */            
/* 559:552 */            String name = propElement.getAttribute("name");
/* 560:553 */            String value = propElement.getAttribute("value");
/* 561:554 */            this.props.setProperty(name, value);
/* 562:    */          }
/* 563:    */        }
/* 564:    */      }
/* 565:    */      
/* 566:559 */      if (this.loadTileSets) {
/* 567:560 */        TileSet tileSet = null;
/* 568:561 */        TileSet lastSet = null;
/* 569:    */        
/* 570:563 */        NodeList setNodes = docElement.getElementsByTagName("tileset");
/* 571:564 */        for (int i = 0; i < setNodes.getLength(); i++) {
/* 572:565 */          Element current = (Element)setNodes.item(i);
/* 573:    */          
/* 574:567 */          tileSet = new TileSet(this, current, !headless);
/* 575:568 */          tileSet.index = i;
/* 576:    */          
/* 577:570 */          if (lastSet != null) {
/* 578:571 */            lastSet.setLimit(tileSet.firstGID - 1);
/* 579:    */          }
/* 580:573 */          lastSet = tileSet;
/* 581:    */          
/* 582:575 */          this.tileSets.add(tileSet);
/* 583:    */        }
/* 584:    */      }
/* 585:    */      
/* 586:579 */      NodeList layerNodes = docElement.getElementsByTagName("layer");
/* 587:580 */      for (int i = 0; i < layerNodes.getLength(); i++) {
/* 588:581 */        Element current = (Element)layerNodes.item(i);
/* 589:582 */        Layer layer = new Layer(this, current);
/* 590:583 */        layer.index = i;
/* 591:    */        
/* 592:585 */        this.layers.add(layer);
/* 593:    */      }
/* 594:    */      
/* 596:589 */      NodeList objectGroupNodes = docElement.getElementsByTagName("objectgroup");
/* 597:    */      
/* 598:591 */      for (int i = 0; i < objectGroupNodes.getLength(); i++) {
/* 599:592 */        Element current = (Element)objectGroupNodes.item(i);
/* 600:593 */        ObjectGroup objectGroup = new ObjectGroup(current);
/* 601:594 */        objectGroup.index = i;
/* 602:    */        
/* 603:596 */        this.objectGroups.add(objectGroup);
/* 604:    */      }
/* 605:    */    } catch (Exception e) {
/* 606:599 */      Log.error(e);
/* 607:600 */      throw new SlickException("Failed to parse tilemap", e);
/* 608:    */    }
/* 609:    */  }
/* 610:    */  
/* 615:    */  public int getTileSetCount()
/* 616:    */  {
/* 617:610 */    return this.tileSets.size();
/* 618:    */  }
/* 619:    */  
/* 625:    */  public TileSet getTileSet(int index)
/* 626:    */  {
/* 627:620 */    return (TileSet)this.tileSets.get(index);
/* 628:    */  }
/* 629:    */  
/* 635:    */  public TileSet getTileSetByGID(int gid)
/* 636:    */  {
/* 637:630 */    for (int i = 0; i < this.tileSets.size(); i++) {
/* 638:631 */      TileSet set = (TileSet)this.tileSets.get(i);
/* 639:    */      
/* 640:633 */      if (set.contains(gid)) {
/* 641:634 */        return set;
/* 642:    */      }
/* 643:    */    }
/* 644:    */    
/* 645:638 */    return null;
/* 646:    */  }
/* 647:    */  
/* 653:    */  public TileSet findTileSet(int gid)
/* 654:    */  {
/* 655:648 */    for (int i = 0; i < this.tileSets.size(); i++) {
/* 656:649 */      TileSet set = (TileSet)this.tileSets.get(i);
/* 657:    */      
/* 658:651 */      if (set.contains(gid)) {
/* 659:652 */        return set;
/* 660:    */      }
/* 661:    */    }
/* 662:    */    
/* 663:656 */    return null;
/* 664:    */  }
/* 665:    */  
/* 672:    */  protected void renderedLine(int visualY, int mapY, int layer) {}
/* 673:    */  
/* 680:    */  public int getObjectGroupCount()
/* 681:    */  {
/* 682:675 */    return this.objectGroups.size();
/* 683:    */  }
/* 684:    */  
/* 689:    */  public int getObjectCount(int groupID)
/* 690:    */  {
/* 691:684 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 692:685 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 693:686 */      return grp.objects.size();
/* 694:    */    }
/* 695:688 */    return -1;
/* 696:    */  }
/* 697:    */  
/* 703:    */  public String getObjectName(int groupID, int objectID)
/* 704:    */  {
/* 705:698 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 706:699 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 707:700 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 708:701 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 709:702 */        return object.name;
/* 710:    */      }
/* 711:    */    }
/* 712:705 */    return null;
/* 713:    */  }
/* 714:    */  
/* 720:    */  public String getObjectType(int groupID, int objectID)
/* 721:    */  {
/* 722:715 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 723:716 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 724:717 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 725:718 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 726:719 */        return object.type;
/* 727:    */      }
/* 728:    */    }
/* 729:722 */    return null;
/* 730:    */  }
/* 731:    */  
/* 737:    */  public int getObjectX(int groupID, int objectID)
/* 738:    */  {
/* 739:732 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 740:733 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 741:734 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 742:735 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 743:736 */        return object.x;
/* 744:    */      }
/* 745:    */    }
/* 746:739 */    return -1;
/* 747:    */  }
/* 748:    */  
/* 754:    */  public int getObjectY(int groupID, int objectID)
/* 755:    */  {
/* 756:749 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 757:750 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 758:751 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 759:752 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 760:753 */        return object.y;
/* 761:    */      }
/* 762:    */    }
/* 763:756 */    return -1;
/* 764:    */  }
/* 765:    */  
/* 771:    */  public int getObjectWidth(int groupID, int objectID)
/* 772:    */  {
/* 773:766 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 774:767 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 775:768 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 776:769 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 777:770 */        return object.width;
/* 778:    */      }
/* 779:    */    }
/* 780:773 */    return -1;
/* 781:    */  }
/* 782:    */  
/* 788:    */  public int getObjectHeight(int groupID, int objectID)
/* 789:    */  {
/* 790:783 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 791:784 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 792:785 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 793:786 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 794:787 */        return object.height;
/* 795:    */      }
/* 796:    */    }
/* 797:790 */    return -1;
/* 798:    */  }
/* 799:    */  
/* 806:    */  public String getObjectImage(int groupID, int objectID)
/* 807:    */  {
/* 808:801 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 809:802 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 810:803 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 811:804 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 812:    */        
/* 813:806 */        if (object == null) {
/* 814:807 */          return null;
/* 815:    */        }
/* 816:    */        
/* 817:810 */        return object.image;
/* 818:    */      }
/* 819:    */    }
/* 820:    */    
/* 821:814 */    return null;
/* 822:    */  }
/* 823:    */  
/* 833:    */  public String getObjectProperty(int groupID, int objectID, String propertyName, String def)
/* 834:    */  {
/* 835:828 */    if ((groupID >= 0) && (groupID < this.objectGroups.size())) {
/* 836:829 */      ObjectGroup grp = (ObjectGroup)this.objectGroups.get(groupID);
/* 837:830 */      if ((objectID >= 0) && (objectID < grp.objects.size())) {
/* 838:831 */        GroupObject object = (GroupObject)grp.objects.get(objectID);
/* 839:    */        
/* 840:833 */        if (object == null) {
/* 841:834 */          return def;
/* 842:    */        }
/* 843:836 */        if (object.props == null) {
/* 844:837 */          return def;
/* 845:    */        }
/* 846:    */        
/* 847:840 */        return object.props.getProperty(propertyName, def);
/* 848:    */      }
/* 849:    */    }
/* 850:843 */    return def;
/* 851:    */  }
/* 852:    */  
/* 856:    */  protected class ObjectGroup
/* 857:    */  {
/* 858:    */    public int index;
/* 859:    */    
/* 861:    */    public String name;
/* 862:    */    
/* 864:    */    public ArrayList objects;
/* 865:    */    
/* 867:    */    public int width;
/* 868:    */    
/* 870:    */    public int height;
/* 871:    */    
/* 873:    */    public Properties props;
/* 874:    */    
/* 877:    */    public ObjectGroup(Element element)
/* 878:    */      throws SlickException
/* 879:    */    {
/* 880:873 */      this.name = element.getAttribute("name");
/* 881:874 */      this.width = Integer.parseInt(element.getAttribute("width"));
/* 882:875 */      this.height = Integer.parseInt(element.getAttribute("height"));
/* 883:876 */      this.objects = new ArrayList();
/* 884:    */      
/* 886:879 */      Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
/* 887:    */      
/* 888:881 */      if (propsElement != null) {
/* 889:882 */        NodeList properties = propsElement.getElementsByTagName("property");
/* 890:    */        
/* 891:884 */        if (properties != null) {
/* 892:885 */          this.props = new Properties();
/* 893:886 */          for (int p = 0; p < properties.getLength(); p++) {
/* 894:887 */            Element propElement = (Element)properties.item(p);
/* 895:    */            
/* 896:889 */            String name = propElement.getAttribute("name");
/* 897:890 */            String value = propElement.getAttribute("value");
/* 898:891 */            this.props.setProperty(name, value);
/* 899:    */          }
/* 900:    */        }
/* 901:    */      }
/* 902:    */      
/* 903:896 */      NodeList objectNodes = element.getElementsByTagName("object");
/* 904:897 */      for (int i = 0; i < objectNodes.getLength(); i++) {
/* 905:898 */        Element objElement = (Element)objectNodes.item(i);
/* 906:899 */        TiledMap.GroupObject object = new TiledMap.GroupObject(TiledMap.this, objElement);
/* 907:900 */        object.index = i;
/* 908:901 */        this.objects.add(object);
/* 909:    */      }
/* 910:    */    }
/* 911:    */  }
/* 912:    */  
/* 915:    */  protected class GroupObject
/* 916:    */  {
/* 917:    */    public int index;
/* 918:    */    
/* 920:    */    public String name;
/* 921:    */    
/* 923:    */    public String type;
/* 924:    */    
/* 926:    */    public int x;
/* 927:    */    
/* 929:    */    public int y;
/* 930:    */    
/* 932:    */    public int width;
/* 933:    */    
/* 935:    */    public int height;
/* 936:    */    
/* 938:    */    private String image;
/* 939:    */    
/* 940:    */    public Properties props;
/* 941:    */    
/* 943:    */    public GroupObject(Element element)
/* 944:    */      throws SlickException
/* 945:    */    {
/* 946:939 */      this.name = element.getAttribute("name");
/* 947:940 */      this.type = element.getAttribute("type");
/* 948:941 */      this.x = Integer.parseInt(element.getAttribute("x"));
/* 949:942 */      this.y = Integer.parseInt(element.getAttribute("y"));
/* 950:943 */      this.width = Integer.parseInt(element.getAttribute("width"));
/* 951:944 */      this.height = Integer.parseInt(element.getAttribute("height"));
/* 952:    */      
/* 953:946 */      Element imageElement = (Element)element.getElementsByTagName("image").item(0);
/* 954:    */      
/* 955:948 */      if (imageElement != null) {
/* 956:949 */        this.image = imageElement.getAttribute("source");
/* 957:    */      }
/* 958:    */      
/* 960:953 */      Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
/* 961:    */      
/* 962:955 */      if (propsElement != null) {
/* 963:956 */        NodeList properties = propsElement.getElementsByTagName("property");
/* 964:    */        
/* 965:958 */        if (properties != null) {
/* 966:959 */          this.props = new Properties();
/* 967:960 */          for (int p = 0; p < properties.getLength(); p++) {
/* 968:961 */            Element propElement = (Element)properties.item(p);
/* 969:    */            
/* 970:963 */            String name = propElement.getAttribute("name");
/* 971:964 */            String value = propElement.getAttribute("value");
/* 972:965 */            this.props.setProperty(name, value);
/* 973:    */          }
/* 974:    */        }
/* 975:    */      }
/* 976:    */    }
/* 977:    */  }
/* 978:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tiled.TiledMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */