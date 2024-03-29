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
 * Subscriber for PFVArControllerType1Dynamics related events. .
 *
 * @author your_name_here
 */
@Component("pFVArControllerType1Dynamics-subscriber")
public class PFVArControllerType1DynamicsSubscriber extends BaseSubscriber {

  public PFVArControllerType1DynamicsSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<PFVArControllerType1Dynamics>, PFVArControllerType1Dynamics>
      pFVArControllerType1DynamicsSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllPFVArControllerType1DynamicsQuery(),
        ResponseTypes.multipleInstancesOf(PFVArControllerType1Dynamics.class),
        ResponseTypes.instanceOf(PFVArControllerType1Dynamics.class));
  }

  public SubscriptionQueryResult<PFVArControllerType1Dynamics, PFVArControllerType1Dynamics>
      pFVArControllerType1DynamicsSubscribe(
          @DestinationVariable UUID pFVArControllerType1DynamicsId) {
    return queryGateway.subscriptionQuery(
        new FindPFVArControllerType1DynamicsQuery(
            new LoadPFVArControllerType1DynamicsFilter(pFVArControllerType1DynamicsId)),
        ResponseTypes.instanceOf(PFVArControllerType1Dynamics.class),
        ResponseTypes.instanceOf(PFVArControllerType1Dynamics.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
