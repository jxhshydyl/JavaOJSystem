package cn.superman.web.service.front;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.superman.web.dao.SubmitRecordDao;
import cn.superman.web.dto.CodeDTO;
import cn.superman.web.dto.MyRecord;
import cn.superman.web.po.SubmitRecord;
import cn.superman.web.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.superman.web.dao.ProblemDao;
import cn.superman.web.dao.base.BaseDao;
import cn.superman.web.po.Problem;
import cn.superman.web.service.page.PageResult;
import cn.superman.web.service.page.PageService;

@Service
public class ProblemService extends PageService<Problem, Problem> {
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    SubmitRecordDao submitRecordDao;

    private static Problem defaultCondition;
    static {
        defaultCondition = new Problem();
        defaultCondition.setIsPublish(true);
    }

    public PageResult<Problem> getPageByLikeName(int pageShowCount, int wantPaegNumber, String likeProblemName) {
        List<Problem> list = problemDao.findPulishProblemByLikeName(likeProblemName);

        PageInfo<Problem> info = new PageInfo<Problem>(list);
        PageResult<Problem> pageResult = new PageResult<Problem>();
        pageResult.setResult(list);
        pageResult.setTotalCount(info.getTotal());
        pageResult.setCurrentPage(info.getPageNum());
        pageResult.setTotalPage(info.getPages());
        return pageResult;
    }

    public PageResult<CodeDTO> getList(int currentPage, int pageShowCount, int wantPageNumber,String problemTypeName){
        PageResult<CodeDTO> pageResult=new PageResult<>();
        List<CodeDTO> list = problemDao.getList( currentPage,  pageShowCount,  wantPageNumber,problemTypeName);
        PageInfo<CodeDTO> info = new PageInfo<CodeDTO>(list);
        pageResult.setResult(list);
        pageResult.setTotalCount(info.getTotal());
        pageResult.setCurrentPage(info.getPageNum());
        pageResult.setTotalPage(info.getPages());
        return pageResult;
    }

    @Override
    public BaseDao<Problem, Problem> getUseDao() {
        return problemDao;
    }

    @Override
    public Problem getDefaultCondition() {
        return defaultCondition;
    }

    public Problem getProblemById(Integer problemId) {
        return problemDao.findById(problemId);
    }

    public Map<String,Object> querySubmitCount(User user,BigInteger problemId){
        Integer userId=null;
        if(user!=null){
            userId=user.getUserId();
        }
        List<MyRecord> myRecords = submitRecordDao.querySubmitCount(userId, problemId);
        MyRecord myRecord1=null;
        if(myRecords!=null){
            Integer rightSubmitCount=0;
            Integer totalSubmitCount=0;
            myRecord1=new MyRecord();
            for(MyRecord myRecord:myRecords){
                totalSubmitCount+=myRecord.getSubmitCount();
                if("1".equals(myRecord.getIsAccepted())){
                    rightSubmitCount=myRecord.getRightSubmitCount();
                }
            }
            myRecord1.setRightSubmitCount(rightSubmitCount);
            myRecord1.setTotalSubmitCount(totalSubmitCount);
        }
        List<String> recentlyWeekDate = getRecentlyWeekDate();
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("problemId",problemId);
        map.put("competitionId",-1);
        map.put("weekStartTime",recentlyWeekDate.get(recentlyWeekDate.size()-1));
        map.put("weekEndTime",recentlyWeekDate.get(0));
        List<SubmitRecord> submitRecords = submitRecordDao.queryMyRecords(map);
        List<Integer> submitCount=new ArrayList<>();
        List<Integer> rightCount=new ArrayList<>();
        for(int i=0;i<recentlyWeekDate.size();i++){
            submitCount.add(0);
            rightCount.add(0);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for(SubmitRecord submitRecord:submitRecords){
            for(int i=0;i<recentlyWeekDate.size();i++){
                if(recentlyWeekDate.get(i).equals(formatter.format(submitRecord.getSubmitTime()))){
                    submitCount.set(i,submitCount.get(i)+1);
                    if(submitRecord.getIsAccepted()==1){
                        rightCount.set(i,rightCount.get(i)+1);
                    }
                }
            }
        }
        Map<String,Object> result=new HashMap<>();
        result.put("myRecord",myRecord1);
        result.put("item",recentlyWeekDate);
        result.put("submitCount",submitCount);
        result.put("rightCount",rightCount);
        System.out.println(result);
        return result;
    }
//  获取当前日期的最近一个星期的日期
    public static List<String> getRecentlyWeekDate(){
        List<String> list=new ArrayList<String>();
        /*Calendar 的 getInstance 方法返回一个 Calendar 对象，其日历字段已由当前日期和时间初始化*/
        Calendar cal = Calendar.getInstance();
        /*获取一周七天的值*/
        for(int i = 0 ; i < 7 ; i++){
            /*获取当前日历的日期的星期数（1:星期天）*/
            int week_index = cal.get(Calendar.DAY_OF_WEEK);
            Date date=cal.getTime();
            /*日期格式化 yyyy-MM-dd M必须大写*/
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            System.out.println(week_index-1+","+dateString);
            list.add(dateString);
            /*将日历日期推后1天*/
            cal.add(cal.DATE,-1);
        }
        return list;
    }

    //判断当前时间日期是否在最近一星期内(是 返回true，否 返回false)
    public static boolean isLatestWeek(Date addtime,Date now){
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -7);  //设置为7天前
        Date before7days = calendar.getTime();   //得到7天前的时间
        if(before7days.getTime() < addtime.getTime()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        System.out.println(getRecentlyWeekDate());
    }
}
