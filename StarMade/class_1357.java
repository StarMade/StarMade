import javax.vecmath.Vector3f;

public class class_1357
  extends class_944
{
  private Vector3f field_9 = new Vector3f();
  private Vector3f field_10 = new Vector3f();
  
  public class_1357()
  {
    super(16);
  }
  
  public boolean a7(int paramInt, class_941 paramclass_941)
  {
    paramclass_941 = this.field_9.a3(paramInt);
    this.field_9.a4(paramInt, this.field_9);
    this.field_9.d(paramInt, this.field_10);
    this.field_9.a7(paramInt, this.field_9.field_615 + this.field_10.field_615, this.field_9.field_616 + this.field_10.field_616, this.field_9.field_617 + this.field_10.field_617);
    return paramclass_941 < 1300.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1357
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */