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
 * Implements Spring Controller query CQRS processing for entity UnderexcitationLimiterUserDefined.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/UnderexcitationLimiterUserDefined")
public class UnderexcitationLimiterUserDefinedQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a UnderexcitationLimiterUserDefined using a UUID
   *
   * @param UUID underexcitationLimiterUserDefinedId
   * @return UnderexcitationLimiterUserDefined
   */
  @GetMapping("/load")
  public UnderexcitationLimiterUserDefined load(
      @RequestParam(required = true) UUID underexcitationLimiterUserDefinedId) {
    UnderexcitationLimiterUserDefined entity = null;

    try {
      entity =
          UnderexcitationLimiterUserDefinedBusinessDelegate
              .getUnderexcitationLimiterUserDefinedInstance()
              .getUnderexcitationLimiterUserDefined(
                  new UnderexcitationLimiterUserDefinedFetchOneSummary(
                      underexcitationLimiterUserDefinedId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "failed to load UnderexcitationLimiterUserDefined using Id "
              + underexcitationLimiterUserDefinedId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all UnderexcitationLimiterUserDefined business objects
   *
   * @return Set<UnderexcitationLimiterUserDefined>
   */
  @GetMapping("/")
  public List<UnderexcitationLimiterUserDefined> loadAll() {
    List<UnderexcitationLimiterUserDefined> underexcitationLimiterUserDefinedList = null;

    try {
      // load the UnderexcitationLimiterUserDefined
      underexcitationLimiterUserDefinedList =
          UnderexcitationLimiterUserDefinedBusinessDelegate
              .getUnderexcitationLimiterUserDefinedInstance()
              .getAllUnderexcitationLimiterUserDefined();

      if (underexcitationLimiterUserDefinedList != null)
        LOGGER.log(Level.INFO, "successfully loaded all UnderexcitationLimiterUserDefineds");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all UnderexcitationLimiterUserDefineds ", exc);
      return null;
    }

    return underexcitationLimiterUserDefinedList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected UnderexcitationLimiterUserDefined underexcitationLimiterUserDefined = null;
  private static final Logger LOGGER =
      Logger.getLogger(UnderexcitationLimiterUserDefinedQueryRestController.class.getName());
}
