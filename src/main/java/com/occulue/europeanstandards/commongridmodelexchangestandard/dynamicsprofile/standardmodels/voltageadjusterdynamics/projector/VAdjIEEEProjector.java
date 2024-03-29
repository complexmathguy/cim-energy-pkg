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
 * Projector for VAdjIEEE as outlined for the CQRS pattern. All event handling and query handling
 * related to VAdjIEEE are invoked here and dispersed as an event to be handled elsewhere.
 *
 * <p>Commands are handled by VAdjIEEEAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("vAdjIEEE")
@Component("vAdjIEEE-projector")
public class VAdjIEEEProjector extends VAdjIEEEEntityProjector {

  // core constructor
  public VAdjIEEEProjector(VAdjIEEERepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateVAdjIEEEEvent
   */
  @EventHandler(payloadType = CreateVAdjIEEEEvent.class)
  public void handle(CreateVAdjIEEEEvent event) {
    LOGGER.info("handling CreateVAdjIEEEEvent - " + event);
    VAdjIEEE entity = new VAdjIEEE();
    entity.setVAdjIEEEId(event.getVAdjIEEEId());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UpdateVAdjIEEEEvent
   */
  @EventHandler(payloadType = UpdateVAdjIEEEEvent.class)
  public void handle(UpdateVAdjIEEEEvent event) {
    LOGGER.info("handling UpdateVAdjIEEEEvent - " + event);

    VAdjIEEE entity = new VAdjIEEE();
    entity.setVAdjIEEEId(event.getVAdjIEEEId());
    entity.setAdjslew(event.getAdjslew());
    entity.setTaoff(event.getTaoff());
    entity.setTaon(event.getTaon());
    entity.setVadjf(event.getVadjf());
    entity.setVadjmax(event.getVadjmax());
    entity.setVadjmin(event.getVadjmin());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event DeleteVAdjIEEEEvent
   */
  @EventHandler(payloadType = DeleteVAdjIEEEEvent.class)
  public void handle(DeleteVAdjIEEEEvent event) {
    LOGGER.info("handling DeleteVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    VAdjIEEE entity = delete(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event AssignAdjslewToVAdjIEEEEvent
   */
  @EventHandler(payloadType = AssignAdjslewToVAdjIEEEEvent.class)
  public void handle(AssignAdjslewToVAdjIEEEEvent event) {
    LOGGER.info("handling AssignAdjslewToVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VAdjIEEE entity = assignAdjslew(event.getVAdjIEEEId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UnAssignAdjslewFromVAdjIEEEEvent
   */
  @EventHandler(payloadType = UnAssignAdjslewFromVAdjIEEEEvent.class)
  public void handle(UnAssignAdjslewFromVAdjIEEEEvent event) {
    LOGGER.info("handling UnAssignAdjslewFromVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VAdjIEEE entity = unAssignAdjslew(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event AssignTaoffToVAdjIEEEEvent
   */
  @EventHandler(payloadType = AssignTaoffToVAdjIEEEEvent.class)
  public void handle(AssignTaoffToVAdjIEEEEvent event) {
    LOGGER.info("handling AssignTaoffToVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VAdjIEEE entity = assignTaoff(event.getVAdjIEEEId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UnAssignTaoffFromVAdjIEEEEvent
   */
  @EventHandler(payloadType = UnAssignTaoffFromVAdjIEEEEvent.class)
  public void handle(UnAssignTaoffFromVAdjIEEEEvent event) {
    LOGGER.info("handling UnAssignTaoffFromVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VAdjIEEE entity = unAssignTaoff(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event AssignTaonToVAdjIEEEEvent
   */
  @EventHandler(payloadType = AssignTaonToVAdjIEEEEvent.class)
  public void handle(AssignTaonToVAdjIEEEEvent event) {
    LOGGER.info("handling AssignTaonToVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VAdjIEEE entity = assignTaon(event.getVAdjIEEEId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UnAssignTaonFromVAdjIEEEEvent
   */
  @EventHandler(payloadType = UnAssignTaonFromVAdjIEEEEvent.class)
  public void handle(UnAssignTaonFromVAdjIEEEEvent event) {
    LOGGER.info("handling UnAssignTaonFromVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VAdjIEEE entity = unAssignTaon(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event AssignVadjfToVAdjIEEEEvent
   */
  @EventHandler(payloadType = AssignVadjfToVAdjIEEEEvent.class)
  public void handle(AssignVadjfToVAdjIEEEEvent event) {
    LOGGER.info("handling AssignVadjfToVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VAdjIEEE entity = assignVadjf(event.getVAdjIEEEId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UnAssignVadjfFromVAdjIEEEEvent
   */
  @EventHandler(payloadType = UnAssignVadjfFromVAdjIEEEEvent.class)
  public void handle(UnAssignVadjfFromVAdjIEEEEvent event) {
    LOGGER.info("handling UnAssignVadjfFromVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VAdjIEEE entity = unAssignVadjf(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event AssignVadjmaxToVAdjIEEEEvent
   */
  @EventHandler(payloadType = AssignVadjmaxToVAdjIEEEEvent.class)
  public void handle(AssignVadjmaxToVAdjIEEEEvent event) {
    LOGGER.info("handling AssignVadjmaxToVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VAdjIEEE entity = assignVadjmax(event.getVAdjIEEEId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UnAssignVadjmaxFromVAdjIEEEEvent
   */
  @EventHandler(payloadType = UnAssignVadjmaxFromVAdjIEEEEvent.class)
  public void handle(UnAssignVadjmaxFromVAdjIEEEEvent event) {
    LOGGER.info("handling UnAssignVadjmaxFromVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VAdjIEEE entity = unAssignVadjmax(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event AssignVadjminToVAdjIEEEEvent
   */
  @EventHandler(payloadType = AssignVadjminToVAdjIEEEEvent.class)
  public void handle(AssignVadjminToVAdjIEEEEvent event) {
    LOGGER.info("handling AssignVadjminToVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VAdjIEEE entity = assignVadjmin(event.getVAdjIEEEId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /*
   * @param	event UnAssignVadjminFromVAdjIEEEEvent
   */
  @EventHandler(payloadType = UnAssignVadjminFromVAdjIEEEEvent.class)
  public void handle(UnAssignVadjminFromVAdjIEEEEvent event) {
    LOGGER.info("handling UnAssignVadjminFromVAdjIEEEEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VAdjIEEE entity = unAssignVadjmin(event.getVAdjIEEEId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVAdjIEEE(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVAdjIEEE(entity);
  }

  /**
   * Method to retrieve the VAdjIEEE via an VAdjIEEEPrimaryKey.
   *
   * @param id Long
   * @return VAdjIEEE
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public VAdjIEEE handle(FindVAdjIEEEQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getVAdjIEEEId());
  }

  /**
   * Method to retrieve a collection of all VAdjIEEEs
   *
   * @param query FindAllVAdjIEEEQuery
   * @return List<VAdjIEEE>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<VAdjIEEE> handle(FindAllVAdjIEEEQuery query) throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindVAdjIEEE, but only if the id matches
   *
   * @param entity VAdjIEEE
   */
  protected void emitFindVAdjIEEE(VAdjIEEE entity) {
    LOGGER.info("handling emitFindVAdjIEEE");

    queryUpdateEmitter.emit(
        FindVAdjIEEEQuery.class,
        query -> query.getFilter().getVAdjIEEEId().equals(entity.getVAdjIEEEId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllVAdjIEEE
   *
   * @param entity VAdjIEEE
   */
  protected void emitFindAllVAdjIEEE(VAdjIEEE entity) {
    LOGGER.info("handling emitFindAllVAdjIEEE");

    queryUpdateEmitter.emit(FindAllVAdjIEEEQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER = Logger.getLogger(VAdjIEEEProjector.class.getName());
}
