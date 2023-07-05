package de.tbuchmann.bxtenddsl;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Changes.ChangesPackage;
import class_.Attribute;
import class_.Class_Factory;
import class_.Class_Package;
import de.tbuchmann.ttc.trafo.Class2Relational;
import relational_.RelationalPackage;

public class BXtendDSLSolution  {
	private Class2Relational trafo;
	protected ResourceSet resourceSet = new ResourceSetImpl();
    protected Resource source;
    protected Resource target;
    protected Resource changes;
    protected Resource corr;

    boolean batchMode = false;

    public Resource getSource() {
        return source;
    }

    public Resource getTarget() {
        return target;
    }

    protected ResourceSet getResourceSet() {
        return resourceSet;
    }

    public boolean isBatchMode() {
        return batchMode;
    }

    public void init() {
        batchMode = (System.getenv("BATCH_MODE") != null);
        setupResourceSet();
    	loadModels();
    	trafo = new Class2Relational(source, target, corr);
    }
    
    protected void setupResourceSet() {
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
            "xmi",
            new XMIResourceFactoryImpl()
        );

        // Register source, change and target metamodels in global registry
        EPackage e;
        e = Class_Package.eINSTANCE;
        e = RelationalPackage.eINSTANCE;
        e = ChangesPackage.eINSTANCE;
    }

    protected void loadModels() {
    	String filePath = "models/correctness7";
    	source = loadModel(filePath + "/class.xmi");
        changes = loadModel(filePath + "/change.xmi");
        target = createModel(filePath + "/relational.xmi");
        corr = createModel(filePath + "/corr.xmi");
        EcoreUtil.resolveAll(resourceSet);
    }

    protected Resource loadModel(String modelPath) {
        System.out.println("Loading model from " + modelPath);
        return resourceSet.getResource(URI.createFileURI(modelPath), true);
    }

    protected Resource createModel(String modelPath) {
        return resourceSet.createResource(URI.createFileURI(modelPath));
    }

    protected void applyChange() {
//    	EcoreUtil.resolveAll(resourceSet);
//        // we ignore change models that are empty (for checking correctness)
//        if (changes.getContents().size() > 0) {
//            ModelChangeSet change = (ModelChangeSet) changes.getContents().get(0);
//            for (ModelChange c : change.getChanges()) {
//                System.out.println("Applying change " + c);
//                c.apply();
//            }
//        }
    	// change for correctness1 - change class name Person -> Member
//    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
//    	if (person != null) {
//    		((class_.Class)person.get()).setName("Member");
//    	} // end correctness 1
    	
//    	// change for correctness2 - change class name Family -> ""
//    	Optional<EObject> family = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Family") ).findFirst();
//    	if (family != null) {
//    		((class_.Class)family.get()).setName("");
//    	} // end correctness 2
    	
//    	// change for correctness3 - change attribute firstName to multivalued
//    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
//    	if (person != null) {
//    		((class_.Class)person.get()).getAttr().stream().filter(a -> a.getName().equals("firstName")).findFirst().get().setMultiValued(true);
//    	} // end correctness 3
    	
    	// change for correctness4 - change attribute email addresses to singlevalued
//    	Optional<EObject> attr = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person")).findFirst();
//    	if (attr != null) {
//    		Attribute att = ((class_.Class)attr.get()).getAttr().stream().filter(a -> a.getName().equals("emailAddresses")).findFirst().get();
//    		att.setMultiValued(false);
//    	} // end correctness4
    	
    	// change for correctness4 - change attribute email addresses to multivalued
//    	Optional<EObject> attr = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person")).findFirst();
//    	if (attr != null) {
//    		Attribute att = ((class_.Class)attr.get()).getAttr().stream().filter(a -> a.getName().equals("emailAddresses")).findFirst().get();
//    		att.setMultiValued(false);
//    	} // end correctness4
    	
    	// change for correctness5 - delete attribute firstnames    	
//    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
//    	if (person != null) {
//			Attribute att = ((class_.Class)person.get()).getAttr().stream().filter(a -> a.getName().equals("firstName")).findFirst().get();
//			EcoreUtil.delete(att, true);
//		} // end correctness5
    	
//    	// change for correctness6 - delete attribute closest friend    	
//    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
//    	if (person != null) {
//			Attribute att = ((class_.Class)person.get()).getAttr().stream().filter(a -> a.getName().equals("closestFriend")).findFirst().get();
//			EcoreUtil.delete(att, true);
//		} // end correctness6
    	
    	// change for correctness7 - set type of attribute closest friend to null
    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
    	if (person != null) {
			Attribute att = ((class_.Class)person.get()).getAttr().stream().filter(a -> a.getName().equals("closestFriend")).findFirst().get();
			att.setType(null);
		} // end correctness7
    	
    	// change for correctness8 - add new class "House"
//    	class_.Class house = Class_Factory.eINSTANCE.createClass();
//    	house.setName("House");
//    	source.getContents().add(house);
    	
    	// change for correctness9 - add new attribute "newMultiValuedAttribute" - multivalued = false, type = null ? to class Person
//    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
//    	if (person != null) {
//    		class_.Attribute newAttr = Class_Factory.eINSTANCE.createAttribute();
//    		newAttr.setName("newMutliValuedAttribute");
//    		((class_.Class)person.get()).getAttr().add(newAttr);
//    	}
    	
//    	// change for correctness10 - add new attribute "newSingleValuedAttribute" - without a parent object
//    	class_.Attribute newAttr = Class_Factory.eINSTANCE.createAttribute();
//		newAttr.setName("newSingleValuedAttribute");
    	
    	// change for correctness13 - delete Person class
//    	Optional<EObject> person = source.getContents().stream().filter(o -> o instanceof class_.Class).filter(o -> ((class_.Class)o).getName().equals("Person") ).findFirst();
//    	if (person != null) {
//    		EcoreUtil.delete(person.get(), true);
//    	}
    	
    	trafo.sourceToTarget();
    }

    protected void saveTarget() throws IOException {
        target.save(Collections.emptyMap());
    }
	
    public static void main(String[] args) throws Exception {
    	BXtendDSLSolution solution = new BXtendDSLSolution();

        solution.init();
        if (solution.isBatchMode()) {
            solution.applyChange();
            solution.applyTransformation();
        }
        else {
            solution.applyTransformation();
            solution.applyChange();
        }
        solution.saveTarget();
    }

    protected void applyTransformation() {
    	trafo.sourceToTarget();
    }
    
        
}
