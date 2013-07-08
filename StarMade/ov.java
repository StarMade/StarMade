/*  1:   */import java.awt.BorderLayout;
/*  2:   */import java.awt.Component;
/*  3:   */import java.awt.Container;
/*  4:   */import java.awt.FlowLayout;
/*  5:   */import java.awt.GridBagConstraints;
/*  6:   */import java.awt.GridBagLayout;
/*  7:   */import java.awt.LayoutManager;
/*  8:   */import java.util.ArrayList;
/*  9:   */import javax.swing.JButton;
/* 10:   */import javax.swing.JDialog;
/* 11:   */import javax.swing.JFrame;
/* 12:   */import javax.swing.JPanel;
/* 13:   */import javax.swing.JRootPane;
/* 14:   */import javax.swing.JSplitPane;
/* 15:   */import javax.swing.border.EmptyBorder;
/* 16:   */
/* 23:   */public final class ov
/* 24:   */  extends JDialog
/* 25:   */{
/* 26:   */  private static final long serialVersionUID = -3693336102651064550L;
/* 27:27 */  private final JPanel a = new JPanel();
/* 28:   */  
/* 34:   */  public ov(JFrame paramJFrame, ArrayList paramArrayList1, ArrayList paramArrayList2, ol paramol)
/* 35:   */  {
/* 36:36 */    super(paramJFrame, true);
/* 37:37 */    setBounds(100, 100, 450, 435);
/* 38:38 */    getContentPane().setLayout(new BorderLayout());
/* 39:39 */    this.a.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 40:40 */    getContentPane().add(this.a, "Center");
/* 41:   */    Object localObject1;
/* 42:42 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 125 };
/* 43:43 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 25, 0 };
/* 44:44 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D };
/* 45:45 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
/* 46:46 */    this.a.setLayout((LayoutManager)localObject1);
/* 47:   */    
/* 48:48 */    (
/* 49:49 */      localObject1 = new JSplitPane()).setOrientation(0);
/* 50:   */    
/* 51:   */    Object localObject2;
/* 52:52 */    (localObject2 = new GridBagConstraints()).weighty = 1.0D;
/* 53:53 */    ((GridBagConstraints)localObject2).weightx = 1.0D;
/* 54:54 */    ((GridBagConstraints)localObject2).fill = 1;
/* 55:55 */    ((GridBagConstraints)localObject2).anchor = 18;
/* 56:56 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 57:57 */    ((GridBagConstraints)localObject2).gridy = 0;
/* 58:58 */    this.a.add((Component)localObject1, localObject2);
/* 59:   */    
/* 60:60 */    paramArrayList1 = new om(paramJFrame, paramArrayList1);
/* 61:61 */    ((JSplitPane)localObject1).setLeftComponent(paramArrayList1);
/* 62:   */    
/* 64:64 */    paramArrayList1 = new om(paramJFrame, paramArrayList2);
/* 65:65 */    ((JSplitPane)localObject1).setRightComponent(paramArrayList1);
/* 66:   */    
/* 67:67 */    ((JSplitPane)localObject1).setDividerLocation(200);
/* 68:   */    
/* 70:70 */    (
/* 71:71 */      localObject1 = new JPanel()).setLayout(new FlowLayout(2));
/* 72:72 */    getContentPane().add((Component)localObject1, "South");
/* 73:   */    
/* 74:74 */    (
/* 75:75 */      localObject2 = new JButton("OK")).setActionCommand("OK");
/* 76:76 */    ((JPanel)localObject1).add((Component)localObject2);
/* 77:77 */    getRootPane().setDefaultButton((JButton)localObject2);
/* 78:78 */    ((JButton)localObject2).addActionListener(new ow(this, paramol));
/* 79:   */    
/* 89:89 */    (
/* 90:90 */      localObject2 = new JButton("Cancel")).setActionCommand("Cancel");
/* 91:91 */    ((JPanel)localObject1).add((Component)localObject2);
/* 92:92 */    ((JButton)localObject2).addActionListener(new ox(this));
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ov
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */