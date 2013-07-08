package org.schema.game.server.data.blueprintnw;

import class_1041;
import class_1055;
import class_1074;
import class_1082;
import class_1084;
import class_1094;
import class_1096;
import class_1216;
import class_29;
import class_371;
import class_48;
import class_69;
import class_703;
import class_707;
import class_720;
import class_748;
import class_796;
import class_798;
import class_988;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.updater.FileUtil;

public class BlueprintEntry
  implements class_1074
{
  private class_988 jdField_field_235_of_type_Class_988;
  private String jdField_field_235_of_type_JavaLangString;
  private class_1084 jdField_field_235_of_type_Class_1084;
  private int jdField_field_235_of_type_Int;
  private class_703 jdField_field_235_of_type_Class_703;
  private File jdField_field_235_of_type_JavaIoFile;
  private File jdField_field_236_of_type_JavaIoFile;
  private File field_237;
  private class_69 jdField_field_235_of_type_Class_69;
  private File field_391;
  private final class_1216 jdField_field_235_of_type_Class_1216;
  private ControlElementMapper jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
  private BlueprintEntry[] jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
  private BlueprintEntry[] jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
  private BlueprintEntry jdField_field_235_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry;
  private SegmentController jdField_field_235_of_type_OrgSchemaGameCommonControllerSegmentController;
  private class_48 jdField_field_235_of_type_Class_48;
  private Vector3f jdField_field_235_of_type_JavaxVecmathVector3f;
  private short jdField_field_235_of_type_Short;
  private byte jdField_field_235_of_type_Byte;
  
  public BlueprintEntry(String paramString)
  {
    this(paramString, class_1216.jdField_field_1429_of_type_Class_1216);
  }
  
  private BlueprintEntry(String paramString, class_1216 paramclass_1216)
  {
    this.jdField_field_235_of_type_Class_1216 = paramclass_1216;
    class_1216 localclass_1216 = paramclass_1216;
    paramclass_1216 = paramString;
    paramString = this;
    this.jdField_field_235_of_type_JavaLangString = paramclass_1216;
    paramString.jdField_field_235_of_type_JavaIoFile = new File(localclass_1216.jdField_field_1429_of_type_JavaLangString + "/" + paramclass_1216 + "/header.smbph");
    paramString.jdField_field_236_of_type_JavaIoFile = new File(localclass_1216.jdField_field_1429_of_type_JavaLangString + "/" + paramclass_1216 + "/logic.smbpl");
    paramString.field_237 = new File(localclass_1216.jdField_field_1429_of_type_JavaLangString + "/" + paramclass_1216 + "/meta.smbpm");
    paramString.field_391 = new File(localclass_1216.jdField_field_1429_of_type_JavaLangString + "/" + paramclass_1216 + "/DATA/");
  }
  
  public final File[] a5()
  {
    return this.field_391.listFiles();
  }
  
  public final void a6()
  {
    Object localObject = this.jdField_field_235_of_type_JavaIoFile;
    BlueprintEntry localBlueprintEntry = this;
    (localObject = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject)))).readInt();
    localBlueprintEntry.jdField_field_235_of_type_Class_1084 = class_1084.values()[localObject.readInt()];
    Vector3f localVector3f1 = new Vector3f(((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat());
    Vector3f localVector3f2 = new Vector3f(((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat(), ((DataInputStream)localObject).readFloat());
    localBlueprintEntry.jdField_field_235_of_type_Class_988 = new class_988(localVector3f1, localVector3f2);
    localBlueprintEntry.jdField_field_235_of_type_Class_703 = new class_703();
    localBlueprintEntry.jdField_field_235_of_type_Class_703.a5((DataInputStream)localObject);
    BlueprintEntry tmp122_121 = localBlueprintEntry;
    tmp122_121.jdField_field_235_of_type_Int = tmp122_121.jdField_field_235_of_type_Class_703.a3();
    ((DataInputStream)localObject).close();
    a11(this.field_237);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    ElementKeyMap.initializeData();
    paramArrayOfString = new BlueprintEntry("llllll");
    try
    {
      paramArrayOfString.a6();
      paramArrayOfString.a12(true);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public final void a7(class_1055 paramclass_1055, String paramString)
  {
    File localFile = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "Tmp/");
    this.jdField_field_235_of_type_JavaIoFile.getParentFile().renameTo(localFile);
    if (!this.jdField_field_235_of_type_JavaIoFile.getParentFile().exists()) {
      this.jdField_field_235_of_type_JavaIoFile.getParentFile().mkdirs();
    }
    try
    {
      b(paramclass_1055, paramString);
      paramString = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_field_235_of_type_JavaIoFile)));
      String str = paramString;
      Object localObject1 = paramclass_1055;
      str.writeInt(0);
      str.writeInt(((class_1055)localObject1).jdField_field_235_of_type_Int);
      Object localObject2 = new class_48(((class_1055)localObject1).jdField_field_235_of_type_Class_988.field_1273);
      class_48 localclass_48 = new class_48(((class_1055)localObject1).jdField_field_235_of_type_Class_988.field_1274);
      str.writeFloat(((class_48)localObject2).field_475);
      str.writeFloat(((class_48)localObject2).field_476);
      str.writeFloat(((class_48)localObject2).field_477);
      str.writeFloat(localclass_48.field_475);
      str.writeFloat(localclass_48.field_476);
      str.writeFloat(localclass_48.field_477);
      (localObject2 = new class_703()).a6(((class_1055)localObject1).jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap);
      ((class_703)localObject2).a4(str);
      paramString.close();
      paramString = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_field_236_of_type_JavaIoFile)));
      str = paramString;
      localObject1 = paramclass_1055;
      str.writeInt(0);
      ControlElementMap.serialize(str, ((class_1055)localObject1).a1());
      paramString.close();
      (localObject1 = paramclass_1055 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.field_237)))).writeInt(0);
      ((DataOutputStream)localObject1).writeByte(1);
      paramclass_1055.close();
      FileUtil.a2(localFile);
      return;
    }
    catch (Exception localException)
    {
      FileUtil.a2(this.jdField_field_235_of_type_JavaIoFile.getParentFile());
    }
  }
  
  private void b(class_1055 paramclass_1055, String paramString)
  {
    paramString = new File(paramString + "/DATA/");
    if ((!jdField_field_235_of_type_Boolean) && (!paramString.isDirectory())) {
      throw new AssertionError(paramString.getAbsolutePath());
    }
    int i = (paramString = paramString.listFiles(new class_1094(paramclass_1055))).length;
    for (int j = 0; j < i; j++)
    {
      Object localObject1;
      if ((localObject1 = paramString[j]).getName().startsWith(paramclass_1055.jdField_field_235_of_type_JavaLangString + "."))
      {
        if (!(localObject2 = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "/DATA/" + ((File)localObject1).getName())).getParentFile().exists()) {
          ((File)localObject2).getParentFile().mkdirs();
        }
        localObject1 = new FileInputStream((File)localObject1);
        Object localObject2 = new FileOutputStream((File)localObject2);
        localObject1 = ((FileInputStream)localObject1).getChannel();
        localObject2 = ((FileOutputStream)localObject2).getChannel();
        ByteBuffer localByteBuffer = class_748.field_136;
        for (;;)
        {
          localByteBuffer.clear();
          if (((FileChannel)localObject1).read(localByteBuffer) == -1) {
            break;
          }
          localByteBuffer.flip();
          ((FileChannel)localObject2).write(localByteBuffer);
        }
        ((FileChannel)localObject1).close();
        ((FileChannel)localObject2).close();
      }
    }
    paramString = this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "/DATA/";
    File localFile;
    File[] arrayOfFile;
    if (((localFile = new File(paramString)).exists()) && (localFile.isDirectory()) && ((arrayOfFile = localFile.listFiles()).length > 0) && (arrayOfFile[0].getName().endsWith(".smd")) && (!arrayOfFile[0].getName().endsWith(".smd2"))) {
      class_1216.b(paramString);
    }
  }
  
  public final void a8(SegmentController paramSegmentController, boolean paramBoolean)
  {
    File localFile = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "Tmp/");
    this.jdField_field_235_of_type_JavaIoFile.getParentFile().renameTo(localFile);
    if (!this.jdField_field_235_of_type_JavaIoFile.getParentFile().exists()) {
      this.jdField_field_235_of_type_JavaIoFile.getParentFile().mkdirs();
    }
    DataOutputStream localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_field_235_of_type_JavaIoFile)));
    DataOutputStream localDataOutputStream2 = localDataOutputStream1;
    SegmentController localSegmentController = paramSegmentController;
    localDataOutputStream2.writeInt(0);
    localDataOutputStream2.writeInt(class_1084.a(localSegmentController.getClass()).ordinal());
    class_48 localclass_481 = new class_48(localSegmentController.getBoundingBox().field_1273);
    class_48 localclass_482 = new class_48(localSegmentController.getBoundingBox().field_1274);
    localDataOutputStream2.writeFloat(localclass_481.field_475);
    localDataOutputStream2.writeFloat(localclass_481.field_476);
    localDataOutputStream2.writeFloat(localclass_481.field_477);
    localDataOutputStream2.writeFloat(localclass_482.field_475);
    localDataOutputStream2.writeFloat(localclass_482.field_476);
    localDataOutputStream2.writeFloat(localclass_482.field_477);
    localSegmentController.getElementClassCountMap().a4(localDataOutputStream2);
    localDataOutputStream1.close();
    localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.jdField_field_236_of_type_JavaIoFile)));
    localDataOutputStream2 = localDataOutputStream1;
    localSegmentController = paramSegmentController;
    localDataOutputStream2.writeInt(0);
    ControlElementMap.serialize(localDataOutputStream2, localSegmentController.getControlElementMap().getControllingMap());
    localDataOutputStream1.close();
    localDataOutputStream1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.field_237)));
    a10(paramSegmentController, localDataOutputStream1);
    localDataOutputStream1.close();
    if (paramBoolean) {
      b1(paramSegmentController);
    } else {
      a9(paramSegmentController);
    }
    if (this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length > 0) {
      for (paramSegmentController = 0; paramSegmentController < this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length; paramSegmentController++)
      {
        this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].a8(this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].jdField_field_235_of_type_OrgSchemaGameCommonControllerSegmentController, paramBoolean);
        this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[paramSegmentController].jdField_field_235_of_type_OrgSchemaGameCommonControllerSegmentController = null;
      }
    }
    this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = null;
    if (this.jdField_field_235_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null) {
      FileUtil.a2(localFile);
    }
  }
  
  private void a9(SegmentController paramSegmentController)
  {
    for (File localFile : new File(class_1041.field_149).listFiles(new class_1096(paramSegmentController)))
    {
      Object localObject;
      if (this.jdField_field_235_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null)
      {
        localObject = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "/DATA/" + localFile.getName());
      }
      else
      {
        localObject = localFile.getName();
        localObject = this.jdField_field_235_of_type_JavaLangString.substring(this.jdField_field_235_of_type_JavaLangString.indexOf("/")) + ((String)localObject).substring(((String)localObject).indexOf("."));
        localObject = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "/DATA/" + (String)localObject);
      }
      if (!((File)localObject).getParentFile().exists()) {
        ((File)localObject).getParentFile().mkdirs();
      }
      FileUtil.b(localFile, (File)localObject);
    }
  }
  
  private void b1(SegmentController paramSegmentController)
  {
    File localFile1 = new File(class_371.field_145);
    StringBuilder localStringBuilder = new StringBuilder();
    class_720.a3(paramSegmentController, localStringBuilder);
    paramSegmentController = localStringBuilder.toString();
    for (File localFile2 : localFile1.listFiles(new class_1082(paramSegmentController)))
    {
      Object localObject;
      if (this.jdField_field_235_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry == null)
      {
        localObject = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "/DATA/" + localFile2.getName());
      }
      else
      {
        localObject = localFile2.getName();
        localObject = this.jdField_field_235_of_type_JavaLangString.substring(this.jdField_field_235_of_type_JavaLangString.indexOf("/")) + ((String)localObject).substring(((String)localObject).indexOf("."));
        localObject = new File(this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + "/" + this.jdField_field_235_of_type_JavaLangString + "/DATA/" + (String)localObject);
      }
      if (!((File)localObject).getParentFile().exists()) {
        ((File)localObject).getParentFile().mkdirs();
      }
      FileUtil.b(localFile2, (File)localObject);
    }
  }
  
  private void a10(SegmentController paramSegmentController, DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(0);
    paramDataOutputStream.writeByte(3);
    paramDataOutputStream.writeInt(paramSegmentController.getDockingController().a5().size());
    this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = new BlueprintEntry[paramSegmentController.getDockingController().a5().size()];
    int i = 0;
    Iterator localIterator = paramSegmentController.getDockingController().a5().iterator();
    while (localIterator.hasNext())
    {
      ElementDocking localElementDocking;
      SegmentController localSegmentController = (localElementDocking = (ElementDocking)localIterator.next()).from.a7().a15();
      class_48 localclass_48 = localElementDocking.field_1740.a2(new class_48());
      String str = this.jdField_field_235_of_type_JavaLangString + "/ATTACHED_" + i;
      paramDataOutputStream.writeUTF(str);
      paramDataOutputStream.writeInt(localclass_48.field_475);
      paramDataOutputStream.writeInt(localclass_48.field_476);
      paramDataOutputStream.writeInt(localclass_48.field_477);
      paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a13().field_615);
      paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a13().field_616);
      paramDataOutputStream.writeFloat(localSegmentController.getDockingController().a13().field_617);
      paramDataOutputStream.writeShort(localElementDocking.field_1740.a9());
      paramDataOutputStream.writeByte(-1);
      this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i] = new BlueprintEntry(str, this.jdField_field_235_of_type_Class_1216);
      this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].jdField_field_235_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry = this;
      this.jdField_field_235_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].jdField_field_235_of_type_OrgSchemaGameCommonControllerSegmentController = localSegmentController;
      i++;
      paramDataOutputStream.flush();
    }
    if ((paramSegmentController instanceof class_798))
    {
      paramDataOutputStream.writeByte(2);
      ((class_798)paramSegmentController).a().toTagStructure().a11(paramDataOutputStream, false);
    }
    else
    {
      paramDataOutputStream.writeByte(1);
    }
    paramDataOutputStream.flush();
  }
  
  private void a11(File paramFile)
  {
    (paramFile = new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile)))).readInt();
    int i = 0;
    int j = 0;
    while ((j == 0) && ((i = paramFile.readByte()) != 1)) {
      switch (i)
      {
      case 2: 
        this.jdField_field_235_of_type_Class_69 = class_69.a10(paramFile, false);
        j = 1;
        break;
      case 3: 
        i = paramFile.readInt();
        this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry = new BlueprintEntry[i];
        for (int k = 0; k < i; k++)
        {
          String str = paramFile.readUTF();
          this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k] = new BlueprintEntry(str, this.jdField_field_235_of_type_Class_1216);
          this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_field_235_of_type_Class_48 = new class_48(paramFile.readInt(), paramFile.readInt(), paramFile.readInt());
          this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_field_235_of_type_JavaxVecmathVector3f = new Vector3f(paramFile.readFloat(), paramFile.readFloat(), paramFile.readFloat());
          this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_field_235_of_type_Short = paramFile.readShort();
          this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_field_235_of_type_Byte = paramFile.readByte();
          this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[k].jdField_field_235_of_type_OrgSchemaGameServerDataBlueprintnwBlueprintEntry = this;
        }
      }
    }
    paramFile.close();
    if (this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry != null) {
      for (i = 0; i < this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry.length; i++) {
        this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry[i].a6();
      }
    }
  }
  
  private ControlElementMapper a12(boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    Object localObject = this.jdField_field_236_of_type_JavaIoFile;
    paramBoolean = this;
    if ((this.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper == null) || (bool))
    {
      (localObject = new DataInputStream(new BufferedInputStream(new FileInputStream((File)localObject)))).readInt();
      ControlElementMapper localControlElementMapper = new ControlElementMapper();
      ControlElementMap.deserialize((DataInputStream)localObject, localControlElementMapper);
      paramBoolean.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper = localControlElementMapper;
      ((DataInputStream)localObject).close();
    }
    return paramBoolean.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
  }
  
  public final class_69 a13()
  {
    return this.jdField_field_235_of_type_Class_69;
  }
  
  public final ControlElementMapper a1()
  {
    try
    {
      return a12(false);
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    return new ControlElementMapper();
  }
  
  public final int a2()
  {
    return this.jdField_field_235_of_type_Int;
  }
  
  public final BlueprintEntry[] a14()
  {
    return this.jdField_field_236_of_type_ArrayOfOrgSchemaGameServerDataBlueprintnwBlueprintEntry;
  }
  
  public final class_48 a15()
  {
    return this.jdField_field_235_of_type_Class_48;
  }
  
  public final short a16()
  {
    return this.jdField_field_235_of_type_Short;
  }
  
  public final class_703 a3()
  {
    return this.jdField_field_235_of_type_Class_703;
  }
  
  public final class_1084 a17()
  {
    return this.jdField_field_235_of_type_Class_1084;
  }
  
  public final String a4()
  {
    return this.jdField_field_235_of_type_JavaLangString;
  }
  
  public final class_988 a18()
  {
    return this.jdField_field_235_of_type_Class_988;
  }
  
  public final class_1216 a19()
  {
    return this.jdField_field_235_of_type_Class_1216;
  }
  
  public final byte a20()
  {
    return this.jdField_field_235_of_type_Byte;
  }
  
  public final Vector3f a21()
  {
    return this.jdField_field_235_of_type_JavaxVecmathVector3f;
  }
  
  public static String a22(File paramFile, class_1216 paramclass_1216)
  {
    File localFile1;
    if ((localFile1 = new File("./bbtmp/")).exists()) {
      FileUtil.a2(localFile1);
    }
    if (!localFile1.exists()) {
      localFile1.mkdir();
    }
    FileUtil.a3(paramFile, "./bbtmp/");
    paramFile = 0;
    Object localObject1;
    if ((localObject1 = localFile1.listFiles()).length != 1) {
      throw new IOException("wrong file format to import. Must be exctly one dir, but found " + Arrays.toString(localFile1.list()));
    }
    File[] arrayOfFile;
    if ((arrayOfFile = (localObject1 = localObject1[0]).listFiles()).length > 0) {
      for (int j = 0; j < arrayOfFile.length; j++) {
        if (arrayOfFile[j].getName().toLowerCase(Locale.ENGLISH).endsWith(".txt"))
        {
          System.err.println("[BLUEPRINT][IMPORT] Found Old Data " + arrayOfFile[j].getName());
          paramclass_1216.a3("./bbtmp/" + ((File)localObject1).getName() + "/", false, arrayOfFile[j].getName());
          paramFile = 1;
          break;
        }
      }
    } else {
      throw new IOException("wrong file format to import. found " + Arrays.toString(((File)localObject1).list()));
    }
    Object localObject2;
    if (paramFile == 0)
    {
      localObject2 = ((File)localObject1).list();
      System.err.println("failed to import old method. no Catalog.txt found in " + Arrays.toString((Object[])localObject2) + " trying new!");
      for (int i = 0; i < localObject2.length; i++) {
        if (!localObject2[i].equals("header.smbph"))
        {
          paramFile = 1;
          break;
        }
      }
      if (paramFile == 0) {
        throw new IOException("ERROR: No blueprint data found to import: " + Arrays.toString((Object[])localObject2));
      }
    }
    if (!(localObject2 = localFile1.listFiles())[0].isDirectory()) {
      throw new IOException("not a directory: " + localObject2[0].getAbsolutePath());
    }
    File localFile2 = new File(paramclass_1216.jdField_field_1429_of_type_JavaLangString + "/" + localObject2[0].getName());
    System.err.println("IMPORT: " + localObject2[0].getAbsolutePath());
    FileUtil.a(localObject2[0], localFile2);
    paramFile = paramclass_1216.jdField_field_1429_of_type_JavaLangString + "/" + localObject2[0].getName() + "/DATA";
    if (((paramclass_1216 = new File(paramFile)).exists()) && (paramclass_1216.isDirectory()) && ((paramclass_1216 = paramclass_1216.listFiles()).length > 0) && (paramclass_1216[0].getName().endsWith(".smd")) && (!paramclass_1216[0].getName().endsWith(".smd2"))) {
      class_1216.b(paramFile);
    }
    FileUtil.a2(localFile1);
    return localFile2.getName();
  }
  
  public final File a23()
  {
    String str1 = this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + File.separator + this.jdField_field_235_of_type_JavaLangString + "/";
    String str2 = this.jdField_field_235_of_type_Class_1216.jdField_field_1429_of_type_JavaLangString + File.separator + "exported" + File.separator + this.jdField_field_235_of_type_JavaLangString + ".sment";
    File localFile;
    if (!(localFile = new File(str2)).getParentFile().exists()) {
      localFile.getParentFile().mkdirs();
    }
    class_29.a11(str1, str2, null);
    return new File(str2);
  }
  
  public String toString()
  {
    return this.jdField_field_235_of_type_JavaLangString;
  }
  
  public final boolean a24()
  {
    return this.field_391.exists();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.server.data.blueprintnw.BlueprintEntry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */