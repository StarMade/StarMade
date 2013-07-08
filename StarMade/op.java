/*   1:    */import java.awt.BorderLayout;
/*   2:    */import java.awt.Component;
/*   3:    */import java.awt.Container;
/*   4:    */import java.awt.FlowLayout;
/*   5:    */import java.awt.GridBagConstraints;
/*   6:    */import java.awt.GridBagLayout;
/*   7:    */import java.awt.Insets;
/*   8:    */import java.awt.LayoutManager;
/*   9:    */import java.io.PrintStream;
/*  10:    */import javax.swing.JButton;
/*  11:    */import javax.swing.JDialog;
/*  12:    */import javax.swing.JFrame;
/*  13:    */import javax.swing.JLabel;
/*  14:    */import javax.swing.JPanel;
/*  15:    */import javax.swing.JRootPane;
/*  16:    */import javax.swing.JSpinner;
/*  17:    */import javax.swing.border.EmptyBorder;
/*  18:    */import org.schema.game.common.data.element.ElementInformation;
/*  19:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  20:    */
/*  28:    */public class op
/*  29:    */  extends JDialog
/*  30:    */{
/*  31:    */  private static final long serialVersionUID = -1325095027616126151L;
/*  32: 32 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  33:    */  
/*  34:    */  private short jdField_a_of_type_Short;
/*  35:    */  
/*  36:    */  private JLabel jdField_a_of_type_JavaxSwingJLabel;
/*  37:    */  
/*  38:    */  private JSpinner jdField_a_of_type_JavaxSwingJSpinner;
/*  39:    */  private JLabel b;
/*  40:    */  
/*  41:    */  public op(JFrame paramJFrame, nb paramnb)
/*  42:    */  {
/*  43: 43 */    super(paramJFrame, true);
/*  44: 44 */    setTitle("Block Level Editor");
/*  45: 45 */    setBounds(100, 100, 510, 184);
/*  46: 46 */    getContentPane().setLayout(new BorderLayout());
/*  47: 47 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  48: 48 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  49:    */    
/*  50: 50 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0, 0 };
/*  51: 51 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
/*  52: 52 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  53: 53 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/*  54: 54 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*  55:    */    
/*  56: 56 */    Object localObject1 = new JLabel("Base Element");
/*  57:    */    Object localObject2;
/*  58: 58 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/*  59: 59 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/*  60: 60 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  61: 61 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  62: 62 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  63:    */    
/*  65: 65 */    this.jdField_a_of_type_Short = -1;
/*  66:    */    
/*  67: 67 */    this.jdField_a_of_type_JavaxSwingJLabel = new JLabel(this.jdField_a_of_type_Short > 0 ? ElementKeyMap.getInfo(this.jdField_a_of_type_Short).toString() : "undefined");
/*  68:    */    
/*  69: 69 */    (localObject1 = new GridBagConstraints()).weightx = 1.0D;
/*  70: 70 */    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 5);
/*  71: 71 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  72: 72 */    ((GridBagConstraints)localObject1).gridy = 0;
/*  73: 73 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJLabel, localObject1);
/*  74:    */    
/*  76: 76 */    localObject1 = new JButton("Choose");
/*  77:    */    
/*  78: 78 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  79: 79 */    ((GridBagConstraints)localObject2).anchor = 13;
/*  80: 80 */    ((GridBagConstraints)localObject2).gridx = 2;
/*  81: 81 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  82: 82 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  83:    */    
/*  84: 84 */    ((JButton)localObject1).addActionListener(new oq(this, paramJFrame));
/*  85:    */    
/* 101:101 */    System.err.println("FAC: " + null);
/* 102:102 */    this.b = new JLabel("Count 0");
/* 103:    */    
/* 104:104 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
/* 105:105 */    ((GridBagConstraints)localObject1).gridx = 0;
/* 106:106 */    ((GridBagConstraints)localObject1).gridy = 1;
/* 107:107 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject1);
/* 108:    */    
/* 110:110 */    this.jdField_a_of_type_JavaxSwingJSpinner = new JSpinner();
/* 111:    */    
/* 117:117 */    this.jdField_a_of_type_JavaxSwingJSpinner.setValue(Integer.valueOf(1));
/* 118:    */    
/* 119:119 */    (localObject1 = new GridBagConstraints()).fill = 2;
/* 120:120 */    ((GridBagConstraints)localObject1).weightx = 11.0D;
/* 121:121 */    ((GridBagConstraints)localObject1).gridwidth = 2;
/* 122:122 */    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 123:123 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 124:124 */    ((GridBagConstraints)localObject1).gridy = 1;
/* 125:125 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSpinner, localObject1);
/* 126:    */    
/* 128:128 */    (localObject2 = new GridBagConstraints()).fill = 2;
/* 129:129 */    ((GridBagConstraints)localObject2).weightx = 11.0D;
/* 130:130 */    ((GridBagConstraints)localObject2).gridwidth = 2;
/* 131:131 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
/* 132:132 */    ((GridBagConstraints)localObject2).gridx = 2;
/* 133:133 */    ((GridBagConstraints)localObject2).gridy = 1;
/* 134:134 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSpinner, localObject2);
/* 135:    */    
/* 136:136 */    this.jdField_a_of_type_JavaxSwingJSpinner.addChangeListener(new os(this));
/* 137:    */    
/* 146:146 */    (
/* 147:147 */      localObject1 = new JPanel()).setLayout(new FlowLayout(2));
/* 148:148 */    getContentPane().add((Component)localObject1, "South");
/* 149:    */    
/* 150:150 */    (
/* 151:151 */      localObject2 = new JButton("OK")).setActionCommand("OK");
/* 152:152 */    ((JPanel)localObject1).add((Component)localObject2);
/* 153:153 */    getRootPane().setDefaultButton((JButton)localObject2);
/* 154:    */    
/* 155:155 */    ((JButton)localObject2).addActionListener(new ot(this, paramnb));
/* 156:    */    
/* 172:172 */    (
/* 173:173 */      localObject2 = new JButton("Cancel")).setActionCommand("Cancel");
/* 174:174 */    ((JPanel)localObject1).add((Component)localObject2);
/* 175:175 */    ((JButton)localObject2).addActionListener(new ou(this));
/* 176:    */  }
/* 177:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     op
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */