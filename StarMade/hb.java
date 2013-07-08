/*   1:    */import org.schema.schine.network.client.ClientState;
/*   2:    */
/* 131:    */final class hb
/* 132:    */  extends yw
/* 133:    */{
/* 134:    */  hb(ha paramha, ClientState paramClientState)
/* 135:    */  {
/* 136:136 */    super(paramClientState);
/* 137:    */  }
/* 138:    */  
/* 139:    */  protected final boolean b() {
/* 140:140 */    return this.a.d;
/* 141:    */  }
/* 142:    */  
/* 144:    */  protected final void f()
/* 145:    */  {
/* 146:146 */    if (this.a.a == 4) {
/* 147:147 */      ((ct)a()).a().b("Cannot modify rights\nof admin role");return;
/* 148:    */    }
/* 149:    */    
/* 153:153 */    this.a.d = false;
/* 154:    */  }
/* 155:    */  
/* 158:    */  protected final void e()
/* 159:    */  {
/* 160:160 */    this.a.d = true;
/* 161:    */  }
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hb
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */