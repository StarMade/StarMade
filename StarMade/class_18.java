import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.data.world.Segment;

public final class class_18
  extends class_16
{
  public class_18(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
    a6().a27().f();
    if (Keyboard.getEventKeyState()) {
      switch (Keyboard.getEventKey())
      {
      case 38: 
        if (class_949.field_1189.b1())
        {
          class_227.field_90 = true;
          return;
        }
        break;
      case 34: 
        class_949.field_1226.d();
        return;
      case 45: 
        class_949.field_1202.d();
        System.err.println("SYNC");
        return;
      case 60: 
        class_949.field_1201.d();
        System.err.println("RELOADING SHADERS");
        return;
      case 61: 
        class_949.field_1206.d();
        System.err.println("fbo: " + class_949.field_1206.b1());
        return;
      case 62: 
        class_949.field_1240.d();
        System.err.println("bloom: " + class_949.field_1240.b1());
        return;
      case 23: 
        class_949.field_1234.d();
        System.err.println("info: " + class_949.field_1234.b1());
        return;
      case 63: 
        SegmentDrawer.field_98 = true;
        System.err.println("force contextSwitch: " + SegmentDrawer.field_98);
        return;
      case 64: 
        if (Keyboard.isKeyDown(42)) {
          SegmentDrawer.field_106 = true;
        }
        a6().a4().a();
        return;
      case 65: 
        a6().a33(true);
        return;
      case 66: 
        if (((localObject1 = a6().a14().field_4.field_4.field_4.a43()) != null) && ((localObject1 instanceof class_747)) && ((localObject1 = ((class_747)localObject1).getSegmentBuffer().a9(class_747.field_136, false)) != null))
        {
          a6().a14().field_4.field_4.field_4.a51().a16((class_796)localObject1);
          a6().a4().a14(a6().a3(), (class_365)((class_796)localObject1).a7().a15(), new class_48(), ((class_796)localObject1).a2(new class_48()), true);
          return;
        }
        Object localObject1 = a6().a7().values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (class_797)((Iterator)localObject1).next();
          try
          {
            if (((localObject2 instanceof class_747)) && (((class_747)localObject2).getSectorId() == a6().a8()) && ((localObject2 = ((class_747)localObject2).getSegmentBuffer().a9(class_747.field_136, true)) != null))
            {
              a6().a14().field_4.field_4.field_4.a51().a16((class_796)localObject2);
              a6().a4().a14(a6().a3(), (class_365)((class_796)localObject2).a7().a15(), new class_48(), ((class_796)localObject2).a2(new class_48()), true);
              return;
            }
          }
          catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
          {
            System.err.println("CANNOT IMMEDIATE REQUEST");
          }
        }
        return;
      case 68: 
        class_949.field_1221.d();
        System.err.println("physics: " + class_949.field_1221.b1());
        return;
      case 88: 
        System.err.println("REQUESTING TEST");
        a6().a4().a29().a8().a(new class_48(0, 0, 0));
        return;
      case 87: 
        a6().a();
        return;
      case 67: 
        class_949.field_1189.d();
        return;
      case 28: 
        if (class_949.field_1189.b1()) {
          class_227.field_90 = true;
        }
        break;
      }
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void a15(class_941 paramclass_941) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_18
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */