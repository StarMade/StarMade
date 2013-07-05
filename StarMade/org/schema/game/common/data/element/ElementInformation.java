/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ public class ElementInformation
/*     */   implements Comparable
/*     */ {
/*     */   public final short id;
/*     */   public final String name;
/*     */   public final Class type;
/*     */   public short textureId;
/*  22 */   public int buildIconNum = 62;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(states={"1", "3", "6"}, tag="IndividualSides", updateTextures=true)
/*  24 */   public int individualSides = 1;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(from=0, to=2147483647, tag="Price")
/*  27 */   public long price = 100L;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Description", textArea=true)
/*  30 */   public String description = "undefined description";
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="FullName")
/*  37 */   public String fullName = "";
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="ControlledBy", collectionElementTag="Element", collectionType="blockTypes")
/*  42 */   public final Set controlledBy = new HashSet();
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Controlling", collectionElementTag="Element", collectionType="blockTypes")
/*  45 */   public final Set controlling = new HashSet();
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Factory", factory=true)
/*     */   public BlockFactory factory;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Animated")
/*     */   public boolean animated;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(from=0, to=2147483647, tag="Armour")
/*  55 */   public int amour = 0;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Transparency")
/*     */   public boolean blended;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="InShop")
/*  61 */   public boolean shoppable = true;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Orientation")
/*     */   public boolean orientatable;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Enterable")
/*     */   public boolean enterable;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="LightSource")
/*     */   public boolean lightSource;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(from=1, to=511, tag="Hitpoints")
/*  73 */   public short maxHitPoints = 100;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Placable")
/*  80 */   public boolean placable = true;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="CanActivate")
/*     */   public boolean canActivate;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Physical")
/*  86 */   public boolean physical = true;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="BlockStyle", states={"0", "1", "2", "3"})
/*     */   public int blockStyle;
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="LightSourceColor", vector3f=true)
/*  92 */   public final Vector3f lightSourceColor = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */ 
/*     */   @org.schema.game.common.data.element.annotation.Element(tag="Level", level=true)
/*     */   public BlockLevel level;
/*     */   private float armourPercent;
/*     */ 
/*     */   public ElementInformation(short paramShort1, String paramString, Class paramClass, short paramShort2)
/*     */   {
/* 105 */     this.name = paramString;
/* 106 */     this.type = paramClass;
/* 107 */     this.textureId = paramShort2;
/* 108 */     this.id = paramShort1;
/*     */   }
/*     */   public boolean canActivate() {
/* 111 */     return this.canActivate;
/*     */   }
/*     */ 
/*     */   public int getAmour()
/*     */   {
/* 117 */     return this.amour;
/*     */   }
/*     */ 
/*     */   public int getBlockStyle()
/*     */   {
/* 123 */     return this.blockStyle;
/*     */   }
/*     */ 
/*     */   public int getBuildIconNum()
/*     */   {
/* 129 */     return this.buildIconNum;
/*     */   }
/*     */   public Set getControlledBy() {
/* 132 */     return this.controlledBy;
/*     */   }
/*     */   public Set getControlling() {
/* 135 */     return this.controlling;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 141 */     return this.description;
/*     */   }
/*     */ 
/*     */   public String getFullName()
/*     */   {
/* 147 */     if (this.fullName == null) {
/* 148 */       return getName();
/*     */     }
/* 150 */     return this.fullName;
/*     */   }
/*     */ 
/*     */   public short getId()
/*     */   {
/* 156 */     return this.id;
/*     */   }
/*     */ 
/*     */   public Vector3f getLightSourceColor()
/*     */   {
/* 162 */     return this.lightSourceColor;
/*     */   }
/*     */ 
/*     */   public short getMaxHitPoints()
/*     */   {
/* 168 */     return this.maxHitPoints;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 174 */     return this.name;
/*     */   }
/*     */ 
/*     */   public long getPrice()
/*     */   {
/* 180 */     return this.price;
/*     */   }
/*     */ 
/*     */   public short getTextureId()
/*     */   {
/* 186 */     return this.textureId;
/*     */   }
/*     */   public Class getType() {
/* 189 */     return this.type;
/*     */   }
/*     */ 
/*     */   public boolean isAnimated()
/*     */   {
/* 195 */     return this.animated;
/*     */   }
/*     */ 
/*     */   public boolean isBlended()
/*     */   {
/* 201 */     return this.blended;
/*     */   }
/*     */   public boolean isController() {
/* 204 */     return !getControlling().isEmpty();
/*     */   }
/*     */ 
/*     */   public boolean isEnterable()
/*     */   {
/* 210 */     return this.enterable;
/*     */   }
/*     */   public int getIndividualSides() {
/* 213 */     return this.individualSides;
/*     */   }
/*     */   public boolean isLightSource() {
/* 216 */     return this.lightSource;
/*     */   }
/*     */   public boolean isOrientatable() {
/* 219 */     return this.orientatable;
/*     */   }
/*     */ 
/*     */   public boolean isPhysical()
/*     */   {
/* 226 */     return this.physical;
/*     */   }
/*     */ 
/*     */   public boolean isPhysical(boolean paramBoolean)
/*     */   {
/* 232 */     if (this.id == 122) {
/* 233 */       return paramBoolean;
/*     */     }
/* 235 */     return this.physical;
/*     */   }
/*     */   public boolean isPlacable() {
/* 238 */     return this.placable;
/*     */   }
/*     */ 
/*     */   public boolean isShoppable()
/*     */   {
/* 244 */     return this.shoppable;
/*     */   }
/*     */ 
/*     */   public void setAmour(int paramInt)
/*     */   {
/* 250 */     this.amour = paramInt;
/* 251 */     this.armourPercent = (paramInt / 100.0F);
/*     */   }
/*     */ 
/*     */   public void setAnimated(boolean paramBoolean)
/*     */   {
/* 257 */     this.animated = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setBlended(boolean paramBoolean)
/*     */   {
/* 264 */     this.blended = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setBlockStyle(int paramInt)
/*     */   {
/* 270 */     this.blockStyle = paramInt;
/*     */   }
/*     */ 
/*     */   public void setBuildIconNum(int paramInt)
/*     */   {
/* 276 */     this.buildIconNum = paramInt;
/*     */   }
/*     */ 
/*     */   public void setCanActivate(boolean paramBoolean)
/*     */   {
/* 282 */     this.canActivate = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setDescription(String paramString)
/*     */   {
/* 289 */     this.description = paramString;
/*     */   }
/*     */ 
/*     */   public void setEnterable(boolean paramBoolean)
/*     */   {
/* 295 */     this.enterable = paramBoolean;
/*     */   }
/*     */   public void setFullName(String paramString) {
/* 298 */     this.fullName = paramString;
/*     */   }
/*     */   public void setIndividualSides(int paramInt) {
/* 301 */     this.individualSides = paramInt;
/*     */   }
/*     */   public void setLightSource(boolean paramBoolean) {
/* 304 */     this.lightSource = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setMaxHitPoints(short paramShort)
/*     */   {
/* 310 */     this.maxHitPoints = paramShort;
/*     */   }
/*     */   public void setOrientatable(boolean paramBoolean) {
/* 313 */     this.orientatable = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setPhysical(boolean paramBoolean)
/*     */   {
/* 319 */     this.physical = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setPlacable(boolean paramBoolean)
/*     */   {
/* 325 */     this.placable = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setPrice(long paramLong)
/*     */   {
/* 331 */     this.price = paramLong;
/*     */   }
/*     */ 
/*     */   public void setShoppable(boolean paramBoolean)
/*     */   {
/* 337 */     this.shoppable = paramBoolean;
/*     */   }
/*     */ 
/*     */   public BlockLevel getLevel()
/*     */   {
/* 343 */     return this.level;
/*     */   }
/*     */ 
/*     */   public void setLevel(BlockLevel paramBlockLevel)
/*     */   {
/* 349 */     this.level = paramBlockLevel;
/*     */   }
/*     */ 
/*     */   public boolean isLeveled() {
/* 353 */     return this.level != null;
/*     */   }
/*     */ 
/*     */   public void setFactory(BlockFactory paramBlockFactory) {
/* 357 */     this.factory = paramBlockFactory;
/*     */   }
/*     */   public BlockFactory getFactory() {
/* 360 */     return this.factory;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 364 */     return getName() + "(" + getId() + ")";
/*     */   }
/*     */ 
/*     */   public int compareTo(ElementInformation paramElementInformation) {
/* 368 */     return this.name.compareTo(paramElementInformation.name);
/*     */   }
/*     */   public static String getKeyId(short paramShort) {
/* 371 */     Object localObject = ElementKeyMap.properties.entrySet();
/* 372 */     String str = null;
/* 373 */     for (localObject = ((Set)localObject).iterator(); ((Iterator)localObject).hasNext(); )
/*     */     {
/*     */       Map.Entry localEntry;
/* 374 */       if ((
/* 374 */         localEntry = (Map.Entry)((Iterator)localObject).next())
/* 374 */         .getValue().equals(String.valueOf(paramShort))) {
/* 375 */         str = localEntry.getKey().toString();
/* 376 */         break;
/*     */       }
/*     */     }
/* 379 */     return str;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 388 */     return ((ElementInformation)paramObject).getId() == getId();
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 395 */     return getId();
/*     */   }
/*     */   public void appendXML(Document paramDocument, org.w3c.dom.Element paramElement) {
/* 398 */     Object localObject1 = getName().replaceAll("[^a-zA-Z]+", "");
/*     */ 
/* 400 */     localObject1 = paramDocument.createElement((String)localObject1);
/*     */     Object localObject2;
/* 407 */     if ((
/* 407 */       localObject2 = getKeyId(getId())) == null)
/*     */     {
/* 408 */       throw new CannotAppendXMLException("Cannot find property key for Block ID " + getId() + "; Check your Block properties file");
/*     */     }
/* 410 */     ((org.w3c.dom.Element)localObject1).setAttribute("type", (String)localObject2);
/* 411 */     ((org.w3c.dom.Element)localObject1).setAttribute("icon", String.valueOf(getBuildIconNum()));
/* 412 */     ((org.w3c.dom.Element)localObject1).setAttribute("textureId", String.valueOf(getTextureId()));
/* 413 */     ((org.w3c.dom.Element)localObject1).setAttribute("name", this.name);
/*     */ 
/* 416 */     for (Object localObject3 : ElementInformation.class.getFields()) {
/*     */       Object localObject4;
/*     */       try {
/* 418 */         if (localObject3.get(this) == null)
/* 419 */           continue;
/*     */       } catch (IllegalArgumentException localIllegalArgumentException) {
/* 421 */         (
/* 422 */           localObject4 = 
/* 427 */           localIllegalArgumentException).printStackTrace();
/* 423 */         throw new CannotAppendXMLException(((IllegalArgumentException)localObject4).getMessage()); } catch (IllegalAccessException localIllegalAccessException) {
/* 424 */         (
/* 425 */           localObject4 = localIllegalAccessException)
/* 425 */           .printStackTrace();
/* 426 */         throw new CannotAppendXMLException(((IllegalAccessException)localObject4).getMessage());
/*     */       }
/*     */ 
/* 431 */       if ((
/* 431 */         localObject4 = (org.schema.game.common.data.element.annotation.Element)localObject3.getAnnotation(org.schema.game.common.data.element.annotation.Element.class)) != null)
/*     */       {
/* 433 */         org.w3c.dom.Element localElement1 = paramDocument.createElement(((org.schema.game.common.data.element.annotation.Element)localObject4).tag());
/*     */         try
/*     */         {
/*     */           Object localObject6;
/*     */           Object localObject7;
/*     */           org.w3c.dom.Element localElement2;
/* 435 */           if (((org.schema.game.common.data.element.annotation.Element)localObject4).factory()) {
/* 436 */             if (getFactory().input == null) {
/* 437 */               localElement1.setTextContent("INPUT");
/*     */             }
/*     */             else
/*     */             {
/* 450 */               for (int k = 0; k < getFactory().input.length; k++) {
/* 451 */                 localObject6 = paramDocument.createElement("Product");
/*     */ 
/* 453 */                 localObject7 = paramDocument.createElement("Input");
/* 454 */                 localElement2 = paramDocument.createElement("Output");
/*     */                 org.w3c.dom.Element localElement3;
/* 456 */                 for (int m = 0; m < getFactory().input[k].length; m++) {
/* 457 */                   localObject4 = getFactory().input[k][m];
/* 458 */                   (
/* 459 */                     localElement3 = paramDocument.createElement("Item"))
/* 459 */                     .setAttribute("count", String.valueOf(((FactoryResource)localObject4).count));
/*     */ 
/* 462 */                   if ((
/* 462 */                     localObject4 = getKeyId(((FactoryResource)localObject4).type)) == null)
/*     */                   {
/* 463 */                     throw new CannotAppendXMLException("[Factory][Input] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
/*     */                   }
/*     */ 
/* 466 */                   localElement3.setTextContent((String)localObject4);
/*     */ 
/* 468 */                   ((org.w3c.dom.Element)localObject7).appendChild(localElement3);
/*     */                 }
/*     */ 
/* 471 */                 for (m = 0; m < getFactory().output[k].length; m++) {
/* 472 */                   localObject4 = getFactory().output[k][m];
/* 473 */                   (
/* 474 */                     localElement3 = paramDocument.createElement("Item"))
/* 474 */                     .setAttribute("count", String.valueOf(((FactoryResource)localObject4).count));
/*     */ 
/* 477 */                   if ((
/* 477 */                     localObject4 = getKeyId(((FactoryResource)localObject4).type)) == null)
/*     */                   {
/* 478 */                     throw new CannotAppendXMLException("[Factory][Output] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
/*     */                   }
/*     */ 
/* 481 */                   localElement3.setTextContent((String)localObject4);
/*     */ 
/* 483 */                   localElement2.appendChild(localElement3);
/*     */                 }
/*     */ 
/* 486 */                 ((org.w3c.dom.Element)localObject6).appendChild((Node)localObject7);
/* 487 */                 ((org.w3c.dom.Element)localObject6).appendChild(localElement2);
/* 488 */                 localElement1.appendChild((Node)localObject6);
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/*     */           }
/* 496 */           else if (((org.schema.game.common.data.element.annotation.Element)localObject4).level())
/*     */           {
/* 498 */             localObject5 = paramDocument.createElement("Id");
/* 499 */             localObject6 = paramDocument.createElement("Nr");
/*     */ 
/* 501 */             if ((
/* 501 */               localObject7 = getKeyId(getLevel().getIdBase())) == null)
/*     */             {
/* 502 */               throw new CannotAppendXMLException("[Level] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
/*     */             }
/* 504 */             ((org.w3c.dom.Element)localObject5).setTextContent((String)localObject7);
/* 505 */             ((org.w3c.dom.Element)localObject6).setTextContent(String.valueOf(getLevel().getLevel()));
/*     */ 
/* 507 */             localElement1.appendChild((Node)localObject5);
/* 508 */             localElement1.appendChild((Node)localObject6);
/* 509 */           } else if (((org.schema.game.common.data.element.annotation.Element)localObject4).vector3f()) {
/* 510 */             localObject5 = (Vector3f)localObject3.get(this);
/*     */ 
/* 512 */             localElement1.setTextContent(((Vector3f)localObject5).x + "," + ((Vector3f)localObject5).y + "," + ((Vector3f)localObject5).z);
/*     */           }
/* 514 */           else if (((org.schema.game.common.data.element.annotation.Element)localObject4).collectionType().equals("blockTypes"))
/*     */           {
/* 517 */             if ((
/* 517 */               localObject5 = (Set)localObject3.get(this))
/* 517 */               .isEmpty()) {
/*     */               continue;
/*     */             }
/* 520 */             for (localObject6 = ((Set)localObject5).iterator(); ((Iterator)localObject6).hasNext(); ) { localObject7 = (Short)((Iterator)localObject6).next();
/* 521 */               if ((!ElementKeyMap.getFactorykeyset().contains(Short.valueOf(getId()))) || (!ElementKeyMap.getFactorykeyset().contains(localObject7))) {
/* 522 */                 localElement2 = paramDocument.createElement(((org.schema.game.common.data.element.annotation.Element)localObject4).collectionElementTag());
/*     */                 String str;
/* 527 */                 if ((
/* 527 */                   str = getKeyId(((Short)localObject7).shortValue())) == null)
/*     */                 {
/* 528 */                   throw new CannotAppendXMLException("[BlockSet] " + localObject3.getName() + " Cannot find property key for Block ID " + localObject7 + "; Check your Block properties file");
/*     */                 }
/* 530 */                 localElement2.setTextContent(str);
/* 531 */                 localElement1.appendChild(localElement2);
/*     */               } }
/*     */           }
/*     */           else {
/* 535 */             localObject5 = localObject3.get(this).toString();
/*     */ 
/* 537 */             if (((org.schema.game.common.data.element.annotation.Element)localObject4).textArea()) {
/* 538 */               localObject5 = ((String)localObject5).replace("\n", "\\n\\r");
/*     */             }
/* 540 */             if (((String)localObject5).length() == 0) {
/*     */               continue;
/*     */             }
/* 543 */             localElement1.setTextContent((String)localObject5);
/*     */           }
/*     */         }
/*     */         catch (Exception localException)
/*     */         {
/*     */           Object localObject5;
/* 546 */           (
/* 547 */             localObject5 = 
/* 549 */             localException).printStackTrace();
/* 548 */           throw new CannotAppendXMLException(((Exception)localObject5).getMessage());
/*     */         }
/*     */ 
/* 551 */         ((org.w3c.dom.Element)localObject1).appendChild(localElement1);
/*     */       }
/*     */     }
/*     */ 
/* 555 */     paramElement.appendChild((Node)localObject1);
/*     */   }
/*     */ 
/*     */   public boolean isDockable() {
/* 559 */     return (getId() == 7) || (getId() == 289);
/*     */   }
/*     */ 
/*     */   public float getArmourPercent()
/*     */   {
/* 565 */     return this.armourPercent;
/*     */   }
/*     */   public static byte defaultActive(short paramShort) {
/* 568 */     return (byte)((paramShort == 16) || (paramShort == 32) || (paramShort == 48) || (paramShort == 40) || (paramShort == 30) || (paramShort == 24) ? 0 : 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementInformation
 * JD-Core Version:    0.6.2
 */