package de.tbuchmann.ttc.rules;

import class_.Attribute;
import class_.Classifier;
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
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import relational_.Column;
import relational_.Type;

@SuppressWarnings("all")
public abstract class SingleClassAttribute2Column extends Elem2Elem {
  @Data
  protected static class Type4colName {
    private final String colName;

    public Type4colName(final String colName) {
      super();
      this.colName = colName;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.colName== null) ? 0 : this.colName.hashCode());
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
      SingleClassAttribute2Column.Type4colName other = (SingleClassAttribute2Column.Type4colName) obj;
      if (this.colName == null) {
        if (other.colName != null)
          return false;
      } else if (!this.colName.equals(other.colName))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("colName", this.colName);
      return b.toString();
    }

    @Pure
    public String getColName() {
      return this.colName;
    }
  }

  @Data
  protected static class Type4colType {
    private final Type colType;

    public Type4colType(final Type colType) {
      super();
      this.colType = colType;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.colType== null) ? 0 : this.colType.hashCode());
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
      SingleClassAttribute2Column.Type4colType other = (SingleClassAttribute2Column.Type4colType) obj;
      if (this.colType == null) {
        if (other.colType != null)
          return false;
      } else if (!this.colType.equals(other.colType))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("colType", this.colType);
      return b.toString();
    }

    @Pure
    public Type getColType() {
      return this.colType;
    }
  }

  @Data
  protected static class Source {
    private final Attribute att;

    public Source(final Attribute att) {
      super();
      this.att = att;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.att== null) ? 0 : this.att.hashCode());
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
      SingleClassAttribute2Column.Source other = (SingleClassAttribute2Column.Source) obj;
      if (this.att == null) {
        if (other.att != null)
          return false;
      } else if (!this.att.equals(other.att))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("att", this.att);
      return b.toString();
    }

    @Pure
    public Attribute getAtt() {
      return this.att;
    }
  }

  @Data
  protected static class Target {
    private final Column col;

    public Target(final Column col) {
      super();
      this.col = col;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.col== null) ? 0 : this.col.hashCode());
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
      SingleClassAttribute2Column.Target other = (SingleClassAttribute2Column.Target) obj;
      if (this.col == null) {
        if (other.col != null)
          return false;
      } else if (!this.col.equals(other.col))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("col", this.col);
      return b.toString();
    }

    @Pure
    public Column getCol() {
      return this.col;
    }
  }

  public SingleClassAttribute2Column(final Class2Relational trafo) {
    super("SingleClassAttribute2Column", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<SingleClassAttribute2Column.Source> _matches = new ArrayList<SingleClassAttribute2Column.Source>();
    final Function1<Attribute, Boolean> _function = (Attribute it) -> {
      return Boolean.valueOf(this.filterAtt(it));
    };
    Iterable<Attribute> _iterable = IteratorExtensions.<Attribute>toIterable(IteratorExtensions.<Attribute>filter(Iterators.<Attribute>filter(this.sourceModel.getAllContents(), Attribute.class), _function));
    for (final Attribute att : _iterable) {
      SingleClassAttribute2Column.Source _source = new SingleClassAttribute2Column.Source(att);
      _matches.add(_source);
    }
    for (final SingleClassAttribute2Column.Source _match : _matches) {
      {
        final Attribute att_1 = _match.att;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(att_1));
        final Elem2Elem.CorrElemType _colType = new Elem2Elem.CorrElemType("Column", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _colType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Column col = ((Column) _unwrap);
        final SingleClassAttribute2Column.Type4colName _colName = this.colNameFrom(att_1.getName(), att_1.getType());
        col.setName(_colName.colName);
        final SingleClassAttribute2Column.Type4colType _colType_2 = this.colTypeFrom(att_1.getName(), att_1.getType());
        col.setType(_colType_2.colType);
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
    final ArrayList<SingleClassAttribute2Column.Target> _matches = new ArrayList<SingleClassAttribute2Column.Target>();
    Iterable<Column> _iterable = IteratorExtensions.<Column>toIterable(Iterators.<Column>filter(this.targetModel.getAllContents(), Column.class));
    for (final Column col : _iterable) {
      SingleClassAttribute2Column.Target _target = new SingleClassAttribute2Column.Target(col);
      _matches.add(_target);
    }
    for (final SingleClassAttribute2Column.Target _match : _matches) {
      {
        final Column col_1 = _match.col;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(col_1));
        final Elem2Elem.CorrElemType _attType = new Elem2Elem.CorrElemType("Attribute", false);
        this.getOrCreateSrc(_corr, _attType);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterAtt(final Attribute att);

  protected abstract SingleClassAttribute2Column.Type4colName colNameFrom(final String attName, final Classifier attType);

  protected abstract SingleClassAttribute2Column.Type4colType colTypeFrom(final String attName, final Classifier attType);

  protected static SingleClassAttribute2Column.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "SingleClassAttribute2Column");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Attribute att = ((Attribute) _unwrap);
    return new SingleClassAttribute2Column.Source(att);
  }

  protected static SingleClassAttribute2Column.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "SingleClassAttribute2Column");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Column col = ((Column) _unwrap);
    return new SingleClassAttribute2Column.Target(col);
  }
}
