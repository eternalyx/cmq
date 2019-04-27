package com.cmq.service;

import com.cmq.bo.response.FunctionTreeResponseBO;

public interface FunctionService {

    FunctionTreeResponseBO findAllFunctionsAsTree();
}
