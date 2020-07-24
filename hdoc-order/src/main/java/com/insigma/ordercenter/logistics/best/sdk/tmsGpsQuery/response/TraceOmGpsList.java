package com.insigma.ordercenter.logistics.best.sdk.tmsGpsQuery.response;

public class TraceOmGpsList {
	private SourceConcernedGpsInfoBean sourceConcernedGpsInfoBean;
	private DestConcernedGpsInfoBean destConcernedGpsInfoBean;
	private HistoryGpsInfos historyGpsInfos;

    public SourceConcernedGpsInfoBean getSourceConcernedGpsInfoBean()
    {
        return this.sourceConcernedGpsInfoBean;
    }

    public void setSourceConcernedGpsInfoBean(SourceConcernedGpsInfoBean value)
    {
        this.sourceConcernedGpsInfoBean = value;
    }

    public DestConcernedGpsInfoBean getDestConcernedGpsInfoBean()
    {
        return this.destConcernedGpsInfoBean;
    }

    public void setDestConcernedGpsInfoBean(DestConcernedGpsInfoBean value)
    {
        this.destConcernedGpsInfoBean = value;
    }

    public HistoryGpsInfos getHistoryGpsInfos()
    {
        return this.historyGpsInfos;
    }

    public void setHistoryGpsInfos(HistoryGpsInfos value)
    {
        this.historyGpsInfos = value;
    }


}
