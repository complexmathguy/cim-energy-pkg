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
 * Subscriber for Limit related events. .
 *
 * @author your_name_here
 */
@Component("limit-subscriber")
public class LimitSubscriber extends BaseSubscriber {

  public LimitSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<Limit>, Limit> limitSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllLimitQuery(),
        ResponseTypes.multipleInstancesOf(Limit.class),
        ResponseTypes.instanceOf(Limit.class));
  }

  public SubscriptionQueryResult<Limit, Limit> limitSubscribe(@DestinationVariable UUID limitId) {
    return queryGateway.subscriptionQuery(
        new FindLimitQuery(new LoadLimitFilter(limitId)),
        ResponseTypes.instanceOf(Limit.class),
        ResponseTypes.instanceOf(Limit.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
