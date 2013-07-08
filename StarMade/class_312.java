final class class_312
  implements class_956
{
  class_312(class_314 paramclass_314) {}
  
  public final boolean a(String paramString, class_1079 paramclass_1079)
  {
    try
    {
      if (paramString.length() > 0)
      {
        if (Integer.parseInt(paramString) <= 0)
        {
          this.field_130.a6().a4().b1("ERROR: Invalid quantity");
          return false;
        }
        class_969.b("0022_action - purchase with credits");
        return true;
      }
    }
    catch (NumberFormatException localNumberFormatException) {}
    paramclass_1079.onFailedTextCheck("Please only enter numbers!");
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_312
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */