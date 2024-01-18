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
package com.occulue.subscriber;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;

/**
 * Subscriber for VoltageCompensatorUserDefined related events. .
 *
 * @author your_name_here
 */
@Component("voltageCompensatorUserDefined-subscriber")
public class VoltageCompensatorUserDefinedSubscriber extends BaseSubscriber {

  public VoltageCompensatorUserDefinedSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<VoltageCompensatorUserDefined>, VoltageCompensatorUserDefined>
      voltageCompensatorUserDefinedSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllVoltageCompensatorUserDefinedQuery(),
        ResponseTypes.multipleInstancesOf(VoltageCompensatorUserDefined.class),
        ResponseTypes.instanceOf(VoltageCompensatorUserDefined.class));
  }

  public SubscriptionQueryResult<VoltageCompensatorUserDefined, VoltageCompensatorUserDefined>
      voltageCompensatorUserDefinedSubscribe(
          @DestinationVariable UUID voltageCompensatorUserDefinedId) {
    return queryGateway.subscriptionQuery(
        new FindVoltageCompensatorUserDefinedQuery(
            new LoadVoltageCompensatorUserDefinedFilter(voltageCompensatorUserDefinedId)),
        ResponseTypes.instanceOf(VoltageCompensatorUserDefined.class),
        ResponseTypes.instanceOf(VoltageCompensatorUserDefined.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
