import org.schema.game.common.controller.SegmentController;
import org.schema.game.network.objects.NetworkSegmentProvider;
import org.schema.game.network.objects.remote.RemoteSegmentGZipPackage;
import org.schema.game.network.objects.remote.RemoteSegmentSignature;

public class class_1202
{
  private final NetworkSegmentProvider jdField_field_1421_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
  private final class_48 jdField_field_1421_of_type_Class_48;
  private final SegmentController jdField_field_1421_of_type_OrgSchemaGameCommonControllerSegmentController;
  private final Class jdField_field_1421_of_type_JavaLangClass;
  
  public class_1202(SegmentController paramSegmentController, class_48 paramclass_48, NetworkSegmentProvider paramNetworkSegmentProvider, Class paramClass)
  {
    this.jdField_field_1421_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_1421_of_type_Class_48 = paramclass_48;
    this.jdField_field_1421_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider = paramNetworkSegmentProvider;
    this.jdField_field_1421_of_type_JavaLangClass = paramClass;
    if ((!jdField_field_1421_of_type_Boolean) && (!a3())) {
      if ((this.jdField_field_1421_of_type_JavaLangClass == RemoteSegmentGZipPackage.class ? 1 : 0) == 0) {
        throw new AssertionError();
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    paramObject = (class_1202)paramObject;
    return (this.jdField_field_1421_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider == paramObject.jdField_field_1421_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider) && (this.jdField_field_1421_of_type_Class_48.equals(paramObject.jdField_field_1421_of_type_Class_48)) && (this.jdField_field_1421_of_type_OrgSchemaGameCommonControllerSegmentController == paramObject.jdField_field_1421_of_type_OrgSchemaGameCommonControllerSegmentController) && (this.jdField_field_1421_of_type_JavaLangClass == paramObject.jdField_field_1421_of_type_JavaLangClass);
  }
  
  public final NetworkSegmentProvider a()
  {
    return this.jdField_field_1421_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
  }
  
  public final SegmentController a1()
  {
    return this.jdField_field_1421_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
  
  public final class_48 a2()
  {
    return this.jdField_field_1421_of_type_Class_48;
  }
  
  public final boolean a3()
  {
    return this.jdField_field_1421_of_type_JavaLangClass == RemoteSegmentSignature.class;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1202
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */