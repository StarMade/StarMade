import java.io.File;
import java.io.PrintStream;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.schine.network.objects.remote.RemoteBuffer;

public final class class_622
  extends class_740
{
  private boolean jdField_field_181_of_type_Boolean;
  private String jdField_field_181_of_type_JavaLangString;
  
  public class_622(class_45 paramclass_45)
  {
    super(paramclass_45);
  }
  
  protected final String a()
  {
    return this.jdField_field_181_of_type_JavaLangString;
  }
  
  protected final File a1(String paramString)
  {
    return new File(paramString);
  }
  
  protected final void a2(File paramFile)
  {
    System.err.println("[CLIENT] successfully received file from server: " + paramFile.getAbsolutePath());
    ((class_371)((class_45)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).getState()).a4().a30().a3(paramFile);
    this.jdField_field_181_of_type_Boolean = false;
  }
  
  protected final RemoteBuffer a3()
  {
    return ((class_45)this.jdField_field_181_of_type_OrgSchemaSchineNetworkObjectsSendable).a().downloadBuffer;
  }
  
  public final void a4(String paramString)
  {
    super.a4(paramString);
    this.jdField_field_181_of_type_Boolean = true;
  }
  
  public final boolean a5()
  {
    return this.jdField_field_181_of_type_Boolean;
  }
  
  public final void b_()
  {
    this.jdField_field_181_of_type_Boolean = false;
  }
  
  public final void b(String paramString)
  {
    this.jdField_field_181_of_type_JavaLangString = paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_622
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */