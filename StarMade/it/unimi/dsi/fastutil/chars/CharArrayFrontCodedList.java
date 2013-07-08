package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.longs.LongArrays;
import it.unimi.dsi.fastutil.objects.AbstractObjectList;
import it.unimi.dsi.fastutil.objects.AbstractObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CharArrayFrontCodedList
  extends AbstractObjectList<char[]>
  implements Serializable, Cloneable
{
  public static final long serialVersionUID = 1L;
  protected final int field_74;
  protected final int ratio;
  protected final char[][] array;
  protected transient long[] field_365;
  
  public CharArrayFrontCodedList(Iterator<char[]> arrays, int ratio)
  {
    if (ratio < 1) {
      throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
    }
    char[][] array = CharBigArrays.EMPTY_BIG_ARRAY;
    long[] local_p = LongArrays.EMPTY_ARRAY;
    char[][] local_a = new char[2][];
    long curSize = 0L;
    int local_n = 0;
    int local_b = 0;
    while (arrays.hasNext())
    {
      local_a[local_b] = ((char[])arrays.next());
      int length = local_a[local_b].length;
      if (local_n % ratio == 0)
      {
        local_p = LongArrays.grow(local_p, local_n / ratio + 1);
        local_p[(local_n / ratio)] = curSize;
        array = CharBigArrays.grow(array, curSize + count(length) + length, curSize);
        curSize += writeInt(array, length, curSize);
        CharBigArrays.copyToBig(local_a[local_b], 0, array, curSize, length);
        curSize += length;
      }
      else
      {
        int minLength = local_a[(1 - local_b)].length;
        if (length < minLength) {
          minLength = length;
        }
        for (int common = 0; (common < minLength) && (local_a[0][common] == local_a[1][common]); common++) {}
        length -= common;
        array = CharBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
        curSize += writeInt(array, length, curSize);
        curSize += writeInt(array, common, curSize);
        CharBigArrays.copyToBig(local_a[local_b], common, array, curSize, length);
        curSize += length;
      }
      local_b = 1 - local_b;
      local_n++;
    }
    this.field_74 = local_n;
    this.ratio = ratio;
    this.array = CharBigArrays.trim(array, curSize);
    this.field_365 = LongArrays.trim(local_p, (local_n + ratio - 1) / ratio);
  }
  
  public CharArrayFrontCodedList(Collection<char[]> local_c, int ratio)
  {
    this(local_c.iterator(), ratio);
  }
  
  private static int readInt(char[][] local_a, long pos)
  {
    char local_c0 = CharBigArrays.get(local_a, pos);
    return local_c0 < 32768 ? local_c0 : (local_c0 & 0x7FFF) << '\020' | CharBigArrays.get(local_a, pos + 1L);
  }
  
  private static int count(int length)
  {
    return length < 32768 ? 1 : 2;
  }
  
  private static int writeInt(char[][] local_a, int length, long pos)
  {
    if (length < 32768)
    {
      CharBigArrays.set(local_a, pos, (char)length);
      return 1;
    }
    CharBigArrays.set(local_a, pos++, (char)(length >>> 16 | 0x8000));
    CharBigArrays.set(local_a, pos, (char)(length & 0xFFFF));
    return 2;
  }
  
  public int ratio()
  {
    return this.ratio;
  }
  
  private int length(int index)
  {
    char[][] array = this.array;
    int delta = index % this.ratio;
    long pos = this.field_365[(index / this.ratio)];
    int length = readInt(array, pos);
    if (delta == 0) {
      return length;
    }
    pos += count(length) + length;
    length = readInt(array, pos);
    int common = readInt(array, pos + count(length));
    for (int local_i = 0; local_i < delta - 1; local_i++)
    {
      pos += count(length) + count(common) + length;
      length = readInt(array, pos);
      common = readInt(array, pos + count(length));
    }
    return length + common;
  }
  
  public int arrayLength(int index)
  {
    ensureRestrictedIndex(index);
    return length(index);
  }
  
  private int extract(int index, char[] local_a, int offset, int length)
  {
    int delta = index % this.ratio;
    long startPos = this.field_365[(index / this.ratio)];
    long pos;
    int arrayLength = readInt(this.array, pos = startPos);
    int currLen = 0;
    if (delta == 0)
    {
      pos = this.field_365[(index / this.ratio)] + count(arrayLength);
      CharBigArrays.copyFromBig(this.array, pos, local_a, offset, Math.min(length, arrayLength));
      return arrayLength;
    }
    int common = 0;
    for (int local_i = 0; local_i < delta; local_i++)
    {
      long prevArrayPos = pos + count(arrayLength) + (local_i != 0 ? count(common) : 0);
      pos = prevArrayPos + arrayLength;
      arrayLength = readInt(this.array, pos);
      common = readInt(this.array, pos + count(arrayLength));
      int actualCommon = Math.min(common, length);
      if (actualCommon <= currLen)
      {
        currLen = actualCommon;
      }
      else
      {
        CharBigArrays.copyFromBig(this.array, prevArrayPos, local_a, currLen + offset, actualCommon - currLen);
        currLen = actualCommon;
      }
    }
    if (currLen < length) {
      CharBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), local_a, currLen + offset, Math.min(arrayLength, length - currLen));
    }
    return arrayLength + common;
  }
  
  public char[] get(int index)
  {
    return getArray(index);
  }
  
  public char[] getArray(int index)
  {
    ensureRestrictedIndex(index);
    int length = length(index);
    char[] local_a = new char[length];
    extract(index, local_a, 0, length);
    return local_a;
  }
  
  public int get(int index, char[] local_a, int offset, int length)
  {
    ensureRestrictedIndex(index);
    CharArrays.ensureOffsetLength(local_a, offset, length);
    int arrayLength = extract(index, local_a, offset, length);
    if (length >= arrayLength) {
      return arrayLength;
    }
    return length - arrayLength;
  }
  
  public int get(int index, char[] local_a)
  {
    return get(index, local_a, 0, local_a.length);
  }
  
  public int size()
  {
    return this.field_74;
  }
  
  public ObjectListIterator<char[]> listIterator(final int start)
  {
    ensureIndex(start);
    new AbstractObjectListIterator()
    {
      char[] field_55 = CharArrays.EMPTY_ARRAY;
      int field_3 = 0;
      long pos = 0L;
      boolean inSync;
      
      public boolean hasNext()
      {
        return this.field_3 < CharArrayFrontCodedList.this.field_74;
      }
      
      public boolean hasPrevious()
      {
        return this.field_3 > 0;
      }
      
      public int previousIndex()
      {
        return this.field_3 - 1;
      }
      
      public int nextIndex()
      {
        return this.field_3;
      }
      
      public char[] next()
      {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        int length;
        if (this.field_3 % CharArrayFrontCodedList.this.ratio == 0)
        {
          this.pos = CharArrayFrontCodedList.this.field_365[(this.field_3 / CharArrayFrontCodedList.this.ratio)];
          int length = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos);
          this.field_55 = CharArrays.ensureCapacity(this.field_55, length, 0);
          CharBigArrays.copyFromBig(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.count(length), this.field_55, 0, length);
          this.pos += length + CharArrayFrontCodedList.count(length);
          this.inSync = true;
        }
        else if (this.inSync)
        {
          int length = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos);
          int common = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.access$100(length));
          this.field_55 = CharArrays.ensureCapacity(this.field_55, length + common, common);
          CharBigArrays.copyFromBig(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.count(length) + CharArrayFrontCodedList.count(common), this.field_55, common, length);
          this.pos += CharArrayFrontCodedList.count(length) + CharArrayFrontCodedList.count(common) + length;
          length += common;
        }
        else
        {
          this.field_55 = CharArrays.ensureCapacity(this.field_55, length = CharArrayFrontCodedList.this.length(this.field_3), 0);
          CharArrayFrontCodedList.this.extract(this.field_3, this.field_55, 0, length);
        }
        this.field_3 += 1;
        return CharArrays.copy(this.field_55, 0, length);
      }
      
      public char[] previous()
      {
        if (!hasPrevious()) {
          throw new NoSuchElementException();
        }
        this.inSync = false;
        return CharArrayFrontCodedList.this.getArray(--this.field_3);
      }
    };
  }
  
  public CharArrayFrontCodedList clone()
  {
    return this;
  }
  
  public String toString()
  {
    StringBuffer local_s = new StringBuffer();
    local_s.append("[ ");
    for (int local_i = 0; local_i < this.field_74; local_i++)
    {
      if (local_i != 0) {
        local_s.append(", ");
      }
      local_s.append(CharArrayList.wrap(getArray(local_i)).toString());
    }
    local_s.append(" ]");
    return local_s.toString();
  }
  
  protected long[] rebuildPointerArray()
  {
    long[] local_p = new long[(this.field_74 + this.ratio - 1) / this.ratio];
    char[][] local_a = this.array;
    long pos = 0L;
    int local_i = 0;
    int local_j = 0;
    int skip = this.ratio - 1;
    while (local_i < this.field_74)
    {
      int length = readInt(local_a, pos);
      int count = count(length);
      skip++;
      if (skip == this.ratio)
      {
        skip = 0;
        local_p[(local_j++)] = pos;
        pos += count + length;
      }
      else
      {
        pos += count + count(readInt(local_a, pos + count)) + length;
      }
      local_i++;
    }
    return local_p;
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    this.field_365 = rebuildPointerArray();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayFrontCodedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */