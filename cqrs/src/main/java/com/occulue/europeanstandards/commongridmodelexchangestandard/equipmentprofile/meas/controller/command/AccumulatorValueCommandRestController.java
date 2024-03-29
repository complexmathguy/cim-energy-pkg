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
package com.occulue.controller.command;

import com.occulue.api.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller command CQRS processing for entity AccumulatorValue.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AccumulatorValue")
public class AccumulatorValueCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a AccumulatorValue. if not key provided, calls create, otherwise calls save
   *
   * @param AccumulatorValue accumulatorValue
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateAccumulatorValueCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          AccumulatorValueBusinessDelegate.getAccumulatorValueInstance()
              .createAccumulatorValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a AccumulatorValue. if no key provided, calls create, otherwise calls save
   *
   * @param AccumulatorValue accumulatorValue
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateAccumulatorValueCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateAccumulatorValueCommand
      // -----------------------------------------------
      completableFuture =
          AccumulatorValueBusinessDelegate.getAccumulatorValueInstance()
              .updateAccumulatorValue(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "AccumulatorValueController:update() - successfully update AccumulatorValue - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a AccumulatorValue entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID accumulatorValueId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteAccumulatorValueCommand command = new DeleteAccumulatorValueCommand(accumulatorValueId);

    try {
      AccumulatorValueBusinessDelegate delegate =
          AccumulatorValueBusinessDelegate.getAccumulatorValueInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted AccumulatorValue with key " + command.getAccumulatorValueId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Value on AccumulatorValue
   *
   * @param command AssignValueToAccumulatorValueCommand
   */
  @PutMapping("/assignValue")
  public void assignValue(@RequestBody AssignValueToAccumulatorValueCommand command) {
    try {
      AccumulatorValueBusinessDelegate.getAccumulatorValueInstance().assignValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Value", exc);
    }
  }

  /**
   * unassign Value on AccumulatorValue
   *
   * @param command UnAssignValueFromAccumulatorValueCommand
   */
  @PutMapping("/unAssignValue")
  public void unAssignValue(
      @RequestBody(required = true) UnAssignValueFromAccumulatorValueCommand command) {
    try {
      AccumulatorValueBusinessDelegate.getAccumulatorValueInstance().unAssignValue(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Value", exc);
    }
  }

  /**
   * save AccumulatorValues on AccumulatorValue
   *
   * @param command AssignAccumulatorValuesToAccumulatorValueCommand
   */
  @PutMapping("/addToAccumulatorValues")
  public void addToAccumulatorValues(
      @RequestBody(required = true) AssignAccumulatorValuesToAccumulatorValueCommand command) {
    try {
      AccumulatorValueBusinessDelegate.getAccumulatorValueInstance()
          .addToAccumulatorValues(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to add to Set AccumulatorValues", exc);
    }
  }

  /**
   * remove AccumulatorValues on AccumulatorValue
   *
   * @param command RemoveAccumulatorValuesFromAccumulatorValueCommand
   */
  @PutMapping("/removeFromAccumulatorValues")
  public void removeFromAccumulatorValues(
      @RequestBody(required = true) RemoveAccumulatorValuesFromAccumulatorValueCommand command) {
    try {
      AccumulatorValueBusinessDelegate.getAccumulatorValueInstance()
          .removeFromAccumulatorValues(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to remove from Set AccumulatorValues", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected AccumulatorValue accumulatorValue = null;
  private static final Logger LOGGER =
      Logger.getLogger(AccumulatorValueCommandRestController.class.getName());
}
