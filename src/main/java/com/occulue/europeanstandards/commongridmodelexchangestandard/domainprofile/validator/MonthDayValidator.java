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

public class MonthDayValidator {

  /** default constructor */
  protected MonthDayValidator() {}

  /** factory method */
  public static MonthDayValidator getInstance() {
    return new MonthDayValidator();
  }

  /** handles creation validation for a MonthDay */
  public void validate(CreateMonthDayCommand monthDay) throws Exception {
    Assert.notNull(monthDay, "CreateMonthDayCommand should not be null");
    //		Assert.isNull( monthDay.getMonthDayId(), "CreateMonthDayCommand identifier should be null"
    // );
  }

  /** handles update validation for a MonthDay */
  public void validate(UpdateMonthDayCommand monthDay) throws Exception {
    Assert.notNull(monthDay, "UpdateMonthDayCommand should not be null");
    Assert.notNull(monthDay.getMonthDayId(), "UpdateMonthDayCommand identifier should not be null");
  }

  /** handles delete validation for a MonthDay */
  public void validate(DeleteMonthDayCommand monthDay) throws Exception {
    Assert.notNull(monthDay, "{commandAlias} should not be null");
    Assert.notNull(monthDay.getMonthDayId(), "DeleteMonthDayCommand identifier should not be null");
  }

  /** handles fetchOne validation for a MonthDay */
  public void validate(MonthDayFetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "MonthDayFetchOneSummary should not be null");
    Assert.notNull(
        summary.getMonthDayId(), "MonthDayFetchOneSummary identifier should not be null");
  }
}
