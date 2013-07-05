/*     */ package org.jasypt.contrib.org.apache.commons.codec_1_3.binary;
/*     */ 
/*     */ import org.jasypt.contrib.org.apache.commons.codec_1_3.BinaryDecoder;
/*     */ import org.jasypt.contrib.org.apache.commons.codec_1_3.BinaryEncoder;
/*     */ import org.jasypt.contrib.org.apache.commons.codec_1_3.DecoderException;
/*     */ import org.jasypt.contrib.org.apache.commons.codec_1_3.EncoderException;
/*     */ 
/*     */ public class Base64
/*     */   implements BinaryEncoder, BinaryDecoder
/*     */ {
/*     */   static final int CHUNK_SIZE = 76;
/*  57 */   static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
/*     */   static final int BASELENGTH = 255;
/*     */   static final int LOOKUPLENGTH = 64;
/*     */   static final int EIGHTBIT = 8;
/*     */   static final int SIXTEENBIT = 16;
/*     */   static final int TWENTYFOURBITGROUP = 24;
/*     */   static final int FOURBYTE = 4;
/*     */   static final int SIGN = -128;
/*     */   static final byte PAD = 61;
/* 101 */   private static byte[] base64Alphabet = new byte['ÿ'];
/* 102 */   private static byte[] lookUpBase64Alphabet = new byte[64];
/*     */ 
/*     */   private static boolean isBase64(byte octect)
/*     */   {
/* 139 */     if (octect == 61)
/* 140 */       return true;
/* 141 */     if (base64Alphabet[octect] == -1) {
/* 142 */       return false;
/*     */     }
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isArrayByteBase64(byte[] arrayOctect)
/*     */   {
/* 158 */     arrayOctect = discardWhitespace(arrayOctect);
/*     */ 
/* 160 */     int length = arrayOctect.length;
/* 161 */     if (length == 0)
/*     */     {
/* 164 */       return true;
/*     */     }
/* 166 */     for (int i = 0; i < length; i++) {
/* 167 */       if (!isBase64(arrayOctect[i])) {
/* 168 */         return false;
/*     */       }
/*     */     }
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */   public static byte[] encodeBase64(byte[] binaryData)
/*     */   {
/* 182 */     return encodeBase64(binaryData, false);
/*     */   }
/*     */ 
/*     */   public static byte[] encodeBase64Chunked(byte[] binaryData)
/*     */   {
/* 193 */     return encodeBase64(binaryData, true);
/*     */   }
/*     */ 
/*     */   public Object decode(Object pObject)
/*     */     throws DecoderException
/*     */   {
/* 210 */     if (!(pObject instanceof byte[])) {
/* 211 */       throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
/*     */     }
/* 213 */     return decode((byte[])pObject);
/*     */   }
/*     */ 
/*     */   public byte[] decode(byte[] pArray)
/*     */   {
/* 224 */     return decodeBase64(pArray);
/*     */   }
/*     */ 
/*     */   public static byte[] encodeBase64(byte[] binaryData, boolean isChunked)
/*     */   {
/* 237 */     int lengthDataBits = binaryData.length * 8;
/* 238 */     int fewerThan24bits = lengthDataBits % 24;
/* 239 */     int numberTriplets = lengthDataBits / 24;
/* 240 */     byte[] encodedData = null;
/* 241 */     int encodedDataLength = 0;
/* 242 */     int nbrChunks = 0;
/*     */ 
/* 244 */     if (fewerThan24bits != 0)
/*     */     {
/* 246 */       encodedDataLength = (numberTriplets + 1) * 4;
/*     */     }
/*     */     else {
/* 249 */       encodedDataLength = numberTriplets * 4;
/*     */     }
/*     */ 
/* 255 */     if (isChunked)
/*     */     {
/* 257 */       nbrChunks = CHUNK_SEPARATOR.length == 0 ? 0 : (int)Math.ceil(encodedDataLength / 76.0F);
/*     */ 
/* 259 */       encodedDataLength += nbrChunks * CHUNK_SEPARATOR.length;
/*     */     }
/*     */ 
/* 262 */     encodedData = new byte[encodedDataLength];
/*     */ 
/* 264 */     byte k = 0; byte l = 0; byte b1 = 0; byte b2 = 0; byte b3 = 0;
/*     */ 
/* 266 */     int encodedIndex = 0;
/* 267 */     int dataIndex = 0;
/* 268 */     int i = 0;
/* 269 */     int nextSeparatorIndex = 76;
/* 270 */     int chunksSoFar = 0;
/*     */ 
/* 273 */     for (i = 0; i < numberTriplets; i++) {
/* 274 */       dataIndex = i * 3;
/* 275 */       b1 = binaryData[dataIndex];
/* 276 */       b2 = binaryData[(dataIndex + 1)];
/* 277 */       b3 = binaryData[(dataIndex + 2)];
/*     */ 
/* 281 */       l = (byte)(b2 & 0xF);
/* 282 */       k = (byte)(b1 & 0x3);
/*     */ 
/* 284 */       byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/*     */ 
/* 286 */       byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/*     */ 
/* 288 */       byte val3 = (b3 & 0xFFFFFF80) == 0 ? (byte)(b3 >> 6) : (byte)(b3 >> 6 ^ 0xFC);
/*     */ 
/* 291 */       encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
/*     */ 
/* 295 */       encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
/*     */ 
/* 297 */       encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2 | val3)];
/*     */ 
/* 299 */       encodedData[(encodedIndex + 3)] = lookUpBase64Alphabet[(b3 & 0x3F)];
/*     */ 
/* 301 */       encodedIndex += 4;
/*     */ 
/* 304 */       if (isChunked)
/*     */       {
/* 306 */         if (encodedIndex == nextSeparatorIndex) {
/* 307 */           System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
/*     */ 
/* 313 */           chunksSoFar++;
/* 314 */           nextSeparatorIndex = 76 * (chunksSoFar + 1) + chunksSoFar * CHUNK_SEPARATOR.length;
/*     */ 
/* 317 */           encodedIndex += CHUNK_SEPARATOR.length;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 323 */     dataIndex = i * 3;
/*     */ 
/* 325 */     if (fewerThan24bits == 8) {
/* 326 */       b1 = binaryData[dataIndex];
/* 327 */       k = (byte)(b1 & 0x3);
/*     */ 
/* 330 */       byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/*     */ 
/* 332 */       encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
/* 333 */       encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(k << 4)];
/* 334 */       encodedData[(encodedIndex + 2)] = 61;
/* 335 */       encodedData[(encodedIndex + 3)] = 61;
/* 336 */     } else if (fewerThan24bits == 16)
/*     */     {
/* 338 */       b1 = binaryData[dataIndex];
/* 339 */       b2 = binaryData[(dataIndex + 1)];
/* 340 */       l = (byte)(b2 & 0xF);
/* 341 */       k = (byte)(b1 & 0x3);
/*     */ 
/* 343 */       byte val1 = (b1 & 0xFFFFFF80) == 0 ? (byte)(b1 >> 2) : (byte)(b1 >> 2 ^ 0xC0);
/*     */ 
/* 345 */       byte val2 = (b2 & 0xFFFFFF80) == 0 ? (byte)(b2 >> 4) : (byte)(b2 >> 4 ^ 0xF0);
/*     */ 
/* 348 */       encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
/* 349 */       encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
/*     */ 
/* 351 */       encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2)];
/* 352 */       encodedData[(encodedIndex + 3)] = 61;
/*     */     }
/*     */ 
/* 355 */     if (isChunked)
/*     */     {
/* 357 */       if (chunksSoFar < nbrChunks) {
/* 358 */         System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 367 */     return encodedData;
/*     */   }
/*     */ 
/*     */   public static byte[] decodeBase64(byte[] base64Data)
/*     */   {
/* 378 */     base64Data = discardNonBase64(base64Data);
/*     */ 
/* 381 */     if (base64Data.length == 0) {
/* 382 */       return new byte[0];
/*     */     }
/*     */ 
/* 385 */     int numberQuadruple = base64Data.length / 4;
/* 386 */     byte[] decodedData = null;
/* 387 */     byte b1 = 0; byte b2 = 0; byte b3 = 0; byte b4 = 0; byte marker0 = 0; byte marker1 = 0;
/*     */ 
/* 391 */     int encodedIndex = 0;
/* 392 */     int dataIndex = 0;
/*     */ 
/* 395 */     int lastData = base64Data.length;
/*     */ 
/* 397 */     while (base64Data[(lastData - 1)] == 61) {
/* 398 */       lastData--; if (lastData == 0) {
/* 399 */         return new byte[0];
/*     */       }
/*     */     }
/* 402 */     decodedData = new byte[lastData - numberQuadruple];
/*     */ 
/* 405 */     for (int i = 0; i < numberQuadruple; i++) {
/* 406 */       dataIndex = i * 4;
/* 407 */       marker0 = base64Data[(dataIndex + 2)];
/* 408 */       marker1 = base64Data[(dataIndex + 3)];
/*     */ 
/* 410 */       b1 = base64Alphabet[base64Data[dataIndex]];
/* 411 */       b2 = base64Alphabet[base64Data[(dataIndex + 1)]];
/*     */ 
/* 413 */       if ((marker0 != 61) && (marker1 != 61))
/*     */       {
/* 415 */         b3 = base64Alphabet[marker0];
/* 416 */         b4 = base64Alphabet[marker1];
/*     */ 
/* 418 */         decodedData[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 419 */         decodedData[(encodedIndex + 1)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/*     */ 
/* 421 */         decodedData[(encodedIndex + 2)] = ((byte)(b3 << 6 | b4));
/* 422 */       } else if (marker0 == 61)
/*     */       {
/* 424 */         decodedData[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 425 */       } else if (marker1 == 61)
/*     */       {
/* 427 */         b3 = base64Alphabet[marker0];
/*     */ 
/* 429 */         decodedData[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
/* 430 */         decodedData[(encodedIndex + 1)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
/*     */       }
/*     */ 
/* 433 */       encodedIndex += 3;
/*     */     }
/* 435 */     return decodedData;
/*     */   }
/*     */ 
/*     */   static byte[] discardWhitespace(byte[] data)
/*     */   {
/* 446 */     byte[] groomedData = new byte[data.length];
/* 447 */     int bytesCopied = 0;
/*     */ 
/* 449 */     for (int i = 0; i < data.length; i++) {
/* 450 */       switch (data[i]) {
/*     */       case 9:
/*     */       case 10:
/*     */       case 13:
/*     */       case 32:
/* 455 */         break;
/*     */       default:
/* 457 */         groomedData[(bytesCopied++)] = data[i];
/*     */       }
/*     */     }
/*     */ 
/* 461 */     byte[] packedData = new byte[bytesCopied];
/*     */ 
/* 463 */     System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
/*     */ 
/* 465 */     return packedData;
/*     */   }
/*     */ 
/*     */   static byte[] discardNonBase64(byte[] data)
/*     */   {
/* 478 */     byte[] groomedData = new byte[data.length];
/* 479 */     int bytesCopied = 0;
/*     */ 
/* 481 */     for (int i = 0; i < data.length; i++) {
/* 482 */       if (isBase64(data[i])) {
/* 483 */         groomedData[(bytesCopied++)] = data[i];
/*     */       }
/*     */     }
/*     */ 
/* 487 */     byte[] packedData = new byte[bytesCopied];
/*     */ 
/* 489 */     System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
/*     */ 
/* 491 */     return packedData;
/*     */   }
/*     */ 
/*     */   public Object encode(Object pObject)
/*     */     throws EncoderException
/*     */   {
/* 510 */     if (!(pObject instanceof byte[])) {
/* 511 */       throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
/*     */     }
/*     */ 
/* 514 */     return encode((byte[])pObject);
/*     */   }
/*     */ 
/*     */   public byte[] encode(byte[] pArray)
/*     */   {
/* 525 */     return encodeBase64(pArray, false);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 106 */     for (int i = 0; i < 255; i++) {
/* 107 */       base64Alphabet[i] = -1;
/*     */     }
/* 109 */     for (int i = 90; i >= 65; i--) {
/* 110 */       base64Alphabet[i] = ((byte)(i - 65));
/*     */     }
/* 112 */     for (int i = 122; i >= 97; i--) {
/* 113 */       base64Alphabet[i] = ((byte)(i - 97 + 26));
/*     */     }
/* 115 */     for (int i = 57; i >= 48; i--) {
/* 116 */       base64Alphabet[i] = ((byte)(i - 48 + 52));
/*     */     }
/*     */ 
/* 119 */     base64Alphabet[43] = 62;
/* 120 */     base64Alphabet[47] = 63;
/*     */ 
/* 122 */     for (int i = 0; i <= 25; i++) {
/* 123 */       lookUpBase64Alphabet[i] = ((byte)(65 + i));
/*     */     }
/*     */ 
/* 126 */     int i = 26; for (int j = 0; i <= 51; j++) {
/* 127 */       lookUpBase64Alphabet[i] = ((byte)(97 + j));
/*     */ 
/* 126 */       i++;
/*     */     }
/*     */ 
/* 130 */     int i = 52; for (int j = 0; i <= 61; j++) {
/* 131 */       lookUpBase64Alphabet[i] = ((byte)(48 + j));
/*     */ 
/* 130 */       i++;
/*     */     }
/*     */ 
/* 134 */     lookUpBase64Alphabet[62] = 43;
/* 135 */     lookUpBase64Alphabet[63] = 47;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64
 * JD-Core Version:    0.6.2
 */