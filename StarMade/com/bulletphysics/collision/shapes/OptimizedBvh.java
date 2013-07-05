/*      */ package com.bulletphysics.collision.shapes;
/*      */ 
/*      */ import com.bulletphysics..Stack;
/*      */ import com.bulletphysics.linearmath.AabbUtil2;
/*      */ import com.bulletphysics.linearmath.MiscUtil;
/*      */ import com.bulletphysics.linearmath.VectorUtil;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import java.io.Serializable;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ public class OptimizedBvh
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*      */   private static final boolean DEBUG_TREE_BUILDING = false;
/*   48 */   private static int gStackDepth = 0;
/*   49 */   private static int gMaxStackDepth = 0;
/*      */ 
/*   51 */   private static int maxIterations = 0;
/*      */   public static final int MAX_SUBTREE_SIZE_IN_BYTES = 2048;
/*      */   public static final int MAX_NUM_PARTS_IN_BITS = 10;
/*      */   private final ObjectArrayList<OptimizedBvhNode> leafNodes;
/*      */   private final ObjectArrayList<OptimizedBvhNode> contiguousNodes;
/*      */   private QuantizedBvhNodes quantizedLeafNodes;
/*      */   private QuantizedBvhNodes quantizedContiguousNodes;
/*      */   private int curNodeIndex;
/*      */   private boolean useQuantization;
/*      */   private final Vector3f bvhAabbMin;
/*      */   private final Vector3f bvhAabbMax;
/*      */   private final Vector3f bvhQuantization;
/*      */   protected TraversalMode traversalMode;
/*      */   protected final ObjectArrayList<BvhSubtreeInfo> SubtreeHeaders;
/*      */   protected int subtreeHeaderCount;
/*      */ 
/*      */   public OptimizedBvh()
/*      */   {
/*   62 */     this.leafNodes = new ObjectArrayList();
/*   63 */     this.contiguousNodes = new ObjectArrayList();
/*      */ 
/*   65 */     this.quantizedLeafNodes = new QuantizedBvhNodes();
/*   66 */     this.quantizedContiguousNodes = new QuantizedBvhNodes();
/*      */ 
/*   72 */     this.bvhAabbMin = new Vector3f();
/*   73 */     this.bvhAabbMax = new Vector3f();
/*   74 */     this.bvhQuantization = new Vector3f();
/*      */ 
/*   76 */     this.traversalMode = TraversalMode.STACKLESS;
/*   77 */     this.SubtreeHeaders = new ObjectArrayList();
/*      */   }
/*      */ 
/*      */   public void setInternalNodeAabbMin(int nodeIndex, Vector3f aabbMin)
/*      */   {
/*   84 */     if (this.useQuantization) {
/*   85 */       this.quantizedContiguousNodes.setQuantizedAabbMin(nodeIndex, quantizeWithClamp(aabbMin));
/*      */     }
/*      */     else
/*   88 */       ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMinOrg.set(aabbMin);
/*      */   }
/*      */ 
/*      */   public void setInternalNodeAabbMax(int nodeIndex, Vector3f aabbMax)
/*      */   {
/*   93 */     if (this.useQuantization) {
/*   94 */       this.quantizedContiguousNodes.setQuantizedAabbMax(nodeIndex, quantizeWithClamp(aabbMax));
/*      */     }
/*      */     else
/*   97 */       ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMaxOrg.set(aabbMax);
/*      */   }
/*      */ 
/*      */   public Vector3f getAabbMin(int nodeIndex)
/*      */   {
/*  102 */     if (this.useQuantization) {
/*  103 */       Vector3f tmp = new Vector3f();
/*  104 */       unQuantize(tmp, this.quantizedLeafNodes.getQuantizedAabbMin(nodeIndex));
/*  105 */       return tmp;
/*      */     }
/*      */ 
/*  109 */     return ((OptimizedBvhNode)this.leafNodes.getQuick(nodeIndex)).aabbMinOrg;
/*      */   }
/*      */ 
/*      */   public Vector3f getAabbMax(int nodeIndex) {
/*  113 */     if (this.useQuantization) {
/*  114 */       Vector3f tmp = new Vector3f();
/*  115 */       unQuantize(tmp, this.quantizedLeafNodes.getQuantizedAabbMax(nodeIndex));
/*  116 */       return tmp;
/*      */     }
/*      */ 
/*  120 */     return ((OptimizedBvhNode)this.leafNodes.getQuick(nodeIndex)).aabbMaxOrg;
/*      */   }
/*      */ 
/*      */   public void setQuantizationValues(Vector3f aabbMin, Vector3f aabbMax) {
/*  124 */     setQuantizationValues(aabbMin, aabbMax, 1.0F);
/*      */   }
/*      */ 
/*      */   public void setQuantizationValues(Vector3f arg1, Vector3f arg2, float arg3)
/*      */   {
/*  129 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f clampValue = localStack.get$javax$vecmath$Vector3f();
/*  130 */       clampValue.set(quantizationMargin, quantizationMargin, quantizationMargin);
/*  131 */       this.bvhAabbMin.sub(aabbMin, clampValue);
/*  132 */       this.bvhAabbMax.add(aabbMax, clampValue);
/*  133 */       Vector3f aabbSize = localStack.get$javax$vecmath$Vector3f();
/*  134 */       aabbSize.sub(this.bvhAabbMax, this.bvhAabbMin);
/*  135 */       this.bvhQuantization.set(65535.0F, 65535.0F, 65535.0F);
/*  136 */       VectorUtil.div(this.bvhQuantization, this.bvhQuantization, aabbSize);
/*      */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   public void setInternalNodeEscapeIndex(int nodeIndex, int escapeIndex) {
/*  140 */     if (this.useQuantization) {
/*  141 */       this.quantizedContiguousNodes.setEscapeIndexOrTriangleIndex(nodeIndex, -escapeIndex);
/*      */     }
/*      */     else
/*  144 */       ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).escapeIndex = escapeIndex;
/*      */   }
/*      */ 
/*      */   public void mergeInternalNodeAabb(int nodeIndex, Vector3f newAabbMin, Vector3f newAabbMax)
/*      */   {
/*  149 */     if (this.useQuantization)
/*      */     {
/*  153 */       long quantizedAabbMin = quantizeWithClamp(newAabbMin);
/*  154 */       long quantizedAabbMax = quantizeWithClamp(newAabbMax);
/*  155 */       for (int i = 0; i < 3; i++) {
/*  156 */         if (this.quantizedContiguousNodes.getQuantizedAabbMin(nodeIndex, i) > QuantizedBvhNodes.getCoord(quantizedAabbMin, i)) {
/*  157 */           this.quantizedContiguousNodes.setQuantizedAabbMin(nodeIndex, i, QuantizedBvhNodes.getCoord(quantizedAabbMin, i));
/*      */         }
/*      */ 
/*  160 */         if (this.quantizedContiguousNodes.getQuantizedAabbMax(nodeIndex, i) < QuantizedBvhNodes.getCoord(quantizedAabbMax, i)) {
/*  161 */           this.quantizedContiguousNodes.setQuantizedAabbMax(nodeIndex, i, QuantizedBvhNodes.getCoord(quantizedAabbMax, i));
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  167 */       VectorUtil.setMin(((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMinOrg, newAabbMin);
/*  168 */       VectorUtil.setMax(((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMaxOrg, newAabbMax);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void swapLeafNodes(int i, int splitIndex) {
/*  173 */     if (this.useQuantization) {
/*  174 */       this.quantizedLeafNodes.swap(i, splitIndex);
/*      */     }
/*      */     else
/*      */     {
/*  178 */       OptimizedBvhNode tmp = (OptimizedBvhNode)this.leafNodes.getQuick(i);
/*  179 */       this.leafNodes.setQuick(i, this.leafNodes.getQuick(splitIndex));
/*  180 */       this.leafNodes.setQuick(splitIndex, tmp);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void assignInternalNodeFromLeafNode(int internalNode, int leafNodeIndex) {
/*  185 */     if (this.useQuantization) {
/*  186 */       this.quantizedContiguousNodes.set(internalNode, this.quantizedLeafNodes, leafNodeIndex);
/*      */     }
/*      */     else
/*  189 */       ((OptimizedBvhNode)this.contiguousNodes.getQuick(internalNode)).set((OptimizedBvhNode)this.leafNodes.getQuick(leafNodeIndex));
/*      */   }
/*      */ 
/*      */   public void build(StridingMeshInterface arg1, boolean arg2, Vector3f arg3, Vector3f arg4)
/*      */   {
/*  279 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); this.useQuantization = useQuantizedAabbCompression;
/*      */ 
/*  283 */       int numLeafNodes = 0;
/*      */ 
/*  285 */       if (this.useQuantization)
/*      */       {
/*  287 */         setQuantizationValues(_aabbMin, _aabbMax);
/*      */ 
/*  289 */         QuantizedNodeTriangleCallback callback = new QuantizedNodeTriangleCallback(this.quantizedLeafNodes, this);
/*      */ 
/*  291 */         triangles.internalProcessAllTriangles(callback, this.bvhAabbMin, this.bvhAabbMax);
/*      */ 
/*  294 */         numLeafNodes = this.quantizedLeafNodes.size();
/*      */ 
/*  296 */         this.quantizedContiguousNodes.resize(2 * numLeafNodes);
/*      */       }
/*      */       else {
/*  299 */         NodeTriangleCallback callback = new NodeTriangleCallback(this.leafNodes);
/*      */ 
/*  301 */         Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
/*  302 */         aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  303 */         Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  304 */         aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*      */ 
/*  306 */         triangles.internalProcessAllTriangles(callback, aabbMin, aabbMax);
/*      */ 
/*  309 */         numLeafNodes = this.leafNodes.size();
/*      */ 
/*  313 */         MiscUtil.resize(this.contiguousNodes, 2 * numLeafNodes, OptimizedBvhNode.class);
/*      */       }
/*      */ 
/*  316 */       this.curNodeIndex = 0;
/*      */ 
/*  318 */       buildTree(0, numLeafNodes);
/*      */ 
/*  321 */       if ((this.useQuantization) && (this.SubtreeHeaders.size() == 0)) {
/*  322 */         BvhSubtreeInfo subtree = new BvhSubtreeInfo();
/*  323 */         this.SubtreeHeaders.add(subtree);
/*      */ 
/*  325 */         subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, 0);
/*  326 */         subtree.rootNodeIndex = 0;
/*  327 */         subtree.subtreeSize = (this.quantizedContiguousNodes.isLeafNode(0) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(0));
/*  331 */       }
/*      */ this.subtreeHeaderCount = this.SubtreeHeaders.size();
/*      */ 
/*  334 */       this.quantizedLeafNodes.clear();
/*  335 */       this.leafNodes.clear();
/*      */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   public void refit(StridingMeshInterface arg1) {
/*  339 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); if (this.useQuantization)
/*      */       {
/*  341 */         Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  342 */         meshInterface.calculateAabbBruteForce(aabbMin, aabbMax);
/*      */ 
/*  344 */         setQuantizationValues(aabbMin, aabbMax);
/*      */ 
/*  346 */         updateBvhNodes(meshInterface, 0, this.curNodeIndex, 0);
/*      */ 
/*  351 */         for (int i = 0; i < this.SubtreeHeaders.size(); i++) {
/*  352 */           BvhSubtreeInfo subtree = (BvhSubtreeInfo)this.SubtreeHeaders.getQuick(i);
/*  353 */           subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, subtree.rootNodeIndex);
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  359 */         build(meshInterface, false, null, null);
/*      */       }return; } finally {
/*  361 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   public void refitPartial(StridingMeshInterface meshInterface, Vector3f aabbMin, Vector3f aabbMax) {
/*  364 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */   public void updateBvhNodes(StridingMeshInterface arg1, int arg2, int arg3, int arg4)
/*      */   {
/*  402 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); assert (this.useQuantization);
/*      */ 
/*  404 */       int curNodeSubPart = -1;
/*      */ 
/*  406 */       Vector3f[] triangleVerts = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/*  407 */       Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  408 */       Vector3f meshScaling = meshInterface.getScaling(localStack.get$javax$vecmath$Vector3f());
/*      */ 
/*  410 */       VertexData data = null;
/*      */ 
/*  412 */       for (int i = endNode - 1; i >= firstNode; i--) {
/*  413 */         QuantizedBvhNodes curNodes = this.quantizedContiguousNodes;
/*  414 */         int curNodeId = i;
/*      */ 
/*  416 */         if (curNodes.isLeafNode(curNodeId))
/*      */         {
/*  418 */           int nodeSubPart = curNodes.getPartId(curNodeId);
/*  419 */           int nodeTriangleIndex = curNodes.getTriangleIndex(curNodeId);
/*  420 */           if (nodeSubPart != curNodeSubPart) {
/*  421 */             if (curNodeSubPart >= 0) {
/*  422 */               meshInterface.unLockReadOnlyVertexBase(curNodeSubPart);
/*      */             }
/*  424 */             data = meshInterface.getLockedReadOnlyVertexIndexBase(nodeSubPart);
/*      */           }
/*      */ 
/*  428 */           data.getTriangle(nodeTriangleIndex * 3, meshScaling, triangleVerts);
/*      */ 
/*  430 */           aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  431 */           aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  432 */           VectorUtil.setMin(aabbMin, triangleVerts[0]);
/*  433 */           VectorUtil.setMax(aabbMax, triangleVerts[0]);
/*  434 */           VectorUtil.setMin(aabbMin, triangleVerts[1]);
/*  435 */           VectorUtil.setMax(aabbMax, triangleVerts[1]);
/*  436 */           VectorUtil.setMin(aabbMin, triangleVerts[2]);
/*  437 */           VectorUtil.setMax(aabbMax, triangleVerts[2]);
/*      */ 
/*  439 */           curNodes.setQuantizedAabbMin(curNodeId, quantizeWithClamp(aabbMin));
/*  440 */           curNodes.setQuantizedAabbMax(curNodeId, quantizeWithClamp(aabbMax));
/*      */         }
/*      */         else
/*      */         {
/*  446 */           int leftChildNodeId = i + 1;
/*      */ 
/*  448 */           int rightChildNodeId = this.quantizedContiguousNodes.isLeafNode(leftChildNodeId) ? i + 2 : i + 1 + this.quantizedContiguousNodes.getEscapeIndex(leftChildNodeId);
/*      */ 
/*  450 */           for (int i2 = 0; i2 < 3; i2++) {
/*  451 */             curNodes.setQuantizedAabbMin(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMin(leftChildNodeId, i2));
/*  452 */             if (curNodes.getQuantizedAabbMin(curNodeId, i2) > this.quantizedContiguousNodes.getQuantizedAabbMin(rightChildNodeId, i2)) {
/*  453 */               curNodes.setQuantizedAabbMin(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMin(rightChildNodeId, i2));
/*      */             }
/*      */ 
/*  456 */             curNodes.setQuantizedAabbMax(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMax(leftChildNodeId, i2));
/*  457 */             if (curNodes.getQuantizedAabbMax(curNodeId, i2) < this.quantizedContiguousNodes.getQuantizedAabbMax(rightChildNodeId, i2)) {
/*  458 */               curNodes.setQuantizedAabbMax(curNodeId, i2, this.quantizedContiguousNodes.getQuantizedAabbMax(rightChildNodeId, i2));
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  464 */       if (curNodeSubPart >= 0)
/*  465 */         meshInterface.unLockReadOnlyVertexBase(curNodeSubPart);
/*      */       return; } finally {
/*  467 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   protected void buildTree(int arg1, int arg2)
/*      */   {
/*  480 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); int numIndices = endIndex - startIndex;
/*  481 */       int curIndex = this.curNodeIndex;
/*      */ 
/*  483 */       assert (numIndices > 0);
/*      */ 
/*  485 */       if (numIndices == 1)
/*      */       {
/*  492 */         assignInternalNodeFromLeafNode(this.curNodeIndex, startIndex);
/*      */ 
/*  494 */         this.curNodeIndex += 1;
/*  495 */         return;
/*      */       }
/*      */ 
/*  499 */       int splitAxis = calcSplittingAxis(startIndex, endIndex);
/*      */ 
/*  501 */       int splitIndex = sortAndCalcSplittingIndex(startIndex, endIndex, splitAxis);
/*      */ 
/*  503 */       int internalNodeIndex = this.curNodeIndex;
/*      */ 
/*  505 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  506 */       tmp1.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  507 */       setInternalNodeAabbMax(this.curNodeIndex, tmp1);
/*  508 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  509 */       tmp2.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  510 */       setInternalNodeAabbMin(this.curNodeIndex, tmp2);
/*      */ 
/*  512 */       for (int i = startIndex; i < endIndex; i++) {
/*  513 */         mergeInternalNodeAabb(this.curNodeIndex, getAabbMin(i), getAabbMax(i));
/*      */       }
/*      */ 
/*  516 */       this.curNodeIndex += 1;
/*      */ 
/*  520 */       int leftChildNodexIndex = this.curNodeIndex;
/*      */ 
/*  523 */       buildTree(startIndex, splitIndex);
/*      */ 
/*  525 */       int rightChildNodexIndex = this.curNodeIndex;
/*      */ 
/*  527 */       buildTree(splitIndex, endIndex);
/*      */ 
/*  535 */       int escapeIndex = this.curNodeIndex - curIndex;
/*      */ 
/*  537 */       if (this.useQuantization)
/*      */       {
/*  539 */         int sizeQuantizedNode = QuantizedBvhNodes.getNodeSize();
/*  540 */         int treeSizeInBytes = escapeIndex * sizeQuantizedNode;
/*  541 */         if (treeSizeInBytes > 2048) {
/*  542 */           updateSubtreeHeaders(leftChildNodexIndex, rightChildNodexIndex);
/*      */         }
/*  546 */       }
/*      */ setInternalNodeEscapeIndex(internalNodeIndex, escapeIndex);
/*      */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   protected boolean testQuantizedAabbAgainstQuantizedAabb(long aabbMin1, long aabbMax1, long aabbMin2, long aabbMax2) {
/*  550 */     int aabbMin1_0 = QuantizedBvhNodes.getCoord(aabbMin1, 0);
/*  551 */     int aabbMin1_1 = QuantizedBvhNodes.getCoord(aabbMin1, 1);
/*  552 */     int aabbMin1_2 = QuantizedBvhNodes.getCoord(aabbMin1, 2);
/*      */ 
/*  554 */     int aabbMax1_0 = QuantizedBvhNodes.getCoord(aabbMax1, 0);
/*  555 */     int aabbMax1_1 = QuantizedBvhNodes.getCoord(aabbMax1, 1);
/*  556 */     int aabbMax1_2 = QuantizedBvhNodes.getCoord(aabbMax1, 2);
/*      */ 
/*  558 */     int aabbMin2_0 = QuantizedBvhNodes.getCoord(aabbMin2, 0);
/*  559 */     int aabbMin2_1 = QuantizedBvhNodes.getCoord(aabbMin2, 1);
/*  560 */     int aabbMin2_2 = QuantizedBvhNodes.getCoord(aabbMin2, 2);
/*      */ 
/*  562 */     int aabbMax2_0 = QuantizedBvhNodes.getCoord(aabbMax2, 0);
/*  563 */     int aabbMax2_1 = QuantizedBvhNodes.getCoord(aabbMax2, 1);
/*  564 */     int aabbMax2_2 = QuantizedBvhNodes.getCoord(aabbMax2, 2);
/*      */ 
/*  566 */     boolean overlap = true;
/*  567 */     overlap = (aabbMin1_0 > aabbMax2_0) || (aabbMax1_0 < aabbMin2_0) ? false : overlap;
/*  568 */     overlap = (aabbMin1_2 > aabbMax2_2) || (aabbMax1_2 < aabbMin2_2) ? false : overlap;
/*  569 */     overlap = (aabbMin1_1 > aabbMax2_1) || (aabbMax1_1 < aabbMin2_1) ? false : overlap;
/*  570 */     return overlap;
/*      */   }
/*      */ 
/*      */   protected void updateSubtreeHeaders(int leftChildNodexIndex, int rightChildNodexIndex) {
/*  574 */     assert (this.useQuantization);
/*      */ 
/*  577 */     int leftSubTreeSize = this.quantizedContiguousNodes.isLeafNode(leftChildNodexIndex) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(leftChildNodexIndex);
/*  578 */     int leftSubTreeSizeInBytes = leftSubTreeSize * QuantizedBvhNodes.getNodeSize();
/*      */ 
/*  581 */     int rightSubTreeSize = this.quantizedContiguousNodes.isLeafNode(rightChildNodexIndex) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(rightChildNodexIndex);
/*  582 */     int rightSubTreeSizeInBytes = rightSubTreeSize * QuantizedBvhNodes.getNodeSize();
/*      */ 
/*  584 */     if (leftSubTreeSizeInBytes <= 2048) {
/*  585 */       BvhSubtreeInfo subtree = new BvhSubtreeInfo();
/*  586 */       this.SubtreeHeaders.add(subtree);
/*      */ 
/*  588 */       subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, leftChildNodexIndex);
/*  589 */       subtree.rootNodeIndex = leftChildNodexIndex;
/*  590 */       subtree.subtreeSize = leftSubTreeSize;
/*      */     }
/*      */ 
/*  593 */     if (rightSubTreeSizeInBytes <= 2048) {
/*  594 */       BvhSubtreeInfo subtree = new BvhSubtreeInfo();
/*  595 */       this.SubtreeHeaders.add(subtree);
/*      */ 
/*  597 */       subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, rightChildNodexIndex);
/*  598 */       subtree.rootNodeIndex = rightChildNodexIndex;
/*  599 */       subtree.subtreeSize = rightSubTreeSize;
/*      */     }
/*      */ 
/*  603 */     this.subtreeHeaderCount = this.SubtreeHeaders.size();
/*      */   }
/*      */ 
/*      */   protected int sortAndCalcSplittingIndex(int arg1, int arg2, int arg3)
/*      */   {
/*  608 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); int splitIndex = startIndex;
/*  609 */       int numIndices = endIndex - startIndex;
/*      */ 
/*  612 */       Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  613 */       means.set(0.0F, 0.0F, 0.0F);
/*  614 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  615 */       for (int i = startIndex; i < endIndex; i++) {
/*  616 */         center.add(getAabbMax(i), getAabbMin(i));
/*  617 */         center.scale(0.5F);
/*  618 */         means.add(center);
/*      */       }
/*  620 */       means.scale(1.0F / numIndices);
/*      */ 
/*  622 */       float splitValue = VectorUtil.getCoord(means, splitAxis);
/*      */ 
/*  625 */       for (i = startIndex; i < endIndex; i++)
/*      */       {
/*  627 */         center.add(getAabbMax(i), getAabbMin(i));
/*  628 */         center.scale(0.5F);
/*      */ 
/*  630 */         if (VectorUtil.getCoord(center, splitAxis) > splitValue)
/*      */         {
/*  632 */           swapLeafNodes(i, splitIndex);
/*  633 */           splitIndex++;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  646 */       int rangeBalancedIndices = numIndices / 3;
/*  647 */       boolean unbalanced = (splitIndex <= startIndex + rangeBalancedIndices) || (splitIndex >= endIndex - 1 - rangeBalancedIndices);
/*      */ 
/*  649 */       if (unbalanced) {
/*  650 */         splitIndex = startIndex + (numIndices >> 1);
/*      */       }
/*      */ 
/*  653 */       boolean unbal = (splitIndex == startIndex) || (splitIndex == endIndex);
/*  654 */       assert (!unbal);
/*      */ 
/*  656 */       return splitIndex; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   protected int calcSplittingAxis(int arg1, int arg2)
/*      */   {
/*  662 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f means = localStack.get$javax$vecmath$Vector3f();
/*  663 */       means.set(0.0F, 0.0F, 0.0F);
/*  664 */       Vector3f variance = localStack.get$javax$vecmath$Vector3f();
/*  665 */       variance.set(0.0F, 0.0F, 0.0F);
/*  666 */       int numIndices = endIndex - startIndex;
/*      */ 
/*  668 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  669 */       for (int i = startIndex; i < endIndex; i++) {
/*  670 */         center.add(getAabbMax(i), getAabbMin(i));
/*  671 */         center.scale(0.5F);
/*  672 */         means.add(center);
/*      */       }
/*  674 */       means.scale(1.0F / numIndices);
/*      */ 
/*  676 */       Vector3f diff2 = localStack.get$javax$vecmath$Vector3f();
/*  677 */       for (i = startIndex; i < endIndex; i++) {
/*  678 */         center.add(getAabbMax(i), getAabbMin(i));
/*  679 */         center.scale(0.5F);
/*  680 */         diff2.sub(center, means);
/*      */ 
/*  682 */         VectorUtil.mul(diff2, diff2, diff2);
/*  683 */         variance.add(diff2);
/*      */       }
/*  685 */       variance.scale(1.0F / (numIndices - 1.0F));
/*      */ 
/*  687 */       return VectorUtil.maxAxis(variance); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   public void reportAabbOverlappingNodex(NodeOverlapCallback nodeCallback, Vector3f aabbMin, Vector3f aabbMax)
/*      */   {
/*  693 */     if (this.useQuantization)
/*      */     {
/*  697 */       long quantizedQueryAabbMin = quantizeWithClamp(aabbMin);
/*  698 */       long quantizedQueryAabbMax = quantizeWithClamp(aabbMax);
/*      */ 
/*  701 */       switch (1.$SwitchMap$com$bulletphysics$collision$shapes$TraversalMode[this.traversalMode.ordinal()]) {
/*      */       case 1:
/*  703 */         walkStacklessQuantizedTree(nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax, 0, this.curNodeIndex);
/*  704 */         break;
/*      */       case 2:
/*  711 */         walkRecursiveQuantizedTreeAgainstQueryAabb(this.quantizedContiguousNodes, 0, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
/*  712 */         break;
/*      */       default:
/*  715 */         if (!$assertionsDisabled) throw new AssertionError(); break;
/*      */       }
/*      */     }
/*      */     else {
/*  719 */       walkStacklessTree(nodeCallback, aabbMin, aabbMax);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void walkStacklessTree(NodeOverlapCallback nodeCallback, Vector3f aabbMin, Vector3f aabbMax) {
/*  724 */     assert (!this.useQuantization);
/*      */ 
/*  727 */     OptimizedBvhNode rootNode = null;
/*  728 */     int rootNode_index = 0;
/*      */ 
/*  730 */     int curIndex = 0;
/*  731 */     int walkIterations = 0;
/*      */ 
/*  737 */     while (curIndex < this.curNodeIndex)
/*      */     {
/*  739 */       assert (walkIterations < this.curNodeIndex);
/*      */ 
/*  741 */       walkIterations++;
/*      */ 
/*  743 */       rootNode = (OptimizedBvhNode)this.contiguousNodes.getQuick(rootNode_index);
/*      */ 
/*  745 */       boolean aabbOverlap = AabbUtil2.testAabbAgainstAabb2(aabbMin, aabbMax, rootNode.aabbMinOrg, rootNode.aabbMaxOrg);
/*  746 */       boolean isLeafNode = rootNode.escapeIndex == -1;
/*      */ 
/*  749 */       if ((isLeafNode) && (aabbOverlap)) {
/*  750 */         nodeCallback.processNode(rootNode.subPart, rootNode.triangleIndex);
/*      */       }
/*      */ 
/*  753 */       rootNode = null;
/*      */ 
/*  756 */       if ((aabbOverlap) || (isLeafNode)) {
/*  757 */         rootNode_index++;
/*  758 */         curIndex++;
/*      */       }
/*      */       else {
/*  761 */         int escapeIndex = ((OptimizedBvhNode)this.contiguousNodes.getQuick(rootNode_index)).escapeIndex;
/*  762 */         rootNode_index += escapeIndex;
/*  763 */         curIndex += escapeIndex;
/*      */       }
/*      */     }
/*  766 */     if (maxIterations < walkIterations)
/*  767 */       maxIterations = walkIterations;
/*      */   }
/*      */ 
/*      */   protected void walkRecursiveQuantizedTreeAgainstQueryAabb(QuantizedBvhNodes currentNodes, int currentNodeId, NodeOverlapCallback nodeCallback, long quantizedQueryAabbMin, long quantizedQueryAabbMax)
/*      */   {
/*  772 */     assert (this.useQuantization);
/*      */ 
/*  777 */     boolean aabbOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, currentNodes.getQuantizedAabbMin(currentNodeId), currentNodes.getQuantizedAabbMax(currentNodeId));
/*  778 */     boolean isLeafNode = currentNodes.isLeafNode(currentNodeId);
/*      */ 
/*  780 */     if (aabbOverlap)
/*  781 */       if (isLeafNode) {
/*  782 */         nodeCallback.processNode(currentNodes.getPartId(currentNodeId), currentNodes.getTriangleIndex(currentNodeId));
/*      */       }
/*      */       else
/*      */       {
/*  786 */         int leftChildNodeId = currentNodeId + 1;
/*  787 */         walkRecursiveQuantizedTreeAgainstQueryAabb(currentNodes, leftChildNodeId, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
/*      */ 
/*  789 */         int rightChildNodeId = currentNodes.isLeafNode(leftChildNodeId) ? leftChildNodeId + 1 : leftChildNodeId + currentNodes.getEscapeIndex(leftChildNodeId);
/*  790 */         walkRecursiveQuantizedTreeAgainstQueryAabb(currentNodes, rightChildNodeId, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
/*      */       }
/*      */   }
/*      */ 
/*      */   protected void walkStacklessQuantizedTreeAgainstRay(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, int arg6, int arg7)
/*      */   {
/*  796 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); assert (this.useQuantization);
/*      */ 
/*  798 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  800 */       int curIndex = startNodeIndex;
/*  801 */       int walkIterations = 0;
/*  802 */       int subTreeSize = endNodeIndex - startNodeIndex;
/*      */ 
/*  804 */       QuantizedBvhNodes rootNode = this.quantizedContiguousNodes;
/*  805 */       int rootNode_idx = startNodeIndex;
/*      */ 
/*  809 */       boolean boxBoxOverlap = false;
/*  810 */       boolean rayBoxOverlap = false;
/*      */ 
/*  812 */       float lambda_max = 1.0F;
/*      */ 
/*  815 */       Vector3f rayFrom = localStack.get$javax$vecmath$Vector3f(raySource);
/*  816 */       Vector3f rayDirection = localStack.get$javax$vecmath$Vector3f();
/*  817 */       tmp.sub(rayTarget, raySource);
/*  818 */       rayDirection.normalize(tmp);
/*  819 */       lambda_max = rayDirection.dot(tmp);
/*  820 */       rayDirection.x = (1.0F / rayDirection.x);
/*  821 */       rayDirection.y = (1.0F / rayDirection.y);
/*  822 */       rayDirection.z = (1.0F / rayDirection.z);
/*      */ 
/*  829 */       Vector3f rayAabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
/*  830 */       Vector3f rayAabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
/*  831 */       VectorUtil.setMin(rayAabbMin, rayTarget);
/*  832 */       VectorUtil.setMax(rayAabbMax, rayTarget);
/*      */ 
/*  835 */       rayAabbMin.add(aabbMin);
/*  836 */       rayAabbMax.add(aabbMax);
/*      */ 
/*  840 */       long quantizedQueryAabbMin = quantizeWithClamp(rayAabbMin);
/*  841 */       long quantizedQueryAabbMax = quantizeWithClamp(rayAabbMax);
/*      */ 
/*  843 */       Vector3f bounds_0 = localStack.get$javax$vecmath$Vector3f();
/*  844 */       Vector3f bounds_1 = localStack.get$javax$vecmath$Vector3f();
/*  845 */       Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  846 */       float[] param = new float[1];
/*      */ 
/*  848 */       while (curIndex < endNodeIndex)
/*      */       {
/*  867 */         assert (walkIterations < subTreeSize);
/*      */ 
/*  869 */         walkIterations++;
/*      */ 
/*  871 */         param[0] = 1.0F;
/*  872 */         rayBoxOverlap = false;
/*  873 */         boxBoxOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, rootNode.getQuantizedAabbMin(rootNode_idx), rootNode.getQuantizedAabbMax(rootNode_idx));
/*  874 */         boolean isLeafNode = rootNode.isLeafNode(rootNode_idx);
/*  875 */         if (boxBoxOverlap) {
/*  876 */           unQuantize(bounds_0, rootNode.getQuantizedAabbMin(rootNode_idx));
/*  877 */           unQuantize(bounds_1, rootNode.getQuantizedAabbMax(rootNode_idx));
/*      */ 
/*  879 */           bounds_0.add(aabbMin);
/*  880 */           bounds_1.add(aabbMax);
/*      */ 
/*  892 */           rayBoxOverlap = AabbUtil2.rayAabb(raySource, rayTarget, bounds_0, bounds_1, param, normal);
/*      */         }
/*      */ 
/*  896 */         if ((isLeafNode) && (rayBoxOverlap)) {
/*  897 */           nodeCallback.processNode(rootNode.getPartId(rootNode_idx), rootNode.getTriangleIndex(rootNode_idx));
/*      */         }
/*      */ 
/*  900 */         if ((rayBoxOverlap) || (isLeafNode)) {
/*  901 */           rootNode_idx++;
/*  902 */           curIndex++;
/*      */         }
/*      */         else {
/*  905 */           int escapeIndex = rootNode.getEscapeIndex(rootNode_idx);
/*  906 */           rootNode_idx += escapeIndex;
/*  907 */           curIndex += escapeIndex;
/*      */         }
/*      */       }
/*      */ 
/*  911 */       if (maxIterations < walkIterations)
/*  912 */         maxIterations = walkIterations;
/*      */       return; } finally {
/*  914 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   protected void walkStacklessQuantizedTree(NodeOverlapCallback nodeCallback, long quantizedQueryAabbMin, long quantizedQueryAabbMax, int startNodeIndex, int endNodeIndex) {
/*  917 */     assert (this.useQuantization);
/*      */ 
/*  919 */     int curIndex = startNodeIndex;
/*  920 */     int walkIterations = 0;
/*  921 */     int subTreeSize = endNodeIndex - startNodeIndex;
/*      */ 
/*  923 */     QuantizedBvhNodes rootNode = this.quantizedContiguousNodes;
/*  924 */     int rootNode_idx = startNodeIndex;
/*      */ 
/*  930 */     while (curIndex < endNodeIndex)
/*      */     {
/*  948 */       assert (walkIterations < subTreeSize);
/*      */ 
/*  950 */       walkIterations++;
/*  951 */       boolean aabbOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, rootNode.getQuantizedAabbMin(rootNode_idx), rootNode.getQuantizedAabbMax(rootNode_idx));
/*  952 */       boolean isLeafNode = rootNode.isLeafNode(rootNode_idx);
/*      */ 
/*  954 */       if ((isLeafNode) && (aabbOverlap)) {
/*  955 */         nodeCallback.processNode(rootNode.getPartId(rootNode_idx), rootNode.getTriangleIndex(rootNode_idx));
/*      */       }
/*      */ 
/*  958 */       if ((aabbOverlap) || (isLeafNode)) {
/*  959 */         rootNode_idx++;
/*  960 */         curIndex++;
/*      */       }
/*      */       else {
/*  963 */         int escapeIndex = rootNode.getEscapeIndex(rootNode_idx);
/*  964 */         rootNode_idx += escapeIndex;
/*  965 */         curIndex += escapeIndex;
/*      */       }
/*      */     }
/*      */ 
/*  969 */     if (maxIterations < walkIterations)
/*  970 */       maxIterations = walkIterations;
/*      */   }
/*      */ 
/*      */   public void reportRayOverlappingNodex(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3)
/*      */   {
/*  975 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); boolean fast_path = (this.useQuantization) && (this.traversalMode == TraversalMode.STACKLESS);
/*  976 */       if (fast_path) {
/*  977 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  978 */         tmp.set(0.0F, 0.0F, 0.0F);
/*  979 */         walkStacklessQuantizedTreeAgainstRay(nodeCallback, raySource, rayTarget, tmp, tmp, 0, this.curNodeIndex);
/*      */       }
/*      */       else
/*      */       {
/*  983 */         Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
/*  984 */         Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
/*  985 */         VectorUtil.setMin(aabbMin, rayTarget);
/*  986 */         VectorUtil.setMax(aabbMax, rayTarget);
/*  987 */         reportAabbOverlappingNodex(nodeCallback, aabbMin, aabbMax);
/*      */       }return; } finally {
/*  989 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   public void reportBoxCastOverlappingNodex(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5) {
/*  992 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); boolean fast_path = (this.useQuantization) && (this.traversalMode == TraversalMode.STACKLESS);
/*  993 */       if (fast_path) {
/*  994 */         walkStacklessQuantizedTreeAgainstRay(nodeCallback, raySource, rayTarget, aabbMin, aabbMax, 0, this.curNodeIndex);
/*      */       }
/*      */       else
/*      */       {
/*  999 */         Vector3f qaabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
/* 1000 */         Vector3f qaabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
/* 1001 */         VectorUtil.setMin(qaabbMin, rayTarget);
/* 1002 */         VectorUtil.setMax(qaabbMax, rayTarget);
/* 1003 */         qaabbMin.add(aabbMin);
/* 1004 */         qaabbMax.add(aabbMax);
/* 1005 */         reportAabbOverlappingNodex(nodeCallback, qaabbMin, qaabbMax);
/*      */       }return; } finally {
/* 1007 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   public long quantizeWithClamp(Vector3f arg1) {
/* 1010 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); assert (this.useQuantization);
/*      */ 
/* 1012 */       Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
/* 1013 */       VectorUtil.setMax(clampedPoint, this.bvhAabbMin);
/* 1014 */       VectorUtil.setMin(clampedPoint, this.bvhAabbMax);
/*      */ 
/* 1016 */       Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 1017 */       v.sub(clampedPoint, this.bvhAabbMin);
/* 1018 */       VectorUtil.mul(v, v, this.bvhQuantization);
/*      */ 
/* 1020 */       int out0 = (int)(v.x + 0.5F) & 0xFFFF;
/* 1021 */       int out1 = (int)(v.y + 0.5F) & 0xFFFF;
/* 1022 */       int out2 = (int)(v.z + 0.5F) & 0xFFFF;
/*      */ 
/* 1024 */       return out0 | out1 << 16 | out2 << 32; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   public void unQuantize(Vector3f vecOut, long vecIn) {
/* 1028 */     int vecIn0 = (int)(vecIn & 0xFFFF);
/* 1029 */     int vecIn1 = (int)((vecIn & 0xFFFF0000) >>> 16);
/* 1030 */     int vecIn2 = (int)((vecIn & 0x0) >>> 32);
/*      */ 
/* 1032 */     vecOut.x = (vecIn0 / this.bvhQuantization.x);
/* 1033 */     vecOut.y = (vecIn1 / this.bvhQuantization.y);
/* 1034 */     vecOut.z = (vecIn2 / this.bvhQuantization.z);
/*      */ 
/* 1036 */     vecOut.add(this.bvhAabbMin);
/*      */   }
/*      */ 
/*      */   private static class QuantizedNodeTriangleCallback extends InternalTriangleIndexCallback
/*      */   {
/*      */     public QuantizedBvhNodes triangleNodes;
/*      */     public OptimizedBvh optimizedTree;
/*      */ 
/*      */     public QuantizedNodeTriangleCallback(QuantizedBvhNodes triangleNodes, OptimizedBvh tree)
/*      */     {
/*  233 */       this.triangleNodes = triangleNodes;
/*  234 */       this.optimizedTree = tree;
/*      */     }
/*      */ 
/*      */     public void internalProcessTriangleIndex(Vector3f[] arg1, int arg2, int arg3)
/*      */     {
/*  239 */       .Stack localStack = .Stack.get();
/*      */       try { localStack.push$javax$vecmath$Vector3f(); assert (partId < 1024);
/*  240 */         assert (triangleIndex < 2097152);
/*      */ 
/*  242 */         assert (triangleIndex >= 0);
/*      */ 
/*  244 */         int nodeId = this.triangleNodes.add();
/*  245 */         Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  246 */         aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  247 */         aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  248 */         VectorUtil.setMin(aabbMin, triangle[0]);
/*  249 */         VectorUtil.setMax(aabbMax, triangle[0]);
/*  250 */         VectorUtil.setMin(aabbMin, triangle[1]);
/*  251 */         VectorUtil.setMax(aabbMax, triangle[1]);
/*  252 */         VectorUtil.setMin(aabbMin, triangle[2]);
/*  253 */         VectorUtil.setMax(aabbMax, triangle[2]);
/*      */ 
/*  256 */         float MIN_AABB_DIMENSION = 0.002F;
/*  257 */         float MIN_AABB_HALF_DIMENSION = 0.001F;
/*  258 */         if (aabbMax.x - aabbMin.x < 0.002F) {
/*  259 */           aabbMax.x += 0.001F;
/*  260 */           aabbMin.x -= 0.001F;
/*      */         }
/*  262 */         if (aabbMax.y - aabbMin.y < 0.002F) {
/*  263 */           aabbMax.y += 0.001F;
/*  264 */           aabbMin.y -= 0.001F;
/*      */         }
/*  266 */         if (aabbMax.z - aabbMin.z < 0.002F) {
/*  267 */           aabbMax.z += 0.001F;
/*  268 */           aabbMin.z -= 0.001F; } 
/*      */ this.triangleNodes.setQuantizedAabbMin(nodeId, this.optimizedTree.quantizeWithClamp(aabbMin));
/*  272 */         this.triangleNodes.setQuantizedAabbMax(nodeId, this.optimizedTree.quantizeWithClamp(aabbMax));
/*      */ 
/*  274 */         this.triangleNodes.setEscapeIndexOrTriangleIndex(nodeId, partId << 21 | triangleIndex);
/*      */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class NodeTriangleCallback extends InternalTriangleIndexCallback
/*      */   {
/*      */     public ObjectArrayList<OptimizedBvhNode> triangleNodes;
/*  200 */     private final Vector3f aabbMin = new Vector3f(); private final Vector3f aabbMax = new Vector3f();
/*      */ 
/*      */     public NodeTriangleCallback(ObjectArrayList<OptimizedBvhNode> triangleNodes)
/*      */     {
/*  197 */       this.triangleNodes = triangleNodes;
/*      */     }
/*      */ 
/*      */     public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex)
/*      */     {
/*  203 */       OptimizedBvhNode node = new OptimizedBvhNode();
/*  204 */       this.aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  205 */       this.aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  206 */       VectorUtil.setMin(this.aabbMin, triangle[0]);
/*  207 */       VectorUtil.setMax(this.aabbMax, triangle[0]);
/*  208 */       VectorUtil.setMin(this.aabbMin, triangle[1]);
/*  209 */       VectorUtil.setMax(this.aabbMax, triangle[1]);
/*  210 */       VectorUtil.setMin(this.aabbMin, triangle[2]);
/*  211 */       VectorUtil.setMax(this.aabbMax, triangle[2]);
/*      */ 
/*  214 */       node.aabbMinOrg.set(this.aabbMin);
/*  215 */       node.aabbMaxOrg.set(this.aabbMax);
/*      */ 
/*  217 */       node.escapeIndex = -1;
/*      */ 
/*  220 */       node.subPart = partId;
/*  221 */       node.triangleIndex = triangleIndex;
/*  222 */       this.triangleNodes.add(node);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.OptimizedBvh
 * JD-Core Version:    0.6.2
 */