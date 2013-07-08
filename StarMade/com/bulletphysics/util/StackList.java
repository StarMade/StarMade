/*   1:    */package com.bulletphysics.util;
/*   2:    */
/*  49:    */public abstract class StackList<T>
/*  50:    */{
/*  51: 51 */  private final ObjectArrayList<T> list = new ObjectArrayList();
/*  52:    */  
/*  53:    */  private T returnObj;
/*  54: 54 */  private int[] stack = new int[512];
/*  55: 55 */  private int stackCount = 0;
/*  56:    */  
/*  57: 57 */  private int pos = 0;
/*  58:    */  
/*  59:    */  public StackList() {
/*  60: 60 */    this.returnObj = create();
/*  61:    */  }
/*  62:    */  
/*  67:    */  protected StackList(boolean unused) {}
/*  68:    */  
/*  72:    */  public final void push()
/*  73:    */  {
/*  74: 74 */    this.stack[(this.stackCount++)] = this.pos;
/*  75:    */  }
/*  76:    */  
/*  79:    */  public final void pop()
/*  80:    */  {
/*  81: 81 */    this.pos = this.stack[(--this.stackCount)];
/*  82:    */  }
/*  83:    */  
/*  91:    */  public T get()
/*  92:    */  {
/*  93: 93 */    if (this.pos == this.list.size()) {
/*  94: 94 */      expand();
/*  95:    */    }
/*  96:    */    
/*  97: 97 */    return this.list.getQuick(this.pos++);
/*  98:    */  }
/*  99:    */  
/* 109:    */  public final T returning(T obj)
/* 110:    */  {
/* 111:111 */    copy(this.returnObj, obj);
/* 112:112 */    return this.returnObj;
/* 113:    */  }
/* 114:    */  
/* 119:    */  protected abstract T create();
/* 120:    */  
/* 124:    */  protected abstract void copy(T paramT1, T paramT2);
/* 125:    */  
/* 129:    */  private void expand()
/* 130:    */  {
/* 131:131 */    this.list.add(create());
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.StackList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */