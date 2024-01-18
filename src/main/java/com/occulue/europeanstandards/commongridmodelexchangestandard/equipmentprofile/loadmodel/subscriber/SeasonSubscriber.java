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
 * Subscriber for Season related events. .
 *
 * @author your_name_here
 */
@Component("season-subscriber")
public class SeasonSubscriber extends BaseSubscriber {

  public SeasonSubscriber() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
  }

  public SubscriptionQueryResult<List<Season>, Season> seasonSubscribe() {
    return queryGateway.subscriptionQuery(
        new FindAllSeasonQuery(),
        ResponseTypes.multipleInstancesOf(Season.class),
        ResponseTypes.instanceOf(Season.class));
  }

  public SubscriptionQueryResult<Season, Season> seasonSubscribe(
      @DestinationVariable UUID seasonId) {
    return queryGateway.subscriptionQuery(
        new FindSeasonQuery(new LoadSeasonFilter(seasonId)),
        ResponseTypes.instanceOf(Season.class),
        ResponseTypes.instanceOf(Season.class));
  }

  // -------------------------------------------------
  // attributes
  // -------------------------------------------------
  @Autowired private final QueryGateway queryGateway;
}
