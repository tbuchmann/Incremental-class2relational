package de.tbuchmann.ttc.rules;

import class_.Attribute
import class_.Class
import de.tbuchmann.ttc.trafo.Class2Relational
import relational_.Table

import static extension org.eclipse.xtext.util.Strings.toFirstLower
import de.tbuchmann.ttc.rules.MultiClassAttribute2Column.Type4idName
import de.tbuchmann.ttc.rules.MultiClassAttribute2Column.Type4fkName
import relational_.Column
import class_.DataType

class MultiClassAttribute2ColumnImpl extends MultiClassAttribute2Column {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	override protected filterAtt(Attribute att) {
		(att.isMultiValued) && (att.type instanceof Class)
	}
	
	override protected tNameFrom(String attName, Class owner) {
		new Type4tName(owner.name + "_" + attName)
	}	
	
	override protected idNameFrom(String attName, Class attOwner) {
		new Type4idName(attOwner.name.toFirstLower + "Id")
	}
	
	override protected fkNameFrom(String attName, Class attOwner) {
		new Type4fkName(attName + "Id")
	}
	
	override protected onIdCreation(Column id) {
		id.type = Utils.getType(findIntegerDatatype())
		id.corr.target().t.col += id
	}
	
	override protected onFkCreation(Column fk) {
		fk.type = Utils.getType(findIntegerDatatype())
		fk.corr.target().t.col += fk
	}
	
	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}	
	
}
