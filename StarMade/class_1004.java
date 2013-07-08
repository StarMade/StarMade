import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_1004
  extends Mesh
{
  public final void a2()
  {
    super.a2();
  }
  
  public final void b()
  {
    if (!this.field_94)
    {
      class_971.field_98.add("Loading " + b14());
      return;
    }
    if (h2()) {
      return;
    }
    GlUtil.d1();
    class_984.a149(this);
    Iterator localIterator = this.field_89.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).b();
    }
    GlUtil.c2();
  }
  
  public static class_1004 a166()
  {
    return null;
  }
  
  public final void c()
  {
    System.err.println("-- OGL: new MeshGroup created");
  }
  
  public final void r()
  {
    class_984.a149(this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1004
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */