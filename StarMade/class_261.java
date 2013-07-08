import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.schine.network.objects.Sendable;

public final class class_261
  implements Observer, class_965, class_937
{
  private class_113 jdField_field_98_of_type_Class_113;
  private class_371 jdField_field_98_of_type_Class_371;
  private class_44 jdField_field_98_of_type_Class_44;
  private class_1408 jdField_field_98_of_type_Class_1408;
  private class_117 jdField_field_98_of_type_Class_117;
  private class_972 jdField_field_98_of_type_Class_972;
  private class_930 jdField_field_98_of_type_Class_930;
  private class_942 jdField_field_98_of_type_Class_942;
  private float jdField_field_98_of_type_Float = 1.0F;
  private float field_106;
  private float field_108;
  private float field_109 = -1.0F;
  public final LinkedList field_98;
  public final LinkedList field_106;
  public final LinkedList field_108;
  public class_108 field_98;
  public class_108 field_106;
  private class_134 jdField_field_98_of_type_Class_134;
  private int jdField_field_98_of_type_Int = 1;
  private class_881 jdField_field_98_of_type_Class_881;
  private class_115 jdField_field_98_of_type_Class_115;
  private class_914 jdField_field_98_of_type_Class_914;
  private class_242 jdField_field_98_of_type_Class_242;
  
  public class_261(class_44 paramclass_44)
  {
    this.jdField_field_106_of_type_Float = 0.2F;
    this.jdField_field_108_of_type_Float = 0.3F;
    this.jdField_field_98_of_type_JavaUtilLinkedList = new LinkedList();
    this.jdField_field_106_of_type_JavaUtilLinkedList = new LinkedList();
    this.jdField_field_108_of_type_JavaUtilLinkedList = new LinkedList();
    this.jdField_field_98_of_type_Class_371 = paramclass_44.a();
    this.jdField_field_98_of_type_Class_44 = paramclass_44;
    this.jdField_field_98_of_type_Class_44.addObserver(this);
  }
  
  public final void a() {}
  
  private void e()
  {
    this.jdField_field_98_of_type_Class_242.h3(48);
    this.jdField_field_98_of_type_Class_117.h3(48);
    this.jdField_field_98_of_type_Class_930.h3(48);
    this.jdField_field_98_of_type_Class_1408.h3(9);
    this.jdField_field_98_of_type_Class_972.h3(6);
    this.jdField_field_98_of_type_Class_881.h3(48);
  }
  
  public final void b()
  {
    if (class_933.b1()) {
      e();
    }
    GL11.glClear(256);
    if (class_949.field_1226.b1()) {
      return;
    }
    if (class_949.field_1225.b1())
    {
      if (c1()) {
        this.jdField_field_98_of_type_Class_134.b();
      }
      if (!this.jdField_field_98_of_type_Class_371.a14().a18().a76().c()) {
        this.jdField_field_98_of_type_Class_113.b();
      }
      int i = (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a61().g()) && (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a58().g()) && (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a57().g()) && (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a62().c()) && (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a59().g()) && (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a64().g()) && (!this.jdField_field_98_of_type_Class_371.a14().a18().a79().a65().g()) && (!b1()) ? 1 : 0;
      class_1363.i();
      if (i != 0)
      {
        class_40.a("HUD");
        this.jdField_field_98_of_type_Class_881.b();
        class_40.b("HUD");
      }
      else if (b1())
      {
        this.jdField_field_98_of_type_Class_881.a127().e();
      }
      if (this.jdField_field_98_of_type_Class_371.d2()) {
        this.jdField_field_98_of_type_Class_972.b();
      }
      class_1363.h1();
    }
    class_1363.i();
    synchronized (this.jdField_field_98_of_type_Class_371.b())
    {
      for (int k = 0; k < this.jdField_field_98_of_type_Class_371.b().size(); k++) {
        ((class_12)this.jdField_field_98_of_type_Class_371.b().get(k)).a3().b();
      }
    }
    class_1363.field_97 = true;
    for (int j = 0; j < this.jdField_field_98_of_type_Class_371.a10().size(); j++)
    {
      class_12 localclass_12;
      (localclass_12 = (class_12)this.jdField_field_98_of_type_Class_371.a10().get(j)).f();
      localclass_12.a3().b();
      if (System.currentTimeMillis() - localclass_12.a5() > 200L)
      {
        this.jdField_field_98_of_type_Class_371.a10().remove(j);
        j--;
      }
    }
    class_1363.field_97 = false;
    if ((!this.jdField_field_98_of_type_Class_371.a14().a18().a77().c()) && (Keyboard.isKeyDown(class_367.field_754.a5()))) {
      this.jdField_field_98_of_type_Class_117.b();
    }
    if ((!this.jdField_field_98_of_type_Class_371.a14().a18().a77().c()) && (Keyboard.isKeyDown(61))) {
      this.jdField_field_98_of_type_Class_242.b();
    }
    this.jdField_field_98_of_type_Class_1408.b();
    class_1363.h1();
    if (class_949.field_1247.b1())
    {
      if (this.jdField_field_106_of_type_Class_108 != null)
      {
        class_1363.i();
        this.jdField_field_106_of_type_Class_108.b();
        class_1363.h1();
      }
      if (this.jdField_field_98_of_type_Class_108 != null)
      {
        class_1363.i();
        this.jdField_field_98_of_type_Class_108.a83().field_616 = (this.jdField_field_98_of_type_Class_108.a3() + 5.0F);
        this.jdField_field_98_of_type_Class_108.b();
        class_1363.h1();
      }
      synchronized (this.jdField_field_98_of_type_JavaUtilLinkedList)
      {
        for (int m = 0; m < this.jdField_field_98_of_type_JavaUtilLinkedList.size(); m++)
        {
          class_1363.i();
          ((class_110)this.jdField_field_98_of_type_JavaUtilLinkedList.get(m)).b();
          class_1363.h1();
        }
      }
      synchronized (this.jdField_field_106_of_type_JavaUtilLinkedList)
      {
        for (int n = 0; n < this.jdField_field_106_of_type_JavaUtilLinkedList.size(); n++)
        {
          class_1363.i();
          ((class_132)this.jdField_field_106_of_type_JavaUtilLinkedList.get(n)).b();
          class_1363.h1();
        }
      }
      synchronized (this.jdField_field_108_of_type_JavaUtilLinkedList)
      {
        for (int i1 = 0; i1 < this.jdField_field_108_of_type_JavaUtilLinkedList.size(); i1++)
        {
          class_1363.i();
          ((class_238)this.jdField_field_108_of_type_JavaUtilLinkedList.get(i1)).b();
          class_1363.h1();
        }
      }
    }
    if (((class_949.field_1232.b1()) && (this.jdField_field_98_of_type_Class_371.a14().a19().c()) ? 1 : 0) != 0) {
      if ((this.jdField_field_98_of_type_Class_371.a4().a5() != null) && (this.jdField_field_98_of_type_Class_371.a4().a6()))
      {
        class_1363.i();
        this.jdField_field_98_of_type_Class_914.b();
        class_1363.h1();
      }
    }
    class_1363.i();
    this.jdField_field_98_of_type_Class_115.a29(this.jdField_field_98_of_type_Class_371.a14().a17().g());
    this.jdField_field_98_of_type_Class_115.b();
    class_1363.h1();
    ??? = this;
    if (this.field_109 < ((class_261)???).jdField_field_106_of_type_Float)
    {
      ((class_261)???).jdField_field_98_of_type_Class_942.a138().field_143 = (((class_261)???).field_109 / ((class_261)???).jdField_field_106_of_type_Float * ((class_261)???).jdField_field_108_of_type_Float);
      class_1363.i();
      ((class_261)???).jdField_field_98_of_type_Class_942.b();
      class_1363.h1();
    }
    if (class_1046.field_1307)
    {
      class_1363.i();
      this.jdField_field_98_of_type_Class_930.b();
      class_1363.h1();
    }
    this.jdField_field_98_of_type_Class_113.e();
  }
  
  private boolean b1()
  {
    return this.jdField_field_98_of_type_Class_371.a14().a18().a79().a66().g();
  }
  
  private void a15(int paramInt, class_16 paramclass_16)
  {
    int i = paramInt;
    if ((this.jdField_field_98_of_type_Int & paramInt) == paramInt) {
      i = 0;
    }
    this.jdField_field_98_of_type_Int += (paramclass_16.g() ? i : -paramInt);
  }
  
  public final boolean a5()
  {
    return this.jdField_field_98_of_type_Class_113.a4();
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Class_113 = new class_113(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_113.c();
    this.jdField_field_98_of_type_Class_942 = new class_942(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_942.a138().set(1.0F, 0.0F, 0.0F, 0.0F);
    this.jdField_field_98_of_type_Class_942.c();
    this.jdField_field_98_of_type_Class_117 = new class_117(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_242 = new class_242(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_117.c();
    this.jdField_field_98_of_type_Class_242.c();
    this.jdField_field_98_of_type_Class_115 = new class_115(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_115.c();
    this.jdField_field_98_of_type_Class_881 = new class_881(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_881.c();
    this.jdField_field_98_of_type_Class_1408 = new class_1408(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_1408.a17("use \"/pm playername msg\" for PMs, and \"/f msg\" for faction chat");
    this.jdField_field_98_of_type_Class_1408.a172(this.jdField_field_98_of_type_Class_371.getChat());
    this.jdField_field_98_of_type_Class_1408.c();
    this.jdField_field_98_of_type_Class_972 = new class_972(class_969.a2().a5("shopinrange-gui-"), this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_972.c();
    this.jdField_field_98_of_type_Class_914 = new class_914(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_914.h3(10);
    this.jdField_field_98_of_type_Class_134 = new class_134(this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_134.c();
    Object localObject = new Font("Arial", 1, 26);
    (localObject = new UnicodeFont((Font)localObject)).getEffects().add(new OutlineEffect(4, Color.black));
    ((UnicodeFont)localObject).getEffects().add(new ColorEffect(Color.white));
    ((UnicodeFont)localObject).addAsciiGlyphs();
    try
    {
      ((UnicodeFont)localObject).loadGlyphs();
    }
    catch (SlickException localSlickException)
    {
      localSlickException;
    }
    this.jdField_field_98_of_type_Class_930 = new class_930(500, 50, (UnicodeFont)localObject, this.jdField_field_98_of_type_Class_371);
    this.jdField_field_98_of_type_Class_930.b16(new ArrayList());
    this.jdField_field_98_of_type_Class_930.b15().add("Detached Mouse From Game. Click to reattach");
    e();
  }
  
  public final void d()
  {
    this.jdField_field_98_of_type_Class_881.e();
  }
  
  private boolean c1()
  {
    return this.jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a51().a45().a36().g();
  }
  
  public final void a16(Sendable paramSendable)
  {
    if (this.field_109 < 0.0F)
    {
      this.field_109 = 0.0F;
      this.jdField_field_98_of_type_Float = 1.0F;
      if ((paramSendable != null) && ((paramSendable instanceof class_798)) && (((class_798)paramSendable instanceof ShieldContainerInterface)) && (((ShieldContainerInterface)paramSendable).getShieldManager().getShields() > 0.0D))
      {
        this.jdField_field_98_of_type_Class_942.a138().field_140 = 0.0F;
        this.jdField_field_98_of_type_Class_942.a138().field_141 = 0.0F;
        this.jdField_field_98_of_type_Class_942.a138().field_142 = 1.0F;
        return;
      }
      this.jdField_field_98_of_type_Class_942.a138().field_140 = 1.0F;
      this.jdField_field_98_of_type_Class_942.a138().field_141 = 0.0F;
      this.jdField_field_98_of_type_Class_942.a138().field_142 = 0.0F;
    }
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if ("ON_SWITCH".equals(paramObject))
    {
      if ((paramObservable instanceof class_455))
      {
        a15(8, (class_16)paramObservable);
        if (this.jdField_field_98_of_type_Class_1408 != null) {
          this.jdField_field_98_of_type_Class_1408.a29(((class_455)paramObservable).c());
        }
      }
      if ((paramObservable instanceof class_469)) {
        a15(2, (class_16)paramObservable);
      }
      if ((paramObservable instanceof class_332)) {
        a15(64, (class_16)paramObservable);
      }
      if ((paramObservable instanceof class_303)) {
        a15(4, (class_16)paramObservable);
      }
      if ((paramObservable instanceof class_330)) {
        a15(16, (class_16)paramObservable);
      }
      if ((paramObservable instanceof class_24)) {
        a15(32, (class_16)paramObservable);
      }
    }
  }
  
  public final void a1(class_941 paramclass_941)
  {
    if (this.field_109 >= 0.0F)
    {
      this.field_109 += this.jdField_field_98_of_type_Float * (paramclass_941.a() * 2.0F);
      if (this.field_109 > this.jdField_field_106_of_type_Float) {
        this.jdField_field_98_of_type_Float = -1.0F;
      }
    }
    else
    {
      this.jdField_field_98_of_type_Float = 1.0F;
    }
    this.jdField_field_98_of_type_Class_1408.a12(paramclass_941);
    if (!c1())
    {
      if (this.jdField_field_106_of_type_Class_108 != null) {
        this.jdField_field_106_of_type_Class_108.e();
      }
      if (this.jdField_field_98_of_type_Class_108 != null) {
        this.jdField_field_98_of_type_Class_108.e();
      }
    }
    if (this.jdField_field_106_of_type_Class_108 != null) {
      this.jdField_field_106_of_type_Class_108.a12(paramclass_941);
    }
    if (this.jdField_field_98_of_type_Class_108 != null) {
      this.jdField_field_98_of_type_Class_108.a12(paramclass_941);
    }
    if ((!this.jdField_field_98_of_type_Class_371.a14().a18().a77().c()) && (Keyboard.isKeyDown(class_367.field_754.a5()))) {
      this.jdField_field_98_of_type_Class_117.a12(paramclass_941);
    }
    if ((!this.jdField_field_98_of_type_Class_371.a14().a18().a77().c()) && (Keyboard.isKeyDown(61))) {
      this.jdField_field_98_of_type_Class_242.a12(paramclass_941);
    }
    this.jdField_field_98_of_type_Class_113.a12(paramclass_941);
    this.jdField_field_98_of_type_Class_881.a12(paramclass_941);
    class_192.field_89.a(paramclass_941);
    int i;
    synchronized (this.jdField_field_98_of_type_JavaUtilLinkedList)
    {
      for (i = 0; i < this.jdField_field_98_of_type_JavaUtilLinkedList.size(); i++)
      {
        ((class_110)this.jdField_field_98_of_type_JavaUtilLinkedList.get(i)).a12(paramclass_941);
        if (!((class_110)this.jdField_field_98_of_type_JavaUtilLinkedList.get(i)).a4())
        {
          this.jdField_field_98_of_type_JavaUtilLinkedList.remove(i);
          i--;
        }
        else
        {
          ((class_110)this.jdField_field_98_of_type_JavaUtilLinkedList.get(i)).field_89 = i;
        }
      }
    }
    synchronized (this.jdField_field_106_of_type_JavaUtilLinkedList)
    {
      for (i = 0; i < this.jdField_field_106_of_type_JavaUtilLinkedList.size(); i++)
      {
        ((class_132)this.jdField_field_106_of_type_JavaUtilLinkedList.get(i)).a12(paramclass_941);
        if (!((class_132)this.jdField_field_106_of_type_JavaUtilLinkedList.get(i)).a4())
        {
          this.jdField_field_106_of_type_JavaUtilLinkedList.remove(i);
          i--;
        }
        else
        {
          ((class_132)this.jdField_field_106_of_type_JavaUtilLinkedList.get(i)).field_89 = i;
        }
      }
    }
    synchronized (this.jdField_field_108_of_type_JavaUtilLinkedList)
    {
      for (i = 0; i < this.jdField_field_108_of_type_JavaUtilLinkedList.size(); i++)
      {
        ((class_238)this.jdField_field_108_of_type_JavaUtilLinkedList.get(i)).a12(paramclass_941);
        if (!((class_238)this.jdField_field_108_of_type_JavaUtilLinkedList.get(i)).a4())
        {
          this.jdField_field_108_of_type_JavaUtilLinkedList.remove(i);
          i--;
        }
        else
        {
          ((class_238)this.jdField_field_108_of_type_JavaUtilLinkedList.get(i)).field_89 = i;
        }
      }
      return;
    }
  }
  
  public final void a17(ElementCollectionManager paramElementCollectionManager)
  {
    this.jdField_field_98_of_type_Class_113.a21(paramElementCollectionManager);
  }
  
  public final class_113 a18()
  {
    return this.jdField_field_98_of_type_Class_113;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_261
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */