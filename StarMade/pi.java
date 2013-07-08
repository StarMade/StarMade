/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JCheckBoxMenuItem;
/*   4:    */import javax.swing.JScrollPane;
/*   5:    */import javax.swing.border.TitledBorder;
/*   6:    */
/* 504:    */final class pi
/* 505:    */  implements ActionListener
/* 506:    */{
/* 507:    */  pi(oU paramoU, JCheckBoxMenuItem paramJCheckBoxMenuItem) {}
/* 508:    */  
/* 509:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 510:    */  {
/* 511:511 */    if (this.jdField_a_of_type_JavaxSwingJCheckBoxMenuItem.isSelected()) {
/* 512:512 */      paramActionEvent = new pn();
/* 513:513 */      oU.a(this.jdField_a_of_type_OU).setViewportView(paramActionEvent);
/* 514:514 */      paramActionEvent.setBorder(new TitledBorder(null, "Settings", 4, 2, null, null));
/* 515:515 */      return; }
/* 516:516 */    paramActionEvent = new po();
/* 517:517 */    oU.a(this.jdField_a_of_type_OU).setViewportView(paramActionEvent);
/* 518:518 */    paramActionEvent.setBorder(new TitledBorder(null, "Settings", 4, 2, null, null));
/* 519:    */  }
/* 520:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */