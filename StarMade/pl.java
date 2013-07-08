/*  1:   */import java.io.IOException;
/*  2:   */import java.util.Observable;
/*  3:   */
/* 53:   */final class pl
/* 54:   */  extends Observable
/* 55:   */{
/* 56:   */  private pl(oU paramoU) {}
/* 57:   */  
/* 58:   */  public final void a(jb paramjb)
/* 59:   */  {
/* 60:   */    try
/* 61:   */    {
/* 62:   */      
/* 63:   */    }
/* 64:   */    catch (IOException localIOException)
/* 65:   */    {
/* 66:66 */      
/* 67:   */      
/* 68:68 */        localIOException;
/* 69:   */    }
/* 70:   */    
/* 71:69 */    if (oU.a(this.a)) {
/* 72:70 */      setChanged();
/* 73:71 */      notifyObservers();
/* 74:   */    } else {
/* 75:73 */      org.schema.game.common.Starter.b = true;
/* 76:   */    }
/* 77:75 */    setChanged();
/* 78:76 */    notifyObservers(paramjb);
/* 79:77 */    this.a.setVisible(false);
/* 80:78 */    this.a.setVisible(false);
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */