/*   1:    */import java.io.IOException;
/*   2:    */import org.schema.game.common.data.world.Segment;
/*   3:    */
/* 488:    */final class kb
/* 489:    */  implements jM
/* 490:    */{
/* 491:    */  kb(ka paramka) {}
/* 492:    */  
/* 493:    */  public final boolean handle(Segment paramSegment)
/* 494:    */  {
/* 495:    */    try
/* 496:    */    {
/* 497:497 */      this.a.getSegmentProvider().a.a((mw)paramSegment);
/* 498:498 */    } catch (IOException localIOException) { 
/* 499:    */      
/* 500:500 */        localIOException;
/* 501:    */    }
/* 502:    */    
/* 503:501 */    return true;
/* 504:    */  }
/* 505:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */