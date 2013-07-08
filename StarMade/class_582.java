import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public final class class_582
  extends JPanel
  implements Observer
{
  private class_568 jdField_field_882_of_type_Class_568;
  private JList jdField_field_882_of_type_JavaxSwingJList;
  private class_580 jdField_field_882_of_type_Class_580;
  
  public class_582(class_371 paramclass_371)
  {
    paramclass_371.a45().addObserver(this);
    paramclass_371.a20().a141().addObserver(this);
    Object localObject1;
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 0.0D, 4.9E-324D };
    setLayout((LayoutManager)localObject1);
    (localObject1 = new JSplitPane()).setDividerSize(3);
    Object localObject2;
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).weighty = 1.0D;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).anchor = 18;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    add((Component)localObject1, localObject2);
    this.jdField_field_882_of_type_Class_568 = new class_568(paramclass_371);
    this.jdField_field_882_of_type_JavaxSwingJList = new JList(this.jdField_field_882_of_type_Class_568);
    this.jdField_field_882_of_type_JavaxSwingJList.setCellRenderer(new class_588());
    (localObject2 = new JScrollPane(this.jdField_field_882_of_type_JavaxSwingJList)).setMinimumSize(new Dimension(250, 23));
    ((JSplitPane)localObject1).setLeftComponent((Component)localObject2);
    ((JSplitPane)localObject1).setRightComponent(new JPanel());
    this.jdField_field_882_of_type_JavaxSwingJList.addMouseListener(new class_584(this, paramclass_371, (JSplitPane)localObject1));
    this.jdField_field_882_of_type_JavaxSwingJList.setSelectionMode(0);
    (localObject1 = new JButton("Add Faction")).addActionListener(new class_586(paramclass_371));
    ((JButton)localObject1).setHorizontalAlignment(2);
    (paramclass_371 = new GridBagConstraints()).anchor = 17;
    paramclass_371.gridx = 0;
    paramclass_371.gridy = 1;
    add((Component)localObject1, paramclass_371);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_882_of_type_Class_568.a();
    if (this.jdField_field_882_of_type_Class_580 != null) {
      this.jdField_field_882_of_type_Class_580.update(paramObservable, paramObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_582
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */