import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.common.ParseException;

public final class class_58
  implements Serializable
{
  private ArrayList field_525 = new ArrayList();
  private static final long serialVersionUID = 592607324358471017L;
  
  private static class_64 a(String paramString)
  {
    for (class_64 localclass_64 : ) {
      if (paramString.equals(localclass_64.toString())) {
        return localclass_64;
      }
    }
    throw new ParseException("-- PARSING ERROR: Type not known: " + paramString);
  }
  
  public final void a1(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = a(paramString2);
      paramString1 = paramString1;
      Object localObject = null;
      switch (class_65.field_533[paramString2.ordinal()])
      {
      case 1: 
        localObject = new class_57(paramString1, paramString2);
        break;
      case 2: 
        localObject = new class_56(paramString1, paramString2);
        break;
      case 3: 
        localObject = new class_63(paramString1, paramString2);
        break;
      case 4: 
        localObject = new class_62(Boolean.valueOf(Boolean.parseBoolean(paramString1)), paramString2);
        break;
      case 5: 
        localObject = new class_60(Boolean.valueOf(Boolean.parseBoolean(paramString1)), paramString2);
      }
      if (localObject == null) {
        throw new ParseException("Type " + paramString2.name() + " not found");
      }
      this.field_525.add(localObject);
      return;
    }
    catch (ParseException localParseException)
    {
      localParseException;
    }
  }
  
  public final class_59 a2(String paramString)
  {
    class_64 localclass_64 = a(paramString);
    paramString = this;
    Iterator localIterator = this.field_525.iterator();
    while (localIterator.hasNext())
    {
      class_59 localclass_59;
      if ((localclass_59 = (class_59)localIterator.next()).field_526.equals(localclass_64)) {
        return localclass_59;
      }
    }
    throw new ParseException("could not find Type \"" + localclass_64 + "\" in Parsed XML! Existing Types: " + paramString.field_525);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_58
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */