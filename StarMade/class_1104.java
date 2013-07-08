import java.io.PrintStream;
import java.util.HashMap;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.network.server.ServerMessage;

public final class class_1104
  extends class_1023
{
  private String field_223;
  private boolean field_249;
  
  public class_1104(class_1041 paramclass_1041)
  {
    super(paramclass_1041);
  }
  
  public class_1104(class_1041 paramclass_1041, class_48 paramclass_48, String paramString)
  {
    super(paramclass_1041, paramclass_48);
    this.field_223 = paramString;
  }
  
  public final void a7(class_748 paramclass_748)
  {
    paramclass_748.a129(new ServerMessage("#### Transmission Start\nHostile identified...\nExterminate...\n#### Transmission End\n", 2, paramclass_748.getId()));
  }
  
  public final void a2(class_941 paramclass_941)
  {
    super.a2(paramclass_941);
    if ((!this.field_249) && (this.field_223 != null))
    {
      if (this.field_223.c8().containsKey(this.field_223))
      {
        paramclass_941 = (SegmentController)this.field_223.c8().get(this.field_223);
        System.err.println("[SIM] Specific Target found: " + this.field_223 + ": " + paramclass_941);
        ((class_1256)this.field_223).a12(paramclass_941.getId());
        this.field_249 = true;
        return;
      }
      System.err.println("[SIM] Target not found " + this.field_223);
    }
  }
  
  protected final class_69 a8()
  {
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_558, null, this.field_224), new class_69(class_79.field_556, null, this.field_223), new class_69(class_79.field_548, null, null) });
  }
  
  protected final void a9(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.field_224 = ((class_48)paramclass_69[0].a4());
    this.field_223 = ((String)paramclass_69[1].a4());
    if (this.field_223 != null)
    {
      ((class_1256)this.field_223).a10(this.field_224);
      if (this.field_223.c8().containsKey(this.field_223)) {
        ((class_1256)this.field_223).a12(((SegmentController)this.field_223.c8().get(this.field_223)).getId());
      }
    }
  }
  
  public final class_1015 a10()
  {
    return class_1015.field_1291;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1104
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */