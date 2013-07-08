/*  1:   */import java.awt.event.ActionEvent;
/*  2:   */import java.awt.event.ActionListener;
/*  3:   */import javax.swing.JTextField;
/*  4:   */import org.schema.game.common.Starter;
/*  5:   */
/* 81:   */final class pt
/* 82:   */  implements ActionListener
/* 83:   */{
/* 84:   */  pt(pr parampr) {}
/* 85:   */  
/* 86:   */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 87:   */  {
/* 88:88 */    if (pr.a(this.a).getText().length() > 0)
/* 89:   */    {
/* 91:91 */      Starter.b(Starter.a = pr.a(this.a).getText());
/* 92:   */    } else {
/* 93:93 */      Starter.a = "";
/* 94:94 */      Starter.b("");
/* 95:   */    }
/* 96:96 */    this.a.dispose();
/* 97:   */  }
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */