package org.apache.commons.lang3.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;

public class TypeUtils
{
  public static boolean isAssignable(Type type, Type toType)
  {
    return isAssignable(type, toType, null);
  }
  
  private static boolean isAssignable(Type type, Type toType, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    if ((toType == null) || ((toType instanceof Class))) {
      return isAssignable(type, (Class)toType);
    }
    if ((toType instanceof ParameterizedType)) {
      return isAssignable(type, (ParameterizedType)toType, typeVarAssigns);
    }
    if ((toType instanceof GenericArrayType)) {
      return isAssignable(type, (GenericArrayType)toType, typeVarAssigns);
    }
    if ((toType instanceof WildcardType)) {
      return isAssignable(type, (WildcardType)toType, typeVarAssigns);
    }
    if ((toType instanceof TypeVariable)) {
      return isAssignable(type, (TypeVariable)toType, typeVarAssigns);
    }
    throw new IllegalStateException("found an unhandled type: " + toType);
  }
  
  private static boolean isAssignable(Type type, Class<?> toClass)
  {
    if (type == null) {
      return (toClass == null) || (!toClass.isPrimitive());
    }
    if (toClass == null) {
      return false;
    }
    if (toClass.equals(type)) {
      return true;
    }
    if ((type instanceof Class)) {
      return ClassUtils.isAssignable((Class)type, toClass);
    }
    if ((type instanceof ParameterizedType)) {
      return isAssignable(getRawType((ParameterizedType)type), toClass);
    }
    if ((type instanceof TypeVariable))
    {
      for (Type bound : ((TypeVariable)type).getBounds()) {
        if (isAssignable(bound, toClass)) {
          return true;
        }
      }
      return false;
    }
    if ((type instanceof GenericArrayType)) {
      return (toClass.equals(Object.class)) || ((toClass.isArray()) && (isAssignable(((GenericArrayType)type).getGenericComponentType(), toClass.getComponentType())));
    }
    if ((type instanceof WildcardType)) {
      return false;
    }
    throw new IllegalStateException("found an unhandled type: " + type);
  }
  
  private static boolean isAssignable(Type type, ParameterizedType toParameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    if (type == null) {
      return true;
    }
    if (toParameterizedType == null) {
      return false;
    }
    if (toParameterizedType.equals(type)) {
      return true;
    }
    Class<?> toClass = getRawType(toParameterizedType);
    Map<TypeVariable<?>, Type> fromTypeVarAssigns = getTypeArguments(type, toClass, null);
    if (fromTypeVarAssigns == null) {
      return false;
    }
    if (fromTypeVarAssigns.isEmpty()) {
      return true;
    }
    Map<TypeVariable<?>, Type> toTypeVarAssigns = getTypeArguments(toParameterizedType, toClass, typeVarAssigns);
    Iterator local_i$ = toTypeVarAssigns.entrySet().iterator();
    while (local_i$.hasNext())
    {
      Map.Entry<TypeVariable<?>, Type> entry = (Map.Entry)local_i$.next();
      Type toTypeArg = (Type)entry.getValue();
      Type fromTypeArg = (Type)fromTypeVarAssigns.get(entry.getKey());
      if ((fromTypeArg != null) && (!toTypeArg.equals(fromTypeArg)) && ((!(toTypeArg instanceof WildcardType)) || (!isAssignable(fromTypeArg, toTypeArg, typeVarAssigns)))) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean isAssignable(Type type, GenericArrayType toGenericArrayType, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    if (type == null) {
      return true;
    }
    if (toGenericArrayType == null) {
      return false;
    }
    if (toGenericArrayType.equals(type)) {
      return true;
    }
    Type toComponentType = toGenericArrayType.getGenericComponentType();
    if ((type instanceof Class))
    {
      Class<?> cls = (Class)type;
      return (cls.isArray()) && (isAssignable(cls.getComponentType(), toComponentType, typeVarAssigns));
    }
    if ((type instanceof GenericArrayType)) {
      return isAssignable(((GenericArrayType)type).getGenericComponentType(), toComponentType, typeVarAssigns);
    }
    if ((type instanceof WildcardType))
    {
      for (Type bound : getImplicitUpperBounds((WildcardType)type)) {
        if (isAssignable(bound, toGenericArrayType)) {
          return true;
        }
      }
      return false;
    }
    if ((type instanceof TypeVariable))
    {
      for (Type bound : getImplicitBounds((TypeVariable)type)) {
        if (isAssignable(bound, toGenericArrayType)) {
          return true;
        }
      }
      return false;
    }
    if ((type instanceof ParameterizedType)) {
      return false;
    }
    throw new IllegalStateException("found an unhandled type: " + type);
  }
  
  private static boolean isAssignable(Type type, WildcardType toWildcardType, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    if (type == null) {
      return true;
    }
    if (toWildcardType == null) {
      return false;
    }
    if (toWildcardType.equals(type)) {
      return true;
    }
    Type[] toUpperBounds = getImplicitUpperBounds(toWildcardType);
    Type[] toLowerBounds = getImplicitLowerBounds(toWildcardType);
    if ((type instanceof WildcardType))
    {
      WildcardType wildcardType = (WildcardType)type;
      Type[] upperBounds = getImplicitUpperBounds(wildcardType);
      Type[] lowerBounds = getImplicitLowerBounds(wildcardType);
      for (Type toBound : toUpperBounds)
      {
        toBound = substituteTypeVariables(toBound, typeVarAssigns);
        for (Type bound : upperBounds) {
          if (!isAssignable(bound, toBound, typeVarAssigns)) {
            return false;
          }
        }
      }
      for (Type toBound : toLowerBounds)
      {
        toBound = substituteTypeVariables(toBound, typeVarAssigns);
        for (Type bound : lowerBounds) {
          if (!isAssignable(toBound, bound, typeVarAssigns)) {
            return false;
          }
        }
      }
      return true;
    }
    for (Type arr$ : toUpperBounds) {
      if (!isAssignable(type, substituteTypeVariables(arr$, typeVarAssigns), typeVarAssigns)) {
        return false;
      }
    }
    for (Type arr$ : toLowerBounds) {
      if (!isAssignable(substituteTypeVariables(arr$, typeVarAssigns), type, typeVarAssigns)) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean isAssignable(Type type, TypeVariable<?> toTypeVariable, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    if (type == null) {
      return true;
    }
    if (toTypeVariable == null) {
      return false;
    }
    if (toTypeVariable.equals(type)) {
      return true;
    }
    if ((type instanceof TypeVariable))
    {
      Type[] bounds = getImplicitBounds((TypeVariable)type);
      for (Type bound : bounds) {
        if (isAssignable(bound, toTypeVariable, typeVarAssigns)) {
          return true;
        }
      }
    }
    if (((type instanceof Class)) || ((type instanceof ParameterizedType)) || ((type instanceof GenericArrayType)) || ((type instanceof WildcardType))) {
      return false;
    }
    throw new IllegalStateException("found an unhandled type: " + type);
  }
  
  private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    if (((type instanceof TypeVariable)) && (typeVarAssigns != null))
    {
      Type replacementType = (Type)typeVarAssigns.get(type);
      if (replacementType == null) {
        throw new IllegalArgumentException("missing assignment type for type variable " + type);
      }
      return replacementType;
    }
    return type;
  }
  
  public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType type)
  {
    return getTypeArguments(type, getRawType(type), null);
  }
  
  public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass)
  {
    return getTypeArguments(type, toClass, null);
  }
  
  private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
  {
    if ((type instanceof Class)) {
      return getTypeArguments((Class)type, toClass, subtypeVarAssigns);
    }
    if ((type instanceof ParameterizedType)) {
      return getTypeArguments((ParameterizedType)type, toClass, subtypeVarAssigns);
    }
    if ((type instanceof GenericArrayType)) {
      return getTypeArguments(((GenericArrayType)type).getGenericComponentType(), toClass.isArray() ? toClass.getComponentType() : toClass, subtypeVarAssigns);
    }
    if ((type instanceof WildcardType))
    {
      for (Type bound : getImplicitUpperBounds((WildcardType)type)) {
        if (isAssignable(bound, toClass)) {
          return getTypeArguments(bound, toClass, subtypeVarAssigns);
        }
      }
      return null;
    }
    if ((type instanceof TypeVariable))
    {
      for (Type bound : getImplicitBounds((TypeVariable)type)) {
        if (isAssignable(bound, toClass)) {
          return getTypeArguments(bound, toClass, subtypeVarAssigns);
        }
      }
      return null;
    }
    throw new IllegalStateException("found an unhandled type: " + type);
  }
  
  private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
  {
    Class<?> cls = getRawType(parameterizedType);
    if (!isAssignable(cls, toClass)) {
      return null;
    }
    Type ownerType = parameterizedType.getOwnerType();
    Map<TypeVariable<?>, Type> typeVarAssigns;
    Map<TypeVariable<?>, Type> typeVarAssigns;
    if ((ownerType instanceof ParameterizedType))
    {
      ParameterizedType parameterizedOwnerType = (ParameterizedType)ownerType;
      typeVarAssigns = getTypeArguments(parameterizedOwnerType, getRawType(parameterizedOwnerType), subtypeVarAssigns);
    }
    else
    {
      typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
    }
    Type[] parameterizedOwnerType = parameterizedType.getActualTypeArguments();
    TypeVariable<?>[] typeParams = cls.getTypeParameters();
    for (int local_i = 0; local_i < typeParams.length; local_i++)
    {
      Type typeArg = parameterizedOwnerType[local_i];
      typeVarAssigns.put(typeParams[local_i], typeVarAssigns.containsKey(typeArg) ? (Type)typeVarAssigns.get(typeArg) : typeArg);
    }
    if (toClass.equals(cls)) {
      return typeVarAssigns;
    }
    return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
  }
  
  private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
  {
    if (!isAssignable(cls, toClass)) {
      return null;
    }
    if (cls.isPrimitive())
    {
      if (toClass.isPrimitive()) {
        return new HashMap();
      }
      cls = ClassUtils.primitiveToWrapper(cls);
    }
    HashMap<TypeVariable<?>, Type> typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
    if ((cls.getTypeParameters().length > 0) || (toClass.equals(cls))) {
      return typeVarAssigns;
    }
    return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
  }
  
  public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType superType)
  {
    Class<?> superClass = getRawType(superType);
    if (!isAssignable(cls, superClass)) {
      return null;
    }
    if (cls.equals(superClass)) {
      return getTypeArguments(superType, superClass, null);
    }
    Type midType = getClosestParentType(cls, superClass);
    if ((midType instanceof Class)) {
      return determineTypeArguments((Class)midType, superType);
    }
    ParameterizedType midParameterizedType = (ParameterizedType)midType;
    Class<?> midClass = getRawType(midParameterizedType);
    Map<TypeVariable<?>, Type> typeVarAssigns = determineTypeArguments(midClass, superType);
    mapTypeVariablesToArguments(cls, midParameterizedType, typeVarAssigns);
    return typeVarAssigns;
  }
  
  private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    Type ownerType = parameterizedType.getOwnerType();
    if ((ownerType instanceof ParameterizedType)) {
      mapTypeVariablesToArguments(cls, (ParameterizedType)ownerType, typeVarAssigns);
    }
    Type[] typeArgs = parameterizedType.getActualTypeArguments();
    TypeVariable<?>[] typeVars = getRawType(parameterizedType).getTypeParameters();
    List<TypeVariable<Class<T>>> typeVarList = Arrays.asList(cls.getTypeParameters());
    for (int local_i = 0; local_i < typeArgs.length; local_i++)
    {
      TypeVariable<?> typeVar = typeVars[local_i];
      Type typeArg = typeArgs[local_i];
      if ((typeVarList.contains(typeArg)) && (typeVarAssigns.containsKey(typeVar))) {
        typeVarAssigns.put((TypeVariable)typeArg, typeVarAssigns.get(typeVar));
      }
    }
  }
  
  private static Type getClosestParentType(Class<?> cls, Class<?> superClass)
  {
    if (superClass.isInterface())
    {
      Type[] interfaceTypes = cls.getGenericInterfaces();
      Type genericInterface = null;
      for (Type midType : interfaceTypes)
      {
        Class<?> midClass = null;
        if ((midType instanceof ParameterizedType)) {
          midClass = getRawType((ParameterizedType)midType);
        } else if ((midType instanceof Class)) {
          midClass = (Class)midType;
        } else {
          throw new IllegalStateException("Unexpected generic interface type found: " + midType);
        }
        if ((isAssignable(midClass, superClass)) && (isAssignable(genericInterface, midClass))) {
          genericInterface = midType;
        }
      }
      if (genericInterface != null) {
        return genericInterface;
      }
    }
    return cls.getGenericSuperclass();
  }
  
  public static boolean isInstance(Object value, Type type)
  {
    if (type == null) {
      return false;
    }
    return value == null ? false : (!(type instanceof Class)) || (!((Class)type).isPrimitive()) ? true : isAssignable(value.getClass(), type, null);
  }
  
  public static Type[] normalizeUpperBounds(Type[] bounds)
  {
    if (bounds.length < 2) {
      return bounds;
    }
    Set<Type> types = new HashSet(bounds.length);
    for (Type type1 : bounds)
    {
      boolean subtypeFound = false;
      for (Type type2 : bounds) {
        if ((type1 != type2) && (isAssignable(type2, type1, null)))
        {
          subtypeFound = true;
          break;
        }
      }
      if (!subtypeFound) {
        types.add(type1);
      }
    }
    return (Type[])types.toArray(new Type[types.size()]);
  }
  
  public static Type[] getImplicitBounds(TypeVariable<?> typeVariable)
  {
    Type[] bounds = typeVariable.getBounds();
    return bounds.length == 0 ? new Type[] { Object.class } : normalizeUpperBounds(bounds);
  }
  
  public static Type[] getImplicitUpperBounds(WildcardType wildcardType)
  {
    Type[] bounds = wildcardType.getUpperBounds();
    return bounds.length == 0 ? new Type[] { Object.class } : normalizeUpperBounds(bounds);
  }
  
  public static Type[] getImplicitLowerBounds(WildcardType wildcardType)
  {
    Type[] bounds = wildcardType.getLowerBounds();
    return bounds.length == 0 ? new Type[] { null } : bounds;
  }
  
  public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> typeVarAssigns)
  {
    Iterator local_i$1 = typeVarAssigns.entrySet().iterator();
    while (local_i$1.hasNext())
    {
      Map.Entry<TypeVariable<?>, Type> entry = (Map.Entry)local_i$1.next();
      TypeVariable<?> typeVar = (TypeVariable)entry.getKey();
      Type type = (Type)entry.getValue();
      for (Type bound : getImplicitBounds(typeVar)) {
        if (!isAssignable(type, substituteTypeVariables(bound, typeVarAssigns), typeVarAssigns)) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static Class<?> getRawType(ParameterizedType parameterizedType)
  {
    Type rawType = parameterizedType.getRawType();
    if (!(rawType instanceof Class)) {
      throw new IllegalStateException("Wait... What!? Type of rawType: " + rawType);
    }
    return (Class)rawType;
  }
  
  public static Class<?> getRawType(Type type, Type assigningType)
  {
    if ((type instanceof Class)) {
      return (Class)type;
    }
    if ((type instanceof ParameterizedType)) {
      return getRawType((ParameterizedType)type);
    }
    if ((type instanceof TypeVariable))
    {
      if (assigningType == null) {
        return null;
      }
      Object genericDeclaration = ((TypeVariable)type).getGenericDeclaration();
      if (!(genericDeclaration instanceof Class)) {
        return null;
      }
      Map<TypeVariable<?>, Type> typeVarAssigns = getTypeArguments(assigningType, (Class)genericDeclaration);
      if (typeVarAssigns == null) {
        return null;
      }
      Type typeArgument = (Type)typeVarAssigns.get(type);
      if (typeArgument == null) {
        return null;
      }
      return getRawType(typeArgument, assigningType);
    }
    if ((type instanceof GenericArrayType))
    {
      Class<?> genericDeclaration = getRawType(((GenericArrayType)type).getGenericComponentType(), assigningType);
      return Array.newInstance(genericDeclaration, 0).getClass();
    }
    if ((type instanceof WildcardType)) {
      return null;
    }
    throw new IllegalArgumentException("unknown type: " + type);
  }
  
  public static boolean isArrayType(Type type)
  {
    return ((type instanceof GenericArrayType)) || (((type instanceof Class)) && (((Class)type).isArray()));
  }
  
  public static Type getArrayComponentType(Type type)
  {
    if ((type instanceof Class))
    {
      Class<?> clazz = (Class)type;
      return clazz.isArray() ? clazz.getComponentType() : null;
    }
    if ((type instanceof GenericArrayType)) {
      return ((GenericArrayType)type).getGenericComponentType();
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.reflect.TypeUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */