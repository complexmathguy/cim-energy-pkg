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
 * Implements Spring Controller command CQRS processing for entity Seconds.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Seconds")
public class SecondsCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a Seconds. if not key provided, calls create, otherwise calls save
   *
   * @param Seconds seconds
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateSecondsCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture = SecondsBusinessDelegate.getSecondsInstance().createSeconds(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a Seconds. if no key provided, calls create, otherwise calls save
   *
   * @param Seconds seconds
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateSecondsCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateSecondsCommand
      // -----------------------------------------------
      completableFuture = SecondsBusinessDelegate.getSecondsInstance().updateSeconds(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "SecondsController:update() - successfully update Seconds - " + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a Seconds entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID secondsId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteSecondsCommand command = new DeleteSecondsCommand(secondsId);

    try {
      SecondsBusinessDelegate delegate = SecondsBusinessDelegate.getSecondsInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(Level.WARNING, "Successfully deleted Seconds with key " + command.getSecondsId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Value on Seconds
   *
   * @param command AssignValueToSecondsCommand
   */
  @PutMapping("/assignValue")
  public void assignValue(@RequestBody AssignValueToSecondsCommand command) {
    try {
      SecondsBusinessDelegate.getSecondsInstance().assignValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Value", exc);
    }
  }

  /**
   * unassign Value on Seconds
   *
   * @param command UnAssignValueFromSecondsCommand
   */
  @PutMapping("/unAssignValue")
  public void unAssignValue(@RequestBody(required = true) UnAssignValueFromSecondsCommand command) {
    try {
      SecondsBusinessDelegate.getSecondsInstance().unAssignValue(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Value", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected Seconds seconds = null;
  private static final Logger LOGGER =
      Logger.getLogger(SecondsCommandRestController.class.getName());
}
