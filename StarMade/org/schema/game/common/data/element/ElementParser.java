package org.schema.game.common.data.element;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import javax.vecmath.Vector3f;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.schema.game.common.data.element.deathstar.DeathStarElement;
import org.schema.game.common.data.element.factory.FactoryElement;
import org.schema.game.common.data.element.factory.ManufacturingElement;
import org.schema.game.common.data.element.ship.ShipElement;
import org.schema.game.common.data.element.spacestation.SpaceStationElement;
import org.schema.game.common.data.element.terrain.MineralElement;
import org.schema.game.common.data.element.terrain.TerrainElement;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElementParser
{
  private Document doc;
  private Properties properties;
  private final ArrayList infoElements = new ArrayList();
  private ElementCategory rootCategory;
  
  public static String getStringFromType(Class paramClass)
  {
    if (paramClass == TerrainElement.class) {
      return "Terrain";
    }
    if (paramClass == GeneralElement.class) {
      return "General";
    }
    if (paramClass == ShipElement.class) {
      return "Ship";
    }
    if (paramClass == Element.class) {
      return "Element";
    }
    if (paramClass == SpaceStationElement.class) {
      return "SpaceStation";
    }
    if (paramClass == DeathStarElement.class) {
      return "DeathStar";
    }
    if (paramClass == MineralElement.class) {
      return "Mineral";
    }
    if (paramClass == FactoryElement.class) {
      return "Factory";
    }
    if (paramClass == ManufacturingElement.class) {
      return "Manufacturing";
    }
    if (paramClass == HullElement.class) {
      return "Hulls";
    }
    if (paramClass == DockingElement.class) {
      return "Docking";
    }
    if (paramClass == LightElement.class) {
      return "Light";
    }
    throw new ElementParserException("The type " + paramClass + " is not known. Only Element, Terrain, Ship, SpaceStation, or DeathStar is permitted");
  }
  
  public static String[] getTypeStringArray()
  {
    return new String[] { "General", "Terrain", "Ship", "Element", "SpaceStation", "General", "DeathStar", "Factory", "Manufacturing", "Mineral", "Hulls", "Docking", "Light" };
  }
  
  public static Class getTypeFromString(String paramString)
  {
    if (paramString.equals("Terrain")) {
      return TerrainElement.class;
    }
    if (paramString.equals("Ship")) {
      return ShipElement.class;
    }
    if (paramString.equals("Element")) {
      return Element.class;
    }
    if (paramString.equals("SpaceStation")) {
      return SpaceStationElement.class;
    }
    if (paramString.equals("General")) {
      return GeneralElement.class;
    }
    if (paramString.equals("DeathStar")) {
      return DeathStarElement.class;
    }
    if (paramString.equals("Factory")) {
      return FactoryElement.class;
    }
    if (paramString.equals("Manufacturing")) {
      return ManufacturingElement.class;
    }
    if (paramString.equals("Mineral")) {
      return MineralElement.class;
    }
    if (paramString.equals("Hulls")) {
      return HullElement.class;
    }
    if (paramString.equals("Docking")) {
      return DockingElement.class;
    }
    if (paramString.equals("Light")) {
      return LightElement.class;
    }
    throw new ElementParserException("The type " + paramString + " is not known. Only Element, Terrain, Ship, SpaceStation, or DeathStar is permitted");
  }
  
  public ArrayList getInfoElements()
  {
    return this.infoElements;
  }
  
  public ElementCategory getRootCategory()
  {
    return this.rootCategory;
  }
  
  private void handleElementNode(Node paramNode, ElementCategory paramElementCategory)
  {
    Object localObject1 = paramNode.getParentNode().getNodeName();
    if (((localObject2 = paramNode.getAttributes()) != null) && (((NamedNodeMap)localObject2).getLength() != 4)) {
      throw new ElementParserException("Element has wrong attribute count (" + ((NamedNodeMap)localObject2).getLength() + ", but should be 4)");
    }
    Node localNode1 = parseType(paramNode, (NamedNodeMap)localObject2, "type");
    Node localNode2 = parseType(paramNode, (NamedNodeMap)localObject2, "icon");
    Node localNode3 = parseType(paramNode, (NamedNodeMap)localObject2, "textureId");
    Object localObject2 = parseType(paramNode, (NamedNodeMap)localObject2, "name");
    String str;
    if ((str = this.properties.getProperty(localNode1.getNodeValue())) == null) {
      throw new ElementParserException("The value of " + localNode1.getNodeName() + " has not been found");
    }
    short s;
    try
    {
      s = (short)Integer.parseInt(str);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new ElementParserException("The property " + str + " has to be an Integer value");
    }
    if (((localObject1 = new ElementInformation(s, ((Node)localObject2).getNodeValue(), getTypeFromString((String)localObject1), (short)parseIntAttribute(localNode3))).getId() < 0) || (((ElementInformation)localObject1).getId() >= 2048)) {
      throw new ElementParserException("Element type has to be between [0, 2048[ for " + paramNode.getNodeName());
    }
    if ((((ElementInformation)localObject1).getTextureId() < 0) || (((ElementInformation)localObject1).getTextureId() >= 2048)) {
      throw new ElementParserException("Texture Id has to be between [0, 2048[ for " + paramNode.getNodeName());
    }
    ((ElementInformation)localObject1).setBuildIconNum(parseInt(localNode2));
    if ((((ElementInformation)localObject1).getBuildIconNum() < 0) || (((ElementInformation)localObject1).getBuildIconNum() >= 2048)) {
      throw new ElementParserException("Icon has to be between [0, 2048[ for " + paramNode.getNodeName() + " but was " + ((ElementInformation)localObject1).getBuildIconNum());
    }
    parseInfoNode(paramNode, (ElementInformation)localObject1);
    this.infoElements.add(localObject1);
    paramElementCategory.getInfoElements().add(localObject1);
  }
  
  private void handleNode(Node paramNode, ElementCategory paramElementCategory)
  {
    if (!paramNode.hasAttributes())
    {
      handleTypeNode(paramNode, paramElementCategory);
      return;
    }
    handleElementNode(paramNode, paramElementCategory);
  }
  
  private void handleTypeNode(Node paramNode, ElementCategory paramElementCategory)
  {
    if (paramNode.getNodeType() == 1)
    {
      ElementCategory localElementCategory = new ElementCategory(getTypeFromString(paramNode.getNodeName()), paramElementCategory);
      paramElementCategory.getChildren().add(localElementCategory);
      paramNode = paramNode.getChildNodes();
      for (paramElementCategory = 0; paramElementCategory < paramNode.getLength(); paramElementCategory++) {
        handleNode(paramNode.item(paramElementCategory), localElementCategory);
      }
    }
  }
  
  public void loadAndParseDefault()
  {
    read(new File("./data/config/BlockConfig.xml"), "./data/config/BlockTypes.properties");
    parse();
  }
  
  public void loadAndParseCustomXML(File paramFile, String paramString)
  {
    read(paramFile, paramString == null ? "./data/config/BlockTypes.properties" : paramString);
    parse();
  }
  
  public void parse()
  {
    Object localObject = this.doc.getDocumentElement();
    this.rootCategory = new ElementCategory(Element.class, null);
    if (!((org.w3c.dom.Element)localObject).getNodeName().equals("Element")) {
      throw new ElementParserException("No root element with the name \"Element\" found");
    }
    localObject = ((org.w3c.dom.Element)localObject).getChildNodes();
    for (int i = 0; i < ((NodeList)localObject).getLength(); i++) {
      handleNode(((NodeList)localObject).item(i), this.rootCategory);
    }
  }
  
  private void parseAnimated(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setAnimated(parseBoolean(paramNode));
  }
  
  private void parseArmour(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setAmour(parseInt(paramNode));
    if ((paramElementInformation.getAmour() < 0) || (paramElementInformation.getAmour() > 100)) {
      throw new ElementParserException("Armour for " + paramNode.getParentNode().getNodeName() + " has to be between 0% and 100%");
    }
  }
  
  private void parseBlockStyle(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setBlockStyle(parseInt(paramNode));
  }
  
  private boolean parseBoolean(Node paramNode)
  {
    try
    {
      return Boolean.parseBoolean(paramNode.getTextContent());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Boolean value for " + paramNode.getParentNode().getNodeName());
    }
  }
  
  private void parseCanActivate(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setCanActivate(parseBoolean(paramNode));
  }
  
  private void parseControlledBy(Node paramNode, ElementInformation paramElementInformation)
  {
    NodeList localNodeList = paramNode.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode;
      if ((localNode = localNodeList.item(i)).getNodeType() == 1)
      {
        if (!localNode.getNodeName().equals("Element")) {
          throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"Element\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
        }
        String str;
        if ((str = this.properties.getProperty(localNode.getTextContent())) == null) {
          throw new ElementParserException("The value of " + localNode.getTextContent() + " has not been found");
        }
        short s;
        try
        {
          s = (short)Integer.parseInt(str);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new ElementParserException("The property " + str + " has to be an Integer value");
        }
        paramElementInformation.getControlledBy().add(Short.valueOf(s));
      }
    }
  }
  
  private void parseControlling(Node paramNode, ElementInformation paramElementInformation)
  {
    NodeList localNodeList = paramNode.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode;
      if ((localNode = localNodeList.item(i)).getNodeType() == 1)
      {
        if (!localNode.getNodeName().equals("Element")) {
          throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"Element\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
        }
        String str;
        if ((str = this.properties.getProperty(localNode.getTextContent())) == null) {
          throw new ElementParserException("The value of " + localNode.getTextContent() + " has not been found");
        }
        short s;
        try
        {
          s = (short)Integer.parseInt(str);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new ElementParserException("The property " + str + " has to be an Integer value");
        }
        paramElementInformation.getControlling().add(Short.valueOf(s));
      }
    }
  }
  
  private FactoryResource[] parseResource(Node paramNode)
  {
    ArrayList localArrayList = new ArrayList();
    NodeList localNodeList = paramNode.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode;
      if ((localNode = localNodeList.item(i)).getNodeType() == 1)
      {
        if (!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("item")) {
          throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"item\" but is " + paramNode.getParentNode().getNodeName() + " (" + paramNode.getParentNode().getParentNode().getNodeName() + ")");
        }
        if (((localObject = localNode.getAttributes()) != null) && (((NamedNodeMap)localObject).getLength() != 1)) {
          throw new ElementParserException("Element has wrong attribute count (" + ((NamedNodeMap)localObject).getLength() + ", but should be 4)");
        }
        Object localObject = parseType(localNode, (NamedNodeMap)localObject, "count");
        int j;
        try
        {
          j = Integer.parseInt(((Node)localObject).getNodeValue());
        }
        catch (NumberFormatException localNumberFormatException1)
        {
          throw new ElementParserException("Cant parse count in " + localNode.getNodeName() + ", in " + paramNode.getParentNode().getNodeName() + " (" + paramNode.getParentNode().getParentNode().getNodeName() + ")");
        }
        String str;
        if ((str = this.properties.getProperty(localNode.getTextContent())) == null) {
          throw new ElementParserException("The value of " + ((Node)localObject).getNodeName() + " has not been found");
        }
        short s;
        try
        {
          s = (short)Integer.parseInt(str);
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          throw new ElementParserException("The property " + str + " has to be an Integer value");
        }
        localArrayList.add(new FactoryResource(j, s));
      }
    }
    FactoryResource[] arrayOfFactoryResource = new FactoryResource[localArrayList.size()];
    localArrayList.toArray(arrayOfFactoryResource);
    return arrayOfFactoryResource;
  }
  
  private void parseFactory(Node paramNode, ElementInformation paramElementInformation)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    BlockFactory localBlockFactory = new BlockFactory();
    paramElementInformation.setFactory(localBlockFactory);
    if (paramNode.getTextContent().toLowerCase(Locale.ENGLISH).equals("input")) {
      return;
    }
    paramElementInformation = paramNode.getChildNodes();
    for (int i = 0; i < paramElementInformation.getLength(); i++)
    {
      Object localObject;
      if ((localObject = paramElementInformation.item(i)).getNodeType() == 1)
      {
        if (!((Node)localObject).getNodeName().toLowerCase(Locale.ENGLISH).equals("product")) {
          throw new ElementParserException("All child nodes of " + ((Node)localObject).getNodeName() + " have to be \"product\" but is " + ((Node)localObject).getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
        }
        localObject = ((Node)localObject).getChildNodes();
        FactoryResource[] arrayOfFactoryResource1 = null;
        FactoryResource[] arrayOfFactoryResource2 = null;
        for (int j = 0; j < ((NodeList)localObject).getLength(); j++)
        {
          Node localNode;
          if ((localNode = ((NodeList)localObject).item(j)).getNodeType() == 1)
          {
            if ((!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("output")) && (!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("input"))) {
              throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"output\" or \"input\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
            }
            if (localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("input")) {
              arrayOfFactoryResource1 = parseResource(localNode);
            }
            if (localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("output")) {
              arrayOfFactoryResource2 = parseResource(localNode);
            }
          }
        }
        if (arrayOfFactoryResource1 == null) {
          throw new ElementParserException("No input defined for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
        }
        if (arrayOfFactoryResource2 == null) {
          throw new ElementParserException("No output defined for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
        }
        localArrayList1.add(arrayOfFactoryResource1);
        localArrayList2.add(arrayOfFactoryResource2);
      }
    }
    if (localArrayList1.size() != localArrayList2.size()) {
      throw new ElementParserException("Factory Parsing failed for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
    }
    localBlockFactory.input = new FactoryResource[localArrayList1.size()][];
    localBlockFactory.output = new FactoryResource[localArrayList2.size()][];
    for (i = 0; i < localBlockFactory.input.length; i++)
    {
      localBlockFactory.input[i] = ((FactoryResource[])localArrayList1.get(i));
      localBlockFactory.output[i] = ((FactoryResource[])localArrayList2.get(i));
    }
  }
  
  private void parseLevel(Node paramNode, ElementInformation paramElementInformation)
  {
    NodeList localNodeList = paramNode.getChildNodes();
    int i = 0;
    int j = 0;
    short s = 0;
    int k = 0;
    for (int m = 0; m < localNodeList.getLength(); m++)
    {
      Node localNode;
      if ((localNode = localNodeList.item(m)).getNodeType() == 1)
      {
        if (localNode.getNodeName().equals("Id"))
        {
          if (i != 0) {
            throw new ElementParserException("[LEVEL] Multiple IDs for level in " + paramNode.getParentNode().getNodeName());
          }
          String str;
          if ((str = this.properties.getProperty(localNode.getTextContent())) == null) {
            throw new ElementParserException("[LEVEL] The value of " + localNode.getTextContent() + " has not been found in " + paramNode.getParentNode().getNodeName());
          }
          try
          {
            s = (short)Integer.parseInt(str);
            i = 1;
          }
          catch (NumberFormatException localNumberFormatException1)
          {
            throw new ElementParserException("[LEVEL] The property " + str + " has to be an Integer value in " + paramNode.getParentNode().getNodeName());
          }
        }
        if (localNode.getNodeName().equals("Nr")) {
          try
          {
            k = Integer.parseInt(localNode.getTextContent());
            j = 1;
          }
          catch (NumberFormatException localNumberFormatException2)
          {
            throw new ElementParserException("[LEVEL] Nr Value '" + localNode.getTextContent() + "' is not an Integer value in " + paramNode.getParentNode().getNodeName());
          }
        }
      }
    }
    if (i == 0) {
      throw new ElementParserException("[LEVEL] No level id in " + paramNode.getParentNode().getNodeName());
    }
    if (j == 0) {
      throw new ElementParserException("[LEVEL] No level nr in " + paramNode.getParentNode().getNodeName());
    }
    BlockLevel localBlockLevel = new BlockLevel(s, k);
    paramElementInformation.setLevel(localBlockLevel);
  }
  
  private void parseDescription(Node paramNode, ElementInformation paramElementInformation)
  {
    paramNode = paramNode.getTextContent().replaceAll("\\r\\n|\\r|\\n", "").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r").replaceAll("\\\\t", "\t");
    paramElementInformation.setDescription(paramNode);
  }
  
  private void parseEnterable(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setEnterable(parseBoolean(paramNode));
  }
  
  private float parseFloat(Node paramNode)
  {
    try
    {
      return Float.parseFloat(paramNode.getTextContent());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be a Float value for " + paramNode.getParentNode().getNodeName());
    }
  }
  
  private void parseFullName(Node paramNode, ElementInformation paramElementInformation)
  {
    paramNode = paramNode.getTextContent();
    paramElementInformation.setFullName(paramNode);
  }
  
  private void parseHitpoints(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setMaxHitPoints((short)parseInt(paramNode));
    if ((paramElementInformation.getMaxHitPoints() < 0) || (paramElementInformation.getMaxHitPoints() > 1023)) {
      throw new ElementParserException("Hitpoints for " + paramNode.getParentNode().getNodeName() + " has to be between [1, 1024[");
    }
  }
  
  private void parseIndividualSides(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setIndividualSides(parseInt(paramNode));
    if ((paramElementInformation.getIndividualSides() != 1) && (paramElementInformation.getIndividualSides() != 3) && (paramElementInformation.getIndividualSides() != 6)) {
      throw new ElementParserException("Individual Sides for " + paramNode.getParentNode().getNodeName() + " has to be either 1 (default), 3, or 6, but was: " + paramElementInformation.getIndividualSides());
    }
  }
  
  private void parseInfoNode(Node paramNode, ElementInformation paramElementInformation)
  {
    NodeList localNodeList = paramNode.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode;
      if ((localNode = localNodeList.item(i)).getNodeType() == 1) {
        if (localNode.getNodeName().equals("InShop")) {
          parseInShop(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Placable")) {
          parsePlacable(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("ControlledBy")) {
          parseControlledBy(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Controlling")) {
          parseControlling(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Level")) {
          parseLevel(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Enterable")) {
          parseEnterable(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("CanActivate")) {
          parseCanActivate(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("LightSource")) {
          parseLightSource(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Physical")) {
          parsePhysical(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("BlockStyle")) {
          parseBlockStyle(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("LightSourceColor")) {
          parseLightSourceColor(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Hitpoints")) {
          parseHitpoints(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Armour")) {
          parseArmour(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Transparency")) {
          parseTransparency(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Description")) {
          parseDescription(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Factory")) {
          parseFactory(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("FullName")) {
          parseFullName(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Animated")) {
          parseAnimated(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Price")) {
          parsePrice(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("InShop")) {
          parseInShop(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("Orientation")) {
          parseOrientation(localNode, paramElementInformation);
        } else if (localNode.getNodeName().equals("IndividualSides")) {
          parseIndividualSides(localNode, paramElementInformation);
        } else {
          throw new ElementParserException("Element Info " + localNode.getNodeName() + " not found. location: " + paramNode.getNodeName());
        }
      }
    }
  }
  
  private void parseInShop(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setShoppable(parseBoolean(paramNode));
  }
  
  private int parseInt(Node paramNode)
  {
    try
    {
      return Integer.parseInt(paramNode.getTextContent());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Integer value for " + paramNode.getParentNode().getNodeName());
    }
  }
  
  private int parseIntAttribute(Node paramNode)
  {
    try
    {
      return Integer.parseInt(paramNode.getNodeValue());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Integer value for " + paramNode.getParentNode().getNodeName());
    }
  }
  
  private void parseLightSource(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setLightSource(parseBoolean(paramNode));
  }
  
  private void parseLightSourceColor(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.getLightSourceColor().set(parseVector3f(paramNode));
  }
  
  private void parseOrientation(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setOrientatable(parseBoolean(paramNode));
  }
  
  private void parsePhysical(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setPhysical(parseBoolean(paramNode));
  }
  
  private void parsePlacable(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setPlacable(parseBoolean(paramNode));
  }
  
  private void parsePrice(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setPrice(parseInt(paramNode));
    if (paramElementInformation.getPrice() < 0L) {
      throw new ElementParserException("Price for " + paramNode.getParentNode().getNodeName() + " has to be greater or equal zero");
    }
  }
  
  private void parseTransparency(Node paramNode, ElementInformation paramElementInformation)
  {
    paramElementInformation.setBlended(parseBoolean(paramNode));
  }
  
  private Node parseType(Node paramNode, NamedNodeMap paramNamedNodeMap, String paramString)
  {
    if ((paramNamedNodeMap = paramNamedNodeMap.getNamedItem(paramString)) == null) {
      throw new ElementParserException("Obligatory attribute \"" + paramString + "\" not found in " + paramNode.getNodeName());
    }
    return paramNamedNodeMap;
  }
  
  private Vector3f parseVector3f(Node paramNode)
  {
    Vector3f localVector3f = new Vector3f();
    String[] arrayOfString;
    if ((arrayOfString = paramNode.getTextContent().split(",")).length != 3) {
      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be 3 Float values seperated by commas");
    }
    try
    {
      localVector3f.set(Float.parseFloat(arrayOfString[0].trim()), Float.parseFloat(arrayOfString[1].trim()), Float.parseFloat(arrayOfString[2].trim()));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be a Float value");
    }
    return localVector3f;
  }
  
  public void read(File paramFile, String paramString)
  {
    DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    this.doc = localDocumentBuilder.parse(paramFile);
    this.properties = new Properties();
    paramFile = new FileInputStream(paramString);
    this.properties.load(paramFile);
    paramFile.close();
    ElementKeyMap.properties = this.properties;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */