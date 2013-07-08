package org.schema.game.common.data.element;

import class_48;
import class_67;
import class_916;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.FastEntrySet;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap.Entry;
import it.unimi.dsi.fastutil.shorts.Short2ObjectMap.FastEntrySet;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.Set;

public class ControlElementMapper
  extends Long2ObjectOpenHashMap
  implements class_67
{
  private static final long serialVersionUID = 8953098951065383692L;
  private final Long2ObjectOpenHashMap all = new Long2ObjectOpenHashMap();
  private final Long2ObjectOpenHashMap controllers = new Long2ObjectOpenHashMap();
  class_916 tmp = new class_916();
  
  public void writeToTag(DataOutputStream paramDataOutputStream)
  {
    ControlElementMap.serialize(paramDataOutputStream, this);
  }
  
  public byte getFactoryId()
  {
    return 0;
  }
  
  public boolean put(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    long l = ElementCollection.getIndex(paramclass_481);
    return put(l, paramclass_482, paramShort);
  }
  
  public boolean put(long paramLong, class_48 paramclass_48, short paramShort)
  {
    assert (paramShort != 0);
    assert (paramShort != 32767);
    Object localObject;
    if (!containsKey(paramLong))
    {
      localObject = new Short2ObjectOpenHashMap();
      put(paramLong, localObject);
    }
    else
    {
      localObject = (Short2ObjectOpenHashMap)get(paramLong);
    }
    ObjectOpenHashSet localObjectOpenHashSet;
    if (!((Short2ObjectOpenHashMap)localObject).containsKey(paramShort))
    {
      localObjectOpenHashSet = new ObjectOpenHashSet();
      ((Short2ObjectOpenHashMap)localObject).put(paramShort, localObjectOpenHashSet);
    }
    else
    {
      localObjectOpenHashSet = (ObjectOpenHashSet)((Short2ObjectOpenHashMap)localObject).get(paramShort);
    }
    paramclass_48 = new class_916(paramclass_48, paramShort);
    if (!getAll().containsKey(paramLong))
    {
      localObject = new ObjectOpenHashSet();
      getAll().put(paramLong, localObject);
    }
    else
    {
      localObject = (ObjectOpenHashSet)getAll().get(paramLong);
    }
    ((ObjectOpenHashSet)localObject).add(paramclass_48);
    if (!ElementKeyMap.getInfo(paramShort).getControlling().isEmpty())
    {
      if (!getControllers().containsKey(paramLong))
      {
        paramShort = new ObjectOpenHashSet();
        getControllers().put(paramLong, paramShort);
      }
      else
      {
        paramShort = (ObjectOpenHashSet)getControllers().get(paramLong);
      }
      paramShort.add(paramclass_48);
    }
    return localObjectOpenHashSet.add(paramclass_48);
  }
  
  public Short2ObjectOpenHashMap remove(class_48 paramclass_48)
  {
    long l = ElementCollection.getIndex(paramclass_48);
    getAll().remove(l);
    getControllers().remove(l);
    return (Short2ObjectOpenHashMap)super.remove(l);
  }
  
  public boolean remove(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    long l = ElementCollection.getIndex(paramclass_481);
    return remove(l, paramclass_482, paramShort);
  }
  
  public boolean remove(long paramLong, class_48 paramclass_48, short paramShort)
  {
    if ((containsKey(paramLong)) && (((Short2ObjectOpenHashMap)get(paramLong)).containsKey(paramShort)))
    {
      this.tmp.a(paramclass_48, paramShort);
      ((ObjectOpenHashSet)getAll().get(paramLong)).remove(this.tmp);
      if (getControllers().containsKey(paramLong)) {
        ((ObjectOpenHashSet)getControllers().get(paramLong)).remove(this.tmp);
      }
      return ((ObjectOpenHashSet)((Short2ObjectOpenHashMap)get(paramLong)).get(paramShort)).remove(paramclass_48);
    }
    return false;
  }
  
  public Long2ObjectOpenHashMap getAll()
  {
    return this.all;
  }
  
  public Long2ObjectOpenHashMap getControllers()
  {
    return this.controllers;
  }
  
  public void set(ControlElementMapper paramControlElementMapper)
  {
    paramControlElementMapper = paramControlElementMapper.long2ObjectEntrySet().iterator();
    Object localObject1;
    Object localObject2;
    Object localObject3;
    while (paramControlElementMapper.hasNext())
    {
      localObject1 = (Long2ObjectMap.Entry)paramControlElementMapper.next();
      localObject2 = new Short2ObjectOpenHashMap();
      put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject2);
      localObject3 = new ObjectOpenHashSet();
      this.all.put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject3);
      localObject1 = ((Short2ObjectOpenHashMap)((Long2ObjectMap.Entry)localObject1).getValue()).short2ObjectEntrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject4 = (Short2ObjectMap.Entry)((Iterator)localObject1).next();
        ObjectOpenHashSet localObjectOpenHashSet = new ObjectOpenHashSet();
        ((Short2ObjectOpenHashMap)localObject2).put(((Short2ObjectMap.Entry)localObject4).getShortKey(), localObjectOpenHashSet);
        localObject4 = ((ObjectOpenHashSet)((Short2ObjectMap.Entry)localObject4).getValue()).iterator();
        while (((Iterator)localObject4).hasNext())
        {
          class_916 localclass_916 = (class_916)((Iterator)localObject4).next();
          localclass_916 = new class_916(localclass_916);
          localObjectOpenHashSet.add(localclass_916);
          ((ObjectOpenHashSet)localObject3).add(localclass_916);
        }
      }
    }
    paramControlElementMapper = this.controllers.long2ObjectEntrySet().iterator();
    while (paramControlElementMapper.hasNext())
    {
      localObject1 = (Long2ObjectMap.Entry)paramControlElementMapper.next();
      localObject2 = new ObjectOpenHashSet();
      this.controllers.put(((Long2ObjectMap.Entry)localObject1).getLongKey(), localObject2);
      localObject3 = ((ObjectOpenHashSet)((Long2ObjectMap.Entry)localObject1).getValue()).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject1 = (class_916)((Iterator)localObject3).next();
        ((ObjectOpenHashSet)localObject2).add(new class_916((class_916)localObject1));
      }
    }
  }
  
  public void clear()
  {
    this.all.clear();
    this.controllers.clear();
    super.clear();
  }
  
  public void clearAndTrim()
  {
    clear();
    this.all.trim();
    this.controllers.trim();
    trim();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMapper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */