package org.schema.game.common.updater;

import class_1189;
import class_1191;
import class_1201;
import class_1270;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MemorySettings
  extends JDialog
{
  private static final long serialVersionUID = -8542005436420601006L;
  private final JPanel jdField_field_2134_of_type_JavaxSwingJPanel = new JPanel();
  private JTextField jdField_field_2134_of_type_JavaxSwingJTextField;
  private JTextField field_2135;
  private JTextField field_2136;
  private JTextField field_2137;
  private JTextField field_2138;
  private JTextField field_2139;
  
  public static boolean a()
  {
    return System.getProperty("os.arch").contains("64");
  }
  
  public static void a1()
  {
    File localFile = new File(class_1270.a1(), "settings.properties");
    Properties localProperties = new Properties();
    if (localFile.exists())
    {
      localObject = new FileInputStream(localFile);
      localProperties.load((InputStream)localObject);
      ((FileInputStream)localObject).close();
    }
    else
    {
      System.err.println("ERROR, FILE DOES NOT EXIST: " + localFile.getAbsolutePath());
      return;
    }
    class_1201.field_1411 = Integer.parseInt(localProperties.get("maxMemory").toString());
    class_1201.field_1412 = Integer.parseInt(localProperties.get("minMemory").toString());
    class_1201.field_1413 = Integer.parseInt(localProperties.get("earlyGenMemory").toString());
    class_1201.field_1414 = Integer.parseInt(localProperties.get("maxMemory32").toString());
    class_1201.field_1415 = Integer.parseInt(localProperties.get("minMemory32").toString());
    class_1201.field_1416 = Integer.parseInt(localProperties.get("earlyGenMemory32").toString());
    class_1201.field_1417 = Integer.parseInt(localProperties.get("serverMaxMemory").toString());
    class_1201.field_1418 = Integer.parseInt(localProperties.get("serverMinMemory").toString());
    class_1201.field_1419 = Integer.parseInt(localProperties.get("serverEarlyGenMemory").toString());
    class_1201.field_1420 = Integer.parseInt(localProperties.get("port").toString());
    localFile.createNewFile();
    Object localObject = new FileOutputStream(localFile);
    localProperties.store((OutputStream)localObject, "Properties for the StarMade Starter");
    ((FileOutputStream)localObject).flush();
    ((FileOutputStream)localObject).close();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      (paramArrayOfString = new MemorySettings()).setDefaultCloseOperation(2);
      paramArrayOfString.setVisible(true);
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  public static void b()
  {
    File localFile = new File(class_1270.a1(), "settings.properties");
    Properties localProperties;
    (localProperties = new Properties()).put("maxMemory", String.valueOf(class_1201.field_1411));
    localProperties.put("minMemory", String.valueOf(class_1201.field_1412));
    localProperties.put("earlyGenMemory", String.valueOf(class_1201.field_1413));
    localProperties.put("maxMemory32", String.valueOf(class_1201.field_1414));
    localProperties.put("minMemory32", String.valueOf(class_1201.field_1415));
    localProperties.put("earlyGenMemory32", String.valueOf(class_1201.field_1416));
    localProperties.put("serverMaxMemory", String.valueOf(class_1201.field_1417));
    localProperties.put("serverMinMemory", String.valueOf(class_1201.field_1418));
    localProperties.put("serverEarlyGenMemory", String.valueOf(class_1201.field_1419));
    localProperties.put("port", String.valueOf(class_1201.field_1420));
    localFile.createNewFile();
    FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
    localProperties.store(localFileOutputStream, "Properties for the StarMade Starter");
    localFileOutputStream.flush();
    localFileOutputStream.close();
    System.out.println("Memory Settings saved to: " + localFile.getAbsolutePath());
  }
  
  public MemorySettings()
  {
    setAlwaysOnTop(true);
    setTitle("Memory Settings");
    setBounds(100, 100, 387, 354);
    getContentPane().setLayout(new BorderLayout());
    (localObject1 = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add((Component)localObject1, "South");
    (localObject2 = new JButton("OK")).addActionListener(new class_1191(this));
    ((JButton)localObject2).setActionCommand("OK");
    ((JPanel)localObject1).add((Component)localObject2);
    getRootPane().setDefaultButton((JButton)localObject2);
    (localObject2 = new JButton("Cancel")).addActionListener(new class_1189(this));
    ((JButton)localObject2).setActionCommand("Cancel");
    ((JPanel)localObject1).add((Component)localObject2);
    Object localObject1 = new JTabbedPane(1);
    getContentPane().add((Component)localObject1, "North");
    ((JTabbedPane)localObject1).addTab("Client & SinglePlayer", null, this.jdField_field_2134_of_type_JavaxSwingJPanel, null);
    this.jdField_field_2134_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    (localObject2 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject2).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject2).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject2).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    this.jdField_field_2134_of_type_JavaxSwingJPanel.setLayout((LayoutManager)localObject2);
    Object localObject2 = new JLabel("Client & Single Player Memory Settings");
    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 0;
    this.jdField_field_2134_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
    (localObject2 = new JTextPane()).setText("Please keep in mind that 32 bit OS have a limit of allocating memory. Should 1024 throw out of memory exceptions, please try less then 1024");
    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).fill = 1;
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 1;
    this.jdField_field_2134_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
    (localObject2 = new JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Maximal Memory (MB)", 4, 2, null, null));
    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).fill = 1;
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 2;
    this.jdField_field_2134_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    this.jdField_field_2134_of_type_JavaxSwingJTextField = new JTextField();
    GridBagConstraints localGridBagConstraints;
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject2).add(this.jdField_field_2134_of_type_JavaxSwingJTextField, localGridBagConstraints);
    if (a()) {
      this.jdField_field_2134_of_type_JavaxSwingJTextField.setText(String.valueOf(class_1201.field_1411));
    } else {
      this.jdField_field_2134_of_type_JavaxSwingJTextField.setText(String.valueOf(class_1201.field_1414));
    }
    this.jdField_field_2134_of_type_JavaxSwingJTextField.setColumns(10);
    (localObject2 = new JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Initial Memory (MB)", 4, 2, null, null));
    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    ((GridBagConstraints)localObject3).fill = 1;
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 3;
    this.jdField_field_2134_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    this.field_2135 = new JTextField();
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject2).add(this.field_2135, localGridBagConstraints);
    if (a()) {
      this.field_2135.setText(String.valueOf(class_1201.field_1412));
    } else {
      this.field_2135.setText(String.valueOf(class_1201.field_1415));
    }
    this.field_2135.setColumns(10);
    (localObject2 = new JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Early Generation Memory", 4, 2, null, null));
    (localObject3 = new GridBagConstraints()).fill = 1;
    ((GridBagConstraints)localObject3).gridx = 0;
    ((GridBagConstraints)localObject3).gridy = 4;
    this.jdField_field_2134_of_type_JavaxSwingJPanel.add((Component)localObject2, localObject3);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    this.field_2136 = new JTextField();
    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject2).add(this.field_2136, localGridBagConstraints);
    if (a()) {
      this.field_2136.setText(String.valueOf(class_1201.field_1413));
    } else {
      this.field_2136.setText(String.valueOf(class_1201.field_1416));
    }
    this.field_2136.setColumns(10);
    (localObject2 = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
    ((JTabbedPane)localObject1).addTab("Dedicated Server", null, (Component)localObject2, null);
    (localObject3 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject3).rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
    ((GridBagLayout)localObject3).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject3).rowWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
    ((JPanel)localObject2).setLayout((LayoutManager)localObject3);
    Object localObject3 = new JLabel("Dedicated Server Memory Settings");
    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 0;
    ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
    (localObject3 = new JTextPane()).setText("Please keep in mind that 32 bit OS have a limit of allocating memory. Should 1024 throw out of memory exceptions, please try less then 1024");
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 1;
    ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
    (localObject3 = new JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Maximal Memory (MB)", 4, 2, null, null));
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 2;
    ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
    this.field_2137 = new JTextField();
    this.field_2137.setText(String.valueOf(class_1201.field_1417));
    this.field_2137.setColumns(10);
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 0;
    ((JPanel)localObject3).add(this.field_2137, localObject1);
    (localObject3 = new JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Initial Memory (MB)", 4, 2, null, null));
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.insets = new Insets(0, 0, 5, 0);
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 3;
    ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
    this.field_2138 = new JTextField();
    this.field_2138.setText(String.valueOf(class_1201.field_1418));
    this.field_2138.setColumns(10);
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 0;
    ((JPanel)localObject3).add(this.field_2138, localObject1);
    (localObject3 = new JPanel()).setBorder(new TitledBorder(new EtchedBorder(1, null, null), "Early Generation Memory", 4, 2, null, null));
    (localGridBagConstraints = new GridBagConstraints()).fill = 1;
    localGridBagConstraints.gridx = 0;
    localGridBagConstraints.gridy = 4;
    ((JPanel)localObject2).add((Component)localObject3, localGridBagConstraints);
    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0 };
    ((GridBagLayout)localObject1).columnWeights = new double[] { 1.0D, 4.9E-324D };
    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 4.9E-324D };
    ((JPanel)localObject3).setLayout((LayoutManager)localObject1);
    this.field_2139 = new JTextField();
    this.field_2139.setText(String.valueOf(class_1201.field_1419));
    this.field_2139.setColumns(10);
    (localObject1 = new GridBagConstraints()).fill = 2;
    ((GridBagConstraints)localObject1).gridx = 0;
    ((GridBagConstraints)localObject1).gridy = 0;
    ((JPanel)localObject3).add(this.field_2139, localObject1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.updater.MemorySettings
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */