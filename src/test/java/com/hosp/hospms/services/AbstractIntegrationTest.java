package com.hosp.hospms.services;

import com.hosp.hospms.HospMsApplication;
import com.hosp.hospms.config.FakeMongo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HospMsApplication.class})
@Import(FakeMongo.class)
class AbstractIntegrationTest {

}
