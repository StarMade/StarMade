import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.database.DatabaseIndex;
import org.schema.game.common.data.world.Universe;
import org.schema.game.common.updater.FileUtil;
import org.schema.game.server.controller.GameServerController;

public class class_1194
{
  public static synchronized void a(String paramString, class_48 paramclass_48, class_1041 paramclass_1041)
  {
    try
    {
      if (!paramString.endsWith(".smsec")) {
        paramString = paramString + ".smsec";
      }
      if (!(localObject1 = new File("./sector-export/")).exists()) {
        ((File)localObject1).mkdir();
      }
      if (!(localObject1 = new File("./sector-export/tmp/")).exists()) {
        ((File)localObject1).mkdir();
      }
      FileUtil.a3(new File("./sector-export/" + paramString), "./sector-export/tmp/");
      paramString = new File("./sector-export/tmp/sector/sector.cfg");
      paramString = new BufferedReader(new FileReader(paramString));
      localObject1 = new class_48();
      class_48 localclass_481 = 0;
      class_48 localclass_482 = 0;
      while ((localObject2 = paramString.readLine()) != null) {
        try
        {
          if (((String)localObject2).toLowerCase(Locale.ENGLISH).contains("pos"))
          {
            localObject2 = localObject2.split("=", 2)[1].split(",");
            ((class_48)localObject1).field_475 = Integer.parseInt(localObject2[0].trim());
            ((class_48)localObject1).field_476 = Integer.parseInt(localObject2[1].trim());
            ((class_48)localObject1).field_477 = Integer.parseInt(localObject2[2].trim());
          }
          else if (((String)localObject2).toLowerCase(Locale.ENGLISH).contains("subtype"))
          {
            localclass_482 = Integer.parseInt(localObject2.split("=", 2)[1].trim());
          }
          else if (((String)localObject2).toLowerCase(Locale.ENGLISH).contains("type"))
          {
            localclass_481 = Integer.parseInt(localObject2.split("=", 2)[1].trim());
          }
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          throw new IOException("Invalid sector.cfg format in file");
        }
      }
      paramString.close();
      if (paramclass_48 != null) {
        ((class_48)localObject1).b1(paramclass_48);
      }
      if (paramclass_1041.a62().isSectorLoaded((class_48)localObject1)) {
        throw new IOException("cannot import while target sector " + localObject1 + " is loaded, please get all players away from this sector");
      }
      System.err.println("[IMPORTSECTOR] PARSED SECTOR FILE: Pos " + localObject1 + "; Type " + localclass_481 + "; SubType: " + localclass_482);
      a2((class_48)localObject1, paramclass_1041);
      Object localObject2 = paramclass_1041;
      localclass_481 = localclass_482;
      paramclass_48 = localclass_481;
      paramString = (String)localObject1;
      Object localObject3 = ((class_1041)localObject2).a62().getStellarSystemFromSecPos(paramString);
      paramclass_48 = class_808.values()[paramclass_48];
      int i = ((class_791)localObject3).a41(paramString.field_475);
      int k = ((class_791)localObject3).a41(paramString.field_476);
      paramString = ((class_791)localObject3).a41(paramString.field_477);
      paramString = class_791.a202(i, k, paramString);
      ((class_791)localObject3).a199(paramString, paramclass_48);
      System.err.println("[IMPORT] SECTOR TYPE: " + paramclass_48);
      if (paramclass_48 == class_808.field_1067)
      {
        System.err.println("[IMPORT] setting planet type to " + class_810.values()[localclass_481]);
        ((class_791)localObject3).a200(paramString, class_810.values()[localclass_481]);
      }
      else if (paramclass_48 == class_808.field_1065)
      {
        ((class_791)localObject3).a201(paramString, class_780.values()[localclass_481]);
      }
      ((class_1041)localObject2).a66().a23((class_791)localObject3, true);
      paramclass_1041.a66().a27((class_48)localObject1, new HashMap(), localclass_482);
      if ((localObject2 = new File("./sector-export/tmp/sector/ENTITIES/")).exists())
      {
        localObject3 = new File("./sector-export/tmp/sector/DATA/");
        localObject2 = ((File)localObject2).listFiles();
        paramString = 0;
        localclass_481 = (paramclass_48 = localObject2).length;
        for (localclass_482 = 0; localclass_482 < localclass_481; localclass_482++)
        {
          Object localObject4;
          if ((localObject2 = paramclass_48[localclass_482]).getName().endsWith(".ent"))
          {
            String str1 = a1((File)localObject2, (class_48)localObject1, paramclass_1041);
            paramclass_1041.a66().a20((File)localObject2);
            String[] arrayOfString = ((File)localObject2).getName().split("_", 3);
            localObject4 = arrayOfString[0] + "_" + arrayOfString[1] + "_";
            String str3;
            if (!str1.startsWith((String)localObject4)) {
              str3 = (String)localObject4 + str1;
            } else {
              str3 = str1;
            }
            System.err.println("[IMPORTSECTOR] FULL ID " + str3 + " (" + (String)localObject4 + " + " + str1 + ")");
            File localFile2 = new File(class_1041.field_144 + str3 + ".ent");
            FileUtil.b((File)localObject2, localFile2);
            String str4 = ((File)localObject2).getName().substring(0, ((File)localObject2).getName().length() - 4);
            System.err.println("[IMPORTSECTOR] pattern for " + ((File)localObject2).getName() + " -> " + str4);
            localObject2 = ((File)localObject3).listFiles(new class_1200(str4));
            if ((!field_1401) && (localObject2.length <= 0)) {
              throw new AssertionError("Pattern: " + str4);
            }
            for (localObject4 : localObject2)
            {
              File localFile1 = new File(class_1041.field_149 + ((File)localObject4).getName().replaceAll(str4, str3));
              System.err.println("[IMPORTSECTOR] copy " + ((File)localObject4).getAbsolutePath() + " " + localFile1.getAbsolutePath());
              FileUtil.b((File)localObject4, localFile1);
            }
          }
          else if (((File)localObject2).getName().endsWith(".erock"))
          {
            try
            {
              BufferedReader localBufferedReader = new BufferedReader(new FileReader((File)localObject2));
              localObject4 = new Vector3f();
              int n = 0;
              long l = 0L;
              String str2;
              while ((str2 = localBufferedReader.readLine()) != null)
              {
                if (str2.toLowerCase(Locale.ENGLISH).contains("genid")) {
                  n = Integer.parseInt(str2.split("=", 2)[1].trim());
                }
                if (str2.toLowerCase(Locale.ENGLISH).contains("seed")) {
                  l = Long.parseLong(str2.split("=", 2)[1].trim());
                }
                if (str2.toLowerCase(Locale.ENGLISH).contains("pos"))
                {
                  localObject2 = str2.split("=", 2)[1].split(",");
                  ((Vector3f)localObject4).field_615 = Float.parseFloat(localObject2[0].trim());
                  ((Vector3f)localObject4).field_616 = Float.parseFloat(localObject2[1].trim());
                  ((Vector3f)localObject4).field_617 = Float.parseFloat(localObject2[2].trim());
                }
              }
              localBufferedReader.close();
              paramclass_1041.a66().a18("ENTITY_FLOATINGROCK_" + System.currentTimeMillis() + "_" + paramString, (class_48)localObject1, 3, l, "", "", "undef", 0, (Vector3f)localObject4, new class_48(-2, -2, -2), new class_48(2, 2, 2), n);
              paramString++;
            }
            catch (Exception localException2)
            {
              localException2;
            }
          }
        }
      }
      FileUtil.a2(new File("./sector-export/tmp/"));
      paramclass_1041.a59().a();
      return;
    }
    catch (Exception localException3)
    {
      Object localObject1;
      (localObject1 = localException3).printStackTrace();
      throw new IOException(localObject1.getClass().getSimpleName() + ": " + ((Exception)localObject1).getMessage());
    }
  }
  
  private static String a1(File paramFile, class_48 paramclass_48, class_1041 paramclass_1041)
  {
    if ((paramFile.getName().startsWith("ENTITY_SHOP")) || (paramFile.getName().startsWith("ENTITY_SPACESTATION")) || (paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) || (paramFile.getName().startsWith("ENTITY_PLANET")) || (paramFile.getName().startsWith("ENTITY_SHIP")))
    {
      Object localObject;
      class_69 localclass_69 = class_69.a10(localObject = new DataInputStream(new FileInputStream(paramFile)), true);
      ((DataInputStream)localObject).close();
      if (paramFile.getName().startsWith("ENTITY_SHOP")) {
        localObject = ((class_69[])localclass_69.a4())[0];
      } else if (paramFile.getName().startsWith("ENTITY_SPACESTATION")) {
        localObject = ((class_69[])localclass_69.a4())[1];
      } else if (paramFile.getName().startsWith("ENTITY_PLANET")) {
        localObject = ((class_69[])localclass_69.a4())[1];
      } else {
        localObject = localclass_69;
      }
      if ((!paramFile.getName().startsWith("ENTITY_SHOP")) && (!paramFile.getName().startsWith("ENTITY_SPACESTATION")) && (!paramFile.getName().startsWith("ENTITY_FLOATINGROCK")) && (!paramFile.getName().startsWith("ENTITY_PLANET"))) {
        paramFile.getName().startsWith("ENTITY_SHIP");
      }
      System.err.println("PARSING: " + paramFile.getName());
      class_69[] arrayOfclass_69 = (class_69[])(localObject = (class_69[])((class_69)localObject).a4())[6].a4();
      String str1 = (String)localObject[0].a4();
      String str2 = new String(str1);
      for (int i = 0; paramclass_1041.a66().a6(str2.split("_", 3)[2], 1).size() > 0; i++) {
        str2 = new String(str1) + "_" + i;
      }
      str1 = new String(str2);
      localObject[0].a5(str1);
      arrayOfclass_69[3].a4();
      arrayOfclass_69[3].a5(new class_48(paramclass_48));
      paramFile = new BufferedOutputStream(new FileOutputStream(paramFile));
      localclass_69.a11(paramFile, true);
      return str1;
    }
    return null;
  }
  
  private static void a2(class_48 paramclass_48, class_1041 paramclass_1041)
  {
    Iterator localIterator = paramclass_1041.a66().a9(paramclass_48, -1).iterator();
    while (localIterator.hasNext())
    {
      class_757 localclass_757 = (class_757)localIterator.next();
      paramclass_1041.a66().b1(localclass_757.jdField_field_1017_of_type_JavaLangString);
    }
    paramclass_1041.a66().a29(paramclass_48);
  }
  
  public static synchronized void a3(class_48 paramclass_48, String paramString, class_1041 paramclass_1041)
  {
    class_670 localclass_670 = new class_670(paramclass_1041);
    paramclass_1041.a66().a28(paramclass_48, localclass_670);
    if (localclass_670.field_136 == null) {
      throw new IOException("Sector " + paramclass_48 + " not in DB (be sure it has been discovered, and use /force_save)");
    }
    paramclass_48 = paramclass_1041.a66().a9(paramclass_48, -1);
    if (!(paramclass_1041 = new File("./sector-export/")).exists()) {
      paramclass_1041.mkdir();
    }
    if (!(paramclass_1041 = new File("./sector-export/sector/")).exists()) {
      paramclass_1041.mkdir();
    }
    if (!(localObject1 = new File("./sector-export/sector/ENTITIES/")).exists()) {
      ((File)localObject1).mkdir();
    }
    if (!(localObject1 = new File("./sector-export/sector/DATA/")).exists()) {
      ((File)localObject1).mkdir();
    }
    paramclass_48 = paramclass_48.iterator();
    while (paramclass_48.hasNext())
    {
      localObject1 = (class_757)paramclass_48.next();
      Object localObject2 = new File(class_1041.field_144 + ((class_757)localObject1).jdField_field_1017_of_type_JavaLangString + ".ent");
      File localFile1 = new File("./sector-export/sector/ENTITIES/" + ((class_757)localObject1).jdField_field_1017_of_type_JavaLangString + ".ent");
      if ((!((File)localObject2).exists()) && (((class_757)localObject1).jdField_field_1017_of_type_JavaLangString.startsWith("ENTITY_FLOATINGROCK")))
      {
        localObject2 = new File("./sector-export/sector/ENTITIES/" + ((class_757)localObject1).jdField_field_1017_of_type_JavaLangString + ".erock");
        (localObject2 = new FileWriter((File)localObject2)).append("name = " + ((class_757)localObject1).jdField_field_1017_of_type_JavaLangString + "\n");
        ((FileWriter)localObject2).append("seed = " + ((class_757)localObject1).jdField_field_1017_of_type_Long + "\n");
        ((FileWriter)localObject2).append("pos = " + ((class_757)localObject1).jdField_field_1017_of_type_JavaxVecmathVector3f.field_615 + ", " + ((class_757)localObject1).jdField_field_1017_of_type_JavaxVecmathVector3f.field_616 + ", " + ((class_757)localObject1).jdField_field_1017_of_type_JavaxVecmathVector3f.field_617 + "\n");
        ((FileWriter)localObject2).append("genid = " + ((class_757)localObject1).field_1019 + "\n");
        ((FileWriter)localObject2).flush();
        ((FileWriter)localObject2).close();
      }
      else
      {
        FileUtil.b((File)localObject2, localFile1);
        for (File localFile2 : new File(class_1041.field_149).listFiles(new class_1198((class_757)localObject1)))
        {
          File localFile3 = new File("./sector-export/sector/DATA/" + localFile2.getName());
          FileUtil.b(localFile2, localFile3);
        }
      }
    }
    paramclass_48 = new File("./sector-export/sector/sector.cfg");
    Object localObject1 = new BufferedWriter(new FileWriter(paramclass_48));
    if ((!field_1401) && (localclass_670.field_136 == null)) {
      throw new AssertionError();
    }
    ((BufferedWriter)localObject1).append("pos = " + localclass_670.field_136.field_475 + ", " + localclass_670.field_136.field_476 + ", " + localclass_670.field_136.field_477 + "\n");
    class_808 localclass_808 = localclass_670.a68();
    ((BufferedWriter)localObject1).append("type = " + localclass_808.ordinal() + "\n");
    if (localclass_808 == class_808.field_1067) {
      ((BufferedWriter)localObject1).append("subtype = " + localclass_670.a69().ordinal() + "\n");
    } else if (localclass_808 == class_808.field_1065) {
      ((BufferedWriter)localObject1).append("subtype = " + localclass_670.a70().ordinal() + "\n");
    } else {
      ((BufferedWriter)localObject1).append("subtype = 0\n");
    }
    ((BufferedWriter)localObject1).flush();
    ((BufferedWriter)localObject1).close();
    String str = "./sector-export/" + paramString + ".smsec";
    class_29.a11("./sector-export/sector/", str, null);
    class_1182.a(paramclass_1041);
  }
  
  public static void a4(class_1041 paramclass_1041, String paramString, boolean paramBoolean)
  {
    if ((paramString = new File("./" + paramString)).exists())
    {
      paramString = new BufferedReader(new FileReader(paramString));
      Object localObject1;
      while ((localObject1 = paramString.readLine()) != null)
      {
        int i;
        if ((i = ((String)localObject1).indexOf("//")) >= 0) {
          localObject1 = ((String)localObject1).substring(0, i);
        }
        if (((String)localObject1).contains(">>")) {
          if ((localObject1 = ((String)localObject1).split(">>", 3)).length == 3)
          {
            Object localObject2 = localObject1[0].trim();
            String str = localObject1[1].trim();
            localObject1[2].trim();
            localObject1 = ((String)localObject2).split(",");
            localObject2 = ((String)localObject2).split(",");
            if (localObject1.length == 3)
            {
              if (localObject2.length == 3)
              {
                localObject1 = new class_48(Integer.parseInt(localObject1[0].trim()), Integer.parseInt(localObject1[1].trim()), Integer.parseInt(localObject1[2].trim()));
                localObject2 = new class_48(Integer.parseInt(localObject2[0].trim()), Integer.parseInt(localObject2[1].trim()), Integer.parseInt(localObject2[2].trim()));
                if (paramBoolean) {
                  a3((class_48)localObject1, str, paramclass_1041);
                } else {
                  a(str, (class_48)localObject2, paramclass_1041);
                }
              }
              else
              {
                throw new IllegalArgumentException("Destination is not a coordinate");
              }
            }
            else {
              throw new IllegalArgumentException("Source is not a coordinate");
            }
          }
          else
          {
            throw new IllegalArgumentException("Illegal format (must be: from >> fileName >> to)");
          }
        }
      }
      paramString.close();
      return;
    }
    throw new FileNotFoundException(paramString.getAbsolutePath());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1194
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */