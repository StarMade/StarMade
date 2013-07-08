package com.bulletphysics.collision.shapes;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.io.Serializable;
import javax.vecmath.Vector3f;

public class OptimizedBvh
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private static final boolean DEBUG_TREE_BUILDING = false;
  private static int gStackDepth = 0;
  private static int gMaxStackDepth = 0;
  private static int maxIterations = 0;
  public static final int MAX_SUBTREE_SIZE_IN_BYTES = 2048;
  public static final int MAX_NUM_PARTS_IN_BITS = 10;
  private final ObjectArrayList<OptimizedBvhNode> leafNodes = new ObjectArrayList();
  private final ObjectArrayList<OptimizedBvhNode> contiguousNodes = new ObjectArrayList();
  private QuantizedBvhNodes quantizedLeafNodes = new QuantizedBvhNodes();
  private QuantizedBvhNodes quantizedContiguousNodes = new QuantizedBvhNodes();
  private int curNodeIndex;
  private boolean useQuantization;
  private final Vector3f bvhAabbMin = new Vector3f();
  private final Vector3f bvhAabbMax = new Vector3f();
  private final Vector3f bvhQuantization = new Vector3f();
  protected TraversalMode traversalMode = TraversalMode.STACKLESS;
  protected final ObjectArrayList<BvhSubtreeInfo> SubtreeHeaders = new ObjectArrayList();
  protected int subtreeHeaderCount;
  
  public void setInternalNodeAabbMin(int nodeIndex, Vector3f aabbMin)
  {
    if (this.useQuantization) {
      this.quantizedContiguousNodes.setQuantizedAabbMin(nodeIndex, quantizeWithClamp(aabbMin));
    } else {
      ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMinOrg.set(aabbMin);
    }
  }
  
  public void setInternalNodeAabbMax(int nodeIndex, Vector3f aabbMax)
  {
    if (this.useQuantization) {
      this.quantizedContiguousNodes.setQuantizedAabbMax(nodeIndex, quantizeWithClamp(aabbMax));
    } else {
      ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMaxOrg.set(aabbMax);
    }
  }
  
  public Vector3f getAabbMin(int nodeIndex)
  {
    if (this.useQuantization)
    {
      Vector3f tmp = new Vector3f();
      unQuantize(tmp, this.quantizedLeafNodes.getQuantizedAabbMin(nodeIndex));
      return tmp;
    }
    return ((OptimizedBvhNode)this.leafNodes.getQuick(nodeIndex)).aabbMinOrg;
  }
  
  public Vector3f getAabbMax(int nodeIndex)
  {
    if (this.useQuantization)
    {
      Vector3f tmp = new Vector3f();
      unQuantize(tmp, this.quantizedLeafNodes.getQuantizedAabbMax(nodeIndex));
      return tmp;
    }
    return ((OptimizedBvhNode)this.leafNodes.getQuick(nodeIndex)).aabbMaxOrg;
  }
  
  public void setQuantizationValues(Vector3f aabbMin, Vector3f aabbMax)
  {
    setQuantizationValues(aabbMin, aabbMax, 1.0F);
  }
  
  public void setQuantizationValues(Vector3f arg1, Vector3f arg2, float arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f clampValue = localStack.get$javax$vecmath$Vector3f();
      clampValue.set(quantizationMargin, quantizationMargin, quantizationMargin);
      this.bvhAabbMin.sub(aabbMin, clampValue);
      this.bvhAabbMax.add(aabbMax, clampValue);
      Vector3f aabbSize = localStack.get$javax$vecmath$Vector3f();
      aabbSize.sub(this.bvhAabbMax, this.bvhAabbMin);
      this.bvhQuantization.set(65535.0F, 65535.0F, 65535.0F);
      VectorUtil.div(this.bvhQuantization, this.bvhQuantization, aabbSize);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setInternalNodeEscapeIndex(int nodeIndex, int escapeIndex)
  {
    if (this.useQuantization) {
      this.quantizedContiguousNodes.setEscapeIndexOrTriangleIndex(nodeIndex, -escapeIndex);
    } else {
      ((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).escapeIndex = escapeIndex;
    }
  }
  
  public void mergeInternalNodeAabb(int nodeIndex, Vector3f newAabbMin, Vector3f newAabbMax)
  {
    if (this.useQuantization)
    {
      long quantizedAabbMin = quantizeWithClamp(newAabbMin);
      long quantizedAabbMax = quantizeWithClamp(newAabbMax);
      for (int local_i = 0; local_i < 3; local_i++)
      {
        if (this.quantizedContiguousNodes.getQuantizedAabbMin(nodeIndex, local_i) > QuantizedBvhNodes.getCoord(quantizedAabbMin, local_i)) {
          this.quantizedContiguousNodes.setQuantizedAabbMin(nodeIndex, local_i, QuantizedBvhNodes.getCoord(quantizedAabbMin, local_i));
        }
        if (this.quantizedContiguousNodes.getQuantizedAabbMax(nodeIndex, local_i) < QuantizedBvhNodes.getCoord(quantizedAabbMax, local_i)) {
          this.quantizedContiguousNodes.setQuantizedAabbMax(nodeIndex, local_i, QuantizedBvhNodes.getCoord(quantizedAabbMax, local_i));
        }
      }
    }
    else
    {
      VectorUtil.setMin(((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMinOrg, newAabbMin);
      VectorUtil.setMax(((OptimizedBvhNode)this.contiguousNodes.getQuick(nodeIndex)).aabbMaxOrg, newAabbMax);
    }
  }
  
  public void swapLeafNodes(int local_i, int splitIndex)
  {
    if (this.useQuantization)
    {
      this.quantizedLeafNodes.swap(local_i, splitIndex);
    }
    else
    {
      OptimizedBvhNode tmp = (OptimizedBvhNode)this.leafNodes.getQuick(local_i);
      this.leafNodes.setQuick(local_i, this.leafNodes.getQuick(splitIndex));
      this.leafNodes.setQuick(splitIndex, tmp);
    }
  }
  
  public void assignInternalNodeFromLeafNode(int internalNode, int leafNodeIndex)
  {
    if (this.useQuantization) {
      this.quantizedContiguousNodes.set(internalNode, this.quantizedLeafNodes, leafNodeIndex);
    } else {
      ((OptimizedBvhNode)this.contiguousNodes.getQuick(internalNode)).set((OptimizedBvhNode)this.leafNodes.getQuick(leafNodeIndex));
    }
  }
  
  public void build(StridingMeshInterface arg1, boolean arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.useQuantization = useQuantizedAabbCompression;
      int numLeafNodes = 0;
      if (this.useQuantization)
      {
        setQuantizationValues(_aabbMin, _aabbMax);
        QuantizedNodeTriangleCallback callback = new QuantizedNodeTriangleCallback(this.quantizedLeafNodes, this);
        triangles.internalProcessAllTriangles(callback, this.bvhAabbMin, this.bvhAabbMax);
        numLeafNodes = this.quantizedLeafNodes.size();
        this.quantizedContiguousNodes.resize(2 * numLeafNodes);
      }
      else
      {
        NodeTriangleCallback callback = new NodeTriangleCallback(this.leafNodes);
        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
        aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
        Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
        aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
        triangles.internalProcessAllTriangles(callback, aabbMin, aabbMax);
        numLeafNodes = this.leafNodes.size();
        MiscUtil.resize(this.contiguousNodes, 2 * numLeafNodes, OptimizedBvhNode.class);
      }
      this.curNodeIndex = 0;
      buildTree(0, numLeafNodes);
      if ((this.useQuantization) && (this.SubtreeHeaders.size() == 0))
      {
        BvhSubtreeInfo callback = new BvhSubtreeInfo();
        this.SubtreeHeaders.add(callback);
        callback.setAabbFromQuantizeNode(this.quantizedContiguousNodes, 0);
        callback.rootNodeIndex = 0;
        callback.subtreeSize = (this.quantizedContiguousNodes.isLeafNode(0) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(0));
      }
      this.subtreeHeaderCount = this.SubtreeHeaders.size();
      this.quantizedLeafNodes.clear();
      this.leafNodes.clear();
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void refit(StridingMeshInterface arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (this.useQuantization)
      {
        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
        Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
        meshInterface.calculateAabbBruteForce(aabbMin, aabbMax);
        setQuantizationValues(aabbMin, aabbMax);
        updateBvhNodes(meshInterface, 0, this.curNodeIndex, 0);
        for (int local_i = 0; local_i < this.SubtreeHeaders.size(); local_i++)
        {
          BvhSubtreeInfo subtree = (BvhSubtreeInfo)this.SubtreeHeaders.getQuick(local_i);
          subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, subtree.rootNodeIndex);
        }
      }
      else
      {
        build(meshInterface, false, null, null);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void refitPartial(StridingMeshInterface meshInterface, Vector3f aabbMin, Vector3f aabbMax)
  {
    throw new UnsupportedOperationException();
  }
  
  public void updateBvhNodes(StridingMeshInterface arg1, int arg2, int arg3, int arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      assert (this.useQuantization);
      int curNodeSubPart = -1;
      Vector3f[] triangleVerts = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
      Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
      Vector3f meshScaling = meshInterface.getScaling(localStack.get$javax$vecmath$Vector3f());
      VertexData data = null;
      for (int local_i = endNode - 1; local_i >= firstNode; local_i--)
      {
        QuantizedBvhNodes curNodes = this.quantizedContiguousNodes;
        int curNodeId = local_i;
        if (curNodes.isLeafNode(curNodeId))
        {
          int nodeSubPart = curNodes.getPartId(curNodeId);
          int nodeTriangleIndex = curNodes.getTriangleIndex(curNodeId);
          if (nodeSubPart != curNodeSubPart)
          {
            if (curNodeSubPart >= 0) {
              meshInterface.unLockReadOnlyVertexBase(curNodeSubPart);
            }
            data = meshInterface.getLockedReadOnlyVertexIndexBase(nodeSubPart);
          }
          data.getTriangle(nodeTriangleIndex * 3, meshScaling, triangleVerts);
          aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
          aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
          VectorUtil.setMin(aabbMin, triangleVerts[0]);
          VectorUtil.setMax(aabbMax, triangleVerts[0]);
          VectorUtil.setMin(aabbMin, triangleVerts[1]);
          VectorUtil.setMax(aabbMax, triangleVerts[1]);
          VectorUtil.setMin(aabbMin, triangleVerts[2]);
          VectorUtil.setMax(aabbMax, triangleVerts[2]);
          curNodes.setQuantizedAabbMin(curNodeId, quantizeWithClamp(aabbMin));
          curNodes.setQuantizedAabbMax(curNodeId, quantizeWithClamp(aabbMax));
        }
        else
        {
          int nodeSubPart = local_i + 1;
          int nodeTriangleIndex = this.quantizedContiguousNodes.isLeafNode(nodeSubPart) ? local_i + 2 : local_i + 1 + this.quantizedContiguousNodes.getEscapeIndex(nodeSubPart);
          for (int local_i2 = 0; local_i2 < 3; local_i2++)
          {
            curNodes.setQuantizedAabbMin(curNodeId, local_i2, this.quantizedContiguousNodes.getQuantizedAabbMin(nodeSubPart, local_i2));
            if (curNodes.getQuantizedAabbMin(curNodeId, local_i2) > this.quantizedContiguousNodes.getQuantizedAabbMin(nodeTriangleIndex, local_i2)) {
              curNodes.setQuantizedAabbMin(curNodeId, local_i2, this.quantizedContiguousNodes.getQuantizedAabbMin(nodeTriangleIndex, local_i2));
            }
            curNodes.setQuantizedAabbMax(curNodeId, local_i2, this.quantizedContiguousNodes.getQuantizedAabbMax(nodeSubPart, local_i2));
            if (curNodes.getQuantizedAabbMax(curNodeId, local_i2) < this.quantizedContiguousNodes.getQuantizedAabbMax(nodeTriangleIndex, local_i2)) {
              curNodes.setQuantizedAabbMax(curNodeId, local_i2, this.quantizedContiguousNodes.getQuantizedAabbMax(nodeTriangleIndex, local_i2));
            }
          }
        }
      }
      if (curNodeSubPart >= 0) {
        meshInterface.unLockReadOnlyVertexBase(curNodeSubPart);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void buildTree(int arg1, int arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      int numIndices = endIndex - startIndex;
      int curIndex = this.curNodeIndex;
      assert (numIndices > 0);
      if (numIndices == 1)
      {
        assignInternalNodeFromLeafNode(this.curNodeIndex, startIndex);
        this.curNodeIndex += 1;
        return;
      }
      int splitAxis = calcSplittingAxis(startIndex, endIndex);
      int splitIndex = sortAndCalcSplittingIndex(startIndex, endIndex, splitAxis);
      int internalNodeIndex = this.curNodeIndex;
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      tmp1.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
      setInternalNodeAabbMax(this.curNodeIndex, tmp1);
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp2.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
      setInternalNodeAabbMin(this.curNodeIndex, tmp2);
      for (int local_i = startIndex; local_i < endIndex; local_i++) {
        mergeInternalNodeAabb(this.curNodeIndex, getAabbMin(local_i), getAabbMax(local_i));
      }
      this.curNodeIndex += 1;
      int leftChildNodexIndex = this.curNodeIndex;
      buildTree(startIndex, splitIndex);
      int rightChildNodexIndex = this.curNodeIndex;
      buildTree(splitIndex, endIndex);
      int escapeIndex = this.curNodeIndex - curIndex;
      if (this.useQuantization)
      {
        int sizeQuantizedNode = QuantizedBvhNodes.getNodeSize();
        int treeSizeInBytes = escapeIndex * sizeQuantizedNode;
        if (treeSizeInBytes > 2048) {
          updateSubtreeHeaders(leftChildNodexIndex, rightChildNodexIndex);
        }
      }
      setInternalNodeEscapeIndex(internalNodeIndex, escapeIndex);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected boolean testQuantizedAabbAgainstQuantizedAabb(long aabbMin1, long aabbMax1, long aabbMin2, long aabbMax2)
  {
    int aabbMin1_0 = QuantizedBvhNodes.getCoord(aabbMin1, 0);
    int aabbMin1_1 = QuantizedBvhNodes.getCoord(aabbMin1, 1);
    int aabbMin1_2 = QuantizedBvhNodes.getCoord(aabbMin1, 2);
    int aabbMax1_0 = QuantizedBvhNodes.getCoord(aabbMax1, 0);
    int aabbMax1_1 = QuantizedBvhNodes.getCoord(aabbMax1, 1);
    int aabbMax1_2 = QuantizedBvhNodes.getCoord(aabbMax1, 2);
    int aabbMin2_0 = QuantizedBvhNodes.getCoord(aabbMin2, 0);
    int aabbMin2_1 = QuantizedBvhNodes.getCoord(aabbMin2, 1);
    int aabbMin2_2 = QuantizedBvhNodes.getCoord(aabbMin2, 2);
    int aabbMax2_0 = QuantizedBvhNodes.getCoord(aabbMax2, 0);
    int aabbMax2_1 = QuantizedBvhNodes.getCoord(aabbMax2, 1);
    int aabbMax2_2 = QuantizedBvhNodes.getCoord(aabbMax2, 2);
    boolean overlap = true;
    overlap = (aabbMin1_0 > aabbMax2_0) || (aabbMax1_0 < aabbMin2_0) ? false : overlap;
    overlap = (aabbMin1_2 > aabbMax2_2) || (aabbMax1_2 < aabbMin2_2) ? false : overlap;
    overlap = (aabbMin1_1 > aabbMax2_1) || (aabbMax1_1 < aabbMin2_1) ? false : overlap;
    return overlap;
  }
  
  protected void updateSubtreeHeaders(int leftChildNodexIndex, int rightChildNodexIndex)
  {
    assert (this.useQuantization);
    int leftSubTreeSize = this.quantizedContiguousNodes.isLeafNode(leftChildNodexIndex) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(leftChildNodexIndex);
    int leftSubTreeSizeInBytes = leftSubTreeSize * QuantizedBvhNodes.getNodeSize();
    int rightSubTreeSize = this.quantizedContiguousNodes.isLeafNode(rightChildNodexIndex) ? 1 : this.quantizedContiguousNodes.getEscapeIndex(rightChildNodexIndex);
    int rightSubTreeSizeInBytes = rightSubTreeSize * QuantizedBvhNodes.getNodeSize();
    if (leftSubTreeSizeInBytes <= 2048)
    {
      BvhSubtreeInfo subtree = new BvhSubtreeInfo();
      this.SubtreeHeaders.add(subtree);
      subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, leftChildNodexIndex);
      subtree.rootNodeIndex = leftChildNodexIndex;
      subtree.subtreeSize = leftSubTreeSize;
    }
    if (rightSubTreeSizeInBytes <= 2048)
    {
      BvhSubtreeInfo subtree = new BvhSubtreeInfo();
      this.SubtreeHeaders.add(subtree);
      subtree.setAabbFromQuantizeNode(this.quantizedContiguousNodes, rightChildNodexIndex);
      subtree.rootNodeIndex = rightChildNodexIndex;
      subtree.subtreeSize = rightSubTreeSize;
    }
    this.subtreeHeaderCount = this.SubtreeHeaders.size();
  }
  
  protected int sortAndCalcSplittingIndex(int arg1, int arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      int splitIndex = startIndex;
      int numIndices = endIndex - startIndex;
      Vector3f means = localStack.get$javax$vecmath$Vector3f();
      means.set(0.0F, 0.0F, 0.0F);
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        center.add(getAabbMax(local_i), getAabbMin(local_i));
        center.scale(0.5F);
        means.add(center);
      }
      means.scale(1.0F / numIndices);
      float splitValue = VectorUtil.getCoord(means, splitAxis);
      for (local_i = startIndex; local_i < endIndex; local_i++)
      {
        center.add(getAabbMax(local_i), getAabbMin(local_i));
        center.scale(0.5F);
        if (VectorUtil.getCoord(center, splitAxis) > splitValue)
        {
          swapLeafNodes(local_i, splitIndex);
          splitIndex++;
        }
      }
      int rangeBalancedIndices = numIndices / 3;
      boolean unbalanced = (splitIndex <= startIndex + rangeBalancedIndices) || (splitIndex >= endIndex - 1 - rangeBalancedIndices);
      if (unbalanced) {
        splitIndex = startIndex + (numIndices >> 1);
      }
      boolean unbal = (splitIndex == startIndex) || (splitIndex == endIndex);
      assert (!unbal);
      return splitIndex;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected int calcSplittingAxis(int arg1, int arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f means = localStack.get$javax$vecmath$Vector3f();
      means.set(0.0F, 0.0F, 0.0F);
      Vector3f variance = localStack.get$javax$vecmath$Vector3f();
      variance.set(0.0F, 0.0F, 0.0F);
      int numIndices = endIndex - startIndex;
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        center.add(getAabbMax(local_i), getAabbMin(local_i));
        center.scale(0.5F);
        means.add(center);
      }
      means.scale(1.0F / numIndices);
      Vector3f diff2 = localStack.get$javax$vecmath$Vector3f();
      for (local_i = startIndex; local_i < endIndex; local_i++)
      {
        center.add(getAabbMax(local_i), getAabbMin(local_i));
        center.scale(0.5F);
        diff2.sub(center, means);
        VectorUtil.mul(diff2, diff2, diff2);
        variance.add(diff2);
      }
      variance.scale(1.0F / (numIndices - 1.0F));
      return VectorUtil.maxAxis(variance);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void reportAabbOverlappingNodex(NodeOverlapCallback nodeCallback, Vector3f aabbMin, Vector3f aabbMax)
  {
    if (this.useQuantization)
    {
      long quantizedQueryAabbMin = quantizeWithClamp(aabbMin);
      long quantizedQueryAabbMax = quantizeWithClamp(aabbMax);
      switch (1.$SwitchMap$com$bulletphysics$collision$shapes$TraversalMode[this.traversalMode.ordinal()])
      {
      case 1: 
        walkStacklessQuantizedTree(nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax, 0, this.curNodeIndex);
        break;
      case 2: 
        walkRecursiveQuantizedTreeAgainstQueryAabb(this.quantizedContiguousNodes, 0, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
        break;
      default: 
        if (!$assertionsDisabled) {
          throw new AssertionError();
        }
        break;
      }
    }
    else
    {
      walkStacklessTree(nodeCallback, aabbMin, aabbMax);
    }
  }
  
  protected void walkStacklessTree(NodeOverlapCallback nodeCallback, Vector3f aabbMin, Vector3f aabbMax)
  {
    assert (!this.useQuantization);
    OptimizedBvhNode rootNode = null;
    int rootNode_index = 0;
    int curIndex = 0;
    int walkIterations = 0;
    while (curIndex < this.curNodeIndex)
    {
      assert (walkIterations < this.curNodeIndex);
      walkIterations++;
      rootNode = (OptimizedBvhNode)this.contiguousNodes.getQuick(rootNode_index);
      boolean aabbOverlap = AabbUtil2.testAabbAgainstAabb2(aabbMin, aabbMax, rootNode.aabbMinOrg, rootNode.aabbMaxOrg);
      boolean isLeafNode = rootNode.escapeIndex == -1;
      if ((isLeafNode) && (aabbOverlap)) {
        nodeCallback.processNode(rootNode.subPart, rootNode.triangleIndex);
      }
      rootNode = null;
      if ((aabbOverlap) || (isLeafNode))
      {
        rootNode_index++;
        curIndex++;
      }
      else
      {
        int escapeIndex = ((OptimizedBvhNode)this.contiguousNodes.getQuick(rootNode_index)).escapeIndex;
        rootNode_index += escapeIndex;
        curIndex += escapeIndex;
      }
    }
    if (maxIterations < walkIterations) {
      maxIterations = walkIterations;
    }
  }
  
  protected void walkRecursiveQuantizedTreeAgainstQueryAabb(QuantizedBvhNodes currentNodes, int currentNodeId, NodeOverlapCallback nodeCallback, long quantizedQueryAabbMin, long quantizedQueryAabbMax)
  {
    assert (this.useQuantization);
    boolean aabbOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, currentNodes.getQuantizedAabbMin(currentNodeId), currentNodes.getQuantizedAabbMax(currentNodeId));
    boolean isLeafNode = currentNodes.isLeafNode(currentNodeId);
    if (aabbOverlap) {
      if (isLeafNode)
      {
        nodeCallback.processNode(currentNodes.getPartId(currentNodeId), currentNodes.getTriangleIndex(currentNodeId));
      }
      else
      {
        int leftChildNodeId = currentNodeId + 1;
        walkRecursiveQuantizedTreeAgainstQueryAabb(currentNodes, leftChildNodeId, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
        int rightChildNodeId = currentNodes.isLeafNode(leftChildNodeId) ? leftChildNodeId + 1 : leftChildNodeId + currentNodes.getEscapeIndex(leftChildNodeId);
        walkRecursiveQuantizedTreeAgainstQueryAabb(currentNodes, rightChildNodeId, nodeCallback, quantizedQueryAabbMin, quantizedQueryAabbMax);
      }
    }
  }
  
  protected void walkStacklessQuantizedTreeAgainstRay(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, int arg6, int arg7)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      assert (this.useQuantization);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      int curIndex = startNodeIndex;
      int walkIterations = 0;
      int subTreeSize = endNodeIndex - startNodeIndex;
      QuantizedBvhNodes rootNode = this.quantizedContiguousNodes;
      int rootNode_idx = startNodeIndex;
      boolean boxBoxOverlap = false;
      boolean rayBoxOverlap = false;
      float lambda_max = 1.0F;
      Vector3f rayFrom = localStack.get$javax$vecmath$Vector3f(raySource);
      Vector3f rayDirection = localStack.get$javax$vecmath$Vector3f();
      tmp.sub(rayTarget, raySource);
      rayDirection.normalize(tmp);
      lambda_max = rayDirection.dot(tmp);
      rayDirection.field_615 = (1.0F / rayDirection.field_615);
      rayDirection.field_616 = (1.0F / rayDirection.field_616);
      rayDirection.field_617 = (1.0F / rayDirection.field_617);
      Vector3f rayAabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
      Vector3f rayAabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
      VectorUtil.setMin(rayAabbMin, rayTarget);
      VectorUtil.setMax(rayAabbMax, rayTarget);
      rayAabbMin.add(aabbMin);
      rayAabbMax.add(aabbMax);
      long quantizedQueryAabbMin = quantizeWithClamp(rayAabbMin);
      long quantizedQueryAabbMax = quantizeWithClamp(rayAabbMax);
      Vector3f bounds_0 = localStack.get$javax$vecmath$Vector3f();
      Vector3f bounds_1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      float[] param = new float[1];
      while (curIndex < endNodeIndex)
      {
        assert (walkIterations < subTreeSize);
        walkIterations++;
        param[0] = 1.0F;
        rayBoxOverlap = false;
        boxBoxOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, rootNode.getQuantizedAabbMin(rootNode_idx), rootNode.getQuantizedAabbMax(rootNode_idx));
        boolean isLeafNode = rootNode.isLeafNode(rootNode_idx);
        if (boxBoxOverlap)
        {
          unQuantize(bounds_0, rootNode.getQuantizedAabbMin(rootNode_idx));
          unQuantize(bounds_1, rootNode.getQuantizedAabbMax(rootNode_idx));
          bounds_0.add(aabbMin);
          bounds_1.add(aabbMax);
          rayBoxOverlap = AabbUtil2.rayAabb(raySource, rayTarget, bounds_0, bounds_1, param, normal);
        }
        if ((isLeafNode) && (rayBoxOverlap)) {
          nodeCallback.processNode(rootNode.getPartId(rootNode_idx), rootNode.getTriangleIndex(rootNode_idx));
        }
        if ((rayBoxOverlap) || (isLeafNode))
        {
          rootNode_idx++;
          curIndex++;
        }
        else
        {
          int escapeIndex = rootNode.getEscapeIndex(rootNode_idx);
          rootNode_idx += escapeIndex;
          curIndex += escapeIndex;
        }
      }
      if (maxIterations < walkIterations) {
        maxIterations = walkIterations;
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void walkStacklessQuantizedTree(NodeOverlapCallback nodeCallback, long quantizedQueryAabbMin, long quantizedQueryAabbMax, int startNodeIndex, int endNodeIndex)
  {
    assert (this.useQuantization);
    int curIndex = startNodeIndex;
    int walkIterations = 0;
    int subTreeSize = endNodeIndex - startNodeIndex;
    QuantizedBvhNodes rootNode = this.quantizedContiguousNodes;
    int rootNode_idx = startNodeIndex;
    while (curIndex < endNodeIndex)
    {
      assert (walkIterations < subTreeSize);
      walkIterations++;
      boolean aabbOverlap = testQuantizedAabbAgainstQuantizedAabb(quantizedQueryAabbMin, quantizedQueryAabbMax, rootNode.getQuantizedAabbMin(rootNode_idx), rootNode.getQuantizedAabbMax(rootNode_idx));
      boolean isLeafNode = rootNode.isLeafNode(rootNode_idx);
      if ((isLeafNode) && (aabbOverlap)) {
        nodeCallback.processNode(rootNode.getPartId(rootNode_idx), rootNode.getTriangleIndex(rootNode_idx));
      }
      if ((aabbOverlap) || (isLeafNode))
      {
        rootNode_idx++;
        curIndex++;
      }
      else
      {
        int escapeIndex = rootNode.getEscapeIndex(rootNode_idx);
        rootNode_idx += escapeIndex;
        curIndex += escapeIndex;
      }
    }
    if (maxIterations < walkIterations) {
      maxIterations = walkIterations;
    }
  }
  
  public void reportRayOverlappingNodex(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      boolean fast_path = (this.useQuantization) && (this.traversalMode == TraversalMode.STACKLESS);
      if (fast_path)
      {
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        tmp.set(0.0F, 0.0F, 0.0F);
        walkStacklessQuantizedTreeAgainstRay(nodeCallback, raySource, rayTarget, tmp, tmp, 0, this.curNodeIndex);
      }
      else
      {
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f(raySource);
        Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
        VectorUtil.setMin(tmp, rayTarget);
        VectorUtil.setMax(aabbMax, rayTarget);
        reportAabbOverlappingNodex(nodeCallback, tmp, aabbMax);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void reportBoxCastOverlappingNodex(NodeOverlapCallback arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      boolean fast_path = (this.useQuantization) && (this.traversalMode == TraversalMode.STACKLESS);
      if (fast_path)
      {
        walkStacklessQuantizedTreeAgainstRay(nodeCallback, raySource, rayTarget, aabbMin, aabbMax, 0, this.curNodeIndex);
      }
      else
      {
        Vector3f qaabbMin = localStack.get$javax$vecmath$Vector3f(raySource);
        Vector3f qaabbMax = localStack.get$javax$vecmath$Vector3f(raySource);
        VectorUtil.setMin(qaabbMin, rayTarget);
        VectorUtil.setMax(qaabbMax, rayTarget);
        qaabbMin.add(aabbMin);
        qaabbMax.add(aabbMax);
        reportAabbOverlappingNodex(nodeCallback, qaabbMin, qaabbMax);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public long quantizeWithClamp(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      assert (this.useQuantization);
      Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
      VectorUtil.setMax(clampedPoint, this.bvhAabbMin);
      VectorUtil.setMin(clampedPoint, this.bvhAabbMax);
      Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
      local_v.sub(clampedPoint, this.bvhAabbMin);
      VectorUtil.mul(local_v, local_v, this.bvhQuantization);
      int out0 = (int)(local_v.field_615 + 0.5F) & 0xFFFF;
      int out1 = (int)(local_v.field_616 + 0.5F) & 0xFFFF;
      int out2 = (int)(local_v.field_617 + 0.5F) & 0xFFFF;
      return out0 | out1 << 16 | out2 << 32;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void unQuantize(Vector3f vecOut, long vecIn)
  {
    int vecIn0 = (int)(vecIn & 0xFFFF);
    int vecIn1 = (int)((vecIn & 0xFFFF0000) >>> 16);
    int vecIn2 = (int)((vecIn & 0x0) >>> 32);
    vecOut.field_615 = (vecIn0 / this.bvhQuantization.field_615);
    vecOut.field_616 = (vecIn1 / this.bvhQuantization.field_616);
    vecOut.field_617 = (vecIn2 / this.bvhQuantization.field_617);
    vecOut.add(this.bvhAabbMin);
  }
  
  private static class QuantizedNodeTriangleCallback
    extends InternalTriangleIndexCallback
  {
    public QuantizedBvhNodes triangleNodes;
    public OptimizedBvh optimizedTree;
    
    public QuantizedNodeTriangleCallback(QuantizedBvhNodes triangleNodes, OptimizedBvh tree)
    {
      this.triangleNodes = triangleNodes;
      this.optimizedTree = tree;
    }
    
    public void internalProcessTriangleIndex(Vector3f[] arg1, int arg2, int arg3)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        assert (partId < 1024);
        assert (triangleIndex < 2097152);
        assert (triangleIndex >= 0);
        int nodeId = this.triangleNodes.add();
        Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f();
        Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
        aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
        aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
        VectorUtil.setMin(aabbMin, triangle[0]);
        VectorUtil.setMax(aabbMax, triangle[0]);
        VectorUtil.setMin(aabbMin, triangle[1]);
        VectorUtil.setMax(aabbMax, triangle[1]);
        VectorUtil.setMin(aabbMin, triangle[2]);
        VectorUtil.setMax(aabbMax, triangle[2]);
        float MIN_AABB_DIMENSION = 0.002F;
        float MIN_AABB_HALF_DIMENSION = 0.001F;
        if (aabbMax.field_615 - aabbMin.field_615 < 0.002F)
        {
          aabbMax.field_615 += 0.001F;
          aabbMin.field_615 -= 0.001F;
        }
        if (aabbMax.field_616 - aabbMin.field_616 < 0.002F)
        {
          aabbMax.field_616 += 0.001F;
          aabbMin.field_616 -= 0.001F;
        }
        if (aabbMax.field_617 - aabbMin.field_617 < 0.002F)
        {
          aabbMax.field_617 += 0.001F;
          aabbMin.field_617 -= 0.001F;
        }
        this.triangleNodes.setQuantizedAabbMin(nodeId, this.optimizedTree.quantizeWithClamp(aabbMin));
        this.triangleNodes.setQuantizedAabbMax(nodeId, this.optimizedTree.quantizeWithClamp(aabbMax));
        this.triangleNodes.setEscapeIndexOrTriangleIndex(nodeId, partId << 21 | triangleIndex);
        return;
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
  }
  
  private static class NodeTriangleCallback
    extends InternalTriangleIndexCallback
  {
    public ObjectArrayList<OptimizedBvhNode> triangleNodes;
    private final Vector3f aabbMin = new Vector3f();
    private final Vector3f aabbMax = new Vector3f();
    
    public NodeTriangleCallback(ObjectArrayList<OptimizedBvhNode> triangleNodes)
    {
      this.triangleNodes = triangleNodes;
    }
    
    public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex)
    {
      OptimizedBvhNode node = new OptimizedBvhNode();
      this.aabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
      this.aabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
      VectorUtil.setMin(this.aabbMin, triangle[0]);
      VectorUtil.setMax(this.aabbMax, triangle[0]);
      VectorUtil.setMin(this.aabbMin, triangle[1]);
      VectorUtil.setMax(this.aabbMax, triangle[1]);
      VectorUtil.setMin(this.aabbMin, triangle[2]);
      VectorUtil.setMax(this.aabbMax, triangle[2]);
      node.aabbMinOrg.set(this.aabbMin);
      node.aabbMaxOrg.set(this.aabbMax);
      node.escapeIndex = -1;
      node.subPart = partId;
      node.triangleIndex = triangleIndex;
      this.triangleNodes.add(node);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.OptimizedBvh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */