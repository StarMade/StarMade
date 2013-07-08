import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.font.effects.ShadowEffect;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.Sendable;

public final class class_177
  extends class_1363
  implements Observer
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 jdField_field_90_of_type_Class_972;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private class_972 jdField_field_92_of_type_Class_972;
  private UnicodeFont jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont;
  private UnicodeFont jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_90_of_type_Boolean;
  private class_797 jdField_field_89_of_type_Class_797;
  private class_934 jdField_field_89_of_type_Class_934;
  private class_934 jdField_field_90_of_type_Class_934;
  private static Vector3f jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f();
  private static Vector4f jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f();
  
  public class_177(ClientState paramClientState)
  {
    super(paramClientState);
    paramClientState = this;
    this.jdField_field_92_of_type_Class_972 = new class_972(class_969.a2().a5("hud-target-c-4x4-gui-"), paramClientState.a24());
    paramClientState.jdField_field_92_of_type_Class_972.a_2(15);
    paramClientState.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("navigation-panel-gui-"), paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_968 = new class_968(288.0F, 316.0F, paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_964 = new class_964(paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_968.c7(paramClientState.jdField_field_89_of_type_Class_964);
    Font localFont = new Font("Arial", 1, 10);
    paramClientState.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont(localFont);
    paramClientState.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ShadowEffect(Color.black, 2, 2, 1.0F));
    paramClientState.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
    paramClientState.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
    try
    {
      paramClientState.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
    }
    catch (SlickException localSlickException1)
    {
      localSlickException1;
    }
    paramClientState.jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont(localFont);
    paramClientState.jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new OutlineEffect(4, Color.black));
    paramClientState.jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
    paramClientState.jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
    try
    {
      paramClientState.jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
    }
    catch (SlickException localSlickException2)
    {
      localSlickException2;
    }
    paramClientState.jdField_field_89_of_type_Class_972.a9(paramClientState.jdField_field_89_of_type_Class_968);
    paramClientState.jdField_field_89_of_type_Class_968.a161(255.0F, 141.0F, 0.0F);
    paramClientState.jdField_field_89_of_type_Class_964.a141(paramClientState.a40().jdField_field_4_of_type_Class_338);
    paramClientState.a24().addObserver(paramClientState);
    paramClientState.a9(paramClientState.jdField_field_89_of_type_Class_972);
  }
  
  public final void a2() {}
  
  public final void b2(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.b2(paramclass_1363);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    if (this.jdField_field_90_of_type_Boolean)
    {
      try
      {
        f();
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
      this.jdField_field_90_of_type_Boolean = false;
    }
    class_443 localclass_443 = ((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443;
    this.jdField_field_89_of_type_Class_797 = localclass_443.a43();
    k();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  private class_445 a40()
  {
    return ((class_371)a24()).a14().field_4.field_4;
  }
  
  private class_797 a60()
  {
    return a40().jdField_field_4_of_type_Class_443.a43();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_90_of_type_Class_972 = new class_972(class_969.a2().a5("powerbar-2x4-gui-"), a24());
    this.jdField_field_90_of_type_Class_972.c();
    this.jdField_field_92_of_type_Class_972.c();
    this.jdField_field_90_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_972);
    this.jdField_field_92_of_type_Class_972.a83().field_615 = (this.jdField_field_90_of_type_Class_972.b1() - this.jdField_field_92_of_type_Class_972.b1());
    this.jdField_field_92_of_type_Class_972.a83().field_616 = (this.jdField_field_92_of_type_Class_972.b1() / 2.0F);
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_968.c();
    this.jdField_field_90_of_type_Class_934 = new class_934(a24(), 150, 30, "Filter", new class_175(this));
    this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 220, 30, new class_140(this), new class_142(this), ((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_338);
    this.jdField_field_89_of_type_Class_930 = new class_930(243, 74, this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont, a24());
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.jdField_field_90_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Boolean = false;
    this.jdField_field_89_of_type_Class_934.a83().set(250.0F, 94.0F, 0.0F);
    a9(this.jdField_field_89_of_type_Class_934);
    this.jdField_field_90_of_type_Class_934.a83().set(500.0F, 94.0F, 0.0F);
    a9(this.jdField_field_90_of_type_Class_934);
    try
    {
      f();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
  
  private void f()
  {
    this.jdField_field_89_of_type_Class_964.clear();
    int i = 0;
    Iterator localIterator = ((class_371)a24()).a7().values().iterator();
    while (localIterator.hasNext())
    {
      class_797 localclass_797;
      if (!(localclass_797 = (class_797)localIterator.next()).isHidden())
      {
        Object localObject2 = localclass_797;
        Object localObject1;
        if (((class_340)localObject1).field_684 != 0L) {}
        if ((((localObject2 instanceof class_737)) && ((((class_340)localObject1).field_684 & 0x4) == 4L) ? 1 : ((localObject2 instanceof class_743)) && ((((class_340)localObject1).field_684 & 1L) == 1L) ? 1 : ((localObject2 instanceof class_864)) && ((((class_340)localObject1).field_684 & 0x10) == 16L) ? 1 : ((localObject2 instanceof class_705)) && ((((class_340)localObject1).field_684 & 0x8) == 8L) ? 1 : ((localObject2 instanceof class_750)) && ((((class_340)localObject1).field_684 & 0x40) == 64L) ? 1 : ((localObject2 instanceof class_747)) && ((((class_340)localObject1).field_684 & 0x20) == 32L) ? 1 : (localObject1 = a40().jdField_field_4_of_type_Class_338.field_4).field_684 == 9223372036854775807L ? 1 : 0) != 0)
        {
          localObject1 = new class_847(this, a24(), localclass_797, false);
          localObject2 = new class_847(this, a24(), localclass_797, true);
          localObject1 = new class_845(this, (class_1363)localObject1, (class_1363)localObject2, a24(), i);
          if (localclass_797 == a60()) {
            ((class_845)localObject1).a29(true);
          }
          this.jdField_field_89_of_type_Class_964.a144((class_959)localObject1);
          i++;
        }
      }
    }
  }
  
  public final void e()
  {
    this.jdField_field_90_of_type_Boolean = true;
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObject != null) && (((paramObject instanceof Sendable)) || (paramObject.equals("NAV_UPDATE")))) {
      this.jdField_field_90_of_type_Boolean = true;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_177
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */