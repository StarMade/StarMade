import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.staremote.Staremote;

public final class class_529
  extends JFrame
{
  private JPanel field_834;
  
  public class_529(Staremote paramStaremote)
  {
    setTitle("Starmote Connection");
    setDefaultCloseOperation(3);
    setBounds(100, 100, 468, 359);
    this.field_834 = new JPanel();
    this.field_834.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.field_834.setLayout(new BorderLayout(0, 0));
    setContentPane(this.field_834);
    paramStaremote = new class_1313(this, paramStaremote);
    this.field_834.add(paramStaremote, "Center");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_529
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */