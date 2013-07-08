import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import org.schema.schine.network.server.ServerController;

final class class_516
  implements ActionListener
{
  class_516(class_520 paramclass_520, int paramInt) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      paramActionEvent = class_520.a2(this.jdField_field_825_of_type_Class_520).getValueAt(this.jdField_field_825_of_type_Int, 1);
      String str = class_520.a2(this.jdField_field_825_of_type_Class_520).getValueAt(this.jdField_field_825_of_type_Int, 2).toString();
      System.err.println("[SERVER] GUI KICK PLAYER: " + paramActionEvent);
      class_520.a3(this.jdField_field_825_of_type_Class_520).sendLogout(Integer.parseInt(paramActionEvent.toString()), "You have been manually kicked!");
      class_520.a3(this.jdField_field_825_of_type_Class_520).unregister(Integer.parseInt(paramActionEvent.toString()));
      class_520.a3(this.jdField_field_825_of_type_Class_520).broadcastMessage("Player " + str + " has been kicked!", 0);
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_516
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */