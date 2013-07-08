import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
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
import org.schema.game.common.data.element.ElementKeyMap;

public final class class_490
  extends JDialog
{
  private static final long serialVersionUID = 5531493462948253661L;
  private final JPanel jdField_field_807_of_type_JavaxSwingJPanel = new JPanel();
  private class_695 jdField_field_807_of_type_Class_695;
  private JButton jdField_field_807_of_type_JavaxSwingJButton;
  private JButton field_808;
  private JList jdField_field_807_of_type_JavaxSwingJList;
  
  public class_490(JFrame paramJFrame, Set paramSet)
  {
    super(paramJFrame, true);
    HashSet localHashSet = new HashSet();
    Object localObject1 = paramSet.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Short)((Iterator)localObject1).next();
      localHashSet.add(ElementKeyMap.getInfo(((Short)localObject2).shortValue()));
    }
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_807_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_807_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 224, 200 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D };
    this.jdField_field_807_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    this.jdField_field_807_of_type_JavaxSwingJButton = new JButton("Add");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_807_of_type_JavaxSwingJPanel.add(this.jdField_field_807_of_type_JavaxSwingJButton, localObject2);
    this.field_808 = new JButton("Delete");
    (localObject2 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 1;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_807_of_type_JavaxSwingJPanel.add(this.field_808, localObject2);
    Object localObject2 = new JScrollPane();
    (localObject1 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject1).weightx = 1.0D;
    ((GridBagConstraints)localObject1).gridwidth = 2;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject1).fill = 1;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 1;
    this.jdField_field_807_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
    this.jdField_field_807_of_type_JavaxSwingJList = new JList();
    this.jdField_field_807_of_type_Class_695 = new class_695();
    this.jdField_field_807_of_type_JavaxSwingJList.setModel(this.jdField_field_807_of_type_Class_695);
    localObject1 = localHashSet.iterator();
    while (((Iterator)localObject1).hasNext()) {
      this.jdField_field_807_of_type_Class_695.a1((Comparable)((Iterator)localObject1).next());
    }
    ((JScrollPane)localObject2).setViewportView(this.jdField_field_807_of_type_JavaxSwingJList);
    (localObject2 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject2, "South");
    (localObject1 = new JButton("OK")).setActionCommand("OK");
    ((JPanel)localObject2).add((Component)localObject1);
    getRootPane().setDefaultButton((JButton)localObject1);
    ((JButton)localObject1).addActionListener(new class_488(this, localHashSet, paramSet));
    (localObject1 = new JButton("Cancel")).setActionCommand("Cancel");
    ((JButton)localObject1).addActionListener(new class_511(this));
    ((JPanel)localObject2).add((Component)localObject1);
    this.jdField_field_807_of_type_JavaxSwingJButton.addActionListener(new class_509(this, paramJFrame));
    this.field_808.addActionListener(new class_505(this));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_490
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */