import org.schema.schine.network.objects.Sendable;

public abstract class class_987
  extends class_989
{
  private final Sendable jdField_field_223_of_type_OrgSchemaSchineNetworkObjectsSendable;
  
  public class_987(String paramString, Sendable paramSendable)
  {
    super(paramString, paramSendable.getState());
    this.jdField_field_223_of_type_OrgSchemaSchineNetworkObjectsSendable = paramSendable;
  }
  
  public Sendable a()
  {
    return this.jdField_field_223_of_type_OrgSchemaSchineNetworkObjectsSendable;
  }
  
  public final boolean a1()
  {
    return (super.a1()) || ((!this.field_224) && (((class_991)this.jdField_field_223_of_type_OrgSchemaSchineNetworkObjectsSendable).a().b()));
  }
  
  public final void a2(class_941 paramclass_941)
  {
    super.a2(paramclass_941);
    if (a1())
    {
      if (this.field_224)
      {
        b1(paramclass_941);
        return;
      }
      a_(paramclass_941);
    }
  }
  
  public float b()
  {
    if (!jdField_field_223_of_type_Boolean) {
      throw new AssertionError();
    }
    return 0.0F;
  }
  
  public abstract void a_(class_941 paramclass_941);
  
  public abstract void b1(class_941 paramclass_941);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_987
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */