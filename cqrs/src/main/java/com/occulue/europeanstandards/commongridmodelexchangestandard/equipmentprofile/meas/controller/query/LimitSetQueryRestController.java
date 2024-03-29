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
 * Implements Spring Controller query CQRS processing for entity LimitSet.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/LimitSet")
public class LimitSetQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a LimitSet using a UUID
   *
   * @param UUID limitSetId
   * @return LimitSet
   */
  @GetMapping("/load")
  public LimitSet load(@RequestParam(required = true) UUID limitSetId) {
    LimitSet entity = null;

    try {
      entity =
          LimitSetBusinessDelegate.getLimitSetInstance()
              .getLimitSet(new LimitSetFetchOneSummary(limitSetId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load LimitSet using Id " + limitSetId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all LimitSet business objects
   *
   * @return Set<LimitSet>
   */
  @GetMapping("/")
  public List<LimitSet> loadAll() {
    List<LimitSet> limitSetList = null;

    try {
      // load the LimitSet
      limitSetList = LimitSetBusinessDelegate.getLimitSetInstance().getAllLimitSet();

      if (limitSetList != null) LOGGER.log(Level.INFO, "successfully loaded all LimitSets");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all LimitSets ", exc);
      return null;
    }

    return limitSetList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected LimitSet limitSet = null;
  private static final Logger LOGGER =
      Logger.getLogger(LimitSetQueryRestController.class.getName());
}
