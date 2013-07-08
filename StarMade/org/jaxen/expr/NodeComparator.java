package org.jaxen.expr;

import java.util.Comparator;
import java.util.Iterator;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

class NodeComparator
  implements Comparator
{
  private Navigator navigator;
  
  NodeComparator(Navigator navigator)
  {
    this.navigator = navigator;
  }
  
  public int compare(Object local_o1, Object local_o2)
  {
    if (local_o1 == local_o2) {
      return 0;
    }
    if (this.navigator == null) {
      return 0;
    }
    if ((isNonChild(local_o1)) && (isNonChild(local_o2))) {
      try
      {
        Object local_p1 = this.navigator.getParentNode(local_o1);
        Object local_p2 = this.navigator.getParentNode(local_o2);
        if (local_p1 == local_p2)
        {
          if ((this.navigator.isNamespace(local_o1)) && (this.navigator.isAttribute(local_o2))) {
            return -1;
          }
          if ((this.navigator.isNamespace(local_o2)) && (this.navigator.isAttribute(local_o1))) {
            return 1;
          }
          if (this.navigator.isNamespace(local_o1))
          {
            String prefix1 = this.navigator.getNamespacePrefix(local_o1);
            String prefix2 = this.navigator.getNamespacePrefix(local_o2);
            return prefix1.compareTo(prefix2);
          }
          if (this.navigator.isAttribute(local_o1))
          {
            String prefix1 = this.navigator.getAttributeQName(local_o1);
            String prefix2 = this.navigator.getAttributeQName(local_o2);
            return prefix1.compareTo(prefix2);
          }
        }
        return compare(local_p1, local_p2);
      }
      catch (UnsupportedAxisException local_p1)
      {
        return 0;
      }
    }
    try
    {
      int local_p1 = getDepth(local_o1);
      int local_p2 = getDepth(local_o2);
      Object prefix1 = local_o1;
      Object prefix2 = local_o2;
      while (local_p1 > local_p2)
      {
        prefix1 = this.navigator.getParentNode(prefix1);
        local_p1--;
      }
      if (prefix1 == local_o2) {
        return 1;
      }
      while (local_p2 > local_p1)
      {
        prefix2 = this.navigator.getParentNode(prefix2);
        local_p2--;
      }
      if (prefix2 == local_o1) {
        return -1;
      }
      for (;;)
      {
        Object local_p11 = this.navigator.getParentNode(prefix1);
        Object local_p21 = this.navigator.getParentNode(prefix2);
        if (local_p11 == local_p21) {
          return compareSiblings(prefix1, prefix2);
        }
        prefix1 = local_p11;
        prefix2 = local_p21;
      }
      return 0;
    }
    catch (UnsupportedAxisException local_p1) {}
  }
  
  private boolean isNonChild(Object local_o)
  {
    return (this.navigator.isAttribute(local_o)) || (this.navigator.isNamespace(local_o));
  }
  
  private int compareSiblings(Object sib1, Object sib2)
    throws UnsupportedAxisException
  {
    if (isNonChild(sib1)) {
      return 1;
    }
    if (isNonChild(sib2)) {
      return -1;
    }
    Iterator following = this.navigator.getFollowingSiblingAxisIterator(sib1);
    while (following.hasNext())
    {
      Object next = following.next();
      if (next.equals(sib2)) {
        return -1;
      }
    }
    return 1;
  }
  
  private int getDepth(Object local_o)
    throws UnsupportedAxisException
  {
    int depth = 0;
    Object parent = local_o;
    while ((parent = this.navigator.getParentNode(parent)) != null) {
      depth++;
    }
    return depth;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.NodeComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */