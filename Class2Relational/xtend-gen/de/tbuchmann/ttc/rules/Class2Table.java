package de.tbuchmann.ttc.rules;

import class_.Attribute;
import com.google.common.base.Objects;
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
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import relational_.Column;
import relational_.Table;

@SuppressWarnings("all")
public abstract class Class2Table extends Elem2Elem {
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
      Class2Table.Type4col other = (Class2Table.Type4col) obj;
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
    private final class_.Class clz;

    public Source(final class_.Class clz) {
      super();
      this.clz = clz;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.clz== null) ? 0 : this.clz.hashCode());
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
      Class2Table.Source other = (Class2Table.Source) obj;
      if (this.clz == null) {
        if (other.clz != null)
          return false;
      } else if (!this.clz.equals(other.clz))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("clz", this.clz);
      return b.toString();
    }

    @Pure
    public class_.Class getClz() {
      return this.clz;
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
      Class2Table.Target other = (Class2Table.Target) obj;
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

  public Class2Table(final Class2Relational trafo) {
    super("Class2Table", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Class2Table.Source> _matches = new ArrayList<Class2Table.Source>();
    Iterable<class_.Class> _iterable = IteratorExtensions.<class_.Class>toIterable(Iterators.<class_.Class>filter(this.sourceModel.getAllContents(), class_.Class.class));
    for (final class_.Class clz : _iterable) {
      Class2Table.Source _source = new Class2Table.Source(clz);
      _matches.add(_source);
    }
    for (final Class2Table.Source _match : _matches) {
      {
        final class_.Class clz_1 = _match.clz;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(clz_1));
        final Elem2Elem.CorrElemType _tblType = new Elem2Elem.CorrElemType("Table", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _tblType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Table tbl = ((Table) _unwrap);
        tbl.setName(clz_1.getName());
        final Function1<Attribute, Boolean> _function = (Attribute it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "SingleAttribute2Column"));
        };
        final Function1<Attribute, Column> _function_1 = (Attribute it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Column) _unwrap_1);
        };
        final List<Column> _attSinCol = IterableExtensions.<Column>toList(IterableExtensions.<Attribute, Column>map(IterableExtensions.<Attribute>filter(clz_1.getAttr(), _function), _function_1));
        final Function1<Attribute, Boolean> _function_2 = (Attribute it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "SingleClassAttribute2Column"));
        };
        final Function1<Attribute, Column> _function_3 = (Attribute it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Column) _unwrap_1);
        };
        final List<Column> _attSinCol_2 = IterableExtensions.<Column>toList(IterableExtensions.<Attribute, Column>map(IterableExtensions.<Attribute>filter(clz_1.getAttr(), _function_2), _function_3));
        final Function1<Attribute, Boolean> _function_4 = (Attribute it) -> {
          String _ruleId = this.getCorr(it).getRuleId();
          return Boolean.valueOf(Objects.equal(_ruleId, "MultiAttribute2Table"));
        };
        final Function1<Attribute, Table> _function_5 = (Attribute it) -> {
          CorrElem _get_1 = this.getCorr(it).getTarget().get(0);
          Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
          return ((Table) _unwrap_1);
        };
        final List<Table> _attMulTbl = IterableExtensions.<Table>toList(IterableExtensions.<Attribute, Table>map(IterableExtensions.<Attribute>filter(clz_1.getAttr(), _function_4), _function_5));
        final Class2Table.Type4col _col = this.colFrom(_attSinCol, _attSinCol_2, _attMulTbl, tbl);
        tbl.getCol().clear();
        tbl.getCol().addAll(_col.col);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  @Override
  public void onTrgElemCreation(final EObject trgElem) {
    int _corrElemPosition = this.getCorrElemPosition(trgElem);
    switch (_corrElemPosition) {
      case 0:
        this.onTblCreation(((Table) trgElem));
        break;
    }
  }

  protected abstract void onTblCreation(final Table tbl);

  @Override
  public Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<Class2Table.Target> _matches = new ArrayList<Class2Table.Target>();
    Iterable<Table> _iterable = IteratorExtensions.<Table>toIterable(Iterators.<Table>filter(this.targetModel.getAllContents(), Table.class));
    for (final Table tbl : _iterable) {
      Class2Table.Target _target = new Class2Table.Target(tbl);
      _matches.add(_target);
    }
    for (final Class2Table.Target _match : _matches) {
      {
        final Table tbl_1 = _match.tbl;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(tbl_1));
        final Elem2Elem.CorrElemType _clzType = new Elem2Elem.CorrElemType("Class", false);
        this.getOrCreateSrc(_corr, _clzType);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract Class2Table.Type4col colFrom(final List<Column> attSinCol, final List<Column> attSinCol_2, final List<Table> attMulTbl, final Table parent);

  protected static Class2Table.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Class2Table");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final class_.Class clz = ((class_.Class) _unwrap);
    return new Class2Table.Source(clz);
  }

  protected static Class2Table.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "Class2Table");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Table tbl = ((Table) _unwrap);
    return new Class2Table.Target(tbl);
  }
}
