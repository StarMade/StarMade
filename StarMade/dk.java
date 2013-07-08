/*   1:    */import java.util.ArrayList;
/*   2:    */
/* 235:    */final class dk
/* 236:    */  implements Runnable
/* 237:    */{
/* 238:    */  dk(dj paramdj) {}
/* 239:    */  
/* 240:    */  public final void run()
/* 241:    */  {
/* 242:242 */    ArrayList localArrayList = new ArrayList();
/* 243:243 */    for (int i = 0; i < 2744; i++)
/* 244:    */    {
/* 245:245 */      if (mD.values()[dj.a(this.a).a().a().b(i)] == mD.c) {
/* 246:246 */        localArrayList.add(Integer.valueOf(i));
/* 247:    */      }
/* 248:    */    }
/* 249:249 */    dj.a(this.a, (Integer[])localArrayList.toArray(new Integer[localArrayList.size()]));
/* 250:    */  }
/* 251:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     dk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */