package org.jasypt.registry;

import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class AlgorithmRegistry
{
  public static Set getAllDigestAlgorithms()
  {
    List algos = new ArrayList(Security.getAlgorithms("MessageDigest"));
    Collections.sort(algos);
    return Collections.unmodifiableSet(new LinkedHashSet(algos));
  }
  
  public static Set getAllPBEAlgorithms()
  {
    List algos = new ArrayList(Security.getAlgorithms("Cipher"));
    Collections.sort(algos);
    LinkedHashSet pbeAlgos = new LinkedHashSet();
    Iterator algosIter = algos.iterator();
    while (algosIter.hasNext())
    {
      String algo = (String)algosIter.next();
      if ((algo != null) && (algo.startsWith("PBE"))) {
        pbeAlgos.add(algo);
      }
    }
    return Collections.unmodifiableSet(pbeAlgos);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.registry.AlgorithmRegistry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */