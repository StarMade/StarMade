/*   1:    */import java.io.PrintStream;
/*   2:    */import java.io.Reader;
/*   3:    */import java.io.StringReader;
/*   4:    */import java.io.StringWriter;
/*   5:    */import java.text.SimpleDateFormat;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.List;
/*   9:    */import javax.swing.text.MutableAttributeSet;
/*  10:    */import javax.swing.text.html.HTML.Tag;
/*  11:    */import javax.swing.text.html.HTMLEditorKit.ParserCallback;
/*  12:    */import org.dom4j.Document;
/*  13:    */import org.dom4j.Node;
/*  14:    */import org.schema.game.common.api.ApiController;
/*  15:    */import org.w3c.tidy.Tidy;
/*  16:    */
/*  44:    */public final class sq
/*  45:    */  extends HTMLEditorKit.ParserCallback
/*  46:    */{
/*  47:    */  private HTML.Tag a;
/*  48:    */  
/*  49:    */  static
/*  50:    */  {
/*  51: 51 */    new SimpleDateFormat("M/d/yyyy - h:mm a");
/*  52:    */  }
/*  53:    */  
/*  55:    */  public static ArrayList a()
/*  56:    */  {
/*  57: 57 */    ApiController.a(localObject1 = new jp());
/*  58: 58 */    System.err.println("Session: " + localObject1 + "\n");
/*  59: 59 */    (
/*  60:    */    
/*  62: 62 */      localObject1 = ApiController.a((jp)localObject1)).getRootElement();
/*  63:    */    
/*  65: 65 */    Object localObject1 = ((Document)localObject1).selectNodes("//result/item");
/*  66:    */    
/*  68: 68 */    ArrayList localArrayList = new ArrayList();
/*  69: 69 */    for (localObject1 = ((List)localObject1).iterator(); ((Iterator)localObject1).hasNext();) {
/*  70: 70 */      Object localObject3 = (localObject2 = (Node)((Iterator)localObject1).next()).selectSingleNode("Body");
/*  71: 71 */      Object localObject2 = ((Node)localObject2).selectSingleNode("node_title");
/*  72:    */      
/*  73: 73 */      Tidy localTidy = new Tidy();
/*  74: 74 */      localObject3 = new StringReader(((Node)localObject3).getText().replaceAll("Â", ""));
/*  75:    */      
/*  76: 76 */      StringWriter localStringWriter = new StringWriter();
/*  77:    */      
/*  78: 78 */      localTidy.parse((Reader)localObject3, localStringWriter);
/*  79:    */      
/*  86: 86 */      localObject2 = localStringWriter.getBuffer().toString().replaceFirst("<body>", "<body>\n<h1>" + ((Node)localObject2).getText() + "</h1>");
/*  87:    */      
/* 108:108 */      localArrayList.add(localObject2);
/* 109:    */    }
/* 110:    */    
/* 113:113 */    return localArrayList;
/* 114:    */  }
/* 115:    */  
/* 188:    */  public final void handleEndOfLineString(String paramString)
/* 189:    */  {
/* 190:190 */    super.handleEndOfLineString(paramString);
/* 191:191 */    null.delete(0, null.indexOf("\n") + 3);
/* 192:192 */    paramString = "Log in or register to post comments";
/* 193:193 */    int i = 0;
/* 194:194 */    while ((i = null.indexOf(paramString)) >= 0) {
/* 195:195 */      null.delete(i, i + paramString.length());
/* 196:    */    }
/* 197:    */  }
/* 198:    */  
/* 203:    */  public final void handleEndTag(HTML.Tag paramTag, int paramInt)
/* 204:    */  {
/* 205:205 */    if (this.a == paramTag) {
/* 206:206 */      null.append("\n\n");
/* 207:207 */      this.a = null;
/* 208:    */    }
/* 209:    */    
/* 210:210 */    super.handleEndTag(paramTag, paramInt);
/* 211:    */  }
/* 212:    */  
/* 218:    */  public final void handleStartTag(HTML.Tag paramTag, MutableAttributeSet paramMutableAttributeSet, int paramInt)
/* 219:    */  {
/* 220:    */    String str;
/* 221:    */    
/* 225:225 */    if ((str = paramMutableAttributeSet.toString()).contains("class=meta submitted")) {
/* 226:226 */      this.a = paramTag;
/* 227:227 */      null.append("\n");
/* 228:    */    }
/* 229:229 */    if (str.contains("node node-news-entry")) {
/* 230:230 */      null.append("\n\n\n");
/* 231:231 */      this.a = paramTag;
/* 232:    */    }
/* 233:233 */    super.handleStartTag(paramTag, paramMutableAttributeSet, paramInt);
/* 234:    */  }
/* 235:    */  
/* 236:    */  public final void handleText(char[] paramArrayOfChar, int paramInt)
/* 237:    */  {
/* 238:238 */    null.append(paramArrayOfChar);
/* 239:    */  }
/* 240:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */