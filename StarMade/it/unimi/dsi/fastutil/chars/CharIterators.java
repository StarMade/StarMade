package it.unimi.dsi.fastutil.chars;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CharIterators
{
  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
  
  public static CharListIterator singleton(char element)
  {
    return new SingletonIterator(element);
  }
  
  public static CharListIterator wrap(char[] array, int offset, int length)
  {
    CharArrays.ensureOffsetLength(array, offset, length);
    return new ArrayIterator(array, offset, length);
  }
  
  public static CharListIterator wrap(char[] array)
  {
    return new ArrayIterator(array, 0, array.length);
  }
  
  public static int unwrap(CharIterator local_i, char[] array, int offset, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    if ((offset < 0) || (offset + max > array.length)) {
      throw new IllegalArgumentException();
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      array[(offset++)] = local_i.nextChar();
    }
    return max - local_j - 1;
  }
  
  public static int unwrap(CharIterator local_i, char[] array)
  {
    return unwrap(local_i, array, 0, array.length);
  }
  
  public static char[] unwrap(CharIterator local_i, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    char[] array = new char[16];
    int local_j = 0;
    while ((max-- != 0) && (local_i.hasNext()))
    {
      if (local_j == array.length) {
        array = CharArrays.grow(array, local_j + 1);
      }
      array[(local_j++)] = local_i.nextChar();
    }
    return CharArrays.trim(array, local_j);
  }
  
  public static char[] unwrap(CharIterator local_i)
  {
    return unwrap(local_i, 2147483647);
  }
  
  public static int unwrap(CharIterator local_i, CharCollection local_c, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_c.add(local_i.nextChar());
    }
    return max - local_j - 1;
  }
  
  public static long unwrap(CharIterator local_i, CharCollection local_c)
  {
    for (long local_n = 0L; local_i.hasNext(); local_n += 1L) {
      local_c.add(local_i.nextChar());
    }
    return local_n;
  }
  
  public static int pour(CharIterator local_i, CharCollection local_s, int max)
  {
    if (max < 0) {
      throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
    }
    int local_j = max;
    while ((local_j-- != 0) && (local_i.hasNext())) {
      local_s.add(local_i.nextChar());
    }
    return max - local_j - 1;
  }
  
  public static int pour(CharIterator local_i, CharCollection local_s)
  {
    return pour(local_i, local_s, 2147483647);
  }
  
  public static CharList pour(CharIterator local_i, int max)
  {
    CharArrayList local_l = new CharArrayList();
    pour(local_i, local_l, max);
    local_l.trim();
    return local_l;
  }
  
  public static CharList pour(CharIterator local_i)
  {
    return pour(local_i, 2147483647);
  }
  
  public static CharIterator asCharIterator(Iterator local_i)
  {
    if ((local_i instanceof CharIterator)) {
      return (CharIterator)local_i;
    }
    return new IteratorWrapper(local_i);
  }
  
  public static CharListIterator asCharIterator(ListIterator local_i)
  {
    if ((local_i instanceof CharListIterator)) {
      return (CharListIterator)local_i;
    }
    return new ListIteratorWrapper(local_i);
  }
  
  public static CharListIterator fromTo(char from, char local_to)
  {
    return new IntervalIterator(from, local_to);
  }
  
  public static CharIterator concat(CharIterator[] local_a)
  {
    return concat(local_a, 0, local_a.length);
  }
  
  public static CharIterator concat(CharIterator[] local_a, int offset, int length)
  {
    return new IteratorConcatenator(local_a, offset, length);
  }
  
  public static CharIterator unmodifiable(CharIterator local_i)
  {
    return new UnmodifiableIterator(local_i);
  }
  
  public static CharBidirectionalIterator unmodifiable(CharBidirectionalIterator local_i)
  {
    return new UnmodifiableBidirectionalIterator(local_i);
  }
  
  public static CharListIterator unmodifiable(CharListIterator local_i)
  {
    return new UnmodifiableListIterator(local_i);
  }
  
  public static class UnmodifiableListIterator
    extends AbstractCharListIterator
  {
    protected final CharListIterator field_67;
    
    public UnmodifiableListIterator(CharListIterator local_i)
    {
      this.field_67 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_67.hasPrevious();
    }
    
    public char nextChar()
    {
      return this.field_67.nextChar();
    }
    
    public char previousChar()
    {
      return this.field_67.previousChar();
    }
    
    public int nextIndex()
    {
      return this.field_67.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_67.previousIndex();
    }
    
    public Character next()
    {
      return (Character)this.field_67.next();
    }
    
    public Character previous()
    {
      return (Character)this.field_67.previous();
    }
  }
  
  public static class UnmodifiableBidirectionalIterator
    extends AbstractCharBidirectionalIterator
  {
    protected final CharBidirectionalIterator field_67;
    
    public UnmodifiableBidirectionalIterator(CharBidirectionalIterator local_i)
    {
      this.field_67 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_67.hasPrevious();
    }
    
    public char nextChar()
    {
      return this.field_67.nextChar();
    }
    
    public char previousChar()
    {
      return this.field_67.previousChar();
    }
    
    public Character next()
    {
      return (Character)this.field_67.next();
    }
    
    public Character previous()
    {
      return (Character)this.field_67.previous();
    }
  }
  
  public static class UnmodifiableIterator
    extends AbstractCharIterator
  {
    protected final CharIterator field_67;
    
    public UnmodifiableIterator(CharIterator local_i)
    {
      this.field_67 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public char nextChar()
    {
      return this.field_67.nextChar();
    }
    
    public Character next()
    {
      return (Character)this.field_67.next();
    }
  }
  
  private static class IteratorConcatenator
    extends AbstractCharIterator
  {
    final CharIterator[] field_350;
    int offset;
    int length;
    int lastOffset = -1;
    
    public IteratorConcatenator(CharIterator[] local_a, int offset, int length)
    {
      this.field_350 = local_a;
      this.offset = offset;
      this.length = length;
      advance();
    }
    
    private void advance()
    {
      while ((this.length != 0) && (!this.field_350[this.offset].hasNext()))
      {
        this.length -= 1;
        this.offset += 1;
      }
    }
    
    public boolean hasNext()
    {
      return this.length > 0;
    }
    
    public char nextChar()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      char next = this.field_350[(this.lastOffset = this.offset)].nextChar();
      advance();
      return next;
    }
    
    public void remove()
    {
      if (this.lastOffset == -1) {
        throw new IllegalStateException();
      }
      this.field_350[this.lastOffset].remove();
    }
    
    public int skip(int local_n)
    {
      this.lastOffset = -1;
      int skipped = 0;
      while ((skipped < local_n) && (this.length != 0))
      {
        skipped += this.field_350[this.offset].skip(local_n - skipped);
        if (this.field_350[this.offset].hasNext()) {
          break;
        }
        this.length -= 1;
        this.offset += 1;
      }
      return skipped;
    }
  }
  
  private static class IntervalIterator
    extends AbstractCharListIterator
  {
    private final char from;
    private final char field_425;
    char curr;
    
    public IntervalIterator(char from, char local_to)
    {
      this.from = (this.curr = from);
      this.field_425 = local_to;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.field_425;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > this.from;
    }
    
    public char nextChar()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.curr++;
    }
    
    public char previousChar()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return this.curr = (char)(this.curr - '\001');
    }
    
    public int nextIndex()
    {
      return this.curr - this.from;
    }
    
    public int previousIndex()
    {
      return this.curr - this.from - 1;
    }
    
    public int skip(int local_n)
    {
      if (this.curr + local_n <= this.field_425)
      {
        this.curr = ((char)(this.curr + local_n));
        return local_n;
      }
      local_n = this.field_425 - this.curr;
      this.curr = this.field_425;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (this.curr - local_n >= this.from)
      {
        this.curr = ((char)(this.curr - local_n));
        return local_n;
      }
      local_n = this.curr - this.from;
      this.curr = this.from;
      return local_n;
    }
  }
  
  private static class ListIteratorWrapper
    extends AbstractCharListIterator
  {
    final ListIterator<Character> field_67;
    
    public ListIteratorWrapper(ListIterator<Character> local_i)
    {
      this.field_67 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public boolean hasPrevious()
    {
      return this.field_67.hasPrevious();
    }
    
    public int nextIndex()
    {
      return this.field_67.nextIndex();
    }
    
    public int previousIndex()
    {
      return this.field_67.previousIndex();
    }
    
    public void set(char local_k)
    {
      this.field_67.set(Character.valueOf(local_k));
    }
    
    public void add(char local_k)
    {
      this.field_67.add(Character.valueOf(local_k));
    }
    
    public void remove()
    {
      this.field_67.remove();
    }
    
    public char nextChar()
    {
      return ((Character)this.field_67.next()).charValue();
    }
    
    public char previousChar()
    {
      return ((Character)this.field_67.previous()).charValue();
    }
  }
  
  private static class IteratorWrapper
    extends AbstractCharIterator
  {
    final Iterator<Character> field_67;
    
    public IteratorWrapper(Iterator<Character> local_i)
    {
      this.field_67 = local_i;
    }
    
    public boolean hasNext()
    {
      return this.field_67.hasNext();
    }
    
    public void remove()
    {
      this.field_67.remove();
    }
    
    public char nextChar()
    {
      return ((Character)this.field_67.next()).charValue();
    }
  }
  
  private static class ArrayIterator
    extends AbstractCharListIterator
  {
    private final char[] array;
    private final int offset;
    private final int length;
    private int curr;
    
    public ArrayIterator(char[] array, int offset, int length)
    {
      this.array = array;
      this.offset = offset;
      this.length = length;
    }
    
    public boolean hasNext()
    {
      return this.curr < this.length;
    }
    
    public boolean hasPrevious()
    {
      return this.curr > 0;
    }
    
    public char nextChar()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + this.curr++)];
    }
    
    public char previousChar()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      return this.array[(this.offset + --this.curr)];
    }
    
    public int skip(int local_n)
    {
      if (local_n <= this.length - this.curr)
      {
        this.curr += local_n;
        return local_n;
      }
      local_n = this.length - this.curr;
      this.curr = this.length;
      return local_n;
    }
    
    public int back(int local_n)
    {
      if (local_n <= this.curr)
      {
        this.curr -= local_n;
        return local_n;
      }
      local_n = this.curr;
      this.curr = 0;
      return local_n;
    }
    
    public int nextIndex()
    {
      return this.curr;
    }
    
    public int previousIndex()
    {
      return this.curr - 1;
    }
  }
  
  private static class SingletonIterator
    extends AbstractCharListIterator
  {
    private final char element;
    private int curr;
    
    public SingletonIterator(char element)
    {
      this.element = element;
    }
    
    public boolean hasNext()
    {
      return this.curr == 0;
    }
    
    public boolean hasPrevious()
    {
      return this.curr == 1;
    }
    
    public char nextChar()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = 1;
      return this.element;
    }
    
    public char previousChar()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = 0;
      return this.element;
    }
    
    public int nextIndex()
    {
      return this.curr;
    }
    
    public int previousIndex()
    {
      return this.curr - 1;
    }
  }
  
  public static class EmptyIterator
    extends AbstractCharListIterator
    implements Serializable, Cloneable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    
    public boolean hasNext()
    {
      return false;
    }
    
    public boolean hasPrevious()
    {
      return false;
    }
    
    public char nextChar()
    {
      throw new NoSuchElementException();
    }
    
    public char previousChar()
    {
      throw new NoSuchElementException();
    }
    
    public int nextIndex()
    {
      return 0;
    }
    
    public int previousIndex()
    {
      return -1;
    }
    
    public int skip(int local_n)
    {
      return 0;
    }
    
    public int back(int local_n)
    {
      return 0;
    }
    
    public Object clone()
    {
      return CharIterators.EMPTY_ITERATOR;
    }
    
    private Object readResolve()
    {
      return CharIterators.EMPTY_ITERATOR;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */