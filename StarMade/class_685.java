import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.data.element.ElementInformation;

public final class class_685
  extends JDialog
{
  private static final long serialVersionUID = 8534270030237588331L;
  private final JPanel jdField_field_951_of_type_JavaxSwingJPanel = new JPanel();
  private final JComboBox jdField_field_951_of_type_JavaxSwingJComboBox = new JComboBox();
  private class_693 jdField_field_951_of_type_Class_693;
  private JTabbedPane jdField_field_951_of_type_JavaxSwingJTabbedPane;
  
  public class_685(JFrame paramJFrame, ElementInformation paramElementInformation, class_532 paramclass_532)
  {
    super(paramJFrame, true);
    setTitle("Texture Sheets");
    setBounds(50, 50, 1200, 900);
    (paramJFrame = new GridBagLayout()).columnWidths = new int[] { 0 };
    paramJFrame.rowHeights = new int[] { 20, 30, 0, 0, 0 };
    paramJFrame.columnWeights = new double[] { 0.0D };
    paramJFrame.rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    getContentPane().setLayout(paramJFrame);
    (paramJFrame = new GridBagConstraints()).weightx = 1.0D;
    paramJFrame.anchor = 11;
    paramJFrame.fill = 2;
    paramJFrame.insets = new Insets(0, 0, 5, 0);
    paramJFrame.gridx = 0;
    paramJFrame.gridy = 0;
    this.jdField_field_951_of_type_JavaxSwingJComboBox.addItem(Integer.valueOf(1));
    this.jdField_field_951_of_type_JavaxSwingJComboBox.addItem(Integer.valueOf(3));
    this.jdField_field_951_of_type_JavaxSwingJComboBox.addItem(Integer.valueOf(6));
    this.jdField_field_951_of_type_JavaxSwingJComboBox.setSelectedItem(Integer.valueOf(paramElementInformation.getIndividualSides()));
    this.jdField_field_951_of_type_JavaxSwingJComboBox.addActionListener(new class_687(this));
    getContentPane().add(this.jdField_field_951_of_type_JavaxSwingJComboBox, paramJFrame);
    this.jdField_field_951_of_type_Class_693 = new class_693(paramElementInformation);
    (paramJFrame = new GridBagConstraints()).weightx = 1.0D;
    paramJFrame.anchor = 17;
    paramJFrame.fill = 3;
    paramJFrame.insets = new Insets(0, 0, 5, 5);
    paramJFrame.gridx = 0;
    paramJFrame.gridy = 1;
    getContentPane().add(this.jdField_field_951_of_type_Class_693, paramJFrame);
    this.jdField_field_951_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    (paramJFrame = new GridBagConstraints()).weighty = 1.0D;
    paramJFrame.weightx = 1.0D;
    paramJFrame.anchor = 17;
    paramJFrame.fill = 1;
    paramJFrame.insets = new Insets(0, 0, 5, 0);
    paramJFrame.gridx = 0;
    paramJFrame.gridy = 2;
    getContentPane().add(this.jdField_field_951_of_type_JavaxSwingJPanel, paramJFrame);
    this.jdField_field_951_of_type_JavaxSwingJPanel.setLayout(new GridLayout(0, 1, 0, 0));
    this.jdField_field_951_of_type_JavaxSwingJTabbedPane = new JTabbedPane(1);
    this.jdField_field_951_of_type_JavaxSwingJPanel.add(this.jdField_field_951_of_type_JavaxSwingJTabbedPane);
    paramElementInformation.getIndividualSides();
    paramJFrame = new class_676(paramElementInformation, 0, this);
    this.jdField_field_951_of_type_JavaxSwingJTabbedPane.addTab("t000", null, paramJFrame, null);
    paramElementInformation.getIndividualSides();
    paramJFrame = new class_676(paramElementInformation, 1, this);
    this.jdField_field_951_of_type_JavaxSwingJTabbedPane.addTab("t001", null, paramJFrame, null);
    paramElementInformation.getIndividualSides();
    paramJFrame = new class_676(paramElementInformation, 2, this);
    this.jdField_field_951_of_type_JavaxSwingJTabbedPane.addTab("t002", null, paramJFrame, null);
    (paramJFrame = new JPanel()).setLayout(new FlowLayout(2));
    Object localObject;
    (localObject = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject).anchor = 11;
    ((GridBagConstraints)localObject).fill = 2;
    ((GridBagConstraints)localObject).gridx = 0;
    ((GridBagConstraints)localObject).gridy = 3;
    getContentPane().add(paramJFrame, localObject);
    (localObject = new JButton("OK")).setActionCommand("OK");
    paramJFrame.add((Component)localObject);
    getRootPane().setDefaultButton((JButton)localObject);
    ((JButton)localObject).addActionListener(new class_689(this, paramElementInformation, paramclass_532));
    (localObject = new JButton("Cancel")).setActionCommand("Cancel");
    paramJFrame.add((Component)localObject);
    ((JButton)localObject).addActionListener(new class_691(this));
  }
  
  public final void a(class_676 paramclass_676, int paramInt)
  {
    if (paramInt > 0)
    {
      System.err.println("UPDATING TEX DISPLAY " + this.jdField_field_951_of_type_JavaxSwingJComboBox.getSelectedItem());
      this.jdField_field_951_of_type_Class_693.a2((paramclass_676.b() << 8) + paramInt, this.jdField_field_951_of_type_JavaxSwingJComboBox.getSelectedItem() != null ? ((Integer)this.jdField_field_951_of_type_JavaxSwingJComboBox.getSelectedItem()).intValue() : 1);
    }
    else
    {
      System.err.println("NOT UPDATING TEX DISPLAY");
    }
    repaint();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_685
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */