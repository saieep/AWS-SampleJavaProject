package com.cybage.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.cybage.authentication.TokenAuthenticationService;
import com.cybage.command.Action;
import com.cybage.command.Audit;
import com.cybage.command.Groupdetail;
import com.cybage.command.Session;
import com.cybage.command.User;
import com.cybage.requestdto.AuditDTO;
import com.cybage.requestdto.GroupDetailDTO;
import com.cybage.requestdto.SessionDTO;
import com.cybage.requestdto.UserDTO;
import com.cybage.responsedto.AuditResponseDTO;
import com.cybage.responsedto.GroupDetailResponseDTO;
import com.cybage.responsedto.SessionResponseDTO;
import com.cybage.responsedto.UserResponseDTO;
import com.cybage.service.ActionService;
import com.cybage.service.AuditService;
import com.cybage.service.GroupDetailService;
import com.cybage.service.SessionService;
import com.cybage.service.UserService;
import com.cybage.util.AllowCORSSupport;
import com.cybage.util.OrikoMapperUtility;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	SessionService sessionService;

	@Autowired
	ActionService actionService;

	@Autowired
	AuditService auditService;

	@Autowired
	GroupDetailService groupService;

	@Autowired
	TokenAuthenticationService tokenAuthService;

	private Logger logger = Logger.getLogger(AdminController.class);

	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<UserResponseDTO>> showUser(
			@RequestHeader(value = "Authorization") String jwt) {

		if (tokenAuthService.isAdmin(jwt)) {
			List<User> list = userService.showUser();
			List<UserResponseDTO> listOfDto = new ArrayList<UserResponseDTO>();
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
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

	
	@RequestMapping(value = "/getadminbyid/{adminid}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<UserResponseDTO>> showUser(
			@PathVariable int adminid,
			@RequestHeader(value = "Authorization") String jwt) {

		if (tokenAuthService.isAdmin(jwt)) {
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			List<User> list = userService.showUserByid(adminid);
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
	
	
	@RequestMapping(value = "/getsession", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<SessionResponseDTO>> showSession(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			List<Session> list = sessionService.showSession();
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

	@RequestMapping(value = "/getaction", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Action>> showAction(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {
			List<Action> list = actionService.showAction();
			return new ResponseEntity<List<Action>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Action>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/getaudit", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<AuditResponseDTO>> showAudit(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Audit> list = auditService.showAudit();

			List<AuditResponseDTO> listOfDto = new ArrayList<AuditResponseDTO>();
			for (Audit audit : list) {
				AuditResponseDTO auditDTO =mapperUtility.getMapper().map(
						audit, AuditResponseDTO.class);
				auditDTO.setGroupid(String.valueOf(audit.getGroupdetail()
						.getId()));
				auditDTO.setDescription(String.valueOf(audit.getAction()
						.getDescription()));
				auditDTO.setScenarioid(String.valueOf(audit.getScenario()
						.getScenarioid()));
				listOfDto.add(auditDTO);
			}
			AuditResponseDTO audit = new AuditResponseDTO();
			return new ResponseEntity<List<AuditResponseDTO>>(listOfDto,
					HttpStatus.OK);
		}
		return new ResponseEntity<List<AuditResponseDTO>>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/getgroup", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<GroupDetailResponseDTO>> showGroupDetail(
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {
			
			OrikoMapperUtility mapperUtility = new OrikoMapperUtility();
			
			List<Groupdetail> list = groupService.showGroupDetail();

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

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addAudit(@RequestBody UserDTO userDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {

			if (userService.isUserExist(userDto)) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			userService.insertUser(userDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getuser/{id}")
					.buildAndExpand(userDto.getUserid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/addaudit", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addAudit(@RequestBody AuditDTO auditDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {

			auditService.insertAudit(auditDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getaudit/{id}")
					.buildAndExpand(auditDto.getAuditid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/addgroup", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addAudit(@RequestBody GroupDetailDTO groupDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {

			groupService.insertGroupDetail(groupDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getaudit/{id}")
					.buildAndExpand(groupDto.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/addsession", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<Void> addSession(@RequestBody SessionDTO sessionDto,
			UriComponentsBuilder ucBuilder,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {

			sessionService.insertSession(sessionDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/getuser/{id}")
					.buildAndExpand(sessionDto.getUserid()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/updateroleandstatus/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<Void> updateRole(@PathVariable int id,
			@RequestBody UserDTO userDto,
			@RequestHeader(value = "Authorization") String jwt) {
		if (tokenAuthService.isAdmin(jwt)) {

			User currentuser = userService.findUserById(id);

			currentuser.setRole(userDto.getRole());
			currentuser.setIsactive(userDto.getIsactive());

			userService.updateRoleAndStatus(currentuser);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<com.cybage.model.Error> handle(RuntimeException exception){
		com.cybage.model.Error error=new com.cybage.model.Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal server error");
		return new ResponseEntity<com.cybage.model.Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
