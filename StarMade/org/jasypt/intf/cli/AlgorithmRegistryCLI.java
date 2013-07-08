/*  1:   */package org.jasypt.intf.cli;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.Set;
/*  5:   */import org.jasypt.registry.AlgorithmRegistry;
/*  6:   */
/* 49:   */public final class AlgorithmRegistryCLI
/* 50:   */{
/* 51:   */  public static void main(String[] args)
/* 52:   */  {
/* 53:   */    try
/* 54:   */    {
/* 55:55 */      Set digestAlgos = AlgorithmRegistry.getAllDigestAlgorithms();
/* 56:56 */      Set pbeAlgos = AlgorithmRegistry.getAllPBEAlgorithms();
/* 57:   */      
/* 58:58 */      System.out.println();
/* 59:59 */      System.out.println("DIGEST ALGORITHMS:   " + digestAlgos);
/* 60:60 */      System.out.println();
/* 61:61 */      System.out.println("PBE ALGORITHMS:      " + pbeAlgos);
/* 62:62 */      System.out.println();
/* 63:   */    }
/* 64:   */    catch (Throwable t) {
/* 65:65 */      t.printStackTrace(System.err);
/* 66:   */    }
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.intf.cli.AlgorithmRegistryCLI
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */