import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class class_590
  implements Comparable
{
  private final class_758 jdField_field_887_of_type_Class_758;
  private final class_371 jdField_field_887_of_type_Class_371;
  private class_773 jdField_field_887_of_type_Class_773;
  
  public class_590(class_758 paramclass_758, class_773 paramclass_773, class_371 paramclass_371)
  {
    this.jdField_field_887_of_type_Class_758 = paramclass_758;
    this.jdField_field_887_of_type_Class_371 = paramclass_371;
    this.jdField_field_887_of_type_Class_773 = paramclass_773;
  }
  
  public final Component a(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return new JLabel(this.jdField_field_887_of_type_Class_758.jdField_field_136_of_type_JavaLangString);
    case 1: 
      paramInt = new JComboBox();
      for (int i = 0; i < this.jdField_field_887_of_type_Class_773.a180().a26().length; i++) {
        paramInt.addItem(this.jdField_field_887_of_type_Class_773.a180().a26()[i]);
      }
      paramInt.setSelectedItem(this.jdField_field_887_of_type_Class_773.a180().a26()[this.jdField_field_887_of_type_Class_758.jdField_field_136_of_type_Byte]);
      paramInt.addActionListener(new class_592(this, paramInt));
      return paramInt;
    case 2: 
      JButton localJButton;
      (localJButton = new JButton("remove")).setPreferredSize(new Dimension(40, 20));
      localJButton.setHorizontalTextPosition(2);
      localJButton.addActionListener(new class_594(this));
      return localJButton;
    }
    return new JLabel("-");
  }
  
  public static Color a1()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_590
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */