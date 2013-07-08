/*  1:   */package org.lwjgl.util.glu;
/*  2:   */
/* 18:   */public class Registry
/* 19:   */  extends Util
/* 20:   */{
/* 21:   */  private static final String versionString = "1.3";
/* 22:   */  
/* 37:   */  private static final String extensionString = "GLU_EXT_nurbs_tessellator GLU_EXT_object_space_tess ";
/* 38:   */  
/* 54:   */  public static String gluGetString(int name)
/* 55:   */  {
/* 56:56 */    if (name == 100800)
/* 57:57 */      return "1.3";
/* 58:58 */    if (name == 100801) {
/* 59:59 */      return "GLU_EXT_nurbs_tessellator GLU_EXT_object_space_tess ";
/* 60:   */    }
/* 61:61 */    return null;
/* 62:   */  }
/* 63:   */  
/* 73:   */  public static boolean gluCheckExtension(String extName, String extString)
/* 74:   */  {
/* 75:75 */    if ((extString == null) || (extName == null)) {
/* 76:76 */      return false;
/* 77:   */    }
/* 78:78 */    return extString.indexOf(extName) != -1;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Registry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */