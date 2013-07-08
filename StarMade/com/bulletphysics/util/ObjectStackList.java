package com.bulletphysics.util;

public class ObjectStackList<T>
  extends StackList<T>
{
  private Class<T> cls;
  
  public ObjectStackList(Class<T> cls)
  {
    super(false);
    this.cls = cls;
  }
  
  protected T create()
  {
    try
    {
      return this.cls.newInstance();
    }
    catch (InstantiationException local_e)
    {
      throw new IllegalStateException(local_e);
    }
    catch (IllegalAccessException local_e)
    {
      throw new IllegalStateException(local_e);
    }
  }
  
  protected void copy(T dest, T src)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.ObjectStackList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */