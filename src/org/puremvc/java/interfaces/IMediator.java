/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package org.puremvc.java.interfaces;

/**
 * The interface definition for a PureMVC Mediator.
 * <p>
 * <p>
 * In PureMVC, <code>IMediator</code> implementors assume these
 * responsibilities:
 * </P>
 * <UL>
 * <LI>Implement a common method which returns a list of all
 * <code>INotification</code>s the <code>IMediator</code> has interest in.</LI>
 * <LI>Implement a common notification (callback) method.</LI>
 * </UL>
 * <p>
 * Additionally, <code>IMediator</code>s typically:
 * <UL>
 * <LI>Act as an intermediary between one or more view components such as text
 * boxes or list controls, maintaining references and coordinating their
 * behavior.</LI>
 * <LI>In Flash-based apps, this is often the place where event listeners are
 * added to view components, and their handlers implemented.</LI>
 * <LI>Respond to and generate <code>INotifications</code>, interacting with
 * of the rest of the PureMVC app.
 * </UL>
 * </P>
 * <p>
 * When an <code>IMediator</code> is registered with the <code>IView</code>,
 * the <code>IView</code> will call the <code>IMediator</code>'s
 * <code>listNotificationInterests</code> method. The <code>IMediator</code>
 * will return an <code>Array</code> of <code>INotification</code> names
 * which it wishes to be notified about.
 * </P>
 * <p>
 * <p>
 * The <code>IView</code> will then create an <code>Observer</code> object
 * encapsulating that <code>IMediator</code>'s (<code>handleNotification</code>)
 * method and register it as an Observer for each <code>INotification</code>
 * name returned by <code>listNotificationInterests</code>.
 * </P>
 * <p>
 * <p>
 * A concrete IMediator implementor usually looks something like this:
 * </P>
 * <p>
 * <listing> import org.puremvc.patterns.mediator.~~; import
 * org.puremvc.patterns.observer.~~; import org.puremvc.core.view.~~;
 * <p>
 * import com.me.myapp.model.~~; import com.me.myapp.view.~~; import
 * com.me.myapp.controller.~~;
 * <p>
 * import mx.controls.ComboBox; import mx.events.ListEvent;
 * <p>
 * public class MyMediator extends Mediator implements IMediator {
 * <p>
 * public function MyComboMediator( viewComponent:Object ) { super(
 * viewComponent ); combo.addEventListener( Event.CHANGE, onChange ); }
 * <p>
 * public function listNotificationInterests():Array { return [
 * MyFacade.SET_SELECTION, MyFacade.SET_DATAPROVIDER ]; }
 * <p>
 * public function handleNotification( notification:INotification ):void {
 * switch ( notification.getName() ) { case MyFacade.SET_SELECTION:
 * setSelection(notification); break; case MyFacade.SET_DATAPROVIDER:
 * setDataProvider(notification); break; } } // Set the data provider of the
 * combo box private function setDataProvider( notification:INotification ):void {
 * combo.dataProvider = notification.getBody() as Array; } // Invoked when the
 * combo box dispatches a change event, we send a // notification with the
 * private function onChange(event:ListEvent):void { sendNotification(
 * MyFacade.MYCOMBO_CHANGED, this ); } // A private getter for accessing the
 * view object by class private function get combo():ComboBox { return view as
 * ComboBox; } } </listing>
 *
 * @see org.puremvc.java.interfaces.INotification INotification
 */
public interface IMediator extends INotifier {

    /**
     * Get the <code>IMediator</code> instance name
     *
     * @return the <code>IMediator</code> instance name
     */
    public String getMediatorName();

    /**
     * Get the <code>IMediator</code>'s view component.
     *
     * @return Object the view component
     */
    public Object getViewComponent();

    /**
     * Set the <code>IMediator</code>'s view component.
     *
     * @param viewComponent The view component
     */
    public void setViewComponent(Object viewComponent);

    /**
     * List <code>INotification</code> interests.
     *
     * @return an <code>Array</code> of the <code>INotification</code> names
     * this <code>IMediator</code> has an interest in.
     */
    public String[] listNotificationInterests();

    /**
     * Handle an <code>INotification</code>.
     *
     * @param notification the <code>INotification</code> to be handled
     */
    public void handleNotification(INotification notification);

    /**
     * Called by the View when the Mediator is registered
     */
    public void onRegister();

    /**
     * Called by the View when the Mediator is removed
     */
    void onRemove();
}
