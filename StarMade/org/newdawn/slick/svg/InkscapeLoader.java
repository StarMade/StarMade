/*   1:    */package org.newdawn.slick.svg;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.util.ArrayList;
/*   7:    */import javax.xml.parsers.DocumentBuilder;
/*   8:    */import javax.xml.parsers.DocumentBuilderFactory;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.geom.Transform;
/*  11:    */import org.newdawn.slick.svg.inkscape.DefsProcessor;
/*  12:    */import org.newdawn.slick.svg.inkscape.ElementProcessor;
/*  13:    */import org.newdawn.slick.svg.inkscape.EllipseProcessor;
/*  14:    */import org.newdawn.slick.svg.inkscape.GroupProcessor;
/*  15:    */import org.newdawn.slick.svg.inkscape.LineProcessor;
/*  16:    */import org.newdawn.slick.svg.inkscape.PathProcessor;
/*  17:    */import org.newdawn.slick.svg.inkscape.PolygonProcessor;
/*  18:    */import org.newdawn.slick.svg.inkscape.RectProcessor;
/*  19:    */import org.newdawn.slick.svg.inkscape.UseProcessor;
/*  20:    */import org.newdawn.slick.util.ResourceLoader;
/*  21:    */import org.w3c.dom.Document;
/*  22:    */import org.w3c.dom.Element;
/*  23:    */import org.w3c.dom.NodeList;
/*  24:    */import org.xml.sax.EntityResolver;
/*  25:    */import org.xml.sax.InputSource;
/*  26:    */import org.xml.sax.SAXException;
/*  27:    */
/*  37:    */public class InkscapeLoader
/*  38:    */  implements Loader
/*  39:    */{
/*  40: 40 */  public static int RADIAL_TRIANGULATION_LEVEL = 1;
/*  41:    */  
/*  43: 43 */  private static ArrayList processors = new ArrayList();
/*  44:    */  
/*  45:    */  private Diagram diagram;
/*  46:    */  
/*  47:    */  static
/*  48:    */  {
/*  49: 49 */    addElementProcessor(new RectProcessor());
/*  50: 50 */    addElementProcessor(new EllipseProcessor());
/*  51: 51 */    addElementProcessor(new PolygonProcessor());
/*  52: 52 */    addElementProcessor(new PathProcessor());
/*  53: 53 */    addElementProcessor(new LineProcessor());
/*  54: 54 */    addElementProcessor(new GroupProcessor());
/*  55: 55 */    addElementProcessor(new DefsProcessor());
/*  56: 56 */    addElementProcessor(new UseProcessor());
/*  57:    */  }
/*  58:    */  
/*  64:    */  public static void addElementProcessor(ElementProcessor proc)
/*  65:    */  {
/*  66: 66 */    processors.add(proc);
/*  67:    */  }
/*  68:    */  
/*  79:    */  public static Diagram load(String ref, boolean offset)
/*  80:    */    throws SlickException
/*  81:    */  {
/*  82: 82 */    return load(ResourceLoader.getResourceAsStream(ref), offset);
/*  83:    */  }
/*  84:    */  
/*  92:    */  public static Diagram load(String ref)
/*  93:    */    throws SlickException
/*  94:    */  {
/*  95: 95 */    return load(ResourceLoader.getResourceAsStream(ref), false);
/*  96:    */  }
/*  97:    */  
/* 108:    */  public static Diagram load(InputStream in, boolean offset)
/* 109:    */    throws SlickException
/* 110:    */  {
/* 111:111 */    return new InkscapeLoader().loadDiagram(in, offset);
/* 112:    */  }
/* 113:    */  
/* 127:    */  private Diagram loadDiagram(InputStream in)
/* 128:    */    throws SlickException
/* 129:    */  {
/* 130:130 */    return loadDiagram(in, false);
/* 131:    */  }
/* 132:    */  
/* 142:    */  private Diagram loadDiagram(InputStream in, boolean offset)
/* 143:    */    throws SlickException
/* 144:    */  {
/* 145:    */    try
/* 146:    */    {
/* 147:147 */      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/* 148:    */      
/* 149:149 */      factory.setValidating(false);
/* 150:150 */      factory.setNamespaceAware(true);
/* 151:    */      
/* 152:152 */      DocumentBuilder builder = factory.newDocumentBuilder();
/* 153:153 */      builder.setEntityResolver(new EntityResolver()
/* 154:    */      {
/* 155:    */        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
/* 156:156 */          return new InputSource(new ByteArrayInputStream(new byte[0]));
/* 157:    */        }
/* 158:    */        
/* 160:160 */      });
/* 161:161 */      Document doc = builder.parse(in);
/* 162:162 */      Element root = doc.getDocumentElement();
/* 163:    */      
/* 164:164 */      String widthString = root.getAttribute("width");
/* 165:165 */      while (Character.isLetter(widthString.charAt(widthString.length() - 1)))
/* 166:    */      {
/* 167:167 */        widthString = widthString.substring(0, widthString.length() - 1);
/* 168:    */      }
/* 169:    */      
/* 170:170 */      String heightString = root.getAttribute("height");
/* 171:171 */      while (Character.isLetter(heightString.charAt(heightString.length() - 1)))
/* 172:    */      {
/* 173:173 */        heightString = heightString.substring(0, heightString.length() - 1);
/* 174:    */      }
/* 175:    */      
/* 176:176 */      float docWidth = Float.parseFloat(widthString);
/* 177:177 */      float docHeight = Float.parseFloat(heightString);
/* 178:    */      
/* 179:179 */      this.diagram = new Diagram(docWidth, docHeight);
/* 180:180 */      if (!offset) {
/* 181:181 */        docHeight = 0.0F;
/* 182:    */      }
/* 183:183 */      loadChildren(root, Transform.createTranslateTransform(0.0F, -docHeight));
/* 184:    */      
/* 186:186 */      return this.diagram;
/* 187:    */    } catch (Exception e) {
/* 188:188 */      throw new SlickException("Failed to load inkscape document", e);
/* 189:    */    }
/* 190:    */  }
/* 191:    */  
/* 195:    */  public void loadChildren(Element element, Transform t)
/* 196:    */    throws ParsingException
/* 197:    */  {
/* 198:198 */    NodeList list = element.getChildNodes();
/* 199:199 */    for (int i = 0; i < list.getLength(); i++) {
/* 200:200 */      if ((list.item(i) instanceof Element)) {
/* 201:201 */        loadElement((Element)list.item(i), t);
/* 202:    */      }
/* 203:    */    }
/* 204:    */  }
/* 205:    */  
/* 215:    */  private void loadElement(Element element, Transform t)
/* 216:    */    throws ParsingException
/* 217:    */  {
/* 218:218 */    for (int i = 0; i < processors.size(); i++) {
/* 219:219 */      ElementProcessor processor = (ElementProcessor)processors.get(i);
/* 220:    */      
/* 221:221 */      if (processor.handles(element)) {
/* 222:222 */        processor.process(this, element, this.diagram, t);
/* 223:    */      }
/* 224:    */    }
/* 225:    */  }
/* 226:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.InkscapeLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */