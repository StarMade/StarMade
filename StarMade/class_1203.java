import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import org.dom4j.Document;
import org.dom4j.Node;
import org.schema.game.common.api.ApiController;
import org.w3c.tidy.Tidy;

public final class class_1203
  extends HTMLEditorKit.ParserCallback
{
  private HTML.Tag field_1422;
  
  public static ArrayList a()
  {
    ApiController.a(localObject1 = new class_719());
    System.err.println("Session: " + localObject1 + "\n");
    (localObject1 = ApiController.a2((class_719)localObject1)).getRootElement();
    Object localObject1 = ((Document)localObject1).selectNodes("//result/item");
    ArrayList localArrayList = new ArrayList();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (localObject2 = (Node)((Iterator)localObject1).next()).selectSingleNode("Body");
      Object localObject2 = ((Node)localObject2).selectSingleNode("node_title");
      Tidy localTidy = new Tidy();
      localObject3 = new StringReader(((Node)localObject3).getText().replaceAll("Â", ""));
      StringWriter localStringWriter = new StringWriter();
      localTidy.parse((Reader)localObject3, localStringWriter);
      localObject2 = localStringWriter.getBuffer().toString().replaceFirst("<body>", "<body>\n<h1>" + ((Node)localObject2).getText() + "</h1>");
      localArrayList.add(localObject2);
    }
    return localArrayList;
  }
  
  public final void handleEndOfLineString(String paramString)
  {
    super.handleEndOfLineString(paramString);
    null.delete(0, null.indexOf("\n") + 3);
    paramString = "Log in or register to post comments";
    int i = 0;
    while ((i = null.indexOf(paramString)) >= 0) {
      null.delete(i, i + paramString.length());
    }
  }
  
  public final void handleEndTag(HTML.Tag paramTag, int paramInt)
  {
    if (this.field_1422 == paramTag)
    {
      null.append("\n\n");
      this.field_1422 = null;
    }
    super.handleEndTag(paramTag, paramInt);
  }
  
  public final void handleStartTag(HTML.Tag paramTag, MutableAttributeSet paramMutableAttributeSet, int paramInt)
  {
    String str;
    if ((str = paramMutableAttributeSet.toString()).contains("class=meta submitted"))
    {
      this.field_1422 = paramTag;
      null.append("\n");
    }
    if (str.contains("node node-news-entry"))
    {
      null.append("\n\n\n");
      this.field_1422 = paramTag;
    }
    super.handleStartTag(paramTag, paramMutableAttributeSet, paramInt);
  }
  
  public final void handleText(char[] paramArrayOfChar, int paramInt)
  {
    null.append(paramArrayOfChar);
  }
  
  static
  {
    new SimpleDateFormat("M/d/yyyy - h:mm a");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1203
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */