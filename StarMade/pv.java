/*   1:    */import java.io.File;
/*   2:    */import javax.swing.filechooser.FileFilter;
/*   3:    */
/* 120:    */final class pv
/* 121:    */  extends FileFilter
/* 122:    */{
/* 123:    */  public final boolean accept(File paramFile)
/* 124:    */  {
/* 125:125 */    if (paramFile.isDirectory()) {
/* 126:126 */      return true;
/* 127:    */    }
/* 128:128 */    if (paramFile.getName().endsWith(".png")) {
/* 129:129 */      return true;
/* 130:    */    }
/* 131:131 */    return false;
/* 132:    */  }
/* 133:    */  
/* 134:    */  public final String getDescription()
/* 135:    */  {
/* 136:136 */    return "PNG";
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pv
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */