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
 * Subscriber for Analog related events. .
 *
 * @author your_name_here
 */
@Component("analog-subscriber")
public class AnalogSubscriber extends BaseSubscriber {

  public AnalogSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<Analog>, Analog> analogSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllAnalogQuery(),
        ResponseTypes.multipleInstancesOf(Analog.class),
        ResponseTypes.instanceOf(Analog.class));
  }

  public SubscriptionQueryResult<Analog, Analog> analogSubscribe(
      @DestinationVariable UUID analogId) {
    return queryGateway.subscriptionQuery(
        new FindAnalogQuery(new LoadAnalogFilter(analogId)),
        ResponseTypes.instanceOf(Analog.class),
        ResponseTypes.instanceOf(Analog.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
