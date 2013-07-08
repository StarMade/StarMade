/*   1:    */import java.awt.Component;
/*   2:    */import java.awt.GridBagConstraints;
/*   3:    */import java.awt.GridBagLayout;
/*   4:    */import java.awt.Insets;
/*   5:    */import java.awt.LayoutManager;
/*   6:    */import java.util.Date;
/*   7:    */import javax.swing.JButton;
/*   8:    */import javax.swing.JLabel;
/*   9:    */import javax.swing.JList;
/*  10:    */import javax.swing.JPanel;
/*  11:    */import javax.swing.JScrollPane;
/*  12:    */import org.schema.game.network.ReceivedPlayer;
/*  13:    */
/*  14:    */public final class rg extends JPanel
/*  15:    */{
/*  16:    */  private static final long serialVersionUID = 1L;
/*  17:    */  
/*  18:    */  public rg(ReceivedPlayer paramReceivedPlayer)
/*  19:    */  {
/*  20: 20 */    (localObject = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/*  21: 21 */    ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
/*  22: 22 */    ((GridBagLayout)localObject).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  23: 23 */    ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 4.9E-324D };
/*  24: 24 */    setLayout((LayoutManager)localObject);
/*  25:    */    
/*  26: 26 */    Object localObject = new JLabel("Name: ");
/*  27:    */    GridBagConstraints localGridBagConstraints;
/*  28: 28 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/*  29: 29 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  30: 30 */    localGridBagConstraints.gridx = 0;
/*  31: 31 */    localGridBagConstraints.gridy = 0;
/*  32: 32 */    add((Component)localObject, localGridBagConstraints);
/*  33:    */    
/*  34: 34 */    localObject = new JLabel(paramReceivedPlayer.name);
/*  35:    */    
/*  36: 36 */    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
/*  37: 37 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/*  38: 38 */    localGridBagConstraints.gridx = 1;
/*  39: 39 */    localGridBagConstraints.gridy = 0;
/*  40: 40 */    add((Component)localObject, localGridBagConstraints);
/*  41:    */    
/*  42: 42 */    localObject = new JLabel("Last Login: ");
/*  43:    */    
/*  44: 44 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/*  45: 45 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  46: 46 */    localGridBagConstraints.gridx = 0;
/*  47: 47 */    localGridBagConstraints.gridy = 1;
/*  48: 48 */    add((Component)localObject, localGridBagConstraints);
/*  49:    */    
/*  50: 50 */    localObject = new JLabel(new Date(paramReceivedPlayer.lastLogin).toString());
/*  51:    */    
/*  52: 52 */    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
/*  53: 53 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/*  54: 54 */    localGridBagConstraints.gridx = 1;
/*  55: 55 */    localGridBagConstraints.gridy = 1;
/*  56: 56 */    add((Component)localObject, localGridBagConstraints);
/*  57:    */    
/*  58: 58 */    localObject = new JLabel("Last Logout: ");
/*  59:    */    
/*  60: 60 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/*  61: 61 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  62: 62 */    localGridBagConstraints.gridx = 0;
/*  63: 63 */    localGridBagConstraints.gridy = 2;
/*  64: 64 */    add((Component)localObject, localGridBagConstraints);
/*  65:    */    
/*  66: 66 */    localObject = new JLabel(new Date(paramReceivedPlayer.lastLogout).toString());
/*  67:    */    
/*  68: 68 */    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
/*  69: 69 */    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/*  70: 70 */    localGridBagConstraints.gridx = 1;
/*  71: 71 */    localGridBagConstraints.gridy = 2;
/*  72: 72 */    add((Component)localObject, localGridBagConstraints);
/*  73:    */    
/*  74: 74 */    localObject = new JLabel("Used IP:");
/*  75:    */    
/*  76: 76 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/*  77: 77 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  78: 78 */    localGridBagConstraints.gridx = 0;
/*  79: 79 */    localGridBagConstraints.gridy = 3;
/*  80: 80 */    add((Component)localObject, localGridBagConstraints);
/*  81:    */    
/*  82: 82 */    localObject = new JScrollPane();
/*  83:    */    
/*  84: 84 */    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  85: 85 */    localGridBagConstraints.fill = 1;
/*  86: 86 */    localGridBagConstraints.gridx = 1;
/*  87: 87 */    localGridBagConstraints.gridy = 3;
/*  88: 88 */    add((Component)localObject, localGridBagConstraints);
/*  89:    */    
/*  90: 90 */    paramReceivedPlayer = new JList(paramReceivedPlayer.ips);
/*  91: 91 */    ((JScrollPane)localObject).setViewportView(paramReceivedPlayer);
/*  92:    */    
/*  93: 93 */    paramReceivedPlayer = new JLabel("Options:");
/*  94:    */    
/*  95: 95 */    (localObject = new GridBagConstraints()).insets = new Insets(0, 0, 0, 5);
/*  96: 96 */    ((GridBagConstraints)localObject).gridx = 0;
/*  97: 97 */    ((GridBagConstraints)localObject).gridy = 4;
/*  98: 98 */    add(paramReceivedPlayer, localObject);
/*  99:    */    
/* 100:100 */    paramReceivedPlayer = new JButton("Coming Soon");
/* 101:    */    
/* 102:102 */    (localObject = new GridBagConstraints()).anchor = 17;
/* 103:103 */    ((GridBagConstraints)localObject).gridx = 1;
/* 104:104 */    ((GridBagConstraints)localObject).gridy = 4;
/* 105:105 */    add(paramReceivedPlayer, localObject);
/* 106:    */  }
/* 107:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */