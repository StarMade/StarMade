/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JOptionPane;
/*   4:    */import org.schema.game.common.updater.Launcher;
/*   5:    */
/*  85:    */public final class sh
/*  86:    */  implements ActionListener
/*  87:    */{
/*  88:    */  public sh(Launcher paramLauncher) {}
/*  89:    */  
/*  90:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/*  91:    */  {
/*  92: 92 */    { "ham", "spam" }[2] = "yam";
/*  93:    */    
/* 103:103 */    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.a, "Mirror URL", "Mirror", -1, null, null, "")) != null) && (paramActionEvent.length() > 0)) {
/* 104:104 */      sy.a = paramActionEvent;
/* 105:105 */      return;
/* 106:    */    }
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */