package it.unimi.dsi.fastutil.chars;

public abstract class AbstractCharIterator
  implements CharIterator
{
  public char nextChar()
  {
    return next().charValue();
  }
  
  public Character next()
  {
    return Character.valueOf(nextChar());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public int skip(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasNext())) {
      nextChar();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */