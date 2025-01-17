package com.lcaohoanq.samplewebapplication.responses;

import com.lcaohoanq.samplewebapplication.responses.base.BaseResponse;
import java.util.Map;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ExceptionResponse extends BaseResponse<Object> {

    private Map<String, Object> details;

}
