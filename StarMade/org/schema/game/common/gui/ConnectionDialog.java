/*  1:   */package org.schema.game.common.gui;
/*  2:   */
/*  3:   */import java.awt.BorderLayout;
/*  4:   */import java.awt.Container;
/*  5:   */import java.awt.FlowLayout;
/*  6:   */import java.io.PrintStream;
/*  7:   */import java.util.Observable;
/*  8:   */import java.util.Observer;
/*  9:   */import javax.swing.JButton;
/* 10:   */import javax.swing.JDialog;
/* 11:   */import javax.swing.JLabel;
/* 12:   */import javax.swing.JPanel;
/* 13:   */import javax.swing.border.EmptyBorder;
/* 14:   */import pq;
/* 15:   */
/* 18:   */public class ConnectionDialog
/* 19:   */  extends JDialog
/* 20:   */  implements Observer
/* 21:   */{
/* 22:   */  private static final long serialVersionUID = 418123465094998578L;
/* 23:   */  
/* 24:   */  public static void main(String[] paramArrayOfString)
/* 25:   */  {
/* 26:   */    try
/* 27:   */    {
/* 28:28 */      (paramArrayOfString = new ConnectionDialog()).setDefaultCloseOperation(2);
/* 29:29 */      paramArrayOfString.setVisible(true); return;
/* 30:30 */    } catch (Exception localException) { 
/* 31:   */      
/* 32:32 */        localException;
/* 33:   */    }
/* 34:   */  }
/* 35:   */  
/* 36:34 */  private final JPanel jdField_a_of_type_JavaxSwingJPanel = new JPanel();
/* 37:   */  private JLabel jdField_a_of_type_JavaxSwingJLabel;
/* 38:   */  
/* 39:37 */  public ConnectionDialog() { this.jdField_a_of_type_Boolean = true;
/* 40:   */    
/* 45:43 */    setAlwaysOnTop(true);
/* 46:44 */    setDefaultCloseOperation(2);
/* 47:45 */    setTitle("StarMade Connector");
/* 48:46 */    setBounds(100, 100, 411, 105);
/* 49:47 */    getContentPane().setLayout(new BorderLayout());
/* 50:48 */    this.jdField_a_of_type_JavaxSwingJPanel.setLayout(new FlowLayout());
/* 51:49 */    this.jdField_a_of_type_JavaxSwingJPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/* 52:50 */    getContentPane().add(this.jdField_a_of_type_JavaxSwingJPanel, "Center");
/* 53:   */    
/* 54:52 */    this.jdField_a_of_type_JavaxSwingJLabel = new JLabel("Connecting");
/* 55:53 */    this.jdField_a_of_type_JavaxSwingJPanel.add(this.jdField_a_of_type_JavaxSwingJLabel);
/* 56:   */    
/* 57:   */    JPanel localJPanel;
/* 58:   */    
/* 59:57 */    (localJPanel = new JPanel()).setLayout(new FlowLayout(2));
/* 60:58 */    getContentPane().add(localJPanel, "South");
/* 61:   */    
/* 62:   */    JButton localJButton;
/* 63:61 */    (localJButton = new JButton("Cancel")).addActionListener(new pq());
/* 64:   */    
/* 70:68 */    localJButton.setActionCommand("Cancel");
/* 71:69 */    localJPanel.add(localJButton);
/* 72:   */  }
/* 73:   */  
/* 75:   */  private boolean b;
/* 76:   */  
/* 77:   */  public boolean a;
/* 78:   */  
/* 79:   */  public void dispose()
/* 80:   */  {
/* 81:79 */    super.dispose();
/* 82:80 */    if ((!this.b) && (this.jdField_a_of_type_Boolean)) {
/* 83:81 */      System.err.println("Connection Dialog Terminated -> EXIT 0");
/* 84:82 */      System.exit(0);
/* 85:   */    }
/* 86:   */  }
/* 87:   */  
/* 89:   */  public void update(Observable paramObservable, Object paramObject)
/* 90:   */  {
/* 91:89 */    if ("DONE".equals(paramObject)) {
/* 92:90 */      this.b = true;
/* 93:91 */      dispose();return;
/* 94:   */    }
/* 95:93 */    this.jdField_a_of_type_JavaxSwingJLabel.setText(paramObject.toString());
/* 96:   */  }
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.gui.ConnectionDialog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */