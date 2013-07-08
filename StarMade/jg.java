/*   1:    */import java.io.File;
/*   2:    */import java.io.FilenameFilter;
/*   3:    */
/* 372:    */public final class jg
/* 373:    */  implements FilenameFilter
/* 374:    */{
/* 375:    */  public final boolean accept(File paramFile, String paramString)
/* 376:    */  {
/* 377:377 */    return paramString.startsWith("ENTITY_SHIP_MOB_SIM");
/* 378:    */  }
/* 379:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */