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
package com.occulue.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity
 * AsynchronousMachineEquivalentCircuit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AsynchronousMachineEquivalentCircuit")
public class AsynchronousMachineEquivalentCircuitQueryRestController
    extends BaseSpringRestController {

  /**
   * Handles loading a AsynchronousMachineEquivalentCircuit using a UUID
   *
   * @param UUID asynchronousMachineEquivalentCircuitId
   * @return AsynchronousMachineEquivalentCircuit
   */
  @GetMapping("/load")
  public AsynchronousMachineEquivalentCircuit load(
      @RequestParam(required = true) UUID asynchronousMachineEquivalentCircuitId) {
    AsynchronousMachineEquivalentCircuit entity = null;

    try {
      entity =
          AsynchronousMachineEquivalentCircuitBusinessDelegate
              .getAsynchronousMachineEquivalentCircuitInstance()
              .getAsynchronousMachineEquivalentCircuit(
                  new AsynchronousMachineEquivalentCircuitFetchOneSummary(
                      asynchronousMachineEquivalentCircuitId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "failed to load AsynchronousMachineEquivalentCircuit using Id "
              + asynchronousMachineEquivalentCircuitId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all AsynchronousMachineEquivalentCircuit business objects
   *
   * @return Set<AsynchronousMachineEquivalentCircuit>
   */
  @GetMapping("/")
  public List<AsynchronousMachineEquivalentCircuit> loadAll() {
    List<AsynchronousMachineEquivalentCircuit> asynchronousMachineEquivalentCircuitList = null;

    try {
      // load the AsynchronousMachineEquivalentCircuit
      asynchronousMachineEquivalentCircuitList =
          AsynchronousMachineEquivalentCircuitBusinessDelegate
              .getAsynchronousMachineEquivalentCircuitInstance()
              .getAllAsynchronousMachineEquivalentCircuit();

      if (asynchronousMachineEquivalentCircuitList != null)
        LOGGER.log(Level.INFO, "successfully loaded all AsynchronousMachineEquivalentCircuits");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all AsynchronousMachineEquivalentCircuits ", exc);
      return null;
    }

    return asynchronousMachineEquivalentCircuitList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected AsynchronousMachineEquivalentCircuit asynchronousMachineEquivalentCircuit = null;
  private static final Logger LOGGER =
      Logger.getLogger(AsynchronousMachineEquivalentCircuitQueryRestController.class.getName());
}
