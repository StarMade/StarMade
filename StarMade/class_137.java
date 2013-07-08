final class class_137
  extends class_15
{
  class_137(class_371 paramclass_371, Object paramObject1, Object paramObject2, String paramString)
  {
    super(paramclass_371, 100, paramObject1, paramObject2, paramString);
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return null;
  }
  
  public final void onFailedTextCheck(String paramString) {}
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a2()
  {
    this.field_4.a14().field_4.field_4.field_4.e2(false);
  }
  
  public final boolean a7(String paramString)
  {
    try
    {
      if (paramString.length() == 0) {
        this.field_4.a4().a32().a1(null);
      } else {
        this.field_4.a4().a32().a1(class_48.a8(paramString));
      }
      return true;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_137
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */