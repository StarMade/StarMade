package org.hsqldb.lib;

import java.util.NoSuchElementException;

abstract class BaseList
{
  protected int elementCount;

  abstract Object get(int paramInt);

  abstract Object remove(int paramInt);

  abstract boolean add(Object paramObject);

  abstract int size();

  public boolean contains(Object paramObject)
  {
    return indexOf(paramObject) != -1;
  }

  public boolean remove(Object paramObject)
  {
    int i = indexOf(paramObject);
    if (i == -1)
      return false;
    remove(i);
    return true;
  }

  public int indexOf(Object paramObject)
  {
    int i = 0;
    int j = size();
    while (i < j)
    {
      Object localObject = get(i);
      if (localObject == null)
      {
        if (paramObject == null)
          return i;
      }
      else if (localObject.equals(paramObject))
        return i;
      i++;
    }
    return -1;
  }

  public boolean addAll(Collection paramCollection)
  {
    boolean bool = false;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      bool = true;
      add(localIterator.next());
    }
    return bool;
  }

  public boolean addAll(Object[] paramArrayOfObject)
  {
    boolean bool = false;
    for (int i = 0; i < paramArrayOfObject.length; i++)
    {
      bool = true;
      add(paramArrayOfObject[i]);
    }
    return bool;
  }

  public boolean isEmpty()
  {
    return this.elementCount == 0;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(32 + this.elementCount * 3);
    localStringBuffer.append("List : size=");
    localStringBuffer.append(this.elementCount);
    localStringBuffer.append(' ');
    localStringBuffer.append('{');
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      localStringBuffer.append(localIterator.next());
      if (localIterator.hasNext())
      {
        localStringBuffer.append(',');
        localStringBuffer.append(' ');
      }
    }
    localStringBuffer.append('}');
    return localStringBuffer.toString();
  }

  public Iterator iterator()
  {
    return new BaseListIterator(null);
  }

  private class BaseListIterator
    implements Iterator
  {
    int counter = 0;
    boolean removed;

    private BaseListIterator()
    {
    }

    public boolean hasNext()
    {
      return this.counter < BaseList.this.elementCount;
    }

    public Object next()
    {
      if (this.counter < BaseList.this.elementCount)
      {
        this.removed = false;
        Object localObject = BaseList.this.get(this.counter);
        this.counter += 1;
        return localObject;
      }
      throw new NoSuchElementException();
    }

    public int nextInt()
    {
      throw new NoSuchElementException();
    }

    public long nextLong()
    {
      throw new NoSuchElementException();
    }

    public void remove()
    {
      if (this.removed)
        throw new NoSuchElementException("Iterator");
      this.removed = true;
      if (this.counter != 0)
      {
        BaseList.this.remove(this.counter - 1);
        this.counter -= 1;
        return;
      }
      throw new NoSuchElementException();
    }

    public void setValue(Object paramObject)
    {
      throw new NoSuchElementException();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.BaseList
 * JD-Core Version:    0.6.2
 */