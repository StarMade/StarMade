/*  1:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  2:   */
/* 23:   */final class ap
/* 24:   */  implements ww
/* 25:   */{
/* 26:   */  ap(short paramShort) {}
/* 27:   */  
/* 28:   */  public final boolean a(String paramString, wB paramwB)
/* 29:   */  {
/* 30:   */    try
/* 31:   */    {
/* 32:32 */      if (paramString.length() > 0) {
/* 33:33 */        ElementKeyMap.getInfo(this.a);
/* 34:34 */        Integer.parseInt(paramString);
/* 35:35 */        return true;
/* 36:   */      }
/* 37:   */    }
/* 38:   */    catch (NumberFormatException localNumberFormatException) {}
/* 39:   */    
/* 40:40 */    paramwB.onFailedTextCheck("Amount must be a number!");
/* 41:41 */    return false;
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */