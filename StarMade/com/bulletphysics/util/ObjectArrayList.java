/*   1:    */package com.bulletphysics.util;
/*   2:    */
/*   3:    */import java.io.Externalizable;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.ObjectInput;
/*   6:    */import java.io.ObjectOutput;
/*   7:    */import java.util.AbstractList;
/*   8:    */import java.util.RandomAccess;
/*   9:    */
/*  34:    */public final class ObjectArrayList<T>
/*  35:    */  extends AbstractList<T>
/*  36:    */  implements RandomAccess, Externalizable
/*  37:    */{
/*  38:    */  private T[] array;
/*  39:    */  private int size;
/*  40:    */  
/*  41:    */  public ObjectArrayList()
/*  42:    */  {
/*  43: 43 */    this(16);
/*  44:    */  }
/*  45:    */  
/*  46:    */  public ObjectArrayList(int initialCapacity)
/*  47:    */  {
/*  48: 48 */    this.array = ((Object[])new Object[initialCapacity]);
/*  49:    */  }
/*  50:    */  
/*  51:    */  public boolean add(T value)
/*  52:    */  {
/*  53: 53 */    if (this.size == this.array.length) {
/*  54: 54 */      expand();
/*  55:    */    }
/*  56:    */    
/*  57: 57 */    this.array[(this.size++)] = value;
/*  58: 58 */    return true;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public void add(int index, T value)
/*  62:    */  {
/*  63: 63 */    if (this.size == this.array.length) {
/*  64: 64 */      expand();
/*  65:    */    }
/*  66:    */    
/*  67: 67 */    int num = this.size - index;
/*  68: 68 */    if (num > 0) {
/*  69: 69 */      System.arraycopy(this.array, index, this.array, index + 1, num);
/*  70:    */    }
/*  71:    */    
/*  72: 72 */    this.array[index] = value;
/*  73: 73 */    this.size += 1;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public T remove(int index)
/*  77:    */  {
/*  78: 78 */    if ((index < 0) || (index >= this.size)) throw new IndexOutOfBoundsException();
/*  79: 79 */    T prev = this.array[index];
/*  80: 80 */    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*  81: 81 */    this.array[(this.size - 1)] = null;
/*  82: 82 */    this.size -= 1;
/*  83: 83 */    return prev;
/*  84:    */  }
/*  85:    */  
/*  86:    */  private void expand()
/*  87:    */  {
/*  88: 88 */    T[] newArray = (Object[])new Object[this.array.length << 1];
/*  89: 89 */    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/*  90: 90 */    this.array = newArray;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void removeQuick(int index) {
/*  94: 94 */    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*  95: 95 */    this.array[(this.size - 1)] = null;
/*  96: 96 */    this.size -= 1;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public T get(int index) {
/* 100:100 */    if (index >= this.size) throw new IndexOutOfBoundsException();
/* 101:101 */    return this.array[index];
/* 102:    */  }
/* 103:    */  
/* 104:    */  public T getQuick(int index) {
/* 105:105 */    return this.array[index];
/* 106:    */  }
/* 107:    */  
/* 108:    */  public T set(int index, T value)
/* 109:    */  {
/* 110:110 */    if (index >= this.size) throw new IndexOutOfBoundsException();
/* 111:111 */    T old = this.array[index];
/* 112:112 */    this.array[index] = value;
/* 113:113 */    return old;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public void setQuick(int index, T value) {
/* 117:117 */    this.array[index] = value;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public int size() {
/* 121:121 */    return this.size;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public int capacity() {
/* 125:125 */    return this.array.length;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void clear()
/* 129:    */  {
/* 130:130 */    this.size = 0;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public int indexOf(Object o)
/* 134:    */  {
/* 135:135 */    int _size = this.size;
/* 136:136 */    T[] _array = this.array;
/* 137:137 */    for (int i = 0; i < _size; i++) {
/* 138:138 */      if (o == null ? _array[i] == null : o.equals(_array[i])) {
/* 139:139 */        return i;
/* 140:    */      }
/* 141:    */    }
/* 142:142 */    return -1;
/* 143:    */  }
/* 144:    */  
/* 145:    */  public void writeExternal(ObjectOutput out) throws IOException {
/* 146:146 */    out.writeInt(this.size);
/* 147:147 */    for (int i = 0; i < this.size; i++) {
/* 148:148 */      out.writeObject(this.array[i]);
/* 149:    */    }
/* 150:    */  }
/* 151:    */  
/* 152:    */  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 153:153 */    this.size = in.readInt();
/* 154:154 */    int cap = 16;
/* 155:155 */    while (cap < this.size) cap <<= 1;
/* 156:156 */    this.array = ((Object[])new Object[cap]);
/* 157:157 */    for (int i = 0; i < this.size; i++) {
/* 158:158 */      this.array[i] = in.readObject();
/* 159:    */    }
/* 160:    */  }
/* 161:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ObjectArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */