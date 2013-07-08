public final class class_34
{
  public float field_450;
  public float field_451;
  public float field_452;
  
  public class_34() {}
  
  public class_34(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.field_450 = paramFloat1;
    this.field_451 = paramFloat2;
    this.field_452 = paramFloat3;
  }
  
  public final boolean equals(Object paramObject)
  {
    if ((paramObject instanceof class_34))
    {
      paramObject = (class_34)paramObject;
      return (this.field_450 == paramObject.field_450) && (this.field_451 == paramObject.field_451) && (this.field_452 == paramObject.field_452);
    }
    return false;
  }
  
  public final String toString()
  {
    return "[" + this.field_450 + ", " + this.field_451 + ", " + this.field_452 + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_34
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */