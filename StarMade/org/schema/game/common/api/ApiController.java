/*     */ package org.schema.game.common.api;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import javax.xml.bind.DatatypeConverter;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import jo;
/*     */ import jp;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.schema.game.common.api.exceptions.AlreadyLoggedInException;
/*     */ import org.schema.game.common.api.exceptions.AuthoriationFailedException;
/*     */ import org.schema.game.common.api.exceptions.LoginFailedException;
/*     */ import org.schema.game.common.api.exceptions.NotLoggedInException;
/*     */ import org.schema.game.common.api.exceptions.WrongUserNameOrPasswordException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class ApiController
/*     */ {
/*     */   public static void a(jp paramjp)
/*     */   {
/*  54 */     (
/*  55 */       localObject = new URL("http://star-made.org/api/system/connect")
/*  54 */       .openConnection())
/*  55 */       .setDoOutput(true);
/*  56 */     ((URLConnection)localObject).setReadTimeout(20000);
/*     */     OutputStreamWriter localOutputStreamWriter;
/*  57 */     (
/*  58 */       localOutputStreamWriter = new OutputStreamWriter(((URLConnection)localObject).getOutputStream()))
/*  58 */       .flush();
/*     */ 
/*  61 */     Object localObject = new BufferedReader(new InputStreamReader(((URLConnection)localObject).getInputStream()));
/*     */     String str;
/*  64 */     while ((str = ((BufferedReader)localObject).readLine()) != null) {
/*  65 */       if (str.startsWith("sessid: "))
/*  66 */         paramjp.b = str.substring(8);
/*  67 */       else if (str.startsWith("user: ")) {
/*  68 */         paramjp.jdField_a_of_type_JavaLangString = str.substring(6);
/*     */       }
/*     */     }
/*  71 */     localOutputStreamWriter.close();
/*  72 */     ((BufferedReader)localObject).close(); } 
/*     */   public static jo a(jp paramjp, File paramFile) { int i;
/*  93 */     Object localObject2 = new byte[i = (int)paramFile.length()];
/*     */ 
/*  94 */     new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile)))
/*  95 */       .readFully((byte[])localObject2);
/*  96 */     localObject2 = DatatypeConverter.printBase64Binary((byte[])localObject2);
/*     */     jo localjo;
/*  97 */     (
/*  98 */       localjo = new jo()).jdField_a_of_type_Int = 
/*  98 */       i;
/*  99 */     Object localObject4 = paramjp.jdField_a_of_type_OrgDom4jDocument.selectSingleNode("//result/user/uid").getText();
/* 100 */     localjo.d = ((String)localObject4);
/*     */ 
/* 102 */     Object localObject5 = (
/* 102 */       paramFile = paramFile.getName())
/* 102 */       .substring(0, paramFile.lastIndexOf("."));
/* 103 */     localObject5 = (String)localObject5 + "_" + (String)localObject4 + "." + paramFile.substring(paramFile.lastIndexOf(".") + 1, paramFile.length());
/* 104 */     localjo.c = paramFile;
/*     */ 
/* 106 */     paramFile = URLEncoder.encode("filesize", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(i), "UTF-8");
/* 107 */     paramFile = paramFile + "&" + URLEncoder.encode("filename", "UTF-8") + "=" + URLEncoder.encode((String)localObject5, "UTF-8");
/* 108 */     paramFile = paramFile + "&" + URLEncoder.encode("filepath", "UTF-8") + "=" + URLEncoder.encode(new StringBuilder("public://apiupload/").append((String)localObject5).toString(), "UTF-8");
/* 109 */     paramFile = paramFile + "&" + URLEncoder.encode("file", "UTF-8") + "=" + URLEncoder.encode((String)localObject2, "UTF-8");
/* 110 */     paramFile = paramFile + "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode((String)localObject4, "UTF-8");
/*     */     Object localObject1;
/* 114 */     (
/* 115 */       localObject1 = (HttpURLConnection)new URL("http://star-made.org/api/file")
/* 114 */       .openConnection())
/* 115 */       .setDoOutput(true);
/* 116 */     ((HttpURLConnection)localObject1).setReadTimeout(20000);
/* 117 */     ((HttpURLConnection)localObject1).setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/*     */ 
/* 119 */     System.err.println("DATA: " + paramFile);
/* 120 */     (
/* 121 */       paramjp = new OutputStreamWriter(((HttpURLConnection)localObject1).getOutputStream()))
/* 121 */       .write(paramFile);
/* 122 */     paramjp.flush();
/*     */ 
/* 124 */     paramFile = ((HttpURLConnection)localObject1).getResponseCode();
/*     */     Object localObject3;
/*     */     try { localObject2 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getInputStream()));
/*     */ 
/* 129 */       System.err.println("LOGOUT OUTPUT " + paramFile);
/*     */ 
/* 136 */       localObject4 = (
/* 136 */         localObject1 = DocumentBuilderFactory.newInstance()
/* 132 */         .newDocumentBuilder()
/* 133 */         .parse(((HttpURLConnection)localObject1).getInputStream())
/* 135 */         .getDocumentElement())
/* 136 */         .getChildNodes();
/* 137 */       if (!((Element)localObject1).getNodeName().equals("result")) {
/* 138 */         throw new IllegalArgumentException("Invalid server response");
/*     */       }
/*     */ 
/* 141 */       for (int j = 0; j < ((NodeList)localObject4).getLength(); j++)
/*     */       {
/* 143 */         if ((
/* 143 */           localObject5 = ((NodeList)localObject4).item(j))
/* 143 */           .getNodeName().equals("uri")) {
/* 144 */           localjo.jdField_a_of_type_JavaLangString = ((org.w3c.dom.Node)localObject5).getTextContent();
/* 145 */           System.err.println("URI: " + localjo.jdField_a_of_type_JavaLangString);
/*     */         }
/* 147 */         if (((org.w3c.dom.Node)localObject5).getNodeName().equals("fid")) {
/* 148 */           localjo.b = ((org.w3c.dom.Node)localObject5).getTextContent();
/* 149 */           System.err.println("FID: " + localjo.b);
/*     */         }
/*     */       }
/* 152 */       if ((localjo.jdField_a_of_type_JavaLangString == null) || (localjo.b == null))
/* 153 */         throw new IllegalArgumentException("could not parse file credentials");
/*     */       String str;
/* 155 */       while ((str = ((BufferedReader)localObject2).readLine()) != null) {
/* 156 */         System.err.println(str);
/* 157 */         if (str.startsWith("uri")) {
/* 158 */           localjo.jdField_a_of_type_JavaLangString = str.substring(3);
/*     */         }
/* 160 */         if (str.startsWith("fid")) {
/* 161 */           localjo.b = str.substring(3);
/*     */         }
/*     */       }
/* 164 */       if ((!a) && (localjo.b == null)) throw new AssertionError();
/* 165 */       paramjp.close();
/* 166 */       ((BufferedReader)localObject2).close();
/* 167 */       System.err.println("Logout successfull");
/* 168 */       return localjo;
/*     */     } catch (IOException localIOException) {
/* 170 */       if (paramFile == 406)
/* 171 */         throw new NotLoggedInException();
/* 172 */       if (paramFile == 401) {
/* 173 */         throw new AuthoriationFailedException();
/*     */       }
/* 175 */       localIOException.printStackTrace();
/* 176 */       throw localIOException;
/*     */     } catch (ParserConfigurationException localParserConfigurationException) {
/* 178 */       (
/* 179 */         localObject3 = localParserConfigurationException)
/* 179 */         .printStackTrace();
/* 180 */       throw ((Throwable)localObject3); } catch (SAXException localSAXException) {
/* 181 */       (
/* 182 */         localObject3 = localSAXException)
/* 182 */         .printStackTrace();
/* 183 */     }throw ((Throwable)localObject3);
/*     */   }
/*     */ 
/*     */   public static org.dom4j.Document a(jp paramjp)
/*     */   {
/* 197 */     new StringBuffer();
/*     */     Object localObject1;
/* 203 */     (
/* 205 */       localObject1 = (HttpURLConnection)new URL("http://star-made.org/api/latestnews")
/* 203 */       .openConnection())
/* 205 */       .setReadTimeout(20000);
/* 206 */     ((HttpURLConnection)localObject1).setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/*     */ 
/* 213 */     paramjp = ((HttpURLConnection)localObject1).getResponseCode();
/*     */     try
/*     */     {
/* 217 */       BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getInputStream()));
/*     */ 
/* 219 */       System.err.println("SS NODE OUTPUT " + paramjp);
/*     */ 
/* 227 */       localObject2 = a(localBufferedReader);
/* 228 */       localBufferedReader.close();
/* 229 */       return localObject2;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */       Object localObject2;
/* 237 */       if (paramjp == 406) {
/* 238 */         System.err.println("!!!!!!!!!!!!!ERROR CODE 406: " + localIOException.getMessage() + ": " + localObject1 + "; " + ((HttpURLConnection)localObject1).getErrorStream());
/*     */ 
/* 240 */         localObject1 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getErrorStream()));
/*     */ 
/* 242 */         System.err.println("NODE OUTPUT " + paramjp);
/*     */ 
/* 244 */         paramjp = new StringBuilder();
/* 245 */         while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
/* 246 */           paramjp.append((String)localObject2);
/*     */         }
/* 248 */         System.err.println((String)localObject2);
/*     */ 
/* 250 */         ((BufferedReader)localObject1).close();
/*     */       } else {
/* 252 */         localIOException.printStackTrace();
/*     */       }
/* 254 */       throw localIOException;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(jp paramjp, jo paramjo) {
/* 259 */     String str = "field_file";
/*     */ 
/* 261 */     Object localObject1 = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(paramjo.c.substring(0, paramjo.c.lastIndexOf(".")), "UTF-8");
/* 262 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("shipnode", "UTF-8");
/* 263 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(paramjp.jdField_a_of_type_JavaLangString, "UTF-8");
/* 264 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(paramjo.d, "UTF-8");
/*     */ 
/* 266 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode("language", "UTF-8") + "=" + URLEncoder.encode("en/US", "UTF-8");
/*     */ 
/* 268 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode("body[und][0][value]", "UTF-8") + "=" + URLEncoder.encode(new StringBuilder("<p>uploaded by ").append(paramjp.jdField_a_of_type_JavaLangString).append("</p> ").toString(), "UTF-8");
/*     */ 
/* 291 */     if ((!a) && (paramjo == null)) throw new AssertionError();
/* 292 */     if ((!a) && (paramjo.b == null)) throw new AssertionError();
/*     */ 
/* 294 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 295 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][fid]", "UTF-8") + "=" + URLEncoder.encode(paramjo.b, "UTF-8");
/*     */ 
/* 297 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 298 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][display]", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
/*     */ 
/* 300 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 301 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][uid]", "UTF-8") + "=" + URLEncoder.encode(paramjo.d, "UTF-8");
/*     */ 
/* 303 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 304 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][uri]", "UTF-8") + "=" + URLEncoder.encode("uri", "UTF-8");
/*     */ 
/* 306 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 307 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][filemime]", "UTF-8") + "=" + URLEncoder.encode("application/octet-stream", "UTF-8");
/*     */ 
/* 309 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 310 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][filesize]", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(paramjo.jdField_a_of_type_Int), "UTF-8");
/*     */ 
/* 312 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 313 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][status]", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
/*     */ 
/* 315 */     localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 316 */     localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][type]", "UTF-8") + "=" + URLEncoder.encode("default", "UTF-8");
/*     */ 
/* 325 */     System.err.println("NODE DATA: " + (String)localObject1);
/*     */ 
/* 333 */     (
/* 334 */       paramjo = (HttpURLConnection)new URL("http://star-made.org/api/node")
/* 333 */       .openConnection())
/* 334 */       .setDoOutput(true);
/* 335 */     paramjo.setReadTimeout(20000);
/* 336 */     paramjo.setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/*     */ 
/* 339 */     (
/* 340 */       paramjp = new OutputStreamWriter(paramjo.getOutputStream()))
/* 340 */       .write((String)localObject1);
/* 341 */     paramjp.flush();
/*     */ 
/* 343 */     int i = paramjo.getResponseCode();
/*     */     try
/*     */     {
/* 347 */       localObject1 = new BufferedReader(new InputStreamReader(paramjo.getInputStream()));
/*     */ 
/* 349 */       System.err.println("SS NODE OUTPUT " + i);
/* 350 */       while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
/* 351 */         System.err.println((String)localObject2);
/*     */       }
/*     */ 
/* 354 */       paramjp.close();
/* 355 */       ((BufferedReader)localObject1).close();
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */       Object localObject2;
/* 357 */       if (i == 406) {
/* 358 */         System.err.println("!!!!!!!!!!!!!ERROR CODE 406: " + localIOException.getMessage());
/*     */ 
/* 360 */         localObject2 = new BufferedReader(new InputStreamReader(paramjo.getErrorStream()));
/*     */ 
/* 362 */         System.err.println("NODE OUTPUT " + i);
/* 363 */         while ((paramjo = ((BufferedReader)localObject2).readLine()) != null) {
/* 364 */           System.err.println(paramjo);
/*     */         }
/*     */ 
/* 367 */         paramjp.close();
/* 368 */         ((BufferedReader)localObject2).close();
/* 369 */         return;
/* 370 */       }localIOException.printStackTrace();
/* 371 */       throw localIOException;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(jp paramjp, String paramString1, String paramString2)
/*     */   {
/* 420 */     System.err.println("Logging on " + paramString1);
/*     */ 
/* 422 */     String str = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(paramString1, "UTF-8");
/* 423 */     str = str + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(paramString2, "UTF-8");
/*     */ 
/* 428 */     (
/* 429 */       paramString2 = (HttpURLConnection)new URL("http://star-made.org/api/user/login.xml")
/* 428 */       .openConnection())
/* 429 */       .setDoOutput(true);
/* 430 */     paramString2.setReadTimeout(20000);
/* 431 */     paramString2.setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/*     */     OutputStreamWriter localOutputStreamWriter;
/* 434 */     (
/* 435 */       localOutputStreamWriter = new OutputStreamWriter(paramString2.getOutputStream()))
/* 435 */       .write(str);
/* 436 */     localOutputStreamWriter.flush();
/*     */ 
/* 438 */     int i = paramString2.getResponseCode();
/*     */     try
/*     */     {
/* 442 */       org.dom4j.Document localDocument = a(paramString2 = new BufferedReader(new InputStreamReader(paramString2.getInputStream())));
/*     */ 
/* 444 */       paramjp.b = localDocument.selectSingleNode("//result/sessid").getText();
/* 445 */       paramjp.c = localDocument.selectSingleNode("//result/session_name").getText();
/* 446 */       paramjp.jdField_a_of_type_OrgDom4jDocument = localDocument;
/*     */ 
/* 458 */       if (i == 200)
/* 459 */         paramjp.jdField_a_of_type_JavaLangString = paramString1;
/*     */       else {
/* 461 */         throw new LoginFailedException(i);
/* 463 */       }localOutputStreamWriter.close();
/* 464 */       paramString2.close();
/*     */       return; } catch (IOException paramString2) {
/* 467 */       if (i == 406)
/* 468 */         throw new AlreadyLoggedInException();
/* 469 */       if (i == 401) {
/* 470 */         throw new WrongUserNameOrPasswordException();
/*     */       }
/* 472 */       paramString2.printStackTrace();
/* 473 */     }throw paramString2;
/*     */   }
/*     */ 
/*     */   public static String a(String paramString1, String paramString2)
/*     */   {
/*     */     jp localjp;
/* 485 */     (
/* 486 */       localjp = new jp()).b = 
/* 486 */       paramString1;
/* 487 */     localjp.c = paramString2;
/*     */ 
/* 490 */     (
/* 491 */       paramString1 = new URL("http://star-made.org/api/system/connect.xml")
/* 490 */       .openConnection())
/* 491 */       .setDoOutput(true);
/* 492 */     paramString1.setRequestProperty("Cookie", localjp.c + "=" + localjp.b);
/* 493 */     paramString1.setReadTimeout(20000);
/*     */ 
/* 495 */     new OutputStreamWriter(paramString1.getOutputStream())
/* 498 */       .flush();
/*     */ 
/* 502 */     paramString2 = a(paramString1 = new BufferedReader(new InputStreamReader(paramString1.getInputStream())));
/*     */ 
/* 504 */     paramString1.close();
/*     */ 
/* 506 */     return paramString2.selectSingleNode("//result/user/name").getText();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*     */     try
/*     */     {
/* 586 */       a(paramArrayOfString = new jp());
/*     */ 
/* 589 */       a(paramArrayOfString);
/*     */       return;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 594 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static org.dom4j.Document a(Reader paramReader)
/*     */   {
/* 602 */     return new SAXReader()
/* 601 */       .read(paramReader);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.api.ApiController
 * JD-Core Version:    0.6.2
 */