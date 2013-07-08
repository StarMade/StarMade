import org.schema.schine.network.client.ClientState;

public class class_966
  extends class_968
{
  public class_966(ClientState paramClientState)
  {
    super(400.0F, 150.0F, paramClientState);
  }
  
  public final void c7(class_1363 paramclass_1363)
  {
    if ((!field_89) && (!(paramclass_1363 instanceof class_930))) {
      throw new AssertionError();
    }
    super.c7(paramclass_1363);
  }
  
  public final void b()
  {
    class_930 localclass_930 = (class_930)this.jdField_field_89_of_type_Class_1363;
    int j = (int)Math.ceil(this.jdField_field_89_of_type_Float + a3());
    int i = (int)Math.floor(this.jdField_field_89_of_type_Float);
    (localclass_930 = localclass_930).jdField_field_90_of_type_Int = i;
    localclass_930.field_92 = j;
    super.b();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_966
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */