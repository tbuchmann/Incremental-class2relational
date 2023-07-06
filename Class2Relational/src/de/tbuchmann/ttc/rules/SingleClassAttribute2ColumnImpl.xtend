package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import class_.Attribute
import class_.Classifier
import class_.DataType
import de.tbuchmann.ttc.rules.SingleClassAttribute2Column.Type4colName
import de.tbuchmann.ttc.rules.SingleClassAttribute2Column.Type4colType

// Transformation
class SingleClassAttribute2ColumnImpl extends SingleClassAttribute2Column {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	override protected filterAtt(Attribute att) {
		// Model Traversal
		!(att.isMultiValued) && !(att.type instanceof DataType) 
	}
	
	override protected colNameFrom(String attName, Classifier attType) {
		new Type4colName(attName + "Id")
	}
	
	override protected colTypeFrom(String attName, Classifier attType) {
		new Type4colType(Utils.getType(findIntegerDatatype()))
	}
	
	def findIntegerDatatype() {
		// Model Traversal
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
}
