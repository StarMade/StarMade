import java.util.concurrent.ConcurrentHashMap;
import org.schema.schine.network.client.ClientState;

final class class_106
  extends class_959
{
  private class_748 field_89;
  
  public class_106(class_117 paramclass_117, class_748 paramclass_748, ClientState paramClientState)
  {
    super(new class_104(paramClientState), new class_104(paramClientState), paramClientState);
    this.field_89 = paramclass_748;
    a12(null);
    this.field_89 = paramclass_748;
    class_117.a31(paramclass_117).put(paramclass_748, this);
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.field_89.equals(((class_106)paramObject).field_89);
  }
  
  public final int hashCode()
  {
    return this.field_89.getId();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    ((class_104)this.field_89).a15(this.field_89.getName(), String.valueOf(this.field_89.f2()), String.valueOf(this.field_89.e()), String.valueOf(this.field_89.g1()), String.valueOf(this.field_89.h1()));
    ((class_104)this.field_90).a15(this.field_89.getName(), String.valueOf(this.field_89.f2()), String.valueOf(this.field_89.e()), String.valueOf(this.field_89.g1()), String.valueOf(this.field_89.h1()));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_106
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */