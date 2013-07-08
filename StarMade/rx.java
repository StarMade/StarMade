/*  1:   */import java.io.IOException;
/*  2:   */import org.schema.game.server.data.admin.AdminCommands;
/*  3:   */
/* 61:   */final class rx
/* 62:   */  extends rW
/* 63:   */{
/* 64:   */  private static final long serialVersionUID = 1L;
/* 65:   */  
/* 66:   */  rx(String paramString, lE paramlE)
/* 67:   */  {
/* 68:68 */    super(paramString);
/* 69:   */  }
/* 70:   */  
/* 71:   */  public final void a() {
/* 72:   */    try {
/* 73:73 */      ((ct)this.a.getState()).a().a(AdminCommands.R, new Object[] { this.a.getName() }); return;
/* 74:74 */    } catch (IOException localIOException) { 
/* 75:   */      
/* 78:78 */        localIOException.printStackTrace(); return;
/* 79:   */    } catch (InterruptedException localInterruptedException) {
/* 80:76 */      
/* 81:   */      
/* 82:78 */        localInterruptedException;
/* 83:   */    }
/* 84:   */  }
/* 85:   */  
/* 86:   */  public final Object a()
/* 87:   */  {
/* 88:82 */    return "";
/* 89:   */  }
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */