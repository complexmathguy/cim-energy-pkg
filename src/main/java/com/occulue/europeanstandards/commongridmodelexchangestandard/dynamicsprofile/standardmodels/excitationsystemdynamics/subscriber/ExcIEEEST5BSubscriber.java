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
 * Subscriber for ExcIEEEST5B related events. .
 *
 * @author your_name_here
 */
@Component("excIEEEST5B-subscriber")
public class ExcIEEEST5BSubscriber extends BaseSubscriber {

  public ExcIEEEST5BSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<ExcIEEEST5B>, ExcIEEEST5B> excIEEEST5BSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllExcIEEEST5BQuery(),
        ResponseTypes.multipleInstancesOf(ExcIEEEST5B.class),
        ResponseTypes.instanceOf(ExcIEEEST5B.class));
  }

  public SubscriptionQueryResult<ExcIEEEST5B, ExcIEEEST5B> excIEEEST5BSubscribe(
      @DestinationVariable UUID excIEEEST5BId) {
    return queryGateway.subscriptionQuery(
        new FindExcIEEEST5BQuery(new LoadExcIEEEST5BFilter(excIEEEST5BId)),
        ResponseTypes.instanceOf(ExcIEEEST5B.class),
        ResponseTypes.instanceOf(ExcIEEEST5B.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
