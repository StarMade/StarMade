/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import java.io.IOException;
/*   4:    */import javax.swing.JOptionPane;
/*   5:    */
/* 546:    */final class pk
/* 547:    */  implements ActionListener
/* 548:    */{
/* 549:    */  pk(oU paramoU) {}
/* 550:    */  
/* 551:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 552:    */  {
/* 553:    */    try
/* 554:    */    {
/* 555:555 */      if (JOptionPane.showOptionDialog(this.a, "Database Migration from v0.078 to v0.079+.\nThis updates only the current universe.\nOld catalog data will automatically update, when you import!\nBe sure to have a Backup!\n(All you data got backed up on Version update)", "Migration", 0, 1, null, new String[] { "Ok", "Cancel" }, "Ok") == 1) {
/* 556:556 */        return;
/* 557:    */      }
/* 558:558 */      JOptionPane.showMessageDialog(this.a, "Database Migration in Progress.\nThis may take a few minutes.\nPlease do not interrupt this progress!");
/* 559:    */      
/* 563:563 */      tH.a.a();
/* 564:564 */      JOptionPane.showOptionDialog(this.a, "Database Migration was successfull.\nYou can now play the game. Have fun!", "Migration completed", 0, 1, null, new String[] { "Ok" }, "Ok"); return;
/* 567:    */    }
/* 568:    */    catch (IOException localIOException)
/* 569:    */    {
/* 571:571 */      (paramActionEvent = 
/* 572:    */      
/* 573:573 */        localIOException).printStackTrace();d.a(paramActionEvent);
/* 574:    */    }
/* 575:    */  }
/* 576:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */