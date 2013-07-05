/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Component;
/*    */ import java.awt.Container;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.GridBagConstraints;
/*    */ import java.awt.GridBagLayout;
/*    */ import java.awt.LayoutManager;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JRootPane;
/*    */ import javax.swing.JSplitPane;
/*    */ import javax.swing.border.EmptyBorder;
/*    */ 
/*    */ public final class ov extends JDialog
/*    */ {
/*    */   private static final long serialVersionUID = -3693336102651064550L;
/* 27 */   private final JPanel a = new JPanel();
/*    */ 
/*    */   public ov(JFrame paramJFrame, ArrayList paramArrayList1, ArrayList paramArrayList2, ol paramol)
/*    */   {
/* 36 */     super(paramJFrame, true);
/* 37 */     setBounds(100, 100, 450, 435);
/* 38 */     getContentPane().setLayout(new BorderLayout());
/* 39 */     this.a.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 40 */     getContentPane().add(this.a, "Center");
/*    */     Object localObject1;
/* 41 */     (
/* 42 */       localObject1 = new GridBagLayout()).columnWidths = 
/* 42 */       new int[] { 125 };
/* 43 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 25, 0 };
/* 44 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D };
/* 45 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 46 */     this.a.setLayout((LayoutManager)localObject1);
/*    */ 
/* 48 */     (
/* 49 */       localObject1 = new JSplitPane())
/* 49 */       .setOrientation(0);
/*    */     Object localObject2;
/* 51 */     (
/* 52 */       localObject2 = new GridBagConstraints()).weighty = 
/* 52 */       1.0D;
/* 53 */     ((GridBagConstraints)localObject2).weightx = 1.0D;
/* 54 */     ((GridBagConstraints)localObject2).fill = 1;
/* 55 */     ((GridBagConstraints)localObject2).anchor = 18;
/* 56 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 57 */     ((GridBagConstraints)localObject2).gridy = 0;
/* 58 */     this.a.add((Component)localObject1, localObject2);
/*    */ 
/* 60 */     paramArrayList1 = new om(paramJFrame, paramArrayList1);
/* 61 */     ((JSplitPane)localObject1).setLeftComponent(paramArrayList1);
/*    */ 
/* 64 */     paramArrayList1 = new om(paramJFrame, paramArrayList2);
/* 65 */     ((JSplitPane)localObject1).setRightComponent(paramArrayList1);
/*    */ 
/* 67 */     ((JSplitPane)localObject1).setDividerLocation(200);
/*    */ 
/* 70 */     (
/* 71 */       localObject1 = new JPanel())
/* 71 */       .setLayout(new FlowLayout(2));
/* 72 */     getContentPane().add((Component)localObject1, "South");
/*    */ 
/* 74 */     (
/* 75 */       localObject2 = new JButton("OK"))
/* 75 */       .setActionCommand("OK");
/* 76 */     ((JPanel)localObject1).add((Component)localObject2);
/* 77 */     getRootPane().setDefaultButton((JButton)localObject2);
/* 78 */     ((JButton)localObject2).addActionListener(new ow(this, paramol));
/*    */ 
/* 89 */     (
/* 90 */       localObject2 = new JButton("Cancel"))
/* 90 */       .setActionCommand("Cancel");
/* 91 */     ((JPanel)localObject1).add((Component)localObject2);
/* 92 */     ((JButton)localObject2).addActionListener(new ox(this));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ov
 * JD-Core Version:    0.6.2
 */