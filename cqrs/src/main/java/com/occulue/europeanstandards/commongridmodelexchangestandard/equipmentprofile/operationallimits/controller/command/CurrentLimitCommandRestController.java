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
 * Implements Spring Controller command CQRS processing for entity CurrentLimit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/CurrentLimit")
public class CurrentLimitCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a CurrentLimit. if not key provided, calls create, otherwise calls save
   *
   * @param CurrentLimit currentLimit
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateCurrentLimitCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          CurrentLimitBusinessDelegate.getCurrentLimitInstance().createCurrentLimit(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a CurrentLimit. if no key provided, calls create, otherwise calls save
   *
   * @param CurrentLimit currentLimit
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateCurrentLimitCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateCurrentLimitCommand
      // -----------------------------------------------
      completableFuture =
          CurrentLimitBusinessDelegate.getCurrentLimitInstance().updateCurrentLimit(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "CurrentLimitController:update() - successfully update CurrentLimit - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a CurrentLimit entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID currentLimitId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteCurrentLimitCommand command = new DeleteCurrentLimitCommand(currentLimitId);

    try {
      CurrentLimitBusinessDelegate delegate =
          CurrentLimitBusinessDelegate.getCurrentLimitInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted CurrentLimit with key " + command.getCurrentLimitId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Value on CurrentLimit
   *
   * @param command AssignValueToCurrentLimitCommand
   */
  @PutMapping("/assignValue")
  public void assignValue(@RequestBody AssignValueToCurrentLimitCommand command) {
    try {
      CurrentLimitBusinessDelegate.getCurrentLimitInstance().assignValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Value", exc);
    }
  }

  /**
   * unassign Value on CurrentLimit
   *
   * @param command UnAssignValueFromCurrentLimitCommand
   */
  @PutMapping("/unAssignValue")
  public void unAssignValue(
      @RequestBody(required = true) UnAssignValueFromCurrentLimitCommand command) {
    try {
      CurrentLimitBusinessDelegate.getCurrentLimitInstance().unAssignValue(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Value", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected CurrentLimit currentLimit = null;
  private static final Logger LOGGER =
      Logger.getLogger(CurrentLimitCommandRestController.class.getName());
}
