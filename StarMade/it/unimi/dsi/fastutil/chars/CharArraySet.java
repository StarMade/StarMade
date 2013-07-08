package it.unimi.dsi.fastutil.chars;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class CharArraySet
  extends AbstractCharSet
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private transient char[] field_61;
  private int size;
  
  public CharArraySet(char[] local_a)
  {
    this.field_61 = local_a;
    this.size = local_a.length;
  }
  
  public CharArraySet()
  {
    this.field_61 = CharArrays.EMPTY_ARRAY;
  }
  
  public CharArraySet(int capacity)
  {
    this.field_61 = new char[capacity];
  }
  
  public CharArraySet(CharCollection local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public CharArraySet(Collection<? extends Character> local_c)
  {
    this(local_c.size());
    addAll(local_c);
  }
  
  public CharArraySet(char[] local_a, int size)
  {
    this.field_61 = local_a;
    this.size = size;
    if (size > local_a.length) {
      throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + local_a.length + ")");
    }
  }
  
  private int findKey(char local_o)
  {
    int local_i = this.size;
    while (local_i-- != 0) {
      if (this.field_61[local_i] == local_o) {
        return local_i;
      }
    }
    return -1;
  }
  
  public CharIterator iterator()
  {
    return CharIterators.wrap(this.field_61, 0, this.size);
  }
  
  public boolean contains(char local_k)
  {
    return findKey(local_k) != -1;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public boolean remove(char local_k)
  {
    int pos = findKey(local_k);
    if (pos == -1) {
      return false;
    }
    int tail = this.size - pos - 1;
    for (int local_i = 0; local_i < tail; local_i++) {
      this.field_61[(pos + local_i)] = this.field_61[(pos + local_i + 1)];
    }
    this.size -= 1;
    return true;
  }
  
  public boolean add(char local_k)
  {
    int pos = findKey(local_k);
    if (pos != -1) {
      return false;
    }
    if (this.size == this.field_61.length)
    {
      char[] local_b = new char[this.size == 0 ? 2 : this.size * 2];
      int local_i = this.size;
      while (local_i-- != 0) {
        local_b[local_i] = this.field_61[local_i];
      }
      this.field_61 = local_b;
    }
    this.field_61[(this.size++)] = local_k;
    return true;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public CharArraySet clone()
  {
    CharArraySet local_c;
    try
    {
      local_c = (CharArraySet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.field_61 = ((char[])this.field_61.clone());
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    local_s.defaultWriteObject();
    for (int local_i = 0; local_i < this.size; local_i++) {
      local_s.writeChar(this.field_61[local_i]);
    }
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_61 = new char[this.size];
    for (int local_i = 0; local_i < this.size; local_i++) {
      this.field_61[local_i] = local_s.readChar();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArraySet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */