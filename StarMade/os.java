/*   1:    */import javax.swing.JLabel;
/*   2:    */import javax.swing.JSpinner;
/*   3:    */import javax.swing.event.ChangeEvent;
/*   4:    */import javax.swing.event.ChangeListener;
/*   5:    */
/* 133:    */final class os
/* 134:    */  implements ChangeListener
/* 135:    */{
/* 136:    */  os(op paramop) {}
/* 137:    */  
/* 138:    */  public final void stateChanged(ChangeEvent paramChangeEvent)
/* 139:    */  {
/* 140:140 */    if ((!jdField_a_of_type_Boolean) && (op.b(this.jdField_a_of_type_Op) == null)) throw new AssertionError();
/* 141:141 */    op.b(this.jdField_a_of_type_Op).setText("Count " + String.valueOf(op.a(this.jdField_a_of_type_Op).getValue()));
/* 142:    */  }
/* 143:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     os
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */