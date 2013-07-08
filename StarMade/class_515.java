import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import org.schema.game.common.data.element.BlockLevel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public final class class_515
  extends JDialog
{
  private static final long serialVersionUID = 2535180629644746651L;
  private final JPanel jdField_field_824_of_type_JavaxSwingJPanel = new JPanel();
  private short jdField_field_824_of_type_Short;
  private JLabel jdField_field_824_of_type_JavaxSwingJLabel;
  private JSlider jdField_field_824_of_type_JavaxSwingJSlider;
  
  public class_515(JFrame paramJFrame, BlockLevel paramBlockLevel, ElementInformation paramElementInformation, JTextPane paramJTextPane)
  {
    super(paramJFrame, true);
    setTitle("Block Level Editor");
    setBounds(100, 100, 510, 184);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_824_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_824_of_type_JavaxSwingJPanel, "Center");
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
    this.jdField_field_824_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    Object localObject1 = new JLabel("Base Element");
    Object localObject2;
    (localObject2 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_824_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.jdField_field_824_of_type_Short = (paramBlockLevel != null ? paramBlockLevel.getIdBase() : -1);
    this.jdField_field_824_of_type_JavaxSwingJLabel = new JLabel(this.jdField_field_824_of_type_Short > 0 ? ElementKeyMap.getInfo(this.jdField_field_824_of_type_Short).toString() : "undefined");
    (localObject1 = new GridBagConstraints()).weightx = 1.0D;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 5);
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 0;
    this.jdField_field_824_of_type_JavaxSwingJPanel.add(this.jdField_field_824_of_type_JavaxSwingJLabel, localObject1);
    localObject1 = new JButton("Choose");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).anchor = 13;
    ((GridBagConstraints)localObject2).gridx = 2;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_824_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    ((JButton)localObject1).addActionListener(new class_538(this, paramJFrame));
    localObject1 = new JLabel("Level");
    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_824_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    this.jdField_field_824_of_type_JavaxSwingJSlider = new JSlider();
    this.jdField_field_824_of_type_JavaxSwingJSlider.setSnapToTicks(true);
    this.jdField_field_824_of_type_JavaxSwingJSlider.setMajorTickSpacing(1);
    this.jdField_field_824_of_type_JavaxSwingJSlider.setPaintTicks(true);
    this.jdField_field_824_of_type_JavaxSwingJSlider.setPaintLabels(true);
    this.jdField_field_824_of_type_JavaxSwingJSlider.setMinimum(1);
    this.jdField_field_824_of_type_JavaxSwingJSlider.setMaximum(5);
    this.jdField_field_824_of_type_JavaxSwingJSlider.setValue(paramBlockLevel != null ? paramBlockLevel.getLevel() : 1);
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).weightx = 11.0D;
    ((GridBagConstraints)localObject1).gridwidth = 2;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 1;
    this.jdField_field_824_of_type_JavaxSwingJPanel.add(this.jdField_field_824_of_type_JavaxSwingJSlider, localObject1);
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    ((JButton)localObject2).addActionListener(new class_534(this, paramElementInformation, paramJTextPane));
    (localObject2 = new JButton("Cancel")).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
    ((JButton)localObject2).addActionListener(new class_536(this));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_515
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */