package at.bestsolution.rap.tooling.css;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.fx.ide.css.cssext.ICSSExtModelProvider;
import org.eclipse.fx.ide.css.cssext.cssExtDsl.CssExtension;

public class RAPCSSModelProvider implements ICSSExtModelProvider {
	private CssExtension rapModel;
	
	@Override
	public Collection<CssExtension> getModels(IFile arg0) {
		if( arg0.getName().endsWith(".rapcss") ) {
			if( rapModel == null ) {
				rapModel = loadModel("rap");
			}
			return Collections.singletonList(rapModel);
		}
		return Collections.emptyList();
	}

	private CssExtension loadModel(String name) {
		URI uri = URI.createPlatformPluginURI("/at.bestsolution.rap.tooling.css/OSGI-INF/"+name+".cssext", true);
		
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.getResource(uri, true);
		CssExtension model = (CssExtension) resource.getContents().get(0);
		return model;
	}
}
