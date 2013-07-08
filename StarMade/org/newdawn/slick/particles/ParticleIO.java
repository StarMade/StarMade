package org.newdawn.slick.particles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParticleIO
{
  public static ParticleSystem loadConfiguredSystem(String ref, Color mask)
    throws IOException
  {
    return loadConfiguredSystem(ResourceLoader.getResourceAsStream(ref), null, null, mask);
  }
  
  public static ParticleSystem loadConfiguredSystem(String ref)
    throws IOException
  {
    return loadConfiguredSystem(ResourceLoader.getResourceAsStream(ref), null, null, null);
  }
  
  public static ParticleSystem loadConfiguredSystem(File ref)
    throws IOException
  {
    return loadConfiguredSystem(new FileInputStream(ref), null, null, null);
  }
  
  public static ParticleSystem loadConfiguredSystem(InputStream ref, Color mask)
    throws IOException
  {
    return loadConfiguredSystem(ref, null, null, mask);
  }
  
  public static ParticleSystem loadConfiguredSystem(InputStream ref)
    throws IOException
  {
    return loadConfiguredSystem(ref, null, null, null);
  }
  
  public static ParticleSystem loadConfiguredSystem(String ref, ConfigurableEmitterFactory factory)
    throws IOException
  {
    return loadConfiguredSystem(ResourceLoader.getResourceAsStream(ref), factory, null, null);
  }
  
  public static ParticleSystem loadConfiguredSystem(File ref, ConfigurableEmitterFactory factory)
    throws IOException
  {
    return loadConfiguredSystem(new FileInputStream(ref), factory, null, null);
  }
  
  public static ParticleSystem loadConfiguredSystem(InputStream ref, ConfigurableEmitterFactory factory)
    throws IOException
  {
    return loadConfiguredSystem(ref, factory, null, null);
  }
  
  public static ParticleSystem loadConfiguredSystem(InputStream ref, ConfigurableEmitterFactory factory, ParticleSystem system, Color mask)
    throws IOException
  {
    if (factory == null) {
      factory = new ConfigurableEmitterFactory()
      {
        public ConfigurableEmitter createEmitter(String name)
        {
          return new ConfigurableEmitter(name);
        }
      };
    }
    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(ref);
      Element element = document.getDocumentElement();
      if (!element.getNodeName().equals("system")) {
        throw new IOException("Not a particle system file");
      }
      if (system == null) {
        system = new ParticleSystem("org/newdawn/slick/data/particle.tga", 2000, mask);
      }
      boolean additive = "true".equals(element.getAttribute("additive"));
      if (additive) {
        system.setBlendingMode(1);
      } else {
        system.setBlendingMode(2);
      }
      boolean points = "true".equals(element.getAttribute("points"));
      system.setUsePoints(points);
      NodeList list = element.getElementsByTagName("emitter");
      for (int local_i = 0; local_i < list.getLength(); local_i++)
      {
        Element local_em = (Element)list.item(local_i);
        ConfigurableEmitter emitter = factory.createEmitter("new");
        elementToEmitter(local_em, emitter);
        system.addEmitter(emitter);
      }
      system.setRemoveCompletedEmitters(false);
      return system;
    }
    catch (IOException builder)
    {
      Log.error(builder);
      throw builder;
    }
    catch (Exception builder)
    {
      Log.error(builder);
      throw new IOException("Unable to load particle system config");
    }
  }
  
  public static void saveConfiguredSystem(File file, ParticleSystem system)
    throws IOException
  {
    saveConfiguredSystem(new FileOutputStream(file), system);
  }
  
  public static void saveConfiguredSystem(OutputStream out, ParticleSystem system)
    throws IOException
  {
    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.newDocument();
      Element root = document.createElement("system");
      root.setAttribute("additive", "" + (system.getBlendingMode() == 1));
      root.setAttribute("points", "" + system.usePoints());
      document.appendChild(root);
      for (int local_i = 0; local_i < system.getEmitterCount(); local_i++)
      {
        ParticleEmitter current = system.getEmitter(local_i);
        if ((current instanceof ConfigurableEmitter))
        {
          Element element = emitterToElement(document, (ConfigurableEmitter)current);
          root.appendChild(element);
        }
        else
        {
          throw new RuntimeException("Only ConfigurableEmitter instances can be stored");
        }
      }
      Result local_i = new StreamResult(new OutputStreamWriter(out, "utf-8"));
      DOMSource current = new DOMSource(document);
      TransformerFactory element = TransformerFactory.newInstance();
      Transformer xformer = element.newTransformer();
      xformer.setOutputProperty("indent", "yes");
      xformer.transform(current, local_i);
    }
    catch (Exception builder)
    {
      Log.error(builder);
      throw new IOException("Unable to save configured particle system");
    }
  }
  
  public static ConfigurableEmitter loadEmitter(String ref)
    throws IOException
  {
    return loadEmitter(ResourceLoader.getResourceAsStream(ref), null);
  }
  
  public static ConfigurableEmitter loadEmitter(File ref)
    throws IOException
  {
    return loadEmitter(new FileInputStream(ref), null);
  }
  
  public static ConfigurableEmitter loadEmitter(InputStream ref)
    throws IOException
  {
    return loadEmitter(ref, null);
  }
  
  public static ConfigurableEmitter loadEmitter(String ref, ConfigurableEmitterFactory factory)
    throws IOException
  {
    return loadEmitter(ResourceLoader.getResourceAsStream(ref), factory);
  }
  
  public static ConfigurableEmitter loadEmitter(File ref, ConfigurableEmitterFactory factory)
    throws IOException
  {
    return loadEmitter(new FileInputStream(ref), factory);
  }
  
  public static ConfigurableEmitter loadEmitter(InputStream ref, ConfigurableEmitterFactory factory)
    throws IOException
  {
    if (factory == null) {
      factory = new ConfigurableEmitterFactory()
      {
        public ConfigurableEmitter createEmitter(String name)
        {
          return new ConfigurableEmitter(name);
        }
      };
    }
    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.parse(ref);
      if (!document.getDocumentElement().getNodeName().equals("emitter")) {
        throw new IOException("Not a particle emitter file");
      }
      ConfigurableEmitter emitter = factory.createEmitter("new");
      elementToEmitter(document.getDocumentElement(), emitter);
      return emitter;
    }
    catch (IOException builder)
    {
      Log.error(builder);
      throw builder;
    }
    catch (Exception builder)
    {
      Log.error(builder);
      throw new IOException("Unable to load emitter");
    }
  }
  
  public static void saveEmitter(File file, ConfigurableEmitter emitter)
    throws IOException
  {
    saveEmitter(new FileOutputStream(file), emitter);
  }
  
  public static void saveEmitter(OutputStream out, ConfigurableEmitter emitter)
    throws IOException
  {
    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = builder.newDocument();
      document.appendChild(emitterToElement(document, emitter));
      Result result = new StreamResult(new OutputStreamWriter(out, "utf-8"));
      DOMSource source = new DOMSource(document);
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer xformer = factory.newTransformer();
      xformer.setOutputProperty("indent", "yes");
      xformer.transform(source, result);
    }
    catch (Exception builder)
    {
      Log.error(builder);
      throw new IOException("Failed to save emitter");
    }
  }
  
  private static Element getFirstNamedElement(Element element, String name)
  {
    NodeList list = element.getElementsByTagName(name);
    if (list.getLength() == 0) {
      return null;
    }
    return (Element)list.item(0);
  }
  
  private static void elementToEmitter(Element element, ConfigurableEmitter emitter)
  {
    emitter.name = element.getAttribute("name");
    emitter.setImageName(element.getAttribute("imageName"));
    String renderType = element.getAttribute("renderType");
    emitter.usePoints = 1;
    if (renderType.equals("quads")) {
      emitter.usePoints = 3;
    }
    if (renderType.equals("points")) {
      emitter.usePoints = 2;
    }
    String useOriented = element.getAttribute("useOriented");
    if (useOriented != null) {
      emitter.useOriented = "true".equals(useOriented);
    }
    String useAdditive = element.getAttribute("useAdditive");
    if (useAdditive != null) {
      emitter.useAdditive = "true".equals(useAdditive);
    }
    parseRangeElement(getFirstNamedElement(element, "spawnInterval"), emitter.spawnInterval);
    parseRangeElement(getFirstNamedElement(element, "spawnCount"), emitter.spawnCount);
    parseRangeElement(getFirstNamedElement(element, "initialLife"), emitter.initialLife);
    parseRangeElement(getFirstNamedElement(element, "initialSize"), emitter.initialSize);
    parseRangeElement(getFirstNamedElement(element, "xOffset"), emitter.xOffset);
    parseRangeElement(getFirstNamedElement(element, "yOffset"), emitter.yOffset);
    parseRangeElement(getFirstNamedElement(element, "initialDistance"), emitter.initialDistance);
    parseRangeElement(getFirstNamedElement(element, "speed"), emitter.speed);
    parseRangeElement(getFirstNamedElement(element, "length"), emitter.length);
    parseRangeElement(getFirstNamedElement(element, "emitCount"), emitter.emitCount);
    parseValueElement(getFirstNamedElement(element, "spread"), emitter.spread);
    parseValueElement(getFirstNamedElement(element, "angularOffset"), emitter.angularOffset);
    parseValueElement(getFirstNamedElement(element, "growthFactor"), emitter.growthFactor);
    parseValueElement(getFirstNamedElement(element, "gravityFactor"), emitter.gravityFactor);
    parseValueElement(getFirstNamedElement(element, "windFactor"), emitter.windFactor);
    parseValueElement(getFirstNamedElement(element, "startAlpha"), emitter.startAlpha);
    parseValueElement(getFirstNamedElement(element, "endAlpha"), emitter.endAlpha);
    parseValueElement(getFirstNamedElement(element, "alpha"), emitter.alpha);
    parseValueElement(getFirstNamedElement(element, "size"), emitter.size);
    parseValueElement(getFirstNamedElement(element, "velocity"), emitter.velocity);
    parseValueElement(getFirstNamedElement(element, "scaleY"), emitter.scaleY);
    Element color = getFirstNamedElement(element, "color");
    NodeList steps = color.getElementsByTagName("step");
    emitter.colors.clear();
    for (int local_i = 0; local_i < steps.getLength(); local_i++)
    {
      Element step = (Element)steps.item(local_i);
      float offset = Float.parseFloat(step.getAttribute("offset"));
      float local_r = Float.parseFloat(step.getAttribute("r"));
      float local_g = Float.parseFloat(step.getAttribute("g"));
      float local_b = Float.parseFloat(step.getAttribute("b"));
      emitter.addColorPoint(offset, new Color(local_r, local_g, local_b, 1.0F));
    }
    emitter.replay();
  }
  
  private static Element emitterToElement(Document document, ConfigurableEmitter emitter)
  {
    Element root = document.createElement("emitter");
    root.setAttribute("name", emitter.name);
    root.setAttribute("imageName", emitter.imageName == null ? "" : emitter.imageName);
    root.setAttribute("useOriented", emitter.useOriented ? "true" : "false");
    root.setAttribute("useAdditive", emitter.useAdditive ? "true" : "false");
    if (emitter.usePoints == 1) {
      root.setAttribute("renderType", "inherit");
    }
    if (emitter.usePoints == 2) {
      root.setAttribute("renderType", "points");
    }
    if (emitter.usePoints == 3) {
      root.setAttribute("renderType", "quads");
    }
    root.appendChild(createRangeElement(document, "spawnInterval", emitter.spawnInterval));
    root.appendChild(createRangeElement(document, "spawnCount", emitter.spawnCount));
    root.appendChild(createRangeElement(document, "initialLife", emitter.initialLife));
    root.appendChild(createRangeElement(document, "initialSize", emitter.initialSize));
    root.appendChild(createRangeElement(document, "xOffset", emitter.xOffset));
    root.appendChild(createRangeElement(document, "yOffset", emitter.yOffset));
    root.appendChild(createRangeElement(document, "initialDistance", emitter.initialDistance));
    root.appendChild(createRangeElement(document, "speed", emitter.speed));
    root.appendChild(createRangeElement(document, "length", emitter.length));
    root.appendChild(createRangeElement(document, "emitCount", emitter.emitCount));
    root.appendChild(createValueElement(document, "spread", emitter.spread));
    root.appendChild(createValueElement(document, "angularOffset", emitter.angularOffset));
    root.appendChild(createValueElement(document, "growthFactor", emitter.growthFactor));
    root.appendChild(createValueElement(document, "gravityFactor", emitter.gravityFactor));
    root.appendChild(createValueElement(document, "windFactor", emitter.windFactor));
    root.appendChild(createValueElement(document, "startAlpha", emitter.startAlpha));
    root.appendChild(createValueElement(document, "endAlpha", emitter.endAlpha));
    root.appendChild(createValueElement(document, "alpha", emitter.alpha));
    root.appendChild(createValueElement(document, "size", emitter.size));
    root.appendChild(createValueElement(document, "velocity", emitter.velocity));
    root.appendChild(createValueElement(document, "scaleY", emitter.scaleY));
    Element color = document.createElement("color");
    ArrayList list = emitter.colors;
    for (int local_i = 0; local_i < list.size(); local_i++)
    {
      ConfigurableEmitter.ColorRecord record = (ConfigurableEmitter.ColorRecord)list.get(local_i);
      Element step = document.createElement("step");
      step.setAttribute("offset", "" + record.pos);
      step.setAttribute("r", "" + record.col.field_1776);
      step.setAttribute("g", "" + record.col.field_1777);
      step.setAttribute("b", "" + record.col.field_1778);
      color.appendChild(step);
    }
    root.appendChild(color);
    return root;
  }
  
  private static Element createRangeElement(Document document, String name, ConfigurableEmitter.Range range)
  {
    Element element = document.createElement(name);
    element.setAttribute("min", "" + range.getMin());
    element.setAttribute("max", "" + range.getMax());
    element.setAttribute("enabled", "" + range.isEnabled());
    return element;
  }
  
  private static Element createValueElement(Document document, String name, ConfigurableEmitter.Value value)
  {
    Element element = document.createElement(name);
    if ((value instanceof ConfigurableEmitter.SimpleValue))
    {
      element.setAttribute("type", "simple");
      element.setAttribute("value", "" + value.getValue(0.0F));
    }
    else if ((value instanceof ConfigurableEmitter.RandomValue))
    {
      element.setAttribute("type", "random");
      element.setAttribute("value", "" + ((ConfigurableEmitter.RandomValue)value).getValue());
    }
    else if ((value instanceof ConfigurableEmitter.LinearInterpolator))
    {
      element.setAttribute("type", "linear");
      element.setAttribute("min", "" + ((ConfigurableEmitter.LinearInterpolator)value).getMin());
      element.setAttribute("max", "" + ((ConfigurableEmitter.LinearInterpolator)value).getMax());
      element.setAttribute("active", "" + ((ConfigurableEmitter.LinearInterpolator)value).isActive());
      ArrayList curve = ((ConfigurableEmitter.LinearInterpolator)value).getCurve();
      for (int local_i = 0; local_i < curve.size(); local_i++)
      {
        Vector2f point = (Vector2f)curve.get(local_i);
        Element pointElement = document.createElement("point");
        pointElement.setAttribute("x", "" + point.field_1680);
        pointElement.setAttribute("y", "" + point.field_1681);
        element.appendChild(pointElement);
      }
    }
    else
    {
      Log.warn("unkown value type ignored: " + value.getClass());
    }
    return element;
  }
  
  private static void parseRangeElement(Element element, ConfigurableEmitter.Range range)
  {
    if (element == null) {
      return;
    }
    range.setMin(Float.parseFloat(element.getAttribute("min")));
    range.setMax(Float.parseFloat(element.getAttribute("max")));
    range.setEnabled("true".equals(element.getAttribute("enabled")));
  }
  
  private static void parseValueElement(Element element, ConfigurableEmitter.Value value)
  {
    if (element == null) {
      return;
    }
    String type = element.getAttribute("type");
    String local_v = element.getAttribute("value");
    if ((type == null) || (type.length() == 0))
    {
      if ((value instanceof ConfigurableEmitter.SimpleValue)) {
        ((ConfigurableEmitter.SimpleValue)value).setValue(Float.parseFloat(local_v));
      } else if ((value instanceof ConfigurableEmitter.RandomValue)) {
        ((ConfigurableEmitter.RandomValue)value).setValue(Float.parseFloat(local_v));
      } else {
        Log.warn("problems reading element, skipping: " + element);
      }
    }
    else if (type.equals("simple"))
    {
      ((ConfigurableEmitter.SimpleValue)value).setValue(Float.parseFloat(local_v));
    }
    else if (type.equals("random"))
    {
      ((ConfigurableEmitter.RandomValue)value).setValue(Float.parseFloat(local_v));
    }
    else if (type.equals("linear"))
    {
      String min = element.getAttribute("min");
      String max = element.getAttribute("max");
      String active = element.getAttribute("active");
      NodeList points = element.getElementsByTagName("point");
      ArrayList curve = new ArrayList();
      for (int local_i = 0; local_i < points.getLength(); local_i++)
      {
        Element point = (Element)points.item(local_i);
        float local_x = Float.parseFloat(point.getAttribute("x"));
        float local_y = Float.parseFloat(point.getAttribute("y"));
        curve.add(new Vector2f(local_x, local_y));
      }
      ((ConfigurableEmitter.LinearInterpolator)value).setCurve(curve);
      ((ConfigurableEmitter.LinearInterpolator)value).setMin(Integer.parseInt(min));
      ((ConfigurableEmitter.LinearInterpolator)value).setMax(Integer.parseInt(max));
      ((ConfigurableEmitter.LinearInterpolator)value).setActive("true".equals(active));
    }
    else
    {
      Log.warn("unkown type detected: " + type);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.particles.ParticleIO
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */