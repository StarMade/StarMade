/*   1:    */package org.jaxen.dom;
/*   2:    */
/*   3:    */import java.lang.reflect.InvocationTargetException;
/*   4:    */import java.lang.reflect.Method;
/*   5:    */import java.util.HashMap;
/*   6:    */import org.w3c.dom.DOMException;
/*   7:    */import org.w3c.dom.Document;
/*   8:    */import org.w3c.dom.NamedNodeMap;
/*   9:    */import org.w3c.dom.Node;
/*  10:    */import org.w3c.dom.NodeList;
/*  11:    */import org.w3c.dom.UserDataHandler;
/*  12:    */
/* 120:    */public class NamespaceNode
/* 121:    */  implements Node
/* 122:    */{
/* 123:    */  public static final short NAMESPACE_NODE = 13;
/* 124:    */  private Node parent;
/* 125:    */  private String name;
/* 126:    */  private String value;
/* 127:    */  
/* 128:    */  public NamespaceNode(Node parent, String name, String value)
/* 129:    */  {
/* 130:130 */    this.parent = parent;
/* 131:131 */    this.name = (name == null ? "" : name);
/* 132:132 */    this.value = value;
/* 133:    */  }
/* 134:    */  
/* 143:    */  NamespaceNode(Node parent, Node attribute)
/* 144:    */  {
/* 145:145 */    String attributeName = attribute.getNodeName();
/* 146:    */    
/* 147:147 */    if (attributeName.equals("xmlns")) {
/* 148:148 */      this.name = "";
/* 149:    */    }
/* 150:150 */    else if (attributeName.startsWith("xmlns:")) {
/* 151:151 */      this.name = attributeName.substring(6);
/* 152:    */    }
/* 153:    */    else {
/* 154:154 */      this.name = attributeName;
/* 155:    */    }
/* 156:156 */    this.parent = parent;
/* 157:157 */    this.value = attribute.getNodeValue();
/* 158:    */  }
/* 159:    */  
/* 172:    */  public String getNodeName()
/* 173:    */  {
/* 174:174 */    return this.name;
/* 175:    */  }
/* 176:    */  
/* 183:    */  public String getNodeValue()
/* 184:    */  {
/* 185:185 */    return this.value;
/* 186:    */  }
/* 187:    */  
/* 194:    */  public void setNodeValue(String value)
/* 195:    */    throws DOMException
/* 196:    */  {
/* 197:197 */    disallowModification();
/* 198:    */  }
/* 199:    */  
/* 206:    */  public short getNodeType()
/* 207:    */  {
/* 208:208 */    return 13;
/* 209:    */  }
/* 210:    */  
/* 221:    */  public Node getParentNode()
/* 222:    */  {
/* 223:223 */    return this.parent;
/* 224:    */  }
/* 225:    */  
/* 232:    */  public NodeList getChildNodes()
/* 233:    */  {
/* 234:234 */    return new EmptyNodeList(null);
/* 235:    */  }
/* 236:    */  
/* 243:    */  public Node getFirstChild()
/* 244:    */  {
/* 245:245 */    return null;
/* 246:    */  }
/* 247:    */  
/* 254:    */  public Node getLastChild()
/* 255:    */  {
/* 256:256 */    return null;
/* 257:    */  }
/* 258:    */  
/* 265:    */  public Node getPreviousSibling()
/* 266:    */  {
/* 267:267 */    return null;
/* 268:    */  }
/* 269:    */  
/* 276:    */  public Node getNextSibling()
/* 277:    */  {
/* 278:278 */    return null;
/* 279:    */  }
/* 280:    */  
/* 287:    */  public NamedNodeMap getAttributes()
/* 288:    */  {
/* 289:289 */    return null;
/* 290:    */  }
/* 291:    */  
/* 298:    */  public Document getOwnerDocument()
/* 299:    */  {
/* 300:300 */    if (this.parent == null) return null;
/* 301:301 */    return this.parent.getOwnerDocument();
/* 302:    */  }
/* 303:    */  
/* 314:    */  public Node insertBefore(Node newChild, Node refChild)
/* 315:    */    throws DOMException
/* 316:    */  {
/* 317:317 */    disallowModification();
/* 318:318 */    return null;
/* 319:    */  }
/* 320:    */  
/* 330:    */  public Node replaceChild(Node newChild, Node oldChild)
/* 331:    */    throws DOMException
/* 332:    */  {
/* 333:333 */    disallowModification();
/* 334:334 */    return null;
/* 335:    */  }
/* 336:    */  
/* 345:    */  public Node removeChild(Node oldChild)
/* 346:    */    throws DOMException
/* 347:    */  {
/* 348:348 */    disallowModification();
/* 349:349 */    return null;
/* 350:    */  }
/* 351:    */  
/* 360:    */  public Node appendChild(Node newChild)
/* 361:    */    throws DOMException
/* 362:    */  {
/* 363:363 */    disallowModification();
/* 364:364 */    return null;
/* 365:    */  }
/* 366:    */  
/* 373:    */  public boolean hasChildNodes()
/* 374:    */  {
/* 375:375 */    return false;
/* 376:    */  }
/* 377:    */  
/* 386:    */  public Node cloneNode(boolean deep)
/* 387:    */  {
/* 388:388 */    return new NamespaceNode(this.parent, this.name, this.value);
/* 389:    */  }
/* 390:    */  
/* 400:    */  public void normalize() {}
/* 401:    */  
/* 411:    */  public boolean isSupported(String feature, String version)
/* 412:    */  {
/* 413:413 */    return false;
/* 414:    */  }
/* 415:    */  
/* 425:    */  public String getNamespaceURI()
/* 426:    */  {
/* 427:427 */    return null;
/* 428:    */  }
/* 429:    */  
/* 440:    */  public String getPrefix()
/* 441:    */  {
/* 442:442 */    return null;
/* 443:    */  }
/* 444:    */  
/* 452:    */  public void setPrefix(String prefix)
/* 453:    */    throws DOMException
/* 454:    */  {
/* 455:455 */    disallowModification();
/* 456:    */  }
/* 457:    */  
/* 465:    */  public String getLocalName()
/* 466:    */  {
/* 467:467 */    return this.name;
/* 468:    */  }
/* 469:    */  
/* 476:    */  public boolean hasAttributes()
/* 477:    */  {
/* 478:478 */    return false;
/* 479:    */  }
/* 480:    */  
/* 486:    */  private void disallowModification()
/* 487:    */    throws DOMException
/* 488:    */  {
/* 489:489 */    throw new DOMException((short)7, "Namespace node may not be modified");
/* 490:    */  }
/* 491:    */  
/* 505:    */  public int hashCode()
/* 506:    */  {
/* 507:507 */    return hashCode(this.parent) + hashCode(this.name) + hashCode(this.value);
/* 508:    */  }
/* 509:    */  
/* 521:    */  public boolean equals(Object o)
/* 522:    */  {
/* 523:523 */    if (o == this) return true;
/* 524:524 */    if (o == null) return false;
/* 525:525 */    if ((o instanceof NamespaceNode)) {
/* 526:526 */      NamespaceNode ns = (NamespaceNode)o;
/* 527:527 */      return (equals(this.parent, ns.getParentNode())) && (equals(this.name, ns.getNodeName())) && (equals(this.value, ns.getNodeValue()));
/* 528:    */    }
/* 529:    */    
/* 531:531 */    return false;
/* 532:    */  }
/* 533:    */  
/* 543:    */  private int hashCode(Object o)
/* 544:    */  {
/* 545:545 */    return o == null ? 0 : o.hashCode();
/* 546:    */  }
/* 547:    */  
/* 557:    */  private boolean equals(Object a, Object b)
/* 558:    */  {
/* 559:559 */    return ((a == null) && (b == null)) || ((a != null) && (a.equals(b)));
/* 560:    */  }
/* 561:    */  
/* 581:    */  private static class EmptyNodeList
/* 582:    */    implements NodeList
/* 583:    */  {
/* 584:    */    EmptyNodeList(NamespaceNode.1 x0)
/* 585:    */    {
/* 586:586 */      this();
/* 587:    */    }
/* 588:    */    
/* 592:    */    public int getLength()
/* 593:    */    {
/* 594:594 */      return 0;
/* 595:    */    }
/* 596:    */    
/* 601:    */    public Node item(int index)
/* 602:    */    {
/* 603:603 */      return null;
/* 604:    */    }
/* 605:    */    
/* 610:    */    private EmptyNodeList() {}
/* 611:    */  }
/* 612:    */  
/* 617:    */  public String getBaseURI()
/* 618:    */  {
/* 619:619 */    Class clazz = Node.class;
/* 620:    */    try {
/* 621:621 */      Class[] args = new Class[0];
/* 622:622 */      Method getBaseURI = clazz.getMethod("getBaseURI", args);
/* 623:623 */      return (String)getBaseURI.invoke(getParentNode(), args);
/* 624:    */    }
/* 625:    */    catch (Exception ex) {}
/* 626:    */    
/* 627:627 */    return null;
/* 628:    */  }
/* 629:    */  
/* 639:    */  public short compareDocumentPosition(Node other)
/* 640:    */    throws DOMException
/* 641:    */  {
/* 642:642 */    DOMException ex = new DOMException((short)9, "DOM level 3 interfaces are not fully implemented in Jaxen's NamespaceNode class");
/* 643:    */    
/* 646:646 */    throw ex;
/* 647:    */  }
/* 648:    */  
/* 655:    */  public String getTextContent()
/* 656:    */  {
/* 657:657 */    return this.value;
/* 658:    */  }
/* 659:    */  
/* 666:    */  public void setTextContent(String textContent)
/* 667:    */    throws DOMException
/* 668:    */  {
/* 669:669 */    disallowModification();
/* 670:    */  }
/* 671:    */  
/* 681:    */  public boolean isSameNode(Node other)
/* 682:    */  {
/* 683:683 */    boolean a = isEqualNode(other);
/* 684:    */    
/* 690:690 */    Node thisParent = getParentNode();
/* 691:691 */    Node thatParent = other.getParentNode();
/* 692:    */    boolean b;
/* 693:693 */    try { Class clazz = Node.class;
/* 694:694 */      Class[] args = { clazz };
/* 695:695 */      Method isEqual = clazz.getMethod("isEqual", args);
/* 696:696 */      Object[] args2 = new Object[1];
/* 697:697 */      args2[0] = thatParent;
/* 698:698 */      Boolean result = (Boolean)isEqual.invoke(thisParent, args2);
/* 699:699 */      b = result.booleanValue();
/* 700:    */    }
/* 701:    */    catch (NoSuchMethodException ex) {
/* 702:702 */      b = thisParent.equals(thatParent);
/* 703:    */    }
/* 704:    */    catch (InvocationTargetException ex) {
/* 705:705 */      b = thisParent.equals(thatParent);
/* 706:    */    }
/* 707:    */    catch (IllegalAccessException ex) {
/* 708:708 */      b = thisParent.equals(thatParent);
/* 709:    */    }
/* 710:    */    
/* 711:711 */    return (a) && (b);
/* 712:    */  }
/* 713:    */  
/* 727:    */  public String lookupPrefix(String namespaceURI)
/* 728:    */  {
/* 729:    */    try
/* 730:    */    {
/* 731:731 */      Class clazz = Node.class;
/* 732:732 */      Class[] argTypes = { String.class };
/* 733:733 */      Method lookupPrefix = clazz.getMethod("lookupPrefix", argTypes);
/* 734:734 */      String[] args = { namespaceURI };
/* 735:735 */      return (String)lookupPrefix.invoke(this.parent, args);
/* 736:    */    }
/* 737:    */    catch (NoSuchMethodException ex)
/* 738:    */    {
/* 739:739 */      throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
/* 740:    */    }
/* 741:    */    catch (InvocationTargetException ex) {
/* 742:742 */      throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
/* 743:    */    }
/* 744:    */    catch (IllegalAccessException ex) {
/* 745:745 */      throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
/* 746:    */    }
/* 747:    */  }
/* 748:    */  
/* 760:    */  public boolean isDefaultNamespace(String namespaceURI)
/* 761:    */  {
/* 762:762 */    return namespaceURI.equals(lookupNamespaceURI(null));
/* 763:    */  }
/* 764:    */  
/* 777:    */  public String lookupNamespaceURI(String prefix)
/* 778:    */  {
/* 779:    */    try
/* 780:    */    {
/* 781:781 */      Class clazz = Node.class;
/* 782:782 */      Class[] argTypes = { String.class };
/* 783:783 */      Method lookupNamespaceURI = clazz.getMethod("lookupNamespaceURI", argTypes);
/* 784:784 */      String[] args = { prefix };
/* 785:785 */      return (String)lookupNamespaceURI.invoke(this.parent, args);
/* 786:    */    }
/* 787:    */    catch (NoSuchMethodException ex)
/* 788:    */    {
/* 789:789 */      throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
/* 790:    */    }
/* 791:    */    catch (InvocationTargetException ex) {
/* 792:792 */      throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
/* 793:    */    }
/* 794:    */    catch (IllegalAccessException ex) {
/* 795:795 */      throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
/* 796:    */    }
/* 797:    */  }
/* 798:    */  
/* 806:    */  public boolean isEqualNode(Node arg)
/* 807:    */  {
/* 808:808 */    if (arg.getNodeType() == getNodeType()) {
/* 809:809 */      NamespaceNode other = (NamespaceNode)arg;
/* 810:810 */      if ((other.name == null) && (this.name != null)) return false;
/* 811:811 */      if ((other.name != null) && (this.name == null)) return false;
/* 812:812 */      if ((other.value == null) && (this.value != null)) return false;
/* 813:813 */      if ((other.value != null) && (this.value == null)) return false;
/* 814:814 */      if ((other.name == null) && (this.name == null)) {
/* 815:815 */        return other.value.equals(this.value);
/* 816:    */      }
/* 817:    */      
/* 818:818 */      return (other.name.equals(this.name)) && (other.value.equals(this.value));
/* 819:    */    }
/* 820:820 */    return false;
/* 821:    */  }
/* 822:    */  
/* 828:    */  public Object getFeature(String feature, String version)
/* 829:    */  {
/* 830:830 */    return null;
/* 831:    */  }
/* 832:    */  
/* 835:835 */  private HashMap userData = new HashMap();
/* 836:    */  
/* 846:    */  public Object setUserData(String key, Object data, UserDataHandler handler)
/* 847:    */  {
/* 848:848 */    Object oldValue = getUserData(key);
/* 849:849 */    this.userData.put(key, data);
/* 850:850 */    return oldValue;
/* 851:    */  }
/* 852:    */  
/* 860:    */  public Object getUserData(String key)
/* 861:    */  {
/* 862:862 */    return this.userData.get(key);
/* 863:    */  }
/* 864:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom.NamespaceNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */