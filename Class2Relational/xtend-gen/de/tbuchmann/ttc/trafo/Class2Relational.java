package de.tbuchmann.ttc.trafo;

import de.tbuchmann.ttc.rules.Class2TableImpl;
import de.tbuchmann.ttc.rules.DataType2Type;
import de.tbuchmann.ttc.rules.Elem2Elem;
import de.tbuchmann.ttc.rules.MultiAttribute2TableImpl;
import de.tbuchmann.ttc.rules.MultiClassAttribute2ColumnImpl;
import de.tbuchmann.ttc.rules.SingleAttribute2ColumnImpl;
import de.tbuchmann.ttc.rules.SingleClassAttribute2ColumnImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class Class2Relational extends AbstractClass2Relational {
  private static final Set<Object> options = Collections.<Object>unmodifiableSet(CollectionLiterals.<Object>newHashSet());

  private final HashMap<String, Object> configuration = new HashMap<String, Object>();

  public Class2Relational() {
    super();
  }

  public Class2Relational(final Resource source, final Resource target, final Resource correspondence) {
    super(source, target, correspondence);
  }

  @Override
  public Object getOption(final String option) {
    boolean _contains = Class2Relational.options.contains(option);
    boolean _not = (!_contains);
    if (_not) {
      throw new IllegalArgumentException((((("Invalid option \'" + option) + "\'! Valid options are ") + Class2Relational.options) + "."));
    }
    return this.configuration.get(option);
  }

  @Override
  public void setOption(final String option, final Object value) {
    boolean _contains = Class2Relational.options.contains(option);
    boolean _not = (!_contains);
    if (_not) {
      throw new IllegalArgumentException((((("Invalid option \'" + option) + "\'! Valid options are ") + Class2Relational.options) + "."));
    }
    this.configuration.put(option, value);
  }

  @Override
  protected List<Elem2Elem> createRules() {
    ArrayList<Elem2Elem> result = new ArrayList<Elem2Elem>();
    DataType2Type _dataType2Type = new DataType2Type(this);
    result.add(_dataType2Type);
    SingleAttribute2ColumnImpl _singleAttribute2ColumnImpl = new SingleAttribute2ColumnImpl(this);
    result.add(_singleAttribute2ColumnImpl);
    MultiAttribute2TableImpl _multiAttribute2TableImpl = new MultiAttribute2TableImpl(this);
    result.add(_multiAttribute2TableImpl);
    SingleClassAttribute2ColumnImpl _singleClassAttribute2ColumnImpl = new SingleClassAttribute2ColumnImpl(this);
    result.add(_singleClassAttribute2ColumnImpl);
    MultiClassAttribute2ColumnImpl _multiClassAttribute2ColumnImpl = new MultiClassAttribute2ColumnImpl(this);
    result.add(_multiClassAttribute2ColumnImpl);
    Class2TableImpl _class2TableImpl = new Class2TableImpl(this);
    result.add(_class2TableImpl);
    return result;
  }
}
