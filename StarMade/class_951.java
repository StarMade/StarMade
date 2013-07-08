import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.schema.schine.graphicsengine.core.settings.PrefixNotFoundException;

public class class_951
  implements ClipboardOwner, class_954
{
  private final ArrayList jdField_field_4_of_type_JavaUtilArrayList;
  private int jdField_field_4_of_type_Int;
  private final StringBuffer jdField_field_4_of_type_JavaLangStringBuffer;
  private String jdField_field_4_of_type_JavaLangString = "";
  private int jdField_field_5_of_type_Int = -1;
  private int jdField_field_6_of_type_Int = -1;
  private boolean jdField_field_4_of_type_Boolean;
  private int jdField_field_7_of_type_Int;
  private class_952 jdField_field_4_of_type_Class_952;
  private String jdField_field_5_of_type_JavaLangString = "";
  private String jdField_field_6_of_type_JavaLangString = "";
  private String jdField_field_7_of_type_JavaLangString = "";
  private class_1079 jdField_field_4_of_type_Class_1079;
  private final int field_132;
  private int field_133 = 1;
  private class_956 jdField_field_4_of_type_Class_956 = new class_1080(this);
  private int field_161 = 1;
  private int field_218;
  private int field_219;
  
  public class_951(int paramInt1, int paramInt2, class_1079 paramclass_1079)
  {
    this.jdField_field_4_of_type_JavaLangStringBuffer = new StringBuffer(paramInt1);
    this.jdField_field_4_of_type_JavaUtilArrayList = new ArrayList();
    this.field_132 = paramInt1;
    this.field_133 = paramInt2;
    this.jdField_field_4_of_type_Class_1079 = paramclass_1079;
    this.jdField_field_4_of_type_JavaUtilArrayList.add("/load Fireball testship");
    this.jdField_field_4_of_type_JavaUtilArrayList.add("/give_category_items schema 100 ship");
    this.jdField_field_4_of_type_JavaUtilArrayList.add("/give_credits schema 9999999999");
    this.jdField_field_4_of_type_JavaUtilArrayList.add("/jump");
  }
  
  public final void a9(String paramString)
  {
    if ((this.jdField_field_6_of_type_Int >= 0) && (this.jdField_field_5_of_type_Int >= 0))
    {
      i = Math.min(this.jdField_field_5_of_type_Int, this.jdField_field_6_of_type_Int);
      int j = Math.max(this.jdField_field_5_of_type_Int, this.jdField_field_6_of_type_Int);
      this.jdField_field_4_of_type_JavaLangStringBuffer.delete(i, j);
      this.jdField_field_4_of_type_Int = i;
      this.jdField_field_4_of_type_Boolean = true;
    }
    for (int i = 0; (i < paramString.length()) && (this.jdField_field_4_of_type_JavaLangStringBuffer.length() < this.field_132); i++)
    {
      this.jdField_field_4_of_type_JavaLangStringBuffer.insert(this.jdField_field_4_of_type_Int, paramString.charAt(i));
      this.jdField_field_4_of_type_Int += 1;
      this.jdField_field_4_of_type_Boolean = true;
    }
    d();
  }
  
  private void h1()
  {
    int i = this.jdField_field_4_of_type_Int;
    if (this.jdField_field_4_of_type_Int > 0)
    {
      this.jdField_field_4_of_type_Int -= 1;
      if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
      {
        while ((this.jdField_field_4_of_type_Int > 0) && (' ' == this.jdField_field_4_of_type_JavaLangStringBuffer.charAt(this.jdField_field_4_of_type_Int - 1))) {
          this.jdField_field_4_of_type_Int -= 1;
        }
        while ((this.jdField_field_4_of_type_Int > 0) && (' ' != this.jdField_field_4_of_type_JavaLangStringBuffer.charAt(this.jdField_field_4_of_type_Int - 1))) {
          this.jdField_field_4_of_type_Int -= 1;
        }
      }
      this.jdField_field_4_of_type_Boolean = true;
    }
    b3(i);
  }
  
  private void i()
  {
    int i = this.jdField_field_4_of_type_Int;
    if (this.jdField_field_4_of_type_Int < this.jdField_field_4_of_type_JavaLangStringBuffer.length())
    {
      this.jdField_field_4_of_type_Int += 1;
      if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
      {
        while ((this.jdField_field_4_of_type_Int < this.jdField_field_4_of_type_JavaLangStringBuffer.length()) && (' ' != this.jdField_field_4_of_type_JavaLangStringBuffer.charAt(this.jdField_field_4_of_type_Int)))
        {
          System.err.println("chat carrier reset!!! right ");
          this.jdField_field_4_of_type_Int += 1;
        }
        while ((this.jdField_field_4_of_type_Int < this.jdField_field_4_of_type_JavaLangStringBuffer.length()) && (' ' == this.jdField_field_4_of_type_JavaLangStringBuffer.charAt(this.jdField_field_4_of_type_Int)))
        {
          System.err.println("chat carrier reset!!! right ");
          this.jdField_field_4_of_type_Int += 1;
        }
      }
      this.jdField_field_4_of_type_Boolean = true;
    }
    b3(i);
  }
  
  private void j()
  {
    if (this.field_133 == 1)
    {
      this.jdField_field_7_of_type_Int += 1;
      if (this.jdField_field_7_of_type_Int <= this.jdField_field_4_of_type_JavaUtilArrayList.size())
      {
        this.jdField_field_4_of_type_JavaLangStringBuffer.delete(0, this.jdField_field_4_of_type_JavaLangStringBuffer.length());
        this.jdField_field_4_of_type_JavaLangStringBuffer.append((String)this.jdField_field_4_of_type_JavaUtilArrayList.get(this.jdField_field_4_of_type_JavaUtilArrayList.size() - this.jdField_field_7_of_type_Int));
        System.err.println("chat carrier reset!!! up " + this.jdField_field_4_of_type_JavaLangStringBuffer.length());
        this.jdField_field_4_of_type_Int = this.jdField_field_4_of_type_JavaLangStringBuffer.length();
      }
      else
      {
        this.jdField_field_7_of_type_Int = this.jdField_field_4_of_type_JavaUtilArrayList.size();
      }
    }
    else
    {
      int i;
      if ((this.field_219 > 0) && ((i = Math.max(0, this.jdField_field_5_of_type_JavaLangString.lastIndexOf("\n"))) >= 0))
      {
        int j = this.jdField_field_5_of_type_JavaLangString.substring(0, i).lastIndexOf("\n");
        int k = i - j;
        i = j + Math.min(k, this.jdField_field_4_of_type_Int - i);
        System.err.println("CHAT CARRIER: " + this.jdField_field_4_of_type_Int + " -> " + i);
        while ((this.jdField_field_4_of_type_Int > 0) && (this.jdField_field_4_of_type_Int > i)) {
          this.jdField_field_4_of_type_Int -= 1;
        }
      }
    }
    d();
    this.jdField_field_4_of_type_Boolean = true;
  }
  
  private void k()
  {
    if (this.field_133 == 1)
    {
      this.jdField_field_7_of_type_Int -= 1;
      if (this.jdField_field_7_of_type_Int > 0)
      {
        this.jdField_field_4_of_type_JavaLangStringBuffer.delete(0, this.jdField_field_4_of_type_JavaLangStringBuffer.length());
        this.jdField_field_4_of_type_JavaLangStringBuffer.append((String)this.jdField_field_4_of_type_JavaUtilArrayList.get(this.jdField_field_4_of_type_JavaUtilArrayList.size() - this.jdField_field_7_of_type_Int));
        System.err.println("chat carrier reset!!! down " + this.jdField_field_4_of_type_JavaLangStringBuffer.length());
        this.jdField_field_4_of_type_Int = this.jdField_field_4_of_type_JavaLangStringBuffer.length();
      }
      else
      {
        this.jdField_field_7_of_type_Int = 0;
        this.jdField_field_4_of_type_JavaLangStringBuffer.delete(0, this.jdField_field_4_of_type_JavaLangStringBuffer.length());
        System.err.println("chat carrier reset!!! keyDown");
        this.jdField_field_4_of_type_Int = 0;
      }
    }
    else if (this.field_219 < this.field_218)
    {
      int i = this.jdField_field_5_of_type_JavaLangString.lastIndexOf("\n");
      int j;
      if ((j = this.jdField_field_4_of_type_JavaLangStringBuffer.indexOf("\n", this.jdField_field_4_of_type_Int)) >= 0)
      {
        int k;
        if ((k = this.jdField_field_4_of_type_JavaLangStringBuffer.indexOf("\n", j + 1)) < 0) {
          k = this.jdField_field_4_of_type_JavaLangStringBuffer.length();
        }
        int m = k - j;
        System.err.println("MAX " + m + " / " + (j - this.jdField_field_4_of_type_Int) + "; next: " + j + " NNext " + k);
        i = j + Math.min(m, this.jdField_field_4_of_type_Int - i);
        while ((this.jdField_field_4_of_type_Int < this.jdField_field_4_of_type_JavaLangStringBuffer.length()) && (this.jdField_field_4_of_type_Int < i)) {
          this.jdField_field_4_of_type_Int += 1;
        }
      }
      else
      {
        System.err.println("DOWN: " + i + " ---- " + j);
      }
    }
    d();
    this.jdField_field_4_of_type_Boolean = true;
  }
  
  public final void a2()
  {
    d();
    this.jdField_field_4_of_type_JavaLangStringBuffer.delete(0, this.jdField_field_4_of_type_JavaLangStringBuffer.length());
    this.jdField_field_4_of_type_Int = 0;
    this.jdField_field_4_of_type_Boolean = true;
    g1();
  }
  
  private void l()
  {
    System.err.println("trying copy");
    if ((this.jdField_field_6_of_type_Int >= 0) && (this.jdField_field_5_of_type_Int >= 0))
    {
      Object localObject = this.jdField_field_6_of_type_JavaLangString;
      class_951 localclass_951 = this;
      localObject = new StringSelection((String)localObject);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents((Transferable)localObject, localclass_951);
      System.err.println("Copied to clipboard: " + this.jdField_field_6_of_type_JavaLangString);
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_4_of_type_JavaLangStringBuffer.length() >= 0)
    {
      String str = this.jdField_field_4_of_type_JavaLangStringBuffer.toString();
      if ((this.jdField_field_4_of_type_Class_956 != null) && (!this.jdField_field_4_of_type_Class_956.a(str, this.jdField_field_4_of_type_Class_1079))) {
        return;
      }
      this.jdField_field_4_of_type_Class_1079.onTextEnter(str, !str.startsWith("/"));
      this.jdField_field_4_of_type_JavaUtilArrayList.add(str);
      this.jdField_field_4_of_type_JavaLangStringBuffer.delete(0, this.jdField_field_4_of_type_JavaLangStringBuffer.length());
      this.jdField_field_4_of_type_Int = 0;
      this.jdField_field_7_of_type_Int = 0;
      this.jdField_field_4_of_type_Boolean = true;
      d();
    }
  }
  
  public final String a4()
  {
    return this.jdField_field_4_of_type_JavaLangString;
  }
  
  public final String b7()
  {
    return this.jdField_field_5_of_type_JavaLangString;
  }
  
  public final String c5()
  {
    return this.jdField_field_6_of_type_JavaLangString;
  }
  
  public final String d4()
  {
    return this.jdField_field_7_of_type_JavaLangString;
  }
  
  public final ArrayList a82()
  {
    return this.jdField_field_4_of_type_JavaUtilArrayList;
  }
  
  public void handleKeyEvent()
  {
    if (Keyboard.getEventKeyState())
    {
      class_951 localclass_951;
      int i;
      int k;
      switch (Keyboard.getEventKey())
      {
      case 28: 
        if (this.field_133 == 1)
        {
          if (!Keyboard.isRepeatEvent()) {
            b();
          }
        }
        else if (this.field_218 + 1 < this.field_133) {
          a9("\n");
        } else {
          System.err.println("[TextAreaInput] line limit reached " + this.field_218 + "/" + this.field_133);
        }
        break;
      case 211: 
        localclass_951 = this;
        if ((this.jdField_field_6_of_type_Int >= 0) && (localclass_951.jdField_field_5_of_type_Int >= 0))
        {
          i = Math.min(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
          k = Math.max(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
          localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.delete(i, k);
          localclass_951.jdField_field_4_of_type_Int = i;
          localclass_951.d();
        }
        else if (localclass_951.jdField_field_4_of_type_Int < localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.length())
        {
          if ((localclass_951.jdField_field_6_of_type_Int >= 0) && (localclass_951.jdField_field_5_of_type_Int >= 0))
          {
            i = Math.min(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
            k = Math.max(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
            localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.delete(i, k);
            localclass_951.jdField_field_4_of_type_Int = i;
            localclass_951.d();
          }
          else
          {
            localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.delete(localclass_951.jdField_field_4_of_type_Int, localclass_951.jdField_field_4_of_type_Int + 1);
          }
          localclass_951.jdField_field_4_of_type_Boolean = true;
        }
        localclass_951.d();
        break;
      case 203: 
        h1();
        break;
      case 205: 
        i();
        break;
      case 14: 
        localclass_951 = this;
        if (this.jdField_field_4_of_type_JavaLangStringBuffer.length() > 0)
        {
          if ((localclass_951.jdField_field_6_of_type_Int >= 0) && (localclass_951.jdField_field_5_of_type_Int >= 0))
          {
            i = Math.min(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
            k = Math.max(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
            localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.delete(i, k);
            localclass_951.jdField_field_4_of_type_Int = Math.max(0, i);
            localclass_951.d();
          }
          else
          {
            localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.delete(Math.max(0, localclass_951.jdField_field_4_of_type_Int - 1), localclass_951.jdField_field_4_of_type_Int);
            localclass_951.jdField_field_4_of_type_Int -= 1;
            localclass_951.jdField_field_4_of_type_Int = Math.max(0, localclass_951.jdField_field_4_of_type_Int);
          }
          localclass_951.jdField_field_4_of_type_Boolean = true;
        }
        localclass_951.d();
        break;
      case 200: 
        j();
        break;
      case 208: 
        k();
        break;
      case 199: 
        localclass_951 = this;
        i = this.jdField_field_4_of_type_Int;
        System.err.println("chat carrier reset!!! pos1");
        localclass_951.jdField_field_4_of_type_Int = 0;
        localclass_951.b3(i);
        localclass_951.jdField_field_4_of_type_Boolean = true;
        break;
      case 207: 
        localclass_951 = this;
        i = this.jdField_field_4_of_type_Int;
        localclass_951.jdField_field_4_of_type_Int = Math.max(0, localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.length());
        localclass_951.b3(i);
        localclass_951.jdField_field_4_of_type_Boolean = true;
        break;
      case 47: 
        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
        {
          localclass_951 = this;
          try
          {
            DataFlavor localDataFlavor = DataFlavor.stringFlavor;
            String str = "";
            Transferable localTransferable;
            if ((((localTransferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)) != null) && (localTransferable.isDataFlavorSupported(localDataFlavor)) ? 1 : 0) != 0) {
              str = (String)localTransferable.getTransferData(localDataFlavor);
            }
            localclass_951.a9(str);
          }
          catch (UnsupportedFlavorException localUnsupportedFlavorException)
          {
            localUnsupportedFlavorException;
          }
          catch (IOException localIOException)
          {
            localIOException;
          }
        }
        else
        {
          m();
        }
        break;
      case 46: 
        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
          l();
        } else {
          m();
        }
        break;
      case 30: 
        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29))) {
          e();
        } else {
          m();
        }
        break;
      case 45: 
        if ((Keyboard.isKeyDown(157)) || (Keyboard.isKeyDown(29)))
        {
          localclass_951 = this;
          l();
          if ((localclass_951.jdField_field_6_of_type_Int >= 0) && (localclass_951.jdField_field_5_of_type_Int >= 0))
          {
            int j = Math.min(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
            int m = Math.max(localclass_951.jdField_field_5_of_type_Int, localclass_951.jdField_field_6_of_type_Int);
            System.err.println("current: " + localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.toString());
            localclass_951.jdField_field_4_of_type_JavaLangStringBuffer.delete(j, m);
            localclass_951.jdField_field_4_of_type_Int = j;
            localclass_951.d();
            localclass_951.jdField_field_4_of_type_Boolean = true;
          }
        }
        else
        {
          m();
        }
        break;
      case 15: 
        n();
        break;
      default: 
        m();
      }
    }
    g1();
  }
  
  public static void c1() {}
  
  private void m()
  {
    char c;
    if (!Character.isIdentifierIgnorable(c = Keyboard.getEventCharacter()))
    {
      String str = String.valueOf(c);
      a9(str);
    }
  }
  
  public void lostOwnership(Clipboard paramClipboard, Transferable paramTransferable)
  {
    System.out.println("Lost clipboard ownership " + this);
  }
  
  private void n()
  {
    try
    {
      String[] arrayOfString;
      if ((arrayOfString = this.jdField_field_4_of_type_Class_1079.getCommandPrefixes()) != null) {
        for (int i = 0; i < arrayOfString.length; i++) {
          if ((this.jdField_field_4_of_type_JavaLangStringBuffer.length() >= arrayOfString[i].length()) && (this.jdField_field_4_of_type_JavaLangStringBuffer.indexOf(arrayOfString[i]) == 0))
          {
            String str = this.jdField_field_4_of_type_JavaLangStringBuffer.substring(arrayOfString[i].length());
            this.jdField_field_4_of_type_JavaLangStringBuffer.delete(0, this.jdField_field_4_of_type_JavaLangStringBuffer.length());
            this.jdField_field_4_of_type_Int = 0;
            this.jdField_field_7_of_type_Int = 0;
            this.jdField_field_4_of_type_Boolean = true;
            d();
            System.err.println("AUTOCOMPLETE ON PREFIX: " + arrayOfString[i] + " with \"" + str + "\"");
            a9(arrayOfString[i] + this.jdField_field_4_of_type_Class_1079.handleAutoComplete(str, this.jdField_field_4_of_type_Class_1079, arrayOfString[i]));
          }
        }
      }
      return;
    }
    catch (PrefixNotFoundException localPrefixNotFoundException)
    {
      localPrefixNotFoundException;
    }
  }
  
  public final void d()
  {
    if ((this.jdField_field_6_of_type_Int >= 0) || (this.jdField_field_5_of_type_Int >= 0))
    {
      this.jdField_field_6_of_type_Int = -1;
      this.jdField_field_5_of_type_Int = -1;
      this.jdField_field_4_of_type_Boolean = true;
    }
    g1();
  }
  
  public final void e()
  {
    if (this.jdField_field_4_of_type_JavaLangStringBuffer.length() > 0)
    {
      this.jdField_field_5_of_type_Int = 0;
      this.jdField_field_6_of_type_Int = this.jdField_field_4_of_type_JavaLangStringBuffer.length();
      this.jdField_field_4_of_type_Boolean = true;
    }
  }
  
  public final void f()
  {
    this.jdField_field_4_of_type_Boolean = true;
  }
  
  public final void a13(int paramInt)
  {
    this.jdField_field_4_of_type_Int = paramInt;
  }
  
  public final void a10(class_956 paramclass_956)
  {
    this.jdField_field_4_of_type_Class_956 = paramclass_956;
  }
  
  public final void a83(class_952 paramclass_952)
  {
    this.jdField_field_4_of_type_Class_952 = paramclass_952;
  }
  
  public final void g1()
  {
    if (this.jdField_field_4_of_type_Boolean)
    {
      this.jdField_field_4_of_type_JavaLangString = this.jdField_field_4_of_type_JavaLangStringBuffer.toString();
      this.jdField_field_5_of_type_JavaLangString = this.jdField_field_4_of_type_JavaLangString.substring(0, this.jdField_field_4_of_type_Int);
      if ((this.jdField_field_5_of_type_Int >= 0) && (this.jdField_field_6_of_type_Int >= 0))
      {
        int i = Math.min(this.jdField_field_5_of_type_Int, this.jdField_field_6_of_type_Int);
        int j = Math.max(this.jdField_field_5_of_type_Int, this.jdField_field_6_of_type_Int);
        this.jdField_field_6_of_type_JavaLangString = this.jdField_field_4_of_type_JavaLangString.substring(i, j);
        this.jdField_field_7_of_type_JavaLangString = this.jdField_field_4_of_type_JavaLangString.substring(0, i);
      }
      else
      {
        this.jdField_field_6_of_type_JavaLangString = "";
        this.jdField_field_7_of_type_JavaLangString = "";
      }
      this.jdField_field_4_of_type_Boolean = false;
      String str = this.jdField_field_4_of_type_JavaLangString;
      class_951 localclass_951 = this;
      if (this.jdField_field_4_of_type_Class_952 != null) {
        localclass_951.jdField_field_4_of_type_Class_952.a(str);
      }
      this.field_218 = (class_41.a(this.jdField_field_4_of_type_JavaLangStringBuffer.toString()) - 1);
      this.field_219 = (class_41.a(this.jdField_field_5_of_type_JavaLangString.toString()) - 1);
    }
    if ((this.jdField_field_5_of_type_Int >= 0) && (this.jdField_field_5_of_type_Int == this.jdField_field_6_of_type_Int)) {
      d();
    }
  }
  
  public final int a28()
  {
    return this.field_218;
  }
  
  private void b3(int paramInt)
  {
    if ((Keyboard.isKeyDown(54)) || (Keyboard.isKeyDown(42)))
    {
      if (this.jdField_field_5_of_type_Int < 0) {
        this.jdField_field_5_of_type_Int = paramInt;
      }
      this.jdField_field_6_of_type_Int = this.jdField_field_4_of_type_Int;
      return;
    }
    d();
  }
  
  public final int b6()
  {
    return this.field_219;
  }
  
  public final int c4()
  {
    return this.field_132;
  }
  
  public final int d5()
  {
    return this.field_133;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_951
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */