/*  1:   */import java.awt.event.ActionEvent;
/*  2:   */import java.awt.event.MouseEvent;
/*  3:   */import java.awt.event.MouseListener;
/*  4:   */import javax.swing.Action;
/*  5:   */import javax.swing.ActionMap;
/*  6:   */import javax.swing.InputMap;
/*  7:   */import javax.swing.JList;
/*  8:   */import javax.swing.KeyStroke;
/*  9:   */
/* 15:   */public final class c
/* 16:   */  implements MouseListener
/* 17:   */{
/* 18:18 */  private static final KeyStroke jdField_a_of_type_JavaxSwingKeyStroke = KeyStroke.getKeyStroke(10, 0);
/* 19:   */  
/* 21:   */  private JList jdField_a_of_type_JavaxSwingJList;
/* 22:   */  
/* 23:   */  private KeyStroke b;
/* 24:   */  
/* 26:   */  public c(JList paramJList, Action paramAction)
/* 27:   */  {
/* 28:28 */    this(paramJList, paramAction, jdField_a_of_type_JavaxSwingKeyStroke);
/* 29:   */  }
/* 30:   */  
/* 34:   */  private c(JList paramJList, Action paramAction, KeyStroke paramKeyStroke)
/* 35:   */  {
/* 36:36 */    this.jdField_a_of_type_JavaxSwingJList = paramJList;
/* 37:37 */    this.b = paramKeyStroke;
/* 38:   */    
/* 41:41 */    paramJList.getInputMap()
/* 42:42 */      .put(paramKeyStroke, paramKeyStroke);
/* 43:   */    
/* 46:46 */    paramKeyStroke = paramAction;paramAction = this;this.jdField_a_of_type_JavaxSwingJList.getActionMap().put(paramAction.b, paramKeyStroke);
/* 47:   */    
/* 50:50 */    paramJList.addMouseListener(this);
/* 51:   */  }
/* 52:   */  
/* 63:   */  public final void mouseClicked(MouseEvent paramMouseEvent)
/* 64:   */  {
/* 65:65 */    if (paramMouseEvent.getClickCount() == 2)
/* 66:   */    {
/* 69:69 */      if ((paramMouseEvent = this.jdField_a_of_type_JavaxSwingJList.getActionMap().get(this.b)) != null)
/* 70:   */      {
/* 71:71 */        ActionEvent localActionEvent = new ActionEvent(this.jdField_a_of_type_JavaxSwingJList, 1001, "");
/* 72:   */        
/* 75:75 */        paramMouseEvent.actionPerformed(localActionEvent);
/* 76:   */      }
/* 77:   */    }
/* 78:   */  }
/* 79:   */  
/* 80:   */  public final void mouseEntered(MouseEvent paramMouseEvent) {}
/* 81:   */  
/* 82:   */  public final void mouseExited(MouseEvent paramMouseEvent) {}
/* 83:   */  
/* 84:   */  public final void mousePressed(MouseEvent paramMouseEvent) {}
/* 85:   */  
/* 86:   */  public final void mouseReleased(MouseEvent paramMouseEvent) {}
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     c
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */