import java.util.ArrayList;
import java.util.Collections;

public final class class_27
  extends ArrayList
{
  private static final long serialVersionUID = 5564392963896845136L;
  
  public final boolean a(Comparable paramComparable)
  {
    if (paramComparable == null) {
      throw new NullPointerException("tried to add null " + paramComparable);
    }
    if (size() == 0) {
      return super.add(paramComparable);
    }
    int i;
    if ((i = Collections.binarySearch(this, paramComparable)) >= 0)
    {
      super.add(i + 1, paramComparable);
      return true;
    }
    super.add(-i - 1, paramComparable);
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_27
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */