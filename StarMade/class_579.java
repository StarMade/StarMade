import java.io.IOException;
import java.util.Observable;

final class class_579
  extends Observable
{
  private class_579(class_489 paramclass_489) {}
  
  public final void a(class_899 paramclass_899)
  {
    try
    {
      
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    if (class_489.a(this.field_879))
    {
      setChanged();
      notifyObservers();
    }
    else
    {
      org.schema.game.common.Starter.field_2078 = true;
    }
    setChanged();
    notifyObservers(paramclass_899);
    this.field_879.setVisible(false);
    this.field_879.setVisible(false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_579
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */