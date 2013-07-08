/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JFrame;
/*   4:    */import javax.swing.JList;
/*   5:    */import org.schema.game.common.staremote.Staremote;
/*   6:    */
/* 101:    */final class qg
/* 102:    */  implements ActionListener
/* 103:    */{
/* 104:    */  qg(JList paramJList, JFrame paramJFrame, Staremote paramStaremote) {}
/* 105:    */  
/* 106:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 107:    */  {
/* 108:108 */    if ((paramActionEvent = this.jdField_a_of_type_JavaxSwingJList.getSelectedValue()) != null) {
/* 109:109 */      paramActionEvent = (qe)paramActionEvent;
/* 110:110 */      this.jdField_a_of_type_JavaxSwingJFrame.dispose();
/* 111:111 */      this.jdField_a_of_type_OrgSchemaGameCommonStaremoteStaremote.a(paramActionEvent);
/* 112:    */    }
/* 113:    */  }
/* 114:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */