package de.tbuchmann.ttc.rules

import class_.Classifier
import de.tbuchmann.ttc.corrmodel.SingleElem
import relational_.Type

class Utils {
	
	def static getType(Classifier type) {
		var corr = Elem2Elem.corrMap.get(type)
		if (corr === null) return null;
		var targetCorr = corr.target.get(0) as SingleElem
		targetCorr.element as Type
	}

}