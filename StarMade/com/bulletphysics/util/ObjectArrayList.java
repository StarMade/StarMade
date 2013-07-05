/*     */ package com.bulletphysics.util;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.AbstractList;
/*     */ import java.util.RandomAccess;
/*     */ 
/*     */ public final class ObjectArrayList<T> extends AbstractList<T>
/*     */   implements RandomAccess, Externalizable
/*     */ {
/*     */   private T[] array;
/*     */   private int size;
/*     */ 
/*     */   public ObjectArrayList()
/*     */   {
/*  43 */     this(16);
/*     */   }
/*     */ 
/*     */   public ObjectArrayList(int initialCapacity)
/*     */   {
/*  48 */     this.array = ((Object[])new Object[initialCapacity]);
/*     */   }
/*     */ 
/*     */   public boolean add(T value)
/*     */   {
/*  53 */     if (this.size == this.array.length) {
/*  54 */       expand();
/*     */     }
/*     */ 
/*  57 */     this.array[(this.size++)] = value;
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   public void add(int index, T value)
/*     */   {
/*  63 */     if (this.size == this.array.length) {
/*  64 */       expand();
/*     */     }
/*     */ 
/*  67 */     int num = this.size - index;
/*  68 */     if (num > 0) {
/*  69 */       System.arraycopy(this.array, index, this.array, index + 1, num);
/*     */     }
/*     */ 
/*  72 */     this.array[index] = value;
/*  73 */     this.size += 1;
/*     */   }
/*     */ 
/*     */   public T remove(int index)
/*     */   {
/*  78 */     if ((index < 0) || (index >= this.size)) throw new IndexOutOfBoundsException();
/*  79 */     Object prev = this.array[index];
/*  80 */     System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*  81 */     this.array[(this.size - 1)] = null;
/*  82 */     this.size -= 1;
/*  83 */     return prev;
/*     */   }
/*     */ 
/*     */   private void expand()
/*     */   {
/*  88 */     Object[] newArray = (Object[])new Object[this.array.length << 1];
/*  89 */     System.arraycopy(this.array, 0, newArray, 0, this.array.length);
/*  90 */     this.array = newArray;
/*     */   }
/*     */ 
/*     */   public void removeQuick(int index) {
/*  94 */     System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
/*  95 */     this.array[(this.size - 1)] = null;
/*  96 */     this.size -= 1;
/*     */   }
/*     */ 
/*     */   public T get(int index) {
/* 100 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 101 */     return this.array[index];
/*     */   }
/*     */ 
/*     */   public T getQuick(int index) {
/* 105 */     return this.array[index];
/*     */   }
/*     */ 
/*     */   public T set(int index, T value)
/*     */   {
/* 110 */     if (index >= this.size) throw new IndexOutOfBoundsException();
/* 111 */     Object old = this.array[index];
/* 112 */     this.array[index] = value;
/* 113 */     return old;
/*     */   }
/*     */ 
/*     */   public void setQuick(int index, T value) {
/* 117 */     this.array[index] = value;
/*     */   }
/*     */ 
/*     */   public int size() {
/* 121 */     return this.size;
/*     */   }
/*     */ 
/*     */   public int capacity() {
/* 125 */     return this.array.length;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 130 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public int indexOf(Object o)
/*     */   {
/* 135 */     int _size = this.size;
/* 136 */     Object[] _array = this.array;
/* 137 */     for (int i = 0; i < _size; i++) {
/* 138 */       if (o == null ? _array[i] == null : o.equals(_array[i])) {
/* 139 */         return i;
/*     */       }
/*     */     }
/* 142 */     return -1;
/*     */   }
/*     */ 
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 146 */     out.writeInt(this.size);
/* 147 */     for (int i = 0; i < this.size; i++)
/* 148 */       out.writeObject(this.array[i]);
/*     */   }
/*     */ 
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
/*     */   {
/* 153 */     this.size = in.readInt();
/* 154 */     int cap = 16;
/* 155 */     while (cap < this.size) cap <<= 1;
/* 156 */     this.array = ((Object[])new Object[cap]);
/* 157 */     for (int i = 0; i < this.size; i++)
/* 158 */       this.array[i] = in.readObject();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ObjectArrayList
 * JD-Core Version:    0.6.2
 */