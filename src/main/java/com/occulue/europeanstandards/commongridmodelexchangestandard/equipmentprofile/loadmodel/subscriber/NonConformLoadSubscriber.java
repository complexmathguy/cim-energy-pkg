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
 * Subscriber for NonConformLoad related events. .
 *
 * @author your_name_here
 */
@Component("nonConformLoad-subscriber")
public class NonConformLoadSubscriber extends BaseSubscriber {

  public NonConformLoadSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<NonConformLoad>, NonConformLoad> nonConformLoadSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllNonConformLoadQuery(),
        ResponseTypes.multipleInstancesOf(NonConformLoad.class),
        ResponseTypes.instanceOf(NonConformLoad.class));
  }

  public SubscriptionQueryResult<NonConformLoad, NonConformLoad> nonConformLoadSubscribe(
      @DestinationVariable UUID nonConformLoadId) {
    return queryGateway.subscriptionQuery(
        new FindNonConformLoadQuery(new LoadNonConformLoadFilter(nonConformLoadId)),
        ResponseTypes.instanceOf(NonConformLoad.class),
        ResponseTypes.instanceOf(NonConformLoad.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
