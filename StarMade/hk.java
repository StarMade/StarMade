/*   1:    */import java.io.PrintStream;
/*   2:    */import org.schema.schine.network.client.ClientState;
/*   3:    */
/*  60:    */final class hk
/*  61:    */  extends yw
/*  62:    */{
/*  63:    */  hk(ClientState paramClientState)
/*  64:    */  {
/*  65: 65 */    super(paramClientState);
/*  66:    */  }
/*  67:    */  
/*  68:    */  protected final void e() {
/*  69: 69 */    int i = ((ct)a()).a().h();
/*  70:    */    
/*  71: 71 */    if (((ct)a()).a().a(i) != null) {
/*  72: 72 */      lP.a(((ct)a()).a(), true);return;
/*  73:    */    }
/*  74: 74 */    ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/*  75: 75 */    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*  76:    */  }
/*  77:    */  
/*  80:    */  protected final void f()
/*  81:    */  {
/*  82: 82 */    int i = ((ct)a()).a().h();
/*  83:    */    
/*  84: 84 */    if (((ct)a()).a().a(i) != null) {
/*  85: 85 */      lP.a(((ct)a()).a(), false);return;
/*  86:    */    }
/*  87: 87 */    ((ct)a()).a().b("Cannot change setting!\nYou are in no faction");
/*  88: 88 */    System.err.println("[CLIENT][FactionSetting] faction not found: " + i);
/*  89:    */  }
/*  90:    */  
/*  93:    */  protected final boolean b()
/*  94:    */  {
/*  95: 95 */    int i = ((ct)a()).a().h();
/*  96:    */    lP locallP;
/*  97: 97 */    if ((locallP = ((ct)a()).a().a(i)) != null) {
/*  98: 98 */      return locallP.c();
/*  99:    */    }
/* 100:100 */    return false;
/* 101:    */  }
/* 102:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */