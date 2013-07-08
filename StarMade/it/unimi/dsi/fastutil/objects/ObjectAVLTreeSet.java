package it.unimi.dsi.fastutil.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

public class ObjectAVLTreeSet<K>
  extends AbstractObjectSortedSet<K>
  implements Serializable, Cloneable, ObjectSortedSet<K>
{
  protected transient Entry<K> tree;
  protected int count;
  protected transient Entry<K> firstEntry;
  protected transient Entry<K> lastEntry;
  protected Comparator<? super K> storedComparator;
  protected transient Comparator<? super K> actualComparator;
  public static final long serialVersionUID = -7046029254386353130L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  
  public ObjectAVLTreeSet()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    this.actualComparator = this.storedComparator;
  }
  
  public ObjectAVLTreeSet(Comparator<? super K> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public ObjectAVLTreeSet(Collection<? extends K> local_c)
  {
    this();
    addAll(local_c);
  }
  
  public ObjectAVLTreeSet(SortedSet<K> local_s)
  {
    this(local_s.comparator());
    addAll(local_s);
  }
  
  public ObjectAVLTreeSet(ObjectCollection<? extends K> local_c)
  {
    this();
    addAll(local_c);
  }
  
  public ObjectAVLTreeSet(ObjectSortedSet<K> local_s)
  {
    this(local_s.comparator());
    addAll(local_s);
  }
  
  public ObjectAVLTreeSet(ObjectIterator<? extends K> local_i)
  {
    allocatePaths();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ObjectAVLTreeSet(Iterator<? extends K> local_i)
  {
    this(ObjectIterators.asObjectIterator(local_i));
  }
  
  public ObjectAVLTreeSet(K[] local_a, int offset, int length, Comparator<? super K> local_c)
  {
    this(local_c);
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ObjectAVLTreeSet(K[] local_a, int offset, int length)
  {
    this(local_a, offset, length, null);
  }
  
  public ObjectAVLTreeSet(K[] local_a)
  {
    this();
    int local_i = local_a.length;
    while (local_i-- != 0) {
      add(local_a[local_i]);
    }
  }
  
  public ObjectAVLTreeSet(K[] local_a, Comparator<? super K> local_c)
  {
    this(local_c);
    int local_i = local_a.length;
    while (local_i-- != 0) {
      add(local_a[local_i]);
    }
  }
  
  final int compare(K local_k1, K local_k2)
  {
    return this.actualComparator == null ? ((Comparable)local_k1).compareTo(local_k2) : this.actualComparator.compare(local_k1, local_k2);
  }
  
  private Entry<K> findKey(K local_k)
  {
    int cmp;
    for (Entry<K> local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry<K> locateKey(K local_k)
  {
    Entry<K> local_e = this.tree;
    Entry<K> last = this.tree;
    int cmp = 0;
    while ((local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0))
    {
      last = local_e;
      local_e = cmp < 0 ? local_e.left() : local_e.right();
    }
    return cmp == 0 ? local_e : last;
  }
  
  private void allocatePaths()
  {
    this.dirPath = new boolean[48];
  }
  
  public boolean add(K local_k)
  {
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k));
    }
    else
    {
      Entry<K> local_p = this.tree;
      Entry<K> local_q = null;
      Entry<K> local_y = this.tree;
      Entry<K> local_z = null;
      Entry<K> local_e = null;
      Entry<K> local_w = null;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0) {
          return false;
        }
        if (local_p.balance() != 0)
        {
          local_i = 0;
          local_z = local_q;
          local_y = local_p;
        }
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            local_e = new Entry(local_k);
            if (local_p.right == null) {
              this.lastEntry = local_e;
            }
            local_e.left = local_p;
            local_e.right = local_p.right;
            local_p.right(local_e);
            break;
          }
          local_q = local_p;
          local_p = local_p.right;
        }
        else
        {
          if (local_p.pred())
          {
            this.count += 1;
            local_e = new Entry(local_k);
            if (local_p.left == null) {
              this.firstEntry = local_e;
            }
            local_e.right = local_p;
            local_e.left = local_p.left;
            local_p.left(local_e);
            break;
          }
          local_q = local_p;
          local_p = local_p.left;
        }
      }
      local_p = local_y;
      local_i = 0;
      while (local_p != local_e)
      {
        if (this.dirPath[local_i] != 0) {
          local_p.incBalance();
        } else {
          local_p.decBalance();
        }
        local_p = this.dirPath[(local_i++)] != 0 ? local_p.right : local_p.left;
      }
      if (local_y.balance() == -2)
      {
        Entry<K> local_x = local_y.left;
        if (local_x.balance() == -1)
        {
          local_w = local_x;
          if (local_x.succ())
          {
            local_x.succ(false);
            local_y.pred(local_x);
          }
          else
          {
            local_y.left = local_x.right;
          }
          local_x.right = local_y;
          local_x.balance(0);
          local_y.balance(0);
        }
        else
        {
          local_w = local_x.right;
          local_x.right = local_w.left;
          local_w.left = local_x;
          local_y.left = local_w.right;
          local_w.right = local_y;
          if (local_w.balance() == -1)
          {
            local_x.balance(0);
            local_y.balance(1);
          }
          else if (local_w.balance() == 0)
          {
            local_x.balance(0);
            local_y.balance(0);
          }
          else
          {
            local_x.balance(-1);
            local_y.balance(0);
          }
          local_w.balance(0);
          if (local_w.pred())
          {
            local_x.succ(local_w);
            local_w.pred(false);
          }
          if (local_w.succ())
          {
            local_y.pred(local_w);
            local_w.succ(false);
          }
        }
      }
      else if (local_y.balance() == 2)
      {
        Entry<K> local_x = local_y.right;
        if (local_x.balance() == 1)
        {
          local_w = local_x;
          if (local_x.pred())
          {
            local_x.pred(false);
            local_y.succ(local_x);
          }
          else
          {
            local_y.right = local_x.left;
          }
          local_x.left = local_y;
          local_x.balance(0);
          local_y.balance(0);
        }
        else
        {
          local_w = local_x.left;
          local_x.left = local_w.right;
          local_w.right = local_x;
          local_y.right = local_w.left;
          local_w.left = local_y;
          if (local_w.balance() == 1)
          {
            local_x.balance(0);
            local_y.balance(-1);
          }
          else if (local_w.balance() == 0)
          {
            local_x.balance(0);
            local_y.balance(0);
          }
          else
          {
            local_x.balance(1);
            local_y.balance(0);
          }
          local_w.balance(0);
          if (local_w.pred())
          {
            local_y.succ(local_w);
            local_w.pred(false);
          }
          if (local_w.succ())
          {
            local_x.pred(local_w);
            local_w.succ(false);
          }
        }
      }
      else
      {
        return true;
      }
      if (local_z == null) {
        this.tree = local_w;
      } else if (local_z.left == local_y) {
        local_z.left = local_w;
      } else {
        local_z.right = local_w;
      }
    }
    return true;
  }
  
  private Entry<K> parent(Entry<K> local_e)
  {
    if (local_e == this.tree) {
      return null;
    }
    Entry<K> local_y;
    Entry<K> local_x = local_y = local_e;
    for (;;)
    {
      if (local_y.succ())
      {
        Entry<K> local_p = local_y.right;
        if ((local_p == null) || (local_p.left != local_e))
        {
          while (!local_x.pred()) {
            local_x = local_x.left;
          }
          local_p = local_x.left;
        }
        return local_p;
      }
      if (local_x.pred())
      {
        Entry<K> local_p = local_x.left;
        if ((local_p == null) || (local_p.right != local_e))
        {
          while (!local_y.succ()) {
            local_y = local_y.right;
          }
          local_p = local_y.right;
        }
        return local_p;
      }
      local_x = local_x.left;
      local_y = local_y.right;
    }
  }
  
  public boolean remove(Object local_k)
  {
    if (this.tree == null) {
      return false;
    }
    Entry<K> local_p = this.tree;
    Entry<K> local_q = null;
    boolean dir = false;
    K local_kk = local_k;
    int cmp;
    while ((cmp = compare(local_kk, local_p.key)) != 0) {
      if ((dir = cmp > 0 ? 1 : 0) != 0)
      {
        local_q = local_p;
        if ((local_p = local_p.right()) == null) {
          return false;
        }
      }
      else
      {
        local_q = local_p;
        if ((local_p = local_p.left()) == null) {
          return false;
        }
      }
    }
    if (local_p.left == null) {
      this.firstEntry = local_p.next();
    }
    if (local_p.right == null) {
      this.lastEntry = local_p.prev();
    }
    if (local_p.succ())
    {
      if (local_p.pred())
      {
        if (local_q != null)
        {
          if (dir) {
            local_q.succ(local_p.right);
          } else {
            local_q.pred(local_p.left);
          }
        }
        else {
          this.tree = (dir ? local_p.right : local_p.left);
        }
      }
      else
      {
        local_p.prev().right = local_p.right;
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_p.left;
          } else {
            local_q.left = local_p.left;
          }
        }
        else {
          this.tree = local_p.left;
        }
      }
    }
    else
    {
      Entry<K> local_r = local_p.right;
      if (local_r.pred())
      {
        local_r.left = local_p.left;
        local_r.pred(local_p.pred());
        if (!local_r.pred()) {
          local_r.prev().right = local_r;
        }
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_r;
          } else {
            local_q.left = local_r;
          }
        }
        else {
          this.tree = local_r;
        }
        local_r.balance(local_p.balance());
        local_q = local_r;
        dir = true;
      }
      else
      {
        Entry<K> local_s;
        for (;;)
        {
          local_s = local_r.left;
          if (local_s.pred()) {
            break;
          }
          local_r = local_s;
        }
        if (local_s.succ()) {
          local_r.pred(local_s);
        } else {
          local_r.left = local_s.right;
        }
        local_s.left = local_p.left;
        if (!local_p.pred())
        {
          local_p.prev().right = local_s;
          local_s.pred(false);
        }
        local_s.right = local_p.right;
        local_s.succ(false);
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_s;
          } else {
            local_q.left = local_s;
          }
        }
        else {
          this.tree = local_s;
        }
        local_s.balance(local_p.balance());
        local_q = local_r;
        dir = false;
      }
    }
    while (local_q != null)
    {
      Entry<K> local_r = local_q;
      local_q = parent(local_r);
      if (!dir)
      {
        dir = (local_q != null) && (local_q.left != local_r);
        local_r.incBalance();
        if (local_r.balance() == 1) {
          break;
        }
        if (local_r.balance() == 2)
        {
          Entry<K> local_s = local_r.right;
          if (local_s.balance() == -1)
          {
            Entry<K> local_w = local_s.left;
            local_s.left = local_w.right;
            local_w.right = local_s;
            local_r.right = local_w.left;
            local_w.left = local_r;
            if (local_w.balance() == 1)
            {
              local_s.balance(0);
              local_r.balance(-1);
            }
            else if (local_w.balance() == 0)
            {
              local_s.balance(0);
              local_r.balance(0);
            }
            else
            {
              local_s.balance(1);
              local_r.balance(0);
            }
            local_w.balance(0);
            if (local_w.pred())
            {
              local_r.succ(local_w);
              local_w.pred(false);
            }
            if (local_w.succ())
            {
              local_s.pred(local_w);
              local_w.succ(false);
            }
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_w;
              } else {
                local_q.left = local_w;
              }
            }
            else {
              this.tree = local_w;
            }
          }
          else
          {
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_s;
              } else {
                local_q.left = local_s;
              }
            }
            else {
              this.tree = local_s;
            }
            if (local_s.balance() == 0)
            {
              local_r.right = local_s.left;
              local_s.left = local_r;
              local_s.balance(-1);
              local_r.balance(1);
              break;
            }
            if (local_s.pred())
            {
              local_r.succ(true);
              local_s.pred(false);
            }
            else
            {
              local_r.right = local_s.left;
            }
            local_s.left = local_r;
            local_r.balance(0);
            local_s.balance(0);
          }
        }
      }
      else
      {
        dir = (local_q != null) && (local_q.left != local_r);
        local_r.decBalance();
        if (local_r.balance() == -1) {
          break;
        }
        if (local_r.balance() == -2)
        {
          Entry<K> local_s = local_r.left;
          if (local_s.balance() == 1)
          {
            Entry<K> local_w = local_s.right;
            local_s.right = local_w.left;
            local_w.left = local_s;
            local_r.left = local_w.right;
            local_w.right = local_r;
            if (local_w.balance() == -1)
            {
              local_s.balance(0);
              local_r.balance(1);
            }
            else if (local_w.balance() == 0)
            {
              local_s.balance(0);
              local_r.balance(0);
            }
            else
            {
              local_s.balance(-1);
              local_r.balance(0);
            }
            local_w.balance(0);
            if (local_w.pred())
            {
              local_s.succ(local_w);
              local_w.pred(false);
            }
            if (local_w.succ())
            {
              local_r.pred(local_w);
              local_w.succ(false);
            }
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_w;
              } else {
                local_q.left = local_w;
              }
            }
            else {
              this.tree = local_w;
            }
          }
          else
          {
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_s;
              } else {
                local_q.left = local_s;
              }
            }
            else {
              this.tree = local_s;
            }
            if (local_s.balance() == 0)
            {
              local_r.left = local_s.right;
              local_s.right = local_r;
              local_s.balance(1);
              local_r.balance(-1);
              break;
            }
            if (local_s.succ())
            {
              local_r.pred(true);
              local_s.succ(false);
            }
            else
            {
              local_r.left = local_s.right;
            }
            local_s.right = local_r;
            local_r.balance(0);
            local_s.balance(0);
          }
        }
      }
    }
    this.count -= 1;
    return true;
  }
  
  public boolean contains(Object local_k)
  {
    return findKey(local_k) != null;
  }
  
  public void clear()
  {
    this.count = 0;
    this.tree = null;
    this.firstEntry = (this.lastEntry = null);
  }
  
  public int size()
  {
    return this.count;
  }
  
  public boolean isEmpty()
  {
    return this.count == 0;
  }
  
  public K first()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public K last()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectBidirectionalIterator<K> iterator()
  {
    return new SetIterator();
  }
  
  public ObjectBidirectionalIterator<K> iterator(K from)
  {
    return new SetIterator(from);
  }
  
  public Comparator<? super K> comparator()
  {
    return this.actualComparator;
  }
  
  public ObjectSortedSet<K> headSet(K local_to)
  {
    return new Subset(null, true, local_to, false);
  }
  
  public ObjectSortedSet<K> tailSet(K from)
  {
    return new Subset(from, false, null, true);
  }
  
  public ObjectSortedSet<K> subSet(K from, K local_to)
  {
    return new Subset(from, false, local_to, false);
  }
  
  public Object clone()
  {
    ObjectAVLTreeSet<K> local_c;
    try
    {
      local_c = (ObjectAVLTreeSet)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.allocatePaths();
    if (this.count != 0)
    {
      Entry<K> local_rp = new Entry();
      Entry<K> local_rq = new Entry();
      Entry<K> local_p = local_rp;
      local_rp.left(this.tree);
      Entry<K> local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry<K> cantHappen = local_p.left.clone();
          cantHappen.pred(local_q.left);
          cantHappen.succ(local_q);
          local_q.left(cantHappen);
          local_p = local_p.left;
          local_q = local_q.left;
        }
        else
        {
          while (local_p.succ())
          {
            local_p = local_p.right;
            if (local_p == null)
            {
              local_q.right = null;
              local_c.tree = local_rq.left;
              for (local_c.firstEntry = local_c.tree; local_c.firstEntry.left != null; local_c.firstEntry = local_c.firstEntry.left) {}
              for (local_c.lastEntry = local_c.tree; local_c.lastEntry.right != null; local_c.lastEntry = local_c.lastEntry.right) {}
              return local_c;
            }
            local_q = local_q.right;
          }
          local_p = local_p.right;
          local_q = local_q.right;
        }
        if (!local_p.succ())
        {
          Entry<K> cantHappen = local_p.right.clone();
          cantHappen.succ(local_q.right);
          cantHappen.pred(local_q);
          local_q.right(cantHappen);
        }
      }
    }
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    int local_n = this.count;
    ObjectAVLTreeSet<K>.SetIterator local_i = new SetIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0) {
      local_s.writeObject(local_i.next());
    }
  }
  
  private Entry<K> readTree(ObjectInputStream local_s, int local_n, Entry<K> pred, Entry<K> succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry<K> top = new Entry(local_s.readObject());
      top.pred(pred);
      top.succ(succ);
      return top;
    }
    if (local_n == 2)
    {
      Entry<K> top = new Entry(local_s.readObject());
      top.right(new Entry(local_s.readObject()));
      top.right.pred(top);
      top.balance(1);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry<K> top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readObject();
    top.right(readTree(local_s, top, top, succ));
    if (local_n == (local_n & -local_n)) {
      top.balance(1);
    }
    return top;
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    setActualComparator();
    allocatePaths();
    if (this.count != 0)
    {
      this.tree = readTree(local_s, this.count, null, null);
      for (Entry<K> local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private static <K> int checkTree(Entry<K> local_e)
  {
    return 0;
  }
  
  private final class Subset
    extends AbstractObjectSortedSet<K>
    implements Serializable, ObjectSortedSet<K>
  {
    public static final long serialVersionUID = -7046029254386353129L;
    K from;
    K field_57;
    boolean bottom;
    boolean top;
    
    public Subset(boolean from, K bottom, boolean local_to)
    {
      if ((!bottom) && (!top) && (ObjectAVLTreeSet.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start element (" + from + ") is larger than end element (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_57 = local_to;
      this.top = top;
    }
    
    public void clear()
    {
      ObjectAVLTreeSet<K>.Subset.SubsetIterator local_i = new SubsetIterator();
      while (local_i.hasNext())
      {
        local_i.next();
        local_i.remove();
      }
    }
    
    final boolean in(K local_k)
    {
      return ((this.bottom) || (ObjectAVLTreeSet.this.compare(local_k, this.from) >= 0)) && ((this.top) || (ObjectAVLTreeSet.this.compare(local_k, this.field_57) < 0));
    }
    
    public boolean contains(Object local_k)
    {
      return (in(local_k)) && (ObjectAVLTreeSet.this.contains(local_k));
    }
    
    public boolean add(K local_k)
    {
      if (!in(local_k)) {
        throw new IllegalArgumentException("Element (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_57)) + ")");
      }
      return ObjectAVLTreeSet.this.add(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      if (!in(local_k)) {
        return false;
      }
      return ObjectAVLTreeSet.this.remove(local_k);
    }
    
    public int size()
    {
      ObjectAVLTreeSet<K>.Subset.SubsetIterator local_i = new SubsetIterator();
      int local_n = 0;
      while (local_i.hasNext())
      {
        local_n++;
        local_i.next();
      }
      return local_n;
    }
    
    public boolean isEmpty()
    {
      return !new SubsetIterator().hasNext();
    }
    
    public Comparator<? super K> comparator()
    {
      return ObjectAVLTreeSet.this.actualComparator;
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new SubsetIterator();
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new SubsetIterator(from);
    }
    
    public ObjectSortedSet<K> headSet(K local_to)
    {
      if (this.top) {
        return new Subset(ObjectAVLTreeSet.this, this.from, this.bottom, local_to, false);
      }
      return ObjectAVLTreeSet.this.compare(local_to, this.field_57) < 0 ? new Subset(ObjectAVLTreeSet.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      if (this.bottom) {
        return new Subset(ObjectAVLTreeSet.this, from, false, this.field_57, this.top);
      }
      return ObjectAVLTreeSet.this.compare(from, this.from) > 0 ? new Subset(ObjectAVLTreeSet.this, from, false, this.field_57, this.top) : this;
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Subset(ObjectAVLTreeSet.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = ObjectAVLTreeSet.this.compare(local_to, this.field_57) < 0 ? local_to : this.field_57;
      }
      if (!this.bottom) {
        from = ObjectAVLTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_57)) {
        return this;
      }
      return new Subset(ObjectAVLTreeSet.this, from, false, local_to, false);
    }
    
    public ObjectAVLTreeSet.Entry<K> firstEntry()
    {
      if (ObjectAVLTreeSet.this.tree == null) {
        return null;
      }
      ObjectAVLTreeSet.Entry<K> local_e;
      ObjectAVLTreeSet.Entry<K> local_e;
      if (this.bottom)
      {
        local_e = ObjectAVLTreeSet.this.firstEntry;
      }
      else
      {
        local_e = ObjectAVLTreeSet.this.locateKey(this.from);
        if (ObjectAVLTreeSet.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (ObjectAVLTreeSet.this.compare(local_e.key, this.field_57) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public ObjectAVLTreeSet.Entry<K> lastEntry()
    {
      if (ObjectAVLTreeSet.this.tree == null) {
        return null;
      }
      ObjectAVLTreeSet.Entry<K> local_e;
      ObjectAVLTreeSet.Entry<K> local_e;
      if (this.top)
      {
        local_e = ObjectAVLTreeSet.this.lastEntry;
      }
      else
      {
        local_e = ObjectAVLTreeSet.this.locateKey(this.field_57);
        if (ObjectAVLTreeSet.this.compare(local_e.key, this.field_57) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (ObjectAVLTreeSet.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public K first()
    {
      ObjectAVLTreeSet.Entry<K> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public K last()
    {
      ObjectAVLTreeSet.Entry<K> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    private final class SubsetIterator
      extends ObjectAVLTreeSet.SetIterator
    {
      SubsetIterator()
      {
        super();
        this.next = ObjectAVLTreeSet.Subset.this.firstEntry();
      }
      
      SubsetIterator()
      {
        this();
        if (this.next != null) {
          if ((!ObjectAVLTreeSet.Subset.this.bottom) && (ObjectAVLTreeSet.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!ObjectAVLTreeSet.Subset.this.top) && (ObjectAVLTreeSet.this.compare(local_k, (this.prev = ObjectAVLTreeSet.Subset.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = ObjectAVLTreeSet.this.locateKey(local_k);
            if (ObjectAVLTreeSet.this.compare(this.next.key, local_k) <= 0)
            {
              this.prev = this.next;
              this.next = this.next.next();
            }
            else
            {
              this.prev = this.next.prev();
            }
          }
        }
      }
      
      void updatePrevious()
      {
        this.prev = this.prev.prev();
        if ((!ObjectAVLTreeSet.Subset.this.bottom) && (this.prev != null) && (ObjectAVLTreeSet.this.compare(this.prev.key, ObjectAVLTreeSet.Subset.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!ObjectAVLTreeSet.Subset.this.top) && (this.next != null) && (ObjectAVLTreeSet.this.compare(this.next.key, ObjectAVLTreeSet.Subset.this.field_57) >= 0)) {
          this.next = null;
        }
      }
    }
  }
  
  private class SetIterator
    extends AbstractObjectListIterator<K>
  {
    ObjectAVLTreeSet.Entry<K> prev;
    ObjectAVLTreeSet.Entry<K> next;
    ObjectAVLTreeSet.Entry<K> curr;
    int index = 0;
    
    SetIterator()
    {
      this.next = ObjectAVLTreeSet.this.firstEntry;
    }
    
    SetIterator()
    {
      if ((this.next = ObjectAVLTreeSet.this.locateKey(local_k)) != null) {
        if (ObjectAVLTreeSet.this.compare(this.next.key, local_k) <= 0)
        {
          this.prev = this.next;
          this.next = this.next.next();
        }
        else
        {
          this.prev = this.next.prev();
        }
      }
    }
    
    public boolean hasNext()
    {
      return this.next != null;
    }
    
    public boolean hasPrevious()
    {
      return this.prev != null;
    }
    
    void updateNext()
    {
      this.next = this.next.next();
    }
    
    ObjectAVLTreeSet.Entry<K> nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.prev = this.next);
      this.index += 1;
      updateNext();
      return this.curr;
    }
    
    public K next()
    {
      return nextEntry().key;
    }
    
    public K previous()
    {
      return previousEntry().key;
    }
    
    void updatePrevious()
    {
      this.prev = this.prev.prev();
    }
    
    ObjectAVLTreeSet.Entry<K> previousEntry()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.next = this.prev);
      this.index -= 1;
      updatePrevious();
      return this.curr;
    }
    
    public int nextIndex()
    {
      return this.index;
    }
    
    public int previousIndex()
    {
      return this.index - 1;
    }
    
    public void remove()
    {
      if (this.curr == null) {
        throw new IllegalStateException();
      }
      if (this.curr == this.prev) {
        this.index -= 1;
      }
      this.next = (this.prev = this.curr);
      updatePrevious();
      updateNext();
      ObjectAVLTreeSet.this.remove(this.curr.key);
      this.curr = null;
    }
  }
  
  private static final class Entry<K>
    implements Cloneable
  {
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    private static final int BALANCE_MASK = 255;
    K key;
    Entry<K> left;
    Entry<K> right;
    int info;
    
    Entry() {}
    
    Entry(K local_k)
    {
      this.key = local_k;
      this.info = -1073741824;
    }
    
    Entry<K> left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry<K> right()
    {
      return (this.info & 0x80000000) != 0 ? null : this.right;
    }
    
    boolean pred()
    {
      return (this.info & 0x40000000) != 0;
    }
    
    boolean succ()
    {
      return (this.info & 0x80000000) != 0;
    }
    
    void pred(boolean pred)
    {
      if (pred) {
        this.info |= 1073741824;
      } else {
        this.info &= -1073741825;
      }
    }
    
    void succ(boolean succ)
    {
      if (succ) {
        this.info |= -2147483648;
      } else {
        this.info &= 2147483647;
      }
    }
    
    void pred(Entry<K> pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry<K> succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry<K> left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry<K> right)
    {
      this.info &= 2147483647;
      this.right = right;
    }
    
    int balance()
    {
      return (byte)this.info;
    }
    
    void balance(int level)
    {
      this.info &= -256;
      this.info |= level & 0xFF;
    }
    
    void incBalance()
    {
      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
    }
    
    protected void decBalance()
    {
      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
    }
    
    Entry<K> next()
    {
      Entry<K> next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry<K> prev()
    {
      Entry<K> prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public Entry<K> clone()
    {
      Entry<K> local_c;
      try
      {
        local_c = (Entry)super.clone();
      }
      catch (CloneNotSupportedException cantHappen)
      {
        throw new InternalError();
      }
      local_c.key = this.key;
      local_c.info = this.info;
      return local_c;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Entry)) {
        return false;
      }
      Entry<?> local_e = (Entry)local_o;
      return this.key == null ? false : local_e.key == null ? true : this.key.equals(local_e.key);
    }
    
    public int hashCode()
    {
      return this.key == null ? 0 : this.key.hashCode();
    }
    
    public String toString()
    {
      return String.valueOf(this.key);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */