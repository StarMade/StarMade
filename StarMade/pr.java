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
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import org.schema.game.common.Starter;
/*     */ 
/*     */ public final class pr extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 2581659058321193133L;
/*  30 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private JTextField jdField_a_of_type_JavaxSwingJTextField;
/*     */   private JFileChooser jdField_a_of_type_JavaxSwingJFileChooser;
/*     */ 
/*     */   public pr(JFrame paramJFrame)
/*     */   {
/*  40 */     super(paramJFrame, true);
/*     */ 
/*  44 */     setBounds(100, 100, 444, 135);
/*  45 */     getContentPane().setLayout(new BorderLayout());
/*  46 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  47 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*     */     Object localObject;
/*  48 */     (
/*  49 */       localObject = new GridBagLayout()).columnWidths = 
/*  49 */       new int[] { 0, 0 };
/*  50 */     ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0 };
/*  51 */     ((GridBagLayout)localObject).columnWeights = new double[] { 1.0D, 4.9E-324D };
/*  52 */     ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/*  53 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject);
/*     */ 
/*  55 */     this.jdField_a_of_type_JavaxSwingJTextField = new JTextField();
/*  56 */     (
/*  57 */       localObject = new GridBagConstraints()).insets = 
/*  57 */       new Insets(0, 0, 5, 0);
/*  58 */     ((GridBagConstraints)localObject).fill = 2;
/*  59 */     ((GridBagConstraints)localObject).gridx = 0;
/*  60 */     ((GridBagConstraints)localObject).gridy = 0;
/*  61 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJTextField, localObject);
/*  62 */     this.jdField_a_of_type_JavaxSwingJTextField.setColumns(10);
/*  63 */     if (Starter.a != null) {
/*  64 */       this.jdField_a_of_type_JavaxSwingJTextField.setText(Starter.a);
/*     */     }
/*     */ 
/*  68 */     (
/*  69 */       localObject = new JButton("browse"))
/*  69 */       .addActionListener(new ps(this, paramJFrame));
/*     */ 
/*  74 */     (
/*  75 */       paramJFrame = new GridBagConstraints()).anchor = 
/*  75 */       13;
/*  76 */     paramJFrame.gridx = 0;
/*  77 */     paramJFrame.gridy = 1;
/*  78 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject, paramJFrame);
/*     */ 
/*  81 */     (
/*  82 */       localObject = new JPanel())
/*  82 */       .setLayout(new FlowLayout(2));
/*  83 */     getContentPane().add((Component)localObject, "South");
/*     */ 
/*  85 */     (
/*  86 */       paramJFrame = new JButton("OK"))
/*  86 */       .addActionListener(new pt(this));
/*     */ 
/*  99 */     paramJFrame.setActionCommand("OK");
/* 100 */     ((JPanel)localObject).add(paramJFrame);
/* 101 */     getRootPane().setDefaultButton(paramJFrame);
/*     */ 
/* 106 */     (
/* 107 */       paramJFrame = new JButton("Cancel"))
/* 107 */       .addActionListener(new pu(this));
/*     */ 
/* 112 */     paramJFrame.setActionCommand("Cancel");
/* 113 */     ((JPanel)localObject).add(paramJFrame);
/*     */ 
/* 116 */     this.jdField_a_of_type_JavaxSwingJTextField.setText(Starter.a);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pr
 * JD-Core Version:    0.6.2
 */