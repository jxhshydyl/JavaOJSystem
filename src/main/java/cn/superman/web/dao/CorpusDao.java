package cn.superman.web.dao;

import cn.superman.web.dao.base.MyBatisRepository;
import cn.superman.web.dto.Corpus;

import java.util.List;

@MyBatisRepository
public interface CorpusDao {
    List<Corpus> getCorpus();
}
