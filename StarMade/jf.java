/*   1:    */import java.io.File;
/*   2:    */import java.io.FilenameFilter;
/*   3:    */
/* 357:    */public final class jf
/* 358:    */  implements FilenameFilter
/* 359:    */{
/* 360:    */  public final boolean accept(File paramFile, String paramString)
/* 361:    */  {
/* 362:362 */    return paramString.startsWith("ENTITY_SHIP_MOB_SIM");
/* 363:    */  }
/* 364:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jf
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */