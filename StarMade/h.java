/*  1:   */import java.util.HashMap;
/*  2:   */
/*  4:   */public final class h
/*  5:   */{
/*  6: 6 */  public static HashMap a = new HashMap();
/*  7:   */  
/*  8:   */  public static void a(String paramString) {
/*  9: 9 */    a.put(paramString, Long.valueOf(System.currentTimeMillis()));
/* 10:   */  }
/* 11:   */  
/* 12:12 */  public static void b(String paramString) { a.put(paramString, Long.valueOf(System.currentTimeMillis() - ((Long)a.get(paramString)).longValue())); }
/* 13:   */  
/* 14:   */  public static long a(String paramString)
/* 15:   */  {
/* 16:16 */    if ((paramString = (Long)a.get(paramString)) != null) return paramString.longValue(); return -1L;
/* 17:   */  }
/* 18:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     h
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */