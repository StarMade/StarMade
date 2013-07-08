package com.bulletphysics.util;

public abstract class StackList<T>
{
  private final ObjectArrayList<T> list = new ObjectArrayList();
  private T returnObj;
  private int[] stack = new int[512];
  private int stackCount = 0;
  private int pos = 0;
  
  public StackList()
  {
    this.returnObj = create();
  }
  
  protected StackList(boolean unused) {}
  
  public final void push()
  {
    this.stack[(this.stackCount++)] = this.pos;
  }
  
  public final void pop()
  {
    this.pos = this.stack[(--this.stackCount)];
  }
  
  public T get()
  {
    if (this.pos == this.list.size()) {
      expand();
    }
    return this.list.getQuick(this.pos++);
  }
  
  public final T returning(T obj)
  {
    copy(this.returnObj, obj);
    return this.returnObj;
  }
  
  protected abstract T create();
  
  protected abstract void copy(T paramT1, T paramT2);
  
  private void expand()
  {
    this.list.add(create());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.StackList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */