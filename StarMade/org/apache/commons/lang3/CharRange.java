package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class CharRange
  implements Iterable<Character>, Serializable
{
  private static final long serialVersionUID = 8270183163158333422L;
  private final char start;
  private final char end;
  private final boolean negated;
  private transient String iToString;
  
  private CharRange(char start, char end, boolean negated)
  {
    if (start > end)
    {
      char temp = start;
      start = end;
      end = temp;
    }
    this.start = start;
    this.end = end;
    this.negated = negated;
  }
  
  public static CharRange is(char local_ch)
  {
    return new CharRange(local_ch, local_ch, false);
  }
  
  public static CharRange isNot(char local_ch)
  {
    return new CharRange(local_ch, local_ch, true);
  }
  
  public static CharRange isIn(char start, char end)
  {
    return new CharRange(start, end, false);
  }
  
  public static CharRange isNotIn(char start, char end)
  {
    return new CharRange(start, end, true);
  }
  
  public char getStart()
  {
    return this.start;
  }
  
  public char getEnd()
  {
    return this.end;
  }
  
  public boolean isNegated()
  {
    return this.negated;
  }
  
  public boolean contains(char local_ch)
  {
    return ((local_ch >= this.start) && (local_ch <= this.end)) != this.negated;
  }
  
  public boolean contains(CharRange range)
  {
    if (range == null) {
      throw new IllegalArgumentException("The Range must not be null");
    }
    if (this.negated)
    {
      if (range.negated) {
        return (this.start >= range.start) && (this.end <= range.end);
      }
      return (range.end < this.start) || (range.start > this.end);
    }
    if (range.negated) {
      return (this.start == 0) && (this.end == 65535);
    }
    return (this.start <= range.start) && (this.end >= range.end);
  }
  
  public boolean equals(Object obj)
  {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof CharRange)) {
      return false;
    }
    CharRange other = (CharRange)obj;
    return (this.start == other.start) && (this.end == other.end) && (this.negated == other.negated);
  }
  
  public int hashCode()
  {
    return 'S' + this.start + '\007' * this.end + (this.negated ? 1 : 0);
  }
  
  public String toString()
  {
    if (this.iToString == null)
    {
      StringBuilder buf = new StringBuilder(4);
      if (isNegated()) {
        buf.append('^');
      }
      buf.append(this.start);
      if (this.start != this.end)
      {
        buf.append('-');
        buf.append(this.end);
      }
      this.iToString = buf.toString();
    }
    return this.iToString;
  }
  
  public Iterator<Character> iterator()
  {
    return new CharacterIterator(this, null);
  }
  
  private static class CharacterIterator
    implements Iterator<Character>
  {
    private char current;
    private final CharRange range;
    private boolean hasNext;
    
    private CharacterIterator(CharRange local_r)
    {
      this.range = local_r;
      this.hasNext = true;
      if (this.range.negated)
      {
        if (this.range.start == 0)
        {
          if (this.range.end == 65535) {
            this.hasNext = false;
          } else {
            this.current = ((char)(this.range.end + '\001'));
          }
        }
        else {
          this.current = '\000';
        }
      }
      else {
        this.current = this.range.start;
      }
    }
    
    private void prepareNext()
    {
      if (this.range.negated)
      {
        if (this.current == 65535) {
          this.hasNext = false;
        } else if (this.current + '\001' == this.range.start)
        {
          if (this.range.end == 65535) {
            this.hasNext = false;
          } else {
            this.current = ((char)(this.range.end + '\001'));
          }
        }
        else {
          this.current = ((char)(this.current + '\001'));
        }
      }
      else if (this.current < this.range.end) {
        this.current = ((char)(this.current + '\001'));
      } else {
        this.hasNext = false;
      }
    }
    
    public boolean hasNext()
    {
      return this.hasNext;
    }
    
    public Character next()
    {
      if (!this.hasNext) {
        throw new NoSuchElementException();
      }
      char cur = this.current;
      prepareNext();
      return Character.valueOf(cur);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.CharRange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */