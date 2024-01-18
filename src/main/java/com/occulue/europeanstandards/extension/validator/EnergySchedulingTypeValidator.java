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
package com.occulue.validator;

import com.occulue.api.*;
import org.springframework.util.Assert;

public class EnergySchedulingTypeValidator {

  /** default constructor */
  protected EnergySchedulingTypeValidator() {}

  /** factory method */
  public static EnergySchedulingTypeValidator getInstance() {
    return new EnergySchedulingTypeValidator();
  }

  /** handles creation validation for a EnergySchedulingType */
  public void validate(CreateEnergySchedulingTypeCommand energySchedulingType) throws Exception {
    Assert.notNull(energySchedulingType, "CreateEnergySchedulingTypeCommand should not be null");
    //		Assert.isNull( energySchedulingType.getEnergySchedulingTypeId(),
    // "CreateEnergySchedulingTypeCommand identifier should be null" );
  }

  /** handles update validation for a EnergySchedulingType */
  public void validate(UpdateEnergySchedulingTypeCommand energySchedulingType) throws Exception {
    Assert.notNull(energySchedulingType, "UpdateEnergySchedulingTypeCommand should not be null");
    Assert.notNull(
        energySchedulingType.getEnergySchedulingTypeId(),
        "UpdateEnergySchedulingTypeCommand identifier should not be null");
  }

  /** handles delete validation for a EnergySchedulingType */
  public void validate(DeleteEnergySchedulingTypeCommand energySchedulingType) throws Exception {
    Assert.notNull(energySchedulingType, "{commandAlias} should not be null");
    Assert.notNull(
        energySchedulingType.getEnergySchedulingTypeId(),
        "DeleteEnergySchedulingTypeCommand identifier should not be null");
  }

  /** handles fetchOne validation for a EnergySchedulingType */
  public void validate(EnergySchedulingTypeFetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "EnergySchedulingTypeFetchOneSummary should not be null");
    Assert.notNull(
        summary.getEnergySchedulingTypeId(),
        "EnergySchedulingTypeFetchOneSummary identifier should not be null");
  }
}
