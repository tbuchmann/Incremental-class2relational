package de.tbuchmann.ttc.rules;

import class_.Attribute;
import class_.Classifier;
import class_.DataType;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.tbuchmann.ttc.trafo.Class2Relational;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import relational_.Type;

@SuppressWarnings("all")
public class SingleClassAttribute2ColumnImpl extends SingleClassAttribute2Column {
  public SingleClassAttribute2ColumnImpl(final Class2Relational trafo) {
    super(trafo);
  }

  @Override
  protected boolean filterAtt(final Attribute att) {
    return ((!att.isMultiValued()) && (!(att.getType() instanceof DataType)));
  }

  @Override
  protected SingleClassAttribute2Column.Type4colName colNameFrom(final String attName, final Classifier attType) {
    return new SingleClassAttribute2Column.Type4colName((attName + "Id"));
  }

  @Override
  protected SingleClassAttribute2Column.Type4colType colTypeFrom(final String attName, final Classifier attType) {
    Type _type = Utils.getType(this.findIntegerDatatype());
    return new SingleClassAttribute2Column.Type4colType(_type);
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
