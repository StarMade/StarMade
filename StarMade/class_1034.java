import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public final class class_1034
{
  public final HashMap field_1303 = new HashMap();
  public float field_1303;
  public String field_1303;
  
  public final void a(float paramFloat1, float paramFloat2, class_1056 paramclass_1056, class_1028 paramclass_1028)
  {
    if (this.field_1303.isEmpty()) {
      System.err.println("No tracks in animation");
    }
    Iterator localIterator = this.field_1303.entrySet().iterator();
    while (localIterator.hasNext()) {
      ((class_1058)((Map.Entry)localIterator.next()).getValue()).a4(paramFloat1, paramFloat2, paramclass_1056, paramclass_1028);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1034
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */