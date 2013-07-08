/*  1:   */import org.schema.game.common.data.element.ElementInformation;
/*  2:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  3:   */
/*  9:   */public final class az
/* 10:   */{
/* 11:11 */  public final q a = new q(8, 8, 8);
/* 12:   */  public final q b;
/* 13:13 */  public final q c = new q(8, 8, 8);
/* 14:   */  public boolean a;
/* 15:   */  public boolean b;
/* 16:   */  public boolean c;
/* 17:   */  public int a;
/* 18:   */  public int b;
/* 19:   */  
/* 20:   */  public az()
/* 21:   */  {
/* 22:12 */    this.jdField_b_of_type_Q = new q(8, 8, 8);
/* 23:   */    
/* 30:20 */    this.jdField_b_of_type_Int = 1;
/* 31:   */  }
/* 32:   */  
/* 37:   */  public static int a(short paramShort, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
/* 38:   */  {
/* 39:29 */    if (paramShort != 0) {
/* 40:30 */      if (!paramBoolean1) {
/* 41:31 */        paramInt += 8;
/* 42:   */      }
/* 43:   */      
/* 44:34 */      if ((paramShort = ElementKeyMap.getInfo(paramShort)).getBlockStyle() > 0) {
/* 45:35 */        paramInt = org.schema.game.common.data.element.Element.orientationMapping[paramInt];
/* 46:   */        
/* 48:38 */        if (paramBoolean2) {
/* 49:39 */          paramInt = dO.a[(paramShort.blockStyle - 1)][paramInt];
/* 50:   */        }
/* 51:   */        
/* 53:43 */        if (paramBoolean3) {
/* 54:44 */          paramInt = dO.b[(paramShort.blockStyle - 1)][paramInt];
/* 55:   */        }
/* 56:   */        
/* 58:48 */        if (paramBoolean4) {
/* 59:49 */          paramInt = dO.c[(paramShort.blockStyle - 1)][paramInt];
/* 60:   */        }
/* 61:   */        
/* 64:54 */        paramInt = org.schema.game.common.data.element.Element.orientationBackMapping[paramInt];
/* 65:   */      }
/* 66:56 */      if (!paramBoolean1) {
/* 67:57 */        paramInt -= 8;
/* 68:   */      }
/* 69:   */    }
/* 70:   */    
/* 71:61 */    return paramInt;
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     az
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */