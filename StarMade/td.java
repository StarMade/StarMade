/*  1:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  2:   */import java.io.PrintStream;
/*  3:   */
/*  9:   */public final class td
/* 10:   */  extends ti
/* 11:   */{
/* 12:   */  private static final long serialVersionUID = 1L;
/* 13:   */  private long a;
/* 14:   */  
/* 15:   */  public td(wk paramwk)
/* 16:   */  {
/* 17:17 */    super(paramwk);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public final boolean c()
/* 21:   */  {
/* 22:22 */    this.a = System.currentTimeMillis();
/* 23:23 */    return false;
/* 24:   */  }
/* 25:   */  
/* 27:   */  public final boolean b()
/* 28:   */  {
/* 29:29 */    return false;
/* 30:   */  }
/* 31:   */  
/* 32:   */  public final boolean d()
/* 33:   */  {
/* 34:34 */    int i = 1;
/* 35:35 */    String str = "";
/* 36:36 */    for (int j = 0; j < ((vY)a()).a().size(); j++) {
/* 37:37 */      if (((vY)a()).a(j)) {
/* 38:38 */        i = 0;
/* 39:39 */        str = str + (String)((vY)a()).a().get(j) + "; ";
/* 40:   */      }
/* 41:   */    }
/* 42:   */    
/* 44:44 */    if (i != 0) {
/* 45:45 */      ((vY)a()).a().a().a((vY)a());
/* 46:   */      
/* 47:47 */      a().d();
/* 48:   */    } else {
/* 49:49 */      System.err.println("[AI] DISBANDING: Waiting for all to unload. Still loaded: " + str);
/* 50:50 */      if (System.currentTimeMillis() - this.a > 240000L) {
/* 51:51 */        a(new tG());
/* 52:   */      }
/* 53:   */    }
/* 54:   */    
/* 56:56 */    return false;
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     td
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */