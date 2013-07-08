import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class class_1169
  extends class_1163
{
  private long field_128;
  private static final long serialVersionUID = 1L;
  
  public class_1169(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final boolean c()
  {
    this.field_128 = System.currentTimeMillis();
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    class_1063 localclass_1063 = (class_1063)a8();
    class_48 localclass_48 = new class_48();
    Iterator localIterator = localclass_1063.a19().b10().values().iterator();
    while (localIterator.hasNext())
    {
      class_748 localclass_748 = (class_748)localIterator.next();
      localclass_48.a6(localclass_748.a44(), localclass_1063.a20());
      if (localclass_48.a4() < 6.0F)
      {
        ((class_1256)a8().a3()).a10(new class_48(localclass_748.a44()));
        localclass_1063.a7(localclass_748);
        a12(new class_1124());
        return true;
      }
    }
    if (System.currentTimeMillis() - this.field_128 > 60000L)
    {
      a12(new class_1147());
      return true;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1169
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */