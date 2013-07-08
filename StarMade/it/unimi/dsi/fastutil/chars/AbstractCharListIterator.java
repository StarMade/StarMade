package it.unimi.dsi.fastutil.chars;

public abstract class AbstractCharListIterator
  extends AbstractCharBidirectionalIterator
  implements CharListIterator
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharListIterator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */