/*   1:    */package org.schema.game.common.staremote;
/*   2:    */
/*   3:    */import ct;
/*   4:    */import java.awt.EventQueue;
/*   5:    */import java.io.IOException;
/*   6:    */import jb;
/*   7:    */import org.schema.game.common.Starter;
/*   8:    */import org.schema.game.network.StarMadeServerStats;
/*   9:    */import pI;
/*  10:    */import pK;
/*  11:    */import pM;
/*  12:    */import qe;
/*  13:    */import qy;
/*  14:    */import rh;
/*  15:    */import sG;
/*  16:    */import x;
/*  17:    */import xu;
/*  18:    */
/*  25:    */public class Staremote
/*  26:    */{
/*  27:    */  private ct jdField_a_of_type_Ct;
/*  28:    */  private pM jdField_a_of_type_PM;
/*  29:    */  private rh jdField_a_of_type_Rh;
/*  30:    */  public static qy a;
/*  31:    */  
/*  32:    */  public static void main(String[] paramArrayOfString)
/*  33:    */  {
/*  34: 34 */    sG.a();
/*  35:    */    
/*  37: 37 */    xu.a();
/*  38:    */    try
/*  39:    */    {
/*  40: 40 */      Starter.d();
/*  41: 41 */    } catch (SecurityException localSecurityException) { 
/*  42:    */      
/*  45: 45 */        localSecurityException;
/*  46:    */    } catch (IOException localIOException) {
/*  47: 43 */      
/*  48:    */      
/*  49: 45 */        localIOException;
/*  50:    */    }
/*  51:    */    
/*  55: 49 */    new Staremote().a();
/*  56:    */  }
/*  57:    */  
/*  58:    */  public final void a() {
/*  59: 53 */    EventQueue.invokeLater(new pI(this));
/*  60:    */  }
/*  61:    */  
/*  90:    */  public final void a(ct paramct)
/*  91:    */  {
/*  92: 86 */    if (jdField_a_of_type_Qy != null) {
/*  93: 87 */      jdField_a_of_type_Qy.b();
/*  94:    */    }
/*  95:    */    try
/*  96:    */    {
/*  97: 91 */      StarMadeServerStats localStarMadeServerStats = paramct.a().a();
/*  98: 92 */      this.jdField_a_of_type_PM.a(localStarMadeServerStats);
/*  99:    */      
/* 100: 94 */      if (this.jdField_a_of_type_Rh != null) {
/* 101: 95 */        paramct = paramct.a().a();
/* 102: 96 */        this.jdField_a_of_type_Rh.a(paramct);
/* 103: 97 */        this.jdField_a_of_type_Rh = null;
/* 104:    */      }
/* 105:    */      return; } catch (IOException localIOException) { 
/* 106:    */      
/* 109:103 */        localIOException.printStackTrace(); return;
/* 110:    */    } catch (InterruptedException localInterruptedException) {
/* 111:101 */      
/* 112:    */      
/* 113:103 */        localInterruptedException;
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public final void a(qe paramqe)
/* 118:    */  {
/* 119:107 */    paramqe = new jb(paramqe.b, paramqe.jdField_a_of_type_Int, paramqe.jdField_a_of_type_JavaLangString);
/* 120:108 */    new Thread(new pK(this, paramqe))
/* 121:    */    
/* 236:224 */      .start();
/* 237:    */  }
/* 238:    */  
/* 244:    */  public static String a()
/* 245:    */  {
/* 246:234 */    return "./.starmotecon";
/* 247:    */  }
/* 248:    */  
/* 249:    */  public final void b() {
/* 250:238 */    Staremote localStaremote = this; try { localStaremote.jdField_a_of_type_Ct.disconnect(); } catch (Exception localException) { localException;
/* 251:    */    }
/* 252:240 */    System.exit(0);
/* 253:    */  }
/* 254:    */  
/* 255:243 */  public final void a(rh paramrh) { this.jdField_a_of_type_Rh = paramrh; }
/* 256:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.staremote.Staremote
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */