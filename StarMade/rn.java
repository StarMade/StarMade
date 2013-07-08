/*  1:   */import java.awt.event.MouseAdapter;
/*  2:   */import java.awt.event.MouseEvent;
/*  3:   */import java.io.PrintStream;
/*  4:   */import javax.swing.JList;
/*  5:   */import javax.swing.JScrollPane;
/*  6:   */import javax.swing.JSplitPane;
/*  7:   */import javax.swing.ListModel;
/*  8:   */
/* 65:   */final class rn
/* 66:   */  extends MouseAdapter
/* 67:   */{
/* 68:   */  rn(rm paramrm, JSplitPane paramJSplitPane) {}
/* 69:   */  
/* 70:   */  public final void mouseClicked(MouseEvent paramMouseEvent)
/* 71:   */  {
/* 72:72 */    if (rm.a(this.jdField_a_of_type_Rm).getSelectedIndex() >= 0) {
/* 73:73 */      paramMouseEvent = (lE)rm.a(this.jdField_a_of_type_Rm).getModel().getElementAt(rm.a(this.jdField_a_of_type_Rm).getSelectedIndex());
/* 74:   */      
/* 76:76 */      rr localrr = new rr(paramMouseEvent);
/* 77:   */      
/* 78:78 */      System.err.println("VALUE CHANGED: " + paramMouseEvent);
/* 79:   */      
/* 80:80 */      this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(localrr));
/* 81:81 */      org.schema.game.common.staremote.Staremote.a = localrr;
/* 82:   */    }
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */