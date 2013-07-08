/*   1:    */
/* 433:    */final class ia
/* 434:    */  implements ww
/* 435:    */{
/* 436:    */  public final boolean a(String paramString, wB paramwB)
/* 437:    */  {
/* 438:    */    try
/* 439:    */    {
/* 440:440 */      if (paramString.length() == 0) {
/* 441:441 */        return true;
/* 442:    */      }
/* 443:443 */      q.a(paramString);
/* 444:444 */      return true;
/* 445:445 */    } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/* 446:    */      
/* 448:448 */      paramwB.onFailedTextCheck("Wrong Format. Must be x, y, z"); }
/* 449:449 */    return false;
/* 450:    */  }
/* 451:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ia
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */