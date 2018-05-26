package cn.superman.web.service.front;

import cn.superman.system.util.CPlusPlusCompare;
import cn.superman.system.util.DelComments;
import cn.superman.util.DateUtil;
import cn.superman.web.dao.TaskDao;
import cn.superman.web.dto.*;
import cn.superman.web.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;

    public List<TaskDTO> getTask(CourseDTO courseDTO){
        List<TaskDTO> tasks = taskDao.getTask(courseDTO);
        return tasks;
    }

    public List<CourseDTO> getCourse(String sno){
        List<CourseDTO> course = taskDao.getCourse(sno);
        return course;
    }

    public List<CodeDTO> getDetailTask(String tid){
        List<CodeDTO> detailTask = taskDao.getDetailTask(tid);
        return detailTask;
    }

    @Async
    public void insertStudentTask(SubmitTaskProblem submitTaskProblem,Map<String, Object> map,User user){
        try{
            List<Stask> stasks = taskDao.queryStask(user.getIsStudent(), submitTaskProblem.getTid());
            if(stasks!=null && stasks.size()>0){
                StaskDetail staskDetail = taskDao.queryStaskDetail(stasks.get(0).getId(), submitTaskProblem.getProblemId());
                StaskDetail sd=new StaskDetail();
                sd.setId(stasks.get(0).getId());
                sd.setSanswer(submitTaskProblem.getCode());
                sd.setLastsubtime(DateUtil.formatToYYYYMMddHHmm(new Date()));
                sd.setQid(submitTaskProblem.getProblemId());
                sd.setUsertime((Long) map.get("time"));
                sd.setUsermemory((Integer) map.get("memory"));
                if(staskDetail != null){
                    taskDao.updateStaskDetail(sd);
                }else{
                    sd.setSubcount(1);
                    taskDao.insertStaskDetail(sd);
                }
            }else{
                //增加
                Stask stask=new Stask();
                stask.setCno(submitTaskProblem.getCno());
                stask.setSno(user.getIsStudent());
                stask.setSdate(DateUtil.formatToYYYYMMddHHmm(new Date()));
                stask.setTstate("已提交");
                stask.setCno(submitTaskProblem.getCno());
                stask.setTid(submitTaskProblem.getTid());
                taskDao.insertStask(stask);

                StaskDetail sd=new StaskDetail();
                sd.setId(stask.getNewId());
                sd.setSanswer(submitTaskProblem.getCode());
                sd.setLastsubtime(DateUtil.formatToYYYYMMddHHmm(new Date()));
                sd.setQid(submitTaskProblem.getProblemId());
                sd.setUsertime((Long) map.get("time"));
                sd.setUsermemory((Integer) map.get("memory"));
                sd.setSubcount(1);
                taskDao.insertStaskDetail(sd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<String,Object> getSim(SubmitTaskProblem submitTaskProblem,User user){
        List<StaskDetail> simCode = taskDao.getSimCode(submitTaskProblem.getProblemId(), user.getIsStudent());
        System.out.println(simCode);
        String code="";
        double max=0;
        int key=0;
        Map<String,Object> map=new HashMap<>();
        if(submitTaskProblem.getCode()!=null && !"".equals(submitTaskProblem.getCode())){
            //删除注释
           // String s = DelComments.delComments(submitTaskProblem.getCode());
            //去掉空格
            code = submitTaskProblem.getCode().replaceAll("\\s", "");
        }
        CPlusPlusCompare cmp = new CPlusPlusCompare();
        if(simCode!=null && simCode.size()>0){
            for(int i=0;i<simCode.size();i++){
                String str=simCode.get(i).getSanswer();
                if(str!=null && !"".equals(str)){
                    //删除注释
                    //String s = DelComments.delComments(str);
                    //去掉空格
                    str = str.replaceAll("\\s", "");
                    double similarity = cmp.getSimilarity(code,str);
                    if(max<similarity){
                        max=similarity;
                        key=i;
                    }
                }
            }
            BigDecimal b  =   new  BigDecimal(max*100);
            double   f1   =  b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put("sim",f1);
            map.put("staskDetail",simCode.get(key));
        }
        return map;
    }
}
