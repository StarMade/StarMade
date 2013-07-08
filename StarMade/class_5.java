import java.util.Observable;

final class class_5
  extends Observable
{
  public final void a(String paramString)
  {
    setChanged();
    notifyObservers(paramString);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_5
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */