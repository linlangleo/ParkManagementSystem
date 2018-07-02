package cn.parkmanasys.service.transactionstatus;

import java.util.List;

import cn.parkmanasys.entity.TransactionStatus;

public interface TransactionStatusService {
	public List<TransactionStatus> findTransactionStatusInfo();
}
