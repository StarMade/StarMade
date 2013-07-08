package org.newdawn.slick.svg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.inkscape.DefsProcessor;
import org.newdawn.slick.svg.inkscape.ElementProcessor;
import org.newdawn.slick.svg.inkscape.EllipseProcessor;
import org.newdawn.slick.svg.inkscape.GroupProcessor;
import org.newdawn.slick.svg.inkscape.LineProcessor;
import org.newdawn.slick.svg.inkscape.PathProcessor;
import org.newdawn.slick.svg.inkscape.PolygonProcessor;
import org.newdawn.slick.svg.inkscape.RectProcessor;
import org.newdawn.slick.svg.inkscape.UseProcessor;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class InkscapeLoader
  implements Loader
{
  public static int RADIAL_TRIANGULATION_LEVEL = 1;
  private static ArrayList processors = new ArrayList();
  private Diagram diagram;
  
  public static void addElementProcessor(ElementProcessor proc)
  {
    processors.add(proc);
  }
  
  public static Diagram load(String ref, boolean offset)
    throws SlickException
  {
    return load(ResourceLoader.getResourceAsStream(ref), offset);
  }
  
  public static Diagram load(String ref)
    throws SlickException
  {
    return load(ResourceLoader.getResourceAsStream(ref), false);
  }
  
  public static Diagram load(InputStream local_in, boolean offset)
    throws SlickException
  {
    return new InkscapeLoader().loadDiagram(local_in, offset);
  }
  
  private Diagram loadDiagram(InputStream local_in)
    throws SlickException
  {
    return loadDiagram(local_in, false);
  }
  
  private Diagram loadDiagram(InputStream local_in, boolean offset)
    throws SlickException
  {
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setEntityResolver(new EntityResolver()
      {
        public InputSource resolveEntity(String publicId, String systemId)
          throws SAXException, IOException
        {
          return new InputSource(new ByteArrayInputStream(new byte[0]));
        }
      });
      Document doc = builder.parse(local_in);
      Element root = doc.getDocumentElement();
      for (String widthString = root.getAttribute("width"); Character.isLetter(widthString.charAt(widthString.length() - 1)); widthString = widthString.substring(0, widthString.length() - 1)) {}
      for (String heightString = root.getAttribute("height"); Character.isLetter(heightString.charAt(heightString.length() - 1)); heightString = heightString.substring(0, heightString.length() - 1)) {}
      float docWidth = Float.parseFloat(widthString);
      float docHeight = Float.parseFloat(heightString);
      this.diagram = new Diagram(docWidth, docHeight);
      if (!offset) {
        docHeight = 0.0F;
      }
      loadChildren(root, Transform.createTranslateTransform(0.0F, -docHeight));
      return this.diagram;
    }
    catch (Exception factory)
    {
      throw new SlickException("Failed to load inkscape document", factory);
    }
  }
  
  public void loadChildren(Element element, Transform local_t)
    throws ParsingException
  {
    NodeList list = element.getChildNodes();
    for (int local_i = 0; local_i < list.getLength(); local_i++) {
      if ((list.item(local_i) instanceof Element)) {
        loadElement((Element)list.item(local_i), local_t);
      }
    }
  }
  
  private void loadElement(Element element, Transform local_t)
    throws ParsingException
  {
    for (int local_i = 0; local_i < processors.size(); local_i++)
    {
      ElementProcessor processor = (ElementProcessor)processors.get(local_i);
      if (processor.handles(element)) {
        processor.process(this, element, this.diagram, local_t);
      }
    }
  }
  
  static
  {
    addElementProcessor(new RectProcessor());
    addElementProcessor(new EllipseProcessor());
    addElementProcessor(new PolygonProcessor());
    addElementProcessor(new PathProcessor());
    addElementProcessor(new LineProcessor());
    addElementProcessor(new GroupProcessor());
    addElementProcessor(new DefsProcessor());
    addElementProcessor(new UseProcessor());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.svg.InkscapeLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */