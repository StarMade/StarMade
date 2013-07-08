import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public final class class_1287
  extends JPanel
  implements Observer
{
  private class_1289 jdField_field_1468_of_type_Class_1289;
  private JList jdField_field_1468_of_type_JavaxSwingJList;
  
  public class_1287(class_371 paramclass_371)
  {
    paramclass_371.addObserver(this);
    Object localObject1;
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 133, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 25, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    (localObject1 = new JSplitPane()).setDividerSize(3);
    Object localObject2;
    (localObject2 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).anchor = 18;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    (localObject2 = new JScrollPane()).setMinimumSize(new Dimension(250, 23));
    ((JSplitPane)localObject1).setLeftComponent((Component)localObject2);
    this.jdField_field_1468_of_type_Class_1289 = new class_1289(paramclass_371);
    this.jdField_field_1468_of_type_JavaxSwingJList = new JList(this.jdField_field_1468_of_type_Class_1289);
    ((JSplitPane)localObject1).setRightComponent(new JPanel());
    this.jdField_field_1468_of_type_JavaxSwingJList.addMouseListener(new class_1281(this, (JSplitPane)localObject1));
    this.jdField_field_1468_of_type_JavaxSwingJList.setSelectionMode(0);
    this.jdField_field_1468_of_type_JavaxSwingJList.addListSelectionListener(new class_1283());
    this.jdField_field_1468_of_type_JavaxSwingJList.setCellRenderer(new class_1285());
    ((JScrollPane)localObject2).setViewportView(this.jdField_field_1468_of_type_JavaxSwingJList);
    paramclass_371 = new JLabel("Players");
    ((JScrollPane)localObject2).setColumnHeaderView(paramclass_371);
    ((JSplitPane)localObject1).setDividerLocation(250);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if (this.jdField_field_1468_of_type_Class_1289 != null) {
      this.jdField_field_1468_of_type_Class_1289.a();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1287
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */