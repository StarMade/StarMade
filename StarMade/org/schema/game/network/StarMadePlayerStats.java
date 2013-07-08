package org.schema.game.network;

import class_1041;
import class_69;
import class_748;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StarMadePlayerStats
{
  public static final int ONLINE_ONLY = 1;
  public static final int INFO_PLAYER_DETAILS = 4;
  public static final int INFO_LAST_LOGIN = 8;
  public static final int INFO_LOGGED_IP = 16;
  private static int paramSize = 4;
  public ReceivedPlayer[] receivedPlayers;
  
  public static StarMadePlayerStats decode(Object[] paramArrayOfObject, int paramInt)
  {
    int i = paramArrayOfObject.length / paramSize;
    StarMadePlayerStats localStarMadePlayerStats;
    (localStarMadePlayerStats = new StarMadePlayerStats()).receivedPlayers = new ReceivedPlayer[i];
    for (int j = 0; j < i; j++)
    {
      localStarMadePlayerStats.receivedPlayers[j] = new ReceivedPlayer();
      localStarMadePlayerStats.receivedPlayers[j].decode(paramArrayOfObject, j * paramSize, paramInt);
    }
    return localStarMadePlayerStats;
  }
  
  public static Object[] encode(class_1041 paramclass_1041, int paramInt)
  {
    paramInt = new File(class_1041.field_144).listFiles(new StarMadePlayerStats.1());
    ArrayList localArrayList1 = new ArrayList();
    Object localObject;
    for (int i = 0; i < paramInt.length; i++) {
      try
      {
        localObject = class_69.a10(new BufferedInputStream(new FileInputStream(paramInt[i])), true);
        class_748 localclass_748;
        (localclass_748 = new class_748(paramclass_1041)).initialize();
        localclass_748.fromTagStructure((class_69)localObject);
        localObject = paramInt[i].getName();
        localclass_748.a106(((String)localObject).substring(19, ((String)localObject).lastIndexOf(".")));
        localArrayList1.add(localclass_748);
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localObject = null;
        localFileNotFoundException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        localObject = null;
        localIOException.printStackTrace();
      }
    }
    Object[] arrayOfObject = new Object[localArrayList1.size() * paramSize];
    for (int j = 0; j < localArrayList1.size(); j++)
    {
      localObject = (class_748)localArrayList1.get(j);
      paramclass_1041 = j * paramSize;
      paramInt = new StringBuilder();
      ArrayList localArrayList2;
      (localArrayList2 = new ArrayList()).addAll(((class_748)localObject).a143());
      for (int k = 0; k < localArrayList2.size(); k++)
      {
        paramInt.append((String)localArrayList2.get(k));
        if (k < ((class_748)localObject).a143().size() - 1) {
          paramInt.append(",");
        }
      }
      arrayOfObject[paramclass_1041] = ((class_748)localObject).getName();
      arrayOfObject[(paramclass_1041 + 1)] = Long.valueOf(((class_748)localObject).b14());
      arrayOfObject[(paramclass_1041 + 2)] = Long.valueOf(((class_748)localObject).c14());
      arrayOfObject[(paramclass_1041 + 3)] = paramInt.toString();
    }
    for (j = 0; j < arrayOfObject.length; j++) {
      assert (arrayOfObject[j] != null) : Arrays.toString(arrayOfObject);
    }
    return arrayOfObject;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.StarMadePlayerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */