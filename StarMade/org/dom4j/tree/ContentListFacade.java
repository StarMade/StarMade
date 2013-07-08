package org.dom4j.tree;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;

public class ContentListFacade
  extends AbstractList
{
  private List branchContent;
  private AbstractBranch branch;
  
  public ContentListFacade(AbstractBranch branch, List branchContent)
  {
    this.branch = branch;
    this.branchContent = branchContent;
  }
  
  public boolean add(Object object)
  {
    this.branch.childAdded(asNode(object));
    return this.branchContent.add(object);
  }
  
  public void add(int index, Object object)
  {
    this.branch.childAdded(asNode(object));
    this.branchContent.add(index, object);
  }
  
  public Object set(int index, Object object)
  {
    this.branch.childAdded(asNode(object));
    return this.branchContent.set(index, object);
  }
  
  public boolean remove(Object object)
  {
    this.branch.childRemoved(asNode(object));
    return this.branchContent.remove(object);
  }
  
  public Object remove(int index)
  {
    Object object = this.branchContent.remove(index);
    if (object != null) {
      this.branch.childRemoved(asNode(object));
    }
    return object;
  }
  
  public boolean addAll(Collection collection)
  {
    int count = this.branchContent.size();
    Iterator iter = collection.iterator();
    while (iter.hasNext())
    {
      add(iter.next());
      count++;
    }
    return count == this.branchContent.size();
  }
  
  public boolean addAll(int index, Collection collection)
  {
    int count = this.branchContent.size();
    Iterator iter = collection.iterator();
    while (iter.hasNext())
    {
      add(index++, iter.next());
      count--;
    }
    return count == this.branchContent.size();
  }
  
  public void clear()
  {
    Iterator iter = iterator();
    while (iter.hasNext())
    {
      Object object = iter.next();
      this.branch.childRemoved(asNode(object));
    }
    this.branchContent.clear();
  }
  
  public boolean removeAll(Collection local_c)
  {
    Iterator iter = local_c.iterator();
    while (iter.hasNext())
    {
      Object object = iter.next();
      this.branch.childRemoved(asNode(object));
    }
    return this.branchContent.removeAll(local_c);
  }
  
  public int size()
  {
    return this.branchContent.size();
  }
  
  public boolean isEmpty()
  {
    return this.branchContent.isEmpty();
  }
  
  public boolean contains(Object local_o)
  {
    return this.branchContent.contains(local_o);
  }
  
  public Object[] toArray()
  {
    return this.branchContent.toArray();
  }
  
  public Object[] toArray(Object[] local_a)
  {
    return this.branchContent.toArray(local_a);
  }
  
  public boolean containsAll(Collection local_c)
  {
    return this.branchContent.containsAll(local_c);
  }
  
  public Object get(int index)
  {
    return this.branchContent.get(index);
  }
  
  public int indexOf(Object local_o)
  {
    return this.branchContent.indexOf(local_o);
  }
  
  public int lastIndexOf(Object local_o)
  {
    return this.branchContent.lastIndexOf(local_o);
  }
  
  protected Node asNode(Object object)
  {
    if ((object instanceof Node)) {
      return (Node)object;
    }
    throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
  }
  
  protected List getBackingList()
  {
    return this.branchContent;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.ContentListFacade
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */