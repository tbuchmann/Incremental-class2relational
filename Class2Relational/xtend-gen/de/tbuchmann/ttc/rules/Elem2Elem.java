package de.tbuchmann.ttc.rules;

import class_.Class_Factory;
import class_.Class_Package;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrElem;
import de.tbuchmann.ttc.corrmodel.CorrModelFactory;
import de.tbuchmann.ttc.corrmodel.MultiElem;
import de.tbuchmann.ttc.corrmodel.SingleElem;
import de.tbuchmann.ttc.corrmodel.Transformation;
import de.tbuchmann.ttc.trafo.Class2Relational;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtext.xbase.lib.ArrayExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import relational_.RelationalFactory;
import relational_.RelationalPackage;

@SuppressWarnings("all")
public abstract class Elem2Elem {
  @Data
  public static class CorrModelDelta {
    private final List<EObject> createdElems;

    private final List<EObject> spareElems;

    private final Set<EObject> detachedCorrElems;

    public CorrModelDelta(final List<EObject> createdElems, final List<EObject> spareElems, final Set<EObject> detachedCorrElems) {
      super();
      this.createdElems = createdElems;
      this.spareElems = spareElems;
      this.detachedCorrElems = detachedCorrElems;
    }

    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.createdElems== null) ? 0 : this.createdElems.hashCode());
      result = prime * result + ((this.spareElems== null) ? 0 : this.spareElems.hashCode());
      return prime * result + ((this.detachedCorrElems== null) ? 0 : this.detachedCorrElems.hashCode());
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
      Elem2Elem.CorrModelDelta other = (Elem2Elem.CorrModelDelta) obj;
      if (this.createdElems == null) {
        if (other.createdElems != null)
          return false;
      } else if (!this.createdElems.equals(other.createdElems))
        return false;
      if (this.spareElems == null) {
        if (other.spareElems != null)
          return false;
      } else if (!this.spareElems.equals(other.spareElems))
        return false;
      if (this.detachedCorrElems == null) {
        if (other.detachedCorrElems != null)
          return false;
      } else if (!this.detachedCorrElems.equals(other.detachedCorrElems))
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("createdElems", this.createdElems);
      b.add("spareElems", this.spareElems);
      b.add("detachedCorrElems", this.detachedCorrElems);
      return b.toString();
    }

    @Pure
    public List<EObject> getCreatedElems() {
      return this.createdElems;
    }

    @Pure
    public List<EObject> getSpareElems() {
      return this.spareElems;
    }

    @Pure
    public Set<EObject> getDetachedCorrElems() {
      return this.detachedCorrElems;
    }
  }

  @Data
  protected static class CorrElemType {
    private final String clazz;

    private final boolean multivalued;

    public CorrElemType(final String clazz, final boolean multivalued) {
      super();
      this.clazz = clazz;
      this.multivalued = multivalued;
    }

    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.clazz== null) ? 0 : this.clazz.hashCode());
      return prime * result + (this.multivalued ? 1231 : 1237);
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
      Elem2Elem.CorrElemType other = (Elem2Elem.CorrElemType) obj;
      if (this.clazz == null) {
        if (other.clazz != null)
          return false;
      } else if (!this.clazz.equals(other.clazz))
        return false;
      if (other.multivalued != this.multivalued)
        return false;
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("clazz", this.clazz);
      b.add("multivalued", this.multivalued);
      return b.toString();
    }

    @Pure
    public String getClazz() {
      return this.clazz;
    }

    @Pure
    public boolean isMultivalued() {
      return this.multivalued;
    }
  }

  protected static abstract class MultiElemUpdater<T extends EObject> {
    private List<T> outdated;

    private List<T> updated;

    private final Function0<T> elemFactory;

    private final Elem2Elem rule;

    private final Corr corr;

    private boolean finished;

    public MultiElemUpdater(final List<T> outdated, final Function0<T> elemFactory, final Elem2Elem rule, final Corr corr) {
      ArrayList<T> _newArrayList = CollectionLiterals.<T>newArrayList();
      final Procedure1<ArrayList<T>> _function = (ArrayList<T> it) -> {
        Iterables.<T>addAll(it, outdated);
      };
      ArrayList<T> _doubleArrow = ObjectExtensions.<ArrayList<T>>operator_doubleArrow(_newArrayList, _function);
      this.outdated = _doubleArrow;
      this.updated = CollectionLiterals.<T>newArrayList();
      this.elemFactory = elemFactory;
      this.rule = rule;
      this.corr = corr;
      this.finished = false;
    }

    protected T update(final Function1<? super T, Boolean> predicate) {
      if (this.finished) {
        throw new IllegalStateException("Finish was already called on this MultiElemUpdater!");
      }
      final Function1<T, Boolean> _function = (T it) -> {
        return predicate.apply(it);
      };
      final T existing = IterableExtensions.<T>findFirst(this.outdated, _function);
      boolean _remove = this.outdated.remove(existing);
      if (_remove) {
        this.updated.add(existing);
        return existing;
      } else {
        final T created = this.elemFactory.apply();
        this.rule.createdElems.add(created);
        Elem2Elem.elementsToCorr.put(created, this.corr);
        this.updated.add(created);
        return created;
      }
    }

    protected List<T> finish() {
      if (this.finished) {
        throw new IllegalStateException("Finish was already called on this MultiElemUpdater!");
      }
      this.finished = true;
      Iterables.<EObject>addAll(this.rule.spareElems, this.outdated);
      return this.updated;
    }
  }

  protected static class SrcMultiElemUpdater<T extends EObject> extends Elem2Elem.MultiElemUpdater<T> {
    public SrcMultiElemUpdater(final List<T> outdated, final String elemClass, final Elem2Elem rule, final Corr corr) {
      super(outdated, ((Function0<T>) () -> {
        EClassifier _eClassifier = Elem2Elem.sourcePackage.getEClassifier(elemClass);
        EObject _create = Elem2Elem.sourceFactory.create(((EClass) _eClassifier));
        return ((T) _create);
      }), rule, corr);
    }
  }

  protected static class TrgMultiElemUpdater<T extends EObject> extends Elem2Elem.MultiElemUpdater<T> {
    public TrgMultiElemUpdater(final List<T> outdated, final String elemClass, final Elem2Elem rule, final Corr corr) {
      super(outdated, ((Function0<T>) () -> {
        EClassifier _eClassifier = Elem2Elem.targetPackage.getEClassifier(elemClass);
        EObject _create = Elem2Elem.targetFactory.create(((EClass) _eClassifier));
        return ((T) _create);
      }), rule, corr);
    }
  }

  public final String ruleId;

  protected final Class2Relational trafo;

  protected final Resource sourceModel;

  protected final Resource targetModel;

  protected final Resource corrModel;

  protected List<EObject> createdElems;

  protected List<EObject> spareElems;

  protected Set<EObject> detachedCorrElems;

  private static final Class_Package sourcePackage = Class_Package.eINSTANCE;

  private static final RelationalPackage targetPackage = RelationalPackage.eINSTANCE;

  private static final Class_Factory sourceFactory = Class_Factory.eINSTANCE;

  private static final RelationalFactory targetFactory = RelationalFactory.eINSTANCE;

  private static final CorrModelFactory corrFactory = CorrModelFactory.eINSTANCE;

  private static Map<EObject, Corr> elementsToCorr = CollectionLiterals.<EObject, Corr>newHashMap();

  public Elem2Elem(final String ruleId, final Class2Relational trafo) {
    this.ruleId = ruleId;
    this.trafo = trafo;
    this.sourceModel = trafo.getSource();
    this.targetModel = trafo.getTarget();
    this.corrModel = trafo.getCorr();
    EObject _get = this.corrModel.getContents().get(0);
    final Consumer<Corr> _function = (Corr c) -> {
      final Consumer<EObject> _function_1 = (EObject e) -> {
        Elem2Elem.elementsToCorr.put(e, c);
      };
      c.flatSrc().forEach(_function_1);
      final Consumer<EObject> _function_2 = (EObject e) -> {
        Elem2Elem.elementsToCorr.put(e, c);
      };
      c.flatTrg().forEach(_function_2);
    };
    ((Transformation) _get).getCorrespondences().forEach(_function);
  }

  public abstract Elem2Elem.CorrModelDelta sourceToTarget(final Set<EObject> _detachedCorrElems);

  public void onTrgElemCreation(final EObject trgElem) {
  }

  public void onTrgElemDeletion(final EObject trgElem) {
  }

  public abstract Elem2Elem.CorrModelDelta targetToSource(final Set<EObject> _detachedCorrElems);

  public void onSrcElemCreation(final EObject srcElem) {
  }

  public void onSrcElemDeletion(final EObject srcElem) {
  }

  protected boolean hasCorr(final EObject obj) {
    return Elem2Elem.elementsToCorr.containsKey(obj);
  }

  protected Corr getCorr(final EObject obj) {
    final Corr corr = Elem2Elem.elementsToCorr.get(obj);
    if ((corr == null)) {
      throw new IllegalArgumentException("No correspondence was created for the given object!");
    } else {
      return corr;
    }
  }

  protected int getCorrElemPosition(final EObject element) {
    final EList<CorrElem> source = this.getCorr(element).getSource();
    for (int i = 0; (i < source.size()); i++) {
      if (((source.get(i) instanceof SingleElem) && Objects.equal(((SingleElem) source.get(i)).getElement(), element))) {
        return i;
      } else {
        if (((source.get(i) instanceof MultiElem) && ((MultiElem) source.get(i)).getElements().contains(element))) {
          return i;
        }
      }
    }
    final EList<CorrElem> target = this.getCorr(element).getTarget();
    for (int i = 0; (i < target.size()); i++) {
      if (((target.get(i) instanceof SingleElem) && Objects.equal(((SingleElem) target.get(i)).getElement(), element))) {
        return i;
      } else {
        if (((target.get(i) instanceof MultiElem) && ((MultiElem) target.get(i)).getElements().contains(element))) {
          return i;
        }
      }
    }
    throw new AssertionError("Invalid mapping in elementsToCorr map!");
  }

  protected SingleElem wrap(final EObject obj) {
    SingleElem _createSingleElem = Elem2Elem.corrFactory.createSingleElem();
    final Procedure1<SingleElem> _function = (SingleElem it) -> {
      it.setElement(obj);
    };
    final SingleElem singleElem = ObjectExtensions.<SingleElem>operator_doubleArrow(_createSingleElem, _function);
    EList<EObject> _contents = this.corrModel.getContents();
    _contents.add(singleElem);
    return singleElem;
  }

  protected static EObject _unwrap(final SingleElem elem) {
    return elem.getElement();
  }

  protected MultiElem wrap(final List<? extends EObject> objs) {
    MultiElem _createMultiElem = Elem2Elem.corrFactory.createMultiElem();
    final Procedure1<MultiElem> _function = (MultiElem it) -> {
      EList<EObject> _elements = it.getElements();
      Iterables.<EObject>addAll(_elements, objs);
    };
    final MultiElem multiElem = ObjectExtensions.<MultiElem>operator_doubleArrow(_createMultiElem, _function);
    EList<EObject> _contents = this.corrModel.getContents();
    _contents.add(multiElem);
    return multiElem;
  }

  protected static List<? extends EObject> _unwrap(final MultiElem elem) {
    return elem.getElements();
  }

  protected static void assertRuleId(final Corr corr, final String... ruleIds) {
    boolean _contains = ArrayExtensions.contains(ruleIds, corr.getRuleId());
    boolean _not = (!_contains);
    if (_not) {
      throw new AssertionError("The given corr doesn\'t have any of the asserted rule ids!");
    }
  }

  protected Corr updateOrCreateCorrSrc(final CorrElem elem, final CorrElem... additionalElems) {
    return this.updateOrCreateCorr(elem, true, additionalElems);
  }

  protected Corr updateOrCreateCorrTrg(final CorrElem elem, final CorrElem... additionalElems) {
    return this.updateOrCreateCorr(elem, false, additionalElems);
  }

  protected List<CorrElem> getOrCreateSrc(final Corr corr, final Elem2Elem.CorrElemType... srcTypes) {
    boolean _isEmpty = ((List<Elem2Elem.CorrElemType>)Conversions.doWrapArray(srcTypes)).isEmpty();
    if (_isEmpty) {
      throw new IllegalArgumentException("The source elements to create may not be empty!");
    }
    List<CorrElem> source = corr.getSource();
    boolean _isEmpty_1 = corr.flatSrc().isEmpty();
    if (_isEmpty_1) {
      for (final Elem2Elem.CorrElemType type : srcTypes) {
        {
          CorrElem _xifexpression = null;
          if ((!type.multivalued)) {
            SingleElem _xblockexpression = null;
            {
              EClassifier _eClassifier = Elem2Elem.sourcePackage.getEClassifier(type.clazz);
              final EClass eClass = ((EClass) _eClassifier);
              SingleElem _createSingleElem = Elem2Elem.corrFactory.createSingleElem();
              final Procedure1<SingleElem> _function = (SingleElem it) -> {
                it.setElement(Elem2Elem.sourceFactory.create(eClass));
              };
              _xblockexpression = ObjectExtensions.<SingleElem>operator_doubleArrow(_createSingleElem, _function);
            }
            _xifexpression = _xblockexpression;
          } else {
            _xifexpression = Elem2Elem.corrFactory.createMultiElem();
          }
          final CorrElem corrElem = _xifexpression;
          source.add(corrElem);
        }
      }
      EList<EObject> _flatSrc = corr.flatSrc();
      Iterables.<EObject>addAll(this.createdElems, _flatSrc);
      final Consumer<EObject> _function = (EObject e) -> {
        Elem2Elem.elementsToCorr.put(e, corr);
      };
      corr.flatSrc().forEach(_function);
    }
    return source;
  }

  protected List<CorrElem> getOrCreateTrg(final Corr corr, final Elem2Elem.CorrElemType... trgTypes) {
    boolean _isEmpty = ((List<Elem2Elem.CorrElemType>)Conversions.doWrapArray(trgTypes)).isEmpty();
    if (_isEmpty) {
      throw new IllegalArgumentException("The target elements to create may not be empty!");
    }
    List<CorrElem> target = corr.getTarget();
    boolean _isEmpty_1 = corr.flatTrg().isEmpty();
    if (_isEmpty_1) {
      for (final Elem2Elem.CorrElemType type : trgTypes) {
        {
          CorrElem _xifexpression = null;
          if ((!type.multivalued)) {
            SingleElem _xblockexpression = null;
            {
              EClassifier _eClassifier = Elem2Elem.targetPackage.getEClassifier(type.clazz);
              final EClass eClass = ((EClass) _eClassifier);
              SingleElem _createSingleElem = Elem2Elem.corrFactory.createSingleElem();
              final Procedure1<SingleElem> _function = (SingleElem it) -> {
                it.setElement(Elem2Elem.targetFactory.create(eClass));
              };
              _xblockexpression = ObjectExtensions.<SingleElem>operator_doubleArrow(_createSingleElem, _function);
            }
            _xifexpression = _xblockexpression;
          } else {
            _xifexpression = Elem2Elem.corrFactory.createMultiElem();
          }
          final CorrElem corrElem = _xifexpression;
          target.add(corrElem);
        }
      }
      EList<EObject> _flatTrg = corr.flatTrg();
      Iterables.<EObject>addAll(this.createdElems, _flatTrg);
      final Consumer<EObject> _function = (EObject e) -> {
        Elem2Elem.elementsToCorr.put(e, corr);
      };
      corr.flatTrg().forEach(_function);
    }
    return target;
  }

  private Corr updateOrCreateCorr(final CorrElem elem, final boolean src, final CorrElem... additionalElems) {
    java.util.Objects.<CorrElem>requireNonNull(elem, "The parameter \'elem\' must not be null!");
    java.util.Objects.<CorrElem[]>requireNonNull(additionalElems, "The parameter \'additionalElems\' must not be null!");
    boolean _contains = ArrayExtensions.contains(additionalElems, null);
    if (_contains) {
      throw new IllegalArgumentException("The additional elements must not contain null!");
    }
    ArrayList<CorrElem> _arrayList = new ArrayList<CorrElem>();
    final Procedure1<ArrayList<CorrElem>> _function = (ArrayList<CorrElem> it) -> {
      it.add(elem);
      Iterables.<CorrElem>addAll(it, ((Iterable<? extends CorrElem>)Conversions.doWrapArray(additionalElems)));
    };
    final ArrayList<CorrElem> elems = ObjectExtensions.<ArrayList<CorrElem>>operator_doubleArrow(_arrayList, _function);
    final Function1<CorrElem, List<EObject>> _function_1 = (CorrElem it) -> {
      List<EObject> _xifexpression = null;
      if ((it instanceof SingleElem)) {
        EObject _element = ((SingleElem)it).getElement();
        _xifexpression = Collections.<EObject>unmodifiableList(CollectionLiterals.<EObject>newArrayList(_element));
      } else {
        _xifexpression = ((MultiElem) it).getElements();
      }
      return _xifexpression;
    };
    final Iterable<EObject> flatElems = Iterables.<EObject>concat(ListExtensions.<CorrElem, List<EObject>>map(elems, _function_1));
    final Function1<EObject, Boolean> _function_2 = (EObject it) -> {
      return Boolean.valueOf((it == null));
    };
    boolean _exists = IterableExtensions.<EObject>exists(flatElems, _function_2);
    if (_exists) {
      throw new IllegalArgumentException("A corr must not be created from a null element!");
    }
    HashMap<Corr, Integer> corrCount = new HashMap<Corr, Integer>();
    for (final EObject e : flatElems) {
      {
        final Corr corr = Elem2Elem.elementsToCorr.get(e);
        if (((corr != null) && corrCount.containsKey(corr))) {
          Integer _get = corrCount.get(corr);
          int _plus = ((_get).intValue() + 1);
          corrCount.put(corr, Integer.valueOf(_plus));
        } else {
          if ((corr != null)) {
            corrCount.put(corr, Integer.valueOf(1));
          }
        }
      }
    }
    final List<Corr> corrs = IterableExtensions.<Corr>toList(corrCount.keySet());
    List<EList<CorrElem>> _xifexpression = null;
    if (src) {
      final Function1<Corr, EList<CorrElem>> _function_3 = (Corr it) -> {
        return it.getSource();
      };
      _xifexpression = ListExtensions.<Corr, EList<CorrElem>>map(corrs, _function_3);
    } else {
      final Function1<Corr, EList<CorrElem>> _function_4 = (Corr it) -> {
        return it.getTarget();
      };
      _xifexpression = ListExtensions.<Corr, EList<CorrElem>>map(corrs, _function_4);
    }
    final List<EList<CorrElem>> corrsElems = _xifexpression;
    List<EList<EObject>> _xifexpression_1 = null;
    if (src) {
      final Function1<Corr, EList<EObject>> _function_5 = (Corr it) -> {
        return it.flatSrc();
      };
      _xifexpression_1 = ListExtensions.<Corr, EList<EObject>>map(corrs, _function_5);
    } else {
      final Function1<Corr, EList<EObject>> _function_6 = (Corr it) -> {
        return it.flatTrg();
      };
      _xifexpression_1 = ListExtensions.<Corr, EList<EObject>>map(corrs, _function_6);
    }
    final List<EList<EObject>> corrsFlatElems = _xifexpression_1;
    final Function1<EObject, Boolean> _function_7 = (EObject it) -> {
      return Boolean.valueOf(this.detachedCorrElems.contains(it));
    };
    final boolean anyDetachedElem = IterableExtensions.<EObject>exists(flatElems, _function_7);
    Corr _xifexpression_2 = null;
    int _size = corrs.size();
    boolean _equals = (_size == 0);
    if (_equals) {
      _xifexpression_2 = Elem2Elem.corrFactory.createCorr();
    } else {
      Corr _xifexpression_3 = null;
      if (((((corrs.size() == 1) && Objects.equal(IterableExtensions.<Corr>head(corrs).getRuleId(), this.ruleId)) && (IterableExtensions.<EList<EObject>>head(corrsFlatElems).size() == (IterableExtensions.<Integer>head(corrCount.values())).intValue())) && (!anyDetachedElem))) {
        Corr _xblockexpression = null;
        {
          IterableExtensions.<EList<CorrElem>>head(corrsElems).clear();
          _xblockexpression = IterableExtensions.<Corr>head(corrs);
        }
        _xifexpression_3 = _xblockexpression;
      } else {
        Corr _xblockexpression_1 = null;
        {
          for (int i = 0; (i < corrs.size()); i++) {
            {
              EList<EObject> _get = corrsFlatElems.get(i);
              for (final EObject element : _get) {
                {
                  Elem2Elem.elementsToCorr.remove(element);
                  this.detachedCorrElems.add(element);
                }
              }
              corrsElems.get(i).clear();
            }
          }
          _xblockexpression_1 = Elem2Elem.corrFactory.createCorr();
        }
        _xifexpression_3 = _xblockexpression_1;
      }
      _xifexpression_2 = _xifexpression_3;
    }
    final Corr corr = _xifexpression_2;
    corr.setRuleId(this.ruleId);
    if (src) {
      EList<CorrElem> _source = corr.getSource();
      Iterables.<CorrElem>addAll(_source, elems);
      final Consumer<EObject> _function_8 = (EObject e_1) -> {
        Elem2Elem.elementsToCorr.put(e_1, corr);
      };
      corr.flatSrc().forEach(_function_8);
    } else {
      EList<CorrElem> _target = corr.getTarget();
      Iterables.<CorrElem>addAll(_target, elems);
      final Consumer<EObject> _function_9 = (EObject e_1) -> {
        Elem2Elem.elementsToCorr.put(e_1, corr);
      };
      corr.flatTrg().forEach(_function_9);
    }
    EObject _get = this.corrModel.getContents().get(0);
    EList<Corr> _correspondences = ((Transformation) _get).getCorrespondences();
    _correspondences.add(corr);
    return corr;
  }

  public static Map<EObject, Corr> getCorrMap() {
    return Elem2Elem.elementsToCorr;
  }

  protected static Object unwrap(final CorrElem elem) {
    if (elem instanceof MultiElem) {
      return _unwrap((MultiElem)elem);
    } else if (elem instanceof SingleElem) {
      return _unwrap((SingleElem)elem);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(elem).toString());
    }
  }
}
