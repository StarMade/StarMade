/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class BvhTree
/*     */ {
/*  41 */   protected int num_nodes = 0;
/*  42 */   protected BvhTreeNodeArray node_array = new BvhTreeNodeArray();
/*     */ 
/*     */   protected int _calc_splitting_axis(BvhDataArray arg1, int arg2, int arg3) {
/*  45 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  46 */       means.set(0.0F, 0.0F, 0.0F);
/*  47 */       Vector3f variance = localStack.get$javax$vecmath$Vector3f();
/*  48 */       variance.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  50 */       int numIndices = endIndex - startIndex;
/*     */ 
/*  52 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  53 */       Vector3f diff2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  55 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  56 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  58 */       for (int i = startIndex; i < endIndex; i++) {
/*  59 */         primitive_boxes.getBoundMax(i, tmp1);
/*  60 */         primitive_boxes.getBoundMin(i, tmp2);
/*  61 */         center.add(tmp1, tmp2);
/*  62 */         center.scale(0.5F);
/*  63 */         means.add(center);
/*     */       }
/*  65 */       means.scale(1.0F / numIndices);
/*     */ 
/*  67 */       for (int i = startIndex; i < endIndex; i++) {
/*  68 */         primitive_boxes.getBoundMax(i, tmp1);
/*  69 */         primitive_boxes.getBoundMin(i, tmp2);
/*  70 */         center.add(tmp1, tmp2);
/*  71 */         center.scale(0.5F);
/*  72 */         diff2.sub(center, means);
/*  73 */         VectorUtil.mul(diff2, diff2, diff2);
/*  74 */         variance.add(diff2);
/*     */       }
/*  76 */       variance.scale(1.0F / (numIndices - 1));
/*     */ 
/*  78 */       return VectorUtil.maxAxis(variance); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   protected int _sort_and_calc_splitting_index(BvhDataArray arg1, int arg2, int arg3, int arg4) {
/*  82 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); int splitIndex = startIndex;
/*  83 */       int numIndices = endIndex - startIndex;
/*     */ 
/*  86 */       float splitValue = 0.0F;
/*     */ 
/*  88 */       Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  89 */       means.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  91 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  93 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  94 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  96 */       for (int i = startIndex; i < endIndex; i++) {
/*  97 */         primitive_boxes.getBoundMax(i, tmp1);
/*  98 */         primitive_boxes.getBoundMin(i, tmp2);
/*  99 */         center.add(tmp1, tmp2);
/* 100 */         center.scale(0.5F);
/* 101 */         means.add(center);
/*     */       }
/* 103 */       means.scale(1.0F / numIndices);
/*     */ 
/* 105 */       splitValue = VectorUtil.getCoord(means, splitAxis);
/*     */ 
/* 108 */       for (int i = startIndex; i < endIndex; i++) {
/* 109 */         primitive_boxes.getBoundMax(i, tmp1);
/* 110 */         primitive_boxes.getBoundMin(i, tmp2);
/* 111 */         center.add(tmp1, tmp2);
/* 112 */         center.scale(0.5F);
/*     */ 
/* 114 */         if (VectorUtil.getCoord(center, splitAxis) > splitValue)
/*     */         {
/* 116 */           primitive_boxes.swap(i, splitIndex);
/*     */ 
/* 118 */           splitIndex++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 131 */       int rangeBalancedIndices = numIndices / 3;
/* 132 */       boolean unbalanced = (splitIndex <= startIndex + rangeBalancedIndices) || (splitIndex >= endIndex - 1 - rangeBalancedIndices);
/*     */ 
/* 134 */       if (unbalanced) {
/* 135 */         splitIndex = startIndex + (numIndices >> 1);
/*     */       }
/*     */ 
/* 138 */       boolean unbal = (splitIndex == startIndex) || (splitIndex == endIndex);
/* 139 */       assert (!unbal);
/*     */ 
/* 141 */       return splitIndex; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   protected void _build_sub_tree(BvhDataArray arg1, int arg2, int arg3) {
/* 145 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); int curIndex = this.num_nodes;
/* 146 */       this.num_nodes += 1;
/*     */ 
/* 148 */       assert (endIndex - startIndex > 0);
/*     */ 
/* 150 */       if (endIndex - startIndex == 1)
/*     */       {
/* 154 */         this.node_array.set(curIndex, primitive_boxes, startIndex);
/*     */ 
/* 156 */         return;
/*     */       }
/*     */ 
/* 161 */       int splitIndex = _calc_splitting_axis(primitive_boxes, startIndex, endIndex);
/*     */ 
/* 163 */       splitIndex = _sort_and_calc_splitting_index(primitive_boxes, startIndex, endIndex, splitIndex);
/*     */ 
/* 167 */       BoxCollision.AABB node_bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 168 */       BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/* 170 */       node_bound.invalidate();
/*     */ 
/* 172 */       for (int i = startIndex; i < endIndex; i++) {
/* 173 */         primitive_boxes.getBound(i, tmpAABB);
/* 174 */         node_bound.merge(tmpAABB); } 
/*     */ setNodeBound(curIndex, node_bound);
/*     */ 
/* 180 */       _build_sub_tree(primitive_boxes, startIndex, splitIndex);
/*     */ 
/* 183 */       _build_sub_tree(primitive_boxes, splitIndex, endIndex);
/*     */ 
/* 185 */       this.node_array.setEscapeIndex(curIndex, this.num_nodes - curIndex);
/*     */       return; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void build_tree(BvhDataArray primitive_boxes) {
/* 190 */     this.num_nodes = 0;
/*     */ 
/* 192 */     this.node_array.resize(primitive_boxes.size() * 2);
/*     */ 
/* 194 */     _build_sub_tree(primitive_boxes, 0, primitive_boxes.size());
/*     */   }
/*     */ 
/*     */   public void clearNodes() {
/* 198 */     this.node_array.clear();
/* 199 */     this.num_nodes = 0;
/*     */   }
/*     */ 
/*     */   public int getNodeCount() {
/* 203 */     return this.num_nodes;
/*     */   }
/*     */ 
/*     */   public boolean isLeafNode(int nodeindex)
/*     */   {
/* 210 */     return this.node_array.isLeafNode(nodeindex);
/*     */   }
/*     */ 
/*     */   public int getNodeData(int nodeindex) {
/* 214 */     return this.node_array.getDataIndex(nodeindex);
/*     */   }
/*     */ 
/*     */   public void getNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 218 */     this.node_array.getBound(nodeindex, bound);
/*     */   }
/*     */ 
/*     */   public void setNodeBound(int nodeindex, BoxCollision.AABB bound) {
/* 222 */     this.node_array.setBound(nodeindex, bound);
/*     */   }
/*     */ 
/*     */   public int getLeftNode(int nodeindex) {
/* 226 */     return nodeindex + 1;
/*     */   }
/*     */ 
/*     */   public int getRightNode(int nodeindex) {
/* 230 */     if (this.node_array.isLeafNode(nodeindex + 1)) {
/* 231 */       return nodeindex + 2;
/*     */     }
/* 233 */     return nodeindex + 1 + this.node_array.getEscapeIndex(nodeindex + 1);
/*     */   }
/*     */ 
/*     */   public int getEscapeNodeIndex(int nodeindex) {
/* 237 */     return this.node_array.getEscapeIndex(nodeindex);
/*     */   }
/*     */ 
/*     */   public BvhTreeNodeArray get_node_pointer() {
/* 241 */     return this.node_array;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhTree
 * JD-Core Version:    0.6.2
 */