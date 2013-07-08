package com.bulletphysics.collision.dispatch;

import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.util.Comparator;

public class UnionFind
{
  private final ObjectArrayList<Element> elements = new ObjectArrayList();
  private static final Comparator<Element> elementComparator = new Comparator()
  {
    public int compare(UnionFind.Element local_o1, UnionFind.Element local_o2)
    {
      return local_o1.field_1648 < local_o2.field_1648 ? -1 : 1;
    }
  };
  
  public void sortIslands()
  {
    int numElements = this.elements.size();
    for (int local_i = 0; local_i < numElements; local_i++)
    {
      ((Element)this.elements.getQuick(local_i)).field_1648 = find(local_i);
      ((Element)this.elements.getQuick(local_i)).field_1649 = local_i;
    }
    MiscUtil.quickSort(this.elements, elementComparator);
  }
  
  public void reset(int local_N)
  {
    allocate(local_N);
    for (int local_i = 0; local_i < local_N; local_i++)
    {
      ((Element)this.elements.getQuick(local_i)).field_1648 = local_i;
      ((Element)this.elements.getQuick(local_i)).field_1649 = 1;
    }
  }
  
  public int getNumElements()
  {
    return this.elements.size();
  }
  
  public boolean isRoot(int local_x)
  {
    return local_x == ((Element)this.elements.getQuick(local_x)).field_1648;
  }
  
  public Element getElement(int index)
  {
    return (Element)this.elements.getQuick(index);
  }
  
  public void allocate(int local_N)
  {
    MiscUtil.resize(this.elements, local_N, Element.class);
  }
  
  public void free()
  {
    this.elements.clear();
  }
  
  public int find(int local_p, int local_q)
  {
    return find(local_p) == find(local_q) ? 1 : 0;
  }
  
  public void unite(int local_p, int local_q)
  {
    int local_i = find(local_p);
    int local_j = find(local_q);
    if (local_i == local_j) {
      return;
    }
    ((Element)this.elements.getQuick(local_i)).field_1648 = local_j;
    ((Element)this.elements.getQuick(local_j)).field_1649 += ((Element)this.elements.getQuick(local_i)).field_1649;
  }
  
  public int find(int local_x)
  {
    while (local_x != ((Element)this.elements.getQuick(local_x)).field_1648)
    {
      ((Element)this.elements.getQuick(local_x)).field_1648 = ((Element)this.elements.getQuick(((Element)this.elements.getQuick(local_x)).field_1648)).field_1648;
      local_x = ((Element)this.elements.getQuick(local_x)).field_1648;
    }
    return local_x;
  }
  
  public static class Element
  {
    public int field_1648;
    public int field_1649;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.UnionFind
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */