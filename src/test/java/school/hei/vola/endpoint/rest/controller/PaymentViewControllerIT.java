package school.hei.vola.endpoint.rest.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithOidcUser;
import org.springframework.test.web.servlet.MockMvc;
import school.hei.vola.repository.jpa.JApplicationRepository;
import school.hei.vola.service.PaymentService;

@WebMvcTest(PaymentViewController.class)
class PaymentViewControllerIT {

  @Autowired private MockMvc mockMvc;

  @MockBean private PaymentService paymentService;

  @MockBean private JApplicationRepository jApplicationRepository;

  @Test
  @WithOidcUser
  void payments_page_contains_logout_dialog() throws Exception {
    mockMvc
        .perform(get("/payments"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("logout-dialog")))
        .andExpect(content().string(containsString("Confirmer la déconnexion")))
        .andExpect(content().string(containsString("Oui, me déconnecter")))
        .andExpect(content().string(containsString("Non, rester connecté")));
  }
}
