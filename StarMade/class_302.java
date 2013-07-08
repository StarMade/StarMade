import org.schema.schine.graphicsengine.shader.ErrorDialogException;

public final class class_302
  extends class_454
{
  private static final long serialVersionUID = 438885771406304916L;
  
  public class_302(class_981 paramclass_981, String paramString, class_371 paramclass_371)
  {
    super(paramclass_981, paramString, paramclass_371);
    this.field_162 = true;
  }
  
  protected final boolean a()
  {
    return this.field_128.a14().a18().a79().a61().c();
  }
  
  public final boolean c()
  {
    class_445 localclass_445;
    if ((localclass_445 = this.field_128.a14().a18().a79()).a61().c()) {
      try
      {
        localclass_445.a61().c2(false);
        localclass_445.a60().c2(true);
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
    }
    return super.c();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_302
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */