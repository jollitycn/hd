package com.insigma.ordercenter.service;

import com.insigma.ordercenter.logistics.sf.qiao.QuerySFRoute;
import com.insigma.ordercenter.service.impl.ZtoRequest;
import hdjf.EdbTradeAdd;
import hdjf.EdbTradeAudit;
import hdjf.EdbTradeCancel;
import hdjf.EdbTradeGet;

import java.io.IOException;

public interface IHDJFService {
    /**
     * 获取订单
     */
    String edbTradeGet( EdbTradeGet edbTradeGet);

    /**
     * 添加订单
     */
    String  edbTradeAdd( EdbTradeAdd bean);

    /**
     * 订单作废
     */
    String edbTradeCancel( EdbTradeCancel bean);

    /**
     * 订单确认
     */
    String edbTradeAudit( EdbTradeAudit bean);

//    String route(QuerySFRoute querySFRoute);


    String searchZTKYRoutes (  ZtoRequest querySFRoute ) throws IOException;

    String searchYTRoutes (String number ) throws IOException;

    String searchZTKDRoutes ( String   number) throws IOException;
}
