package cn.parkmanasys.service.transactionstatusinfo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.PlateNumberMapper;
import cn.parkmanasys.dao.TransactionStatusInfoMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.entity.Transaction;
import cn.parkmanasys.entity.TransactionStatusInfo;

@Service
public class TransactionStatusInfoServiceImpl implements TransactionStatusInfoService{

	@PersistenceContext
	private EntityManager em;
	@Resource
	private TransactionStatusInfoMapper transactionStatusInfoMapper;

	
	/*@Override
	public Transaction updateTransaction(Transaction TransactionUpdate) {
		PlateNumber plateNumber = null;

		try {
			plateNumber = plateNumberMapper.findOne(plateNumberUpdate.getId());
			
			if(plateNumberUpdate.getPlateNumber() != null){
				plateNumber.setPlateNumber(plateNumberUpdate.getPlateNumber());
			}

			plateNumber = plateNumberMapper.save(plateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return plateNumber;
	}*/

	@Override
	public TransactionStatusInfo updateTransaction(TransactionStatusInfo TransactionUpdate) {
		TransactionStatusInfo  transactionStatusInfo =null;
		TransactionUpdate.setTransactionStatusId(5);
		try {
			transactionStatusInfo = transactionStatusInfoMapper.findOne(TransactionUpdate.getId());
			
			if(TransactionUpdate.getTransactionStatusId() != null){
				transactionStatusInfo.setTransactionStatusId(TransactionUpdate.getTransactionStatusId());
			}

			transactionStatusInfo = transactionStatusInfoMapper.save(transactionStatusInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return transactionStatusInfo;
	}


	@Override
	public TransactionStatusInfo updateTransactiontype(TransactionStatusInfo TransactionUpdate) {
		TransactionStatusInfo  transactionStatusInfo =null;
		TransactionUpdate.setTransactionStatusId(4);
		try {
			transactionStatusInfo = transactionStatusInfoMapper.findOne(TransactionUpdate.getId());
			
			if(TransactionUpdate.getTransactionStatusId() != null){
				transactionStatusInfo.setTransactionStatusId(TransactionUpdate.getTransactionStatusId());
			}

			transactionStatusInfo = transactionStatusInfoMapper.save(transactionStatusInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return transactionStatusInfo;
	}


	@Override
	public TransactionStatusInfo updateTransactionok(TransactionStatusInfo TransactionUpdate) {
		TransactionStatusInfo  transactionStatusInfo =null;
		TransactionUpdate.setTransactionStatusId(1);
		try {
			transactionStatusInfo = transactionStatusInfoMapper.findOne(TransactionUpdate.getId());
			
			if(TransactionUpdate.getTransactionStatusId() != null){
				transactionStatusInfo.setTransactionStatusId(TransactionUpdate.getTransactionStatusId());
			}

			transactionStatusInfo = transactionStatusInfoMapper.save(transactionStatusInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return transactionStatusInfo;
	}
	
}
