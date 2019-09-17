package com.comodide.deprecated;

import org.semanticweb.owlapi.model.OWLProperty;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import com.comodide.editor.SDConstants;

@Deprecated
public class SDEdge
{
	private static final ShortFormProvider shortFormProvider = new SimpleShortFormProvider();

	private boolean isSubclass;
	
	private SDNode source;
	private SDNode target;
	
	private OWLProperty owlProperty;
	
	public SDEdge(SDNode source, SDNode target, boolean isSubClass, OWLProperty owlProperty)
	{
		this.isSubclass = isSubClass;
		this.source = source;
		this.target = target;
		this.owlProperty = owlProperty;
	}
	
	public String toString()
	{
		return isSubclass ? "subClassOf" : shortFormProvider.getShortForm(owlProperty);
	}

	@Deprecated
	public String getLabel()
	{
		return toString();
	}
	
	public boolean isSubclass()
	{
		return isSubclass;
	}

	public void setSubclass(boolean isSubclass)
	{
		this.isSubclass = isSubclass;
	}

	public SDNode getSource()
	{
		return source;
	}

	public void setSource(SDNode source)
	{
		this.source = source;
	}

	public SDNode getTarget()
	{
		return target;
	}

	public void setTarget(SDNode target)
	{
		this.target = target;
	}

	public OWLProperty getOwlProperty() {
		return owlProperty;
	}

	public String getStyle() {
		if (this.owlProperty.getIRI().equals(OWLRDFVocabulary.RDFS_SUBCLASS_OF.getIRI())) {
			return SDConstants.subclassEdgeStyle;
		}
		else {
			return SDConstants.standardEdgeStyle;
		}
	}
	
	
}