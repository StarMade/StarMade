import java.io.InvalidObjectException;
import java.util.logging.Level;

public final class class_28
  extends Level
{
  private static final long serialVersionUID = 4998223571342726511L;
  public static Level field_442 = new class_28("STDOUT", Level.INFO.intValue() + 53);
  public static Level field_443 = new class_28("STDERR", Level.INFO.intValue() + 54);
  
  private class_28(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }
  
  protected final Object readResolve()
  {
    if (intValue() == field_442.intValue()) {
      return field_442;
    }
    if (intValue() == field_443.intValue()) {
      return field_443;
    }
    throw new InvalidObjectException("Unknown instance :" + this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_28
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */