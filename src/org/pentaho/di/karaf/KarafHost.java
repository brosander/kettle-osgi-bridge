package org.pentaho.di.karaf;

import org.apache.commons.lang.ObjectUtils;
import org.apache.karaf.main.Main;
import org.pentaho.di.core.annotations.LifecyclePlugin;
import org.pentaho.di.core.gui.GUIOption;
import org.pentaho.di.core.lifecycle.LifeEventHandler;
import org.pentaho.di.core.lifecycle.LifecycleException;
import org.pentaho.di.core.lifecycle.LifecycleListener;
import org.pentaho.di.core.plugins.PluginClassTypeMapping;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User: nbaker
 * Date: 1/19/11
 */

public class KarafHost {
  private static final KarafHost instance = init();
  
  public static KarafHost getInstance() {
    return instance;
  }
  
  private static KarafHost init() {
    try {
      return new KarafHost();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private boolean initialized;
  private final Main main;

  private KarafHost() throws Exception {
    String root = new File( "plugins/karaf-plugin/karaf").getAbsolutePath();
    System.setProperty("karaf.home", root);
    System.setProperty("karaf.base", root);
    System.setProperty("karaf.data", root + "/data");
    System.setProperty("karaf.history", root + "/data/history.txt");
    System.setProperty("karaf.instances", root + "/instances");
    System.setProperty("karaf.startLocalConsole", "false");
    System.setProperty("karaf.startRemoteShell", "true");
    System.setProperty("karaf.lock", "false");
    main = new Main(new String[0]);
    main.launch();
  }

  public synchronized boolean isInitialized(){
    return initialized;
  }

  public synchronized void setInitialized(boolean inited){
    initialized = inited;
  }
}

