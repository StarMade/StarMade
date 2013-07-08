import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.schine.network.client.ClientState;

public final class class_262
  extends class_114
{
  public class_262(ClientState paramClientState)
  {
    super(420.0F, paramClientState);
  }
  
  public final boolean equals(Object paramObject)
  {
    return paramObject instanceof class_262;
  }
  
  protected final void a27(class_964 paramclass_964)
  {
    int i = 0;
    paramclass_964.clear();
    System.err.println("[GUI] UPDATING LIST: " + ((class_371)a24()).a20().a141().a75().size());
    Iterator localIterator = ((class_371)a24()).a20().a141().a75().iterator();
    while (localIterator.hasNext())
    {
      class_777 localclass_777 = (class_777)localIterator.next();
      paramclass_964.a144(new class_264(a24(), localclass_777, i));
      i++;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_262
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */