/*   1:    */import java.awt.event.ActionEvent;
/*   2:    */import java.awt.event.ActionListener;
/*   3:    */import javax.swing.JCheckBox;
/*   4:    */
/* 235:    */final class rf
/* 236:    */  implements ActionListener
/* 237:    */{
/* 238:    */  rf(rc paramrc, JCheckBox paramJCheckBox1, int paramInt, JCheckBox paramJCheckBox2, JCheckBox paramJCheckBox3, JCheckBox paramJCheckBox4) {}
/* 239:    */  
/* 240:    */  public final void actionPerformed(ActionEvent paramActionEvent)
/* 241:    */  {
/* 242:242 */    if (this.jdField_a_of_type_JavaxSwingJCheckBox.isSelected()) {
/* 243:243 */      rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 1L;
/* 244:    */    }
/* 245:245 */    if (this.b.isSelected()) {
/* 246:246 */      rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 4L;
/* 247:    */    }
/* 248:248 */    if (this.c.isSelected()) {
/* 249:249 */      rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 2L;
/* 250:    */    }
/* 251:251 */    if (this.d.isSelected()) {
/* 252:252 */      rc.a(this.jdField_a_of_type_Rc).a()[this.jdField_a_of_type_Int] |= 8L;
/* 253:    */    }
/* 254:    */  }
/* 255:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */