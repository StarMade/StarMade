import javax.vecmath.Vector3f;

public abstract class class_944
{
  public int field_10;
  public class_950 field_9;
  public boolean field_11;
  protected int field_11;
  
  public class_944()
  {
    this.field_9 = new class_950();
  }
  
  public class_944(int paramInt)
  {
    this.field_9 = new class_950(paramInt);
  }
  
  public final int a14(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    if (this.field_10 >= this.field_9.a1() - 1) {
      this.field_9.a8();
    }
    if (this.jdField_field_11_of_type_Boolean)
    {
      i = this.field_10 % this.field_9.a1();
      this.field_9.a7(i, paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
      this.field_9.b1(i, paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
      this.field_9.d1(i, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
      this.field_9.a6(i, 0.0F);
      this.field_10 += 1;
      return i;
    }
    int i = this.field_10 % this.field_9.a1();
    this.field_9.a7(i, paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    this.field_9.b1(i, paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617);
    this.field_9.d1(i, paramVector3f2.field_615, paramVector3f2.field_616, paramVector3f2.field_617);
    this.field_9.a6(i, 0.0F);
    this.field_10 += 1;
    return i;
  }
  
  public final void b(int paramInt)
  {
    if (this.jdField_field_11_of_type_Boolean)
    {
      this.jdField_field_11_of_type_Int += 1;
      return;
    }
    this.field_9.a((this.field_10 - 1) % this.field_9.a1(), paramInt);
    this.field_10 -= 1;
  }
  
  public final int a15()
  {
    return this.jdField_field_11_of_type_Int;
  }
  
  public final int b1()
  {
    return this.field_10 - this.jdField_field_11_of_type_Int;
  }
  
  protected void a4() {}
  
  public final void b2()
  {
    this.field_10 = 0;
    this.jdField_field_11_of_type_Int = 0;
  }
  
  public abstract boolean a7(int paramInt, class_941 paramclass_941);
  
  public void a6(class_941 paramclass_941)
  {
    a4();
    if (this.jdField_field_11_of_type_Boolean)
    {
      for (i = this.jdField_field_11_of_type_Int; i < this.field_10; i++)
      {
        int j = i % this.field_9.a1();
        if (!a7(j, paramclass_941)) {
          b(j);
        }
      }
      return;
    }
    for (int i = 0; i < this.field_10; i++) {
      if (!a7(i, paramclass_941))
      {
        b(i);
        i--;
      }
    }
    class_949.field_1227.b1();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_944
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */