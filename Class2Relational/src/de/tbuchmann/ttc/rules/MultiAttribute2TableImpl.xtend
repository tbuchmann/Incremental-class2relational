package de.tbuchmann.ttc.rules;

import class_.Attribute
import class_.Class
import class_.Classifier
import class_.DataType
import de.tbuchmann.ttc.trafo.Class2Relational
import relational_.RelationalFactory

class MultiAttribute2TableImpl extends MultiAttribute2Table {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	override protected filterAtt(Attribute att) {
		(att.isMultiValued) && !(att.type instanceof Class) 
	}
	
	override protected colFrom(String attName, Classifier type, Class owner) {
		val colList = newArrayList
		val idCol = RelationalFactory.eINSTANCE.createColumn() => [name = owner.name.toFirstLower + "Id"
			type = Utils.getType(findIntegerDatatype())
		]
		val valCol = RelationalFactory.eINSTANCE.createColumn() => [name = attName
			type = Utils.getType(type)
		]
		
		colList += idCol
		colList += valCol
		
		return new Type4col(colList)
	}
	
	override protected tblNameFrom(String attName, Class owner) {
		var tblName = owner.name
		if (tblName == null || tblName === "") tblName = "Table"
		new Type4tblName(owner.name + "_" + attName)
	}
	
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}
	
}
