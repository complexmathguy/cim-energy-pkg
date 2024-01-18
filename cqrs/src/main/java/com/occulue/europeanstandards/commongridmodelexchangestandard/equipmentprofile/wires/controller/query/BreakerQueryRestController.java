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
 * Implements Spring Controller query CQRS processing for entity Breaker.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Breaker")
public class BreakerQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a Breaker using a UUID
   *
   * @param UUID breakerId
   * @return Breaker
   */
  @GetMapping("/load")
  public Breaker load(@RequestParam(required = true) UUID breakerId) {
    Breaker entity = null;

    try {
      entity =
          BreakerBusinessDelegate.getBreakerInstance()
              .getBreaker(new BreakerFetchOneSummary(breakerId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load Breaker using Id " + breakerId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all Breaker business objects
   *
   * @return Set<Breaker>
   */
  @GetMapping("/")
  public List<Breaker> loadAll() {
    List<Breaker> breakerList = null;

    try {
      // load the Breaker
      breakerList = BreakerBusinessDelegate.getBreakerInstance().getAllBreaker();

      if (breakerList != null) LOGGER.log(Level.INFO, "successfully loaded all Breakers");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all Breakers ", exc);
      return null;
    }

    return breakerList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected Breaker breaker = null;
  private static final Logger LOGGER = Logger.getLogger(BreakerQueryRestController.class.getName());
}
