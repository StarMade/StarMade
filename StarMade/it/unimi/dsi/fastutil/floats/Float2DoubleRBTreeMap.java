package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleCollection;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.doubles.DoubleListIterator;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
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

public class Float2DoubleRBTreeMap
  extends AbstractFloat2DoubleSortedMap
  implements Serializable, Cloneable
{
  protected transient Entry tree;
  protected int count;
  protected transient Entry firstEntry;
  protected transient Entry lastEntry;
  protected volatile transient ObjectSortedSet<Float2DoubleMap.Entry> entries;
  protected volatile transient FloatSortedSet keys;
  protected volatile transient DoubleCollection values;
  protected transient boolean modified;
  protected Comparator<? super Float> storedComparator;
  protected transient FloatComparator actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  private transient Entry[] nodePath;
  
  public Float2DoubleRBTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    if ((this.storedComparator == null) || ((this.storedComparator instanceof FloatComparator))) {
      this.actualComparator = ((FloatComparator)this.storedComparator);
    } else {
      this.actualComparator = new FloatComparator()
      {
        public int compare(float local_k1, float local_k2)
        {
          return Float2DoubleRBTreeMap.this.storedComparator.compare(Float.valueOf(local_k1), Float.valueOf(local_k2));
        }
        
        public int compare(Float ok1, Float ok2)
        {
          return Float2DoubleRBTreeMap.this.storedComparator.compare(ok1, ok2);
        }
      };
    }
  }
  
  public Float2DoubleRBTreeMap(Comparator<? super Float> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Float2DoubleRBTreeMap(Map<? extends Float, ? extends Double> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Float2DoubleRBTreeMap(SortedMap<Float, Double> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Float2DoubleRBTreeMap(Float2DoubleMap local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Float2DoubleRBTreeMap(Float2DoubleSortedMap local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Float2DoubleRBTreeMap(float[] local_k, double[] local_v, Comparator<? super Float> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Float2DoubleRBTreeMap(float[] local_k, double[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(float local_k1, float local_k2)
  {
    return this.actualComparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry findKey(float local_k)
  {
    int cmp;
    for (Entry local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry locateKey(float local_k)
  {
    Entry local_e = this.tree;
    Entry last = this.tree;
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
  
  public double put(float local_k, double local_v)
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
      Entry local_p = this.tree;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          double oldValue = local_p.value;
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
            Entry local_e = new Entry(local_k, local_v);
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
            Entry local_e = new Entry(local_k, local_v);
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
      Entry local_e;
      this.modified = true;
      maxDepth = local_i--;
      while ((local_i > 0) && (!this.nodePath[local_i].black())) {
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry oldValue = this.nodePath[(local_i - 1)].right;
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
              Entry local_x = this.nodePath[local_i];
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
            Entry local_x = this.nodePath[(local_i - 1)];
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
          Entry oldValue = this.nodePath[(local_i - 1)].left;
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
              Entry local_x = this.nodePath[local_i];
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
            Entry local_x = this.nodePath[(local_i - 1)];
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
  
  public double remove(float local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry local_p = this.tree;
    int local_i = 0;
    float local_kk = local_k;
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
      Entry local_r = local_p.right;
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
        Entry local_s;
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
          Entry local_r = this.dirPath[(local_i - 1)] != 0 ? this.nodePath[(local_i - 1)].right : this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            break;
          }
        }
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry local_r = this.nodePath[(local_i - 1)].right;
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
              Entry local_s = local_r.left;
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
          Entry local_r = this.nodePath[(local_i - 1)].left;
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
              Entry local_s = local_r.right;
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
  
  public Double put(Float local_ok, Double local_ov)
  {
    double oldValue = put(local_ok.floatValue(), local_ov.doubleValue());
    return this.modified ? null : Double.valueOf(oldValue);
  }
  
  public Double remove(Object local_ok)
  {
    double oldValue = remove(((Float)local_ok).floatValue());
    return this.modified ? Double.valueOf(oldValue) : null;
  }
  
  public boolean containsValue(double local_v)
  {
    ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      double local_ev = local_i.nextDouble();
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
  
  public boolean containsKey(float local_k)
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
  
  public double get(float local_k)
  {
    Entry local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public float firstFloatKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public float lastFloatKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Float2DoubleMap.Entry> comparator = new Comparator()
        {
          public int compare(Float2DoubleMap.Entry local_x, Float2DoubleMap.Entry local_y)
          {
            return Float2DoubleRBTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Float2DoubleMap.Entry> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator()
        {
          return new Float2DoubleRBTreeMap.EntryIterator(Float2DoubleRBTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator(Float2DoubleMap.Entry from)
        {
          return new Float2DoubleRBTreeMap.EntryIterator(Float2DoubleRBTreeMap.this, ((Float)from.getKey()).floatValue());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Float, Double> local_e = (Map.Entry)local_o;
          Float2DoubleRBTreeMap.Entry local_f = Float2DoubleRBTreeMap.this.findKey(((Float)local_e.getKey()).floatValue());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Float, Double> local_e = (Map.Entry)local_o;
          Float2DoubleRBTreeMap.Entry local_f = Float2DoubleRBTreeMap.this.findKey(((Float)local_e.getKey()).floatValue());
          if (local_f != null) {
            Float2DoubleRBTreeMap.this.remove(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Float2DoubleRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Float2DoubleRBTreeMap.this.clear();
        }
        
        public Float2DoubleMap.Entry first()
        {
          return Float2DoubleRBTreeMap.this.firstEntry;
        }
        
        public Float2DoubleMap.Entry last()
        {
          return Float2DoubleRBTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Float2DoubleMap.Entry> subSet(Float2DoubleMap.Entry from, Float2DoubleMap.Entry local_to)
        {
          return Float2DoubleRBTreeMap.this.subMap((Float)from.getKey(), (Float)local_to.getKey()).float2DoubleEntrySet();
        }
        
        public ObjectSortedSet<Float2DoubleMap.Entry> headSet(Float2DoubleMap.Entry local_to)
        {
          return Float2DoubleRBTreeMap.this.headMap((Float)local_to.getKey()).float2DoubleEntrySet();
        }
        
        public ObjectSortedSet<Float2DoubleMap.Entry> tailSet(Float2DoubleMap.Entry from)
        {
          return Float2DoubleRBTreeMap.this.tailMap((Float)from.getKey()).float2DoubleEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public FloatSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public DoubleCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractDoubleCollection()
      {
        public DoubleIterator iterator()
        {
          return new Float2DoubleRBTreeMap.ValueIterator(Float2DoubleRBTreeMap.this, null);
        }
        
        public boolean contains(double local_k)
        {
          return Float2DoubleRBTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Float2DoubleRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Float2DoubleRBTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public FloatComparator comparator()
  {
    return this.actualComparator;
  }
  
  public Float2DoubleSortedMap headMap(float local_to)
  {
    return new Submap(0.0F, true, local_to, false);
  }
  
  public Float2DoubleSortedMap tailMap(float from)
  {
    return new Submap(from, false, 0.0F, true);
  }
  
  public Float2DoubleSortedMap subMap(float from, float local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Float2DoubleRBTreeMap clone()
  {
    Float2DoubleRBTreeMap local_c;
    try
    {
      local_c = (Float2DoubleRBTreeMap)super.clone();
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
      Entry local_rp = new Entry();
      Entry local_rq = new Entry();
      Entry local_p = local_rp;
      local_rp.left(this.tree);
      Entry local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry cantHappen = local_p.left.clone();
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
          Entry cantHappen = local_p.right.clone();
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
    EntryIterator local_i = new EntryIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0)
    {
      Entry local_e = local_i.nextEntry();
      local_s.writeFloat(local_e.key);
      local_s.writeDouble(local_e.value);
    }
  }
  
  private Entry readTree(ObjectInputStream local_s, int local_n, Entry pred, Entry succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry top = new Entry(local_s.readFloat(), local_s.readDouble());
      top.pred(pred);
      top.succ(succ);
      top.black(true);
      return top;
    }
    if (local_n == 2)
    {
      Entry top = new Entry(local_s.readFloat(), local_s.readDouble());
      top.black(true);
      top.right(new Entry(local_s.readFloat(), local_s.readDouble()));
      top.right.pred(top);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readFloat();
    top.value = local_s.readDouble();
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
      for (Entry local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private void checkNodePath() {}
  
  private int checkTree(Entry local_e, int local_d, int local_D)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractFloat2DoubleSortedMap
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    float from;
    float field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Float2DoubleMap.Entry> entries;
    protected volatile transient FloatSortedSet keys;
    protected volatile transient DoubleCollection values;
    
    public Submap(float from, boolean bottom, float local_to, boolean top)
    {
      if ((!bottom) && (!top) && (Float2DoubleRBTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Float2DoubleRBTreeMap.this.defRetValue;
    }
    
    public void clear()
    {
      SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        local_i.nextEntry();
        local_i.remove();
      }
    }
    
    final boolean in(float local_k)
    {
      return ((this.bottom) || (Float2DoubleRBTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Float2DoubleRBTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator()
          {
            return new Float2DoubleRBTreeMap.Submap.SubmapEntryIterator(Float2DoubleRBTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator(Float2DoubleMap.Entry from)
          {
            return new Float2DoubleRBTreeMap.Submap.SubmapEntryIterator(Float2DoubleRBTreeMap.Submap.this, ((Float)from.getKey()).floatValue());
          }
          
          public Comparator<? super Float2DoubleMap.Entry> comparator()
          {
            return Float2DoubleRBTreeMap.this.float2DoubleEntrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Float, Double> local_e = (Map.Entry)local_o;
            Float2DoubleRBTreeMap.Entry local_f = Float2DoubleRBTreeMap.this.findKey(((Float)local_e.getKey()).floatValue());
            return (local_f != null) && (Float2DoubleRBTreeMap.Submap.this.in(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Float, Double> local_e = (Map.Entry)local_o;
            Float2DoubleRBTreeMap.Entry local_f = Float2DoubleRBTreeMap.this.findKey(((Float)local_e.getKey()).floatValue());
            if ((local_f != null) && (Float2DoubleRBTreeMap.Submap.this.in(local_f.key))) {
              Float2DoubleRBTreeMap.Submap.this.remove(local_f.key);
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
            return !new Float2DoubleRBTreeMap.Submap.SubmapIterator(Float2DoubleRBTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Float2DoubleRBTreeMap.Submap.this.clear();
          }
          
          public Float2DoubleMap.Entry first()
          {
            return Float2DoubleRBTreeMap.Submap.this.firstEntry();
          }
          
          public Float2DoubleMap.Entry last()
          {
            return Float2DoubleRBTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Float2DoubleMap.Entry> subSet(Float2DoubleMap.Entry from, Float2DoubleMap.Entry local_to)
          {
            return Float2DoubleRBTreeMap.Submap.this.subMap((Float)from.getKey(), (Float)local_to.getKey()).float2DoubleEntrySet();
          }
          
          public ObjectSortedSet<Float2DoubleMap.Entry> headSet(Float2DoubleMap.Entry local_to)
          {
            return Float2DoubleRBTreeMap.Submap.this.headMap((Float)local_to.getKey()).float2DoubleEntrySet();
          }
          
          public ObjectSortedSet<Float2DoubleMap.Entry> tailSet(Float2DoubleMap.Entry from)
          {
            return Float2DoubleRBTreeMap.Submap.this.tailMap((Float)from.getKey()).float2DoubleEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public FloatSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public DoubleCollection values()
    {
      if (this.values == null) {
        this.values = new AbstractDoubleCollection()
        {
          public DoubleIterator iterator()
          {
            return new Float2DoubleRBTreeMap.Submap.SubmapValueIterator(Float2DoubleRBTreeMap.Submap.this, null);
          }
          
          public boolean contains(double local_k)
          {
            return Float2DoubleRBTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Float2DoubleRBTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Float2DoubleRBTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(float local_k)
    {
      return (in(local_k)) && (Float2DoubleRBTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(double local_v)
    {
      SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        double local_ev = local_i.nextEntry().value;
        if (local_ev == local_v) {
          return true;
        }
      }
      return false;
    }
    
    public double get(float local_k)
    {
      float local_kk = local_k;
      Float2DoubleRBTreeMap.Entry local_e;
      return (in(local_kk)) && ((local_e = Float2DoubleRBTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public double put(float local_k, double local_v)
    {
      Float2DoubleRBTreeMap.this.modified = false;
      if (!in(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      double oldValue = Float2DoubleRBTreeMap.this.put(local_k, local_v);
      return Float2DoubleRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public Double put(Float local_ok, Double local_ov)
    {
      double oldValue = put(local_ok.floatValue(), local_ov.doubleValue());
      return Float2DoubleRBTreeMap.this.modified ? null : Double.valueOf(oldValue);
    }
    
    public double remove(float local_k)
    {
      Float2DoubleRBTreeMap.this.modified = false;
      if (!in(local_k)) {
        return this.defRetValue;
      }
      double oldValue = Float2DoubleRBTreeMap.this.remove(local_k);
      return Float2DoubleRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public Double remove(Object local_ok)
    {
      double oldValue = remove(((Float)local_ok).floatValue());
      return Float2DoubleRBTreeMap.this.modified ? Double.valueOf(oldValue) : null;
    }
    
    public int size()
    {
      SubmapIterator local_i = new SubmapIterator();
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
    
    public FloatComparator comparator()
    {
      return Float2DoubleRBTreeMap.this.actualComparator;
    }
    
    public Float2DoubleSortedMap headMap(float local_to)
    {
      if (this.top) {
        return new Submap(Float2DoubleRBTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Float2DoubleRBTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Float2DoubleRBTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Float2DoubleSortedMap tailMap(float from)
    {
      if (this.bottom) {
        return new Submap(Float2DoubleRBTreeMap.this, from, false, this.field_47, this.top);
      }
      return Float2DoubleRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Float2DoubleRBTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Float2DoubleSortedMap subMap(float from, float local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Float2DoubleRBTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Float2DoubleRBTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Float2DoubleRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Float2DoubleRBTreeMap.this, from, false, local_to, false);
    }
    
    public Float2DoubleRBTreeMap.Entry firstEntry()
    {
      if (Float2DoubleRBTreeMap.this.tree == null) {
        return null;
      }
      Float2DoubleRBTreeMap.Entry local_e;
      Float2DoubleRBTreeMap.Entry local_e;
      if (this.bottom)
      {
        local_e = Float2DoubleRBTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Float2DoubleRBTreeMap.this.locateKey(this.from);
        if (Float2DoubleRBTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Float2DoubleRBTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Float2DoubleRBTreeMap.Entry lastEntry()
    {
      if (Float2DoubleRBTreeMap.this.tree == null) {
        return null;
      }
      Float2DoubleRBTreeMap.Entry local_e;
      Float2DoubleRBTreeMap.Entry local_e;
      if (this.top)
      {
        local_e = Float2DoubleRBTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Float2DoubleRBTreeMap.this.locateKey(this.field_47);
        if (Float2DoubleRBTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Float2DoubleRBTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public float firstFloatKey()
    {
      Float2DoubleRBTreeMap.Entry local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public float lastFloatKey()
    {
      Float2DoubleRBTreeMap.Entry local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public Float firstKey()
    {
      Float2DoubleRBTreeMap.Entry local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    public Float lastKey()
    {
      Float2DoubleRBTreeMap.Entry local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    private final class SubmapValueIterator
      extends Float2DoubleRBTreeMap.Submap.SubmapIterator
      implements DoubleListIterator
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public double nextDouble()
      {
        return nextEntry().value;
      }
      
      public double previousDouble()
      {
        return previousEntry().value;
      }
      
      public void set(double local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(double local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public Double next()
      {
        return Double.valueOf(nextEntry().value);
      }
      
      public Double previous()
      {
        return Double.valueOf(previousEntry().value);
      }
      
      public void set(Double local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Double local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private final class SubmapKeyIterator
      extends Float2DoubleRBTreeMap.Submap.SubmapIterator
      implements FloatListIterator
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator(float from)
      {
        super(from);
      }
      
      public float nextFloat()
      {
        return nextEntry().key;
      }
      
      public float previousFloat()
      {
        return previousEntry().key;
      }
      
      public void set(float local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(float local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public Float next()
      {
        return Float.valueOf(nextEntry().key);
      }
      
      public Float previous()
      {
        return Float.valueOf(previousEntry().key);
      }
      
      public void set(Float local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Float local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Float2DoubleRBTreeMap.Submap.SubmapIterator
      implements ObjectListIterator<Float2DoubleMap.Entry>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator(float local_k)
      {
        super(local_k);
      }
      
      public Float2DoubleMap.Entry next()
      {
        return nextEntry();
      }
      
      public Float2DoubleMap.Entry previous()
      {
        return previousEntry();
      }
      
      public void set(Float2DoubleMap.Entry local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Float2DoubleMap.Entry local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Float2DoubleRBTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Float2DoubleRBTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator(float local_k)
      {
        this();
        if (this.next != null) {
          if ((!Float2DoubleRBTreeMap.Submap.this.bottom) && (Float2DoubleRBTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Float2DoubleRBTreeMap.Submap.this.top) && (Float2DoubleRBTreeMap.this.compare(local_k, (this.prev = Float2DoubleRBTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Float2DoubleRBTreeMap.this.locateKey(local_k);
            if (Float2DoubleRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
        if ((!Float2DoubleRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Float2DoubleRBTreeMap.this.compare(this.prev.key, Float2DoubleRBTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Float2DoubleRBTreeMap.Submap.this.top) && (this.next != null) && (Float2DoubleRBTreeMap.this.compare(this.next.key, Float2DoubleRBTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractFloat2DoubleSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public FloatBidirectionalIterator iterator()
      {
        return new Float2DoubleRBTreeMap.Submap.SubmapKeyIterator(Float2DoubleRBTreeMap.Submap.this);
      }
      
      public FloatBidirectionalIterator iterator(float from)
      {
        return new Float2DoubleRBTreeMap.Submap.SubmapKeyIterator(Float2DoubleRBTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Float2DoubleRBTreeMap.TreeIterator
    implements DoubleListIterator
  {
    private ValueIterator()
    {
      super();
    }
    
    public double nextDouble()
    {
      return nextEntry().value;
    }
    
    public double previousDouble()
    {
      return previousEntry().value;
    }
    
    public void set(double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(double local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public Double next()
    {
      return Double.valueOf(nextEntry().value);
    }
    
    public Double previous()
    {
      return Double.valueOf(previousEntry().value);
    }
    
    public void set(Double local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Double local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class KeySet
    extends AbstractFloat2DoubleSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public FloatBidirectionalIterator iterator()
    {
      return new Float2DoubleRBTreeMap.KeyIterator(Float2DoubleRBTreeMap.this);
    }
    
    public FloatBidirectionalIterator iterator(float from)
    {
      return new Float2DoubleRBTreeMap.KeyIterator(Float2DoubleRBTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Float2DoubleRBTreeMap.TreeIterator
    implements FloatListIterator
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator(float local_k)
    {
      super(local_k);
    }
    
    public float nextFloat()
    {
      return nextEntry().key;
    }
    
    public float previousFloat()
    {
      return previousEntry().key;
    }
    
    public void set(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(float local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Float next()
    {
      return Float.valueOf(nextEntry().key);
    }
    
    public Float previous()
    {
      return Float.valueOf(previousEntry().key);
    }
    
    public void set(Float local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Float local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Float2DoubleRBTreeMap.TreeIterator
    implements ObjectListIterator<Float2DoubleMap.Entry>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator(float local_k)
    {
      super(local_k);
    }
    
    public Float2DoubleMap.Entry next()
    {
      return nextEntry();
    }
    
    public Float2DoubleMap.Entry previous()
    {
      return previousEntry();
    }
    
    public void set(Float2DoubleMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Float2DoubleMap.Entry local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Float2DoubleRBTreeMap.Entry prev;
    Float2DoubleRBTreeMap.Entry next;
    Float2DoubleRBTreeMap.Entry curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Float2DoubleRBTreeMap.this.firstEntry;
    }
    
    TreeIterator(float local_k)
    {
      if ((this.next = Float2DoubleRBTreeMap.this.locateKey(local_k)) != null) {
        if (Float2DoubleRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
    
    Float2DoubleRBTreeMap.Entry nextEntry()
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
    
    Float2DoubleRBTreeMap.Entry previousEntry()
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
      Float2DoubleRBTreeMap.this.remove(this.curr.key);
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
  
  private static final class Entry
    implements Cloneable, Float2DoubleMap.Entry
  {
    private static final int BLACK_MASK = 1;
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    float key;
    double value;
    Entry left;
    Entry right;
    int info;
    
    Entry() {}
    
    Entry(float local_k, double local_v)
    {
      this.key = local_k;
      this.value = local_v;
      this.info = -1073741824;
    }
    
    Entry left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry right()
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
    
    void pred(Entry pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry right)
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
    
    Entry next()
    {
      Entry next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry prev()
    {
      Entry prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public Float getKey()
    {
      return Float.valueOf(this.key);
    }
    
    public float getFloatKey()
    {
      return this.key;
    }
    
    public Double getValue()
    {
      return Double.valueOf(this.value);
    }
    
    public double getDoubleValue()
    {
      return this.value;
    }
    
    public double setValue(double value)
    {
      double oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Double setValue(Double value)
    {
      return Double.valueOf(setValue(value.doubleValue()));
    }
    
    public Entry clone()
    {
      Entry local_c;
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
      Map.Entry<Float, Double> local_e = (Map.Entry)local_o;
      return (this.key == ((Float)local_e.getKey()).floatValue()) && (this.value == ((Double)local_e.getValue()).doubleValue());
    }
    
    public int hashCode()
    {
      return HashCommon.float2int(this.key) ^ HashCommon.double2int(this.value);
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */