/*   1:    */package org.jasypt.contrib.org.apache.commons.codec_1_3.binary;
/*   2:    */
/*   3:    */import org.jasypt.contrib.org.apache.commons.codec_1_3.BinaryDecoder;
/*   4:    */import org.jasypt.contrib.org.apache.commons.codec_1_3.BinaryEncoder;
/*   5:    */import org.jasypt.contrib.org.apache.commons.codec_1_3.DecoderException;
/*   6:    */import org.jasypt.contrib.org.apache.commons.codec_1_3.EncoderException;
/*   7:    */
/*  53:    */public class Base64
/*  54:    */  implements BinaryEncoder, BinaryDecoder
/*  55:    */{
/*  56:    */  static final int CHUNK_SIZE = 76;
/*  57: 57 */  static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
/*  58:    */  
/*  62:    */  static final int BASELENGTH = 255;
/*  63:    */  
/*  67:    */  static final int LOOKUPLENGTH = 64;
/*  68:    */  
/*  72:    */  static final int EIGHTBIT = 8;
/*  73:    */  
/*  77:    */  static final int SIXTEENBIT = 16;
/*  78:    */  
/*  82:    */  static final int TWENTYFOURBITGROUP = 24;
/*  83:    */  
/*  87:    */  static final int FOURBYTE = 4;
/*  88:    */  
/*  92:    */  static final int SIGN = -128;
/*  93:    */  
/*  97:    */  static final byte PAD = 61;
/*  98:    */  
/* 101:101 */  private static byte[] base64Alphabet = new byte['ÿ'];
/* 102:102 */  private static byte[] lookUpBase64Alphabet = new byte[64];
/* 103:    */  
/* 104:    */  static
/* 105:    */  {
/* 106:106 */    for (int i = 0; i < 255; i++) {
/* 107:107 */      base64Alphabet[i] = -1;
/* 108:    */    }
/* 109:109 */    for (int i = 90; i >= 65; i--) {
/* 110:110 */      base64Alphabet[i] = ((byte)(i - 65));
/* 111:    */    }
/* 112:112 */    for (int i = 122; i >= 97; i--) {
/* 113:113 */      base64Alphabet[i] = ((byte)(i - 97 + 26));
/* 114:    */    }
/* 115:115 */    for (int i = 57; i >= 48; i--) {
/* 116:116 */      base64Alphabet[i] = ((byte)(i - 48 + 52));
/* 117:    */    }
/* 118:    */    
/* 119:119 */    base64Alphabet[43] = 62;
/* 120:120 */    base64Alphabet[47] = 63;
/* 121:    */    
/* 122:122 */    for (int i = 0; i <= 25; i++) {
/* 123:123 */      lookUpBase64Alphabet[i] = ((byte)(65 + i));
/* 124:    */    }
/* 125:    */    
/* 126:126 */    int i = 26; for (int j = 0; i <= 51; j++) {
/* 127:127 */      lookUpBase64Alphabet[i] = ((byte)(97 + j));i++;
/* 128:    */    }
/* 129:    */    
/* 131:130 */    int i = 52; for (int j = 0; i <= 61; j++) {
/* 132:131 */      lookUpBase64Alphabet[i] = ((byte)(48 + j));i++;
/* 133:    */    }
/* 134:    */    
/* 136:134 */    lookUpBase64Alphabet[62] = 43;
/* 137:135 */    lookUpBase64Alphabet[63] = 47;
/* 138:    */  }
/* 139:    */  
/* 140:    */  private static boolean isBase64(byte octect) {
/* 141:139 */    if (octect == 61)
/* 142:140 */      return true;
/* 143:141 */    if (base64Alphabet[octect] == -1) {
/* 144:142 */      return false;
/* 145:    */    }
/* 146:144 */    return true;
/* 147:    */  }
/* 148:    */  
/* 158:    */  public static boolean isArrayByteBase64(byte[] arrayOctect)
/* 159:    */  {
/* 160:158 */    arrayOctect = discardWhitespace(arrayOctect);
/* 161:    */    
/* 162:160 */    int length = arrayOctect.length;
/* 163:161 */    if (length == 0)
/* 164:    */    {
/* 166:164 */      return true;
/* 167:    */    }
/* 168:166 */    for (int i = 0; i < length; i++) {
/* 169:167 */      if (!isBase64(arrayOctect[i])) {
/* 170:168 */        return false;
/* 171:    */      }
/* 172:    */    }
/* 173:171 */    return true;
/* 174:    */  }
/* 175:    */  
/* 182:    */  public static byte[] encodeBase64(byte[] binaryData)
/* 183:    */  {
/* 184:182 */    return encodeBase64(binaryData, false);
/* 185:    */  }
/* 186:    */  
/* 193:    */  public static byte[] encodeBase64Chunked(byte[] binaryData)
/* 194:    */  {
/* 195:193 */    return encodeBase64(binaryData, true);
/* 196:    */  }
/* 197:    */  
/* 209:    */  public Object decode(Object pObject)
/* 210:    */    throws DecoderException
/* 211:    */  {
/* 212:210 */    if (!(pObject instanceof byte[])) {
/* 213:211 */      throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
/* 214:    */    }
/* 215:213 */    return decode((byte[])pObject);
/* 216:    */  }
/* 217:    */  
/* 224:    */  public byte[] decode(byte[] pArray)
/* 225:    */  {
/* 226:224 */    return decodeBase64(pArray);
/* 227:    */  }
/* 228:    */  
/* 237:    */  public static byte[] encodeBase64(byte[] binaryData, boolean isChunked)
/* 238:    */  {
/* 239:237 */    int lengthDataBits = binaryData.length * 8;
/* 240:238 */    int fewerThan24bits = lengthDataBits % 24;
/* 241:239 */    int numberTriplets = lengthDataBits / 24;
/* 242:240 */    byte[] encodedData = null;
/* 243:241 */    int encodedDataLength = 0;
/* 244:242 */    int nbrChunks = 0;
/* 245:    */    
/* 246:244 */    if (fewerThan24bits != 0)
/* 247:    */    {
/* 248:246 */      encodedDataLength = (numberTriplets + 1) * 4;
/* 249:    */    }
/* 250:    */    else {
/* 251:249 */      encodedDataLength = numberTriplets * 4;
/* 252:    */    }
/* 253:    */    
/* 257:255 */    if (isChunked)
/* 258:    */    {
/* 259:257 */      nbrChunks = CHUNK_SEPARATOR.length == 0 ? 0 : (int)Math.ceil(encodedDataLength / 76.0F);
/* 260:    */      
/* 261:259 */      encodedDataLength += nbrChunks * CHUNK_SEPARATOR.length;
/* 262:    */    }
/* 263:    */    
/* 264:262 */    encodedData = new byte[encodedDataLength];
/* 265:    */    
/* 266:264 */    byte k = 0;byte l = 0;byte b1 = 0;byte b2 = 0;byte b3 = 0;
/* 267:    */    
/* 268:266 */    int encodedIndex = 0;
/* 269:267 */    int dataIndex = 0;
/* 270:268 */    int i = 0;
/* 271:269 */    int nextSeparatorIndex = 76;
/* 272:270 */    int chunksSoFar = 0;
/* 273:    */    
/* 275:273 */    for (i = 0; i < numberTriplets; i++) {
/* 276:274 */      dataIndex = i * 3;
/* 277:275 */      b1 = binaryData[dataIndex];
/* 278:276 */      b2 = binaryData[(dataIndex + 1)];
/* 279:277 */      b3 = binaryData[(dataIndex + 2)];
/* 280:    */      
/* 283:281 */      l = (byte)(b2 & 0xF);
/* 284:282 */      k = (byte)(b1 & 0x3);
/* 285:    */      
/* 286:284 */      byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 287:    */      
/* 288:286 */      byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/* 289:    */      
/* 290:288 */      byte val3 = (b3 & 0xFFFFFF80) == 0 ? (byte)(b3 >> 6) : (byte)(b3 >> 6 ^ 0xFC);
/* 291:    */      
/* 293:291 */      encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
/* 294:    */      
/* 297:295 */      encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
/* 298:    */      
/* 299:297 */      encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2 | val3)];
/* 300:    */      
/* 301:299 */      encodedData[(encodedIndex + 3)] = lookUpBase64Alphabet[(b3 & 0x3F)];
/* 302:    */      
/* 303:301 */      encodedIndex += 4;
/* 304:    */      
/* 306:304 */      if (isChunked)
/* 307:    */      {
/* 308:306 */        if (encodedIndex == nextSeparatorIndex) {
/* 309:307 */          System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
/* 310:    */          
/* 315:313 */          chunksSoFar++;
/* 316:314 */          nextSeparatorIndex = 76 * (chunksSoFar + 1) + chunksSoFar * CHUNK_SEPARATOR.length;
/* 317:    */          
/* 319:317 */          encodedIndex += CHUNK_SEPARATOR.length;
/* 320:    */        }
/* 321:    */      }
/* 322:    */    }
/* 323:    */    
/* 325:323 */    dataIndex = i * 3;
/* 326:    */    
/* 327:325 */    if (fewerThan24bits == 8) {
/* 328:326 */      b1 = binaryData[dataIndex];
/* 329:327 */      k = (byte)(b1 & 0x3);
/* 330:    */      
/* 332:330 */      byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 333:    */      
/* 334:332 */      encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
/* 335:333 */      encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(k << 4)];
/* 336:334 */      encodedData[(encodedIndex + 2)] = 61;
/* 337:335 */      encodedData[(encodedIndex + 3)] = 61;
/* 338:336 */    } else if (fewerThan24bits == 16)
/* 339:    */    {
/* 340:338 */      b1 = binaryData[dataIndex];
/* 341:339 */      b2 = binaryData[(dataIndex + 1)];
/* 342:340 */      l = (byte)(b2 & 0xF);
/* 343:341 */      k = (byte)(b1 & 0x3);
/* 344:    */      
/* 345:343 */      byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/* 346:    */      
/* 347:345 */      byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/* 348:    */      
/* 350:348 */      encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
/* 351:349 */      encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
/* 352:    */      
/* 353:351 */      encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2)];
/* 354:352 */      encodedData[(encodedIndex + 3)] = 61;
/* 355:    */    }
/* 356:    */    
/* 357:355 */    if (isChunked)
/* 358:    */    {
/* 359:357 */      if (chunksSoFar < nbrChunks) {
/* 360:358 */        System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
/* 361:    */      }
/* 362:    */    }
/* 363:    */    
/* 369:367 */    return encodedData;
/* 370:    */  }
/* 371:    */  
/* 378:    */  public static byte[] decodeBase64(byte[] base64Data)
/* 379:    */  {
/* 380:378 */    base64Data = discardNonBase64(base64Data);
/* 381:    */    
/* 383:381 */    if (base64Data.length == 0) {
/* 384:382 */      return new byte[0];
/* 385:    */    }
/* 386:    */    
/* 387:385 */    int numberQuadruple = base64Data.length / 4;
/* 388:386 */    byte[] decodedData = null;
/* 389:387 */    byte b1 = 0;byte b2 = 0;byte b3 = 0;byte b4 = 0;byte marker0 = 0;byte marker1 = 0;
/* 390:    */    
/* 393:391 */    int encodedIndex = 0;
/* 394:392 */    int dataIndex = 0;
/* 395:    */    
/* 397:395 */    int lastData = base64Data.length;
/* 398:    */    
/* 399:397 */    while (base64Data[(lastData - 1)] == 61) {
/* 400:398 */      lastData--; if (lastData == 0) {
/* 401:399 */        return new byte[0];
/* 402:    */      }
/* 403:    */    }
/* 404:402 */    decodedData = new byte[lastData - numberQuadruple];
/* 405:    */    
/* 407:405 */    for (int i = 0; i < numberQuadruple; i++) {
/* 408:406 */      dataIndex = i * 4;
/* 409:407 */      marker0 = base64Data[(dataIndex + 2)];
/* 410:408 */      marker1 = base64Data[(dataIndex + 3)];
/* 411:    */      
/* 412:410 */      b1 = base64Alphabet[base64Data[dataIndex]];
/* 413:411 */      b2 = base64Alphabet[base64Data[(dataIndex + 1)]];
/* 414:    */      
/* 415:413 */      if ((marker0 != 61) && (marker1 != 61))
/* 416:    */      {
/* 417:415 */        b3 = base64Alphabet[marker0];
/* 418:416 */        b4 = base64Alphabet[marker1];
/* 419:    */        
/* 420:418 */        decodedData[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 421:419 */        decodedData[(encodedIndex + 1)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/* 422:    */        
/* 423:421 */        decodedData[(encodedIndex + 2)] = ((byte)(b3 << 6 | b4));
/* 424:422 */      } else if (marker0 == 61)
/* 425:    */      {
/* 426:424 */        decodedData[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 427:425 */      } else if (marker1 == 61)
/* 428:    */      {
/* 429:427 */        b3 = base64Alphabet[marker0];
/* 430:    */        
/* 431:429 */        decodedData[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 432:430 */        decodedData[(encodedIndex + 1)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/* 433:    */      }
/* 434:    */      
/* 435:433 */      encodedIndex += 3;
/* 436:    */    }
/* 437:435 */    return decodedData;
/* 438:    */  }
/* 439:    */  
/* 446:    */  static byte[] discardWhitespace(byte[] data)
/* 447:    */  {
/* 448:446 */    byte[] groomedData = new byte[data.length];
/* 449:447 */    int bytesCopied = 0;
/* 450:    */    
/* 451:449 */    for (int i = 0; i < data.length; i++) {
/* 452:450 */      switch (data[i]) {
/* 453:    */      case 9: 
/* 454:    */      case 10: 
/* 455:    */      case 13: 
/* 456:    */      case 32: 
/* 457:455 */        break;
/* 458:    */      default: 
/* 459:457 */        groomedData[(bytesCopied++)] = data[i];
/* 460:    */      }
/* 461:    */      
/* 462:    */    }
/* 463:461 */    byte[] packedData = new byte[bytesCopied];
/* 464:    */    
/* 465:463 */    System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
/* 466:    */    
/* 467:465 */    return packedData;
/* 468:    */  }
/* 469:    */  
/* 478:    */  static byte[] discardNonBase64(byte[] data)
/* 479:    */  {
/* 480:478 */    byte[] groomedData = new byte[data.length];
/* 481:479 */    int bytesCopied = 0;
/* 482:    */    
/* 483:481 */    for (int i = 0; i < data.length; i++) {
/* 484:482 */      if (isBase64(data[i])) {
/* 485:483 */        groomedData[(bytesCopied++)] = data[i];
/* 486:    */      }
/* 487:    */    }
/* 488:    */    
/* 489:487 */    byte[] packedData = new byte[bytesCopied];
/* 490:    */    
/* 491:489 */    System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
/* 492:    */    
/* 493:491 */    return packedData;
/* 494:    */  }
/* 495:    */  
/* 509:    */  public Object encode(Object pObject)
/* 510:    */    throws EncoderException
/* 511:    */  {
/* 512:510 */    if (!(pObject instanceof byte[])) {
/* 513:511 */      throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
/* 514:    */    }
/* 515:    */    
/* 516:514 */    return encode((byte[])pObject);
/* 517:    */  }
/* 518:    */  
/* 525:    */  public byte[] encode(byte[] pArray)
/* 526:    */  {
/* 527:525 */    return encodeBase64(pArray, false);
/* 528:    */  }
/* 529:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */