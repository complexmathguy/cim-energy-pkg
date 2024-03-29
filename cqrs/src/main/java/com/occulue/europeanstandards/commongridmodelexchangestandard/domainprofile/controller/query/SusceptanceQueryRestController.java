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
 * Implements Spring Controller query CQRS processing for entity Susceptance.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Susceptance")
public class SusceptanceQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a Susceptance using a UUID
   *
   * @param UUID susceptanceId
   * @return Susceptance
   */
  @GetMapping("/load")
  public Susceptance load(@RequestParam(required = true) UUID susceptanceId) {
    Susceptance entity = null;

    try {
      entity =
          SusceptanceBusinessDelegate.getSusceptanceInstance()
              .getSusceptance(new SusceptanceFetchOneSummary(susceptanceId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load Susceptance using Id " + susceptanceId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all Susceptance business objects
   *
   * @return Set<Susceptance>
   */
  @GetMapping("/")
  public List<Susceptance> loadAll() {
    List<Susceptance> susceptanceList = null;

    try {
      // load the Susceptance
      susceptanceList = SusceptanceBusinessDelegate.getSusceptanceInstance().getAllSusceptance();

      if (susceptanceList != null) LOGGER.log(Level.INFO, "successfully loaded all Susceptances");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all Susceptances ", exc);
      return null;
    }

    return susceptanceList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected Susceptance susceptance = null;
  private static final Logger LOGGER =
      Logger.getLogger(SusceptanceQueryRestController.class.getName());
}
