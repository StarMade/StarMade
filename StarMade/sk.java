/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JOptionPane;
/*   4:    */import javax.swing.JPanel;
/*   5:    */import javax.swing.JTextField;
/*   6:    */import org.schema.game.common.updater.MemorySettings;
/*   7:    */
/* 135:    */public final class sk
/* 136:    */  implements ActionListener
/* 137:    */{
/* 138:    */  public sk(MemorySettings paramMemorySettings) {}
/* 139:    */  
/* 140:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 141:    */  {
/* 142:    */    try
/* 143:    */    {
/* 144:144 */      if (MemorySettings.a()) {
/* 145:145 */        sr.a = Integer.parseInt(MemorySettings.a(this.a).getText());
/* 146:146 */        sr.b = Integer.parseInt(MemorySettings.b(this.a).getText());
/* 147:147 */        sr.c = Integer.parseInt(MemorySettings.c(this.a).getText());
/* 148:    */      } else {
/* 149:149 */        sr.d = Integer.parseInt(MemorySettings.a(this.a).getText());
/* 150:150 */        sr.e = Integer.parseInt(MemorySettings.b(this.a).getText());
/* 151:151 */        sr.f = Integer.parseInt(MemorySettings.c(this.a).getText());
/* 152:    */      }
/* 153:    */      
/* 154:154 */      sr.g = Integer.parseInt(MemorySettings.d(this.a).getText());
/* 155:155 */      sr.h = Integer.parseInt(MemorySettings.e(this.a).getText());
/* 156:156 */      sr.i = Integer.parseInt(MemorySettings.f(this.a).getText());
/* 157:    */      try
/* 158:    */      {
/* 159:159 */        MemorySettings.b();
/* 160:160 */      } catch (Exception localException1) { 
/* 161:    */        
/* 165:165 */          localException1.printStackTrace();JOptionPane.showOptionDialog(new JPanel(), "Settings applied but failed to save for next session", "ERROR", 0, 0, null, null, null);
/* 166:    */      }
/* 167:167 */      this.a.dispose(); return;
/* 168:168 */    } catch (Exception localException2) { 
/* 169:    */      
/* 173:173 */        localException2.printStackTrace();JOptionPane.showOptionDialog(new JPanel(), "Please only use numbers", "ERROR", 0, 0, null, null, null);
/* 174:    */    }
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */