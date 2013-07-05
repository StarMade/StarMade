/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ public final class qb extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = -490690742906601152L;
/*  27 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*     */   private JTextField b;
/*     */   private JTextField c;
/*     */ 
/*     */   public qb(JFrame paramJFrame, qa paramqa, qe paramqe)
/*     */   {
/*  37 */     super(paramJFrame, true);
/*  38 */     setTitle("Create Connection");
/*  39 */     setBounds(100, 100, 320, 166);
/*  40 */     getContentPane().setLayout(new BorderLayout());
/*  41 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  42 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  43 */     (
/*  44 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  44 */       new int[] { 0, 0, 0 };
/*  45 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0 };
/*  46 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  47 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  48 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/*  50 */     Object localObject1 = new JLabel("Login Name");
/*     */     Object localObject2;
/*  51 */     (
/*  52 */       localObject2 = new GridBagConstraints()).insets = 
/*  52 */       new Insets(0, 5, 5, 5);
/*  53 */     ((GridBagConstraints)localObject2).anchor = 17;
/*  54 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  55 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  56 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  59 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/*  60 */     (
/*  61 */       localObject1 = new GridBagConstraints()).insets = 
/*  61 */       new Insets(0, 0, 5, 0);
/*  62 */     ((GridBagConstraints)localObject1).fill = 2;
/*  63 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  64 */     ((GridBagConstraints)localObject1).gridy = 0;
/*  65 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJTextField, localObject1);
/*  66 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*     */ 
/*  69 */     localObject1 = new JLabel("Host URL");
/*  70 */     (
/*  71 */       localObject2 = new GridBagConstraints()).anchor = 
/*  71 */       17;
/*  72 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/*  73 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  74 */     ((GridBagConstraints)localObject2).gridy = 1;
/*  75 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  78 */     this.b = new JTextField();
/*  79 */     (
/*  80 */       localObject1 = new GridBagConstraints()).insets = 
/*  80 */       new Insets(0, 0, 5, 0);
/*  81 */     ((GridBagConstraints)localObject1).fill = 2;
/*  82 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  83 */     ((GridBagConstraints)localObject1).gridy = 1;
/*  84 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject1);
/*  85 */     this.b.setColumns(10);
/*     */ 
/*  88 */     (
/*  89 */       localObject1 = new JLabel("Port"))
/*  89 */       .setHorizontalAlignment(2);
/*  90 */     (
/*  91 */       localObject2 = new GridBagConstraints()).anchor = 
/*  91 */       17;
/*  92 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 0, 5);
/*  93 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  94 */     ((GridBagConstraints)localObject2).gridy = 2;
/*  95 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  98 */     this.c = new JTextField();
/*  99 */     (
/* 100 */       localObject1 = new GridBagConstraints()).fill = 
/* 100 */       2;
/* 101 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 102 */     ((GridBagConstraints)localObject1).gridy = 2;
/* 103 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.c, localObject1);
/* 104 */     this.c.setColumns(10);
/* 105 */     this.c.setText("4242");
/*     */ 
/* 108 */     (
/* 109 */       localObject1 = new JPanel())
/* 109 */       .setLayout(new FlowLayout(2));
/* 110 */     getContentPane().add((Component)localObject1, "South");
/*     */ 
/* 112 */     (
/* 113 */       localObject2 = new JButton("OK"))
/* 113 */       .addActionListener(new qc(this, paramqe, paramqa, paramJFrame));
/*     */ 
/* 140 */     ((JButton)localObject2).setActionCommand("OK");
/* 141 */     ((JPanel)localObject1).add((Component)localObject2);
/* 142 */     getRootPane().setDefaultButton((JButton)localObject2);
/*     */ 
/* 145 */     (
/* 146 */       localObject2 = new JButton("Cancel"))
/* 146 */       .addActionListener(new qd(this));
/*     */ 
/* 151 */     ((JButton)localObject2).setActionCommand("Cancel");
/* 152 */     ((JPanel)localObject1).add((Component)localObject2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qb
 * JD-Core Version:    0.6.2
 */