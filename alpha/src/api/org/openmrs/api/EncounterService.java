package org.openmrs.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.db.EncounterDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Encounter-related services
 * 
 * @author Ben Wolfe
 * @version 1.0
 */
@Transactional
public interface EncounterService {
	
	public void setEncounterDAO(EncounterDAO dao);
	
	/**
	 * Creates a new encounter
	 * 
	 * @param encounter to be created
	 * @throws APIException
	 */
	public void createEncounter(Encounter encounter) throws APIException;

	/**
	 * Get encounter by internal identifier
	 * 
	 * @param encounterId encounter id
	 * @return encounter with given internal identifier
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public Encounter getEncounter(Integer encounterId) throws APIException;
	
	/**
	 * 
	 * @param identifier
	 * @param includeVoided
	 * @return all encounters for the given patient identifer
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public List<Encounter> getEncountersByPatientIdentifier(String identifier, boolean includeVoided) throws APIException;
	
	/**
	 * 
	 * @param patientId
	 * @param includeVoided
	 * @return all encounters for the given patient identifer
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public List<Encounter> getEncountersByPatientId(Integer patientId, boolean includeVoided) throws APIException;
	
	/**
	 * Get all encounter types
	 * 
	 * @return encounter types list
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public List<EncounterType> getEncounterTypes() throws APIException;

	/**
	 * Get encounterType by internal identifier
	 * 
	 * @param encounterType id
	 * @return encounterType with given internal identifier
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public EncounterType getEncounterType(Integer encounterTypeId) throws APIException;
	
	/**
	 * Get encounterType by name
	 * 
	 * @param encounterType string
	 * @return EncounterType
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public EncounterType getEncounterType(String name) throws APIException;

	/**
	 * Get all locations
	 * 
	 * @return location list
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public List<Location> getLocations() throws APIException;

	/**
	 * Get location by internal identifier
	 * 
	 * @param location id
	 * @return location with given internal identifier
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public Location getLocation(Integer locationId) throws APIException;

	/**
	 * Get location by name
	 * 
	 * @param name location's name
	 * @return location with given name
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public Location getLocationByName(String name) throws APIException;
	
	/**
	 * Search for locations by name.  Matches returned match the given string at 
	 * the beginning of the name
	 * 
	 * @param name location's name
	 * @return list of locations with similar name
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	public List<Location> findLocations(String name) throws APIException;
	
	/**
	 * Save changes to encounter.  
	 * Automatically applys encounter.patient to all encounter.obs.patient
	 * 
	 * @param encounter
	 * @throws APIException
	 */
	public void updateEncounter(Encounter encounter) throws APIException;
	
	/**
	 * Delete encounter from database.
	 * 
	 * For super users only. If dereferencing encounters, use
	 * <code>voidEncounter(org.openmrs.Encounter)</code>
	 * 
	 * @param encounter encounter object to be deleted 
	 */
	public void deleteEncounter(Encounter encounter) throws APIException;
	
	/**
	 * Voiding a encounter essentially removes it from circulation
	 * 
	 * @param Encounter
	 *            encounter
	 * @param String
	 *            reason
	 */
	public void voidEncounter(Encounter encounter, String reason);
	
	/**
	 * Unvoid encounter record 
	 * 
	 * @param encounter encounter to be revived
	 */
	public void unvoidEncounter(Encounter encounter) throws APIException;
	
	/**
	 * All unvoided encounters for a patient
	 * @param who
	 * @return
	 */
	@Transactional(readOnly=true)
	public Set<Encounter> getEncounters(Patient who);
	
	/**
	 * All encounters for a patient
	 * @param who
	 * @param includeVoided
	 * @return
	 */
	@Transactional(readOnly=true)
	public Set<Encounter> getEncounters(Patient who, boolean includeVoided);

	/**
	 * Get all encounters for a patient that took place at a specific location
	 * @param who
	 * @param where
	 * @return
	 */
	@Transactional(readOnly=true)
	public Set<Encounter> getEncounters(Patient who, Location where);

    /**
     * Get all encounters for a patient that took place between fromDate and toDate (both nullable and inclusive)
     * @param who
     * @param fromDate
     * @param toDate
     * @return
     */
	@Transactional(readOnly=true)
	public Set<Encounter> getEncounters(Patient who, Date fromDate, Date toDate);
    
    /**
     * Get all encounters that took place between fromDate and toDate (both nullable and inclusive)
     * @param fromDate
     * @param toDate
     * @return
     */
	@Transactional(readOnly=true)
	public Collection<Encounter> getEncounters(Date fromDate, Date toDate);
	
    /**
     * Get all encounters that took place between fromDate and toDate (both nullable and inclusive)
     * at the given location
     * @param loc Location
     * @param fromDate
     * @param toDate
     * @return
     */
	@Transactional(readOnly=true)
	public Collection<Encounter> getEncounters(Location loc, Date fromDate, Date toDate);
}
