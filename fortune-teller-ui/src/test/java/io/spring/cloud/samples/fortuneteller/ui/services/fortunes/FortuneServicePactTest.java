package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactDslJsonBody;
import au.com.dius.pact.consumer.PactRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.model.PactFragment;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = io.spring.cloud.samples.fortuneteller.ui.Application.class)
@WebAppConfiguration
@ActiveProfiles({ "pact" })
public class FortuneServicePactTest {

	@Autowired
	FortuneService fortuneService;

	@Rule
	public PactRule rule = new PactRule("localhost", 8080, this);

	@Pact(state = "FortuneState", provider = "FortuneService", consumer = "FortuneUi")
	public PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json;charset=UTF-8");

		PactDslJsonBody responseBody = new PactDslJsonBody().numberType("id").stringType("text");

		return builder.uponReceiving("a request for a random fortune").path("/random").method("GET").willRespondWith()
				.headers(headers).status(200).body(responseBody).toFragment();
	}

	@Test
	@PactVerification("FortuneState")
	public void runTest() {
		Fortune fortune = fortuneService.randomFortune();
		assertNotNull(fortune);
		assertThat(fortune.getId(), is(greaterThan(0L)));
		assertThat(fortune.getId(), is(not(equalTo(42L))));
		assertThat(fortune.getText(), not(isEmptyOrNullString()));
		assertThat(fortune.getText(), is(not(equalTo("Your future is unclear."))));
	}
}
