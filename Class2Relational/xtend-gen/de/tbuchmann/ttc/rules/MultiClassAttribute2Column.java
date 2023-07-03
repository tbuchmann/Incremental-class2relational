package de.tbuchmann.ttc.rules;

import class_.Attribute;
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
public abstract class MultiClassAttribute2Column extends Elem2Elem {
  @Data
  protected static class Type4tName {
    private final String tName;

    public Type4tName(final String tName) {
      super();
      this.tName = tName;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.tName== null) ? 0 : this.tName.hashCode());
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
      MultiClassAttribute2Column.Type4tName other = (MultiClassAttribute2Column.Type4tName) obj;
      if (this.tName == null) {
        if (other.tName != null)
          return false;
      } else if (!this.tName.equals(other.tName))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("tName", this.tName);
      return b.toString();
    }

    @Pure
    public String getTName() {
      return this.tName;
    }
  }

  @Data
  protected static class Type4idName {
    private final String idName;

    public Type4idName(final String idName) {
      super();
      this.idName = idName;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.idName== null) ? 0 : this.idName.hashCode());
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
      MultiClassAttribute2Column.Type4idName other = (MultiClassAttribute2Column.Type4idName) obj;
      if (this.idName == null) {
        if (other.idName != null)
          return false;
      } else if (!this.idName.equals(other.idName))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("idName", this.idName);
      return b.toString();
    }

    @Pure
    public String getIdName() {
      return this.idName;
    }
  }

  @Data
  protected static class Type4fkName {
    private final String fkName;

    public Type4fkName(final String fkName) {
      super();
      this.fkName = fkName;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.fkName== null) ? 0 : this.fkName.hashCode());
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
      MultiClassAttribute2Column.Type4fkName other = (MultiClassAttribute2Column.Type4fkName) obj;
      if (this.fkName == null) {
        if (other.fkName != null)
          return false;
      } else if (!this.fkName.equals(other.fkName))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("fkName", this.fkName);
      return b.toString();
    }

    @Pure
    public String getFkName() {
      return this.fkName;
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
      MultiClassAttribute2Column.Source other = (MultiClassAttribute2Column.Source) obj;
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
    private final Table t;

    private final Column id;

    private final Column fk;

    public Target(final Table t, final Column id, final Column fk) {
      super();
      this.t = t;
      this.id = id;
      this.fk = fk;
    }

    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.t== null) ? 0 : this.t.hashCode());
      result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
      return prime * result + ((this.fk== null) ? 0 : this.fk.hashCode());
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
      MultiClassAttribute2Column.Target other = (MultiClassAttribute2Column.Target) obj;
      if (this.t == null) {
        if (other.t != null)
          return false;
      } else if (!this.t.equals(other.t))
        return false;
      if (this.id == null) {
        if (other.id != null)
          return false;
      } else if (!this.id.equals(other.id))
        return false;
      if (this.fk == null) {
        if (other.fk != null)
          return false;
      } else if (!this.fk.equals(other.fk))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("t", this.t);
      b.add("id", this.id);
      b.add("fk", this.fk);
      return b.toString();
    }

    @Pure
    public Table getT() {
      return this.t;
    }

    @Pure
    public Column getId() {
      return this.id;
    }

    @Pure
    public Column getFk() {
      return this.fk;
    }
  }

  public MultiClassAttribute2Column(final Class2Relational trafo) {
    super("MultiClassAttribute2Column", trafo);
  }

  @Override
  public Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<MultiClassAttribute2Column.Source> _matches = new ArrayList<MultiClassAttribute2Column.Source>();
    final Function1<Attribute, Boolean> _function = (Attribute it) -> {
      return Boolean.valueOf(this.filterAtt(it));
    };
    Iterable<Attribute> _iterable = IteratorExtensions.<Attribute>toIterable(IteratorExtensions.<Attribute>filter(Iterators.<Attribute>filter(this.sourceModel.getAllContents(), Attribute.class), _function));
    for (final Attribute att : _iterable) {
      MultiClassAttribute2Column.Source _source = new MultiClassAttribute2Column.Source(att);
      _matches.add(_source);
    }
    for (final MultiClassAttribute2Column.Source _match : _matches) {
      {
        final Attribute att_1 = _match.att;
        final Corr _corr = this.updateOrCreateCorrSrc(this.wrap(att_1));
        final Elem2Elem.CorrElemType _tType = new Elem2Elem.CorrElemType("Table", false);
        final Elem2Elem.CorrElemType _idType = new Elem2Elem.CorrElemType("Column", false);
        final Elem2Elem.CorrElemType _fkType = new Elem2Elem.CorrElemType("Column", false);
        final List<CorrElem> _trg = this.getOrCreateTrg(_corr, _tType, _idType, _fkType);
        CorrElem _get = _trg.get(0);
        Object _unwrap = Elem2Elem.unwrap(((SingleElem) _get));
        final Table t = ((Table) _unwrap);
        CorrElem _get_1 = _trg.get(1);
        Object _unwrap_1 = Elem2Elem.unwrap(((SingleElem) _get_1));
        final Column id = ((Column) _unwrap_1);
        CorrElem _get_2 = _trg.get(2);
        Object _unwrap_2 = Elem2Elem.unwrap(((SingleElem) _get_2));
        final Column fk = ((Column) _unwrap_2);
        final MultiClassAttribute2Column.Type4tName _tName = this.tNameFrom(att_1.getName(), att_1.getOwner());
        t.setName(_tName.tName);
        final MultiClassAttribute2Column.Type4idName _idName = this.idNameFrom(att_1.getName(), att_1.getOwner());
        id.setName(_idName.idName);
        final MultiClassAttribute2Column.Type4fkName _fkName = this.fkNameFrom(att_1.getName(), att_1.getOwner());
        fk.setName(_fkName.fkName);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  @Override
  public void onTrgElemCreation(final EObject trgElem) {
    int _corrElemPosition = this.getCorrElemPosition(trgElem);
    switch (_corrElemPosition) {
      case 1:
        this.onIdCreation(((Column) trgElem));
        break;
      case 2:
        this.onFkCreation(((Column) trgElem));
        break;
    }
  }

  protected abstract void onIdCreation(final Column id);

  protected abstract void onFkCreation(final Column fk);

  @Override
  public Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems) {
    ArrayList<EObject> _arrayList = new ArrayList<EObject>();
    this.createdElems = _arrayList;
    ArrayList<EObject> _arrayList_1 = new ArrayList<EObject>();
    this.spareElems = _arrayList_1;
    this.detachedCorrElems = _detachedCorrElems;
    final ArrayList<MultiClassAttribute2Column.Target> _matches = new ArrayList<MultiClassAttribute2Column.Target>();
    Iterable<Table> _iterable = IteratorExtensions.<Table>toIterable(Iterators.<Table>filter(this.targetModel.getAllContents(), Table.class));
    for (final Table t : _iterable) {
      Iterable<Column> _iterable_1 = IteratorExtensions.<Column>toIterable(Iterators.<Column>filter(this.targetModel.getAllContents(), Column.class));
      for (final Column id : _iterable_1) {
        Iterable<Column> _iterable_2 = IteratorExtensions.<Column>toIterable(Iterators.<Column>filter(this.targetModel.getAllContents(), Column.class));
        for (final Column fk : _iterable_2) {
          MultiClassAttribute2Column.Target _target = new MultiClassAttribute2Column.Target(t, id, fk);
          _matches.add(_target);
        }
      }
    }
    for (final MultiClassAttribute2Column.Target _match : _matches) {
      {
        final Table t_1 = _match.t;
        final Column id_1 = _match.id;
        final Column fk_1 = _match.fk;
        final Corr _corr = this.updateOrCreateCorrTrg(this.wrap(t_1), this.wrap(id_1), this.wrap(fk_1));
        final Elem2Elem.CorrElemType _attType = new Elem2Elem.CorrElemType("Attribute", false);
        this.getOrCreateSrc(_corr, _attType);
      }
    }
    return new Elem2Elem.CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems);
  }

  protected abstract boolean filterAtt(final Attribute att);

  protected abstract MultiClassAttribute2Column.Type4tName tNameFrom(final String attName, final class_.Class attOwner);

  protected abstract MultiClassAttribute2Column.Type4idName idNameFrom(final String attName, final class_.Class attOwner);

  protected abstract MultiClassAttribute2Column.Type4fkName fkNameFrom(final String attName, final class_.Class attOwner);

  protected static MultiClassAttribute2Column.Source source(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "MultiClassAttribute2Column");
    Object _unwrap = Elem2Elem.unwrap(_corr.getSource().get(0));
    final Attribute att = ((Attribute) _unwrap);
    return new MultiClassAttribute2Column.Source(att);
  }

  protected static MultiClassAttribute2Column.Target target(final Corr _corr) {
    Elem2Elem.assertRuleId(_corr, "MultiClassAttribute2Column");
    Object _unwrap = Elem2Elem.unwrap(_corr.getTarget().get(0));
    final Table t = ((Table) _unwrap);
    Object _unwrap_1 = Elem2Elem.unwrap(_corr.getTarget().get(1));
    final Column id = ((Column) _unwrap_1);
    Object _unwrap_2 = Elem2Elem.unwrap(_corr.getTarget().get(2));
    final Column fk = ((Column) _unwrap_2);
    return new MultiClassAttribute2Column.Target(t, id, fk);
  }
}
