/*   1:    */import java.lang.reflect.Field;
/*   2:    */import javax.swing.JTextField;
/*   3:    */import org.schema.game.common.data.element.ElementInformation;
/*   4:    */
/* 351:    */final class od
/* 352:    */  implements na
/* 353:    */{
/* 354:    */  od(JTextField paramJTextField) {}
/* 355:    */  
/* 356:    */  public final void a(Field paramField, ElementInformation paramElementInformation)
/* 357:    */  {
/* 358:358 */    paramField.set(paramElementInformation, this.a.getText());
/* 359:    */  }
/* 360:    */  
/* 361:    */  public final void b(Field paramField, ElementInformation paramElementInformation)
/* 362:    */  {
/* 363:363 */    this.a.setText(paramField.get(paramElementInformation).toString());
/* 364:    */  }
/* 365:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     od
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */