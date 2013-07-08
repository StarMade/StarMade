import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public final class class_697
  extends JDialog
{
  private static final long serialVersionUID = -8429762774210141903L;
  private JPanel field_963;
  
  public class_697(JFrame paramJFrame)
  {
    setDefaultCloseOperation(2);
    setBounds(100, 100, 688, 351);
    this.field_963 = new JPanel();
    setTitle("StarMade Ship Catalog Manager");
    this.field_963.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.field_963.setLayout(new BorderLayout(0, 0));
    setContentPane(this.field_963);
    paramJFrame = new class_699(paramJFrame, this);
    this.field_963.add(paramJFrame, "Center");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_697
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */