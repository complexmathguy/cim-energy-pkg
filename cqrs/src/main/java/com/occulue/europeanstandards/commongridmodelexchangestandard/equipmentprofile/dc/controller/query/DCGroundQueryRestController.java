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
 * Implements Spring Controller query CQRS processing for entity DCGround.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/DCGround")
public class DCGroundQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a DCGround using a UUID
   *
   * @param UUID dCGroundId
   * @return DCGround
   */
  @GetMapping("/load")
  public DCGround load(@RequestParam(required = true) UUID dCGroundId) {
    DCGround entity = null;

    try {
      entity =
          DCGroundBusinessDelegate.getDCGroundInstance()
              .getDCGround(new DCGroundFetchOneSummary(dCGroundId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load DCGround using Id " + dCGroundId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all DCGround business objects
   *
   * @return Set<DCGround>
   */
  @GetMapping("/")
  public List<DCGround> loadAll() {
    List<DCGround> dCGroundList = null;

    try {
      // load the DCGround
      dCGroundList = DCGroundBusinessDelegate.getDCGroundInstance().getAllDCGround();

      if (dCGroundList != null) LOGGER.log(Level.INFO, "successfully loaded all DCGrounds");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all DCGrounds ", exc);
      return null;
    }

    return dCGroundList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected DCGround dCGround = null;
  private static final Logger LOGGER =
      Logger.getLogger(DCGroundQueryRestController.class.getName());
}
