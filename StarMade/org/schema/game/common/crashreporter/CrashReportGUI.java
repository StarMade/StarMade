package org.schema.game.common.crashreporter;

import class_704;
import class_706;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class CrashReportGUI
  extends JFrame
  implements Observer
{
  private static final long serialVersionUID = -4472460272445143389L;
  private JPanel jdField_field_1852_of_type_JavaxSwingJPanel;
  private JTextField jdField_field_1852_of_type_JavaxSwingJTextField;
  private JProgressBar jdField_field_1852_of_type_JavaxSwingJProgressBar;
  
  public static void main(String[] paramArrayOfString)
  {
    if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("-nogui")))
    {
      if (paramArrayOfString.length < 3)
      {
        System.out.println("You need at least 2 more arguments:\nExample:\njava -jar CrashAndBugReport.jar -nogui myemail@mymaildom.com description of the bug i had");
        System.exit(0);
        return;
      }
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 2; i < paramArrayOfString.length; i++) {
        localStringBuffer.append(paramArrayOfString[i] + " ");
      }
      CrashReporter localCrashReporter = new CrashReporter();
      try
      {
        localCrashReporter.a2(paramArrayOfString[1], localStringBuffer.toString());
        localCrashReporter.b();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    }
    EventQueue.invokeLater(new class_704());
  }
  
  public CrashReportGUI()
  {
    setTitle("Report Bug or Crash");
    setDefaultCloseOperation(3);
    setBounds(100, 100, 626, 413);
    this.jdField_field_1852_of_type_JavaxSwingJPanel = new JPanel();
    this.jdField_field_1852_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.jdField_field_1852_of_type_JavaxSwingJPanel);
    Object localObject1;
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 4.9E-324D };
    this.jdField_field_1852_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject1);
    (localObject1 = new JPanel()).setBorder(new TitledBorder(null, "Basic Information", 4, 2, null, null));
    (localObject2 = new GridBagConstraints()).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 0;
    this.jdField_field_1852_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 198, 112, 86, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 20, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 0.0D, 0.0D, 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
    Object localObject2 = new JLabel("Email (required)");
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).anchor = 18;
    localGridBagConstraints.insets = new Insets(0, 0, 0, 5);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
    this.jdField_field_1852_of_type_JavaxSwingJTextField = new JTextField();
    (localObject2 = new GridBagConstraints()).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).anchor = 18;
    ((GridBagConstraints)localObject2).gridx = 1;
    ((GridBagConstraints)localObject2).gridy = 0;
    ((JPanel)localObject1).add(this.jdField_field_1852_of_type_JavaxSwingJTextField, localObject2);
    this.jdField_field_1852_of_type_JavaxSwingJTextField.setPreferredSize(new Dimension(300, 20));
    this.jdField_field_1852_of_type_JavaxSwingJTextField.setColumns(10);
    (localObject1 = new JPanel()).setBorder(new TitledBorder(null, "Bug/Crash description", 4, 2, null, null));
    ((JPanel)localObject1).setPreferredSize(new Dimension(300, 300));
    (localObject2 = new GridBagConstraints()).weighty = 100.0D;
    ((GridBagConstraints)localObject2).gridheight = 8;
    ((GridBagConstraints)localObject2).gridwidth = 2;
    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject2).fill = 1;
    ((GridBagConstraints)localObject2).gridx = 0;
    ((GridBagConstraints)localObject2).gridy = 1;
    this.jdField_field_1852_of_type_JavaxSwingJPanel.add((Component)localObject1, localObject2);
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 93, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 19, 19, 19, 0, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 4.9E-324D };
    ((JPanel)localObject1).setLayout((LayoutManager)localObject2);
    localObject2 = new JLabel("Where did the game crash?");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 16;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
    localObject2 = new JLabel("What were you doing in the game when the Bug occurred?");
    (localGridBagConstraints = new GridBagConstraints()).anchor = 18;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 1;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
    localObject2 = new JLabel("Please describe the Problem:");
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.anchor = 16;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 2;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    ((JLabel)localObject2).setFont(new Font("Arial", 0, 16));
    (localObject2 = new JScrollPane()).setHorizontalScrollBarPolicy(31);
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 3;
    ((JPanel)localObject1).add((Component)localObject2, localGridBagConstraints);
    (localObject1 = new JTextArea()).setWrapStyleWord(true);
    ((JTextArea)localObject1).setLineWrap(true);
    ((JScrollPane)localObject2).setViewportView((Component)localObject1);
    (localObject2 = new JButton("Send Report and Logs")).addActionListener(new class_706(this, (JTextArea)localObject1));
    (localObject1 = new JTextPane()).setFont(new Font("Arial", 0, 10));
    ((JTextPane)localObject1).setEditable(false);
    ((JTextPane)localObject1).setText("All information you send will only be used to fix bugs and crashes in StarMade, and that purpose only. The information won't be saved permanently and will never be given to a third party.");
    (localGridBagConstraints = new GridBagConstraints()).gridwidth = 2;
    localGridBagConstraints.anchor = 15;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.fill = 2;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 9;
    this.jdField_field_1852_of_type_JavaxSwingJPanel.add((Component)localObject1, localGridBagConstraints);
    (localObject1 = new GridBagConstraints()).anchor = 17;
    ((GridBagConstraints)localObject1).insets = new Insets(0, 0, 0, 5);
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 10;
    this.jdField_field_1852_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject1);
    this.jdField_field_1852_of_type_JavaxSwingJProgressBar = new JProgressBar();
    this.jdField_field_1852_of_type_JavaxSwingJProgressBar.setStringPainted(true);
    (localObject1 = new GridBagConstraints()).weightx = 100.0D;
    ((GridBagConstraints)localObject1).fill = 1;
    ((GridBagConstraints)localObject1).gridx = 1;
    ((GridBagConstraints)localObject1).gridy = 10;
    this.jdField_field_1852_of_type_JavaxSwingJPanel.add(this.jdField_field_1852_of_type_JavaxSwingJProgressBar, localObject1);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject != null)
    {
      if ((paramObject instanceof Integer))
      {
        this.jdField_field_1852_of_type_JavaxSwingJProgressBar.setValue(((Integer)paramObject).intValue());
        return;
      }
      if (paramObject.toString().equals("FINISHED"))
      {
        paramObservable = "Thank You For Sending the Report!\n\nI (schema) will be automatically notified about this Report\nand I will try to fix your issue as soon as I can!\n\nThanks for playing StarMade!\n";
        Object[] arrayOfObject = { "OK" };
        String str = "Information";
        JFrame localJFrame;
        (localJFrame = new JFrame(str)).setUndecorated(true);
        localJFrame.setVisible(true);
        Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
        localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
        switch (JOptionPane.showOptionDialog(localJFrame, paramObservable, str, 0, 1, null, arrayOfObject, arrayOfObject[0]))
        {
        case 0: 
          System.err.println("Exiting because of exit info dialog");
          try
          {
            CrashReporter.a();
          }
          catch (IOException localIOException)
          {
            localIOException;
          }
          System.exit(0);
        }
        localJFrame.dispose();
      }
      this.jdField_field_1852_of_type_JavaxSwingJProgressBar.setString(paramObject.toString());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.crashreporter.CrashReportGUI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */