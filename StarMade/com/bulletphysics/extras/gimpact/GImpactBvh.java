/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class GImpactBvh
/*     */ {
/*  43 */   protected BvhTree box_tree = new BvhTree();
/*     */   protected PrimitiveManagerBase primitive_manager;
/*     */ 
/*     */   public GImpactBvh()
/*     */   {
/*  50 */     this.primitive_manager = null;
/*     */   }
/*     */ 
/*     */   public GImpactBvh(PrimitiveManagerBase primitive_manager)
/*     */   {
/*  57 */     this.primitive_manager = primitive_manager;
/*     */   }
/*     */ 
/*     */   public BoxCollision.AABB getGlobalBox(BoxCollision.AABB out) {
/*  61 */     getNodeBound(0, out);
/*  62 */     return out;
/*     */   }
/*     */ 
/*     */   public void setPrimitiveManager(PrimitiveManagerBase primitive_manager) {
/*  66 */     this.primitive_manager = primitive_manager;
/*     */   }
/*     */ 
/*     */   public PrimitiveManagerBase getPrimitiveManager() {
/*  70 */     return this.primitive_manager;
/*     */   }
/*     */ 
/*     */   protected void refit()
/*     */   {
/*  75 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB leafbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  76 */       BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  77 */       BoxCollision.AABB temp_box = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/*  79 */       int nodecount = getNodeCount();
/*  80 */       while (nodecount-- != 0)
/*  81 */         if (isLeafNode(nodecount)) {
/*  82 */           this.primitive_manager.get_primitive_box(getNodeData(nodecount), leafbox);
/*  83 */           setNodeBound(nodecount, leafbox);
/*     */         }
/*     */         else
/*     */         {
/*  88 */           bound.invalidate();
/*     */ 
/*  90 */           int child_node = getLeftNode(nodecount);
/*  91 */           if (child_node != 0) {
/*  92 */             getNodeBound(child_node, temp_box);
/*  93 */             bound.merge(temp_box);
/*     */           }
/*     */ 
/*  96 */           child_node = getRightNode(nodecount);
/*  97 */           if (child_node != 0) {
/*  98 */             getNodeBound(child_node, temp_box);
/*  99 */             bound.merge(temp_box);
/*     */           }
/*     */ 
/* 102 */           setNodeBound(nodecount, bound);
/*     */         }
/*     */       return; } finally {
/* 105 */       localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void update()
/*     */   {
/* 112 */     refit();
/*     */   }
/*     */ 
/*     */   public void buildSet()
/*     */   {
/* 120 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BvhDataArray primitive_boxes = new BvhDataArray();
/* 121 */       primitive_boxes.resize(this.primitive_manager.get_primitive_count());
/*     */ 
/* 123 */       BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/* 125 */       for (int i = 0; i < primitive_boxes.size(); i++)
/*     */       {
/* 127 */         this.primitive_manager.get_primitive_box(i, tmpAABB);
/* 128 */         primitive_boxes.setBound(i, tmpAABB);
/*     */ 
/* 130 */         primitive_boxes.setData(i, i);
/* 133 */       }
/*     */ this.box_tree.build_tree(primitive_boxes);
/*     */       return; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean boxQuery(BoxCollision.AABB arg1, IntArrayList arg2)
/*     */   {
/* 140 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); int curIndex = 0;
/* 141 */       int numNodes = getNodeCount();
/*     */ 
/* 143 */       BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/* 145 */       while (curIndex < numNodes) {
/* 146 */         getNodeBound(curIndex, bound);
/*     */ 
/* 150 */         boolean aabbOverlap = bound.has_collision(box);
/* 151 */         boolean isleafnode = isLeafNode(curIndex);
/*     */ 
/* 153 */         if ((isleafnode) && (aabbOverlap)) {
/* 154 */           collided_results.add(getNodeData(curIndex));
/*     */         }
/*     */ 
/* 157 */         if ((aabbOverlap) || (isleafnode))
/*     */         {
/* 159 */           curIndex++;
/*     */         }
/*     */         else
/*     */         {
/* 163 */           curIndex += getEscapeNodeIndex(curIndex);
/*     */         }
/*     */       }
/* 166 */       if (collided_results.size() > 0) {
/* 167 */         return true;
/*     */       }
/* 169 */       return false; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean boxQueryTrans(BoxCollision.AABB arg1, Transform arg2, IntArrayList arg3)
/*     */   {
/* 176 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB transbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
/* 177 */       transbox.appy_transform(transform);
/* 178 */       return boxQuery(transbox, collided_results); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean rayQuery(Vector3f arg1, Vector3f arg2, IntArrayList arg3)
/*     */   {
/* 185 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); int curIndex = 0;
/* 186 */       int numNodes = getNodeCount();
/*     */ 
/* 188 */       BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/* 190 */       while (curIndex < numNodes) {
/* 191 */         getNodeBound(curIndex, bound);
/*     */ 
/* 195 */         boolean aabbOverlap = bound.collide_ray(ray_origin, ray_dir);
/* 196 */         boolean isleafnode = isLeafNode(curIndex);
/*     */ 
/* 198 */         if ((isleafnode) && (aabbOverlap)) {
/* 199 */           collided_results.add(getNodeData(curIndex));
/*     */         }
/*     */ 
/* 202 */         if ((aabbOverlap) || (isleafnode))
/*     */         {
/* 204 */           curIndex++;
/*     */         }
/*     */         else
/*     */         {
/* 208 */           curIndex += getEscapeNodeIndex(curIndex);
/*     */         }
/*     */       }
/* 211 */       if (collided_results.size() > 0) {
/* 212 */         return true;
/*     */       }
/* 214 */       return false; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean hasHierarchy()
/*     */   {
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isTrimesh()
/*     */   {
/* 228 */     return this.primitive_manager.is_trimesh();
/*     */   }
/*     */ 
/*     */   public int getNodeCount() {
/* 232 */     return this.box_tree.getNodeCount();
/*     */   }
/*     */ 
/*     */   public boolean isLeafNode(int nodeindex)
/*     */   {
/* 239 */     return this.box_tree.isLeafNode(nodeindex);
/*     */   }
/*     */ 
/*     */   public int getNodeData(int nodeindex) {
/* 243 */     return this.box_tree.getNodeData(nodeindex);
/*     */   }
/*     */ 
/*     */   public void getNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 247 */     this.box_tree.getNodeBound(nodeindex, bound);
/*     */   }
/*     */ 
/*     */   public void setNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 251 */     this.box_tree.setNodeBound(nodeindex, bound);
/*     */   }
/*     */ 
/*     */   public int getLeftNode(int nodeindex) {
/* 255 */     return this.box_tree.getLeftNode(nodeindex);
/*     */   }
/*     */ 
/*     */   public int getRightNode(int nodeindex) {
/* 259 */     return this.box_tree.getRightNode(nodeindex);
/*     */   }
/*     */ 
/*     */   public int getEscapeNodeIndex(int nodeindex) {
/* 263 */     return this.box_tree.getEscapeNodeIndex(nodeindex);
/*     */   }
/*     */ 
/*     */   public void getNodeTriangle(int nodeindex, PrimitiveTriangle triangle) {
/* 267 */     this.primitive_manager.get_primitive_triangle(getNodeData(nodeindex), triangle);
/*     */   }
/*     */ 
/*     */   public BvhTreeNodeArray get_node_pointer() {
/* 271 */     return this.box_tree.get_node_pointer();
/*     */   }
/*     */ 
/*     */   private static boolean _node_collision(GImpactBvh arg0, GImpactBvh arg1, BoxCollision.BoxBoxTransformCache arg2, int arg3, int arg4, boolean arg5) {
/* 275 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB box0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 276 */       boxset0.getNodeBound(node0, box0);
/* 277 */       BoxCollision.AABB box1 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 278 */       boxset1.getNodeBound(node1, box1);
/*     */ 
/* 280 */       return box0.overlapping_trans_cache(box1, trans_cache_1to0, complete_primitive_tests); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static void _find_collision_pairs_recursive(GImpactBvh boxset0, GImpactBvh boxset1, PairSet collision_pairs, BoxCollision.BoxBoxTransformCache trans_cache_1to0, int node0, int node1, boolean complete_primitive_tests)
/*     */   {
/* 289 */     if (!_node_collision(boxset0, boxset1, trans_cache_1to0, node0, node1, complete_primitive_tests))
/*     */     {
/* 292 */       return;
/*     */     }
/* 294 */     if (boxset0.isLeafNode(node0)) {
/* 295 */       if (boxset1.isLeafNode(node1))
/*     */       {
/* 297 */         collision_pairs.push_pair(boxset0.getNodeData(node0), boxset1.getNodeData(node1));
/* 298 */         return;
/*     */       }
/*     */ 
/* 302 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, node0, boxset1.getLeftNode(node1), false);
/*     */ 
/* 308 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, node0, boxset1.getRightNode(node1), false);
/*     */     }
/* 315 */     else if (boxset1.isLeafNode(node1))
/*     */     {
/* 317 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), node1, false);
/*     */ 
/* 324 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), node1, false);
/*     */     }
/*     */     else
/*     */     {
/* 331 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), boxset1.getLeftNode(node1), false);
/*     */ 
/* 337 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), boxset1.getRightNode(node1), false);
/*     */ 
/* 343 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), boxset1.getLeftNode(node1), false);
/*     */ 
/* 349 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), boxset1.getRightNode(node1), false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void find_collision(GImpactBvh arg0, Transform arg1, GImpactBvh arg2, Transform arg3, PairSet arg4)
/*     */   {
/* 361 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache(); if ((boxset0.getNodeCount() == 0) || (boxset1.getNodeCount() == 0)) {
/* 362 */         return;
/*     */       }
/*     */ 
/* 364 */       BoxCollision.BoxBoxTransformCache trans_cache_1to0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
/*     */ 
/* 366 */       trans_cache_1to0.calc_from_homogenic(trans0, trans1);
/*     */ 
/* 372 */       _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, 0, 0, true);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 379 */       localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactBvh
 * JD-Core Version:    0.6.2
 */