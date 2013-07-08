package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.IntArrayList;
import javax.vecmath.Vector3f;

class GImpactBvh
{
  protected BvhTree box_tree = new BvhTree();
  protected PrimitiveManagerBase primitive_manager;
  
  public GImpactBvh()
  {
    this.primitive_manager = null;
  }
  
  public GImpactBvh(PrimitiveManagerBase primitive_manager)
  {
    this.primitive_manager = primitive_manager;
  }
  
  public BoxCollision.AABB getGlobalBox(BoxCollision.AABB out)
  {
    getNodeBound(0, out);
    return out;
  }
  
  public void setPrimitiveManager(PrimitiveManagerBase primitive_manager)
  {
    this.primitive_manager = primitive_manager;
  }
  
  public PrimitiveManagerBase getPrimitiveManager()
  {
    return this.primitive_manager;
  }
  
  protected void refit()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB leafbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB temp_box = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      int nodecount = getNodeCount();
      while (nodecount-- != 0) {
        if (isLeafNode(nodecount))
        {
          this.primitive_manager.get_primitive_box(getNodeData(nodecount), leafbox);
          setNodeBound(nodecount, leafbox);
        }
        else
        {
          bound.invalidate();
          int child_node = getLeftNode(nodecount);
          if (child_node != 0)
          {
            getNodeBound(child_node, temp_box);
            bound.merge(temp_box);
          }
          child_node = getRightNode(nodecount);
          if (child_node != 0)
          {
            getNodeBound(child_node, temp_box);
            bound.merge(temp_box);
          }
          setNodeBound(nodecount, bound);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public void update()
  {
    refit();
  }
  
  public void buildSet()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BvhDataArray primitive_boxes = new BvhDataArray();
      primitive_boxes.resize(this.primitive_manager.get_primitive_count());
      BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      for (int local_i = 0; local_i < primitive_boxes.size(); local_i++)
      {
        this.primitive_manager.get_primitive_box(local_i, tmpAABB);
        primitive_boxes.setBound(local_i, tmpAABB);
        primitive_boxes.setData(local_i, local_i);
      }
      this.box_tree.build_tree(primitive_boxes);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public boolean boxQuery(BoxCollision.AABB arg1, IntArrayList arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      int curIndex = 0;
      int numNodes = getNodeCount();
      BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      while (curIndex < numNodes)
      {
        getNodeBound(curIndex, bound);
        boolean aabbOverlap = bound.has_collision(box);
        boolean isleafnode = isLeafNode(curIndex);
        if ((isleafnode) && (aabbOverlap)) {
          collided_results.add(getNodeData(curIndex));
        }
        if ((aabbOverlap) || (isleafnode)) {
          curIndex++;
        } else {
          curIndex += getEscapeNodeIndex(curIndex);
        }
      }
      return collided_results.size() > 0;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public boolean boxQueryTrans(BoxCollision.AABB arg1, Transform arg2, IntArrayList arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB transbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
      transbox.appy_transform(transform);
      return boxQuery(transbox, collided_results);
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public boolean rayQuery(Vector3f arg1, Vector3f arg2, IntArrayList arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      int curIndex = 0;
      int numNodes = getNodeCount();
      BoxCollision.AABB bound = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      while (curIndex < numNodes)
      {
        getNodeBound(curIndex, bound);
        boolean aabbOverlap = bound.collide_ray(ray_origin, ray_dir);
        boolean isleafnode = isLeafNode(curIndex);
        if ((isleafnode) && (aabbOverlap)) {
          collided_results.add(getNodeData(curIndex));
        }
        if ((aabbOverlap) || (isleafnode)) {
          curIndex++;
        } else {
          curIndex += getEscapeNodeIndex(curIndex);
        }
      }
      return collided_results.size() > 0;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  public boolean hasHierarchy()
  {
    return true;
  }
  
  public boolean isTrimesh()
  {
    return this.primitive_manager.is_trimesh();
  }
  
  public int getNodeCount()
  {
    return this.box_tree.getNodeCount();
  }
  
  public boolean isLeafNode(int nodeindex)
  {
    return this.box_tree.isLeafNode(nodeindex);
  }
  
  public int getNodeData(int nodeindex)
  {
    return this.box_tree.getNodeData(nodeindex);
  }
  
  public void getNodeBound(int nodeindex, BoxCollision.AABB bound)
  {
    this.box_tree.getNodeBound(nodeindex, bound);
  }
  
  public void setNodeBound(int nodeindex, BoxCollision.AABB bound)
  {
    this.box_tree.setNodeBound(nodeindex, bound);
  }
  
  public int getLeftNode(int nodeindex)
  {
    return this.box_tree.getLeftNode(nodeindex);
  }
  
  public int getRightNode(int nodeindex)
  {
    return this.box_tree.getRightNode(nodeindex);
  }
  
  public int getEscapeNodeIndex(int nodeindex)
  {
    return this.box_tree.getEscapeNodeIndex(nodeindex);
  }
  
  public void getNodeTriangle(int nodeindex, PrimitiveTriangle triangle)
  {
    this.primitive_manager.get_primitive_triangle(getNodeData(nodeindex), triangle);
  }
  
  public BvhTreeNodeArray get_node_pointer()
  {
    return this.box_tree.get_node_pointer();
  }
  
  private static boolean _node_collision(GImpactBvh arg0, GImpactBvh arg1, BoxCollision.BoxBoxTransformCache arg2, int arg3, int arg4, boolean arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      BoxCollision.AABB box0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      boxset0.getNodeBound(node0, box0);
      BoxCollision.AABB box1 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
      boxset1.getNodeBound(node1, box1);
      return box0.overlapping_trans_cache(box1, trans_cache_1to0, complete_primitive_tests);
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
    }
  }
  
  private static void _find_collision_pairs_recursive(GImpactBvh boxset0, GImpactBvh boxset1, PairSet collision_pairs, BoxCollision.BoxBoxTransformCache trans_cache_1to0, int node0, int node1, boolean complete_primitive_tests)
  {
    if (!_node_collision(boxset0, boxset1, trans_cache_1to0, node0, node1, complete_primitive_tests)) {
      return;
    }
    if (boxset0.isLeafNode(node0))
    {
      if (boxset1.isLeafNode(node1))
      {
        collision_pairs.push_pair(boxset0.getNodeData(node0), boxset1.getNodeData(node1));
        return;
      }
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, node0, boxset1.getLeftNode(node1), false);
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, node0, boxset1.getRightNode(node1), false);
    }
    else if (boxset1.isLeafNode(node1))
    {
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), node1, false);
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), node1, false);
    }
    else
    {
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), boxset1.getLeftNode(node1), false);
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getLeftNode(node0), boxset1.getRightNode(node1), false);
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), boxset1.getLeftNode(node1), false);
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, boxset0.getRightNode(node0), boxset1.getRightNode(node1), false);
    }
  }
  
  public static void find_collision(GImpactBvh arg0, Transform arg1, GImpactBvh arg2, Transform arg3, PairSet arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
      if ((boxset0.getNodeCount() == 0) || (boxset1.getNodeCount() == 0)) {
        return;
      }
      BoxCollision.BoxBoxTransformCache trans_cache_1to0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
      trans_cache_1to0.calc_from_homogenic(trans0, trans1);
      _find_collision_pairs_recursive(boxset0, boxset1, collision_pairs, trans_cache_1to0, 0, 0, true);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$BoxBoxTransformCache();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactBvh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */