/*   1:    */import javax.swing.JList;
/*   2:    */import javax.swing.JScrollPane;
/*   3:    */import javax.swing.JSplitPane;
/*   4:    */import javax.swing.event.ListSelectionEvent;
/*   5:    */import javax.swing.event.ListSelectionListener;
/*   6:    */import org.schema.game.network.ReceivedPlayer;
/*   7:    */
/* 132:    */final class rq
/* 133:    */  implements ListSelectionListener
/* 134:    */{
/* 135:    */  rq(JList paramJList, JSplitPane paramJSplitPane) {}
/* 136:    */  
/* 137:    */  public final void valueChanged(ListSelectionEvent paramListSelectionEvent)
/* 138:    */  {
/* 139:139 */    if ((paramListSelectionEvent = this.jdField_a_of_type_JavaxSwingJList.getSelectedValue()) != null) {
/* 140:140 */      this.jdField_a_of_type_JavaxSwingJSplitPane.setRightComponent(new JScrollPane(new rg((ReceivedPlayer)paramListSelectionEvent)));
/* 141:    */    }
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */