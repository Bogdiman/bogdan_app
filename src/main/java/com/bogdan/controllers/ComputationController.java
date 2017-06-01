package com.bogdan.controllers;

import com.bogdan.data.Person;
import com.bogdan.services.MapReduceService;
import com.bogdan.services.PermutationService;
import com.bogdan.services.PersonService;
import com.bogdan.services.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Bogdan on 13-May-17.
 */
@Controller
@Api(value="Computations", description="A controller with endpoints for testing the efficiency of VMs or containers")
public class ComputationController {
    @Autowired
    private PersonService personService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private MapReduceService mapReduceService;

    @Autowired
    private PermutationService permutationService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View all the persons")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(method = RequestMethod.POST, value = "/person")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void savePerson(@Valid @RequestBody Person person) {
        personService.save(person);
    }

    @RequestMapping(value = "/echofile", method = RequestMethod.POST)
    @ApiOperation(value = "Send a file to a server and get it back")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseStatus(HttpStatus.OK)
    public void sendFile(@RequestParam("file") MultipartFile file) throws IOException {
        uploadService.uploadFile(file);
    }

    @RequestMapping(value = "/map_reduce", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Calculate the sum of the elements of a list with the given size on multiple threads")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void mapReduce(@RequestParam("listSize") int listSize) throws IOException {
        mapReduceService.mapReduce(listSize);
    }

    @RequestMapping(value = "/permutations", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Generate given permutations of 6 characters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public void permute(@RequestParam("wordSize") int wordSize) throws IOException {
        permutationService.permute(wordSize);
    }
}
