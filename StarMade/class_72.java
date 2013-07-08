import java.util.LinkedList;
import org.schema.schine.graphicsengine.core.ResourceException;

public class class_72
{
  public int field_538;
  public String field_538;
  public String field_539;
  public String field_540;
  public String field_541;
  
  public class_72()
  {
    this.jdField_field_538_of_type_Int = -1;
  }
  
  public void a(class_73 paramclass_73)
  {
    switch (this.jdField_field_538_of_type_Int)
    {
    case 1: 
      paramclass_73.b1(this);
      return;
    case 0: 
      paramclass_73.c1(this);
      return;
    case 2: 
      paramclass_73.a8(this);
      return;
    case 3: 
      paramclass_73.a2().add(0, "loading FONTS...");
      class_29.m();
      class_29.a16();
      class_29.b2();
      class_29.h();
      class_29.e();
      class_29.d();
      class_29.j();
      class_29.i();
      class_29.g();
      class_29.n();
      class_29.o();
      class_29.c();
      class_29.f();
      return;
    }
    throw new ResourceException("Unspecified resource type: " + this.jdField_field_538_of_type_Int);
  }
  
  public String toString()
  {
    return "RESOURCE(" + this.jdField_field_538_of_type_Int + "; " + this.jdField_field_538_of_type_JavaLangString + "; " + this.field_539 + "; " + this.field_540 + "; 0)";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_72
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */