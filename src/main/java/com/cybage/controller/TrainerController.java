package com.cybage.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.cybage.authentication.TokenAuthenticationService;
import com.cybage.command.Response;
import com.cybage.command.ResponsePK;
import com.cybage.command.Scenario;
import com.cybage.command.Session;
import com.cybage.command.User;
import com.cybage.requestdto.ResponseDTO;
import com.cybage.requestdto.ScenarioDTO;
import com.cybage.requestdto.SessionDTO;
import com.cybage.responsedto.ScenarioResponseDTO;
import com.cybage.responsedto.SessionResponseDTO;
import com.cybage.responsedto.UserResponseDTO;
import com.cybage.service.ResponseService;
import com.cybage.service.ScenarioService;
import com.cybage.service.SessionService;
import com.cybage.service.UserService;
import com.cybage.util.AllowCORSSupport;
import com.cybage.util.OrikoMapperUtility;

@Controller
@RequestMapping(value = "/trainer")
public class TrainerController {

	private Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	ScenarioService scenarioService;

	@Autowired
	ResponseService responseService;

	@Autowired
	SessionService sessionService;

	@Autowired
	TokenAuthenticationService tokenAuthService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/getscenariobyid/{sessionid}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<ScenarioResponseDTO>> showScenario(
			@PathVariable int sessionid,
			@RequestHeader(value = "Authorization") String jwt) {
	
		if (tokenAuthService.isTrainer(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Scenario> list = scenarioService.showScenarioBySID(sessionid);

			List<ScenarioResponseDTO> listofDto = new ArrayList<ScenarioResponseDTO>();
			for (Scenario scenario : list) {
				ScenarioResponseDTO scenarioDTO = mapperUtility.getMapper().map(scenario, ScenarioResponseDTO.class);
				scenarioDTO.setSessionid(String.valueOf(scenario.getSession()
						.getSessionid()));
				listofDto.add(scenarioDTO);

			}
			
			ScenarioResponseDTO scenarioDto = new ScenarioResponseDTO();
			if(listofDto.isEmpty()){
				return new ResponseEntity<List<ScenarioResponseDTO>>(HttpStatus.NOT_FOUND);
					
			}
			else{
			return new ResponseEntity<List<ScenarioResponseDTO>>(listofDto,
					HttpStatus.OK);
			}
		}
		else{
		return new ResponseEntity<List<ScenarioResponseDTO>>(
				HttpStatus.UNAUTHORIZED);
		}			
	}

	
	
	
	
	@RequestMapping(value = "/gettrainerbyid/{trainerid}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<UserResponseDTO>> showUser(
			@PathVariable int trainerid,
			@RequestHeader(value = "Authorization") String jwt) {

		if (tokenAuthService.isTrainer(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<User> list = userService.showUserByid(trainerid);
			List<UserResponseDTO> listOfDto = new ArrayList<UserResponseDTO>();
			for (User user : list) {
				UserResponseDTO userDTO = mapperUtility.getMapper().map(
						user, UserResponseDTO.class);

				listOfDto.add(userDTO);
			}

			UserResponseDTO audit = new UserResponseDTO();
			HttpHeaders headers = AllowCORSSupport
					.addAccessControllAllowOrigin();

			return new ResponseEntity<List<UserResponseDTO>>(listOfDto,
					headers, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserResponseDTO>>(HttpStatus.UNAUTHORIZED);

	}
	
	@RequestMapping(value = "/getsessionbyid/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<SessionResponseDTO>> showSessionById(
			@PathVariable int id,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Session> list = sessionService.findSessionbyuserid(id);
			List<SessionResponseDTO> listOfDto = new ArrayList<SessionResponseDTO>();
			for (Session session : list) {
				SessionResponseDTO sessionDTO = mapperUtility.getMapper()
						.map(session, SessionResponseDTO.class);
				sessionDTO.setUserid(String.valueOf(session.getUser().getId()));
				listOfDto.add(sessionDTO);
			}

			SessionResponseDTO sessionDTO = new SessionResponseDTO();

			return new ResponseEntity<List<SessionResponseDTO>>(listOfDto,
					HttpStatus.OK);
		}
		return new ResponseEntity<List<SessionResponseDTO>>(
				HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/getresponse", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Response>> showResponse(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {
			List<Response> list = responseService.showResponse();
			return new ResponseEntity<List<Response>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Response>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/addscore/{id}/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<Void> insertScore(@PathVariable("id") int id,
			@PathVariable("id") int id1, @RequestBody ResponseDTO responseDto,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {

			ResponsePK responsepk = new ResponsePK();
			responsepk.setGroupid(id);
			responsepk.setSenarioid(id1);

			Response currentResponse = responseService
					.findByGroupId(responsepk);
			currentResponse.setScore(responseDto.getScore());

			responseService.insertScore(currentResponse);

			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/addscenario", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addScenario(@RequestBody ScenarioDTO scenarioDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {

			scenarioService.insertScenario(scenarioDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getsession/{id}")
					.buildAndExpand(scenarioDto.getSessionid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/updatesession/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<Void> updateSession(@PathVariable int id,
			@RequestBody SessionDTO sessionDto,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {
			Session currentsession = sessionService.findsessionById(id);

			currentsession.setIsactive(sessionDto.getIsactive());

			sessionService.updateStatus(currentsession);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/updatescenario/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<Void> updateScenario(@PathVariable int id,
			@RequestBody ScenarioDTO scenarioDto,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {
			Scenario currentScenario = scenarioService.findScenarioById(id);

			currentScenario.setIsactive(scenarioDto.getIsactive());

			scenarioService.updateIsActive(currentScenario);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

}
