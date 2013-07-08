/*   1:    */import java.io.PrintStream;
/*   2:    */import java.lang.reflect.Field;
/*   3:    */import javax.swing.JTextArea;
/*   4:    */import org.schema.game.common.data.element.ElementInformation;
/*   5:    */
/* 333:    */final class oc
/* 334:    */  implements na
/* 335:    */{
/* 336:    */  oc(JTextArea paramJTextArea) {}
/* 337:    */  
/* 338:    */  public final void a(Field paramField, ElementInformation paramElementInformation)
/* 339:    */  {
/* 340:340 */    paramField.set(paramElementInformation, this.a.getText());
/* 341:341 */    System.err.println("APPLIED TEXT AREA");
/* 342:    */  }
/* 343:    */  
/* 344:    */  public final void b(Field paramField, ElementInformation paramElementInformation) {}
/* 345:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */