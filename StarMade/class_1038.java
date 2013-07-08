public final class class_1038
  extends class_1042
{
  private String field_230;
  
  public final void a()
  {
    class_1071 tmp11_8 = ((class_1071)null.a2()[0]);
    tmp11_8.field_19 = Math.max(tmp11_8.field_19 - 1, 0);
    null.a1();
  }
  
  public final String a1()
  {
    null.a2();
    return null;
  }
  
  public final Object a3()
  {
    Object localObject;
    if (!(localObject = null.a2()[0].a15()).equals(this.field_230))
    {
      class_1040.a2();
      this.field_230 = localObject.toString();
    }
    return localObject;
  }
  
  public final void a2(Object paramObject)
  {
    class_1071 localclass_1071 = (class_1071)null.a2()[0];
    int i = 0;
    for (int j = 0; j < null.length; j++) {
      if (null.equals(paramObject.toString()))
      {
        i = j;
        break;
      }
    }
    localclass_1071.field_19 = i;
    null.a1();
    class_1040.a2();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1038
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */