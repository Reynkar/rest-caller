package hu.pangalo.rest.caller.service;

import org.springframework.stereotype.Service;

@Service
public class RepeatService {

	private Integer counter = 1;

	public Integer getCounter() {
		return counter;
	}
	public void setCounter(Integer counter) {
		this.counter = counter;
	}
}
