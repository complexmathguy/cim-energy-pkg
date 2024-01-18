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
 * Implements Spring Controller query CQRS processing for entity PhaseTapChangerTabular.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/PhaseTapChangerTabular")
public class PhaseTapChangerTabularQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a PhaseTapChangerTabular using a UUID
   *
   * @param UUID phaseTapChangerTabularId
   * @return PhaseTapChangerTabular
   */
  @GetMapping("/load")
  public PhaseTapChangerTabular load(@RequestParam(required = true) UUID phaseTapChangerTabularId) {
    PhaseTapChangerTabular entity = null;

    try {
      entity =
          PhaseTapChangerTabularBusinessDelegate.getPhaseTapChangerTabularInstance()
              .getPhaseTapChangerTabular(
                  new PhaseTapChangerTabularFetchOneSummary(phaseTapChangerTabularId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "failed to load PhaseTapChangerTabular using Id " + phaseTapChangerTabularId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all PhaseTapChangerTabular business objects
   *
   * @return Set<PhaseTapChangerTabular>
   */
  @GetMapping("/")
  public List<PhaseTapChangerTabular> loadAll() {
    List<PhaseTapChangerTabular> phaseTapChangerTabularList = null;

    try {
      // load the PhaseTapChangerTabular
      phaseTapChangerTabularList =
          PhaseTapChangerTabularBusinessDelegate.getPhaseTapChangerTabularInstance()
              .getAllPhaseTapChangerTabular();

      if (phaseTapChangerTabularList != null)
        LOGGER.log(Level.INFO, "successfully loaded all PhaseTapChangerTabulars");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all PhaseTapChangerTabulars ", exc);
      return null;
    }

    return phaseTapChangerTabularList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected PhaseTapChangerTabular phaseTapChangerTabular = null;
  private static final Logger LOGGER =
      Logger.getLogger(PhaseTapChangerTabularQueryRestController.class.getName());
}
