package org.schema.game.common.updater;

import class_1182;
import class_1195;
import class_1201;
import class_1209;
import class_1213;
import class_1215;
import class_1217;
import class_1219;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Launcher
  extends JFrame
{
  public static int field_2240;
  private static final long serialVersionUID = -2537463060839705206L;
  private JPanel field_2240;
  
  public static void main(String[] paramArrayOfString)
  {
    System.setProperty("http.agent", "");
    if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("-nogui")))
    {
      if (paramArrayOfString.length > 1) {
        paramArrayOfString[1].equals("-force");
      }
      class_1182.a1();
      return;
    }
    EventQueue.invokeLater(new class_1209());
  }
  
  public Launcher()
  {
    setTitle("StarMade [Launcher v" + jdField_field_2240_of_type_Int + "]");
    setDefaultCloseOperation(3);
    setBounds(100, 100, 849, 551);
    Object localObject1 = new JMenuBar();
    setJMenuBar((JMenuBar)localObject1);
    Object localObject2 = new JMenu("Options");
    ((JMenuBar)localObject1).add((JMenu)localObject2);
    (localObject1 = new JMenuItem("Memory Settings")).addActionListener(new class_1215());
    ((JMenu)localObject2).add((JMenuItem)localObject1);
    (localObject1 = new JMenuItem("Mirror Settings")).addActionListener(new class_1213(this));
    ((JMenu)localObject2).add((JMenuItem)localObject1);
    (localObject1 = new JMenuItem("Server Port")).addActionListener(new class_1219(this));
    ((JMenu)localObject2).add((JMenuItem)localObject1);
    this.jdField_field_2240_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_2240_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.jdField_field_2240_of_type_JavaxSwingJPanel);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 1.0D, 1.0D, 1.0D, 4.9E-324D };
    this.jdField_field_2240_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    (localObject1 = new JScrollPane()).setPreferredSize(new Dimension(400, 100));
    ((JScrollPane)localObject1).setMinimumSize(new Dimension(23, 30));
    (localObject2 = new GridBagConstraints()).fill = 1;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_2240_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject2 = new JTextPane()).setPreferredSize(new Dimension(300, 30));
    ((JScrollPane)localObject1).setViewportView((Component)localObject2);
    ((JTextPane)localObject2).setText("If the download gets stuck, launching the starter as Administrator helps in some cases. You can also download the latest Version manually from http://files.star-made.org/build/ and extract it to the directory where this launcher is located.\r\n\r\nIntel graphics cards are known to have buggy drivers in old versions, so be sure to update to the newest version.\r\n\r\nThere is a CrashAndBugReporter.jar in the StarMade directory if you want to send in a crash report manually.\r\nPlease use 64 bit java for maximal performance.\r\nIf you have any questions about the game, feel free to mail me at schema@star-made.org\r\n\r\nHave fun playing!");
    SwingUtilities.invokeLater(new class_1217((JScrollPane)localObject1));
    (localObject1 = new class_1195()).setMinimumSize(new Dimension(450, 200));
    (localObject2 = new GridBagConstraints()).weighty = 3.0D;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_2240_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    localObject1 = new class_1201();
    (localObject2 = new GridBagConstraints()).weighty = 1.0D;
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).weightx = 1.0D;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 2;
    this.jdField_field_2240_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
  }
  
  static
  {
    jdField_field_2240_of_type_Int = 9;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.updater.Launcher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */