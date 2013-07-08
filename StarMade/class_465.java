import org.schema.game.common.data.element.ElementKeyMap;

final class class_465
  implements class_956
{
  class_465(short paramShort) {}
  
  public final boolean a(String paramString, class_1079 paramclass_1079)
  {
    try
    {
      if (paramString.length() > 0)
      {
        ElementKeyMap.getInfo(this.field_130);
        Integer.parseInt(paramString);
        return true;
      }
    }
    catch (NumberFormatException localNumberFormatException) {}
    paramclass_1079.onFailedTextCheck("Amount must be a number!");
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_465
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */