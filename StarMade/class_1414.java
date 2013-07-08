import java.util.Iterator;
import java.util.List;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_1414
  extends class_1363
{
  protected float field_89;
  protected float field_90;
  
  public class_1414(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public class_1414(ClientState paramClientState, float paramFloat1, float paramFloat2)
  {
    this(paramClientState);
    this.field_90 = paramFloat1;
    this.field_89 = paramFloat2;
  }
  
  public void a2() {}
  
  protected final void d() {}
  
  public void b()
  {
    GlUtil.d1();
    r();
    Iterator localIterator = this.field_89.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).b();
    }
    if (this.field_96) {
      l();
    }
    GlUtil.c2();
  }
  
  public float a3()
  {
    return this.field_89;
  }
  
  public float b1()
  {
    return this.field_90;
  }
  
  public void c() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1414
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */