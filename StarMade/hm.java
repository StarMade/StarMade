/*   1:    */import java.io.PrintStream;
/*   2:    */import org.schema.schine.network.client.ClientState;
/*   3:    */
/* 149:    */final class hm
/* 150:    */  extends yw
/* 151:    */{
/* 152:    */  hm(ClientState paramClientState)
/* 153:    */  {
/* 154:154 */    super(paramClientState);
/* 155:    */  }
/* 156:    */  
/* 157:    */  protected final void e() {
/* 158:158 */    int i = ((ct)a()).a().h();
/* 159:    */    
/* 160:160 */    if (((ct)a()).a().a(i) != null) {
/* 161:161 */      lP.c(((ct)a()).a(), true);return;
/* 162:    */    }
/* 163:163 */    ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 164:164 */    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/* 165:    */  }
/* 166:    */  
/* 169:    */  protected final void f()
/* 170:    */  {
/* 171:171 */    int i = ((ct)a()).a().h();
/* 172:    */    
/* 173:173 */    if (((ct)a()).a().a(i) != null) {
/* 174:174 */      lP.c(((ct)a()).a(), false);return;
/* 175:    */    }
/* 176:176 */    ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 177:177 */    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/* 178:    */  }
/* 179:    */  
/* 182:    */  protected final boolean b()
/* 183:    */  {
/* 184:184 */    int i = ((ct)a()).a().h();
/* 185:    */    lP locallP;
/* 186:186 */    if ((locallP = ((ct)a()).a().a(i)) != null) {
/* 187:187 */      return locallP.d();
/* 188:    */    }
/* 189:189 */    return false;
/* 190:    */  }
/* 191:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */