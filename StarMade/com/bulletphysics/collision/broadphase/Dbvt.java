/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.util.Collections;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class Dbvt
/*     */ {
/*     */   public static final int SIMPLE_STACKSIZE = 64;
/*     */   public static final int DOUBLE_STACKSIZE = 128;
/*  47 */   public Node root = null;
/*  48 */   public Node free = null;
/*  49 */   public int lkhd = -1;
/*  50 */   public int leaves = 0;
/*  51 */   public int opath = 0;
/*     */ 
/* 769 */   private static Vector3f[] axis = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*     */ 
/*     */   public void clear()
/*     */   {
/*  57 */     if (this.root != null) {
/*  58 */       recursedeletenode(this, this.root);
/*     */     }
/*     */ 
/*  61 */     this.free = null;
/*     */   }
/*     */ 
/*     */   public boolean empty() {
/*  65 */     return this.root == null;
/*     */   }
/*     */ 
/*     */   public void optimizeBottomUp() {
/*  69 */     if (this.root != null) {
/*  70 */       ObjectArrayList leaves = new ObjectArrayList(this.leaves);
/*  71 */       fetchleaves(this, this.root, leaves);
/*  72 */       bottomup(this, leaves);
/*  73 */       this.root = ((Node)leaves.getQuick(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void optimizeTopDown() {
/*  78 */     optimizeTopDown(128);
/*     */   }
/*     */ 
/*     */   public void optimizeTopDown(int bu_treshold) {
/*  82 */     if (this.root != null) {
/*  83 */       ObjectArrayList leaves = new ObjectArrayList(this.leaves);
/*  84 */       fetchleaves(this, this.root, leaves);
/*  85 */       this.root = topdown(this, leaves, bu_treshold);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void optimizeIncremental(int passes) {
/*  90 */     if (passes < 0) {
/*  91 */       passes = this.leaves;
/*     */     }
/*     */ 
/*  94 */     if ((this.root != null) && (passes > 0)) {
/*  95 */       Node[] root_ref = new Node[1];
/*     */       do {
/*  97 */         Node node = this.root;
/*  98 */         int bit = 0;
/*  99 */         while (node.isinternal()) {
/* 100 */           root_ref[0] = this.root;
/* 101 */           node = sort(node, root_ref).childs[(this.opath >>> bit & 0x1)];
/* 102 */           this.root = root_ref[0];
/*     */ 
/* 104 */           bit = bit + 1 & 0x1F;
/*     */         }
/* 106 */         update(node);
/* 107 */         this.opath += 1;
/*     */ 
/* 109 */         passes--; } while (passes != 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Node insert(DbvtAabbMm box, Object data) {
/* 114 */     Node leaf = createnode(this, null, box, data);
/* 115 */     insertleaf(this, this.root, leaf);
/* 116 */     this.leaves += 1;
/* 117 */     return leaf;
/*     */   }
/*     */ 
/*     */   public void update(Node leaf) {
/* 121 */     update(leaf, -1);
/*     */   }
/*     */ 
/*     */   public void update(Node leaf, int lookahead) {
/* 125 */     Node root = removeleaf(this, leaf);
/* 126 */     if (root != null) {
/* 127 */       if (lookahead >= 0) {
/* 128 */         for (int i = 0; (i < lookahead) && (root.parent != null); i++) {
/* 129 */           root = root.parent;
/*     */         }
/*     */       }
/*     */       else {
/* 133 */         root = this.root;
/*     */       }
/*     */     }
/* 136 */     insertleaf(this, root, leaf);
/*     */   }
/*     */ 
/*     */   public void update(Node leaf, DbvtAabbMm volume) {
/* 140 */     Node root = removeleaf(this, leaf);
/* 141 */     if (root != null) {
/* 142 */       if (this.lkhd >= 0) {
/* 143 */         for (int i = 0; (i < this.lkhd) && (root.parent != null); i++) {
/* 144 */           root = root.parent;
/*     */         }
/*     */       }
/*     */       else {
/* 148 */         root = this.root;
/*     */       }
/*     */     }
/* 151 */     leaf.volume.set(volume);
/* 152 */     insertleaf(this, root, leaf);
/*     */   }
/*     */ 
/*     */   public boolean update(Node arg1, DbvtAabbMm arg2, Vector3f arg3, float arg4) {
/* 156 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (leaf.volume.Contain(volume)) {
/* 157 */         return false;
/*     */       }
/* 159 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 160 */       tmp.set(margin, margin, margin);
/* 161 */       volume.Expand(tmp);
/* 162 */       volume.SignedExpand(velocity);
/* 163 */       update(leaf, volume);
/* 164 */       return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean update(Node leaf, DbvtAabbMm volume, Vector3f velocity) {
/* 168 */     if (leaf.volume.Contain(volume)) {
/* 169 */       return false;
/*     */     }
/* 171 */     volume.SignedExpand(velocity);
/* 172 */     update(leaf, volume);
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean update(Node arg1, DbvtAabbMm arg2, float arg3) {
/* 177 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (leaf.volume.Contain(volume)) {
/* 178 */         return false;
/*     */       }
/* 180 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 181 */       tmp.set(margin, margin, margin);
/* 182 */       volume.Expand(tmp);
/* 183 */       update(leaf, volume);
/* 184 */       return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void remove(Node leaf) {
/* 188 */     removeleaf(this, leaf);
/* 189 */     deletenode(this, leaf);
/* 190 */     this.leaves -= 1;
/*     */   }
/*     */ 
/*     */   public void write(IWriter iwriter) {
/* 194 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void clone(Dbvt dest) {
/* 198 */     clone(dest, null);
/*     */   }
/*     */ 
/*     */   public void clone(Dbvt dest, IClone iclone) {
/* 202 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public static int countLeaves(Node node) {
/* 206 */     if (node.isinternal()) {
/* 207 */       return countLeaves(node.childs[0]) + countLeaves(node.childs[1]);
/*     */     }
/*     */ 
/* 210 */     return 1;
/*     */   }
/*     */ 
/*     */   public static void extractLeaves(Node node, ObjectArrayList<Node> leaves)
/*     */   {
/* 215 */     if (node.isinternal()) {
/* 216 */       extractLeaves(node.childs[0], leaves);
/* 217 */       extractLeaves(node.childs[1], leaves);
/*     */     }
/*     */     else {
/* 220 */       leaves.add(node);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void enumNodes(Node root, ICollide policy)
/*     */   {
/* 226 */     policy.Process(root);
/* 227 */     if (root.isinternal()) {
/* 228 */       enumNodes(root.childs[0], policy);
/* 229 */       enumNodes(root.childs[1], policy);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void enumLeaves(Node root, ICollide policy)
/*     */   {
/* 235 */     if (root.isinternal()) {
/* 236 */       enumLeaves(root.childs[0], policy);
/* 237 */       enumLeaves(root.childs[1], policy);
/*     */     }
/*     */     else {
/* 240 */       policy.Process(root);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void collideTT(Node root0, Node root1, ICollide policy)
/*     */   {
/* 246 */     if ((root0 != null) && (root1 != null)) {
/* 247 */       ObjectArrayList stack = new ObjectArrayList(128);
/* 248 */       stack.add(new sStkNN(root0, root1));
/*     */       do {
/* 250 */         sStkNN p = (sStkNN)stack.remove(stack.size() - 1);
/* 251 */         if (p.a == p.b) {
/* 252 */           if (p.a.isinternal()) {
/* 253 */             stack.add(new sStkNN(p.a.childs[0], p.a.childs[0]));
/* 254 */             stack.add(new sStkNN(p.a.childs[1], p.a.childs[1]));
/* 255 */             stack.add(new sStkNN(p.a.childs[0], p.a.childs[1]));
/*     */           }
/*     */         }
/* 258 */         else if (DbvtAabbMm.Intersect(p.a.volume, p.b.volume)) {
/* 259 */           if (p.a.isinternal()) {
/* 260 */             if (p.b.isinternal()) {
/* 261 */               stack.add(new sStkNN(p.a.childs[0], p.b.childs[0]));
/* 262 */               stack.add(new sStkNN(p.a.childs[1], p.b.childs[0]));
/* 263 */               stack.add(new sStkNN(p.a.childs[0], p.b.childs[1]));
/* 264 */               stack.add(new sStkNN(p.a.childs[1], p.b.childs[1]));
/*     */             }
/*     */             else {
/* 267 */               stack.add(new sStkNN(p.a.childs[0], p.b));
/* 268 */               stack.add(new sStkNN(p.a.childs[1], p.b));
/*     */             }
/*     */ 
/*     */           }
/* 272 */           else if (p.b.isinternal()) {
/* 273 */             stack.add(new sStkNN(p.a, p.b.childs[0]));
/* 274 */             stack.add(new sStkNN(p.a, p.b.childs[1]));
/*     */           }
/*     */           else {
/* 277 */             policy.Process(p.a, p.b);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 282 */       while (stack.size() > 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void collideTT(Node root0, Node root1, Transform xform, ICollide policy)
/*     */   {
/* 288 */     if ((root0 != null) && (root1 != null)) {
/* 289 */       ObjectArrayList stack = new ObjectArrayList(128);
/* 290 */       stack.add(new sStkNN(root0, root1));
/*     */       do {
/* 292 */         sStkNN p = (sStkNN)stack.remove(stack.size() - 1);
/* 293 */         if (p.a == p.b) {
/* 294 */           if (p.a.isinternal()) {
/* 295 */             stack.add(new sStkNN(p.a.childs[0], p.a.childs[0]));
/* 296 */             stack.add(new sStkNN(p.a.childs[1], p.a.childs[1]));
/* 297 */             stack.add(new sStkNN(p.a.childs[0], p.a.childs[1]));
/*     */           }
/*     */         }
/* 300 */         else if (DbvtAabbMm.Intersect(p.a.volume, p.b.volume, xform)) {
/* 301 */           if (p.a.isinternal()) {
/* 302 */             if (p.b.isinternal()) {
/* 303 */               stack.add(new sStkNN(p.a.childs[0], p.b.childs[0]));
/* 304 */               stack.add(new sStkNN(p.a.childs[1], p.b.childs[0]));
/* 305 */               stack.add(new sStkNN(p.a.childs[0], p.b.childs[1]));
/* 306 */               stack.add(new sStkNN(p.a.childs[1], p.b.childs[1]));
/*     */             }
/*     */             else {
/* 309 */               stack.add(new sStkNN(p.a.childs[0], p.b));
/* 310 */               stack.add(new sStkNN(p.a.childs[1], p.b));
/*     */             }
/*     */ 
/*     */           }
/* 314 */           else if (p.b.isinternal()) {
/* 315 */             stack.add(new sStkNN(p.a, p.b.childs[0]));
/* 316 */             stack.add(new sStkNN(p.a, p.b.childs[1]));
/*     */           }
/*     */           else {
/* 319 */             policy.Process(p.a, p.b);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 324 */       while (stack.size() > 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void collideTT(Node arg0, Transform arg1, Node arg2, Transform arg3, ICollide arg4) {
/* 329 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform xform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 330 */       xform.inverse(xform0);
/* 331 */       xform.mul(xform1);
/* 332 */       collideTT(root0, root1, xform, policy);
/*     */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void collideTV(Node root, DbvtAabbMm volume, ICollide policy) {
/* 337 */     if (root != null) {
/* 338 */       ObjectArrayList stack = new ObjectArrayList(64);
/* 339 */       stack.add(root);
/*     */       do {
/* 341 */         Node n = (Node)stack.remove(stack.size() - 1);
/* 342 */         if (DbvtAabbMm.Intersect(n.volume, volume)) {
/* 343 */           if (n.isinternal()) {
/* 344 */             stack.add(n.childs[0]);
/* 345 */             stack.add(n.childs[1]);
/*     */           }
/*     */           else {
/* 348 */             policy.Process(n);
/*     */           }
/*     */         }
/*     */       }
/* 352 */       while (stack.size() > 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void collideRAY(Node arg0, Vector3f arg1, Vector3f arg2, ICollide arg3)
/*     */   {
/* 358 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (root != null) {
/* 359 */         Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 360 */         normal.normalize(direction);
/* 361 */         Vector3f invdir = localStack.get$javax$vecmath$Vector3f();
/* 362 */         invdir.set(1.0F / normal.x, 1.0F / normal.y, 1.0F / normal.z);
/* 363 */         int[] signs = { direction.x < 0.0F ? 1 : 0, direction.y < 0.0F ? 1 : 0, direction.z < 0.0F ? 1 : 0 };
/* 364 */         ObjectArrayList stack = new ObjectArrayList(64);
/* 365 */         stack.add(root);
/*     */         do {
/* 367 */           Node node = (Node)stack.remove(stack.size() - 1);
/* 368 */           if (DbvtAabbMm.Intersect(node.volume, origin, invdir, signs)) {
/* 369 */             if (node.isinternal()) {
/* 370 */               stack.add(node.childs[0]);
/* 371 */               stack.add(node.childs[1]);
/*     */             }
/*     */             else {
/* 374 */               policy.Process(node);
/*     */             }
/*     */           }
/*     */         }
/* 378 */         while (stack.size() != 0);
/*     */       }return; } finally {
/* 380 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void collideKDOP(Node root, Vector3f[] normals, float[] offsets, int count, ICollide policy) {
/* 384 */     if (root != null) {
/* 385 */       int inside = (1 << count) - 1;
/* 386 */       ObjectArrayList stack = new ObjectArrayList(64);
/* 387 */       int[] signs = new int[32];
/* 388 */       assert (count < 32);
/* 389 */       for (int i = 0; i < count; i++) {
/* 390 */         signs[i] = ((normals[i].x >= 0.0F ? 1 : 0) + (normals[i].y >= 0.0F ? 2 : 0) + (normals[i].z >= 0.0F ? 4 : 0));
/*     */       }
/*     */ 
/* 394 */       stack.add(new sStkNP(root, 0));
/*     */       do {
/* 396 */         sStkNP se = (sStkNP)stack.remove(stack.size() - 1);
/* 397 */         boolean out = false;
/* 398 */         int i = 0; for (int j = 1; (!out) && (i < count); j <<= 1) {
/* 399 */           if (0 == (se.mask & j)) {
/* 400 */             int side = se.node.volume.Classify(normals[i], offsets[i], signs[i]);
/* 401 */             switch (side) {
/*     */             case -1:
/* 403 */               out = true;
/* 404 */               break;
/*     */             case 1:
/* 406 */               se.mask |= j;
/*     */             }
/*     */           }
/* 398 */           i++;
/*     */         }
/*     */ 
/* 411 */         if (!out) {
/* 412 */           if ((se.mask != inside) && (se.node.isinternal())) {
/* 413 */             stack.add(new sStkNP(se.node.childs[0], se.mask));
/* 414 */             stack.add(new sStkNP(se.node.childs[1], se.mask));
/*     */           }
/* 417 */           else if (policy.AllLeaves(se.node)) {
/* 418 */             enumLeaves(se.node, policy);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 423 */       while (stack.size() != 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void collideOCL(Node root, Vector3f[] normals, float[] offsets, Vector3f sortaxis, int count, ICollide policy) {
/* 428 */     collideOCL(root, normals, offsets, sortaxis, count, policy, true);
/*     */   }
/*     */ 
/*     */   public static void collideOCL(Node root, Vector3f[] normals, float[] offsets, Vector3f sortaxis, int count, ICollide policy, boolean fullsort)
/*     */   {
/* 433 */     if (root != null) {
/* 434 */       int srtsgns = (sortaxis.x >= 0.0F ? 1 : 0) + (sortaxis.y >= 0.0F ? 2 : 0) + (sortaxis.z >= 0.0F ? 4 : 0);
/*     */ 
/* 437 */       int inside = (1 << count) - 1;
/* 438 */       ObjectArrayList stock = new ObjectArrayList();
/* 439 */       IntArrayList ifree = new IntArrayList();
/* 440 */       IntArrayList stack = new IntArrayList();
/* 441 */       int[] signs = new int[32];
/* 442 */       assert (count < 32);
/* 443 */       for (int i = 0; i < count; i++) {
/* 444 */         signs[i] = ((normals[i].x >= 0.0F ? 1 : 0) + (normals[i].y >= 0.0F ? 2 : 0) + (normals[i].z >= 0.0F ? 4 : 0));
/*     */       }
/*     */ 
/* 451 */       stack.add(allocate(ifree, stock, new sStkNPS(root, 0, root.volume.ProjectMinimum(sortaxis, srtsgns))));
/*     */       do
/*     */       {
/* 454 */         int id = stack.remove(stack.size() - 1);
/* 455 */         sStkNPS se = (sStkNPS)stock.getQuick(id);
/* 456 */         ifree.add(id);
/* 457 */         if (se.mask != inside) {
/* 458 */           boolean out = false;
/* 459 */           int i = 0; for (int j = 1; (!out) && (i < count); j <<= 1) {
/* 460 */             if (0 == (se.mask & j)) {
/* 461 */               int side = se.node.volume.Classify(normals[i], offsets[i], signs[i]);
/* 462 */               switch (side) {
/*     */               case -1:
/* 464 */                 out = true;
/* 465 */                 break;
/*     */               case 1:
/* 467 */                 se.mask |= j;
/*     */               }
/*     */             }
/* 459 */             i++;
/*     */           }
/*     */ 
/* 472 */           if (out);
/*     */         }
/* 476 */         else if (policy.Descent(se.node)) {
/* 477 */           if (se.node.isinternal()) {
/* 478 */             Node[] pns = { se.node.childs[0], se.node.childs[1] };
/* 479 */             sStkNPS[] nes = { new sStkNPS(pns[0], se.mask, pns[0].volume.ProjectMinimum(sortaxis, srtsgns)), new sStkNPS(pns[1], se.mask, pns[1].volume.ProjectMinimum(sortaxis, srtsgns)) };
/*     */ 
/* 482 */             int q = nes[0].value < nes[1].value ? 1 : 0;
/* 483 */             int j = stack.size();
/* 484 */             if ((fullsort) && (j > 0))
/*     */             {
/* 486 */               j = nearest(stack, stock, nes[q].value, 0, stack.size());
/* 487 */               stack.add(0);
/*     */ 
/* 491 */               for (int k = stack.size() - 1; k > j; k--) {
/* 492 */                 stack.set(k, stack.get(k - 1));
/*     */               }
/*     */ 
/* 495 */               stack.set(j, allocate(ifree, stock, nes[q]));
/*     */ 
/* 497 */               j = nearest(stack, stock, nes[(1 - q)].value, j, stack.size());
/* 498 */               stack.add(0);
/*     */ 
/* 502 */               for (int k = stack.size() - 1; k > j; k--) {
/* 503 */                 stack.set(k, stack.get(k - 1));
/*     */               }
/*     */ 
/* 506 */               stack.set(j, allocate(ifree, stock, nes[(1 - q)]));
/*     */             }
/*     */             else {
/* 509 */               stack.add(allocate(ifree, stock, nes[q]));
/* 510 */               stack.add(allocate(ifree, stock, nes[(1 - q)]));
/*     */             }
/*     */           }
/*     */           else {
/* 514 */             policy.Process(se.node, se.value);
/*     */           }
/*     */         }
/*     */       }
/* 518 */       while (stack.size() != 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void collideTU(Node root, ICollide policy)
/*     */   {
/* 524 */     if (root != null) {
/* 525 */       ObjectArrayList stack = new ObjectArrayList(64);
/* 526 */       stack.add(root);
/*     */       do {
/* 528 */         Node n = (Node)stack.remove(stack.size() - 1);
/* 529 */         if (policy.Descent(n)) {
/* 530 */           if (n.isinternal()) {
/* 531 */             stack.add(n.childs[0]);
/* 532 */             stack.add(n.childs[1]);
/*     */           }
/*     */           else {
/* 535 */             policy.Process(n);
/*     */           }
/*     */         }
/*     */       }
/* 539 */       while (stack.size() > 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int nearest(IntArrayList i, ObjectArrayList<sStkNPS> a, float v, int l, int h) {
/* 544 */     int m = 0;
/* 545 */     while (l < h) {
/* 546 */       m = l + h >> 1;
/* 547 */       if (((sStkNPS)a.getQuick(i.get(m))).value >= v) {
/* 548 */         l = m + 1;
/*     */       }
/*     */       else {
/* 551 */         h = m;
/*     */       }
/*     */     }
/* 554 */     return h;
/*     */   }
/*     */ 
/*     */   public static int allocate(IntArrayList ifree, ObjectArrayList<sStkNPS> stock, sStkNPS value)
/*     */   {
/*     */     int i;
/* 559 */     if (ifree.size() > 0) {
/* 560 */       int i = ifree.get(ifree.size() - 1);
/* 561 */       ifree.remove(ifree.size() - 1);
/* 562 */       ((sStkNPS)stock.getQuick(i)).set(value);
/*     */     }
/*     */     else {
/* 565 */       i = stock.size();
/* 566 */       stock.add(value);
/*     */     }
/* 568 */     return i;
/*     */   }
/*     */ 
/*     */   private static int indexof(Node node)
/*     */   {
/* 574 */     return node.parent.childs[1] == node ? 1 : 0;
/*     */   }
/*     */ 
/*     */   private static DbvtAabbMm merge(DbvtAabbMm a, DbvtAabbMm b, DbvtAabbMm out) {
/* 578 */     DbvtAabbMm.Merge(a, b, out);
/* 579 */     return out;
/*     */   }
/*     */ 
/*     */   private static float size(DbvtAabbMm arg0)
/*     */   {
/* 584 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f edges = a.Lengths(localStack.get$javax$vecmath$Vector3f());
/* 585 */       return edges.x * edges.y * edges.z + edges.x + edges.y + edges.z; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static void deletenode(Dbvt pdbvt, Node node)
/*     */   {
/* 591 */     pdbvt.free = node;
/*     */   }
/*     */ 
/*     */   private static void recursedeletenode(Dbvt pdbvt, Node node) {
/* 595 */     if (!node.isleaf()) {
/* 596 */       recursedeletenode(pdbvt, node.childs[0]);
/* 597 */       recursedeletenode(pdbvt, node.childs[1]);
/*     */     }
/* 599 */     if (node == pdbvt.root) {
/* 600 */       pdbvt.root = null;
/*     */     }
/* 602 */     deletenode(pdbvt, node);
/*     */   }
/*     */ 
/*     */   private static Node createnode(Dbvt pdbvt, Node parent, DbvtAabbMm volume, Object data)
/*     */   {
/*     */     Node node;
/* 607 */     if (pdbvt.free != null) {
/* 608 */       Node node = pdbvt.free;
/* 609 */       pdbvt.free = null;
/*     */     }
/*     */     else {
/* 612 */       node = new Node();
/*     */     }
/* 614 */     node.parent = parent;
/* 615 */     node.volume.set(volume);
/* 616 */     node.data = data;
/* 617 */     node.childs[1] = null;
/* 618 */     return node;
/*     */   }
/*     */ 
/*     */   private static void insertleaf(Dbvt pdbvt, Node root, Node leaf) {
/* 622 */     if (pdbvt.root == null) {
/* 623 */       pdbvt.root = leaf;
/* 624 */       leaf.parent = null;
/*     */     }
/*     */     else {
/* 627 */       if (!root.isleaf()) {
/*     */         do {
/* 629 */           if (DbvtAabbMm.Proximity(root.childs[0].volume, leaf.volume) < DbvtAabbMm.Proximity(root.childs[1].volume, leaf.volume))
/*     */           {
/* 631 */             root = root.childs[0];
/*     */           }
/*     */           else {
/* 634 */             root = root.childs[1];
/*     */           }
/*     */         }
/* 637 */         while (!root.isleaf());
/*     */       }
/* 639 */       Node prev = root.parent;
/* 640 */       Node node = createnode(pdbvt, prev, merge(leaf.volume, root.volume, new DbvtAabbMm()), null);
/* 641 */       if (prev != null) {
/* 642 */         prev.childs[indexof(root)] = node;
/* 643 */         node.childs[0] = root;
/* 644 */         root.parent = node;
/* 645 */         node.childs[1] = leaf;
/* 646 */         leaf.parent = node;
/*     */         do {
/* 648 */           if (prev.volume.Contain(node.volume)) break;
/* 649 */           DbvtAabbMm.Merge(prev.childs[0].volume, prev.childs[1].volume, prev.volume);
/*     */ 
/* 654 */           node = prev;
/*     */         }
/* 656 */         while (null != (prev = node.parent));
/*     */       }
/*     */       else {
/* 659 */         node.childs[0] = root;
/* 660 */         root.parent = node;
/* 661 */         node.childs[1] = leaf;
/* 662 */         leaf.parent = node;
/* 663 */         pdbvt.root = node;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Node removeleaf(Dbvt pdbvt, Node leaf) {
/* 669 */     if (leaf == pdbvt.root) {
/* 670 */       pdbvt.root = null;
/* 671 */       return null;
/*     */     }
/*     */ 
/* 674 */     Node parent = leaf.parent;
/* 675 */     Node prev = parent.parent;
/* 676 */     Node sibling = parent.childs[(1 - indexof(leaf))];
/* 677 */     if (prev != null) {
/* 678 */       prev.childs[indexof(parent)] = sibling;
/* 679 */       sibling.parent = prev;
/* 680 */       deletenode(pdbvt, parent);
/* 681 */       while (prev != null) {
/* 682 */         DbvtAabbMm pb = prev.volume;
/* 683 */         DbvtAabbMm.Merge(prev.childs[0].volume, prev.childs[1].volume, prev.volume);
/* 684 */         if (!DbvtAabbMm.NotEqual(pb, prev.volume)) break;
/* 685 */         prev = prev.parent;
/*     */       }
/*     */ 
/* 691 */       return prev != null ? prev : pdbvt.root;
/*     */     }
/*     */ 
/* 694 */     pdbvt.root = sibling;
/* 695 */     sibling.parent = null;
/* 696 */     deletenode(pdbvt, parent);
/* 697 */     return pdbvt.root;
/*     */   }
/*     */ 
/*     */   private static void fetchleaves(Dbvt pdbvt, Node root, ObjectArrayList<Node> leaves)
/*     */   {
/* 703 */     fetchleaves(pdbvt, root, leaves, -1);
/*     */   }
/*     */ 
/*     */   private static void fetchleaves(Dbvt pdbvt, Node root, ObjectArrayList<Node> leaves, int depth) {
/* 707 */     if ((root.isinternal()) && (depth != 0)) {
/* 708 */       fetchleaves(pdbvt, root.childs[0], leaves, depth - 1);
/* 709 */       fetchleaves(pdbvt, root.childs[1], leaves, depth - 1);
/* 710 */       deletenode(pdbvt, root);
/*     */     }
/*     */     else {
/* 713 */       leaves.add(root);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void split(ObjectArrayList<Node> arg0, ObjectArrayList<Node> arg1, ObjectArrayList<Node> arg2, Vector3f arg3, Vector3f arg4) {
/* 718 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 719 */       MiscUtil.resize(left, 0, Node.class);
/* 720 */       MiscUtil.resize(right, 0, Node.class);
/* 721 */       int i = 0; for (int ni = leaves.size(); i < ni; i++) {
/* 722 */         ((Node)leaves.getQuick(i)).volume.Center(tmp);
/* 723 */         tmp.sub(org);
/* 724 */         if (axis.dot(tmp) < 0.0F) {
/* 725 */           left.add(leaves.getQuick(i));
/*     */         }
/*     */         else
/* 728 */           right.add(leaves.getQuick(i));
/*     */       }
/*     */       return; } finally {
/* 731 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   private static DbvtAabbMm bounds(ObjectArrayList<Node> leaves) {
/* 734 */     DbvtAabbMm volume = new DbvtAabbMm(((Node)leaves.getQuick(0)).volume);
/* 735 */     int i = 1; for (int ni = leaves.size(); i < ni; i++) {
/* 736 */       merge(volume, ((Node)leaves.getQuick(i)).volume, volume);
/*     */     }
/* 738 */     return volume;
/*     */   }
/*     */ 
/*     */   private static void bottomup(Dbvt pdbvt, ObjectArrayList<Node> leaves) {
/* 742 */     DbvtAabbMm tmpVolume = new DbvtAabbMm();
/* 743 */     while (leaves.size() > 1) {
/* 744 */       float minsize = 3.4028235E+38F;
/* 745 */       int[] minidx = { -1, -1 };
/* 746 */       for (int i = 0; i < leaves.size(); i++) {
/* 747 */         for (int j = i + 1; j < leaves.size(); j++) {
/* 748 */           float sz = size(merge(((Node)leaves.getQuick(i)).volume, ((Node)leaves.getQuick(j)).volume, tmpVolume));
/* 749 */           if (sz < minsize) {
/* 750 */             minsize = sz;
/* 751 */             minidx[0] = i;
/* 752 */             minidx[1] = j;
/*     */           }
/*     */         }
/*     */       }
/* 756 */       Node[] n = { (Node)leaves.getQuick(minidx[0]), (Node)leaves.getQuick(minidx[1]) };
/* 757 */       Node p = createnode(pdbvt, null, merge(n[0].volume, n[1].volume, new DbvtAabbMm()), null);
/* 758 */       p.childs[0] = n[0];
/* 759 */       p.childs[1] = n[1];
/* 760 */       n[0].parent = p;
/* 761 */       n[1].parent = p;
/*     */ 
/* 763 */       leaves.setQuick(minidx[0], p);
/* 764 */       Collections.swap(leaves, minidx[1], leaves.size() - 1);
/* 765 */       leaves.removeQuick(leaves.size() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static Node topdown(Dbvt arg0, ObjectArrayList<Node> arg1, int arg2)
/*     */   {
/* 772 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (leaves.size() > 1) {
/* 773 */         if (leaves.size() > bu_treshold) {
/* 774 */           DbvtAabbMm vol = bounds(leaves);
/* 775 */           Vector3f org = vol.Center(localStack.get$javax$vecmath$Vector3f());
/* 776 */           ObjectArrayList[] sets = new ObjectArrayList[2];
/* 777 */           for (int i = 0; i < sets.length; i++) {
/* 778 */             sets[i] = new ObjectArrayList();
/*     */           }
/* 780 */           int bestaxis = -1;
/* 781 */           int bestmidp = leaves.size();
/* 782 */           int[][] splitcount = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
/*     */ 
/* 784 */           Vector3f x = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 786 */           for (int i = 0; i < leaves.size(); i++) {
/* 787 */             ((Node)leaves.getQuick(i)).volume.Center(x);
/* 788 */             x.sub(org);
/* 789 */             for (int j = 0; j < 3; j++) {
/* 790 */               splitcount[j][(x.dot(axis[j]) > 0.0F ? 1 : 0)] += 1;
/*     */             }
/*     */           }
/* 793 */           for (int i = 0; i < 3; i++) {
/* 794 */             if ((splitcount[i][0] > 0) && (splitcount[i][1] > 0)) {
/* 795 */               int midp = Math.abs(splitcount[i][0] - splitcount[i][1]);
/* 796 */               if (midp < bestmidp) {
/* 797 */                 bestaxis = i;
/* 798 */                 bestmidp = midp;
/*     */               }
/*     */             }
/*     */           }
/* 802 */           if (bestaxis >= 0)
/*     */           {
/* 805 */             split(leaves, sets[0], sets[1], org, axis[bestaxis]);
/*     */           }
/*     */           else
/*     */           {
/* 810 */             int i = 0; for (int ni = leaves.size(); i < ni; i++) {
/* 811 */               sets[(i & 0x1)].add(leaves.getQuick(i));
/*     */             }
/*     */           }
/* 814 */           Node node = createnode(pdbvt, null, vol, null);
/* 815 */           node.childs[0] = topdown(pdbvt, sets[0], bu_treshold);
/* 816 */           node.childs[1] = topdown(pdbvt, sets[1], bu_treshold);
/* 817 */           node.childs[0].parent = node;
/* 818 */           node.childs[1].parent = node;
/* 819 */           return node;
/*     */         }
/*     */ 
/* 822 */         bottomup(pdbvt, leaves);
/* 823 */         return (Node)leaves.getQuick(0);
/*     */       }
/*     */ 
/* 826 */       return (Node)leaves.getQuick(0); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static Node sort(Node n, Node[] r) {
/* 830 */     Node p = n.parent;
/* 831 */     assert (n.isinternal());
/*     */ 
/* 833 */     if ((p != null) && (p.hashCode() > n.hashCode())) {
/* 834 */       int i = indexof(n);
/* 835 */       int j = 1 - i;
/* 836 */       Node s = p.childs[j];
/* 837 */       Node q = p.parent;
/* 838 */       assert (n == p.childs[i]);
/* 839 */       if (q != null) {
/* 840 */         q.childs[indexof(p)] = n;
/*     */       }
/*     */       else {
/* 843 */         r[0] = n;
/*     */       }
/* 845 */       s.parent = n;
/* 846 */       p.parent = n;
/* 847 */       n.parent = q;
/* 848 */       p.childs[0] = n.childs[0];
/* 849 */       p.childs[1] = n.childs[1];
/* 850 */       n.childs[0].parent = p;
/* 851 */       n.childs[1].parent = p;
/* 852 */       n.childs[i] = p;
/* 853 */       n.childs[j] = s;
/*     */ 
/* 855 */       DbvtAabbMm.swap(p.volume, n.volume);
/* 856 */       return p;
/*     */     }
/* 858 */     return n;
/*     */   }
/*     */ 
/*     */   private static Node walkup(Node n, int count) {
/* 862 */     while ((n != null) && (count-- != 0)) {
/* 863 */       n = n.parent;
/*     */     }
/* 865 */     return n;
/*     */   }
/*     */ 
/*     */   public static class IClone
/*     */   {
/*     */     public void CloneLeaf(Dbvt.Node n)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class IWriter
/*     */   {
/*     */     public abstract void Prepare(Dbvt.Node paramNode, int paramInt);
/*     */ 
/*     */     public abstract void WriteNode(Dbvt.Node paramNode, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */     public abstract void WriteLeaf(Dbvt.Node paramNode, int paramInt1, int paramInt2);
/*     */   }
/*     */ 
/*     */   public static class ICollide
/*     */   {
/*     */     public void Process(Dbvt.Node n1, Dbvt.Node n2)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void Process(Dbvt.Node n)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void Process(Dbvt.Node n, float f)
/*     */     {
/* 945 */       Process(n);
/*     */     }
/*     */ 
/*     */     public boolean Descent(Dbvt.Node n) {
/* 949 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean AllLeaves(Dbvt.Node n) {
/* 953 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class sStkCLN
/*     */   {
/*     */     public Dbvt.Node node;
/*     */     public Dbvt.Node parent;
/*     */ 
/*     */     public sStkCLN(Dbvt.Node n, Dbvt.Node p)
/*     */     {
/* 932 */       this.node = n;
/* 933 */       this.parent = p;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class sStkNPS
/*     */   {
/*     */     public Dbvt.Node node;
/*     */     public int mask;
/*     */     public float value;
/*     */ 
/*     */     public sStkNPS()
/*     */     {
/*     */     }
/*     */ 
/*     */     public sStkNPS(Dbvt.Node n, int m, float v)
/*     */     {
/* 915 */       this.node = n;
/* 916 */       this.mask = m;
/* 917 */       this.value = v;
/*     */     }
/*     */ 
/*     */     public void set(sStkNPS o) {
/* 921 */       this.node = o.node;
/* 922 */       this.mask = o.mask;
/* 923 */       this.value = o.value;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class sStkNP
/*     */   {
/*     */     public Dbvt.Node node;
/*     */     public int mask;
/*     */ 
/*     */     public sStkNP(Dbvt.Node n, int m)
/*     */     {
/* 901 */       this.node = n;
/* 902 */       this.mask = m;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class sStkNN
/*     */   {
/*     */     public Dbvt.Node a;
/*     */     public Dbvt.Node b;
/*     */ 
/*     */     public sStkNN(Dbvt.Node na, Dbvt.Node nb)
/*     */     {
/* 891 */       this.a = na;
/* 892 */       this.b = nb;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class Node
/*     */   {
/* 871 */     public final DbvtAabbMm volume = new DbvtAabbMm();
/*     */     public Node parent;
/* 873 */     public final Node[] childs = new Node[2];
/*     */     public Object data;
/*     */ 
/*     */     public boolean isleaf()
/*     */     {
/* 877 */       return this.childs[1] == null;
/*     */     }
/*     */ 
/*     */     public boolean isinternal() {
/* 881 */       return !isleaf();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.Dbvt
 * JD-Core Version:    0.6.2
 */