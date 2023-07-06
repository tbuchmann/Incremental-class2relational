package de.tbuchmann.ttc.rules;

import de.tbuchmann.ttc.trafo.Class2Relational
import relational_.Table
import relational_.RelationalFactory
import java.util.List
import relational_.Column
import de.tbuchmann.ttc.rules.Class2Table.Type4col
import class_.DataType
import org.eclipse.emf.ecore.util.EcoreUtil
import de.tbuchmann.ttc.corrmodel.SingleElem
import class_.Attribute

// Transformation
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

		// check for columns with null-Type and delete them (avoid dangling references)		
		for (Column c : attSinCol) {
			// Tracing
			var obj = unwrap(c.corr.source.get(0) as SingleElem) as Attribute
			if (obj.type !== null) {
				columnsList += c	
			}
			else {
				c.owner = null
				EcoreUtil.delete(c, true)
			}
		}

		for (Column c : attSinCol_2) {
			// Tracing
			var obj = unwrap(c.corr.source.get(0) as SingleElem) as Attribute
			if (obj.type !== null) {
				columnsList += c	
			}
			else {
				EcoreUtil.delete(c, true)
			}
		}
		// delete Tables that are created from attributes with null-Type
		for (Table t : attMulT) {
			// Tracing
			var obj = unwrap(t.corr.source.get(0) as SingleElem) as Attribute
			if (obj.type === null)
				EcoreUtil.delete(t, true);
		}		
		new Type4col(columnsList)
	}

	def findIntegerDatatype() {
		// Model traversal
		val datatype = sourceModel.contents.filter(typeof(DataType)).findFirst[name == "Integer"]
		datatype
	}	
	
	def removeNullTypeColumns(List<Column> cols) {
		for (Column c : cols) {
			if (c.type === null) 
				EcoreUtil.delete(c, true);
			// check if corresponding attribute has null type
			// Tracing
			var obj = unwrap(c.corr.source.get(0) as SingleElem) as Attribute
			if (obj.type === null) {
				c.owner = null;
				EcoreUtil.delete(c, true);	
			}
		}
	}
}
