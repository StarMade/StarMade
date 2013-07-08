import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;

public final class class_287
  implements Comparable
{
  long jdField_field_655_of_type_Long = -1L;
  private ElementCollection jdField_field_655_of_type_OrgSchemaGameCommonDataElementElementCollection;
  float jdField_field_655_of_type_Float = 0.0F;
  private final class_212 jdField_field_655_of_type_Class_212 = new class_212();
  
  public class_287(class_747 paramclass_747, ElementCollection paramElementCollection)
  {
    ElementCollection localElementCollection = paramElementCollection;
    paramElementCollection = paramclass_747;
    paramclass_747 = this;
    this.jdField_field_655_of_type_OrgSchemaGameCommonDataElementElementCollection = localElementCollection;
    if ((localElementCollection instanceof PointDistributionCustomOutputUnit)) {
      paramclass_747.jdField_field_655_of_type_Class_212.a2(paramElementCollection, ((PointDistributionCustomOutputUnit)localElementCollection).getOutput());
    } else {
      paramclass_747.jdField_field_655_of_type_Class_212.a2(paramElementCollection, localElementCollection.getSignificator());
    }
    paramclass_747.jdField_field_655_of_type_Float = 0.0F;
    paramclass_747.jdField_field_655_of_type_Long = -1L;
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.jdField_field_655_of_type_OrgSchemaGameCommonDataElementElementCollection.equals(((class_287)paramObject).jdField_field_655_of_type_OrgSchemaGameCommonDataElementElementCollection);
  }
  
  public final int hashCode()
  {
    return this.jdField_field_655_of_type_OrgSchemaGameCommonDataElementElementCollection.hashCode();
  }
  
  public final boolean a()
  {
    return (this.jdField_field_655_of_type_Long >= 0L) && ((float)(System.currentTimeMillis() - this.jdField_field_655_of_type_Long) <= 200.0F);
  }
  
  public final void a1()
  {
    long l;
    if (((float)(l = System.currentTimeMillis() - this.jdField_field_655_of_type_Long) > 160.0F) && ((float)l < 200.0F))
    {
      this.jdField_field_655_of_type_Long = (System.currentTimeMillis() + 160L);
      return;
    }
    if ((float)l >= 200.0F) {
      this.jdField_field_655_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public final void a2(class_941 paramclass_941)
  {
    this.jdField_field_655_of_type_Float = ((float)(this.jdField_field_655_of_type_Float + paramclass_941.a() / 1000.0F * ((Math.random() + 9.999999747378752E-005D) / 0.1000000014901161D)));
    if (this.jdField_field_655_of_type_Float > 1.0F) {
      this.jdField_field_655_of_type_Float = 0.0F;
    }
  }
  
  public final void a3(Transform paramTransform, Vector3f paramVector3f)
  {
    this.jdField_field_655_of_type_Class_212.a1(paramTransform, paramVector3f);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_287
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */