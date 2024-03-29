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

public class DCBusbarValidator {

  /** default constructor */
  protected DCBusbarValidator() {}

  /** factory method */
  public static DCBusbarValidator getInstance() {
    return new DCBusbarValidator();
  }

  /** handles creation validation for a DCBusbar */
  public void validate(CreateDCBusbarCommand dCBusbar) throws Exception {
    Assert.notNull(dCBusbar, "CreateDCBusbarCommand should not be null");
    //		Assert.isNull( dCBusbar.getDCBusbarId(), "CreateDCBusbarCommand identifier should be null"
    // );
  }

  /** handles update validation for a DCBusbar */
  public void validate(UpdateDCBusbarCommand dCBusbar) throws Exception {
    Assert.notNull(dCBusbar, "UpdateDCBusbarCommand should not be null");
    Assert.notNull(dCBusbar.getDCBusbarId(), "UpdateDCBusbarCommand identifier should not be null");
  }

  /** handles delete validation for a DCBusbar */
  public void validate(DeleteDCBusbarCommand dCBusbar) throws Exception {
    Assert.notNull(dCBusbar, "{commandAlias} should not be null");
    Assert.notNull(dCBusbar.getDCBusbarId(), "DeleteDCBusbarCommand identifier should not be null");
  }

  /** handles fetchOne validation for a DCBusbar */
  public void validate(DCBusbarFetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "DCBusbarFetchOneSummary should not be null");
    Assert.notNull(
        summary.getDCBusbarId(), "DCBusbarFetchOneSummary identifier should not be null");
  }
}
