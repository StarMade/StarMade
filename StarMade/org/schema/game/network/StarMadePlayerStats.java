/*   1:    */package org.schema.game.network;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import java.io.BufferedInputStream;
/*   5:    */import java.io.File;
/*   6:    */import java.io.FileInputStream;
/*   7:    */import java.io.FileNotFoundException;
/*   8:    */import java.io.IOException;
/*   9:    */import java.util.ArrayList;
/*  10:    */import java.util.Arrays;
/*  11:    */import java.util.HashSet;
/*  12:    */import lE;
/*  13:    */import vg;
/*  14:    */
/*  20:    */public class StarMadePlayerStats
/*  21:    */{
/*  22:    */  public static final int ONLINE_ONLY = 1;
/*  23:    */  public static final int INFO_PLAYER_DETAILS = 4;
/*  24:    */  public static final int INFO_LAST_LOGIN = 8;
/*  25:    */  public static final int INFO_LOGGED_IP = 16;
/*  26: 26 */  private static int paramSize = 4;
/*  27:    */  
/*  28:    */  public ReceivedPlayer[] receivedPlayers;
/*  29:    */  
/*  30:    */  public static StarMadePlayerStats decode(Object[] paramArrayOfObject, int paramInt)
/*  31:    */  {
/*  32: 32 */    int i = paramArrayOfObject.length / paramSize;
/*  33:    */    
/*  34:    */    StarMadePlayerStats localStarMadePlayerStats;
/*  35:    */    
/*  36: 36 */    (localStarMadePlayerStats = new StarMadePlayerStats()).receivedPlayers = new ReceivedPlayer[i];
/*  37:    */    
/*  38: 38 */    for (int j = 0; j < i; j++) {
/*  39: 39 */      localStarMadePlayerStats.receivedPlayers[j] = new ReceivedPlayer();
/*  40: 40 */      localStarMadePlayerStats.receivedPlayers[j].decode(paramArrayOfObject, j * paramSize, paramInt);
/*  41:    */    }
/*  42:    */    
/*  44: 44 */    return localStarMadePlayerStats;
/*  45:    */  }
/*  46:    */  
/*  49:    */  public static Object[] encode(vg paramvg, int paramInt)
/*  50:    */  {
/*  51: 51 */    paramInt = new File(vg.a).listFiles(new StarMadePlayerStats.1());
/*  52:    */    
/*  57: 57 */    ArrayList localArrayList1 = new ArrayList();
/*  58: 58 */    Object localObject; for (int i = 0; i < paramInt.length; i++) {
/*  59:    */      try {
/*  60: 60 */        localObject = Ah.a(new BufferedInputStream(new FileInputStream(paramInt[i])), true);
/*  61:    */        lE locallE;
/*  62: 62 */        (locallE = new lE(paramvg)).initialize();
/*  63: 63 */        locallE.fromTagStructure((Ah)localObject);
/*  64: 64 */        localObject = paramInt[i].getName();
/*  65: 65 */        locallE.a(((String)localObject).substring(19, ((String)localObject).lastIndexOf(".")));
/*  66: 66 */        localArrayList1.add(locallE);
/*  67: 67 */      } catch (FileNotFoundException localFileNotFoundException) { localObject = null;
/*  68:    */        
/*  71: 71 */        localFileNotFoundException.printStackTrace();
/*  72:    */      } catch (IOException localIOException) {
/*  73: 69 */        localObject = null;
/*  74:    */        
/*  75: 71 */        localIOException.printStackTrace();
/*  76:    */      }
/*  77:    */    }
/*  78:    */    
/*  81: 75 */    Object[] arrayOfObject = new Object[localArrayList1.size() * paramSize];
/*  82:    */    
/*  83: 77 */    for (int j = 0; j < localArrayList1.size(); 
/*  84: 78 */        j++)
/*  85:    */    {
/*  86: 80 */      localObject = (lE)localArrayList1.get(j);
/*  87: 81 */      paramvg = j * paramSize;
/*  88:    */      
/*  89: 83 */      paramInt = new StringBuilder();
/*  90:    */      ArrayList localArrayList2;
/*  91: 85 */      (localArrayList2 = new ArrayList()).addAll(((lE)localObject).a());
/*  92: 86 */      for (int k = 0; k < localArrayList2.size(); k++) {
/*  93: 87 */        paramInt.append((String)localArrayList2.get(k));
/*  94: 88 */        if (k < ((lE)localObject).a().size() - 1) {
/*  95: 89 */          paramInt.append(",");
/*  96:    */        }
/*  97:    */      }
/*  98:    */      
/*  99: 93 */      arrayOfObject[paramvg] = ((lE)localObject).getName();
/* 100: 94 */      arrayOfObject[(paramvg + 1)] = Long.valueOf(((lE)localObject).b());
/* 101: 95 */      arrayOfObject[(paramvg + 2)] = Long.valueOf(((lE)localObject).c());
/* 102: 96 */      arrayOfObject[(paramvg + 3)] = paramInt.toString();
/* 103:    */    }
/* 104:    */    
/* 105: 99 */    for (j = 0; j < arrayOfObject.length; j++) {
/* 106:100 */      assert (arrayOfObject[j] != null) : Arrays.toString(arrayOfObject);
/* 107:    */    }
/* 108:102 */    return arrayOfObject;
/* 109:    */  }
/* 110:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.StarMadePlayerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */