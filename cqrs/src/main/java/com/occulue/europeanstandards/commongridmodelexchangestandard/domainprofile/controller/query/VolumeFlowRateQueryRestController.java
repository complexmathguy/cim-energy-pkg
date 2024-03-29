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
 * Implements Spring Controller query CQRS processing for entity VolumeFlowRate.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/VolumeFlowRate")
public class VolumeFlowRateQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a VolumeFlowRate using a UUID
   *
   * @param UUID volumeFlowRateId
   * @return VolumeFlowRate
   */
  @GetMapping("/load")
  public VolumeFlowRate load(@RequestParam(required = true) UUID volumeFlowRateId) {
    VolumeFlowRate entity = null;

    try {
      entity =
          VolumeFlowRateBusinessDelegate.getVolumeFlowRateInstance()
              .getVolumeFlowRate(new VolumeFlowRateFetchOneSummary(volumeFlowRateId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load VolumeFlowRate using Id " + volumeFlowRateId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all VolumeFlowRate business objects
   *
   * @return Set<VolumeFlowRate>
   */
  @GetMapping("/")
  public List<VolumeFlowRate> loadAll() {
    List<VolumeFlowRate> volumeFlowRateList = null;

    try {
      // load the VolumeFlowRate
      volumeFlowRateList =
          VolumeFlowRateBusinessDelegate.getVolumeFlowRateInstance().getAllVolumeFlowRate();

      if (volumeFlowRateList != null)
        LOGGER.log(Level.INFO, "successfully loaded all VolumeFlowRates");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all VolumeFlowRates ", exc);
      return null;
    }

    return volumeFlowRateList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected VolumeFlowRate volumeFlowRate = null;
  private static final Logger LOGGER =
      Logger.getLogger(VolumeFlowRateQueryRestController.class.getName());
}
