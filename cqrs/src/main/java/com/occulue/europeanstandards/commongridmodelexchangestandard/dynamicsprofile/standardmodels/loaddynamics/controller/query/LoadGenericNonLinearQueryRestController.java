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
 * Implements Spring Controller query CQRS processing for entity LoadGenericNonLinear.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/LoadGenericNonLinear")
public class LoadGenericNonLinearQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a LoadGenericNonLinear using a UUID
   *
   * @param UUID loadGenericNonLinearId
   * @return LoadGenericNonLinear
   */
  @GetMapping("/load")
  public LoadGenericNonLinear load(@RequestParam(required = true) UUID loadGenericNonLinearId) {
    LoadGenericNonLinear entity = null;

    try {
      entity =
          LoadGenericNonLinearBusinessDelegate.getLoadGenericNonLinearInstance()
              .getLoadGenericNonLinear(
                  new LoadGenericNonLinearFetchOneSummary(loadGenericNonLinearId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING, "failed to load LoadGenericNonLinear using Id " + loadGenericNonLinearId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all LoadGenericNonLinear business objects
   *
   * @return Set<LoadGenericNonLinear>
   */
  @GetMapping("/")
  public List<LoadGenericNonLinear> loadAll() {
    List<LoadGenericNonLinear> loadGenericNonLinearList = null;

    try {
      // load the LoadGenericNonLinear
      loadGenericNonLinearList =
          LoadGenericNonLinearBusinessDelegate.getLoadGenericNonLinearInstance()
              .getAllLoadGenericNonLinear();

      if (loadGenericNonLinearList != null)
        LOGGER.log(Level.INFO, "successfully loaded all LoadGenericNonLinears");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all LoadGenericNonLinears ", exc);
      return null;
    }

    return loadGenericNonLinearList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected LoadGenericNonLinear loadGenericNonLinear = null;
  private static final Logger LOGGER =
      Logger.getLogger(LoadGenericNonLinearQueryRestController.class.getName());
}
