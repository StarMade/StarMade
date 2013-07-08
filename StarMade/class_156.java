import java.io.PrintStream;
import java.util.HashMap;
import javax.vecmath.Vector4f;
import org.schema.game.common.data.player.faction.FactionNotFoundException;

public final class class_156
  extends class_1363
  implements class_1412
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_773 jdField_field_89_of_type_Class_773;
  private class_625 jdField_field_89_of_type_Class_625;
  private boolean field_100;
  boolean jdField_field_89_of_type_Boolean;
  boolean jdField_field_90_of_type_Boolean;
  boolean jdField_field_92_of_type_Boolean;
  boolean jdField_field_93_of_type_Boolean;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_934 jdField_field_89_of_type_Class_934;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private class_930 jdField_field_93_of_type_Class_930;
  private class_930 field_94;
  private class_1404 jdField_field_89_of_type_Class_1404;
  private class_1404 jdField_field_90_of_type_Class_1404;
  private class_1404 jdField_field_92_of_type_Class_1404;
  private class_1404 jdField_field_93_of_type_Class_1404;
  private String jdField_field_90_of_type_JavaLangString;
  int jdField_field_89_of_type_Int;
  
  public class_156(class_371 paramclass_371, class_773 paramclass_773, int paramInt)
  {
    super(paramclass_371);
    this.jdField_field_89_of_type_Class_773 = paramclass_773;
    this.jdField_field_89_of_type_Class_625 = paramclass_773.a180();
    this.jdField_field_89_of_type_Int = paramInt;
    this.jdField_field_93_of_type_Boolean = this.jdField_field_89_of_type_Class_625.a18(paramInt);
    this.jdField_field_89_of_type_Boolean = this.jdField_field_89_of_type_Class_625.b3(paramInt);
    this.jdField_field_90_of_type_Boolean = this.jdField_field_89_of_type_Class_625.c(paramInt);
    this.jdField_field_92_of_type_Boolean = this.jdField_field_89_of_type_Class_625.d(paramInt);
    this.jdField_field_90_of_type_JavaLangString = this.jdField_field_89_of_type_Class_625.a26()[paramInt];
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 410.0F, 90.0F, paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.2F, 0.2F, 0.2F, 1.0F));
  }
  
  public final void c()
  {
    try
    {
      Object localObject = this;
      int i = ((class_371)a24()).a20().a141().a3();
      class_773 localclass_773;
      if ((localclass_773 = ((class_371)((class_156)localObject).a24()).a12().a51().a156(i)) == null) {
        throw new FactionNotFoundException(Integer.valueOf(i));
      }
      if ((localObject = (class_758)localclass_773.a162().get(((class_371)((class_156)localObject).a24()).a20().getName())) == null) {
        throw new FactionNotFoundException(Integer.valueOf(i));
      }
      if ((localObject = localObject).d7(this.jdField_field_89_of_type_Class_773))
      {
        this.jdField_field_89_of_type_Class_1414 = new class_1414(a24(), 400.0F, 100.0F);
        this.jdField_field_92_of_type_Class_930 = new class_930(100, 20, a24());
        this.jdField_field_93_of_type_Class_930 = new class_930(100, 20, a24());
        this.field_94 = new class_930(100, 20, a24());
        this.jdField_field_90_of_type_Class_930 = new class_930(100, 20, a24());
        this.jdField_field_89_of_type_Class_930 = new class_930(100, 20, a24());
        this.jdField_field_90_of_type_Class_930.a136("Edit");
        this.jdField_field_92_of_type_Class_930.a136("Kick");
        this.jdField_field_93_of_type_Class_930.a136("Invite");
        this.field_94.a136("Permission-Edit");
        this.jdField_field_89_of_type_Class_930.a136("Permissions");
        this.jdField_field_89_of_type_Class_1404 = new class_151(this, a24());
        this.jdField_field_90_of_type_Class_1404 = new class_153(this, a24());
        this.jdField_field_92_of_type_Class_1404 = new class_147(this, a24());
        this.jdField_field_93_of_type_Class_1404 = new class_149(this, a24());
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_930);
        this.jdField_field_90_of_type_Class_930.a161(0.0F, 20.0F, 0.0F);
        this.jdField_field_92_of_type_Class_930.a161(70.0F, 20.0F, 0.0F);
        this.jdField_field_93_of_type_Class_930.a161(140.0F, 20.0F, 0.0F);
        this.field_94.a161(210.0F, 20.0F, 0.0F);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_930);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_930);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_93_of_type_Class_930);
        this.jdField_field_89_of_type_Class_1414.a9(this.field_94);
        this.jdField_field_89_of_type_Class_1404.a161(0.0F, 35.0F, 0.0F);
        this.jdField_field_90_of_type_Class_1404.a161(70.0F, 35.0F, 0.0F);
        this.jdField_field_92_of_type_Class_1404.a161(140.0F, 35.0F, 0.0F);
        this.jdField_field_93_of_type_Class_1404.a161(210.0F, 35.0F, 0.0F);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_1404);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_1404);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_1404);
        this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_93_of_type_Class_1404);
        this.jdField_field_89_of_type_Class_1414.a83().field_616 = 20.0F;
        this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_1414);
        this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 200, 20, new class_143(this), new class_145(this));
        this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_934);
      }
      if (!((class_758)localObject).d7(this.jdField_field_89_of_type_Class_773))
      {
        (localObject = new class_930(100, 20, a24())).a136("You don't have permission to edit this member!");
        this.jdField_field_89_of_type_Class_1402.a9((class_1363)localObject);
      }
    }
    catch (FactionNotFoundException localFactionNotFoundException)
    {
      class_930 localclass_930;
      (localclass_930 = new class_930(100, 20, a24())).a136("You don't have permission to edit this!");
      this.jdField_field_89_of_type_Class_1402.a9(localclass_930);
      localFactionNotFoundException.printStackTrace();
    }
    a9(this.jdField_field_89_of_type_Class_1402);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    k();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_1402.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_1402.b1();
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    System.err.println("TODO CALLBACK");
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final boolean b3()
  {
    return this.jdField_field_89_of_type_Boolean;
  }
  
  public final boolean c1()
  {
    return this.jdField_field_90_of_type_Boolean;
  }
  
  public final boolean d1()
  {
    return this.jdField_field_92_of_type_Boolean;
  }
  
  public final boolean e1()
  {
    return this.jdField_field_93_of_type_Boolean;
  }
  
  public final String a16()
  {
    return this.jdField_field_90_of_type_JavaLangString;
  }
  
  public final boolean f1()
  {
    return this.field_100;
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.field_100 = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_156
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */