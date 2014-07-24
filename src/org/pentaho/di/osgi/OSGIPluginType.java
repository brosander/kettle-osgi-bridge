package org.pentaho.di.osgi;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.List;

import org.pentaho.di.core.exception.KettlePluginException;
import org.pentaho.di.core.plugins.PluginFolderInterface;
import org.pentaho.di.core.plugins.PluginTypeInterface;

/**
 * User: nbaker
 * Date: 12/9/10
 */
public class OSGIPluginType implements PluginTypeInterface {

  private String ID;
  private String name;
  private static OSGIPluginType pluginType;

  public OSGIPluginType(){
    ID = "OSGI_PLUGIN_TYPE";
    name = "Osgi Plugin";
  }

	public static OSGIPluginType getInstance() {
		if (pluginType==null) {
			pluginType=new OSGIPluginType();
		}
		return pluginType;
	}

  /**
   * No meaning in OSGI
   */
  public void addObjectType(Class<?> clz, String xmlNodeName) {
  }

  public String getId() {
    return ID;
  }

  public String getName() {
    return name;
  }

  /**
   * No meaning in OSGI
   */
  public List<PluginFolderInterface> getPluginFolders() {
    return null;
  }

  /**
   * No meaning in OSGI
   */
  public void handlePluginAnnotation(Class<?> clazz, Annotation annotation, List<String> libraries, boolean nativePluginType, URL pluginFolder) throws KettlePluginException {

  }

  public void searchPlugins() throws KettlePluginException {

  }
}
