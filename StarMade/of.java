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
/*  11:    */import javax.swing.JFrame;
/*  12:    */import javax.swing.JLabel;
/*  13:    */import javax.swing.JPanel;
/*  14:    */import javax.swing.JRootPane;
/*  15:    */import javax.swing.JSlider;
/*  16:    */import javax.swing.JTextPane;
/*  17:    */import javax.swing.border.EmptyBorder;
/*  18:    */import org.schema.game.common.data.element.BlockLevel;
/*  19:    */import org.schema.game.common.data.element.ElementInformation;
/*  20:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  21:    */
/*  27:    */public final class of
/*  28:    */  extends JDialog
/*  29:    */{
/*  30:    */  private static final long serialVersionUID = 2535180629644746651L;
/*  31: 31 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  32:    */  
/*  34:    */  private short jdField_a_of_type_Short;
/*  35:    */  
/*  36:    */  private JLabel jdField_a_of_type_JavaxSwingJLabel;
/*  37:    */  
/*  38:    */  private JSlider jdField_a_of_type_JavaxSwingJSlider;
/*  39:    */  
/*  41:    */  public of(JFrame paramJFrame, BlockLevel paramBlockLevel, ElementInformation paramElementInformation, JTextPane paramJTextPane)
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
/*  65: 65 */    this.jdField_a_of_type_Short = (paramBlockLevel != null ? paramBlockLevel.getIdBase() : -1);
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
/*  84: 84 */    ((JButton)localObject1).addActionListener(new og(this, paramJFrame));
/*  85:    */    
/* 101:101 */    localObject1 = new JLabel("Level");
/* 102:    */    
/* 103:103 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
/* 104:104 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 105:105 */    ((GridBagConstraints)localObject2).gridy = 1;
/* 106:106 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/* 107:    */    
/* 109:109 */    this.jdField_a_of_type_JavaxSwingJSlider = new JSlider();
/* 110:110 */    this.jdField_a_of_type_JavaxSwingJSlider.setSnapToTicks(true);
/* 111:111 */    this.jdField_a_of_type_JavaxSwingJSlider.setMajorTickSpacing(1);
/* 112:112 */    this.jdField_a_of_type_JavaxSwingJSlider.setPaintTicks(true);
/* 113:113 */    this.jdField_a_of_type_JavaxSwingJSlider.setPaintLabels(true);
/* 114:114 */    this.jdField_a_of_type_JavaxSwingJSlider.setMinimum(1);
/* 115:115 */    this.jdField_a_of_type_JavaxSwingJSlider.setMaximum(5);
/* 116:116 */    this.jdField_a_of_type_JavaxSwingJSlider.setValue(paramBlockLevel != null ? paramBlockLevel.getLevel() : 1);
/* 117:    */    
/* 118:118 */    (localObject1 = new GridBagConstraints()).fill = 2;
/* 119:119 */    ((GridBagConstraints)localObject1).weightx = 11.0D;
/* 120:120 */    ((GridBagConstraints)localObject1).gridwidth = 2;
/* 121:121 */    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 122:122 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 123:123 */    ((GridBagConstraints)localObject1).gridy = 1;
/* 124:124 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSlider, localObject1);
/* 125:    */    
/* 127:127 */    (
/* 128:128 */      localObject1 = new JPanel()).setLayout(new FlowLayout(2));
/* 129:129 */    getContentPane().add((Component)localObject1, "South");
/* 130:    */    
/* 131:131 */    (
/* 132:132 */      localObject2 = new JButton("OK")).setActionCommand("OK");
/* 133:133 */    ((JPanel)localObject1).add((Component)localObject2);
/* 134:134 */    getRootPane().setDefaultButton((JButton)localObject2);
/* 135:    */    
/* 136:136 */    ((JButton)localObject2).addActionListener(new oi(this, paramElementInformation, paramJTextPane));
/* 137:    */    
/* 152:152 */    (
/* 153:153 */      localObject2 = new JButton("Cancel")).setActionCommand("Cancel");
/* 154:154 */    ((JPanel)localObject1).add((Component)localObject2);
/* 155:155 */    ((JButton)localObject2).addActionListener(new oj(this));
/* 156:    */  }
/* 157:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     of
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */