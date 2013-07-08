/*   1:    */package org.schema.game.common.api;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.BufferedReader;
/*   5:    */import java.io.DataInputStream;
/*   6:    */import java.io.File;
/*   7:    */import java.io.FileInputStream;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.InputStreamReader;
/*  10:    */import java.io.OutputStreamWriter;
/*  11:    */import java.io.PrintStream;
/*  12:    */import java.io.Reader;
/*  13:    */import java.net.HttpURLConnection;
/*  14:    */import java.net.URL;
/*  15:    */import java.net.URLConnection;
/*  16:    */import java.net.URLEncoder;
/*  17:    */import javax.xml.bind.DatatypeConverter;
/*  18:    */import javax.xml.parsers.DocumentBuilder;
/*  19:    */import javax.xml.parsers.DocumentBuilderFactory;
/*  20:    */import javax.xml.parsers.ParserConfigurationException;
/*  21:    */import jo;
/*  22:    */import jp;
/*  23:    */import org.dom4j.io.SAXReader;
/*  24:    */import org.schema.game.common.api.exceptions.AlreadyLoggedInException;
/*  25:    */import org.schema.game.common.api.exceptions.AuthoriationFailedException;
/*  26:    */import org.schema.game.common.api.exceptions.LoginFailedException;
/*  27:    */import org.schema.game.common.api.exceptions.NotLoggedInException;
/*  28:    */import org.schema.game.common.api.exceptions.WrongUserNameOrPasswordException;
/*  29:    */import org.w3c.dom.Element;
/*  30:    */import org.w3c.dom.NodeList;
/*  31:    */import org.xml.sax.SAXException;
/*  32:    */
/*  51:    */public class ApiController
/*  52:    */{
/*  53:    */  public static void a(jp paramjp)
/*  54:    */  {
/*  55: 55 */    (localObject = new URL("http://star-made.org/api/system/connect").openConnection()).setDoOutput(true);
/*  56: 56 */    ((URLConnection)localObject).setReadTimeout(20000);
/*  57:    */    OutputStreamWriter localOutputStreamWriter;
/*  58: 58 */    (localOutputStreamWriter = new OutputStreamWriter(((URLConnection)localObject).getOutputStream())).flush();
/*  59:    */    
/*  61: 61 */    Object localObject = new BufferedReader(new InputStreamReader(((URLConnection)localObject).getInputStream()));
/*  62:    */    
/*  63:    */    String str;
/*  64: 64 */    while ((str = ((BufferedReader)localObject).readLine()) != null) {
/*  65: 65 */      if (str.startsWith("sessid: ")) {
/*  66: 66 */        paramjp.b = str.substring(8);
/*  67: 67 */      } else if (str.startsWith("user: ")) {
/*  68: 68 */        paramjp.jdField_a_of_type_JavaLangString = str.substring(6);
/*  69:    */      }
/*  70:    */    }
/*  71: 71 */    localOutputStreamWriter.close();
/*  72: 72 */    ((BufferedReader)localObject).close();
/*  73:    */  }
/*  74:    */  
/*  83:    */  public static jo a(jp paramjp, File paramFile)
/*  84:    */  {
/*  85:    */    int i;
/*  86:    */    
/*  93: 93 */    Object localObject2 = new byte[i = (int)paramFile.length()];
/*  94: 94 */    new DataInputStream(new BufferedInputStream(new FileInputStream(paramFile)))
/*  95: 95 */      .readFully((byte[])localObject2);
/*  96: 96 */    localObject2 = DatatypeConverter.printBase64Binary((byte[])localObject2);
/*  97:    */    jo localjo;
/*  98: 98 */    (localjo = new jo()).jdField_a_of_type_Int = i;
/*  99: 99 */    Object localObject4 = paramjp.jdField_a_of_type_OrgDom4jDocument.selectSingleNode("//result/user/uid").getText();
/* 100:100 */    localjo.d = ((String)localObject4);
/* 101:    */    
/* 102:102 */    String str = (paramFile = paramFile.getName()).substring(0, paramFile.lastIndexOf("."));
/* 103:103 */    str = str + "_" + (String)localObject4 + "." + paramFile.substring(paramFile.lastIndexOf(".") + 1, paramFile.length());
/* 104:104 */    localjo.c = paramFile;
/* 105:    */    
/* 106:106 */    paramFile = URLEncoder.encode("filesize", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(i), "UTF-8");
/* 107:107 */    paramFile = paramFile + "&" + URLEncoder.encode("filename", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8");
/* 108:108 */    paramFile = paramFile + "&" + URLEncoder.encode("filepath", "UTF-8") + "=" + URLEncoder.encode(new StringBuilder("public://apiupload/").append(str).toString(), "UTF-8");
/* 109:109 */    paramFile = paramFile + "&" + URLEncoder.encode("file", "UTF-8") + "=" + URLEncoder.encode((String)localObject2, "UTF-8");
/* 110:110 */    paramFile = paramFile + "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode((String)localObject4, "UTF-8");
/* 111:    */    
/* 113:    */    Object localObject1;
/* 114:    */    
/* 115:115 */    (localObject1 = (HttpURLConnection)new URL("http://star-made.org/api/file").openConnection()).setDoOutput(true);
/* 116:116 */    ((HttpURLConnection)localObject1).setReadTimeout(20000);
/* 117:117 */    ((HttpURLConnection)localObject1).setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/* 118:    */    
/* 119:119 */    System.err.println("DATA: " + paramFile);
/* 120:120 */    (
/* 121:121 */      paramjp = new OutputStreamWriter(((HttpURLConnection)localObject1).getOutputStream())).write(paramFile);
/* 122:122 */    paramjp.flush();
/* 123:    */    
/* 124:124 */    paramFile = ((HttpURLConnection)localObject1).getResponseCode();
/* 125:    */    
/* 127:    */    try
/* 128:    */    {
/* 129:129 */      System.err.println("UPLOAD OUTPUT " + paramFile);
/* 130:    */      
/* 136:136 */      localObject2 = (localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(((HttpURLConnection)localObject1).getInputStream()).getDocumentElement()).getChildNodes();
/* 137:137 */      if (!((Element)localObject1).getNodeName().equals("result")) {
/* 138:138 */        throw new IllegalArgumentException("Invalid server response");
/* 139:    */      }
/* 140:    */      
/* 141:141 */      for (int j = 0; j < ((NodeList)localObject2).getLength(); j++)
/* 142:    */      {
/* 143:143 */        if ((localObject4 = ((NodeList)localObject2).item(j)).getNodeName().equals("uri")) {
/* 144:144 */          localjo.jdField_a_of_type_JavaLangString = ((org.w3c.dom.Node)localObject4).getTextContent();
/* 145:145 */          System.err.println("URI: " + localjo.jdField_a_of_type_JavaLangString);
/* 146:    */        }
/* 147:147 */        if (((org.w3c.dom.Node)localObject4).getNodeName().equals("fid")) {
/* 148:148 */          localjo.b = ((org.w3c.dom.Node)localObject4).getTextContent();
/* 149:149 */          System.err.println("FID: " + localjo.b);
/* 150:    */        }
/* 151:    */      }
/* 152:152 */      if ((localjo.jdField_a_of_type_JavaLangString == null) || (localjo.b == null)) {
/* 153:153 */        throw new IllegalArgumentException("could not parse file credentials");
/* 154:    */      }
/* 155:    */      
/* 165:165 */      if ((!a) && (localjo.b == null)) throw new AssertionError();
/* 166:166 */      paramjp.close();
/* 167:    */      
/* 168:168 */      System.err.println("Logout successfull");
/* 169:169 */      return localjo;
/* 170:    */    } catch (IOException localIOException) {
/* 171:171 */      if (paramFile == 406)
/* 172:172 */        throw new NotLoggedInException();
/* 173:173 */      if (paramFile == 401) {
/* 174:174 */        throw new AuthoriationFailedException();
/* 175:    */      }
/* 176:176 */      localIOException.printStackTrace();
/* 177:177 */      throw localIOException;
/* 178:    */    }
/* 179:    */    catch (ParserConfigurationException localParserConfigurationException) {
/* 180:180 */      (localObject3 = localParserConfigurationException).printStackTrace();
/* 181:181 */      throw ((Throwable)localObject3);
/* 182:    */    } catch (SAXException localSAXException) { Object localObject3;
/* 183:183 */      (localObject3 = localSAXException).printStackTrace();
/* 184:184 */      throw ((Throwable)localObject3);
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 196:    */  public static org.dom4j.Document a(jp paramjp)
/* 197:    */  {
/* 198:198 */    new StringBuffer();
/* 199:    */    
/* 202:    */    Object localObject1;
/* 203:    */    
/* 206:206 */    (localObject1 = (HttpURLConnection)new URL("http://star-made.org/api/latestnews").openConnection()).setReadTimeout(20000);
/* 207:207 */    ((HttpURLConnection)localObject1).setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/* 208:    */    
/* 214:214 */    paramjp = ((HttpURLConnection)localObject1).getResponseCode();
/* 215:    */    
/* 216:    */    try
/* 217:    */    {
/* 218:218 */      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getInputStream()));
/* 219:    */      
/* 220:220 */      System.err.println("SS NODE OUTPUT " + paramjp);
/* 221:    */      
/* 228:228 */      localObject2 = a(localBufferedReader);
/* 229:229 */      localBufferedReader.close();
/* 230:230 */      return localObject2;
/* 231:    */    }
/* 232:    */    catch (IOException localIOException)
/* 233:    */    {
/* 234:    */      Object localObject2;
/* 235:    */      
/* 238:238 */      if (paramjp == 406) {
/* 239:239 */        System.err.println("!!!!!!!!!!!!!ERROR CODE 406: " + localIOException.getMessage() + ": " + localObject1 + "; " + ((HttpURLConnection)localObject1).getErrorStream());
/* 240:    */        
/* 241:241 */        localObject1 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getErrorStream()));
/* 242:    */        
/* 243:243 */        System.err.println("NODE OUTPUT " + paramjp);
/* 244:    */        
/* 245:245 */        paramjp = new StringBuilder();
/* 246:246 */        while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
/* 247:247 */          paramjp.append((String)localObject2);
/* 248:    */        }
/* 249:249 */        System.err.println((String)localObject2);
/* 250:    */        
/* 251:251 */        ((BufferedReader)localObject1).close();
/* 252:    */      } else {
/* 253:253 */        localIOException.printStackTrace();
/* 254:    */      }
/* 255:255 */      throw localIOException;
/* 256:    */    }
/* 257:    */  }
/* 258:    */  
/* 259:    */  public static void a(jp paramjp, jo paramjo) {
/* 260:260 */    String str = "field_file";
/* 261:    */    
/* 262:262 */    Object localObject1 = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(paramjo.c.substring(0, paramjo.c.lastIndexOf(".")), "UTF-8");
/* 263:263 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("shipnode", "UTF-8");
/* 264:264 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(paramjp.jdField_a_of_type_JavaLangString, "UTF-8");
/* 265:265 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(paramjo.d, "UTF-8");
/* 266:    */    
/* 267:267 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("language", "UTF-8") + "=" + URLEncoder.encode("en/US", "UTF-8");
/* 268:    */    
/* 269:269 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode("body[und][0][value]", "UTF-8") + "=" + URLEncoder.encode(new StringBuilder("<p>uploaded by ").append(paramjp.jdField_a_of_type_JavaLangString).append("</p> ").toString(), "UTF-8");
/* 270:    */    
/* 292:292 */    if ((!a) && (paramjo == null)) throw new AssertionError();
/* 293:293 */    if ((!a) && (paramjo.b == null)) { throw new AssertionError();
/* 294:    */    }
/* 295:295 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 296:296 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][fid]", "UTF-8") + "=" + URLEncoder.encode(paramjo.b, "UTF-8");
/* 297:    */    
/* 298:298 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 299:299 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][display]", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
/* 300:    */    
/* 301:301 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 302:302 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][uid]", "UTF-8") + "=" + URLEncoder.encode(paramjo.d, "UTF-8");
/* 303:    */    
/* 304:304 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 305:305 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][uri]", "UTF-8") + "=" + URLEncoder.encode("uri", "UTF-8");
/* 306:    */    
/* 307:307 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 308:308 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][filemime]", "UTF-8") + "=" + URLEncoder.encode("application/octet-stream", "UTF-8");
/* 309:    */    
/* 310:310 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 311:311 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][filesize]", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(paramjo.jdField_a_of_type_Int), "UTF-8");
/* 312:    */    
/* 313:313 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 314:314 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][status]", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
/* 315:    */    
/* 316:316 */    localObject1 = (String)localObject1 + "&" + URLEncoder.encode(str, "UTF-8");
/* 317:317 */    localObject1 = (String)localObject1 + URLEncoder.encode("[und][0][type]", "UTF-8") + "=" + URLEncoder.encode("default", "UTF-8");
/* 318:    */    
/* 326:326 */    System.err.println("NODE DATA: " + (String)localObject1);
/* 327:    */    
/* 334:334 */    (
/* 335:335 */      paramjo = (HttpURLConnection)new URL("http://star-made.org/api/node").openConnection()).setDoOutput(true);
/* 336:336 */    paramjo.setReadTimeout(20000);
/* 337:337 */    paramjo.setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/* 338:    */    
/* 340:340 */    (
/* 341:341 */      paramjp = new OutputStreamWriter(paramjo.getOutputStream())).write((String)localObject1);
/* 342:342 */    paramjp.flush();
/* 343:    */    
/* 344:344 */    int i = paramjo.getResponseCode();
/* 345:    */    
/* 346:    */    try
/* 347:    */    {
/* 348:348 */      localObject1 = new BufferedReader(new InputStreamReader(paramjo.getInputStream()));
/* 349:    */      
/* 350:350 */      System.err.println("SS NODE OUTPUT " + i);
/* 351:351 */      while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null) {
/* 352:352 */        System.err.println((String)localObject2);
/* 353:    */      }
/* 354:    */      
/* 355:355 */      paramjp.close();
/* 356:356 */      ((BufferedReader)localObject1).close(); return;
/* 357:    */    } catch (IOException localIOException) { Object localObject2;
/* 358:358 */      if (i == 406) {
/* 359:359 */        System.err.println("!!!!!!!!!!!!!ERROR CODE 406: " + localIOException.getMessage());
/* 360:    */        
/* 361:361 */        localObject2 = new BufferedReader(new InputStreamReader(paramjo.getErrorStream()));
/* 362:    */        
/* 363:363 */        System.err.println("NODE OUTPUT " + i);
/* 364:364 */        while ((paramjo = ((BufferedReader)localObject2).readLine()) != null) {
/* 365:365 */          System.err.println(paramjo);
/* 366:    */        }
/* 367:    */        
/* 368:368 */        paramjp.close();
/* 369:369 */        ((BufferedReader)localObject2).close();
/* 370:370 */        return; }
/* 371:371 */      localIOException.printStackTrace();
/* 372:372 */      throw localIOException;
/* 373:    */    }
/* 374:    */  }
/* 375:    */  
/* 419:    */  public static void a(jp paramjp, String paramString1, String paramString2)
/* 420:    */  {
/* 421:421 */    System.err.println("Logging on " + paramString1);
/* 422:    */    
/* 423:423 */    String str = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(paramString1, "UTF-8");
/* 424:424 */    str = str + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(paramString2, "UTF-8");
/* 425:    */    
/* 429:429 */    (
/* 430:430 */      paramString2 = (HttpURLConnection)new URL("http://star-made.org/api/user/login.xml").openConnection()).setDoOutput(true);
/* 431:431 */    paramString2.setReadTimeout(20000);
/* 432:432 */    paramString2.setRequestProperty("Cookie", paramjp.c + "=" + paramjp.b);
/* 433:    */    
/* 434:    */    OutputStreamWriter localOutputStreamWriter;
/* 435:    */    
/* 436:436 */    (localOutputStreamWriter = new OutputStreamWriter(paramString2.getOutputStream())).write(str);
/* 437:437 */    localOutputStreamWriter.flush();
/* 438:    */    
/* 439:439 */    int i = paramString2.getResponseCode();
/* 440:    */    
/* 441:    */    try
/* 442:    */    {
/* 443:443 */      org.dom4j.Document localDocument = a(paramString2 = new BufferedReader(new InputStreamReader(paramString2.getInputStream())));
/* 444:    */      
/* 445:445 */      paramjp.b = localDocument.selectSingleNode("//result/sessid").getText();
/* 446:446 */      paramjp.c = localDocument.selectSingleNode("//result/session_name").getText();
/* 447:447 */      paramjp.jdField_a_of_type_OrgDom4jDocument = localDocument;
/* 448:    */      
/* 459:459 */      if (i == 200) {
/* 460:460 */        paramjp.jdField_a_of_type_JavaLangString = paramString1;
/* 461:    */      } else {
/* 462:462 */        throw new LoginFailedException(i);
/* 463:    */      }
/* 464:464 */      localOutputStreamWriter.close();
/* 465:465 */      paramString2.close(); return;
/* 466:    */    }
/* 467:    */    catch (IOException paramString2) {
/* 468:468 */      if (i == 406)
/* 469:469 */        throw new AlreadyLoggedInException();
/* 470:470 */      if (i == 401) {
/* 471:471 */        throw new WrongUserNameOrPasswordException();
/* 472:    */      }
/* 473:473 */      paramString2.printStackTrace();
/* 474:474 */      throw paramString2;
/* 475:    */    }
/* 476:    */  }
/* 477:    */  
/* 481:    */  public static String a(String paramString1, String paramString2)
/* 482:    */  {
/* 483:    */    jp localjp;
/* 484:    */    
/* 487:487 */    (localjp = new jp()).b = paramString1;
/* 488:488 */    localjp.c = paramString2;
/* 489:    */    
/* 491:491 */    (
/* 492:492 */      paramString1 = new URL("http://star-made.org/api/system/connect.xml").openConnection()).setDoOutput(true);
/* 493:493 */    paramString1.setRequestProperty("Cookie", localjp.c + "=" + localjp.b);
/* 494:494 */    paramString1.setReadTimeout(20000);
/* 495:    */    
/* 496:496 */    new OutputStreamWriter(paramString1.getOutputStream())
/* 497:    */    
/* 499:499 */      .flush();
/* 500:    */    
/* 503:503 */    paramString2 = a(paramString1 = new BufferedReader(new InputStreamReader(paramString1.getInputStream())));
/* 504:    */    
/* 505:505 */    paramString1.close();
/* 506:    */    
/* 507:507 */    return paramString2.selectSingleNode("//result/user/name").getText();
/* 508:    */  }
/* 509:    */  
/* 583:    */  public static void main(String[] paramArrayOfString)
/* 584:    */  {
/* 585:    */    try
/* 586:    */    {
/* 587:587 */      a(paramArrayOfString = new jp());
/* 588:    */      
/* 590:590 */      a(paramArrayOfString); return;
/* 591:    */    }
/* 592:    */    catch (Exception localException) {
/* 593:593 */      
/* 594:    */      
/* 595:595 */        localException;
/* 596:    */    }
/* 597:    */  }
/* 598:    */  
/* 603:    */  private static org.dom4j.Document a(Reader paramReader)
/* 604:    */  {
/* 605:603 */    return new SAXReader().read(paramReader);
/* 606:    */  }
/* 607:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.api.ApiController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */