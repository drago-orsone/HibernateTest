package it.csttech.hibernatetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import java.io.Serializable;
import java.util.Arrays;
import org.hibernate.type.Type;

public class LoggingInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	
	static final Logger log = LogManager.getLogger(ManageBooks.class.getName());
	
	public LoggingInterceptor() {
		super();
	}

	@Override
    public boolean onFlushDirty(
        Object entity,
        Serializable id,
        Object[] currentState,
        Object[] previousState,
        String[] propertyNames,
        Type[] types) {
		log.debug( "Entity " + 
                entity.getClass().getSimpleName() + "#" +
                id + " changed from " +
                Arrays.toString( previousState ) + " to " +
                Arrays.toString( currentState ));
		return super.onFlushDirty( entity, id, currentState,
				previousState, propertyNames, types);
    }
}
