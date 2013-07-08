/*   1:    */package org.newdawn.slick.opengl;
/*   2:    */
/*   3:    */import java.io.EOFException;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */import java.util.Arrays;
/*   8:    */import java.util.zip.CRC32;
/*   9:    */import java.util.zip.DataFormatException;
/*  10:    */import java.util.zip.Inflater;
/*  11:    */
/*  45:    */public class PNGDecoder
/*  46:    */{
/*  47: 47 */  public static Format ALPHA = new Format(1, true, null);
/*  48: 48 */  public static Format LUMINANCE = new Format(1, false, null);
/*  49: 49 */  public static Format LUMINANCE_ALPHA = new Format(2, true, null);
/*  50: 50 */  public static Format RGB = new Format(3, false, null);
/*  51: 51 */  public static Format RGBA = new Format(4, true, null);
/*  52: 52 */  public static Format BGRA = new Format(4, true, null);
/*  53: 53 */  public static Format ABGR = new Format(4, true, null);
/*  54:    */  
/*  55:    */  public static class Format
/*  56:    */  {
/*  57:    */    final int numComponents;
/*  58:    */    final boolean hasAlpha;
/*  59:    */    
/*  60:    */    private Format(int numComponents, boolean hasAlpha) {
/*  61: 61 */      this.numComponents = numComponents;
/*  62: 62 */      this.hasAlpha = hasAlpha;
/*  63:    */    }
/*  64:    */    
/*  65:    */    public int getNumComponents() {
/*  66: 66 */      return this.numComponents;
/*  67:    */    }
/*  68:    */    
/*  69:    */    public boolean isHasAlpha() {
/*  70: 70 */      return this.hasAlpha;
/*  71:    */    }
/*  72:    */  }
/*  73:    */  
/*  74: 74 */  private static final byte[] SIGNATURE = { -119, 80, 78, 71, 13, 10, 26, 10 };
/*  75:    */  
/*  76:    */  private static final int IHDR = 1229472850;
/*  77:    */  
/*  78:    */  private static final int PLTE = 1347179589;
/*  79:    */  
/*  80:    */  private static final int tRNS = 1951551059;
/*  81:    */  private static final int IDAT = 1229209940;
/*  82:    */  private static final int IEND = 1229278788;
/*  83:    */  private static final byte COLOR_GREYSCALE = 0;
/*  84:    */  private static final byte COLOR_TRUECOLOR = 2;
/*  85:    */  private static final byte COLOR_INDEXED = 3;
/*  86:    */  private static final byte COLOR_GREYALPHA = 4;
/*  87:    */  private static final byte COLOR_TRUEALPHA = 6;
/*  88:    */  private final InputStream input;
/*  89:    */  private final CRC32 crc;
/*  90:    */  private final byte[] buffer;
/*  91:    */  private int chunkLength;
/*  92:    */  private int chunkType;
/*  93:    */  private int chunkRemaining;
/*  94:    */  private int width;
/*  95:    */  private int height;
/*  96:    */  private int bitdepth;
/*  97:    */  private int colorType;
/*  98:    */  private int bytesPerPixel;
/*  99:    */  private byte[] palette;
/* 100:    */  private byte[] paletteA;
/* 101:    */  private byte[] transPixel;
/* 102:    */  
/* 103:    */  public PNGDecoder(InputStream input)
/* 104:    */    throws IOException
/* 105:    */  {
/* 106:106 */    this.input = input;
/* 107:107 */    this.crc = new CRC32();
/* 108:108 */    this.buffer = new byte[4096];
/* 109:    */    
/* 110:110 */    readFully(this.buffer, 0, SIGNATURE.length);
/* 111:111 */    if (!checkSignature(this.buffer)) {
/* 112:112 */      throw new IOException("Not a valid PNG file");
/* 113:    */    }
/* 114:    */    
/* 115:115 */    openChunk(1229472850);
/* 116:116 */    readIHDR();
/* 117:117 */    closeChunk();
/* 118:    */    for (;;)
/* 119:    */    {
/* 120:120 */      openChunk();
/* 121:121 */      switch (this.chunkType) {
/* 122:    */      case 1229209940: 
/* 123:123 */        break;
/* 124:    */      case 1347179589: 
/* 125:125 */        readPLTE();
/* 126:126 */        break;
/* 127:    */      case 1951551059: 
/* 128:128 */        readtRNS();
/* 129:    */      }
/* 130:    */      
/* 131:131 */      closeChunk();
/* 132:    */    }
/* 133:    */    
/* 134:134 */    if ((this.colorType == 3) && (this.palette == null)) {
/* 135:135 */      throw new IOException("Missing PLTE chunk");
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 139:    */  public int getHeight() {
/* 140:140 */    return this.height;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public int getWidth() {
/* 144:144 */    return this.width;
/* 145:    */  }
/* 146:    */  
/* 147:    */  public boolean hasAlpha() {
/* 148:148 */    return (this.colorType == 6) || (this.paletteA != null) || (this.transPixel != null);
/* 149:    */  }
/* 150:    */  
/* 151:    */  public boolean isRGB()
/* 152:    */  {
/* 153:153 */    return (this.colorType == 6) || (this.colorType == 2) || (this.colorType == 3);
/* 154:    */  }
/* 155:    */  
/* 164:    */  public Format decideTextureFormat(Format fmt)
/* 165:    */  {
/* 166:166 */    switch (this.colorType) {
/* 167:    */    case 2: 
/* 168:168 */      if ((fmt == ABGR) || (fmt == RGBA) || (fmt == BGRA) || (fmt == RGB)) {
/* 169:169 */        return fmt;
/* 170:    */      }
/* 171:    */      
/* 172:172 */      return RGB;
/* 173:    */    case 6: 
/* 174:174 */      if ((fmt == ABGR) || (fmt == RGBA) || (fmt == BGRA) || (fmt == RGB)) {
/* 175:175 */        return fmt;
/* 176:    */      }
/* 177:    */      
/* 178:178 */      return RGBA;
/* 179:    */    case 0: 
/* 180:180 */      if ((fmt == LUMINANCE) || (fmt == ALPHA)) {
/* 181:181 */        return fmt;
/* 182:    */      }
/* 183:    */      
/* 184:184 */      return LUMINANCE;
/* 185:    */    case 4: 
/* 186:186 */      return LUMINANCE_ALPHA;
/* 187:    */    case 3: 
/* 188:188 */      if ((fmt == ABGR) || (fmt == RGBA) || (fmt == BGRA)) {
/* 189:189 */        return fmt;
/* 190:    */      }
/* 191:    */      
/* 192:192 */      return RGBA;
/* 193:    */    }
/* 194:194 */    throw new UnsupportedOperationException("Not yet implemented");
/* 195:    */  }
/* 196:    */  
/* 197:    */  public void decode(ByteBuffer buffer, int stride, Format fmt) throws IOException
/* 198:    */  {
/* 199:199 */    int offset = buffer.position();
/* 200:200 */    int lineSize = (this.width * this.bitdepth + 7) / 8 * this.bytesPerPixel;
/* 201:201 */    byte[] curLine = new byte[lineSize + 1];
/* 202:202 */    byte[] prevLine = new byte[lineSize + 1];
/* 203:203 */    byte[] palLine = this.bitdepth < 8 ? new byte[this.width + 1] : null;
/* 204:    */    
/* 205:205 */    Inflater inflater = new Inflater();
/* 206:    */    try {
/* 207:207 */      for (int y = 0; y < this.height; y++) {
/* 208:208 */        readChunkUnzip(inflater, curLine, 0, curLine.length);
/* 209:209 */        unfilter(curLine, prevLine);
/* 210:    */        
/* 211:211 */        buffer.position(offset + y * stride);
/* 212:    */        
/* 213:213 */        switch (this.colorType) {
/* 214:    */        case 2: 
/* 215:215 */          if (fmt == ABGR) {
/* 216:216 */            copyRGBtoABGR(buffer, curLine);
/* 217:    */          }
/* 218:218 */          else if (fmt == RGBA) {
/* 219:219 */            copyRGBtoRGBA(buffer, curLine);
/* 220:    */          }
/* 221:221 */          else if (fmt == BGRA) {
/* 222:222 */            copyRGBtoBGRA(buffer, curLine);
/* 223:    */          }
/* 224:224 */          else if (fmt == RGB) {
/* 225:225 */            copy(buffer, curLine);
/* 226:    */          } else {
/* 227:227 */            throw new UnsupportedOperationException("Unsupported format for this image");
/* 228:    */          }
/* 229:    */          break;
/* 230:    */        case 6: 
/* 231:231 */          if (fmt == ABGR) {
/* 232:232 */            copyRGBAtoABGR(buffer, curLine);
/* 233:233 */          } else if (fmt == RGBA) {
/* 234:234 */            copy(buffer, curLine);
/* 235:235 */          } else if (fmt == BGRA) {
/* 236:236 */            copyRGBAtoBGRA(buffer, curLine);
/* 237:237 */          } else if (fmt == RGB) {
/* 238:238 */            copyRGBAtoRGB(buffer, curLine);
/* 239:    */          } else {
/* 240:240 */            throw new UnsupportedOperationException("Unsupported format for this image");
/* 241:    */          }
/* 242:    */          break;
/* 243:    */        case 0: 
/* 244:244 */          if ((fmt == LUMINANCE) || (fmt == ALPHA)) {
/* 245:245 */            copy(buffer, curLine);
/* 246:    */          } else {
/* 247:247 */            throw new UnsupportedOperationException("Unsupported format for this image");
/* 248:    */          }
/* 249:    */          break;
/* 250:    */        case 4: 
/* 251:251 */          if (fmt == LUMINANCE_ALPHA) {
/* 252:252 */            copy(buffer, curLine);
/* 253:    */          } else {
/* 254:254 */            throw new UnsupportedOperationException("Unsupported format for this image");
/* 255:    */          }
/* 256:    */          break;
/* 257:    */        case 3: 
/* 258:258 */          switch (this.bitdepth) {
/* 259:259 */          case 8:  palLine = curLine;break;
/* 260:260 */          case 4:  expand4(curLine, palLine);break;
/* 261:261 */          case 2:  expand2(curLine, palLine);break;
/* 262:262 */          case 1:  expand1(curLine, palLine);break;
/* 263:263 */          case 3: case 5: case 6: case 7: default:  throw new UnsupportedOperationException("Unsupported bitdepth for this image");
/* 264:    */          }
/* 265:265 */          if (fmt == ABGR) {
/* 266:266 */            copyPALtoABGR(buffer, palLine);
/* 267:267 */          } else if (fmt == RGBA) {
/* 268:268 */            copyPALtoRGBA(buffer, palLine);
/* 269:269 */          } else if (fmt == BGRA) {
/* 270:270 */            copyPALtoBGRA(buffer, palLine);
/* 271:    */          } else {
/* 272:272 */            throw new UnsupportedOperationException("Unsupported format for this image");
/* 273:    */          }
/* 274:    */          break;
/* 275:    */        case 1: case 5: default: 
/* 276:276 */          throw new UnsupportedOperationException("Not yet implemented");
/* 277:    */        }
/* 278:    */        
/* 279:279 */        byte[] tmp = curLine;
/* 280:280 */        curLine = prevLine;
/* 281:281 */        prevLine = tmp;
/* 282:    */      }
/* 283:    */    } finally {
/* 284:284 */      inflater.end();
/* 285:    */    }
/* 286:    */  }
/* 287:    */  
/* 288:    */  private void copy(ByteBuffer buffer, byte[] curLine) {
/* 289:289 */    buffer.put(curLine, 1, curLine.length - 1);
/* 290:    */  }
/* 291:    */  
/* 292:    */  private void copyRGBtoABGR(ByteBuffer buffer, byte[] curLine) {
/* 293:293 */    if (this.transPixel != null) {
/* 294:294 */      byte tr = this.transPixel[1];
/* 295:295 */      byte tg = this.transPixel[3];
/* 296:296 */      byte tb = this.transPixel[5];
/* 297:297 */      int i = 1; for (int n = curLine.length; i < n; i += 3) {
/* 298:298 */        byte r = curLine[i];
/* 299:299 */        byte g = curLine[(i + 1)];
/* 300:300 */        byte b = curLine[(i + 2)];
/* 301:301 */        byte a = -1;
/* 302:302 */        if ((r == tr) && (g == tg) && (b == tb)) {
/* 303:303 */          a = 0;
/* 304:    */        }
/* 305:305 */        buffer.put(a).put(b).put(g).put(r);
/* 306:    */      }
/* 307:    */    } else {
/* 308:308 */      int i = 1; for (int n = curLine.length; i < n; i += 3) {
/* 309:309 */        buffer.put((byte)-1).put(curLine[(i + 2)]).put(curLine[(i + 1)]).put(curLine[i]);
/* 310:    */      }
/* 311:    */    }
/* 312:    */  }
/* 313:    */  
/* 314:    */  private void copyRGBtoRGBA(ByteBuffer buffer, byte[] curLine) {
/* 315:315 */    if (this.transPixel != null) {
/* 316:316 */      byte tr = this.transPixel[1];
/* 317:317 */      byte tg = this.transPixel[3];
/* 318:318 */      byte tb = this.transPixel[5];
/* 319:319 */      int i = 1; for (int n = curLine.length; i < n; i += 3) {
/* 320:320 */        byte r = curLine[i];
/* 321:321 */        byte g = curLine[(i + 1)];
/* 322:322 */        byte b = curLine[(i + 2)];
/* 323:323 */        byte a = -1;
/* 324:324 */        if ((r == tr) && (g == tg) && (b == tb)) {
/* 325:325 */          a = 0;
/* 326:    */        }
/* 327:327 */        buffer.put(r).put(g).put(b).put(a);
/* 328:    */      }
/* 329:    */    } else {
/* 330:330 */      int i = 1; for (int n = curLine.length; i < n; i += 3) {
/* 331:331 */        buffer.put(curLine[i]).put(curLine[(i + 1)]).put(curLine[(i + 2)]).put((byte)-1);
/* 332:    */      }
/* 333:    */    }
/* 334:    */  }
/* 335:    */  
/* 336:    */  private void copyRGBtoBGRA(ByteBuffer buffer, byte[] curLine) {
/* 337:337 */    if (this.transPixel != null) {
/* 338:338 */      byte tr = this.transPixel[1];
/* 339:339 */      byte tg = this.transPixel[3];
/* 340:340 */      byte tb = this.transPixel[5];
/* 341:341 */      int i = 1; for (int n = curLine.length; i < n; i += 3) {
/* 342:342 */        byte r = curLine[i];
/* 343:343 */        byte g = curLine[(i + 1)];
/* 344:344 */        byte b = curLine[(i + 2)];
/* 345:345 */        byte a = -1;
/* 346:346 */        if ((r == tr) && (g == tg) && (b == tb)) {
/* 347:347 */          a = 0;
/* 348:    */        }
/* 349:349 */        buffer.put(b).put(g).put(r).put(a);
/* 350:    */      }
/* 351:    */    } else {
/* 352:352 */      int i = 1; for (int n = curLine.length; i < n; i += 3) {
/* 353:353 */        buffer.put(curLine[(i + 2)]).put(curLine[(i + 1)]).put(curLine[i]).put((byte)-1);
/* 354:    */      }
/* 355:    */    }
/* 356:    */  }
/* 357:    */  
/* 358:    */  private void copyRGBAtoABGR(ByteBuffer buffer, byte[] curLine) {
/* 359:359 */    int i = 1; for (int n = curLine.length; i < n; i += 4) {
/* 360:360 */      buffer.put(curLine[(i + 3)]).put(curLine[(i + 2)]).put(curLine[(i + 1)]).put(curLine[i]);
/* 361:    */    }
/* 362:    */  }
/* 363:    */  
/* 364:    */  private void copyRGBAtoBGRA(ByteBuffer buffer, byte[] curLine) {
/* 365:365 */    int i = 1; for (int n = curLine.length; i < n; i += 4) {
/* 366:366 */      buffer.put(curLine[(i + 2)]).put(curLine[(i + 1)]).put(curLine[(i + 0)]).put(curLine[(i + 3)]);
/* 367:    */    }
/* 368:    */  }
/* 369:    */  
/* 370:    */  private void copyRGBAtoRGB(ByteBuffer buffer, byte[] curLine) {
/* 371:371 */    int i = 1; for (int n = curLine.length; i < n; i += 4) {
/* 372:372 */      buffer.put(curLine[i]).put(curLine[(i + 1)]).put(curLine[(i + 2)]);
/* 373:    */    }
/* 374:    */  }
/* 375:    */  
/* 376:    */  private void copyPALtoABGR(ByteBuffer buffer, byte[] curLine) {
/* 377:377 */    if (this.paletteA != null) {
/* 378:378 */      int i = 1; for (int n = curLine.length; i < n; i++) {
/* 379:379 */        int idx = curLine[i] & 0xFF;
/* 380:380 */        byte r = this.palette[(idx * 3 + 0)];
/* 381:381 */        byte g = this.palette[(idx * 3 + 1)];
/* 382:382 */        byte b = this.palette[(idx * 3 + 2)];
/* 383:383 */        byte a = this.paletteA[idx];
/* 384:384 */        buffer.put(a).put(b).put(g).put(r);
/* 385:    */      }
/* 386:    */    } else {
/* 387:387 */      int i = 1; for (int n = curLine.length; i < n; i++) {
/* 388:388 */        int idx = curLine[i] & 0xFF;
/* 389:389 */        byte r = this.palette[(idx * 3 + 0)];
/* 390:390 */        byte g = this.palette[(idx * 3 + 1)];
/* 391:391 */        byte b = this.palette[(idx * 3 + 2)];
/* 392:392 */        byte a = -1;
/* 393:393 */        buffer.put(a).put(b).put(g).put(r);
/* 394:    */      }
/* 395:    */    }
/* 396:    */  }
/* 397:    */  
/* 398:    */  private void copyPALtoRGBA(ByteBuffer buffer, byte[] curLine) {
/* 399:399 */    if (this.paletteA != null) {
/* 400:400 */      int i = 1; for (int n = curLine.length; i < n; i++) {
/* 401:401 */        int idx = curLine[i] & 0xFF;
/* 402:402 */        byte r = this.palette[(idx * 3 + 0)];
/* 403:403 */        byte g = this.palette[(idx * 3 + 1)];
/* 404:404 */        byte b = this.palette[(idx * 3 + 2)];
/* 405:405 */        byte a = this.paletteA[idx];
/* 406:406 */        buffer.put(r).put(g).put(b).put(a);
/* 407:    */      }
/* 408:    */    } else {
/* 409:409 */      int i = 1; for (int n = curLine.length; i < n; i++) {
/* 410:410 */        int idx = curLine[i] & 0xFF;
/* 411:411 */        byte r = this.palette[(idx * 3 + 0)];
/* 412:412 */        byte g = this.palette[(idx * 3 + 1)];
/* 413:413 */        byte b = this.palette[(idx * 3 + 2)];
/* 414:414 */        byte a = -1;
/* 415:415 */        buffer.put(r).put(g).put(b).put(a);
/* 416:    */      }
/* 417:    */    }
/* 418:    */  }
/* 419:    */  
/* 420:    */  private void copyPALtoBGRA(ByteBuffer buffer, byte[] curLine) {
/* 421:421 */    if (this.paletteA != null) {
/* 422:422 */      int i = 1; for (int n = curLine.length; i < n; i++) {
/* 423:423 */        int idx = curLine[i] & 0xFF;
/* 424:424 */        byte r = this.palette[(idx * 3 + 0)];
/* 425:425 */        byte g = this.palette[(idx * 3 + 1)];
/* 426:426 */        byte b = this.palette[(idx * 3 + 2)];
/* 427:427 */        byte a = this.paletteA[idx];
/* 428:428 */        buffer.put(b).put(g).put(r).put(a);
/* 429:    */      }
/* 430:    */    } else {
/* 431:431 */      int i = 1; for (int n = curLine.length; i < n; i++) {
/* 432:432 */        int idx = curLine[i] & 0xFF;
/* 433:433 */        byte r = this.palette[(idx * 3 + 0)];
/* 434:434 */        byte g = this.palette[(idx * 3 + 1)];
/* 435:435 */        byte b = this.palette[(idx * 3 + 2)];
/* 436:436 */        byte a = -1;
/* 437:437 */        buffer.put(b).put(g).put(r).put(a);
/* 438:    */      }
/* 439:    */    }
/* 440:    */  }
/* 441:    */  
/* 442:    */  private void expand4(byte[] src, byte[] dst) {
/* 443:443 */    int i = 1; for (int n = dst.length; i < n; i += 2) {
/* 444:444 */      int val = src[(1 + (i >> 1))] & 0xFF;
/* 445:445 */      switch (n - i) {
/* 446:446 */      default:  dst[(i + 1)] = ((byte)(val & 0xF)); }
/* 447:447 */      dst[i] = ((byte)(val >> 4));
/* 448:    */    }
/* 449:    */  }
/* 450:    */  
/* 451:    */  private void expand2(byte[] src, byte[] dst)
/* 452:    */  {
/* 453:453 */    int i = 1; for (int n = dst.length; i < n; i += 4) {
/* 454:454 */      int val = src[(1 + (i >> 2))] & 0xFF;
/* 455:455 */      switch (n - i) {
/* 456:456 */      default:  dst[(i + 3)] = ((byte)(val & 0x3));
/* 457:457 */      case 3:  dst[(i + 2)] = ((byte)(val >> 2 & 0x3));
/* 458:458 */      case 2:  dst[(i + 1)] = ((byte)(val >> 4 & 0x3)); }
/* 459:459 */      dst[i] = ((byte)(val >> 6));
/* 460:    */    }
/* 461:    */  }
/* 462:    */  
/* 463:    */  private void expand1(byte[] src, byte[] dst)
/* 464:    */  {
/* 465:465 */    int i = 1; for (int n = dst.length; i < n; i += 8) {
/* 466:466 */      int val = src[(1 + (i >> 3))] & 0xFF;
/* 467:467 */      switch (n - i) {
/* 468:468 */      default:  dst[(i + 7)] = ((byte)(val & 0x1));
/* 469:469 */      case 7:  dst[(i + 6)] = ((byte)(val >> 1 & 0x1));
/* 470:470 */      case 6:  dst[(i + 5)] = ((byte)(val >> 2 & 0x1));
/* 471:471 */      case 5:  dst[(i + 4)] = ((byte)(val >> 3 & 0x1));
/* 472:472 */      case 4:  dst[(i + 3)] = ((byte)(val >> 4 & 0x1));
/* 473:473 */      case 3:  dst[(i + 2)] = ((byte)(val >> 5 & 0x1));
/* 474:474 */      case 2:  dst[(i + 1)] = ((byte)(val >> 6 & 0x1)); }
/* 475:475 */      dst[i] = ((byte)(val >> 7));
/* 476:    */    }
/* 477:    */  }
/* 478:    */  
/* 479:    */  private void unfilter(byte[] curLine, byte[] prevLine) throws IOException
/* 480:    */  {
/* 481:481 */    switch (curLine[0]) {
/* 482:    */    case 0: 
/* 483:483 */      break;
/* 484:    */    case 1: 
/* 485:485 */      unfilterSub(curLine);
/* 486:486 */      break;
/* 487:    */    case 2: 
/* 488:488 */      unfilterUp(curLine, prevLine);
/* 489:489 */      break;
/* 490:    */    case 3: 
/* 491:491 */      unfilterAverage(curLine, prevLine);
/* 492:492 */      break;
/* 493:    */    case 4: 
/* 494:494 */      unfilterPaeth(curLine, prevLine);
/* 495:495 */      break;
/* 496:    */    default: 
/* 497:497 */      throw new IOException("invalide filter type in scanline: " + curLine[0]);
/* 498:    */    }
/* 499:    */  }
/* 500:    */  
/* 501:    */  private void unfilterSub(byte[] curLine) {
/* 502:502 */    int bpp = this.bytesPerPixel;
/* 503:503 */    int i = bpp + 1; for (int n = curLine.length; i < n; i++) {
/* 504:504 */      int tmp21_20 = i; byte[] tmp21_19 = curLine;tmp21_19[tmp21_20] = ((byte)(tmp21_19[tmp21_20] + curLine[(i - bpp)]));
/* 505:    */    }
/* 506:    */  }
/* 507:    */  
/* 508:    */  private void unfilterUp(byte[] curLine, byte[] prevLine) {
/* 509:509 */    int bpp = this.bytesPerPixel;
/* 510:510 */    int i = 1; for (int n = curLine.length; i < n; i++) {
/* 511:511 */      int tmp22_20 = i; byte[] tmp22_19 = curLine;tmp22_19[tmp22_20] = ((byte)(tmp22_19[tmp22_20] + prevLine[i]));
/* 512:    */    }
/* 513:    */  }
/* 514:    */  
/* 515:    */  private void unfilterAverage(byte[] curLine, byte[] prevLine) {
/* 516:516 */    int bpp = this.bytesPerPixel;
/* 517:    */    
/* 519:519 */    for (int i = 1; i <= bpp; i++) {
/* 520:520 */      int tmp17_15 = i; byte[] tmp17_14 = curLine;tmp17_14[tmp17_15] = ((byte)(tmp17_14[tmp17_15] + (byte)((prevLine[i] & 0xFF) >>> 1)));
/* 521:    */    }
/* 522:522 */    for (int n = curLine.length; i < n; i++) {
/* 523:523 */      int tmp53_51 = i; byte[] tmp53_50 = curLine;tmp53_50[tmp53_51] = ((byte)(tmp53_50[tmp53_51] + (byte)((prevLine[i] & 0xFF) + (curLine[(i - bpp)] & 0xFF) >>> 1)));
/* 524:    */    }
/* 525:    */  }
/* 526:    */  
/* 527:    */  private void unfilterPaeth(byte[] curLine, byte[] prevLine) {
/* 528:528 */    int bpp = this.bytesPerPixel;
/* 529:    */    
/* 531:531 */    for (int i = 1; i <= bpp; i++) {
/* 532:532 */      int tmp17_15 = i; byte[] tmp17_14 = curLine;tmp17_14[tmp17_15] = ((byte)(tmp17_14[tmp17_15] + prevLine[i]));
/* 533:    */    }
/* 534:534 */    for (int n = curLine.length; i < n; i++) {
/* 535:535 */      int a = curLine[(i - bpp)] & 0xFF;
/* 536:536 */      int b = prevLine[i] & 0xFF;
/* 537:537 */      int c = prevLine[(i - bpp)] & 0xFF;
/* 538:538 */      int p = a + b - c;
/* 539:539 */      int pa = p - a; if (pa < 0) pa = -pa;
/* 540:540 */      int pb = p - b; if (pb < 0) pb = -pb;
/* 541:541 */      int pc = p - c; if (pc < 0) pc = -pc;
/* 542:542 */      if ((pa <= pb) && (pa <= pc)) {
/* 543:543 */        c = a;
/* 544:544 */      } else if (pb <= pc)
/* 545:545 */        c = b;
/* 546:546 */      int tmp173_171 = i; byte[] tmp173_170 = curLine;tmp173_170[tmp173_171] = ((byte)(tmp173_170[tmp173_171] + (byte)c));
/* 547:    */    }
/* 548:    */  }
/* 549:    */  
/* 550:    */  private void readIHDR() throws IOException {
/* 551:551 */    checkChunkLength(13);
/* 552:552 */    readChunk(this.buffer, 0, 13);
/* 553:553 */    this.width = readInt(this.buffer, 0);
/* 554:554 */    this.height = readInt(this.buffer, 4);
/* 555:555 */    this.bitdepth = (this.buffer[8] & 0xFF);
/* 556:556 */    this.colorType = (this.buffer[9] & 0xFF);
/* 557:    */    
/* 558:558 */    switch (this.colorType) {
/* 559:    */    case 0: 
/* 560:560 */      if (this.bitdepth != 8) {
/* 561:561 */        throw new IOException("Unsupported bit depth: " + this.bitdepth);
/* 562:    */      }
/* 563:563 */      this.bytesPerPixel = 1;
/* 564:564 */      break;
/* 565:    */    case 4: 
/* 566:566 */      if (this.bitdepth != 8) {
/* 567:567 */        throw new IOException("Unsupported bit depth: " + this.bitdepth);
/* 568:    */      }
/* 569:569 */      this.bytesPerPixel = 2;
/* 570:570 */      break;
/* 571:    */    case 2: 
/* 572:572 */      if (this.bitdepth != 8) {
/* 573:573 */        throw new IOException("Unsupported bit depth: " + this.bitdepth);
/* 574:    */      }
/* 575:575 */      this.bytesPerPixel = 3;
/* 576:576 */      break;
/* 577:    */    case 6: 
/* 578:578 */      if (this.bitdepth != 8) {
/* 579:579 */        throw new IOException("Unsupported bit depth: " + this.bitdepth);
/* 580:    */      }
/* 581:581 */      this.bytesPerPixel = 4;
/* 582:582 */      break;
/* 583:    */    case 3: 
/* 584:584 */      switch (this.bitdepth) {
/* 585:    */      case 1: 
/* 586:    */      case 2: 
/* 587:    */      case 4: 
/* 588:    */      case 8: 
/* 589:589 */        this.bytesPerPixel = 1;
/* 590:590 */        break;
/* 591:    */      case 3: case 5: case 6: case 7: default: 
/* 592:592 */        throw new IOException("Unsupported bit depth: " + this.bitdepth);
/* 593:    */      }
/* 594:    */      break;
/* 595:    */    case 1: case 5: default: 
/* 596:596 */      throw new IOException("unsupported color format: " + this.colorType);
/* 597:    */    }
/* 598:    */    
/* 599:599 */    if (this.buffer[10] != 0) {
/* 600:600 */      throw new IOException("unsupported compression method");
/* 601:    */    }
/* 602:602 */    if (this.buffer[11] != 0) {
/* 603:603 */      throw new IOException("unsupported filtering method");
/* 604:    */    }
/* 605:605 */    if (this.buffer[12] != 0) {
/* 606:606 */      throw new IOException("unsupported interlace method");
/* 607:    */    }
/* 608:    */  }
/* 609:    */  
/* 610:    */  private void readPLTE() throws IOException {
/* 611:611 */    int paletteEntries = this.chunkLength / 3;
/* 612:612 */    if ((paletteEntries < 1) || (paletteEntries > 256) || (this.chunkLength % 3 != 0)) {
/* 613:613 */      throw new IOException("PLTE chunk has wrong length");
/* 614:    */    }
/* 615:615 */    this.palette = new byte[paletteEntries * 3];
/* 616:616 */    readChunk(this.palette, 0, this.palette.length);
/* 617:    */  }
/* 618:    */  
/* 619:    */  private void readtRNS() throws IOException {
/* 620:620 */    switch (this.colorType) {
/* 621:    */    case 0: 
/* 622:622 */      checkChunkLength(2);
/* 623:623 */      this.transPixel = new byte[2];
/* 624:624 */      readChunk(this.transPixel, 0, 2);
/* 625:625 */      break;
/* 626:    */    case 2: 
/* 627:627 */      checkChunkLength(6);
/* 628:628 */      this.transPixel = new byte[6];
/* 629:629 */      readChunk(this.transPixel, 0, 6);
/* 630:630 */      break;
/* 631:    */    case 3: 
/* 632:632 */      if (this.palette == null) {
/* 633:633 */        throw new IOException("tRNS chunk without PLTE chunk");
/* 634:    */      }
/* 635:635 */      this.paletteA = new byte[this.palette.length / 3];
/* 636:636 */      Arrays.fill(this.paletteA, (byte)-1);
/* 637:637 */      readChunk(this.paletteA, 0, this.paletteA.length);
/* 638:638 */      break;
/* 639:    */    }
/* 640:    */  }
/* 641:    */  
/* 642:    */  private void closeChunk()
/* 643:    */    throws IOException
/* 644:    */  {
/* 645:645 */    if (this.chunkRemaining > 0)
/* 646:    */    {
/* 647:647 */      skip(this.chunkRemaining + 4);
/* 648:    */    } else {
/* 649:649 */      readFully(this.buffer, 0, 4);
/* 650:650 */      int expectedCrc = readInt(this.buffer, 0);
/* 651:651 */      int computedCrc = (int)this.crc.getValue();
/* 652:652 */      if (computedCrc != expectedCrc) {
/* 653:653 */        throw new IOException("Invalid CRC");
/* 654:    */      }
/* 655:    */    }
/* 656:656 */    this.chunkRemaining = 0;
/* 657:657 */    this.chunkLength = 0;
/* 658:658 */    this.chunkType = 0;
/* 659:    */  }
/* 660:    */  
/* 661:    */  private void openChunk() throws IOException {
/* 662:662 */    readFully(this.buffer, 0, 8);
/* 663:663 */    this.chunkLength = readInt(this.buffer, 0);
/* 664:664 */    this.chunkType = readInt(this.buffer, 4);
/* 665:665 */    this.chunkRemaining = this.chunkLength;
/* 666:666 */    this.crc.reset();
/* 667:667 */    this.crc.update(this.buffer, 4, 4);
/* 668:    */  }
/* 669:    */  
/* 670:    */  private void openChunk(int expected) throws IOException {
/* 671:671 */    openChunk();
/* 672:672 */    if (this.chunkType != expected) {
/* 673:673 */      throw new IOException("Expected chunk: " + Integer.toHexString(expected));
/* 674:    */    }
/* 675:    */  }
/* 676:    */  
/* 677:    */  private void checkChunkLength(int expected) throws IOException {
/* 678:678 */    if (this.chunkLength != expected) {
/* 679:679 */      throw new IOException("Chunk has wrong size");
/* 680:    */    }
/* 681:    */  }
/* 682:    */  
/* 683:    */  private int readChunk(byte[] buffer, int offset, int length) throws IOException {
/* 684:684 */    if (length > this.chunkRemaining) {
/* 685:685 */      length = this.chunkRemaining;
/* 686:    */    }
/* 687:687 */    readFully(buffer, offset, length);
/* 688:688 */    this.crc.update(buffer, offset, length);
/* 689:689 */    this.chunkRemaining -= length;
/* 690:690 */    return length;
/* 691:    */  }
/* 692:    */  
/* 693:    */  private void refillInflater(Inflater inflater) throws IOException {
/* 694:694 */    while (this.chunkRemaining == 0) {
/* 695:695 */      closeChunk();
/* 696:696 */      openChunk(1229209940);
/* 697:    */    }
/* 698:698 */    int read = readChunk(this.buffer, 0, this.buffer.length);
/* 699:699 */    inflater.setInput(this.buffer, 0, read);
/* 700:    */  }
/* 701:    */  
/* 702:    */  private void readChunkUnzip(Inflater inflater, byte[] buffer, int offset, int length) throws IOException {
/* 703:    */    try {
/* 704:    */      do {
/* 705:705 */        int read = inflater.inflate(buffer, offset, length);
/* 706:706 */        if (read <= 0) {
/* 707:707 */          if (inflater.finished()) {
/* 708:708 */            throw new EOFException();
/* 709:    */          }
/* 710:710 */          if (inflater.needsInput()) {
/* 711:711 */            refillInflater(inflater);
/* 712:    */          } else {
/* 713:713 */            throw new IOException("Can't inflate " + length + " bytes");
/* 714:    */          }
/* 715:    */        } else {
/* 716:716 */          offset += read;
/* 717:717 */          length -= read;
/* 718:    */        }
/* 719:719 */      } while (length > 0);
/* 720:    */    } catch (DataFormatException ex) {
/* 721:721 */      throw ((IOException)new IOException("inflate error").initCause(ex));
/* 722:    */    }
/* 723:    */  }
/* 724:    */  
/* 725:    */  private void readFully(byte[] buffer, int offset, int length) throws IOException {
/* 726:    */    do {
/* 727:727 */      int read = this.input.read(buffer, offset, length);
/* 728:728 */      if (read < 0) {
/* 729:729 */        throw new EOFException();
/* 730:    */      }
/* 731:731 */      offset += read;
/* 732:732 */      length -= read;
/* 733:733 */    } while (length > 0);
/* 734:    */  }
/* 735:    */  
/* 736:    */  private int readInt(byte[] buffer, int offset) {
/* 737:737 */    return buffer[offset] << 24 | (buffer[(offset + 1)] & 0xFF) << 16 | (buffer[(offset + 2)] & 0xFF) << 8 | buffer[(offset + 3)] & 0xFF;
/* 738:    */  }
/* 739:    */  
/* 742:    */  private void skip(long amount)
/* 743:    */    throws IOException
/* 744:    */  {
/* 745:745 */    while (amount > 0L) {
/* 746:746 */      long skipped = this.input.skip(amount);
/* 747:747 */      if (skipped < 0L) {
/* 748:748 */        throw new EOFException();
/* 749:    */      }
/* 750:750 */      amount -= skipped;
/* 751:    */    }
/* 752:    */  }
/* 753:    */  
/* 754:    */  private static boolean checkSignature(byte[] buffer) {
/* 755:755 */    for (int i = 0; i < SIGNATURE.length; i++) {
/* 756:756 */      if (buffer[i] != SIGNATURE[i]) {
/* 757:757 */        return false;
/* 758:    */      }
/* 759:    */    }
/* 760:760 */    return true;
/* 761:    */  }
/* 762:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.PNGDecoder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */