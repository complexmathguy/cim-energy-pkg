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
 * Implements Spring Controller query CQRS processing for entity NonlinearShuntCompensator.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/NonlinearShuntCompensator")
public class NonlinearShuntCompensatorQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a NonlinearShuntCompensator using a UUID
   *
   * @param UUID nonlinearShuntCompensatorId
   * @return NonlinearShuntCompensator
   */
  @GetMapping("/load")
  public NonlinearShuntCompensator load(
      @RequestParam(required = true) UUID nonlinearShuntCompensatorId) {
    NonlinearShuntCompensator entity = null;

    try {
      entity =
          NonlinearShuntCompensatorBusinessDelegate.getNonlinearShuntCompensatorInstance()
              .getNonlinearShuntCompensator(
                  new NonlinearShuntCompensatorFetchOneSummary(nonlinearShuntCompensatorId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "failed to load NonlinearShuntCompensator using Id " + nonlinearShuntCompensatorId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all NonlinearShuntCompensator business objects
   *
   * @return Set<NonlinearShuntCompensator>
   */
  @GetMapping("/")
  public List<NonlinearShuntCompensator> loadAll() {
    List<NonlinearShuntCompensator> nonlinearShuntCompensatorList = null;

    try {
      // load the NonlinearShuntCompensator
      nonlinearShuntCompensatorList =
          NonlinearShuntCompensatorBusinessDelegate.getNonlinearShuntCompensatorInstance()
              .getAllNonlinearShuntCompensator();

      if (nonlinearShuntCompensatorList != null)
        LOGGER.log(Level.INFO, "successfully loaded all NonlinearShuntCompensators");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all NonlinearShuntCompensators ", exc);
      return null;
    }

    return nonlinearShuntCompensatorList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected NonlinearShuntCompensator nonlinearShuntCompensator = null;
  private static final Logger LOGGER =
      Logger.getLogger(NonlinearShuntCompensatorQueryRestController.class.getName());
}
