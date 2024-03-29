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
 * Implements Spring Controller command CQRS processing for entity RegulationSchedule.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/RegulationSchedule")
public class RegulationScheduleCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a RegulationSchedule. if not key provided, calls create, otherwise calls save
   *
   * @param RegulationSchedule regulationSchedule
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateRegulationScheduleCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          RegulationScheduleBusinessDelegate.getRegulationScheduleInstance()
              .createRegulationSchedule(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a RegulationSchedule. if no key provided, calls create, otherwise calls save
   *
   * @param RegulationSchedule regulationSchedule
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateRegulationScheduleCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateRegulationScheduleCommand
      // -----------------------------------------------
      completableFuture =
          RegulationScheduleBusinessDelegate.getRegulationScheduleInstance()
              .updateRegulationSchedule(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "RegulationScheduleController:update() - successfully update RegulationSchedule - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a RegulationSchedule entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID regulationScheduleId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteRegulationScheduleCommand command =
        new DeleteRegulationScheduleCommand(regulationScheduleId);

    try {
      RegulationScheduleBusinessDelegate delegate =
          RegulationScheduleBusinessDelegate.getRegulationScheduleInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted RegulationSchedule with key " + command.getRegulationScheduleId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save RegulationSchedule on RegulationSchedule
   *
   * @param command AssignRegulationScheduleToRegulationScheduleCommand
   */
  @PutMapping("/addToRegulationSchedule")
  public void addToRegulationSchedule(
      @RequestBody(required = true) AssignRegulationScheduleToRegulationScheduleCommand command) {
    try {
      RegulationScheduleBusinessDelegate.getRegulationScheduleInstance()
          .addToRegulationSchedule(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to add to Set RegulationSchedule", exc);
    }
  }

  /**
   * remove RegulationSchedule on RegulationSchedule
   *
   * @param command RemoveRegulationScheduleFromRegulationScheduleCommand
   */
  @PutMapping("/removeFromRegulationSchedule")
  public void removeFromRegulationSchedule(
      @RequestBody(required = true) RemoveRegulationScheduleFromRegulationScheduleCommand command) {
    try {
      RegulationScheduleBusinessDelegate.getRegulationScheduleInstance()
          .removeFromRegulationSchedule(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to remove from Set RegulationSchedule", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected RegulationSchedule regulationSchedule = null;
  private static final Logger LOGGER =
      Logger.getLogger(RegulationScheduleCommandRestController.class.getName());
}
