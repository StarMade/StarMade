import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import org.schema.game.common.staremote.Staremote;

public final class class_1251
  extends JPanel
  implements Observer
{
  private class_1257 jdField_field_1439_of_type_Class_1257;
  private JList jdField_field_1439_of_type_JavaxSwingJList;
  private class_1261 jdField_field_1439_of_type_Class_1261;
  
  public class_1251(class_371 paramclass_371, Staremote paramStaremote)
  {
    paramclass_371.addObserver(this);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 120, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 25, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    Object localObject1 = new JTabbedPane(1);
    (localObject2 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).anchor = 18;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    Object localObject2 = new JSplitPane();
    ((JTabbedPane)localObject1).addTab("Online", null, (Component)localObject2, null);
    ((JSplitPane)localObject2).setDividerSize(3);
    Object localObject3;
    (localObject3 = new JScrollPane()).setMinimumSize(new Dimension(100, 23));
    ((JSplitPane)localObject2).setLeftComponent((Component)localObject3);
    this.jdField_field_1439_of_type_JavaxSwingJList = new JList(this.jdField_field_1439_of_type_Class_1257 = new class_1257(paramclass_371));
    ((JSplitPane)localObject2).setRightComponent(new JPanel());
    this.jdField_field_1439_of_type_JavaxSwingJList.addMouseListener(new class_1249(this, (JSplitPane)localObject2));
    this.jdField_field_1439_of_type_JavaxSwingJList.setSelectionMode(0);
    this.jdField_field_1439_of_type_JavaxSwingJList.addListSelectionListener(new class_1247());
    this.jdField_field_1439_of_type_JavaxSwingJList.setCellRenderer(new class_1253());
    ((JScrollPane)localObject3).setViewportView(this.jdField_field_1439_of_type_JavaxSwingJList);
    paramclass_371 = new JLabel("Players");
    ((JScrollPane)localObject3).setColumnHeaderView(paramclass_371);
    ((JSplitPane)localObject2).setDividerLocation(130);
    (localObject2 = new JSplitPane()).setPreferredSize(new Dimension(130, 25));
    ((JSplitPane)localObject2).setMinimumSize(new Dimension(100, 25));
    ((JTabbedPane)localObject1).addTab("All", null, (Component)localObject2, null);
    localObject1 = new JPanel();
    ((JSplitPane)localObject2).setLeftComponent((Component)localObject1);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject3);
    this.jdField_field_1439_of_type_Class_1261 = new class_1261();
    (paramclass_371 = new JButton("Request")).addActionListener(new class_1245(this, paramStaremote));
    (paramStaremote = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    paramStaremote.gridx = 0;
    paramStaremote.gridy = 0;
    ((JPanel)localObject1).add(paramclass_371, paramStaremote);
    paramclass_371 = new JScrollPane();
    (paramStaremote = new GridBagConstraints()).fill = 1;
    paramStaremote.gridx = 0;
    paramStaremote.gridy = 1;
    ((JPanel)localObject1).add(paramclass_371, paramStaremote);
    (paramStaremote = new JList(this.jdField_field_1439_of_type_Class_1261)).addListSelectionListener(new class_1243(paramStaremote, (JSplitPane)localObject2));
    paramclass_371.setViewportView(paramStaremote);
    paramclass_371 = new JPanel();
    ((JSplitPane)localObject2).setRightComponent(paramclass_371);
    (paramStaremote = new GridBagLayout()).columnWidths = new int[] { 0 };
    paramStaremote.rowHeights = new int[] { 0 };
    paramStaremote.columnWeights = new double[] { 4.9E-324D };
    paramStaremote.rowWeights = new double[] { 4.9E-324D };
    paramclass_371.setLayout(paramStaremote);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if (this.jdField_field_1439_of_type_Class_1257 != null) {
      this.jdField_field_1439_of_type_Class_1257.a();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1251
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */