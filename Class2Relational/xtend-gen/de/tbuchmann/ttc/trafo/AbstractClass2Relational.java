package de.tbuchmann.ttc.trafo;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import de.tbuchmann.ttc.corrmodel.Corr;
import de.tbuchmann.ttc.corrmodel.CorrModelFactory;
import de.tbuchmann.ttc.corrmodel.Transformation;
import de.tbuchmann.ttc.rules.Elem2Elem;
import de.ubt.ai1.m2m.bxtenddsl.BXtendTransformation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public abstract class AbstractClass2Relational implements BXtendTransformation {
  protected final Resource sourceModel;

  protected final Resource targetModel;

  protected final Resource corrModel;

  private final List<Elem2Elem> rules;

  public AbstractClass2Relational() {
    final ResourceSet set = new ResourceSetImpl();
    Map<String, Object> _extensionToFactoryMap = set.getResourceFactoryRegistry().getExtensionToFactoryMap();
    XMIResourceFactoryImpl _xMIResourceFactoryImpl = new XMIResourceFactoryImpl();
    _extensionToFactoryMap.put("xmi", _xMIResourceFactoryImpl);
    this.sourceModel = set.createResource(URI.createURI("source.xmi"));
    this.targetModel = set.createResource(URI.createURI("target.xmi"));
    this.corrModel = set.createResource(URI.createURI("corr.xmi"));
    this.corrModel.getContents().add(CorrModelFactory.eINSTANCE.createTransformation());
    this.rules = this.createRules();
  }

  public AbstractClass2Relational(final Resource source, final Resource target, final Resource correspondence) {
    this.sourceModel = source;
    this.targetModel = target;
    this.corrModel = correspondence;
    int _size = this.corrModel.getContents().size();
    boolean _equals = (_size == 0);
    if (_equals) {
      this.corrModel.getContents().add(CorrModelFactory.eINSTANCE.createTransformation());
    }
    this.rules = this.createRules();
  }

  @Override
  public void sourceToTarget() {
    final HashMap<Elem2Elem, List<EObject>> createdElems = new HashMap<Elem2Elem, List<EObject>>();
    final HashMap<Elem2Elem, List<EObject>> spareElems = new HashMap<Elem2Elem, List<EObject>>();
    Set<EObject> detachedCorrElems = new HashSet<EObject>();
    for (final Elem2Elem rule : this.rules) {
      {
        final Elem2Elem.CorrModelDelta delta = rule.sourceToTarget(detachedCorrElems);
        createdElems.put(rule, delta.getCreatedElems());
        spareElems.put(rule, delta.getSpareElems());
        detachedCorrElems = delta.getDetachedCorrElems();
      }
    }
    EObject _get = this.corrModel.getContents().get(0);
    EList<Corr> _correspondences = ((Transformation) _get).getCorrespondences();
    for (final Corr corr : _correspondences) {
      final Function1<EObject, Boolean> _function = (EObject it) -> {
        EObject _eContainer = it.eContainer();
        return Boolean.valueOf((_eContainer == null));
      };
      Iterable<EObject> _filter = IterableExtensions.<EObject>filter(corr.flatTrg(), _function);
      for (final EObject trg : _filter) {
        EList<EObject> _contents = this.targetModel.getContents();
        _contents.add(trg);
      }
    }
    for (final Elem2Elem rule_1 : this.rules) {
      List<EObject> _get_1 = createdElems.get(rule_1);
      for (final EObject createdElem : _get_1) {
        rule_1.onTrgElemCreation(createdElem);
      }
    }
    for (final Elem2Elem rule_2 : this.rules) {
      List<EObject> _get_2 = spareElems.get(rule_2);
      for (final EObject spareElem : _get_2) {
        {
          rule_2.onTrgElemDeletion(spareElem);
          EcoreUtil.delete(spareElem, false);
        }
      }
    }
    this.deleteUnreferencedTargetElements();
  }

  @Override
  public void targetToSource() {
    final HashMap<Elem2Elem, List<EObject>> createdElems = new HashMap<Elem2Elem, List<EObject>>();
    final HashMap<Elem2Elem, List<EObject>> spareElems = new HashMap<Elem2Elem, List<EObject>>();
    Set<EObject> detachedCorrElems = new HashSet<EObject>();
    for (final Elem2Elem rule : this.rules) {
      {
        final Elem2Elem.CorrModelDelta delta = rule.targetToSource(detachedCorrElems);
        createdElems.put(rule, delta.getCreatedElems());
        spareElems.put(rule, delta.getSpareElems());
        detachedCorrElems = delta.getDetachedCorrElems();
      }
    }
    EObject _get = this.corrModel.getContents().get(0);
    EList<Corr> _correspondences = ((Transformation) _get).getCorrespondences();
    for (final Corr corr : _correspondences) {
      final Function1<EObject, Boolean> _function = (EObject it) -> {
        EObject _eContainer = it.eContainer();
        return Boolean.valueOf((_eContainer == null));
      };
      Iterable<EObject> _filter = IterableExtensions.<EObject>filter(corr.flatSrc(), _function);
      for (final EObject src : _filter) {
        EList<EObject> _contents = this.sourceModel.getContents();
        _contents.add(src);
      }
    }
    for (final Elem2Elem rule_1 : this.rules) {
      List<EObject> _get_1 = createdElems.get(rule_1);
      for (final EObject createdElem : _get_1) {
        rule_1.onSrcElemCreation(createdElem);
      }
    }
    for (final Elem2Elem rule_2 : this.rules) {
      List<EObject> _get_2 = spareElems.get(rule_2);
      for (final EObject spareElem : _get_2) {
        {
          rule_2.onSrcElemDeletion(spareElem);
          EcoreUtil.delete(spareElem, false);
        }
      }
    }
    this.deleteUnreferencedSourceElements();
  }

  @Override
  public Resource getCorr() {
    return this.corrModel;
  }

  @Override
  public Resource getSource() {
    return this.sourceModel;
  }

  @Override
  public Resource getTarget() {
    return this.targetModel;
  }

  protected abstract List<Elem2Elem> createRules();

  private Iterator<Corr> detectSourceDeletions() {
    final Function1<Corr, Boolean> _function = (Corr c) -> {
      return Boolean.valueOf(c.flatSrc().isEmpty());
    };
    return IteratorExtensions.<Corr>filter(Iterators.<Corr>filter(this.corrModel.getAllContents(), Corr.class), _function);
  }

  private Iterator<Corr> detectTargetDeletions() {
    final Function1<Corr, Boolean> _function = (Corr c) -> {
      return Boolean.valueOf(c.flatTrg().isEmpty());
    };
    return IteratorExtensions.<Corr>filter(Iterators.<Corr>filter(this.corrModel.getAllContents(), Corr.class), _function);
  }

  private void deleteUnreferencedTargetElements() {
    final List<EObject> deletionList = CollectionLiterals.<EObject>newArrayList();
    final Procedure1<Corr> _function = (Corr c) -> {
      final Function1<Elem2Elem, Boolean> _function_1 = (Elem2Elem it) -> {
        String _ruleId = c.getRuleId();
        return Boolean.valueOf(Objects.equal(it.ruleId, _ruleId));
      };
      final Elem2Elem rule = IterableExtensions.<Elem2Elem>findFirst(this.rules, _function_1);
      final Consumer<EObject> _function_2 = (EObject it) -> {
        rule.onTrgElemDeletion(it);
      };
      c.flatTrg().forEach(_function_2);
      EList<EObject> _flatTrg = c.flatTrg();
      Iterables.<EObject>addAll(deletionList, _flatTrg);
      deletionList.add(c);
    };
    IteratorExtensions.<Corr>forEach(this.detectSourceDeletions(), _function);
    final Consumer<EObject> _function_1 = (EObject e) -> {
      EcoreUtil.delete(e, true);
    };
    deletionList.forEach(_function_1);
  }

  private void deleteUnreferencedSourceElements() {
    final List<EObject> deletionList = CollectionLiterals.<EObject>newArrayList();
    final Procedure1<Corr> _function = (Corr c) -> {
      final Function1<Elem2Elem, Boolean> _function_1 = (Elem2Elem it) -> {
        String _ruleId = c.getRuleId();
        return Boolean.valueOf(Objects.equal(it.ruleId, _ruleId));
      };
      final Elem2Elem rule = IterableExtensions.<Elem2Elem>findFirst(this.rules, _function_1);
      final Consumer<EObject> _function_2 = (EObject it) -> {
        rule.onSrcElemDeletion(it);
      };
      c.flatSrc().forEach(_function_2);
      EList<EObject> _flatSrc = c.flatSrc();
      Iterables.<EObject>addAll(deletionList, _flatSrc);
      deletionList.add(c);
    };
    IteratorExtensions.<Corr>forEach(this.detectTargetDeletions(), _function);
    final Consumer<EObject> _function_1 = (EObject e) -> {
      EcoreUtil.delete(e, true);
    };
    deletionList.forEach(_function_1);
  }
}
