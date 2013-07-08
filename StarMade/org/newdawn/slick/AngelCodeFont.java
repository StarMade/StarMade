/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.BufferedReader;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.io.InputStreamReader;
/*   7:    */import java.util.ArrayList;
/*   8:    */import java.util.HashMap;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.LinkedHashMap;
/*  11:    */import java.util.List;
/*  12:    */import java.util.Map;
/*  13:    */import java.util.Map.Entry;
/*  14:    */import java.util.Set;
/*  15:    */import java.util.StringTokenizer;
/*  16:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  17:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  18:    */import org.newdawn.slick.util.Log;
/*  19:    */import org.newdawn.slick.util.ResourceLoader;
/*  20:    */
/*  34:    */public class AngelCodeFont
/*  35:    */  implements Font
/*  36:    */{
/*  37: 37 */  private static SGL GL = ;
/*  38:    */  
/*  41:    */  private static final int DISPLAY_LIST_CACHE_SIZE = 200;
/*  42:    */  
/*  45:    */  private static final int MAX_CHAR = 255;
/*  46:    */  
/*  49: 49 */  private boolean displayListCaching = true;
/*  50:    */  
/*  52:    */  private Image fontImage;
/*  53:    */  
/*  54:    */  private CharDef[] chars;
/*  55:    */  
/*  56:    */  private int lineHeight;
/*  57:    */  
/*  58: 58 */  private int baseDisplayListID = -1;
/*  59:    */  
/*  61:    */  private int eldestDisplayListID;
/*  62:    */  
/*  63:    */  private DisplayList eldestDisplayList;
/*  64:    */  
/*  65: 65 */  private final LinkedHashMap displayLists = new LinkedHashMap(200, 1.0F, true) {
/*  66:    */    protected boolean removeEldestEntry(Map.Entry eldest) {
/*  67: 67 */      AngelCodeFont.this.eldestDisplayList = ((AngelCodeFont.DisplayList)eldest.getValue());
/*  68: 68 */      AngelCodeFont.this.eldestDisplayListID = AngelCodeFont.this.eldestDisplayList.id;
/*  69:    */      
/*  70: 70 */      return false;
/*  71:    */    }
/*  72:    */  };
/*  73:    */  
/*  84:    */  public AngelCodeFont(String fntFile, Image image)
/*  85:    */    throws SlickException
/*  86:    */  {
/*  87: 87 */    this.fontImage = image;
/*  88:    */    
/*  89: 89 */    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
/*  90:    */  }
/*  91:    */  
/* 101:    */  public AngelCodeFont(String fntFile, String imgFile)
/* 102:    */    throws SlickException
/* 103:    */  {
/* 104:104 */    this.fontImage = new Image(imgFile);
/* 105:    */    
/* 106:106 */    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
/* 107:    */  }
/* 108:    */  
/* 121:    */  public AngelCodeFont(String fntFile, Image image, boolean caching)
/* 122:    */    throws SlickException
/* 123:    */  {
/* 124:124 */    this.fontImage = image;
/* 125:125 */    this.displayListCaching = caching;
/* 126:126 */    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
/* 127:    */  }
/* 128:    */  
/* 141:    */  public AngelCodeFont(String fntFile, String imgFile, boolean caching)
/* 142:    */    throws SlickException
/* 143:    */  {
/* 144:144 */    this.fontImage = new Image(imgFile);
/* 145:145 */    this.displayListCaching = caching;
/* 146:146 */    parseFnt(ResourceLoader.getResourceAsStream(fntFile));
/* 147:    */  }
/* 148:    */  
/* 161:    */  public AngelCodeFont(String name, InputStream fntFile, InputStream imgFile)
/* 162:    */    throws SlickException
/* 163:    */  {
/* 164:164 */    this.fontImage = new Image(imgFile, name, false);
/* 165:    */    
/* 166:166 */    parseFnt(fntFile);
/* 167:    */  }
/* 168:    */  
/* 183:    */  public AngelCodeFont(String name, InputStream fntFile, InputStream imgFile, boolean caching)
/* 184:    */    throws SlickException
/* 185:    */  {
/* 186:186 */    this.fontImage = new Image(imgFile, name, false);
/* 187:    */    
/* 188:188 */    this.displayListCaching = caching;
/* 189:189 */    parseFnt(fntFile);
/* 190:    */  }
/* 191:    */  
/* 197:    */  private void parseFnt(InputStream fntFile)
/* 198:    */    throws SlickException
/* 199:    */  {
/* 200:200 */    if (this.displayListCaching) {
/* 201:201 */      this.baseDisplayListID = GL.glGenLists(200);
/* 202:202 */      if (this.baseDisplayListID == 0) { this.displayListCaching = false;
/* 203:    */      }
/* 204:    */    }
/* 205:    */    try
/* 206:    */    {
/* 207:207 */      BufferedReader in = new BufferedReader(new InputStreamReader(fntFile));
/* 208:    */      
/* 209:209 */      String info = in.readLine();
/* 210:210 */      String common = in.readLine();
/* 211:211 */      String page = in.readLine();
/* 212:    */      
/* 213:213 */      Map kerning = new HashMap(64);
/* 214:214 */      List charDefs = new ArrayList(255);
/* 215:215 */      int maxChar = 0;
/* 216:216 */      boolean done = false;
/* 217:217 */      while (!done) {
/* 218:218 */        String line = in.readLine();
/* 219:219 */        if (line == null) {
/* 220:220 */          done = true;
/* 221:    */        } else {
/* 222:222 */          if (!line.startsWith("chars c"))
/* 223:    */          {
/* 224:224 */            if (line.startsWith("char")) {
/* 225:225 */              CharDef def = parseChar(line);
/* 226:226 */              if (def != null) {
/* 227:227 */                maxChar = Math.max(maxChar, def.id);
/* 228:228 */                charDefs.add(def);
/* 229:    */              }
/* 230:    */            } }
/* 231:231 */          if (!line.startsWith("kernings c"))
/* 232:    */          {
/* 233:233 */            if (line.startsWith("kerning")) {
/* 234:234 */              StringTokenizer tokens = new StringTokenizer(line, " =");
/* 235:235 */              tokens.nextToken();
/* 236:236 */              tokens.nextToken();
/* 237:237 */              short first = Short.parseShort(tokens.nextToken());
/* 238:238 */              tokens.nextToken();
/* 239:239 */              int second = Integer.parseInt(tokens.nextToken());
/* 240:240 */              tokens.nextToken();
/* 241:241 */              int offset = Integer.parseInt(tokens.nextToken());
/* 242:242 */              List values = (List)kerning.get(new Short(first));
/* 243:243 */              if (values == null) {
/* 244:244 */                values = new ArrayList();
/* 245:245 */                kerning.put(new Short(first), values);
/* 246:    */              }
/* 247:    */              
/* 248:248 */              values.add(new Short((short)(offset << 8 | second)));
/* 249:    */            }
/* 250:    */          }
/* 251:    */        }
/* 252:    */      }
/* 253:253 */      this.chars = new CharDef[maxChar + 1];
/* 254:254 */      for (Iterator iter = charDefs.iterator(); iter.hasNext();) {
/* 255:255 */        CharDef def = (CharDef)iter.next();
/* 256:256 */        this.chars[def.id] = def;
/* 257:    */      }
/* 258:    */      
/* 260:260 */      for (iter = kerning.entrySet().iterator(); iter.hasNext();) {
/* 261:261 */        Map.Entry entry = (Map.Entry)iter.next();
/* 262:262 */        short first = ((Short)entry.getKey()).shortValue();
/* 263:263 */        List valueList = (List)entry.getValue();
/* 264:264 */        short[] valueArray = new short[valueList.size()];
/* 265:265 */        int i = 0;
/* 266:266 */        for (Iterator valueIter = valueList.iterator(); valueIter.hasNext(); i++)
/* 267:267 */          valueArray[i] = ((Short)valueIter.next()).shortValue();
/* 268:268 */        this.chars[first].kerning = valueArray;
/* 269:    */      }
/* 270:    */    } catch (IOException e) { Iterator iter;
/* 271:271 */      Log.error(e);
/* 272:272 */      throw new SlickException("Failed to parse font file: " + fntFile);
/* 273:    */    }
/* 274:    */  }
/* 275:    */  
/* 282:    */  private CharDef parseChar(String line)
/* 283:    */    throws SlickException
/* 284:    */  {
/* 285:285 */    CharDef def = new CharDef(null);
/* 286:286 */    StringTokenizer tokens = new StringTokenizer(line, " =");
/* 287:    */    
/* 288:288 */    tokens.nextToken();
/* 289:289 */    tokens.nextToken();
/* 290:290 */    def.id = Short.parseShort(tokens.nextToken());
/* 291:291 */    if (def.id < 0) {
/* 292:292 */      return null;
/* 293:    */    }
/* 294:294 */    if (def.id > 255) {
/* 295:295 */      throw new SlickException("Invalid character '" + def.id + "': AngelCodeFont does not support characters above " + 255);
/* 296:    */    }
/* 297:    */    
/* 299:299 */    tokens.nextToken();
/* 300:300 */    def.x = Short.parseShort(tokens.nextToken());
/* 301:301 */    tokens.nextToken();
/* 302:302 */    def.y = Short.parseShort(tokens.nextToken());
/* 303:303 */    tokens.nextToken();
/* 304:304 */    def.width = Short.parseShort(tokens.nextToken());
/* 305:305 */    tokens.nextToken();
/* 306:306 */    def.height = Short.parseShort(tokens.nextToken());
/* 307:307 */    tokens.nextToken();
/* 308:308 */    def.xoffset = Short.parseShort(tokens.nextToken());
/* 309:309 */    tokens.nextToken();
/* 310:310 */    def.yoffset = Short.parseShort(tokens.nextToken());
/* 311:311 */    tokens.nextToken();
/* 312:312 */    def.xadvance = Short.parseShort(tokens.nextToken());
/* 313:    */    
/* 314:314 */    def.init();
/* 315:    */    
/* 316:316 */    if (def.id != 32) {
/* 317:317 */      this.lineHeight = Math.max(def.height + def.yoffset, this.lineHeight);
/* 318:    */    }
/* 319:    */    
/* 320:320 */    return def;
/* 321:    */  }
/* 322:    */  
/* 325:    */  public void drawString(float x, float y, String text)
/* 326:    */  {
/* 327:327 */    drawString(x, y, text, Color.white);
/* 328:    */  }
/* 329:    */  
/* 333:    */  public void drawString(float x, float y, String text, Color col)
/* 334:    */  {
/* 335:335 */    drawString(x, y, text, col, 0, text.length() - 1);
/* 336:    */  }
/* 337:    */  
/* 341:    */  public void drawString(float x, float y, String text, Color col, int startIndex, int endIndex)
/* 342:    */  {
/* 343:343 */    this.fontImage.bind();
/* 344:344 */    col.bind();
/* 345:    */    
/* 346:346 */    GL.glTranslatef(x, y, 0.0F);
/* 347:347 */    if ((this.displayListCaching) && (startIndex == 0) && (endIndex == text.length() - 1)) {
/* 348:348 */      DisplayList displayList = (DisplayList)this.displayLists.get(text);
/* 349:349 */      if (displayList != null) {
/* 350:350 */        GL.glCallList(displayList.id);
/* 351:    */      }
/* 352:    */      else {
/* 353:353 */        displayList = new DisplayList(null);
/* 354:354 */        displayList.text = text;
/* 355:355 */        int displayListCount = this.displayLists.size();
/* 356:356 */        if (displayListCount < 200) {
/* 357:357 */          displayList.id = (this.baseDisplayListID + displayListCount);
/* 358:    */        } else {
/* 359:359 */          displayList.id = this.eldestDisplayListID;
/* 360:360 */          this.displayLists.remove(this.eldestDisplayList.text);
/* 361:    */        }
/* 362:    */        
/* 363:363 */        this.displayLists.put(text, displayList);
/* 364:    */        
/* 365:365 */        GL.glNewList(displayList.id, 4865);
/* 366:366 */        render(text, startIndex, endIndex);
/* 367:367 */        GL.glEndList();
/* 368:    */      }
/* 369:    */    } else {
/* 370:370 */      render(text, startIndex, endIndex);
/* 371:    */    }
/* 372:372 */    GL.glTranslatef(-x, -y, 0.0F);
/* 373:    */  }
/* 374:    */  
/* 381:    */  private void render(String text, int start, int end)
/* 382:    */  {
/* 383:383 */    GL.glBegin(7);
/* 384:    */    
/* 385:385 */    int x = 0;int y = 0;
/* 386:386 */    CharDef lastCharDef = null;
/* 387:387 */    char[] data = text.toCharArray();
/* 388:388 */    for (int i = 0; i < data.length; i++) {
/* 389:389 */      int id = data[i];
/* 390:390 */      if (id == 10) {
/* 391:391 */        x = 0;
/* 392:392 */        y += getLineHeight();
/* 394:    */      }
/* 395:395 */      else if (id < this.chars.length)
/* 396:    */      {
/* 398:398 */        CharDef charDef = this.chars[id];
/* 399:399 */        if (charDef != null)
/* 400:    */        {
/* 403:403 */          if (lastCharDef != null) x += lastCharDef.getKerning(id);
/* 404:404 */          lastCharDef = charDef;
/* 405:    */          
/* 406:406 */          if ((i >= start) && (i <= end)) {
/* 407:407 */            charDef.draw(x, y);
/* 408:    */          }
/* 409:    */          
/* 410:410 */          x += charDef.xadvance;
/* 411:    */        } } }
/* 412:412 */    GL.glEnd();
/* 413:    */  }
/* 414:    */  
/* 421:    */  public int getYOffset(String text)
/* 422:    */  {
/* 423:423 */    DisplayList displayList = null;
/* 424:424 */    if (this.displayListCaching) {
/* 425:425 */      displayList = (DisplayList)this.displayLists.get(text);
/* 426:426 */      if ((displayList != null) && (displayList.yOffset != null)) { return displayList.yOffset.intValue();
/* 427:    */      }
/* 428:    */    }
/* 429:429 */    int stopIndex = text.indexOf('\n');
/* 430:430 */    if (stopIndex == -1) { stopIndex = text.length();
/* 431:    */    }
/* 432:432 */    int minYOffset = 10000;
/* 433:433 */    for (int i = 0; i < stopIndex; i++) {
/* 434:434 */      int id = text.charAt(i);
/* 435:435 */      CharDef charDef = this.chars[id];
/* 436:436 */      if (charDef != null)
/* 437:    */      {
/* 439:439 */        minYOffset = Math.min(charDef.yoffset, minYOffset);
/* 440:    */      }
/* 441:    */    }
/* 442:442 */    if (displayList != null) { displayList.yOffset = new Short((short)minYOffset);
/* 443:    */    }
/* 444:444 */    return minYOffset;
/* 445:    */  }
/* 446:    */  
/* 449:    */  public int getHeight(String text)
/* 450:    */  {
/* 451:451 */    DisplayList displayList = null;
/* 452:452 */    if (this.displayListCaching) {
/* 453:453 */      displayList = (DisplayList)this.displayLists.get(text);
/* 454:454 */      if ((displayList != null) && (displayList.height != null)) { return displayList.height.intValue();
/* 455:    */      }
/* 456:    */    }
/* 457:457 */    int lines = 0;
/* 458:458 */    int maxHeight = 0;
/* 459:459 */    for (int i = 0; i < text.length(); i++) {
/* 460:460 */      int id = text.charAt(i);
/* 461:461 */      if (id == 10) {
/* 462:462 */        lines++;
/* 463:463 */        maxHeight = 0;
/* 466:    */      }
/* 467:467 */      else if (id != 32)
/* 468:    */      {
/* 470:470 */        CharDef charDef = this.chars[id];
/* 471:471 */        if (charDef != null)
/* 472:    */        {
/* 475:475 */          maxHeight = Math.max(charDef.height + charDef.yoffset, maxHeight);
/* 476:    */        }
/* 477:    */      }
/* 478:    */    }
/* 479:479 */    maxHeight += lines * getLineHeight();
/* 480:    */    
/* 481:481 */    if (displayList != null) { displayList.height = new Short((short)maxHeight);
/* 482:    */    }
/* 483:483 */    return maxHeight;
/* 484:    */  }
/* 485:    */  
/* 488:    */  public int getWidth(String text)
/* 489:    */  {
/* 490:490 */    DisplayList displayList = null;
/* 491:491 */    if (this.displayListCaching) {
/* 492:492 */      displayList = (DisplayList)this.displayLists.get(text);
/* 493:493 */      if ((displayList != null) && (displayList.width != null)) { return displayList.width.intValue();
/* 494:    */      }
/* 495:    */    }
/* 496:496 */    int maxWidth = 0;
/* 497:497 */    int width = 0;
/* 498:498 */    CharDef lastCharDef = null;
/* 499:499 */    int i = 0; for (int n = text.length(); i < n; i++) {
/* 500:500 */      int id = text.charAt(i);
/* 501:501 */      if (id == 10) {
/* 502:502 */        width = 0;
/* 504:    */      }
/* 505:505 */      else if (id < this.chars.length)
/* 506:    */      {
/* 508:508 */        CharDef charDef = this.chars[id];
/* 509:509 */        if (charDef != null)
/* 510:    */        {
/* 513:513 */          if (lastCharDef != null) width += lastCharDef.getKerning(id);
/* 514:514 */          lastCharDef = charDef;
/* 515:    */          
/* 516:516 */          if (i < n - 1) {
/* 517:517 */            width += charDef.xadvance;
/* 518:    */          } else {
/* 519:519 */            width += charDef.width;
/* 520:    */          }
/* 521:521 */          maxWidth = Math.max(maxWidth, width);
/* 522:    */        }
/* 523:    */      } }
/* 524:524 */    if (displayList != null) { displayList.width = new Short((short)maxWidth);
/* 525:    */    }
/* 526:526 */    return maxWidth;
/* 527:    */  }
/* 528:    */  
/* 530:    */  private static class DisplayList
/* 531:    */  {
/* 532:    */    int id;
/* 533:    */    
/* 534:    */    Short yOffset;
/* 535:    */    
/* 536:    */    Short width;
/* 537:    */    
/* 538:    */    Short height;
/* 539:    */    
/* 540:    */    String text;
/* 541:    */  }
/* 542:    */  
/* 544:    */  private class CharDef
/* 545:    */  {
/* 546:    */    public short id;
/* 547:    */    
/* 548:    */    public short x;
/* 549:    */    
/* 550:    */    public short y;
/* 551:    */    
/* 552:    */    public short width;
/* 553:    */    public short height;
/* 554:    */    public short xoffset;
/* 555:    */    public short yoffset;
/* 556:    */    public short xadvance;
/* 557:    */    public Image image;
/* 558:    */    public short dlIndex;
/* 559:    */    public short[] kerning;
/* 560:    */    
/* 561:    */    private CharDef() {}
/* 562:    */    
/* 563:    */    public void init()
/* 564:    */    {
/* 565:565 */      this.image = AngelCodeFont.this.fontImage.getSubImage(this.x, this.y, this.width, this.height);
/* 566:    */    }
/* 567:    */    
/* 570:    */    public String toString()
/* 571:    */    {
/* 572:572 */      return "[CharDef id=" + this.id + " x=" + this.x + " y=" + this.y + "]";
/* 573:    */    }
/* 574:    */    
/* 582:    */    public void draw(float x, float y)
/* 583:    */    {
/* 584:584 */      this.image.drawEmbedded(x + this.xoffset, y + this.yoffset, this.width, this.height);
/* 585:    */    }
/* 586:    */    
/* 591:    */    public int getKerning(int otherCodePoint)
/* 592:    */    {
/* 593:593 */      if (this.kerning == null) return 0;
/* 594:594 */      int low = 0;
/* 595:595 */      int high = this.kerning.length - 1;
/* 596:596 */      while (low <= high) {
/* 597:597 */        int midIndex = low + high >>> 1;
/* 598:598 */        int value = this.kerning[midIndex];
/* 599:599 */        int foundCodePoint = value & 0xFF;
/* 600:600 */        if (foundCodePoint < otherCodePoint) {
/* 601:601 */          low = midIndex + 1;
/* 602:602 */        } else if (foundCodePoint > otherCodePoint) {
/* 603:603 */          high = midIndex - 1;
/* 604:    */        } else
/* 605:605 */          return value >> 8;
/* 606:    */      }
/* 607:607 */      return 0;
/* 608:    */    }
/* 609:    */  }
/* 610:    */  
/* 613:    */  public int getLineHeight()
/* 614:    */  {
/* 615:615 */    return this.lineHeight;
/* 616:    */  }
/* 617:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.AngelCodeFont
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */