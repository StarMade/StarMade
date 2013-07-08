/*  1:   */import java.awt.event.MouseAdapter;
/*  2:   */import java.awt.event.MouseEvent;
/*  3:   */import java.io.PrintStream;
/*  4:   */import javax.swing.JList;
/*  5:   */import javax.swing.JScrollPane;
/*  6:   */import javax.swing.JSplitPane;
/*  7:   */import javax.swing.ListModel;
/*  8:   */import org.schema.schine.network.objects.Sendable;
/*  9:   */
/* 54:   */final class qw
/* 55:   */  extends MouseAdapter
/* 56:   */{
/* 57:   */  qw(qv paramqv, JSplitPane paramJSplitPane) {}
/* 58:   */  
/* 59:   */  public final void mouseClicked(MouseEvent paramMouseEvent)
/* 60:   */  {
/* 61:61 */    if (qv.a(this.jdField_a_of_type_Qv).getSelectedIndex() >= 0) {
/* 62:62 */      paramMouseEvent = (Sendable)qv.a(this.jdField_a_of_type_Qv).getModel().getElementAt(qv.a(this.jdField_a_of_type_Qv).getSelectedIndex());
/* 63:   */      
/* 65:65 */      qy localqy = new qy(paramMouseEvent);
/* 66:   */      
/* 67:67 */      System.err.println("VALUE CHANGED: " + paramMouseEvent);
/* 68:   */      
/* 69:69 */      this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(localqy));
/* 70:70 */      org.schema.game.common.staremote.Staremote.a = localqy;
/* 71:   */    }
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qw
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */