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
 * Implements Spring Controller command CQRS processing for entity EnergySource.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/EnergySource")
public class EnergySourceCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a EnergySource. if not key provided, calls create, otherwise calls save
   *
   * @param EnergySource energySource
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateEnergySourceCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          EnergySourceBusinessDelegate.getEnergySourceInstance().createEnergySource(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a EnergySource. if no key provided, calls create, otherwise calls save
   *
   * @param EnergySource energySource
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateEnergySourceCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateEnergySourceCommand
      // -----------------------------------------------
      completableFuture =
          EnergySourceBusinessDelegate.getEnergySourceInstance().updateEnergySource(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "EnergySourceController:update() - successfully update EnergySource - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a EnergySource entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID energySourceId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteEnergySourceCommand command = new DeleteEnergySourceCommand(energySourceId);

    try {
      EnergySourceBusinessDelegate delegate =
          EnergySourceBusinessDelegate.getEnergySourceInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted EnergySource with key " + command.getEnergySourceId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save EnergySource on EnergySource
   *
   * @param command AssignEnergySourceToEnergySourceCommand
   */
  @PutMapping("/addToEnergySource")
  public void addToEnergySource(
      @RequestBody(required = true) AssignEnergySourceToEnergySourceCommand command) {
    try {
      EnergySourceBusinessDelegate.getEnergySourceInstance().addToEnergySource(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to add to Set EnergySource", exc);
    }
  }

  /**
   * remove EnergySource on EnergySource
   *
   * @param command RemoveEnergySourceFromEnergySourceCommand
   */
  @PutMapping("/removeFromEnergySource")
  public void removeFromEnergySource(
      @RequestBody(required = true) RemoveEnergySourceFromEnergySourceCommand command) {
    try {
      EnergySourceBusinessDelegate.getEnergySourceInstance().removeFromEnergySource(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to remove from Set EnergySource", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected EnergySource energySource = null;
  private static final Logger LOGGER =
      Logger.getLogger(EnergySourceCommandRestController.class.getName());
}
