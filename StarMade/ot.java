/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JSpinner;
/*   4:    */import org.schema.game.common.data.element.FactoryResource;
/*   5:    */
/* 152:    */final class ot
/* 153:    */  implements ActionListener
/* 154:    */{
/* 155:    */  ot(op paramop, nb paramnb) {}
/* 156:    */  
/* 157:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 158:    */  {
/* 159:159 */    if ((op.a(this.jdField_a_of_type_Op) > 0) && 
/* 160:160 */      (((Integer)op.a(this.jdField_a_of_type_Op).getValue()).intValue() > 0)) {
/* 161:161 */      this.jdField_a_of_type_Nb.a(new FactoryResource(((Integer)op.a(this.jdField_a_of_type_Op).getValue()).intValue(), op.a(this.jdField_a_of_type_Op)));
/* 162:    */    }
/* 163:    */    
/* 167:167 */    this.jdField_a_of_type_Op.dispose();
/* 168:    */  }
/* 169:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ot
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */