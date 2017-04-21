package pt.ulisboa.tecnico.softeng.hotel.services.remote;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.ulisboa.tecnico.softeng.hotel.domain.Room;
import pt.ulisboa.tecnico.softeng.hotel.exception.HotelException;
import pt.ulisboa.tecnico.softeng.hotel.services.local.HotelInterface;

@RestController
@RequestMapping(value = "/rest/hotels")
public class HotelRestController {
	private static Logger logger = LoggerFactory.getLogger(HotelRestController.class);

	@RequestMapping(value = "/reserve", method = RequestMethod.POST)
	public ResponseEntity<String> reserve(@RequestParam Room.Type type,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrival,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate departure) {
		logger.info("reserve type:{}, arrival:{}, departure:{}", type, arrival, departure);
		try {
			return new ResponseEntity<>(HotelInterface.reserveRoom(type, arrival, departure), HttpStatus.OK);
		} catch (HotelException be) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
