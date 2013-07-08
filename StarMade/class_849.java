final class class_849
  implements class_956
{
  public final boolean a(String paramString, class_1079 paramclass_1079)
  {
    try
    {
      if (paramString.length() == 0) {
        return true;
      }
      class_48.a8(paramString);
      return true;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      paramclass_1079.onFailedTextCheck("Wrong Format. Must be x, y, z");
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_849
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */