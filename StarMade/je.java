/*  1:   */import java.util.LinkedList;
/*  2:   */
/*  7:   */final class je
/*  8:   */{
/*  9: 9 */  LinkedList a = new LinkedList();
/* 10:   */  
/* 17:   */  final boolean a(Object paramObject)
/* 18:   */  {
/* 19:19 */    synchronized (this.a) {
/* 20:20 */      return this.a.contains(paramObject);
/* 21:   */    }
/* 22:   */  }
/* 23:   */  
/* 24:   */  final Object a() {
/* 25:25 */    synchronized (this.a) {
/* 26:26 */      while (this.a.isEmpty()) {
/* 27:27 */        this.a.wait();
/* 28:   */      }
/* 29:29 */      return this.a.removeFirst();
/* 30:   */    }
/* 31:   */  }
/* 32:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     je
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */