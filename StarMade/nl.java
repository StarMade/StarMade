/*   1:    */import java.awt.BorderLayout;
/*   2:    */import java.awt.Component;
/*   3:    */import java.awt.Container;
/*   4:    */import java.awt.FlowLayout;
/*   5:    */import java.awt.GridBagConstraints;
/*   6:    */import java.awt.GridBagLayout;
/*   7:    */import java.awt.Insets;
/*   8:    */import java.awt.LayoutManager;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.HashSet;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.Set;
/*  13:    */import javax.swing.JButton;
/*  14:    */import javax.swing.JDialog;
/*  15:    */import javax.swing.JFrame;
/*  16:    */import javax.swing.JList;
/*  17:    */import javax.swing.JPanel;
/*  18:    */import javax.swing.JRootPane;
/*  19:    */import javax.swing.JScrollPane;
/*  20:    */import javax.swing.border.EmptyBorder;
/*  21:    */import org.schema.game.common.data.element.ElementInformation;
/*  22:    */
/*  29:    */public final class nl
/*  30:    */  extends JDialog
/*  31:    */{
/*  32:    */  private static final long serialVersionUID = 5531493462948253661L;
/*  33: 33 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*  34:    */  
/*  38:    */  private oK jdField_a_of_type_OK;
/*  39:    */  
/*  43:    */  private JButton jdField_a_of_type_JavaxSwingJButton;
/*  44:    */  
/*  48:    */  private JButton b;
/*  49:    */  
/*  53:    */  private JList jdField_a_of_type_JavaxSwingJList;
/*  54:    */  
/*  58:    */  private JButton c;
/*  59:    */  
/*  64:    */  public nl(JFrame paramJFrame, ElementInformation paramElementInformation, ol paramol)
/*  65:    */  {
/*  66: 66 */    super(paramJFrame, true);
/*  67:    */    
/*  69:    */    oy localoy;
/*  70:    */    
/*  71: 71 */    (localoy = new oy()).a(paramElementInformation.id);
/*  72: 72 */    HashSet localHashSet = new HashSet();
/*  73:    */    
/*  74: 74 */    for (Object localObject1 = localoy.a.iterator(); ((Iterator)localObject1).hasNext();) { localObject2 = (oz)((Iterator)localObject1).next();
/*  75: 75 */      localHashSet.add(localObject2);
/*  76:    */    }
/*  77: 77 */    setBounds(100, 100, 450, 300);
/*  78: 78 */    getContentPane().setLayout(new BorderLayout());
/*  79: 79 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  80: 80 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  81:    */    
/*  82: 82 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 50, 0, 200 };
/*  83: 83 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
/*  84: 84 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D };
/*  85: 85 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D };
/*  86: 86 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*  87:    */    
/*  88: 88 */    this.jdField_a_of_type_JavaxSwingJButton = new JButton("Edit");
/*  89:    */    
/*  90: 90 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/*  91: 91 */    ((GridBagConstraints)localObject2).weightx = 1.0D;
/*  92: 92 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/*  93: 93 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  94: 94 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  95: 95 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJButton, localObject2);
/*  96:    */    
/* 100:100 */    this.c = new JButton("Add");
/* 101:    */    
/* 102:102 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 103:103 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/* 104:104 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 105:105 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 106:106 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.c, localObject2);
/* 107:    */    
/* 109:109 */    this.b = new JButton("Delete");
/* 110:    */    
/* 111:111 */    (localObject2 = new GridBagConstraints()).anchor = 13;
/* 112:112 */    ((GridBagConstraints)localObject2).weightx = 1.0D;
/* 113:113 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 114:114 */    ((GridBagConstraints)localObject2).gridx = 2;
/* 115:115 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 116:    */    
/* 117:117 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject2);
/* 118:    */    
/* 120:120 */    Object localObject2 = new JScrollPane();
/* 121:    */    
/* 122:122 */    (localObject1 = new GridBagConstraints()).weighty = 1.0D;
/* 123:123 */    ((GridBagConstraints)localObject1).weightx = 1.0D;
/* 124:124 */    ((GridBagConstraints)localObject1).gridwidth = 3;
/* 125:125 */    ((GridBagConstraints)localObject1).fill = 1;
/* 126:126 */    ((GridBagConstraints)localObject1).gridx = 0;
/* 127:127 */    ((GridBagConstraints)localObject1).gridy = 1;
/* 128:128 */    this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
/* 129:    */    
/* 130:130 */    this.jdField_a_of_type_JavaxSwingJList = new JList();
/* 131:131 */    this.jdField_a_of_type_OK = new oK();
/* 132:132 */    this.jdField_a_of_type_JavaxSwingJList.setModel(this.jdField_a_of_type_OK);
/* 133:133 */    localObject1 = localHashSet.iterator();
/* 134:134 */    while (((Iterator)localObject1).hasNext()) {
/* 135:135 */      this.jdField_a_of_type_OK.a((Comparable)((Iterator)localObject1).next());
/* 136:    */    }
/* 137:137 */    ((JScrollPane)localObject2).setViewportView(this.jdField_a_of_type_JavaxSwingJList);
/* 138:    */    
/* 141:141 */    (
/* 142:142 */      localObject2 = new JPanel()).setLayout(new FlowLayout(2));
/* 143:143 */    getContentPane().add((Component)localObject2, "South");
/* 144:    */    
/* 145:145 */    (
/* 146:146 */      localObject1 = new JButton("OK")).setActionCommand("OK");
/* 147:147 */    ((JPanel)localObject2).add((Component)localObject1);
/* 148:148 */    getRootPane().setDefaultButton((JButton)localObject1);
/* 149:    */    
/* 150:150 */    ((JButton)localObject1).addActionListener(new nm(this, localHashSet, localoy, paramElementInformation, paramol));
/* 151:    */    
/* 174:174 */    (
/* 175:175 */      localObject1 = new JButton("Cancel")).setActionCommand("Cancel");
/* 176:    */    
/* 177:177 */    ((JButton)localObject1).addActionListener(new nn(this));
/* 178:    */    
/* 184:184 */    ((JPanel)localObject2).add((Component)localObject1);
/* 185:    */    
/* 188:188 */    this.jdField_a_of_type_JavaxSwingJButton.addActionListener(new no(this, paramJFrame));
/* 189:    */    
/* 205:205 */    this.c.addActionListener(new nq(this, paramJFrame));
/* 206:    */    
/* 222:222 */    this.b.addActionListener(new ns(this));
/* 223:    */  }
/* 224:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     nl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */