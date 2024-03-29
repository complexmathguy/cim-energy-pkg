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
 * Implements Spring Controller query CQRS processing for entity AsynchronousMachine.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AsynchronousMachine")
public class AsynchronousMachineQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a AsynchronousMachine using a UUID
   *
   * @param UUID asynchronousMachineId
   * @return AsynchronousMachine
   */
  @GetMapping("/load")
  public AsynchronousMachine load(@RequestParam(required = true) UUID asynchronousMachineId) {
    AsynchronousMachine entity = null;

    try {
      entity =
          AsynchronousMachineBusinessDelegate.getAsynchronousMachineInstance()
              .getAsynchronousMachine(
                  new AsynchronousMachineFetchOneSummary(asynchronousMachineId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING, "failed to load AsynchronousMachine using Id " + asynchronousMachineId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all AsynchronousMachine business objects
   *
   * @return Set<AsynchronousMachine>
   */
  @GetMapping("/")
  public List<AsynchronousMachine> loadAll() {
    List<AsynchronousMachine> asynchronousMachineList = null;

    try {
      // load the AsynchronousMachine
      asynchronousMachineList =
          AsynchronousMachineBusinessDelegate.getAsynchronousMachineInstance()
              .getAllAsynchronousMachine();

      if (asynchronousMachineList != null)
        LOGGER.log(Level.INFO, "successfully loaded all AsynchronousMachines");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all AsynchronousMachines ", exc);
      return null;
    }

    return asynchronousMachineList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected AsynchronousMachine asynchronousMachine = null;
  private static final Logger LOGGER =
      Logger.getLogger(AsynchronousMachineQueryRestController.class.getName());
}
