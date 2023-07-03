package de.tbuchmann.ttc.rules;

import class_.DataType;
import com.google.common.collect.Iterators;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.trafo.Class2Relational;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import relational_.Type;

@SuppressWarnings("all")
public class DataType2Type extends Elem2Elem {
  @Data
  protected static class Source {
    private final DataType dt;

    public Source(final DataType dt) {
      super();
      this.dt = dt;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.dt== null) ? 0 : this.dt.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      DataType2Type.Source other = (DataType2Type.Source) obj;
      if (this.dt == null) {
        if (other.dt != null)
          return false;
      } else if (!this.dt.equals(other.dt))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("dt", this.dt);
      return b.toString();
    }

    @Pure
    public DataType getDt() {
      return this.dt;
    }
  }

  @Data
  protected static class Target {
    private final Type t;

    public Target(final Type t) {
      super();
      this.t = t;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.t== null) ? 0 : this.t.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      DataType2Type.Target other = (DataType2Type.Target) obj;
      if (this.t == null) {
        if (other.t != null)
          return false;
      } else if (!this.t.equals(other.t))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("t", this.t);
      return b.toString();
    }

    @Pure
    public Type getT() {
      return this.t;
    }
  }

  public DataType2Type(final Class2Relational trafo) {
    super("DataType2Type", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<DataType2Type.Source> _matches = new ArrayList<DataType2Type.Source>();
    Iterable<DataType> _iterable = IteratorExtensions.<DataType>toIterable(Iterators.<DataType>filter(this.sourceModel.getAllContents(), DataType.class));
    for (final DataType dt : _iterable) {
      DataType2Type.Source _source = new DataType2Type.Source(dt);
      _matches.add(_source);
    }
    for (final DataType2Type.Source _match : _matches) {
      {
        final DataType dt_1 = _match.dt;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(dt_1));
        final Elem2Elem.CorrElemType _tType = new Elem2Elem.CorrElemType("Type", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _tType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Type t = ((Type) _unwrap);
        t.setName(dt_1.getName());
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  @Override
  public Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<DataType2Type.Target> _matches = new ArrayList<DataType2Type.Target>();
    Iterable<Type> _iterable = IteratorExtensions.<Type>toIterable(Iterators.<Type>filter(this.targetModel.getAllContents(), Type.class));
    for (final Type t : _iterable) {
      DataType2Type.Target _target = new DataType2Type.Target(t);
      _matches.add(_target);
    }
    for (final DataType2Type.Target _match : _matches) {
      {
        final Type t_1 = _match.t;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(t_1));
        final Elem2Elem.CorrElemType _dtType = new Elem2Elem.CorrElemType("DataType", false);
        this.getOrCreateSrc(_corr, _dtType);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected static DataType2Type.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "DataType2Type");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final DataType dt = ((DataType) _unwrap);
    return new DataType2Type.Source(dt);
  }

  protected static DataType2Type.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "DataType2Type");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Type t = ((Type) _unwrap);
    return new DataType2Type.Target(t);
  }
}
