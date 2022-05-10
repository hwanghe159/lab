package domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TicketSeller {

  private TicketOffice ticketOffice;

  public void sellTo(Audience audience) {
    Long fee = audience.buy(ticketOffice.getTicket());
    ticketOffice.plusAmount(fee);
  }
}