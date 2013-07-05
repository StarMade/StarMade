package org.hsqldb.util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

class ConnectionDialogSwing extends JDialog
  implements ActionListener, ItemListener
{
  private static final long serialVersionUID = 1L;
  private Connection mConnection;
  private JTextField mName;
  private JTextField mDriver;
  private JTextField mURL;
  private JTextField mUser;
  private JPasswordField mPassword;
  private String[][] connTypes;
  private Hashtable settings;
  private JButton okCancel;
  private JButton clear;
  private JComboBox mSettingName = new JComboBox(loadRecentConnectionSettings());
  private static ConnectionSetting currentConnectionSetting = null;

  public static void setConnectionSetting(ConnectionSetting paramConnectionSetting)
  {
    currentConnectionSetting = paramConnectionSetting;
  }

  public static Connection createConnection(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception
  {
    Class.forName(paramString1).newInstance();
    return DriverManager.getConnection(paramString2, paramString3, paramString4);
  }

  ConnectionDialogSwing(JFrame paramJFrame, String paramString)
  {
    super(paramJFrame, paramString, true);
  }

  private void create()
  {
    Box localBox1 = Box.createHorizontalBox();
    Box localBox2 = Box.createVerticalBox();
    Box localBox3 = Box.createVerticalBox();
    Box localBox4 = Box.createHorizontalBox();
    Box localBox5 = Box.createVerticalBox();
    Box localBox6 = Box.createHorizontalBox();
    localBox1.add(Box.createHorizontalStrut(10));
    localBox1.add(Box.createHorizontalGlue());
    localBox1.add(localBox2);
    localBox1.add(Box.createHorizontalStrut(10));
    localBox1.add(Box.createHorizontalGlue());
    localBox1.add(localBox3);
    localBox1.add(Box.createHorizontalStrut(10));
    localBox1.add(Box.createVerticalGlue());
    localBox1.add(localBox6);
    localBox1.add(Box.createVerticalGlue());
    localBox5.add(Box.createVerticalGlue());
    localBox5.add(Box.createVerticalStrut(10));
    localBox5.add(localBox1);
    localBox5.add(Box.createVerticalGlue());
    localBox5.add(Box.createVerticalStrut(10));
    localBox5.add(localBox4);
    localBox5.add(Box.createVerticalGlue());
    localBox5.add(Box.createVerticalStrut(10));
    localBox5.add(Box.createVerticalGlue());
    localBox2.add(createLabel("Recent Setting:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(createLabel("Setting Name:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(createLabel("Type:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(createLabel("Driver:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(createLabel("URL:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(createLabel("User:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(createLabel("Password:"));
    localBox2.add(Box.createVerticalGlue());
    localBox2.add(Box.createVerticalStrut(10));
    localBox3.add(Box.createVerticalGlue());
    this.mSettingName.setActionCommand("Select Setting");
    this.mSettingName.addActionListener(this);
    localBox3.add(this.mSettingName);
    localBox3.add(Box.createHorizontalGlue());
    this.mName = new JTextField();
    this.mName.addActionListener(this);
    localBox3.add(this.mName);
    this.clear = new JButton("Clear Names");
    this.clear.setActionCommand("Clear");
    this.clear.addActionListener(this);
    localBox4.add(this.clear);
    localBox4.add(Box.createHorizontalGlue());
    localBox4.add(Box.createHorizontalStrut(10));
    JComboBox localJComboBox = new JComboBox();
    this.connTypes = ConnectionDialogCommon.getTypes();
    for (int i = 0; i < this.connTypes.length; i++)
      localJComboBox.addItem(this.connTypes[i][0]);
    localJComboBox.addItemListener(this);
    localBox3.add(localJComboBox);
    localBox3.add(Box.createVerticalGlue());
    this.mDriver = new JTextField(this.connTypes[0][1]);
    this.mDriver.addActionListener(this);
    localBox3.add(this.mDriver);
    this.mURL = new JTextField(this.connTypes[0][2]);
    this.mURL.addActionListener(this);
    localBox3.add(this.mURL);
    localBox3.add(Box.createVerticalGlue());
    this.mUser = new JTextField("SA");
    this.mUser.addActionListener(this);
    localBox3.add(this.mUser);
    localBox3.add(Box.createVerticalGlue());
    this.mPassword = new JPasswordField("");
    this.mPassword.addActionListener(this);
    localBox3.add(this.mPassword);
    localBox3.add(Box.createVerticalGlue());
    localBox3.add(Box.createVerticalStrut(10));
    localBox4.add(Box.createHorizontalGlue());
    localBox4.add(Box.createHorizontalStrut(10));
    this.okCancel = new JButton("     Ok      ");
    this.okCancel.setActionCommand("ConnectOk");
    this.okCancel.addActionListener(this);
    localBox4.add(this.okCancel);
    getRootPane().setDefaultButton(this.okCancel);
    localBox4.add(Box.createHorizontalGlue());
    localBox4.add(Box.createHorizontalStrut(20));
    this.okCancel = new JButton("  Cancel   ");
    this.okCancel.setActionCommand("ConnectCancel");
    this.okCancel.addActionListener(this);
    localBox4.add(this.okCancel);
    localBox4.add(Box.createHorizontalGlue());
    localBox4.add(Box.createHorizontalStrut(10));
    JPanel localJPanel = new JPanel();
    localJPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    localJPanel.add("Center", localBox5);
    getContentPane().add("Center", localJPanel);
    doLayout();
    pack();
    Dimension localDimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension localDimension2 = getSize();
    if (currentConnectionSetting != null)
    {
      this.mName.setText(currentConnectionSetting.getName());
      this.mDriver.setText(currentConnectionSetting.getDriver());
      this.mURL.setText(currentConnectionSetting.getUrl());
      this.mUser.setText(currentConnectionSetting.getUser());
      this.mPassword.setText(currentConnectionSetting.getPassword());
    }
    if (localDimension1.width >= 640)
    {
      setLocation((localDimension1.width - localDimension2.width) / 2, (localDimension1.height - localDimension2.height) / 2);
    }
    else
    {
      setLocation(0, 0);
      setSize(localDimension1);
    }
    setVisible(true);
  }

  public static Connection createConnection(JFrame paramJFrame, String paramString)
  {
    ConnectionDialogSwing localConnectionDialogSwing = new ConnectionDialogSwing(paramJFrame, paramString);
    try
    {
      SwingUtilities.updateComponentTreeUI(localConnectionDialogSwing);
    }
    catch (Exception localException)
    {
      CommonSwing.errorMessage(localException);
    }
    localConnectionDialogSwing.create();
    return localConnectionDialogSwing.mConnection;
  }

  private static JLabel createLabel(String paramString)
  {
    JLabel localJLabel = new JLabel(paramString);
    return localJLabel;
  }

  public Vector loadRecentConnectionSettings()
  {
    Vector localVector = new Vector();
    this.settings = new Hashtable();
    try
    {
      this.settings = ConnectionDialogCommon.loadRecentConnectionSettings();
      Iterator localIterator = this.settings.values().iterator();
      localVector.add(ConnectionDialogCommon.emptySettingName);
      while (localIterator.hasNext())
        localVector.add(((ConnectionSetting)localIterator.next()).getName());
    }
    catch (IOException localIOException)
    {
      CommonSwing.errorMessage(localIOException);
    }
    return localVector;
  }

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    String str1 = paramActionEvent.getActionCommand();
    if ((str1.equals("ConnectOk")) || ((paramActionEvent.getSource() instanceof JTextField)))
    {
      try
      {
        if (this.mURL.getText().indexOf(171) >= 0)
          throw new Exception("please specify db path");
        this.mConnection = createConnection(this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), new String(this.mPassword.getPassword()));
        if ((this.mName.getText() != null) && (this.mName.getText().trim().length() != 0))
        {
          ConnectionSetting localConnectionSetting1 = new ConnectionSetting(this.mName.getText(), this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), new String(this.mPassword.getPassword()));
          ConnectionDialogCommon.addToRecentConnectionSettings(this.settings, localConnectionSetting1);
        }
        dispose();
      }
      catch (SQLException localSQLException)
      {
        this.mConnection = null;
        CommonSwing.errorMessage(localSQLException, true);
      }
      catch (Exception localException)
      {
        CommonSwing.errorMessage(localException);
      }
    }
    else if (str1.equals("Select Setting"))
    {
      String str2 = (String)this.mSettingName.getSelectedItem();
      ConnectionSetting localConnectionSetting2 = (ConnectionSetting)this.settings.get(str2);
      if (localConnectionSetting2 != null)
      {
        this.mName.setText(localConnectionSetting2.getName());
        this.mDriver.setText(localConnectionSetting2.getDriver());
        this.mURL.setText(localConnectionSetting2.getUrl());
        this.mUser.setText(localConnectionSetting2.getUser());
        this.mPassword.setText(localConnectionSetting2.getPassword());
      }
    }
    else if (str1.equals("ConnectCancel"))
    {
      dispose();
    }
    else if (str1.equals("Clear"))
    {
      ConnectionDialogCommon.deleteRecentConnectionSettings();
      this.settings = new Hashtable();
      this.mSettingName.removeAllItems();
      this.mSettingName.addItem(ConnectionDialogCommon.emptySettingName);
      this.mName.setText(null);
    }
  }

  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    String str = (String)paramItemEvent.getItem();
    for (int i = 0; i < this.connTypes.length; i++)
      if (str.equals(this.connTypes[i][0]))
      {
        this.mDriver.setText(this.connTypes[i][1]);
        this.mURL.setText(this.connTypes[i][2]);
      }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.ConnectionDialogSwing
 * JD-Core Version:    0.6.2
 */