package cn.parkmanasys.service.transactionstatusinfo;

import cn.parkmanasys.entity.Transaction;
import cn.parkmanasys.entity.TransactionStatusInfo;

public interface TransactionStatusInfoService {
	public TransactionStatusInfo updateTransaction(TransactionStatusInfo TransactionUpdate);
	
	public TransactionStatusInfo updateTransactiontype(TransactionStatusInfo TransactionUpdate);
	
	public TransactionStatusInfo updateTransactionok(TransactionStatusInfo TransactionUpdate);
}
