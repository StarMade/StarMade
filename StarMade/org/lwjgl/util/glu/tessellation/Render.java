/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/*  48:    */class Render
/*  49:    */{
/*  50:    */  private static final boolean USE_OPTIMIZED_CODE_PATH = false;
/*  51:    */  
/*  96: 96 */  private static final RenderFan renderFan = new RenderFan(null);
/*  97: 97 */  private static final RenderStrip renderStrip = new RenderStrip(null);
/*  98: 98 */  private static final RenderTriangle renderTriangle = new RenderTriangle(null);
/*  99:    */  private static final int SIGN_INCONSISTENT = 2;
/* 100:    */  
/* 101:    */  private static class FaceCount {
/* 102:    */    long size;
/* 103:    */    GLUhalfEdge eStart;
/* 104:    */    Render.renderCallBack render;
/* 105:    */    
/* 106:    */    private FaceCount() {}
/* 107:    */    
/* 108:    */    private FaceCount(long size, GLUhalfEdge eStart, Render.renderCallBack render) {
/* 109:109 */      this.size = size;
/* 110:110 */      this.eStart = eStart;
/* 111:111 */      this.render = render;
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 134:    */  public static void __gl_renderMesh(GLUtessellatorImpl tess, GLUmesh mesh)
/* 135:    */  {
/* 136:136 */    tess.lonelyTriList = null;
/* 137:    */    
/* 138:138 */    for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = f.next) {
/* 139:139 */      f.marked = false;
/* 140:    */    }
/* 141:141 */    for (f = mesh.fHead.next; f != mesh.fHead; f = f.next)
/* 142:    */    {
/* 147:147 */      if ((f.inside) && (!f.marked)) {
/* 148:148 */        RenderMaximumFaceGroup(tess, f);
/* 149:149 */        assert (f.marked);
/* 150:    */      }
/* 151:    */    }
/* 152:152 */    if (tess.lonelyTriList != null) {
/* 153:153 */      RenderLonelyTriangles(tess, tess.lonelyTriList);
/* 154:154 */      tess.lonelyTriList = null;
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 165:    */  static void RenderMaximumFaceGroup(GLUtessellatorImpl tess, GLUface fOrig)
/* 166:    */  {
/* 167:167 */    GLUhalfEdge e = fOrig.anEdge;
/* 168:168 */    FaceCount max = new FaceCount(null);
/* 169:    */    
/* 171:171 */    max.size = 1L;
/* 172:172 */    max.eStart = e;
/* 173:173 */    max.render = renderTriangle;
/* 174:    */    
/* 175:175 */    if (!tess.flagBoundary) {
/* 176:176 */      FaceCount newFace = MaximumFan(e);
/* 177:177 */      if (newFace.size > max.size) {
/* 178:178 */        max = newFace;
/* 179:    */      }
/* 180:180 */      newFace = MaximumFan(e.Lnext);
/* 181:181 */      if (newFace.size > max.size) {
/* 182:182 */        max = newFace;
/* 183:    */      }
/* 184:184 */      newFace = MaximumFan(e.Onext.Sym);
/* 185:185 */      if (newFace.size > max.size) {
/* 186:186 */        max = newFace;
/* 187:    */      }
/* 188:    */      
/* 189:189 */      newFace = MaximumStrip(e);
/* 190:190 */      if (newFace.size > max.size) {
/* 191:191 */        max = newFace;
/* 192:    */      }
/* 193:193 */      newFace = MaximumStrip(e.Lnext);
/* 194:194 */      if (newFace.size > max.size) {
/* 195:195 */        max = newFace;
/* 196:    */      }
/* 197:197 */      newFace = MaximumStrip(e.Onext.Sym);
/* 198:198 */      if (newFace.size > max.size) {
/* 199:199 */        max = newFace;
/* 200:    */      }
/* 201:    */    }
/* 202:202 */    max.render.render(tess, max.eStart, max.size);
/* 203:    */  }
/* 204:    */  
/* 212:    */  private static boolean Marked(GLUface f)
/* 213:    */  {
/* 214:214 */    return (!f.inside) || (f.marked);
/* 215:    */  }
/* 216:    */  
/* 217:    */  private static GLUface AddToTrail(GLUface f, GLUface t) {
/* 218:218 */    f.trail = t;
/* 219:219 */    f.marked = true;
/* 220:220 */    return f;
/* 221:    */  }
/* 222:    */  
/* 223:    */  private static void FreeTrail(GLUface t)
/* 224:    */  {
/* 225:225 */    while (t != null) {
/* 226:226 */      t.marked = false;
/* 227:227 */      t = t.trail;
/* 228:    */    }
/* 229:    */  }
/* 230:    */  
/* 237:    */  static FaceCount MaximumFan(GLUhalfEdge eOrig)
/* 238:    */  {
/* 239:239 */    FaceCount newFace = new FaceCount(0L, null, renderFan, null);
/* 240:240 */    GLUface trail = null;
/* 241:    */    
/* 243:243 */    for (GLUhalfEdge e = eOrig; !Marked(e.Lface); e = e.Onext) {
/* 244:244 */      trail = AddToTrail(e.Lface, trail);
/* 245:245 */      newFace.size += 1L;
/* 246:    */    }
/* 247:247 */    for (e = eOrig; !Marked(e.Sym.Lface); e = e.Sym.Lnext) {
/* 248:248 */      trail = AddToTrail(e.Sym.Lface, trail);
/* 249:249 */      newFace.size += 1L;
/* 250:    */    }
/* 251:251 */    newFace.eStart = e;
/* 252:    */    
/* 253:253 */    FreeTrail(trail);
/* 254:254 */    return newFace;
/* 255:    */  }
/* 256:    */  
/* 257:    */  private static boolean IsEven(long n)
/* 258:    */  {
/* 259:259 */    return (n & 1L) == 0L;
/* 260:    */  }
/* 261:    */  
/* 271:    */  static FaceCount MaximumStrip(GLUhalfEdge eOrig)
/* 272:    */  {
/* 273:273 */    FaceCount newFace = new FaceCount(0L, null, renderStrip, null);
/* 274:274 */    long headSize = 0L;long tailSize = 0L;
/* 275:275 */    GLUface trail = null;
/* 276:    */    
/* 278:278 */    for (GLUhalfEdge e = eOrig; !Marked(e.Lface); e = e.Onext) {
/* 279:279 */      trail = AddToTrail(e.Lface, trail);
/* 280:280 */      tailSize += 1L;
/* 281:281 */      e = e.Lnext.Sym;
/* 282:282 */      if (Marked(e.Lface)) break;
/* 283:283 */      trail = AddToTrail(e.Lface, trail);tailSize += 1L;
/* 284:    */    }
/* 285:285 */    GLUhalfEdge eTail = e;
/* 286:    */    
/* 287:287 */    for (e = eOrig; !Marked(e.Sym.Lface); e = e.Sym.Onext.Sym) {
/* 288:288 */      trail = AddToTrail(e.Sym.Lface, trail);
/* 289:289 */      headSize += 1L;
/* 290:290 */      e = e.Sym.Lnext;
/* 291:291 */      if (Marked(e.Sym.Lface)) break;
/* 292:292 */      trail = AddToTrail(e.Sym.Lface, trail);headSize += 1L;
/* 293:    */    }
/* 294:294 */    GLUhalfEdge eHead = e;
/* 295:    */    
/* 296:296 */    newFace.size = (tailSize + headSize);
/* 297:297 */    if (IsEven(tailSize)) {
/* 298:298 */      newFace.eStart = eTail.Sym;
/* 299:299 */    } else if (IsEven(headSize)) {
/* 300:300 */      newFace.eStart = eHead;
/* 302:    */    }
/* 303:    */    else
/* 304:    */    {
/* 305:305 */      newFace.size -= 1L;
/* 306:306 */      newFace.eStart = eHead.Onext;
/* 307:    */    }
/* 308:    */    
/* 309:309 */    FreeTrail(trail);
/* 310:310 */    return newFace;
/* 311:    */  }
/* 312:    */  
/* 313:    */  private static abstract interface renderCallBack {
/* 314:    */    public abstract void render(GLUtessellatorImpl paramGLUtessellatorImpl, GLUhalfEdge paramGLUhalfEdge, long paramLong);
/* 315:    */  }
/* 316:    */  
/* 317:    */  private static class RenderTriangle implements Render.renderCallBack {
/* 318:318 */    public void render(GLUtessellatorImpl tess, GLUhalfEdge e, long size) { assert (size == 1L);
/* 319:319 */      tess.lonelyTriList = Render.AddToTrail(e.Lface, tess.lonelyTriList);
/* 320:    */    }
/* 321:    */  }
/* 322:    */  
/* 328:    */  static void RenderLonelyTriangles(GLUtessellatorImpl tess, GLUface f)
/* 329:    */  {
/* 330:330 */    int edgeState = -1;
/* 331:    */    
/* 332:332 */    tess.callBeginOrBeginData(4);
/* 333:334 */    for (; 
/* 334:334 */        f != null; f = f.trail)
/* 335:    */    {
/* 337:337 */      GLUhalfEdge e = f.anEdge;
/* 338:    */      do {
/* 339:339 */        if (tess.flagBoundary)
/* 340:    */        {
/* 343:343 */          int newState = !e.Sym.Lface.inside ? 1 : 0;
/* 344:344 */          if (edgeState != newState) {
/* 345:345 */            edgeState = newState;
/* 346:346 */            tess.callEdgeFlagOrEdgeFlagData(edgeState != 0);
/* 347:    */          }
/* 348:    */        }
/* 349:349 */        tess.callVertexOrVertexData(e.Org.data);
/* 350:    */        
/* 351:351 */        e = e.Lnext;
/* 352:352 */      } while (e != f.anEdge);
/* 353:    */    }
/* 354:354 */    tess.callEndOrEndData();
/* 355:    */  }
/* 356:    */  
/* 358:    */  private static class RenderFan
/* 359:    */    implements Render.renderCallBack
/* 360:    */  {
/* 361:    */    public void render(GLUtessellatorImpl tess, GLUhalfEdge e, long size)
/* 362:    */    {
/* 363:363 */      tess.callBeginOrBeginData(6);
/* 364:364 */      tess.callVertexOrVertexData(e.Org.data);
/* 365:365 */      tess.callVertexOrVertexData(e.Sym.Org.data);
/* 366:    */      
/* 367:367 */      while (!Render.Marked(e.Lface)) {
/* 368:368 */        e.Lface.marked = true;
/* 369:369 */        size -= 1L;
/* 370:370 */        e = e.Onext;
/* 371:371 */        tess.callVertexOrVertexData(e.Sym.Org.data);
/* 372:    */      }
/* 373:    */      
/* 374:374 */      assert (size == 0L);
/* 375:375 */      tess.callEndOrEndData();
/* 376:    */    }
/* 377:    */  }
/* 378:    */  
/* 380:    */  private static class RenderStrip
/* 381:    */    implements Render.renderCallBack
/* 382:    */  {
/* 383:    */    public void render(GLUtessellatorImpl tess, GLUhalfEdge e, long size)
/* 384:    */    {
/* 385:385 */      tess.callBeginOrBeginData(5);
/* 386:386 */      tess.callVertexOrVertexData(e.Org.data);
/* 387:387 */      tess.callVertexOrVertexData(e.Sym.Org.data);
/* 388:    */      
/* 389:389 */      while (!Render.Marked(e.Lface)) {
/* 390:390 */        e.Lface.marked = true;
/* 391:391 */        size -= 1L;
/* 392:392 */        e = e.Lnext.Sym;
/* 393:393 */        tess.callVertexOrVertexData(e.Org.data);
/* 394:394 */        if (Render.Marked(e.Lface))
/* 395:    */          break;
/* 396:396 */        e.Lface.marked = true;
/* 397:397 */        size -= 1L;
/* 398:398 */        e = e.Onext;
/* 399:399 */        tess.callVertexOrVertexData(e.Sym.Org.data);
/* 400:    */      }
/* 401:    */      
/* 402:402 */      assert (size == 0L);
/* 403:403 */      tess.callEndOrEndData();
/* 404:    */    }
/* 405:    */  }
/* 406:    */  
/* 415:    */  public static void __gl_renderBoundary(GLUtessellatorImpl tess, GLUmesh mesh)
/* 416:    */  {
/* 417:417 */    for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = f.next) {
/* 418:418 */      if (f.inside) {
/* 419:419 */        tess.callBeginOrBeginData(2);
/* 420:420 */        GLUhalfEdge e = f.anEdge;
/* 421:    */        do {
/* 422:422 */          tess.callVertexOrVertexData(e.Org.data);
/* 423:423 */          e = e.Lnext;
/* 424:424 */        } while (e != f.anEdge);
/* 425:425 */        tess.callEndOrEndData();
/* 426:    */      }
/* 427:    */    }
/* 428:    */  }
/* 429:    */  
/* 442:    */  static int ComputeNormal(GLUtessellatorImpl tess, double[] norm, boolean check)
/* 443:    */  {
/* 444:444 */    CachedVertex[] v = tess.cache;
/* 445:    */    
/* 446:446 */    int vn = tess.cacheCount;
/* 447:    */    
/* 450:450 */    double[] n = new double[3];
/* 451:451 */    int sign = 0;
/* 452:    */    
/* 466:466 */    if (!check) {
/* 467:467 */      double tmp32_31 = (norm[2] = 0.0D);norm[1] = tmp32_31;norm[0] = tmp32_31;
/* 468:    */    }
/* 469:    */    
/* 470:470 */    int vc = 1;
/* 471:471 */    double xc = v[vc].coords[0] - v[0].coords[0];
/* 472:472 */    double yc = v[vc].coords[1] - v[0].coords[1];
/* 473:473 */    double zc = v[vc].coords[2] - v[0].coords[2];
/* 474:474 */    for (;;) { vc++; if (vc >= vn) break;
/* 475:475 */      double xp = xc;
/* 476:476 */      double yp = yc;
/* 477:477 */      double zp = zc;
/* 478:478 */      xc = v[vc].coords[0] - v[0].coords[0];
/* 479:479 */      yc = v[vc].coords[1] - v[0].coords[1];
/* 480:480 */      zc = v[vc].coords[2] - v[0].coords[2];
/* 481:    */      
/* 483:483 */      n[0] = (yp * zc - zp * yc);
/* 484:484 */      n[1] = (zp * xc - xp * zc);
/* 485:485 */      n[2] = (xp * yc - yp * xc);
/* 486:    */      
/* 487:487 */      double dot = n[0] * norm[0] + n[1] * norm[1] + n[2] * norm[2];
/* 488:488 */      if (!check)
/* 489:    */      {
/* 492:492 */        if (dot >= 0.0D) {
/* 493:493 */          norm[0] += n[0];
/* 494:494 */          norm[1] += n[1];
/* 495:495 */          norm[2] += n[2];
/* 496:    */        } else {
/* 497:497 */          norm[0] -= n[0];
/* 498:498 */          norm[1] -= n[1];
/* 499:499 */          norm[2] -= n[2];
/* 500:    */        }
/* 501:501 */      } else if (dot != 0.0D)
/* 502:    */      {
/* 503:503 */        if (dot > 0.0D) {
/* 504:504 */          if (sign < 0) return 2;
/* 505:505 */          sign = 1;
/* 506:    */        } else {
/* 507:507 */          if (sign > 0) return 2;
/* 508:508 */          sign = -1;
/* 509:    */        }
/* 510:    */      }
/* 511:    */    }
/* 512:512 */    return sign;
/* 513:    */  }
/* 514:    */  
/* 521:    */  public static boolean __gl_renderCache(GLUtessellatorImpl tess)
/* 522:    */  {
/* 523:523 */    CachedVertex[] v = tess.cache;
/* 524:    */    
/* 525:525 */    int vn = tess.cacheCount;
/* 526:    */    
/* 528:528 */    double[] norm = new double[3];
/* 529:    */    
/* 531:531 */    if (tess.cacheCount < 3)
/* 532:    */    {
/* 533:533 */      return true;
/* 534:    */    }
/* 535:    */    
/* 536:536 */    norm[0] = tess.normal[0];
/* 537:537 */    norm[1] = tess.normal[1];
/* 538:538 */    norm[2] = tess.normal[2];
/* 539:539 */    if ((norm[0] == 0.0D) && (norm[1] == 0.0D) && (norm[2] == 0.0D)) {
/* 540:540 */      ComputeNormal(tess, norm, false);
/* 541:    */    }
/* 542:    */    
/* 543:543 */    int sign = ComputeNormal(tess, norm, true);
/* 544:544 */    if (sign == 2)
/* 545:    */    {
/* 546:546 */      return false;
/* 547:    */    }
/* 548:548 */    if (sign == 0)
/* 549:    */    {
/* 550:550 */      return true;
/* 551:    */    }
/* 552:    */    
/* 554:554 */    return false;
/* 555:    */  }
/* 556:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.Render
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */