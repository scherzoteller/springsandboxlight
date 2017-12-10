package com.scherzoteller.springsandbox.web.gss;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scherzoteller.springsandbox.web.gss.beans.GSSMultiResponse;
import com.scherzoteller.springsandbox.web.gss.beans.GssRequest;

@Controller
@RequestMapping("/gss/geocoding")
public class GssController {
    
    
    @RequestMapping("/list")
    @ResponseBody
    public GSSMultiResponse list(@RequestBody GssRequest request){
        
        return success();
    }

    private GSSMultiResponse success() {
        final GSSMultiResponse response = new GSSMultiResponse();
        response.setSuccess(true);
        return response;
    }
}
