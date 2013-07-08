/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.LWJGLUtil;
/*   6:    */
/*  69:    */public final class ContextAttribs
/*  70:    */{
/*  71:    */  private static final int CONTEXT_ES2_PROFILE_BIT_EXT = 4;
/*  72:    */  private static final int CONTEXT_ROBUST_ACCESS_BIT_ARB = 4;
/*  73:    */  private static final int CONTEXT_RESET_NOTIFICATION_STRATEGY_ARB = 33366;
/*  74:    */  private static final int NO_RESET_NOTIFICATION_ARB = 33377;
/*  75:    */  private static final int LOSE_CONTEXT_ON_RESET_ARB = 33362;
/*  76:    */  private static final int CONTEXT_RESET_ISOLATION_BIT_ARB = 8;
/*  77:    */  private int majorVersion;
/*  78:    */  private int minorVersion;
/*  79:    */  private int layerPlane;
/*  80:    */  private boolean debug;
/*  81:    */  private boolean forwardCompatible;
/*  82:    */  private boolean robustAccess;
/*  83:    */  private boolean profileCore;
/*  84:    */  private boolean profileCompatibility;
/*  85:    */  private boolean profileES;
/*  86:    */  private boolean loseContextOnReset;
/*  87:    */  private boolean contextResetIsolation;
/*  88:    */  
/*  89:    */  public ContextAttribs()
/*  90:    */  {
/*  91: 91 */    this(1, 0);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public ContextAttribs(int majorVersion, int minorVersion) {
/*  95: 95 */    if ((majorVersion < 0) || (4 < majorVersion) || (minorVersion < 0) || ((majorVersion == 4) && (3 < minorVersion)) || ((majorVersion == 3) && (3 < minorVersion)) || ((majorVersion == 2) && (1 < minorVersion)) || ((majorVersion == 1) && (5 < minorVersion)))
/*  96:    */    {
/* 101:101 */      throw new IllegalArgumentException("Invalid OpenGL version specified: " + majorVersion + '.' + minorVersion);
/* 102:    */    }
/* 103:103 */    this.majorVersion = majorVersion;
/* 104:104 */    this.minorVersion = minorVersion;
/* 105:    */  }
/* 106:    */  
/* 107:    */  private ContextAttribs(ContextAttribs attribs) {
/* 108:108 */    this.majorVersion = attribs.majorVersion;
/* 109:109 */    this.minorVersion = attribs.minorVersion;
/* 110:    */    
/* 111:111 */    this.layerPlane = attribs.layerPlane;
/* 112:    */    
/* 113:113 */    this.debug = attribs.debug;
/* 114:114 */    this.forwardCompatible = attribs.forwardCompatible;
/* 115:115 */    this.robustAccess = attribs.robustAccess;
/* 116:    */    
/* 117:117 */    this.profileCore = attribs.profileCore;
/* 118:118 */    this.profileCompatibility = attribs.profileCompatibility;
/* 119:119 */    this.profileES = attribs.profileES;
/* 120:    */    
/* 121:121 */    this.loseContextOnReset = attribs.loseContextOnReset;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public int getMajorVersion() {
/* 125:125 */    return this.majorVersion;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public int getMinorVersion() {
/* 129:129 */    return this.minorVersion;
/* 130:    */  }
/* 131:    */  
/* 132:    */  public int getLayerPlane() {
/* 133:133 */    return this.layerPlane;
/* 134:    */  }
/* 135:    */  
/* 136:    */  public boolean isDebug() {
/* 137:137 */    return this.debug;
/* 138:    */  }
/* 139:    */  
/* 140:    */  public boolean isForwardCompatible() {
/* 141:141 */    return this.forwardCompatible;
/* 142:    */  }
/* 143:    */  
/* 144:    */  public boolean isProfileCore() {
/* 145:145 */    return this.profileCore;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public boolean isProfileCompatibility() {
/* 149:149 */    return this.profileCompatibility;
/* 150:    */  }
/* 151:    */  
/* 152:    */  public boolean isProfileES() {
/* 153:153 */    return this.profileES;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public ContextAttribs withLayer(int layerPlane) {
/* 157:157 */    if (layerPlane < 0) {
/* 158:158 */      throw new IllegalArgumentException("Invalid layer plane specified: " + layerPlane);
/* 159:    */    }
/* 160:160 */    if (layerPlane == this.layerPlane) {
/* 161:161 */      return this;
/* 162:    */    }
/* 163:163 */    ContextAttribs attribs = new ContextAttribs(this);
/* 164:164 */    attribs.layerPlane = layerPlane;
/* 165:165 */    return attribs;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public ContextAttribs withDebug(boolean debug) {
/* 169:169 */    if (debug == this.debug) {
/* 170:170 */      return this;
/* 171:    */    }
/* 172:172 */    ContextAttribs attribs = new ContextAttribs(this);
/* 173:173 */    attribs.debug = debug;
/* 174:174 */    return attribs;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public ContextAttribs withForwardCompatible(boolean forwardCompatible) {
/* 178:178 */    if (forwardCompatible == this.forwardCompatible) {
/* 179:179 */      return this;
/* 180:    */    }
/* 181:181 */    ContextAttribs attribs = new ContextAttribs(this);
/* 182:182 */    attribs.forwardCompatible = forwardCompatible;
/* 183:183 */    return attribs;
/* 184:    */  }
/* 185:    */  
/* 186:    */  public ContextAttribs withProfileCore(boolean profileCore) {
/* 187:187 */    if ((this.majorVersion < 3) || ((this.majorVersion == 3) && (this.minorVersion < 2))) {
/* 188:188 */      throw new IllegalArgumentException("Profiles are only supported on OpenGL version 3.2 or higher.");
/* 189:    */    }
/* 190:190 */    if (profileCore == this.profileCore) {
/* 191:191 */      return this;
/* 192:    */    }
/* 193:193 */    ContextAttribs attribs = new ContextAttribs(this);
/* 194:194 */    attribs.profileCore = profileCore;
/* 195:195 */    if (profileCore) {
/* 196:196 */      attribs.profileCompatibility = false;
/* 197:    */    }
/* 198:198 */    return attribs;
/* 199:    */  }
/* 200:    */  
/* 201:    */  public ContextAttribs withProfileCompatibility(boolean profileCompatibility) {
/* 202:202 */    if ((this.majorVersion < 3) || ((this.majorVersion == 3) && (this.minorVersion < 2))) {
/* 203:203 */      throw new IllegalArgumentException("Profiles are only supported on OpenGL version 3.2 or higher.");
/* 204:    */    }
/* 205:205 */    if (profileCompatibility == this.profileCompatibility) {
/* 206:206 */      return this;
/* 207:    */    }
/* 208:208 */    ContextAttribs attribs = new ContextAttribs(this);
/* 209:209 */    attribs.profileCompatibility = profileCompatibility;
/* 210:210 */    if (profileCompatibility) {
/* 211:211 */      attribs.profileCore = false;
/* 212:    */    }
/* 213:213 */    return attribs;
/* 214:    */  }
/* 215:    */  
/* 216:    */  public ContextAttribs withProfileES(boolean profileES) {
/* 217:217 */    if ((this.majorVersion != 2) || (this.minorVersion != 0)) {
/* 218:218 */      throw new IllegalArgumentException("The OpenGL ES profiles is only supported for OpenGL version 2.0.");
/* 219:    */    }
/* 220:220 */    if (profileES == this.profileES) {
/* 221:221 */      return this;
/* 222:    */    }
/* 223:223 */    ContextAttribs attribs = new ContextAttribs(this);
/* 224:224 */    attribs.profileES = profileES;
/* 225:    */    
/* 226:226 */    return attribs;
/* 227:    */  }
/* 228:    */  
/* 237:    */  public ContextAttribs withLoseContextOnReset(boolean loseContextOnReset)
/* 238:    */  {
/* 239:239 */    if (loseContextOnReset == this.loseContextOnReset) {
/* 240:240 */      return this;
/* 241:    */    }
/* 242:242 */    ContextAttribs attribs = new ContextAttribs(this);
/* 243:243 */    attribs.loseContextOnReset = loseContextOnReset;
/* 244:244 */    return attribs;
/* 245:    */  }
/* 246:    */  
/* 247:    */  public ContextAttribs withContextResetIsolation(boolean contextResetIsolation) {
/* 248:248 */    if (contextResetIsolation == this.contextResetIsolation) {
/* 249:249 */      return this;
/* 250:    */    }
/* 251:251 */    ContextAttribs attribs = new ContextAttribs(this);
/* 252:252 */    attribs.contextResetIsolation = contextResetIsolation;
/* 253:253 */    return attribs;
/* 254:    */  }
/* 255:    */  
/* 256:    */  private static ContextAttribsImplementation getImplementation() {
/* 257:257 */    switch () {
/* 258:    */    case 1: 
/* 259:259 */      return new LinuxContextAttribs();
/* 260:    */    case 3: 
/* 261:261 */      return new WindowsContextAttribs();
/* 262:    */    }
/* 263:263 */    throw new IllegalStateException("Unsupported platform");
/* 264:    */  }
/* 265:    */  
/* 266:    */  IntBuffer getAttribList()
/* 267:    */  {
/* 268:268 */    if (LWJGLUtil.getPlatform() == 2) {
/* 269:269 */      return null;
/* 270:    */    }
/* 271:271 */    ContextAttribsImplementation implementation = getImplementation();
/* 272:    */    
/* 273:273 */    int attribCount = 0;
/* 274:    */    
/* 275:275 */    if ((this.majorVersion != 1) || (this.minorVersion != 0))
/* 276:276 */      attribCount += 2;
/* 277:277 */    if (0 < this.layerPlane) {
/* 278:278 */      attribCount++;
/* 279:    */    }
/* 280:280 */    int flags = 0;
/* 281:281 */    if (this.debug)
/* 282:282 */      flags |= implementation.getDebugBit();
/* 283:283 */    if (this.forwardCompatible)
/* 284:284 */      flags |= implementation.getForwardCompatibleBit();
/* 285:285 */    if (this.robustAccess)
/* 286:286 */      flags |= 4;
/* 287:287 */    if (this.contextResetIsolation)
/* 288:288 */      flags |= 8;
/* 289:289 */    if (0 < flags) {
/* 290:290 */      attribCount++;
/* 291:    */    }
/* 292:292 */    int profileMask = 0;
/* 293:293 */    if (this.profileCore) {
/* 294:294 */      profileMask |= implementation.getProfileCoreBit();
/* 295:295 */    } else if (this.profileCompatibility) {
/* 296:296 */      profileMask |= implementation.getProfileCompatibilityBit();
/* 297:297 */    } else if (this.profileES)
/* 298:298 */      profileMask |= 4;
/* 299:299 */    if (0 < profileMask) {
/* 300:300 */      attribCount++;
/* 301:    */    }
/* 302:302 */    if (this.loseContextOnReset) {
/* 303:303 */      attribCount++;
/* 304:    */    }
/* 305:305 */    if (attribCount == 0) {
/* 306:306 */      return null;
/* 307:    */    }
/* 308:308 */    IntBuffer attribs = BufferUtils.createIntBuffer(attribCount * 2 + 1);
/* 309:    */    
/* 310:310 */    if ((this.majorVersion != 1) || (this.minorVersion != 0)) {
/* 311:311 */      attribs.put(implementation.getMajorVersionAttrib()).put(this.majorVersion);
/* 312:312 */      attribs.put(implementation.getMinorVersionAttrib()).put(this.minorVersion);
/* 313:    */    }
/* 314:314 */    if (0 < this.layerPlane)
/* 315:315 */      attribs.put(implementation.getLayerPlaneAttrib()).put(this.layerPlane);
/* 316:316 */    if (0 < flags)
/* 317:317 */      attribs.put(implementation.getFlagsAttrib()).put(flags);
/* 318:318 */    if (0 < profileMask)
/* 319:319 */      attribs.put(implementation.getProfileMaskAttrib()).put(profileMask);
/* 320:320 */    if (this.loseContextOnReset) {
/* 321:321 */      attribs.put(33366).put(33362);
/* 322:    */    }
/* 323:323 */    attribs.put(0);
/* 324:324 */    attribs.rewind();
/* 325:325 */    return attribs;
/* 326:    */  }
/* 327:    */  
/* 328:    */  public String toString() {
/* 329:329 */    StringBuilder sb = new StringBuilder(32);
/* 330:    */    
/* 331:331 */    sb.append("ContextAttribs:");
/* 332:332 */    sb.append(" Version=").append(this.majorVersion).append('.').append(this.minorVersion);
/* 333:333 */    sb.append(" - Layer=").append(this.layerPlane);
/* 334:334 */    sb.append(" - Debug=").append(this.debug);
/* 335:335 */    sb.append(" - ForwardCompatible=").append(this.forwardCompatible);
/* 336:336 */    sb.append(" - RobustAccess=").append(this.robustAccess);
/* 337:337 */    if (this.robustAccess)
/* 338:338 */      sb.append(" (").append(this.loseContextOnReset ? "LOSE_CONTEXT_ON_RESET" : "NO_RESET_NOTIFICATION");
/* 339:339 */    sb.append(" - Profile=");
/* 340:340 */    if (this.profileCore) {
/* 341:341 */      sb.append("Core");
/* 342:342 */    } else if (this.profileCompatibility) {
/* 343:343 */      sb.append("Compatibility");
/* 344:    */    } else {
/* 345:345 */      sb.append("None");
/* 346:    */    }
/* 347:347 */    return sb.toString();
/* 348:    */  }
/* 349:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextAttribs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */