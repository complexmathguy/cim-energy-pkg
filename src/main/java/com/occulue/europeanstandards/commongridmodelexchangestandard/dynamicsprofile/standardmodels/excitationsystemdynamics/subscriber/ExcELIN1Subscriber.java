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
 * Subscriber for ExcELIN1 related events. .
 *
 * @author your_name_here
 */
@Component("excELIN1-subscriber")
public class ExcELIN1Subscriber extends BaseSubscriber {

  public ExcELIN1Subscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<ExcELIN1>, ExcELIN1> excELIN1Subscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllExcELIN1Query(),
        ResponseTypes.multipleInstancesOf(ExcELIN1.class),
        ResponseTypes.instanceOf(ExcELIN1.class));
  }

  public SubscriptionQueryResult<ExcELIN1, ExcELIN1> excELIN1Subscribe(
      @DestinationVariable UUID excELIN1Id) {
    return queryGateway.subscriptionQuery(
        new FindExcELIN1Query(new LoadExcELIN1Filter(excELIN1Id)),
        ResponseTypes.instanceOf(ExcELIN1.class),
        ResponseTypes.instanceOf(ExcELIN1.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
