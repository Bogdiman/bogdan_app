package com.bogdan.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bogdan on 13-May-17.
 */
@Controller
@Api(value="Test", description="Hai sa testam")
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the Specific info of the product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public String get() {
        return "SAL ALL";
    }
}
