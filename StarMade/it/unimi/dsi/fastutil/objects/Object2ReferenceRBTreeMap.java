package it.unimi.dsi.fastutil.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.SortedMap;

public class Object2ReferenceRBTreeMap<K, V>
  extends AbstractObject2ReferenceSortedMap<K, V>
  implements Serializable, Cloneable
{
  protected transient Entry<K, V> tree;
  protected int count;
  protected transient Entry<K, V> firstEntry;
  protected transient Entry<K, V> lastEntry;
  protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
  protected volatile transient ObjectSortedSet<K> keys;
  protected volatile transient ReferenceCollection<V> values;
  protected transient boolean modified;
  protected Comparator<? super K> storedComparator;
  protected transient Comparator<? super K> actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  private transient Entry<K, V>[] nodePath;
  
  public Object2ReferenceRBTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    this.actualComparator = this.storedComparator;
  }
  
  public Object2ReferenceRBTreeMap(Comparator<? super K> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Object2ReferenceRBTreeMap(Map<? extends K, ? extends V> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Object2ReferenceRBTreeMap(SortedMap<K, V> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Object2ReferenceRBTreeMap(Object2ReferenceMap<? extends K, ? extends V> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Object2ReferenceRBTreeMap(Object2ReferenceSortedMap<K, V> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Object2ReferenceRBTreeMap(K[] local_k, V[] local_v, Comparator<? super K> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2ReferenceRBTreeMap(K[] local_k, V[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(K local_k1, K local_k2)
  {
    return this.actualComparator == null ? ((Comparable)local_k1).compareTo(local_k2) : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry<K, V> findKey(K local_k)
  {
    int cmp;
    for (Entry<K, V> local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry<K, V> locateKey(K local_k)
  {
    Entry<K, V> local_e = this.tree;
    Entry<K, V> last = this.tree;
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
  
  public V put(K local_k, V local_v)
  {
    this.modified = false;
    int maxDepth = 0;
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k, local_v));
    }
    else
    {
      Entry<K, V> local_p = this.tree;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          V oldValue = local_p.value;
          local_p.value = local_v;
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return oldValue;
        }
        this.nodePath[local_i] = local_p;
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            Entry<K, V> local_e = new Entry(local_k, local_v);
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
            Entry<K, V> local_e = new Entry(local_k, local_v);
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
      Entry<K, V> local_e;
      this.modified = true;
      maxDepth = local_i--;
      while ((local_i > 0) && (!this.nodePath[local_i].black())) {
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<K, V> oldValue = this.nodePath[(local_i - 1)].right;
          if ((!this.nodePath[(local_i - 1)].succ()) && (!oldValue.black()))
          {
            this.nodePath[local_i].black(true);
            oldValue.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] == 0)
            {
              oldValue = this.nodePath[local_i];
            }
            else
            {
              Entry<K, V> local_x = this.nodePath[local_i];
              oldValue = local_x.right;
              local_x.right = oldValue.left;
              oldValue.left = local_x;
              this.nodePath[(local_i - 1)].left = oldValue;
              if (oldValue.pred())
              {
                oldValue.pred(false);
                local_x.succ(oldValue);
              }
            }
            Entry<K, V> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            oldValue.black(true);
            local_x.left = oldValue.right;
            oldValue.right = local_x;
            if (local_i < 2) {
              this.tree = oldValue;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = oldValue;
            } else {
              this.nodePath[(local_i - 2)].left = oldValue;
            }
            if (!oldValue.succ()) {
              break;
            }
            oldValue.succ(false);
            local_x.pred(oldValue);
            break;
          }
        }
        else
        {
          Entry<K, V> oldValue = this.nodePath[(local_i - 1)].left;
          if ((!this.nodePath[(local_i - 1)].pred()) && (!oldValue.black()))
          {
            this.nodePath[local_i].black(true);
            oldValue.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] != 0)
            {
              oldValue = this.nodePath[local_i];
            }
            else
            {
              Entry<K, V> local_x = this.nodePath[local_i];
              oldValue = local_x.left;
              local_x.left = oldValue.right;
              oldValue.right = local_x;
              this.nodePath[(local_i - 1)].right = oldValue;
              if (oldValue.succ())
              {
                oldValue.succ(false);
                local_x.pred(oldValue);
              }
            }
            Entry<K, V> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            oldValue.black(true);
            local_x.right = oldValue.left;
            oldValue.left = local_x;
            if (local_i < 2) {
              this.tree = oldValue;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = oldValue;
            } else {
              this.nodePath[(local_i - 2)].left = oldValue;
            }
            if (!oldValue.pred()) {
              break;
            }
            oldValue.pred(false);
            local_x.succ(oldValue);
            break;
          }
        }
      }
    }
    this.tree.black(true);
    while (maxDepth-- != 0) {
      this.nodePath[maxDepth] = null;
    }
    return this.defRetValue;
  }
  
  public V remove(Object local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry<K, V> local_p = this.tree;
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
          return this.defRetValue;
        }
      }
      else if ((local_p = local_p.left()) == null)
      {
        while (local_i-- != 0) {
          this.nodePath[local_i] = null;
        }
        return this.defRetValue;
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
      Entry<K, V> local_r = local_p.right;
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
        Entry<K, V> local_s;
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
          Entry<K, V> local_r = this.dirPath[(local_i - 1)] != 0 ? this.nodePath[(local_i - 1)].right : this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            break;
          }
        }
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<K, V> local_r = this.nodePath[(local_i - 1)].right;
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
              Entry<K, V> local_s = local_r.left;
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
          Entry<K, V> local_r = this.nodePath[(local_i - 1)].left;
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
              Entry<K, V> local_s = local_r.right;
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
    this.modified = true;
    this.count -= 1;
    while (color-- != 0) {
      this.nodePath[color] = null;
    }
    return local_p.value;
  }
  
  public boolean containsValue(Object local_v)
  {
    Object2ReferenceRBTreeMap<K, V>.ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      Object local_ev = local_i.next();
      if (local_ev == local_v) {
        return true;
      }
    }
    return false;
  }
  
  public void clear()
  {
    this.count = 0;
    this.tree = null;
    this.entries = null;
    this.values = null;
    this.keys = null;
    this.firstEntry = (this.lastEntry = null);
  }
  
  public boolean containsKey(Object local_k)
  {
    return findKey(local_k) != null;
  }
  
  public int size()
  {
    return this.count;
  }
  
  public boolean isEmpty()
  {
    return this.count == 0;
  }
  
  public V get(Object local_k)
  {
    Entry<K, V> local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public K firstKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public K lastKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator = new Comparator()
        {
          public int compare(Object2ReferenceMap.Entry<K, V> local_x, Object2ReferenceMap.Entry<K, V> local_y)
          {
            return Object2ReferenceRBTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator()
        {
          return new Object2ReferenceRBTreeMap.EntryIterator(Object2ReferenceRBTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from)
        {
          return new Object2ReferenceRBTreeMap.EntryIterator(Object2ReferenceRBTreeMap.this, from.getKey());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<K, V> local_e = (Map.Entry)local_o;
          Object2ReferenceRBTreeMap.Entry<K, V> local_f = Object2ReferenceRBTreeMap.this.findKey(local_e.getKey());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<K, V> local_e = (Map.Entry)local_o;
          Object2ReferenceRBTreeMap.Entry<K, V> local_f = Object2ReferenceRBTreeMap.this.findKey(local_e.getKey());
          if (local_f != null) {
            Object2ReferenceRBTreeMap.this.remove(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Object2ReferenceRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Object2ReferenceRBTreeMap.this.clear();
        }
        
        public Object2ReferenceMap.Entry<K, V> first()
        {
          return Object2ReferenceRBTreeMap.this.firstEntry;
        }
        
        public Object2ReferenceMap.Entry<K, V> last()
        {
          return Object2ReferenceRBTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> local_to)
        {
          return Object2ReferenceRBTreeMap.this.subMap(from.getKey(), local_to.getKey()).object2ReferenceEntrySet();
        }
        
        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> local_to)
        {
          return Object2ReferenceRBTreeMap.this.headMap(local_to.getKey()).object2ReferenceEntrySet();
        }
        
        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from)
        {
          return Object2ReferenceRBTreeMap.this.tailMap(from.getKey()).object2ReferenceEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public ObjectSortedSet<K> keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ReferenceCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractReferenceCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Object2ReferenceRBTreeMap.ValueIterator(Object2ReferenceRBTreeMap.this, null);
        }
        
        public boolean contains(Object local_k)
        {
          return Object2ReferenceRBTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Object2ReferenceRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Object2ReferenceRBTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public Comparator<? super K> comparator()
  {
    return this.actualComparator;
  }
  
  public Object2ReferenceSortedMap<K, V> headMap(K local_to)
  {
    return new Submap(null, true, local_to, false);
  }
  
  public Object2ReferenceSortedMap<K, V> tailMap(K from)
  {
    return new Submap(from, false, null, true);
  }
  
  public Object2ReferenceSortedMap<K, V> subMap(K from, K local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Object2ReferenceRBTreeMap<K, V> clone()
  {
    Object2ReferenceRBTreeMap<K, V> local_c;
    try
    {
      local_c = (Object2ReferenceRBTreeMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.allocatePaths();
    if (this.count != 0)
    {
      Entry<K, V> local_rp = new Entry();
      Entry<K, V> local_rq = new Entry();
      Entry<K, V> local_p = local_rp;
      local_rp.left(this.tree);
      Entry<K, V> local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry<K, V> cantHappen = local_p.left.clone();
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
          Entry<K, V> cantHappen = local_p.right.clone();
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
    Object2ReferenceRBTreeMap<K, V>.EntryIterator local_i = new EntryIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0)
    {
      Entry<K, V> local_e = local_i.nextEntry();
      local_s.writeObject(local_e.key);
      local_s.writeObject(local_e.value);
    }
  }
  
  private Entry<K, V> readTree(ObjectInputStream local_s, int local_n, Entry<K, V> pred, Entry<K, V> succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry<K, V> top = new Entry(local_s.readObject(), local_s.readObject());
      top.pred(pred);
      top.succ(succ);
      top.black(true);
      return top;
    }
    if (local_n == 2)
    {
      Entry<K, V> top = new Entry(local_s.readObject(), local_s.readObject());
      top.black(true);
      top.right(new Entry(local_s.readObject(), local_s.readObject()));
      top.right.pred(top);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry<K, V> top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readObject();
    top.value = local_s.readObject();
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
      for (Entry<K, V> local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private void checkNodePath() {}
  
  private int checkTree(Entry<K, V> local_e, int local_d, int local_D)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractObject2ReferenceSortedMap<K, V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    K from;
    K field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
    protected volatile transient ObjectSortedSet<K> keys;
    protected volatile transient ReferenceCollection<V> values;
    
    public Submap(boolean from, K bottom, boolean local_to)
    {
      if ((!bottom) && (!top) && (Object2ReferenceRBTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Object2ReferenceRBTreeMap.this.defRetValue;
    }
    
    public void clear()
    {
      Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        local_i.nextEntry();
        local_i.remove();
      }
    }
    
    final boolean in5(K local_k)
    {
      return ((this.bottom) || (Object2ReferenceRBTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Object2ReferenceRBTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator()
          {
            return new Object2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Object2ReferenceRBTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from)
          {
            return new Object2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Object2ReferenceRBTreeMap.Submap.this, from.getKey());
          }
          
          public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator()
          {
            return Object2ReferenceRBTreeMap.this.object2ReferenceEntrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<K, V> local_e = (Map.Entry)local_o;
            Object2ReferenceRBTreeMap.Entry<K, V> local_f = Object2ReferenceRBTreeMap.this.findKey(local_e.getKey());
            return (local_f != null) && (Object2ReferenceRBTreeMap.Submap.this.in5(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<K, V> local_e = (Map.Entry)local_o;
            Object2ReferenceRBTreeMap.Entry<K, V> local_f = Object2ReferenceRBTreeMap.this.findKey(local_e.getKey());
            if ((local_f != null) && (Object2ReferenceRBTreeMap.Submap.this.in5(local_f.key))) {
              Object2ReferenceRBTreeMap.Submap.this.remove(local_f.key);
            }
            return local_f != null;
          }
          
          public int size()
          {
            int local_c = 0;
            Iterator<?> local_i = iterator();
            while (local_i.hasNext())
            {
              local_c++;
              local_i.next();
            }
            return local_c;
          }
          
          public boolean isEmpty()
          {
            return !new Object2ReferenceRBTreeMap.Submap.SubmapIterator(Object2ReferenceRBTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Object2ReferenceRBTreeMap.Submap.this.clear();
          }
          
          public Object2ReferenceMap.Entry<K, V> first()
          {
            return Object2ReferenceRBTreeMap.Submap.this.firstEntry();
          }
          
          public Object2ReferenceMap.Entry<K, V> last()
          {
            return Object2ReferenceRBTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> local_to)
          {
            return Object2ReferenceRBTreeMap.Submap.this.subMap(from.getKey(), local_to.getKey()).object2ReferenceEntrySet();
          }
          
          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> local_to)
          {
            return Object2ReferenceRBTreeMap.Submap.this.headMap(local_to.getKey()).object2ReferenceEntrySet();
          }
          
          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from)
          {
            return Object2ReferenceRBTreeMap.Submap.this.tailMap(from.getKey()).object2ReferenceEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public ReferenceCollection<V> values()
    {
      if (this.values == null) {
        this.values = new AbstractReferenceCollection()
        {
          public ObjectIterator<V> iterator()
          {
            return new Object2ReferenceRBTreeMap.Submap.SubmapValueIterator(Object2ReferenceRBTreeMap.Submap.this, null);
          }
          
          public boolean contains(Object local_k)
          {
            return Object2ReferenceRBTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Object2ReferenceRBTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Object2ReferenceRBTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(Object local_k)
    {
      return (in5(local_k)) && (Object2ReferenceRBTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(Object local_v)
    {
      Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        Object local_ev = local_i.nextEntry().value;
        if (local_ev == local_v) {
          return true;
        }
      }
      return false;
    }
    
    public V get(Object local_k)
    {
      K local_kk = local_k;
      Object2ReferenceRBTreeMap.Entry<K, V> local_e;
      return (in5(local_kk)) && ((local_e = Object2ReferenceRBTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public V put(K local_k, V local_v)
    {
      Object2ReferenceRBTreeMap.this.modified = false;
      if (!in5(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      V oldValue = Object2ReferenceRBTreeMap.this.put(local_k, local_v);
      return Object2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public V remove(Object local_k)
    {
      Object2ReferenceRBTreeMap.this.modified = false;
      if (!in5(local_k)) {
        return this.defRetValue;
      }
      V oldValue = Object2ReferenceRBTreeMap.this.remove(local_k);
      return Object2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public int size()
    {
      Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator local_i = new SubmapIterator();
      int local_n = 0;
      while (local_i.hasNext())
      {
        local_n++;
        local_i.nextEntry();
      }
      return local_n;
    }
    
    public boolean isEmpty()
    {
      return !new SubmapIterator().hasNext();
    }
    
    public Comparator<? super K> comparator()
    {
      return Object2ReferenceRBTreeMap.this.actualComparator;
    }
    
    public Object2ReferenceSortedMap<K, V> headMap(K local_to)
    {
      if (this.top) {
        return new Submap(Object2ReferenceRBTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Object2ReferenceRBTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Object2ReferenceRBTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Object2ReferenceSortedMap<K, V> tailMap(K from)
    {
      if (this.bottom) {
        return new Submap(Object2ReferenceRBTreeMap.this, from, false, this.field_47, this.top);
      }
      return Object2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ReferenceRBTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Object2ReferenceSortedMap<K, V> subMap(K from, K local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Object2ReferenceRBTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Object2ReferenceRBTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Object2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Object2ReferenceRBTreeMap.this, from, false, local_to, false);
    }
    
    public Object2ReferenceRBTreeMap.Entry<K, V> firstEntry()
    {
      if (Object2ReferenceRBTreeMap.this.tree == null) {
        return null;
      }
      Object2ReferenceRBTreeMap.Entry<K, V> local_e;
      Object2ReferenceRBTreeMap.Entry<K, V> local_e;
      if (this.bottom)
      {
        local_e = Object2ReferenceRBTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Object2ReferenceRBTreeMap.this.locateKey(this.from);
        if (Object2ReferenceRBTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Object2ReferenceRBTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Object2ReferenceRBTreeMap.Entry<K, V> lastEntry()
    {
      if (Object2ReferenceRBTreeMap.this.tree == null) {
        return null;
      }
      Object2ReferenceRBTreeMap.Entry<K, V> local_e;
      Object2ReferenceRBTreeMap.Entry<K, V> local_e;
      if (this.top)
      {
        local_e = Object2ReferenceRBTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Object2ReferenceRBTreeMap.this.locateKey(this.field_47);
        if (Object2ReferenceRBTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Object2ReferenceRBTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public K firstKey()
    {
      Object2ReferenceRBTreeMap.Entry<K, V> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public K lastKey()
    {
      Object2ReferenceRBTreeMap.Entry<K, V> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    private final class SubmapValueIterator
      extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
      implements ObjectListIterator<V>
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public V next()
      {
        return nextEntry().value;
      }
      
      public V previous()
      {
        return previousEntry().value;
      }
      
      public void set(V local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(V local_v)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private final class SubmapKeyIterator
      extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
      implements ObjectListIterator<K>
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator()
      {
        super(from);
      }
      
      public K next()
      {
        return nextEntry().key;
      }
      
      public K previous()
      {
        return previousEntry().key;
      }
      
      public void set(K local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(K local_k)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
      implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator()
      {
        super(local_k);
      }
      
      public Object2ReferenceMap.Entry<K, V> next()
      {
        return nextEntry();
      }
      
      public Object2ReferenceMap.Entry<K, V> previous()
      {
        return previousEntry();
      }
      
      public void set(Object2ReferenceMap.Entry<K, V> local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Object2ReferenceMap.Entry<K, V> local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Object2ReferenceRBTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Object2ReferenceRBTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator()
      {
        this();
        if (this.next != null) {
          if ((!Object2ReferenceRBTreeMap.Submap.this.bottom) && (Object2ReferenceRBTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Object2ReferenceRBTreeMap.Submap.this.top) && (Object2ReferenceRBTreeMap.this.compare(local_k, (this.prev = Object2ReferenceRBTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Object2ReferenceRBTreeMap.this.locateKey(local_k);
            if (Object2ReferenceRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
        if ((!Object2ReferenceRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ReferenceRBTreeMap.this.compare(this.prev.key, Object2ReferenceRBTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Object2ReferenceRBTreeMap.Submap.this.top) && (this.next != null) && (Object2ReferenceRBTreeMap.this.compare(this.next.key, Object2ReferenceRBTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractObject2ReferenceSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public ObjectBidirectionalIterator<K> iterator()
      {
        return new Object2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Object2ReferenceRBTreeMap.Submap.this);
      }
      
      public ObjectBidirectionalIterator<K> iterator(K from)
      {
        return new Object2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Object2ReferenceRBTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
    implements ObjectListIterator<V>
  {
    private ValueIterator()
    {
      super();
    }
    
    public V next()
    {
      return nextEntry().value;
    }
    
    public V previous()
    {
      return previousEntry().value;
    }
    
    public void set(V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(V local_v)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class KeySet
    extends AbstractObject2ReferenceSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new Object2ReferenceRBTreeMap.KeyIterator(Object2ReferenceRBTreeMap.this);
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new Object2ReferenceRBTreeMap.KeyIterator(Object2ReferenceRBTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
    implements ObjectListIterator<K>
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator()
    {
      super(local_k);
    }
    
    public K next()
    {
      return nextEntry().key;
    }
    
    public K previous()
    {
      return previousEntry().key;
    }
    
    public void set(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
    implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator()
    {
      super(local_k);
    }
    
    public Object2ReferenceMap.Entry<K, V> next()
    {
      return nextEntry();
    }
    
    public Object2ReferenceMap.Entry<K, V> previous()
    {
      return previousEntry();
    }
    
    public void set(Object2ReferenceMap.Entry<K, V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Object2ReferenceMap.Entry<K, V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Object2ReferenceRBTreeMap.Entry<K, V> prev;
    Object2ReferenceRBTreeMap.Entry<K, V> next;
    Object2ReferenceRBTreeMap.Entry<K, V> curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Object2ReferenceRBTreeMap.this.firstEntry;
    }
    
    TreeIterator()
    {
      if ((this.next = Object2ReferenceRBTreeMap.this.locateKey(local_k)) != null) {
        if (Object2ReferenceRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
    
    Object2ReferenceRBTreeMap.Entry<K, V> nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.prev = this.next);
      this.index += 1;
      updateNext();
      return this.curr;
    }
    
    void updatePrevious()
    {
      this.prev = this.prev.prev();
    }
    
    Object2ReferenceRBTreeMap.Entry<K, V> previousEntry()
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
      Object2ReferenceRBTreeMap.this.remove(this.curr.key);
      this.curr = null;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
    
    public int back(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasPrevious())) {
        previousEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private static final class Entry<K, V>
    implements Cloneable, Object2ReferenceMap.Entry<K, V>
  {
    private static final int BLACK_MASK = 1;
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    K key;
    V value;
    Entry<K, V> left;
    Entry<K, V> right;
    int info;
    
    Entry() {}
    
    Entry(K local_k, V local_v)
    {
      this.key = local_k;
      this.value = local_v;
      this.info = -1073741824;
    }
    
    Entry<K, V> left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry<K, V> right()
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
    
    void pred(Entry<K, V> pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry<K, V> succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry<K, V> left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry<K, V> right)
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
    
    Entry<K, V> next()
    {
      Entry<K, V> next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry<K, V> prev()
    {
      Entry<K, V> prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public K getKey()
    {
      return this.key;
    }
    
    public V getValue()
    {
      return this.value;
    }
    
    public V setValue(V value)
    {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Entry<K, V> clone()
    {
      Entry<K, V> local_c;
      try
      {
        local_c = (Entry)super.clone();
      }
      catch (CloneNotSupportedException cantHappen)
      {
        throw new InternalError();
      }
      local_c.key = this.key;
      local_c.value = this.value;
      local_c.info = this.info;
      return local_c;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> local_e = (Map.Entry)local_o;
      return (this.key == null ? local_e.getKey() == null : this.key.equals(local_e.getKey())) && (this.value == local_e.getValue());
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */