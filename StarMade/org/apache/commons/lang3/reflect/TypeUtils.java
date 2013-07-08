/*    1:     */package org.apache.commons.lang3.reflect;
/*    2:     */
/*    3:     */import java.lang.reflect.Array;
/*    4:     */import java.lang.reflect.GenericArrayType;
/*    5:     */import java.lang.reflect.ParameterizedType;
/*    6:     */import java.lang.reflect.Type;
/*    7:     */import java.lang.reflect.TypeVariable;
/*    8:     */import java.lang.reflect.WildcardType;
/*    9:     */import java.util.Arrays;
/*   10:     */import java.util.HashMap;
/*   11:     */import java.util.HashSet;
/*   12:     */import java.util.List;
/*   13:     */import java.util.Map;
/*   14:     */import java.util.Map.Entry;
/*   15:     */import java.util.Set;
/*   16:     */import org.apache.commons.lang3.ClassUtils;
/*   17:     */
/*   61:     */public class TypeUtils
/*   62:     */{
/*   63:     */  public static boolean isAssignable(Type type, Type toType)
/*   64:     */  {
/*   65:  65 */    return isAssignable(type, toType, null);
/*   66:     */  }
/*   67:     */  
/*   77:     */  private static boolean isAssignable(Type type, Type toType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*   78:     */  {
/*   79:  79 */    if ((toType == null) || ((toType instanceof Class))) {
/*   80:  80 */      return isAssignable(type, (Class)toType);
/*   81:     */    }
/*   82:     */    
/*   83:  83 */    if ((toType instanceof ParameterizedType)) {
/*   84:  84 */      return isAssignable(type, (ParameterizedType)toType, typeVarAssigns);
/*   85:     */    }
/*   86:     */    
/*   87:  87 */    if ((toType instanceof GenericArrayType)) {
/*   88:  88 */      return isAssignable(type, (GenericArrayType)toType, typeVarAssigns);
/*   89:     */    }
/*   90:     */    
/*   91:  91 */    if ((toType instanceof WildcardType)) {
/*   92:  92 */      return isAssignable(type, (WildcardType)toType, typeVarAssigns);
/*   93:     */    }
/*   94:     */    
/*   96:  96 */    if ((toType instanceof TypeVariable)) {
/*   97:  97 */      return isAssignable(type, (TypeVariable)toType, typeVarAssigns);
/*   98:     */    }
/*   99:     */    
/*  101: 101 */    throw new IllegalStateException("found an unhandled type: " + toType);
/*  102:     */  }
/*  103:     */  
/*  111:     */  private static boolean isAssignable(Type type, Class<?> toClass)
/*  112:     */  {
/*  113: 113 */    if (type == null)
/*  114:     */    {
/*  115: 115 */      return (toClass == null) || (!toClass.isPrimitive());
/*  116:     */    }
/*  117:     */    
/*  120: 120 */    if (toClass == null) {
/*  121: 121 */      return false;
/*  122:     */    }
/*  123:     */    
/*  125: 125 */    if (toClass.equals(type)) {
/*  126: 126 */      return true;
/*  127:     */    }
/*  128:     */    
/*  129: 129 */    if ((type instanceof Class))
/*  130:     */    {
/*  131: 131 */      return ClassUtils.isAssignable((Class)type, toClass);
/*  132:     */    }
/*  133:     */    
/*  134: 134 */    if ((type instanceof ParameterizedType))
/*  135:     */    {
/*  136: 136 */      return isAssignable(getRawType((ParameterizedType)type), toClass);
/*  137:     */    }
/*  138:     */    
/*  140: 140 */    if ((type instanceof TypeVariable))
/*  141:     */    {
/*  143: 143 */      for (Type bound : ((TypeVariable)type).getBounds()) {
/*  144: 144 */        if (isAssignable(bound, toClass)) {
/*  145: 145 */          return true;
/*  146:     */        }
/*  147:     */      }
/*  148:     */      
/*  149: 149 */      return false;
/*  150:     */    }
/*  151:     */    
/*  154: 154 */    if ((type instanceof GenericArrayType)) {
/*  155: 155 */      return (toClass.equals(Object.class)) || ((toClass.isArray()) && (isAssignable(((GenericArrayType)type).getGenericComponentType(), toClass.getComponentType())));
/*  156:     */    }
/*  157:     */    
/*  163: 163 */    if ((type instanceof WildcardType)) {
/*  164: 164 */      return false;
/*  165:     */    }
/*  166:     */    
/*  167: 167 */    throw new IllegalStateException("found an unhandled type: " + type);
/*  168:     */  }
/*  169:     */  
/*  179:     */  private static boolean isAssignable(Type type, ParameterizedType toParameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*  180:     */  {
/*  181: 181 */    if (type == null) {
/*  182: 182 */      return true;
/*  183:     */    }
/*  184:     */    
/*  187: 187 */    if (toParameterizedType == null) {
/*  188: 188 */      return false;
/*  189:     */    }
/*  190:     */    
/*  192: 192 */    if (toParameterizedType.equals(type)) {
/*  193: 193 */      return true;
/*  194:     */    }
/*  195:     */    
/*  197: 197 */    Class<?> toClass = getRawType(toParameterizedType);
/*  198:     */    
/*  200: 200 */    Map<TypeVariable<?>, Type> fromTypeVarAssigns = getTypeArguments(type, toClass, null);
/*  201:     */    
/*  203: 203 */    if (fromTypeVarAssigns == null) {
/*  204: 204 */      return false;
/*  205:     */    }
/*  206:     */    
/*  210: 210 */    if (fromTypeVarAssigns.isEmpty()) {
/*  211: 211 */      return true;
/*  212:     */    }
/*  213:     */    
/*  215: 215 */    Map<TypeVariable<?>, Type> toTypeVarAssigns = getTypeArguments(toParameterizedType, toClass, typeVarAssigns);
/*  216:     */    
/*  219: 219 */    for (Map.Entry<TypeVariable<?>, Type> entry : toTypeVarAssigns.entrySet()) {
/*  220: 220 */      Type toTypeArg = (Type)entry.getValue();
/*  221: 221 */      Type fromTypeArg = (Type)fromTypeVarAssigns.get(entry.getKey());
/*  222:     */      
/*  226: 226 */      if ((fromTypeArg != null) && (!toTypeArg.equals(fromTypeArg)) && ((!(toTypeArg instanceof WildcardType)) || (!isAssignable(fromTypeArg, toTypeArg, typeVarAssigns))))
/*  227:     */      {
/*  230: 230 */        return false;
/*  231:     */      }
/*  232:     */    }
/*  233:     */    
/*  234: 234 */    return true;
/*  235:     */  }
/*  236:     */  
/*  247:     */  private static boolean isAssignable(Type type, GenericArrayType toGenericArrayType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*  248:     */  {
/*  249: 249 */    if (type == null) {
/*  250: 250 */      return true;
/*  251:     */    }
/*  252:     */    
/*  255: 255 */    if (toGenericArrayType == null) {
/*  256: 256 */      return false;
/*  257:     */    }
/*  258:     */    
/*  260: 260 */    if (toGenericArrayType.equals(type)) {
/*  261: 261 */      return true;
/*  262:     */    }
/*  263:     */    
/*  264: 264 */    Type toComponentType = toGenericArrayType.getGenericComponentType();
/*  265:     */    
/*  266: 266 */    if ((type instanceof Class)) {
/*  267: 267 */      Class<?> cls = (Class)type;
/*  268:     */      
/*  270: 270 */      return (cls.isArray()) && (isAssignable(cls.getComponentType(), toComponentType, typeVarAssigns));
/*  271:     */    }
/*  272:     */    
/*  274: 274 */    if ((type instanceof GenericArrayType))
/*  275:     */    {
/*  276: 276 */      return isAssignable(((GenericArrayType)type).getGenericComponentType(), toComponentType, typeVarAssigns);
/*  277:     */    }
/*  278:     */    
/*  280: 280 */    if ((type instanceof WildcardType))
/*  281:     */    {
/*  282: 282 */      for (Type bound : getImplicitUpperBounds((WildcardType)type)) {
/*  283: 283 */        if (isAssignable(bound, toGenericArrayType)) {
/*  284: 284 */          return true;
/*  285:     */        }
/*  286:     */      }
/*  287:     */      
/*  288: 288 */      return false;
/*  289:     */    }
/*  290:     */    
/*  291: 291 */    if ((type instanceof TypeVariable))
/*  292:     */    {
/*  294: 294 */      for (Type bound : getImplicitBounds((TypeVariable)type)) {
/*  295: 295 */        if (isAssignable(bound, toGenericArrayType)) {
/*  296: 296 */          return true;
/*  297:     */        }
/*  298:     */      }
/*  299:     */      
/*  300: 300 */      return false;
/*  301:     */    }
/*  302:     */    
/*  303: 303 */    if ((type instanceof ParameterizedType))
/*  304:     */    {
/*  307: 307 */      return false;
/*  308:     */    }
/*  309:     */    
/*  310: 310 */    throw new IllegalStateException("found an unhandled type: " + type);
/*  311:     */  }
/*  312:     */  
/*  323:     */  private static boolean isAssignable(Type type, WildcardType toWildcardType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*  324:     */  {
/*  325: 325 */    if (type == null) {
/*  326: 326 */      return true;
/*  327:     */    }
/*  328:     */    
/*  331: 331 */    if (toWildcardType == null) {
/*  332: 332 */      return false;
/*  333:     */    }
/*  334:     */    
/*  336: 336 */    if (toWildcardType.equals(type)) {
/*  337: 337 */      return true;
/*  338:     */    }
/*  339:     */    
/*  340: 340 */    Type[] toUpperBounds = getImplicitUpperBounds(toWildcardType);
/*  341: 341 */    Type[] toLowerBounds = getImplicitLowerBounds(toWildcardType);
/*  342:     */    
/*  343: 343 */    if ((type instanceof WildcardType)) {
/*  344: 344 */      WildcardType wildcardType = (WildcardType)type;
/*  345: 345 */      Type[] upperBounds = getImplicitUpperBounds(wildcardType);
/*  346: 346 */      Type[] lowerBounds = getImplicitLowerBounds(wildcardType);
/*  347:     */      
/*  348: 348 */      for (Type toBound : toUpperBounds)
/*  349:     */      {
/*  351: 351 */        toBound = substituteTypeVariables(toBound, typeVarAssigns);
/*  352:     */        
/*  356: 356 */        for (Type bound : upperBounds) {
/*  357: 357 */          if (!isAssignable(bound, toBound, typeVarAssigns)) {
/*  358: 358 */            return false;
/*  359:     */          }
/*  360:     */        }
/*  361:     */      }
/*  362:     */      
/*  363: 363 */      for (Type toBound : toLowerBounds)
/*  364:     */      {
/*  366: 366 */        toBound = substituteTypeVariables(toBound, typeVarAssigns);
/*  367:     */        
/*  371: 371 */        for (Type bound : lowerBounds) {
/*  372: 372 */          if (!isAssignable(toBound, bound, typeVarAssigns)) {
/*  373: 373 */            return false;
/*  374:     */          }
/*  375:     */        }
/*  376:     */      }
/*  377:     */      
/*  378: 378 */      return true;
/*  379:     */    }
/*  380:     */    
/*  381: 381 */    for (Type toBound : toUpperBounds)
/*  382:     */    {
/*  384: 384 */      if (!isAssignable(type, substituteTypeVariables(toBound, typeVarAssigns), typeVarAssigns))
/*  385:     */      {
/*  386: 386 */        return false;
/*  387:     */      }
/*  388:     */    }
/*  389:     */    
/*  390: 390 */    for (Type toBound : toLowerBounds)
/*  391:     */    {
/*  393: 393 */      if (!isAssignable(substituteTypeVariables(toBound, typeVarAssigns), type, typeVarAssigns))
/*  394:     */      {
/*  395: 395 */        return false;
/*  396:     */      }
/*  397:     */    }
/*  398:     */    
/*  399: 399 */    return true;
/*  400:     */  }
/*  401:     */  
/*  412:     */  private static boolean isAssignable(Type type, TypeVariable<?> toTypeVariable, Map<TypeVariable<?>, Type> typeVarAssigns)
/*  413:     */  {
/*  414: 414 */    if (type == null) {
/*  415: 415 */      return true;
/*  416:     */    }
/*  417:     */    
/*  420: 420 */    if (toTypeVariable == null) {
/*  421: 421 */      return false;
/*  422:     */    }
/*  423:     */    
/*  425: 425 */    if (toTypeVariable.equals(type)) {
/*  426: 426 */      return true;
/*  427:     */    }
/*  428:     */    
/*  429: 429 */    if ((type instanceof TypeVariable))
/*  430:     */    {
/*  433: 433 */      Type[] bounds = getImplicitBounds((TypeVariable)type);
/*  434:     */      
/*  435: 435 */      for (Type bound : bounds) {
/*  436: 436 */        if (isAssignable(bound, toTypeVariable, typeVarAssigns)) {
/*  437: 437 */          return true;
/*  438:     */        }
/*  439:     */      }
/*  440:     */    }
/*  441:     */    
/*  442: 442 */    if (((type instanceof Class)) || ((type instanceof ParameterizedType)) || ((type instanceof GenericArrayType)) || ((type instanceof WildcardType)))
/*  443:     */    {
/*  444: 444 */      return false;
/*  445:     */    }
/*  446:     */    
/*  447: 447 */    throw new IllegalStateException("found an unhandled type: " + type);
/*  448:     */  }
/*  449:     */  
/*  457:     */  private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> typeVarAssigns)
/*  458:     */  {
/*  459: 459 */    if (((type instanceof TypeVariable)) && (typeVarAssigns != null)) {
/*  460: 460 */      Type replacementType = (Type)typeVarAssigns.get(type);
/*  461:     */      
/*  462: 462 */      if (replacementType == null) {
/*  463: 463 */        throw new IllegalArgumentException("missing assignment type for type variable " + type);
/*  464:     */      }
/*  465:     */      
/*  467: 467 */      return replacementType;
/*  468:     */    }
/*  469:     */    
/*  470: 470 */    return type;
/*  471:     */  }
/*  472:     */  
/*  483:     */  public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType type)
/*  484:     */  {
/*  485: 485 */    return getTypeArguments(type, getRawType(type), null);
/*  486:     */  }
/*  487:     */  
/*  519:     */  public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass)
/*  520:     */  {
/*  521: 521 */    return getTypeArguments(type, toClass, null);
/*  522:     */  }
/*  523:     */  
/*  532:     */  private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
/*  533:     */  {
/*  534: 534 */    if ((type instanceof Class)) {
/*  535: 535 */      return getTypeArguments((Class)type, toClass, subtypeVarAssigns);
/*  536:     */    }
/*  537:     */    
/*  538: 538 */    if ((type instanceof ParameterizedType)) {
/*  539: 539 */      return getTypeArguments((ParameterizedType)type, toClass, subtypeVarAssigns);
/*  540:     */    }
/*  541:     */    
/*  542: 542 */    if ((type instanceof GenericArrayType)) {
/*  543: 543 */      return getTypeArguments(((GenericArrayType)type).getGenericComponentType(), toClass.isArray() ? toClass.getComponentType() : toClass, subtypeVarAssigns);
/*  544:     */    }
/*  545:     */    
/*  549: 549 */    if ((type instanceof WildcardType)) {
/*  550: 550 */      for (Type bound : getImplicitUpperBounds((WildcardType)type))
/*  551:     */      {
/*  552: 552 */        if (isAssignable(bound, toClass)) {
/*  553: 553 */          return getTypeArguments(bound, toClass, subtypeVarAssigns);
/*  554:     */        }
/*  555:     */      }
/*  556:     */      
/*  557: 557 */      return null;
/*  558:     */    }
/*  559:     */    
/*  561: 561 */    if ((type instanceof TypeVariable)) {
/*  562: 562 */      for (Type bound : getImplicitBounds((TypeVariable)type))
/*  563:     */      {
/*  564: 564 */        if (isAssignable(bound, toClass)) {
/*  565: 565 */          return getTypeArguments(bound, toClass, subtypeVarAssigns);
/*  566:     */        }
/*  567:     */      }
/*  568:     */      
/*  569: 569 */      return null;
/*  570:     */    }
/*  571:     */    
/*  573: 573 */    throw new IllegalStateException("found an unhandled type: " + type);
/*  574:     */  }
/*  575:     */  
/*  585:     */  private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
/*  586:     */  {
/*  587: 587 */    Class<?> cls = getRawType(parameterizedType);
/*  588:     */    
/*  590: 590 */    if (!isAssignable(cls, toClass)) {
/*  591: 591 */      return null;
/*  592:     */    }
/*  593:     */    
/*  594: 594 */    Type ownerType = parameterizedType.getOwnerType();
/*  595:     */    Map<TypeVariable<?>, Type> typeVarAssigns;
/*  596:     */    Map<TypeVariable<?>, Type> typeVarAssigns;
/*  597: 597 */    if ((ownerType instanceof ParameterizedType))
/*  598:     */    {
/*  599: 599 */      ParameterizedType parameterizedOwnerType = (ParameterizedType)ownerType;
/*  600: 600 */      typeVarAssigns = getTypeArguments(parameterizedOwnerType, getRawType(parameterizedOwnerType), subtypeVarAssigns);
/*  601:     */    }
/*  602:     */    else
/*  603:     */    {
/*  604: 604 */      typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
/*  605:     */    }
/*  606:     */    
/*  609: 609 */    Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*  610:     */    
/*  611: 611 */    TypeVariable<?>[] typeParams = cls.getTypeParameters();
/*  612:     */    
/*  614: 614 */    for (int i = 0; i < typeParams.length; i++) {
/*  615: 615 */      Type typeArg = typeArgs[i];
/*  616: 616 */      typeVarAssigns.put(typeParams[i], typeVarAssigns.containsKey(typeArg) ? (Type)typeVarAssigns.get(typeArg) : typeArg);
/*  617:     */    }
/*  618:     */    
/*  620: 620 */    if (toClass.equals(cls))
/*  621:     */    {
/*  622: 622 */      return typeVarAssigns;
/*  623:     */    }
/*  624:     */    
/*  626: 626 */    return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
/*  627:     */  }
/*  628:     */  
/*  638:     */  private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> toClass, Map<TypeVariable<?>, Type> subtypeVarAssigns)
/*  639:     */  {
/*  640: 640 */    if (!isAssignable(cls, toClass)) {
/*  641: 641 */      return null;
/*  642:     */    }
/*  643:     */    
/*  645: 645 */    if (cls.isPrimitive())
/*  646:     */    {
/*  647: 647 */      if (toClass.isPrimitive())
/*  648:     */      {
/*  650: 650 */        return new HashMap();
/*  651:     */      }
/*  652:     */      
/*  654: 654 */      cls = ClassUtils.primitiveToWrapper(cls);
/*  655:     */    }
/*  656:     */    
/*  658: 658 */    HashMap<TypeVariable<?>, Type> typeVarAssigns = subtypeVarAssigns == null ? new HashMap() : new HashMap(subtypeVarAssigns);
/*  659:     */    
/*  662: 662 */    if ((cls.getTypeParameters().length > 0) || (toClass.equals(cls))) {
/*  663: 663 */      return typeVarAssigns;
/*  664:     */    }
/*  665:     */    
/*  667: 667 */    return getTypeArguments(getClosestParentType(cls, toClass), toClass, typeVarAssigns);
/*  668:     */  }
/*  669:     */  
/*  697:     */  public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType superType)
/*  698:     */  {
/*  699: 699 */    Class<?> superClass = getRawType(superType);
/*  700:     */    
/*  702: 702 */    if (!isAssignable(cls, superClass)) {
/*  703: 703 */      return null;
/*  704:     */    }
/*  705:     */    
/*  706: 706 */    if (cls.equals(superClass)) {
/*  707: 707 */      return getTypeArguments(superType, superClass, null);
/*  708:     */    }
/*  709:     */    
/*  711: 711 */    Type midType = getClosestParentType(cls, superClass);
/*  712:     */    
/*  714: 714 */    if ((midType instanceof Class)) {
/*  715: 715 */      return determineTypeArguments((Class)midType, superType);
/*  716:     */    }
/*  717:     */    
/*  718: 718 */    ParameterizedType midParameterizedType = (ParameterizedType)midType;
/*  719: 719 */    Class<?> midClass = getRawType(midParameterizedType);
/*  720:     */    
/*  722: 722 */    Map<TypeVariable<?>, Type> typeVarAssigns = determineTypeArguments(midClass, superType);
/*  723:     */    
/*  724: 724 */    mapTypeVariablesToArguments(cls, midParameterizedType, typeVarAssigns);
/*  725:     */    
/*  726: 726 */    return typeVarAssigns;
/*  727:     */  }
/*  728:     */  
/*  738:     */  private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> typeVarAssigns)
/*  739:     */  {
/*  740: 740 */    Type ownerType = parameterizedType.getOwnerType();
/*  741:     */    
/*  742: 742 */    if ((ownerType instanceof ParameterizedType))
/*  743:     */    {
/*  744: 744 */      mapTypeVariablesToArguments(cls, (ParameterizedType)ownerType, typeVarAssigns);
/*  745:     */    }
/*  746:     */    
/*  751: 751 */    Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*  752:     */    
/*  755: 755 */    TypeVariable<?>[] typeVars = getRawType(parameterizedType).getTypeParameters();
/*  756:     */    
/*  758: 758 */    List<TypeVariable<Class<T>>> typeVarList = Arrays.asList(cls.getTypeParameters());
/*  759:     */    
/*  761: 761 */    for (int i = 0; i < typeArgs.length; i++) {
/*  762: 762 */      TypeVariable<?> typeVar = typeVars[i];
/*  763: 763 */      Type typeArg = typeArgs[i];
/*  764:     */      
/*  766: 766 */      if ((typeVarList.contains(typeArg)) && (typeVarAssigns.containsKey(typeVar)))
/*  767:     */      {
/*  771: 771 */        typeVarAssigns.put((TypeVariable)typeArg, typeVarAssigns.get(typeVar));
/*  772:     */      }
/*  773:     */    }
/*  774:     */  }
/*  775:     */  
/*  784:     */  private static Type getClosestParentType(Class<?> cls, Class<?> superClass)
/*  785:     */  {
/*  786: 786 */    if (superClass.isInterface())
/*  787:     */    {
/*  788: 788 */      Type[] interfaceTypes = cls.getGenericInterfaces();
/*  789:     */      
/*  790: 790 */      Type genericInterface = null;
/*  791:     */      
/*  793: 793 */      for (Type midType : interfaceTypes) {
/*  794: 794 */        Class<?> midClass = null;
/*  795:     */        
/*  796: 796 */        if ((midType instanceof ParameterizedType)) {
/*  797: 797 */          midClass = getRawType((ParameterizedType)midType);
/*  798: 798 */        } else if ((midType instanceof Class)) {
/*  799: 799 */          midClass = (Class)midType;
/*  800:     */        } else {
/*  801: 801 */          throw new IllegalStateException("Unexpected generic interface type found: " + midType);
/*  802:     */        }
/*  803:     */        
/*  807: 807 */        if ((isAssignable(midClass, superClass)) && (isAssignable(genericInterface, midClass)))
/*  808:     */        {
/*  809: 809 */          genericInterface = midType;
/*  810:     */        }
/*  811:     */      }
/*  812:     */      
/*  814: 814 */      if (genericInterface != null) {
/*  815: 815 */        return genericInterface;
/*  816:     */      }
/*  817:     */    }
/*  818:     */    
/*  821: 821 */    return cls.getGenericSuperclass();
/*  822:     */  }
/*  823:     */  
/*  831:     */  public static boolean isInstance(Object value, Type type)
/*  832:     */  {
/*  833: 833 */    if (type == null) {
/*  834: 834 */      return false;
/*  835:     */    }
/*  836:     */    
/*  837: 837 */    return value == null ? false : (!(type instanceof Class)) || (!((Class)type).isPrimitive()) ? true : isAssignable(value.getClass(), type, null);
/*  838:     */  }
/*  839:     */  
/*  862:     */  public static Type[] normalizeUpperBounds(Type[] bounds)
/*  863:     */  {
/*  864: 864 */    if (bounds.length < 2) {
/*  865: 865 */      return bounds;
/*  866:     */    }
/*  867:     */    
/*  868: 868 */    Set<Type> types = new HashSet(bounds.length);
/*  869:     */    
/*  870: 870 */    for (Type type1 : bounds) {
/*  871: 871 */      boolean subtypeFound = false;
/*  872:     */      
/*  873: 873 */      for (Type type2 : bounds) {
/*  874: 874 */        if ((type1 != type2) && (isAssignable(type2, type1, null))) {
/*  875: 875 */          subtypeFound = true;
/*  876: 876 */          break;
/*  877:     */        }
/*  878:     */      }
/*  879:     */      
/*  880: 880 */      if (!subtypeFound) {
/*  881: 881 */        types.add(type1);
/*  882:     */      }
/*  883:     */    }
/*  884:     */    
/*  885: 885 */    return (Type[])types.toArray(new Type[types.size()]);
/*  886:     */  }
/*  887:     */  
/*  896:     */  public static Type[] getImplicitBounds(TypeVariable<?> typeVariable)
/*  897:     */  {
/*  898: 898 */    Type[] bounds = typeVariable.getBounds();
/*  899:     */    
/*  900: 900 */    return bounds.length == 0 ? new Type[] { Object.class } : normalizeUpperBounds(bounds);
/*  901:     */  }
/*  902:     */  
/*  912:     */  public static Type[] getImplicitUpperBounds(WildcardType wildcardType)
/*  913:     */  {
/*  914: 914 */    Type[] bounds = wildcardType.getUpperBounds();
/*  915:     */    
/*  916: 916 */    return bounds.length == 0 ? new Type[] { Object.class } : normalizeUpperBounds(bounds);
/*  917:     */  }
/*  918:     */  
/*  927:     */  public static Type[] getImplicitLowerBounds(WildcardType wildcardType)
/*  928:     */  {
/*  929: 929 */    Type[] bounds = wildcardType.getLowerBounds();
/*  930:     */    
/*  931: 931 */    return bounds.length == 0 ? new Type[] { null } : bounds;
/*  932:     */  }
/*  933:     */  
/*  948:     */  public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> typeVarAssigns)
/*  949:     */  {
/*  950: 950 */    for (Map.Entry<TypeVariable<?>, Type> entry : typeVarAssigns.entrySet()) {
/*  951: 951 */      TypeVariable<?> typeVar = (TypeVariable)entry.getKey();
/*  952: 952 */      Type type = (Type)entry.getValue();
/*  953:     */      
/*  954: 954 */      for (Type bound : getImplicitBounds(typeVar)) {
/*  955: 955 */        if (!isAssignable(type, substituteTypeVariables(bound, typeVarAssigns), typeVarAssigns))
/*  956:     */        {
/*  957: 957 */          return false;
/*  958:     */        }
/*  959:     */      }
/*  960:     */    }
/*  961:     */    
/*  962: 962 */    return true;
/*  963:     */  }
/*  964:     */  
/*  971:     */  private static Class<?> getRawType(ParameterizedType parameterizedType)
/*  972:     */  {
/*  973: 973 */    Type rawType = parameterizedType.getRawType();
/*  974:     */    
/*  980: 980 */    if (!(rawType instanceof Class)) {
/*  981: 981 */      throw new IllegalStateException("Wait... What!? Type of rawType: " + rawType);
/*  982:     */    }
/*  983:     */    
/*  984: 984 */    return (Class)rawType;
/*  985:     */  }
/*  986:     */  
/*  998:     */  public static Class<?> getRawType(Type type, Type assigningType)
/*  999:     */  {
/* 1000:1000 */    if ((type instanceof Class))
/* 1001:     */    {
/* 1002:1002 */      return (Class)type;
/* 1003:     */    }
/* 1004:     */    
/* 1005:1005 */    if ((type instanceof ParameterizedType))
/* 1006:     */    {
/* 1007:1007 */      return getRawType((ParameterizedType)type);
/* 1008:     */    }
/* 1009:     */    
/* 1010:1010 */    if ((type instanceof TypeVariable)) {
/* 1011:1011 */      if (assigningType == null) {
/* 1012:1012 */        return null;
/* 1013:     */      }
/* 1014:     */      
/* 1016:1016 */      Object genericDeclaration = ((TypeVariable)type).getGenericDeclaration();
/* 1017:     */      
/* 1020:1020 */      if (!(genericDeclaration instanceof Class)) {
/* 1021:1021 */        return null;
/* 1022:     */      }
/* 1023:     */      
/* 1026:1026 */      Map<TypeVariable<?>, Type> typeVarAssigns = getTypeArguments(assigningType, (Class)genericDeclaration);
/* 1027:     */      
/* 1031:1031 */      if (typeVarAssigns == null) {
/* 1032:1032 */        return null;
/* 1033:     */      }
/* 1034:     */      
/* 1036:1036 */      Type typeArgument = (Type)typeVarAssigns.get(type);
/* 1037:     */      
/* 1038:1038 */      if (typeArgument == null) {
/* 1039:1039 */        return null;
/* 1040:     */      }
/* 1041:     */      
/* 1043:1043 */      return getRawType(typeArgument, assigningType);
/* 1044:     */    }
/* 1045:     */    
/* 1046:1046 */    if ((type instanceof GenericArrayType))
/* 1047:     */    {
/* 1048:1048 */      Class<?> rawComponentType = getRawType(((GenericArrayType)type).getGenericComponentType(), assigningType);
/* 1049:     */      
/* 1052:1052 */      return Array.newInstance(rawComponentType, 0).getClass();
/* 1053:     */    }
/* 1054:     */    
/* 1056:1056 */    if ((type instanceof WildcardType)) {
/* 1057:1057 */      return null;
/* 1058:     */    }
/* 1059:     */    
/* 1060:1060 */    throw new IllegalArgumentException("unknown type: " + type);
/* 1061:     */  }
/* 1062:     */  
/* 1067:     */  public static boolean isArrayType(Type type)
/* 1068:     */  {
/* 1069:1069 */    return ((type instanceof GenericArrayType)) || (((type instanceof Class)) && (((Class)type).isArray()));
/* 1070:     */  }
/* 1071:     */  
/* 1076:     */  public static Type getArrayComponentType(Type type)
/* 1077:     */  {
/* 1078:1078 */    if ((type instanceof Class)) {
/* 1079:1079 */      Class<?> clazz = (Class)type;
/* 1080:1080 */      return clazz.isArray() ? clazz.getComponentType() : null;
/* 1081:     */    }
/* 1082:1082 */    if ((type instanceof GenericArrayType)) {
/* 1083:1083 */      return ((GenericArrayType)type).getGenericComponentType();
/* 1084:     */    }
/* 1085:1085 */    return null;
/* 1086:     */  }
/* 1087:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.reflect.TypeUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */