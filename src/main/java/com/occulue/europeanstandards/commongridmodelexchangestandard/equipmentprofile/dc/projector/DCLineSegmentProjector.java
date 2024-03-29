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
import java.util.logging.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for DCLineSegment as outlined for the CQRS pattern. All event handling and query
 * handling related to DCLineSegment are invoked here and dispersed as an event to be handled
 * elsewhere.
 *
 * <p>Commands are handled by DCLineSegmentAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("dCLineSegment")
@Component("dCLineSegment-projector")
public class DCLineSegmentProjector extends DCLineSegmentEntityProjector {

  // core constructor
  public DCLineSegmentProjector(
      DCLineSegmentRepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateDCLineSegmentEvent
   */
  @EventHandler(payloadType = CreateDCLineSegmentEvent.class)
  public void handle(CreateDCLineSegmentEvent event) {
    LOGGER.info("handling CreateDCLineSegmentEvent - " + event);
    DCLineSegment entity = new DCLineSegment();
    entity.setDCLineSegmentId(event.getDCLineSegmentId());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event UpdateDCLineSegmentEvent
   */
  @EventHandler(payloadType = UpdateDCLineSegmentEvent.class)
  public void handle(UpdateDCLineSegmentEvent event) {
    LOGGER.info("handling UpdateDCLineSegmentEvent - " + event);

    DCLineSegment entity = new DCLineSegment();
    entity.setDCLineSegmentId(event.getDCLineSegmentId());
    entity.setCapacitance(event.getCapacitance());
    entity.setInductance(event.getInductance());
    entity.setLength(event.getLength());
    entity.setResistance(event.getResistance());
    entity.setDCLineSegments(event.getDCLineSegments());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event DeleteDCLineSegmentEvent
   */
  @EventHandler(payloadType = DeleteDCLineSegmentEvent.class)
  public void handle(DeleteDCLineSegmentEvent event) {
    LOGGER.info("handling DeleteDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    DCLineSegment entity = delete(event.getDCLineSegmentId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event AssignCapacitanceToDCLineSegmentEvent
   */
  @EventHandler(payloadType = AssignCapacitanceToDCLineSegmentEvent.class)
  public void handle(AssignCapacitanceToDCLineSegmentEvent event) {
    LOGGER.info("handling AssignCapacitanceToDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    DCLineSegment entity = assignCapacitance(event.getDCLineSegmentId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event UnAssignCapacitanceFromDCLineSegmentEvent
   */
  @EventHandler(payloadType = UnAssignCapacitanceFromDCLineSegmentEvent.class)
  public void handle(UnAssignCapacitanceFromDCLineSegmentEvent event) {
    LOGGER.info("handling UnAssignCapacitanceFromDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    DCLineSegment entity = unAssignCapacitance(event.getDCLineSegmentId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event AssignInductanceToDCLineSegmentEvent
   */
  @EventHandler(payloadType = AssignInductanceToDCLineSegmentEvent.class)
  public void handle(AssignInductanceToDCLineSegmentEvent event) {
    LOGGER.info("handling AssignInductanceToDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    DCLineSegment entity = assignInductance(event.getDCLineSegmentId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event UnAssignInductanceFromDCLineSegmentEvent
   */
  @EventHandler(payloadType = UnAssignInductanceFromDCLineSegmentEvent.class)
  public void handle(UnAssignInductanceFromDCLineSegmentEvent event) {
    LOGGER.info("handling UnAssignInductanceFromDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    DCLineSegment entity = unAssignInductance(event.getDCLineSegmentId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event AssignLengthToDCLineSegmentEvent
   */
  @EventHandler(payloadType = AssignLengthToDCLineSegmentEvent.class)
  public void handle(AssignLengthToDCLineSegmentEvent event) {
    LOGGER.info("handling AssignLengthToDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    DCLineSegment entity = assignLength(event.getDCLineSegmentId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event UnAssignLengthFromDCLineSegmentEvent
   */
  @EventHandler(payloadType = UnAssignLengthFromDCLineSegmentEvent.class)
  public void handle(UnAssignLengthFromDCLineSegmentEvent event) {
    LOGGER.info("handling UnAssignLengthFromDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    DCLineSegment entity = unAssignLength(event.getDCLineSegmentId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event AssignResistanceToDCLineSegmentEvent
   */
  @EventHandler(payloadType = AssignResistanceToDCLineSegmentEvent.class)
  public void handle(AssignResistanceToDCLineSegmentEvent event) {
    LOGGER.info("handling AssignResistanceToDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    DCLineSegment entity = assignResistance(event.getDCLineSegmentId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event UnAssignResistanceFromDCLineSegmentEvent
   */
  @EventHandler(payloadType = UnAssignResistanceFromDCLineSegmentEvent.class)
  public void handle(UnAssignResistanceFromDCLineSegmentEvent event) {
    LOGGER.info("handling UnAssignResistanceFromDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    DCLineSegment entity = unAssignResistance(event.getDCLineSegmentId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event AssignDCLineSegmentsToDCLineSegmentEvent
   */
  @EventHandler(payloadType = AssignDCLineSegmentsToDCLineSegmentEvent.class)
  public void handle(AssignDCLineSegmentsToDCLineSegmentEvent event) {
    LOGGER.info("handling AssignDCLineSegmentsToDCLineSegmentEvent - " + event);

    // ------------------------------------------
    // delegate to addTo
    // ------------------------------------------
    DCLineSegment entity = addToDCLineSegments(event.getDCLineSegmentId(), event.getAddTo());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /*
   * @param	event RemoveDCLineSegmentsFromDCLineSegmentEvent
   */
  @EventHandler(payloadType = RemoveDCLineSegmentsFromDCLineSegmentEvent.class)
  public void handle(RemoveDCLineSegmentsFromDCLineSegmentEvent event) {
    LOGGER.info("handling RemoveDCLineSegmentsFromDCLineSegmentEvent - " + event);

    DCLineSegment entity =
        removeFromDCLineSegments(event.getDCLineSegmentId(), event.getRemoveFrom());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindDCLineSegment(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllDCLineSegment(entity);
  }

  /**
   * Method to retrieve the DCLineSegment via an DCLineSegmentPrimaryKey.
   *
   * @param id Long
   * @return DCLineSegment
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public DCLineSegment handle(FindDCLineSegmentQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getDCLineSegmentId());
  }

  /**
   * Method to retrieve a collection of all DCLineSegments
   *
   * @param query FindAllDCLineSegmentQuery
   * @return List<DCLineSegment>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<DCLineSegment> handle(FindAllDCLineSegmentQuery query) throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindDCLineSegment, but only if the id matches
   *
   * @param entity DCLineSegment
   */
  protected void emitFindDCLineSegment(DCLineSegment entity) {
    LOGGER.info("handling emitFindDCLineSegment");

    queryUpdateEmitter.emit(
        FindDCLineSegmentQuery.class,
        query -> query.getFilter().getDCLineSegmentId().equals(entity.getDCLineSegmentId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllDCLineSegment
   *
   * @param entity DCLineSegment
   */
  protected void emitFindAllDCLineSegment(DCLineSegment entity) {
    LOGGER.info("handling emitFindAllDCLineSegment");

    queryUpdateEmitter.emit(FindAllDCLineSegmentQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER = Logger.getLogger(DCLineSegmentProjector.class.getName());
}
