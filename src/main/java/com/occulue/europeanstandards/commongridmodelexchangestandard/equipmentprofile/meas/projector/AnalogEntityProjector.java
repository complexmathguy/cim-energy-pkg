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
 * Projector for Analog as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by AnalogAggregate
 *
 * @author your_name_here
 */
@Component("analog-entity-projector")
public class AnalogEntityProjector {

  // core constructor
  public AnalogEntityProjector(AnalogRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a Analog
   *
   * @param	entity Analog
   */
  public Analog create(Analog entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a Analog
   *
   * @param	entity Analog
   */
  public Analog update(Analog entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a Analog
   *
   * @param	id		UUID
   */
  public Analog delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    Analog entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /*
   * Assign a PositiveFlowIn
   *
   * @param	parentId	UUID
   * @param	assignment 	BooleanProxy
   * @return	Analog
   */
  public Analog assignPositiveFlowIn(UUID parentId, BooleanProxy assignment) {
    LOGGER.info("assigning PositiveFlowIn as " + assignment.toString());

    Analog parentEntity = repository.findById(parentId).get();
    assignment = BooleanProxyProjector.find(assignment.getBooleanProxyId());

    // ------------------------------------------
    // assign the PositiveFlowIn to the parent entity
    // ------------------------------------------
    parentEntity.setPositiveFlowIn(assignment);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /*
   * Unassign the PositiveFlowIn
   *
   * @param	parentId		UUID
   * @return	Analog
   */
  public Analog unAssignPositiveFlowIn(UUID parentId) {
    Analog parentEntity = repository.findById(parentId).get();

    LOGGER.info("unAssigning PositiveFlowIn on " + parentEntity.toString());

    // ------------------------------------------
    // null out the PositiveFlowIn on the parent entithy
    // ------------------------------------------
    parentEntity.setPositiveFlowIn(null);

    // ------------------------------------------
    // save the parent entity
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /**
   * Method to retrieve the Analog via an FindAnalogQuery
   *
   * @return query FindAnalogQuery
   */
  @SuppressWarnings("unused")
  public Analog find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a Analog - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all Analogs
   *
   * @param query FindAllAnalogQuery
   * @return List<Analog>
   */
  @SuppressWarnings("unused")
  public List<Analog> findAll(FindAllAnalogQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all Analog - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final AnalogRepository repository;

  @Autowired
  @Qualifier(value = "booleanProxy-entity-projector")
  BooleanProxyEntityProjector BooleanProxyProjector;

  private static final Logger LOGGER = Logger.getLogger(AnalogEntityProjector.class.getName());
}
