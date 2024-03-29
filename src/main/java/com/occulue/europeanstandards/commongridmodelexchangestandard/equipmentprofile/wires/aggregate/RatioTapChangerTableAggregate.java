package com.occulue.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Logger;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * Aggregate handler for RatioTapChangerTable as outlined for the CQRS pattern, all write
 * responsibilities related to RatioTapChangerTable are handled here.
 *
 * @author your_name_here
 */
@Aggregate
public class RatioTapChangerTableAggregate {

  // -----------------------------------------
  // Axon requires an empty constructor
  // -----------------------------------------
  public RatioTapChangerTableAggregate() {}

  // ----------------------------------------------
  // intrinsic command handlers
  // ----------------------------------------------
  @CommandHandler
  public RatioTapChangerTableAggregate(CreateRatioTapChangerTableCommand command) throws Exception {
    LOGGER.info("Handling command CreateRatioTapChangerTableCommand");
    CreateRatioTapChangerTableEvent event =
        new CreateRatioTapChangerTableEvent(command.getRatioTapChangerTableId());

    apply(event);
  }

  @CommandHandler
  public void handle(UpdateRatioTapChangerTableCommand command) throws Exception {
    LOGGER.info("handling command UpdateRatioTapChangerTableCommand");
    UpdateRatioTapChangerTableEvent event =
        new UpdateRatioTapChangerTableEvent(command.getRatioTapChangerTableId());

    apply(event);
  }

  @CommandHandler
  public void handle(DeleteRatioTapChangerTableCommand command) throws Exception {
    LOGGER.info("Handling command DeleteRatioTapChangerTableCommand");
    apply(new DeleteRatioTapChangerTableEvent(command.getRatioTapChangerTableId()));
  }

  // ----------------------------------------------
  // association command handlers
  // ----------------------------------------------

  // single association commands

  // multiple association commands

  // ----------------------------------------------
  // intrinsic event source handlers
  // ----------------------------------------------
  @EventSourcingHandler
  void on(CreateRatioTapChangerTableEvent event) {
    LOGGER.info("Event sourcing CreateRatioTapChangerTableEvent");
    this.ratioTapChangerTableId = event.getRatioTapChangerTableId();
  }

  @EventSourcingHandler
  void on(UpdateRatioTapChangerTableEvent event) {
    LOGGER.info("Event sourcing classObject.getUpdateEventAlias()}");
  }

  // ----------------------------------------------
  // association event source handlers
  // ----------------------------------------------

  // ------------------------------------------
  // attributes
  // ------------------------------------------

  @AggregateIdentifier private UUID ratioTapChangerTableId;

  private static final Logger LOGGER =
      Logger.getLogger(RatioTapChangerTableAggregate.class.getName());
}
