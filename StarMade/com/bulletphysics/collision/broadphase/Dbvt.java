/*   1:    */package com.bulletphysics.collision.broadphase;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.linearmath.MiscUtil;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import com.bulletphysics.util.IntArrayList;
/*   7:    */import com.bulletphysics.util.ObjectArrayList;
/*   8:    */import java.util.Collections;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */
/*  43:    */public class Dbvt
/*  44:    */{
/*  45:    */  public static final int SIMPLE_STACKSIZE = 64;
/*  46:    */  public static final int DOUBLE_STACKSIZE = 128;
/*  47: 47 */  public Node root = null;
/*  48: 48 */  public Node free = null;
/*  49: 49 */  public int lkhd = -1;
/*  50: 50 */  public int leaves = 0;
/*  51: 51 */  public int opath = 0;
/*  52:    */  
/*  55:    */  public void clear()
/*  56:    */  {
/*  57: 57 */    if (this.root != null) {
/*  58: 58 */      recursedeletenode(this, this.root);
/*  59:    */    }
/*  60:    */    
/*  61: 61 */    this.free = null;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public boolean empty() {
/*  65: 65 */    return this.root == null;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public void optimizeBottomUp() {
/*  69: 69 */    if (this.root != null) {
/*  70: 70 */      ObjectArrayList<Node> leaves = new ObjectArrayList(this.leaves);
/*  71: 71 */      fetchleaves(this, this.root, leaves);
/*  72: 72 */      bottomup(this, leaves);
/*  73: 73 */      this.root = ((Node)leaves.getQuick(0));
/*  74:    */    }
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void optimizeTopDown() {
/*  78: 78 */    optimizeTopDown(128);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public void optimizeTopDown(int bu_treshold) {
/*  82: 82 */    if (this.root != null) {
/*  83: 83 */      ObjectArrayList<Node> leaves = new ObjectArrayList(this.leaves);
/*  84: 84 */      fetchleaves(this, this.root, leaves);
/*  85: 85 */      this.root = topdown(this, leaves, bu_treshold);
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  89:    */  public void optimizeIncremental(int passes) {
/*  90: 90 */    if (passes < 0) {
/*  91: 91 */      passes = this.leaves;
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    if ((this.root != null) && (passes > 0)) {
/*  95: 95 */      Node[] root_ref = new Node[1];
/*  96:    */      do {
/*  97: 97 */        Node node = this.root;
/*  98: 98 */        int bit = 0;
/*  99: 99 */        while (node.isinternal()) {
/* 100:100 */          root_ref[0] = this.root;
/* 101:101 */          node = sort(node, root_ref).childs[(this.opath >>> bit & 0x1)];
/* 102:102 */          this.root = root_ref[0];
/* 103:    */          
/* 104:104 */          bit = bit + 1 & 0x1F;
/* 105:    */        }
/* 106:106 */        update(node);
/* 107:107 */        this.opath += 1;
/* 108:    */        
/* 109:109 */        passes--; } while (passes != 0);
/* 110:    */    }
/* 111:    */  }
/* 112:    */  
/* 113:    */  public Node insert(DbvtAabbMm box, Object data) {
/* 114:114 */    Node leaf = createnode(this, null, box, data);
/* 115:115 */    insertleaf(this, this.root, leaf);
/* 116:116 */    this.leaves += 1;
/* 117:117 */    return leaf;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void update(Node leaf) {
/* 121:121 */    update(leaf, -1);
/* 122:    */  }
/* 123:    */  
/* 124:    */  public void update(Node leaf, int lookahead) {
/* 125:125 */    Node root = removeleaf(this, leaf);
/* 126:126 */    if (root != null) {
/* 127:127 */      if (lookahead >= 0) {
/* 128:128 */        for (int i = 0; (i < lookahead) && (root.parent != null); i++) {
/* 129:129 */          root = root.parent;
/* 130:    */        }
/* 131:    */        
/* 132:    */      } else {
/* 133:133 */        root = this.root;
/* 134:    */      }
/* 135:    */    }
/* 136:136 */    insertleaf(this, root, leaf);
/* 137:    */  }
/* 138:    */  
/* 139:    */  public void update(Node leaf, DbvtAabbMm volume) {
/* 140:140 */    Node root = removeleaf(this, leaf);
/* 141:141 */    if (root != null) {
/* 142:142 */      if (this.lkhd >= 0) {
/* 143:143 */        for (int i = 0; (i < this.lkhd) && (root.parent != null); i++) {
/* 144:144 */          root = root.parent;
/* 145:    */        }
/* 146:    */        
/* 147:    */      } else {
/* 148:148 */        root = this.root;
/* 149:    */      }
/* 150:    */    }
/* 151:151 */    leaf.volume.set(volume);
/* 152:152 */    insertleaf(this, root, leaf);
/* 153:    */  }
/* 154:    */  
/* 155:    */  public boolean update(Node arg1, DbvtAabbMm arg2, Vector3f arg3, float arg4) {
/* 156:156 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (leaf.volume.Contain(volume)) {
/* 157:157 */        return false;
/* 158:    */      }
/* 159:159 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 160:160 */      tmp.set(margin, margin, margin);
/* 161:161 */      volume.Expand(tmp);
/* 162:162 */      volume.SignedExpand(velocity);
/* 163:163 */      update(leaf, volume);
/* 164:164 */      return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 168:168 */  public boolean update(Node leaf, DbvtAabbMm volume, Vector3f velocity) { if (leaf.volume.Contain(volume)) {
/* 169:169 */      return false;
/* 170:    */    }
/* 171:171 */    volume.SignedExpand(velocity);
/* 172:172 */    update(leaf, volume);
/* 173:173 */    return true;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public boolean update(Node arg1, DbvtAabbMm arg2, float arg3) {
/* 177:177 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (leaf.volume.Contain(volume)) {
/* 178:178 */        return false;
/* 179:    */      }
/* 180:180 */      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 181:181 */      tmp.set(margin, margin, margin);
/* 182:182 */      volume.Expand(tmp);
/* 183:183 */      update(leaf, volume);
/* 184:184 */      return true; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 188:188 */  public void remove(Node leaf) { removeleaf(this, leaf);
/* 189:189 */    deletenode(this, leaf);
/* 190:190 */    this.leaves -= 1;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public void write(IWriter iwriter) {
/* 194:194 */    throw new UnsupportedOperationException();
/* 195:    */  }
/* 196:    */  
/* 197:    */  public void clone(Dbvt dest) {
/* 198:198 */    clone(dest, null);
/* 199:    */  }
/* 200:    */  
/* 201:    */  public void clone(Dbvt dest, IClone iclone) {
/* 202:202 */    throw new UnsupportedOperationException();
/* 203:    */  }
/* 204:    */  
/* 205:    */  public static int countLeaves(Node node) {
/* 206:206 */    if (node.isinternal()) {
/* 207:207 */      return countLeaves(node.childs[0]) + countLeaves(node.childs[1]);
/* 208:    */    }
/* 209:    */    
/* 210:210 */    return 1;
/* 211:    */  }
/* 212:    */  
/* 213:    */  public static void extractLeaves(Node node, ObjectArrayList<Node> leaves)
/* 214:    */  {
/* 215:215 */    if (node.isinternal()) {
/* 216:216 */      extractLeaves(node.childs[0], leaves);
/* 217:217 */      extractLeaves(node.childs[1], leaves);
/* 218:    */    }
/* 219:    */    else {
/* 220:220 */      leaves.add(node);
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 224:    */  public static void enumNodes(Node root, ICollide policy)
/* 225:    */  {
/* 226:226 */    policy.Process(root);
/* 227:227 */    if (root.isinternal()) {
/* 228:228 */      enumNodes(root.childs[0], policy);
/* 229:229 */      enumNodes(root.childs[1], policy);
/* 230:    */    }
/* 231:    */  }
/* 232:    */  
/* 233:    */  public static void enumLeaves(Node root, ICollide policy)
/* 234:    */  {
/* 235:235 */    if (root.isinternal()) {
/* 236:236 */      enumLeaves(root.childs[0], policy);
/* 237:237 */      enumLeaves(root.childs[1], policy);
/* 238:    */    }
/* 239:    */    else {
/* 240:240 */      policy.Process(root);
/* 241:    */    }
/* 242:    */  }
/* 243:    */  
/* 244:    */  public static void collideTT(Node root0, Node root1, ICollide policy)
/* 245:    */  {
/* 246:246 */    if ((root0 != null) && (root1 != null)) {
/* 247:247 */      ObjectArrayList<sStkNN> stack = new ObjectArrayList(128);
/* 248:248 */      stack.add(new sStkNN(root0, root1));
/* 249:    */      do {
/* 250:250 */        sStkNN p = (sStkNN)stack.remove(stack.size() - 1);
/* 251:251 */        if (p.a == p.b) {
/* 252:252 */          if (p.a.isinternal()) {
/* 253:253 */            stack.add(new sStkNN(p.a.childs[0], p.a.childs[0]));
/* 254:254 */            stack.add(new sStkNN(p.a.childs[1], p.a.childs[1]));
/* 255:255 */            stack.add(new sStkNN(p.a.childs[0], p.a.childs[1]));
/* 256:    */          }
/* 257:    */        }
/* 258:258 */        else if (DbvtAabbMm.Intersect(p.a.volume, p.b.volume)) {
/* 259:259 */          if (p.a.isinternal()) {
/* 260:260 */            if (p.b.isinternal()) {
/* 261:261 */              stack.add(new sStkNN(p.a.childs[0], p.b.childs[0]));
/* 262:262 */              stack.add(new sStkNN(p.a.childs[1], p.b.childs[0]));
/* 263:263 */              stack.add(new sStkNN(p.a.childs[0], p.b.childs[1]));
/* 264:264 */              stack.add(new sStkNN(p.a.childs[1], p.b.childs[1]));
/* 265:    */            }
/* 266:    */            else {
/* 267:267 */              stack.add(new sStkNN(p.a.childs[0], p.b));
/* 268:268 */              stack.add(new sStkNN(p.a.childs[1], p.b));
/* 269:    */            }
/* 270:    */            
/* 271:    */          }
/* 272:272 */          else if (p.b.isinternal()) {
/* 273:273 */            stack.add(new sStkNN(p.a, p.b.childs[0]));
/* 274:274 */            stack.add(new sStkNN(p.a, p.b.childs[1]));
/* 275:    */          }
/* 276:    */          else {
/* 277:277 */            policy.Process(p.a, p.b);
/* 278:    */          }
/* 279:    */          
/* 280:    */        }
/* 281:    */        
/* 282:282 */      } while (stack.size() > 0);
/* 283:    */    }
/* 284:    */  }
/* 285:    */  
/* 286:    */  public static void collideTT(Node root0, Node root1, Transform xform, ICollide policy)
/* 287:    */  {
/* 288:288 */    if ((root0 != null) && (root1 != null)) {
/* 289:289 */      ObjectArrayList<sStkNN> stack = new ObjectArrayList(128);
/* 290:290 */      stack.add(new sStkNN(root0, root1));
/* 291:    */      do {
/* 292:292 */        sStkNN p = (sStkNN)stack.remove(stack.size() - 1);
/* 293:293 */        if (p.a == p.b) {
/* 294:294 */          if (p.a.isinternal()) {
/* 295:295 */            stack.add(new sStkNN(p.a.childs[0], p.a.childs[0]));
/* 296:296 */            stack.add(new sStkNN(p.a.childs[1], p.a.childs[1]));
/* 297:297 */            stack.add(new sStkNN(p.a.childs[0], p.a.childs[1]));
/* 298:    */          }
/* 299:    */        }
/* 300:300 */        else if (DbvtAabbMm.Intersect(p.a.volume, p.b.volume, xform)) {
/* 301:301 */          if (p.a.isinternal()) {
/* 302:302 */            if (p.b.isinternal()) {
/* 303:303 */              stack.add(new sStkNN(p.a.childs[0], p.b.childs[0]));
/* 304:304 */              stack.add(new sStkNN(p.a.childs[1], p.b.childs[0]));
/* 305:305 */              stack.add(new sStkNN(p.a.childs[0], p.b.childs[1]));
/* 306:306 */              stack.add(new sStkNN(p.a.childs[1], p.b.childs[1]));
/* 307:    */            }
/* 308:    */            else {
/* 309:309 */              stack.add(new sStkNN(p.a.childs[0], p.b));
/* 310:310 */              stack.add(new sStkNN(p.a.childs[1], p.b));
/* 311:    */            }
/* 312:    */            
/* 313:    */          }
/* 314:314 */          else if (p.b.isinternal()) {
/* 315:315 */            stack.add(new sStkNN(p.a, p.b.childs[0]));
/* 316:316 */            stack.add(new sStkNN(p.a, p.b.childs[1]));
/* 317:    */          }
/* 318:    */          else {
/* 319:319 */            policy.Process(p.a, p.b);
/* 320:    */          }
/* 321:    */          
/* 322:    */        }
/* 323:    */        
/* 324:324 */      } while (stack.size() > 0);
/* 325:    */    }
/* 326:    */  }
/* 327:    */  
/* 328:    */  public static void collideTT(Node arg0, Transform arg1, Node arg2, Transform arg3, ICollide arg4) {
/* 329:329 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform xform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 330:330 */      xform.inverse(xform0);
/* 331:331 */      xform.mul(xform1);
/* 332:332 */      collideTT(root0, root1, xform, policy);
/* 333:333 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/* 334:    */    }
/* 335:    */  }
/* 336:    */  
/* 337:337 */  public static void collideTV(Node root, DbvtAabbMm volume, ICollide policy) { if (root != null) {
/* 338:338 */      ObjectArrayList<Node> stack = new ObjectArrayList(64);
/* 339:339 */      stack.add(root);
/* 340:    */      do {
/* 341:341 */        Node n = (Node)stack.remove(stack.size() - 1);
/* 342:342 */        if (DbvtAabbMm.Intersect(n.volume, volume)) {
/* 343:343 */          if (n.isinternal()) {
/* 344:344 */            stack.add(n.childs[0]);
/* 345:345 */            stack.add(n.childs[1]);
/* 346:    */          }
/* 347:    */          else {
/* 348:348 */            policy.Process(n);
/* 349:    */          }
/* 350:    */          
/* 351:    */        }
/* 352:352 */      } while (stack.size() > 0);
/* 353:    */    }
/* 354:    */  }
/* 355:    */  
/* 356:    */  public static void collideRAY(Node arg0, Vector3f arg1, Vector3f arg2, ICollide arg3)
/* 357:    */  {
/* 358:358 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (root != null) {
/* 359:359 */        Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 360:360 */        normal.normalize(direction);
/* 361:361 */        Vector3f invdir = localStack.get$javax$vecmath$Vector3f();
/* 362:362 */        invdir.set(1.0F / normal.x, 1.0F / normal.y, 1.0F / normal.z);
/* 363:363 */        int[] signs = { direction.x < 0.0F ? 1 : 0, direction.y < 0.0F ? 1 : 0, direction.z < 0.0F ? 1 : 0 };
/* 364:364 */        ObjectArrayList<Node> stack = new ObjectArrayList(64);
/* 365:365 */        stack.add(root);
/* 366:    */        do {
/* 367:367 */          Node node = (Node)stack.remove(stack.size() - 1);
/* 368:368 */          if (DbvtAabbMm.Intersect(node.volume, origin, invdir, signs)) {
/* 369:369 */            if (node.isinternal()) {
/* 370:370 */              stack.add(node.childs[0]);
/* 371:371 */              stack.add(node.childs[1]);
/* 372:    */            }
/* 373:    */            else {
/* 374:374 */              policy.Process(node);
/* 375:    */            }
/* 376:    */            
/* 377:    */          }
/* 378:378 */        } while (stack.size() != 0);
/* 379:    */      }
/* 380:380 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 381:    */    }
/* 382:    */  }
/* 383:    */  
/* 384:384 */  public static void collideKDOP(Node root, Vector3f[] normals, float[] offsets, int count, ICollide policy) { if (root != null) {
/* 385:385 */      int inside = (1 << count) - 1;
/* 386:386 */      ObjectArrayList<sStkNP> stack = new ObjectArrayList(64);
/* 387:387 */      int[] signs = new int[32];
/* 388:388 */      assert (count < 32);
/* 389:389 */      for (int i = 0; i < count; i++) {
/* 390:390 */        signs[i] = ((normals[i].x >= 0.0F ? 1 : 0) + (normals[i].y >= 0.0F ? 2 : 0) + (normals[i].z >= 0.0F ? 4 : 0));
/* 391:    */      }
/* 392:    */      
/* 394:394 */      stack.add(new sStkNP(root, 0));
/* 395:    */      do {
/* 396:396 */        sStkNP se = (sStkNP)stack.remove(stack.size() - 1);
/* 397:397 */        boolean out = false;
/* 398:398 */        int i = 0; for (int j = 1; (!out) && (i < count); j <<= 1) {
/* 399:399 */          if (0 == (se.mask & j)) {
/* 400:400 */            int side = se.node.volume.Classify(normals[i], offsets[i], signs[i]);
/* 401:401 */            switch (side) {
/* 402:    */            case -1: 
/* 403:403 */              out = true;
/* 404:404 */              break;
/* 405:    */            case 1: 
/* 406:406 */              se.mask |= j;
/* 407:    */            }
/* 408:    */          }
/* 409:398 */          i++;
/* 410:    */        }
/* 411:    */        
/* 422:411 */        if (!out) {
/* 423:412 */          if ((se.mask != inside) && (se.node.isinternal())) {
/* 424:413 */            stack.add(new sStkNP(se.node.childs[0], se.mask));
/* 425:414 */            stack.add(new sStkNP(se.node.childs[1], se.mask));
/* 427:    */          }
/* 428:417 */          else if (policy.AllLeaves(se.node)) {
/* 429:418 */            enumLeaves(se.node, policy);
/* 430:    */          }
/* 431:    */          
/* 432:    */        }
/* 433:    */        
/* 434:423 */      } while (stack.size() != 0);
/* 435:    */    }
/* 436:    */  }
/* 437:    */  
/* 438:    */  public static void collideOCL(Node root, Vector3f[] normals, float[] offsets, Vector3f sortaxis, int count, ICollide policy) {
/* 439:428 */    collideOCL(root, normals, offsets, sortaxis, count, policy, true);
/* 440:    */  }
/* 441:    */  
/* 442:    */  public static void collideOCL(Node root, Vector3f[] normals, float[] offsets, Vector3f sortaxis, int count, ICollide policy, boolean fullsort)
/* 443:    */  {
/* 444:433 */    if (root != null) {
/* 445:434 */      int srtsgns = (sortaxis.x >= 0.0F ? 1 : 0) + (sortaxis.y >= 0.0F ? 2 : 0) + (sortaxis.z >= 0.0F ? 4 : 0);
/* 446:    */      
/* 448:437 */      int inside = (1 << count) - 1;
/* 449:438 */      ObjectArrayList<sStkNPS> stock = new ObjectArrayList();
/* 450:439 */      IntArrayList ifree = new IntArrayList();
/* 451:440 */      IntArrayList stack = new IntArrayList();
/* 452:441 */      int[] signs = new int[32];
/* 453:442 */      assert (count < 32);
/* 454:443 */      for (int i = 0; i < count; i++) {
/* 455:444 */        signs[i] = ((normals[i].x >= 0.0F ? 1 : 0) + (normals[i].y >= 0.0F ? 2 : 0) + (normals[i].z >= 0.0F ? 4 : 0));
/* 456:    */      }
/* 457:    */      
/* 462:451 */      stack.add(allocate(ifree, stock, new sStkNPS(root, 0, root.volume.ProjectMinimum(sortaxis, srtsgns))));
/* 463:    */      do
/* 464:    */      {
/* 465:454 */        int id = stack.remove(stack.size() - 1);
/* 466:455 */        sStkNPS se = (sStkNPS)stock.getQuick(id);
/* 467:456 */        ifree.add(id);
/* 468:457 */        if (se.mask != inside) {
/* 469:458 */          boolean out = false;
/* 470:459 */          int i = 0; for (int j = 1; (!out) && (i < count); j <<= 1) {
/* 471:460 */            if (0 == (se.mask & j)) {
/* 472:461 */              int side = se.node.volume.Classify(normals[i], offsets[i], signs[i]);
/* 473:462 */              switch (side) {
/* 474:    */              case -1: 
/* 475:464 */                out = true;
/* 476:465 */                break;
/* 477:    */              case 1: 
/* 478:467 */                se.mask |= j;
/* 479:    */              }
/* 480:    */            }
/* 481:459 */            i++;
/* 482:    */          }
/* 483:    */          
/* 494:472 */          if (out) {}
/* 497:    */        }
/* 498:476 */        else if (policy.Descent(se.node)) {
/* 499:477 */          if (se.node.isinternal()) {
/* 500:478 */            Node[] pns = { se.node.childs[0], se.node.childs[1] };
/* 501:479 */            sStkNPS[] nes = { new sStkNPS(pns[0], se.mask, pns[0].volume.ProjectMinimum(sortaxis, srtsgns)), new sStkNPS(pns[1], se.mask, pns[1].volume.ProjectMinimum(sortaxis, srtsgns)) };
/* 502:    */            
/* 504:482 */            int q = nes[0].value < nes[1].value ? 1 : 0;
/* 505:483 */            int j = stack.size();
/* 506:484 */            if ((fullsort) && (j > 0))
/* 507:    */            {
/* 508:486 */              j = nearest(stack, stock, nes[q].value, 0, stack.size());
/* 509:487 */              stack.add(0);
/* 510:    */              
/* 513:491 */              for (int k = stack.size() - 1; k > j; k--) {
/* 514:492 */                stack.set(k, stack.get(k - 1));
/* 515:    */              }
/* 516:    */              
/* 517:495 */              stack.set(j, allocate(ifree, stock, nes[q]));
/* 518:    */              
/* 519:497 */              j = nearest(stack, stock, nes[(1 - q)].value, j, stack.size());
/* 520:498 */              stack.add(0);
/* 521:    */              
/* 524:502 */              for (int k = stack.size() - 1; k > j; k--) {
/* 525:503 */                stack.set(k, stack.get(k - 1));
/* 526:    */              }
/* 527:    */              
/* 528:506 */              stack.set(j, allocate(ifree, stock, nes[(1 - q)]));
/* 529:    */            }
/* 530:    */            else {
/* 531:509 */              stack.add(allocate(ifree, stock, nes[q]));
/* 532:510 */              stack.add(allocate(ifree, stock, nes[(1 - q)]));
/* 533:    */            }
/* 534:    */          }
/* 535:    */          else {
/* 536:514 */            policy.Process(se.node, se.value);
/* 537:    */          }
/* 538:    */          
/* 539:    */        }
/* 540:518 */      } while (stack.size() != 0);
/* 541:    */    }
/* 542:    */  }
/* 543:    */  
/* 544:    */  public static void collideTU(Node root, ICollide policy)
/* 545:    */  {
/* 546:524 */    if (root != null) {
/* 547:525 */      ObjectArrayList<Node> stack = new ObjectArrayList(64);
/* 548:526 */      stack.add(root);
/* 549:    */      do {
/* 550:528 */        Node n = (Node)stack.remove(stack.size() - 1);
/* 551:529 */        if (policy.Descent(n)) {
/* 552:530 */          if (n.isinternal()) {
/* 553:531 */            stack.add(n.childs[0]);
/* 554:532 */            stack.add(n.childs[1]);
/* 555:    */          }
/* 556:    */          else {
/* 557:535 */            policy.Process(n);
/* 558:    */          }
/* 559:    */          
/* 560:    */        }
/* 561:539 */      } while (stack.size() > 0);
/* 562:    */    }
/* 563:    */  }
/* 564:    */  
/* 565:    */  public static int nearest(IntArrayList i, ObjectArrayList<sStkNPS> a, float v, int l, int h) {
/* 566:544 */    int m = 0;
/* 567:545 */    while (l < h) {
/* 568:546 */      m = l + h >> 1;
/* 569:547 */      if (((sStkNPS)a.getQuick(i.get(m))).value >= v) {
/* 570:548 */        l = m + 1;
/* 571:    */      }
/* 572:    */      else {
/* 573:551 */        h = m;
/* 574:    */      }
/* 575:    */    }
/* 576:554 */    return h;
/* 577:    */  }
/* 578:    */  
/* 579:    */  public static int allocate(IntArrayList ifree, ObjectArrayList<sStkNPS> stock, sStkNPS value) {
/* 580:    */    int i;
/* 581:559 */    if (ifree.size() > 0) {
/* 582:560 */      int i = ifree.get(ifree.size() - 1);
/* 583:561 */      ifree.remove(ifree.size() - 1);
/* 584:562 */      ((sStkNPS)stock.getQuick(i)).set(value);
/* 585:    */    }
/* 586:    */    else {
/* 587:565 */      i = stock.size();
/* 588:566 */      stock.add(value);
/* 589:    */    }
/* 590:568 */    return i;
/* 591:    */  }
/* 592:    */  
/* 594:    */  private static int indexof(Node node)
/* 595:    */  {
/* 596:574 */    return node.parent.childs[1] == node ? 1 : 0;
/* 597:    */  }
/* 598:    */  
/* 599:    */  private static DbvtAabbMm merge(DbvtAabbMm a, DbvtAabbMm b, DbvtAabbMm out) {
/* 600:578 */    DbvtAabbMm.Merge(a, b, out);
/* 601:579 */    return out;
/* 602:    */  }
/* 603:    */  
/* 604:    */  private static float size(DbvtAabbMm arg0)
/* 605:    */  {
/* 606:584 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f edges = a.Lengths(localStack.get$javax$vecmath$Vector3f());
/* 607:585 */      return edges.x * edges.y * edges.z + edges.x + edges.y + edges.z; } finally { localStack.pop$javax$vecmath$Vector3f();
/* 608:    */    }
/* 609:    */  }
/* 610:    */  
/* 611:    */  private static void deletenode(Dbvt pdbvt, Node node)
/* 612:    */  {
/* 613:591 */    pdbvt.free = node;
/* 614:    */  }
/* 615:    */  
/* 616:    */  private static void recursedeletenode(Dbvt pdbvt, Node node) {
/* 617:595 */    if (!node.isleaf()) {
/* 618:596 */      recursedeletenode(pdbvt, node.childs[0]);
/* 619:597 */      recursedeletenode(pdbvt, node.childs[1]);
/* 620:    */    }
/* 621:599 */    if (node == pdbvt.root) {
/* 622:600 */      pdbvt.root = null;
/* 623:    */    }
/* 624:602 */    deletenode(pdbvt, node);
/* 625:    */  }
/* 626:    */  
/* 627:    */  private static Node createnode(Dbvt pdbvt, Node parent, DbvtAabbMm volume, Object data) {
/* 628:    */    Node node;
/* 629:607 */    if (pdbvt.free != null) {
/* 630:608 */      Node node = pdbvt.free;
/* 631:609 */      pdbvt.free = null;
/* 632:    */    }
/* 633:    */    else {
/* 634:612 */      node = new Node();
/* 635:    */    }
/* 636:614 */    node.parent = parent;
/* 637:615 */    node.volume.set(volume);
/* 638:616 */    node.data = data;
/* 639:617 */    node.childs[1] = null;
/* 640:618 */    return node;
/* 641:    */  }
/* 642:    */  
/* 643:    */  private static void insertleaf(Dbvt pdbvt, Node root, Node leaf) {
/* 644:622 */    if (pdbvt.root == null) {
/* 645:623 */      pdbvt.root = leaf;
/* 646:624 */      leaf.parent = null;
/* 647:    */    }
/* 648:    */    else {
/* 649:627 */      if (!root.isleaf()) {
/* 650:    */        do {
/* 651:629 */          if (DbvtAabbMm.Proximity(root.childs[0].volume, leaf.volume) < DbvtAabbMm.Proximity(root.childs[1].volume, leaf.volume))
/* 652:    */          {
/* 653:631 */            root = root.childs[0];
/* 654:    */          }
/* 655:    */          else {
/* 656:634 */            root = root.childs[1];
/* 657:    */          }
/* 658:    */          
/* 659:637 */        } while (!root.isleaf());
/* 660:    */      }
/* 661:639 */      Node prev = root.parent;
/* 662:640 */      Node node = createnode(pdbvt, prev, merge(leaf.volume, root.volume, new DbvtAabbMm()), null);
/* 663:641 */      if (prev != null) {
/* 664:642 */        prev.childs[indexof(root)] = node;
/* 665:643 */        node.childs[0] = root;
/* 666:644 */        root.parent = node;
/* 667:645 */        node.childs[1] = leaf;
/* 668:646 */        leaf.parent = node;
/* 669:    */        do {
/* 670:648 */          if (prev.volume.Contain(node.volume)) break;
/* 671:649 */          DbvtAabbMm.Merge(prev.childs[0].volume, prev.childs[1].volume, prev.volume);
/* 672:    */          
/* 676:654 */          node = prev;
/* 677:    */        }
/* 678:656 */        while (null != (prev = node.parent));
/* 679:    */      }
/* 680:    */      else {
/* 681:659 */        node.childs[0] = root;
/* 682:660 */        root.parent = node;
/* 683:661 */        node.childs[1] = leaf;
/* 684:662 */        leaf.parent = node;
/* 685:663 */        pdbvt.root = node;
/* 686:    */      }
/* 687:    */    }
/* 688:    */  }
/* 689:    */  
/* 690:    */  private static Node removeleaf(Dbvt pdbvt, Node leaf) {
/* 691:669 */    if (leaf == pdbvt.root) {
/* 692:670 */      pdbvt.root = null;
/* 693:671 */      return null;
/* 694:    */    }
/* 695:    */    
/* 696:674 */    Node parent = leaf.parent;
/* 697:675 */    Node prev = parent.parent;
/* 698:676 */    Node sibling = parent.childs[(1 - indexof(leaf))];
/* 699:677 */    if (prev != null) {
/* 700:678 */      prev.childs[indexof(parent)] = sibling;
/* 701:679 */      sibling.parent = prev;
/* 702:680 */      deletenode(pdbvt, parent);
/* 703:681 */      while (prev != null) {
/* 704:682 */        DbvtAabbMm pb = prev.volume;
/* 705:683 */        DbvtAabbMm.Merge(prev.childs[0].volume, prev.childs[1].volume, prev.volume);
/* 706:684 */        if (!DbvtAabbMm.NotEqual(pb, prev.volume)) break;
/* 707:685 */        prev = prev.parent;
/* 708:    */      }
/* 709:    */      
/* 713:691 */      return prev != null ? prev : pdbvt.root;
/* 714:    */    }
/* 715:    */    
/* 716:694 */    pdbvt.root = sibling;
/* 717:695 */    sibling.parent = null;
/* 718:696 */    deletenode(pdbvt, parent);
/* 719:697 */    return pdbvt.root;
/* 720:    */  }
/* 721:    */  
/* 723:    */  private static void fetchleaves(Dbvt pdbvt, Node root, ObjectArrayList<Node> leaves)
/* 724:    */  {
/* 725:703 */    fetchleaves(pdbvt, root, leaves, -1);
/* 726:    */  }
/* 727:    */  
/* 728:    */  private static void fetchleaves(Dbvt pdbvt, Node root, ObjectArrayList<Node> leaves, int depth) {
/* 729:707 */    if ((root.isinternal()) && (depth != 0)) {
/* 730:708 */      fetchleaves(pdbvt, root.childs[0], leaves, depth - 1);
/* 731:709 */      fetchleaves(pdbvt, root.childs[1], leaves, depth - 1);
/* 732:710 */      deletenode(pdbvt, root);
/* 733:    */    }
/* 734:    */    else {
/* 735:713 */      leaves.add(root);
/* 736:    */    }
/* 737:    */  }
/* 738:    */  
/* 739:    */  private static void split(ObjectArrayList<Node> arg0, ObjectArrayList<Node> arg1, ObjectArrayList<Node> arg2, Vector3f arg3, Vector3f arg4) {
/* 740:718 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 741:719 */      MiscUtil.resize(left, 0, Node.class);
/* 742:720 */      MiscUtil.resize(right, 0, Node.class);
/* 743:721 */      int i = 0; for (int ni = leaves.size(); i < ni; i++) {
/* 744:722 */        ((Node)leaves.getQuick(i)).volume.Center(tmp);
/* 745:723 */        tmp.sub(org);
/* 746:724 */        if (axis.dot(tmp) < 0.0F) {
/* 747:725 */          left.add(leaves.getQuick(i));
/* 748:    */        }
/* 749:    */        else
/* 750:728 */          right.add(leaves.getQuick(i));
/* 751:    */      }
/* 752:    */    } finally {
/* 753:731 */      localStack.pop$javax$vecmath$Vector3f();
/* 754:    */    } }
/* 755:    */  
/* 756:734 */  private static DbvtAabbMm bounds(ObjectArrayList<Node> leaves) { DbvtAabbMm volume = new DbvtAabbMm(((Node)leaves.getQuick(0)).volume);
/* 757:735 */    int i = 1; for (int ni = leaves.size(); i < ni; i++) {
/* 758:736 */      merge(volume, ((Node)leaves.getQuick(i)).volume, volume);
/* 759:    */    }
/* 760:738 */    return volume;
/* 761:    */  }
/* 762:    */  
/* 763:    */  private static void bottomup(Dbvt pdbvt, ObjectArrayList<Node> leaves) {
/* 764:742 */    DbvtAabbMm tmpVolume = new DbvtAabbMm();
/* 765:743 */    while (leaves.size() > 1) {
/* 766:744 */      float minsize = 3.4028235E+38F;
/* 767:745 */      int[] minidx = { -1, -1 };
/* 768:746 */      for (int i = 0; i < leaves.size(); i++) {
/* 769:747 */        for (int j = i + 1; j < leaves.size(); j++) {
/* 770:748 */          float sz = size(merge(((Node)leaves.getQuick(i)).volume, ((Node)leaves.getQuick(j)).volume, tmpVolume));
/* 771:749 */          if (sz < minsize) {
/* 772:750 */            minsize = sz;
/* 773:751 */            minidx[0] = i;
/* 774:752 */            minidx[1] = j;
/* 775:    */          }
/* 776:    */        }
/* 777:    */      }
/* 778:756 */      Node[] n = { (Node)leaves.getQuick(minidx[0]), (Node)leaves.getQuick(minidx[1]) };
/* 779:757 */      Node p = createnode(pdbvt, null, merge(n[0].volume, n[1].volume, new DbvtAabbMm()), null);
/* 780:758 */      p.childs[0] = n[0];
/* 781:759 */      p.childs[1] = n[1];
/* 782:760 */      n[0].parent = p;
/* 783:761 */      n[1].parent = p;
/* 784:    */      
/* 785:763 */      leaves.setQuick(minidx[0], p);
/* 786:764 */      Collections.swap(leaves, minidx[1], leaves.size() - 1);
/* 787:765 */      leaves.removeQuick(leaves.size() - 1);
/* 788:    */    }
/* 789:    */  }
/* 790:    */  
/* 791:769 */  private static Vector3f[] axis = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/* 792:    */  
/* 793:    */  private static Node topdown(Dbvt arg0, ObjectArrayList<Node> arg1, int arg2) {
/* 794:772 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (leaves.size() > 1) {
/* 795:773 */        if (leaves.size() > bu_treshold) {
/* 796:774 */          DbvtAabbMm vol = bounds(leaves);
/* 797:775 */          Vector3f org = vol.Center(localStack.get$javax$vecmath$Vector3f());
/* 798:776 */          ObjectArrayList[] sets = new ObjectArrayList[2];
/* 799:777 */          for (int i = 0; i < sets.length; i++) {
/* 800:778 */            sets[i] = new ObjectArrayList();
/* 801:    */          }
/* 802:780 */          int bestaxis = -1;
/* 803:781 */          int bestmidp = leaves.size();
/* 804:782 */          int[][] splitcount = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
/* 805:    */          
/* 806:784 */          Vector3f x = localStack.get$javax$vecmath$Vector3f();
/* 807:    */          
/* 808:786 */          for (int i = 0; i < leaves.size(); i++) {
/* 809:787 */            ((Node)leaves.getQuick(i)).volume.Center(x);
/* 810:788 */            x.sub(org);
/* 811:789 */            for (int j = 0; j < 3; j++) {
/* 812:790 */              splitcount[j][(x.dot(axis[j]) > 0.0F ? 1 : 0)] += 1;
/* 813:    */            }
/* 814:    */          }
/* 815:793 */          for (int i = 0; i < 3; i++) {
/* 816:794 */            if ((splitcount[i][0] > 0) && (splitcount[i][1] > 0)) {
/* 817:795 */              int midp = Math.abs(splitcount[i][0] - splitcount[i][1]);
/* 818:796 */              if (midp < bestmidp) {
/* 819:797 */                bestaxis = i;
/* 820:798 */                bestmidp = midp;
/* 821:    */              }
/* 822:    */            }
/* 823:    */          }
/* 824:802 */          if (bestaxis >= 0)
/* 825:    */          {
/* 827:805 */            split(leaves, sets[0], sets[1], org, axis[bestaxis]);
/* 829:    */          }
/* 830:    */          else
/* 831:    */          {
/* 832:810 */            int i = 0; for (int ni = leaves.size(); i < ni; i++) {
/* 833:811 */              sets[(i & 0x1)].add(leaves.getQuick(i));
/* 834:    */            }
/* 835:    */          }
/* 836:814 */          Node node = createnode(pdbvt, null, vol, null);
/* 837:815 */          node.childs[0] = topdown(pdbvt, sets[0], bu_treshold);
/* 838:816 */          node.childs[1] = topdown(pdbvt, sets[1], bu_treshold);
/* 839:817 */          node.childs[0].parent = node;
/* 840:818 */          node.childs[1].parent = node;
/* 841:819 */          return node;
/* 842:    */        }
/* 843:    */        
/* 844:822 */        bottomup(pdbvt, leaves);
/* 845:823 */        return (Node)leaves.getQuick(0);
/* 846:    */      }
/* 847:    */      
/* 848:826 */      return (Node)leaves.getQuick(0); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 849:    */    }
/* 850:    */  }
/* 851:    */  
/* 852:830 */  private static Node sort(Node n, Node[] r) { Node p = n.parent;
/* 853:831 */    assert (n.isinternal());
/* 854:    */    
/* 855:833 */    if ((p != null) && (p.hashCode() > n.hashCode())) {
/* 856:834 */      int i = indexof(n);
/* 857:835 */      int j = 1 - i;
/* 858:836 */      Node s = p.childs[j];
/* 859:837 */      Node q = p.parent;
/* 860:838 */      assert (n == p.childs[i]);
/* 861:839 */      if (q != null) {
/* 862:840 */        q.childs[indexof(p)] = n;
/* 863:    */      }
/* 864:    */      else {
/* 865:843 */        r[0] = n;
/* 866:    */      }
/* 867:845 */      s.parent = n;
/* 868:846 */      p.parent = n;
/* 869:847 */      n.parent = q;
/* 870:848 */      p.childs[0] = n.childs[0];
/* 871:849 */      p.childs[1] = n.childs[1];
/* 872:850 */      n.childs[0].parent = p;
/* 873:851 */      n.childs[1].parent = p;
/* 874:852 */      n.childs[i] = p;
/* 875:853 */      n.childs[j] = s;
/* 876:    */      
/* 877:855 */      DbvtAabbMm.swap(p.volume, n.volume);
/* 878:856 */      return p;
/* 879:    */    }
/* 880:858 */    return n;
/* 881:    */  }
/* 882:    */  
/* 883:    */  private static Node walkup(Node n, int count) {
/* 884:862 */    while ((n != null) && (count-- != 0)) {
/* 885:863 */      n = n.parent;
/* 886:    */    }
/* 887:865 */    return n;
/* 888:    */  }
/* 889:    */  
/* 891:    */  public static class Node
/* 892:    */  {
/* 893:871 */    public final DbvtAabbMm volume = new DbvtAabbMm();
/* 894:    */    public Node parent;
/* 895:873 */    public final Node[] childs = new Node[2];
/* 896:    */    public Object data;
/* 897:    */    
/* 898:    */    public boolean isleaf() {
/* 899:877 */      return this.childs[1] == null;
/* 900:    */    }
/* 901:    */    
/* 902:    */    public boolean isinternal() {
/* 903:881 */      return !isleaf();
/* 904:    */    }
/* 905:    */  }
/* 906:    */  
/* 907:    */  public static class sStkNN
/* 908:    */  {
/* 909:    */    public Dbvt.Node a;
/* 910:    */    public Dbvt.Node b;
/* 911:    */    
/* 912:    */    public sStkNN(Dbvt.Node na, Dbvt.Node nb) {
/* 913:891 */      this.a = na;
/* 914:892 */      this.b = nb;
/* 915:    */    }
/* 916:    */  }
/* 917:    */  
/* 918:    */  public static class sStkNP {
/* 919:    */    public Dbvt.Node node;
/* 920:    */    public int mask;
/* 921:    */    
/* 922:    */    public sStkNP(Dbvt.Node n, int m) {
/* 923:901 */      this.node = n;
/* 924:902 */      this.mask = m;
/* 925:    */    }
/* 926:    */  }
/* 927:    */  
/* 928:    */  public static class sStkNPS
/* 929:    */  {
/* 930:    */    public Dbvt.Node node;
/* 931:    */    public int mask;
/* 932:    */    public float value;
/* 933:    */    
/* 934:    */    public sStkNPS() {}
/* 935:    */    
/* 936:    */    public sStkNPS(Dbvt.Node n, int m, float v) {
/* 937:915 */      this.node = n;
/* 938:916 */      this.mask = m;
/* 939:917 */      this.value = v;
/* 940:    */    }
/* 941:    */    
/* 942:    */    public void set(sStkNPS o) {
/* 943:921 */      this.node = o.node;
/* 944:922 */      this.mask = o.mask;
/* 945:923 */      this.value = o.value;
/* 946:    */    }
/* 947:    */  }
/* 948:    */  
/* 949:    */  public static class sStkCLN {
/* 950:    */    public Dbvt.Node node;
/* 951:    */    public Dbvt.Node parent;
/* 952:    */    
/* 953:    */    public sStkCLN(Dbvt.Node n, Dbvt.Node p) {
/* 954:932 */      this.node = n;
/* 955:933 */      this.parent = p;
/* 956:    */    }
/* 957:    */  }
/* 958:    */  
/* 959:    */  public static class ICollide
/* 960:    */  {
/* 961:    */    public void Process(Dbvt.Node n1, Dbvt.Node n2) {}
/* 962:    */    
/* 963:    */    public void Process(Dbvt.Node n) {}
/* 964:    */    
/* 965:    */    public void Process(Dbvt.Node n, float f)
/* 966:    */    {
/* 967:945 */      Process(n);
/* 968:    */    }
/* 969:    */    
/* 970:    */    public boolean Descent(Dbvt.Node n) {
/* 971:949 */      return true;
/* 972:    */    }
/* 973:    */    
/* 974:    */    public boolean AllLeaves(Dbvt.Node n) {
/* 975:953 */      return true;
/* 976:    */    }
/* 977:    */  }
/* 978:    */  
/* 979:    */  public static abstract class IWriter
/* 980:    */  {
/* 981:    */    public abstract void Prepare(Dbvt.Node paramNode, int paramInt);
/* 982:    */    
/* 983:    */    public abstract void WriteNode(Dbvt.Node paramNode, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/* 984:    */    
/* 985:    */    public abstract void WriteLeaf(Dbvt.Node paramNode, int paramInt1, int paramInt2);
/* 986:    */  }
/* 987:    */  
/* 988:    */  public static class IClone
/* 989:    */  {
/* 990:    */    public void CloneLeaf(Dbvt.Node n) {}
/* 991:    */  }
/* 992:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.Dbvt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */