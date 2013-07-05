package org.hsqldb.persist;

public abstract interface PersistentStoreCollection
{
  public abstract PersistentStore getStore(Object paramObject);

  public abstract void setStore(Object paramObject, PersistentStore paramPersistentStore);

  public abstract void release();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.PersistentStoreCollection
 * JD-Core Version:    0.6.2
 */