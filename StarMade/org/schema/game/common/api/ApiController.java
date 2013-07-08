package org.schema.game.common.api;

import class_719;
import class_725;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.io.SAXReader;
import org.schema.game.common.api.exceptions.AlreadyLoggedInException;
import org.schema.game.common.api.exceptions.AuthoriationFailedException;
import org.schema.game.common.api.exceptions.LoginFailedException;
import org.schema.game.common.api.exceptions.NotLoggedInException;
import org.schema.game.common.api.exceptions.WrongUserNameOrPasswordException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ApiController
{
  public static void a(class_719 paramclass_719)
  {
    (localObject = new URL("http://star-made.org/api/system/connect").openConnection()).setDoOutput(true);
    ((URLConnection)localObject).setReadTimeout(20000);
    OutputStreamWriter localOutputStreamWriter;
    (localOutputStreamWriter = new OutputStreamWriter(((URLConnection)localObject).getOutputStream())).flush();
    Object localObject = new BufferedReader(new InputStreamReader(((URLConnection)localObject).getInputStream()));
    String str;
    while ((str = ((BufferedReader)localObject).readLine()) != null) {
      if (str.startsWith("sessid: ")) {
        paramclass_719.field_989 = str.substring(8);
      } else if (str.startsWith("user: ")) {
        paramclass_719.jdField_field_988_of_type_JavaLangString = str.substring(6);
      }
    }
    localOutputStreamWriter.close();
    ((BufferedReader)localObject).close();
  }
  
  public static class_725 a1(class_719 paramclass_719, File paramFile)
  {
    int i;
    Object localObject2 = new byte[i = (int)paramFile.length()];
    new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile))).readFully((byte[])localObject2);
    localObject2 = DatatypeConverter.printBase64Binary((byte[])localObject2);
    class_725 localclass_725;
    (localclass_725 = new class_725()).jdField_field_1000_of_type_Int = i;
    Object localObject4 = paramclass_719.jdField_field_988_of_type_OrgDom4jDocument.selectSingleNode("//result/user/uid").getText();
    localclass_725.field_1003 = ((String)localObject4);
    String str = (paramFile = paramFile.getName()).substring(0, paramFile.lastIndexOf("."));
    str = str + "_" + (String)localObject4 + "." + paramFile.substring(paramFile.lastIndexOf(".") + 1, paramFile.length());
    localclass_725.field_1002 = paramFile;
    paramFile = URLEncoder.encode("filesize", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(i), "UTF-8");
    paramFile = paramFile + "&" + URLEncoder.encode("filename", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8");
    paramFile = paramFile + "&" + URLEncoder.encode("filepath", "UTF-8") + "=" + URLEncoder.encode(new StringBuilder("public://apiupload/").append(str).toString(), "UTF-8");
    paramFile = paramFile + "&" + URLEncoder.encode("file", "UTF-8") + "=" + URLEncoder.encode((String)localObject2, "UTF-8");
    paramFile = paramFile + "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode((String)localObject4, "UTF-8");
    Object localObject1;
    (localObject1 = (HttpURLConnection)new URL("http://star-made.org/api/file").openConnection()).setDoOutput(true);
    ((HttpURLConnection)localObject1).setReadTimeout(20000);
    ((HttpURLConnection)localObject1).setRequestProperty("Cookie", paramclass_719.field_990 + "=" + paramclass_719.field_989);
    System.err.println("DATA: " + paramFile);
    (paramclass_719 = new OutputStreamWriter(((HttpURLConnection)localObject1).getOutputStream())).write(paramFile);
    paramclass_719.flush();
    paramFile = ((HttpURLConnection)localObject1).getResponseCode();
    try
    {
      System.err.println("UPLOAD OUTPUT " + paramFile);
      localObject2 = (localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(((HttpURLConnection)localObject1).getInputStream()).getDocumentElement()).getChildNodes();
      if (!((Element)localObject1).getNodeName().equals("result")) {
        throw new IllegalArgumentException("Invalid server response");
      }
      for (int j = 0; j < ((NodeList)localObject2).getLength(); j++)
      {
        if ((localObject4 = ((NodeList)localObject2).item(j)).getNodeName().equals("uri"))
        {
          localclass_725.jdField_field_1000_of_type_JavaLangString = ((org.w3c.dom.Node)localObject4).getTextContent();
          System.err.println("URI: " + localclass_725.jdField_field_1000_of_type_JavaLangString);
        }
        if (((org.w3c.dom.Node)localObject4).getNodeName().equals("fid"))
        {
          localclass_725.field_1001 = ((org.w3c.dom.Node)localObject4).getTextContent();
          System.err.println("FID: " + localclass_725.field_1001);
        }
      }
      if ((localclass_725.jdField_field_1000_of_type_JavaLangString == null) || (localclass_725.field_1001 == null)) {
        throw new IllegalArgumentException("could not parse file credentials");
      }
      if ((!field_1820) && (localclass_725.field_1001 == null)) {
        throw new AssertionError();
      }
      paramclass_719.close();
      System.err.println("Logout successfull");
      return localclass_725;
    }
    catch (IOException localIOException)
    {
      if (paramFile == 406) {
        throw new NotLoggedInException();
      }
      if (paramFile == 401) {
        throw new AuthoriationFailedException();
      }
      localIOException.printStackTrace();
      throw localIOException;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      (localObject3 = localParserConfigurationException).printStackTrace();
      throw ((Throwable)localObject3);
    }
    catch (SAXException localSAXException)
    {
      Object localObject3;
      (localObject3 = localSAXException).printStackTrace();
      throw ((Throwable)localObject3);
    }
  }
  
  public static org.dom4j.Document a2(class_719 paramclass_719)
  {
    new StringBuffer();
    Object localObject1;
    (localObject1 = (HttpURLConnection)new URL("http://star-made.org/api/latestnews").openConnection()).setReadTimeout(20000);
    ((HttpURLConnection)localObject1).setRequestProperty("Cookie", paramclass_719.field_990 + "=" + paramclass_719.field_989);
    paramclass_719 = ((HttpURLConnection)localObject1).getResponseCode();
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getInputStream()));
      System.err.println("SS NODE OUTPUT " + paramclass_719);
      localObject2 = a6(localBufferedReader);
      localBufferedReader.close();
      return localObject2;
    }
    catch (IOException localIOException)
    {
      Object localObject2;
      if (paramclass_719 == 406)
      {
        System.err.println("!!!!!!!!!!!!!ERROR CODE 406: " + localIOException.getMessage() + ": " + localObject1 + "; " + ((HttpURLConnection)localObject1).getErrorStream());
        localObject1 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getErrorStream()));
        System.err.println("NODE OUTPUT " + paramclass_719);
        paramclass_719 = new StringBuilder();
        while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
          paramclass_719.append((String)localObject2);
        }
        System.err.println((String)localObject2);
        ((BufferedReader)localObject1).close();
      }
      else
      {
        localIOException.printStackTrace();
      }
      throw localIOException;
    }
  }
  
  public static void a3(class_719 paramclass_719, class_725 paramclass_725)
  {
    String str = "field_file";
    Object localObject1 = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(paramclass_725.field_1002.substring(0, paramclass_725.field_1002.lastIndexOf(".")), "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("shipnode", "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(paramclass_719.jdField_field_988_of_type_JavaLangString, "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(paramclass_725.field_1003, "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("language", "UTF-8") + "=" + URLEncoder.encode("en/US", "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("body[und][0][value]", "UTF-8") + "=" + URLEncoder.encode(new StringBuilder("<p>uploaded by ").append(paramclass_719.jdField_field_988_of_type_JavaLangString).append("</p> ").toString(), "UTF-8");
    if ((!field_1820) && (paramclass_725 == null)) {
      throw new AssertionError();
    }
    if ((!field_1820) && (paramclass_725.field_1001 == null)) {
      throw new AssertionError();
    }
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][fid]", "UTF-8") + "=" + URLEncoder.encode(paramclass_725.field_1001, "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][display]", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][uid]", "UTF-8") + "=" + URLEncoder.encode(paramclass_725.field_1003, "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][uri]", "UTF-8") + "=" + URLEncoder.encode("uri", "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][filemime]", "UTF-8") + "=" + URLEncoder.encode("application/octet-stream", "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][filesize]", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(paramclass_725.jdField_field_1000_of_type_Int), "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][status]", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][type]", "UTF-8") + "=" + URLEncoder.encode("default", "UTF-8");
    System.err.println("NODE DATA: " + (String)localObject1);
    (paramclass_725 = (HttpURLConnection)new URL("http://star-made.org/api/node").openConnection()).setDoOutput(true);
    paramclass_725.setReadTimeout(20000);
    paramclass_725.setRequestProperty("Cookie", paramclass_719.field_990 + "=" + paramclass_719.field_989);
    (paramclass_719 = new OutputStreamWriter(paramclass_725.getOutputStream())).write((String)localObject1);
    paramclass_719.flush();
    int i = paramclass_725.getResponseCode();
    try
    {
      localObject1 = new BufferedReader(new InputStreamReader(paramclass_725.getInputStream()));
      System.err.println("SS NODE OUTPUT " + i);
      while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
        System.err.println((String)localObject2);
      }
      paramclass_719.close();
      ((BufferedReader)localObject1).close();
      return;
    }
    catch (IOException localIOException)
    {
      Object localObject2;
      if (i == 406)
      {
        System.err.println("!!!!!!!!!!!!!ERROR CODE 406: " + localIOException.getMessage());
        localObject2 = new BufferedReader(new InputStreamReader(paramclass_725.getErrorStream()));
        System.err.println("NODE OUTPUT " + i);
        while ((paramclass_725 = ((BufferedReader)localObject2).readLine()) != null) {
          System.err.println(paramclass_725);
        }
        paramclass_719.close();
        ((BufferedReader)localObject2).close();
        return;
      }
      localIOException.printStackTrace();
      throw localIOException;
    }
  }
  
  public static void a4(class_719 paramclass_719, String paramString1, String paramString2)
  {
    System.err.println("Logging on " + paramString1);
    String str = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(paramString1, "UTF-8");
    str = str + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(paramString2, "UTF-8");
    (paramString2 = (HttpURLConnection)new URL("http://star-made.org/api/user/login.xml").openConnection()).setDoOutput(true);
    paramString2.setReadTimeout(20000);
    paramString2.setRequestProperty("Cookie", paramclass_719.field_990 + "=" + paramclass_719.field_989);
    OutputStreamWriter localOutputStreamWriter;
    (localOutputStreamWriter = new OutputStreamWriter(paramString2.getOutputStream())).write(str);
    localOutputStreamWriter.flush();
    int i = paramString2.getResponseCode();
    try
    {
      org.dom4j.Document localDocument = a6(paramString2 = new BufferedReader(new InputStreamReader(paramString2.getInputStream())));
      paramclass_719.field_989 = localDocument.selectSingleNode("//result/sessid").getText();
      paramclass_719.field_990 = localDocument.selectSingleNode("//result/session_name").getText();
      paramclass_719.jdField_field_988_of_type_OrgDom4jDocument = localDocument;
      if (i == 200) {
        paramclass_719.jdField_field_988_of_type_JavaLangString = paramString1;
      } else {
        throw new LoginFailedException(i);
      }
      localOutputStreamWriter.close();
      paramString2.close();
      return;
    }
    catch (IOException paramString2)
    {
      if (i == 406) {
        throw new AlreadyLoggedInException();
      }
      if (i == 401) {
        throw new WrongUserNameOrPasswordException();
      }
      paramString2.printStackTrace();
      throw paramString2;
    }
  }
  
  public static String a5(String paramString1, String paramString2)
  {
    class_719 localclass_719;
    (localclass_719 = new class_719()).field_989 = paramString1;
    localclass_719.field_990 = paramString2;
    (paramString1 = new URL("http://star-made.org/api/system/connect.xml").openConnection()).setDoOutput(true);
    paramString1.setRequestProperty("Cookie", localclass_719.field_990 + "=" + localclass_719.field_989);
    paramString1.setReadTimeout(20000);
    new OutputStreamWriter(paramString1.getOutputStream()).flush();
    paramString2 = a6(paramString1 = new BufferedReader(new InputStreamReader(paramString1.getInputStream())));
    paramString1.close();
    return paramString2.selectSingleNode("//result/user/name").getText();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      a(paramArrayOfString = new class_719());
      a2(paramArrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  private static org.dom4j.Document a6(Reader paramReader)
  {
    return new SAXReader().read(paramReader);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.api.ApiController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */