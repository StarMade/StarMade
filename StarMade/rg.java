/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.util.Date;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import org.schema.game.network.ReceivedPlayer;
/*     */ 
/*     */ public final class rg extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public rg(ReceivedPlayer paramReceivedPlayer)
/*     */   {
/*  19 */     (
/*  20 */       localObject = new GridBagLayout()).columnWidths = 
/*  20 */       new int[] { 0, 0, 0 };
/*  21 */     ((GridBagLayout)localObject).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
/*  22 */     ((GridBagLayout)localObject).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  23 */     ((GridBagLayout)localObject).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 4.9E-324D };
/*  24 */     setLayout((LayoutManager)localObject);
/*     */ 
/*  26 */     Object localObject = new JLabel("Name: ");
/*     */     GridBagConstraints localGridBagConstraints;
/*  27 */     (
/*  28 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/*  28 */       17;
/*  29 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  30 */     localGridBagConstraints.gridx = 0;
/*  31 */     localGridBagConstraints.gridy = 0;
/*  32 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  34 */     localObject = new JLabel(paramReceivedPlayer.name);
/*  35 */     (
/*  36 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/*  36 */       2;
/*  37 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/*  38 */     localGridBagConstraints.gridx = 1;
/*  39 */     localGridBagConstraints.gridy = 0;
/*  40 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  42 */     localObject = new JLabel("Last Login: ");
/*  43 */     (
/*  44 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/*  44 */       17;
/*  45 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  46 */     localGridBagConstraints.gridx = 0;
/*  47 */     localGridBagConstraints.gridy = 1;
/*  48 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  50 */     localObject = new JLabel(new Date(paramReceivedPlayer.lastLogin).toString());
/*  51 */     (
/*  52 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/*  52 */       2;
/*  53 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/*  54 */     localGridBagConstraints.gridx = 1;
/*  55 */     localGridBagConstraints.gridy = 1;
/*  56 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  58 */     localObject = new JLabel("Last Logout: ");
/*  59 */     (
/*  60 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/*  60 */       17;
/*  61 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  62 */     localGridBagConstraints.gridx = 0;
/*  63 */     localGridBagConstraints.gridy = 2;
/*  64 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  66 */     localObject = new JLabel(new Date(paramReceivedPlayer.lastLogout).toString());
/*  67 */     (
/*  68 */       localGridBagConstraints = new GridBagConstraints()).fill = 
/*  68 */       2;
/*  69 */     localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
/*  70 */     localGridBagConstraints.gridx = 1;
/*  71 */     localGridBagConstraints.gridy = 2;
/*  72 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  74 */     localObject = new JLabel("Used IP:");
/*  75 */     (
/*  76 */       localGridBagConstraints = new GridBagConstraints()).anchor = 
/*  76 */       17;
/*  77 */     localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  78 */     localGridBagConstraints.gridx = 0;
/*  79 */     localGridBagConstraints.gridy = 3;
/*  80 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  82 */     localObject = new JScrollPane();
/*  83 */     (
/*  84 */       localGridBagConstraints = new GridBagConstraints()).insets = 
/*  84 */       new Insets(0, 0, 5, 0);
/*  85 */     localGridBagConstraints.fill = 1;
/*  86 */     localGridBagConstraints.gridx = 1;
/*  87 */     localGridBagConstraints.gridy = 3;
/*  88 */     add((Component)localObject, localGridBagConstraints);
/*     */ 
/*  90 */     paramReceivedPlayer = new JList(paramReceivedPlayer.ips);
/*  91 */     ((JScrollPane)localObject).setViewportView(paramReceivedPlayer);
/*     */ 
/*  93 */     paramReceivedPlayer = new JLabel("Options:");
/*  94 */     (
/*  95 */       localObject = new GridBagConstraints()).insets = 
/*  95 */       new Insets(0, 0, 0, 5);
/*  96 */     ((GridBagConstraints)localObject).gridx = 0;
/*  97 */     ((GridBagConstraints)localObject).gridy = 4;
/*  98 */     add(paramReceivedPlayer, localObject);
/*     */ 
/* 100 */     paramReceivedPlayer = new JButton("Coming Soon");
/* 101 */     (
/* 102 */       localObject = new GridBagConstraints()).anchor = 
/* 102 */       17;
/* 103 */     ((GridBagConstraints)localObject).gridx = 1;
/* 104 */     ((GridBagConstraints)localObject).gridy = 4;
/* 105 */     add(paramReceivedPlayer, localObject);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rg
 * JD-Core Version:    0.6.2
 */