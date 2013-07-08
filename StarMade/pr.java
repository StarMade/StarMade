/*   1:    */import java.awt.BorderLayout;
/*   2:    */import java.awt.Component;
/*   3:    */import java.awt.Container;
/*   4:    */import java.awt.FlowLayout;
/*   5:    */import java.awt.GridBagConstraints;
/*   6:    */import java.awt.GridBagLayout;
/*   7:    */import java.awt.Insets;
/*   8:    */import java.awt.LayoutManager;
/*   9:    */import javax.swing.JButton;
/*  10:    */import javax.swing.JDialog;
/*  11:    */import javax.swing.JFileChooser;
/*  12:    */import javax.swing.JFrame;
/*  13:    */import javax.swing.JPanel;
/*  14:    */import javax.swing.JRootPane;
/*  15:    */import javax.swing.JTextField;
/*  16:    */import javax.swing.border.EmptyBorder;
/*  17:    */import org.schema.game.common.Starter;
/*  18:    */
/*  26:    */public final class pr
/*  27:    */  extends JDialog
/*  28:    */{
/*  29:    */  private static final long serialVersionUID = 2581659058321193133L;
/*  30: 30 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  31:    */  
/*  33:    */  private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*  34:    */  
/*  35:    */  private JFileChooser jdField_a_of_type_JavaxSwingJFileChooser;
/*  36:    */  
/*  38:    */  public pr(JFrame paramJFrame)
/*  39:    */  {
/*  40: 40 */    super(paramJFrame, true);
/*  41:    */    
/*  44: 44 */    setBounds(100, 100, 444, 135);
/*  45: 45 */    getContentPane().setLayout(new BorderLayout());
/*  46: 46 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  47: 47 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  48:    */    Object localObject;
/*  49: 49 */    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
/*  50: 50 */    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0 };
/*  51: 51 */    ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 4.9E-324D };
/*  52: 52 */    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/*  53: 53 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject);
/*  54:    */    
/*  55: 55 */    this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/*  56:    */    
/*  57: 57 */    (localObject = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  58: 58 */    ((GridBagConstraints)localObject).fill = 2;
/*  59: 59 */    ((GridBagConstraints)localObject).gridx = 0;
/*  60: 60 */    ((GridBagConstraints)localObject).gridy = 0;
/*  61: 61 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJTextField, localObject);
/*  62: 62 */    this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*  63: 63 */    if (Starter.a != null) {
/*  64: 64 */      this.jdField_a_of_type_JavaxSwingJTextField.setText(Starter.a);
/*  65:    */    }
/*  66:    */    
/*  69: 69 */    (localObject = new JButton("browse")).addActionListener(new ps(this, paramJFrame));
/*  70:    */    
/*  75: 75 */    (paramJFrame = new GridBagConstraints()).anchor = 13;
/*  76: 76 */    paramJFrame.gridx = 0;
/*  77: 77 */    paramJFrame.gridy = 1;
/*  78: 78 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject, paramJFrame);
/*  79:    */    
/*  81: 81 */    (
/*  82: 82 */      localObject = new JPanel()).setLayout(new FlowLayout(2));
/*  83: 83 */    getContentPane().add((Component)localObject, "South");
/*  84:    */    
/*  85: 85 */    (
/*  86: 86 */      paramJFrame = new JButton("OK")).addActionListener(new pt(this));
/*  87:    */    
/*  99: 99 */    paramJFrame.setActionCommand("OK");
/* 100:100 */    ((JPanel)localObject).add(paramJFrame);
/* 101:101 */    getRootPane().setDefaultButton(paramJFrame);
/* 102:    */    
/* 106:106 */    (
/* 107:107 */      paramJFrame = new JButton("Cancel")).addActionListener(new pu(this));
/* 108:    */    
/* 112:112 */    paramJFrame.setActionCommand("Cancel");
/* 113:113 */    ((JPanel)localObject).add(paramJFrame);
/* 114:    */    
/* 116:116 */    this.jdField_a_of_type_JavaxSwingJTextField.setText(Starter.a);
/* 117:    */  }
/* 118:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */