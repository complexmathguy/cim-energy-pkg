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
 * Implements Spring Controller query CQRS processing for entity ExcANS.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/ExcANS")
public class ExcANSQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a ExcANS using a UUID
   *
   * @param UUID excANSId
   * @return ExcANS
   */
  @GetMapping("/load")
  public ExcANS load(@RequestParam(required = true) UUID excANSId) {
    ExcANS entity = null;

    try {
      entity =
          ExcANSBusinessDelegate.getExcANSInstance().getExcANS(new ExcANSFetchOneSummary(excANSId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load ExcANS using Id " + excANSId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all ExcANS business objects
   *
   * @return Set<ExcANS>
   */
  @GetMapping("/")
  public List<ExcANS> loadAll() {
    List<ExcANS> excANSList = null;

    try {
      // load the ExcANS
      excANSList = ExcANSBusinessDelegate.getExcANSInstance().getAllExcANS();

      if (excANSList != null) LOGGER.log(Level.INFO, "successfully loaded all ExcANSs");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all ExcANSs ", exc);
      return null;
    }

    return excANSList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected ExcANS excANS = null;
  private static final Logger LOGGER = Logger.getLogger(ExcANSQueryRestController.class.getName());
}
