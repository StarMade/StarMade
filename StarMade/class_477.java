import java.util.ArrayList;
import java.util.Iterator;

public final class class_477
  extends class_16
{
  public class_477(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void b2(boolean paramBoolean)
  {
    int i = 0;
    if (paramBoolean)
    {
      synchronized (a6().b())
      {
        Iterator localIterator = a6().b().iterator();
        while (localIterator.hasNext()) {
          if (((class_12)localIterator.next() instanceof class_4)) {
            i = 1;
          }
        }
      }
      if (i == 0)
      {
        ??? = new class_4(a6());
        a6().b().add(???);
      }
    }
    else
    {
      class_12.field_4 = System.currentTimeMillis();
      synchronized (a6().b())
      {
        for (int j = 0; j < a6().b().size(); j++) {
          if (((class_12)a6().b().get(j) instanceof class_4))
          {
            ((class_12)a6().b().get(j)).d();
            break;
          }
        }
      }
    }
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
    super.a15(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_477
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */