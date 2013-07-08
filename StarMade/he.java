/*   1:    */import org.schema.schine.network.client.ClientState;
/*   2:    */
/* 215:    */final class he
/* 216:    */  extends yw
/* 217:    */{
/* 218:    */  he(ha paramha, ClientState paramClientState)
/* 219:    */  {
/* 220:220 */    super(paramClientState);
/* 221:    */  }
/* 222:    */  
/* 223:    */  protected final boolean b() {
/* 224:224 */    return this.a.c;
/* 225:    */  }
/* 226:    */  
/* 228:    */  protected final void f()
/* 229:    */  {
/* 230:230 */    if (this.a.a == 4) {
/* 231:231 */      ((ct)a()).a().b("Cannot modify rights\nof admin role");return;
/* 232:    */    }
/* 233:    */    
/* 237:237 */    this.a.c = false;
/* 238:    */  }
/* 239:    */  
/* 242:    */  protected final void e()
/* 243:    */  {
/* 244:244 */    this.a.c = true;
/* 245:    */  }
/* 246:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     he
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */