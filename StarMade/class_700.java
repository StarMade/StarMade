import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.data.element.ElementInformation;

public final class class_700
  extends JDialog
{
  private static final long serialVersionUID = 5531493462948253661L;
  private final JPanel jdField_field_966_of_type_JavaxSwingJPanel = new JPanel();
  private class_695 jdField_field_966_of_type_Class_695;
  private JButton jdField_field_966_of_type_JavaxSwingJButton;
  private JButton field_967;
  private JList jdField_field_966_of_type_JavaxSwingJList;
  private JButton field_968;
  
  public class_700(JFrame paramJFrame, ElementInformation paramElementInformation, class_532 paramclass_532)
  {
    super(paramJFrame, true);
    class_563 localclass_563;
    (localclass_563 = new class_563()).a(paramElementInformation.field_1931);
    HashSet localHashSet = new HashSet();
    Object localObject1 = localclass_563.field_869.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (class_561)((Iterator)localObject1).next();
      localHashSet.add(localObject2);
    }
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_966_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_966_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 50, 0, 200 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D };
    this.jdField_field_966_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    this.jdField_field_966_of_type_JavaxSwingJButton = new JButton("Edit");
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_966_of_type_JavaxSwingJPanel.add(this.jdField_field_966_of_type_JavaxSwingJButton, localObject2);
    this.field_968 = new JButton("Add");
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 1;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_966_of_type_JavaxSwingJPanel.add(this.field_968, localObject2);
    this.field_967 = new JButton("Delete");
    (localObject2 = new GridBagConstraints()).anchor = 13;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 2;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_966_of_type_JavaxSwingJPanel.add(this.field_967, localObject2);
    Object localObject2 = new JScrollPane();
    (localObject1 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject1).weightx = 1.0D;
    ((GridBagConstraints)localObject1).gridwidth = 3;
    ((GridBagConstraints)localObject1).fill = 1;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 1;
    this.jdField_field_966_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
    this.jdField_field_966_of_type_JavaxSwingJList = new JList();
    this.jdField_field_966_of_type_Class_695 = new class_695();
    this.jdField_field_966_of_type_JavaxSwingJList.setModel(this.jdField_field_966_of_type_Class_695);
    localObject1 = localHashSet.iterator();
    while (((Iterator)localObject1).hasNext()) {
      this.jdField_field_966_of_type_Class_695.a1((Comparable)((Iterator)localObject1).next());
    }
    ((JScrollPane)localObject2).setViewportView(this.jdField_field_966_of_type_JavaxSwingJList);
    (localObject2 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject2, "South");
    (localObject1 = new JButton("OK")).setActionCommand("OK");
    ((JPanel)localObject2).add((Component)localObject1);
    getRootPane().setDefaultButton((JButton)localObject1);
    ((JButton)localObject1).addActionListener(new class_702(this, localHashSet, localclass_563, paramElementInformation, paramclass_532));
    (localObject1 = new JButton("Cancel")).setActionCommand("Cancel");
    ((JButton)localObject1).addActionListener(new class_502(this));
    ((JPanel)localObject2).add((Component)localObject1);
    this.jdField_field_966_of_type_JavaxSwingJButton.addActionListener(new class_500(this, paramJFrame));
    this.field_968.addActionListener(new class_496(this, paramJFrame));
    this.field_967.addActionListener(new class_492(this));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_700
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */