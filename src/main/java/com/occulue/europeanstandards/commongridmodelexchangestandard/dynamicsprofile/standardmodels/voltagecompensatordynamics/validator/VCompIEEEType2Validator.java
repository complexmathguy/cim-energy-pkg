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

public class VCompIEEEType2Validator {

  /** default constructor */
  protected VCompIEEEType2Validator() {}

  /** factory method */
  public static VCompIEEEType2Validator getInstance() {
    return new VCompIEEEType2Validator();
  }

  /** handles creation validation for a VCompIEEEType2 */
  public void validate(CreateVCompIEEEType2Command vCompIEEEType2) throws Exception {
    Assert.notNull(vCompIEEEType2, "CreateVCompIEEEType2Command should not be null");
    //		Assert.isNull( vCompIEEEType2.getVCompIEEEType2Id(), "CreateVCompIEEEType2Command identifier
    // should be null" );
  }

  /** handles update validation for a VCompIEEEType2 */
  public void validate(UpdateVCompIEEEType2Command vCompIEEEType2) throws Exception {
    Assert.notNull(vCompIEEEType2, "UpdateVCompIEEEType2Command should not be null");
    Assert.notNull(
        vCompIEEEType2.getVCompIEEEType2Id(),
        "UpdateVCompIEEEType2Command identifier should not be null");
  }

  /** handles delete validation for a VCompIEEEType2 */
  public void validate(DeleteVCompIEEEType2Command vCompIEEEType2) throws Exception {
    Assert.notNull(vCompIEEEType2, "{commandAlias} should not be null");
    Assert.notNull(
        vCompIEEEType2.getVCompIEEEType2Id(),
        "DeleteVCompIEEEType2Command identifier should not be null");
  }

  /** handles fetchOne validation for a VCompIEEEType2 */
  public void validate(VCompIEEEType2FetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "VCompIEEEType2FetchOneSummary should not be null");
    Assert.notNull(
        summary.getVCompIEEEType2Id(),
        "VCompIEEEType2FetchOneSummary identifier should not be null");
  }

  /**
   * handles assign Tr validation for a VCompIEEEType2
   *
   * @param command AssignTrToVCompIEEEType2Command
   */
  public void validate(AssignTrToVCompIEEEType2Command command) throws Exception {
    Assert.notNull(command, "AssignTrToVCompIEEEType2Command should not be null");
    Assert.notNull(
        command.getVCompIEEEType2Id(),
        "AssignTrToVCompIEEEType2Command identifier should not be null");
    Assert.notNull(
        command.getAssignment(), "AssignTrToVCompIEEEType2Command assignment should not be null");
  }

  /**
   * handles unassign Tr validation for a VCompIEEEType2
   *
   * @param command UnAssignTrFromVCompIEEEType2Command
   */
  public void validate(UnAssignTrFromVCompIEEEType2Command command) throws Exception {
    Assert.notNull(command, "UnAssignTrFromVCompIEEEType2Command should not be null");
    Assert.notNull(
        command.getVCompIEEEType2Id(),
        "UnAssignTrFromVCompIEEEType2Command identifier should not be null");
  }
}
