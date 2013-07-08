import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public abstract class class_138
  extends class_1363
  implements Observer
{
  private int jdField_field_89_of_type_Int;
  private int jdField_field_90_of_type_Int;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_930 jdField_field_89_of_type_Class_930;
  private String jdField_field_90_of_type_JavaLangString;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_138(ClientState paramClientState, int paramInt)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Int = paramInt;
    this.jdField_field_90_of_type_Int = 80;
    ((class_371)a24()).a45().addObserver(this);
  }
  
  public final void a2()
  {
    ((class_371)a24()).a45().deleteObserver(this);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      this.jdField_field_90_of_type_JavaLangString = a16();
      this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
      String[] arrayOfString = this.jdField_field_90_of_type_JavaLangString.split("\\\\n");
      this.jdField_field_89_of_type_Class_930.field_90.clear();
      for (int i = 0; i < arrayOfString.length; i++) {
        this.jdField_field_89_of_type_Class_930.field_90.add(arrayOfString[i]);
      }
      this.jdField_field_89_of_type_Boolean = false;
    }
    k();
  }
  
  public abstract String a16();
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_1414 = new class_1414(a24(), this.jdField_field_89_of_type_Int, this.jdField_field_90_of_type_Int);
    this.jdField_field_89_of_type_Class_930 = new class_930(this.jdField_field_89_of_type_Int, this.jdField_field_90_of_type_Int, a24());
    this.jdField_field_90_of_type_JavaLangString = a16();
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    String[] arrayOfString = this.jdField_field_90_of_type_JavaLangString.split("\\\\n");
    for (int i = 0; i < arrayOfString.length; i++) {
      this.jdField_field_89_of_type_Class_930.field_90.add(arrayOfString[i]);
    }
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_930);
    a9(this.jdField_field_89_of_type_Class_1414);
  }
  
  public final float a3()
  {
    return this.jdField_field_90_of_type_Int;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Int;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_138
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */