package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.corrmodel.Corr
import de.tbuchmann.ttc.corrmodel.SingleElem
import de.tbuchmann.ttc.rules.Elem2Elem
import de.tbuchmann.ttc.trafo.Class2Relational
import java.util.ArrayList
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Data

abstract class MultiClassAttribute2Column extends Elem2Elem {
	new(Class2Relational trafo) {
		super("MultiClassAttribute2Column", trafo)
	}
	
	override CorrModelDelta sourceToTarget(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Source>()
		for (att : sourceModel.allContents.filter(typeof(class_.Attribute)).filter[filterAtt(it)].toIterable()) {
			_matches += new Source(att)
		}
		
		for (_match : _matches) {
			val att = _match.att
			
			val _corr = wrap(att).updateOrCreateCorrSrc()
			val _tType = new CorrElemType("Table", false)
			val _idType = new CorrElemType("Column", false)
			val _fkType = new CorrElemType("Column", false)
			val _trg = _corr.getOrCreateTrg(_tType, _idType, _fkType)
			val t = unwrap(_trg.get(0) as SingleElem) as relational_.Table
			val id = unwrap(_trg.get(1) as SingleElem) as relational_.Column
			val fk = unwrap(_trg.get(2) as SingleElem) as relational_.Column
			
			val _tName = tNameFrom(att.getName(), att.getOwner())
			t.setName(_tName.tName)
			
			val _idName = idNameFrom(att.getName(), att.getOwner())
			id.setName(_idName.idName)
			
			val _fkName = fkNameFrom(att.getName(), att.getOwner())
			fk.setName(_fkName.fkName)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	override void onTrgElemCreation(EObject trgElem) {
		switch (trgElem.corrElemPosition) {
			case 1: onIdCreation(trgElem as relational_.Column)
			case 2: onFkCreation(trgElem as relational_.Column)
		}
	}
	def protected abstract void onIdCreation(relational_.Column id);
	def protected abstract void onFkCreation(relational_.Column fk);
	
	override CorrModelDelta targetToSource(Set<EObject> _detachedCorrElems) {
		this.createdElems = new ArrayList<EObject>()
		this.spareElems = new ArrayList<EObject>()
		this.detachedCorrElems = _detachedCorrElems
				
		val _matches = new ArrayList<Target>()
		for (t : targetModel.allContents.filter(typeof(relational_.Table)).toIterable()) {
			for (id : targetModel.allContents.filter(typeof(relational_.Column)).toIterable()) {
				for (fk : targetModel.allContents.filter(typeof(relational_.Column)).toIterable()) {
					_matches += new Target(t, id, fk)
				}
			}
		}
		
		for (_match : _matches) {
			val t = _match.t
			val id = _match.id
			val fk = _match.fk
			
			val _corr = wrap(t).updateOrCreateCorrTrg(wrap(id), wrap(fk))
			val _attType = new CorrElemType("Attribute", false)
			_corr.getOrCreateSrc(_attType)
		}
		
		return new CorrModelDelta(this.createdElems, this.spareElems, this.detachedCorrElems)
	}
	
	def protected abstract boolean filterAtt(class_.Attribute att);
	
	@Data protected static class Type4tName {
		String tName
	}
	def protected abstract Type4tName tNameFrom(String attName, class_.Class attOwner);
	
	@Data protected static class Type4idName {
		String idName
	}
	def protected abstract Type4idName idNameFrom(String attName, class_.Class attOwner);
	
	@Data protected static class Type4fkName {
		String fkName
	}
	def protected abstract Type4fkName fkNameFrom(String attName, class_.Class attOwner);
	
	@Data protected static class Source {
		class_.Attribute att
	}
	def protected static Source source(Corr _corr) {
		_corr.assertRuleId("MultiClassAttribute2Column")
		val att = unwrap(_corr.source.get(0)) as class_.Attribute
		return new Source(att)
	}
	@Data protected static class Target {
		relational_.Table t
		relational_.Column id
		relational_.Column fk
	}
	def protected static Target target(Corr _corr) {
		_corr.assertRuleId("MultiClassAttribute2Column")
		val t = unwrap(_corr.target.get(0)) as relational_.Table
		val id = unwrap(_corr.target.get(1)) as relational_.Column
		val fk = unwrap(_corr.target.get(2)) as relational_.Column
		return new Target(t, id, fk)
	}
}