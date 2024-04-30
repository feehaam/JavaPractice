package com.feeham.playground.services.interfaces;

import com.feeham.playground.constants.ApplicationConstants;
import com.feeham.playground.constants.ErrorConstants;
import com.feeham.playground.constants.PropertyConstants;
import com.feeham.playground.utils.MapExtractor;

public interface CommonService extends MapExtractor, ApplicationConstants, PropertyConstants, ErrorConstants {
    default Integer getUserId(){
        return 1;
    }
}
