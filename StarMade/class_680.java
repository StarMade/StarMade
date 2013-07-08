import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;

public final class class_680
  extends AbstractListModel
{
  private static final long serialVersionUID = 1L;
  private ArrayList field_947;
  
  public class_680(ArrayList paramArrayList)
  {
    this.field_947 = paramArrayList;
  }
  
  public final Object getElementAt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getSize()))
    {
      localObject1 = "index, " + paramInt + ", is out of bounds for getSize() = " + getSize();
      throw new IllegalArgumentException((String)localObject1);
    }
    Object localObject1 = this.field_947.iterator();
    for (int i = 0; ((Iterator)localObject1).hasNext(); i++)
    {
      Object localObject2 = ((Iterator)localObject1).next();
      if (paramInt == i) {
        return localObject2;
      }
    }
    return null;
  }
  
  public final int getSize()
  {
    return this.field_947.size();
  }
  
  private int a(Object paramObject)
  {
    int i = 0;
    Iterator localIterator = this.field_947.iterator();
    while (localIterator.hasNext())
    {
      if (localIterator.next().equals(paramObject)) {
        return i;
      }
      i++;
    }
    return -1;
  }
  
  public final boolean a1(Object paramObject)
  {
    boolean bool;
    if ((bool = this.field_947.add(paramObject)))
    {
      paramObject = a(paramObject);
      fireIntervalAdded(this, paramObject, paramObject + 1);
    }
    return bool;
  }
  
  public final boolean b(Object paramObject)
  {
    int i;
    if ((i = a(paramObject)) < 0) {
      return false;
    }
    paramObject = this.field_947.remove(paramObject);
    fireIntervalRemoved(this, i, i + 1);
    return paramObject;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_680
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */