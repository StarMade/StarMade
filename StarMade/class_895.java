import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.schine.network.client.ClientState;

public final class class_895
  extends class_1414
{
  private final short jdField_field_89_of_type_Short;
  private final ElementInformation jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation;
  private int jdField_field_89_of_type_Int;
  
  public class_895(ClientState paramClientState, ElementInformation paramElementInformation, int paramInt)
  {
    super(paramClientState, 224.0F, 74.0F);
    this.jdField_field_89_of_type_Short = ((short)paramElementInformation.getBuildIconNum());
    this.field_89 = Short.valueOf(paramElementInformation.getId());
    this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
    this.jdField_field_89_of_type_Int = paramInt;
  }
  
  public final void c()
  {
    super.c();
    if (this.jdField_field_89_of_type_Int % 2 == 0)
    {
      class_1402 localclass_1402 = new class_1402(a24(), 224.0F, 74.0F, new Vector4f(0.06F, 0.06F, 0.06F, 1.0F));
      a9(localclass_1402);
    }
    int i = this.jdField_field_89_of_type_Short / 256;
    Object localObject = class_969.a2().a5("build-icons-" + class_41.b(i) + "-16x16-gui-");
    (localObject = new class_972((class_1380)localObject, a24())).a83().field_616 = 14.0F;
    class_930 localclass_9301;
    (localclass_9301 = new class_930(224, 20, class_29.h(), a24())).field_90 = new ArrayList();
    localclass_9301.field_90.add(this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation.getName());
    localclass_9301.a83().field_616 = 3.0F;
    class_930 localclass_9302;
    (localclass_9302 = new class_930(224, 64, class_29.n(), a24())).a83().field_615 = 64.0F;
    localclass_9302.a83().field_616 = 24.0F;
    int j = this.jdField_field_89_of_type_Short % 256;
    ((class_972)localObject).a_2(j);
    localclass_9302.field_90 = new ArrayList();
    localclass_9302.field_90.add(new class_865(this, this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation));
    localclass_9302.field_90.add(new class_867(this, this.jdField_field_89_of_type_OrgSchemaGameCommonDataElementElementInformation));
    a9(localclass_9301);
    a9((class_1363)localObject);
    a9(localclass_9302);
    this.field_96 = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_895
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */