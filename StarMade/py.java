/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import java.io.File;
/*   4:    */
/* 139:    */final class py
/* 140:    */  implements ActionListener
/* 141:    */{
/* 142:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 143:    */  {
/* 144:    */    try
/* 145:    */    {
/* 146:146 */      new File(sE.a("StarMade"), "cred").delete(); return;
/* 147:    */    } catch (Exception localException) {
/* 148:148 */      (paramActionEvent = 
/* 149:    */      
/* 150:150 */        localException).printStackTrace();d.a(paramActionEvent);
/* 151:    */    }
/* 152:    */  }
/* 153:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     py
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */