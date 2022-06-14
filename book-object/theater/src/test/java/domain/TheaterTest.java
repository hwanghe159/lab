package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TheaterTest {

  private Theater theater;
  private TicketOffice ticketOffice;
  private Audience audience1;
  private Audience audience2;

  @BeforeEach
  void setUp() {
    this.ticketOffice = new TicketOffice(1000L, new Ticket(100L), new Ticket(100L), new Ticket(100L));
    this.theater = new Theater(new TicketSeller(ticketOffice));
    this.audience1 = new Audience(
        new Bag(new Invitation(LocalDateTime.of(2022, 12, 31, 12, 0)), 500L));
    this.audience2 = new Audience(new Bag(500L));
  }

  @DisplayName("초대장이 있는 관객에겐 티켓을 준다")
  @Test
  void enter1() {
    int ticketSize = ticketOffice.getTickets().size();
    Long amount = ticketOffice.getAmount();

    theater.enter(audience1);

    assertThat(ticketOffice.getTickets().size()).isEqualTo(ticketSize - 1);
    assertThat(ticketOffice.getAmount()).isEqualTo(amount);
  }

  @DisplayName("초대장이 없는 관객에겐 티켓을 판매한다")
  @Test
  void enter2() {
    int ticketSize = ticketOffice.getTickets().size();
    Long amount = ticketOffice.getAmount();

    theater.enter(audience2);

    assertThat(ticketOffice.getTickets().size()).isEqualTo(ticketSize - 1);
    assertThat(ticketOffice.getAmount()).isEqualTo(amount + 100L);
  }
}