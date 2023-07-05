package de.tbuchmann.ttc.rules;

import class_.Attribute;
import class_.Classifier;
import class_.DataType;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.tbuchmann.ttc.trafo.Class2Relational;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import relational_.Column;

@SuppressWarnings("all")
public class MultiClassAttribute2ColumnImpl extends MultiClassAttribute2Column {
  public MultiClassAttribute2ColumnImpl(final Class2Relational trafo) {
    super(trafo);
  }

  @Override
  protected boolean filterAtt(final Attribute att) {
    return (att.isMultiValued() && (att.getType() instanceof class_.Class));
  }

  @Override
  protected MultiClassAttribute2Column.Type4tName tNameFrom(final String attName, final class_.Class owner, final Classifier attType) {
    MultiClassAttribute2Column.Type4tName _xblockexpression = null;
    {
      String name = "";
      boolean _equals = Objects.equal(owner, null);
      if (_equals) {
        name = "Table";
      } else {
        name = owner.getName();
      }
      _xblockexpression = new MultiClassAttribute2Column.Type4tName(((name + "_") + attName));
    }
    return _xblockexpression;
  }

  @Override
  protected MultiClassAttribute2Column.Type4idName idNameFrom(final String attName, final class_.Class attOwner) {
    String _firstLower = Strings.toFirstLower(attOwner.getName());
    String _plus = (_firstLower + "Id");
    return new MultiClassAttribute2Column.Type4idName(_plus);
  }

  @Override
  protected MultiClassAttribute2Column.Type4fkName fkNameFrom(final String attName, final class_.Class attOwner) {
    return new MultiClassAttribute2Column.Type4fkName((attName + "Id"));
  }

  @Override
  protected void onIdCreation(final Column id) {
    id.setType(Utils.getType(this.findIntegerDatatype()));
    EList<Column> _col = MultiClassAttribute2Column.target(this.getCorr(id)).getT().getCol();
    _col.add(id);
  }

  @Override
  protected void onFkCreation(final Column fk) {
    fk.setType(Utils.getType(this.findIntegerDatatype()));
    EList<Column> _col = MultiClassAttribute2Column.target(this.getCorr(fk)).getT().getCol();
    _col.add(fk);
  }

  public DataType findIntegerDatatype() {
    DataType _xblockexpression = null;
    {
      final Function1<DataType, Boolean> _function = (DataType it) -> {
        String _name = it.getName();
        return Boolean.valueOf(Objects.equal(_name, "Integer"));
      };
      final DataType datatype = IterableExtensions.<DataType>findFirst(Iterables.<DataType>filter(this.sourceModel.getContents(), DataType.class), _function);
      _xblockexpression = datatype;
    }
    return _xblockexpression;
  }
}
