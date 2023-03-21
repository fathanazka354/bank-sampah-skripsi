package com.skripsi.waste_bank.utils;

import com.skripsi.waste_bank.dto.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class MethodGenericService {
    Logger logger = LoggerFactory.getLogger(MethodGenericService.class);
    public <T> ResponseEntity extractDataToResponse(List<T> dataList){
        ResponseData responseData = new ResponseData();
        logger.info(dataList.toString());
        if (dataList.isEmpty()){
            responseData.setData(null);
            responseData.setStatus("Failed");
            logger.info("Data is empty");
            return new ResponseEntity(responseData, HttpStatus.NO_CONTENT);
        }
        responseData.setData(dataList);
        responseData.setStatus("Success");
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    public <T> ResponseEntity<ResponseData<T>> extractDataToResponseSingle(boolean state,T data){
        ResponseData responseData = new ResponseData();
        if (!state){
            responseData.setData(null);
            responseData.setStatus("Success");
            responseData.setMessage(Arrays.asList("Data is empty"));
            return new ResponseEntity(responseData, HttpStatus.NO_CONTENT);
        }
        responseData.setData(data);
        responseData.setStatus("Success");
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    public <T> ResponseEntity<ResponseData<T>> extractDataToResponseSingleCreateUpdate(List<String> message,String data){
        ResponseData responseData = new ResponseData();
        if (!Objects.equals(message.get(0), "")){
            responseData.setData(data);
            responseData.setMessage(message);
            responseData.setStatus("Failed");
            return new ResponseEntity(responseData, HttpStatus.BAD_REQUEST);
        }
        responseData.setData(data);
        responseData.setStatus("Success");
        return new ResponseEntity(responseData, HttpStatus.OK);
    }

    public <T> ResponseEntity<ResponseData<T>> extractDataToResponseDelete(boolean state){
        ResponseData responseData = new ResponseData();

        System.out.println(state);
        if (!state){
            responseData.setData(null);
            responseData.setStatus("Failed");
            responseData.setMessage(Arrays.asList("Data is empty"));
            return new ResponseEntity(responseData, HttpStatus.BAD_REQUEST);
        }
        responseData.setData("Data Removed");
        responseData.setStatus("Success");
        return new ResponseEntity(responseData, HttpStatus.OK);
    }
}
