import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.schema.schine.graphicsengine.core.ResourceException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class class_53
  extends DefaultHandler
{
  private static class_55 field_518;
  private class_55 field_519;
  
  public static class_55 a()
  {
    return field_518;
  }
  
  public final void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2) {}
  
  public final void endDocument() {}
  
  public final void endElement(String paramString1, String paramString2, String paramString3)
  {
    if (("".equals(paramString2) ? paramString3 : paramString2).equals(this.field_519.jdField_field_522_of_type_JavaLangString))
    {
      this.field_519.jdField_field_522_of_type_Boolean = true;
      if (this.field_519.jdField_field_522_of_type_Class_55 != null) {
        this.field_519 = this.field_519.jdField_field_522_of_type_Class_55;
      }
    }
  }
  
  public final void a1(String paramString)
  {
    try
    {
      long l = System.currentTimeMillis();
      System.out.println("[XMLParser] Parsing main configuration XML File: data/" + paramString);
      SAXParser localSAXParser = SAXParserFactory.newInstance().newSAXParser();
      try
      {
        localSAXParser.parse(class_73.field_134.a2("data/" + paramString), this);
      }
      catch (ResourceException localResourceException)
      {
        localResourceException;
      }
      System.out.println("[XMLParser] DONE Parsing main configuration. TIME: " + (System.currentTimeMillis() - l) + "ms");
      return;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      localParserConfigurationException.printStackTrace();
      return;
    }
    catch (SAXException localSAXException)
    {
      localSAXException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public final void startDocument() {}
  
  public final void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    paramString1 = "".equals(paramString2) ? paramString3 : paramString2;
    if (field_518 == null)
    {
      (class_53.field_518 = new class_55()).jdField_field_522_of_type_JavaLangString = paramString1;
      this.field_519 = field_518;
      return;
    }
    (paramString2 = new class_55()).jdField_field_522_of_type_JavaLangString = paramString1;
    if (paramAttributes != null)
    {
      paramString2.jdField_field_522_of_type_Class_58 = new class_58();
      for (paramString1 = 0; paramString1 < paramAttributes.getLength(); paramString1++)
      {
        paramString3 = paramAttributes.getLocalName(paramString1);
        if ("".equals(paramString3)) {
          paramString3 = paramAttributes.getQName(paramString1);
        }
        paramString2.jdField_field_522_of_type_Class_58.a1(paramAttributes.getValue(paramString1), paramString3);
      }
    }
    this.field_519.jdField_field_522_of_type_JavaUtilLinkedList.add(paramString2);
    paramString2.jdField_field_522_of_type_Class_55 = this.field_519;
    if (!this.field_519.jdField_field_522_of_type_Boolean) {
      this.field_519 = paramString2;
    }
  }
  
  static
  {
    System.getProperty("line.separator");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_53
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */