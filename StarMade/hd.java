/*   1:    */import org.schema.schine.network.client.ClientState;
/*   2:    */
/* 187:    */final class hd
/* 188:    */  extends yw
/* 189:    */{
/* 190:    */  hd(ha paramha, ClientState paramClientState)
/* 191:    */  {
/* 192:192 */    super(paramClientState);
/* 193:    */  }
/* 194:    */  
/* 195:    */  protected final boolean b() {
/* 196:196 */    return this.a.b;
/* 197:    */  }
/* 198:    */  
/* 200:    */  protected final void f()
/* 201:    */  {
/* 202:202 */    if (this.a.a == 4) {
/* 203:203 */      ((ct)a()).a().b("Cannot modify rights\nof admin role");return;
/* 204:    */    }
/* 205:    */    
/* 209:209 */    this.a.b = false;
/* 210:    */  }
/* 211:    */  
/* 214:    */  protected final void e()
/* 215:    */  {
/* 216:216 */    this.a.b = true;
/* 217:    */  }
/* 218:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hd
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */