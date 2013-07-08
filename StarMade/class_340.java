public final class class_340
{
  public long field_684 = 117L;
  
  public class_340(class_340 paramclass_340)
  {
    this.field_684 = paramclass_340.field_684;
  }
  
  public class_340() {}
  
  public final void a(boolean paramBoolean, long paramLong)
  {
    if (paramBoolean)
    {
      this.field_684 |= paramLong;
      return;
    }
    this.field_684 &= (paramLong ^ 0xFFFFFFFF);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_340
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */