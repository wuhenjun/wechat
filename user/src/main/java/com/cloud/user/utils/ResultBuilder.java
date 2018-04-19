package com.cloud.user.utils;

import java.io.Serializable;

public class ResultBuilder implements Serializable{
    private static final long serialVersionUID = -1621804642691415409L;

    /**
     * 包装返回对象
     * @param args
     * @return
     */
    public static ResultInfo build(Object... args){
        ResultInfo resultInfo = new ResultInfo();
        try {
            resultInfo.setResult((Integer) args[0]);
            resultInfo.setResultCode(args.length >= 2 ? (Integer) args[1] : 666);
            resultInfo.setResultMsg(args.length == 3 ? (String) args[2] : "");
        }catch (IllegalArgumentException e){
            resultInfo.setResultCode(-777);
            resultInfo.setResultMsg(e.getMessage());
        }
        return resultInfo;
    }
}
