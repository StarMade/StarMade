/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.Buffer;
/*   4:    */import java.nio.FloatBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.LWJGLUtil;
/*   7:    */
/*  65:    */class GLChecks
/*  66:    */{
/*  67:    */  static int getBufferObjectSize(ContextCapabilities caps, int buffer_enum)
/*  68:    */  {
/*  69: 69 */    return GL15.glGetBufferParameteri(buffer_enum, 34660);
/*  70:    */  }
/*  71:    */  
/*  72:    */  static int getBufferObjectSizeARB(ContextCapabilities caps, int buffer_enum) {
/*  73: 73 */    return ARBBufferObject.glGetBufferParameteriARB(buffer_enum, 34660);
/*  74:    */  }
/*  75:    */  
/*  76:    */  static int getBufferObjectSizeATI(ContextCapabilities caps, int buffer) {
/*  77: 77 */    return ATIVertexArrayObject.glGetObjectBufferiATI(buffer, 34660);
/*  78:    */  }
/*  79:    */  
/*  80:    */  static int getNamedBufferObjectSize(ContextCapabilities caps, int buffer) {
/*  81: 81 */    return EXTDirectStateAccess.glGetNamedBufferParameterEXT(buffer, 34660);
/*  82:    */  }
/*  83:    */  
/*  84:    */  static void ensureArrayVBOdisabled(ContextCapabilities caps)
/*  85:    */  {
/*  86: 86 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).arrayBuffer != 0)) {
/*  87: 87 */      throw new OpenGLException("Cannot use Buffers when Array Buffer Object is enabled");
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  91:    */  static void ensureArrayVBOenabled(ContextCapabilities caps) {
/*  92: 92 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).arrayBuffer == 0)) {
/*  93: 93 */      throw new OpenGLException("Cannot use offsets when Array Buffer Object is disabled");
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/*  97:    */  static void ensureElementVBOdisabled(ContextCapabilities caps) {
/*  98: 98 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getElementArrayBufferBound(caps) != 0)) {
/*  99: 99 */      throw new OpenGLException("Cannot use Buffers when Element Array Buffer Object is enabled");
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 103:    */  static void ensureElementVBOenabled(ContextCapabilities caps) {
/* 104:104 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getElementArrayBufferBound(caps) == 0)) {
/* 105:105 */      throw new OpenGLException("Cannot use offsets when Element Array Buffer Object is disabled");
/* 106:    */    }
/* 107:    */  }
/* 108:    */  
/* 109:    */  static void ensureIndirectBOdisabled(ContextCapabilities caps) {
/* 110:110 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).indirectBuffer != 0)) {
/* 111:111 */      throw new OpenGLException("Cannot use Buffers when Draw Indirect Object is enabled");
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 115:    */  static void ensureIndirectBOenabled(ContextCapabilities caps) {
/* 116:116 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).indirectBuffer == 0)) {
/* 117:117 */      throw new OpenGLException("Cannot use offsets when Draw Indirect Object is disabled");
/* 118:    */    }
/* 119:    */  }
/* 120:    */  
/* 121:    */  static void ensurePackPBOdisabled(ContextCapabilities caps) {
/* 122:122 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelPackBuffer != 0)) {
/* 123:123 */      throw new OpenGLException("Cannot use Buffers when Pixel Pack Buffer Object is enabled");
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 127:    */  static void ensurePackPBOenabled(ContextCapabilities caps) {
/* 128:128 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelPackBuffer == 0)) {
/* 129:129 */      throw new OpenGLException("Cannot use offsets when Pixel Pack Buffer Object is disabled");
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 133:    */  static void ensureUnpackPBOdisabled(ContextCapabilities caps) {
/* 134:134 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelUnpackBuffer != 0)) {
/* 135:135 */      throw new OpenGLException("Cannot use Buffers when Pixel Unpack Buffer Object is enabled");
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 139:    */  static void ensureUnpackPBOenabled(ContextCapabilities caps) {
/* 140:140 */    if ((LWJGLUtil.CHECKS) && (StateTracker.getReferences(caps).pixelUnpackBuffer == 0)) {
/* 141:141 */      throw new OpenGLException("Cannot use offsets when Pixel Unpack Buffer Object is disabled");
/* 142:    */    }
/* 143:    */  }
/* 144:    */  
/* 154:    */  static int calculateImageStorage(Buffer buffer, int format, int type, int width, int height, int depth)
/* 155:    */  {
/* 156:156 */    return LWJGLUtil.CHECKS ? calculateImageStorage(format, type, width, height, depth) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/* 157:    */  }
/* 158:    */  
/* 159:    */  static int calculateTexImage1DStorage(Buffer buffer, int format, int type, int width) {
/* 160:160 */    return LWJGLUtil.CHECKS ? calculateTexImage1DStorage(format, type, width) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/* 161:    */  }
/* 162:    */  
/* 163:    */  static int calculateTexImage2DStorage(Buffer buffer, int format, int type, int width, int height) {
/* 164:164 */    return LWJGLUtil.CHECKS ? calculateTexImage2DStorage(format, type, width, height) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/* 165:    */  }
/* 166:    */  
/* 167:    */  static int calculateTexImage3DStorage(Buffer buffer, int format, int type, int width, int height, int depth) {
/* 168:168 */    return LWJGLUtil.CHECKS ? calculateTexImage3DStorage(format, type, width, height, depth) >> BufferUtils.getElementSizeExponent(buffer) : 0;
/* 169:    */  }
/* 170:    */  
/* 181:    */  private static int calculateImageStorage(int format, int type, int width, int height, int depth)
/* 182:    */  {
/* 183:183 */    return calculateBytesPerPixel(format, type) * width * height * depth;
/* 184:    */  }
/* 185:    */  
/* 186:    */  private static int calculateTexImage1DStorage(int format, int type, int width) {
/* 187:187 */    return calculateBytesPerPixel(format, type) * width;
/* 188:    */  }
/* 189:    */  
/* 190:    */  private static int calculateTexImage2DStorage(int format, int type, int width, int height) {
/* 191:191 */    return calculateTexImage1DStorage(format, type, width) * height;
/* 192:    */  }
/* 193:    */  
/* 194:    */  private static int calculateTexImage3DStorage(int format, int type, int width, int height, int depth) {
/* 195:195 */    return calculateTexImage2DStorage(format, type, width, height) * depth;
/* 196:    */  }
/* 197:    */  
/* 198:    */  private static int calculateBytesPerPixel(int format, int type) {
/* 199:    */    int bpe;
/* 200:200 */    switch (type) {
/* 201:    */    case 5120: 
/* 202:    */    case 5121: 
/* 203:203 */      bpe = 1;
/* 204:204 */      break;
/* 205:    */    case 5122: 
/* 206:    */    case 5123: 
/* 207:207 */      bpe = 2;
/* 208:208 */      break;
/* 209:    */    case 5124: 
/* 210:    */    case 5125: 
/* 211:    */    case 5126: 
/* 212:212 */      bpe = 4;
/* 213:213 */      break;
/* 214:    */    
/* 215:    */    default: 
/* 216:216 */      return 0;
/* 217:    */    }
/* 218:    */    
/* 219:    */    int epp;
/* 220:220 */    switch (format) {
/* 221:    */    case 6406: 
/* 222:    */    case 6409: 
/* 223:223 */      epp = 1;
/* 224:224 */      break;
/* 225:    */    
/* 226:    */    case 6410: 
/* 227:227 */      epp = 2;
/* 228:228 */      break;
/* 229:    */    case 6407: 
/* 230:    */    case 32992: 
/* 231:231 */      epp = 3;
/* 232:232 */      break;
/* 233:    */    case 6408: 
/* 234:    */    case 32768: 
/* 235:    */    case 32993: 
/* 236:236 */      epp = 4;
/* 237:237 */      break;
/* 238:    */    
/* 239:    */    default: 
/* 240:240 */      return 0;
/* 241:    */    }
/* 242:    */    
/* 243:    */    
/* 245:245 */    return bpe * epp;
/* 246:    */  }
/* 247:    */  
/* 249:    */  static int calculateBytesPerCharCode(int type)
/* 250:    */  {
/* 251:251 */    switch (type) {
/* 252:    */    case 5121: 
/* 253:    */    case 37018: 
/* 254:254 */      return 1;
/* 255:    */    case 5123: 
/* 256:    */    case 5127: 
/* 257:    */    case 37019: 
/* 258:258 */      return 2;
/* 259:    */    case 5128: 
/* 260:260 */      return 3;
/* 261:    */    case 5129: 
/* 262:262 */      return 4;
/* 263:    */    }
/* 264:264 */    throw new IllegalArgumentException("Unsupported charcode type: " + type);
/* 265:    */  }
/* 266:    */  
/* 267:    */  static int calculateBytesPerPathName(int pathNameType)
/* 268:    */  {
/* 269:269 */    switch (pathNameType) {
/* 270:    */    case 5120: 
/* 271:    */    case 5121: 
/* 272:    */    case 37018: 
/* 273:273 */      return 1;
/* 274:    */    case 5122: 
/* 275:    */    case 5123: 
/* 276:    */    case 5127: 
/* 277:    */    case 37019: 
/* 278:278 */      return 2;
/* 279:    */    case 5128: 
/* 280:280 */      return 3;
/* 281:    */    case 5124: 
/* 282:    */    case 5125: 
/* 283:    */    case 5126: 
/* 284:    */    case 5129: 
/* 285:285 */      return 4;
/* 286:    */    }
/* 287:287 */    throw new IllegalArgumentException("Unsupported path name type: " + pathNameType);
/* 288:    */  }
/* 289:    */  
/* 290:    */  static int calculateTransformPathValues(int transformType)
/* 291:    */  {
/* 292:292 */    switch (transformType) {
/* 293:    */    case 0: 
/* 294:294 */      return 0;
/* 295:    */    case 37006: 
/* 296:    */    case 37007: 
/* 297:297 */      return 1;
/* 298:    */    case 37008: 
/* 299:299 */      return 2;
/* 300:    */    case 37009: 
/* 301:301 */      return 3;
/* 302:    */    case 37010: 
/* 303:    */    case 37014: 
/* 304:304 */      return 6;
/* 305:    */    case 37012: 
/* 306:    */    case 37016: 
/* 307:307 */      return 12;
/* 308:    */    }
/* 309:309 */    throw new IllegalArgumentException("Unsupported transform type: " + transformType);
/* 310:    */  }
/* 311:    */  
/* 312:    */  static int calculatePathColorGenCoeffsCount(int genMode, int colorFormat)
/* 313:    */  {
/* 314:314 */    int coeffsPerComponent = calculatePathGenCoeffsPerComponent(genMode);
/* 315:    */    
/* 316:316 */    switch (colorFormat) {
/* 317:    */    case 6407: 
/* 318:318 */      return 3 * coeffsPerComponent;
/* 319:    */    case 6408: 
/* 320:320 */      return 4 * coeffsPerComponent;
/* 321:    */    }
/* 322:322 */    return coeffsPerComponent;
/* 323:    */  }
/* 324:    */  
/* 325:    */  static int calculatePathTextGenCoeffsPerComponent(FloatBuffer coeffs, int genMode)
/* 326:    */  {
/* 327:327 */    if (genMode == 0) {
/* 328:328 */      return 0;
/* 329:    */    }
/* 330:330 */    return coeffs.remaining() / calculatePathGenCoeffsPerComponent(genMode);
/* 331:    */  }
/* 332:    */  
/* 333:    */  private static int calculatePathGenCoeffsPerComponent(int genMode) {
/* 334:334 */    switch (genMode) {
/* 335:    */    case 0: 
/* 336:336 */      return 0;
/* 337:    */    case 9217: 
/* 338:    */    case 37002: 
/* 339:339 */      return 3;
/* 340:    */    case 9216: 
/* 341:341 */      return 4;
/* 342:    */    }
/* 343:343 */    throw new IllegalArgumentException("Unsupported gen mode: " + genMode);
/* 344:    */  }
/* 345:    */  
/* 346:    */  static int calculateMetricsSize(int metricQueryMask, int stride)
/* 347:    */  {
/* 348:348 */    if ((LWJGLUtil.DEBUG) && ((stride < 0) || (stride % 4 != 0))) {
/* 349:349 */      throw new IllegalArgumentException("Invalid stride value: " + stride);
/* 350:    */    }
/* 351:351 */    int metrics = Integer.bitCount(metricQueryMask);
/* 352:    */    
/* 353:353 */    if ((LWJGLUtil.DEBUG) && (stride >> 2 < metrics)) {
/* 354:354 */      throw new IllegalArgumentException("The queried metrics do not fit in the specified stride: " + stride);
/* 355:    */    }
/* 356:356 */    return stride == 0 ? metrics : stride >> 2;
/* 357:    */  }
/* 358:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GLChecks
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */