package com.cmq.service;

import com.cmq.bo.response.app.FunctionTreeResponseBO;

public interface FunctionService {

    FunctionTreeResponseBO findAllFunctionsAsTree();
}
