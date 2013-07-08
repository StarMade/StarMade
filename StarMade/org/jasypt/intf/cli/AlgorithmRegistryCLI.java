package org.jasypt.intf.cli;

import java.io.PrintStream;
import java.util.Set;
import org.jasypt.registry.AlgorithmRegistry;

public final class AlgorithmRegistryCLI
{
  public static void main(String[] args)
  {
    try
    {
      Set digestAlgos = AlgorithmRegistry.getAllDigestAlgorithms();
      Set pbeAlgos = AlgorithmRegistry.getAllPBEAlgorithms();
      System.out.println();
      System.out.println("DIGEST ALGORITHMS:   " + digestAlgos);
      System.out.println();
      System.out.println("PBE ALGORITHMS:      " + pbeAlgos);
      System.out.println();
    }
    catch (Throwable digestAlgos)
    {
      digestAlgos.printStackTrace(System.err);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.intf.cli.AlgorithmRegistryCLI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */