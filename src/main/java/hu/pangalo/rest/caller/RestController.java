package hu.pangalo.rest.caller;


import hu.pangalo.rest.caller.service.RepeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

	private static final Logger logger = LoggerFactory.getLogger(RestController.class);

	@Autowired
	RepeatService repeatService;

	@GetMapping
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.ok("Hello World!");
	}

	@GetMapping(path = "/repeat-call")
	public ResponseEntity<String> RepeatedApiCall() throws InterruptedException {
		Integer repeat = repeatService.getCounter();
		logger.info("entered repeated api caller, repeated value: " + repeat);
		for(int i = 0; i < repeat; i++) {
			ResponseEntity<String> respone = new RestTemplate().getForEntity("https://rest-webservice-feature-demo1.apps-crc.testing/latened-response", String.class);

			//String message = respone.getBody();
		}
		logger.info("repeated api caller calling finished");
		return ResponseEntity.ok("repeated api caller calling finished");
	}

	@PostMapping(path = "/repeat/{repeat}")
	public ResponseEntity<String> setRepeatValue(@PathVariable Integer repeat) {
		repeatService.setCounter(repeat);
		logger.info("repeat value set to " + repeat + "!");
		return ResponseEntity.ok("repeat value set to " + repeat + "!");
	}
}
