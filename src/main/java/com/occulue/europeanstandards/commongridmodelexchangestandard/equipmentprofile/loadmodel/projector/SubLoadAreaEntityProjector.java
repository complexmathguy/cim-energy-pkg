/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Projector for SubLoadArea as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by SubLoadAreaAggregate
 *
 * @author your_name_here
 */
@Component("subLoadArea-entity-projector")
public class SubLoadAreaEntityProjector {

  // core constructor
  public SubLoadAreaEntityProjector(SubLoadAreaRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a SubLoadArea
   *
   * @param	entity SubLoadArea
   */
  public SubLoadArea create(SubLoadArea entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a SubLoadArea
   *
   * @param	entity SubLoadArea
   */
  public SubLoadArea update(SubLoadArea entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a SubLoadArea
   *
   * @param	id		UUID
   */
  public SubLoadArea delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    SubLoadArea entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /*
   * Add to the SubLoadAreas
   *
   * @param	parentID	UUID
   * @param	addTo		childType
   * @return	SubLoadArea
   */
  public SubLoadArea addToSubLoadAreas(UUID parentId, SubLoadArea addTo) {
    LOGGER.info("handling AssignSubLoadAreasToSubLoadAreaEvent - ");

    SubLoadArea parentEntity = repository.findById(parentId).get();
    SubLoadArea child = SubLoadAreaProjector.find(addTo.getSubLoadAreaId());

    parentEntity.getSubLoadAreas().add(child);

    // ------------------------------------------
    // save
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /*
   * Remove from the SubLoadAreas
   *
   * @param	parentID	UUID
   * @param	removeFrom	childType
   * @return	SubLoadArea
   */
  public SubLoadArea removeFromSubLoadAreas(UUID parentId, SubLoadArea removeFrom) {
    LOGGER.info("handling RemoveSubLoadAreasFromSubLoadAreaEvent ");

    SubLoadArea parentEntity = repository.findById(parentId).get();
    SubLoadArea child = SubLoadAreaProjector.find(removeFrom.getSubLoadAreaId());

    parentEntity.getSubLoadAreas().remove(child);

    // ------------------------------------------
    // save
    // ------------------------------------------
    update(parentEntity);

    return parentEntity;
  }

  /**
   * Method to retrieve the SubLoadArea via an FindSubLoadAreaQuery
   *
   * @return query FindSubLoadAreaQuery
   */
  @SuppressWarnings("unused")
  public SubLoadArea find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a SubLoadArea - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all SubLoadAreas
   *
   * @param query FindAllSubLoadAreaQuery
   * @return List<SubLoadArea>
   */
  @SuppressWarnings("unused")
  public List<SubLoadArea> findAll(FindAllSubLoadAreaQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all SubLoadArea - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final SubLoadAreaRepository repository;

  @Autowired
  @Qualifier(value = "subLoadArea-entity-projector")
  SubLoadAreaEntityProjector SubLoadAreaProjector;

  private static final Logger LOGGER = Logger.getLogger(SubLoadAreaEntityProjector.class.getName());
}
