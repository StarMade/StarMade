import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;

public abstract class class_1223
  extends JLabel
  implements class_1284
{
  private static final long serialVersionUID = 5254155298324471539L;
  private final String jdField_field_171_of_type_JavaLangString;
  private Object jdField_field_171_of_type_JavaLangObject;
  
  public class_1223(String paramString)
  {
    this.jdField_field_171_of_type_JavaLangString = paramString;
    this.jdField_field_171_of_type_JavaLangObject = a();
  }
  
  public final JComponent a1()
  {
    setText(a().toString());
    setOpaque(true);
    setBackground(UIManager.getColor("List.background"));
    return this;
  }
  
  public final boolean a2()
  {
    return false;
  }
  
  public final boolean b()
  {
    Object localObject;
    if (!(localObject = a()).equals(this.jdField_field_171_of_type_JavaLangObject))
    {
      setText(localObject.toString());
      this.jdField_field_171_of_type_JavaLangObject = localObject;
      return true;
    }
    return false;
  }
  
  public final String a3()
  {
    return this.jdField_field_171_of_type_JavaLangString;
  }
  
  public abstract Object a();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1223
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */