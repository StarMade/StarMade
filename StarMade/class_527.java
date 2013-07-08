import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;

public class class_527
  implements Comparable
{
  private final class_781 jdField_field_832_of_type_Class_781;
  private final class_371 jdField_field_832_of_type_Class_371;
  private class_541 jdField_field_832_of_type_Class_541;
  
  public class_527(class_781 paramclass_781, class_371 paramclass_371, class_541 paramclass_541)
  {
    this.jdField_field_832_of_type_Class_781 = paramclass_781;
    this.jdField_field_832_of_type_Class_371 = paramclass_371;
    this.jdField_field_832_of_type_Class_541 = paramclass_541;
  }
  
  public final Component a(int paramInt, JTable paramJTable)
  {
    switch (paramInt)
    {
    case 0: 
      return new JLabel(this.jdField_field_832_of_type_Class_781.jdField_field_136_of_type_JavaLangString);
    case 1: 
      return new JLabel(this.jdField_field_832_of_type_Class_781.jdField_field_139_of_type_JavaLangString);
    case 2: 
      return new JLabel(String.valueOf(this.jdField_field_832_of_type_Class_781.jdField_field_139_of_type_Int));
    case 3: 
      return new JLabel(String.valueOf(this.jdField_field_832_of_type_Class_781.jdField_field_136_of_type_Float));
    case 4: 
      return new JLabel(String.valueOf(this.jdField_field_832_of_type_Class_781.jdField_field_182_of_type_JavaLangString));
    case 5: 
      return new JLabel(new Date(this.jdField_field_832_of_type_Class_781.jdField_field_136_of_type_Long).toString());
    case 6: 
      return new JLabel(String.valueOf(this.jdField_field_832_of_type_Class_781.jdField_field_136_of_type_Int));
    case 7: 
      return a1(16);
    case 8: 
      return a1(1);
    case 9: 
      return a1(4);
    case 10: 
      return a1(2);
    case 11: 
      (paramInt = new JButton("Edit")).setPreferredSize(new Dimension(40, 20));
      paramInt.setHorizontalTextPosition(2);
      paramInt.addActionListener(new class_551(this, paramJTable));
      return paramInt;
    }
    return new JLabel("-");
  }
  
  private JCheckBox a1(int paramInt)
  {
    JCheckBox localJCheckBox;
    (localJCheckBox = new JCheckBox()).setSelected((this.jdField_field_832_of_type_Class_781.jdField_field_182_of_type_Int & paramInt) == paramInt);
    paramInt = new class_549(this, paramInt);
    localJCheckBox.addActionListener(paramInt);
    return localJCheckBox;
  }
  
  public final Color a2(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return null;
    case 1: 
      return null;
    case 2: 
      return null;
    case 3: 
      return null;
    case 4: 
      return null;
    case 5: 
      return null;
    case 6: 
      return null;
    case 7: 
      if (this.jdField_field_832_of_type_Class_781.d10()) {
        return Color.GREEN;
      }
      return Color.RED;
    case 8: 
      if (this.jdField_field_832_of_type_Class_781.b2()) {
        return Color.GREEN;
      }
      return Color.RED;
    case 9: 
      if (this.jdField_field_832_of_type_Class_781.c3()) {
        return Color.GREEN;
      }
      return Color.RED;
    case 10: 
      if (this.jdField_field_832_of_type_Class_781.a7()) {
        return Color.GREEN;
      }
      return Color.RED;
    case 11: 
      return null;
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_527
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */