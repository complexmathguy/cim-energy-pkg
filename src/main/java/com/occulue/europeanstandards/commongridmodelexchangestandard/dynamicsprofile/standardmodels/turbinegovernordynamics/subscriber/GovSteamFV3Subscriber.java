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
 * Subscriber for GovSteamFV3 related events. .
 *
 * @author your_name_here
 */
@Component("govSteamFV3-subscriber")
public class GovSteamFV3Subscriber extends BaseSubscriber {

  public GovSteamFV3Subscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<GovSteamFV3>, GovSteamFV3> govSteamFV3Subscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllGovSteamFV3Query(),
        ResponseTypes.multipleInstancesOf(GovSteamFV3.class),
        ResponseTypes.instanceOf(GovSteamFV3.class));
  }

  public SubscriptionQueryResult<GovSteamFV3, GovSteamFV3> govSteamFV3Subscribe(
      @DestinationVariable UUID govSteamFV3Id) {
    return queryGateway.subscriptionQuery(
        new FindGovSteamFV3Query(new LoadGovSteamFV3Filter(govSteamFV3Id)),
        ResponseTypes.instanceOf(GovSteamFV3.class),
        ResponseTypes.instanceOf(GovSteamFV3.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
