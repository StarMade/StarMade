package it.unimi.dsi.fastutil.objects;

import java.util.List;

public abstract interface ReferenceList<K>
  extends List<K>, ReferenceCollection<K>
{
  public abstract ObjectListIterator<K> iterator();
  
  @Deprecated
  public abstract ObjectListIterator<K> objectListIterator();
  
  @Deprecated
  public abstract ObjectListIterator<K> objectListIterator(int paramInt);
  
  public abstract ObjectListIterator<K> listIterator();
  
  public abstract ObjectListIterator<K> listIterator(int paramInt);
  
  @Deprecated
  public abstract ReferenceList<K> referenceSubList(int paramInt1, int paramInt2);
  
  public abstract ReferenceList<K> subList(int paramInt1, int paramInt2);
  
  public abstract void size(int paramInt);
  
  public abstract void getElements(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int paramInt3);
  
  public abstract void removeElements(int paramInt1, int paramInt2);
  
  public abstract void addElements(int paramInt, K[] paramArrayOfK);
  
  public abstract void addElements(int paramInt1, K[] paramArrayOfK, int paramInt2, int paramInt3);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */