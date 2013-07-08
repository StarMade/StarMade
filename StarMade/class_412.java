public final class class_412
  extends class_15
{
  public class_412(class_423 paramclass_423, class_371 paramclass_371, Object paramObject1, Object paramObject2)
  {
    super(paramclass_371, 50, paramObject1, paramObject2);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void onFailedTextCheck(String paramString) {}
  
  public final String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return null;
  }
  
  public final String[] getCommandPrefixes()
  {
    return null;
  }
  
  public final boolean a7(String paramString)
  {
    if (this.field_4.a20().h1() != 0)
    {
      class_777 localclass_777 = new class_777(this.field_4.getPlayerName(), paramString, this.field_4.a20().h1(), System.currentTimeMillis());
      this.field_4.a45().c16(localclass_777);
      this.field_4.a4().d1("Invitation sent to " + paramString);
    }
    return true;
  }
  
  public final void a2()
  {
    this.field_4.e2(false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_412
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */