/*   1:    */import java.io.File;
/*   2:    */import java.io.FilenameFilter;
/*   3:    */
/* 172:    */public final class kx
/* 173:    */  implements FilenameFilter
/* 174:    */{
/* 175:    */  public kx(String paramString) {}
/* 176:    */  
/* 177:    */  public final boolean accept(File paramFile, String paramString)
/* 178:    */  {
/* 179:179 */    return paramString.startsWith(this.a);
/* 180:    */  }
/* 181:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */