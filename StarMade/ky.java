/*   1:    */import java.io.File;
/*   2:    */import java.io.FilenameFilter;
/*   3:    */
/* 640:    */public final class ky
/* 641:    */  implements FilenameFilter
/* 642:    */{
/* 643:    */  public final boolean accept(File paramFile, String paramString)
/* 644:    */  {
/* 645:645 */    return (paramString.startsWith("SECTOR")) || (paramString.startsWith("VOIDSYSTEM"));
/* 646:    */  }
/* 647:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ky
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */