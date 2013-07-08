/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.lang.annotation.Annotation;
/*   4:    */import java.lang.reflect.InvocationTargetException;
/*   5:    */import java.lang.reflect.Method;
/*   6:    */import java.util.Arrays;
/*   7:    */import org.apache.commons.lang3.builder.ToStringBuilder;
/*   8:    */import org.apache.commons.lang3.builder.ToStringStyle;
/*   9:    */
/*  49:    */public class AnnotationUtils
/*  50:    */{
/*  51: 51 */  private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle()
/*  52:    */  {
/*  53:    */    private static final long serialVersionUID = 1L;
/*  54:    */    
/*  71:    */    protected String getShortClassName(Class<?> cls)
/*  72:    */    {
/*  73: 73 */      Class<? extends Annotation> annotationType = null;
/*  74: 74 */      for (Class<?> iface : ClassUtils.getAllInterfaces(cls)) {
/*  75: 75 */        if (Annotation.class.isAssignableFrom(iface))
/*  76:    */        {
/*  78: 78 */          Class<? extends Annotation> found = iface;
/*  79: 79 */          annotationType = found;
/*  80: 80 */          break;
/*  81:    */        }
/*  82:    */      }
/*  83: 83 */      return new StringBuilder(annotationType == null ? "" : annotationType.getName()).insert(0, '@').toString();
/*  84:    */    }
/*  85:    */    
/*  90:    */    protected void appendDetail(StringBuffer buffer, String fieldName, Object value)
/*  91:    */    {
/*  92: 92 */      if ((value instanceof Annotation)) {
/*  93: 93 */        value = AnnotationUtils.toString((Annotation)value);
/*  94:    */      }
/*  95: 95 */      super.appendDetail(buffer, fieldName, value);
/*  96:    */    }
/*  97:    */  };
/*  98:    */  
/* 121:    */  public static boolean equals(Annotation a1, Annotation a2)
/* 122:    */  {
/* 123:123 */    if (a1 == a2) {
/* 124:124 */      return true;
/* 125:    */    }
/* 126:126 */    if ((a1 == null) || (a2 == null)) {
/* 127:127 */      return false;
/* 128:    */    }
/* 129:129 */    Class<? extends Annotation> type = a1.annotationType();
/* 130:130 */    Class<? extends Annotation> type2 = a2.annotationType();
/* 131:131 */    Validate.notNull(type, "Annotation %s with null annotationType()", new Object[] { a1 });
/* 132:132 */    Validate.notNull(type2, "Annotation %s with null annotationType()", new Object[] { a2 });
/* 133:133 */    if (!type.equals(type2)) {
/* 134:134 */      return false;
/* 135:    */    }
/* 136:    */    try {
/* 137:137 */      for (Method m : type.getDeclaredMethods()) {
/* 138:138 */        if ((m.getParameterTypes().length == 0) && (isValidAnnotationMemberType(m.getReturnType())))
/* 139:    */        {
/* 140:140 */          Object v1 = m.invoke(a1, new Object[0]);
/* 141:141 */          Object v2 = m.invoke(a2, new Object[0]);
/* 142:142 */          if (!memberEquals(m.getReturnType(), v1, v2)) {
/* 143:143 */            return false;
/* 144:    */          }
/* 145:    */        }
/* 146:    */      }
/* 147:    */    } catch (IllegalAccessException ex) {
/* 148:148 */      return false;
/* 149:    */    } catch (InvocationTargetException ex) {
/* 150:150 */      return false;
/* 151:    */    }
/* 152:152 */    return true;
/* 153:    */  }
/* 154:    */  
/* 166:    */  public static int hashCode(Annotation a)
/* 167:    */  {
/* 168:168 */    int result = 0;
/* 169:169 */    Class<? extends Annotation> type = a.annotationType();
/* 170:170 */    for (Method m : type.getDeclaredMethods()) {
/* 171:    */      try {
/* 172:172 */        Object value = m.invoke(a, new Object[0]);
/* 173:173 */        if (value == null) {
/* 174:174 */          throw new IllegalStateException(String.format("Annotation method %s returned null", new Object[] { m }));
/* 175:    */        }
/* 176:    */        
/* 177:177 */        result += hashMember(m.getName(), value);
/* 178:    */      } catch (RuntimeException ex) {
/* 179:179 */        throw ex;
/* 180:    */      } catch (Exception ex) {
/* 181:181 */        throw new RuntimeException(ex);
/* 182:    */      }
/* 183:    */    }
/* 184:184 */    return result;
/* 185:    */  }
/* 186:    */  
/* 194:    */  public static String toString(Annotation a)
/* 195:    */  {
/* 196:196 */    ToStringBuilder builder = new ToStringBuilder(a, TO_STRING_STYLE);
/* 197:197 */    for (Method m : a.annotationType().getDeclaredMethods()) {
/* 198:198 */      if (m.getParameterTypes().length <= 0)
/* 199:    */      {
/* 200:    */        try
/* 201:    */        {
/* 202:202 */          builder.append(m.getName(), m.invoke(a, new Object[0]));
/* 203:    */        } catch (RuntimeException ex) {
/* 204:204 */          throw ex;
/* 205:    */        } catch (Exception ex) {
/* 206:206 */          throw new RuntimeException(ex);
/* 207:    */        } }
/* 208:    */    }
/* 209:209 */    return builder.build();
/* 210:    */  }
/* 211:    */  
/* 222:    */  public static boolean isValidAnnotationMemberType(Class<?> type)
/* 223:    */  {
/* 224:224 */    if (type == null) {
/* 225:225 */      return false;
/* 226:    */    }
/* 227:227 */    if (type.isArray()) {
/* 228:228 */      type = type.getComponentType();
/* 229:    */    }
/* 230:230 */    return (type.isPrimitive()) || (type.isEnum()) || (type.isAnnotation()) || (String.class.equals(type)) || (Class.class.equals(type));
/* 231:    */  }
/* 232:    */  
/* 241:    */  private static int hashMember(String name, Object value)
/* 242:    */  {
/* 243:243 */    int part1 = name.hashCode() * 127;
/* 244:244 */    if (value.getClass().isArray()) {
/* 245:245 */      return part1 ^ arrayMemberHash(value.getClass().getComponentType(), value);
/* 246:    */    }
/* 247:247 */    if ((value instanceof Annotation)) {
/* 248:248 */      return part1 ^ hashCode((Annotation)value);
/* 249:    */    }
/* 250:250 */    return part1 ^ value.hashCode();
/* 251:    */  }
/* 252:    */  
/* 262:    */  private static boolean memberEquals(Class<?> type, Object o1, Object o2)
/* 263:    */  {
/* 264:264 */    if (o1 == o2) {
/* 265:265 */      return true;
/* 266:    */    }
/* 267:267 */    if ((o1 == null) || (o2 == null)) {
/* 268:268 */      return false;
/* 269:    */    }
/* 270:270 */    if (type.isArray()) {
/* 271:271 */      return arrayMemberEquals(type.getComponentType(), o1, o2);
/* 272:    */    }
/* 273:273 */    if (type.isAnnotation()) {
/* 274:274 */      return equals((Annotation)o1, (Annotation)o2);
/* 275:    */    }
/* 276:276 */    return o1.equals(o2);
/* 277:    */  }
/* 278:    */  
/* 286:    */  private static boolean arrayMemberEquals(Class<?> componentType, Object o1, Object o2)
/* 287:    */  {
/* 288:288 */    if (componentType.isAnnotation()) {
/* 289:289 */      return annotationArrayMemberEquals((Annotation[])o1, (Annotation[])o2);
/* 290:    */    }
/* 291:291 */    if (componentType.equals(Byte.TYPE)) {
/* 292:292 */      return Arrays.equals((byte[])o1, (byte[])o2);
/* 293:    */    }
/* 294:294 */    if (componentType.equals(Short.TYPE)) {
/* 295:295 */      return Arrays.equals((short[])o1, (short[])o2);
/* 296:    */    }
/* 297:297 */    if (componentType.equals(Integer.TYPE)) {
/* 298:298 */      return Arrays.equals((int[])o1, (int[])o2);
/* 299:    */    }
/* 300:300 */    if (componentType.equals(Character.TYPE)) {
/* 301:301 */      return Arrays.equals((char[])o1, (char[])o2);
/* 302:    */    }
/* 303:303 */    if (componentType.equals(Long.TYPE)) {
/* 304:304 */      return Arrays.equals((long[])o1, (long[])o2);
/* 305:    */    }
/* 306:306 */    if (componentType.equals(Float.TYPE)) {
/* 307:307 */      return Arrays.equals((float[])o1, (float[])o2);
/* 308:    */    }
/* 309:309 */    if (componentType.equals(Double.TYPE)) {
/* 310:310 */      return Arrays.equals((double[])o1, (double[])o2);
/* 311:    */    }
/* 312:312 */    if (componentType.equals(Boolean.TYPE)) {
/* 313:313 */      return Arrays.equals((boolean[])o1, (boolean[])o2);
/* 314:    */    }
/* 315:315 */    return Arrays.equals((Object[])o1, (Object[])o2);
/* 316:    */  }
/* 317:    */  
/* 324:    */  private static boolean annotationArrayMemberEquals(Annotation[] a1, Annotation[] a2)
/* 325:    */  {
/* 326:326 */    if (a1.length != a2.length) {
/* 327:327 */      return false;
/* 328:    */    }
/* 329:329 */    for (int i = 0; i < a1.length; i++) {
/* 330:330 */      if (!equals(a1[i], a2[i])) {
/* 331:331 */        return false;
/* 332:    */      }
/* 333:    */    }
/* 334:334 */    return true;
/* 335:    */  }
/* 336:    */  
/* 343:    */  private static int arrayMemberHash(Class<?> componentType, Object o)
/* 344:    */  {
/* 345:345 */    if (componentType.equals(Byte.TYPE)) {
/* 346:346 */      return Arrays.hashCode((byte[])o);
/* 347:    */    }
/* 348:348 */    if (componentType.equals(Short.TYPE)) {
/* 349:349 */      return Arrays.hashCode((short[])o);
/* 350:    */    }
/* 351:351 */    if (componentType.equals(Integer.TYPE)) {
/* 352:352 */      return Arrays.hashCode((int[])o);
/* 353:    */    }
/* 354:354 */    if (componentType.equals(Character.TYPE)) {
/* 355:355 */      return Arrays.hashCode((char[])o);
/* 356:    */    }
/* 357:357 */    if (componentType.equals(Long.TYPE)) {
/* 358:358 */      return Arrays.hashCode((long[])o);
/* 359:    */    }
/* 360:360 */    if (componentType.equals(Float.TYPE)) {
/* 361:361 */      return Arrays.hashCode((float[])o);
/* 362:    */    }
/* 363:363 */    if (componentType.equals(Double.TYPE)) {
/* 364:364 */      return Arrays.hashCode((double[])o);
/* 365:    */    }
/* 366:366 */    if (componentType.equals(Boolean.TYPE)) {
/* 367:367 */      return Arrays.hashCode((boolean[])o);
/* 368:    */    }
/* 369:369 */    return Arrays.hashCode((Object[])o);
/* 370:    */  }
/* 371:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.AnnotationUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */