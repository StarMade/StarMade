/*  1:   */import java.awt.BorderLayout;
/*  2:   */import java.util.ArrayList;
/*  3:   */import javax.swing.JPanel;
/*  4:   */import javax.swing.JScrollPane;
/*  5:   */import javax.swing.JTextArea;
/*  6:   */import javax.swing.text.DefaultCaret;
/*  7:   */
/* 12:   */public class qr
/* 13:   */  extends JPanel
/* 14:   */  implements cr
/* 15:   */{
/* 16:   */  private JTextArea jdField_a_of_type_JavaxSwingJTextArea;
/* 17:   */  
/* 18:   */  public qr(ct paramct)
/* 19:   */  {
/* 20:20 */    setLayout(new BorderLayout(0, 0));
/* 21:   */    
/* 23:23 */    JScrollPane localJScrollPane = new JScrollPane();
/* 24:24 */    add(localJScrollPane, "Center");
/* 25:   */    
/* 26:26 */    this.jdField_a_of_type_JavaxSwingJTextArea = new JTextArea();
/* 27:27 */    ((DefaultCaret)this.jdField_a_of_type_JavaxSwingJTextArea.getCaret())
/* 28:28 */      .setUpdatePolicy(2);
/* 29:29 */    localJScrollPane.setViewportView(this.jdField_a_of_type_JavaxSwingJTextArea);
/* 30:30 */    localJScrollPane.setAutoscrolls(true);
/* 31:31 */    if ((!jdField_a_of_type_Boolean) && (paramct == null)) throw new AssertionError();
/* 32:32 */    paramct.e().add(this);
/* 33:   */  }
/* 34:   */  
/* 36:   */  public final void a(String paramString)
/* 37:   */  {
/* 38:38 */    this.jdField_a_of_type_JavaxSwingJTextArea.append(paramString + "\n");
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */