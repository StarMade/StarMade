import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;

public final class class_1157
  extends class_1163
{
  private static final long serialVersionUID = 1L;
  private long field_128;
  
  public class_1157(class_981 paramclass_981)
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
    int i = 1;
    String str = "";
    for (int j = 0; j < ((class_1063)a8()).a18().size(); j++) {
      if (((class_1063)a8()).a15(j))
      {
        i = 0;
        str = str + (String)((class_1063)a8()).a18().get(j) + "; ";
      }
    }
    if (i != 0)
    {
      ((class_1063)a8()).a19().a69().a213((class_1063)a8());
      a8().d();
    }
    else
    {
      System.err.println("[AI] DISBANDING: Waiting for all to unload. Still loaded: " + str);
      if (System.currentTimeMillis() - this.field_128 > 240000L) {
        a12(new class_1210());
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1157
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */