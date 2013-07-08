/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import java.io.IOException;
/*   4:    */import javax.swing.JOptionPane;
/*   5:    */import org.schema.game.common.updater.Launcher;
/*   6:    */import org.schema.game.common.updater.MemorySettings;
/*   7:    */
/* 118:    */public final class si
/* 119:    */  implements ActionListener
/* 120:    */{
/* 121:    */  public si(Launcher paramLauncher) {}
/* 122:    */  
/* 123:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 124:    */  {
/* 125:125 */    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.a, "Please enter a port to start the server on", "Port", -1, null, null, String.valueOf(sr.j))) != null) && (paramActionEvent.length() > 0)) {
/* 126:    */      try
/* 127:    */      {
/* 128:128 */        paramActionEvent = 0;sr.j = Integer.parseInt(paramActionEvent);
/* 129:    */        
/* 130:130 */        JOptionPane.showMessageDialog(this.a, "Saving Setting: Port set to " + sr.j + ".", "Port", 1);
/* 131:    */        
/* 134:134 */        MemorySettings.b(); return;
/* 135:    */      }
/* 136:    */      catch (NumberFormatException localNumberFormatException) {
/* 137:137 */        JOptionPane.showMessageDialog(this.a, "Port must be a number", "Port set error", 0); return;
/* 138:    */      }
/* 139:    */      catch (IOException localIOException) {
/* 140:140 */        paramActionEvent = null;
/* 141:    */        
/* 143:143 */        localIOException.printStackTrace();
/* 144:    */      }
/* 145:    */    }
/* 146:    */  }
/* 147:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     si
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */