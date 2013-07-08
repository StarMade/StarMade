/*  1:   */import java.util.HashMap;
/*  2:   */
/* 11:   */public final class tc
/* 12:   */  extends ti
/* 13:   */{
/* 14:   */  private long a;
/* 15:   */  private static final long serialVersionUID = 1L;
/* 16:   */  
/* 17:   */  public tc(wk paramwk)
/* 18:   */  {
/* 19:19 */    super(paramwk);
/* 20:   */  }
/* 21:   */  
/* 28:   */  public final boolean c()
/* 29:   */  {
/* 30:30 */    this.a = System.currentTimeMillis();
/* 31:31 */    return false;
/* 32:   */  }
/* 33:   */  
/* 35:   */  public final boolean b()
/* 36:   */  {
/* 37:37 */    return false;
/* 38:   */  }
/* 39:   */  
/* 41:   */  public final boolean d()
/* 42:   */  {
/* 43:43 */    vY localvY = (vY)a();
/* 44:44 */    q localq = new q();
/* 45:45 */    for (lE locallE : localvY.a().b().values()) {
/* 46:46 */      localq.a(locallE.a(), localvY.a());
/* 47:47 */      if (localq.a() < 6.0F) {
/* 48:48 */        ((sL)a().a()).a(new q(locallE.a()));
/* 49:   */        
/* 50:50 */        localvY.a(locallE);
/* 51:   */        
/* 54:54 */        a(new tv());
/* 55:55 */        return true;
/* 56:   */      }
/* 57:   */    }
/* 58:   */    
/* 59:59 */    if (System.currentTimeMillis() - this.a > 60000L) {
/* 60:60 */      a(new tq());
/* 61:61 */      return true;
/* 62:   */    }
/* 63:   */    
/* 64:64 */    return false;
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */