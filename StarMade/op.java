/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRootPane;
/*     */ import javax.swing.JSpinner;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ 
/*     */ public class op extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = -1325095027616126151L;
/*  32 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private short jdField_a_of_type_Short;
/*     */   private JLabel jdField_a_of_type_JavaxSwingJLabel;
/*     */   private JSpinner jdField_a_of_type_JavaxSwingJSpinner;
/*     */   private JLabel b;
/*     */ 
/*     */   public op(JFrame paramJFrame, nb paramnb)
/*     */   {
/*  43 */     super(paramJFrame, true);
/*  44 */     setTitle("Block Level Editor");
/*  45 */     setBounds(100, 100, 510, 184);
/*  46 */     getContentPane().setLayout(new BorderLayout());
/*  47 */     this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  48 */     getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/*  49 */     (
/*  50 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  50 */       new int[] { 0, 0, 0, 0 };
/*  51 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0 };
/*  52 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  53 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 4.9E-324D };
/*  54 */     this.jdField_a_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
/*     */ 
/*  56 */     Object localObject1 = new JLabel("Base Element");
/*     */     Object localObject2;
/*  57 */     (
/*  58 */       localObject2 = new GridBagConstraints()).anchor = 
/*  58 */       17;
/*  59 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 5);
/*  60 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  61 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  62 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  65 */     this.jdField_a_of_type_Short = -1;
/*     */ 
/*  67 */     this.jdField_a_of_type_JavaxSwingJLabel = new JLabel(this.jdField_a_of_type_Short > 0 ? ElementKeyMap.getInfo(this.jdField_a_of_type_Short).toString() : "undefined");
/*  68 */     (
/*  69 */       localObject1 = new GridBagConstraints()).weightx = 
/*  69 */       1.0D;
/*  70 */     ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 5, 5);
/*  71 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  72 */     ((GridBagConstraints)localObject1).gridy = 0;
/*  73 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJLabel, localObject1);
/*     */ 
/*  76 */     localObject1 = new JButton("Choose");
/*  77 */     (
/*  78 */       localObject2 = new GridBagConstraints()).insets = 
/*  78 */       new Insets(0, 0, 5, 0);
/*  79 */     ((GridBagConstraints)localObject2).anchor = 13;
/*  80 */     ((GridBagConstraints)localObject2).gridx = 2;
/*  81 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  82 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/*  84 */     ((JButton)localObject1).addActionListener(new oq(this, paramJFrame));
/*     */ 
/* 101 */     System.err.println("FAC: " + null);
/* 102 */     this.b = new JLabel("Count 0");
/* 103 */     (
/* 104 */       localObject1 = new GridBagConstraints()).insets = 
/* 104 */       new Insets(0, 0, 0, 5);
/* 105 */     ((GridBagConstraints)localObject1).gridx = 0;
/* 106 */     ((GridBagConstraints)localObject1).gridy = 1;
/* 107 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.b, localObject1);
/*     */ 
/* 110 */     this.jdField_a_of_type_JavaxSwingJSpinner = new JSpinner();
/*     */ 
/* 117 */     this.jdField_a_of_type_JavaxSwingJSpinner.setValue(Integer.valueOf(1));
/* 118 */     (
/* 119 */       localObject1 = new GridBagConstraints()).fill = 
/* 119 */       2;
/* 120 */     ((GridBagConstraints)localObject1).weightx = 11.0D;
/* 121 */     ((GridBagConstraints)localObject1).gridwidth = 2;
/* 122 */     ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 123 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 124 */     ((GridBagConstraints)localObject1).gridy = 1;
/* 125 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSpinner, localObject1);
/*     */ 
/* 127 */     (
/* 128 */       localObject2 = new GridBagConstraints()).fill = 
/* 128 */       2;
/* 129 */     ((GridBagConstraints)localObject2).weightx = 11.0D;
/* 130 */     ((GridBagConstraints)localObject2).gridwidth = 2;
/* 131 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
/* 132 */     ((GridBagConstraints)localObject2).gridx = 2;
/* 133 */     ((GridBagConstraints)localObject2).gridy = 1;
/* 134 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSpinner, localObject2);
/*     */ 
/* 136 */     this.jdField_a_of_type_JavaxSwingJSpinner.addChangeListener(new os(this));
/*     */ 
/* 146 */     (
/* 147 */       localObject1 = new JPanel())
/* 147 */       .setLayout(new FlowLayout(2));
/* 148 */     getContentPane().add((Component)localObject1, "South");
/*     */ 
/* 150 */     (
/* 151 */       localObject2 = new JButton("OK"))
/* 151 */       .setActionCommand("OK");
/* 152 */     ((JPanel)localObject1).add((Component)localObject2);
/* 153 */     getRootPane().setDefaultButton((JButton)localObject2);
/*     */ 
/* 155 */     ((JButton)localObject2).addActionListener(new ot(this, paramnb));
/*     */ 
/* 172 */     (
/* 173 */       localObject2 = new JButton("Cancel"))
/* 173 */       .setActionCommand("Cancel");
/* 174 */     ((JPanel)localObject1).add((Component)localObject2);
/* 175 */     ((JButton)localObject2).addActionListener(new ou(this));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     op
 * JD-Core Version:    0.6.2
 */