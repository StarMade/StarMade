package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

class BvhTree
{
  protected int num_nodes = 0;
  protected BvhTreeNodeArray node_array = new BvhTreeNodeArray();
  
  protected int _calc_splitting_axis(BvhDataArray arg1, int arg2, int arg3)
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
      Vector3f diff2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        primitive_boxes.getBoundMax(local_i, tmp1);
        primitive_boxes.getBoundMin(local_i, tmp2);
        center.add(tmp1, tmp2);
        center.scale(0.5F);
        means.add(center);
      }
      means.scale(1.0F / numIndices);
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        primitive_boxes.getBoundMax(local_i, tmp1);
        primitive_boxes.getBoundMin(local_i, tmp2);
        center.add(tmp1, tmp2);
        center.scale(0.5F);
        diff2.sub(center, means);
        VectorUtil.mul(diff2, diff2, diff2);
        variance.add(diff2);
      }
      variance.scale(1.0F / (numIndices - 1));
      return VectorUtil.maxAxis(variance);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected int _sort_and_calc_splitting_index(BvhDataArray arg1, int arg2, int arg3, int arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      int splitIndex = startIndex;
      int numIndices = endIndex - startIndex;
      float splitValue = 0.0F;
      Vector3f means = localStack.get$javax$vecmath$Vector3f();
      means.set(0.0F, 0.0F, 0.0F);
      Vector3f center = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        primitive_boxes.getBoundMax(local_i, tmp1);
        primitive_boxes.getBoundMin(local_i, tmp2);
        center.add(tmp1, tmp2);
        center.scale(0.5F);
        means.add(center);
      }
      means.scale(1.0F / numIndices);
      splitValue = VectorUtil.getCoord(means, splitAxis);
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        primitive_boxes.getBoundMax(local_i, tmp1);
        primitive_boxes.getBoundMin(local_i, tmp2);
        center.add(tmp1, tmp2);
        center.scale(0.5F);
        if (VectorUtil.getCoord(center, splitAxis) > splitValue)
        {
          primitive_boxes.swap(local_i, splitIndex);
          splitIndex++;
        }
      }
      int local_i = numIndices / 3;
      boolean unbalanced = (splitIndex <= startIndex + local_i) || (splitIndex >= endIndex - 1 - local_i);
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
  
  protected void _build_sub_tree(BvhDataArray arg1, int arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      int curIndex = this.num_nodes;
      this.num_nodes += 1;
      assert (endIndex - startIndex > 0);
      if (endIndex - startIndex == 1)
      {
        this.node_array.set(curIndex, primitive_boxes, startIndex);
        return;
      }
      int splitIndex = _calc_splitting_axis(primitive_boxes, startIndex, endIndex);
      splitIndex = _sort_and_calc_splitting_index(primitive_boxes, startIndex, endIndex, splitIndex);
      BoxCollision.AABB node_bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      node_bound.invalidate();
      for (int local_i = startIndex; local_i < endIndex; local_i++)
      {
        primitive_boxes.getBound(local_i, tmpAABB);
        node_bound.merge(tmpAABB);
      }
      setNodeBound(curIndex, node_bound);
      _build_sub_tree(primitive_boxes, startIndex, splitIndex);
      _build_sub_tree(primitive_boxes, splitIndex, endIndex);
      this.node_array.setEscapeIndex(curIndex, this.num_nodes - curIndex);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public void build_tree(BvhDataArray primitive_boxes)
  {
    this.num_nodes = 0;
    this.node_array.resize(primitive_boxes.size() * 2);
    _build_sub_tree(primitive_boxes, 0, primitive_boxes.size());
  }
  
  public void clearNodes()
  {
    this.node_array.clear();
    this.num_nodes = 0;
  }
  
  public int getNodeCount()
  {
    return this.num_nodes;
  }
  
  public boolean isLeafNode(int nodeindex)
  {
    return this.node_array.isLeafNode(nodeindex);
  }
  
  public int getNodeData(int nodeindex)
  {
    return this.node_array.getDataIndex(nodeindex);
  }
  
  public void getNodeBound(int nodeindex, BoxCollision.AABB bound)
  {
    this.node_array.getBound(nodeindex, bound);
  }
  
  public void setNodeBound(int nodeindex, BoxCollision.AABB bound)
  {
    this.node_array.setBound(nodeindex, bound);
  }
  
  public int getLeftNode(int nodeindex)
  {
    return nodeindex + 1;
  }
  
  public int getRightNode(int nodeindex)
  {
    if (this.node_array.isLeafNode(nodeindex + 1)) {
      return nodeindex + 2;
    }
    return nodeindex + 1 + this.node_array.getEscapeIndex(nodeindex + 1);
  }
  
  public int getEscapeNodeIndex(int nodeindex)
  {
    return this.node_array.getEscapeIndex(nodeindex);
  }
  
  public BvhTreeNodeArray get_node_pointer()
  {
    return this.node_array;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhTree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */