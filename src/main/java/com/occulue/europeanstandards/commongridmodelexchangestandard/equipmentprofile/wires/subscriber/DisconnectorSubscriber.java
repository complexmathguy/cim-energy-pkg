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
 * Subscriber for Disconnector related events. .
 *
 * @author your_name_here
 */
@Component("disconnector-subscriber")
public class DisconnectorSubscriber extends BaseSubscriber {

  public DisconnectorSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<Disconnector>, Disconnector> disconnectorSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllDisconnectorQuery(),
        ResponseTypes.multipleInstancesOf(Disconnector.class),
        ResponseTypes.instanceOf(Disconnector.class));
  }

  public SubscriptionQueryResult<Disconnector, Disconnector> disconnectorSubscribe(
      @DestinationVariable UUID disconnectorId) {
    return queryGateway.subscriptionQuery(
        new FindDisconnectorQuery(new LoadDisconnectorFilter(disconnectorId)),
        ResponseTypes.instanceOf(Disconnector.class),
        ResponseTypes.instanceOf(Disconnector.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
