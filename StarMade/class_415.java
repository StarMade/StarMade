import java.io.PrintStream;
import java.util.ArrayList;

public final class class_415
  extends class_15
{
  private class_16 field_4;
  
  public class_415(class_371 paramclass_371, String paramString, class_16 paramclass_16)
  {
    super(paramclass_371, 23, paramString, "Enter a name for the new faction\n\nWARNING: if you aleady are in a faction,\nyou will leave that faction\nwhen creating a new one", null);
    System.err.println("CURRENT FACTION CODE: " + paramclass_371.a20().h1());
    this.field_4 = paramclass_16;
    a10(new class_407());
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return paramString1;
  }
  
  public final boolean a1()
  {
    return this.field_4.b().indexOf(this) != this.field_4.b().size() - 1;
  }
  
  public final void a2()
  {
    this.field_4.e2(false);
  }
  
  public final void onFailedTextCheck(String paramString)
  {
    a9(paramString);
  }
  
  public final boolean a7(String paramString)
  {
    this.field_4.a20().a141().a203(class_765.a(), paramString, "a faction");
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_415
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */