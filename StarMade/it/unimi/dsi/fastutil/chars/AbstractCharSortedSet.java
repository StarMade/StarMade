package it.unimi.dsi.fastutil.chars;

public abstract class AbstractCharSortedSet
  extends AbstractCharSet
  implements CharSortedSet
{
  public CharSortedSet headSet(Character local_to)
  {
    return headSet(local_to.charValue());
  }
  
  public CharSortedSet tailSet(Character from)
  {
    return tailSet(from.charValue());
  }
  
  public CharSortedSet subSet(Character from, Character local_to)
  {
    return subSet(from.charValue(), local_to.charValue());
  }
  
  public Character first()
  {
    return Character.valueOf(firstChar());
  }
  
  public Character last()
  {
    return Character.valueOf(lastChar());
  }
  
  @Deprecated
  public CharBidirectionalIterator charIterator()
  {
    return iterator();
  }
  
  public abstract CharBidirectionalIterator iterator();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractCharSortedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */