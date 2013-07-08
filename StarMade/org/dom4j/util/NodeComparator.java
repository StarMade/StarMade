package org.dom4j.util;

import java.util.Comparator;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;

public class NodeComparator
  implements Comparator
{
  public int compare(Object local_o1, Object local_o2)
  {
    if (local_o1 == local_o2) {
      return 0;
    }
    if (local_o1 == null) {
      return -1;
    }
    if (local_o2 == null) {
      return 1;
    }
    if ((local_o1 instanceof Node))
    {
      if ((local_o2 instanceof Node)) {
        return compare((Node)local_o1, (Node)local_o2);
      }
      return 1;
    }
    if ((local_o2 instanceof Node)) {
      return -1;
    }
    if ((local_o1 instanceof Comparable))
    {
      Comparable local_c1 = (Comparable)local_o1;
      return local_c1.compareTo(local_o2);
    }
    String local_c1 = local_o1.getClass().getName();
    String name2 = local_o2.getClass().getName();
    return local_c1.compareTo(name2);
  }
  
  public int compare(Node local_n1, Node local_n2)
  {
    int nodeType1 = local_n1.getNodeType();
    int nodeType2 = local_n2.getNodeType();
    int answer = nodeType1 - nodeType2;
    if (answer != 0) {
      return answer;
    }
    switch (nodeType1)
    {
    case 1: 
      return compare((Element)local_n1, (Element)local_n2);
    case 9: 
      return compare((Document)local_n1, (Document)local_n2);
    case 2: 
      return compare((Attribute)local_n1, (Attribute)local_n2);
    case 3: 
      return compare((Text)local_n1, (Text)local_n2);
    case 4: 
      return compare((CDATA)local_n1, (CDATA)local_n2);
    case 5: 
      return compare((Entity)local_n1, (Entity)local_n2);
    case 7: 
      return compare((ProcessingInstruction)local_n1, (ProcessingInstruction)local_n2);
    case 8: 
      return compare((Comment)local_n1, (Comment)local_n2);
    case 10: 
      return compare((DocumentType)local_n1, (DocumentType)local_n2);
    case 13: 
      return compare((Namespace)local_n1, (Namespace)local_n2);
    }
    throw new RuntimeException("Invalid node types. node1: " + local_n1 + " and node2: " + local_n2);
  }
  
  public int compare(Document local_n1, Document local_n2)
  {
    int answer = compare(local_n1.getDocType(), local_n2.getDocType());
    if (answer == 0) {
      answer = compareContent(local_n1, local_n2);
    }
    return answer;
  }
  
  public int compare(Element local_n1, Element local_n2)
  {
    int answer = compare(local_n1.getQName(), local_n2.getQName());
    if (answer == 0)
    {
      int local_c1 = local_n1.attributeCount();
      int local_c2 = local_n2.attributeCount();
      answer = local_c1 - local_c2;
      if (answer == 0)
      {
        for (int local_i = 0; local_i < local_c1; local_i++)
        {
          Attribute local_a1 = local_n1.attribute(local_i);
          Attribute local_a2 = local_n2.attribute(local_a1.getQName());
          answer = compare(local_a1, local_a2);
          if (answer != 0) {
            return answer;
          }
        }
        answer = compareContent(local_n1, local_n2);
      }
    }
    return answer;
  }
  
  public int compare(Attribute local_n1, Attribute local_n2)
  {
    int answer = compare(local_n1.getQName(), local_n2.getQName());
    if (answer == 0) {
      answer = compare(local_n1.getValue(), local_n2.getValue());
    }
    return answer;
  }
  
  public int compare(QName local_n1, QName local_n2)
  {
    int answer = compare(local_n1.getNamespaceURI(), local_n2.getNamespaceURI());
    if (answer == 0) {
      answer = compare(local_n1.getQualifiedName(), local_n2.getQualifiedName());
    }
    return answer;
  }
  
  public int compare(Namespace local_n1, Namespace local_n2)
  {
    int answer = compare(local_n1.getURI(), local_n2.getURI());
    if (answer == 0) {
      answer = compare(local_n1.getPrefix(), local_n2.getPrefix());
    }
    return answer;
  }
  
  public int compare(CharacterData local_t1, CharacterData local_t2)
  {
    return compare(local_t1.getText(), local_t2.getText());
  }
  
  public int compare(DocumentType local_o1, DocumentType local_o2)
  {
    if (local_o1 == local_o2) {
      return 0;
    }
    if (local_o1 == null) {
      return -1;
    }
    if (local_o2 == null) {
      return 1;
    }
    int answer = compare(local_o1.getPublicID(), local_o2.getPublicID());
    if (answer == 0)
    {
      answer = compare(local_o1.getSystemID(), local_o2.getSystemID());
      if (answer == 0) {
        answer = compare(local_o1.getName(), local_o2.getName());
      }
    }
    return answer;
  }
  
  public int compare(Entity local_n1, Entity local_n2)
  {
    int answer = compare(local_n1.getName(), local_n2.getName());
    if (answer == 0) {
      answer = compare(local_n1.getText(), local_n2.getText());
    }
    return answer;
  }
  
  public int compare(ProcessingInstruction local_n1, ProcessingInstruction local_n2)
  {
    int answer = compare(local_n1.getTarget(), local_n2.getTarget());
    if (answer == 0) {
      answer = compare(local_n1.getText(), local_n2.getText());
    }
    return answer;
  }
  
  public int compareContent(Branch local_b1, Branch local_b2)
  {
    int local_c1 = local_b1.nodeCount();
    int local_c2 = local_b2.nodeCount();
    int answer = local_c1 - local_c2;
    if (answer == 0) {
      for (int local_i = 0; local_i < local_c1; local_i++)
      {
        Node local_n1 = local_b1.node(local_i);
        Node local_n2 = local_b2.node(local_i);
        answer = compare(local_n1, local_n2);
        if (answer != 0) {
          break;
        }
      }
    }
    return answer;
  }
  
  public int compare(String local_o1, String local_o2)
  {
    if (local_o1 == local_o2) {
      return 0;
    }
    if (local_o1 == null) {
      return -1;
    }
    if (local_o2 == null) {
      return 1;
    }
    return local_o1.compareTo(local_o2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.NodeComparator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */