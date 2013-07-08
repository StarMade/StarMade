/*  1:   */import java.util.ArrayList;
/*  2:   */import java.util.Iterator;
/*  3:   */
/*  9:   */public final class ab
/* 10:   */  extends U
/* 11:   */{
/* 12:   */  public ab(ct paramct)
/* 13:   */  {
/* 14:14 */    super(paramct);
/* 15:   */  }
/* 16:   */  
/* 19:   */  public final void handleKeyEvent()
/* 20:   */  {
/* 21:21 */    super.handleKeyEvent();
/* 22:   */  }
/* 23:   */  
/* 25:   */  public final void a(xp paramxp)
/* 26:   */  {
/* 27:27 */    super.a(paramxp);
/* 28:   */  }
/* 29:   */  
/* 35:   */  public final void b(boolean paramBoolean)
/* 36:   */  {
/* 37:37 */    int i = 0;
/* 38:38 */    if (paramBoolean) { Iterator localIterator;
/* 39:39 */      synchronized (a().b()) {
/* 40:40 */        for (localIterator = a().b().iterator(); localIterator.hasNext();) {
/* 41:41 */          if (((H)localIterator.next() instanceof G)) {
/* 42:42 */            i = 1;
/* 43:   */          }
/* 44:   */        }
/* 45:   */      }
/* 46:46 */      if (i == 0) {
/* 47:47 */        ??? = new G(a());
/* 48:48 */        a().b().add(???);
/* 49:   */      }
/* 50:   */    } else {
/* 51:51 */      H.a = System.currentTimeMillis();
/* 52:52 */      synchronized (a().b()) {
/* 53:53 */        for (int j = 0; j < a().b().size(); j++)
/* 54:   */        {
/* 55:55 */          if (((H)a().b().get(j) instanceof G))
/* 56:   */          {
/* 57:57 */            ((H)a().b().get(j)).d();
/* 58:58 */            break;
/* 59:   */          }
/* 60:   */        }
/* 61:   */      }
/* 62:   */    }
/* 63:   */    
/* 64:64 */    super.b(paramBoolean);
/* 65:   */  }
/* 66:   */  
/* 69:   */  public final void a(xq paramxq)
/* 70:   */  {
/* 71:71 */    wV.a = false;
/* 72:72 */    super.a(paramxq);
/* 73:   */  }
/* 74:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ab
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */