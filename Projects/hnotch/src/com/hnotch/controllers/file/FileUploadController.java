package com.hnotch.controllers.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnotch.util.UnZip;

@Controller
public class FileUploadController {
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
    public String provideUploadInfo(ModelMap model) {
        return "upload";
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
    	JSONObject response = new JSONObject();
    	final List<String> allowedMIMETypes = Arrays.asList("application/xml", "text/xml", "application/x-zip-compressed", "application/x-zip-compressed");
		System.out.println(file.getContentType());
    	String extension = ".xml";
    	String rootDirectory = "E:\\apps\\marrker\\videos";
    	String fileName = UUID.randomUUID().toString()+UUID.randomUUID().toString();
    	String message;
    	BufferedOutputStream stream;
        if (!file.isEmpty()) {
            try {
            	if(allowedMIMETypes.contains(file.getContentType())){
	                if(allowedMIMETypes.indexOf(file.getContentType()) == allowedMIMETypes.size() - 2 ){
	                	extension = ".zip";
	                	stream =
		                        new BufferedOutputStream(new FileOutputStream(new File(rootDirectory + fileName + extension)));
	                	writeBytes(stream, file);
	                	UnZip unZip = new UnZip();
	                	unZip.unZipIt(rootDirectory + fileName + extension, rootDirectory + fileName);
	                }
	                else{
	                	stream =
		                        new BufferedOutputStream(new FileOutputStream(new File(rootDirectory + fileName + extension)));
	                	writeBytes(stream, file);
	                }
	                message = "<div class='alert alert-info'><b>Upload successfully !</b></div>";
                }
                else{
                	message = "<div class='alert alert-danger'><b>Invalid file format !</b></div>";
                }
                response.put("message", message);
                return response.toJSONString();
            } catch (Exception e) {
            	e.printStackTrace();
            	message = "<div class='alert alert-danger'><b>Encountered Exception. Try again !</b></div>";
            	response.put("message", message);
            	return response.toJSONString();
            }
        } else {
        	message = "<div class='alert alert-info'><b>File is empty !</b></div>";
        	response.put("message", message);
        	return response.toJSONString();
        }
    }

	private void writeBytes(BufferedOutputStream stream, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
        stream.write(file.getBytes());
        stream.close();
	}

}
