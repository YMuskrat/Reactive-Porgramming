package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.*;
import java.io.*;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;
import javax.jws.WebService;
import java.util.Base64;  

import java.awt.AWTException; 
import java.io.IOException; 
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

@RestController
public class ServiceImplController {
	@GetMapping("/OsName")
	public String getOsName(){
        String os = System.getProperty("os.name");
        return os;
    }

	@GetMapping("/reboot")

    public String reboot() throws Exception{
        try {
            Runtime.getRuntime().exec("shutdown -r -t 15");
            return "System is going to reboot in 15sec";
          }
          catch(IOException e) {
            e.printStackTrace();
            return "failed";
          }
          
	   
        
    }
    @CrossOrigin(origins = "*")
	@GetMapping("/screenshot")
	public String getScreenshot() {
        try{
            System.setProperty("java.awt.headless", "false");
            Robot r = new Robot();
            Rectangle capture =new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
            BufferedImage Image = r.createScreenCapture(capture);
            //byte[] encodedBytes = Base64.getEncoder().encode(Image.getBytes());
            //String encodedFile = Base64.getEncoder().encodeToString(encodedBytes);
            String imgstr = encodeToString(Image, "png");
            return imgstr;}
            catch (AWTException e)
            {
            e.printStackTrace();
            return e.getMessage();
            }
	}
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
    
        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();
    
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
    
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}



