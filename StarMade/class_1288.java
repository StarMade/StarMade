import javax.swing.JButton;
import javax.swing.JComponent;

public abstract class class_1288
  extends JButton
  implements class_1284
{
  private static final long serialVersionUID = 5254155298324471539L;
  private final String jdField_field_171_of_type_JavaLangString;
  private Object jdField_field_171_of_type_JavaLangObject;
  
  public class_1288(String paramString)
  {
    this.jdField_field_171_of_type_JavaLangString = paramString;
    this.jdField_field_171_of_type_JavaLangObject = a();
    addActionListener(new class_1282(this));
  }
  
  public final boolean a2()
  {
    return true;
  }
  
  public final JComponent a1()
  {
    setText(this.jdField_field_171_of_type_JavaLangString + "   " + a());
    setOpaque(true);
    return this;
  }
  
  public final boolean b()
  {
    Object localObject;
    if (!(localObject = a()).equals(this.jdField_field_171_of_type_JavaLangObject))
    {
      setText(this.jdField_field_171_of_type_JavaLangString + "   " + a());
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
  
  public abstract void a4();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1288
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */