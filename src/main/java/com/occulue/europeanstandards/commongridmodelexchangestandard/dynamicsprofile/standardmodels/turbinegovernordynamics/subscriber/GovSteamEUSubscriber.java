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
 * Subscriber for GovSteamEU related events. .
 *
 * @author your_name_here
 */
@Component("govSteamEU-subscriber")
public class GovSteamEUSubscriber extends BaseSubscriber {

  public GovSteamEUSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<GovSteamEU>, GovSteamEU> govSteamEUSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllGovSteamEUQuery(),
        ResponseTypes.multipleInstancesOf(GovSteamEU.class),
        ResponseTypes.instanceOf(GovSteamEU.class));
  }

  public SubscriptionQueryResult<GovSteamEU, GovSteamEU> govSteamEUSubscribe(
      @DestinationVariable UUID govSteamEUId) {
    return queryGateway.subscriptionQuery(
        new FindGovSteamEUQuery(new LoadGovSteamEUFilter(govSteamEUId)),
        ResponseTypes.instanceOf(GovSteamEU.class),
        ResponseTypes.instanceOf(GovSteamEU.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
