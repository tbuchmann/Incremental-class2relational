package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import relational_.Table
import relational_.RelationalFactory
import java.util.List
import relational_.Column
import de.tbuchmann.ttc.rules.Class2Table.Type4col
import class_.DataType

class Class2TableImpl extends Class2Table {	
	new(Class2Relational trafo) {
		super(trafo)
	}
	
	override protected onTblCreation(Table tbl) {
		var key = RelationalFactory.eINSTANCE.createColumn => [name = "objectID"]
		key.type = Utils.getType(findIntegerDatatype())
		tbl.col.add(0, key)
		tbl.key += key
	}
 
	override protected colFrom(List<Column> attSinCol, List<Column> attSinCol_2, List<Table> attMulT, 
		Table parent
	) {
		val columnsList = newArrayList
		// save Object ID column
		if (!parent.col.empty) {
			var key = parent.col.get(0)
			columnsList += key	
		}
		// add SingleColtoDatatype (attSinCol) and attMulCol directly to columns
		columnsList += attSinCol
		columnsList += attSinCol_2		
		new Type4col(columnsList)
	}

	def findIntegerDatatype() {
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}	
}
