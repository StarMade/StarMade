/*   1:    */import java.io.PrintStream;
/*   2:    */import org.schema.schine.network.client.ClientState;
/*   3:    */
/* 104:    */final class hl
/* 105:    */  extends yw
/* 106:    */{
/* 107:    */  hl(ClientState paramClientState)
/* 108:    */  {
/* 109:109 */    super(paramClientState);
/* 110:    */  }
/* 111:    */  
/* 112:    */  protected final void e() {
/* 113:113 */    int i = ((ct)a()).a().h();
/* 114:    */    
/* 115:115 */    if (((ct)a()).a().a(i) != null) {
/* 116:116 */      lP.b(((ct)a()).a(), true);return;
/* 117:    */    }
/* 118:118 */    ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 119:119 */    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/* 120:    */  }
/* 121:    */  
/* 124:    */  protected final void f()
/* 125:    */  {
/* 126:126 */    int i = ((ct)a()).a().h();
/* 127:    */    
/* 128:128 */    if (((ct)a()).a().a(i) != null) {
/* 129:129 */      lP.b(((ct)a()).a(), false);return;
/* 130:    */    }
/* 131:131 */    ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/* 132:132 */    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/* 133:    */  }
/* 134:    */  
/* 137:    */  protected final boolean b()
/* 138:    */  {
/* 139:139 */    int i = ((ct)a()).a().h();
/* 140:    */    lP locallP;
/* 141:141 */    if ((locallP = ((ct)a()).a().a(i)) != null) {
/* 142:142 */      return locallP.b();
/* 143:    */    }
/* 144:144 */    return false;
/* 145:    */  }
/* 146:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */