/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.Collections;
/*   4:    */import java.util.List;
/*   5:    */import org.jaxen.Context;
/*   6:    */import org.jaxen.ContextSupport;
/*   7:    */import org.jaxen.JaxenException;
/*   8:    */import org.jaxen.Navigator;
/*   9:    */import org.jaxen.util.SingletonList;
/*  10:    */
/*  66:    *//**
/*  67:    */ * @deprecated
/*  68:    */ */
/*  69:    */public class DefaultAbsoluteLocationPath
/*  70:    */  extends DefaultLocationPath
/*  71:    */{
/*  72:    */  private static final long serialVersionUID = 2174836928310146874L;
/*  73:    */  
/*  74:    */  public String toString()
/*  75:    */  {
/*  76: 76 */    return "[(DefaultAbsoluteLocationPath): " + super.toString() + "]";
/*  77:    */  }
/*  78:    */  
/*  79:    */  public boolean isAbsolute()
/*  80:    */  {
/*  81: 81 */    return true;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public String getText()
/*  85:    */  {
/*  86: 86 */    return "/" + super.getText();
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Object evaluate(Context context) throws JaxenException
/*  90:    */  {
/*  91: 91 */    ContextSupport support = context.getContextSupport();
/*  92: 92 */    Navigator nav = support.getNavigator();
/*  93: 93 */    Context absContext = new Context(support);
/*  94: 94 */    List contextNodes = context.getNodeSet();
/*  95:    */    
/*  96: 96 */    if (contextNodes.isEmpty())
/*  97:    */    {
/*  98: 98 */      return Collections.EMPTY_LIST;
/*  99:    */    }
/* 100:    */    
/* 101:101 */    Object firstNode = contextNodes.get(0);
/* 102:102 */    Object docNode = nav.getDocumentNode(firstNode);
/* 103:    */    
/* 104:104 */    if (docNode == null)
/* 105:    */    {
/* 106:106 */      return Collections.EMPTY_LIST;
/* 107:    */    }
/* 108:    */    
/* 109:109 */    List list = new SingletonList(docNode);
/* 110:    */    
/* 111:111 */    absContext.setNodeSet(list);
/* 112:    */    
/* 113:113 */    return super.evaluate(absContext);
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultAbsoluteLocationPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */