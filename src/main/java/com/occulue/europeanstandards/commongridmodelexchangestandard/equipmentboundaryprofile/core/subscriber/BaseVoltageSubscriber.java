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
 * Subscriber for BaseVoltage related events. .
 *
 * @author your_name_here
 */
@Component("baseVoltage-subscriber")
public class BaseVoltageSubscriber extends BaseSubscriber {

  public BaseVoltageSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<BaseVoltage>, BaseVoltage> baseVoltageSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllBaseVoltageQuery(),
        ResponseTypes.multipleInstancesOf(BaseVoltage.class),
        ResponseTypes.instanceOf(BaseVoltage.class));
  }

  public SubscriptionQueryResult<BaseVoltage, BaseVoltage> baseVoltageSubscribe(
      @DestinationVariable UUID baseVoltageId) {
    return queryGateway.subscriptionQuery(
        new FindBaseVoltageQuery(new LoadBaseVoltageFilter(baseVoltageId)),
        ResponseTypes.instanceOf(BaseVoltage.class),
        ResponseTypes.instanceOf(BaseVoltage.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
