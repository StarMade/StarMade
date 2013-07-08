package org.schema.game.common.data.physics;

import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.util.Comparator;

public class UnionFindExt
{
  private final ObjectArrayList elements = new ObjectArrayList();
  private static final Comparator elementComparator = new UnionFindExt.1();
  
  public void sortIslands()
  {
    int i = this.elements.size();
    for (int j = 0; j < i; j++)
    {
      UnionFindExt.Element localElement;
      (localElement = (UnionFindExt.Element)this.elements.getQuick(j)).field_2174 = find(j);
      localElement.field_2175 = j;
    }
    MiscUtil.quickSort(this.elements, elementComparator);
  }
  
  public void reset(int paramInt)
  {
    allocate(paramInt);
    for (int i = 0; i < paramInt; i++)
    {
      UnionFindExt.Element localElement;
      (localElement = (UnionFindExt.Element)this.elements.getQuick(i)).field_2174 = i;
      localElement.field_2175 = 1;
    }
  }
  
  public int getNumElements()
  {
    return this.elements.size();
  }
  
  public boolean isRoot(int paramInt)
  {
    return paramInt == ((UnionFindExt.Element)this.elements.getQuick(paramInt)).field_2174;
  }
  
  public UnionFindExt.Element getElement(int paramInt)
  {
    return (UnionFindExt.Element)this.elements.getQuick(paramInt);
  }
  
  public void allocate(int paramInt)
  {
    MiscUtil.resize(this.elements, paramInt, UnionFindExt.Element.class);
    while (this.elements.size() < paramInt) {
      this.elements.add(new UnionFindExt.Element());
    }
    while (this.elements.size() > paramInt) {
      this.elements.removeQuick(this.elements.size() - 1);
    }
  }
  
  public void free()
  {
    this.elements.clear();
  }
  
  public int find(int paramInt1, int paramInt2)
  {
    if (find(paramInt1) == find(paramInt2)) {
      return 1;
    }
    return 0;
  }
  
  public void unite(int paramInt1, int paramInt2)
  {
    paramInt1 = find(paramInt1);
    paramInt2 = find(paramInt2);
    if (paramInt1 == paramInt2) {
      return;
    }
    ((UnionFindExt.Element)this.elements.getQuick(paramInt1)).field_2174 = paramInt2;
    ((UnionFindExt.Element)this.elements.getQuick(paramInt2)).field_2175 += ((UnionFindExt.Element)this.elements.getQuick(paramInt1)).field_2175;
  }
  
  public int find(int paramInt)
  {
    while (paramInt != ((UnionFindExt.Element)this.elements.getQuick(paramInt)).field_2174)
    {
      ((UnionFindExt.Element)this.elements.getQuick(paramInt)).field_2174 = ((UnionFindExt.Element)this.elements.getQuick(((UnionFindExt.Element)this.elements.getQuick(paramInt)).field_2174)).field_2174;
      paramInt = ((UnionFindExt.Element)this.elements.getQuick(paramInt)).field_2174;
    }
    return paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.UnionFindExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */