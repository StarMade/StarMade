import java.util.ArrayList;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_274
  extends class_961
{
  private class_1402 field_89;
  
  public class_274(ClientState paramClientState, class_160 paramclass_160, class_781 paramclass_781, boolean paramBoolean, int paramInt)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 510.0F, 80.0F, paramInt % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F));
    (paramClientState = new class_286(a24(), paramclass_781, paramBoolean)).c();
    this.jdField_field_89_of_type_Class_1402.a9(paramClientState);
    this.jdField_field_89_of_type_Class_964.a144(new class_959(this.jdField_field_89_of_type_Class_1402, this.jdField_field_89_of_type_Class_1402, a24()));
    this.jdField_field_89_of_type_Class_1363 = new class_288(a24(), paramclass_781, "+", paramInt);
    this.field_90 = new class_288(a24(), paramclass_781, "-", paramInt);
    this.jdField_field_89_of_type_Class_1363.c();
    this.field_90.c();
    c();
    addObserver(paramclass_160);
  }
  
  protected final boolean b3()
  {
    return ((class_371)a24()).b().isEmpty();
  }
  
  public final void a72(int paramInt)
  {
    this.jdField_field_89_of_type_Class_1402.a63().set(paramInt % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F));
    ((class_288)this.jdField_field_89_of_type_Class_1363).a72(paramInt);
    ((class_288)this.field_90).a72(paramInt);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_274
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */