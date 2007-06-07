package org.openmrs.reporting.db.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.DAOException;
import org.openmrs.reporting.AbstractReportObject;
import org.openmrs.reporting.ReportObjectWrapper;
import org.openmrs.reporting.db.ReportObjectDAO;

public class HibernateReportObjectDAO implements
		ReportObjectDAO {

	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;
	
	public HibernateReportObjectDAO() { }

	/**
	 * Set session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) { 
		this.sessionFactory = sessionFactory;
	}
	
	public List<AbstractReportObject> getAllReportObjects() {
		List<AbstractReportObject> reportObjects = new Vector<AbstractReportObject>();
		List<ReportObjectWrapper> wrappedObjects = new Vector<ReportObjectWrapper>();
		wrappedObjects.addAll((ArrayList<ReportObjectWrapper>)sessionFactory.getCurrentSession().createQuery("from ReportObjectWrapper order by date_created, name").list());
		for ( ReportObjectWrapper wrappedObject : wrappedObjects ) {
			AbstractReportObject reportObject = (AbstractReportObject)wrappedObject.getReportObject();
			if ( reportObject.getReportObjectId() == null ) {
				reportObject.setReportObjectId(wrappedObject.getReportObjectId());
			}
			reportObjects.add(reportObject);
		}
		return reportObjects;
	}

	public AbstractReportObject getReportObject(Integer reportObjId) throws DAOException {
		ReportObjectWrapper wrappedReportObject = new ReportObjectWrapper();
		wrappedReportObject = (ReportObjectWrapper)sessionFactory.getCurrentSession().get(ReportObjectWrapper.class, reportObjId);
		
		if (wrappedReportObject == null)
			return null;
		
		AbstractReportObject reportObject = wrappedReportObject.getReportObject();
		if ( reportObject.getReportObjectId() == null )
			reportObject.setReportObjectId(wrappedReportObject.getReportObjectId());
		
		return reportObject;
	}

	public Integer createReportObject(AbstractReportObject reportObj) throws DAOException {
		reportObj.setCreator(Context.getAuthenticatedUser());
		reportObj.setDateCreated(new Date());
		log.debug("Saving: " + reportObj);
		
		ReportObjectWrapper wrappedReportObject = new ReportObjectWrapper(reportObj);
		sessionFactory.getCurrentSession().save(wrappedReportObject);
		return wrappedReportObject.getReportObjectId();
	}

	public void deleteReportObject(AbstractReportObject reportObj) throws DAOException {
		ReportObjectWrapper wrappedReportObject = new ReportObjectWrapper(reportObj);		
		
		sessionFactory.getCurrentSession().delete(wrappedReportObject);
	}

	public void updateReportObject(AbstractReportObject reportObj) throws DAOException {
		if (reportObj.getReportObjectId() == null)
			createReportObject(reportObj);
		else {
			ReportObjectWrapper wrappedReportObject = new ReportObjectWrapper(reportObj);		

			//wrappedReportObject = (ReportObjectWrapper)sessionFactory.getCurrentSession().merge(wrappedReportObject);
			sessionFactory.getCurrentSession().update(wrappedReportObject);
		}
	}

	public List<AbstractReportObject> getReportObjectsByType(String reportObjectType) throws DAOException {
		List<AbstractReportObject> reportObjects = new Vector<AbstractReportObject>();
		List<ReportObjectWrapper> wrappedObjects = new Vector<ReportObjectWrapper>();
		Query query = sessionFactory.getCurrentSession().createQuery("from ReportObjectWrapper ro where ro.type=:type order by date_created, name");
		query.setString("type", reportObjectType);
		wrappedObjects.addAll((ArrayList<ReportObjectWrapper>)query.list());
		for ( ReportObjectWrapper wrappedObject : wrappedObjects ) {
			AbstractReportObject reportObject = (AbstractReportObject)wrappedObject.getReportObject();
			if ( reportObject.getReportObjectId() == null ) {
				reportObject.setReportObjectId(wrappedObject.getReportObjectId());
			}
			reportObjects.add(reportObject);
		}
		return reportObjects;
	}
}
