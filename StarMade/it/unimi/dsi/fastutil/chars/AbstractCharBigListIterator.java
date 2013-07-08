package it.unimi.dsi.fastutil.chars;

public abstract class AbstractCharBigListIterator
  extends AbstractCharBidirectionalIterator
  implements CharBigListIterator
{
  public void set(Character local_ok)
  {
    set(local_ok.charValue());
  }
  
  public void add(Character local_ok)
  {
    add(local_ok.charValue());
  }
  
  public void set(char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public void add(char local_k)
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasNext())) {
      nextChar();
    }
    return local_n - local_i - 1L;
  }
  
  public long back(long local_n)
  {
    long local_i = local_n;
    while ((local_i-- != 0L) && (hasPrevious())) {
      previousChar();
    }
    return local_n - local_i - 1L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharBigListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */