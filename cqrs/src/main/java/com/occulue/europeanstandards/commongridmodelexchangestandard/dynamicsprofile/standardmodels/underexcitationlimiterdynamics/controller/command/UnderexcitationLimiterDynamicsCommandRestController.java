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
 * Implements Spring Controller command CQRS processing for entity UnderexcitationLimiterDynamics.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/UnderexcitationLimiterDynamics")
public class UnderexcitationLimiterDynamicsCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a UnderexcitationLimiterDynamics. if not key provided, calls create, otherwise
   * calls save
   *
   * @param UnderexcitationLimiterDynamics underexcitationLimiterDynamics
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateUnderexcitationLimiterDynamicsCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          UnderexcitationLimiterDynamicsBusinessDelegate.getUnderexcitationLimiterDynamicsInstance()
              .createUnderexcitationLimiterDynamics(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a UnderexcitationLimiterDynamics. if no key provided, calls create, otherwise
   * calls save
   *
   * @param UnderexcitationLimiterDynamics underexcitationLimiterDynamics
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateUnderexcitationLimiterDynamicsCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateUnderexcitationLimiterDynamicsCommand
      // -----------------------------------------------
      completableFuture =
          UnderexcitationLimiterDynamicsBusinessDelegate.getUnderexcitationLimiterDynamicsInstance()
              .updateUnderexcitationLimiterDynamics(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "UnderexcitationLimiterDynamicsController:update() - successfully update UnderexcitationLimiterDynamics - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a UnderexcitationLimiterDynamics entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(
      @RequestParam(required = true) UUID underexcitationLimiterDynamicsId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteUnderexcitationLimiterDynamicsCommand command =
        new DeleteUnderexcitationLimiterDynamicsCommand(underexcitationLimiterDynamicsId);

    try {
      UnderexcitationLimiterDynamicsBusinessDelegate delegate =
          UnderexcitationLimiterDynamicsBusinessDelegate
              .getUnderexcitationLimiterDynamicsInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted UnderexcitationLimiterDynamics with key "
              + command.getUnderexcitationLimiterDynamicsId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save UnderexcitationLimiterDynamics on UnderexcitationLimiterDynamics
   *
   * @param command AssignUnderexcitationLimiterDynamicsToUnderexcitationLimiterDynamicsCommand
   */
  @PutMapping("/assignUnderexcitationLimiterDynamics")
  public void assignUnderexcitationLimiterDynamics(
      @RequestBody
          AssignUnderexcitationLimiterDynamicsToUnderexcitationLimiterDynamicsCommand command) {
    try {
      UnderexcitationLimiterDynamicsBusinessDelegate.getUnderexcitationLimiterDynamicsInstance()
          .assignUnderexcitationLimiterDynamics(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign UnderexcitationLimiterDynamics", exc);
    }
  }

  /**
   * unassign UnderexcitationLimiterDynamics on UnderexcitationLimiterDynamics
   *
   * @param command UnAssignUnderexcitationLimiterDynamicsFromUnderexcitationLimiterDynamicsCommand
   */
  @PutMapping("/unAssignUnderexcitationLimiterDynamics")
  public void unAssignUnderexcitationLimiterDynamics(
      @RequestBody(required = true)
          UnAssignUnderexcitationLimiterDynamicsFromUnderexcitationLimiterDynamicsCommand command) {
    try {
      UnderexcitationLimiterDynamicsBusinessDelegate.getUnderexcitationLimiterDynamicsInstance()
          .unAssignUnderexcitationLimiterDynamics(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign UnderexcitationLimiterDynamics", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected UnderexcitationLimiterDynamics underexcitationLimiterDynamics = null;
  private static final Logger LOGGER =
      Logger.getLogger(UnderexcitationLimiterDynamicsCommandRestController.class.getName());
}
