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
 * Subscriber for DCGround related events. .
 *
 * @author your_name_here
 */
@Component("dCGround-subscriber")
public class DCGroundSubscriber extends BaseSubscriber {

  public DCGroundSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<DCGround>, DCGround> dCGroundSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllDCGroundQuery(),
        ResponseTypes.multipleInstancesOf(DCGround.class),
        ResponseTypes.instanceOf(DCGround.class));
  }

  public SubscriptionQueryResult<DCGround, DCGround> dCGroundSubscribe(
      @DestinationVariable UUID dCGroundId) {
    return queryGateway.subscriptionQuery(
        new FindDCGroundQuery(new LoadDCGroundFilter(dCGroundId)),
        ResponseTypes.instanceOf(DCGround.class),
        ResponseTypes.instanceOf(DCGround.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
