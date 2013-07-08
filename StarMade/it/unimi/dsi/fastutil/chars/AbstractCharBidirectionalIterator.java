package it.unimi.dsi.fastutil.chars;

public abstract class AbstractCharBidirectionalIterator
  extends AbstractCharIterator
  implements CharBidirectionalIterator
{
  public char previousChar()
  {
    return previous().charValue();
  }
  
  public Character previous()
  {
    return Character.valueOf(previousChar());
  }
  
  public int back(int local_n)
  {
    int local_i = local_n;
    while ((local_i-- != 0) && (hasPrevious())) {
      previousChar();
    }
    return local_n - local_i - 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharBidirectionalIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */