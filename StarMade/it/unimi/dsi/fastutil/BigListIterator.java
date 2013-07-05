package it.unimi.dsi.fastutil;

public abstract interface BigListIterator<K> extends BidirectionalIterator<K>
{
  public abstract long nextIndex();

  public abstract long previousIndex();

  public abstract long skip(long paramLong);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.BigListIterator
 * JD-Core Version:    0.6.2
 */