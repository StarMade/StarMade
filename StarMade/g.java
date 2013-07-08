/*  1:   */import java.io.InvalidObjectException;
/*  2:   */import java.util.logging.Level;
/*  3:   */
/* 17:   */public final class g
/* 18:   */  extends Level
/* 19:   */{
/* 20:   */  private static final long serialVersionUID = 4998223571342726511L;
/* 21:21 */  public static Level a = new g("STDOUT", Level.INFO.intValue() + 53);
/* 22:   */  
/* 26:26 */  public static Level b = new g("STDERR", Level.INFO.intValue() + 54);
/* 27:   */  
/* 30:   */  private g(String paramString, int paramInt)
/* 31:   */  {
/* 32:32 */    super(paramString, paramInt);
/* 33:   */  }
/* 34:   */  
/* 42:   */  protected final Object readResolve()
/* 43:   */  {
/* 44:44 */    if (intValue() == a.intValue()) {
/* 45:45 */      return a;
/* 46:   */    }
/* 47:47 */    if (intValue() == b.intValue()) {
/* 48:48 */      return b;
/* 49:   */    }
/* 50:50 */    throw new InvalidObjectException("Unknown instance :" + this);
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     g
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */