/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.HashSet;
/*   3:    */import javax.vecmath.Vector3f;
/*   4:    */import org.schema.game.client.view.SegmentDrawer;
/*   5:    */import org.schema.game.common.data.world.Segment;
/*   6:    */import org.schema.game.common.data.world.SegmentData;
/*   7:    */
/* 427:    */final class dh
/* 428:    */  implements jM
/* 429:    */{
/* 430:    */  private dh(df paramdf) {}
/* 431:    */  
/* 432:432 */  Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/* 433:    */  
/* 434:    */  public final boolean handle(Segment paramSegment) {
/* 435:435 */    mr localmr = (mr)paramSegment;
/* 436:    */    
/* 437:437 */    synchronized (paramSegment.c) {
/* 438:438 */      if (((!localmr.g()) || (localmr.c)) && (localmr.a()))
/* 439:    */      {
/* 443:443 */        if ((!jdField_a_of_type_Boolean) && (localmr.a().getSegment() != localmr)) { throw new AssertionError();
/* 444:    */        }
/* 445:445 */        if (SegmentDrawer.a(paramSegment.a(), localmr, this.jdField_a_of_type_JavaxVecmathVector3f, df.a(this.jdField_a_of_type_Df))) {
/* 446:446 */          this.jdField_a_of_type_Df.jdField_a_of_type_JavaUtilArrayList.add(localmr);
/* 447:447 */          this.jdField_a_of_type_Df.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.c += 1;
/* 448:    */        }
/* 449:449 */        else if (localmr.b()) {
/* 450:450 */          df.a(this.jdField_a_of_type_Df).add(localmr);
/* 452:    */        }
/* 453:    */        
/* 456:    */      }
/* 457:457 */      else if (localmr.g()) {
/* 458:458 */        paramSegment = null;this.jdField_a_of_type_Df.jdField_a_of_type_OrgSchemaGameClientViewSegmentDrawer.a.i += 1L;
/* 459:    */      }
/* 460:460 */      else if (localmr.b()) {
/* 461:461 */        df.a(this.jdField_a_of_type_Df).add(localmr);
/* 462:    */      }
/* 463:    */    }
/* 464:    */    
/* 468:468 */    return !xm.a();
/* 469:    */  }
/* 470:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */