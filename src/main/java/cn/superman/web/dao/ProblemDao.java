package cn.superman.web.dao;

import java.math.BigInteger;
import java.util.List;

import cn.superman.web.dto.CodeDTO;
import org.apache.ibatis.annotations.Param;

import cn.superman.web.dao.base.BaseDao;
import cn.superman.web.dao.base.MyBatisRepository;
import cn.superman.web.po.Problem;

@MyBatisRepository
public interface ProblemDao extends BaseDao<Problem, Problem> {
    void userSloveProblem(@Param("userIdData") String userIdData, @Param("problemId") Integer problemId);

    void increaseSubmitProblemCount(Integer problemId);

    List<Problem> findPulishProblemByLikeName(String problemName);

    CodeDTO findExample(BigInteger id);

    List<CodeDTO> getList(@Param("currentPage")int currentPage,@Param("pageShowCount")int pageShowCount, @Param("wantPageNumber")int wantPageNumber,@Param("problemTypeName") String problemTypeName);
}
