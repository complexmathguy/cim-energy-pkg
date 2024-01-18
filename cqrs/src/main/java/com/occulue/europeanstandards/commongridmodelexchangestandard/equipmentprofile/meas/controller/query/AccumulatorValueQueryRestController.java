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
 * Implements Spring Controller query CQRS processing for entity AccumulatorValue.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/AccumulatorValue")
public class AccumulatorValueQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a AccumulatorValue using a UUID
   *
   * @param UUID accumulatorValueId
   * @return AccumulatorValue
   */
  @GetMapping("/load")
  public AccumulatorValue load(@RequestParam(required = true) UUID accumulatorValueId) {
    AccumulatorValue entity = null;

    try {
      entity =
          AccumulatorValueBusinessDelegate.getAccumulatorValueInstance()
              .getAccumulatorValue(new AccumulatorValueFetchOneSummary(accumulatorValueId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load AccumulatorValue using Id " + accumulatorValueId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all AccumulatorValue business objects
   *
   * @return Set<AccumulatorValue>
   */
  @GetMapping("/")
  public List<AccumulatorValue> loadAll() {
    List<AccumulatorValue> accumulatorValueList = null;

    try {
      // load the AccumulatorValue
      accumulatorValueList =
          AccumulatorValueBusinessDelegate.getAccumulatorValueInstance().getAllAccumulatorValue();

      if (accumulatorValueList != null)
        LOGGER.log(Level.INFO, "successfully loaded all AccumulatorValues");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all AccumulatorValues ", exc);
      return null;
    }

    return accumulatorValueList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected AccumulatorValue accumulatorValue = null;
  private static final Logger LOGGER =
      Logger.getLogger(AccumulatorValueQueryRestController.class.getName());
}