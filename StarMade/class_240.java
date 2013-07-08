import java.util.concurrent.ConcurrentHashMap;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.Sendable;

final class class_240
  extends class_959
{
  private Sendable field_89;
  
  public class_240(class_242 paramclass_242, Sendable paramSendable, ClientState paramClientState)
  {
    super(new class_190(paramClientState), new class_190(paramClientState), paramClientState);
    this.field_89 = paramSendable;
    a12(null);
    this.field_89 = paramSendable;
    class_242.a107(paramclass_242).put(paramSendable, this);
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.field_89.equals(((class_240)paramObject).field_89);
  }
  
  public final int hashCode()
  {
    return this.field_89.getId();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    ((class_190)this.field_89).a15(String.valueOf(this.field_89.getId()), this.field_89.toString(), a16(), "", "");
    ((class_190)this.field_90).a15(String.valueOf(this.field_89.getId()), this.field_89.toString(), a16(), "", "");
  }
  
  private String a16()
  {
    if ((this.field_89 instanceof class_797)) {
      return String.valueOf(((class_797)this.field_89).getSectorId());
    }
    return "-";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_240
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */