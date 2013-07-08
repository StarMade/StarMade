/*   1:    */import java.io.PrintStream;
/*   2:    */import java.util.regex.Pattern;
/*   3:    */
/* 350:    */final class hi
/* 351:    */  implements ww
/* 352:    */{
/* 353:    */  public final boolean a(String paramString, wB paramwB)
/* 354:    */  {
/* 355:355 */    if ((paramString.length() >= 3) && (paramString.length() <= 16))
/* 356:    */    {
/* 357:357 */      if (Pattern.matches("[a-zA-Z0-9 _-]+", paramString))
/* 358:    */      {
/* 360:360 */        return true;
/* 361:    */      }
/* 362:362 */      System.err.println("MATCH FOUND ^ALPHANUMERIC");
/* 363:    */    }
/* 364:    */    
/* 367:367 */    paramwB.onFailedTextCheck("Please only alphanumeric (and space, _, -) values \nand between 3 and 16 long!");
/* 368:    */    
/* 372:372 */    return false;
/* 373:    */  }
/* 374:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */