package org.pentaho.di.karaf;

import java.io.File;

import org.apache.karaf.main.Main;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LogChannelInterface;

/**
 * User: nbaker
 * Date: 1/19/11
 */

public class KarafHost {
  private static final KarafHost instance = init();
  private final LogChannelInterface log;
  
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
  private Main main;

  private KarafHost() throws Exception {
    log = KettleLogStore.getLogChannelInterfaceFactory().create( this );
    Thread karafLaunchThread = new Thread( new Runnable() {
      
      @Override
      public void run() {
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
        try {
          main.launch();
        } catch ( Exception e ) {
          log.logError( "Unable to launch Karaf", e );
        }
      }
    } );
    karafLaunchThread.setContextClassLoader( this.getClass().getClassLoader() );
    karafLaunchThread.start();
  }

  public synchronized boolean isInitialized(){
    return initialized;
  }

  public synchronized void setInitialized(boolean inited){
    initialized = inited;
  }
}

