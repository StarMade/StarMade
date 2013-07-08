/*  1:   */import java.awt.event.ActionEvent;
/*  2:   */import java.awt.event.ActionListener;
/*  3:   */import javax.swing.JList;
/*  4:   */import org.schema.game.common.data.element.FactoryResource;
/*  5:   */
/* 66:   */final class oo
/* 67:   */  implements ActionListener
/* 68:   */{
/* 69:   */  oo(om paramom) {}
/* 70:   */  
/* 71:   */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 72:   */  {
/* 73:73 */    for (Object localObject : om.a(this.a).getSelectedValues())
/* 74:   */    {
/* 75:74 */      if (paramActionEvent != null) {
/* 76:75 */        om.a(this.a).b((FactoryResource)localObject);
/* 77:   */      }
/* 78:   */    }
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */