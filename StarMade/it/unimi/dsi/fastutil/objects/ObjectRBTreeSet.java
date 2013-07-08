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

public class ObjectRBTreeSet<K>
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
  private transient Entry<K>[] nodePath;
  
  public ObjectRBTreeSet()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    this.actualComparator = this.storedComparator;
  }
  
  public ObjectRBTreeSet(Comparator<? super K> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public ObjectRBTreeSet(Collection<? extends K> local_c)
  {
    this();
    addAll(local_c);
  }
  
  public ObjectRBTreeSet(SortedSet<K> local_s)
  {
    this(local_s.comparator());
    addAll(local_s);
  }
  
  public ObjectRBTreeSet(ObjectCollection<? extends K> local_c)
  {
    this();
    addAll(local_c);
  }
  
  public ObjectRBTreeSet(ObjectSortedSet<K> local_s)
  {
    this(local_s.comparator());
    addAll(local_s);
  }
  
  public ObjectRBTreeSet(ObjectIterator<? extends K> local_i)
  {
    allocatePaths();
    while (local_i.hasNext()) {
      add(local_i.next());
    }
  }
  
  public ObjectRBTreeSet(Iterator<? extends K> local_i)
  {
    this(ObjectIterators.asObjectIterator(local_i));
  }
  
  public ObjectRBTreeSet(K[] local_a, int offset, int length, Comparator<? super K> local_c)
  {
    this(local_c);
    ObjectArrays.ensureOffsetLength(local_a, offset, length);
    for (int local_i = 0; local_i < length; local_i++) {
      add(local_a[(offset + local_i)]);
    }
  }
  
  public ObjectRBTreeSet(K[] local_a, int offset, int length)
  {
    this(local_a, offset, length, null);
  }
  
  public ObjectRBTreeSet(K[] local_a)
  {
    this();
    int local_i = local_a.length;
    while (local_i-- != 0) {
      add(local_a[local_i]);
    }
  }
  
  public ObjectRBTreeSet(K[] local_a, Comparator<? super K> local_c)
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
    this.dirPath = new boolean[64];
    this.nodePath = new Entry[64];
  }
  
  public boolean add(K local_k)
  {
    int maxDepth = 0;
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k));
    }
    else
    {
      Entry<K> local_p = this.tree;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return false;
        }
        this.nodePath[local_i] = local_p;
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            Entry<K> local_e = new Entry(local_k);
            if (local_p.right == null) {
              this.lastEntry = local_e;
            }
            local_e.left = local_p;
            local_e.right = local_p.right;
            local_p.right(local_e);
            break;
          }
          local_p = local_p.right;
        }
        else
        {
          if (local_p.pred())
          {
            this.count += 1;
            Entry<K> local_e = new Entry(local_k);
            if (local_p.left == null) {
              this.firstEntry = local_e;
            }
            local_e.right = local_p;
            local_e.left = local_p.left;
            local_p.left(local_e);
            break;
          }
          local_p = local_p.left;
        }
      }
      Entry<K> local_e;
      maxDepth = local_i--;
      while ((local_i > 0) && (!this.nodePath[local_i].black())) {
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<K> local_y = this.nodePath[(local_i - 1)].right;
          if ((!this.nodePath[(local_i - 1)].succ()) && (!local_y.black()))
          {
            this.nodePath[local_i].black(true);
            local_y.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] == 0)
            {
              local_y = this.nodePath[local_i];
            }
            else
            {
              Entry<K> local_x = this.nodePath[local_i];
              local_y = local_x.right;
              local_x.right = local_y.left;
              local_y.left = local_x;
              this.nodePath[(local_i - 1)].left = local_y;
              if (local_y.pred())
              {
                local_y.pred(false);
                local_x.succ(local_y);
              }
            }
            Entry<K> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            local_y.black(true);
            local_x.left = local_y.right;
            local_y.right = local_x;
            if (local_i < 2) {
              this.tree = local_y;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_y;
            } else {
              this.nodePath[(local_i - 2)].left = local_y;
            }
            if (!local_y.succ()) {
              break;
            }
            local_y.succ(false);
            local_x.pred(local_y);
            break;
          }
        }
        else
        {
          Entry<K> local_y = this.nodePath[(local_i - 1)].left;
          if ((!this.nodePath[(local_i - 1)].pred()) && (!local_y.black()))
          {
            this.nodePath[local_i].black(true);
            local_y.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] != 0)
            {
              local_y = this.nodePath[local_i];
            }
            else
            {
              Entry<K> local_x = this.nodePath[local_i];
              local_y = local_x.left;
              local_x.left = local_y.right;
              local_y.right = local_x;
              this.nodePath[(local_i - 1)].right = local_y;
              if (local_y.succ())
              {
                local_y.succ(false);
                local_x.pred(local_y);
              }
            }
            Entry<K> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            local_y.black(true);
            local_x.right = local_y.left;
            local_y.left = local_x;
            if (local_i < 2) {
              this.tree = local_y;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_y;
            } else {
              this.nodePath[(local_i - 2)].left = local_y;
            }
            if (!local_y.pred()) {
              break;
            }
            local_y.pred(false);
            local_x.succ(local_y);
            break;
          }
        }
      }
    }
    this.tree.black(true);
    while (maxDepth-- != 0) {
      this.nodePath[maxDepth] = null;
    }
    return true;
  }
  
  public boolean remove(Object local_k)
  {
    if (this.tree == null) {
      return false;
    }
    Entry<K> local_p = this.tree;
    int local_i = 0;
    K local_kk = local_k;
    int cmp;
    while ((cmp = compare(local_kk, local_p.key)) != 0)
    {
      this.dirPath[local_i] = (cmp > 0 ? 1 : false);
      this.nodePath[local_i] = local_p;
      if (this.dirPath[(local_i++)] != 0)
      {
        if ((local_p = local_p.right()) == null)
        {
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return false;
        }
      }
      else if ((local_p = local_p.left()) == null)
      {
        while (local_i-- != 0) {
          this.nodePath[local_i] = null;
        }
        return false;
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
        if (local_i == 0) {
          this.tree = local_p.left;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].succ(local_p.right);
        } else {
          this.nodePath[(local_i - 1)].pred(local_p.left);
        }
      }
      else
      {
        local_p.prev().right = local_p.right;
        if (local_i == 0) {
          this.tree = local_p.left;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].right = local_p.left;
        } else {
          this.nodePath[(local_i - 1)].left = local_p.left;
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
        if (local_i == 0) {
          this.tree = local_r;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].right = local_r;
        } else {
          this.nodePath[(local_i - 1)].left = local_r;
        }
        boolean color = local_r.black();
        local_r.black(local_p.black());
        local_p.black(color);
        this.dirPath[local_i] = true;
        this.nodePath[(local_i++)] = local_r;
      }
      else
      {
        int local_j = local_i++;
        Entry<K> local_s;
        for (;;)
        {
          this.dirPath[local_i] = false;
          this.nodePath[(local_i++)] = local_r;
          local_s = local_r.left;
          if (local_s.pred()) {
            break;
          }
          local_r = local_s;
        }
        this.dirPath[local_j] = true;
        this.nodePath[local_j] = local_s;
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
        local_s.right(local_p.right);
        boolean color = local_s.black();
        local_s.black(local_p.black());
        local_p.black(color);
        if (local_j == 0) {
          this.tree = local_s;
        } else if (this.dirPath[(local_j - 1)] != 0) {
          this.nodePath[(local_j - 1)].right = local_s;
        } else {
          this.nodePath[(local_j - 1)].left = local_s;
        }
      }
    }
    int color = local_i;
    if (local_p.black())
    {
      while (local_i > 0)
      {
        if (((this.dirPath[(local_i - 1)] != 0) && (!this.nodePath[(local_i - 1)].succ())) || ((this.dirPath[(local_i - 1)] == 0) && (!this.nodePath[(local_i - 1)].pred())))
        {
          Entry<K> local_r = this.dirPath[(local_i - 1)] != 0 ? this.nodePath[(local_i - 1)].right : this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            break;
          }
        }
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<K> local_r = this.nodePath[(local_i - 1)].right;
          if (!local_r.black())
          {
            local_r.black(true);
            this.nodePath[(local_i - 1)].black(false);
            this.nodePath[(local_i - 1)].right = local_r.left;
            local_r.left = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            this.nodePath[local_i] = this.nodePath[(local_i - 1)];
            this.dirPath[local_i] = false;
            this.nodePath[(local_i - 1)] = local_r;
            if (color == local_i++) {
              color++;
            }
            local_r = this.nodePath[(local_i - 1)].right;
          }
          if (((local_r.pred()) || (local_r.left.black())) && ((local_r.succ()) || (local_r.right.black())))
          {
            local_r.black(false);
          }
          else
          {
            if ((local_r.succ()) || (local_r.right.black()))
            {
              Entry<K> local_s = local_r.left;
              local_s.black(true);
              local_r.black(false);
              local_r.left = local_s.right;
              local_s.right = local_r;
              local_r = this.nodePath[(local_i - 1)].right = local_s;
              if (local_r.succ())
              {
                local_r.succ(false);
                local_r.right.pred(local_r);
              }
            }
            local_r.black(this.nodePath[(local_i - 1)].black());
            this.nodePath[(local_i - 1)].black(true);
            local_r.right.black(true);
            this.nodePath[(local_i - 1)].right = local_r.left;
            local_r.left = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            if (!local_r.pred()) {
              break;
            }
            local_r.pred(false);
            this.nodePath[(local_i - 1)].succ(local_r);
            break;
          }
        }
        else
        {
          Entry<K> local_r = this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            this.nodePath[(local_i - 1)].black(false);
            this.nodePath[(local_i - 1)].left = local_r.right;
            local_r.right = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            this.nodePath[local_i] = this.nodePath[(local_i - 1)];
            this.dirPath[local_i] = true;
            this.nodePath[(local_i - 1)] = local_r;
            if (color == local_i++) {
              color++;
            }
            local_r = this.nodePath[(local_i - 1)].left;
          }
          if (((local_r.pred()) || (local_r.left.black())) && ((local_r.succ()) || (local_r.right.black())))
          {
            local_r.black(false);
          }
          else
          {
            if ((local_r.pred()) || (local_r.left.black()))
            {
              Entry<K> local_s = local_r.right;
              local_s.black(true);
              local_r.black(false);
              local_r.right = local_s.left;
              local_s.left = local_r;
              local_r = this.nodePath[(local_i - 1)].left = local_s;
              if (local_r.pred())
              {
                local_r.pred(false);
                local_r.left.succ(local_r);
              }
            }
            local_r.black(this.nodePath[(local_i - 1)].black());
            this.nodePath[(local_i - 1)].black(true);
            local_r.left.black(true);
            this.nodePath[(local_i - 1)].left = local_r.right;
            local_r.right = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            if (!local_r.succ()) {
              break;
            }
            local_r.succ(false);
            this.nodePath[(local_i - 1)].pred(local_r);
            break;
          }
        }
        local_i--;
      }
      if (this.tree != null) {
        this.tree.black(true);
      }
    }
    this.count -= 1;
    while (color-- != 0) {
      this.nodePath[color] = null;
    }
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
    ObjectRBTreeSet<K> local_c;
    try
    {
      local_c = (ObjectRBTreeSet)super.clone();
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
    ObjectRBTreeSet<K>.SetIterator local_i = new SetIterator();
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
      top.black(true);
      return top;
    }
    if (local_n == 2)
    {
      Entry<K> top = new Entry(local_s.readObject());
      top.black(true);
      top.right(new Entry(local_s.readObject()));
      top.right.pred(top);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry<K> top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readObject();
    top.black(true);
    top.right(readTree(local_s, top, top, succ));
    if (local_n + 2 == (local_n + 2 & -(local_n + 2))) {
      top.right.black(false);
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
  
  private void checkNodePath() {}
  
  private int checkTree(Entry<K> local_e, int local_d, int local_D)
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
      if ((!bottom) && (!top) && (ObjectRBTreeSet.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start element (" + from + ") is larger than end element (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_57 = local_to;
      this.top = top;
    }
    
    public void clear()
    {
      ObjectRBTreeSet<K>.Subset.SubsetIterator local_i = new SubsetIterator();
      while (local_i.hasNext())
      {
        local_i.next();
        local_i.remove();
      }
    }
    
    final boolean in(K local_k)
    {
      return ((this.bottom) || (ObjectRBTreeSet.this.compare(local_k, this.from) >= 0)) && ((this.top) || (ObjectRBTreeSet.this.compare(local_k, this.field_57) < 0));
    }
    
    public boolean contains(Object local_k)
    {
      return (in(local_k)) && (ObjectRBTreeSet.this.contains(local_k));
    }
    
    public boolean add(K local_k)
    {
      if (!in(local_k)) {
        throw new IllegalArgumentException("Element (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_57)) + ")");
      }
      return ObjectRBTreeSet.this.add(local_k);
    }
    
    public boolean remove(Object local_k)
    {
      if (!in(local_k)) {
        return false;
      }
      return ObjectRBTreeSet.this.remove(local_k);
    }
    
    public int size()
    {
      ObjectRBTreeSet<K>.Subset.SubsetIterator local_i = new SubsetIterator();
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
      return ObjectRBTreeSet.this.actualComparator;
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
        return new Subset(ObjectRBTreeSet.this, this.from, this.bottom, local_to, false);
      }
      return ObjectRBTreeSet.this.compare(local_to, this.field_57) < 0 ? new Subset(ObjectRBTreeSet.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public ObjectSortedSet<K> tailSet(K from)
    {
      if (this.bottom) {
        return new Subset(ObjectRBTreeSet.this, from, false, this.field_57, this.top);
      }
      return ObjectRBTreeSet.this.compare(from, this.from) > 0 ? new Subset(ObjectRBTreeSet.this, from, false, this.field_57, this.top) : this;
    }
    
    public ObjectSortedSet<K> subSet(K from, K local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Subset(ObjectRBTreeSet.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = ObjectRBTreeSet.this.compare(local_to, this.field_57) < 0 ? local_to : this.field_57;
      }
      if (!this.bottom) {
        from = ObjectRBTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_57)) {
        return this;
      }
      return new Subset(ObjectRBTreeSet.this, from, false, local_to, false);
    }
    
    public ObjectRBTreeSet.Entry<K> firstEntry()
    {
      if (ObjectRBTreeSet.this.tree == null) {
        return null;
      }
      ObjectRBTreeSet.Entry<K> local_e;
      ObjectRBTreeSet.Entry<K> local_e;
      if (this.bottom)
      {
        local_e = ObjectRBTreeSet.this.firstEntry;
      }
      else
      {
        local_e = ObjectRBTreeSet.this.locateKey(this.from);
        if (ObjectRBTreeSet.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (ObjectRBTreeSet.this.compare(local_e.key, this.field_57) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public ObjectRBTreeSet.Entry<K> lastEntry()
    {
      if (ObjectRBTreeSet.this.tree == null) {
        return null;
      }
      ObjectRBTreeSet.Entry<K> local_e;
      ObjectRBTreeSet.Entry<K> local_e;
      if (this.top)
      {
        local_e = ObjectRBTreeSet.this.lastEntry;
      }
      else
      {
        local_e = ObjectRBTreeSet.this.locateKey(this.field_57);
        if (ObjectRBTreeSet.this.compare(local_e.key, this.field_57) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (ObjectRBTreeSet.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public K first()
    {
      ObjectRBTreeSet.Entry<K> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public K last()
    {
      ObjectRBTreeSet.Entry<K> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    private final class SubsetIterator
      extends ObjectRBTreeSet.SetIterator
    {
      SubsetIterator()
      {
        super();
        this.next = ObjectRBTreeSet.Subset.this.firstEntry();
      }
      
      SubsetIterator()
      {
        this();
        if (this.next != null) {
          if ((!ObjectRBTreeSet.Subset.this.bottom) && (ObjectRBTreeSet.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!ObjectRBTreeSet.Subset.this.top) && (ObjectRBTreeSet.this.compare(local_k, (this.prev = ObjectRBTreeSet.Subset.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = ObjectRBTreeSet.this.locateKey(local_k);
            if (ObjectRBTreeSet.this.compare(this.next.key, local_k) <= 0)
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
        if ((!ObjectRBTreeSet.Subset.this.bottom) && (this.prev != null) && (ObjectRBTreeSet.this.compare(this.prev.key, ObjectRBTreeSet.Subset.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!ObjectRBTreeSet.Subset.this.top) && (this.next != null) && (ObjectRBTreeSet.this.compare(this.next.key, ObjectRBTreeSet.Subset.this.field_57) >= 0)) {
          this.next = null;
        }
      }
    }
  }
  
  private class SetIterator
    extends AbstractObjectListIterator<K>
  {
    ObjectRBTreeSet.Entry<K> prev;
    ObjectRBTreeSet.Entry<K> next;
    ObjectRBTreeSet.Entry<K> curr;
    int index = 0;
    
    SetIterator()
    {
      this.next = ObjectRBTreeSet.this.firstEntry;
    }
    
    SetIterator()
    {
      if ((this.next = ObjectRBTreeSet.this.locateKey(local_k)) != null) {
        if (ObjectRBTreeSet.this.compare(this.next.key, local_k) <= 0)
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
    
    ObjectRBTreeSet.Entry<K> nextEntry()
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
    
    ObjectRBTreeSet.Entry<K> previousEntry()
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
      ObjectRBTreeSet.this.remove(this.curr.key);
      this.curr = null;
    }
  }
  
  private static final class Entry<K>
    implements Cloneable
  {
    private static final int BLACK_MASK = 1;
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
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
    
    boolean black()
    {
      return (this.info & 0x1) != 0;
    }
    
    void black(boolean black)
    {
      if (black) {
        this.info |= 1;
      } else {
        this.info &= -2;
      }
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectRBTreeSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */