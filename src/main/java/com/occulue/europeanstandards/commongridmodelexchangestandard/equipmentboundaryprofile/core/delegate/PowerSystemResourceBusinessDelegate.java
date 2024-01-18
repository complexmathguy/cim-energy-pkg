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
package com.occulue.delegate;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * PowerSystemResource business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of PowerSystemResource related services in the case of a
 *       PowerSystemResource business related service failing.
 *   <li>Exposes a simpler, uniform PowerSystemResource interface to the business tier, making it
 *       easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill PowerSystemResource
 *       business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class PowerSystemResourceBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public PowerSystemResourceBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * PowerSystemResource Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return PowerSystemResourceBusinessDelegate
   */
  public static PowerSystemResourceBusinessDelegate getPowerSystemResourceInstance() {
    return (new PowerSystemResourceBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createPowerSystemResource(CreatePowerSystemResourceCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getPowerSystemResourceId() == null)
        command.setPowerSystemResourceId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      PowerSystemResourceValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreatePowerSystemResourceCommand - by convention the future return value for a
      // create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreatePowerSystemResourceCommand of PowerSystemResource is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create PowerSystemResource - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdatePowerSystemResourceCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updatePowerSystemResource(UpdatePowerSystemResourceCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      PowerSystemResourceValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdatePowerSystemResourceCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save PowerSystemResource - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeletePowerSystemResourceCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeletePowerSystemResourceCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      PowerSystemResourceValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeletePowerSystemResourceCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete PowerSystemResource using Id = " + command.getPowerSystemResourceId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the PowerSystemResource via PowerSystemResourceFetchOneSummary
   *
   * @param summary PowerSystemResourceFetchOneSummary
   * @return PowerSystemResourceFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public PowerSystemResource getPowerSystemResource(PowerSystemResourceFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("PowerSystemResourceFetchOneSummary arg cannot be null");

    PowerSystemResource entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      PowerSystemResourceValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a PowerSystemResource
      // --------------------------------------
      CompletableFuture<PowerSystemResource> futureEntity =
          queryGateway.query(
              new FindPowerSystemResourceQuery(
                  new LoadPowerSystemResourceFilter(summary.getPowerSystemResourceId())),
              ResponseTypes.instanceOf(PowerSystemResource.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate PowerSystemResource with id " + summary.getPowerSystemResourceId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all PowerSystemResources
   *
   * @return List<PowerSystemResource>
   * @exception ProcessingException Thrown if any problems
   */
  public List<PowerSystemResource> getAllPowerSystemResource() throws ProcessingException {
    List<PowerSystemResource> list = null;

    try {
      CompletableFuture<List<PowerSystemResource>> futureList =
          queryGateway.query(
              new FindAllPowerSystemResourceQuery(),
              ResponseTypes.multipleInstancesOf(PowerSystemResource.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all PowerSystemResource";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return PowerSystemResource
   */
  private PowerSystemResource load(UUID id) throws ProcessingException {
    powerSystemResource =
        PowerSystemResourceBusinessDelegate.getPowerSystemResourceInstance()
            .getPowerSystemResource(new PowerSystemResourceFetchOneSummary(id));
    return powerSystemResource;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private PowerSystemResource powerSystemResource = null;
  private static final Logger LOGGER =
      Logger.getLogger(PowerSystemResourceBusinessDelegate.class.getName());
}
