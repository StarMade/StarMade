/*   1:    */import java.util.HashMap;
/*   2:    */import org.schema.schine.network.client.ClientState;
/*   3:    */
/* 199:    */final class hn
/* 200:    */  extends yN
/* 201:    */{
/* 202:    */  hn(ClientState paramClientState, Object paramObject, ys paramys, yT paramyT)
/* 203:    */  {
/* 204:204 */    super(paramClientState, 50, 20, paramObject, paramys, paramyT);
/* 205:    */  }
/* 206:    */  
/* 209:    */  public final void b()
/* 210:    */  {
/* 211:211 */    int i = ((ct)a()).a().h();
/* 212:    */    lP locallP;
/* 213:213 */    if ((locallP = ((ct)a()).a().a(i)) != null) {
/* 214:    */      lX locallX;
/* 215:215 */      if (((locallX = (lX)locallP.a().get(((ct)a()).a().getName())) != null) && (locallX.d(locallP))) {
/* 216:216 */        super.b();
/* 217:    */      }
/* 218:    */    }
/* 219:    */  }
/* 220:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */