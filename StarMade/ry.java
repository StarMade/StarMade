/*  1:   */import java.io.IOException;
/*  2:   */import org.schema.game.server.data.admin.AdminCommands;
/*  3:   */
/* 78:   */final class ry
/* 79:   */  extends rW
/* 80:   */{
/* 81:   */  private static final long serialVersionUID = 1L;
/* 82:   */  
/* 83:   */  ry(String paramString, lE paramlE)
/* 84:   */  {
/* 85:85 */    super(paramString);
/* 86:   */  }
/* 87:   */  
/* 88:   */  public final void a() {
/* 89:   */    try {
/* 90:90 */      ((ct)this.a.getState()).a().a(AdminCommands.K, new Object[] { this.a.getName() }); return;
/* 91:91 */    } catch (IOException localIOException) { 
/* 92:   */      
/* 95:95 */        localIOException.printStackTrace(); return;
/* 96:   */    } catch (InterruptedException localInterruptedException) {
/* 97:93 */      
/* 98:   */      
/* 99:95 */        localInterruptedException;
/* 100:   */    }
/* 101:   */  }
/* 102:   */  
/* 103:   */  public final Object a()
/* 104:   */  {
/* 105:99 */    return "";
/* 106:   */  }
/* 107:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */