package org.schema.schine.graphicsengine.meshimporter;

import class_1000;
import class_1004;
import class_1034;
import class_1052;
import class_1058;
import class_1354;
import class_1355;
import class_1384;
import class_1386;
import class_1392;
import class_1393;
import class_1430;
import class_1432;
import class_29;
import class_39;
import class_54;
import class_73;
import class_984;
import class_986;
import class_988;
import class_996;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.AnimationNotFoundException;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLOgreParser
  extends DefaultHandler
{
  private HashMap jdField_field_1854_of_type_JavaUtilHashMap = new HashMap();
  private class_1355 jdField_field_1854_of_type_Class_1355;
  private class_1355 jdField_field_1855_of_type_Class_1355;
  private String jdField_field_1854_of_type_JavaLangString;
  private String jdField_field_1855_of_type_JavaLangString;
  private class_1393[] jdField_field_1854_of_type_ArrayOfClass_1393;
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new XMLOgreParser();
    String str = "ogretest/temple-machine.scene";
    paramArrayOfString.a11(str, "");
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfChar = new String(paramArrayOfChar, paramInt1, paramInt2)).trim().length() > 0) {
      this.jdField_field_1855_of_type_Class_1355.field_1539 = paramArrayOfChar;
    }
  }
  
  public void endDocument() {}
  
  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    if (("".equals(paramString2) ? paramString3 : paramString2).equals(this.jdField_field_1855_of_type_Class_1355.jdField_field_1538_of_type_JavaLangString))
    {
      this.jdField_field_1855_of_type_Class_1355.jdField_field_1538_of_type_Boolean = true;
      if (this.jdField_field_1855_of_type_Class_1355.jdField_field_1538_of_type_Class_1355 != null) {
        this.jdField_field_1855_of_type_Class_1355 = this.jdField_field_1855_of_type_Class_1355.jdField_field_1538_of_type_Class_1355;
      }
    }
  }
  
  private static class_1034 a(class_1355 paramclass_1355)
  {
    class_1034 localclass_1034 = new class_1034();
    int i = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.size();
    Iterator localIterator = paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
    while (localIterator.hasNext())
    {
      class_54 localclass_54;
      if ((localclass_54 = (class_54)localIterator.next()).field_520.equals("name"))
      {
        localclass_1034.jdField_field_1303_of_type_JavaLangString = localclass_54.field_521;
        System.err.println("... Animation name " + localclass_1034.jdField_field_1303_of_type_JavaLangString);
      }
      if (localclass_54.field_520.equals("loop")) {
        Boolean.parseBoolean(localclass_54.field_521);
      }
      localclass_54.field_520.equals("interpolationMode");
      localclass_54.field_520.equals("rotationInterpolationMode");
      localclass_54.field_520.equals("length");
    }
    a7(paramclass_1355, i);
    return localclass_1034;
  }
  
  private static Vector a1(class_1355 paramclass_1355)
  {
    Vector localVector = new Vector();
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      class_1355 localclass_1355;
      if ((localclass_1355 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("animation")) {
        localVector.add(a(localclass_1355));
      }
    }
    return localVector;
  }
  
  private static class_1034 b(class_1355 paramclass_1355)
  {
    Object localObject1 = null;
    float f = 0.0F;
    Iterator localIterator1 = paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
    Object localObject3;
    while (localIterator1.hasNext())
    {
      if ((localObject3 = (class_54)localIterator1.next()).field_520.equals("name")) {
        localObject1 = ((class_54)localObject3).field_521;
      }
      if (((class_54)localObject3).field_520.equals("loop")) {
        Boolean.parseBoolean(((class_54)localObject3).field_521);
      }
      ((class_54)localObject3).field_520.equals("interpolationMode");
      ((class_54)localObject3).field_520.equals("rotationInterpolationMode");
      if (((class_54)localObject3).field_520.equals("length")) {
        f = Float.parseFloat(((class_54)localObject3).field_521);
      }
    }
    class_1034 localclass_1034;
    (localclass_1034 = new class_1034()).jdField_field_1303_of_type_JavaLangString = ((String)localObject1);
    localclass_1034.jdField_field_1303_of_type_Float = f;
    localIterator1 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (localIterator1.hasNext()) {
      if ((localObject3 = (class_1355)localIterator1.next()).jdField_field_1538_of_type_JavaLangString.equals("tracks"))
      {
        paramclass_1355 = ((class_1355)localObject3).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (paramclass_1355.hasNext()) {
          if ((localObject1 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("track"))
          {
            localObject3 = localObject1;
            Object localObject2 = localclass_1034;
            localObject1 = null;
            Iterator localIterator2 = ((class_1355)localObject3).jdField_field_1538_of_type_JavaUtilVector.iterator();
            Object localObject4;
            while (localIterator2.hasNext()) {
              if ((localObject4 = (class_54)localIterator2.next()).field_520.equals("bone")) {
                localObject1 = ((class_54)localObject4).field_521;
              }
            }
            localIterator2 = ((class_1355)localObject3).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
            while (localIterator2.hasNext())
            {
              localObject4 = (class_1355)localIterator2.next();
              if ((!jdField_field_1854_of_type_Boolean) && (localObject1 == null)) {
                throw new AssertionError();
              }
              localObject3 = new class_1052((String)localObject1);
              a15((class_1355)localObject4, (class_1058)localObject3);
              localObject2.jdField_field_1303_of_type_JavaUtilHashMap.put(((class_1052)localObject3).a(), localObject3);
            }
          }
        }
      }
    }
    return localclass_1034;
  }
  
  private static List a2(class_1355 paramclass_1355)
  {
    ArrayList localArrayList = new ArrayList();
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      class_1355 localclass_1355;
      if ((localclass_1355 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("animation")) {
        localArrayList.add(b(localclass_1355));
      }
    }
    return localArrayList;
  }
  
  private static void a3(Mesh paramMesh, class_1355 paramclass_1355)
  {
    class_1432[] arrayOfclass_1432 = new class_1432[paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.size()];
    int i = 0;
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      Object localObject;
      if ((localObject = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("vertexboneassignment"))
      {
        int j = -1;
        int k = -1;
        float f = -1.0F;
        localObject = ((class_1355)localObject).jdField_field_1538_of_type_JavaUtilVector.iterator();
        while (((Iterator)localObject).hasNext())
        {
          class_54 localclass_54;
          String str = (localclass_54 = (class_54)((Iterator)localObject).next()).field_521;
          if (localclass_54.field_520.equals("vertexindex")) {
            j = Integer.parseInt(str);
          }
          if (localclass_54.field_520.equals("boneindex")) {
            k = Integer.parseInt(str);
          }
          if (localclass_54.field_520.equals("weight")) {
            f = Float.parseFloat(str);
          }
        }
        localObject = new class_1432(j, k, f);
        arrayOfclass_1432[i] = localObject;
        paramMesh.a180(arrayOfclass_1432);
      }
      i++;
    }
  }
  
  private static void a4(class_1384 paramclass_1384, class_1355 paramclass_1355)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    Object localObject2;
    label241:
    label247:
    while (paramclass_1355.hasNext()) {
      if ((localObject1 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("boneparent"))
      {
        localObject2 = null;
        String str = null;
        localObject1 = ((class_1355)localObject1).jdField_field_1538_of_type_JavaUtilVector.iterator();
        Object localObject3;
        while (((Iterator)localObject1).hasNext())
        {
          if ((localObject3 = (class_54)((Iterator)localObject1).next()).field_520.equals("bone")) {
            localObject2 = ((class_54)localObject3).field_521;
          }
          if (((class_54)localObject3).field_520.equals("parent")) {
            str = ((class_54)localObject3).field_521;
          }
        }
        if ((!jdField_field_1854_of_type_Boolean) && (localObject2 == null)) {
          throw new AssertionError();
        }
        localObject1 = paramclass_1384.a6().values().iterator();
        for (;;)
        {
          if (!((Iterator)localObject1).hasNext()) {
            break label247;
          }
          if ((localObject3 = (class_986)((Iterator)localObject1).next()).jdField_field_98_of_type_JavaLangString.equals(localObject2))
          {
            localObject1 = paramclass_1384.a6().values().iterator();
            for (;;)
            {
              if (!((Iterator)localObject1).hasNext()) {
                break label241;
              }
              if ((localObject2 = (class_986)((Iterator)localObject1).next()).jdField_field_98_of_type_JavaLangString.equals(str))
              {
                ((class_986)localObject3).a60((class_986)localObject2);
                ((class_986)localObject2).a57().add(localObject3);
                break;
              }
            }
            break;
          }
        }
      }
    }
    paramclass_1355 = 0;
    Object localObject1 = paramclass_1384.a6().values().iterator();
    while (((Iterator)localObject1).hasNext()) {
      if ((localObject2 = (class_986)((Iterator)localObject1).next()).a58() == null) {
        if (paramclass_1355 == 0)
        {
          paramclass_1355 = 1;
          paramclass_1384.a12((class_986)localObject2);
        }
        else
        {
          System.err.println("WARNING: more than one skeleton root bone found " + ((class_986)localObject2).jdField_field_98_of_type_JavaLangString);
        }
      }
    }
  }
  
  private static void b1(class_1384 paramclass_1384, class_1355 paramclass_1355)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      Object localObject1;
      if ((localObject1 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("bone"))
      {
        Object localObject2 = null;
        int i = 0;
        Object localObject3 = ((class_1355)localObject1).jdField_field_1538_of_type_JavaUtilVector.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          if ((localObject4 = (class_54)((Iterator)localObject3).next()).field_520.equals("name")) {
            localObject2 = ((class_54)localObject4).field_521;
          }
          if (((class_54)localObject4).field_520.equals("id")) {
            i = Integer.parseInt(((class_54)localObject4).field_521);
          }
        }
        localObject3 = new class_986(i, (String)localObject2);
        class_986.f();
        Object localObject4 = null;
        localObject2 = null;
        localObject1 = ((class_1355)localObject1).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          class_1355 localclass_1355;
          if ((localclass_1355 = (class_1355)((Iterator)localObject1).next()).jdField_field_1538_of_type_JavaLangString.equals("position")) {
            localObject4 = a16(localclass_1355);
          }
          if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("rotation"))
          {
            localObject2 = new AxisAngle4f();
            Iterator localIterator = localclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
            Object localObject5;
            Object localObject6;
            while (localIterator.hasNext())
            {
              localObject6 = (localObject5 = (class_54)localIterator.next()).field_521;
              if (((class_54)localObject5).field_520.equals("angle")) {
                ((AxisAngle4f)localObject2).angle = Float.parseFloat((String)localObject6);
              }
            }
            localIterator = localclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
            while (localIterator.hasNext()) {
              if ((localObject5 = (class_1355)localIterator.next()).jdField_field_1538_of_type_JavaLangString.equals("axis"))
              {
                (localObject6 = a16((class_1355)localObject5)).normalize();
                ((AxisAngle4f)localObject2).field_586 = ((Vector3f)localObject6).field_615;
                ((AxisAngle4f)localObject2).field_587 = ((Vector3f)localObject6).field_616;
                ((AxisAngle4f)localObject2).field_588 = ((Vector3f)localObject6).field_617;
              }
            }
          }
          if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("scale")) {
            a16(localclass_1355);
          }
        }
        ((class_986)localObject3).b8((Vector3f)localObject4, class_29.a2(((AxisAngle4f)localObject2).angle, new Vector3f(((AxisAngle4f)localObject2).field_586, ((AxisAngle4f)localObject2).field_587, ((AxisAngle4f)localObject2).field_588), new Quat4f()));
        if ((!jdField_field_1854_of_type_Boolean) && (((class_986)localObject3).jdField_field_98_of_type_JavaLangString == null)) {
          throw new AssertionError();
        }
        if ((!jdField_field_1854_of_type_Boolean) && (((class_986)localObject3).jdField_field_98_of_type_Int < 0)) {
          throw new AssertionError();
        }
        paramclass_1384.a6().put(((class_986)localObject3).jdField_field_98_of_type_Int, localObject3);
      }
    }
  }
  
  private class_996 a5(class_1355 paramclass_1355)
  {
    Object localObject1 = null;
    Object localObject2 = "default";
    Iterator localIterator = paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
    Object localObject4;
    Object localObject5;
    Object localObject7;
    while (localIterator.hasNext())
    {
      localObject4 = (localObject3 = (class_54)localIterator.next()).field_521;
      if (((class_54)localObject3).field_520.equals("description")) {
        localObject2 = localObject4;
      }
      ((class_54)localObject3).field_520.equals("id");
      ((class_54)localObject3).field_520.equals("castShadows");
      ((class_54)localObject3).field_520.equals("receiveShadows");
      if (((class_54)localObject3).field_520.equals("meshFile"))
      {
        localObject3 = this.jdField_field_1854_of_type_JavaLangString;
        localObject5 = new Vector();
        (localObject6 = new XMLOgreParser()).a18((String)localObject3 + (String)localObject4 + ".xml");
        localObject4 = null;
        localObject7 = ((XMLOgreParser)localObject6).jdField_field_1854_of_type_Class_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (((Iterator)localObject7).hasNext())
        {
          if ((localObject6 = (class_1355)((Iterator)localObject7).next()).jdField_field_1538_of_type_JavaLangString.equals("submeshes")) {
            ((Vector)localObject5).addAll(b4((class_1355)localObject6));
          }
          if (((class_1355)localObject6).jdField_field_1538_of_type_JavaLangString.equals("submeshnames")) {
            a14((class_1355)localObject6, (Vector)localObject5);
          }
          ((class_1355)localObject6).jdField_field_1538_of_type_JavaLangString.equals("poses");
          if (((class_1355)localObject6).jdField_field_1538_of_type_JavaLangString.equals("skeletonlink")) {
            localObject4 = a13((class_1355)localObject6);
          }
        }
        localObject7 = (class_996)((Vector)localObject5).remove(0);
        Object localObject6 = ((Vector)localObject5).iterator();
        while (((Iterator)localObject6).hasNext())
        {
          localObject5 = (class_996)((Iterator)localObject6).next();
          ((class_996)localObject7).a150((class_984)localObject5);
        }
        if (localObject4 != null) {
          a12((Mesh)localObject7, (String)localObject3, (String)localObject4);
        }
        localObject1 = localObject7;
      }
    }
    localObject1.c11((String)localObject2);
    localIterator = null;
    Object localObject3 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    class_1393 localclass_1393;
    while (((Iterator)localObject3).hasNext()) {
      if ((localObject4 = (class_1355)((Iterator)localObject3).next()).jdField_field_1538_of_type_JavaLangString.equals("subentities"))
      {
        paramclass_1355 = ((class_1355)localObject4).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (paramclass_1355.hasNext())
        {
          localObject5 = ((class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaUtilVector.iterator();
          while (((Iterator)localObject5).hasNext())
          {
            (localObject2 = (class_54)((Iterator)localObject5).next()).field_520.equals("index");
            if (((class_54)localObject2).field_520.equals("materialName"))
            {
              if ((!this.jdField_field_1854_of_type_JavaUtilHashMap.containsKey(((class_54)localObject2).field_521)) && (this.jdField_field_1854_of_type_ArrayOfClass_1393 == null))
              {
                this.jdField_field_1854_of_type_ArrayOfClass_1393 = a8(this.jdField_field_1854_of_type_JavaLangString + this.jdField_field_1855_of_type_JavaLangString + ".material");
                for (localObject7 : this.jdField_field_1854_of_type_ArrayOfClass_1393) {
                  this.jdField_field_1854_of_type_JavaUtilHashMap.put(((class_1393)localObject7).a10(), localObject7);
                }
              }
              localclass_1393 = (class_1393)this.jdField_field_1854_of_type_JavaUtilHashMap.get(((class_54)localObject2).field_521);
            }
          }
        }
      }
      else if ((((class_1355)localObject4).jdField_field_1538_of_type_JavaLangString.equals("userData")) && (((class_1355)localObject4).field_1539 != null))
      {
        try
        {
          localObject2 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
          (localObject5 = new InputSource()).setCharacterStream(new StringReader(((class_1355)localObject4).field_1539));
          localObject2 = ((DocumentBuilder)localObject2).parse((InputSource)localObject5);
          localObject1.a165((Document)localObject2);
          if ((localObject4 = ((Document)localObject2).getElementsByTagName("Mass").item(0)) != null)
          {
            float f = Float.parseFloat(((Node)localObject4).getTextContent());
            localObject1.a(f);
          }
        }
        catch (SAXException paramclass_1355)
        {
          System.err.println("Exception while parsing userdata from " + localObject1.b14());
          paramclass_1355.printStackTrace();
        }
        catch (IOException localIOException)
        {
          localIOException;
        }
        catch (ParserConfigurationException localParserConfigurationException)
        {
          localParserConfigurationException;
        }
      }
    }
    localObject1.a160(localclass_1393);
    return localObject1;
  }
  
  private static void a6(class_1355 paramclass_1355)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext()) {
      paramclass_1355.next();
    }
  }
  
  @Deprecated
  private static class_1354 a7(class_1355 paramclass_1355, int paramInt)
  {
    paramInt = new class_1354(paramInt);
    int i = 0;
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      Object localObject;
      if ((localObject = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("keyframe"))
      {
        localObject = ((class_1355)localObject).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (((Iterator)localObject).hasNext())
        {
          class_1355 localclass_1355;
          if ((localclass_1355 = (class_1355)((Iterator)localObject).next()).jdField_field_1538_of_type_JavaLangString.equals("translation")) {
            paramInt.field_1536[i] = a16(localclass_1355);
          }
          if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("rotation")) {
            paramInt.jdField_field_1535_of_type_ArrayOfJavaxVecmathVector4f[i] = a17(localclass_1355);
          }
          if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("scale")) {
            paramInt.jdField_field_1535_of_type_ArrayOfJavaxVecmathVector3f[i] = a16(localclass_1355);
          }
        }
        i++;
      }
    }
    return paramInt;
  }
  
  private static class_1393[] a8(String paramString)
  {
    paramString = new BufferedReader(new InputStreamReader(class_73.field_134.a2(paramString)));
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      while (paramString.ready()) {
        localStringBuffer.append(paramString.readLine() + "\n");
      }
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    String[] arrayOfString1;
    paramString = new class_1393[(arrayOfString1 = localStringBuffer.toString().trim().split("material")).length];
    for (int i = 0; i < arrayOfString1.length; i++)
    {
      String str1;
      String[] arrayOfString2;
      if ((arrayOfString2 = (str1 = arrayOfString1[i]).split("\n")).length == 0) {
        System.err.println("Material String: line length 0: " + str1);
      }
      paramString[i] = new class_1393();
      paramString[i].a13(arrayOfString2[0].trim());
      for (int j = 0; j < arrayOfString2.length; j++)
      {
        String str2;
        Object localObject;
        if ((str2 = arrayOfString2[j]).contains("ambient"))
        {
          localObject = a9(str2);
          paramString[i].a11((float[])localObject);
        }
        if (str2.contains("diffuse"))
        {
          localObject = a9(str2);
          paramString[i].b2((float[])localObject);
        }
        if (str2.contains("specular"))
        {
          localObject = a9(str2);
          paramString[i].c1((float[])localObject);
        }
        if (str2.contains("emissive")) {
          a9(str2);
        }
        if (str2.contains("texture "))
        {
          localObject = str2.split(" ");
          paramString[i].a12(true);
          paramString[i].b4(localObject[1].trim());
        }
      }
    }
    return paramString;
  }
  
  private static float[] a9(String paramString)
  {
    paramString = paramString.split(" ");
    float[] arrayOfFloat;
    (arrayOfFloat = new float[4])[0] = Float.parseFloat(paramString[1]);
    arrayOfFloat[1] = Float.parseFloat(paramString[2]);
    arrayOfFloat[2] = Float.parseFloat(paramString[3]);
    if (paramString.length > 4) {
      arrayOfFloat[3] = Float.parseFloat(paramString[4]);
    } else {
      arrayOfFloat[3] = 1.0F;
    }
    return arrayOfFloat;
  }
  
  private void a10(class_1355 paramclass_1355, class_984 paramclass_984)
  {
    String str = "default";
    int i = 1;
    Object localObject1 = paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      if ((localObject2 = (class_54)((Iterator)localObject1).next()).field_520.equals("description")) {
        str = ((class_54)localObject2).field_521;
      }
      if (((class_54)localObject2).field_520.equals("visibility"))
      {
        if (((class_54)localObject2).field_521.equals("visible")) {
          i = 1;
        }
        if (((class_54)localObject2).field_521.equals("hidden")) {
          i = 2;
        }
        if (((class_54)localObject2).field_521.equals("tree visible")) {
          i = 4;
        }
        if (((class_54)localObject2).field_521.equals("tree hidden")) {
          i = 8;
        }
      }
    }
    localObject1 = null;
    Object localObject2 = new Vector3f();
    Vector4f localVector4f = new Vector4f();
    Vector3f localVector3f = new Vector3f();
    Vector localVector = null;
    int j = 0;
    Iterator localIterator = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    class_1355 localclass_1355;
    while (localIterator.hasNext())
    {
      if ((localclass_1355 = (class_1355)localIterator.next()).jdField_field_1538_of_type_JavaLangString.equals("position")) {
        localObject2 = a16(localclass_1355);
      }
      if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("rotation")) {
        localVector4f = a17(localclass_1355);
      }
      if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("scale")) {
        localVector3f = a16(localclass_1355);
      }
      if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("animations")) {
        localVector = a1(localclass_1355);
      }
      if (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("entity"))
      {
        j = 1;
        (localObject1 = a5(localclass_1355)).g4(i);
      }
    }
    if (j == 0)
    {
      (localObject1 = new class_1392()).c11(str);
      ((class_984)localObject1).g4(i);
    }
    localIterator = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (localIterator.hasNext()) {
      if ((localclass_1355 = (class_1355)localIterator.next()).jdField_field_1538_of_type_JavaLangString.equals("node")) {
        a10(localclass_1355, (class_984)localObject1);
      }
    }
    paramclass_984.a150((class_984)localObject1);
    if (localVector != null)
    {
      ((class_984)localObject1).a156(localVector);
      try
      {
        ((class_984)localObject1).b23(((class_984)localObject1).b14());
      }
      catch (AnimationNotFoundException localAnimationNotFoundException)
      {
        localAnimationNotFoundException;
      }
      class_984.p1();
    }
    ((class_984)localObject1).b24((Vector3f)localObject2);
    ((class_984)localObject1).b26(localVector4f);
    ((class_984)localObject1).a158(localVector4f);
    ((class_984)localObject1).e7().set(localVector3f);
    ((class_984)localObject1).a159(new Vector3f(localVector3f));
  }
  
  private void b2(class_1355 paramclass_1355, class_984 paramclass_984)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      class_1355 localclass_1355;
      if ((localclass_1355 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("node")) {
        a10(localclass_1355, paramclass_984);
      }
    }
  }
  
  public final class_1004 a11(String paramString1, String paramString2)
  {
    this.jdField_field_1854_of_type_JavaLangString = paramString1;
    this.jdField_field_1855_of_type_JavaLangString = paramString2;
    class_1004 localclass_1004 = new class_1004();
    class_984 localclass_984 = null;
    if (this.jdField_field_1854_of_type_Class_1355 == null)
    {
      for (paramString1 = paramString1 + paramString2 + ".scene"; paramString1.contains("//"); paramString1 = paramString1.replaceAll("//", "/")) {}
      a18(paramString1);
    }
    paramString1 = this.jdField_field_1854_of_type_Class_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramString1.hasNext())
    {
      if ((paramString2 = (class_1355)paramString1.next()).jdField_field_1538_of_type_JavaLangString.equals("environment")) {
        a6(paramString2);
      }
      if (paramString2.jdField_field_1538_of_type_JavaLangString.equals("nodes")) {
        b2(paramString2, localclass_1004);
      }
    }
    paramString1 = 0;
    paramString2 = localclass_1004.a152().iterator();
    while (paramString2.hasNext()) {
      if (((localclass_984 = (class_984)paramString2.next()) instanceof class_996)) {
        paramString1 = (int)(paramString1 + ((class_996)localclass_984).a3());
      }
    }
    localclass_1004.a(paramString1);
    localclass_1004.a157(localclass_1004.a155(new Vector3f(), new Vector3f()));
    localclass_1004.o1();
    localclass_1004.q();
    return localclass_1004;
  }
  
  private static void a12(Mesh paramMesh, String paramString1, String paramString2)
  {
    Object localObject1 = paramString2;
    paramString2 = paramString1;
    System.err.println("[PARSER] parsing skeleton " + paramString2 + (String)localObject1);
    (localObject2 = new XMLOgreParser()).a18(paramString2 + (String)localObject1 + ".xml");
    paramString2 = new class_1384();
    localObject1 = null;
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject2 = ((XMLOgreParser)localObject2).jdField_field_1854_of_type_Class_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    Object localObject5;
    while (((Iterator)localObject2).hasNext())
    {
      if ((localObject5 = (class_1355)((Iterator)localObject2).next()).jdField_field_1538_of_type_JavaLangString.equals("bones")) {
        localObject1 = localObject5;
      }
      if (((class_1355)localObject5).jdField_field_1538_of_type_JavaLangString.equals("bonehierarchy")) {
        localObject3 = localObject5;
      }
      if (((class_1355)localObject5).jdField_field_1538_of_type_JavaLangString.equals("animations")) {
        localObject4 = localObject5;
      }
    }
    b1(paramString2, (class_1355)localObject1);
    a4(paramString2, localObject3);
    if (localObject4 != null)
    {
      System.out.println("... parsing skeleton animation");
      paramString2.a11(a2(localObject4));
    }
    paramString1 = paramString2;
    paramString2 = new HashMap();
    for (localObject4 : paramMesh.a178())
    {
      if ((localObject5 = (Vector)paramString2.get(Integer.valueOf(localObject4.field_1645))) == null)
      {
        localObject5 = new Vector();
        paramString2.put(Integer.valueOf(localObject4.field_1645), localObject5);
      }
      ((Vector)localObject5).add(localObject4);
    }
    (localObject1 = new class_1430(paramMesh.a57())).a(paramString2, paramString1);
    paramMesh.a180(null);
    paramMesh.a179(new class_1386(paramString1, (class_1430)localObject1));
  }
  
  private static String a13(class_1355 paramclass_1355)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
    while (paramclass_1355.hasNext())
    {
      class_54 localclass_54;
      if ((localclass_54 = (class_54)paramclass_1355.next()).field_520.equals("name")) {
        return localclass_54.field_521;
      }
    }
    return null;
  }
  
  private static class_996 b3(class_1355 paramclass_1355)
  {
    Object localObject3 = null;
    Iterator localIterator1 = paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.iterator();
    Object localObject4;
    Object localObject5;
    while (localIterator1.hasNext())
    {
      localObject5 = (localObject4 = (class_54)localIterator1.next()).field_521;
      ((class_54)localObject4).field_520.equals("material");
      if (((class_54)localObject4).field_520.equals("usesharedvertices")) {
        Boolean.parseBoolean((String)localObject5);
      }
      if (((class_54)localObject4).field_520.equals("use32bitindexes")) {
        Boolean.parseBoolean((String)localObject5);
      }
      if (((class_54)localObject4).field_520.equals("operationtype")) {
        localObject3 = localObject5;
      }
    }
    Object localObject2;
    if (((String)localObject3).equals("line_list")) {
      localObject2 = new class_1000();
    } else {
      localObject2 = new Mesh();
    }
    localIterator1 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (localIterator1.hasNext())
    {
      if (((localObject4 = (class_1355)localIterator1.next()).jdField_field_1538_of_type_JavaLangString.equals("faces")) && ((localObject2 instanceof Mesh)))
      {
        localObject5 = (Mesh)localObject2;
        int i = Integer.parseInt(((class_54)((class_1355)localObject4).jdField_field_1538_of_type_JavaUtilVector.get(0)).field_521);
        ((Mesh)localObject5).b13(i);
        ((Mesh)localObject5).a164(GlUtil.a5(i * 3 << 2, 0).asIntBuffer());
        ((Mesh)localObject5).field_90 = GlUtil.a5(i * 3 << 2, 1).asIntBuffer();
        ((Mesh)localObject5).jdField_field_92_of_type_JavaNioIntBuffer = GlUtil.a5(i * 3 << 2, 2).asIntBuffer();
        localObject3 = ((class_1355)localObject4).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          paramclass_1355 = (class_1355)((Iterator)localObject3).next();
          ((Mesh)localObject5).j();
          for (i = 0; i < 3; i++)
          {
            ((Mesh)localObject5).a162().put(Integer.parseInt(((class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(i)).field_521));
            ((Mesh)localObject5).jdField_field_92_of_type_JavaNioIntBuffer.put(Integer.parseInt(((class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(i)).field_521));
            ((Mesh)localObject5).field_90.put(Integer.parseInt(((class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(i)).field_521));
          }
        }
      }
      if (((class_1355)localObject4).jdField_field_1538_of_type_JavaLangString.equals("geometry"))
      {
        int j = 0;
        paramclass_1355 = ((class_1355)localObject4).jdField_field_1538_of_type_JavaUtilVector.iterator();
        Object localObject1;
        while (paramclass_1355.hasNext())
        {
          localObject3 = (localObject1 = (class_54)paramclass_1355.next()).field_521;
          if (((class_54)localObject1).field_520.equals("vertexcount"))
          {
            j = Integer.parseInt((String)localObject3);
            ((class_996)localObject2).a72(j);
            if ((localObject2 instanceof Mesh)) {
              ((Mesh)localObject2).c4(j);
            }
          }
        }
        paramclass_1355 = ((class_1355)localObject4).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (paramclass_1355.hasNext()) {
          if ((localObject1 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("vertexbuffer"))
          {
            int k = j;
            Object localObject6 = localObject1;
            localObject3 = localObject2;
            boolean bool1 = false;
            boolean bool2 = false;
            int m = 0;
            Iterator localIterator2 = ((class_1355)localObject6).jdField_field_1538_of_type_JavaUtilVector.iterator();
            while (localIterator2.hasNext())
            {
              class_54 localclass_54;
              String str = (localclass_54 = (class_54)localIterator2.next()).field_521;
              if (localclass_54.field_520.equals("positions")) {
                bool1 = Boolean.parseBoolean(str);
              }
              if (localclass_54.field_520.equals("normals")) {
                bool2 = Boolean.parseBoolean(str);
              }
              if (localclass_54.field_520.equals("texture_coords")) {
                m = Integer.parseInt(str);
              }
              if (localclass_54.field_520.equals("texture_coord_dimension_0")) {
                Integer.parseInt(str);
              }
            }
            if (bool1) {
              ((class_996)localObject3).field_90 = GlUtil.a5(k * 3 << 2, 3).asFloatBuffer();
            }
            if (bool2) {
              ((Mesh)localObject3).field_93 = GlUtil.a5(k * 3 << 2, 4).asFloatBuffer();
            }
            if (m > 0) {
              ((Mesh)localObject3).jdField_field_92_of_type_JavaNioFloatBuffer = GlUtil.a5(k * 3 << 2, 5).asFloatBuffer();
            }
            int n = 0;
            float f2 = -2.147484E+009F;
            float f1 = 2.147484E+009F;
            float f3 = -2.147484E+009F;
            float f4 = 2.147484E+009F;
            float f5 = -2.147484E+009F;
            float f6 = 2.147484E+009F;
            localObject6 = ((class_1355)localObject6).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
            Object localObject7;
            while (((Iterator)localObject6).hasNext())
            {
              localObject7 = ((class_1355)((Iterator)localObject6).next()).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
              while (((Iterator)localObject7).hasNext())
              {
                class_1355 localclass_1355 = (class_1355)((Iterator)localObject7).next();
                Vector3f localVector3f;
                if ((bool1) && (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("position")))
                {
                  localVector3f = a16(localclass_1355);
                  ((class_996)localObject3).field_90.put(localVector3f.field_615);
                  ((class_996)localObject3).field_90.put(localVector3f.field_616);
                  ((class_996)localObject3).field_90.put(localVector3f.field_617);
                  f2 = localVector3f.field_615 > f2 ? localVector3f.field_615 : f2;
                  f1 = localVector3f.field_615 < f1 ? localVector3f.field_615 : f1;
                  f3 = localVector3f.field_616 > f3 ? localVector3f.field_616 : f3;
                  f4 = localVector3f.field_616 < f4 ? localVector3f.field_616 : f4;
                  f5 = localVector3f.field_617 > f5 ? localVector3f.field_617 : f5;
                  f6 = localVector3f.field_617 < f6 ? localVector3f.field_617 : f6;
                  n++;
                }
                if ((bool2) && (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("normal")))
                {
                  localVector3f = a16(localclass_1355);
                  ((Mesh)localObject3).field_93.put(localVector3f.field_615);
                  ((Mesh)localObject3).field_93.put(localVector3f.field_616);
                  ((Mesh)localObject3).field_93.put(localVector3f.field_617);
                }
                if ((m > 0) && (localclass_1355.jdField_field_1538_of_type_JavaLangString.equals("texcoord")))
                {
                  localVector3f = a16(localclass_1355);
                  ((Mesh)localObject3).jdField_field_92_of_type_JavaNioFloatBuffer.put(localVector3f.field_615);
                  ((Mesh)localObject3).jdField_field_92_of_type_JavaNioFloatBuffer.put(localVector3f.field_616);
                }
              }
            }
            if (bool1)
            {
              localObject6 = new Vector3f(f1, f4, f6);
              localObject7 = new Vector3f(f2, f3, f5);
              if ((!jdField_field_1854_of_type_Boolean) && (n != k)) {
                throw new AssertionError();
              }
              ((class_996)localObject3).a157(new class_988((Vector3f)localObject6, (Vector3f)localObject7));
              ((class_996)localObject3).o1();
            }
          }
        }
      }
      if (((class_1355)localObject4).jdField_field_1538_of_type_JavaLangString.equals("boneassignments")) {
        a3((Mesh)localObject2, (class_1355)localObject4);
      }
    }
    ((class_996)localObject2).field_90.rewind();
    ((class_996)localObject2).a162().rewind();
    if ((localObject2 instanceof Mesh))
    {
      ((Mesh)localObject2).jdField_field_92_of_type_JavaNioFloatBuffer.rewind();
      ((Mesh)localObject2).field_93.rewind();
      ((Mesh)localObject2).field_90.rewind();
      ((Mesh)localObject2).jdField_field_92_of_type_JavaNioIntBuffer.rewind();
    }
    return localObject2;
  }
  
  private static Vector b4(class_1355 paramclass_1355)
  {
    Vector localVector = new Vector();
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      class_996 localclass_996 = b3((class_1355)paramclass_1355.next());
      localVector.add(localclass_996);
    }
    return localVector;
  }
  
  private static void a14(class_1355 paramclass_1355, Vector paramVector)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      Object localObject = (class_1355)paramclass_1355.next();
      int i = 0;
      String str = "";
      localObject = ((class_1355)localObject).jdField_field_1538_of_type_JavaUtilVector.iterator();
      while (((Iterator)localObject).hasNext())
      {
        class_54 localclass_54;
        if ((localclass_54 = (class_54)((Iterator)localObject).next()).field_520.equals("name")) {
          str = localclass_54.field_521;
        }
        if (localclass_54.field_520.equals("index")) {
          i = Integer.parseInt(localclass_54.field_521);
        }
      }
      ((class_996)paramVector.get(i)).c11(str);
    }
  }
  
  private static void a15(class_1355 paramclass_1355, class_1058 paramclass_1058)
  {
    paramclass_1355 = paramclass_1355.jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
    while (paramclass_1355.hasNext())
    {
      Object localObject1;
      if ((localObject1 = (class_1355)paramclass_1355.next()).jdField_field_1538_of_type_JavaLangString.equals("keyframe"))
      {
        float f2 = -1.0F;
        Object localObject3 = ((class_1355)localObject1).jdField_field_1538_of_type_JavaUtilVector.iterator();
        while (((Iterator)localObject3).hasNext()) {
          if ((localObject4 = (class_54)((Iterator)localObject3).next()).field_520.equals("time")) {
            f2 = Float.parseFloat(((class_54)localObject4).field_521);
          }
        }
        localObject3 = null;
        Object localObject4 = null;
        Vector3f localVector3f = null;
        localObject1 = ((class_1355)localObject1).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          if ((localObject5 = (class_1355)((Iterator)localObject1).next()).jdField_field_1538_of_type_JavaLangString.equals("translate")) {
            localObject3 = a16((class_1355)localObject5);
          }
          if (((class_1355)localObject5).jdField_field_1538_of_type_JavaLangString.equals("rotate"))
          {
            localObject4 = new AxisAngle4f();
            Iterator localIterator = ((class_1355)localObject5).jdField_field_1538_of_type_JavaUtilVector.iterator();
            Object localObject6;
            Object localObject7;
            while (localIterator.hasNext())
            {
              localObject7 = (localObject6 = (class_54)localIterator.next()).field_521;
              if (((class_54)localObject6).field_520.equals("angle")) {
                ((AxisAngle4f)localObject4).angle = Float.parseFloat((String)localObject7);
              }
            }
            localIterator = ((class_1355)localObject5).jdField_field_1538_of_type_JavaUtilLinkedList.iterator();
            while (localIterator.hasNext()) {
              if ((localObject6 = (class_1355)localIterator.next()).jdField_field_1538_of_type_JavaLangString.equals("axis"))
              {
                (localObject7 = a16((class_1355)localObject6)).normalize();
                ((AxisAngle4f)localObject4).field_586 = ((Vector3f)localObject7).field_615;
                ((AxisAngle4f)localObject4).field_587 = ((Vector3f)localObject7).field_616;
                ((AxisAngle4f)localObject4).field_588 = ((Vector3f)localObject7).field_617;
              }
            }
          }
          if (((class_1355)localObject5).jdField_field_1538_of_type_JavaLangString.equals("scale")) {
            localVector3f = a16((class_1355)localObject5);
          }
        }
        if ((!jdField_field_1854_of_type_Boolean) && (f2 < 0.0F)) {
          throw new AssertionError();
        }
        if ((!jdField_field_1854_of_type_Boolean) && (localObject3 == null)) {
          throw new AssertionError();
        }
        if ((!jdField_field_1854_of_type_Boolean) && (localObject4 == null)) {
          throw new AssertionError();
        }
        Object localObject5 = new Quat4f();
        localObject4 = new Vector3f(((AxisAngle4f)localObject4).field_586, ((AxisAngle4f)localObject4).field_587, ((AxisAngle4f)localObject4).field_588);
        float f1 = ((AxisAngle4f)localObject4).angle;
        (localObject4 = new Vector3f((Vector3f)localObject4)).normalize();
        class_29.a2(f1, (Vector3f)localObject4, (Quat4f)localObject5);
        Object localObject2 = localObject5;
        paramclass_1058.a5(f2, (Vector3f)localObject3, localObject2, localVector3f);
      }
    }
  }
  
  private static Vector3f a16(class_1355 paramclass_1355)
  {
    Vector3f localVector3f = new Vector3f();
    class_54 localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(0);
    localVector3f.field_615 = Float.parseFloat(localclass_54.field_521);
    localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(1);
    localVector3f.field_616 = Float.parseFloat(localclass_54.field_521);
    if (paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.size() > 2)
    {
      localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(2);
      localVector3f.field_617 = Float.parseFloat(localclass_54.field_521);
    }
    else
    {
      localVector3f.field_617 = 1.0F;
    }
    return localVector3f;
  }
  
  private static Vector4f a17(class_1355 paramclass_1355)
  {
    Vector4f localVector4f = new Vector4f();
    class_54 localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(0);
    localVector4f.field_596 = Float.parseFloat(localclass_54.field_521);
    localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(1);
    localVector4f.field_597 = Float.parseFloat(localclass_54.field_521);
    localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(2);
    localVector4f.field_598 = Float.parseFloat(localclass_54.field_521);
    localclass_54 = (class_54)paramclass_1355.jdField_field_1538_of_type_JavaUtilVector.get(3);
    localVector4f.field_599 = Float.parseFloat(localclass_54.field_521);
    return localVector4f;
  }
  
  private void a18(String paramString)
  {
    try
    {
      SAXParserFactory.newInstance().newSAXParser().parse(class_73.field_134.a2(paramString), this);
      return;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      localParserConfigurationException.printStackTrace();
      return;
    }
    catch (SAXException localSAXException)
    {
      localSAXException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public void startDocument() {}
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    (paramString1 = "".equals(paramString2) ? paramString3 : paramString2).contains("userData");
    if (this.jdField_field_1854_of_type_Class_1355 == null)
    {
      this.jdField_field_1854_of_type_Class_1355 = new class_1355();
      this.jdField_field_1854_of_type_Class_1355.jdField_field_1538_of_type_JavaLangString = paramString1;
      this.jdField_field_1855_of_type_Class_1355 = this.jdField_field_1854_of_type_Class_1355;
      return;
    }
    (paramString2 = new class_1355()).jdField_field_1538_of_type_JavaLangString = paramString1;
    if (paramAttributes != null) {
      for (paramString1 = 0; paramString1 < paramAttributes.getLength(); paramString1++)
      {
        paramString3 = paramAttributes.getLocalName(paramString1);
        if ("".equals(paramString3)) {
          paramString3 = paramAttributes.getQName(paramString1);
        }
        paramString2.jdField_field_1538_of_type_JavaUtilVector.add(new class_54(paramString3, paramAttributes.getValue(paramString1)));
      }
    }
    this.jdField_field_1855_of_type_Class_1355.jdField_field_1538_of_type_JavaUtilLinkedList.add(paramString2);
    paramString2.jdField_field_1538_of_type_Class_1355 = this.jdField_field_1855_of_type_Class_1355;
    if (!this.jdField_field_1855_of_type_Class_1355.jdField_field_1538_of_type_Boolean) {
      this.jdField_field_1855_of_type_Class_1355 = paramString2;
    }
  }
  
  static
  {
    System.getProperty("line.separator");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.graphicsengine.meshimporter.XMLOgreParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */