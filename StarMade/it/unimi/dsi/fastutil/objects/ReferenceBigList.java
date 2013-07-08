package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.BigList;

public abstract interface ReferenceBigList<K>
  extends BigList<K>, ReferenceCollection<K>
{
  public abstract ObjectBigListIterator<K> iterator();
  
  public abstract ObjectBigListIterator<K> listIterator();
  
  public abstract ObjectBigListIterator<K> listIterator(long paramLong);
  
  public abstract ReferenceBigList<K> subList(long paramLong1, long paramLong2);
  
  public abstract void getElements(long paramLong1, Object[][] paramArrayOfObject, long paramLong2, long paramLong3);
  
  public abstract void removeElements(long paramLong1, long paramLong2);
  
  public abstract void addElements(long paramLong, K[][] paramArrayOfK);
  
  public abstract void addElements(long paramLong1, K[][] paramArrayOfK, long paramLong2, long paramLong3);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */