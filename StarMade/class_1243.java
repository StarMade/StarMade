import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.schema.game.network.ReceivedPlayer;

final class class_1243
  implements ListSelectionListener
{
  class_1243(JList paramJList, JSplitPane paramJSplitPane) {}
  
  public final void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    if ((paramListSelectionEvent = this.jdField_field_1436_of_type_JavaxSwingJList.getSelectedValue()) != null) {
      this.jdField_field_1436_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(new class_1263((ReceivedPlayer)paramListSelectionEvent)));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1243
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */