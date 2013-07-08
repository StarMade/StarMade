package org.hsqldb.util;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Hashtable;

class ConnectionDialog
  extends Dialog
  implements ActionListener, ItemListener
{
  protected Connection mConnection;
  protected TextField mName;
  protected TextField mDriver;
  protected TextField mURL;
  protected TextField mUser;
  protected TextField mPassword;
  protected Label mError;
  private String[][] connTypes;
  private Hashtable settings;
  private Choice types;
  private Choice recent;
  
  public static Connection createConnection(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception
  {
    Class.forName(paramString1).newInstance();
    return DriverManager.getConnection(paramString2, paramString3, paramString4);
  }
  
  ConnectionDialog(Frame paramFrame, String paramString)
  {
    super(paramFrame, paramString, true);
  }
  
  private void create()
  {
    Dimension localDimension1 = Toolkit.getDefaultToolkit().getScreenSize();
    setLayout(new BorderLayout());
    Panel localPanel1 = new Panel(new BorderLayout());
    Panel localPanel2;
    Panel localPanel3;
    Panel localPanel4;
    Panel localPanel5;
    if (localDimension1.width >= 640)
    {
      localPanel2 = new Panel(new GridLayout(8, 1, 10, 10));
      localPanel3 = new Panel(new GridLayout(8, 1, 10, 10));
      localPanel4 = new Panel(new GridLayout(1, 2, 10, 10));
      localPanel5 = new Panel(new GridLayout(8, 1, 10, 10));
    }
    else
    {
      localPanel2 = new Panel(new GridLayout(8, 1));
      localPanel3 = new Panel(new GridLayout(8, 1));
      localPanel4 = new Panel(new GridLayout(1, 2));
      localPanel5 = new Panel(new GridLayout(8, 1));
    }
    localPanel1.add("West", localPanel2);
    localPanel1.add("Center", localPanel3);
    localPanel1.add("South", localPanel4);
    localPanel1.add("North", createLabel(""));
    localPanel1.add("East", localPanel5);
    localPanel1.setBackground(SystemColor.control);
    localPanel3.setBackground(SystemColor.control);
    localPanel2.setBackground(SystemColor.control);
    localPanel4.setBackground(SystemColor.control);
    localPanel2.add(createLabel("Recent:"));
    this.recent = new Choice();
    try
    {
      this.settings = ConnectionDialogCommon.loadRecentConnectionSettings();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    this.recent.add(ConnectionDialogCommon.emptySettingName);
    Enumeration localEnumeration = this.settings.elements();
    while (localEnumeration.hasMoreElements()) {
      this.recent.add(((ConnectionSetting)localEnumeration.nextElement()).getName());
    }
    this.recent.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
      {
        String str = (String)paramAnonymousItemEvent.getItem();
        ConnectionSetting localConnectionSetting = (ConnectionSetting)ConnectionDialog.this.settings.get(str);
        if (localConnectionSetting != null)
        {
          ConnectionDialog.this.mName.setText(localConnectionSetting.getName());
          ConnectionDialog.this.mDriver.setText(localConnectionSetting.getDriver());
          ConnectionDialog.this.mURL.setText(localConnectionSetting.getUrl());
          ConnectionDialog.this.mUser.setText(localConnectionSetting.getUser());
          ConnectionDialog.this.mPassword.setText(localConnectionSetting.getPassword());
        }
      }
    });
    localPanel3.add(this.recent);
    Button localButton = new Button("Clr");
    localButton.setActionCommand("Clear");
    localButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ConnectionDialogCommon.deleteRecentConnectionSettings();
        ConnectionDialog.this.settings = new Hashtable();
        ConnectionDialog.this.recent.removeAll();
        ConnectionDialog.this.recent.add(ConnectionDialogCommon.emptySettingName);
        ConnectionDialog.this.mName.setText(null);
      }
    });
    localPanel5.add(localButton);
    localPanel2.add(createLabel("Setting Name:"));
    this.mName = new TextField("");
    localPanel3.add(this.mName);
    localPanel2.add(createLabel("Type:"));
    this.types = new Choice();
    this.connTypes = ConnectionDialogCommon.getTypes();
    for (int i = 0; i < this.connTypes.length; i++) {
      this.types.add(this.connTypes[i][0]);
    }
    this.types.addItemListener(this);
    localPanel3.add(this.types);
    localPanel2.add(createLabel("Driver:"));
    this.mDriver = new TextField(this.connTypes[0][1]);
    localPanel3.add(this.mDriver);
    localPanel2.add(createLabel("URL:"));
    this.mURL = new TextField(this.connTypes[0][2]);
    this.mURL.addActionListener(this);
    localPanel3.add(this.mURL);
    localPanel2.add(createLabel("User:"));
    this.mUser = new TextField("SA");
    this.mUser.addActionListener(this);
    localPanel3.add(this.mUser);
    localPanel2.add(createLabel("Password:"));
    this.mPassword = new TextField("");
    this.mPassword.addActionListener(this);
    this.mPassword.setEchoChar('*');
    localPanel3.add(this.mPassword);
    localButton = new Button("Ok");
    localButton.setActionCommand("ConnectOk");
    localButton.addActionListener(this);
    localPanel4.add(localButton);
    localButton = new Button("Cancel");
    localButton.setActionCommand("ConnectCancel");
    localButton.addActionListener(this);
    localPanel4.add(localButton);
    add("East", createLabel(""));
    add("West", createLabel(""));
    this.mError = new Label("");
    Panel localPanel6 = createBorderPanel(this.mError);
    add("South", localPanel6);
    add("North", createLabel(""));
    add("Center", localPanel1);
    doLayout();
    pack();
    Dimension localDimension2 = getSize();
    if (localDimension1.width >= 640)
    {
      setLocation((localDimension1.width - localDimension2.width) / 2, (localDimension1.height - localDimension2.height) / 2);
    }
    else
    {
      setLocation(0, 0);
      setSize(localDimension1);
    }
    show();
  }
  
  public static Connection createConnection(Frame paramFrame, String paramString)
  {
    ConnectionDialog localConnectionDialog = new ConnectionDialog(paramFrame, paramString);
    localConnectionDialog.create();
    return localConnectionDialog.mConnection;
  }
  
  protected static Label createLabel(String paramString)
  {
    Label localLabel = new Label(paramString);
    localLabel.setBackground(SystemColor.control);
    return localLabel;
  }
  
  protected static Panel createBorderPanel(Component paramComponent)
  {
    Panel localPanel = new Panel();
    localPanel.setBackground(SystemColor.control);
    localPanel.setLayout(new BorderLayout());
    localPanel.add("Center", paramComponent);
    localPanel.add("North", createLabel(""));
    localPanel.add("South", createLabel(""));
    localPanel.add("East", createLabel(""));
    localPanel.add("West", createLabel(""));
    localPanel.setBackground(SystemColor.control);
    return localPanel;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    String str = paramActionEvent.getActionCommand();
    if ((str.equals("ConnectOk")) || ((paramActionEvent.getSource() instanceof TextField))) {
      try
      {
        if (this.mURL.getText().indexOf('«') >= 0) {
          throw new Exception("please specify db path");
        }
        this.mConnection = createConnection(this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), this.mPassword.getText());
        if ((this.mName.getText() != null) && (this.mName.getText().trim().length() != 0))
        {
          ConnectionSetting localConnectionSetting = new ConnectionSetting(this.mName.getText(), this.mDriver.getText(), this.mURL.getText(), this.mUser.getText(), this.mPassword.getText());
          ConnectionDialogCommon.addToRecentConnectionSettings(this.settings, localConnectionSetting);
        }
        dispose();
      }
      catch (IOException localIOException)
      {
        dispose();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        this.mError.setText(localException.toString());
      }
    } else if (str.equals("ConnectCancel")) {
      dispose();
    }
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    String str = (String)paramItemEvent.getItem();
    for (int i = 0; i < this.connTypes.length; i++) {
      if (str.equals(this.connTypes[i][0]))
      {
        this.mDriver.setText(this.connTypes[i][1]);
        this.mURL.setText(this.connTypes[i][2]);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.ConnectionDialog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */