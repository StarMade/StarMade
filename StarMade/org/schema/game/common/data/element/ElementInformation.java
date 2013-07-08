package org.schema.game.common.data.element;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ElementInformation
  implements Comparable
{
  public final short field_1931;
  public final String name;
  public final Class type;
  public short textureId;
  public int buildIconNum = 62;
  @org.schema.game.common.data.element.annotation.Element(states={"1", "3", "6"}, tag="IndividualSides", updateTextures=true)
  public int individualSides = 1;
  @org.schema.game.common.data.element.annotation.Element(from=0, to=2147483647, tag="Price")
  public long price = 100L;
  @org.schema.game.common.data.element.annotation.Element(tag="Description", textArea=true)
  public String description = "undefined description";
  @org.schema.game.common.data.element.annotation.Element(tag="FullName")
  public String fullName = "";
  @org.schema.game.common.data.element.annotation.Element(tag="ControlledBy", collectionElementTag="Element", collectionType="blockTypes")
  public final Set controlledBy = new HashSet();
  @org.schema.game.common.data.element.annotation.Element(tag="Controlling", collectionElementTag="Element", collectionType="blockTypes")
  public final Set controlling = new HashSet();
  @org.schema.game.common.data.element.annotation.Element(tag="Factory", factory=true)
  public BlockFactory factory;
  @org.schema.game.common.data.element.annotation.Element(tag="Animated")
  public boolean animated;
  @org.schema.game.common.data.element.annotation.Element(from=0, to=2147483647, tag="Armour")
  public int amour = 0;
  @org.schema.game.common.data.element.annotation.Element(tag="Transparency")
  public boolean blended;
  @org.schema.game.common.data.element.annotation.Element(tag="InShop")
  public boolean shoppable = true;
  @org.schema.game.common.data.element.annotation.Element(tag="Orientation")
  public boolean orientatable;
  @org.schema.game.common.data.element.annotation.Element(tag="Enterable")
  public boolean enterable;
  @org.schema.game.common.data.element.annotation.Element(tag="LightSource")
  public boolean lightSource;
  @org.schema.game.common.data.element.annotation.Element(from=1, to=511, tag="Hitpoints")
  public short maxHitPoints = 100;
  @org.schema.game.common.data.element.annotation.Element(tag="Placable")
  public boolean placable = true;
  @org.schema.game.common.data.element.annotation.Element(tag="CanActivate")
  public boolean canActivate;
  @org.schema.game.common.data.element.annotation.Element(tag="Physical")
  public boolean physical = true;
  @org.schema.game.common.data.element.annotation.Element(tag="BlockStyle", states={"0", "1", "2", "3"})
  public int blockStyle;
  @org.schema.game.common.data.element.annotation.Element(tag="LightSourceColor", vector3f=true)
  public final Vector3f lightSourceColor = new Vector3f(1.0F, 1.0F, 1.0F);
  @org.schema.game.common.data.element.annotation.Element(tag="Level", level=true)
  public BlockLevel level;
  private float armourPercent;
  
  public ElementInformation(short paramShort1, String paramString, Class paramClass, short paramShort2)
  {
    this.name = paramString;
    this.type = paramClass;
    this.textureId = paramShort2;
    this.field_1931 = paramShort1;
  }
  
  public boolean canActivate()
  {
    return this.canActivate;
  }
  
  public int getAmour()
  {
    return this.amour;
  }
  
  public int getBlockStyle()
  {
    return this.blockStyle;
  }
  
  public int getBuildIconNum()
  {
    return this.buildIconNum;
  }
  
  public Set getControlledBy()
  {
    return this.controlledBy;
  }
  
  public Set getControlling()
  {
    return this.controlling;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getFullName()
  {
    if (this.fullName == null) {
      return getName();
    }
    return this.fullName;
  }
  
  public short getId()
  {
    return this.field_1931;
  }
  
  public Vector3f getLightSourceColor()
  {
    return this.lightSourceColor;
  }
  
  public short getMaxHitPoints()
  {
    return this.maxHitPoints;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public long getPrice()
  {
    return this.price;
  }
  
  public short getTextureId()
  {
    return this.textureId;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public boolean isAnimated()
  {
    return this.animated;
  }
  
  public boolean isBlended()
  {
    return this.blended;
  }
  
  public boolean isController()
  {
    return !getControlling().isEmpty();
  }
  
  public boolean isEnterable()
  {
    return this.enterable;
  }
  
  public int getIndividualSides()
  {
    return this.individualSides;
  }
  
  public boolean isLightSource()
  {
    return this.lightSource;
  }
  
  public boolean isOrientatable()
  {
    return this.orientatable;
  }
  
  public boolean isPhysical()
  {
    return this.physical;
  }
  
  public boolean isPhysical(boolean paramBoolean)
  {
    if (this.field_1931 == 122) {
      return paramBoolean;
    }
    return this.physical;
  }
  
  public boolean isPlacable()
  {
    return this.placable;
  }
  
  public boolean isShoppable()
  {
    return this.shoppable;
  }
  
  public void setAmour(int paramInt)
  {
    this.amour = paramInt;
    this.armourPercent = (paramInt / 100.0F);
  }
  
  public void setAnimated(boolean paramBoolean)
  {
    this.animated = paramBoolean;
  }
  
  public void setBlended(boolean paramBoolean)
  {
    this.blended = paramBoolean;
  }
  
  public void setBlockStyle(int paramInt)
  {
    this.blockStyle = paramInt;
  }
  
  public void setBuildIconNum(int paramInt)
  {
    this.buildIconNum = paramInt;
  }
  
  public void setCanActivate(boolean paramBoolean)
  {
    this.canActivate = paramBoolean;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setEnterable(boolean paramBoolean)
  {
    this.enterable = paramBoolean;
  }
  
  public void setFullName(String paramString)
  {
    this.fullName = paramString;
  }
  
  public void setIndividualSides(int paramInt)
  {
    this.individualSides = paramInt;
  }
  
  public void setLightSource(boolean paramBoolean)
  {
    this.lightSource = paramBoolean;
  }
  
  public void setMaxHitPoints(short paramShort)
  {
    this.maxHitPoints = paramShort;
  }
  
  public void setOrientatable(boolean paramBoolean)
  {
    this.orientatable = paramBoolean;
  }
  
  public void setPhysical(boolean paramBoolean)
  {
    this.physical = paramBoolean;
  }
  
  public void setPlacable(boolean paramBoolean)
  {
    this.placable = paramBoolean;
  }
  
  public void setPrice(long paramLong)
  {
    this.price = paramLong;
  }
  
  public void setShoppable(boolean paramBoolean)
  {
    this.shoppable = paramBoolean;
  }
  
  public BlockLevel getLevel()
  {
    return this.level;
  }
  
  public void setLevel(BlockLevel paramBlockLevel)
  {
    this.level = paramBlockLevel;
  }
  
  public boolean isLeveled()
  {
    return this.level != null;
  }
  
  public void setFactory(BlockFactory paramBlockFactory)
  {
    this.factory = paramBlockFactory;
  }
  
  public BlockFactory getFactory()
  {
    return this.factory;
  }
  
  public String toString()
  {
    return getName() + "(" + getId() + ")";
  }
  
  public int compareTo(ElementInformation paramElementInformation)
  {
    return this.name.compareTo(paramElementInformation.name);
  }
  
  public static String getKeyId(short paramShort)
  {
    Object localObject = ElementKeyMap.properties.entrySet();
    String str = null;
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry;
      if ((localEntry = (Map.Entry)((Iterator)localObject).next()).getValue().equals(String.valueOf(paramShort)))
      {
        str = localEntry.getKey().toString();
        break;
      }
    }
    return str;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((ElementInformation)paramObject).getId() == getId();
  }
  
  public int hashCode()
  {
    return getId();
  }
  
  public void appendXML(Document paramDocument, org.w3c.dom.Element paramElement)
  {
    Object localObject1 = getName().replaceAll("[^a-zA-Z]+", "");
    localObject1 = paramDocument.createElement((String)localObject1);
    Object localObject2;
    if ((localObject2 = getKeyId(getId())) == null) {
      throw new CannotAppendXMLException("Cannot find property key for Block ID " + getId() + "; Check your Block properties file");
    }
    ((org.w3c.dom.Element)localObject1).setAttribute("type", (String)localObject2);
    ((org.w3c.dom.Element)localObject1).setAttribute("icon", String.valueOf(getBuildIconNum()));
    ((org.w3c.dom.Element)localObject1).setAttribute("textureId", String.valueOf(getTextureId()));
    ((org.w3c.dom.Element)localObject1).setAttribute("name", this.name);
    for (Object localObject3 : ElementInformation.class.getFields())
    {
      Object localObject4;
      try
      {
        if (localObject3.get(this) == null) {
          continue;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        (localObject4 = localIllegalArgumentException).printStackTrace();
        throw new CannotAppendXMLException(((IllegalArgumentException)localObject4).getMessage());
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        (localObject4 = localIllegalAccessException).printStackTrace();
        throw new CannotAppendXMLException(((IllegalAccessException)localObject4).getMessage());
      }
      if ((localObject4 = (org.schema.game.common.data.element.annotation.Element)localObject3.getAnnotation(org.schema.game.common.data.element.annotation.Element.class)) != null)
      {
        org.w3c.dom.Element localElement1 = paramDocument.createElement(((org.schema.game.common.data.element.annotation.Element)localObject4).tag());
        try
        {
          Object localObject6;
          Object localObject7;
          org.w3c.dom.Element localElement2;
          if (((org.schema.game.common.data.element.annotation.Element)localObject4).factory())
          {
            if (getFactory().input == null) {
              localElement1.setTextContent("INPUT");
            } else {
              for (int k = 0; k < getFactory().input.length; k++)
              {
                localObject6 = paramDocument.createElement("Product");
                localObject7 = paramDocument.createElement("Input");
                localElement2 = paramDocument.createElement("Output");
                org.w3c.dom.Element localElement3;
                for (int m = 0; m < getFactory().input[k].length; m++)
                {
                  localObject4 = getFactory().input[k][m];
                  (localElement3 = paramDocument.createElement("Item")).setAttribute("count", String.valueOf(((FactoryResource)localObject4).count));
                  if ((localObject4 = getKeyId(((FactoryResource)localObject4).type)) == null) {
                    throw new CannotAppendXMLException("[Factory][Input] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
                  }
                  localElement3.setTextContent((String)localObject4);
                  ((org.w3c.dom.Element)localObject7).appendChild(localElement3);
                }
                for (m = 0; m < getFactory().output[k].length; m++)
                {
                  localObject4 = getFactory().output[k][m];
                  (localElement3 = paramDocument.createElement("Item")).setAttribute("count", String.valueOf(((FactoryResource)localObject4).count));
                  if ((localObject4 = getKeyId(((FactoryResource)localObject4).type)) == null) {
                    throw new CannotAppendXMLException("[Factory][Output] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
                  }
                  localElement3.setTextContent((String)localObject4);
                  localElement2.appendChild(localElement3);
                }
                ((org.w3c.dom.Element)localObject6).appendChild((Node)localObject7);
                ((org.w3c.dom.Element)localObject6).appendChild(localElement2);
                localElement1.appendChild((Node)localObject6);
              }
            }
          }
          else if (((org.schema.game.common.data.element.annotation.Element)localObject4).level())
          {
            localObject5 = paramDocument.createElement("Id");
            localObject6 = paramDocument.createElement("Nr");
            if ((localObject7 = getKeyId(getLevel().getIdBase())) == null) {
              throw new CannotAppendXMLException("[Level] " + localObject3.getName() + " Cannot find property key for Block ID " + getLevel().getIdBase() + "; Check your Block properties file");
            }
            ((org.w3c.dom.Element)localObject5).setTextContent((String)localObject7);
            ((org.w3c.dom.Element)localObject6).setTextContent(String.valueOf(getLevel().getLevel()));
            localElement1.appendChild((Node)localObject5);
            localElement1.appendChild((Node)localObject6);
          }
          else if (((org.schema.game.common.data.element.annotation.Element)localObject4).vector3f())
          {
            localObject5 = (Vector3f)localObject3.get(this);
            localElement1.setTextContent(((Vector3f)localObject5).field_615 + "," + ((Vector3f)localObject5).field_616 + "," + ((Vector3f)localObject5).field_617);
          }
          else if (((org.schema.game.common.data.element.annotation.Element)localObject4).collectionType().equals("blockTypes"))
          {
            if ((localObject5 = (Set)localObject3.get(this)).isEmpty()) {
              continue;
            }
            localObject6 = ((Set)localObject5).iterator();
            while (((Iterator)localObject6).hasNext())
            {
              localObject7 = (Short)((Iterator)localObject6).next();
              if ((!ElementKeyMap.getFactorykeyset().contains(Short.valueOf(getId()))) || (!ElementKeyMap.getFactorykeyset().contains(localObject7)))
              {
                localElement2 = paramDocument.createElement(((org.schema.game.common.data.element.annotation.Element)localObject4).collectionElementTag());
                String str;
                if ((str = getKeyId(((Short)localObject7).shortValue())) == null) {
                  throw new CannotAppendXMLException("[BlockSet] " + localObject3.getName() + " Cannot find property key for Block ID " + localObject7 + "; Check your Block properties file");
                }
                localElement2.setTextContent(str);
                localElement1.appendChild(localElement2);
              }
            }
          }
          else
          {
            localObject5 = localObject3.get(this).toString();
            if (((org.schema.game.common.data.element.annotation.Element)localObject4).textArea()) {
              localObject5 = ((String)localObject5).replace("\n", "\\n\\r");
            }
            if (((String)localObject5).length() == 0) {
              continue;
            }
            localElement1.setTextContent((String)localObject5);
          }
        }
        catch (Exception localException)
        {
          Object localObject5;
          (localObject5 = localException).printStackTrace();
          throw new CannotAppendXMLException(((Exception)localObject5).getMessage());
        }
        ((org.w3c.dom.Element)localObject1).appendChild(localElement1);
      }
    }
    paramElement.appendChild((Node)localObject1);
  }
  
  public boolean isDockable()
  {
    return (getId() == 7) || (getId() == 289);
  }
  
  public float getArmourPercent()
  {
    return this.armourPercent;
  }
  
  public static byte defaultActive(short paramShort)
  {
    return (byte)((paramShort == 16) || (paramShort == 32) || (paramShort == 48) || (paramShort == 40) || (paramShort == 30) || (paramShort == 24) ? 0 : 1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementInformation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */