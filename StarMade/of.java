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
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import org.schema.game.common.data.element.BlockLevel;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ 
/*     */ public final class of extends JDialog
/*     */ {
/*     */   private static final long serialVersionUID = 2535180629644746651L;
/*  31 */   private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/*     */   private short jdField_a_of_type_Short;
/*     */   private JLabel jdField_a_of_type_JavaxSwingJLabel;
/*     */   private JSlider jdField_a_of_type_JavaxSwingJSlider;
/*     */ 
/*     */   public of(JFrame paramJFrame, BlockLevel paramBlockLevel, ElementInformation paramElementInformation, JTextPane paramJTextPane)
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
/*  65 */     this.jdField_a_of_type_Short = (paramBlockLevel != null ? paramBlockLevel.getIdBase() : -1);
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
/*  84 */     ((JButton)localObject1).addActionListener(new og(this, paramJFrame));
/*     */ 
/* 101 */     localObject1 = new JLabel("Level");
/* 102 */     (
/* 103 */       localObject2 = new GridBagConstraints()).insets = 
/* 103 */       new Insets(0, 0, 0, 5);
/* 104 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 105 */     ((GridBagConstraints)localObject2).gridy = 1;
/* 106 */     this.jdField_a_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
/*     */ 
/* 109 */     this.jdField_a_of_type_JavaxSwingJSlider = new JSlider();
/* 110 */     this.jdField_a_of_type_JavaxSwingJSlider.setSnapToTicks(true);
/* 111 */     this.jdField_a_of_type_JavaxSwingJSlider.setMajorTickSpacing(1);
/* 112 */     this.jdField_a_of_type_JavaxSwingJSlider.setPaintTicks(true);
/* 113 */     this.jdField_a_of_type_JavaxSwingJSlider.setPaintLabels(true);
/* 114 */     this.jdField_a_of_type_JavaxSwingJSlider.setMinimum(1);
/* 115 */     this.jdField_a_of_type_JavaxSwingJSlider.setMaximum(5);
/* 116 */     this.jdField_a_of_type_JavaxSwingJSlider.setValue(paramBlockLevel != null ? paramBlockLevel.getLevel() : 1);
/* 117 */     (
/* 118 */       localObject1 = new GridBagConstraints()).fill = 
/* 118 */       2;
/* 119 */     ((GridBagConstraints)localObject1).weightx = 11.0D;
/* 120 */     ((GridBagConstraints)localObject1).gridwidth = 2;
/* 121 */     ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
/* 122 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 123 */     ((GridBagConstraints)localObject1).gridy = 1;
/* 124 */     this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJSlider, localObject1);
/*     */ 
/* 127 */     (
/* 128 */       localObject1 = new JPanel())
/* 128 */       .setLayout(new FlowLayout(2));
/* 129 */     getContentPane().add((Component)localObject1, "South");
/*     */ 
/* 131 */     (
/* 132 */       localObject2 = new JButton("OK"))
/* 132 */       .setActionCommand("OK");
/* 133 */     ((JPanel)localObject1).add((Component)localObject2);
/* 134 */     getRootPane().setDefaultButton((JButton)localObject2);
/*     */ 
/* 136 */     ((JButton)localObject2).addActionListener(new oi(this, paramElementInformation, paramJTextPane));
/*     */ 
/* 152 */     (
/* 153 */       localObject2 = new JButton("Cancel"))
/* 153 */       .setActionCommand("Cancel");
/* 154 */     ((JPanel)localObject1).add((Component)localObject2);
/* 155 */     ((JButton)localObject2).addActionListener(new oj(this));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     of
 * JD-Core Version:    0.6.2
 */