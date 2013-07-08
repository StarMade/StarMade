/*    1:     */import java.io.File;
/*    2:     */import java.io.FilenameFilter;
/*    3:     */
/* 1254:     */public final class kz
/* 1255:     */  implements FilenameFilter
/* 1256:     */{
/* 1257:     */  public final boolean accept(File paramFile, String paramString)
/* 1258:     */  {
/* 1259:1259 */    return (paramString.startsWith("SECTOR")) || (paramString.startsWith("VOIDSYSTEM"));
/* 1260:     */  }
/* 1261:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kz
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */