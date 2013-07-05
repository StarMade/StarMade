/*      */ package org.apache.commons.lang3.reflect;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.GenericArrayType;
/*      */ import java.lang.reflect.ParameterizedType;
/*      */ import java.lang.reflect.Type;
/*      */ import java.lang.reflect.TypeVariable;
/*      */ import java.lang.reflect.WildcardType;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.lang3.ClassUtils;
/*      */ 
/*      */ public class TypeUtils
/*      */ {
/*      */   public static boolean isAssignable(Type type, Type toType)
/*      */   {
/*   65 */     return isAssignable(type, toType, null);
/*      */   }
/*      */ 
/*      */   private static boolean isAssignable(Type type, Type toType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*   79 */     if ((toType == null) || ((toType instanceof Class))) {
/*   80 */       return isAssignable(type, (Class)toType);
/*      */     }
/*      */ 
/*   83 */     if ((toType instanceof ParameterizedType)) {
/*   84 */       return isAssignable(type, (ParameterizedType)toType, typeVarAssigns);
/*      */     }
/*      */ 
/*   87 */     if ((toType instanceof GenericArrayType)) {
/*   88 */       return isAssignable(type, (GenericArrayType)toType, typeVarAssigns);
/*      */     }
/*      */ 
/*   91 */     if ((toType instanceof WildcardType)) {
/*   92 */       return isAssignable(type, (WildcardType)toType, typeVarAssigns);
/*      */     }
/*      */ 
/*   96 */     if ((toType instanceof TypeVariable)) {
/*   97 */       return isAssignable(type, (TypeVariable)toType, typeVarAssigns);
/*      */     }
/*      */ 
/*  101 */     throw new IllegalStateException("found an unhandled type: " + toType);
/*      */   }
/*      */ 
/*      */   private static boolean isAssignable(Type type, Class<?> toClass)
/*      */   {
/*  113 */     if (type == null)
/*      */     {
/*  115 */       return (toClass == null) || (!toClass.isPrimitive());
/*      */     }
/*      */ 
/*  120 */     if (toClass == null) {
/*  121 */       return false;
/*      */     }
/*      */ 
/*  125 */     if (toClass.equals(type)) {
/*  126 */       return true;
/*      */     }
/*      */ 
/*  129 */     if ((type instanceof Class))
/*      */     {
/*  131 */       return ClassUtils.isAssignable((Class)type, toClass);
/*      */     }
/*      */ 
/*  134 */     if ((type instanceof ParameterizedType))
/*      */     {
/*  136 */       return isAssignable(getRawType((ParameterizedType)type), toClass);
/*      */     }
/*      */ 
/*  140 */     if ((type instanceof TypeVariable))
/*      */     {
/*  143 */       for (Type bound : ((TypeVariable)type).getBounds()) {
/*  144 */         if (isAssignable(bound, toClass)) {
/*  145 */           return true;
/*      */         }
/*      */       }
/*      */ 
/*  149 */       return false;
/*      */     }
/*      */ 
/*  154 */     if ((type instanceof GenericArrayType)) {
/*  155 */       return (toClass.equals(Object.class)) || ((toClass.isArray()) && (isAssignable(((GenericArrayType)type).getGenericComponentType(), toClass.getComponentType())));
/*      */     }
/*      */ 
/*  163 */     if ((type instanceof WildcardType)) {
/*  164 */       return false;
/*      */     }
/*      */ 
/*  167 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */   private static boolean isAssignable(Type type, ParameterizedType toParameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  181 */     if (type == null) {
/*  182 */       return true;
/*      */     }
/*      */ 
/*  187 */     if (toParameterizedType == null) {
/*  188 */       return false;
/*      */     }
/*      */ 
/*  192 */     if (toParameterizedType.equals(type)) {
/*  193 */       return true;
/*      */     }
/*      */ 
/*  197 */     Class toClass = getRawType(toParameterizedType);
/*      */ 
/*  200 */     Map fromTypeVarAssigns = getTypeArguments(type, toClass, null);
/*      */ 
/*  203 */     if (fromTypeVarAssigns == null) {
/*  204 */       return false;
/*      */     }
/*      */ 
/*  210 */     if (fromTypeVarAssigns.isEmpty()) {
/*  211 */       return true;
/*      */     }
/*      */ 
/*  215 */     Map toTypeVarAssigns = getTypeArguments(toParameterizedType, toClass, typeVarAssigns);
/*      */ 
/*  219 */     for (Map.Entry entry : toTypeVarAssigns.entrySet()) {
/*  220 */       Type toTypeArg = (Type)entry.getValue();
/*  221 */       Type fromTypeArg = (Type)fromTypeVarAssigns.get(entry.getKey());
/*      */ 
/*  226 */       if ((fromTypeArg != null) && (!toTypeArg.equals(fromTypeArg)) && ((!(toTypeArg instanceof WildcardType)) || (!isAssignable(fromTypeArg, toTypeArg, typeVarAssigns))))
/*      */       {
/*  230 */         return false;
/*      */       }
/*      */     }
/*      */ 
/*  234 */     return true;
/*      */   }
/*      */ 
/*      */   private static boolean isAssignable(Type type, GenericArrayType toGenericArrayType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  249 */     if (type == null) {
/*  250 */       return true;
/*      */     }
/*      */ 
/*  255 */     if (toGenericArrayType == null) {
/*  256 */       return false;
/*      */     }
/*      */ 
/*  260 */     if (toGenericArrayType.equals(type)) {
/*  261 */       return true;
/*      */     }
/*      */ 
/*  264 */     Type toComponentType = toGenericArrayType.getGenericComponentType();
/*      */ 
/*  266 */     if ((type instanceof Class)) {
/*  267 */       Class cls = (Class)type;
/*      */ 
/*  270 */       return (cls.isArray()) && (isAssignable(cls.getComponentType(), toComponentType, typeVarAssigns));
/*      */     }
/*      */ 
/*  274 */     if ((type instanceof GenericArrayType))
/*      */     {
/*  276 */       return isAssignable(((GenericArrayType)type).getGenericComponentType(), toComponentType, typeVarAssigns);
/*      */     }
/*      */ 
/*  280 */     if ((type instanceof WildcardType))
/*      */     {
/*  282 */       for (Type bound : getImplicitUpperBounds((WildcardType)type)) {
/*  283 */         if (isAssignable(bound, toGenericArrayType)) {
/*  284 */           return true;
/*      */         }
/*      */       }
/*      */ 
/*  288 */       return false;
/*      */     }
/*      */ 
/*  291 */     if ((type instanceof TypeVariable))
/*      */     {
/*  294 */       for (Type bound : getImplicitBounds((TypeVariable)type)) {
/*  295 */         if (isAssignable(bound, toGenericArrayType)) {
/*  296 */           return true;
/*      */         }
/*      */       }
/*      */ 
/*  300 */       return false;
/*      */     }
/*      */ 
/*  303 */     if ((type instanceof ParameterizedType))
/*      */     {
/*  307 */       return false;
/*      */     }
/*      */ 
/*  310 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */   private static boolean isAssignable(Type type, WildcardType toWildcardType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  325 */     if (type == null) {
/*  326 */       return true;
/*      */     }
/*      */ 
/*  331 */     if (toWildcardType == null) {
/*  332 */       return false;
/*      */     }
/*      */ 
/*  336 */     if (toWildcardType.equals(type)) {
/*  337 */       return true;
/*      */     }
/*      */ 
/*  340 */     Type[] toUpperBounds = getImplicitUpperBounds(toWildcardType);
/*  341 */     Type[] toLowerBounds = getImplicitLowerBounds(toWildcardType);
/*      */ 
/*  343 */     if ((type instanceof WildcardType)) {
/*  344 */       WildcardType wildcardType = (WildcardType)type;
/*  345 */       Type[] upperBounds = getImplicitUpperBounds(wildcardType);
/*  346 */       Type[] lowerBounds = getImplicitLowerBounds(wildcardType);
/*      */ 
/*  348 */       for (Type toBound : toUpperBounds)
/*      */       {
/*  351 */         toBound = substituteTypeVariables(toBound, typeVarAssigns);
/*      */ 
/*  356 */         for (Type bound : upperBounds) {
/*  357 */           if (!isAssignable(bound, toBound, typeVarAssigns)) {
/*  358 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  363 */       for (Type toBound : toLowerBounds)
/*      */       {
/*  366 */         toBound = substituteTypeVariables(toBound, typeVarAssigns);
/*      */ 
/*  371 */         for (Type bound : lowerBounds) {
/*  372 */           if (!isAssignable(toBound, bound, typeVarAssigns)) {
/*  373 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  378 */       return true;
/*      */     }
/*      */ 
/*  381 */     for (Type toBound : toUpperBounds)
/*      */     {
/*  384 */       if (!isAssignable(type, substituteTypeVariables(toBound, typeVarAssigns), typeVarAssigns))
/*      */       {
/*  386 */         return false;
/*      */       }
/*      */     }
/*      */ 
/*  390 */     for (Type toBound : toLowerBounds)
/*      */     {
/*  393 */       if (!isAssignable(substituteTypeVariables(toBound, typeVarAssigns), type, typeVarAssigns))
/*      */       {
/*  395 */         return false;
/*      */       }
/*      */     }
/*      */ 
/*  399 */     return true;
/*      */   }
/*      */ 
/*      */   private static boolean isAssignable(Type type, TypeVariable<?> toTypeVariable, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  414 */     if (type == null) {
/*  415 */       return true;
/*      */     }
/*      */ 
/*  420 */     if (toTypeVariable == null) {
/*  421 */       return false;
/*      */     }
/*      */ 
/*  425 */     if (toTypeVariable.equals(type)) {
/*  426 */       return true;
/*      */     }
/*      */ 
/*  429 */     if ((type instanceof TypeVariable))
/*      */     {
/*  433 */       Type[] bounds = getImplicitBounds((TypeVariable)type);
/*      */ 
/*  435 */       for (Type bound : bounds) {
/*  436 */         if (isAssignable(bound, toTypeVariable, typeVarAssigns)) {
/*  437 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  442 */     if (((type instanceof Class)) || ((type instanceof ParameterizedType)) || ((type instanceof GenericArrayType)) || ((type instanceof WildcardType)))
/*      */     {
/*  444 */       return false;
/*      */     }
/*      */ 
/*  447 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */   private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  459 */     if (((type instanceof TypeVariable)) && (typeVarAssigns != null)) {
/*  460 */       Type replacementType = (Type)typeVarAssigns.get(type);
/*      */ 
/*  462 */       if (replacementType == null) {
/*  463 */         throw new IllegalArgumentException("missing assignment type for type variable " + type);
/*      */       }
/*      */ 
/*  467 */       return replacementType;
/*      */     }
/*      */ 
/*  470 */     return type;
/*      */   }
/*      */ 
/*      */   public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType type)
/*      */   {
/*  485 */     return getTypeArguments(type, getRawType(type), null);
/*      */   }
/*      */ 
/*      */   public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass)
/*      */   {
/*  521 */     return getTypeArguments(type, toClass, null);
/*      */   }
/*      */ 
/*      */   private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
/*      */   {
/*  534 */     if ((type instanceof Class)) {
/*  535 */       return getTypeArguments((Class)type, toClass, subtypeVarAssigns);
/*      */     }
/*      */ 
/*  538 */     if ((type instanceof ParameterizedType)) {
/*  539 */       return getTypeArguments((ParameterizedType)type, toClass, subtypeVarAssigns);
/*      */     }
/*      */ 
/*  542 */     if ((type instanceof GenericArrayType)) {
/*  543 */       return getTypeArguments(((GenericArrayType)type).getGenericComponentType(), toClass.isArray() ? toClass.getComponentType() : toClass, subtypeVarAssigns);
/*      */     }
/*      */ 
/*  549 */     if ((type instanceof WildcardType)) {
/*  550 */       for (Type bound : getImplicitUpperBounds((WildcardType)type))
/*      */       {
/*  552 */         if (isAssignable(bound, toClass)) {
/*  553 */           return getTypeArguments(bound, toClass, subtypeVarAssigns);
/*      */         }
/*      */       }
/*      */ 
/*  557 */       return null;
/*      */     }
/*      */ 
/*  561 */     if ((type instanceof TypeVariable)) {
/*  562 */       for (Type bound : getImplicitBounds((TypeVariable)type))
/*      */       {
/*  564 */         if (isAssignable(bound, toClass)) {
/*  565 */           return getTypeArguments(bound, toClass, subtypeVarAssigns);
/*      */         }
/*      */       }
/*      */ 
/*  569 */       return null;
/*      */     }
/*      */ 
/*  573 */     throw new IllegalStateException("found an unhandled type: " + type);
/*      */   }
/*      */ 
/*      */   private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
/*      */   {
/*  587 */     Class cls = getRawType(parameterizedType);
/*      */ 
/*  590 */     if (!isAssignable(cls, toClass)) {
/*  591 */       return null;
/*      */     }
/*      */ 
/*  594 */     Type ownerType = parameterizedType.getOwnerType();
/*      */     Map typeVarAssigns;
/*      */     Map typeVarAssigns;
/*  597 */     if ((ownerType instanceof ParameterizedType))
/*      */     {
/*  599 */       ParameterizedType parameterizedOwnerType = (ParameterizedType)ownerType;
/*  600 */       typeVarAssigns = getTypeArguments(parameterizedOwnerType, getRawType(parameterizedOwnerType), subtypeVarAssigns);
/*      */     }
/*      */     else
/*      */     {
/*  604 */       typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
/*      */     }
/*      */ 
/*  609 */     Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*      */ 
/*  611 */     TypeVariable[] typeParams = cls.getTypeParameters();
/*      */ 
/*  614 */     for (int i = 0; i < typeParams.length; i++) {
/*  615 */       Type typeArg = typeArgs[i];
/*  616 */       typeVarAssigns.put(typeParams[i], typeVarAssigns.containsKey(typeArg) ? (Type)typeVarAssigns.get(typeArg) : typeArg);
/*      */     }
/*      */ 
/*  620 */     if (toClass.equals(cls))
/*      */     {
/*  622 */       return typeVarAssigns;
/*      */     }
/*      */ 
/*  626 */     return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
/*      */   }
/*      */ 
/*      */   private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
/*      */   {
/*  640 */     if (!isAssignable(cls, toClass)) {
/*  641 */       return null;
/*      */     }
/*      */ 
/*  645 */     if (cls.isPrimitive())
/*      */     {
/*  647 */       if (toClass.isPrimitive())
/*      */       {
/*  650 */         return new HashMap();
/*      */       }
/*      */ 
/*  654 */       cls = ClassUtils.primitiveToWrapper(cls);
/*      */     }
/*      */ 
/*  658 */     HashMap typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
/*      */ 
/*  662 */     if ((cls.getTypeParameters().length > 0) || (toClass.equals(cls))) {
/*  663 */       return typeVarAssigns;
/*      */     }
/*      */ 
/*  667 */     return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
/*      */   }
/*      */ 
/*      */   public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType superType)
/*      */   {
/*  699 */     Class superClass = getRawType(superType);
/*      */ 
/*  702 */     if (!isAssignable(cls, superClass)) {
/*  703 */       return null;
/*      */     }
/*      */ 
/*  706 */     if (cls.equals(superClass)) {
/*  707 */       return getTypeArguments(superType, superClass, null);
/*      */     }
/*      */ 
/*  711 */     Type midType = getClosestParentType(cls, superClass);
/*      */ 
/*  714 */     if ((midType instanceof Class)) {
/*  715 */       return determineTypeArguments((Class)midType, superType);
/*      */     }
/*      */ 
/*  718 */     ParameterizedType midParameterizedType = (ParameterizedType)midType;
/*  719 */     Class midClass = getRawType(midParameterizedType);
/*      */ 
/*  722 */     Map typeVarAssigns = determineTypeArguments(midClass, superType);
/*      */ 
/*  724 */     mapTypeVariablesToArguments(cls, midParameterizedType, typeVarAssigns);
/*      */ 
/*  726 */     return typeVarAssigns;
/*      */   }
/*      */ 
/*      */   private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  740 */     Type ownerType = parameterizedType.getOwnerType();
/*      */ 
/*  742 */     if ((ownerType instanceof ParameterizedType))
/*      */     {
/*  744 */       mapTypeVariablesToArguments(cls, (ParameterizedType)ownerType, typeVarAssigns);
/*      */     }
/*      */ 
/*  751 */     Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*      */ 
/*  755 */     TypeVariable[] typeVars = getRawType(parameterizedType).getTypeParameters();
/*      */ 
/*  758 */     List typeVarList = Arrays.asList(cls.getTypeParameters());
/*      */ 
/*  761 */     for (int i = 0; i < typeArgs.length; i++) {
/*  762 */       TypeVariable typeVar = typeVars[i];
/*  763 */       Type typeArg = typeArgs[i];
/*      */ 
/*  766 */       if ((typeVarList.contains(typeArg)) && (typeVarAssigns.containsKey(typeVar)))
/*      */       {
/*  771 */         typeVarAssigns.put((TypeVariable)typeArg, typeVarAssigns.get(typeVar));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static Type getClosestParentType(Class<?> cls, Class<?> superClass)
/*      */   {
/*  786 */     if (superClass.isInterface())
/*      */     {
/*  788 */       Type[] interfaceTypes = cls.getGenericInterfaces();
/*      */ 
/*  790 */       Type genericInterface = null;
/*      */ 
/*  793 */       for (Type midType : interfaceTypes) {
/*  794 */         Class midClass = null;
/*      */ 
/*  796 */         if ((midType instanceof ParameterizedType))
/*  797 */           midClass = getRawType((ParameterizedType)midType);
/*  798 */         else if ((midType instanceof Class))
/*  799 */           midClass = (Class)midType;
/*      */         else {
/*  801 */           throw new IllegalStateException("Unexpected generic interface type found: " + midType);
/*      */         }
/*      */ 
/*  807 */         if ((isAssignable(midClass, superClass)) && (isAssignable(genericInterface, midClass)))
/*      */         {
/*  809 */           genericInterface = midType;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  814 */       if (genericInterface != null) {
/*  815 */         return genericInterface;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  821 */     return cls.getGenericSuperclass();
/*      */   }
/*      */ 
/*      */   public static boolean isInstance(Object value, Type type)
/*      */   {
/*  833 */     if (type == null) {
/*  834 */       return false;
/*      */     }
/*      */ 
/*  837 */     return value == null ? false : (!(type instanceof Class)) || (!((Class)type).isPrimitive()) ? true : isAssignable(value.getClass(), type, null);
/*      */   }
/*      */ 
/*      */   public static Type[] normalizeUpperBounds(Type[] bounds)
/*      */   {
/*  864 */     if (bounds.length < 2) {
/*  865 */       return bounds;
/*      */     }
/*      */ 
/*  868 */     Set types = new HashSet(bounds.length);
/*      */ 
/*  870 */     for (Type type1 : bounds) {
/*  871 */       boolean subtypeFound = false;
/*      */ 
/*  873 */       for (Type type2 : bounds) {
/*  874 */         if ((type1 != type2) && (isAssignable(type2, type1, null))) {
/*  875 */           subtypeFound = true;
/*  876 */           break;
/*      */         }
/*      */       }
/*      */ 
/*  880 */       if (!subtypeFound) {
/*  881 */         types.add(type1);
/*      */       }
/*      */     }
/*      */ 
/*  885 */     return (Type[])types.toArray(new Type[types.size()]);
/*      */   }
/*      */ 
/*      */   public static Type[] getImplicitBounds(TypeVariable<?> typeVariable)
/*      */   {
/*  898 */     Type[] bounds = typeVariable.getBounds();
/*      */ 
/*  900 */     return bounds.length == 0 ? new Type[] { Object.class } : normalizeUpperBounds(bounds);
/*      */   }
/*      */ 
/*      */   public static Type[] getImplicitUpperBounds(WildcardType wildcardType)
/*      */   {
/*  914 */     Type[] bounds = wildcardType.getUpperBounds();
/*      */ 
/*  916 */     return bounds.length == 0 ? new Type[] { Object.class } : normalizeUpperBounds(bounds);
/*      */   }
/*      */ 
/*      */   public static Type[] getImplicitLowerBounds(WildcardType wildcardType)
/*      */   {
/*  929 */     Type[] bounds = wildcardType.getLowerBounds();
/*      */ 
/*  931 */     return bounds.length == 0 ? new Type[] { null } : bounds;
/*      */   }
/*      */ 
/*      */   public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> typeVarAssigns)
/*      */   {
/*  950 */     for (Map.Entry entry : typeVarAssigns.entrySet()) {
/*  951 */       TypeVariable typeVar = (TypeVariable)entry.getKey();
/*  952 */       Type type = (Type)entry.getValue();
/*      */ 
/*  954 */       for (Type bound : getImplicitBounds(typeVar)) {
/*  955 */         if (!isAssignable(type, substituteTypeVariables(bound, typeVarAssigns), typeVarAssigns))
/*      */         {
/*  957 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  962 */     return true;
/*      */   }
/*      */ 
/*      */   private static Class<?> getRawType(ParameterizedType parameterizedType)
/*      */   {
/*  973 */     Type rawType = parameterizedType.getRawType();
/*      */ 
/*  980 */     if (!(rawType instanceof Class)) {
/*  981 */       throw new IllegalStateException("Wait... What!? Type of rawType: " + rawType);
/*      */     }
/*      */ 
/*  984 */     return (Class)rawType;
/*      */   }
/*      */ 
/*      */   public static Class<?> getRawType(Type type, Type assigningType)
/*      */   {
/* 1000 */     if ((type instanceof Class))
/*      */     {
/* 1002 */       return (Class)type;
/*      */     }
/*      */ 
/* 1005 */     if ((type instanceof ParameterizedType))
/*      */     {
/* 1007 */       return getRawType((ParameterizedType)type);
/*      */     }
/*      */ 
/* 1010 */     if ((type instanceof TypeVariable)) {
/* 1011 */       if (assigningType == null) {
/* 1012 */         return null;
/*      */       }
/*      */ 
/* 1016 */       Object genericDeclaration = ((TypeVariable)type).getGenericDeclaration();
/*      */ 
/* 1020 */       if (!(genericDeclaration instanceof Class)) {
/* 1021 */         return null;
/*      */       }
/*      */ 
/* 1026 */       Map typeVarAssigns = getTypeArguments(assigningType, (Class)genericDeclaration);
/*      */ 
/* 1031 */       if (typeVarAssigns == null) {
/* 1032 */         return null;
/*      */       }
/*      */ 
/* 1036 */       Type typeArgument = (Type)typeVarAssigns.get(type);
/*      */ 
/* 1038 */       if (typeArgument == null) {
/* 1039 */         return null;
/*      */       }
/*      */ 
/* 1043 */       return getRawType(typeArgument, assigningType);
/*      */     }
/*      */ 
/* 1046 */     if ((type instanceof GenericArrayType))
/*      */     {
/* 1048 */       Class rawComponentType = getRawType(((GenericArrayType)type).getGenericComponentType(), assigningType);
/*      */ 
/* 1052 */       return Array.newInstance(rawComponentType, 0).getClass();
/*      */     }
/*      */ 
/* 1056 */     if ((type instanceof WildcardType)) {
/* 1057 */       return null;
/*      */     }
/*      */ 
/* 1060 */     throw new IllegalArgumentException("unknown type: " + type);
/*      */   }
/*      */ 
/*      */   public static boolean isArrayType(Type type)
/*      */   {
/* 1069 */     return ((type instanceof GenericArrayType)) || (((type instanceof Class)) && (((Class)type).isArray()));
/*      */   }
/*      */ 
/*      */   public static Type getArrayComponentType(Type type)
/*      */   {
/* 1078 */     if ((type instanceof Class)) {
/* 1079 */       Class clazz = (Class)type;
/* 1080 */       return clazz.isArray() ? clazz.getComponentType() : null;
/*      */     }
/* 1082 */     if ((type instanceof GenericArrayType)) {
/* 1083 */       return ((GenericArrayType)type).getGenericComponentType();
/*      */     }
/* 1085 */     return null;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.TypeUtils
 * JD-Core Version:    0.6.2
 */