package com.bulletphysics.collision.broadphase;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import java.util.Collections;
import javax.vecmath.Vector3f;

public class Dbvt
{
  public static final int SIMPLE_STACKSIZE = 64;
  public static final int DOUBLE_STACKSIZE = 128;
  public Node root = null;
  public Node free = null;
  public int lkhd = -1;
  public int leaves = 0;
  public int opath = 0;
  private static Vector3f[] axis = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
  
  public void clear()
  {
    if (this.root != null) {
      recursedeletenode(this, this.root);
    }
    this.free = null;
  }
  
  public boolean empty()
  {
    return this.root == null;
  }
  
  public void optimizeBottomUp()
  {
    if (this.root != null)
    {
      ObjectArrayList<Node> leaves = new ObjectArrayList(this.leaves);
      fetchleaves(this, this.root, leaves);
      bottomup(this, leaves);
      this.root = ((Node)leaves.getQuick(0));
    }
  }
  
  public void optimizeTopDown()
  {
    optimizeTopDown(128);
  }
  
  public void optimizeTopDown(int bu_treshold)
  {
    if (this.root != null)
    {
      ObjectArrayList<Node> leaves = new ObjectArrayList(this.leaves);
      fetchleaves(this, this.root, leaves);
      this.root = topdown(this, leaves, bu_treshold);
    }
  }
  
  public void optimizeIncremental(int passes)
  {
    if (passes < 0) {
      passes = this.leaves;
    }
    if ((this.root != null) && (passes > 0))
    {
      Node[] root_ref = new Node[1];
      do
      {
        Node node = this.root;
        for (int bit = 0; node.isinternal(); bit = bit + 1 & 0x1F)
        {
          root_ref[0] = this.root;
          node = sort(node, root_ref).childs[(this.opath >>> bit & 0x1)];
          this.root = root_ref[0];
        }
        update(node);
        this.opath += 1;
        passes--;
      } while (passes != 0);
    }
  }
  
  public Node insert(DbvtAabbMm box, Object data)
  {
    Node leaf = createnode(this, null, box, data);
    insertleaf(this, this.root, leaf);
    this.leaves += 1;
    return leaf;
  }
  
  public void update(Node leaf)
  {
    update(leaf, -1);
  }
  
  public void update(Node leaf, int lookahead)
  {
    Node root = removeleaf(this, leaf);
    if (root != null) {
      if (lookahead >= 0) {
        for (int local_i = 0; (local_i < lookahead) && (root.parent != null); local_i++) {
          root = root.parent;
        }
      } else {
        root = this.root;
      }
    }
    insertleaf(this, root, leaf);
  }
  
  public void update(Node leaf, DbvtAabbMm volume)
  {
    Node root = removeleaf(this, leaf);
    if (root != null) {
      if (this.lkhd >= 0) {
        for (int local_i = 0; (local_i < this.lkhd) && (root.parent != null); local_i++) {
          root = root.parent;
        }
      } else {
        root = this.root;
      }
    }
    leaf.volume.set(volume);
    insertleaf(this, root, leaf);
  }
  
  public boolean update(Node arg1, DbvtAabbMm arg2, Vector3f arg3, float arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (leaf.volume.Contain(volume)) {
        return false;
      }
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.set(margin, margin, margin);
      volume.Expand(tmp);
      volume.SignedExpand(velocity);
      update(leaf, volume);
      return true;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean update(Node leaf, DbvtAabbMm volume, Vector3f velocity)
  {
    if (leaf.volume.Contain(volume)) {
      return false;
    }
    volume.SignedExpand(velocity);
    update(leaf, volume);
    return true;
  }
  
  public boolean update(Node arg1, DbvtAabbMm arg2, float arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (leaf.volume.Contain(volume)) {
        return false;
      }
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.set(margin, margin, margin);
      volume.Expand(tmp);
      update(leaf, volume);
      return true;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void remove(Node leaf)
  {
    removeleaf(this, leaf);
    deletenode(this, leaf);
    this.leaves -= 1;
  }
  
  public void write(IWriter iwriter)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clone(Dbvt dest)
  {
    clone(dest, null);
  }
  
  public void clone(Dbvt dest, IClone iclone)
  {
    throw new UnsupportedOperationException();
  }
  
  public static int countLeaves(Node node)
  {
    if (node.isinternal()) {
      return countLeaves(node.childs[0]) + countLeaves(node.childs[1]);
    }
    return 1;
  }
  
  public static void extractLeaves(Node node, ObjectArrayList<Node> leaves)
  {
    if (node.isinternal())
    {
      extractLeaves(node.childs[0], leaves);
      extractLeaves(node.childs[1], leaves);
    }
    else
    {
      leaves.add(node);
    }
  }
  
  public static void enumNodes(Node root, ICollide policy)
  {
    policy.Process(root);
    if (root.isinternal())
    {
      enumNodes(root.childs[0], policy);
      enumNodes(root.childs[1], policy);
    }
  }
  
  public static void enumLeaves(Node root, ICollide policy)
  {
    if (root.isinternal())
    {
      enumLeaves(root.childs[0], policy);
      enumLeaves(root.childs[1], policy);
    }
    else
    {
      policy.Process(root);
    }
  }
  
  public static void collideTT(Node root0, Node root1, ICollide policy)
  {
    if ((root0 != null) && (root1 != null))
    {
      ObjectArrayList<sStkNN> stack = new ObjectArrayList(128);
      stack.add(new sStkNN(root0, root1));
      do
      {
        sStkNN local_p = (sStkNN)stack.remove(stack.size() - 1);
        if (local_p.field_1674 == local_p.field_1675)
        {
          if (local_p.field_1674.isinternal())
          {
            stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1674.childs[0]));
            stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1674.childs[1]));
            stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1674.childs[1]));
          }
        }
        else if (DbvtAabbMm.Intersect(local_p.field_1674.volume, local_p.field_1675.volume)) {
          if (local_p.field_1674.isinternal())
          {
            if (local_p.field_1675.isinternal())
            {
              stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1675.childs[0]));
              stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1675.childs[0]));
              stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1675.childs[1]));
              stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1675.childs[1]));
            }
            else
            {
              stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1675));
              stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1675));
            }
          }
          else if (local_p.field_1675.isinternal())
          {
            stack.add(new sStkNN(local_p.field_1674, local_p.field_1675.childs[0]));
            stack.add(new sStkNN(local_p.field_1674, local_p.field_1675.childs[1]));
          }
          else
          {
            policy.Process(local_p.field_1674, local_p.field_1675);
          }
        }
      } while (stack.size() > 0);
    }
  }
  
  public static void collideTT(Node root0, Node root1, Transform xform, ICollide policy)
  {
    if ((root0 != null) && (root1 != null))
    {
      ObjectArrayList<sStkNN> stack = new ObjectArrayList(128);
      stack.add(new sStkNN(root0, root1));
      do
      {
        sStkNN local_p = (sStkNN)stack.remove(stack.size() - 1);
        if (local_p.field_1674 == local_p.field_1675)
        {
          if (local_p.field_1674.isinternal())
          {
            stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1674.childs[0]));
            stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1674.childs[1]));
            stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1674.childs[1]));
          }
        }
        else if (DbvtAabbMm.Intersect(local_p.field_1674.volume, local_p.field_1675.volume, xform)) {
          if (local_p.field_1674.isinternal())
          {
            if (local_p.field_1675.isinternal())
            {
              stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1675.childs[0]));
              stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1675.childs[0]));
              stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1675.childs[1]));
              stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1675.childs[1]));
            }
            else
            {
              stack.add(new sStkNN(local_p.field_1674.childs[0], local_p.field_1675));
              stack.add(new sStkNN(local_p.field_1674.childs[1], local_p.field_1675));
            }
          }
          else if (local_p.field_1675.isinternal())
          {
            stack.add(new sStkNN(local_p.field_1674, local_p.field_1675.childs[0]));
            stack.add(new sStkNN(local_p.field_1674, local_p.field_1675.childs[1]));
          }
          else
          {
            policy.Process(local_p.field_1674, local_p.field_1675);
          }
        }
      } while (stack.size() > 0);
    }
  }
  
  public static void collideTT(Node arg0, Transform arg1, Node arg2, Transform arg3, ICollide arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform xform = localStack.get$com$bulletphysics$linearmath$Transform();
      xform.inverse(xform0);
      xform.mul(xform1);
      collideTT(root0, root1, xform, policy);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public static void collideTV(Node root, DbvtAabbMm volume, ICollide policy)
  {
    if (root != null)
    {
      ObjectArrayList<Node> stack = new ObjectArrayList(64);
      stack.add(root);
      do
      {
        Node local_n = (Node)stack.remove(stack.size() - 1);
        if (DbvtAabbMm.Intersect(local_n.volume, volume)) {
          if (local_n.isinternal())
          {
            stack.add(local_n.childs[0]);
            stack.add(local_n.childs[1]);
          }
          else
          {
            policy.Process(local_n);
          }
        }
      } while (stack.size() > 0);
    }
  }
  
  public static void collideRAY(Node arg0, Vector3f arg1, Vector3f arg2, ICollide arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (root != null)
      {
        Vector3f normal = localStack.get$javax$vecmath$Vector3f();
        normal.normalize(direction);
        Vector3f invdir = localStack.get$javax$vecmath$Vector3f();
        invdir.set(1.0F / normal.field_615, 1.0F / normal.field_616, 1.0F / normal.field_617);
        int[] signs = { direction.field_615 < 0.0F ? 1 : 0, direction.field_616 < 0.0F ? 1 : 0, direction.field_617 < 0.0F ? 1 : 0 };
        ObjectArrayList<Node> stack = new ObjectArrayList(64);
        stack.add(root);
        do
        {
          Node node = (Node)stack.remove(stack.size() - 1);
          if (DbvtAabbMm.Intersect(node.volume, origin, invdir, signs)) {
            if (node.isinternal())
            {
              stack.add(node.childs[0]);
              stack.add(node.childs[1]);
            }
            else
            {
              policy.Process(node);
            }
          }
        } while (stack.size() != 0);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void collideKDOP(Node root, Vector3f[] normals, float[] offsets, int count, ICollide policy)
  {
    if (root != null)
    {
      int inside = (1 << count) - 1;
      ObjectArrayList<sStkNP> stack = new ObjectArrayList(64);
      int[] signs = new int[32];
      assert (count < 32);
      for (int local_i = 0; local_i < count; local_i++) {
        signs[local_i] = ((normals[local_i].field_615 >= 0.0F ? 1 : 0) + (normals[local_i].field_616 >= 0.0F ? 2 : 0) + (normals[local_i].field_617 >= 0.0F ? 4 : 0));
      }
      stack.add(new sStkNP(root, 0));
      do
      {
        sStkNP local_i = (sStkNP)stack.remove(stack.size() - 1);
        boolean out = false;
        int local_i1 = 0;
        int local_j = 1;
        while ((!out) && (local_i1 < count))
        {
          if (0 == (local_i.mask & local_j))
          {
            int side = local_i.node.volume.Classify(normals[local_i1], offsets[local_i1], signs[local_i1]);
            switch (side)
            {
            case -1: 
              out = true;
              break;
            case 1: 
              local_i.mask |= local_j;
            }
          }
          local_i1++;
          local_j <<= 1;
        }
        if (!out) {
          if ((local_i.mask != inside) && (local_i.node.isinternal()))
          {
            stack.add(new sStkNP(local_i.node.childs[0], local_i.mask));
            stack.add(new sStkNP(local_i.node.childs[1], local_i.mask));
          }
          else if (policy.AllLeaves(local_i.node))
          {
            enumLeaves(local_i.node, policy);
          }
        }
      } while (stack.size() != 0);
    }
  }
  
  public static void collideOCL(Node root, Vector3f[] normals, float[] offsets, Vector3f sortaxis, int count, ICollide policy)
  {
    collideOCL(root, normals, offsets, sortaxis, count, policy, true);
  }
  
  public static void collideOCL(Node root, Vector3f[] normals, float[] offsets, Vector3f sortaxis, int count, ICollide policy, boolean fullsort)
  {
    if (root != null)
    {
      int srtsgns = (sortaxis.field_615 >= 0.0F ? 1 : 0) + (sortaxis.field_616 >= 0.0F ? 2 : 0) + (sortaxis.field_617 >= 0.0F ? 4 : 0);
      int inside = (1 << count) - 1;
      ObjectArrayList<sStkNPS> stock = new ObjectArrayList();
      IntArrayList ifree = new IntArrayList();
      IntArrayList stack = new IntArrayList();
      int[] signs = new int[32];
      assert (count < 32);
      for (int local_i = 0; local_i < count; local_i++) {
        signs[local_i] = ((normals[local_i].field_615 >= 0.0F ? 1 : 0) + (normals[local_i].field_616 >= 0.0F ? 2 : 0) + (normals[local_i].field_617 >= 0.0F ? 4 : 0));
      }
      stack.add(allocate(ifree, stock, new sStkNPS(root, 0, root.volume.ProjectMinimum(sortaxis, srtsgns))));
      do
      {
        int local_i = stack.remove(stack.size() - 1);
        sStkNPS local_se = (sStkNPS)stock.getQuick(local_i);
        ifree.add(local_i);
        if (local_se.mask != inside)
        {
          boolean out = false;
          int local_i1 = 0;
          int local_j = 1;
          while ((!out) && (local_i1 < count))
          {
            if (0 == (local_se.mask & local_j))
            {
              int side = local_se.node.volume.Classify(normals[local_i1], offsets[local_i1], signs[local_i1]);
              switch (side)
              {
              case -1: 
                out = true;
                break;
              case 1: 
                local_se.mask |= local_j;
              }
            }
            local_i1++;
            local_j <<= 1;
          }
          if (out) {}
        }
        else if (policy.Descent(local_se.node))
        {
          if (local_se.node.isinternal())
          {
            Node[] out = { local_se.node.childs[0], local_se.node.childs[1] };
            sStkNPS[] local_i1 = { new sStkNPS(out[0], local_se.mask, out[0].volume.ProjectMinimum(sortaxis, srtsgns)), new sStkNPS(out[1], local_se.mask, out[1].volume.ProjectMinimum(sortaxis, srtsgns)) };
            int local_j = local_i1[0].value < local_i1[1].value ? 1 : 0;
            int side = stack.size();
            if ((fullsort) && (side > 0))
            {
              side = nearest(stack, stock, local_i1[local_j].value, 0, stack.size());
              stack.add(0);
              for (int local_k = stack.size() - 1; local_k > side; local_k--) {
                stack.set(local_k, stack.get(local_k - 1));
              }
              stack.set(side, allocate(ifree, stock, local_i1[local_j]));
              side = nearest(stack, stock, local_i1[(1 - local_j)].value, side, stack.size());
              stack.add(0);
              for (int local_k = stack.size() - 1; local_k > side; local_k--) {
                stack.set(local_k, stack.get(local_k - 1));
              }
              stack.set(side, allocate(ifree, stock, local_i1[(1 - local_j)]));
            }
            else
            {
              stack.add(allocate(ifree, stock, local_i1[local_j]));
              stack.add(allocate(ifree, stock, local_i1[(1 - local_j)]));
            }
          }
          else
          {
            policy.Process(local_se.node, local_se.value);
          }
        }
      } while (stack.size() != 0);
    }
  }
  
  public static void collideTU(Node root, ICollide policy)
  {
    if (root != null)
    {
      ObjectArrayList<Node> stack = new ObjectArrayList(64);
      stack.add(root);
      do
      {
        Node local_n = (Node)stack.remove(stack.size() - 1);
        if (policy.Descent(local_n)) {
          if (local_n.isinternal())
          {
            stack.add(local_n.childs[0]);
            stack.add(local_n.childs[1]);
          }
          else
          {
            policy.Process(local_n);
          }
        }
      } while (stack.size() > 0);
    }
  }
  
  public static int nearest(IntArrayList local_i, ObjectArrayList<sStkNPS> local_a, float local_v, int local_l, int local_h)
  {
    int local_m = 0;
    while (local_l < local_h)
    {
      local_m = local_l + local_h >> 1;
      if (((sStkNPS)local_a.getQuick(local_i.get(local_m))).value >= local_v) {
        local_l = local_m + 1;
      } else {
        local_h = local_m;
      }
    }
    return local_h;
  }
  
  public static int allocate(IntArrayList ifree, ObjectArrayList<sStkNPS> stock, sStkNPS value)
  {
    int local_i;
    if (ifree.size() > 0)
    {
      int local_i = ifree.get(ifree.size() - 1);
      ifree.remove(ifree.size() - 1);
      ((sStkNPS)stock.getQuick(local_i)).set(value);
    }
    else
    {
      local_i = stock.size();
      stock.add(value);
    }
    return local_i;
  }
  
  private static int indexof(Node node)
  {
    return node.parent.childs[1] == node ? 1 : 0;
  }
  
  private static DbvtAabbMm merge(DbvtAabbMm local_a, DbvtAabbMm local_b, DbvtAabbMm out)
  {
    DbvtAabbMm.Merge(local_a, local_b, out);
    return out;
  }
  
  private static float size(DbvtAabbMm arg0)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f edges = local_a.Lengths(localStack.get$javax$vecmath$Vector3f());
      return edges.field_615 * edges.field_616 * edges.field_617 + edges.field_615 + edges.field_616 + edges.field_617;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static void deletenode(Dbvt pdbvt, Node node)
  {
    pdbvt.free = node;
  }
  
  private static void recursedeletenode(Dbvt pdbvt, Node node)
  {
    if (!node.isleaf())
    {
      recursedeletenode(pdbvt, node.childs[0]);
      recursedeletenode(pdbvt, node.childs[1]);
    }
    if (node == pdbvt.root) {
      pdbvt.root = null;
    }
    deletenode(pdbvt, node);
  }
  
  private static Node createnode(Dbvt pdbvt, Node parent, DbvtAabbMm volume, Object data)
  {
    Node node;
    if (pdbvt.free != null)
    {
      Node node = pdbvt.free;
      pdbvt.free = null;
    }
    else
    {
      node = new Node();
    }
    node.parent = parent;
    node.volume.set(volume);
    node.data = data;
    node.childs[1] = null;
    return node;
  }
  
  private static void insertleaf(Dbvt pdbvt, Node root, Node leaf)
  {
    if (pdbvt.root == null)
    {
      pdbvt.root = leaf;
      leaf.parent = null;
    }
    else
    {
      if (!root.isleaf()) {
        do
        {
          if (DbvtAabbMm.Proximity(root.childs[0].volume, leaf.volume) < DbvtAabbMm.Proximity(root.childs[1].volume, leaf.volume)) {
            root = root.childs[0];
          } else {
            root = root.childs[1];
          }
        } while (!root.isleaf());
      }
      Node prev = root.parent;
      Node node = createnode(pdbvt, prev, merge(leaf.volume, root.volume, new DbvtAabbMm()), null);
      if (prev != null)
      {
        prev.childs[indexof(root)] = node;
        node.childs[0] = root;
        root.parent = node;
        node.childs[1] = leaf;
        leaf.parent = node;
        do
        {
          if (prev.volume.Contain(node.volume)) {
            break;
          }
          DbvtAabbMm.Merge(prev.childs[0].volume, prev.childs[1].volume, prev.volume);
          node = prev;
        } while (null != (prev = node.parent));
      }
      else
      {
        node.childs[0] = root;
        root.parent = node;
        node.childs[1] = leaf;
        leaf.parent = node;
        pdbvt.root = node;
      }
    }
  }
  
  private static Node removeleaf(Dbvt pdbvt, Node leaf)
  {
    if (leaf == pdbvt.root)
    {
      pdbvt.root = null;
      return null;
    }
    Node parent = leaf.parent;
    Node prev = parent.parent;
    Node sibling = parent.childs[(1 - indexof(leaf))];
    if (prev != null)
    {
      prev.childs[indexof(parent)] = sibling;
      sibling.parent = prev;
      deletenode(pdbvt, parent);
      while (prev != null)
      {
        DbvtAabbMm local_pb = prev.volume;
        DbvtAabbMm.Merge(prev.childs[0].volume, prev.childs[1].volume, prev.volume);
        if (!DbvtAabbMm.NotEqual(local_pb, prev.volume)) {
          break;
        }
        prev = prev.parent;
      }
      return prev != null ? prev : pdbvt.root;
    }
    pdbvt.root = sibling;
    sibling.parent = null;
    deletenode(pdbvt, parent);
    return pdbvt.root;
  }
  
  private static void fetchleaves(Dbvt pdbvt, Node root, ObjectArrayList<Node> leaves)
  {
    fetchleaves(pdbvt, root, leaves, -1);
  }
  
  private static void fetchleaves(Dbvt pdbvt, Node root, ObjectArrayList<Node> leaves, int depth)
  {
    if ((root.isinternal()) && (depth != 0))
    {
      fetchleaves(pdbvt, root.childs[0], leaves, depth - 1);
      fetchleaves(pdbvt, root.childs[1], leaves, depth - 1);
      deletenode(pdbvt, root);
    }
    else
    {
      leaves.add(root);
    }
  }
  
  private static void split(ObjectArrayList<Node> arg0, ObjectArrayList<Node> arg1, ObjectArrayList<Node> arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      MiscUtil.resize(left, 0, Node.class);
      MiscUtil.resize(right, 0, Node.class);
      int local_i = 0;
      int local_ni = leaves.size();
      while (local_i < local_ni)
      {
        ((Node)leaves.getQuick(local_i)).volume.Center(tmp);
        tmp.sub(org);
        if (axis.dot(tmp) < 0.0F) {
          left.add(leaves.getQuick(local_i));
        } else {
          right.add(leaves.getQuick(local_i));
        }
        local_i++;
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static DbvtAabbMm bounds(ObjectArrayList<Node> leaves)
  {
    DbvtAabbMm volume = new DbvtAabbMm(((Node)leaves.getQuick(0)).volume);
    int local_i = 1;
    int local_ni = leaves.size();
    while (local_i < local_ni)
    {
      merge(volume, ((Node)leaves.getQuick(local_i)).volume, volume);
      local_i++;
    }
    return volume;
  }
  
  private static void bottomup(Dbvt pdbvt, ObjectArrayList<Node> leaves)
  {
    DbvtAabbMm tmpVolume = new DbvtAabbMm();
    while (leaves.size() > 1)
    {
      float minsize = 3.4028235E+38F;
      int[] minidx = { -1, -1 };
      for (int local_i = 0; local_i < leaves.size(); local_i++) {
        for (int local_j = local_i + 1; local_j < leaves.size(); local_j++)
        {
          float local_sz = size(merge(((Node)leaves.getQuick(local_i)).volume, ((Node)leaves.getQuick(local_j)).volume, tmpVolume));
          if (local_sz < minsize)
          {
            minsize = local_sz;
            minidx[0] = local_i;
            minidx[1] = local_j;
          }
        }
      }
      Node[] local_i = { (Node)leaves.getQuick(minidx[0]), (Node)leaves.getQuick(minidx[1]) };
      Node local_j = createnode(pdbvt, null, merge(local_i[0].volume, local_i[1].volume, new DbvtAabbMm()), null);
      local_j.childs[0] = local_i[0];
      local_j.childs[1] = local_i[1];
      local_i[0].parent = local_j;
      local_i[1].parent = local_j;
      leaves.setQuick(minidx[0], local_j);
      Collections.swap(leaves, minidx[1], leaves.size() - 1);
      leaves.removeQuick(leaves.size() - 1);
    }
  }
  
  private static Node topdown(Dbvt arg0, ObjectArrayList<Node> arg1, int arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (leaves.size() > 1)
      {
        if (leaves.size() > bu_treshold)
        {
          DbvtAabbMm vol = bounds(leaves);
          Vector3f org = vol.Center(localStack.get$javax$vecmath$Vector3f());
          ObjectArrayList[] sets = new ObjectArrayList[2];
          for (int local_i = 0; local_i < sets.length; local_i++) {
            sets[local_i] = new ObjectArrayList();
          }
          int local_i = -1;
          int bestmidp = leaves.size();
          int[][] splitcount = { { 0, 0 }, { 0, 0 }, { 0, 0 } };
          Vector3f local_x = localStack.get$javax$vecmath$Vector3f();
          for (int local_i1 = 0; local_i1 < leaves.size(); local_i1++)
          {
            ((Node)leaves.getQuick(local_i1)).volume.Center(local_x);
            local_x.sub(org);
            for (int local_j = 0; local_j < 3; local_j++) {
              splitcount[local_j][(local_x.dot(axis[local_j]) > 0.0F ? 1 : 0)] += 1;
            }
          }
          for (int local_i1 = 0; local_i1 < 3; local_i1++) {
            if ((splitcount[local_i1][0] > 0) && (splitcount[local_i1][1] > 0))
            {
              int local_j = Math.abs(splitcount[local_i1][0] - splitcount[local_i1][1]);
              if (local_j < bestmidp)
              {
                local_i = local_i1;
                bestmidp = local_j;
              }
            }
          }
          if (local_i >= 0)
          {
            split(leaves, sets[0], sets[1], org, axis[local_i]);
          }
          else
          {
            int local_i1 = 0;
            int local_j = leaves.size();
            while (local_i1 < local_j)
            {
              sets[(local_i1 & 0x1)].add(leaves.getQuick(local_i1));
              local_i1++;
            }
          }
          Node local_i1 = createnode(pdbvt, null, vol, null);
          local_i1.childs[0] = topdown(pdbvt, sets[0], bu_treshold);
          local_i1.childs[1] = topdown(pdbvt, sets[1], bu_treshold);
          local_i1.childs[0].parent = local_i1;
          local_i1.childs[1].parent = local_i1;
          return local_i1;
        }
        bottomup(pdbvt, leaves);
        return (Node)leaves.getQuick(0);
      }
      return (Node)leaves.getQuick(0);
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static Node sort(Node local_n, Node[] local_r)
  {
    Node local_p = local_n.parent;
    assert (local_n.isinternal());
    if ((local_p != null) && (local_p.hashCode() > local_n.hashCode()))
    {
      int local_i = indexof(local_n);
      int local_j = 1 - local_i;
      Node local_s = local_p.childs[local_j];
      Node local_q = local_p.parent;
      assert (local_n == local_p.childs[local_i]);
      if (local_q != null) {
        local_q.childs[indexof(local_p)] = local_n;
      } else {
        local_r[0] = local_n;
      }
      local_s.parent = local_n;
      local_p.parent = local_n;
      local_n.parent = local_q;
      local_p.childs[0] = local_n.childs[0];
      local_p.childs[1] = local_n.childs[1];
      local_n.childs[0].parent = local_p;
      local_n.childs[1].parent = local_p;
      local_n.childs[local_i] = local_p;
      local_n.childs[local_j] = local_s;
      DbvtAabbMm.swap(local_p.volume, local_n.volume);
      return local_p;
    }
    return local_n;
  }
  
  private static Node walkup(Node local_n, int count)
  {
    while ((local_n != null) && (count-- != 0)) {
      local_n = local_n.parent;
    }
    return local_n;
  }
  
  public static class IClone
  {
    public void CloneLeaf(Dbvt.Node local_n) {}
  }
  
  public static abstract class IWriter
  {
    public abstract void Prepare(Dbvt.Node paramNode, int paramInt);
    
    public abstract void WriteNode(Dbvt.Node paramNode, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract void WriteLeaf(Dbvt.Node paramNode, int paramInt1, int paramInt2);
  }
  
  public static class ICollide
  {
    public void Process(Dbvt.Node local_n1, Dbvt.Node local_n2) {}
    
    public void Process(Dbvt.Node local_n) {}
    
    public void Process(Dbvt.Node local_n, float local_f)
    {
      Process(local_n);
    }
    
    public boolean Descent(Dbvt.Node local_n)
    {
      return true;
    }
    
    public boolean AllLeaves(Dbvt.Node local_n)
    {
      return true;
    }
  }
  
  public static class sStkCLN
  {
    public Dbvt.Node node;
    public Dbvt.Node parent;
    
    public sStkCLN(Dbvt.Node local_n, Dbvt.Node local_p)
    {
      this.node = local_n;
      this.parent = local_p;
    }
  }
  
  public static class sStkNPS
  {
    public Dbvt.Node node;
    public int mask;
    public float value;
    
    public sStkNPS() {}
    
    public sStkNPS(Dbvt.Node local_n, int local_m, float local_v)
    {
      this.node = local_n;
      this.mask = local_m;
      this.value = local_v;
    }
    
    public void set(sStkNPS local_o)
    {
      this.node = local_o.node;
      this.mask = local_o.mask;
      this.value = local_o.value;
    }
  }
  
  public static class sStkNP
  {
    public Dbvt.Node node;
    public int mask;
    
    public sStkNP(Dbvt.Node local_n, int local_m)
    {
      this.node = local_n;
      this.mask = local_m;
    }
  }
  
  public static class sStkNN
  {
    public Dbvt.Node field_1674;
    public Dbvt.Node field_1675;
    
    public sStkNN(Dbvt.Node local_na, Dbvt.Node local_nb)
    {
      this.field_1674 = local_na;
      this.field_1675 = local_nb;
    }
  }
  
  public static class Node
  {
    public final DbvtAabbMm volume = new DbvtAabbMm();
    public Node parent;
    public final Node[] childs = new Node[2];
    public Object data;
    
    public boolean isleaf()
    {
      return this.childs[1] == null;
    }
    
    public boolean isinternal()
    {
      return !isleaf();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.Dbvt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */