package cn.superman.web.dao;

import java.math.BigInteger;
import java.util.List;

import cn.superman.web.dao.base.MyBatisRepository;
import cn.superman.web.dto.MyRecord;
import cn.superman.web.po.SubmitRecord;
import cn.superman.web.po.User;
import org.apache.ibatis.annotations.Param;

@MyBatisRepository
public interface SubmitRecordDao {
	void add(SubmitRecord submitRecord);

	void update(SubmitRecord submitRecord);

	void deleteWithCondition(SubmitRecord condition);

	List<SubmitRecord> findWithCondition(SubmitRecord condition);

	long querySubmitRecordotalCountWithCondition(SubmitRecord condition);

	MyRecord queryMyRecord(@Param("user") User user, @Param("problemId") Integer problemId);

    SubmitRecord queryMyRecords(@Param("user") User user, @Param("problemId") BigInteger problemId, @Param("competitionId") Integer competitionId);
}
