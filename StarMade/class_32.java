import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JList;
import javax.swing.KeyStroke;

public final class class_32
  implements MouseListener
{
  private static final KeyStroke jdField_field_447_of_type_JavaxSwingKeyStroke = KeyStroke.getKeyStroke(10, 0);
  private JList jdField_field_447_of_type_JavaxSwingJList;
  private KeyStroke field_448;
  
  public class_32(JList paramJList, Action paramAction)
  {
    this(paramJList, paramAction, jdField_field_447_of_type_JavaxSwingKeyStroke);
  }
  
  private class_32(JList paramJList, Action paramAction, KeyStroke paramKeyStroke)
  {
    this.jdField_field_447_of_type_JavaxSwingJList = paramJList;
    this.field_448 = paramKeyStroke;
    paramJList.getInputMap().put(paramKeyStroke, paramKeyStroke);
    paramKeyStroke = paramAction;
    paramAction = this;
    this.jdField_field_447_of_type_JavaxSwingJList.getActionMap().put(paramAction.field_448, paramKeyStroke);
    paramJList.addMouseListener(this);
  }
  
  public final void mouseClicked(MouseEvent paramMouseEvent)
  {
    if ((paramMouseEvent.getClickCount() == 2) && ((paramMouseEvent = this.jdField_field_447_of_type_JavaxSwingJList.getActionMap().get(this.field_448)) != null))
    {
      ActionEvent localActionEvent = new ActionEvent(this.jdField_field_447_of_type_JavaxSwingJList, 1001, "");
      paramMouseEvent.actionPerformed(localActionEvent);
    }
  }
  
  public final void mouseEntered(MouseEvent paramMouseEvent) {}
  
  public final void mouseExited(MouseEvent paramMouseEvent) {}
  
  public final void mousePressed(MouseEvent paramMouseEvent) {}
  
  public final void mouseReleased(MouseEvent paramMouseEvent) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_32
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */