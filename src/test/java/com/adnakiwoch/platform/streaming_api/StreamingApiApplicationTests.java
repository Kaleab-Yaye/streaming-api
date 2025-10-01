package com.adnakiwoch.platform.streaming_api;

import static org.assertj.core.api.Assertions.assertThat;

import com.adnakiwoch.platform.streaming_api.config.BillingProperties;
import com.adnakiwoch.platform.streaming_api.config.CdnProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

@SpringBootTest
class StreamingApiApplicationTests {
  // here you ceat the actuall APPlicationContextRunner
  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

  // now here we define the test that we are going to do a good naming is the way to go

  @Test
  void apiKeyMustNotBeBlank() {
    // so first we load our calss in to the context runner
    this.contextRunner
        .withUserConfiguration(
            StreamingApiApplication.class) // this is where we load our application class
        .withPropertyValues(
            "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration",
            "platform.billing.trail-period-days=30") /*this is where we specify the propties our memmeroy only exisiting aplicationproperies file
                                                     and as you can see we are creting a propties for the trialPeriodDays field which weillbe populated with it but
                                                     on perpouse leaving the api-key proprtie empety so it would lead to the failing of the aplication context when run*/
        .run(
            context -> {
              /*when you run this conntext runner runs it return an object we could name that what evver we wan
              but it has all the information that we need abotu the start of the app */
              assertThat(context)
                  .hasFailed(); // if the app fiald then this wontn though an error and we know
              // ommitin of the api-key coused this
              assertThat(context)
                  .getFailure()
                  .hasRootCauseInstanceOf(BindValidationException.class);
            });
  }

  @Autowired BillingProperties billingProperties;

  @Autowired CdnProperties cdnProperties;

  @Test
  void contextLoads() {}

  @Test
  void billingPropertiesAreINBound() {
    assertThat(billingProperties.apiKey()).isEqualTo("dummy api key");
    assertThat(billingProperties.trailPeriodDays()).isEqualTo(7);
  }

  @Test
  void cdnPropertiesAreInBound() {
    assertThat(cdnProperties.baseUrl()).isEqualTo("https://cdn.streaming-platform.com");
    assertThat(cdnProperties.privateStreamingEnabled()).isTrue();
  }
}
