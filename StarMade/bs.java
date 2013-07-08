/*  1:   */
/* 17:   */final class bs
/* 18:   */  implements ww
/* 19:   */{
/* 20:   */  bs(br parambr) {}
/* 21:   */  
/* 22:   */  public final boolean a(String paramString, wB paramwB)
/* 23:   */  {
/* 24:   */    try
/* 25:   */    {
/* 26:26 */      if (paramString.length() > 0)
/* 27:   */      {
/* 29:29 */        if (Integer.parseInt(paramString) <= 0) {
/* 30:30 */          this.a.a().a().b("ERROR: Invalid quantity");
/* 31:31 */          return false;
/* 32:   */        }
/* 33:   */        
/* 35:35 */        xe.b("0022_action - purchase with credits");
/* 36:36 */        return true;
/* 37:   */      }
/* 38:   */    }
/* 39:   */    catch (NumberFormatException localNumberFormatException) {}
/* 40:40 */    paramwB.onFailedTextCheck("Please only enter numbers!");
/* 41:41 */    return false;
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bs
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */