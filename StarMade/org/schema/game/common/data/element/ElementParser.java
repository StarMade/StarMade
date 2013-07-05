/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.schema.game.common.data.element.deathstar.DeathStarElement;
/*     */ import org.schema.game.common.data.element.factory.FactoryElement;
/*     */ import org.schema.game.common.data.element.factory.ManufacturingElement;
/*     */ import org.schema.game.common.data.element.ship.ShipElement;
/*     */ import org.schema.game.common.data.element.spacestation.SpaceStationElement;
/*     */ import org.schema.game.common.data.element.terrain.MineralElement;
/*     */ import org.schema.game.common.data.element.terrain.TerrainElement;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class ElementParser
/*     */ {
/*     */   private Document doc;
/*     */   private Properties properties;
/* 101 */   private final ArrayList infoElements = new ArrayList();
/*     */   private ElementCategory rootCategory;
/*     */ 
/*     */   public static String getStringFromType(Class paramClass)
/*     */   {
/*  37 */     if (paramClass == TerrainElement.class)
/*  38 */       return "Terrain";
/*  39 */     if (paramClass == GeneralElement.class)
/*  40 */       return "General";
/*  41 */     if (paramClass == ShipElement.class)
/*  42 */       return "Ship";
/*  43 */     if (paramClass == Element.class)
/*  44 */       return "Element";
/*  45 */     if (paramClass == SpaceStationElement.class)
/*  46 */       return "SpaceStation";
/*  47 */     if (paramClass == DeathStarElement.class)
/*  48 */       return "DeathStar";
/*  49 */     if (paramClass == MineralElement.class)
/*  50 */       return "Mineral";
/*  51 */     if (paramClass == FactoryElement.class)
/*  52 */       return "Factory";
/*  53 */     if (paramClass == ManufacturingElement.class)
/*  54 */       return "Manufacturing";
/*  55 */     if (paramClass == HullElement.class)
/*  56 */       return "Hulls";
/*  57 */     if (paramClass == DockingElement.class)
/*  58 */       return "Docking";
/*  59 */     if (paramClass == LightElement.class) {
/*  60 */       return "Light";
/*     */     }
/*     */ 
/*  63 */     throw new ElementParserException("The type " + paramClass + " is not known. Only Element, Terrain, Ship, SpaceStation, or DeathStar is permitted");
/*     */   }
/*     */   public static String[] getTypeStringArray() {
/*  66 */     return new String[] { "General", "Terrain", "Ship", "Element", "SpaceStation", "General", "DeathStar", "Factory", "Manufacturing", "Mineral", "Hulls", "Docking", "Light" };
/*     */   }
/*     */   public static Class getTypeFromString(String paramString) {
/*  69 */     if (paramString.equals("Terrain"))
/*  70 */       return TerrainElement.class;
/*  71 */     if (paramString.equals("Ship"))
/*  72 */       return ShipElement.class;
/*  73 */     if (paramString.equals("Element"))
/*  74 */       return Element.class;
/*  75 */     if (paramString.equals("SpaceStation"))
/*  76 */       return SpaceStationElement.class;
/*  77 */     if (paramString.equals("General"))
/*  78 */       return GeneralElement.class;
/*  79 */     if (paramString.equals("DeathStar"))
/*  80 */       return DeathStarElement.class;
/*  81 */     if (paramString.equals("Factory"))
/*  82 */       return FactoryElement.class;
/*  83 */     if (paramString.equals("Manufacturing"))
/*  84 */       return ManufacturingElement.class;
/*  85 */     if (paramString.equals("Mineral"))
/*  86 */       return MineralElement.class;
/*  87 */     if (paramString.equals("Hulls"))
/*  88 */       return HullElement.class;
/*  89 */     if (paramString.equals("Docking"))
/*  90 */       return DockingElement.class;
/*  91 */     if (paramString.equals("Light")) {
/*  92 */       return LightElement.class;
/*     */     }
/*     */ 
/*  95 */     throw new ElementParserException("The type " + paramString + " is not known. Only Element, Terrain, Ship, SpaceStation, or DeathStar is permitted");
/*     */   }
/*     */ 
/*     */   public ArrayList getInfoElements()
/*     */   {
/* 109 */     return this.infoElements;
/*     */   }
/*     */ 
/*     */   public ElementCategory getRootCategory()
/*     */   {
/* 116 */     return this.rootCategory;
/*     */   }
/*     */ 
/*     */   private void handleElementNode(Node paramNode, ElementCategory paramElementCategory) {
/* 120 */     Object localObject1 = paramNode.getParentNode().getNodeName();
/*     */ 
/* 122 */     if (((
/* 122 */       localObject2 = paramNode.getAttributes()) != null) && 
/* 122 */       (((NamedNodeMap)localObject2).getLength() != 4)) {
/* 123 */       throw new ElementParserException("Element has wrong attribute count (" + ((NamedNodeMap)localObject2).getLength() + ", but should be 4)");
/*     */     }
/*     */ 
/* 126 */     Node localNode1 = parseType(paramNode, (NamedNodeMap)localObject2, "type");
/*     */ 
/* 128 */     Node localNode2 = parseType(paramNode, (NamedNodeMap)localObject2, "icon");
/*     */ 
/* 130 */     Node localNode3 = parseType(paramNode, (NamedNodeMap)localObject2, "textureId");
/*     */ 
/* 132 */     Object localObject2 = parseType(paramNode, (NamedNodeMap)localObject2, "name");
/*     */     String str;
/* 136 */     if ((
/* 136 */       str = this.properties.getProperty(localNode1.getNodeValue())) == null)
/*     */     {
/* 137 */       throw new ElementParserException("The value of " + localNode1.getNodeName() + " has not been found");
/*     */     }short s;
/*     */     try {
/* 140 */       s = (short)Integer.parseInt(str);
/*     */     } catch (NumberFormatException localNumberFormatException) {
/* 142 */       throw new ElementParserException("The property " + str + " has to be an Integer value");
/*     */     }
/*     */ 
/* 145 */     if (((
/* 145 */       localObject1 = new ElementInformation(s, ((Node)localObject2).getNodeValue(), getTypeFromString((String)localObject1), (short)parseIntAttribute(localNode3)))
/* 145 */       .getId() < 0) || (((ElementInformation)localObject1).getId() >= 2048)) {
/* 146 */       throw new ElementParserException("Element type has to be between [0, 2048[ for " + paramNode.getNodeName());
/*     */     }
/* 148 */     if ((((ElementInformation)localObject1).getTextureId() < 0) || (((ElementInformation)localObject1).getTextureId() >= 2048)) {
/* 149 */       throw new ElementParserException("Texture Id has to be between [0, 2048[ for " + paramNode.getNodeName());
/*     */     }
/*     */ 
/* 153 */     ((ElementInformation)localObject1).setBuildIconNum(parseInt(localNode2));
/* 154 */     if ((((ElementInformation)localObject1).getBuildIconNum() < 0) || (((ElementInformation)localObject1).getBuildIconNum() >= 2048)) {
/* 155 */       throw new ElementParserException("Icon has to be between [0, 2048[ for " + paramNode.getNodeName() + " but was " + ((ElementInformation)localObject1).getBuildIconNum());
/*     */     }
/*     */ 
/* 160 */     parseInfoNode(paramNode, (ElementInformation)localObject1);
/*     */ 
/* 162 */     this.infoElements.add(localObject1);
/*     */ 
/* 164 */     paramElementCategory.getInfoElements().add(localObject1);
/*     */   }
/*     */ 
/*     */   private void handleNode(Node paramNode, ElementCategory paramElementCategory) {
/* 168 */     if (!paramNode.hasAttributes()) {
/* 169 */       handleTypeNode(paramNode, paramElementCategory); return;
/*     */     }
/* 171 */     handleElementNode(paramNode, paramElementCategory);
/*     */   }
/*     */ 
/*     */   private void handleTypeNode(Node paramNode, ElementCategory paramElementCategory) {
/* 175 */     if (paramNode.getNodeType() == 1) {
/* 176 */       ElementCategory localElementCategory = new ElementCategory(getTypeFromString(paramNode.getNodeName()), paramElementCategory);
/*     */ 
/* 178 */       paramElementCategory.getChildren().add(localElementCategory);
/* 179 */       paramNode = paramNode.getChildNodes();
/* 180 */       for (paramElementCategory = 0; paramElementCategory < paramNode.getLength(); paramElementCategory++)
/* 181 */         handleNode(paramNode.item(paramElementCategory), localElementCategory);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void loadAndParseDefault()
/*     */   {
/* 187 */     read(new File("./data/config/BlockConfig.xml"), "./data/config/BlockTypes.properties");
/* 188 */     parse();
/*     */   }
/*     */   public void loadAndParseCustomXML(File paramFile, String paramString) {
/* 191 */     read(paramFile, paramString == null ? "./data/config/BlockTypes.properties" : paramString);
/* 192 */     parse();
/*     */   }
/*     */ 
/*     */   public void parse() {
/* 196 */     Object localObject = this.doc.getDocumentElement();
/* 197 */     this.rootCategory = new ElementCategory(Element.class, null);
/* 198 */     if (!((org.w3c.dom.Element)localObject).getNodeName().equals("Element")) {
/* 199 */       throw new ElementParserException("No root element with the name \"Element\" found");
/*     */     }
/* 201 */     localObject = ((org.w3c.dom.Element)localObject).getChildNodes();
/* 202 */     for (int i = 0; i < ((NodeList)localObject).getLength(); i++)
/* 203 */       handleNode(((NodeList)localObject).item(i), this.rootCategory);
/*     */   }
/*     */ 
/*     */   private void parseAnimated(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 208 */     paramElementInformation.setAnimated(parseBoolean(paramNode));
/*     */   }
/*     */   private void parseArmour(Node paramNode, ElementInformation paramElementInformation) {
/* 211 */     paramElementInformation.setAmour(parseInt(paramNode));
/*     */ 
/* 213 */     if ((paramElementInformation.getAmour() < 0) || (paramElementInformation.getAmour() > 100))
/* 214 */       throw new ElementParserException("Armour for " + paramNode.getParentNode().getNodeName() + " has to be between 0% and 100%");
/*     */   }
/*     */ 
/*     */   private void parseBlockStyle(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 219 */     paramElementInformation.setBlockStyle(parseInt(paramNode));
/*     */   }
/*     */ 
/*     */   private boolean parseBoolean(Node paramNode)
/*     */   {
/*     */     try {
/* 225 */       return Boolean.parseBoolean(paramNode.getTextContent()); } catch (NumberFormatException localNumberFormatException) { localNumberFormatException
/* 226 */         .printStackTrace();
/*     */     }
/* 228 */     throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Boolean value for " + paramNode.getParentNode().getNodeName());
/*     */   }
/*     */ 
/*     */   private void parseCanActivate(Node paramNode, ElementInformation paramElementInformation) {
/* 232 */     paramElementInformation.setCanActivate(parseBoolean(paramNode));
/*     */   }
/*     */ 
/*     */   private void parseControlledBy(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 237 */     NodeList localNodeList = paramNode.getChildNodes();
/* 238 */     for (int i = 0; i < localNodeList.getLength(); i++)
/*     */     {
/*     */       Node localNode;
/* 241 */       if ((
/* 241 */         localNode = localNodeList.item(i))
/* 241 */         .getNodeType() == 1)
/*     */       {
/* 243 */         if (!localNode.getNodeName().equals("Element"))
/* 244 */           throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"Element\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/*     */         String str;
/* 249 */         if ((
/* 249 */           str = this.properties.getProperty(localNode.getTextContent())) == null)
/*     */         {
/* 250 */           throw new ElementParserException("The value of " + localNode.getTextContent() + " has not been found");
/*     */         }short s;
/*     */         try {
/* 253 */           s = (short)Integer.parseInt(str);
/*     */         } catch (NumberFormatException localNumberFormatException) {
/* 255 */           throw new ElementParserException("The property " + str + " has to be an Integer value");
/*     */         }
/*     */ 
/* 258 */         paramElementInformation.getControlledBy().add(Short.valueOf(s));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseControlling(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 266 */     NodeList localNodeList = paramNode.getChildNodes();
/* 267 */     for (int i = 0; i < localNodeList.getLength(); i++)
/*     */     {
/*     */       Node localNode;
/* 270 */       if ((
/* 270 */         localNode = localNodeList.item(i))
/* 270 */         .getNodeType() == 1)
/*     */       {
/* 272 */         if (!localNode.getNodeName().equals("Element"))
/* 273 */           throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"Element\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/*     */         String str;
/* 278 */         if ((
/* 278 */           str = this.properties.getProperty(localNode.getTextContent())) == null)
/*     */         {
/* 279 */           throw new ElementParserException("The value of " + localNode.getTextContent() + " has not been found");
/*     */         }short s;
/*     */         try {
/* 282 */           s = (short)Integer.parseInt(str);
/*     */         } catch (NumberFormatException localNumberFormatException) {
/* 284 */           throw new ElementParserException("The property " + str + " has to be an Integer value");
/*     */         }
/*     */ 
/* 287 */         paramElementInformation.getControlling().add(Short.valueOf(s));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private FactoryResource[] parseResource(Node paramNode)
/*     */   {
/* 295 */     ArrayList localArrayList = new ArrayList();
/* 296 */     NodeList localNodeList = paramNode.getChildNodes();
/* 297 */     for (int i = 0; i < localNodeList.getLength(); i++)
/*     */     {
/*     */       Node localNode;
/* 299 */       if ((
/* 299 */         localNode = localNodeList.item(i))
/* 299 */         .getNodeType() == 1) {
/* 300 */         if (!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("item")) {
/* 301 */           throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"item\" but is " + paramNode.getParentNode().getNodeName() + " (" + paramNode.getParentNode().getParentNode().getNodeName() + ")");
/*     */         }
/*     */ 
/* 305 */         if (((
/* 305 */           localObject = localNode.getAttributes()) != null) && 
/* 305 */           (((NamedNodeMap)localObject).getLength() != 1)) {
/* 306 */           throw new ElementParserException("Element has wrong attribute count (" + ((NamedNodeMap)localObject).getLength() + ", but should be 4)"); } 
/*     */ Object localObject = parseType(localNode, (NamedNodeMap)localObject, "count");
/*     */         int j;
/*     */         try { j = Integer.parseInt(((Node)localObject).getNodeValue()); }
/*     */         catch (NumberFormatException localNumberFormatException1)
/*     */         {
/* 314 */           throw new ElementParserException("Cant parse count in " + localNode.getNodeName() + ", in " + paramNode.getParentNode().getNodeName() + " (" + paramNode.getParentNode().getParentNode().getNodeName() + ")");
/*     */         }
/*     */         String str;
/* 321 */         if ((
/* 321 */           str = this.properties.getProperty(localNode.getTextContent())) == null)
/*     */         {
/* 322 */           throw new ElementParserException("The value of " + ((Node)localObject).getNodeName() + " has not been found");
/*     */         }short s;
/*     */         try {
/* 325 */           s = (short)Integer.parseInt(str);
/*     */         } catch (NumberFormatException localNumberFormatException2) {
/* 327 */           throw new ElementParserException("The property " + str + " has to be an Integer value");
/*     */         }
/* 329 */         localArrayList.add(new FactoryResource(j, s));
/*     */       }
/*     */     }
/*     */ 
/* 333 */     FactoryResource[] arrayOfFactoryResource = new FactoryResource[localArrayList.size()];
/* 334 */     localArrayList.toArray(arrayOfFactoryResource);
/* 335 */     return arrayOfFactoryResource;
/*     */   }
/*     */ 
/*     */   private void parseFactory(Node paramNode, ElementInformation paramElementInformation) {
/* 339 */     ArrayList localArrayList1 = new ArrayList();
/* 340 */     ArrayList localArrayList2 = new ArrayList();
/* 341 */     BlockFactory localBlockFactory = new BlockFactory();
/* 342 */     paramElementInformation.setFactory(localBlockFactory);
/* 343 */     if (paramNode.getTextContent().toLowerCase(Locale.ENGLISH).equals("input"))
/*     */     {
/* 345 */       return;
/*     */     }
/* 347 */     paramElementInformation = paramNode.getChildNodes();
/* 348 */     for (int i = 0; i < paramElementInformation.getLength(); i++)
/*     */     {
/*     */       Object localObject;
/* 350 */       if ((
/* 350 */         localObject = paramElementInformation.item(i))
/* 350 */         .getNodeType() == 1) {
/* 351 */         if (!((Node)localObject).getNodeName().toLowerCase(Locale.ENGLISH).equals("product")) {
/* 352 */           throw new ElementParserException("All child nodes of " + ((Node)localObject).getNodeName() + " have to be \"product\" but is " + ((Node)localObject).getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/*     */         }
/* 354 */         localObject = ((Node)localObject).getChildNodes();
/* 355 */         FactoryResource[] arrayOfFactoryResource1 = null;
/* 356 */         FactoryResource[] arrayOfFactoryResource2 = null;
/* 357 */         for (int j = 0; j < ((NodeList)localObject).getLength(); j++)
/*     */         {
/*     */           Node localNode;
/* 359 */           if ((
/* 359 */             localNode = ((NodeList)localObject).item(j))
/* 359 */             .getNodeType() == 1) {
/* 360 */             if ((!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("output")) && (!localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("input")))
/*     */             {
/* 362 */               throw new ElementParserException("All child nodes of " + paramNode.getNodeName() + " have to be \"output\" or \"input\" but is " + localNode.getNodeName() + " (" + paramNode.getParentNode().getNodeName() + ")");
/*     */             }
/*     */ 
/* 365 */             if (localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("input")) {
/* 366 */               arrayOfFactoryResource1 = parseResource(localNode);
/*     */             }
/* 368 */             if (localNode.getNodeName().toLowerCase(Locale.ENGLISH).equals("output")) {
/* 369 */               arrayOfFactoryResource2 = parseResource(localNode);
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 374 */         if (arrayOfFactoryResource1 == null) {
/* 375 */           throw new ElementParserException("No input defined for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
/*     */         }
/* 377 */         if (arrayOfFactoryResource2 == null) {
/* 378 */           throw new ElementParserException("No output defined for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
/*     */         }
/*     */ 
/* 381 */         localArrayList1.add(arrayOfFactoryResource1);
/* 382 */         localArrayList2.add(arrayOfFactoryResource2);
/*     */       }
/*     */     }
/* 385 */     if (localArrayList1.size() != localArrayList2.size()) {
/* 386 */       throw new ElementParserException("Factory Parsing failed for " + paramNode.getNodeName() + " in (" + paramNode.getParentNode().getNodeName() + ")");
/*     */     }
/*     */ 
/* 389 */     localBlockFactory.input = new FactoryResource[localArrayList1.size()][];
/* 390 */     localBlockFactory.output = new FactoryResource[localArrayList2.size()][];
/*     */ 
/* 392 */     for (i = 0; i < localBlockFactory.input.length; i++) {
/* 393 */       localBlockFactory.input[i] = ((FactoryResource[])localArrayList1.get(i));
/* 394 */       localBlockFactory.output[i] = ((FactoryResource[])localArrayList2.get(i));
/*     */     }
/*     */   }
/*     */ 
/* 398 */   private void parseLevel(Node paramNode, ElementInformation paramElementInformation) { NodeList localNodeList = paramNode.getChildNodes();
/* 399 */     int i = 0;
/* 400 */     int j = 0;
/* 401 */     short s = 0;
/* 402 */     int k = 0;
/* 403 */     for (int m = 0; m < localNodeList.getLength(); m++)
/*     */     {
/*     */       Node localNode;
/* 406 */       if ((
/* 406 */         localNode = localNodeList.item(m))
/* 406 */         .getNodeType() == 1)
/*     */       {
/* 408 */         if (localNode.getNodeName().equals("Id")) {
/* 409 */           if (i != 0)
/* 410 */             throw new ElementParserException("[LEVEL] Multiple IDs for level in " + paramNode.getParentNode().getNodeName());
/*     */           String str;
/* 414 */           if ((
/* 414 */             str = this.properties.getProperty(localNode.getTextContent())) == null)
/*     */           {
/* 415 */             throw new ElementParserException("[LEVEL] The value of " + localNode.getTextContent() + " has not been found in " + paramNode.getParentNode().getNodeName());
/*     */           }
/*     */           try {
/* 418 */             s = (short)Integer.parseInt(str);
/* 419 */             i = 1;
/*     */           } catch (NumberFormatException localNumberFormatException1) {
/* 421 */             throw new ElementParserException("[LEVEL] The property " + str + " has to be an Integer value in " + paramNode.getParentNode().getNodeName());
/*     */           }
/*     */         }
/*     */ 
/* 425 */         if (localNode.getNodeName().equals("Nr")) {
/*     */           try {
/* 427 */             k = Integer.parseInt(localNode.getTextContent());
/* 428 */             j = 1;
/*     */           } catch (NumberFormatException localNumberFormatException2) {
/* 430 */             throw new ElementParserException("[LEVEL] Nr Value '" + localNode.getTextContent() + "' is not an Integer value in " + paramNode.getParentNode().getNodeName());
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 437 */     if (i == 0) {
/* 438 */       throw new ElementParserException("[LEVEL] No level id in " + paramNode.getParentNode().getNodeName());
/*     */     }
/* 440 */     if (j == 0) {
/* 441 */       throw new ElementParserException("[LEVEL] No level nr in " + paramNode.getParentNode().getNodeName());
/*     */     }
/* 443 */     BlockLevel localBlockLevel = new BlockLevel(s, k);
/* 444 */     paramElementInformation.setLevel(localBlockLevel);
/*     */   }
/*     */ 
/*     */   private void parseDescription(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 453 */     paramNode = paramNode.getTextContent()
/* 450 */       .replaceAll("\\r\\n|\\r|\\n", "")
/* 451 */       .replaceAll("\\\\n", "\n")
/* 452 */       .replaceAll("\\\\r", "\r")
/* 453 */       .replaceAll("\\\\t", "\t");
/*     */ 
/* 456 */     paramElementInformation.setDescription(paramNode);
/*     */   }
/*     */ 
/*     */   private void parseEnterable(Node paramNode, ElementInformation paramElementInformation) {
/* 460 */     paramElementInformation.setEnterable(parseBoolean(paramNode));
/*     */   }
/*     */ 
/*     */   private float parseFloat(Node paramNode) {
/*     */     try {
/* 465 */       return Float.parseFloat(paramNode.getTextContent()); } catch (NumberFormatException localNumberFormatException) { localNumberFormatException
/* 466 */         .printStackTrace();
/*     */     }
/* 468 */     throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be a Float value for " + paramNode.getParentNode().getNodeName());
/*     */   }
/*     */ 
/*     */   private void parseFullName(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 474 */     paramNode = paramNode.getTextContent();
/*     */ 
/* 477 */     paramElementInformation.setFullName(paramNode);
/*     */   }
/*     */ 
/*     */   private void parseHitpoints(Node paramNode, ElementInformation paramElementInformation) {
/* 481 */     paramElementInformation.setMaxHitPoints((short)parseInt(paramNode));
/* 482 */     if ((paramElementInformation.getMaxHitPoints() < 0) || (paramElementInformation.getMaxHitPoints() > 1023))
/* 483 */       throw new ElementParserException("Hitpoints for " + paramNode.getParentNode().getNodeName() + " has to be between [1, 1024[");
/*     */   }
/*     */ 
/*     */   private void parseIndividualSides(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 488 */     paramElementInformation.setIndividualSides(parseInt(paramNode));
/* 489 */     if ((paramElementInformation.getIndividualSides() != 1) && (paramElementInformation.getIndividualSides() != 3) && (paramElementInformation.getIndividualSides() != 6))
/*     */     {
/* 491 */       throw new ElementParserException("Individual Sides for " + paramNode.getParentNode().getNodeName() + " has to be either 1 (default), 3, or 6, but was: " + paramElementInformation.getIndividualSides());
/*     */     }
/*     */   }
/*     */ 
/* 495 */   private void parseInfoNode(Node paramNode, ElementInformation paramElementInformation) { NodeList localNodeList = paramNode.getChildNodes();
/*     */ 
/* 497 */     for (int i = 0; i < localNodeList.getLength(); i++)
/*     */     {
/*     */       Node localNode;
/* 499 */       if ((
/* 499 */         localNode = localNodeList.item(i))
/* 499 */         .getNodeType() == 1)
/* 500 */         if (localNode.getNodeName().equals("InShop"))
/* 501 */           parseInShop(localNode, paramElementInformation);
/* 502 */         else if (localNode.getNodeName().equals("Placable"))
/* 503 */           parsePlacable(localNode, paramElementInformation);
/* 504 */         else if (localNode.getNodeName().equals("ControlledBy"))
/* 505 */           parseControlledBy(localNode, paramElementInformation);
/* 506 */         else if (localNode.getNodeName().equals("Controlling"))
/* 507 */           parseControlling(localNode, paramElementInformation);
/* 508 */         else if (localNode.getNodeName().equals("Level"))
/* 509 */           parseLevel(localNode, paramElementInformation);
/* 510 */         else if (localNode.getNodeName().equals("Enterable"))
/* 511 */           parseEnterable(localNode, paramElementInformation);
/* 512 */         else if (localNode.getNodeName().equals("CanActivate"))
/* 513 */           parseCanActivate(localNode, paramElementInformation);
/* 514 */         else if (localNode.getNodeName().equals("LightSource"))
/* 515 */           parseLightSource(localNode, paramElementInformation);
/* 516 */         else if (localNode.getNodeName().equals("Physical"))
/* 517 */           parsePhysical(localNode, paramElementInformation);
/* 518 */         else if (localNode.getNodeName().equals("BlockStyle"))
/* 519 */           parseBlockStyle(localNode, paramElementInformation);
/* 520 */         else if (localNode.getNodeName().equals("LightSourceColor"))
/* 521 */           parseLightSourceColor(localNode, paramElementInformation);
/* 522 */         else if (localNode.getNodeName().equals("Hitpoints"))
/* 523 */           parseHitpoints(localNode, paramElementInformation);
/* 524 */         else if (localNode.getNodeName().equals("Armour"))
/* 525 */           parseArmour(localNode, paramElementInformation);
/* 526 */         else if (localNode.getNodeName().equals("Transparency"))
/* 527 */           parseTransparency(localNode, paramElementInformation);
/* 528 */         else if (localNode.getNodeName().equals("Description"))
/* 529 */           parseDescription(localNode, paramElementInformation);
/* 530 */         else if (localNode.getNodeName().equals("Factory"))
/* 531 */           parseFactory(localNode, paramElementInformation);
/* 532 */         else if (localNode.getNodeName().equals("FullName"))
/* 533 */           parseFullName(localNode, paramElementInformation);
/* 534 */         else if (localNode.getNodeName().equals("Animated"))
/* 535 */           parseAnimated(localNode, paramElementInformation);
/* 536 */         else if (localNode.getNodeName().equals("Price"))
/* 537 */           parsePrice(localNode, paramElementInformation);
/* 538 */         else if (localNode.getNodeName().equals("InShop"))
/* 539 */           parseInShop(localNode, paramElementInformation);
/* 540 */         else if (localNode.getNodeName().equals("Orientation"))
/* 541 */           parseOrientation(localNode, paramElementInformation);
/* 542 */         else if (localNode.getNodeName().equals("IndividualSides"))
/* 543 */           parseIndividualSides(localNode, paramElementInformation);
/*     */         else
/* 545 */           throw new ElementParserException("Element Info " + localNode.getNodeName() + " not found. location: " + paramNode.getNodeName());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parseInShop(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 552 */     paramElementInformation.setShoppable(parseBoolean(paramNode));
/*     */   }
/*     */ 
/*     */   private int parseInt(Node paramNode) {
/*     */     try {
/* 557 */       return Integer.parseInt(paramNode.getTextContent()); } catch (NumberFormatException localNumberFormatException) { localNumberFormatException
/* 558 */         .printStackTrace();
/*     */     }
/* 560 */     throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Integer value for " + paramNode.getParentNode().getNodeName());
/*     */   }
/*     */ 
/*     */   private int parseIntAttribute(Node paramNode)
/*     */   {
/*     */     try {
/* 566 */       return Integer.parseInt(paramNode.getNodeValue()); } catch (NumberFormatException localNumberFormatException) { localNumberFormatException
/* 567 */         .printStackTrace();
/*     */     }
/* 569 */     throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be an Integer value for " + paramNode.getParentNode().getNodeName());
/*     */   }
/*     */ 
/*     */   private void parseLightSource(Node paramNode, ElementInformation paramElementInformation) {
/* 573 */     paramElementInformation.setLightSource(parseBoolean(paramNode));
/*     */   }
/*     */ 
/*     */   private void parseLightSourceColor(Node paramNode, ElementInformation paramElementInformation) {
/* 577 */     paramElementInformation.getLightSourceColor().set(parseVector3f(paramNode));
/*     */   }
/*     */   private void parseOrientation(Node paramNode, ElementInformation paramElementInformation) {
/* 580 */     paramElementInformation.setOrientatable(parseBoolean(paramNode));
/*     */   }
/*     */   private void parsePhysical(Node paramNode, ElementInformation paramElementInformation) {
/* 583 */     paramElementInformation.setPhysical(parseBoolean(paramNode));
/*     */   }
/*     */ 
/*     */   private void parsePlacable(Node paramNode, ElementInformation paramElementInformation) {
/* 587 */     paramElementInformation.setPlacable(parseBoolean(paramNode));
/*     */   }
/*     */   private void parsePrice(Node paramNode, ElementInformation paramElementInformation) {
/* 590 */     paramElementInformation.setPrice(parseInt(paramNode));
/* 591 */     if (paramElementInformation.getPrice() < 0L)
/* 592 */       throw new ElementParserException("Price for " + paramNode.getParentNode().getNodeName() + " has to be greater or equal zero");
/*     */   }
/*     */ 
/*     */   private void parseTransparency(Node paramNode, ElementInformation paramElementInformation)
/*     */   {
/* 597 */     paramElementInformation.setBlended(parseBoolean(paramNode));
/*     */   }
/*     */ 
/*     */   private Node parseType(Node paramNode, NamedNodeMap paramNamedNodeMap, String paramString)
/*     */   {
/* 602 */     if ((
/* 602 */       paramNamedNodeMap = paramNamedNodeMap.getNamedItem(paramString)) == null)
/*     */     {
/* 603 */       throw new ElementParserException("Obligatory attribute \"" + paramString + "\" not found in " + paramNode.getNodeName());
/*     */     }
/* 605 */     return paramNamedNodeMap;
/*     */   }
/*     */ 
/*     */   private Vector3f parseVector3f(Node paramNode) {
/* 609 */     Vector3f localVector3f = new Vector3f();
/*     */     String[] arrayOfString;
/* 613 */     if ((
/* 613 */       arrayOfString = paramNode.getTextContent().split(",")).length != 
/* 613 */       3)
/* 614 */       throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be 3 Float values seperated by commas");
/*     */     try
/*     */     {
/* 617 */       localVector3f.set(Float.parseFloat(arrayOfString[0].trim()), Float.parseFloat(arrayOfString[1].trim()), Float.parseFloat(arrayOfString[2].trim()));
/*     */     } catch (NumberFormatException localNumberFormatException) {
/* 619 */       throw new ElementParserException("The value of " + paramNode.getNodeName() + " has to be a Float value");
/*     */     }
/* 621 */     return localVector3f;
/*     */   }
/*     */ 
/*     */   public void read(File paramFile, String paramString) {
/* 625 */     DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance()
/* 625 */       .newDocumentBuilder();
/* 626 */     this.doc = localDocumentBuilder.parse(paramFile);
/*     */ 
/* 629 */     this.properties = new Properties();
/* 630 */     paramFile = new FileInputStream(paramString);
/* 631 */     this.properties.load(paramFile);
/* 632 */     paramFile.close();
/*     */ 
/* 634 */     ElementKeyMap.properties = this.properties;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementParser
 * JD-Core Version:    0.6.2
 */