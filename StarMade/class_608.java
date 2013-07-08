import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_608
  implements ActionListener
{
  class_608(class_610 paramclass_610, JFrame paramJFrame, JLabel paramJLabel) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    Object localObject1;
    for (;;)
    {
      try
      {
        
      }
      catch (IOException localIOException)
      {
        (localObject1 = localIOException).printStackTrace();
        class_29.a12((Exception)localObject1);
      }
      localObject1 = new HashSet();
      localObject2 = ElementKeyMap.properties.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject2).next();
        if (!ElementKeyMap.keySet.contains(Short.valueOf(Short.parseShort(localEntry.getValue().toString())))) {
          ((HashSet)localObject1).add(localEntry.getKey().toString());
        }
      }
      if (!((HashSet)localObject1).isEmpty()) {
        break;
      }
      localObject2 = new Object[] { "cancel", "retry" };
      switch (JOptionPane.showOptionDialog(this.jdField_field_894_of_type_JavaxSwingJFrame, "Error: No Free ID's available in BlockTypes.properties\nPlease add a new entry and try again!", "No ID available", 2, 0, null, (Object[])localObject2, localObject2[0]))
      {
      case 0: 
        return;
      }
    }
    return;
    Object localObject2 = new Object[((HashSet)localObject1).size()];
    int i = 0;
    paramActionEvent = ((HashSet)localObject1).iterator();
    while (paramActionEvent.hasNext())
    {
      localObject1 = (String)paramActionEvent.next();
      localObject2[i] = localObject1;
      i++;
    }
    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.jdField_field_894_of_type_JavaxSwingJFrame, "Pick an ID from the list", "Pick ID", -1, null, (Object[])localObject2, localObject2[0])) != null) && (paramActionEvent.length() > 0))
    {
      class_610.a(this.jdField_field_894_of_type_Class_610, Short.parseShort(ElementKeyMap.properties.get(paramActionEvent).toString()));
      this.jdField_field_894_of_type_JavaxSwingJLabel.setText(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_608
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */