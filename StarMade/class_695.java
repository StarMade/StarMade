import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.AbstractListModel;

public final class class_695
  extends AbstractListModel
{
  private static final long serialVersionUID = 1L;
  private TreeSet field_961 = new TreeSet();
  
  public final int getSize()
  {
    return this.field_961.size();
  }
  
  private int a(Comparable paramComparable)
  {
    int i = 0;
    Iterator localIterator = this.field_961.iterator();
    while (localIterator.hasNext())
    {
      if (((Comparable)localIterator.next()).equals(paramComparable)) {
        return i;
      }
      i++;
    }
    return -1;
  }
  
  public final boolean a1(Comparable paramComparable)
  {
    boolean bool;
    if ((bool = this.field_961.add(paramComparable)))
    {
      paramComparable = a(paramComparable);
      fireIntervalAdded(this, paramComparable, paramComparable + 1);
    }
    return bool;
  }
  
  public final boolean b(Comparable paramComparable)
  {
    int i;
    if ((i = a(paramComparable)) < 0) {
      return false;
    }
    paramComparable = this.field_961.remove(paramComparable);
    fireIntervalRemoved(this, i, i + 1);
    return paramComparable;
  }
  
  public final TreeSet a2()
  {
    return this.field_961;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_695
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */