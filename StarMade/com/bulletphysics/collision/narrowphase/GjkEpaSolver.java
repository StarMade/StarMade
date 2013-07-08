/*   1:    */package com.bulletphysics.collision.narrowphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.shapes.ConvexShape;
/*   5:    */import com.bulletphysics.linearmath.MatrixUtil;
/*   6:    */import com.bulletphysics.linearmath.QuaternionUtil;
/*   7:    */import com.bulletphysics.linearmath.Transform;
/*   8:    */import com.bulletphysics.util.ArrayPool;
/*   9:    */import com.bulletphysics.util.ObjectStackList;
/*  10:    */import java.util.Arrays;
/*  11:    */import javax.vecmath.Matrix3f;
/*  12:    */import javax.vecmath.Quat4f;
/*  13:    */import javax.vecmath.Vector3f;
/*  14:    */
/*  50:    */public class GjkEpaSolver
/*  51:    */{
/*  52: 52 */  protected final ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
/*  53:    */  
/*  54: 54 */  protected final ObjectStackList<Mkv> stackMkv = new ObjectStackList(Mkv.class);
/*  55: 55 */  protected final ObjectStackList<He> stackHe = new ObjectStackList(He.class);
/*  56: 56 */  protected final ObjectStackList<Face> stackFace = new ObjectStackList(Face.class);
/*  57:    */  private static final float cstInf = 3.4028235E+38F;
/*  58:    */  
/*  59: 59 */  protected void pushStack() { this.stackMkv.push();
/*  60: 60 */    this.stackHe.push();
/*  61: 61 */    this.stackFace.push();
/*  62:    */  }
/*  63:    */  
/*  64:    */  protected void popStack() {
/*  65: 65 */    this.stackMkv.pop();
/*  66: 66 */    this.stackHe.pop();
/*  67: 67 */    this.stackFace.pop();
/*  68:    */  }
/*  69:    */  
/*  70:    */  public static enum ResultsStatus {
/*  71: 71 */    Separated, 
/*  72: 72 */    Penetrating, 
/*  73: 73 */    GJK_Failed, 
/*  74: 74 */    EPA_Failed;
/*  75:    */    
/*  76:    */    private ResultsStatus() {} }
/*  77:    */  
/*  78:    */  public static class Results { public GjkEpaSolver.ResultsStatus status;
/*  79: 79 */    public final Vector3f[] witnesses = { new Vector3f(), new Vector3f() };
/*  80: 80 */    public final Vector3f normal = new Vector3f();
/*  81:    */    
/*  82:    */    public float depth;
/*  83:    */    
/*  84:    */    public int epa_iterations;
/*  85:    */    
/*  86:    */    public int gjk_iterations;
/*  87:    */  }
/*  88:    */  
/*  90:    */  private static final float cstPi = 3.141593F;
/*  91:    */  
/*  92:    */  private static final float cst2Pi = 6.283186F;
/*  93:    */  private static final int GJK_maxiterations = 128;
/*  94:    */  private static final int GJK_hashsize = 64;
/*  95:    */  private static final int GJK_hashmask = 63;
/*  96:    */  private static final float GJK_insimplex_eps = 1.0E-004F;
/*  97:    */  private static final float GJK_sqinsimplex_eps = 9.999999E-009F;
/*  98:    */  private static final int EPA_maxiterations = 256;
/*  99:    */  private static final float EPA_inface_eps = 0.01F;
/* 100:    */  private static final float EPA_accuracy = 0.001F;
/* 101:    */  public static class Mkv
/* 102:    */  {
/* 103:103 */    public final Vector3f w = new Vector3f();
/* 104:104 */    public final Vector3f r = new Vector3f();
/* 105:    */    
/* 106:    */    public void set(Mkv m) {
/* 107:107 */      this.w.set(m.w);
/* 108:108 */      this.r.set(m.r);
/* 109:    */    }
/* 110:    */  }
/* 111:    */  
/* 112:    */  public static class He {
/* 113:113 */    public final Vector3f v = new Vector3f();
/* 114:    */    
/* 116:    */    public He n;
/* 117:    */  }
/* 118:    */  
/* 120:    */  protected class GJK
/* 121:    */  {
/* 122:122 */    public final GjkEpaSolver.He[] table = new GjkEpaSolver.He[64];
/* 123:123 */    public final Matrix3f[] wrotations = { new Matrix3f(), new Matrix3f() };
/* 124:124 */    public final Vector3f[] positions = { new Vector3f(), new Vector3f() };
/* 125:125 */    public final ConvexShape[] shapes = new ConvexShape[2];
/* 126:126 */    public final GjkEpaSolver.Mkv[] simplex = new GjkEpaSolver.Mkv[5];
/* 127:127 */    public final Vector3f ray = new Vector3f();
/* 128:    */    public int order;
/* 129:    */    public int iterations;
/* 130:    */    public float margin;
/* 131:    */    public boolean failed;
/* 132:    */    
/* 133:    */    public GJK() {
/* 134:134 */      for (int i = 0; i < this.simplex.length; i++) { this.simplex[i] = new GjkEpaSolver.Mkv();
/* 135:    */      }
/* 136:    */    }
/* 137:    */    
/* 141:    */    public GJK(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1)
/* 142:    */    {
/* 143:143 */      this(wrot0, pos0, shape0, wrot1, pos1, shape1, 0.0F);
/* 144:    */    }
/* 145:    */    
/* 146:    */    public GJK(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1, float pmargin)
/* 147:    */    {
/* 148:134 */      for (int i = 0; i < this.simplex.length; i++) { this.simplex[i] = new GjkEpaSolver.Mkv();
/* 149:    */      }
/* 150:    */      
/* 164:150 */      init(wrot0, pos0, shape0, wrot1, pos1, shape1, pmargin);
/* 165:    */    }
/* 166:    */    
/* 169:    */    public void init(Matrix3f wrot0, Vector3f pos0, ConvexShape shape0, Matrix3f wrot1, Vector3f pos1, ConvexShape shape1, float pmargin)
/* 170:    */    {
/* 171:157 */      GjkEpaSolver.this.pushStack();
/* 172:158 */      this.wrotations[0].set(wrot0);
/* 173:159 */      this.positions[0].set(pos0);
/* 174:160 */      this.shapes[0] = shape0;
/* 175:161 */      this.wrotations[1].set(wrot1);
/* 176:162 */      this.positions[1].set(pos1);
/* 177:163 */      this.shapes[1] = shape1;
/* 178:    */      
/* 180:166 */      this.margin = pmargin;
/* 181:167 */      this.failed = false;
/* 182:    */    }
/* 183:    */    
/* 184:    */    public void destroy() {
/* 185:171 */      GjkEpaSolver.this.popStack();
/* 186:    */    }
/* 187:    */    
/* 188:    */    public int Hash(Vector3f v)
/* 189:    */    {
/* 190:176 */      int h = (int)(v.x * 15461.0F) ^ (int)(v.y * 83003.0F) ^ (int)(v.z * 15473.0F);
/* 191:177 */      return h * 169639 & 0x3F;
/* 192:    */    }
/* 193:    */    
/* 194:    */    public Vector3f LocalSupport(Vector3f arg1, int arg2, Vector3f arg3) {
/* 195:181 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 196:182 */        MatrixUtil.transposeTransform(tmp, d, this.wrotations[i]);
/* 197:    */        
/* 198:184 */        this.shapes[i].localGetSupportingVertex(tmp, out);
/* 199:185 */        this.wrotations[i].transform(out);
/* 200:186 */        out.add(this.positions[i]);
/* 201:    */        
/* 202:188 */        return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 203:    */      }
/* 204:    */    }
/* 205:    */    
/* 206:192 */    public void Support(Vector3f arg1, GjkEpaSolver.Mkv arg2) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();v.r.set(d);
/* 207:    */        
/* 208:194 */        Vector3f tmp1 = LocalSupport(d, 0, localStack.get$javax$vecmath$Vector3f());
/* 209:    */        
/* 210:196 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 211:197 */        tmp.set(d);
/* 212:198 */        tmp.negate();
/* 213:199 */        Vector3f tmp2 = LocalSupport(tmp, 1, localStack.get$javax$vecmath$Vector3f());
/* 214:    */        
/* 215:201 */        v.w.sub(tmp1, tmp2);
/* 216:202 */        v.w.scaleAdd(this.margin, d, v.w);
/* 217:203 */      } finally { localStack.pop$javax$vecmath$Vector3f();
/* 218:    */      } }
/* 219:    */    
/* 220:206 */    public boolean FetchSupport() { int h = Hash(this.ray);
/* 221:207 */      GjkEpaSolver.He e = this.table[h];
/* 222:208 */      while (e != null) {
/* 223:209 */        if (e.v.equals(this.ray)) {
/* 224:210 */          this.order -= 1;
/* 225:211 */          return false;
/* 226:    */        }
/* 227:    */        
/* 228:214 */        e = e.n;
/* 229:    */      }
/* 230:    */      
/* 233:219 */      e = (GjkEpaSolver.He)GjkEpaSolver.this.stackHe.get();
/* 234:220 */      e.v.set(this.ray);
/* 235:221 */      e.n = this.table[h];
/* 236:222 */      this.table[h] = e;
/* 237:223 */      Support(this.ray, this.simplex[(++this.order)]);
/* 238:224 */      return this.ray.dot(this.simplex[this.order].w) > 0.0F;
/* 239:    */    }
/* 240:    */    
/* 241:    */    public boolean SolveSimplex2(Vector3f arg1, Vector3f arg2) {
/* 242:228 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (ab.dot(ao) >= 0.0F) {
/* 243:229 */          Vector3f cabo = localStack.get$javax$vecmath$Vector3f();
/* 244:230 */          cabo.cross(ab, ao);
/* 245:231 */          if (cabo.lengthSquared() > 9.999999E-009F) {
/* 246:232 */            this.ray.cross(cabo, ab);
/* 247:    */          }
/* 248:    */          else {
/* 249:235 */            return true;
/* 250:    */          }
/* 251:    */        }
/* 252:    */        else {
/* 253:239 */          this.order = 0;
/* 254:240 */          this.simplex[0].set(this.simplex[1]);
/* 255:241 */          this.ray.set(ao);
/* 256:    */        }
/* 257:243 */        return false; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 258:    */      }
/* 259:    */    }
/* 260:    */    
/* 261:    */    public boolean SolveSimplex3(Vector3f arg1, Vector3f arg2, Vector3f arg3) {
/* 262:248 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 263:249 */        tmp.cross(ab, ac);
/* 264:250 */        return SolveSimplex3a(ao, ab, ac, tmp); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 265:    */      }
/* 266:    */    }
/* 267:    */    
/* 268:    */    public boolean SolveSimplex3a(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
/* 269:    */    {
/* 270:256 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 271:257 */        tmp.cross(cabc, ab);
/* 272:    */        
/* 273:259 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 274:260 */        tmp2.cross(cabc, ac);
/* 275:    */        
/* 276:262 */        if (tmp.dot(ao) < -1.0E-004F) {
/* 277:263 */          this.order = 1;
/* 278:264 */          this.simplex[0].set(this.simplex[1]);
/* 279:265 */          this.simplex[1].set(this.simplex[2]);
/* 280:266 */          return SolveSimplex2(ao, ab);
/* 281:    */        }
/* 282:268 */        if (tmp2.dot(ao) > 1.0E-004F) {
/* 283:269 */          this.order = 1;
/* 284:270 */          this.simplex[1].set(this.simplex[2]);
/* 285:271 */          return SolveSimplex2(ao, ac);
/* 286:    */        }
/* 287:    */        
/* 288:274 */        float d = cabc.dot(ao);
/* 289:275 */        if (Math.abs(d) > 1.0E-004F) {
/* 290:276 */          if (d > 0.0F) {
/* 291:277 */            this.ray.set(cabc);
/* 292:    */          }
/* 293:    */          else {
/* 294:280 */            this.ray.negate(cabc);
/* 295:    */            
/* 296:282 */            GjkEpaSolver.Mkv swapTmp = new GjkEpaSolver.Mkv();
/* 297:283 */            swapTmp.set(this.simplex[0]);
/* 298:284 */            this.simplex[0].set(this.simplex[1]);
/* 299:285 */            this.simplex[1].set(swapTmp);
/* 300:    */          }
/* 301:287 */          return false;
/* 302:    */        }
/* 303:    */        
/* 304:290 */        return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 305:    */      }
/* 306:    */    }
/* 307:    */    
/* 310:    */    public boolean SolveSimplex4(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
/* 311:    */    {
/* 312:298 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f crs = localStack.get$javax$vecmath$Vector3f();
/* 313:    */        
/* 314:300 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 315:301 */        tmp.cross(ab, ac);
/* 316:    */        
/* 317:303 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 318:304 */        tmp2.cross(ac, ad);
/* 319:    */        
/* 320:306 */        Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 321:307 */        tmp3.cross(ad, ab);
/* 322:    */        
/* 323:309 */        if (tmp.dot(ao) > 1.0E-004F) {
/* 324:310 */          crs.set(tmp);
/* 325:311 */          this.order = 2;
/* 326:312 */          this.simplex[0].set(this.simplex[1]);
/* 327:313 */          this.simplex[1].set(this.simplex[2]);
/* 328:314 */          this.simplex[2].set(this.simplex[3]);
/* 329:315 */          return SolveSimplex3a(ao, ab, ac, crs);
/* 330:    */        }
/* 331:317 */        if (tmp2.dot(ao) > 1.0E-004F) {
/* 332:318 */          crs.set(tmp2);
/* 333:319 */          this.order = 2;
/* 334:320 */          this.simplex[2].set(this.simplex[3]);
/* 335:321 */          return SolveSimplex3a(ao, ac, ad, crs);
/* 336:    */        }
/* 337:323 */        if (tmp3.dot(ao) > 1.0E-004F) {
/* 338:324 */          crs.set(tmp3);
/* 339:325 */          this.order = 2;
/* 340:326 */          this.simplex[1].set(this.simplex[0]);
/* 341:327 */          this.simplex[0].set(this.simplex[2]);
/* 342:328 */          this.simplex[2].set(this.simplex[3]);
/* 343:329 */          return SolveSimplex3a(ao, ad, ab, crs);
/* 344:    */        }
/* 345:    */        
/* 346:332 */        return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 347:    */      }
/* 348:    */    }
/* 349:    */    
/* 350:    */    public boolean SearchOrigin() {
/* 351:337 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 352:338 */        tmp.set(1.0F, 0.0F, 0.0F);
/* 353:339 */        return SearchOrigin(tmp); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 354:    */      }
/* 355:    */    }
/* 356:    */    
/* 357:343 */    public boolean SearchOrigin(Vector3f arg1) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 358:344 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 359:345 */        Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 360:346 */        Vector3f tmp4 = localStack.get$javax$vecmath$Vector3f();
/* 361:    */        
/* 362:348 */        this.iterations = 0;
/* 363:349 */        this.order = -1;
/* 364:350 */        this.failed = false;
/* 365:351 */        this.ray.set(initray);
/* 366:352 */        this.ray.normalize();
/* 367:    */        
/* 368:354 */        Arrays.fill(this.table, null);
/* 369:    */        
/* 370:356 */        FetchSupport();
/* 371:357 */        this.ray.negate(this.simplex[0].w);
/* 372:358 */        for (; this.iterations < 128; this.iterations += 1) {
/* 373:359 */          float rl = this.ray.length();
/* 374:360 */          this.ray.scale(1.0F / (rl > 0.0F ? rl : 1.0F));
/* 375:361 */          if (FetchSupport()) {
/* 376:362 */            boolean found = false;
/* 377:363 */            switch (this.order) {
/* 378:    */            case 1: 
/* 379:365 */              tmp1.negate(this.simplex[1].w);
/* 380:366 */              tmp2.sub(this.simplex[0].w, this.simplex[1].w);
/* 381:367 */              found = SolveSimplex2(tmp1, tmp2);
/* 382:368 */              break;
/* 383:    */            
/* 384:    */            case 2: 
/* 385:371 */              tmp1.negate(this.simplex[2].w);
/* 386:372 */              tmp2.sub(this.simplex[1].w, this.simplex[2].w);
/* 387:373 */              tmp3.sub(this.simplex[0].w, this.simplex[2].w);
/* 388:374 */              found = SolveSimplex3(tmp1, tmp2, tmp3);
/* 389:375 */              break;
/* 390:    */            
/* 391:    */            case 3: 
/* 392:378 */              tmp1.negate(this.simplex[3].w);
/* 393:379 */              tmp2.sub(this.simplex[2].w, this.simplex[3].w);
/* 394:380 */              tmp3.sub(this.simplex[1].w, this.simplex[3].w);
/* 395:381 */              tmp4.sub(this.simplex[0].w, this.simplex[3].w);
/* 396:382 */              found = SolveSimplex4(tmp1, tmp2, tmp3, tmp4);
/* 397:    */            }
/* 398:    */            
/* 399:    */            
/* 400:386 */            if (found) {
/* 401:387 */              return true;
/* 402:    */            }
/* 403:    */          }
/* 404:    */          else {
/* 405:391 */            return false;
/* 406:    */          }
/* 407:    */        }
/* 408:394 */        this.failed = true;
/* 409:395 */        return false; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 410:    */      }
/* 411:    */    }
/* 412:    */    
/* 413:399 */    public boolean EncloseOrigin() { .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$javax$vecmath$Vector3f(); .Stack tmp11_7 = tmp7_5;tmp11_7.push$javax$vecmath$Quat4f();tmp11_7.push$javax$vecmath$Matrix3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 414:400 */        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 415:401 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 416:    */        
/* 417:403 */        switch (this.order)
/* 418:    */        {
/* 419:    */        case 0: 
/* 420:406 */          break;
/* 421:    */        
/* 422:    */        case 1: 
/* 423:409 */          Vector3f ab = localStack.get$javax$vecmath$Vector3f();
/* 424:410 */          ab.sub(this.simplex[1].w, this.simplex[0].w);
/* 425:    */          
/* 426:412 */          Vector3f[] b = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 427:413 */          b[0].set(1.0F, 0.0F, 0.0F);
/* 428:414 */          b[1].set(0.0F, 1.0F, 0.0F);
/* 429:415 */          b[2].set(0.0F, 0.0F, 1.0F);
/* 430:    */          
/* 431:417 */          b[0].cross(ab, b[0]);
/* 432:418 */          b[1].cross(ab, b[1]);
/* 433:419 */          b[2].cross(ab, b[2]);
/* 434:    */          
/* 435:421 */          float[] m = { b[0].lengthSquared(), b[1].lengthSquared(), b[2].lengthSquared() };
/* 436:    */          
/* 437:423 */          Quat4f tmpQuat = localStack.get$javax$vecmath$Quat4f();
/* 438:424 */          tmp.normalize(ab);
/* 439:425 */          QuaternionUtil.setRotation(tmpQuat, tmp, 2.094395F);
/* 440:    */          
/* 441:427 */          Matrix3f r = localStack.get$javax$vecmath$Matrix3f();
/* 442:428 */          MatrixUtil.setRotation(r, tmpQuat);
/* 443:    */          
/* 444:430 */          Vector3f w = localStack.get$javax$vecmath$Vector3f();
/* 445:431 */          w.set(b[2]);
/* 446:    */          
/* 447:433 */          tmp.normalize(w);
/* 448:434 */          Support(tmp, this.simplex[4]);r.transform(w);
/* 449:435 */          tmp.normalize(w);
/* 450:436 */          Support(tmp, this.simplex[2]);r.transform(w);
/* 451:437 */          tmp.normalize(w);
/* 452:438 */          Support(tmp, this.simplex[3]);r.transform(w);
/* 453:439 */          this.order = 4;
/* 454:440 */          return true;
/* 455:    */        
/* 457:    */        case 2: 
/* 458:444 */          tmp1.sub(this.simplex[1].w, this.simplex[0].w);
/* 459:445 */          tmp2.sub(this.simplex[2].w, this.simplex[0].w);
/* 460:446 */          Vector3f n = localStack.get$javax$vecmath$Vector3f();
/* 461:447 */          n.cross(tmp1, tmp2);
/* 462:448 */          n.normalize();
/* 463:    */          
/* 464:450 */          Support(n, this.simplex[3]);
/* 465:    */          
/* 466:452 */          tmp.negate(n);
/* 467:453 */          Support(tmp, this.simplex[4]);
/* 468:454 */          this.order = 4;
/* 469:455 */          return true;
/* 470:    */        
/* 472:    */        case 3: 
/* 473:459 */          return true;
/* 474:    */        
/* 475:    */        case 4: 
/* 476:462 */          return true;
/* 477:    */        }
/* 478:464 */        return false; } finally { .Stack tmp594_592 = localStack;tmp594_592.pop$javax$vecmath$Vector3f(); .Stack tmp598_594 = tmp594_592;tmp598_594.pop$javax$vecmath$Quat4f();tmp598_594.pop$javax$vecmath$Matrix3f();
/* 479:    */      }
/* 480:    */    }
/* 481:    */  }
/* 482:    */  
/* 485:471 */  private static int[] mod3 = { 0, 1, 2, 0, 1 };
/* 486:    */  
/* 487:473 */  private static final int[][] tetrahedron_fidx = { { 2, 1, 0 }, { 3, 0, 1 }, { 3, 1, 2 }, { 3, 2, 0 } };
/* 488:474 */  private static final int[][] tetrahedron_eidx = { { 0, 0, 2, 1 }, { 0, 1, 1, 1 }, { 0, 2, 3, 1 }, { 1, 0, 3, 2 }, { 2, 0, 1, 2 }, { 3, 0, 2, 2 } };
/* 489:    */  
/* 490:476 */  private static final int[][] hexahedron_fidx = { { 2, 0, 4 }, { 4, 1, 2 }, { 1, 4, 0 }, { 0, 3, 1 }, { 0, 2, 3 }, { 1, 3, 2 } };
/* 491:477 */  private static final int[][] hexahedron_eidx = { { 0, 0, 4, 0 }, { 0, 1, 2, 1 }, { 0, 2, 1, 2 }, { 1, 1, 5, 2 }, { 1, 0, 2, 0 }, { 2, 2, 3, 2 }, { 3, 1, 5, 0 }, { 3, 0, 4, 2 }, { 5, 1, 4, 1 } };
/* 492:    */  
/* 493:    */  public static class Face {
/* 494:480 */    public final GjkEpaSolver.Mkv[] v = new GjkEpaSolver.Mkv[3];
/* 495:481 */    public final Face[] f = new Face[3];
/* 496:482 */    public final int[] e = new int[3];
/* 497:483 */    public final Vector3f n = new Vector3f();
/* 498:    */    
/* 499:    */    public float d;
/* 500:    */    
/* 501:    */    public int mark;
/* 502:    */    public Face prev;
/* 503:    */    public Face next;
/* 504:    */  }
/* 505:    */  
/* 506:    */  protected class EPA
/* 507:    */  {
/* 508:    */    public GjkEpaSolver.GJK gjk;
/* 509:    */    public GjkEpaSolver.Face root;
/* 510:    */    public int nfaces;
/* 511:    */    public int iterations;
/* 512:498 */    public final Vector3f[][] features = new Vector3f[2][3];
/* 513:499 */    public final Vector3f[] nearest = { new Vector3f(), new Vector3f() };
/* 514:500 */    public final Vector3f normal = new Vector3f();
/* 515:    */    public float depth;
/* 516:    */    public boolean failed;
/* 517:    */    
/* 518:    */    public EPA(GjkEpaSolver.GJK pgjk) {
/* 519:505 */      for (int i = 0; i < this.features.length; i++) {
/* 520:506 */        for (int j = 0; j < this.features[i].length; j++) {
/* 521:507 */          this.features[i][j] = new Vector3f();
/* 522:    */        }
/* 523:    */      }
/* 524:    */      
/* 527:513 */      this.gjk = pgjk;
/* 528:    */    }
/* 529:    */    
/* 530:    */    public Vector3f GetCoordinates(GjkEpaSolver.Face arg1, Vector3f arg2)
/* 531:    */    {
/* 532:518 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 533:519 */        Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 534:520 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 535:    */        
/* 536:522 */        Vector3f o = localStack.get$javax$vecmath$Vector3f();
/* 537:523 */        o.scale(-face.d, face.n);
/* 538:    */        
/* 539:525 */        float[] a = (float[])GjkEpaSolver.this.floatArrays.getFixed(3);
/* 540:    */        
/* 541:527 */        tmp1.sub(face.v[0].w, o);
/* 542:528 */        tmp2.sub(face.v[1].w, o);
/* 543:529 */        tmp.cross(tmp1, tmp2);
/* 544:530 */        a[0] = tmp.length();
/* 545:    */        
/* 546:532 */        tmp1.sub(face.v[1].w, o);
/* 547:533 */        tmp2.sub(face.v[2].w, o);
/* 548:534 */        tmp.cross(tmp1, tmp2);
/* 549:535 */        a[1] = tmp.length();
/* 550:    */        
/* 551:537 */        tmp1.sub(face.v[2].w, o);
/* 552:538 */        tmp2.sub(face.v[0].w, o);
/* 553:539 */        tmp.cross(tmp1, tmp2);
/* 554:540 */        a[2] = tmp.length();
/* 555:    */        
/* 556:542 */        float sm = a[0] + a[1] + a[2];
/* 557:    */        
/* 558:544 */        out.set(a[1], a[2], a[0]);
/* 559:545 */        out.scale(1.0F / (sm > 0.0F ? sm : 1.0F));
/* 560:    */        
/* 561:547 */        GjkEpaSolver.this.floatArrays.release(a);
/* 562:    */        
/* 563:549 */        return out; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 564:    */      }
/* 565:    */    }
/* 566:    */    
/* 567:553 */    public GjkEpaSolver.Face FindBest() { GjkEpaSolver.Face bf = null;
/* 568:554 */      if (this.root != null) {
/* 569:555 */        GjkEpaSolver.Face cf = this.root;
/* 570:556 */        float bd = 3.4028235E+38F;
/* 571:    */        do {
/* 572:558 */          if (cf.d < bd) {
/* 573:559 */            bd = cf.d;
/* 574:560 */            bf = cf;
/* 575:    */          }
/* 576:    */          
/* 577:563 */        } while (null != (cf = cf.next));
/* 578:    */      }
/* 579:565 */      return bf;
/* 580:    */    }
/* 581:    */    
/* 582:    */    public boolean Set(GjkEpaSolver.Face arg1, GjkEpaSolver.Mkv arg2, GjkEpaSolver.Mkv arg3, GjkEpaSolver.Mkv arg4) {
/* 583:569 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 584:570 */        Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 585:571 */        Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 586:    */        
/* 587:573 */        Vector3f nrm = localStack.get$javax$vecmath$Vector3f();
/* 588:574 */        tmp1.sub(b.w, a.w);
/* 589:575 */        tmp2.sub(c.w, a.w);
/* 590:576 */        nrm.cross(tmp1, tmp2);
/* 591:    */        
/* 592:578 */        float len = nrm.length();
/* 593:    */        
/* 594:580 */        tmp1.cross(a.w, b.w);
/* 595:581 */        tmp2.cross(b.w, c.w);
/* 596:582 */        tmp3.cross(c.w, a.w);
/* 597:    */        
/* 598:584 */        boolean valid = (tmp1.dot(nrm) >= -0.01F) && (tmp2.dot(nrm) >= -0.01F) && (tmp3.dot(nrm) >= -0.01F);
/* 599:    */        
/* 602:588 */        f.v[0] = a;
/* 603:589 */        f.v[1] = b;
/* 604:590 */        f.v[2] = c;
/* 605:591 */        f.mark = 0;
/* 606:592 */        f.n.scale(1.0F / (len > 0.0F ? len : 3.4028235E+38F), nrm);
/* 607:593 */        f.d = Math.max(0.0F, -f.n.dot(a.w));
/* 608:594 */        return valid; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 609:    */      }
/* 610:    */    }
/* 611:    */    
/* 612:    */    public GjkEpaSolver.Face NewFace(GjkEpaSolver.Mkv a, GjkEpaSolver.Mkv b, GjkEpaSolver.Mkv c) {
/* 613:599 */      GjkEpaSolver.Face pf = (GjkEpaSolver.Face)GjkEpaSolver.this.stackFace.get();
/* 614:600 */      if (Set(pf, a, b, c)) {
/* 615:601 */        if (this.root != null) {
/* 616:602 */          this.root.prev = pf;
/* 617:    */        }
/* 618:604 */        pf.prev = null;
/* 619:605 */        pf.next = this.root;
/* 620:606 */        this.root = pf;
/* 621:607 */        this.nfaces += 1;
/* 622:    */      }
/* 623:    */      else {
/* 624:610 */        pf.prev = (pf.next = null);
/* 625:    */      }
/* 626:612 */      return pf;
/* 627:    */    }
/* 628:    */    
/* 629:    */    public void Detach(GjkEpaSolver.Face face) {
/* 630:616 */      if ((face.prev != null) || (face.next != null)) {
/* 631:617 */        this.nfaces -= 1;
/* 632:618 */        if (face == this.root) {
/* 633:619 */          this.root = face.next;
/* 634:620 */          this.root.prev = null;
/* 636:    */        }
/* 637:623 */        else if (face.next == null) {
/* 638:624 */          face.prev.next = null;
/* 639:    */        }
/* 640:    */        else {
/* 641:627 */          face.prev.next = face.next;
/* 642:628 */          face.next.prev = face.prev;
/* 643:    */        }
/* 644:    */        
/* 645:631 */        face.prev = (face.next = null);
/* 646:    */      }
/* 647:    */    }
/* 648:    */    
/* 649:    */    public void Link(GjkEpaSolver.Face f0, int e0, GjkEpaSolver.Face f1, int e1) {
/* 650:636 */      f0.f[e0] = f1;f1.e[e1] = e0;
/* 651:637 */      f1.f[e1] = f0;f0.e[e0] = e1;
/* 652:    */    }
/* 653:    */    
/* 654:    */    public GjkEpaSolver.Mkv Support(Vector3f w)
/* 655:    */    {
/* 656:642 */      GjkEpaSolver.Mkv v = (GjkEpaSolver.Mkv)GjkEpaSolver.this.stackMkv.get();
/* 657:643 */      this.gjk.Support(w, v);
/* 658:644 */      return v;
/* 659:    */    }
/* 660:    */    
/* 661:    */    public int BuildHorizon(int markid, GjkEpaSolver.Mkv w, GjkEpaSolver.Face f, int e, GjkEpaSolver.Face[] cf, GjkEpaSolver.Face[] ff) {
/* 662:648 */      int ne = 0;
/* 663:649 */      if (f.mark != markid) {
/* 664:650 */        int e1 = GjkEpaSolver.mod3[(e + 1)];
/* 665:651 */        if (f.n.dot(w.w) + f.d > 0.0F) {
/* 666:652 */          GjkEpaSolver.Face nf = NewFace(f.v[e1], f.v[e], w);
/* 667:653 */          Link(nf, 0, f, e);
/* 668:654 */          if (cf[0] != null) {
/* 669:655 */            Link(cf[0], 1, nf, 2);
/* 670:    */          }
/* 671:    */          else {
/* 672:658 */            ff[0] = nf;
/* 673:    */          }
/* 674:660 */          cf[0] = nf;
/* 675:661 */          ne = 1;
/* 676:    */        }
/* 677:    */        else {
/* 678:664 */          int e2 = GjkEpaSolver.mod3[(e + 2)];
/* 679:665 */          Detach(f);
/* 680:666 */          f.mark = markid;
/* 681:667 */          ne += BuildHorizon(markid, w, f.f[e1], f.e[e1], cf, ff);
/* 682:668 */          ne += BuildHorizon(markid, w, f.f[e2], f.e[e2], cf, ff);
/* 683:    */        }
/* 684:    */      }
/* 685:671 */      return ne;
/* 686:    */    }
/* 687:    */    
/* 688:    */    public float EvaluatePD() {
/* 689:675 */      return EvaluatePD(0.001F);
/* 690:    */    }
/* 691:    */    
/* 692:    */    /* Error */
/* 693:    */    public float EvaluatePD(float arg1)
/* 694:    */    {
/* 695:    */      // Byte code:
/* 696:    */      //   0: invokestatic 68	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/* 697:    */      //   3: astore 15
/* 698:    */      //   5: aload 15
/* 699:    */      //   7: invokevirtual 71	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/* 700:    */      //   10: aload_0
/* 701:    */      //   11: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/* 702:    */      //   14: invokevirtual 245	com/bulletphysics/collision/narrowphase/GjkEpaSolver:pushStack	()V
/* 703:    */      //   17: aload 15
/* 704:    */      //   19: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 705:    */      //   22: astore_2
/* 706:    */      //   23: aconst_null
/* 707:    */      //   24: astore_3
/* 708:    */      //   25: iconst_1
/* 709:    */      //   26: istore 4
/* 710:    */      //   28: aload_0
/* 711:    */      //   29: ldc 246
/* 712:    */      //   31: putfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/* 713:    */      //   34: aload_0
/* 714:    */      //   35: getfield 53	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:normal	Ljavax/vecmath/Vector3f;
/* 715:    */      //   38: fconst_0
/* 716:    */      //   39: fconst_0
/* 717:    */      //   40: fconst_0
/* 718:    */      //   41: invokevirtual 120	javax/vecmath/Vector3f:set	(FFF)V
/* 719:    */      //   44: aload_0
/* 720:    */      //   45: aconst_null
/* 721:    */      //   46: putfield 144	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:root	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/* 722:    */      //   49: aload_0
/* 723:    */      //   50: iconst_0
/* 724:    */      //   51: putfield 193	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nfaces	I
/* 725:    */      //   54: aload_0
/* 726:    */      //   55: iconst_0
/* 727:    */      //   56: putfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/* 728:    */      //   59: aload_0
/* 729:    */      //   60: iconst_0
/* 730:    */      //   61: putfield 252	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:failed	Z
/* 731:    */      //   64: aload_0
/* 732:    */      //   65: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/* 733:    */      //   68: invokevirtual 256	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:EncloseOrigin	()Z
/* 734:    */      //   71: ifeq +289 -> 360
/* 735:    */      //   74: aconst_null
/* 736:    */      //   75: checkcast 258	[[I
/* 737:    */      //   78: astore 5
/* 738:    */      //   80: iconst_0
/* 739:    */      //   81: istore 6
/* 740:    */      //   83: iconst_0
/* 741:    */      //   84: istore 7
/* 742:    */      //   86: aconst_null
/* 743:    */      //   87: checkcast 258	[[I
/* 744:    */      //   90: astore 8
/* 745:    */      //   92: iconst_0
/* 746:    */      //   93: istore 9
/* 747:    */      //   95: iconst_0
/* 748:    */      //   96: istore 10
/* 749:    */      //   98: iconst_5
/* 750:    */      //   99: anewarray 16	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv
/* 751:    */      //   102: astore 11
/* 752:    */      //   104: bipush 6
/* 753:    */      //   106: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
/* 754:    */      //   109: astore 12
/* 755:    */      //   111: aload_0
/* 756:    */      //   112: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/* 757:    */      //   115: getfield 261	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:order	I
/* 758:    */      //   118: lookupswitch	default:+76->194, 3:+26->144, 4:+52->170
/* 759:    */      //   145: aconst_null
/* 760:    */      //   146: lconst_0
/* 761:    */      //   147: astore 5
/* 762:    */      //   149: iconst_0
/* 763:    */      //   150: istore 6
/* 764:    */      //   152: iconst_4
/* 765:    */      //   153: istore 7
/* 766:    */      //   155: invokestatic 268	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$200	()[[I
/* 767:    */      //   158: astore 8
/* 768:    */      //   160: iconst_0
/* 769:    */      //   161: istore 9
/* 770:    */      //   163: bipush 6
/* 771:    */      //   165: istore 10
/* 772:    */      //   167: goto +27 -> 194
/* 773:    */      //   170: invokestatic 271	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$300	()[[I
/* 774:    */      //   173: astore 5
/* 775:    */      //   175: iconst_0
/* 776:    */      //   176: istore 6
/* 777:    */      //   178: bipush 6
/* 778:    */      //   180: istore 7
/* 779:    */      //   182: invokestatic 274	com/bulletphysics/collision/narrowphase/GjkEpaSolver:access$400	()[[I
/* 780:    */      //   185: astore 8
/* 781:    */      //   187: iconst_0
/* 782:    */      //   188: istore 9
/* 783:    */      //   190: bipush 9
/* 784:    */      //   192: istore 10
/* 785:    */      //   194: iconst_0
/* 786:    */      //   195: istore 13
/* 787:    */      //   197: iload 13
/* 788:    */      //   199: aload_0
/* 789:    */      //   200: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/* 790:    */      //   203: getfield 261	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:order	I
/* 791:    */      //   206: if_icmpgt +39 -> 245
/* 792:    */      //   209: aload 11
/* 793:    */      //   211: iload 13
/* 794:    */      //   213: new 16	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv
/* 795:    */      //   216: dup
/* 796:    */      //   217: invokespecial 275	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:<init>	()V
/* 797:    */      //   220: aastore
/* 798:    */      //   221: aload 11
/* 799:    */      //   223: iload 13
/* 800:    */      //   225: aaload
/* 801:    */      //   226: aload_0
/* 802:    */      //   227: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/* 803:    */      //   230: getfield 278	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:simplex	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
/* 804:    */      //   233: iload 13
/* 805:    */      //   235: aaload
/* 806:    */      //   236: invokevirtual 281	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:set	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;)V
/* 807:    */      //   239: iinc 13 1
/* 808:    */      //   242: goto -45 -> 197
/* 809:    */      //   245: iconst_0
/* 810:    */      //   246: istore 13
/* 811:    */      //   248: iload 13
/* 812:    */      //   250: iload 7
/* 813:    */      //   252: if_icmpge +51 -> 303
/* 814:    */      //   255: aload 12
/* 815:    */      //   257: iload 13
/* 816:    */      //   259: aload_0
/* 817:    */      //   260: aload 11
/* 818:    */      //   262: aload 5
/* 819:    */      //   264: iload 6
/* 820:    */      //   266: aaload
/* 821:    */      //   267: iconst_0
/* 822:    */      //   268: iaload
/* 823:    */      //   269: aaload
/* 824:    */      //   270: aload 11
/* 825:    */      //   272: aload 5
/* 826:    */      //   274: iload 6
/* 827:    */      //   276: aaload
/* 828:    */      //   277: iconst_1
/* 829:    */      //   278: iaload
/* 830:    */      //   279: aaload
/* 831:    */      //   280: aload 11
/* 832:    */      //   282: aload 5
/* 833:    */      //   284: iload 6
/* 834:    */      //   286: aaload
/* 835:    */      //   287: iconst_2
/* 836:    */      //   288: iaload
/* 837:    */      //   289: aaload
/* 838:    */      //   290: invokevirtual 225	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:NewFace	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;)Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/* 839:    */      //   293: aastore
/* 840:    */      //   294: iinc 13 1
/* 841:    */      //   297: iinc 6 1
/* 842:    */      //   300: goto -52 -> 248
/* 843:    */      //   303: iconst_0
/* 844:    */      //   304: istore 13
/* 845:    */      //   306: iload 13
/* 846:    */      //   308: iload 10
/* 847:    */      //   310: if_icmpge +50 -> 360
/* 848:    */      //   313: aload_0
/* 849:    */      //   314: aload 12
/* 850:    */      //   316: aload 8
/* 851:    */      //   318: iload 9
/* 852:    */      //   320: aaload
/* 853:    */      //   321: iconst_0
/* 854:    */      //   322: iaload
/* 855:    */      //   323: aaload
/* 856:    */      //   324: aload 8
/* 857:    */      //   326: iload 9
/* 858:    */      //   328: aaload
/* 859:    */      //   329: iconst_1
/* 860:    */      //   330: iaload
/* 861:    */      //   331: aload 12
/* 862:    */      //   333: aload 8
/* 863:    */      //   335: iload 9
/* 864:    */      //   337: aaload
/* 865:    */      //   338: iconst_2
/* 866:    */      //   339: iaload
/* 867:    */      //   340: aaload
/* 868:    */      //   341: aload 8
/* 869:    */      //   343: iload 9
/* 870:    */      //   345: aaload
/* 871:    */      //   346: iconst_3
/* 872:    */      //   347: iaload
/* 873:    */      //   348: invokevirtual 227	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Link	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I)V
/* 874:    */      //   351: iinc 13 1
/* 875:    */      //   354: iinc 9 1
/* 876:    */      //   357: goto -51 -> 306
/* 877:    */      //   360: iconst_0
/* 878:    */      //   361: aload_0
/* 879:    */      //   362: getfield 193	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nfaces	I
/* 880:    */      //   365: if_icmpne +24 -> 389
/* 881:    */      //   368: aload_0
/* 882:    */      //   369: getfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/* 883:    */      //   372: fstore 5
/* 884:    */      //   374: aload_0
/* 885:    */      //   375: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/* 886:    */      //   378: invokevirtual 284	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
/* 887:    */      //   381: fload 5
/* 888:    */      //   383: aload 15
/* 889:    */      //   385: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 890:    */      //   388: freturn
/* 891:    */      //   389: aload_0
/* 892:    */      //   390: getfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/* 893:    */      //   393: sipush 256
/* 894:    */      //   396: if_icmpge +185 -> 581
/* 895:    */      //   399: aload_0
/* 896:    */      //   400: invokevirtual 286	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:FindBest	()Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/* 897:    */      //   403: astore 5
/* 898:    */      //   405: aload 5
/* 899:    */      //   407: ifnull +174 -> 581
/* 900:    */      //   410: aload_2
/* 901:    */      //   411: aload 5
/* 902:    */      //   413: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:n	Ljavax/vecmath/Vector3f;
/* 903:    */      //   416: invokevirtual 290	javax/vecmath/Vector3f:negate	(Ljavax/vecmath/Tuple3f;)V
/* 904:    */      //   419: aload_0
/* 905:    */      //   420: aload_2
/* 906:    */      //   421: invokevirtual 292	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Support	(Ljavax/vecmath/Vector3f;)Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
/* 907:    */      //   424: astore 6
/* 908:    */      //   426: aload 5
/* 909:    */      //   428: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:n	Ljavax/vecmath/Vector3f;
/* 910:    */      //   431: aload 6
/* 911:    */      //   433: getfield 104	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:w	Ljavax/vecmath/Vector3f;
/* 912:    */      //   436: invokevirtual 157	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/* 913:    */      //   439: aload 5
/* 914:    */      //   441: getfield 78	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:d	F
/* 915:    */      //   444: fadd
/* 916:    */      //   445: fstore 7
/* 917:    */      //   447: aload 5
/* 918:    */      //   449: astore_3
/* 919:    */      //   450: fload 7
/* 920:    */      //   452: fload_1
/* 921:    */      //   453: fneg
/* 922:    */      //   454: fcmpg
/* 923:    */      //   455: ifge +126 -> 581
/* 924:    */      //   458: iconst_1
/* 925:    */      //   459: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
/* 926:    */      //   462: dup
/* 927:    */      //   463: iconst_0
/* 928:    */      //   464: aconst_null
/* 929:    */      //   465: aastore
/* 930:    */      //   466: astore 8
/* 931:    */      //   468: iconst_1
/* 932:    */      //   469: anewarray 12	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face
/* 933:    */      //   472: dup
/* 934:    */      //   473: iconst_0
/* 935:    */      //   474: aconst_null
/* 936:    */      //   475: aastore
/* 937:    */      //   476: astore 9
/* 938:    */      //   478: iconst_0
/* 939:    */      //   479: istore 10
/* 940:    */      //   481: aload_0
/* 941:    */      //   482: aload 5
/* 942:    */      //   484: invokevirtual 229	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Detach	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;)V
/* 943:    */      //   487: aload 5
/* 944:    */      //   489: iinc 4 1
/* 945:    */      //   492: iload 4
/* 946:    */      //   494: putfield 161	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:mark	I
/* 947:    */      //   497: iconst_0
/* 948:    */      //   498: istore 11
/* 949:    */      //   500: iload 11
/* 950:    */      //   502: iconst_3
/* 951:    */      //   503: if_icmpge +42 -> 545
/* 952:    */      //   506: iload 10
/* 953:    */      //   508: aload_0
/* 954:    */      //   509: iload 4
/* 955:    */      //   511: aload 6
/* 956:    */      //   513: aload 5
/* 957:    */      //   515: getfield 201	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:f	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;
/* 958:    */      //   518: iload 11
/* 959:    */      //   520: aaload
/* 960:    */      //   521: aload 5
/* 961:    */      //   523: getfield 205	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:e	[I
/* 962:    */      //   526: iload 11
/* 963:    */      //   528: iaload
/* 964:    */      //   529: aload 8
/* 965:    */      //   531: aload 9
/* 966:    */      //   533: invokevirtual 231	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:BuildHorizon	(ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;)I
/* 967:    */      //   536: iadd
/* 968:    */      //   537: istore 10
/* 969:    */      //   539: iinc 11 1
/* 970:    */      //   542: goto -42 -> 500
/* 971:    */      //   545: iload 10
/* 972:    */      //   547: iconst_2
/* 973:    */      //   548: if_icmpgt +6 -> 554
/* 974:    */      //   551: goto +30 -> 581
/* 975:    */      //   554: aload_0
/* 976:    */      //   555: aload 8
/* 977:    */      //   557: iconst_0
/* 978:    */      //   558: aaload
/* 979:    */      //   559: iconst_1
/* 980:    */      //   560: aload 9
/* 981:    */      //   562: iconst_0
/* 982:    */      //   563: aaload
/* 983:    */      //   564: iconst_2
/* 984:    */      //   565: invokevirtual 227	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:Link	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;ILcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;I)V
/* 985:    */      //   568: aload_0
/* 986:    */      //   569: dup
/* 987:    */      //   570: getfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/* 988:    */      //   573: iconst_1
/* 989:    */      //   574: iadd
/* 990:    */      //   575: putfield 250	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:iterations	I
/* 991:    */      //   578: goto -189 -> 389
/* 992:    */      //   581: aload_3
/* 993:    */      //   582: ifnull +281 -> 863
/* 994:    */      //   585: aload_0
/* 995:    */      //   586: aload_3
/* 996:    */      //   587: aload 15
/* 997:    */      //   589: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 998:    */      //   592: invokevirtual 294	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:GetCoordinates	(Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Face;Ljavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/* 999:    */      //   595: astore 5
/* 1000:    */      //   597: aload_0
/* 1001:    */      //   598: getfield 53	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:normal	Ljavax/vecmath/Vector3f;
/* 1002:    */      //   601: aload_3
/* 1003:    */      //   602: getfield 81	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:n	Ljavax/vecmath/Vector3f;
/* 1004:    */      //   605: invokevirtual 296	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/* 1005:    */      //   608: aload_0
/* 1006:    */      //   609: fconst_0
/* 1007:    */      //   610: aload_3
/* 1008:    */      //   611: getfield 78	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:d	F
/* 1009:    */      //   614: invokestatic 167	java/lang/Math:max	(FF)F
/* 1010:    */      //   617: putfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/* 1011:    */      //   620: iconst_0
/* 1012:    */      //   621: istore 6
/* 1013:    */      //   623: iload 6
/* 1014:    */      //   625: iconst_2
/* 1015:    */      //   626: if_icmpge +75 -> 701
/* 1016:    */      //   629: iload 6
/* 1017:    */      //   631: ifeq +9 -> 640
/* 1018:    */      //   634: ldc_w 297
/* 1019:    */      //   637: goto +4 -> 641
/* 1020:    */      //   640: fconst_1
/* 1021:    */      //   641: fstore 7
/* 1022:    */      //   643: iconst_0
/* 1023:    */      //   644: istore 8
/* 1024:    */      //   646: iload 8
/* 1025:    */      //   648: iconst_3
/* 1026:    */      //   649: if_icmpge +46 -> 695
/* 1027:    */      //   652: aload_2
/* 1028:    */      //   653: fload 7
/* 1029:    */      //   655: aload_3
/* 1030:    */      //   656: getfield 101	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Face:v	[Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv;
/* 1031:    */      //   659: iload 8
/* 1032:    */      //   661: aaload
/* 1033:    */      //   662: getfield 300	com/bulletphysics/collision/narrowphase/GjkEpaSolver$Mkv:r	Ljavax/vecmath/Vector3f;
/* 1034:    */      //   665: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1035:    */      //   668: aload_0
/* 1036:    */      //   669: getfield 55	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:gjk	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK;
/* 1037:    */      //   672: aload_2
/* 1038:    */      //   673: iload 6
/* 1039:    */      //   675: aload_0
/* 1040:    */      //   676: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1041:    */      //   679: iload 6
/* 1042:    */      //   681: aaload
/* 1043:    */      //   682: iload 8
/* 1044:    */      //   684: aaload
/* 1045:    */      //   685: invokevirtual 304	com/bulletphysics/collision/narrowphase/GjkEpaSolver$GJK:LocalSupport	(Ljavax/vecmath/Vector3f;ILjavax/vecmath/Vector3f;)Ljavax/vecmath/Vector3f;
/* 1046:    */      //   688: pop
/* 1047:    */      //   689: iinc 8 1
/* 1048:    */      //   692: goto -46 -> 646
/* 1049:    */      //   695: iinc 6 1
/* 1050:    */      //   698: goto -75 -> 623
/* 1051:    */      //   701: aload 15
/* 1052:    */      //   703: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 1053:    */      //   706: astore 6
/* 1054:    */      //   708: aload 15
/* 1055:    */      //   710: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 1056:    */      //   713: astore 7
/* 1057:    */      //   715: aload 15
/* 1058:    */      //   717: invokevirtual 75	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/* 1059:    */      //   720: astore 8
/* 1060:    */      //   722: aload 6
/* 1061:    */      //   724: aload 5
/* 1062:    */      //   726: getfield 307	javax/vecmath/Vector3f:x	F
/* 1063:    */      //   729: aload_0
/* 1064:    */      //   730: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1065:    */      //   733: iconst_0
/* 1066:    */      //   734: aaload
/* 1067:    */      //   735: iconst_0
/* 1068:    */      //   736: aaload
/* 1069:    */      //   737: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1070:    */      //   740: aload 7
/* 1071:    */      //   742: aload 5
/* 1072:    */      //   744: getfield 310	javax/vecmath/Vector3f:y	F
/* 1073:    */      //   747: aload_0
/* 1074:    */      //   748: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1075:    */      //   751: iconst_0
/* 1076:    */      //   752: aaload
/* 1077:    */      //   753: iconst_1
/* 1078:    */      //   754: aaload
/* 1079:    */      //   755: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1080:    */      //   758: aload 8
/* 1081:    */      //   760: aload 5
/* 1082:    */      //   762: getfield 313	javax/vecmath/Vector3f:z	F
/* 1083:    */      //   765: aload_0
/* 1084:    */      //   766: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1085:    */      //   769: iconst_0
/* 1086:    */      //   770: aaload
/* 1087:    */      //   771: iconst_2
/* 1088:    */      //   772: aaload
/* 1089:    */      //   773: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1090:    */      //   776: aload_0
/* 1091:    */      //   777: getfield 51	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nearest	[Ljavax/vecmath/Vector3f;
/* 1092:    */      //   780: iconst_0
/* 1093:    */      //   781: aaload
/* 1094:    */      //   782: aload 6
/* 1095:    */      //   784: aload 7
/* 1096:    */      //   786: aload 8
/* 1097:    */      //   788: invokestatic 319	com/bulletphysics/linearmath/VectorUtil:add	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/* 1098:    */      //   791: aload 6
/* 1099:    */      //   793: aload 5
/* 1100:    */      //   795: getfield 307	javax/vecmath/Vector3f:x	F
/* 1101:    */      //   798: aload_0
/* 1102:    */      //   799: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1103:    */      //   802: iconst_1
/* 1104:    */      //   803: aaload
/* 1105:    */      //   804: iconst_0
/* 1106:    */      //   805: aaload
/* 1107:    */      //   806: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1108:    */      //   809: aload 7
/* 1109:    */      //   811: aload 5
/* 1110:    */      //   813: getfield 310	javax/vecmath/Vector3f:y	F
/* 1111:    */      //   816: aload_0
/* 1112:    */      //   817: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1113:    */      //   820: iconst_1
/* 1114:    */      //   821: aaload
/* 1115:    */      //   822: iconst_1
/* 1116:    */      //   823: aaload
/* 1117:    */      //   824: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1118:    */      //   827: aload 8
/* 1119:    */      //   829: aload 5
/* 1120:    */      //   831: getfield 313	javax/vecmath/Vector3f:z	F
/* 1121:    */      //   834: aload_0
/* 1122:    */      //   835: getfield 46	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:features	[[Ljavax/vecmath/Vector3f;
/* 1123:    */      //   838: iconst_1
/* 1124:    */      //   839: aaload
/* 1125:    */      //   840: iconst_2
/* 1126:    */      //   841: aaload
/* 1127:    */      //   842: invokevirtual 85	javax/vecmath/Vector3f:scale	(FLjavax/vecmath/Tuple3f;)V
/* 1128:    */      //   845: aload_0
/* 1129:    */      //   846: getfield 51	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:nearest	[Ljavax/vecmath/Vector3f;
/* 1130:    */      //   849: iconst_1
/* 1131:    */      //   850: aaload
/* 1132:    */      //   851: aload 6
/* 1133:    */      //   853: aload 7
/* 1134:    */      //   855: aload 8
/* 1135:    */      //   857: invokestatic 319	com/bulletphysics/linearmath/VectorUtil:add	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)V
/* 1136:    */      //   860: goto +8 -> 868
/* 1137:    */      //   863: aload_0
/* 1138:    */      //   864: iconst_1
/* 1139:    */      //   865: putfield 252	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:failed	Z
/* 1140:    */      //   868: aload_0
/* 1141:    */      //   869: getfield 248	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:depth	F
/* 1142:    */      //   872: fstore 5
/* 1143:    */      //   874: aload_0
/* 1144:    */      //   875: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/* 1145:    */      //   878: invokevirtual 284	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
/* 1146:    */      //   881: fload 5
/* 1147:    */      //   883: aload 15
/* 1148:    */      //   885: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 1149:    */      //   888: freturn
/* 1150:    */      //   889: astore 14
/* 1151:    */      //   891: aload_0
/* 1152:    */      //   892: getfield 40	com/bulletphysics/collision/narrowphase/GjkEpaSolver$EPA:this$0	Lcom/bulletphysics/collision/narrowphase/GjkEpaSolver;
/* 1153:    */      //   895: invokevirtual 284	com/bulletphysics/collision/narrowphase/GjkEpaSolver:popStack	()V
/* 1154:    */      //   898: aload 14
/* 1155:    */      //   900: athrow
/* 1156:    */      //   901: aload 15
/* 1157:    */      //   903: invokevirtual 130	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/* 1158:    */      //   906: athrow
/* 1159:    */      // Line number table:
/* 1160:    */      //   Java source line #679	-> byte code offset #10
/* 1161:    */      //   Java source line #681	-> byte code offset #17
/* 1162:    */      //   Java source line #684	-> byte code offset #23
/* 1163:    */      //   Java source line #685	-> byte code offset #25
/* 1164:    */      //   Java source line #686	-> byte code offset #28
/* 1165:    */      //   Java source line #687	-> byte code offset #34
/* 1166:    */      //   Java source line #688	-> byte code offset #44
/* 1167:    */      //   Java source line #689	-> byte code offset #49
/* 1168:    */      //   Java source line #690	-> byte code offset #54
/* 1169:    */      //   Java source line #691	-> byte code offset #59
/* 1170:    */      //   Java source line #693	-> byte code offset #64
/* 1171:    */      //   Java source line #695	-> byte code offset #74
/* 1172:    */      //   Java source line #696	-> byte code offset #80
/* 1173:    */      //   Java source line #698	-> byte code offset #83
/* 1174:    */      //   Java source line #700	-> byte code offset #86
/* 1175:    */      //   Java source line #701	-> byte code offset #92
/* 1176:    */      //   Java source line #703	-> byte code offset #95
/* 1177:    */      //   Java source line #704	-> byte code offset #98
/* 1178:    */      //   Java source line #705	-> byte code offset #104
/* 1179:    */      //   Java source line #706	-> byte code offset #111
/* 1180:    */      //   Java source line #711	-> byte code offset #144
/* 1181:    */      //   Java source line #712	-> byte code offset #149
/* 1182:    */      //   Java source line #714	-> byte code offset #152
/* 1183:    */      //   Java source line #717	-> byte code offset #155
/* 1184:    */      //   Java source line #718	-> byte code offset #160
/* 1185:    */      //   Java source line #720	-> byte code offset #163
/* 1186:    */      //   Java source line #722	-> byte code offset #167
/* 1187:    */      //   Java source line #727	-> byte code offset #170
/* 1188:    */      //   Java source line #728	-> byte code offset #175
/* 1189:    */      //   Java source line #730	-> byte code offset #178
/* 1190:    */      //   Java source line #733	-> byte code offset #182
/* 1191:    */      //   Java source line #734	-> byte code offset #187
/* 1192:    */      //   Java source line #736	-> byte code offset #190
/* 1193:    */      //   Java source line #742	-> byte code offset #194
/* 1194:    */      //   Java source line #743	-> byte code offset #209
/* 1195:    */      //   Java source line #744	-> byte code offset #221
/* 1196:    */      //   Java source line #742	-> byte code offset #239
/* 1197:    */      //   Java source line #746	-> byte code offset #245
/* 1198:    */      //   Java source line #747	-> byte code offset #255
/* 1199:    */      //   Java source line #746	-> byte code offset #294
/* 1200:    */      //   Java source line #749	-> byte code offset #303
/* 1201:    */      //   Java source line #750	-> byte code offset #313
/* 1202:    */      //   Java source line #749	-> byte code offset #351
/* 1203:    */      //   Java source line #753	-> byte code offset #360
/* 1204:    */      //   Java source line #755	-> byte code offset #368
/* 1205:    */      //   Java source line #821	-> byte code offset #374
/* 1206:    */      //   Java source line #758	-> byte code offset #389
/* 1207:    */      //   Java source line #759	-> byte code offset #399
/* 1208:    */      //   Java source line #760	-> byte code offset #405
/* 1209:    */      //   Java source line #761	-> byte code offset #410
/* 1210:    */      //   Java source line #762	-> byte code offset #419
/* 1211:    */      //   Java source line #763	-> byte code offset #426
/* 1212:    */      //   Java source line #764	-> byte code offset #447
/* 1213:    */      //   Java source line #765	-> byte code offset #450
/* 1214:    */      //   Java source line #766	-> byte code offset #458
/* 1215:    */      //   Java source line #767	-> byte code offset #468
/* 1216:    */      //   Java source line #768	-> byte code offset #478
/* 1217:    */      //   Java source line #769	-> byte code offset #481
/* 1218:    */      //   Java source line #770	-> byte code offset #487
/* 1219:    */      //   Java source line #771	-> byte code offset #497
/* 1220:    */      //   Java source line #772	-> byte code offset #506
/* 1221:    */      //   Java source line #771	-> byte code offset #539
/* 1222:    */      //   Java source line #774	-> byte code offset #545
/* 1223:    */      //   Java source line #775	-> byte code offset #551
/* 1224:    */      //   Java source line #777	-> byte code offset #554
/* 1225:    */      //   Java source line #758	-> byte code offset #568
/* 1226:    */      //   Java source line #788	-> byte code offset #581
/* 1227:    */      //   Java source line #789	-> byte code offset #585
/* 1228:    */      //   Java source line #790	-> byte code offset #597
/* 1229:    */      //   Java source line #791	-> byte code offset #608
/* 1230:    */      //   Java source line #792	-> byte code offset #620
/* 1231:    */      //   Java source line #793	-> byte code offset #629
/* 1232:    */      //   Java source line #794	-> byte code offset #643
/* 1233:    */      //   Java source line #795	-> byte code offset #652
/* 1234:    */      //   Java source line #796	-> byte code offset #668
/* 1235:    */      //   Java source line #794	-> byte code offset #689
/* 1236:    */      //   Java source line #792	-> byte code offset #695
/* 1237:    */      //   Java source line #800	-> byte code offset #701
/* 1238:    */      //   Java source line #801	-> byte code offset #708
/* 1239:    */      //   Java source line #802	-> byte code offset #715
/* 1240:    */      //   Java source line #804	-> byte code offset #722
/* 1241:    */      //   Java source line #805	-> byte code offset #740
/* 1242:    */      //   Java source line #806	-> byte code offset #758
/* 1243:    */      //   Java source line #807	-> byte code offset #776
/* 1244:    */      //   Java source line #809	-> byte code offset #791
/* 1245:    */      //   Java source line #810	-> byte code offset #809
/* 1246:    */      //   Java source line #811	-> byte code offset #827
/* 1247:    */      //   Java source line #812	-> byte code offset #845
/* 1248:    */      //   Java source line #813	-> byte code offset #860
/* 1249:    */      //   Java source line #815	-> byte code offset #863
/* 1250:    */      //   Java source line #818	-> byte code offset #868
/* 1251:    */      //   Java source line #821	-> byte code offset #874
/* 1252:    */      // Local variable table:
/* 1253:    */      //   start	length	slot	name	signature
/* 1254:    */      //   10	891	0	this	EPA
/* 1255:    */      //   10	891	1	accuracy	float
/* 1256:    */      //   22	651	2	tmp	Vector3f
/* 1257:    */      //   24	632	3	bestface	GjkEpaSolver.Face
/* 1258:    */      //   26	484	4	markid	int
/* 1259:    */      //   78	304	5	pfidx_ptr	int[][]
/* 1260:    */      //   403	119	5	bf	GjkEpaSolver.Face
/* 1261:    */      //   595	287	5	b	Vector3f
/* 1262:    */      //   81	217	6	pfidx_index	int
/* 1263:    */      //   424	88	6	w	GjkEpaSolver.Mkv
/* 1264:    */      //   621	75	6	i	int
/* 1265:    */      //   706	146	6	tmp1	Vector3f
/* 1266:    */      //   84	167	7	nfidx	int
/* 1267:    */      //   445	6	7	d	float
/* 1268:    */      //   641	13	7	s	float
/* 1269:    */      //   713	141	7	tmp2	Vector3f
/* 1270:    */      //   90	252	8	peidx_ptr	int[][]
/* 1271:    */      //   466	90	8	cf	GjkEpaSolver.Face[]
/* 1272:    */      //   644	46	8	j	int
/* 1273:    */      //   720	136	8	tmp3	Vector3f
/* 1274:    */      //   93	262	9	peidx_index	int
/* 1275:    */      //   476	85	9	ff	GjkEpaSolver.Face[]
/* 1276:    */      //   96	213	10	neidx	int
/* 1277:    */      //   479	67	10	nf	int
/* 1278:    */      //   102	179	11	basemkv	GjkEpaSolver.Mkv[]
/* 1279:    */      //   498	42	11	i	int
/* 1280:    */      //   109	223	12	basefaces	GjkEpaSolver.Face[]
/* 1281:    */      //   195	157	13	i	int
/* 1282:    */      //   889	10	14	localObject	Object
/* 1283:    */      //   3	899	15	localStack	.Stack
/* 1284:    */      // Exception table:
/* 1285:    */      //   from	to	target	type
/* 1286:    */      //   17	374	889	finally
/* 1287:    */      //   389	874	889	finally
/* 1288:    */      //   889	891	889	finally
/* 1289:    */      //   5	901	901	finally
/* 1290:    */    }
/* 1291:    */  }
/* 1292:    */  
/* 1293:829 */  private GJK gjk = new GJK();
/* 1294:    */  
/* 1300:    */  public boolean collide(ConvexShape shape0, Transform wtrs0, ConvexShape shape1, Transform wtrs1, float radialmargin, Results results)
/* 1301:    */  {
/* 1302:838 */    results.witnesses[0].set(0.0F, 0.0F, 0.0F);
/* 1303:839 */    results.witnesses[1].set(0.0F, 0.0F, 0.0F);
/* 1304:840 */    results.normal.set(0.0F, 0.0F, 0.0F);
/* 1305:841 */    results.depth = 0.0F;
/* 1306:842 */    results.status = ResultsStatus.Separated;
/* 1307:843 */    results.epa_iterations = 0;
/* 1308:844 */    results.gjk_iterations = 0;
/* 1309:    */    
/* 1310:846 */    this.gjk.init(wtrs0.basis, wtrs0.origin, shape0, wtrs1.basis, wtrs1.origin, shape1, radialmargin + 0.001F);
/* 1311:    */    
/* 1313:    */    try
/* 1314:    */    {
/* 1315:851 */      boolean collide = this.gjk.SearchOrigin();
/* 1316:852 */      results.gjk_iterations = (this.gjk.iterations + 1);
/* 1317:853 */      EPA epa; if (collide)
/* 1318:    */      {
/* 1319:855 */        epa = new EPA(this.gjk);
/* 1320:856 */        float pd = epa.EvaluatePD();
/* 1321:857 */        results.epa_iterations = (epa.iterations + 1);
/* 1322:858 */        if (pd > 0.0F) {
/* 1323:859 */          results.status = ResultsStatus.Penetrating;
/* 1324:860 */          results.normal.set(epa.normal);
/* 1325:861 */          results.depth = pd;
/* 1326:862 */          results.witnesses[0].set(epa.nearest[0]);
/* 1327:863 */          results.witnesses[1].set(epa.nearest[1]);
/* 1328:864 */          return true;
/* 1329:    */        }
/* 1330:    */        
/* 1331:867 */        if (epa.failed) {
/* 1332:868 */          results.status = ResultsStatus.EPA_Failed;
/* 1333:    */        }
/* 1334:    */        
/* 1336:    */      }
/* 1337:873 */      else if (this.gjk.failed) {
/* 1338:874 */        results.status = ResultsStatus.GJK_Failed;
/* 1339:    */      }
/* 1340:    */      
/* 1341:877 */      return 0;
/* 1342:    */    }
/* 1343:    */    finally {
/* 1344:880 */      this.gjk.destroy();
/* 1345:    */    }
/* 1346:    */  }
/* 1347:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkEpaSolver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */