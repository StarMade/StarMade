package org.schema.game.common.gui;

import class_1345;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConnectionDialog
  extends JDialog
  implements Observer
{
  private static final long serialVersionUID = 418123465094998578L;
  private final JPanel jdField_field_1738_of_type_JavaxSwingJPanel = new JPanel();
  private JLabel jdField_field_1738_of_type_JavaxSwingJLabel;
  private boolean field_1739;
  public boolean field_1738;
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      (paramArrayOfString = new ConnectionDialog()).setDefaultCloseOperation(2);
      paramArrayOfString.setVisible(true);
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  public ConnectionDialog()
  {
    this.jdField_field_1738_of_type_Boolean = true;
    setAlwaysOnTop(true);
    setDefaultCloseOperation(2);
    setTitle("StarMade Connector");
    setBounds(100, 100, 411, 105);
    getContentPane().setLayout(new BorderLayout());
    this.jdField_field_1738_of_type_JavaxSwingJPanel.setLayout(new FlowLayout());
    this.jdField_field_1738_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(this.jdField_field_1738_of_type_JavaxSwingJPanel, "Center");
    this.jdField_field_1738_of_type_JavaxSwingJLabel = new JLabel("Connecting");
    this.jdField_field_1738_of_type_JavaxSwingJPanel.add(this.jdField_field_1738_of_type_JavaxSwingJLabel);
    JPanel localJPanel;
    (localJPanel = new JPanel()).setLayout(new FlowLayout(2));
    getContentPane().add(localJPanel, "South");
    JButton localJButton;
    (localJButton = new JButton("Cancel")).addActionListener(new class_1345());
    localJButton.setActionCommand("Cancel");
    localJPanel.add(localJButton);
  }
  
  public void dispose()
  {
    super.dispose();
    if ((!this.field_1739) && (this.jdField_field_1738_of_type_Boolean))
    {
      System.err.println("Connection Dialog Terminated -> EXIT 0");
      System.exit(0);
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ("DONE".equals(paramObject))
    {
      this.field_1739 = true;
      dispose();
      return;
    }
    this.jdField_field_1738_of_type_JavaxSwingJLabel.setText(paramObject.toString());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.gui.ConnectionDialog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */