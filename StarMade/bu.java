/*  1:   */import java.util.ArrayList;
/*  2:   */
/*  5:   */public abstract class bu
/*  6:   */  extends K
/*  7:   */{
/*  8:   */  protected short a;
/*  9:   */  protected final Object a;
/* 10:   */  
/* 11:   */  public bu(ct paramct, short paramShort, String paramString, Object paramObject, int paramInt)
/* 12:   */  {
/* 13:13 */    super(paramct, 5, paramString, paramObject, String.valueOf(paramInt));
/* 14:14 */    this.jdField_a_of_type_JavaLangObject = paramObject;
/* 15:15 */    this.jdField_a_of_type_Short = paramShort;
/* 16:   */  }
/* 17:   */  
/* 20:   */  public bu(ct paramct, String paramString, Object paramObject)
/* 21:   */  {
/* 22:22 */    super(paramct, 10, paramString, paramObject, "0");
/* 23:23 */    this.jdField_a_of_type_JavaLangObject = paramObject;
/* 24:24 */    this.jdField_a_of_type_Short = -2;
/* 25:   */  }
/* 26:   */  
/* 29:   */  public String[] getCommandPrefixes()
/* 30:   */  {
/* 31:31 */    return null;
/* 32:   */  }
/* 33:   */  
/* 42:   */  public String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/* 43:   */  {
/* 44:44 */    return paramString1;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public final boolean a()
/* 48:   */  {
/* 49:49 */    return this.a.b().indexOf(this) != this.a.b().size() - 1;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public final void a() {
/* 53:53 */    this.a.a().a.a.a.e(false);
/* 54:   */  }
/* 55:   */  
/* 58:   */  public void onFailedTextCheck(String paramString)
/* 59:   */  {
/* 60:60 */    a(paramString);
/* 61:   */  }
/* 62:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */