/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.EtchedBorder;
/*     */ 
/*     */ public final class px extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 7116287916594864261L;
/*  37 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*     */   private JPasswordField jdField_a_of_type_JavaxSwingJPasswordField;
/*     */   private JCheckBox jdField_a_of_type_JavaxSwingJCheckBox;
/*     */ 
/*     */   public px()
/*     */   {
/*  48 */     setTitle("Login to Star-Made.org");
/*  49 */     setBounds(100, 100, 470, 275);
/*  50 */     getContentPane().setLayout(new BorderLayout());
/*  51 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  52 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  53 */     (
/*  54 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  54 */       new int[] { 0 };
/*  55 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 20, 0, 0, 0, 0 };
/*  56 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D };
/*  57 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D };
/*  58 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/*  60 */     Object localObject1 = new JLabel("Please enter your www.Star-Made.org credentials");
/*  61 */     (
/*  62 */       localObject2 = new GridBagConstraints()).insets = 
/*  62 */       new Insets(0, 0, 5, 0);
/*  63 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  64 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  65 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  68 */     localObject1 = new JLabel("If you don't have an Account yet, please go to www.star-made.org to create one");
/*  69 */     (
/*  70 */       localObject2 = new GridBagConstraints()).insets = 
/*  70 */       new Insets(0, 0, 20, 0);
/*  71 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  72 */     ((GridBagConstraints)localObject2).gridy = 1;
/*  73 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  76 */     (
/*  77 */       localObject1 = new JPanel())
/*  77 */       .setBorder(new EtchedBorder(1, null, null));
/*  78 */     (
/*  79 */       localObject2 = new GridBagConstraints()).weightx = 
/*  79 */       1.0D;
/*  80 */     ((GridBagConstraints)localObject2).fill = 1;
/*  81 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/*  82 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  83 */     ((GridBagConstraints)localObject2).gridy = 2;
/*  84 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*  85 */     (
/*  86 */       localObject2 = new GridBagLayout()).columnWidths = 
/*  86 */       new int[] { 0, 80, 0, 50, 0 };
/*  87 */     ((GridBagLayout)localObject2).rowHeights = new int[] { 20, 0 };
/*  88 */     ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  89 */     ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
/*  90 */     ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
/*     */ 
/*  92 */     Object localObject2 = new JLabel("User Name");
/*     */     GridBagConstraints localGridBagConstraints;
/*  93 */     (
/*  94 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/*  94 */       13;
/*  95 */     localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/*  96 */     localGridBagConstraints.gridx = 0;
/*  97 */     localGridBagConstraints.gridy = 0;
/*  98 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/*     */ 
/* 101 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/* 102 */     (
/* 103 */       localObject2 = new GridBagConstraints()).weightx = 
/* 103 */       0.5D;
/* 104 */     ((GridBagConstraints)localObject2).anchor = 11;
/* 105 */     ((GridBagConstraints)localObject2).fill = 2;
/* 106 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
/* 107 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 108 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 109 */     ((JPanel)localObject1).add(this.jdField_a_of_type_JavaxSwingJTextField, localObject2);
/* 110 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(13);
/*     */ 
/* 113 */     localObject2 = new JLabel("Password");
/* 114 */     (
/* 115 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/* 115 */       13;
/* 116 */     localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
/* 117 */     localGridBagConstraints.gridx = 2;
/* 118 */     localGridBagConstraints.gridy = 0;
/* 119 */     ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
/*     */ 
/* 122 */     this.jdField_a_of_type_JavaxSwingJPasswordField = new JPasswordField();
/* 123 */     (
/* 124 */       localObject2 = new GridBagConstraints()).weightx = 
/* 124 */       1.0D;
/* 125 */     ((GridBagConstraints)localObject2).anchor = 11;
/* 126 */     ((GridBagConstraints)localObject2).fill = 2;
/* 127 */     ((GridBagConstraints)localObject2).gridx = 3;
/* 128 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 129 */     ((JPanel)localObject1).add(this.jdField_a_of_type_JavaxSwingJPasswordField, localObject2);
/*     */ 
/* 133 */     this.jdField_a_of_type_JavaxSwingJCheckBox = new JCheckBox("Save Login (encrypted)");
/* 134 */     (
/* 135 */       localObject1 = new GridBagConstraints()).anchor = 
/* 135 */       13;
/* 136 */     ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 0);
/* 137 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 138 */     ((GridBagConstraints)localObject1).gridy = 3;
/* 139 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJCheckBox, localObject1);
/*     */ 
/* 142 */     (
/* 143 */       localObject1 = new JButton("Delete Saved Login"))
/* 143 */       .addActionListener(new py());
/*     */ 
/* 153 */     ((JButton)localObject1).setEnabled(sD.a());
/* 154 */     ((JButton)localObject1).setHorizontalAlignment(4);
/* 155 */     (
/* 156 */       localObject2 = new GridBagConstraints()).anchor = 
/* 156 */       13;
/* 157 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 158 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 159 */     ((GridBagConstraints)localObject2).gridy = 4;
/* 160 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/* 163 */     (
/* 164 */       localObject1 = new JLabel("Note: this is optional!"))
/* 164 */       .setForeground(new Color(139, 0, 0));
/* 165 */     ((JLabel)localObject1).setFont(new Font("Tahoma", 1, 12));
/* 166 */     (
/* 167 */       localObject2 = new GridBagConstraints()).insets = 
/* 167 */       new Insets(15, 0, 5, 0);
/* 168 */     ((GridBagConstraints)localObject2).anchor = 17;
/* 169 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 170 */     ((GridBagConstraints)localObject2).gridy = 5;
/* 171 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/* 174 */     localObject1 = new JLabel("You don't have to be logged on to play single, or multiplayer");
/* 175 */     (
/* 176 */       localObject2 = new GridBagConstraints()).anchor = 
/* 176 */       17;
/* 177 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 178 */     ((GridBagConstraints)localObject2).gridy = 6;
/* 179 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/* 182 */     (
/* 183 */       localObject1 = new JPanel())
/* 183 */       .setLayout(new FlowLayout(2));
/* 184 */     getContentPane().add((Component)localObject1, "South");
/*     */ 
/* 186 */     (
/* 187 */       localObject2 = new JButton("OK"))
/* 187 */       .addActionListener(new pz(this));
/*     */ 
/* 210 */     ((JButton)localObject2).setActionCommand("OK");
/* 211 */     ((JPanel)localObject1).add((Component)localObject2);
/* 212 */     getRootPane().setDefaultButton((JButton)localObject2);
/*     */ 
/* 215 */     (
/* 216 */       localObject2 = new JButton("Cancel"))
/* 216 */       .addActionListener(new pA(this));
/*     */ 
/* 223 */     ((JButton)localObject2).setActionCommand("Cancel");
/* 224 */     ((JPanel)localObject1).add((Component)localObject2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     px
 * JD-Core Version:    0.6.2
 */