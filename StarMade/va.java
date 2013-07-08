/*  1:   */import org.schema.game.common.data.element.ElementInformation;
/*  2:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  3:   */
/*  4:   */public class va extends uZ
/*  5:   */{
/*  6:   */  static
/*  7:   */  {
/*  8: 8 */    jdField_a_of_type_Boolean = !va.class.desiredAssertionStatus();
/*  9:   */  }
/* 10:   */  
/* 11:   */  public va(short paramShort1, int paramInt, short paramShort2)
/* 12:   */  {
/* 13:13 */    super(paramShort1, paramInt, paramShort2);
/* 14:14 */    if ((!jdField_a_of_type_Boolean) && (!ElementKeyMap.getInfo(paramShort1).isLeveled())) throw new AssertionError();
/* 15:   */  }
/* 16:   */  
/* 17:   */  public final void a(org.schema.game.common.data.world.SegmentData paramSegmentData, int paramInt1, int paramInt2, int paramInt3, java.util.Random paramRandom)
/* 18:   */  {
/* 19:19 */    int i = 1;
/* 20:20 */    float f = 0.1F;
/* 21:21 */    while ((i < 5) && (paramRandom.nextFloat() < f)) {
/* 22:22 */      i++;
/* 23:23 */      f *= 0.9F;
/* 24:   */    }
/* 25:   */    
/* 26:26 */    paramRandom = ElementKeyMap.getLevel(this.jdField_a_of_type_Short, i);
/* 27:   */    
/* 28:28 */    paramSegmentData.setInfoElementUnsynched((byte)Math.abs(paramInt1 % 16), (byte)Math.abs(paramInt2 % 16), (byte)Math.abs(paramInt3 % 16), paramRandom, false);
/* 29:   */  }
/* 30:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     va
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */