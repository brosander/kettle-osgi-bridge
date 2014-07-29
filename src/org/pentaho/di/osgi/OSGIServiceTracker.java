package org.pentaho.di.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * User: nbaker Date: 11/11/10
 */
public class OSGIServiceTracker<S, T> extends ServiceTracker<S, T> {

  private Class<T> clazzToTrack;

  private List<ServiceReference<S>> references = new ArrayList<ServiceReference<S>>();
  private OSGIPluginTracker tracker;

  public OSGIServiceTracker( OSGIPluginTracker tracker, Class<T> clazzToTrack ) {
    super( tracker.getBundleContext(), clazzToTrack.getName(), null );
    this.tracker = tracker;
    this.clazzToTrack = clazzToTrack;
  }

  @Override
  public T addingService( ServiceReference<S> reference ) {
    references.add( reference );
    T retVal = super.addingService( reference );
    tracker.serviceChanged( clazzToTrack, OSGIPluginTracker.Event.START, reference );
    return retVal;
  }

  @Override
  public void removedService( ServiceReference<S> reference, T service ) {
    references.remove( reference );
    super.removedService( reference, service );
    tracker.serviceChanged( clazzToTrack, OSGIPluginTracker.Event.STOP, reference );
  }

  @Override
  public void modifiedService( ServiceReference<S> reference, T service ) {
    super.modifiedService( reference, service );
    tracker.serviceChanged( clazzToTrack, OSGIPluginTracker.Event.MODIFY, reference );
  }

}
