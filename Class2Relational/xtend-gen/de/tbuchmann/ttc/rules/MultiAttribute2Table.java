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
import relational_.Table;

@SuppressWarnings("all")
public abstract class MultiAttribute2Table extends Elem2Elem {
  @Data
  protected static class Type4tblName {
    private final String tblName;

    public Type4tblName(final String tblName) {
      super();
      this.tblName = tblName;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.tblName== null) ? 0 : this.tblName.hashCode());
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
      MultiAttribute2Table.Type4tblName other = (MultiAttribute2Table.Type4tblName) obj;
      if (this.tblName == null) {
        if (other.tblName != null)
          return false;
      } else if (!this.tblName.equals(other.tblName))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("tblName", this.tblName);
      return b.toString();
    }

    @Pure
    public String getTblName() {
      return this.tblName;
    }
  }

  @Data
  protected static class Type4col {
    private final List<Column> col;

    public Type4col(final List<Column> col) {
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
      MultiAttribute2Table.Type4col other = (MultiAttribute2Table.Type4col) obj;
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
    public List<Column> getCol() {
      return this.col;
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
      MultiAttribute2Table.Source other = (MultiAttribute2Table.Source) obj;
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
    private final Table tbl;

    public Target(final Table tbl) {
      super();
      this.tbl = tbl;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.tbl== null) ? 0 : this.tbl.hashCode());
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
      MultiAttribute2Table.Target other = (MultiAttribute2Table.Target) obj;
      if (this.tbl == null) {
        if (other.tbl != null)
          return false;
      } else if (!this.tbl.equals(other.tbl))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("tbl", this.tbl);
      return b.toString();
    }

    @Pure
    public Table getTbl() {
      return this.tbl;
    }
  }

  public MultiAttribute2Table(final Class2Relational trafo) {
    super("MultiAttribute2Table", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<MultiAttribute2Table.Source> _matches = new ArrayList<MultiAttribute2Table.Source>();
    final Function1<Attribute, Boolean> _function = (Attribute it) -> {
      return Boolean.valueOf(this.filterAtt(it));
    };
    Iterable<Attribute> _iterable = IteratorExtensions.<Attribute>toIterable(IteratorExtensions.<Attribute>filter(Iterators.<Attribute>filter(this.sourceModel.getAllContents(), Attribute.class), _function));
    for (final Attribute att : _iterable) {
      MultiAttribute2Table.Source _source = new MultiAttribute2Table.Source(att);
      _matches.add(_source);
    }
    for (final MultiAttribute2Table.Source _match : _matches) {
      {
        final Attribute att_1 = _match.att;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(att_1));
        final Elem2Elem.CorrElemType _tblType = new Elem2Elem.CorrElemType("Table", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _tblType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Table tbl = ((Table) _unwrap);
        final MultiAttribute2Table.Type4tblName _tblName = this.tblNameFrom(att_1.getName(), att_1.getOwner());
        tbl.setName(_tblName.tblName);
        final MultiAttribute2Table.Type4col _col = this.colFrom(att_1.getName(), att_1.getType(), att_1.getOwner());
        tbl.getCol().clear();
        tbl.getCol().addAll(_col.col);
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
    final ArrayList<MultiAttribute2Table.Target> _matches = new ArrayList<MultiAttribute2Table.Target>();
    Iterable<Table> _iterable = IteratorExtensions.<Table>toIterable(Iterators.<Table>filter(this.targetModel.getAllContents(), Table.class));
    for (final Table tbl : _iterable) {
      MultiAttribute2Table.Target _target = new MultiAttribute2Table.Target(tbl);
      _matches.add(_target);
    }
    for (final MultiAttribute2Table.Target _match : _matches) {
      {
        final Table tbl_1 = _match.tbl;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(tbl_1));
        final Elem2Elem.CorrElemType _attType = new Elem2Elem.CorrElemType("Attribute", false);
        this.getOrCreateSrc(_corr, _attType);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterAtt(final Attribute att);

  protected abstract MultiAttribute2Table.Type4tblName tblNameFrom(final String attName, final class_.Class owner);

  protected abstract MultiAttribute2Table.Type4col colFrom(final String attName, final Classifier type, final class_.Class owner);

  protected static MultiAttribute2Table.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "MultiAttribute2Table");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Attribute att = ((Attribute) _unwrap);
    return new MultiAttribute2Table.Source(att);
  }

  protected static MultiAttribute2Table.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "MultiAttribute2Table");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Table tbl = ((Table) _unwrap);
    return new MultiAttribute2Table.Target(tbl);
  }
}
