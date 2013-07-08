/*   1:    */import java.lang.reflect.Field;
/*   2:    */import javax.swing.JTextPane;
/*   3:    */import org.schema.game.common.data.element.BlockFactory;
/*   4:    */
/* 289:    */final class ob
/* 290:    */  implements ol
/* 291:    */{
/* 292:    */  ob(oa paramoa) {}
/* 293:    */  
/* 294:    */  public final void a()
/* 295:    */  {
/* 296:    */    try
/* 297:    */    {
/* 298:298 */      if ((localObject = this.a.jdField_a_of_type_JavaLangReflectField.get(nM.a(this.a.jdField_a_of_type_NM))) != null) {
/* 299:299 */        localObject = (BlockFactory)localObject;
/* 300:300 */        this.a.jdField_a_of_type_JavaxSwingJTextPane.setText(((BlockFactory)localObject).toString());
/* 301:301 */        return; }
/* 302:302 */      this.a.jdField_a_of_type_JavaxSwingJTextPane.setText("   -   "); return;
/* 303:    */    }
/* 304:    */    catch (IllegalArgumentException localIllegalArgumentException)
/* 305:    */    {
/* 306:306 */      (localObject = 
/* 307:    */      
/* 312:312 */        localIllegalArgumentException).printStackTrace();d.a((Exception)localObject); return;
/* 313:    */    } catch (IllegalAccessException localIllegalAccessException) {
/* 314:    */      Object localObject;
/* 315:310 */      (localObject = 
/* 316:    */      
/* 317:312 */        localIllegalAccessException).printStackTrace();d.a((Exception)localObject);
/* 318:    */    }
/* 319:    */  }
/* 320:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ob
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */