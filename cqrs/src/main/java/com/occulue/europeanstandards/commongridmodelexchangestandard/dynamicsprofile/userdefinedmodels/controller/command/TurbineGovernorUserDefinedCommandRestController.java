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
 * Implements Spring Controller command CQRS processing for entity TurbineGovernorUserDefined.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/TurbineGovernorUserDefined")
public class TurbineGovernorUserDefinedCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a TurbineGovernorUserDefined. if not key provided, calls create, otherwise calls
   * save
   *
   * @param TurbineGovernorUserDefined turbineGovernorUserDefined
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateTurbineGovernorUserDefinedCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance()
              .createTurbineGovernorUserDefined(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a TurbineGovernorUserDefined. if no key provided, calls create, otherwise
   * calls save
   *
   * @param TurbineGovernorUserDefined turbineGovernorUserDefined
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateTurbineGovernorUserDefinedCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateTurbineGovernorUserDefinedCommand
      // -----------------------------------------------
      completableFuture =
          TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance()
              .updateTurbineGovernorUserDefined(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "TurbineGovernorUserDefinedController:update() - successfully update TurbineGovernorUserDefined - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a TurbineGovernorUserDefined entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(
      @RequestParam(required = true) UUID turbineGovernorUserDefinedId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteTurbineGovernorUserDefinedCommand command =
        new DeleteTurbineGovernorUserDefinedCommand(turbineGovernorUserDefinedId);

    try {
      TurbineGovernorUserDefinedBusinessDelegate delegate =
          TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted TurbineGovernorUserDefined with key "
              + command.getTurbineGovernorUserDefinedId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Proprietary on TurbineGovernorUserDefined
   *
   * @param command AssignProprietaryToTurbineGovernorUserDefinedCommand
   */
  @PutMapping("/assignProprietary")
  public void assignProprietary(
      @RequestBody AssignProprietaryToTurbineGovernorUserDefinedCommand command) {
    try {
      TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance()
          .assignProprietary(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Proprietary", exc);
    }
  }

  /**
   * unassign Proprietary on TurbineGovernorUserDefined
   *
   * @param command UnAssignProprietaryFromTurbineGovernorUserDefinedCommand
   */
  @PutMapping("/unAssignProprietary")
  public void unAssignProprietary(
      @RequestBody(required = true)
          UnAssignProprietaryFromTurbineGovernorUserDefinedCommand command) {
    try {
      TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance()
          .unAssignProprietary(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Proprietary", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected TurbineGovernorUserDefined turbineGovernorUserDefined = null;
  private static final Logger LOGGER =
      Logger.getLogger(TurbineGovernorUserDefinedCommandRestController.class.getName());
}
