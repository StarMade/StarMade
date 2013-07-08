import javax.vecmath.Vector4f;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public final class class_244
  extends class_196
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private final class_781 jdField_field_90_of_type_Class_781;
  private class_1414 jdField_field_90_of_type_Class_1414;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private class_930 jdField_field_93_of_type_Class_930;
  private class_930 field_94;
  private class_930 field_95;
  private class_1404 jdField_field_89_of_type_Class_1404;
  private class_1404 jdField_field_90_of_type_Class_1404;
  private class_1404 jdField_field_92_of_type_Class_1404;
  private class_1404 jdField_field_93_of_type_Class_1404;
  final class_781 jdField_field_89_of_type_Class_781;
  private boolean jdField_field_90_of_type_Boolean;
  
  public class_244(class_371 paramclass_371, class_781 paramclass_781, class_428 paramclass_428, boolean paramBoolean)
  {
    super(paramclass_371, paramclass_428, "Edit Entry Permission", "Edit Entry Permission");
    this.jdField_field_90_of_type_Boolean = paramBoolean;
    this.jdField_field_90_of_type_Class_781 = paramclass_781;
    this.jdField_field_89_of_type_Class_781 = new class_781(paramclass_781);
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 410.0F, 90.0F, new Vector4f(0.1F, 0.1F, 0.1F, 1.0F));
  }
  
  public final void c()
  {
    super.c();
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24(), 400.0F, 100.0F);
    this.jdField_field_93_of_type_Class_930 = new class_930(100, 20, a24());
    this.field_94 = new class_930(100, 20, a24());
    this.field_95 = new class_930(100, 20, a24());
    this.jdField_field_92_of_type_Class_930 = new class_930(100, 20, a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(100, 20, a24());
    this.jdField_field_92_of_type_Class_930.a136("Faction");
    this.jdField_field_93_of_type_Class_930.a136("Others");
    this.field_94.a136("HomeBase");
    this.field_95.a136("EnemyUsable");
    this.jdField_field_90_of_type_Class_930.a136("Permissions");
    this.jdField_field_89_of_type_Class_1404 = new class_246(this, a24());
    this.jdField_field_90_of_type_Class_1404 = new class_252(this, a24());
    this.jdField_field_92_of_type_Class_1404 = new class_100(this, a24());
    this.jdField_field_93_of_type_Class_1404 = new class_102(this, a24());
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_92_of_type_Class_930.a161(0.0F, 20.0F, 0.0F);
    this.jdField_field_93_of_type_Class_930.a161(70.0F, 20.0F, 0.0F);
    this.field_94.a161(140.0F, 20.0F, 0.0F);
    this.field_95.a161(210.0F, 20.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_93_of_type_Class_930);
    this.jdField_field_90_of_type_Class_1414.a9(this.field_94);
    this.jdField_field_89_of_type_Class_1404.a161(0.0F, 35.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1404.a161(70.0F, 35.0F, 0.0F);
    this.jdField_field_92_of_type_Class_1404.a161(140.0F, 35.0F, 0.0F);
    this.jdField_field_93_of_type_Class_1404.a161(210.0F, 35.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_1404);
    this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_92_of_type_Class_1404);
    if ((this.jdField_field_90_of_type_Boolean) && (((Boolean)((class_371)a24()).a20().a127().isAdminClient.get()).booleanValue()))
    {
      this.jdField_field_90_of_type_Class_1414.a9(this.field_95);
      this.jdField_field_90_of_type_Class_1414.a9(this.jdField_field_93_of_type_Class_1404);
    }
    this.jdField_field_90_of_type_Class_1414.a83().field_616 = 20.0F;
    this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_90_of_type_Class_1414);
    class_930 localclass_930;
    (localclass_930 = new class_930(100, 20, a24())).a136("Specify the permissions for " + this.jdField_field_90_of_type_Class_781.field_136);
    this.jdField_field_89_of_type_Class_1402.a9(localclass_930);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_1402);
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
  
  public final class_781 a108()
  {
    return this.jdField_field_89_of_type_Class_781;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_244
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */