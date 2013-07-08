/*   1:    */package org.schema.game.common.data.element;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileInputStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.Locale;
/*   7:    */import java.util.Properties;
/*   8:    */import java.util.Set;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import javax.xml.parsers.DocumentBuilder;
/*  11:    */import javax.xml.parsers.DocumentBuilderFactory;
/*  12:    */import org.schema.game.common.data.element.deathstar.DeathStarElement;
/*  13:    */import org.schema.game.common.data.element.factory.FactoryElement;
/*  14:    */import org.schema.game.common.data.element.factory.ManufacturingElement;
/*  15:    */import org.schema.game.common.data.element.ship.ShipElement;
/*  16:    */import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*  17:    */import org.schema.game.common.data.element.terrain.MineralElement;
/*  18:    */import org.schema.game.common.data.element.terrain.TerrainElement;
/*  19:    */import org.w3c.dom.Document;
/*  20:    */import org.w3c.dom.NamedNodeMap;
/*  21:    */import org.w3c.dom.Node;
/*  22:    */import org.w3c.dom.NodeList;
/*  23:    */
/*  30:    */public class ElementParser
/*  31:    */{
/*  32:    */  private Document doc;
/*  33:    */  private Properties properties;
/*  34:    */  
/*  35:    */  public static String getStringFromType(Class paramClass)
/*  36:    */  {
/*  37: 37 */    if (paramClass == TerrainElement.class)
/*  38: 38 */      return "Terrain";
/*  39: 39 */    if (paramClass == GeneralElement.class)
/*  40: 40 */      return "General";
/*  41: 41 */    if (paramClass == ShipElement.class)
/*  42: 42 */      return "Ship";
/*  43: 43 */    if (paramClass == Element.class)
/*  44: 44 */      return "Element";
/*  45: 45 */    if (paramClass == SpaceStationElement.class)
/*  46: 46 */      return "SpaceStation";
/*  47: 47 */    if (paramClass == DeathStarElement.class)
/*  48: 48 */      return "DeathStar";
/*  49: 49 */    if (paramClass == MineralElement.class)
/*  50: 50 */      return "Mineral";
/*  51: 51 */    if (paramClass == FactoryElement.class)
/*  52: 52 */      return "Factory";
/*  53: 53 */    if (paramClass == ManufacturingElement.class)
/*  54: 54 */      return "Manufacturing";
/*  55: 55 */    if (paramClass == HullElement.class)
/*  56: 56 */      return "Hulls";
/*  57: 57 */    if (paramClass == DockingElement.class)
/*  58: 58 */      return "Docking";
/*  59: 59 */    if (paramClass == LightElement.class) {
/*  60: 60 */      return "Light";
/*  61:    */    }
/*  62:    */    
/*  63: 63 */    throw new ElementParserException("The type " + paramClass + " is not known. Only Element, Terrain, Ship, SpaceStation, or DeathStar is permitted");
/*  64:    */  }
/*  65:    */  
/*  66: 66 */  public static String[] getTypeStringArray() { return new String[] { "General", "Terrain", "Ship", "Element", "SpaceStation", "General", "DeathStar", "Factory", "Manufacturing", "Mineral", "Hulls", "Docking", "Light" }; }
/*  67:    */  
/*  68:    */  public static Class getTypeFromString(String paramString) {
/*  69: 69 */    if (paramString.equals("Terrain"))
/*  70: 70 */      return TerrainElement.class;
/*  71: 71 */    if (paramString.equals("Ship"))
/*  72: 72 */      return ShipElement.class;
/*  73: 73 */    if (paramString.equals("Element"))
/*  74: 74 */      return Element.class;
/*  75: 75 */    if (paramString.equals("SpaceStation"))
/*  76: 76 */      return SpaceStationElement.class;
/*  77: 77 */    if (paramString.equals("General"))
/*  78: 78 */      return GeneralElement.class;
/*  79: 79 */    if (paramString.equals("DeathStar"))
/*  80: 80 */      return DeathStarElement.class;
/*  81: 81 */    if (paramString.equals("Factory"))
/*  82: 82 */      return FactoryElement.class;
/*  83: 83 */    if (paramString.equals("Manufacturing"))
/*  84: 84 */      return ManufacturingElement.class;
/*  85: 85 */    if (paramString.equals("Mineral"))
/*  86: 86 */      return MineralElement.class;
/*  87: 87 */    if (paramString.equals("Hulls"))
/*  88: 88 */      return HullElement.class;
/*  89: 89 */    if (paramString.equals("Docking"))
/*  90: 90 */      return DockingElement.class;
/*  91: 91 */    if (paramString.equals("Light")) {
/*  92: 92 */      return LightElement.class;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    throw new ElementParserException("The type " + paramString + " is not known. Only Element, Terrain, Ship, SpaceStation, or DeathStar is permitted");
/*  96:    */  }
/*  97:    */  
/* 101:101 */  private final ArrayList infoElements = new ArrayList();
/* 102:    */  
/* 104:    */  private ElementCategory rootCategory;
/* 105:    */  
/* 107:    */  public ArrayList getInfoElements()
/* 108:    */  {
/* 109:109 */    return this.infoElements;
/* 110:    */  }
/* 111:    */  
/* 114:    */  public ElementCategory getRootCategory()
/* 115:    */  {
/* 116:116 */    return this.rootCategory;
/* 117:    */  }
/* 118:    */  
/* 119:    */  private void handleElementNode(Node paramNode, ElementCategory paramElementCategory) {
/* 120:120 */    Object localObject1 = paramNode.getParentNode().getNodeName();
/* 121:    */    
/* 122:122 */    if (((localObject2 = paramNode.getAttributes()) != null) && (((NamedNodeMap)localObject2).getLength() != 4)) {
/* 123:123 */      throw new ElementParserException("Element has wrong attribute count (" + ((NamedNodeMap)localObject2).getLength() + ", but should be 4)");
/* 124:    */    }
/* 125:    */    
/* 126:126 */    Node localNode1 = parseType(paramNode, (NamedNodeMap)localObject2, "type");
/* 127:    */    
/* 128:128 */    Node localNode2 = parseType(paramNode, (NamedNodeMap)localObject2, "icon");
/* 129:    */    
/* 130:130 */    Node localNode3 = parseType(paramNode, (NamedNodeMap)localObject2, "textureId");
/* 131:    */    
/* 132:132 */    Object localObject2 = parseType(paramNode, (NamedNodeMap)localObject2, "name");
/* 133:    */    
/* 134:    */    String str;
/* 135:    */    
/* 136:136 */    if ((str = this.properties.getProperty(localNode1.getNodeValue())) == null)
/* 137:137 */      throw new ElementParserException("The value of " + localNode1.getNodeName() + " has not been found");
/* 138:    */    short s;
/* 139:    */    try {
/* 140:140 */      s = (short)Integer.parseInt(str);
/* 141:    */    } catch (NumberFormatException localNumberFormatException) {
/* 142:142 */      throw new ElementParserException("The property " + str + " has to be an Integer value");
/* 143:    */    }
/* 144:    */    
/* 145:145 */    if (((localObject1 = new ElementInformation(s, ((Node)localObject2).getNodeValue(), getTypeFromString((String)localObject1), (short)parseIntAttribute(localNode3))).getId() < 0) || (((ElementInformation)localObject1).getId() >= 2048)) {
/* 146:146 */      throw new ElementParserException("Element type has to be between [0, 2048[ for " + paramNode.getNodeName());
/* 147:    */    }
/* 148:148 */    if ((((ElementInformation)localObject1).getTextureId() < 0) || (((ElementInformation)localObject1).getTextureId() >= 2048)) {
/* 149:149 */      throw new ElementParserException("Texture Id has to be between [0, 2048[ for " + paramNode.getNodeName());
/* 150:    */    }
/* 151:    */    
/* 153:153 */    ((ElementInformation)localObject1).setBuildIconNum(parseInt(localNode2));
/* 154:154 */    if ((((ElementInformation)localObject1).getBuildIconNum() < 0) || (((ElementInformation)localObject1).getBuildIconNum() >= 2048)) {
/* 155:155 */      throw new ElementParserException("Icon has to be between [0, 2048[ for " + paramNode.getNodeName() + " but was " + ((ElementInformation)localObject1).getBuildIconNum());
/* 156:    */    }
/* 157:    */    
/* 160:160 */    parseInfoNode(paramNode, (ElementInformation)localObject1);
/* 161:    */    
/* 162:162 */    this.infoElements.add(localObject1);
/* 163:    */    
/* 164:164 */    paramElementCategory.getInfoElements().add(localObject1);
/* 165:    */  }
/* 166:    */  
/* 167:    */  private void handleNode(Node paramNode, ElementCategory paramElementCategory) {
/* 168:168 */    if (!paramNode.hasAttributes()) {
/* 169:169 */      handleTypeNode(paramNode, paramElementCategory);return;
/* 170:    */    }
/* 171:171 */    handleElementNode(paramNode, paramElementCategory);
/* 172:    */  }
/* 173:    */  
/* 174:    */  private void handleTypeNode(Node paramNode, ElementCategory paramElementCategory) {
/* 175:175 */    if (paramNode.getNodeType() == 1) {
/* 176:176 */      ElementCategory localElementCategory = new ElementCategory(getTypeFromString(paramNode.getNodeName()), paramElementCategory);
/* 177:    */      
/* 178:178 */      paramElementCategory.getChildren().add(localElementCategory);
/* 179:179 */      paramNode = paramNode.getChildNodes();
/* 180:180 */      for (paramElementCategory = 0; paramElementCategory < paramNode.getLength(); paramElementCategory++) {
/* 181:181 */        handleNode(paramNode.item(paramElementCategory), localElementCategory);
/* 182:    */      }
/* 183:    */    }
/* 184:    */  }
/* 185:    */  
/* 186:    */  public void loadAndParseDefault() {
/* 187:187 */    read(new File("./data/config/BlockConfig.xml"), "./data/config/BlockTypes.properties");
/* 188:188 */    parse();
/* 189:    */  }
/* 190:    */  
/* 191:191 */  public void loadAndParseCustomXML(File paramFile, String paramString) { read(paramFile, paramString == null ? "./data/config/BlockTypes.properties" : paramString);
/* 192:192 */    parse();
/* 193:    */  }
/* 194:    */  
/* 195:    */  public void parse() {
/* 196:196 */    Object localObject = this.doc.getDocumentElement();
/* 197:197 */    this.rootCategory = new ElementCategory(Element.class, null);
/* 198:198 */    if (!((org.w3c.dom.Element)localObject).getNodeName().equals("Element")) {
/* 199:199 */      throw new ElementParserException("No root element with the name \"Element\" found");
/* 200:    */    }
/* 201:201 */    localObject = ((org.w3c.dom.Element)localObject).getChildNodes();
/* 202:202 */    for (int i = 0; i < ((NodeList)localObject).getLength(); i++) {
/* 203:203 */      handleNode(((NodeList)localObject).item(i), this.rootCategory);
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 208:208 */  private void parseAnimated(Node paramNode, ElementInformation paramElementInformation) { paramElementInformation.setAnimated(parseBoolean(paramNode)); }
/* 209:    */  
/* 210:    */  private void parseArmour(Node paramNode, ElementInformation paramElementInformation) {
/* 211:211 */    paramElementInformation.setAmour(parseInt(paramNode));
/* 212:    */    
/* 213:213 */    if ((paramElementInformation.getAmour() < 0) || (paramElementInformation.getAmour() > 100)) {
/* 214:214 */      throw new ElementParserException("Armour for " + paramNode.getParentNode().getNodeName() + " has to be between 0% and 100%");
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 218:    */  private void parseBlockStyle(Node paramNode, ElementInformation paramElementInformation) {
/* 219:219 */    paramElementInformation.setBlockStyle(parseInt(paramNode));
/* 220:    */  }
/* 221:    */  
/* 222:    */  private boolean parseBoolean(Node paramNode)
/* 223:    */  {
/* 224:    */    try {
/* 225:225 */      return Boolean.parseBoolean(paramNode.getTextContent());
/* 226:226 */    } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/* 227:    */      
/* 228:228 */      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Boolean value for " + paramNode.getParentNode().getNodeName());
/* 229:    */    }
/* 230:    */  }
/* 231:    */  
/* 232:232 */  private void parseCanActivate(Node paramNode, ElementInformation paramElementInformation) { paramElementInformation.setCanActivate(parseBoolean(paramNode)); }
/* 233:    */  
/* 235:    */  private void parseControlledBy(Node paramNode, ElementInformation paramElementInformation)
/* 236:    */  {
/* 237:237 */    NodeList localNodeList = paramNode.getChildNodes();
/* 238:238 */    for (int i = 0; i < localNodeList.getLength(); i++)
/* 239:    */    {
/* 240:    */      Node localNode;
/* 241:241 */      if ((localNode = localNodeList.item(i)).getNodeType() == 1)
/* 242:    */      {
/* 243:243 */        if (!localNode.getNodeName().equals("Element")) {
/* 244:244 */          throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"Element\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/* 245:    */        }
/* 246:    */        
/* 247:    */        String str;
/* 248:    */        
/* 249:249 */        if ((str = this.properties.getProperty(localNode.getTextContent())) == null)
/* 250:250 */          throw new ElementParserException("The value of " + localNode.getTextContent() + " has not been found");
/* 251:    */        short s;
/* 252:    */        try {
/* 253:253 */          s = (short)Integer.parseInt(str);
/* 254:    */        } catch (NumberFormatException localNumberFormatException) {
/* 255:255 */          throw new ElementParserException("The property " + str + " has to be an Integer value");
/* 256:    */        }
/* 257:    */        
/* 258:258 */        paramElementInformation.getControlledBy().add(Short.valueOf(s));
/* 259:    */      }
/* 260:    */    }
/* 261:    */  }
/* 262:    */  
/* 264:    */  private void parseControlling(Node paramNode, ElementInformation paramElementInformation)
/* 265:    */  {
/* 266:266 */    NodeList localNodeList = paramNode.getChildNodes();
/* 267:267 */    for (int i = 0; i < localNodeList.getLength(); i++)
/* 268:    */    {
/* 269:    */      Node localNode;
/* 270:270 */      if ((localNode = localNodeList.item(i)).getNodeType() == 1)
/* 271:    */      {
/* 272:272 */        if (!localNode.getNodeName().equals("Element")) {
/* 273:273 */          throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"Element\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/* 274:    */        }
/* 275:    */        
/* 276:    */        String str;
/* 277:    */        
/* 278:278 */        if ((str = this.properties.getProperty(localNode.getTextContent())) == null)
/* 279:279 */          throw new ElementParserException("The value of " + localNode.getTextContent() + " has not been found");
/* 280:    */        short s;
/* 281:    */        try {
/* 282:282 */          s = (short)Integer.parseInt(str);
/* 283:    */        } catch (NumberFormatException localNumberFormatException) {
/* 284:284 */          throw new ElementParserException("The property " + str + " has to be an Integer value");
/* 285:    */        }
/* 286:    */        
/* 287:287 */        paramElementInformation.getControlling().add(Short.valueOf(s));
/* 288:    */      }
/* 289:    */    }
/* 290:    */  }
/* 291:    */  
/* 293:    */  private FactoryResource[] parseResource(Node paramNode)
/* 294:    */  {
/* 295:295 */    ArrayList localArrayList = new ArrayList();
/* 296:296 */    NodeList localNodeList = paramNode.getChildNodes();
/* 297:297 */    for (int i = 0; i < localNodeList.getLength(); i++) {
/* 298:    */      Node localNode;
/* 299:299 */      if ((localNode = localNodeList.item(i)).getNodeType() == 1) {
/* 300:300 */        if (!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("item")) {
/* 301:301 */          throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"item\" but is " + paramNode.getParentNode().getNodeName() + " (" + paramNode.getParentNode().getParentNode().getNodeName() + ")");
/* 302:    */        }
/* 303:    */        
/* 305:305 */        if (((localObject = localNode.getAttributes()) != null) && (((NamedNodeMap)localObject).getLength() != 1)) {
/* 306:306 */          throw new ElementParserException("Element has wrong attribute count (" + ((NamedNodeMap)localObject).getLength() + ", but should be 4)");
/* 307:    */        }
/* 308:    */        
/* 309:309 */        Object localObject = parseType(localNode, (NamedNodeMap)localObject, "count");
/* 310:310 */        int j; try { j = Integer.parseInt(((Node)localObject).getNodeValue());
/* 311:    */        }
/* 312:    */        catch (NumberFormatException localNumberFormatException1)
/* 313:    */        {
/* 314:314 */          throw new ElementParserException("Cant parse count in " + localNode.getNodeName() + ", in " + paramNode.getParentNode().getNodeName() + " (" + paramNode.getParentNode().getParentNode().getNodeName() + ")");
/* 315:    */        }
/* 316:    */        
/* 318:    */        String str;
/* 319:    */        
/* 321:321 */        if ((str = this.properties.getProperty(localNode.getTextContent())) == null)
/* 322:322 */          throw new ElementParserException("The value of " + ((Node)localObject).getNodeName() + " has not been found");
/* 323:    */        short s;
/* 324:    */        try {
/* 325:325 */          s = (short)Integer.parseInt(str);
/* 326:    */        } catch (NumberFormatException localNumberFormatException2) {
/* 327:327 */          throw new ElementParserException("The property " + str + " has to be an Integer value");
/* 328:    */        }
/* 329:329 */        localArrayList.add(new FactoryResource(j, s));
/* 330:    */      }
/* 331:    */    }
/* 332:    */    
/* 333:333 */    FactoryResource[] arrayOfFactoryResource = new FactoryResource[localArrayList.size()];
/* 334:334 */    localArrayList.toArray(arrayOfFactoryResource);
/* 335:335 */    return arrayOfFactoryResource;
/* 336:    */  }
/* 337:    */  
/* 338:    */  private void parseFactory(Node paramNode, ElementInformation paramElementInformation) {
/* 339:339 */    ArrayList localArrayList1 = new ArrayList();
/* 340:340 */    ArrayList localArrayList2 = new ArrayList();
/* 341:341 */    BlockFactory localBlockFactory = new BlockFactory();
/* 342:342 */    paramElementInformation.setFactory(localBlockFactory);
/* 343:343 */    if (paramNode.getTextContent().toLowerCase(Locale.ENGLISH).equals("input"))
/* 344:    */    {
/* 345:345 */      return;
/* 346:    */    }
/* 347:347 */    paramElementInformation = paramNode.getChildNodes();
/* 348:348 */    for (int i = 0; i < paramElementInformation.getLength(); i++) {
/* 349:    */      Object localObject;
/* 350:350 */      if ((localObject = paramElementInformation.item(i)).getNodeType() == 1) {
/* 351:351 */        if (!((Node)localObject).getNodeName().toLowerCase(Locale.ENGLISH).equals("product")) {
/* 352:352 */          throw new ElementParserException("All child nodes of " + ((Node)localObject).getNodeName() + " have to be \"product\" but is " + ((Node)localObject).getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/* 353:    */        }
/* 354:354 */        localObject = ((Node)localObject).getChildNodes();
/* 355:355 */        FactoryResource[] arrayOfFactoryResource1 = null;
/* 356:356 */        FactoryResource[] arrayOfFactoryResource2 = null;
/* 357:357 */        for (int j = 0; j < ((NodeList)localObject).getLength(); j++) {
/* 358:    */          Node localNode;
/* 359:359 */          if ((localNode = ((NodeList)localObject).item(j)).getNodeType() == 1) {
/* 360:360 */            if ((!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("output")) && (!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("input")))
/* 361:    */            {
/* 362:362 */              throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"output\" or \"input\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/* 363:    */            }
/* 364:    */            
/* 365:365 */            if (localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("input")) {
/* 366:366 */              arrayOfFactoryResource1 = parseResource(localNode);
/* 367:    */            }
/* 368:368 */            if (localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("output")) {
/* 369:369 */              arrayOfFactoryResource2 = parseResource(localNode);
/* 370:    */            }
/* 371:    */          }
/* 372:    */        }
/* 373:    */        
/* 374:374 */        if (arrayOfFactoryResource1 == null) {
/* 375:375 */          throw new ElementParserException("No input defined for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
/* 376:    */        }
/* 377:377 */        if (arrayOfFactoryResource2 == null) {
/* 378:378 */          throw new ElementParserException("No output defined for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
/* 379:    */        }
/* 380:    */        
/* 381:381 */        localArrayList1.add(arrayOfFactoryResource1);
/* 382:382 */        localArrayList2.add(arrayOfFactoryResource2);
/* 383:    */      }
/* 384:    */    }
/* 385:385 */    if (localArrayList1.size() != localArrayList2.size()) {
/* 386:386 */      throw new ElementParserException("Factory Parsing failed for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
/* 387:    */    }
/* 388:    */    
/* 389:389 */    localBlockFactory.input = new FactoryResource[localArrayList1.size()][];
/* 390:390 */    localBlockFactory.output = new FactoryResource[localArrayList2.size()][];
/* 391:    */    
/* 392:392 */    for (i = 0; i < localBlockFactory.input.length; i++) {
/* 393:393 */      localBlockFactory.input[i] = ((FactoryResource[])localArrayList1.get(i));
/* 394:394 */      localBlockFactory.output[i] = ((FactoryResource[])localArrayList2.get(i));
/* 395:    */    }
/* 396:    */  }
/* 397:    */  
/* 398:398 */  private void parseLevel(Node paramNode, ElementInformation paramElementInformation) { NodeList localNodeList = paramNode.getChildNodes();
/* 399:399 */    int i = 0;
/* 400:400 */    int j = 0;
/* 401:401 */    short s = 0;
/* 402:402 */    int k = 0;
/* 403:403 */    for (int m = 0; m < localNodeList.getLength(); m++)
/* 404:    */    {
/* 405:    */      Node localNode;
/* 406:406 */      if ((localNode = localNodeList.item(m)).getNodeType() == 1)
/* 407:    */      {
/* 408:408 */        if (localNode.getNodeName().equals("Id")) {
/* 409:409 */          if (i != 0) {
/* 410:410 */            throw new ElementParserException("[LEVEL] Multiple IDs for level in " + paramNode.getParentNode().getNodeName());
/* 411:    */          }
/* 412:    */          
/* 413:    */          String str;
/* 414:414 */          if ((str = this.properties.getProperty(localNode.getTextContent())) == null) {
/* 415:415 */            throw new ElementParserException("[LEVEL] The value of " + localNode.getTextContent() + " has not been found in " + paramNode.getParentNode().getNodeName());
/* 416:    */          }
/* 417:    */          try {
/* 418:418 */            s = (short)Integer.parseInt(str);
/* 419:419 */            i = 1;
/* 420:    */          } catch (NumberFormatException localNumberFormatException1) {
/* 421:421 */            throw new ElementParserException("[LEVEL] The property " + str + " has to be an Integer value in " + paramNode.getParentNode().getNodeName());
/* 422:    */          }
/* 423:    */        }
/* 424:    */        
/* 425:425 */        if (localNode.getNodeName().equals("Nr")) {
/* 426:    */          try {
/* 427:427 */            k = Integer.parseInt(localNode.getTextContent());
/* 428:428 */            j = 1;
/* 429:    */          } catch (NumberFormatException localNumberFormatException2) {
/* 430:430 */            throw new ElementParserException("[LEVEL] Nr Value '" + localNode.getTextContent() + "' is not an Integer value in " + paramNode.getParentNode().getNodeName());
/* 431:    */          }
/* 432:    */        }
/* 433:    */      }
/* 434:    */    }
/* 435:    */    
/* 437:437 */    if (i == 0) {
/* 438:438 */      throw new ElementParserException("[LEVEL] No level id in " + paramNode.getParentNode().getNodeName());
/* 439:    */    }
/* 440:440 */    if (j == 0) {
/* 441:441 */      throw new ElementParserException("[LEVEL] No level nr in " + paramNode.getParentNode().getNodeName());
/* 442:    */    }
/* 443:443 */    BlockLevel localBlockLevel = new BlockLevel(s, k);
/* 444:444 */    paramElementInformation.setLevel(localBlockLevel);
/* 445:    */  }
/* 446:    */  
/* 451:    */  private void parseDescription(Node paramNode, ElementInformation paramElementInformation)
/* 452:    */  {
/* 453:453 */    paramNode = paramNode.getTextContent().replaceAll("\\r\\n|\\r|\\n", "").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r").replaceAll("\\\\t", "\t");
/* 454:    */    
/* 456:456 */    paramElementInformation.setDescription(paramNode);
/* 457:    */  }
/* 458:    */  
/* 459:    */  private void parseEnterable(Node paramNode, ElementInformation paramElementInformation) {
/* 460:460 */    paramElementInformation.setEnterable(parseBoolean(paramNode));
/* 461:    */  }
/* 462:    */  
/* 463:    */  private float parseFloat(Node paramNode) {
/* 464:    */    try {
/* 465:465 */      return Float.parseFloat(paramNode.getTextContent());
/* 466:466 */    } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/* 467:    */      
/* 468:468 */      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be a Float value for " + paramNode.getParentNode().getNodeName());
/* 469:    */    }
/* 470:    */  }
/* 471:    */  
/* 472:    */  private void parseFullName(Node paramNode, ElementInformation paramElementInformation)
/* 473:    */  {
/* 474:474 */    paramNode = paramNode.getTextContent();
/* 475:    */    
/* 477:477 */    paramElementInformation.setFullName(paramNode);
/* 478:    */  }
/* 479:    */  
/* 480:    */  private void parseHitpoints(Node paramNode, ElementInformation paramElementInformation) {
/* 481:481 */    paramElementInformation.setMaxHitPoints((short)parseInt(paramNode));
/* 482:482 */    if ((paramElementInformation.getMaxHitPoints() < 0) || (paramElementInformation.getMaxHitPoints() > 1023)) {
/* 483:483 */      throw new ElementParserException("Hitpoints for " + paramNode.getParentNode().getNodeName() + " has to be between [1, 1024[");
/* 484:    */    }
/* 485:    */  }
/* 486:    */  
/* 487:    */  private void parseIndividualSides(Node paramNode, ElementInformation paramElementInformation) {
/* 488:488 */    paramElementInformation.setIndividualSides(parseInt(paramNode));
/* 489:489 */    if ((paramElementInformation.getIndividualSides() != 1) && (paramElementInformation.getIndividualSides() != 3) && (paramElementInformation.getIndividualSides() != 6))
/* 490:    */    {
/* 491:491 */      throw new ElementParserException("Individual Sides for " + paramNode.getParentNode().getNodeName() + " has to be either 1 (default), 3, or 6, but was: " + paramElementInformation.getIndividualSides()); }
/* 492:    */  }
/* 493:    */  
/* 494:    */  private void parseInfoNode(Node paramNode, ElementInformation paramElementInformation) {
/* 495:495 */    NodeList localNodeList = paramNode.getChildNodes();
/* 496:    */    
/* 497:497 */    for (int i = 0; i < localNodeList.getLength(); i++) {
/* 498:    */      Node localNode;
/* 499:499 */      if ((localNode = localNodeList.item(i)).getNodeType() == 1) {
/* 500:500 */        if (localNode.getNodeName().equals("InShop")) {
/* 501:501 */          parseInShop(localNode, paramElementInformation);
/* 502:502 */        } else if (localNode.getNodeName().equals("Placable")) {
/* 503:503 */          parsePlacable(localNode, paramElementInformation);
/* 504:504 */        } else if (localNode.getNodeName().equals("ControlledBy")) {
/* 505:505 */          parseControlledBy(localNode, paramElementInformation);
/* 506:506 */        } else if (localNode.getNodeName().equals("Controlling")) {
/* 507:507 */          parseControlling(localNode, paramElementInformation);
/* 508:508 */        } else if (localNode.getNodeName().equals("Level")) {
/* 509:509 */          parseLevel(localNode, paramElementInformation);
/* 510:510 */        } else if (localNode.getNodeName().equals("Enterable")) {
/* 511:511 */          parseEnterable(localNode, paramElementInformation);
/* 512:512 */        } else if (localNode.getNodeName().equals("CanActivate")) {
/* 513:513 */          parseCanActivate(localNode, paramElementInformation);
/* 514:514 */        } else if (localNode.getNodeName().equals("LightSource")) {
/* 515:515 */          parseLightSource(localNode, paramElementInformation);
/* 516:516 */        } else if (localNode.getNodeName().equals("Physical")) {
/* 517:517 */          parsePhysical(localNode, paramElementInformation);
/* 518:518 */        } else if (localNode.getNodeName().equals("BlockStyle")) {
/* 519:519 */          parseBlockStyle(localNode, paramElementInformation);
/* 520:520 */        } else if (localNode.getNodeName().equals("LightSourceColor")) {
/* 521:521 */          parseLightSourceColor(localNode, paramElementInformation);
/* 522:522 */        } else if (localNode.getNodeName().equals("Hitpoints")) {
/* 523:523 */          parseHitpoints(localNode, paramElementInformation);
/* 524:524 */        } else if (localNode.getNodeName().equals("Armour")) {
/* 525:525 */          parseArmour(localNode, paramElementInformation);
/* 526:526 */        } else if (localNode.getNodeName().equals("Transparency")) {
/* 527:527 */          parseTransparency(localNode, paramElementInformation);
/* 528:528 */        } else if (localNode.getNodeName().equals("Description")) {
/* 529:529 */          parseDescription(localNode, paramElementInformation);
/* 530:530 */        } else if (localNode.getNodeName().equals("Factory")) {
/* 531:531 */          parseFactory(localNode, paramElementInformation);
/* 532:532 */        } else if (localNode.getNodeName().equals("FullName")) {
/* 533:533 */          parseFullName(localNode, paramElementInformation);
/* 534:534 */        } else if (localNode.getNodeName().equals("Animated")) {
/* 535:535 */          parseAnimated(localNode, paramElementInformation);
/* 536:536 */        } else if (localNode.getNodeName().equals("Price")) {
/* 537:537 */          parsePrice(localNode, paramElementInformation);
/* 538:538 */        } else if (localNode.getNodeName().equals("InShop")) {
/* 539:539 */          parseInShop(localNode, paramElementInformation);
/* 540:540 */        } else if (localNode.getNodeName().equals("Orientation")) {
/* 541:541 */          parseOrientation(localNode, paramElementInformation);
/* 542:542 */        } else if (localNode.getNodeName().equals("IndividualSides")) {
/* 543:543 */          parseIndividualSides(localNode, paramElementInformation);
/* 544:    */        } else {
/* 545:545 */          throw new ElementParserException("Element Info " + localNode.getNodeName() + " not found. location: " + paramNode.getNodeName());
/* 546:    */        }
/* 547:    */      }
/* 548:    */    }
/* 549:    */  }
/* 550:    */  
/* 551:    */  private void parseInShop(Node paramNode, ElementInformation paramElementInformation) {
/* 552:552 */    paramElementInformation.setShoppable(parseBoolean(paramNode));
/* 553:    */  }
/* 554:    */  
/* 555:    */  private int parseInt(Node paramNode) {
/* 556:    */    try {
/* 557:557 */      return Integer.parseInt(paramNode.getTextContent());
/* 558:558 */    } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/* 559:    */      
/* 560:560 */      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Integer value for " + paramNode.getParentNode().getNodeName());
/* 561:    */    }
/* 562:    */  }
/* 563:    */  
/* 564:    */  private int parseIntAttribute(Node paramNode) {
/* 565:    */    try {
/* 566:566 */      return Integer.parseInt(paramNode.getNodeValue());
/* 567:567 */    } catch (NumberFormatException localNumberFormatException) { localNumberFormatException.printStackTrace();
/* 568:    */      
/* 569:569 */      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Integer value for " + paramNode.getParentNode().getNodeName());
/* 570:    */    }
/* 571:    */  }
/* 572:    */  
/* 573:573 */  private void parseLightSource(Node paramNode, ElementInformation paramElementInformation) { paramElementInformation.setLightSource(parseBoolean(paramNode)); }
/* 574:    */  
/* 575:    */  private void parseLightSourceColor(Node paramNode, ElementInformation paramElementInformation)
/* 576:    */  {
/* 577:577 */    paramElementInformation.getLightSourceColor().set(parseVector3f(paramNode));
/* 578:    */  }
/* 579:    */  
/* 580:580 */  private void parseOrientation(Node paramNode, ElementInformation paramElementInformation) { paramElementInformation.setOrientatable(parseBoolean(paramNode)); }
/* 581:    */  
/* 582:    */  private void parsePhysical(Node paramNode, ElementInformation paramElementInformation) {
/* 583:583 */    paramElementInformation.setPhysical(parseBoolean(paramNode));
/* 584:    */  }
/* 585:    */  
/* 587:587 */  private void parsePlacable(Node paramNode, ElementInformation paramElementInformation) { paramElementInformation.setPlacable(parseBoolean(paramNode)); }
/* 588:    */  
/* 589:    */  private void parsePrice(Node paramNode, ElementInformation paramElementInformation) {
/* 590:590 */    paramElementInformation.setPrice(parseInt(paramNode));
/* 591:591 */    if (paramElementInformation.getPrice() < 0L) {
/* 592:592 */      throw new ElementParserException("Price for " + paramNode.getParentNode().getNodeName() + " has to be greater or equal zero");
/* 593:    */    }
/* 594:    */  }
/* 595:    */  
/* 596:    */  private void parseTransparency(Node paramNode, ElementInformation paramElementInformation) {
/* 597:597 */    paramElementInformation.setBlended(parseBoolean(paramNode));
/* 598:    */  }
/* 599:    */  
/* 600:    */  private Node parseType(Node paramNode, NamedNodeMap paramNamedNodeMap, String paramString)
/* 601:    */  {
/* 602:602 */    if ((paramNamedNodeMap = paramNamedNodeMap.getNamedItem(paramString)) == null) {
/* 603:603 */      throw new ElementParserException("Obligatory attribute \"" + paramString + "\" not found in " + paramNode.getNodeName());
/* 604:    */    }
/* 605:605 */    return paramNamedNodeMap;
/* 606:    */  }
/* 607:    */  
/* 608:    */  private Vector3f parseVector3f(Node paramNode) {
/* 609:609 */    Vector3f localVector3f = new Vector3f();
/* 610:    */    
/* 611:    */    String[] arrayOfString;
/* 612:    */    
/* 613:613 */    if ((arrayOfString = paramNode.getTextContent().split(",")).length != 3) {
/* 614:614 */      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be 3 Float values seperated by commas");
/* 615:    */    }
/* 616:    */    try {
/* 617:617 */      localVector3f.set(Float.parseFloat(arrayOfString[0].trim()), Float.parseFloat(arrayOfString[1].trim()), Float.parseFloat(arrayOfString[2].trim()));
/* 618:    */    } catch (NumberFormatException localNumberFormatException) {
/* 619:619 */      throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be a Float value");
/* 620:    */    }
/* 621:621 */    return localVector3f;
/* 622:    */  }
/* 623:    */  
/* 624:    */  public void read(File paramFile, String paramString) {
/* 625:625 */    DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/* 626:626 */    this.doc = localDocumentBuilder.parse(paramFile);
/* 627:    */    
/* 629:629 */    this.properties = new Properties();
/* 630:630 */    paramFile = new FileInputStream(paramString);
/* 631:631 */    this.properties.load(paramFile);
/* 632:632 */    paramFile.close();
/* 633:    */    
/* 634:634 */    ElementKeyMap.properties = this.properties;
/* 635:    */  }
/* 636:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */