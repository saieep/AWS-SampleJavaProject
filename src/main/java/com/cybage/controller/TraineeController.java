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
import com.cybage.command.Groupdetail;
import com.cybage.command.Response;
import com.cybage.command.Scenario;
import com.cybage.requestdto.ActionDTO;
import com.cybage.requestdto.AuditDTO;
import com.cybage.requestdto.ResponseDTO;
import com.cybage.responsedto.GroupDetailResponseDTO;
import com.cybage.responsedto.ScenarioResponseDTO;
import com.cybage.service.ActionService;
import com.cybage.service.AuditService;
import com.cybage.service.GroupDetailService;
import com.cybage.service.ResponseService;
import com.cybage.service.ScenarioService;
import com.cybage.util.OrikoMapperUtility;

@Controller
@RequestMapping(value = "/trainee")
public class TraineeController {

	private Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	ResponseService responseService;

	@Autowired
	ActionService actionService;

	@Autowired
	AuditService auditService;

	@Autowired
	TokenAuthenticationService tokenAuthService;
	
	@Autowired
	ScenarioService scenarioService;

	@Autowired
	GroupDetailService groupService;
	
	@RequestMapping(value = "/getresponse", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Response>> showResponse(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainee(jwt) ) {
			List<Response> list = responseService.showResponse();
			return new ResponseEntity<List<Response>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Response>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/getscenariobyidactive/{sessionid}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<ScenarioResponseDTO>> showScenario(
			@PathVariable int sessionid,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainer(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Scenario> list = scenarioService.showScenarioBySIDActive(sessionid);

			List<ScenarioResponseDTO> listofDto = new ArrayList<ScenarioResponseDTO>();
			for (Scenario scenario : list) {
				ScenarioResponseDTO scenarioDTO = mapperUtility.getMapper().map(scenario, ScenarioResponseDTO.class);
				scenarioDTO.setSessionid(String.valueOf(scenario.getSession()
						.getSessionid()));
				listofDto.add(scenarioDTO);

			}
			ScenarioResponseDTO scenarioDto = new ScenarioResponseDTO();
			return new ResponseEntity<List<ScenarioResponseDTO>>(listofDto,
					HttpStatus.OK);
		}
		return new ResponseEntity<List<ScenarioResponseDTO>>(
				HttpStatus.UNAUTHORIZED);
	}

	
	
	@RequestMapping(value = "/addresponse", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addAudit(@RequestBody ResponseDTO responseDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainee(jwt)) {
			responseService.insertResponse(responseDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getscenario/{id}")
					.buildAndExpand(responseDto.getScenarioid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/addaction", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addAction(@RequestBody ActionDTO actionDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainee(jwt)) {
			actionService.insertAction(actionDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/admin/getaction/{id}")
					.buildAndExpand(actionDto.getActionid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/getscenario", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<ScenarioResponseDTO>> showUser(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainee(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Scenario> list = scenarioService.showScenario();

			List<ScenarioResponseDTO> listofDto = new ArrayList<ScenarioResponseDTO>();
			for (Scenario scenario : list) {
				ScenarioResponseDTO scenarioDTO = mapperUtility.getMapper().map(scenario, ScenarioResponseDTO.class);
				scenarioDTO.setSessionid(String.valueOf(scenario.getSession()
						.getSessionid()));
				listofDto.add(scenarioDTO);

			}
			ScenarioResponseDTO scenarioDto = new ScenarioResponseDTO();
			return new ResponseEntity<List<ScenarioResponseDTO>>(listofDto,
					HttpStatus.OK);
		}
		return new ResponseEntity<List<ScenarioResponseDTO>>(
				HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/gettraineebyid/{groupid}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<GroupDetailResponseDTO>> showGroupDetail(
			@PathVariable int groupid,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainee(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Groupdetail> list = groupService.showGroupById(groupid);

			List<GroupDetailResponseDTO> listOfDto = new ArrayList<GroupDetailResponseDTO>();
			for (Groupdetail groupdetail : list) {
				GroupDetailResponseDTO groupDTO = mapperUtility.getMapper().map(groupdetail,
								GroupDetailResponseDTO.class);
				groupDTO.setSessionid(String.valueOf(groupdetail.getSession()
						.getSessionid()));
				listOfDto.add(groupDTO);
			}

			GroupDetailResponseDTO groupdetail1 = new GroupDetailResponseDTO();
			return new ResponseEntity<List<GroupDetailResponseDTO>>(listOfDto,
					HttpStatus.OK);
		}
		return new ResponseEntity<List<GroupDetailResponseDTO>>(
				HttpStatus.UNAUTHORIZED);
	}
	
	
	
	@RequestMapping(value = "/addaudit", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addAudit(@RequestBody AuditDTO auditDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isTrainee(jwt) ) {
			auditService.insertAudit(auditDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getaudit/{id}")
					.buildAndExpand(auditDto.getAuditid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
