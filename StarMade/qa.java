/*   1:    */import java.io.BufferedReader;
/*   2:    */import java.io.BufferedWriter;
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileNotFoundException;
/*   5:    */import java.io.FileReader;
/*   6:    */import java.io.FileWriter;
/*   7:    */import java.io.IOException;
/*   8:    */import java.util.ArrayList;
/*   9:    */import javax.swing.AbstractListModel;
/*  10:    */import org.schema.game.common.staremote.Staremote;
/*  11:    */
/*  17:    */public final class qa
/*  18:    */  extends AbstractListModel
/*  19:    */{
/*  20:    */  private static final long serialVersionUID = 6959802025354159616L;
/*  21:    */  
/*  22:    */  public qa()
/*  23:    */  {
/*  24:    */    try
/*  25:    */    {
/*  26: 26 */      b(); return;
/*  27: 27 */    } catch (NumberFormatException localNumberFormatException) { 
/*  28:    */      
/*  31: 31 */        localNumberFormatException.printStackTrace(); return;
/*  32:    */    } catch (IOException localIOException) {
/*  33: 29 */      
/*  34:    */      
/*  35: 31 */        localIOException;
/*  36:    */    }
/*  37:    */  }
/*  38:    */  
/*  40: 34 */  private ArrayList a = new ArrayList();
/*  41:    */  
/*  42:    */  public final Object getElementAt(int paramInt)
/*  43:    */  {
/*  44: 38 */    return this.a.get(paramInt);
/*  45:    */  }
/*  46:    */  
/*  49: 43 */  public final int getSize() { return this.a.size(); }
/*  50:    */  
/*  51:    */  public final void a(qe paramqe) {
/*  52: 46 */    this.a.add(paramqe);
/*  53: 47 */    a();
/*  54:    */    try {
/*  55: 49 */      b(); return;
/*  56: 50 */    } catch (NumberFormatException localNumberFormatException) { 
/*  57:    */      
/*  60: 54 */        localNumberFormatException.printStackTrace(); return;
/*  61:    */    } catch (IOException localIOException) {
/*  62: 52 */      
/*  63:    */      
/*  64: 54 */        localIOException;
/*  65:    */    }
/*  66:    */  }
/*  67:    */  
/*  69:    */  private void a()
/*  70:    */  {
/*  71:    */    try
/*  72:    */    {
/*  73: 61 */      if (!(localObject = new File(Staremote.a())).exists()) {
/*  74: 62 */        ((File)localObject).createNewFile();
/*  75:    */      }
/*  76: 64 */      Object localObject = new BufferedWriter(new FileWriter((File)localObject));
/*  77: 65 */      for (qe localqe : this.a) {
/*  78: 66 */        ((BufferedWriter)localObject).append(localqe.jdField_a_of_type_JavaLangString + "," + localqe.b + ":" + localqe.jdField_a_of_type_Int + "\n");
/*  79:    */      }
/*  80: 68 */      ((BufferedWriter)localObject).flush();
/*  81: 69 */      ((BufferedWriter)localObject).close(); return;
/*  82: 70 */    } catch (Exception localException) { 
/*  83:    */      
/*  84: 72 */        localException;
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  88:    */  private void b()
/*  89:    */  {
/*  90: 76 */    this.a.clear();
/*  91:    */    
/*  92: 78 */    if (!(localObject1 = new File(Staremote.a())).exists()) {
/*  93: 79 */      throw new FileNotFoundException();
/*  94:    */    }
/*  95: 81 */    Object localObject1 = new BufferedReader(new FileReader((File)localObject1));
/*  96:    */    Object localObject2;
/*  97: 83 */    while ((localObject2 = ((BufferedReader)localObject1).readLine()) != null)
/*  98:    */    {
/*  99: 85 */      String str1 = (localObject2 = ((String)localObject2).split(",", 21))[0];
/* 100:    */      
/* 101: 87 */      String str2 = (localObject2 = localObject2[1].split(":", 2))[0];
/* 102: 88 */      int i = Integer.parseInt(localObject2[1]);
/* 103:    */      
/* 104: 90 */      qe localqe = new qe(str2, i, str1);
/* 105:    */      
/* 106: 92 */      this.a.add(localqe);
/* 107:    */    }
/* 108:    */    
/* 109: 95 */    ((BufferedReader)localObject1).close();
/* 110:    */    
/* 111: 97 */    fireContentsChanged(this, 0, this.a.size() - 1);
/* 112:    */  }
/* 113:    */  
/* 114:    */  public final void b(qe paramqe) {
/* 115:101 */    this.a.remove(paramqe);
/* 116:102 */    a();
/* 117:    */    try {
/* 118:104 */      b(); return;
/* 119:105 */    } catch (NumberFormatException localNumberFormatException) { 
/* 120:    */      
/* 123:109 */        localNumberFormatException.printStackTrace(); return;
/* 124:    */    } catch (IOException localIOException) {
/* 125:107 */      
/* 126:    */      
/* 127:109 */        localIOException;
/* 128:    */    }
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qa
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */