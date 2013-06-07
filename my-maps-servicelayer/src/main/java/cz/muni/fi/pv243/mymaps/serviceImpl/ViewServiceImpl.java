package cz.muni.fi.pv243.mymaps.serviceImpl;

import cz.muni.fi.pv243.mymaps.dao.UserDao;
import cz.muni.fi.pv243.mymaps.dao.ViewDao;
import cz.muni.fi.pv243.mymaps.dto.MyMap;
import cz.muni.fi.pv243.mymaps.dto.User;
import cz.muni.fi.pv243.mymaps.dto.View;
import cz.muni.fi.pv243.mymaps.entities.UserEntity;
import cz.muni.fi.pv243.mymaps.entities.ViewEntity;
import cz.muni.fi.pv243.mymaps.service.ViewService;
import cz.muni.fi.pv243.mymaps.util.EntityDTOconvertor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jboss.logging.Logger;

/**
 *
 * @author Kuba
 */
@Stateless
public class ViewServiceImpl implements ViewService {

    @Inject
    private ViewDao viewDao;
     @Inject
    private UserDao userDao;
    @Inject
    protected Logger log;
    
    @Override
    public View createView(View view) {
        if (view == null) {
            String msg = "View can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        ViewEntity newView = EntityDTOconvertor.convertView(view);

        newView = viewDao.create(newView);
        
        return EntityDTOconvertor.convertView(newView);
    }
    
     @Override
    public View createView(View view, User user) {
        if (view == null) {
            String msg = "View can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        ViewEntity newView = EntityDTOconvertor.convertView(view);
        UserEntity userEntity = EntityDTOconvertor.convertUser(user);
        newView = viewDao.create(newView);
        Collection<ViewEntity> views = userEntity.getViews();
        views.add(newView);
        userEntity.setViews(views);
        userDao.update(userEntity);
        
        return EntityDTOconvertor.convertView(newView);       
    }

    @Override
    public View updateView(View view) {
        if (view == null) {
            String msg = "View can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        ViewEntity newView = EntityDTOconvertor.convertView(view);

        newView = viewDao.update(newView);
        
        return EntityDTOconvertor.convertView(newView);
    }

    @Override
    public void deleteView(View view) {
        if (view == null) {
            String msg = "View can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        ViewEntity newView = EntityDTOconvertor.convertView(view);

        viewDao.delete(newView);
    }

    @Override
    public View getViewById(Long id) {
        if (id == null) {
            String msg = "ID can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        return EntityDTOconvertor.convertView(viewDao.getById(id));

    }

    @Override
    public List<View> findViewsByName(String name) {
        if (name == null) {
            String msg = "Name can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        List<ViewEntity> views = viewDao.findViewsByName(name);
        List<View> newViews = new ArrayList<>();
        for (ViewEntity u : views) {
            newViews.add(EntityDTOconvertor.convertView(u));
        }
        return newViews;

    }

    @Override
    public View getViewByMap(MyMap myMap) {
        if (myMap == null) {
            String msg = "Map can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        View view = myMap.getView();

        return view;
    }

    @Override
    public Collection<View> getViewsByUser(User user) {
        if (user == null) {
            String msg = "User can not be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        Collection<View> userViews = user.getViews();

        return userViews;
    }
}
