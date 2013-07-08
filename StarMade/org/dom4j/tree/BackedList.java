package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;

public class BackedList
  extends ArrayList
{
  private List branchContent;
  private AbstractBranch branch;
  
  public BackedList(AbstractBranch branch, List branchContent)
  {
    this(branch, branchContent, branchContent.size());
  }
  
  public BackedList(AbstractBranch branch, List branchContent, int capacity)
  {
    super(capacity);
    this.branch = branch;
    this.branchContent = branchContent;
  }
  
  public BackedList(AbstractBranch branch, List branchContent, List initialContent)
  {
    super(initialContent);
    this.branch = branch;
    this.branchContent = branchContent;
  }
  
  public boolean add(Object object)
  {
    this.branch.addNode(asNode(object));
    return super.add(object);
  }
  
  public void add(int index, Object object)
  {
    int size = size();
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index value: " + index + " is less than zero");
    }
    if (index > size) {
      throw new IndexOutOfBoundsException("Index value: " + index + " cannot be greater than " + "the size: " + size);
    }
    int realIndex;
    int realIndex;
    if (size == 0)
    {
      realIndex = this.branchContent.size();
    }
    else
    {
      int realIndex;
      if (index < size) {
        realIndex = this.branchContent.indexOf(get(index));
      } else {
        realIndex = this.branchContent.indexOf(get(size - 1)) + 1;
      }
    }
    this.branch.addNode(realIndex, asNode(object));
    super.add(index, object);
  }
  
  public Object set(int index, Object object)
  {
    int realIndex = this.branchContent.indexOf(get(index));
    if (realIndex < 0) {
      realIndex = index == 0 ? 0 : 2147483647;
    }
    if (realIndex < this.branchContent.size())
    {
      this.branch.removeNode(asNode(get(index)));
      this.branch.addNode(realIndex, asNode(object));
    }
    else
    {
      this.branch.removeNode(asNode(get(index)));
      this.branch.addNode(asNode(object));
    }
    this.branch.childAdded(asNode(object));
    return super.set(index, object);
  }
  
  public boolean remove(Object object)
  {
    this.branch.removeNode(asNode(object));
    return super.remove(object);
  }
  
  public Object remove(int index)
  {
    Object object = super.remove(index);
    if (object != null) {
      this.branch.removeNode(asNode(object));
    }
    return object;
  }
  
  public boolean addAll(Collection collection)
  {
    ensureCapacity(size() + collection.size());
    int count = size();
    Iterator iter = collection.iterator();
    while (iter.hasNext())
    {
      add(iter.next());
      count--;
    }
    return count != 0;
  }
  
  public boolean addAll(int index, Collection collection)
  {
    ensureCapacity(size() + collection.size());
    int count = size();
    Iterator iter = collection.iterator();
    while (iter.hasNext())
    {
      add(index++, iter.next());
      count--;
    }
    return count != 0;
  }
  
  public void clear()
  {
    Iterator iter = iterator();
    while (iter.hasNext())
    {
      Object object = iter.next();
      this.branchContent.remove(object);
      this.branch.childRemoved(asNode(object));
    }
    super.clear();
  }
  
  public void addLocal(Object object)
  {
    super.add(object);
  }
  
  protected Node asNode(Object object)
  {
    if ((object instanceof Node)) {
      return (Node)object;
    }
    throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.BackedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */