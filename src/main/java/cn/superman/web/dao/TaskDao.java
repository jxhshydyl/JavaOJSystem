package cn.superman.web.dao;

import cn.superman.web.dao.base.MyBatisRepository;
import cn.superman.web.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisRepository
public interface TaskDao {

    List<TaskDTO> getTask(CourseDTO courseDTO);

    List<CourseDTO> getCourse(String sno);

    List<CodeDTO> getDetailTask(String tid);

    List<Stask> queryStask(@Param("sno") String sno, @Param("tid") Integer tid);

    int insertStask(Stask stask);

    StaskDetail queryStaskDetail(@Param("id") Long id,@Param("qid")Integer qid);

    int updateStaskDetail(StaskDetail staskDetail);

    int insertStaskDetail(StaskDetail staskDetail);

    List<StaskDetail> getSimCode(@Param("qid") Integer qid,@Param("sno") String sno);

}
