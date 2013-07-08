import java.lang.reflect.Field;
import javax.swing.JTextPane;
import org.schema.game.common.data.element.BlockFactory;

final class class_523
  implements class_532
{
  class_523(class_521 paramclass_521) {}
  
  public final void a()
  {
    try
    {
      if ((localObject = this.field_168.jdField_field_828_of_type_JavaLangReflectField.get(class_644.a2(this.field_168.jdField_field_828_of_type_Class_644))) != null)
      {
        localObject = (BlockFactory)localObject;
        this.field_168.jdField_field_828_of_type_JavaxSwingJTextPane.setText(((BlockFactory)localObject).toString());
        return;
      }
      this.field_168.jdField_field_828_of_type_JavaxSwingJTextPane.setText("   -   ");
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      (localObject = localIllegalArgumentException).printStackTrace();
      class_29.a12((Exception)localObject);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Object localObject;
      (localObject = localIllegalAccessException).printStackTrace();
      class_29.a12((Exception)localObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_523
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */