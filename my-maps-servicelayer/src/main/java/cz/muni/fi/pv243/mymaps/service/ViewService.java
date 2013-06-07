/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv243.mymaps.service;

import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface ViewService {

    /**
     * Creates new view
     * 
     * @param view view to be created
     * @throws IllegalArgumentException if view is null
     * @return newly created view
     */
    public View createView(View view);

    /**
     * Creates new view
     * 
     * @param view view to be created
     * @param User owner of newly created view
     * @throws IllegalArgumentException if view is null
     * @return newly created view
     */
    public View createView(View view, User user);

    /**
     * Updates existing view
     * 
     * @param view view to be updated
     * @throws IllegalArgumentException if view is null
     * @return updated view
     */
    public View updateView(View view);

    /**
     * Deletes view
     * 
     * @param view view to be deleted
     * @throws IllegalArgumentException if view is null
     */
    public void deleteView(View view);

    /**
     * Returns view with specified ID
     * 
     * @param id id of mviewap
     * @throws IllegalArgumentException if id is null
     * @return view with specified id
     */
    public View getViewById(Long id);

    /**
     * Returns list of views with specified name
     *
     * @param name name of user to be found
     * @throws IllegalArgumentException if name is null
     * @return list of found users
     */
    public List<View> findViewsByName(String name);

    /**
     * Returns view for specified map
     *
     * @param myMap myMap which view is to be found
     * @throws IllegalArgumentException if myMap is null
     * @return found view
     */
    public View getViewByMap(MyMap myMap);

    /**
     * Returns list of views for specified user
     *
     * @param user user whose views are to be found
     * @throws IllegalArgumentException if user is null
     * @return list of found views
     */
    public Collection<View> getViewsByUser(User user);
}
