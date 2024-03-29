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

public class JunctionValidator {

  /** default constructor */
  protected JunctionValidator() {}

  /** factory method */
  public static JunctionValidator getInstance() {
    return new JunctionValidator();
  }

  /** handles creation validation for a Junction */
  public void validate(CreateJunctionCommand junction) throws Exception {
    Assert.notNull(junction, "CreateJunctionCommand should not be null");
    //		Assert.isNull( junction.getJunctionId(), "CreateJunctionCommand identifier should be null"
    // );
  }

  /** handles update validation for a Junction */
  public void validate(UpdateJunctionCommand junction) throws Exception {
    Assert.notNull(junction, "UpdateJunctionCommand should not be null");
    Assert.notNull(junction.getJunctionId(), "UpdateJunctionCommand identifier should not be null");
  }

  /** handles delete validation for a Junction */
  public void validate(DeleteJunctionCommand junction) throws Exception {
    Assert.notNull(junction, "{commandAlias} should not be null");
    Assert.notNull(junction.getJunctionId(), "DeleteJunctionCommand identifier should not be null");
  }

  /** handles fetchOne validation for a Junction */
  public void validate(JunctionFetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "JunctionFetchOneSummary should not be null");
    Assert.notNull(
        summary.getJunctionId(), "JunctionFetchOneSummary identifier should not be null");
  }
}
