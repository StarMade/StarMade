/*  1:   */package org.jasypt.registry;
/*  2:   */
/*  3:   */import java.security.Security;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.Collections;
/*  6:   */import java.util.Iterator;
/*  7:   */import java.util.LinkedHashSet;
/*  8:   */import java.util.List;
/*  9:   */import java.util.Set;
/* 10:   */
/* 57:   */public final class AlgorithmRegistry
/* 58:   */{
/* 59:   */  public static Set getAllDigestAlgorithms()
/* 60:   */  {
/* 61:61 */    List algos = new ArrayList(Security.getAlgorithms("MessageDigest"));
/* 62:62 */    Collections.sort(algos);
/* 63:63 */    return Collections.unmodifiableSet(new LinkedHashSet(algos));
/* 64:   */  }
/* 65:   */  
/* 79:   */  public static Set getAllPBEAlgorithms()
/* 80:   */  {
/* 81:81 */    List algos = new ArrayList(Security.getAlgorithms("Cipher"));
/* 82:82 */    Collections.sort(algos);
/* 83:83 */    LinkedHashSet pbeAlgos = new LinkedHashSet();
/* 84:84 */    Iterator algosIter = algos.iterator();
/* 85:85 */    while (algosIter.hasNext()) {
/* 86:86 */      String algo = (String)algosIter.next();
/* 87:87 */      if ((algo != null) && (algo.startsWith("PBE"))) {
/* 88:88 */        pbeAlgos.add(algo);
/* 89:   */      }
/* 90:   */    }
/* 91:91 */    return Collections.unmodifiableSet(pbeAlgos);
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.registry.AlgorithmRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */