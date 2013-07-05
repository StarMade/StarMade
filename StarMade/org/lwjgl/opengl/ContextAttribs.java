/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ public final class ContextAttribs
/*     */ {
/*     */   private static final int CONTEXT_ES2_PROFILE_BIT_EXT = 4;
/*     */   private static final int CONTEXT_ROBUST_ACCESS_BIT_ARB = 4;
/*     */   private static final int CONTEXT_RESET_NOTIFICATION_STRATEGY_ARB = 33366;
/*     */   private static final int NO_RESET_NOTIFICATION_ARB = 33377;
/*     */   private static final int LOSE_CONTEXT_ON_RESET_ARB = 33362;
/*     */   private static final int CONTEXT_RESET_ISOLATION_BIT_ARB = 8;
/*     */   private int majorVersion;
/*     */   private int minorVersion;
/*     */   private int layerPlane;
/*     */   private boolean debug;
/*     */   private boolean forwardCompatible;
/*     */   private boolean robustAccess;
/*     */   private boolean profileCore;
/*     */   private boolean profileCompatibility;
/*     */   private boolean profileES;
/*     */   private boolean loseContextOnReset;
/*     */   private boolean contextResetIsolation;
/*     */ 
/*     */   public ContextAttribs()
/*     */   {
/*  91 */     this(1, 0);
/*     */   }
/*     */ 
/*     */   public ContextAttribs(int majorVersion, int minorVersion) {
/*  95 */     if ((majorVersion < 0) || (4 < majorVersion) || (minorVersion < 0) || ((majorVersion == 4) && (3 < minorVersion)) || ((majorVersion == 3) && (3 < minorVersion)) || ((majorVersion == 2) && (1 < minorVersion)) || ((majorVersion == 1) && (5 < minorVersion)))
/*     */     {
/* 101 */       throw new IllegalArgumentException("Invalid OpenGL version specified: " + majorVersion + '.' + minorVersion);
/*     */     }
/* 103 */     this.majorVersion = majorVersion;
/* 104 */     this.minorVersion = minorVersion;
/*     */   }
/*     */ 
/*     */   private ContextAttribs(ContextAttribs attribs) {
/* 108 */     this.majorVersion = attribs.majorVersion;
/* 109 */     this.minorVersion = attribs.minorVersion;
/*     */ 
/* 111 */     this.layerPlane = attribs.layerPlane;
/*     */ 
/* 113 */     this.debug = attribs.debug;
/* 114 */     this.forwardCompatible = attribs.forwardCompatible;
/* 115 */     this.robustAccess = attribs.robustAccess;
/*     */ 
/* 117 */     this.profileCore = attribs.profileCore;
/* 118 */     this.profileCompatibility = attribs.profileCompatibility;
/* 119 */     this.profileES = attribs.profileES;
/*     */ 
/* 121 */     this.loseContextOnReset = attribs.loseContextOnReset;
/*     */   }
/*     */ 
/*     */   public int getMajorVersion() {
/* 125 */     return this.majorVersion;
/*     */   }
/*     */ 
/*     */   public int getMinorVersion() {
/* 129 */     return this.minorVersion;
/*     */   }
/*     */ 
/*     */   public int getLayerPlane() {
/* 133 */     return this.layerPlane;
/*     */   }
/*     */ 
/*     */   public boolean isDebug() {
/* 137 */     return this.debug;
/*     */   }
/*     */ 
/*     */   public boolean isForwardCompatible() {
/* 141 */     return this.forwardCompatible;
/*     */   }
/*     */ 
/*     */   public boolean isProfileCore() {
/* 145 */     return this.profileCore;
/*     */   }
/*     */ 
/*     */   public boolean isProfileCompatibility() {
/* 149 */     return this.profileCompatibility;
/*     */   }
/*     */ 
/*     */   public boolean isProfileES() {
/* 153 */     return this.profileES;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withLayer(int layerPlane) {
/* 157 */     if (layerPlane < 0) {
/* 158 */       throw new IllegalArgumentException("Invalid layer plane specified: " + layerPlane);
/*     */     }
/* 160 */     if (layerPlane == this.layerPlane) {
/* 161 */       return this;
/*     */     }
/* 163 */     ContextAttribs attribs = new ContextAttribs(this);
/* 164 */     attribs.layerPlane = layerPlane;
/* 165 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withDebug(boolean debug) {
/* 169 */     if (debug == this.debug) {
/* 170 */       return this;
/*     */     }
/* 172 */     ContextAttribs attribs = new ContextAttribs(this);
/* 173 */     attribs.debug = debug;
/* 174 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withForwardCompatible(boolean forwardCompatible) {
/* 178 */     if (forwardCompatible == this.forwardCompatible) {
/* 179 */       return this;
/*     */     }
/* 181 */     ContextAttribs attribs = new ContextAttribs(this);
/* 182 */     attribs.forwardCompatible = forwardCompatible;
/* 183 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withProfileCore(boolean profileCore) {
/* 187 */     if ((this.majorVersion < 3) || ((this.majorVersion == 3) && (this.minorVersion < 2))) {
/* 188 */       throw new IllegalArgumentException("Profiles are only supported on OpenGL version 3.2 or higher.");
/*     */     }
/* 190 */     if (profileCore == this.profileCore) {
/* 191 */       return this;
/*     */     }
/* 193 */     ContextAttribs attribs = new ContextAttribs(this);
/* 194 */     attribs.profileCore = profileCore;
/* 195 */     if (profileCore) {
/* 196 */       attribs.profileCompatibility = false;
/*     */     }
/* 198 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withProfileCompatibility(boolean profileCompatibility) {
/* 202 */     if ((this.majorVersion < 3) || ((this.majorVersion == 3) && (this.minorVersion < 2))) {
/* 203 */       throw new IllegalArgumentException("Profiles are only supported on OpenGL version 3.2 or higher.");
/*     */     }
/* 205 */     if (profileCompatibility == this.profileCompatibility) {
/* 206 */       return this;
/*     */     }
/* 208 */     ContextAttribs attribs = new ContextAttribs(this);
/* 209 */     attribs.profileCompatibility = profileCompatibility;
/* 210 */     if (profileCompatibility) {
/* 211 */       attribs.profileCore = false;
/*     */     }
/* 213 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withProfileES(boolean profileES) {
/* 217 */     if ((this.majorVersion != 2) || (this.minorVersion != 0)) {
/* 218 */       throw new IllegalArgumentException("The OpenGL ES profiles is only supported for OpenGL version 2.0.");
/*     */     }
/* 220 */     if (profileES == this.profileES) {
/* 221 */       return this;
/*     */     }
/* 223 */     ContextAttribs attribs = new ContextAttribs(this);
/* 224 */     attribs.profileES = profileES;
/*     */ 
/* 226 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withLoseContextOnReset(boolean loseContextOnReset)
/*     */   {
/* 239 */     if (loseContextOnReset == this.loseContextOnReset) {
/* 240 */       return this;
/*     */     }
/* 242 */     ContextAttribs attribs = new ContextAttribs(this);
/* 243 */     attribs.loseContextOnReset = loseContextOnReset;
/* 244 */     return attribs;
/*     */   }
/*     */ 
/*     */   public ContextAttribs withContextResetIsolation(boolean contextResetIsolation) {
/* 248 */     if (contextResetIsolation == this.contextResetIsolation) {
/* 249 */       return this;
/*     */     }
/* 251 */     ContextAttribs attribs = new ContextAttribs(this);
/* 252 */     attribs.contextResetIsolation = contextResetIsolation;
/* 253 */     return attribs;
/*     */   }
/*     */ 
/*     */   private static ContextAttribsImplementation getImplementation() {
/* 257 */     switch (LWJGLUtil.getPlatform()) {
/*     */     case 1:
/* 259 */       return new LinuxContextAttribs();
/*     */     case 3:
/* 261 */       return new WindowsContextAttribs();
/*     */     }
/* 263 */     throw new IllegalStateException("Unsupported platform");
/*     */   }
/*     */ 
/*     */   IntBuffer getAttribList()
/*     */   {
/* 268 */     if (LWJGLUtil.getPlatform() == 2) {
/* 269 */       return null;
/*     */     }
/* 271 */     ContextAttribsImplementation implementation = getImplementation();
/*     */ 
/* 273 */     int attribCount = 0;
/*     */ 
/* 275 */     if ((this.majorVersion != 1) || (this.minorVersion != 0))
/* 276 */       attribCount += 2;
/* 277 */     if (0 < this.layerPlane) {
/* 278 */       attribCount++;
/*     */     }
/* 280 */     int flags = 0;
/* 281 */     if (this.debug)
/* 282 */       flags |= implementation.getDebugBit();
/* 283 */     if (this.forwardCompatible)
/* 284 */       flags |= implementation.getForwardCompatibleBit();
/* 285 */     if (this.robustAccess)
/* 286 */       flags |= 4;
/* 287 */     if (this.contextResetIsolation)
/* 288 */       flags |= 8;
/* 289 */     if (0 < flags) {
/* 290 */       attribCount++;
/*     */     }
/* 292 */     int profileMask = 0;
/* 293 */     if (this.profileCore)
/* 294 */       profileMask |= implementation.getProfileCoreBit();
/* 295 */     else if (this.profileCompatibility)
/* 296 */       profileMask |= implementation.getProfileCompatibilityBit();
/* 297 */     else if (this.profileES)
/* 298 */       profileMask |= 4;
/* 299 */     if (0 < profileMask) {
/* 300 */       attribCount++;
/*     */     }
/* 302 */     if (this.loseContextOnReset) {
/* 303 */       attribCount++;
/*     */     }
/* 305 */     if (attribCount == 0) {
/* 306 */       return null;
/*     */     }
/* 308 */     IntBuffer attribs = BufferUtils.createIntBuffer(attribCount * 2 + 1);
/*     */ 
/* 310 */     if ((this.majorVersion != 1) || (this.minorVersion != 0)) {
/* 311 */       attribs.put(implementation.getMajorVersionAttrib()).put(this.majorVersion);
/* 312 */       attribs.put(implementation.getMinorVersionAttrib()).put(this.minorVersion);
/*     */     }
/* 314 */     if (0 < this.layerPlane)
/* 315 */       attribs.put(implementation.getLayerPlaneAttrib()).put(this.layerPlane);
/* 316 */     if (0 < flags)
/* 317 */       attribs.put(implementation.getFlagsAttrib()).put(flags);
/* 318 */     if (0 < profileMask)
/* 319 */       attribs.put(implementation.getProfileMaskAttrib()).put(profileMask);
/* 320 */     if (this.loseContextOnReset) {
/* 321 */       attribs.put(33366).put(33362);
/*     */     }
/* 323 */     attribs.put(0);
/* 324 */     attribs.rewind();
/* 325 */     return attribs;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 329 */     StringBuilder sb = new StringBuilder(32);
/*     */ 
/* 331 */     sb.append("ContextAttribs:");
/* 332 */     sb.append(" Version=").append(this.majorVersion).append('.').append(this.minorVersion);
/* 333 */     sb.append(" - Layer=").append(this.layerPlane);
/* 334 */     sb.append(" - Debug=").append(this.debug);
/* 335 */     sb.append(" - ForwardCompatible=").append(this.forwardCompatible);
/* 336 */     sb.append(" - RobustAccess=").append(this.robustAccess);
/* 337 */     if (this.robustAccess)
/* 338 */       sb.append(" (").append(this.loseContextOnReset ? "LOSE_CONTEXT_ON_RESET" : "NO_RESET_NOTIFICATION");
/* 339 */     sb.append(" - Profile=");
/* 340 */     if (this.profileCore)
/* 341 */       sb.append("Core");
/* 342 */     else if (this.profileCompatibility)
/* 343 */       sb.append("Compatibility");
/*     */     else {
/* 345 */       sb.append("None");
/*     */     }
/* 347 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ContextAttribs
 * JD-Core Version:    0.6.2
 */