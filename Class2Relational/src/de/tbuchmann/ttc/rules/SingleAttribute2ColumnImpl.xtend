package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import class_.Attribute

class SingleAttribute2ColumnImpl extends SingleAttribute2Column {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	override protected filterAtt(Attribute att) {
		!(att.isMultiValued) && !(att.type instanceof class_.Class) 
	}
	
}
