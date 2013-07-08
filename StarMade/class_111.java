import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_111
  extends class_959
{
  private class_1402 jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 540.0F, 100.0F, paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.2F, 0.2F, 0.2F, 1.0F));
  private class_1402 field_90;
  private class_771 jdField_field_89_of_type_Class_771;
  
  public class_111(ClientState paramClientState, class_771 paramclass_771, int paramInt)
  {
    super(paramClientState);
    this.field_89 = this.jdField_field_89_of_type_Class_1402;
    this.field_90 = this.jdField_field_89_of_type_Class_1402;
    this.jdField_field_89_of_type_Class_771 = paramclass_771;
  }
  
  public final void c()
  {
    super.c();
    class_930 localclass_9301 = new class_930(200, 20, a24());
    class_930 localclass_9302 = new class_930(200, 20, a24());
    class_930 localclass_9303 = new class_930(200, 20, a24());
    this.field_90 = new class_1402(a24(), 550.0F, 20.0F, new Vector4f(0.3F, 0.3F, 0.4F, 0.8F));
    localclass_9301.a136(this.jdField_field_89_of_type_Class_771.a());
    Object localObject1;
    (localObject1 = new GregorianCalendar()).setTime(new Date(this.jdField_field_89_of_type_Class_771.a28()));
    localclass_9302.a136(((GregorianCalendar)localObject1).getTime().toString());
    localclass_9303.field_90 = new ArrayList();
    for (Object localObject2 : this.jdField_field_89_of_type_Class_771.b().split("\\\\n")) {
      localclass_9303.field_90.add(localObject2);
    }
    this.field_90.a9(localclass_9301);
    this.field_90.a9(localclass_9302);
    localclass_9302.a83().field_615 = 300.0F;
    localclass_9303.a83().field_616 = 20.0F;
    this.jdField_field_89_of_type_Class_1402.a9(this.field_90);
    this.jdField_field_89_of_type_Class_1402.a9(localclass_9303);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_111
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */