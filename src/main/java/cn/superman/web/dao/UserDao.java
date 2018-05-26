package cn.superman.web.dao;

import java.util.Date;
import java.util.List;

import cn.superman.web.dto.Students;
import org.apache.ibatis.annotations.Param;

import cn.superman.web.dao.base.BaseDao;
import cn.superman.web.dao.base.MyBatisRepository;
import cn.superman.web.po.User;

@MyBatisRepository
public interface UserDao extends BaseDao<User, User> {

    public void createNewSubmitRecordTable(String tableName);

    public List<User> findWithLastSubmitTimeGap(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

    public void countTotalSolveValue(List<BatchUpdateData> data);

    public void countHaveDoneProblem(List<BatchUpdateData> data);

    public void countRightProblem(List<BatchUpdateData> data);

    Students queryStudent(String sno);
    int updateConfirmStudents(@Param("userId") Integer userId,@Param("sno") String sno);
    public static class BatchUpdateData {
        private String submitRecordTableName;
        private Integer userId;

        public String getSubmitRecordTableName() {
            return submitRecordTableName;
        }

        public void setSubmitRecordTableName(String submitRecordTableName) {
            this.submitRecordTableName = submitRecordTableName;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

    }
}
