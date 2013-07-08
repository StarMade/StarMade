package org.hsqldb.lib;

public class HashMappedList
  extends HashMap
{
  public HashMappedList()
  {
    this(8);
  }
  
  public HashMappedList(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt);
    this.isList = true;
  }
  
  public Object get(int paramInt)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    return this.objectValueTable[paramInt];
  }
  
  public Object remove(Object paramObject)
  {
    int i = getLookup(paramObject, paramObject.hashCode());
    if (i < 0) {
      return null;
    }
    Object localObject = super.remove(paramObject);
    removeRow(i);
    return localObject;
  }
  
  public Object remove(int paramInt)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    return remove(this.objectKeyTable[paramInt]);
  }
  
  public boolean add(Object paramObject1, Object paramObject2)
  {
    int i = getLookup(paramObject1, paramObject1.hashCode());
    if (i >= 0) {
      return false;
    }
    super.put(paramObject1, paramObject2);
    return true;
  }
  
  public Object put(Object paramObject1, Object paramObject2)
  {
    return super.put(paramObject1, paramObject2);
  }
  
  public Object set(int paramInt, Object paramObject)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    Object localObject = this.objectKeyTable[paramInt];
    this.objectKeyTable[paramInt] = paramObject;
    return localObject;
  }
  
  public boolean insert(int paramInt, Object paramObject1, Object paramObject2)
    throws IndexOutOfBoundsException
  {
    if ((paramInt < 0) || (paramInt > size())) {
      throw new IndexOutOfBoundsException();
    }
    int i = getLookup(paramObject1, paramObject1.hashCode());
    if (i >= 0) {
      return false;
    }
    if (paramInt == size()) {
      return add(paramObject1, paramObject2);
    }
    HashMappedList localHashMappedList = new HashMappedList(size());
    for (int j = paramInt; j < size(); j++) {
      localHashMappedList.add(getKey(j), get(j));
    }
    for (j = size() - 1; j >= paramInt; j--) {
      remove(j);
    }
    for (j = 0; j < localHashMappedList.size(); j++) {
      add(localHashMappedList.getKey(j), localHashMappedList.get(j));
    }
    return true;
  }
  
  public boolean set(int paramInt, Object paramObject1, Object paramObject2)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    if ((keySet().contains(paramObject1)) && (getIndex(paramObject1) != paramInt)) {
      return false;
    }
    super.remove(this.objectKeyTable[paramInt]);
    super.put(paramObject1, paramObject2);
    return true;
  }
  
  public boolean setKey(int paramInt, Object paramObject)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    Object localObject = this.objectValueTable[paramInt];
    return set(paramInt, paramObject, localObject);
  }
  
  public boolean setValue(int paramInt, Object paramObject)
    throws IndexOutOfBoundsException
  {
    Object localObject = this.objectValueTable[paramInt];
    boolean bool;
    if (paramObject == null) {
      bool = paramObject != localObject;
    } else {
      bool = !paramObject.equals(localObject);
    }
    this.objectValueTable[paramInt] = paramObject;
    return bool;
  }
  
  public Object getKey(int paramInt)
    throws IndexOutOfBoundsException
  {
    checkRange(paramInt);
    return this.objectKeyTable[paramInt];
  }
  
  public int getIndex(Object paramObject)
  {
    return getLookup(paramObject, paramObject.hashCode());
  }
  
  public Object[] toValuesArray(Object[] paramArrayOfObject)
  {
    int i = size();
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length < i)) {
      paramArrayOfObject = new Object[i];
    }
    for (int j = 0; j < i; j++) {
      paramArrayOfObject[j] = this.objectValueTable[j];
    }
    return paramArrayOfObject;
  }
  
  public Object[] toKeysArray(Object[] paramArrayOfObject)
  {
    int i = size();
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length < i)) {
      paramArrayOfObject = new Object[i];
    }
    for (int j = 0; j < i; j++) {
      paramArrayOfObject[j] = this.objectKeyTable[j];
    }
    return paramArrayOfObject;
  }
  
  private void checkRange(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= size())) {
      throw new IndexOutOfBoundsException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.HashMappedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */